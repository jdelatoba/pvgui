/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.admon;

import app.choya.sys.pv.utils.SqlAppsException;
import java.util.ListIterator;
import app.choya.sys.pv.dao.EApps;
import app.choya.sys.pv.dao.EDriver;

/**
 *
 * @author carlos.rembao
 */
public interface ResponsabilidadI {

    /**
     * 
     * @param usuario_id identificador unico de usuario
     * @param driver driver de conexion de base de datos
     * @param app aplicación con la cual se esta trabajando
     * @return ListIterator<ResponsabilidadUsuarioBean> que 
     * contiene las responsabilidades
     * asignada para el usuario especificado
     * @throws SqlAppsException 
     */
    public ListIterator<ResponsabilidadUsuarioBean> getListaReponsabilidadByUsuarioId(
            int usuario_id
            ,EDriver driver
            ,EApps app) throws SqlAppsException;
    
    
    /**
     * 
     * @param responsabilidad_id identificador unico de responsabilidad 
     * @param driver driver de conexion de base de datos
     * @param app aplicación con la cual se esta trabajando
     * @return AdmResponsabilidad la información de la responsabilidad
     * que se especifica
     * @throws SqlAppsException 
     */
    public AdmResponsabilidadBean getInfoResponsabilidad(
            int responsabilidad_id
            ,EDriver driver
            ,EApps app) throws SqlAppsException;
}
