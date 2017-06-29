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
public interface CompraI {
    
    public int setCompra(CompraBean tb) throws SqlAppsException;
    
    public int updateCompra(CompraBean tb) throws SqlAppsException;
    
    public int deleteCompra(CompraBean tb) throws SqlAppsException;
    
    public CompraBean getCompra(int id) throws SqlAppsException;
    
    
    
}

