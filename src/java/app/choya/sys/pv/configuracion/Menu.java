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
import java.sql.Connection;
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
public class Menu extends GenericDAO implements MenuI {

    static final Logger LOGGER = Logger.getLogger(Menu.class.getName());
    
    @Override
    public int setMenu(MenuBean mb) throws SqlAppsException {

        int resultado = 0;
        int secuencia = 0;

        Connection cnn = getConexionSinCommit(EDriver.MYSQL, EApps.PV);

        String sql = "INSERT INTO conf_menu\n"
                + "(id,\n"
                + "descripcion,\n"
                + "creado_por,\n"
                + "comentario,\n"
                + "modificado_por)\n"
                + "VALUES\n"
                + "(?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?)";

        try {
            PreparedStatement ps = getPrepareStatement(sql);
            UtilsDao ut = new UtilsDao();

            secuencia = ut.nextVal("s_conf_menu",cnn);
            ps.setInt(1, secuencia);
            ps.setString(2, mb.getDescripcion());
            ps.setInt(3, mb.getCreado_por());
            ps.setString(4, mb.getComentario());
            ps.setInt(5, mb.getModificado_por());

            resultado = ps.executeUpdate();
            commit();
            if (resultado != 0) {
                resultado = secuencia;
            }

        } catch (SQLException ex) {
            rollback();
            throw new SqlAppsException(ex, Menu.class.getName() + "-- metodo: setMenu", LOGGER);

        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Menu.class.getName() + "-- metodo: setMenu", LOGGER);
            }
            
            closeConexion();
        }

        return resultado;

        
    }

    @Override
    public int updateMenu(MenuBean mb) throws SqlAppsException {
        
        int resultado = 0;
        
        Connection conn =  getConexionSinCommit(EDriver.MYSQL, EApps.PV);
        
        String sql = "UPDATE conf_menu\n" +
        "SET\n" +
        "descripcion = ?,\n" +
        "comentario = ?,\n" +
        "modificado_por = ?\n" +
        "WHERE id = ?";
        
        try {
            PreparedStatement ps = getCallableStatement(sql);
            ps.setString(1, mb.getDescripcion());
            ps.setString(2, mb.getComentario());
            ps.setInt(3, mb.getModificado_por());
            ps.setInt(4, mb.getId());
            
            resultado = ps.executeUpdate();
            commit();
            
        } catch (SQLException ex) {
            rollback();
            throw new SqlAppsException(ex, Menu.class.getName() + "-- metodo: updateMenu", LOGGER);
            
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Menu.class.getName() + "-- metodo: updateMenu", LOGGER);
            }
            
            closeConexion();
        }

        return resultado;

        
    }

    @Override
    public int deleteMenu(MenuBean mb) throws SqlAppsException {
        int resultado = 0;
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "UPDATE conf_menu \n"
                + "set estatus = 'D'\n"
                + ",modificado_por = ?\n"
                + "WHERE id = ?";
        
        try {
            PreparedStatement ps = getPrepareStatement(sql);
            
            ps.setInt(1, mb.getModificado_por());
            ps.setInt(2, mb.getId());
            
            resultado = ps.executeUpdate();
            
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Menu.class.getName() + "-- metodo: deleteMenu", LOGGER);
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Menu.class.getName() + "-- metodo: deleteMenu", LOGGER);
            }
            closeConexion();
        }

        return resultado;
    }

    @Override
    public ListIterator<MenuBean> getListaMenu() throws SqlAppsException {
        
        LinkedList<MenuBean> lista = new LinkedList<>();
        Statement stmt = null;
        ResultSet rs = null;
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "SELECT id,\n" +
        "id_rol,\n" +
        "descripcion,\n" +
        "creado_por,\n" +
        "serie,\n" +
        "estatus,\n" +
        "comentario,\n" +
        "date_format(fecha_creacion,'%d/%m/%Y %H:%i:%s') fecha_creacion,\n" +
        "modificado_por,\n" +
        "date_format(fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion\n" +
        "FROM conf_menu  WHERE estatus = 'A' ORDER BY descripcion";
        
        try {
            stmt = getStatement();
            
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                MenuBean mb = new MenuBean();
                mb.setId(rs.getInt("id"));
                mb.setId_rol(rs.getInt("id_rol"));
                mb.setDescripcion(rs.getString("descripcion"));
                mb.setCreado_por(rs.getInt("creado_por"));
                mb.setSerie(rs.getString("serie"));
                mb.setEstatus(rs.getString("estatus"));
                mb.setComentario(rs.getString("comentario"));
                mb.setFecha_creacion(rs.getString("fecha_creacion"));
                mb.setModificado_por(rs.getInt("modificado_por"));
                mb.setFecha_modificacion(rs.getString("fecha_modificacion"));
                
                lista.add(mb);
            
            }
            
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Menu.class.getName() + "-- metodo: getFuncionLista", LOGGER);
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion();
        }
        
        return lista.listIterator();
        
    }

    @Override
    public ListIterator<MenuBean> getListaMenu(int id) throws SqlAppsException {
        LinkedList<MenuBean> lista = new LinkedList<>();
        Statement stmt = null;
        ResultSet rs = null;
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "SELECT id,\n" +
        "id_rol,\n" +
        "descripcion,\n" +
        "creado_por,\n" +
        "serie,\n" +
        "estatus,\n" +
        "comentario,\n" +
        "date_format(fecha_creacion,'%d/%m/%Y %H:%i:%s') fecha_creacion,\n" +
        "modificado_por,\n" +
        "date_format(fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion\n" +
        "FROM conf_menu WHERE id = ? AND estatus = 'A' ORDER BY descripcion";
        
        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            
            
            while(rs.next()){
                MenuBean mb = new MenuBean();
                mb.setId(rs.getInt("id"));
                mb.setId_rol(rs.getInt("id_rol"));
                mb.setDescripcion(rs.getString("descripcion"));
                mb.setCreado_por(rs.getInt("creado_por"));
                mb.setSerie(rs.getString("serie"));
                mb.setEstatus(rs.getString("estatus"));
                mb.setComentario(rs.getString("comentario"));
                mb.setFecha_creacion(rs.getString("fecha_creacion"));
                mb.setModificado_por(rs.getInt("modificado_por"));
                mb.setFecha_modificacion(rs.getString("fecha_modificacion"));
                
                lista.add(mb);
            
            }
            
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Menu.class.getName() + "-- metodo: getFuncionLista", LOGGER);
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion();
        }
        
        return lista.listIterator();
    }

    @Override
    public MenuBean getMenu(int id) throws SqlAppsException {
        
        LinkedList<MenuBean> lista = new LinkedList<>();
        ResultSet rs = null;
        getConexion(EDriver.MYSQL, EApps.PV);
        MenuBean mb = new MenuBean();
        String sql = "SELECT id,\n" +
        "id_rol,\n" +
        "descripcion,\n" +
        "creado_por,\n" +
        "serie,\n" +
        "estatus,\n" +
        "comentario,\n" +
        "date_format(fecha_creacion,'%d/%m/%Y %H:%i:%s') fecha_creacion,\n" +
        "modificado_por,\n" +
        "date_format(fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion\n" +
        "FROM conf_menu WHERE id = ? AND estatus = 'A' ORDER BY descripcion";
        
        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            
            
            while(rs.next()){
                
                mb.setId(rs.getInt("id"));
                mb.setId_rol(rs.getInt("id_rol"));
                mb.setDescripcion(rs.getString("descripcion"));
                mb.setCreado_por(rs.getInt("creado_por"));
                mb.setSerie(rs.getString("serie"));
                mb.setEstatus(rs.getString("estatus"));
                mb.setComentario(rs.getString("comentario"));
                mb.setFecha_creacion(rs.getString("fecha_creacion"));
                mb.setModificado_por(rs.getInt("modificado_por"));
                mb.setFecha_modificacion(rs.getString("fecha_modificacion"));
                
                
            }
            
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Menu.class.getName() + "-- metodo: getFuncionLista", LOGGER);
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion();
        }
        
        return mb;
    }

    @Override
    public JSONObject getListaMenuJSON(int draw) throws SqlAppsException {
        
        JSONObject menus = new JSONObject();
        JSONArray data = new JSONArray();
        Statement stmt = null;
        ResultSet rs = null;
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "SELECT id,\n" +
        "id_rol,\n" +
        "descripcion,\n" +
        "creado_por,\n" +
        "serie,\n" +
        "estatus,\n" +
        "comentario,\n" +
        "date_format(fecha_creacion,'%d/%m/%Y %H:%i:%s') fecha_creacion,\n" +
        "modificado_por,\n" +
        "date_format(fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion\n" +
        "FROM conf_menu WHERE estatus = 'A' ORDER BY descripcion";
        
        try {
            stmt = getStatement();
            
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                
                JSONObject menuJSON = new JSONObject();
                
                menuJSON.put("id", rs.getInt("id"));
                menuJSON.put("id_rol", rs.getInt("id_rol"));
                menuJSON.put("descripcion", rs.getString("descripcion"));
                menuJSON.put("creado_por", rs.getInt("creado_por"));
                menuJSON.put("serie", rs.getString("serie"));
                menuJSON.put("estatus", rs.getString("estatus"));
                menuJSON.put("comentario", rs.getString("comentario"));
                menuJSON.put("fecha_creacion", rs.getString("fecha_creacion"));
                menuJSON.put("fecha_modificacion", rs.getString("fecha_modificacion"));
                menuJSON.put("modificado_por", rs.getInt("modificado_por"));
                
                data.add(menuJSON);
                
                //contador++;
                
            
            }
            
            menus.put("data", data);
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Menu.class.getName() + "-- metodo: getFuncionLista", LOGGER);
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion();
        }
        
        return menus;
    }

}
