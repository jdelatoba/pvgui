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
 * @author jdelatoba
 */
public interface GrupoI {
    
    public int setGrupo(GrupoBean gb) throws SqlAppsException;
    
    public int updateGrupo(GrupoBean gb) throws SqlAppsException;
    
    public int deleteGrupo(int id) throws SqlAppsException;
    
    public ListIterator<GrupoBean> getGrupoLista(int id) throws SqlAppsException;
    
    public ListIterator<GrupoBean> getGrupo() throws SqlAppsException;
    
    public JSONObject getGrupoJSON(int draw) throws SqlAppsException;  
}
