/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.almacen;

import app.choya.sys.pv.utils.SqlAppsException;
import java.util.ListIterator;
import org.json.simple.JSONObject;

/**
 *
 * @author Condese
 */
public interface TraspasoSolicitudI {
    
    public int setTraspasoSolicitud(TraspasoSolicitudBean tb) throws SqlAppsException;
    
    public int updateTraspasoSolicitud(TraspasoSolicitudBean tb) throws SqlAppsException;
    
    public int deleteTraspasoSolicitud(TraspasoSolicitudBean tb) throws SqlAppsException;
    
    public TraspasoSolicitudBean getTraspasoSolicitud(int id) throws SqlAppsException;
    
    public ListIterator<TraspasoSolicitudBean> getTraspasoSolicitud() throws SqlAppsException;
    
    public ListIterator<TraspasoSolicitudBean> getTraspasoSolicitud(String campo, String buscar) throws SqlAppsException;
    
    public ListIterator<TraspasoSolicitudBean> getTraspasoSolicitudBean(int id) throws SqlAppsException;
    
  //  public TraspasoSolicitudBean getTraspasoSolicitudBean(int id) throws SqlAppsException;
}
