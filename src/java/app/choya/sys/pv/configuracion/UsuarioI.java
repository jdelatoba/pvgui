/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.configuracion;

import app.choya.sys.pv.utils.SqlAppsException;
import java.util.ListIterator;
import org.json.simple.JSONObject;

/**
 *
 * @author Condese
 */

public interface UsuarioI {
    
    public int setUsuario(UsuarioBean ub) throws SqlAppsException;
    
    public int updateUsuario(UsuarioBean ub) throws SqlAppsException;
    
    public ListIterator<UsuarioBean> getUsuarioLista() throws SqlAppsException;
    
    public ListIterator<UsuarioBean> getUsuarioLista(int id) throws SqlAppsException;
    
    public JSONObject getUsuarioJSON(int draw) throws SqlAppsException;
     
    public UsuarioBean getUsuario(int id) throws SqlAppsException;
    
    public int deleteUsuario(UsuarioBean ub) throws SqlAppsException;
    
    public int updatePassword(UsuarioBean ub) throws SqlAppsException;
    
    public UsuarioBean getUsuarioInfo(String usuario) throws SqlAppsException;
    
    
}