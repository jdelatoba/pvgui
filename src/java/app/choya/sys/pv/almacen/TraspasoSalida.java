/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.almacen;

import app.choya.sys.pv.dao.EApps;
import app.choya.sys.pv.dao.EDriver;
import app.choya.sys.pv.dao.GenericDAO;
import app.choya.sys.pv.utils.SqlAppsException;
import app.choya.sys.pv.utils.UtilsDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/**
 *
 * @author Condese
 */
public class TraspasoSalida extends GenericDAO implements TraspasoSalidaI{
    
    static final Logger LOGGER = Logger.getLogger(TraspasoSalida.class.getName());

    @Override
    public int setTraspasoSalida(TraspasoSalidaBean ts) throws SqlAppsException {
        int resultado = 0;
        int secuencia = 0;
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "INSERT INTO alm_traspaso_salida\n"
                + "(id,\n"
		+ "id_traspaso_solicitud,\n"
                + "id_almacen_origen,\n"
                + "id_solicita,\n"
                + "id_almacen_destino,\n"
                + "estatus,\n"
                + "serie,\n"
                + "observacion,\n"
                + "creado_por,\n"
                + "modificado_por)\n"
                + "VALUES\n"
                + "(?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?)";
         
        try {
            PreparedStatement ps = getPrepareStatement(sql);

            secuencia = new UtilsDao().nextVal("s_alm_traspaso_salida");
            ps.setInt(1, secuencia);
	    ps.setInt(2, ts.getId_traspaso_solicitud());
            ps.setInt(3, ts.getId_almacen_origen());
            ps.setInt(4, ts.getId_solicita());
            ps.setInt(5, ts.getId_almacen_destino());
            ps.setString(6, ts.getEstatus());
            ps.setString(7, ts.getSerie());
            ps.setString(8, ts.getObservacion());
            ps.setInt(9, ts.getCreado_por());
            ps.setInt(10, ts.getModificado_por());
            
        resultado = ps.executeUpdate();

            if (resultado != 0) {
                resultado = secuencia;
            }

        } catch (SQLException ex) {
                       
           System.out.println("El metodo setTraspasoSalida ha lanzado el siguiente error " + ex.getMessage());
           Logger.getLogger(TraspasoSalida.class.getName()).log(Level.SEVERE, "EL metodo setTraspasoSalida ha lanzado una excepción ", ex);
           throw new SqlAppsException(ex);
        } finally {

            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(TraspasoSalida.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion();
        }

        return resultado;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateTraspasoSalida(TraspasoSalidaBean ts) throws SqlAppsException {
               int resultado = 0;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "UPDATE alm_traspaso_salida\n"
                + "SET\n"
                + "id_almacen_destino= ?,\n"
                + "estatus= ?,\n"
                + "serie= ?,\n"
                + "observacion= ?\n"
                + "WHERE id = ?";
        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setInt(1, ts.getId_almacen_destino());
            ps.setString(2, ts.getEstatus());
            ps.setString(3, ts.getSerie());
            ps.setString(4, ts.getObservacion());
            ps.setInt(5, ts.getModificado_por());
    
            resultado = ps.executeUpdate();
            
            
        } catch (SQLException ex) {
            System.out.println("el metodo updateTraspasoSalida ha lanzado el siguiente error "+ex.getMessage());
            throw  new SqlAppsException(ex);
        }finally{
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(TraspasoSalida.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
            
        }

        return resultado;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteTraspasoSalida(TraspasoSalidaBean ts) throws SqlAppsException {
               int resultado = 0;
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "UPDATE alm_traspaso_salida set estatus = 'D', modificado_por = ? WHERE id = ?";
        
        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);
            ps.setInt(1, ts.getModificado_por());
            ps.setInt(2, ts.getId());
            
            resultado = ps.executeUpdate();
            
         } catch (SQLException ex) {
            System.out.println("el metodo deleteTraspasoSalida ha lanzado el siguiente error "+ex.getMessage());
            throw  new SqlAppsException(ex);
        }finally{
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(TraspasoSalida.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
            
        }
        
        
        
        return resultado;
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TraspasoSalidaBean getTraspasoSalida(int id) throws SqlAppsException {
        Statement stmt = null;
        ResultSet rs = null;
        TraspasoSalidaBean ts = new TraspasoSalidaBean();
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "SELECT id,\n" 
		+ "id_traspaso_solicitud,\n"
                + "id_almacen_origen,\n"
                + "id_solicita,\n"
                + "id_almacen_destino,\n"
                + "estatus,\n"
                + "serie,\n"
                + "observacion,\n"
                + "creado_por,\n"
                + "modificado_por \n"
                + "FROM alm_traspaso_salida \n"
                + " where id = ?";
        
        PreparedStatement ps = null;
        
      
        try {
            ps = getPrepareStatement(sql);
            
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            while(rs.next()){
                 
                ts.setId(rs.getInt("id"));
		ts.setId_almacen_origen(rs.getInt("id_traspaso_solicitud"));
                ts.setId_almacen_origen(rs.getInt("id_almacen_origen"));
                ts.setId_solicita(rs.getInt("id_solicita"));
                ts.setId_almacen_destino(rs.getInt("id_almacen_destino"));
                ts.setEstatus(rs.getString("estatus"));
                ts.setSerie(rs.getString("serie"));
                ts.setObservacion(rs.getString("observacion"));
             
                return ts;
            }
        } catch (SQLException ex) {
                 System.out.println("El método getTraspaso(id) a lanzado el siguiente erro "+ex.getMessage());
                throw new SqlAppsException(ex);
            //throw new SqlAppsException(ex, TraspasoSalida.class.getName() + "-- metodo: getArticulo", LOGGER);
        }finally{
            if(rs != null){
                try {
                    closeResultSet();
                } catch (SQLException ex) {
                    Logger.getLogger(TraspasoSalida.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(ps != null){
                try {
                    closePStmt();
                } catch (SQLException ex) {
                    Logger.getLogger(TraspasoSalida.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            closeConexion();
        }
        
            return ts;
    }


    @Override
    public ListIterator<TraspasoSalidaBean> getTraspasoSalida() throws SqlAppsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<TraspasoSalidaBean> getTraspasoSalida(String campo, String buscar) throws SqlAppsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<TraspasoSalidaBean> getTraspasoSalidaBean(int id) throws SqlAppsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
   
}

