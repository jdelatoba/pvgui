/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.configuracion;


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
public class Funcion extends GenericDAO implements FuncionI{
    
    static final Logger LOGGER = Logger.getLogger(Usuario.class.getName());
    
    @Override
    public int setFuncion(FuncionBean fb) throws SqlAppsException {
        int resultado = 0;
         int secuencia = 0;
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "INSERT INTO conf_funcion\n" +
        "(id,\n" +
        "menu_id,\n" +
        "descripcion,\n" +
        "url,\n" +
        "comentario,\n" +
        "creado_por,\n" +
        "modificado_por)\n" +
        "VALUES\n" +
        "(?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?)";
        
        try {
            PreparedStatement ps = getPrepareStatement(sql);
            
            UtilsDao ut = new UtilsDao();

            secuencia = ut.nextVal("s_conf_funcion");
            
            ps.setInt(1, secuencia);
            ps.setInt(2, fb.getMenu_id());
            ps.setString(3, fb.getDescripcion());
            ps.setString(4, fb.getUrl());
            ps.setString(5, fb.getComentario());
            ps.setInt(6, fb.getCreado_por());
            ps.setInt(7, fb.getModificado_por());
            
            resultado = ps.executeUpdate();
            
            if(resultado != 0){
                resultado = secuencia;
            }
            
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Funcion.class.getName() + "-- metodo: setFuncion", LOGGER);
            
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Funcion.class.getName() + "-- metodo: setFuncion", LOGGER);
            }
            closeConexion();
        }

        return resultado;
        
        
        
        
    }

    @Override
    public int updateFuncion(FuncionBean fb) throws SqlAppsException {
        
        int resultado = 0;
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "UPDATE conf_funcion\n" +
        "SET\n" +
        "menu_id = ?,\n" +
        "descripcion = ?,\n" +
        "url = ?,\n" +
        "comentario = ?,\n" +
        "modificado_por = ?,\n" +
        "WHERE id = ?";
        
        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setInt(1, fb.getMenu_id());
            ps.setString(2, fb.getDescripcion());
            ps.setString(3, fb.getUrl());
            ps.setString(4, fb.getComentario());
            ps.setInt(5, fb.getModificado_por());
            ps.setInt(6, fb.getId());
            
            resultado = ps.executeUpdate();
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Funcion.class.getName() + "-- metodo: updateFuncion", LOGGER);

        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Funcion.class.getName() + "-- metodo: updateFuncion", LOGGER);
            }
            closeConexion();
        }

        return resultado;

    }

    @Override
    public int deleteFuncion(FuncionBean fb) throws SqlAppsException {
        
        int resultado = 0;
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "UPDATE conf_funcion "
                + "set estatus = 'D',"
                + "modificador_por = ?"
                + "WHERE id = ?";
        
        try {
            PreparedStatement ps = getPrepareStatement(sql);
            
            ps.setInt(1, fb.getModificado_por());
            ps.setInt(2, fb.getId());
            
            resultado = ps.executeUpdate();
            
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Funcion.class.getName() + "-- metodo: deleteFuncion", LOGGER);
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Funcion.class.getName() + "-- metodo: updateFuncion", LOGGER);
            }
            closeConexion();
        }

        return resultado;
        
    }

    @Override
    public ListIterator<FuncionBean> getFuncionLista() throws SqlAppsException {
        LinkedList<FuncionBean> lista = new LinkedList<>();
        Statement stmt = null;
        ResultSet rs = null;
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql ="SELECT a.id,\n" +
        "a.menu_id,\n" +
        "a.descripcion,\n" +
        "a.url,\n" +
        "a.comentario,\n" +
        "a.creado_por,\n" +
        "date_format(a.fecha_creacion,'%d/%m/%Y %H:%i:%s') fecha_creacion,\n" +
        "a.modificado_por,\n" +
        "date_format(a.fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion,\n" +
        "b.descripcion menu\n" +
        "FROM conf_funcion a\n" +
        "LEFT JOIN conf_menu b\n" +
        "ON a.menu_id = b.id\n" +
        "WHERE a.estatus = 'A'\n" +
        "ORDER BY a.descripcion";
        
        try {
            stmt = getStatement();
            
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                FuncionBean fb = new FuncionBean();
                fb.setId(rs.getInt("id"));
                fb.setMenu_id(rs.getInt("menu_id"));
                fb.setDescripcion(rs.getString("descripcion"));
                fb.setUrl(rs.getString("url"));
                fb.setComentario(rs.getString("comentario"));
                fb.setCreado_por(rs.getInt("creado_por"));
                fb.setFecha_creacion(rs.getString("fecha_creacion"));
                fb.setModificado_por(rs.getInt("modificado_por"));
                fb.setFecha_modificacio(rs.getString("fecha_modificiacion"));
                fb.setMenu(rs.getString("menu"));
                
                lista.add(fb);
                
            }
            
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Funcion.class.getName() + "-- metodo: getFuncionLista", LOGGER);
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion();
        }
        
        
        return lista.listIterator();
    }

    @Override
    public ListIterator<FuncionBean> getFuncionLista(int id) throws SqlAppsException {
        LinkedList<FuncionBean> lista = new LinkedList<>();
        Statement stmt = null;
        ResultSet rs = null;
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql ="SELECT a.id,\n" +
        "a.menu_id,\n" +
        "a.descripcion,\n" +
        "a.url,\n" +
        "a.comentario,\n" +
        "a.creado_por,\n" +
        "date_format(a.fecha_creacion,'%d/%m/%Y %H:%i:%s') fecha_creacion,\n" +
        "a.modificado_por,\n" +
        "date_format(a.fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion,\n" +
        "b.descripcion menu\n" +
        "FROM conf_funcion a\n" +
        "LEFT JOIN conf_menu b\n" +
        "ON a.menu_id = b.id\n" +
        "WHERE a.estatus = 'A' AND a.id = ?\n" +
        "ORDER BY a.descripcion";
        
        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                FuncionBean fb = new FuncionBean();
                fb.setId(rs.getInt("id"));
                fb.setMenu_id(rs.getInt("menu_id"));
                fb.setDescripcion(rs.getString("descripcion"));
                fb.setUrl(rs.getString("url"));
                fb.setComentario(rs.getString("comentario"));
                fb.setCreado_por(rs.getInt("creado_por"));
                fb.setFecha_creacion(rs.getString("fecha_creacion"));
                fb.setModificado_por(rs.getInt("modificado_por"));
                fb.setFecha_modificacio(rs.getString("fecha_modificiacion"));
                fb.setMenu(rs.getString("menu"));
                
                lista.add(fb);
                
            }
            
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Funcion.class.getName() + "-- metodo: getFuncionLista", LOGGER);
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion();
        }
        
        
        return lista.listIterator();
    }

    @Override
    public FuncionBean getFuncion(int id) throws SqlAppsException {
        Statement stmt = null;
        ResultSet rs = null;
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql ="SELECT a.id,\n" +
        "a.menu_id,\n" +
        "a.descripcion,\n" +
        "a.url,\n" +
        "a.comentario,\n" +
        "a.creado_por,\n" +
        "date_format(a.fecha_creacion,'%d/%m/%Y %H:%i:%s') fecha_creacion,\n" +
        "a.modificado_por,\n" +
        "date_format(a.fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion,\n" +
        "b.descripcion menu\n" +
        "FROM conf_funcion a\n" +
        "LEFT JOIN conf_menu b\n" +
        "ON a.menu_id = b.id\n" +
        "WHERE a.estatus = 'A' AND a.id = ?\n" +
        "ORDER BY a.descripcion";
        
        FuncionBean fb = new FuncionBean();
        
        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                fb.setId(rs.getInt("id"));
                fb.setMenu_id(rs.getInt("menu_id"));
                fb.setDescripcion(rs.getString("descripcion"));
                fb.setUrl(rs.getString("url"));
                fb.setComentario(rs.getString("comentario"));
                fb.setCreado_por(rs.getInt("creado_por"));
                fb.setFecha_creacion(rs.getString("fecha_creacion"));
                fb.setModificado_por(rs.getInt("modificado_por"));
                fb.setFecha_modificacio(rs.getString("fecha_modificiacion"));
                fb.setMenu(rs.getString("menu"));
                
                
                
            }
            
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Funcion.class.getName() + "-- metodo: getFuncionLista", LOGGER);
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion();
        }
        
        return fb;
    }

    @Override
    public JSONObject getFuncionListaJSON(int draw) throws SqlAppsException {
        
        JSONObject funciones = new JSONObject();
        JSONArray data = new JSONArray();
        Statement stmt = null;
        ResultSet rs = null;
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql ="SELECT a.id,\n" +
        "a.menu_id,\n" +
        "a.descripcion,\n" +
        "a.url,\n" +
        "a.comentario,\n" +
        "a.creado_por,\n" +
        "date_format(a.fecha_creacion,'%d/%m/%Y %H:%i:%s') fecha_creacion,\n" +
        "a.modificado_por,\n" +
        "date_format(a.fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion,\n" +
        "b.descripcion menu\n" +
        "FROM conf_funcion a\n" +
        "LEFT JOIN conf_menu b\n" +
        "ON a.menu_id = b.id\n" +
        "WHERE a.estatus = 'A'\n" +
        "ORDER BY a.descripcion";
        
        try {
            stmt = getStatement();
            
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                
                JSONObject funcionJSON = new JSONObject();
                
                funcionJSON.put("id", rs.getInt("id"));
                funcionJSON.put("menu_id", rs.getInt("menu_id"));
                funcionJSON.put("descripcion", rs.getString("descripcion"));
                funcionJSON.put("comentario", rs.getString("comentario"));
                funcionJSON.put("url", rs.getString("url"));
                funcionJSON.put("creado_por", rs.getInt("creado_por"));
                funcionJSON.put("url", rs.getString("url"));
                funcionJSON.put("modificado_por",rs.getInt("modificado_por"));
                funcionJSON.put("fecha_creacion", rs.getString("fecha_creacion"));
                funcionJSON.put("fecha_modificacion", rs.getString("fecha_modificacion"));
                funcionJSON.put("menu", rs.getString("menu"));
                
                
                data.add(funcionJSON);
                
                
            }
            funciones.put("data", data);
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Funcion.class.getName() + "-- metodo: getFuncionListaJSON", LOGGER);
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion();
        }
        
        
        return funciones;
    }

    
    
    
}
