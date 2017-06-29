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
 * interface utilizada para la implementacion de las sentencias
 * sobre la tabla ADM_FUNCIONES utilizando el bean AdmFuncionBean
 * 20-feb-2015
 */
public interface AdmFuncionI {
    
    /**
     * metodo de insercion sobre la tabla ADM_FUNCIONES
     * @param fb bean AdmFuncionBean
     * @return regresa un 1 si la insercion fue exitosa
     * @throws SiafesException 
     */
    public int setAdmFuncion(AdmFuncionBean fb, EDriver driver, EApps app) throws SqlAppsException;
    
    /**
     * Metodo para actualizar los campos de la tabla ADM_FUNCIONES
     * @param fb bean AdmFuncionBean
     * @return regresa un 1 si la acutalizacion fue exitosa
     * @throws SiafesException 
     */
    
    public int updateAdmFuncion(AdmFuncionBean fb, EDriver driver, EApps app) throws SqlAppsException;
    
    /**
     * metodo para traernos un listado de los elementos de la tabla
     * ADM_FUNCIONES
     * @return regresa una lista con los elementos de la tabla
     * @throws SiafesException 
     */
         
    public ListIterator<AdmFuncionBean> getAdmFuncion(EDriver driver, EApps app) throws SqlAppsException;
    
    
    public AdmFuncionBean getAdmFuncion(int funcion_id, EDriver driver, EApps app) throws SqlAppsException;
    
}
