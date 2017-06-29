/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app.choya.sys.pv.admon;

import java.util.ListIterator;
import app.choya.sys.pv.dao.EApps;
import app.choya.sys.pv.dao.EDriver;

import app.choya.sys.pv.utils.SqlAppsException;

/**
 * 
 * @author Informatica
 */
public interface AdmUsuariosI {
    
    
    
    /**
     * setAdmUsuarios metodo para insertar un nuevo usuario al sistema
     * @param ub Objecto de tipo AdmUsuarioBean
     * @param driver
     * @param app
     * @return int  en caso de exito regresa el valor de la secuencia que se inserto,  
     * en caso de error regresa un valor menor de 0
     * @throws SqlAppsException excepcion que es lanzada a la interfaz grafica
     */
    public int setAdmUsuarios(AdmUsuariosBean ub, EDriver driver, EApps app) throws SqlAppsException;
    
 
    
    /**
     * updateAdmUsuarios metodo para actualizar el registro de un usuario
     * @param ub Objecto de tipo AdmUsuarioBean
     * @param driver
     * @param app
     * @return en caso de exito regresa 1,
     * en caso de error regresa un valor menor a 0
     * @throws SqlAppsException excepcion que es lanzada a la interfaz grafica
     */
    public int updateAdmUsuarios(AdmUsuariosBean ub, EDriver driver, EApps app) throws SqlAppsException;
    
    /**
     * getAdmUsuario este metodo obtiene la información del usuario buscado 
     * por medio de su campo clave o llave
     * @param usuario_id
     * @param driver
     * @param app
     * @return ListIterator<AdmUsuariosBean> una lista con la informacion del usuario buscado
     * @throws app.choya.sys.pv.utils.SqlAppsException
     */
   
    public AdmUsuariosBean getAdmUsuario(int usuario_id, EDriver driver, EApps app) throws SqlAppsException;
    
    /**
     * getAdmUsuario este metodo obtiene la información del usuario buscado
     * por medio del nombre de usuario unico.
     * @param usuario es el nombre de usuario unico
     * @param driver
     * @param app
     * @return ListIterator<AdmUsuariosBean> una lista con la información del usuario
     * que se busca
     * @throws SqlAppsException 
     */
    public ListIterator<AdmUsuariosBean> getAdmUsuario(String usuario, EDriver driver, EApps app) throws SqlAppsException;
    
    /**
     * getAdmUsuario este metodo obtiene la información del usuario buscado
     * por medio del nombre y apellido
     * @param nombre nombre(s) del usuario
     * @param apellido apellido(s) del usuario
     * @param driver
     * @param app
     * @return ListIterator<AdmUsuariosBean> una lista con la información del usuario
     * que se busca
     * @throws SqlAppsException 
     */
    public ListIterator<AdmUsuariosBean> getAdmUsuario(String nombre, String apellido, EDriver driver, EApps app) throws SqlAppsException;
    
    
    
    
    
}
