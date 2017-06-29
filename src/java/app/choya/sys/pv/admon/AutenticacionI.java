/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.admon;

import app.choya.sys.pv.dao.EApps;
import app.choya.sys.pv.dao.EDriver;

import app.choya.sys.pv.utils.SqlAppsException;

/**
 *
 * @author carlos rembao
 */
public interface AutenticacionI {

    /**
     * doLogin es el metodo que se utiliza para validar las credenciales
     * del usuario que quiere acceder al sistema
     * @param usuario identificador unico de usuario
     * @param contrasena
     * @param driver
     * @param app
     * @return los valores de regreso
     * 1 usuario y contraseña correctos login exitoso
     * 2 contraseña expirada
     * -1 usuario no existe
     * -2 usuario caducado
     * -3 password invalido
     * @throws SqlAppsException 
     */
    public int doLogin(String usuario, String contrasena, EDriver driver
            , EApps app) throws SqlAppsException;
    
    /**
     * 
     * @param usuario_id
     * @param contrasena_anterior
     * @param contrasena_nueva
     * @param driver
     * @param app
     * @return
     * @throws SqlAppsException 
     */
    public int updateContrasena(int usuario_id
            , String contrasena_anterior
            , String contrasena_nueva
            ) throws SqlAppsException;
    
    
    
    
}
