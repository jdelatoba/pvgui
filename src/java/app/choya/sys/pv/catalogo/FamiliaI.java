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
public interface FamiliaI{
    
    public int setFamilia(FamiliaBean fb) throws SqlAppsException;
    
    public int updateFamilia(FamiliaBean fb) throws SqlAppsException;
    
    public int updateFamilia(FamiliaBean fb1, FamiliaBean fb2) throws SqlAppsException;
    
    public ListIterator<FamiliaBean> getFamilia() throws SqlAppsException;
    
    public FamiliaBean getFamilia(int id) throws SqlAppsException;
    
    public int deleteFamiliaBean(int id) throws SqlAppsException;
 
    
}
