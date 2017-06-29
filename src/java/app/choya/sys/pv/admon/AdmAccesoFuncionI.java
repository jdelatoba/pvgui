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
 * Creacion de la intterface del uso de la tabla ADM_ACCESOS_FUNCIONES
 */
public interface AdmAccesoFuncionI {
    /**
     * 
     * @param afb AdmAccesoFuncionBean
     * @return regesa un valor positivo si la insercion se realizo con exito
     * @throws SiafesException   excepciones predefinidas en caso de algun fallo
     */
    
    public int setAdmAccesoFuncion(AdmAccesoFuncionBean afb) throws SiafesException; 
     
    /***
     * 
     * @param afb AdmAccesoFuncionBean 
     * @return regresa un valor positivo si la actualizacion se realizo con exito
     * @throws SiafesException 
     */
    public int updateAdmAccesoFuncion(AdmAccesoFuncionBean afb) throws SiafesException; 
     
    /**
     * 
     * @return regresa la lista de los Accesos a las pantallas de la aplicacion
     * @throws SiafesException 
     */
    public ListIterator<AdmAccesoFuncionBean> getAdmAccesoFuncion() throws SiafesException;
}
