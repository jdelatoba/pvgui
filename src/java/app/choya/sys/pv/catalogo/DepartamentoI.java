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
 * @author Rembao
 */
public interface DepartamentoI {
    
    public int setDepartamento(DepartamentoBean db) throws SqlAppsException;
    
    public int updateDepartamento(DepartamentoBean db) throws SqlAppsException;
    
    public int deleteDepartamento(DepartamentoBean db) throws SqlAppsException;
    
    public ListIterator<DepartamentoBean> getDepartamento() throws SqlAppsException;
    
    public DepartamentoBean getDepartamento(int id) throws SqlAppsException;
    
}
