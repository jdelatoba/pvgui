/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.catalogo;

import app.choya.sys.pv.utils.SqlAppsException;
import java.util.ListIterator;
import org.json.simple.JSONObject;

/**
 *
 * @author Rembao
 */
public interface ProveedorI {
    
    public int setProveedor(ProveedorBean pb) throws SqlAppsException;
    
    public int updateProveedor(ProveedorBean pb) throws SqlAppsException;
    
    public ListIterator<ProveedorBean> getProveedorLista() throws SqlAppsException;
    
    public JSONObject getProveedorJSON(int draw) throws SqlAppsException;
    
    public ListIterator<ProveedorBean> getProveedorLista(int id) throws SqlAppsException;
    
    public ProveedorBean getProveedor(int id) throws SqlAppsException;
    
    public int deleteProveedor(int id) throws SqlAppsException;
    
}
