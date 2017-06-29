/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.admon;

import java.util.ListIterator;
import app.choya.sys.pv.utils.SqlAppsException;

/**
 *
 * @author Icosio
 */
public interface AdmPerfilI {
    
    /**
     * Metodo para insertar registros a la tabla de ADM_PERFILES 
     * @param pb
     * @return regresa un valor si fue existosa la insercion
     * @throws SqlAppsException 
     */
    public int setAdmPerfil(AdmPerfilBean pb) throws SqlAppsException;
    /**
     * Metodo para actuliazar un registro completo en ADM_PERILES
     * @param pb resibo un bean  para vaciar la actualizacion
     * @return regrsa un valor si la actualizacion fue exitosa
     * @throws SqlAppsException 
     */
    public int updateAdmPerfil(AdmPerfilBean pb) throws SqlAppsException;
      
    /**
     * consulta todos los registros de la tabla ADM_PERFILES
     * @return resresa una lista de todoos los perfiles dados de alta
     * @throws SqlAppsException 
     */
    public ListIterator<AdmPerfilBean> getAdmPerfil() throws SqlAppsException;
    /**
     * Metodo que consulta los perfiles por nombre
     * @param Nombre
     * @return regresa una lista de aquellos perfiles que tengan este nombre
     * @throws SqlSiafesException     /**
     * Metodo que consulta los perfiles por nombre
     * @param Nombre
     * @return regresa una lista de aquellos perfiles que tengan este nombre
     * @throws SqlSiafesException 
     */
    
    /**
     * Metodo que consulta los perfiles por nombre
     * @param Nombre
     * @return regresa una lista de aquellos perfiles que tengan este nombre
     * @throws SqlAppsException
     */
    public ListIterator<AdmPerfilBean> getAdmPerfil(String Nombre) throws SqlAppsException;
}
