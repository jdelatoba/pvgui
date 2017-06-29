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
public interface FuncionI {
    
    
    public int setFuncion(FuncionBean fb) throws SqlAppsException;
    
    public int updateFuncion(FuncionBean fb) throws SqlAppsException;
    
    public int deleteFuncion(FuncionBean fb) throws SqlAppsException;
    
    public ListIterator<FuncionBean> getFuncionLista() throws SqlAppsException;
    
    public ListIterator<FuncionBean> getFuncionLista(int id) throws SqlAppsException;
    
    public FuncionBean getFuncion(int id) throws SqlAppsException;
    
    public JSONObject getFuncionListaJSON(int draw) throws SqlAppsException;
}
