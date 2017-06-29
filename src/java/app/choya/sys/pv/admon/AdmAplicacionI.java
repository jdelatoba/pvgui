/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.admon;

import java.util.ListIterator;
import app.choya.sys.pv.utils.SqlAppsException;
import java.util.ArrayList;

/**
 *
 * @author developer
 */
public interface AdmAplicacionI {

     /**
     *
     * @param ab AdmAplicacionBean
     * @return el numero de aplicacion_id que se interto o 0 si se a producido
     * un * error
     * @throws SqlAppsException lanza una excepción si se producido un error
     * de sql
     */

    public int setAdmAplicacion(AdmAplicacionBean ab) throws SqlAppsException;

    public int updateAdmAplicacion(AdmAplicacionBean ab) throws SqlAppsException;

    public AdmAplicacionBean getAdmAplicacionById(int aplicacion_id) throws SqlAppsException;

    public ListIterator<AdmAplicacionBean> getAdmAplicacion() throws SqlAppsException;

    public ListIterator<AdmAplicacionBean> getAdmAplicacion(String ordenaPor) throws SqlAppsException;
    
    public ListIterator<AdmAplicacionBean> getAdmAplicacion(String ordenaPor, int desde, int hasta) throws SqlAppsException;

}
