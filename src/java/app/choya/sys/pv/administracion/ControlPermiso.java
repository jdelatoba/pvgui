/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.administracion;

import app.choya.sys.pv.configuracion.FuncionBean;
import app.choya.sys.pv.configuracion.MenuBean;
import app.choya.sys.pv.configuracion.RolBean;
import app.choya.sys.pv.configuracion.UsuarioRolBean;
import app.choya.sys.pv.dao.EApps;
import app.choya.sys.pv.dao.EDriver;
import app.choya.sys.pv.dao.GenericDAO;
import app.choya.sys.pv.utils.SqlAppsException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rembao
 */
public class ControlPermiso extends GenericDAO{
    
    
    static final Logger LOGGER = Logger.getLogger(ControlPermiso.class.getName());
    
    
    public ControlPermiso(){}
    
    
    
    
    
    public ListIterator<UsuarioRolBean> getRolesByUsuario(int usuario_id) throws SqlAppsException{
        LinkedList<UsuarioRolBean> lista = new LinkedList<>();
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        ResultSet rs = null;
        String sql = "select \n" +
        "a.id\n" +
        ",a.usuario_id\n" +
        ",a.rol_id\n" +
        ",b.imagen\n" +
        ",b.css\n" +        
        ",b.descripcion as rol\n" +
        "FROM conf_usuario_rol a\n" +
        "LEFT JOIN conf_rol b\n" +
        "ON a.rol_id = b.id\n" +
        "WHERE usuario_id = ?";
        
        try {
            PreparedStatement ps = getPrepareStatement(sql);
            
            ps.setInt(1, usuario_id);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
            
                UsuarioRolBean rb = new UsuarioRolBean();
                
                rb.setId(rs.getInt("id"));
                rb.setRol_id(rs.getInt("rol_id"));
                rb.setRol(rs.getString("rol"));
                rb.setImagen(rs.getString("imagen"));
                rb.setCss(rs.getString("css"));
                lista.add(rb);
            }
            
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, ControlPermiso.class.getName() + "-- metodo: getRolesByUsuario", LOGGER);
            
        }finally{
        
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ControlPermiso.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(ControlPermiso.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
        }
        
        
        
        
        return lista.listIterator();
    }
    
    
    public ListIterator<MenuBean> getMenuByRol(int rol_id) throws SqlAppsException{
    
        LinkedList<MenuBean> lista = new LinkedList<>();
        ResultSet rs = null;
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "select a.id as id_rol\n" +
        ",a.descripcion as rol\n" +
        ",b.id as id_menu\n" +
        ",b.descripcion as menu\n" +
        "from conf_rol a, conf_menu b\n" +
        "WHERE a.id = ?\n" +
        "and a.id = b.id_rol";
        
        try {
            PreparedStatement ps = getPrepareStatement(sql);
            
            ps.setInt(1, rol_id);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                MenuBean mb = new MenuBean();
                
                mb.setId_rol(rs.getInt("id_rol"));
                mb.setId(rs.getInt("id_menu"));
                mb.setDescripcion(rs.getString("menu"));
                
                lista.add(mb);
                
                
            }
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, ControlPermiso.class.getName() + "metodo getMenuByRol", LOGGER);
            
        }finally{
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ControlPermiso.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(ControlPermiso.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
        }
        
        
        return lista.listIterator();
    }
    
    public ListIterator<FuncionBean> getFuncionByMenu(int id_rol, int id_menu) throws SqlAppsException{
        LinkedList<FuncionBean> lista = new LinkedList<>();
        
        ResultSet rs = null;
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "select a.id\n" +
        ",a.descripcion as funcion\n" +
        ",a.url\n" +
        ",a.menu_id\n" +
        ",a.submenu_id\n" +
        ",b.descripcion submenu\n" +
        "from conf_funcion a\n" +
        "left join conf_submenu b\n" +
        "ON a.menu_id = b.id_menu\n" +
        "and a.submenu_id = b.id\n" +
        "and b.id_rol = ?\n" +
        "WHERE a.menu_id = ?";
        
        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);
            
            ps.setInt(1, id_rol);
            ps.setInt(2, id_menu);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
            
                FuncionBean fb = new FuncionBean();
                fb.setId(rs.getInt("id"));
                fb.setDescripcion(rs.getString("descripcion"));
                fb.setUrl(rs.getString("url"));
                
                lista.add(fb);
                
            }
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, ControlPermiso.class.getName() + " metodo getFuncionByMenu", LOGGER);
        } finally{
        
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ControlPermiso.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(ControlPermiso.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
            
        }
        
        
        
        return lista.listIterator();
    }
    
}
