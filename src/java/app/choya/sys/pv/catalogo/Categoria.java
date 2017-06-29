/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.catalogo;

import static app.choya.sys.pv.catalogo.Clientes.LOGGER;
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
 * @author Rembao
 */
public class Categoria extends GenericDAO implements CategoriaI {

    @Override
    public int setCategoria(CategoriaBean cb) throws SqlAppsException {

        int resultado = 0;
        int secuencia = 0;
        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "INSERT INTO cat_categoria\n"
                + "(id,\n"
                + "departamento_id,\n"
                + "descripcion,\n"
                + "creado_por,\n"
                + "modificado_por,\n"
                + "serie,\n"
                + "estatus)\n"
                + "VALUES\n"
                + "(?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?)";

        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);

            UtilsDao ut = new UtilsDao();

            secuencia = ut.nextVal("s_cat_categoria");
            ps.setInt(1, secuencia);
            ps.setInt(2, cb.getDepartamento_id());
            ps.setString(3, cb.getDescripcion());
            ps.setInt(4, cb.getCreado_por());
            ps.setInt(5, cb.getModificado_por());
            ps.setString(6, cb.getSerie());
            ps.setString(7, cb.getEstatus());

            resultado = ps.executeUpdate();

            if (resultado != 0) {
                resultado = secuencia;
            }

        } catch (SQLException ex) {
            System.out.println("el método setCategoria a lanzado el siguiente error " + ex.getMessage());
            throw new SqlAppsException(ex);
            //Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion();
        }

        return resultado;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateCategoria(CategoriaBean cb) throws SqlAppsException {
        int resultado = 0;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "UPDATE cat_categoria\n"
                + "SET\n"
                + "departamento_id = ?,\n"
                + "descripcion = ?,\n"
                + "modificado_por = ?,\n"
                + "serie = ?,\n"
                + "estatus = ?\n"
                + "WHERE id =?";

        try {
            PreparedStatement ps = getPrepareStatement(sql);

            ps.setInt(1, cb.getDepartamento_id());
            ps.setString(2, cb.getDescripcion());
            ps.setInt(3, cb.getModificado_por());
            ps.setString(4, cb.getSerie());
            ps.setString(5, cb.getEstatus());
            ps.setInt(6, cb.getId());

            resultado = ps.executeUpdate();
        } catch (SQLException ex) {

            System.out.println("el método updateCategoria a lanzado el siguiente error " + ex.getMessage());
            throw new SqlAppsException(ex);
            //Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion();

        }

        return resultado;

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteCategoria(CategoriaBean cb) throws SqlAppsException {

        int resultado = 0;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "UPDATE cat_categoria "
                + "set estatus = 'D',"
                + "modificador_por = ?"
                + "WHERE id = ?";

        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);
            ps.setInt(1, cb.getModificado_por());
            ps.setInt(2, cb.getId());

            resultado = ps.executeUpdate();

        } catch (SQLException ex) {

            System.out.println("el método deleteCategoria a lanzado el siguiente error " + ex.getMessage());
            throw new SqlAppsException(ex);
            //Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion();
        }

        return resultado;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<CategoriaBean> getCategoria() throws SqlAppsException {
        LinkedList<CategoriaBean> lista = new LinkedList<>();
        
        Statement stmt = null;
        ResultSet rs = null;
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "SELECT id,\n"
                + "    departamento_id,\n"
                + "    descripcion,\n"
                + "    creado_por,\n"
                + "    fecha_creacion,\n"
                + "    modificado_por,\n"
                + "    fecha_modificacion,\n"
                + "    serie,\n"
                + "    estatus\n"
                + "FROM cat_categoria WHERE estatus = 'A'";
        
        try {
            stmt = getStatement();
            
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                
                CategoriaBean cb = new CategoriaBean();
                cb.setId(rs.getInt("id"));
                cb.setDepartamento_id(rs.getInt("departamento_id"));
                cb.setDescripcion(rs.getString("descripcion"));
                cb.setCreado_por(rs.getInt("creado_por"));
                cb.setFecha_creacion(rs.getString("fecha_creacion"));
                cb.setModificado_por(rs.getInt("modificado_por"));
                cb.setFecha_modificacion(rs.getString("fecha_modificacion"));
                cb.setSerie(rs.getString("serie"));
                cb.setEstatus(rs.getString("estatus"));
                
                lista.add(cb);
            }
            
        } catch (SQLException ex) {
            System.out.println("el método getCategoria ha lanzado el siguiente error "+ex.getMessage());
            throw new SqlAppsException(ex);
            //Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            try {
                if(rs != null)
                    rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                if(stmt != null)
                    stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
        }

        return lista.listIterator();

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CategoriaBean getCategoria(int id) throws SqlAppsException {
        
        Statement stmt = null;
        ResultSet rs = null;
        CategoriaBean cb = new CategoriaBean();
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "SELECT id,\n"
                + "    departamento_id,\n"
                + "    descripcion,\n"
                + "    creado_por,\n"
                + "    fecha_creacion,\n"
                + "    modificado_por,\n"
                + "    fecha_modificacion,\n"
                + "    serie,\n"
                + "    estatus\n"
                + "FROM cat_categoria "
                + "WHERE id = ? "
                + "AND estatus = 'A'";
        
        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            while(rs.next()){
                
                
                cb.setId(rs.getInt("id"));
                cb.setDepartamento_id(rs.getInt("departamento_id"));
                cb.setDescripcion(rs.getString("descripcion"));
                cb.setCreado_por(rs.getInt("creado_por"));
                cb.setFecha_creacion(rs.getString("fecha_creacion"));
                cb.setModificado_por(rs.getInt("modificado_por"));
                cb.setFecha_modificacion(rs.getString("fecha_modificacion"));
                cb.setSerie(rs.getString("serie"));
                cb.setEstatus(rs.getString("estatus"));
                
                
            }
        } catch (SQLException ex) {
            System.out.println("El método getCategoria(id) a lanzado el siguiente erro "+ex.getMessage());
            throw new SqlAppsException(ex);
            //Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            try {
                if(rs != null)
                    rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
            
        }
        
        
        
        
        return cb;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JSONObject getCategoriaListaJSON(int draw) throws SqlAppsException {
        
        Statement stmt = null;
        ResultSet rs = null;
        JSONObject categoria = new JSONObject();
        JSONArray data = new JSONArray();
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "SELECT a.id,\n" +
            "a.departamento_id,\n" +
            "a.descripcion,\n" +
            "a.creado_por,\n" +
            "a.fecha_creacion,\n" +
            "a.modificado_por,\n" +
            "a.fecha_modificacion,\n" +
            "a.serie,\n" +
            "a.estatus,\n" +
            "b.descripcion departamento\n" +
            "FROM cat_categoria a\n" +
            "LEFT JOIN cat_departamento b\n" +
            "ON a.departamento_id = b.id \n" +
            "WHERE a.estatus = 'A'";
        
        try {
            stmt = getStatement();
            
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                
                JSONObject categoriaJSON = new JSONObject();
                
                categoriaJSON.put("id", rs.getInt("id"));
                categoriaJSON.put("departamento_id", rs.getInt("departamento_id"));
                categoriaJSON.put("descripcion", rs.getString("descripcion"));
                categoriaJSON.put("creado_por", rs.getInt("creado_por"));
                categoriaJSON.put("fecha_creacion", rs.getString("fecha_creacion"));
                categoriaJSON.put("modificado_por", rs.getInt("modificado_por"));
                categoriaJSON.put("fecha_modificacion", rs.getString("fecha_modificacion"));
                categoriaJSON.put("serie", rs.getString("serie"));
                categoriaJSON.put("estatus", rs.getString("estatus"));
                categoriaJSON.put("departamento", rs.getString("departamento"));
                
                data.add(categoriaJSON);
            }
            
            categoria.put("data", data);
        } catch (SQLException ex) {
            
            throw new SqlAppsException(ex, Categoria.class.getName() + "-- metodo: getCategoriaListaJSON", LOGGER);
        }finally{
            
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
        
        }
        
        
        return categoria;
                
            
    }

    @Override
    public CategoriaBean getCategoriaResume(int id) throws SqlAppsException {
        
        
        Statement stmt = null;
        ResultSet rs = null;
        CategoriaBean cb = new CategoriaBean();
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "SELECT a.id,\n" +
            "a.departamento_id,\n" +
            "a.descripcion,\n" +
            "b.descripcion departamento\n" +
            "FROM cat_categoria a\n" +
            "LEFT JOIN cat_departamento b\n" +
            "ON a.departamento_id = b.id \n" +
            "WHERE a.id = ? and a.estatus = 'A'";
        
        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            while(rs.next()){
                
                
                cb.setId(rs.getInt("id"));
                cb.setDepartamento_id(rs.getInt("departamento_id"));
                cb.setDescripcion(rs.getString("descripcion"));
                cb.setDepartamento(rs.getString("departamento"));
                
            }
        } catch (SQLException ex) {
            System.out.println("El método getCategoriaResume(id) a lanzado el siguiente erro "+ex.getMessage());
            throw new SqlAppsException(ex);
            //Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            try {
                if(rs != null)
                    rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
            
        }
        
        
        
        return cb;
    }
    
    
    

}
