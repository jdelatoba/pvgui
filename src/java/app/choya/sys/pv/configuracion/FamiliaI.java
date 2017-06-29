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
public interface FamiliaI {
    
     public int setFamilia(FamiliaBean fb) throws SqlAppsException;
    
    public int updateFamilia(FamiliaBean fb) throws SqlAppsException;
    
    public int deleteFamilia(int id) throws SqlAppsException;
    
    public ListIterator<FamiliaBean> getFamiliaLista(int id) throws SqlAppsException;
    
    public ListIterator<FamiliaBean> getFamilia() throws SqlAppsException;
    
    public JSONObject getGrupoJSON(int draw) throws SqlAppsException;  
}
