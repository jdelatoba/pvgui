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

public class TraspasoEntrada extends GenericDAO implements TraspasoEntradaI{
    
    static final Logger LOGGER = Logger.getLogger(TraspasoEntrada.class.getName());

    @Override
    public int setTraspasoEntrada(TraspasoEntradaBean te) throws SqlAppsException {
        int resultado = 0;
        int secuencia = 0;
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "INSERT INTO alm_traspaso_entrada\n"
                + "(id,\n"
		+ "id_traspaso_salida,\n"
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

            secuencia = new UtilsDao().nextVal("s_alm_traspaso_entrada");
            ps.setInt(1, secuencia);
	    ps.setInt(2, te.getId_traspaso_salida());
            ps.setInt(3, te.getId_almacen_origen());
            ps.setInt(4, te.getId_solicita());
            ps.setInt(5, te.getId_almacen_destino());
            ps.setString(6, te.getEstatus());
            ps.setString(7, te.getSerie());
            ps.setString(8, te.getObservacion());
            ps.setInt(9, te.getCreado_por());
            ps.setInt(10, te.getModificado_por());
            
        resultado = ps.executeUpdate();

            if (resultado != 0) {
                resultado = secuencia;
            }

        } catch (SQLException ex) {
                       
           System.out.println("El metodo setTraspasoEntrada ha lanzado el siguiente error " + ex.getMessage());
           Logger.getLogger(TraspasoEntrada.class.getName()).log(Level.SEVERE, "EL metodo setTraspasoEntrada ha lanzado una excepción ", ex);
           throw new SqlAppsException(ex);
        } finally {

            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(TraspasoEntrada.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion();
        }

        return resultado;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateTraspasoEntrada(TraspasoEntradaBean te) throws SqlAppsException {
               int resultado = 0;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "UPDATE alm_traspaso_entrada\n"
                + "SET\n"
                + "id_almacen_destino= ?,\n"
                + "estatus= ?,\n"
                + "serie= ?,\n"
                + "observacion= ?\n"
                + "WHERE id = ?";
        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setInt(1, te.getId_almacen_destino());
            ps.setString(2, te.getEstatus());
            ps.setString(3, te.getSerie());
            ps.setString(4, te.getObservacion());
            ps.setInt(5, te.getModificado_por());
    
            resultado = ps.executeUpdate();
            
            
        } catch (SQLException ex) {
            System.out.println("el metodo updateTraspasoEntrada ha lanzado el siguiente error "+ex.getMessage());
            throw  new SqlAppsException(ex);
        }finally{
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(TraspasoEntrada.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
            
        }

        return resultado;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteTraspasoEntrada(TraspasoEntradaBean te) throws SqlAppsException {
               int resultado = 0;
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "UPDATE alm_traspaso_entrada set estatus = 'D', modificado_por = ? WHERE id = ?";
        
        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);
            ps.setInt(1, te.getModificado_por());
            ps.setInt(2, te.getId());
            
            resultado = ps.executeUpdate();
            
         } catch (SQLException ex) {
            System.out.println("el metodo deleteTraspasoEntrada ha lanzado el siguiente error "+ex.getMessage());
            throw  new SqlAppsException(ex);
        }finally{
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(TraspasoEntrada.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
            
        }
        
        
        
        return resultado;
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TraspasoEntradaBean getTraspasoEntrada(int id) throws SqlAppsException {
        Statement stmt = null;
        ResultSet rs = null;
        TraspasoEntradaBean te = new TraspasoEntradaBean();
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "SELECT id,\n" 
		+ "id_traspaso_salida,\n"
                + "id_almacen_origen,\n"
                + "id_solicita,\n"
                + "id_almacen_destino,\n"
                + "estatus,\n"
                + "serie,\n"
                + "observacion,\n"
                + "creado_por,\n"
                + "modificado_por \n"
                + "FROM alm_traspaso_entrada \n"
                + " where id = ?";
        
        PreparedStatement ps = null;
        
      
        try {
            ps = getPrepareStatement(sql);
            
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            while(rs.next()){
                 
                te.setId(rs.getInt("id"));
		te.setId_almacen_origen(rs.getInt("id_traspaso_salida"));
                te.setId_almacen_origen(rs.getInt("id_almacen_origen"));
                te.setId_solicita(rs.getInt("id_solicita"));
                te.setId_almacen_destino(rs.getInt("id_almacen_destino"));
                te.setEstatus(rs.getString("estatus"));
                te.setSerie(rs.getString("serie"));
                te.setObservacion(rs.getString("observacion"));
             
                return te;
            }
        } catch (SQLException ex) {
                 System.out.println("El método getTraspaso(id) a lanzado el siguiente erro "+ex.getMessage());
                throw new SqlAppsException(ex);
            //throw new SqlAppsException(ex, TraspasoEntrada.class.getName() + "-- metodo: getArticulo", LOGGER);
        }finally{
            if(rs != null){
                try {
                    closeResultSet();
                } catch (SQLException ex) {
                    Logger.getLogger(TraspasoEntrada.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(ps != null){
                try {
                    closePStmt();
                } catch (SQLException ex) {
                    Logger.getLogger(TraspasoEntrada.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            closeConexion();
        }
        
            return te;
    }


    @Override
    public ListIterator<TraspasoEntradaBean> getTraspasoEntrada() throws SqlAppsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<TraspasoEntradaBean> getTraspasoEntrada(String campo, String buscar) throws SqlAppsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<TraspasoEntradaBean> getTraspasoEntradaBean(int id) throws SqlAppsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
   
}
