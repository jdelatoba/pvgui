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
 * @author Condese
 */
public interface ClienteI {
    
    public int setCliente(ClienteBean cb) throws SqlAppsException;
    
    public int updateCliente(ClienteBean cb) throws SqlAppsException;
    
    public ListIterator<ClienteBean> getClienteLista() throws SqlAppsException;
    
    public ListIterator<ClienteBean> getClienteLista(int id) throws SqlAppsException;
    
    public ClienteBean getCliente(int id) throws SqlAppsException;
    
    public int deleteCliente(int id) throws SqlAppsException;
    
    public JSONObject getClienteListaJSON(int draw) throws SqlAppsException;
    
}
