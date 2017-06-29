/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.catalogo;

import app.choya.sys.pv.utils.SqlAppsException;
import java.util.ListIterator;
/**
 *
 * @author Condese
 */
public interface ClienteDomicilioI {
    
    public int setClienteDomicilio(ClienteDomicilioBean cdb) throws SqlAppsException;
    
    public int updateClienteDomicilio(ClienteDomicilioBean cdb) throws SqlAppsException;
    
    public ListIterator<ClienteDomicilioBean> getClienteDomicilioLista() throws SqlAppsException;
    
    public ListIterator<ClienteDomicilioBean> getClienteDomicilioLista(int id) throws SqlAppsException;
    
    public ClienteDomicilioBean getClienteDomicilio(int id) throws SqlAppsException;
    
    public int deleteClienteDomicilio(int id) throws SqlAppsException;
    
}
