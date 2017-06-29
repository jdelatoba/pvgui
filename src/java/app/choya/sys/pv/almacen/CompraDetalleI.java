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
public interface CompraDetalleI {
    
     public int setCompraDetalle(CompraDetalleBean tdb) throws SqlAppsException;
    
    public int updateCompraDetalle(CompraDetalleBean tdb) throws SqlAppsException;
    
    public int deleteCompraDetalle(CompraDetalleBean tdb) throws SqlAppsException;
    
    public CompraDetalleBean getCompraDetalle(int id) throws SqlAppsException;
    
}
