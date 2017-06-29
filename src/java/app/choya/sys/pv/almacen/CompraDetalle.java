/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.almacen;

import static app.choya.sys.pv.almacen.CompraDetalle.LOGGER;
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


/**
 *
 * @author Condese
 */
public class CompraDetalle extends GenericDAO implements CompraDetalleI{
    
    static final Logger LOGGER = Logger.getLogger(CompraDetalle.class.getName());
     
    @Override
    public int setCompraDetalle(CompraDetalleBean tdb) throws SqlAppsException {
         
        int resultado = 0;
        int secuencia = 0;
        getConexion(EDriver.MYSQL, EApps.PV);
        
         String sql = "INSERT INTO alm_compra_detalle\n"
                + "(id,\n"
                + "id_compra,\n"
                + "id_articulo,\n"
                + "cantidad,\n"
                + "surtida,\n"
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

            secuencia = new UtilsDao().nextVal("s_alm_compra_detalle");
            ps.setInt(1, secuencia);
            ps.setInt(2, tdb.getId_compra());
            ps.setInt(3, tdb.getId_articulo());
            ps.setInt(4, tdb.getCantidad());
            ps.setInt(5, tdb.getSurtida());
            ps.setString(6, tdb.getEstatus());
            ps.setString(7, tdb.getSerie());
			ps.setInt(8, tdb.getCreado_por());
            ps.setInt(9, tdb.getModificado_por());
            
       resultado = ps.executeUpdate();

            if (resultado != 0) {
                resultado = secuencia;
            }

        } catch (SQLException ex) {
                       
           System.out.println("El metodo setCompraDetalle ha lanzado el siguiente error " + ex.getMessage());
           Logger.getLogger(CompraDetalle.class.getName()).log(Level.SEVERE, "EL metodo setCompraDetalle ha lanzado una excepción ", ex);
           throw new SqlAppsException(ex);
        } finally {

            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(CompraDetalle.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion();
        }

        return resultado;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    @Override
    public int updateCompraDetalle(CompraDetalleBean tdb) throws SqlAppsException {
            int resultado = 0;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "UPDATE alm_compra_detalle\n"
                + "SET\n"
                + "cantidad= ?,\n"
                + "surtida= ?,\n"
                + "estatus= ?,\n"
                + "serie= ? \n"
                + "WHERE id = ?";
        
        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setInt(1, tdb.getCantidad());
            ps.setInt(2, tdb.getSurtida());
            ps.setString(3, tdb.getEstatus());
            ps.setString(4, tdb.getSerie());
            ps.setInt(5, tdb.getModificado_por());
            resultado = ps.executeUpdate();
             System.out.println("Sql update "+ps);
            
        } catch (SQLException ex) {
            System.out.println("el metodo updateArticulo ha lanzado el siguiente error "+ex.getMessage());
            throw  new SqlAppsException(ex);
        }finally{
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(CompraDetalle.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
            
        }

        return resultado;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteCompraDetalle(CompraDetalleBean tdb) throws SqlAppsException {
      int resultado = 0;
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "UPDATE alm_compra_detalle set estatus = 'D', modificado_por = ? WHERE id = ?";
        
        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);
            ps.setInt(1, tdb.getModificado_por());
            ps.setInt(2, tdb.getId());
            
            resultado = ps.executeUpdate();
            
         } catch (SQLException ex) {
            System.out.println("el metodo deleteArticulo ha lanzado el siguiente error "+ex.getMessage());
            throw  new SqlAppsException(ex);
        }finally{
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(CompraDetalle.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
            
        }
        
        
        
        return resultado;
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
    public CompraDetalleBean getCompraDetalle(int id) throws SqlAppsException {
        Statement stmt = null;
        ResultSet rs = null;
        CompraDetalleBean tdb = new CompraDetalleBean();
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "SELECT id,\n" 
                + "id_compra,\n"
                + "id_articulo,\n"
                + "cantidad,\n"
                + "surtida,\n"
                + "estatus,\n"
                + "serie,\n"
                + "creado_por,\n"
                + "modificado_por \n"
                + "FROM alm_compra_detalle \n"
                + " where id = ?";
        
        PreparedStatement ps = null;
        
      
        try {
            ps = getPrepareStatement(sql);
            
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            while(rs.next()){
                 
                tdb.setId(rs.getInt("id"));
                tdb.setId_compra(rs.getInt("id_compra"));
                tdb.setId_articulo(rs.getInt("id_articulo"));
                tdb.setCantidad(rs.getInt("cantidad"));
                tdb.setSurtida(rs.getInt("surtida"));
                tdb.setEstatus(rs.getString("estatus"));
                tdb.setSerie(rs.getString("serie"));
             
                return tdb;
            }
        } catch (SQLException ex) {
                 System.out.println("El método getCompraDetalle(id) a lanzado el siguiente erro "+ex.getMessage());
                throw new SqlAppsException(ex);
            //throw new SqlAppsException(ex, CompraDetalle.class.getName() + "-- metodo: getArticulo", LOGGER);
        }finally{
            if(rs != null){
                try {
                    closeResultSet();
                } catch (SQLException ex) {
                    Logger.getLogger(CompraDetalle.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(ps != null){
                try {
                    closePStmt();
                } catch (SQLException ex) {
                    Logger.getLogger(CompraDetalle.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            closeConexion();
        }
        
            return tdb;
    }

}