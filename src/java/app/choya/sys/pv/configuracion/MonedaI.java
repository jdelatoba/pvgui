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
public interface MonedaI {

    public int setMoneda(MonedaBean mb) throws SqlAppsException;
    
    public int updateMoneda(MonedaBean mb) throws SqlAppsException;
    
    public int deleteMoneda(int id) throws SqlAppsException;
    
    public ListIterator<MonedaBean> getMonedaLista() throws SqlAppsException;
    
    public ListIterator<MonedaBean> getMonedaLista(int id) throws SqlAppsException;
    
    public JSONObject getMonedaJSON(int draw) throws SqlAppsException;
    
    public MonedaBean getMoneda(int id) throws SqlAppsException;
    
    
}
