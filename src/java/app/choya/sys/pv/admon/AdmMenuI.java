/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.admon;
    
import java.util.ListIterator;
import app.choya.sys.pv.utils.SiafesException;

/**
 *
 * @author Icosio
 * Interface para la implementacion de los metodos 
 * que utilizara los sentencias para la manipulacion de la
 * tabla ADM_MENUS
 */
public interface AdmMenuI {
    
 
    /**
     * metodo de insercion sobre la tabla ADM_MENUS
     * @param mb bean AdmMenuBean
     * @return regresa un 1 si la insercion fue exitosa
     * @throws SiafesException 
     */
    public int setAdmMenu(AdmMenuBean mb) throws SiafesException;
    
    /**
     * Metodo para actualizar los campos de la tabla ADM_MENUS
     * @param mb bean AdmMenuBean
     * @return regresa un 1 si la acutalizacion fue exitosa
     * @throws SiafesException 
     */
    
    public int updateAdmMenu(AdmMenuBean mb) throws SiafesException;
    
    /**
     * metodo para traernos un listado de los elementos de la tabla
     * ADM_MENUS
     * @return regresa una lista con los elementos de la tabla
     * @throws SiafesException 
     */
         
    public ListIterator<AdmMenuBean> getAdmMenu () throws SiafesException;    
}
