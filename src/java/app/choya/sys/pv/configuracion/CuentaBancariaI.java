/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app.choya.sys.pv.configuracion;

import app.choya.sys.pv.utils.SqlAppsException;
import java.util.ListIterator;

/**
 *
 * @author Condese
 */
public interface CuentaBancariaI {
    
    public int setCuentaBancaria(CuentaBancariaBean cbb) throws SqlAppsException;
    
    public int updateCuentaBancaria(CuentaBancariaBean cbb) throws SqlAppsException;
    
    public int deleteCuentaBancaria(CuentaBancariaBean cbb) throws SqlAppsException;
    
    public ListIterator<CuentaBancariaBean> getCuentaBancaria() throws SqlAppsException;
    
    public ListIterator<CuentaBancariaBean> getCuentaBancaria(int id) throws SqlAppsException;
  
     
}
