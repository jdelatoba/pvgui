/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.admon;

import java.util.ListIterator;
import app.choya.sys.pv.utils.SqlAppsException;
import app.choya.sys.pv.dao.EApps;
import app.choya.sys.pv.dao.EDriver;
/**
 *
 * @author Icosio
 */
public interface AdmMenuFuncionI {
    /**
     * 
     * @param pb
     * @return
     * @throws SqlAppsException 
     */
    
    public int setAdmMenuFuncionI(AdmMenuFuncionBean pb) throws SqlAppsException;
    
    public int updateAdmMenuFuncion(AdmMenuFuncionBean pb) throws SqlAppsException;
      
    public ListIterator<AdmMenuFuncionBean> getAdmMenuFuncion() throws SqlAppsException;
    
    public ListIterator<AdmMenuFuncionBean> getAdmMenuFuncion(int menu_id, EDriver driver, EApps app) throws SqlAppsException;
    
}
