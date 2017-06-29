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
public interface TraspasoEntradaI {
    
     public int setTraspasoEntrada(TraspasoEntradaBean te) throws SqlAppsException;
    
    public int updateTraspasoEntrada(TraspasoEntradaBean te) throws SqlAppsException;
    
    public int deleteTraspasoEntrada(TraspasoEntradaBean te) throws SqlAppsException;
    
    public TraspasoEntradaBean getTraspasoEntrada(int id) throws SqlAppsException;
    
    public ListIterator<TraspasoEntradaBean> getTraspasoEntrada() throws SqlAppsException;
    
    public ListIterator<TraspasoEntradaBean> getTraspasoEntrada(String campo, String buscar) throws SqlAppsException;
    
    public ListIterator<TraspasoEntradaBean> getTraspasoEntradaBean(int id) throws SqlAppsException;
    
  //  public TraspasoEntradaBean getTraspasoEntradaBean(int id) throws SqlAppsException
}

