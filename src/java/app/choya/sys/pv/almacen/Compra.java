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
public class Compra extends GenericDAO implements CompraI{
    
static final Logger LOGGER = Logger.getLogger(Compra.class.getName());

    @Override
    public int setCompra(CompraBean cb) throws SqlAppsException {
        
        int resultado = 0;
        int secuencia = 0;
        getConexion(EDriver.MYSQL, EApps.PV);
        
         String sql = "INSERT INTO alm_Compra\n"
                + "(id,\n"
                + "id_proveedor,\n"
                + "id_solicita,\n"
                + "id_recibe,\n"
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
                + "?)";
         
        try {
            PreparedStatement ps = getPrepareStatement(sql);

            secuencia = new UtilsDao().nextVal("s_alm_compra");
            ps.setInt(1, secuencia);
            ps.setInt(2, cb.getId_proveedor());
            ps.setInt(3, cb.getId_solicita());
            ps.setInt(4, cb.getId_recibe());
            ps.setString(5, cb.getEstatus());
            ps.setString(6, cb.getSerie());
            ps.setString(7, cb.getObservacion());
            ps.setInt(8, cb.getCreado_por());
            ps.setInt(9, cb.getModificado_por());
            
       resultado = ps.executeUpdate();

            if (resultado != 0) {
                resultado = secuencia;
            }

        } catch (SQLException ex) {
                       
           System.out.println("El metodo setArticulo ha lanzado el siguiente error " + ex.getMessage());
           Logger.getLogger(Compra.class.getName()).log(Level.SEVERE, "EL metodo setCompra ha lanzado una excepción ", ex);
           throw new SqlAppsException(ex);
        } finally {

            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Compra.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion();
        }

        return resultado;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public int updateCompra(CompraBean cb) throws SqlAppsException {
        int resultado = 0;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "UPDATE alm_compra\n"
                + "SET\n"
                + "id_proveedor = ?,\n"
                + "id_recibe= ?,\n"
                + "estatus= ?,\n"
                + "serie= ?,\n"
                + "observacion= ?\n"
                + "WHERE id = ?";
        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setInt(1, cb.getId_proveedor());
            ps.setInt(2, cb.getId_recibe());
            ps.setString(3, cb.getEstatus());
            ps.setString(4, cb.getSerie());
            ps.setString(5, cb.getObservacion());
            ps.setInt(6, cb.getModificado_por());
    
            resultado = ps.executeUpdate();
            
            
        } catch (SQLException ex) {
            System.out.println("el metodo updateCompra ha lanzado el siguiente error "+ex.getMessage());
            throw  new SqlAppsException(ex);
        }finally{
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Compra.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
            
        }

        return resultado;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteCompra(CompraBean cb) throws SqlAppsException {
        
        int resultado = 0;
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "UPDATE alm_compra set estatus = 'D', modificado_por = ? WHERE id = ?";
        
        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);
            ps.setInt(1, cb.getModificado_por());
            ps.setInt(2, cb.getId());
            
            resultado = ps.executeUpdate();
            
         } catch (SQLException ex) {
            System.out.println("el metodo deleteCompra ha lanzado el siguiente error "+ex.getMessage());
            throw  new SqlAppsException(ex);
        }finally{
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Compra.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
            
        }
        
        
        
        return resultado;
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public CompraBean getCompra(int id) throws SqlAppsException {
        
        Statement stmt = null;
        ResultSet rs = null;
        CompraBean cb = new CompraBean();
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "SELECT id,\n" 
                + "id_proveedor,\n"
                + "id_solicita,\n"
                + "id_recibe,\n"
                + "estatus,\n"
                + "serie,\n"
                + "observacion,\n"
                + "creado_por,\n"
                + "modificado_por \n"
                + "FROM alm_Compra \n"
                + " where id = ?";
        
        PreparedStatement ps = null;
        
      
        try {
            ps = getPrepareStatement(sql);
            
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            while(rs.next()){
                 
                cb.setId(rs.getInt("id"));
                cb.setId_proveedor(rs.getInt("id_proveedor"));
                cb.setId_solicita(rs.getInt("id_solicita"));
                cb.setId_recibe(rs.getInt("id_recibe"));
                cb.setEstatus(rs.getString("estatus"));
                cb.setSerie(rs.getString("serie"));
                cb.setObservacion(rs.getString("observacion"));
             
                return cb;
            }
        } catch (SQLException ex) {
                 System.out.println("El método getCompra(id) a lanzado el siguiente erro "+ex.getMessage());
                throw new SqlAppsException(ex);
            //throw new SqlAppsException(ex, Compra.class.getName() + "-- metodo: getArticulo", LOGGER);
        }finally{
            if(rs != null){
                try {
                    closeResultSet();
                } catch (SQLException ex) {
                    Logger.getLogger(Compra.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(ps != null){
                try {
                    closePStmt();
                } catch (SQLException ex) {
                    Logger.getLogger(Compra.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            closeConexion();
        }
        
            return cb;
    }

}