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
public interface EmpresaI {
    
    
    public int setEmpresa(EmpresaBean eb) throws SqlAppsException;
    
    public int updateEmpresa(EmpresaBean eb) throws SqlAppsException;
    
    public int deleteEmpresa(int id) throws SqlAppsException;
    
    public ListIterator<EmpresaBean> getEmpresa(int id) throws SqlAppsException;
    
    public ListIterator<EmpresaBean> getEmpresa() throws SqlAppsException;
    
    public JSONObject getEmpresaJSON(int draw) throws SqlAppsException;
    
}
