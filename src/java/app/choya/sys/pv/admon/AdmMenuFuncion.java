/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.admon;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import app.choya.sys.pv.utils.SqlAppsException;
import app.choya.sys.pv.dao.EApps;
import app.choya.sys.pv.dao.EDriver;
import app.choya.sys.pv.dao.GenericDAO;

/**
 *
 * @author developer
 */
public class AdmMenuFuncion extends GenericDAO implements AdmMenuFuncionI {
    LinkedList<AdmMenuFuncionBean> lista_menu = new LinkedList<>();
    private String sql = "";

    @Override
    public int setAdmMenuFuncionI(AdmMenuFuncionBean pb) throws SqlAppsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateAdmMenuFuncion(AdmMenuFuncionBean pb) throws SqlAppsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<AdmMenuFuncionBean> getAdmMenuFuncion() throws SqlAppsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public ListIterator<AdmMenuFuncionBean> getMenu(int menu_id, EDriver driver, EApps app){
        
        ListIterator<AdmMenuFuncionBean> lista;
        
        try {
            lista = getAdmMenuFuncion(menu_id, driver, app);
            
            while(lista.hasNext()){
                
                AdmMenuFuncionBean ab = new AdmMenuFuncionBean();
                ab = lista.next();
                if(ab.getTipo_accion().compareTo("M") == 0){
                    lista_menu.add(ab);
                    getMenu(ab.getAccion_id(), driver, app);
                }else{
                    lista_menu.add(ab);
                }
                
            
            
            }
            
            
            
            
        } catch (SqlAppsException ex) {
            Logger.getLogger(AdmMenuFuncion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista_menu.listIterator();
    }
    
    
    @Override
    public ListIterator<AdmMenuFuncionBean> getAdmMenuFuncion(int menu_id, EDriver driver, EApps app) throws SqlAppsException {

       
            LinkedList<AdmMenuFuncionBean> lista = new LinkedList<>();
            
            getConexion(driver, app);
            
            sql = "SELECT menu_funcion_id\n"
                    + ",menu_id\n"
                    + ",menu_secuencia\n"
                    + ",prompt\n"
                    + ",tipo_accion\n"
                    + ",descripcion\n"
                    + ",accion_id\n"
                    + "FROM adm_menu_funciones\n"
                    + "WHERE menu_id = ?\n"
                    + "ORDER BY menu_secuencia";
            
        try {    
            PreparedStatement ps = getPrepareStatement(sql);
            
            ps.setInt(1, menu_id);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                AdmMenuFuncionBean ab = new AdmMenuFuncionBean();
                ab.setMenu_funcion_id(rs.getInt("menu_funcion_id"));
                ab.setMenu_id(rs.getInt("menu_id"));
                ab.setMenu_secuencia(rs.getInt("menu_secuencia"));
                ab.setPrompt(rs.getString("prompt"));
                ab.setTipo_accion(rs.getString("tipo_accion"));
                ab.setDescripcion(rs.getString("descripcion"));
                ab.setAccion_id(rs.getInt("accion_id"));
                
                lista.add(ab);
                
                
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdmMenuFuncion.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                try {
                    closeResultSet();
                    closePStmt();
                    closeConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(AdmMenuFuncion.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
        return lista.listIterator();

    }

}
