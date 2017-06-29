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
public interface TraspasoSolicitudDetalleI {
    
    public int setTraspasoSolicitudDetalle(TraspasoSolicitudDetalleBean tb) throws SqlAppsException;
    
    public int updateTraspasoSolicitudDetalle(TraspasoSolicitudDetalleBean tb) throws SqlAppsException;
    
    public int deleteTraspasoSolicitudDetalle(TraspasoSolicitudDetalleBean tb) throws SqlAppsException;
    
    public TraspasoSolicitudDetalleBean getTraspasoSolicitudDetalle(int id) throws SqlAppsException;
    
    public ListIterator<TraspasoSolicitudDetalleBean> getTraspasoSolicitudDetalle() throws SqlAppsException;
    
    public ListIterator<TraspasoSolicitudDetalleBean> getTraspasoSolicitudDetalle(String campo, String buscar) throws SqlAppsException;
    
    public ListIterator<TraspasoSolicitudBean> getTraspasoSolicitudBean(int id) throws SqlAppsException;
    
  //  public TraspasoSolicitudDetalleBean getTraspasoSolicitudDetalleBean(int id) throws SqlAppsException;
    
}
