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
public class TraspasoSolicitud extends GenericDAO implements TraspasoSolicitudI{
    
    static final Logger LOGGER = Logger.getLogger(TraspasoSolicitud.class.getName());

    @Override
    public int setTraspasoSolicitud(TraspasoSolicitudBean tb) throws SqlAppsException {
        int resultado = 0;
        int secuencia = 0;
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "INSERT INTO alm_traspaso_solicitud\n"
                + "(id,\n"
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
                + "?)";
         
        try {
            PreparedStatement ps = getPrepareStatement(sql);

            secuencia = new UtilsDao().nextVal("s_alm_traspaso_solicitud");
            ps.setInt(1, secuencia);
            ps.setInt(2, tb.getId_almacen_origen());
            ps.setInt(3, tb.getId_solicita());
            ps.setInt(4, tb.getId_almacen_destino());
            ps.setString(5, tb.getEstatus());
            ps.setString(6, tb.getSerie());
            ps.setString(7, tb.getObservacion());
            ps.setInt(8, tb.getCreado_por());
            ps.setInt(9, tb.getModificado_por());
            
        resultado = ps.executeUpdate();

            if (resultado != 0) {
                resultado = secuencia;
            }

        } catch (SQLException ex) {
                       
           System.out.println("El metodo setTraspasoSolicitud ha lanzado el siguiente error " + ex.getMessage());
           Logger.getLogger(TraspasoSolicitud.class.getName()).log(Level.SEVERE, "EL metodo setTraspasoSolicitud ha lanzado una excepción ", ex);
           throw new SqlAppsException(ex);
        } finally {

            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(TraspasoSolicitud.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion();
        }

        return resultado;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateTraspasoSolicitud(TraspasoSolicitudBean tb) throws SqlAppsException {
               int resultado = 0;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "UPDATE alm_traspaso_solicitud\n"
                + "SET\n"
                + "id_almacen_destino= ?,\n"
                + "estatus= ?,\n"
                + "serie= ?,\n"
                + "observacion= ?\n"
                + "WHERE id = ?";
        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setInt(1, tb.getId_almacen_destino());
            ps.setString(2, tb.getEstatus());
            ps.setString(3, tb.getSerie());
            ps.setString(4, tb.getObservacion());
            ps.setInt(5, tb.getModificado_por());
    
            resultado = ps.executeUpdate();
            
            
        } catch (SQLException ex) {
            System.out.println("el metodo updateTraspasoSolicitud ha lanzado el siguiente error "+ex.getMessage());
            throw  new SqlAppsException(ex);
        }finally{
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(TraspasoSolicitud.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
            
        }

        return resultado;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteTraspasoSolicitud(TraspasoSolicitudBean tb) throws SqlAppsException {
               int resultado = 0;
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "UPDATE alm_traspaso_solicitud set estatus = 'D', modificado_por = ? WHERE id = ?";
        
        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);
            ps.setInt(1, tb.getModificado_por());
            ps.setInt(2, tb.getId());
            
            resultado = ps.executeUpdate();
            
         } catch (SQLException ex) {
            System.out.println("el metodo deleteTraspasoSolicitud ha lanzado el siguiente error "+ex.getMessage());
            throw  new SqlAppsException(ex);
        }finally{
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(TraspasoSolicitud.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
            
        }
        
        
        
        return resultado;
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TraspasoSolicitudBean getTraspasoSolicitud(int id) throws SqlAppsException {
       Statement stmt = null;
        ResultSet rs = null;
        TraspasoSolicitudBean tb = new TraspasoSolicitudBean();
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "SELECT id,\n" 
                + "id_almacen_origen,\n"
                + "id_solicita,\n"
                + "id_almacen_destino,\n"
                + "estatus,\n"
                + "serie,\n"
                + "observacion,\n"
                + "creado_por,\n"
                + "modificado_por \n"
                + "FROM alm_traspaso_solicitud \n"
                + " where id = ?";
        
        PreparedStatement ps = null;
        
      
        try {
            ps = getPrepareStatement(sql);
            
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            while(rs.next()){
                 
                tb.setId(rs.getInt("id"));
                tb.setId_almacen_origen(rs.getInt("id_almacen_origen"));
                tb.setId_solicita(rs.getInt("id_solicita"));
                tb.setId_almacen_destino(rs.getInt("id_almacen_destino"));
                tb.setEstatus(rs.getString("estatus"));
                tb.setSerie(rs.getString("serie"));
                tb.setObservacion(rs.getString("observacion"));
             
                return tb;
            }
        } catch (SQLException ex) {
                 System.out.println("El método getTraspaso(id) a lanzado el siguiente erro "+ex.getMessage());
                throw new SqlAppsException(ex);
            //throw new SqlAppsException(ex, TraspasoSolicitud.class.getName() + "-- metodo: getArticulo", LOGGER);
        }finally{
            if(rs != null){
                try {
                    closeResultSet();
                } catch (SQLException ex) {
                    Logger.getLogger(TraspasoSolicitud.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(ps != null){
                try {
                    closePStmt();
                } catch (SQLException ex) {
                    Logger.getLogger(TraspasoSolicitud.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            closeConexion();
        }
        
            return tb;
    }


    @Override
    public ListIterator<TraspasoSolicitudBean> getTraspasoSolicitud() throws SqlAppsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<TraspasoSolicitudBean> getTraspasoSolicitud(String campo, String buscar) throws SqlAppsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<TraspasoSolicitudBean> getTraspasoSolicitudBean(int id) throws SqlAppsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
   
}
