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
 * @author Rembao
 */
public interface MenuI {
    
    public int setMenu(MenuBean mb) throws SqlAppsException;
    
    public int updateMenu(MenuBean mb) throws SqlAppsException;
    
    public int deleteMenu(MenuBean mb) throws SqlAppsException;
    
    public ListIterator<MenuBean> getListaMenu() throws SqlAppsException;
    
    public ListIterator<MenuBean> getListaMenu(int id) throws SqlAppsException;
    
    public MenuBean getMenu(int id) throws SqlAppsException;
    
    public JSONObject getListaMenuJSON(int draw) throws SqlAppsException;
    
}
