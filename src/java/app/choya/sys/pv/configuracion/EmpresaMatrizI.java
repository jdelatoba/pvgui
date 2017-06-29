/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.configuracion;

import app.choya.sys.pv.utils.SqlAppsException;

/**
 *
 * @author Rembao
 */
public interface EmpresaMatrizI {
    
    public int setEmpresaMatriz(EmpresaBean eb) throws SqlAppsException;
    
    public int updateEmpresaMatriz(EmpresaBean eb) throws SqlAppsException;
    
    
    
}
