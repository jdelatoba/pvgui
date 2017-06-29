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
public class AdmonMenu extends GenericDAO{

    
    static final Logger LOGGER = Logger.getLogger(AdmonMenu.class.getName());
    public AdmonMenu() {
    }
    
    
    public ListIterator<AdmonMenuBean> getMenuByRol(int id_rol) throws SqlAppsException{
    
        LinkedList<AdmonMenuBean> lista = new LinkedList<>();
        ResultSet rs = null;
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "select a.id as id_rol\n" +
        ",a.descripcion as rol\n" +
        ",b.id as id_menu\n" +
        ",b.descripcion as menu\n" +
        ",b.imagen \n" +
        "from conf_rol a, conf_menu b\n" +
        "WHERE a.id = ?\n" +
        "and a.id = b.id_rol";
        
        try {
            PreparedStatement ps = getPrepareStatement(sql);
            
            ps.setInt(1, id_rol);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
            
                AdmonMenuBean mb = new AdmonMenuBean();
                
                mb.setId_rol(rs.getInt("id_rol"));
                mb.setRol(rs.getString("rol"));
                mb.setId_menu(rs.getInt("id_menu"));
                mb.setMenu(rs.getString("menu"));
                mb.setImagen(rs.getString("imagen"));
                
                lista.add(mb);
            }
            
        } catch (SQLException ex) {
            
            throw new SqlAppsException(ex, AdmonMenu.class.getName(), LOGGER);
            
        }finally{
            
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdmonMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(AdmonMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
            
        }
        
        
        
        return lista.listIterator();
    
    }
    
    
    public ListIterator<AdmonMenuBean> getFuncionByMenu(int id_menu) throws SqlAppsException{
    
        LinkedList<AdmonMenuBean> lista = new LinkedList<>();
        ResultSet rs = null;
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "select a.id as id_submenu\n" +
        ",a.id_menu\n" +
        ",a.descripcion submenu\n" +
        ",b.id as id_funcion\n" +
        ",b.descripcion as funcion\n" +
        ",b.url\n" +
        "from conf_submenu a\n" +
        "LEFT JOIN conf_funcion b\n" +
        "ON a.id = b.submenu_id\n" +
        "and a.id_menu = b.menu_id\n" +
        "WHERE a.id_menu = ?\n" +
        "order by a.id, b.descripcion";
        
        try { 
            PreparedStatement ps = getPrepareStatement(sql);
            
            ps.setInt(1, id_menu);
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                AdmonMenuBean ab = new AdmonMenuBean();
                ab.setId_submenu(rs.getInt("id_submenu"));
                ab.setId_menu(rs.getInt("id_menu"));
                ab.setSubmenu(rs.getString("submenu"));
                ab.setId_funcion(rs.getInt("id_funcion"));
                ab.setFuncion(rs.getString("funcion"));
                ab.setUrl(rs.getString("url"));
                
                lista.add(ab);
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AdmonMenu.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdmonMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(AdmonMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
        }
        
        
        
        
        return lista.listIterator();
    }
    
}
