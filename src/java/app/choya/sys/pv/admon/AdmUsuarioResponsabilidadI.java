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
public interface AdmUsuarioResponsabilidadI {

    /**
     *
     * @param ub
     * @return
     * @throws SqlAppsException
     */
    public int setAdmUsuarioResponsabilidad(AdmUsuarioResponsabilidadBean ub, EDriver driver, EApps app) throws SqlAppsException;
    
    public int updateAdmUsuarioResponsabilidad(AdmUsuarioResponsabilidadBean ub, EDriver driver, EApps app) throws SqlAppsException;
      
    public ListIterator<AdmUsuarioResponsabilidadBean> getAdmUsuarioResponsabilidad(EDriver driver, EApps app) throws SqlAppsException;
}
