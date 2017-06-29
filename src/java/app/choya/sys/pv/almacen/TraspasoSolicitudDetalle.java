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
public class TraspasoSolicitudDetalle extends GenericDAO implements TraspasoSolicitudDetalleI{

     static final Logger LOGGER = Logger.getLogger(TraspasoSolicitudDetalle.class.getName());

    @Override
    public int setTraspasoSolicitudDetalle(TraspasoSolicitudDetalleBean tdb) throws SqlAppsException {
        int resultado = 0;
        int secuencia = 0;
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "INSERT INTO alm_traspaso_detalle_solicitud\n"
                + "(id,\n"
                + "id_traspaso_solicitud,\n"
                + "id_articulo,\n"
                + "cantidad,\n"
		+ "sugerida,\n"
		+ "existencia_origen,\n"
		+ "existencia_destino,\n"
                + "estatus)\n"
                + "VALUES\n"
                + "(?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
	        + "?,\n"
		+ "?,\n"
		+ "?,\n"
                + "?)";
         
        try {
            PreparedStatement ps = getPrepareStatement(sql);

            secuencia = new UtilsDao().nextVal("s_alm_traspaso_detalle_solicitud");
            ps.setInt(1, secuencia);
            ps.setInt(2, tdb.getId_traspaso_solicitud());
            ps.setInt(3, tdb.getId_articulo());
            ps.setInt(4, tdb.getCantidad());
            ps.setInt(5, tdb.getSugerida());
	    ps.setInt(6, tdb.getExistencia_origen());
	    ps.setInt(7, tdb.getExistencia_destino());
	    ps.setString(8, tdb.getEstatus());
            
        resultado = ps.executeUpdate();

            if (resultado != 0) {
                resultado = secuencia;
            }

        } catch (SQLException ex) {
                       
           System.out.println("El metodo setTraspasoSolicitudDetalle ha lanzado el siguiente error " + ex.getMessage());
           Logger.getLogger(TraspasoSolicitudDetalle.class.getName()).log(Level.SEVERE, "EL metodo setTraspasoSolicitudDetalle ha lanzado una excepción ", ex);
           throw new SqlAppsException(ex);
        } finally {

            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(TraspasoSolicitudDetalle.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion();
        }

        return resultado;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateTraspasoSolicitudDetalle(TraspasoSolicitudDetalleBean tdb) throws SqlAppsException {
               int resultado = 0;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "UPDATE alm_traspaso_solicitud\n"
                + "SET\n"
                + "cantidad= ?,\n"
		+ "sugerida= ?,\n"
		+ "existencia_origen= ?,\n"
		+ "existencia_destino= ?,\n"
		+ "estatus= ?\n"
                + "WHERE id = ? and id_articulo = ?";
        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setInt(1, tdb.getCantidad());
	    ps.setInt(2, tdb.getSugerida());
	    ps.setInt(3, tdb.getExistencia_origen());
	    ps.setInt(4, tdb.getExistencia_destino());
            ps.setString(5, tdb.getEstatus());
            ps.setInt(6, tdb.getId());
            ps.setInt(7, tdb.getId_articulo());
            
             System.out.println("sql update" + ps);
            resultado = ps.executeUpdate();
            
            
        } catch (SQLException ex) {
            System.out.println("el metodo updateTraspasoSolicitudDetalle ha lanzado el siguiente error "+ex.getMessage());
            throw  new SqlAppsException(ex);
        }finally{
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(TraspasoSolicitudDetalle.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
            
        }

        return resultado;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteTraspasoSolicitudDetalle(TraspasoSolicitudDetalleBean tb) throws SqlAppsException {
           int resultado = 0;
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "UPDATE alm_traspaso_solicitud_detalle set estatus = 'D', modificado_por = ? WHERE id = ?";
        
        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);
            ps.setInt(1, tb.getModificado_por());
            ps.setInt(2, tb.getId());
            
            resultado = ps.executeUpdate();
            
         } catch (SQLException ex) {
            System.out.println("el metodo deleteTraspasoSolicitudDetalle ha lanzado el siguiente error "+ex.getMessage());
            throw  new SqlAppsException(ex);
        }finally{
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(TraspasoSolicitudDetalle.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
            
        }
        
        
        
        return resultado;
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public TraspasoSolicitudDetalleBean getTraspasoSolicitudDetalle(int id) throws SqlAppsException {
        Statement stmt = null;
        ResultSet rs = null;
        TraspasoSolicitudDetalleBean tb = new TraspasoSolicitudDetalleBean();
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "SELECT id,\n" 
                + "id_traspaso_solicitud,\n"
                + "id_articulo,\n"
                + "cantidad,\n"
		+ "sugerida,\n"
		+ "existencia_origen,\n"
		+ "existencia_destino,\n"
                + "estatus,\n"
                + "creado_por,\n"
                + "modificado_por \n"
                + "FROM alm_traspaso_solicitud_detalle \n"
                + " where id = ?";
        
        PreparedStatement ps = null;
        
      
        try {
            ps = getPrepareStatement(sql);
            
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            while(rs.next()){
                 
                tb.setId(rs.getInt("id"));
                tb.setId_traspaso_solicitud(rs.getInt("id_traspaso_solicitud"));
		tb.setId_articulo(rs.getInt("id_articulo"));
		tb.setCantidad(rs.getInt("cantidad"));
                tb.setSugerida(rs.getInt("sugerida"));
		tb.setExistencia_origen(rs.getInt("existencia_origen"));
		tb.setExistencia_destino(rs.getInt("existencia_destino"));
                tb.setEstatus(rs.getString("estatus"));
                             
                return tb;
            }
        } catch (SQLException ex) {
                 System.out.println("El método getTraspasoSolicitudDetalle(id) a lanzado el siguiente erro "+ex.getMessage());
                throw new SqlAppsException(ex);
            //throw new SqlAppsException(ex, TraspasoSolicitudDetalle.class.getName() + "-- metodo: getArticulo", LOGGER);
        }finally{
            if(rs != null){
                try {
                    closeResultSet();
                } catch (SQLException ex) {
                    Logger.getLogger(TraspasoSolicitudDetalle.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(ps != null){
                try {
                    closePStmt();
                } catch (SQLException ex) {
                    Logger.getLogger(TraspasoSolicitudDetalle.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            closeConexion();
        }
        
            return tb;
    }

    @Override
    public ListIterator<TraspasoSolicitudDetalleBean> getTraspasoSolicitudDetalle() throws SqlAppsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<TraspasoSolicitudDetalleBean> getTraspasoSolicitudDetalle(String campo, String buscar) throws SqlAppsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<TraspasoSolicitudBean> getTraspasoSolicitudBean(int id) throws SqlAppsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
