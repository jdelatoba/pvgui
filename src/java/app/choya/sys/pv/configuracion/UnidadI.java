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
public interface UnidadI {
    
    public int setUnidad(UnidadBean ub) throws SqlAppsException;
    
    public int updateUnidad(UnidadBean ub) throws SqlAppsException;
    
    public int deleteUnidad(int id) throws SqlAppsException;
    
    public ListIterator<UnidadBean> getUnidad() throws SqlAppsException;
    
    public ListIterator<UnidadBean> getUnidad(int id) throws SqlAppsException;
    
    public UnidadBean getUnidadBean(int id) throws SqlAppsException;
    
    public JSONObject getUnidadListaJSON(int draw) throws SqlAppsException;
    
    
}
