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
public interface ImpuestoI {
    
    public int setImpuesto(ImpuestoBean ib) throws SqlAppsException;
    
    public int updateImpuesto(ImpuestoBean ib) throws SqlAppsException;
    
    public int deleteImpuesto(int id) throws SqlAppsException;
    
    public ListIterator<ImpuestoBean> getListaImpuesto() throws SqlAppsException;
    
    public ListIterator<ImpuestoBean> getListaImpuesto(int id) throws SqlAppsException;
    
    public JSONObject getImpuestoJSON(int id) throws SqlAppsException;
    
}
