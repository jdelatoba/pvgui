/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.catalogo;

import app.choya.sys.pv.dao.EApps;
import app.choya.sys.pv.dao.EDriver;
import app.choya.sys.pv.dao.GenericDAO;
import app.choya.sys.pv.utils.SqlAppsException;
import app.choya.sys.pv.utils.UtilsDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Rembao
 */
public class ArticuloCompatible extends GenericDAO{

    public static final Logger LOGGER = Logger.getLogger(Articulo.class.getName());
    
    public ArticuloCompatible() {
    }
    
    public int setArticuloCompatible(ArticuloCompatibleBean ab) throws SqlAppsException{
        
        int resultado = 0;
        PreparedStatement ps = null;
        
        Connection cnn = getConexionSinCommit(EDriver.MYSQL, EApps.PV);
        
        String sql = "INSERT INTO cat_articulo_compatible\n" +
        "(id,\n" +
        "id_articulo,\n" +
        "creado_por,\n" +
        "modificado_por,\n" +
        "serie,\n" +
        "sucursal)\n" +
        "VALUES\n" +
        "(?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?)";
        
        try {
            ps = cnn.prepareStatement(sql);
            
            int secuencia = new UtilsDao().nextVal("s_articulo_compatible", cnn);
            
            ps.setInt(1, secuencia);
            ps.setInt(2, ab.getId_articulo());
            ps.setInt(3, ab.getCreado_por());
            ps.setInt(4, ab.getModificado_por());
            ps.setString(5, ab.getSerie());
            ps.setInt(6, ab.getSucursal());
            
            resultado = ps.executeUpdate();
            commit();
        } catch (SQLException ex) {
            rollback();
            
            throw new SqlAppsException(ex, ArticuloCompatible.class.getName()+" el metodo setArticuloCompatible a lanzado un error ", LOGGER);
            
        }finally{
            if(ps != null){
            
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ArticuloCompatible.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(cnn != null){
                try {
                    cnn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ArticuloCompatible.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        
        return resultado;
    
    }
    
    
    
    public int deleteArticuloCompatible(int id) throws SqlAppsException{
        
        int resultado = 0;
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        
        String sql = "DELETE FROM cat_articulo_compatible WHERE id = ?";
        
        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);
            ps.setInt(1, id);
            
            resultado = ps.executeUpdate();
            
        } catch (SQLException ex) {
            
            throw new SqlAppsException(ex, ArticuloCompatible.class.getName()+" el metodo deleteArticuloCompatible ha lanzado un error  ", LOGGER);
           
        }finally{
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloCompatible.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
        
        }
        
        
        
        
        return resultado;
    }
    
    
    public JSONObject getArticuloCompatibleInfoById(int id) throws SqlAppsException{
        JSONObject articulo_json = new JSONObject();
        
        
        ResultSet rs = null;
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "select 0 as id,\n" +
        "a.id as id_articulo,\n" +
        "a.clave,\n" +
        "a.descripcion,\n" +
        "a.serie,\n" +
        "a.sucursal,\n" +
        "c.descripcion as categoria,\n" +
        "d.descripcion as departamento\n" +
        "from cat_articulo a\n" +
        "LEFT JOIN cat_categoria c\n" +
        "ON a.categoria_id = c.id\n" +
        "LEFT JOIN cat_departamento d\n" +
        "ON a.departamento_id = d.id\n" +
        "WHERE a.id = ?";
        
        try {
            PreparedStatement ps = getPrepareStatement(sql);
            
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                articulo_json.put("id", rs.getInt("id"));
                articulo_json.put("id_articulo", rs.getInt("id_articulo"));
                articulo_json.put("serie", rs.getString("serie"));
                articulo_json.put("sucursal",rs.getInt("sucursal"));
                articulo_json.put("clave",rs.getString("clave"));
                articulo_json.put("descripcion", rs.getString("descripcion"));
                articulo_json.put("categoria",rs.getString("categoria"));
                articulo_json.put("departamento", rs.getString("departamento"));
            
            }
            
            
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, ArticuloCompatible.class.getName()+" el metodo a lanzado un error", LOGGER);
            
        }finally{
            
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ArticuloCompatible.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloCompatible.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
        
        }
        
        return articulo_json;
    
    }
    
    
    
    public JSONObject getArticuloCompatibleByIdArticuloJSON(int id) throws SqlAppsException{
        JSONObject articulos = new JSONObject();
        JSONArray articulos_array = new JSONArray();
        
        getConexion(EDriver.MYSQL, EApps.PV);
        ResultSet rs = null;
        
        String sql = "select a.id,\n" +
        "a.id_articulo,\n" +
        "a.serie,\n" +
        "a.sucursal,\n" +
        "b.clave,\n" +
        "b.descripcion,\n" +
        "c.descripcion as categoria,\n" +
        "d.descripcion as departamento\n" +
        "FROM cat_articulo_compatible a\n" +
        "LEFT JOIN cat_articulo b\n" +
        "ON a.id_articulo = b.id\n" +
        "LEFT JOIN cat_categoria c\n" +
        "ON b.categoria_id = c.id\n" +
        "LEFT JOIN cat_departamento d\n" +
        "ON b.departamento_id = d.id\n" +
        "WHERE a.id_articulo = ?";
        
        
        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);
            
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                JSONObject json = new JSONObject();
                json.put("id", rs.getInt("id"));
                json.put("id_articulo", rs.getInt("id_articulo"));
                json.put("serie", rs.getString("serie"));
                json.put("sucursal",rs.getInt("sucursal"));
                json.put("clave",rs.getString("clave"));
                json.put("descripcion", rs.getString("descripcion"));
                json.put("categoria",rs.getString("categoria"));
                json.put("departamento", rs.getString("departamento"));
                        
                
                articulos_array.add(json);
                
                
            
            }
            
            articulos.put("data", articulos_array);
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, ArticuloCompatible.class.getName()+" el metodo getArticuloCompatibleByIdArticuloJSON a lanzado un error ", LOGGER);
        }finally{
        
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ArticuloCompatible.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }
            
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloCompatible.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
        }
            
            
        
        return articulos;
    
    }
    
    public ListIterator<ArticuloCompatibleBean> getArticuloCompatibleByIdArticulo(int id) throws SqlAppsException{
    
        LinkedList<ArticuloCompatibleBean> lista = new LinkedList<>();
        
        getConexion(EDriver.MYSQL, EApps.PV);
        ResultSet rs = null;
        
        String sql = "select a.id,\n" +
        "a.id_articulo,\n" +
        "a.serie,\n" +
        "a.sucursal,\n" +
        "b.clave,\n" +
        "b.descripcion,\n" +
        "c.descripcion as categoria,\n" +
        "d.descripcion as departamento\n" +
        "FROM cat_articulo_compatible a\n" +
        "LEFT JOIN cat_articulo b\n" +
        "ON a.id_articulo = b.id\n" +
        "LEFT JOIN cat_categoria c\n" +
        "ON b.categoria_id = c.id\n" +
        "LEFT JOIN cat_departamento d\n" +
        "ON b.departamento_id = d.id\n" +
        "WHERE a.id_articulo = ?";
        
        try {
            PreparedStatement ps = getPrepareStatement(sql);
            
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                ArticuloCompatibleBean ab = new ArticuloCompatibleBean();
                ab.setId(rs.getInt("id"));
                ab.setId_articulo(rs.getInt("id_articulo"));
                ab.setSerie(rs.getString("serie"));
                ab.setSucursal(rs.getInt("sucursal"));
                ab.setClave(rs.getString("clave"));
                ab.setDescripcion(rs.getString("descripcion"));
                ab.setCategoria(rs.getString("categoria"));
                ab.setDepartamento(rs.getString("departamento"));
                lista.add(ab);
            
            }
            
            
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, ArticuloCompatible.class.getName()+" el metodo getArticuloCompatibleByIdArticulo a lanzado un error", LOGGER);
        }finally{
            
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ArticuloCompatible.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloCompatible.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
        
        }
        
        
        return lista.listIterator();
    
    }
    
}
