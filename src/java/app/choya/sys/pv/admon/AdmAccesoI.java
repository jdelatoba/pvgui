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
 * 17/02/15  creacion de la interface de la tabla ADM_ACCESOS
 * 
 */
public interface AdmAccesoI  {
        
    /**
     * 
     * @param ab AdmAccesoBean
     * @return consecutivo Acceso id que se inserto  identificar si existe alguna secuencia
     * 
     * @throws SiafesException 
     */
   public int setAdmAcceso(AdmAccesoBean ab) throws SiafesException; 
   /**
    * 
    * @param ab AdmAccesoBean
    * @return Acceso id que se modifico
    * @throws SiafesException 
    */
   public int updateAdmAcceso(AdmAccesoBean ab) throws SiafesException; 

   /**
     * getAdmAcceso
     * El metodo getAdmAcceso nos regresa una lista de elementos AdmAccesoBean
     * Accesos al sistema
     * @return ListIterator<AdmAccesoBean> información del los Accesos del sistema
     * de la tabla ADM_ACCESOS
     * @throws app.choya.sys.pv.utils.SiafesException
     * @since 17/feb/2015
     */
   public ListIterator<AdmAccesoBean> getAdmAcceso() throws SiafesException;
    
}
