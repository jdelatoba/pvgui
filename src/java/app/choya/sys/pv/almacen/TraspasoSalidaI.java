/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.almacen;

import app.choya.sys.pv.utils.SqlAppsException;
import java.util.ListIterator;

/**
 *
 * @author Condese
 */
public interface TraspasoSalidaI {
    
     public int setTraspasoSalida(TraspasoSalidaBean ts) throws SqlAppsException;
    
    public int updateTraspasoSalida(TraspasoSalidaBean ts) throws SqlAppsException;
    
    public int deleteTraspasoSalida(TraspasoSalidaBean ts) throws SqlAppsException;
    
    public TraspasoSalidaBean getTraspasoSalida(int id) throws SqlAppsException;
    
    public ListIterator<TraspasoSalidaBean> getTraspasoSalida() throws SqlAppsException;
    
    public ListIterator<TraspasoSalidaBean> getTraspasoSalida(String campo, String buscar) throws SqlAppsException;
    
    public ListIterator<TraspasoSalidaBean> getTraspasoSalidaBean(int id) throws SqlAppsException;
    
  //  public TraspasoSalidaBean getTraspasoSalidaBean(int id) throws SqlAppsException
}
