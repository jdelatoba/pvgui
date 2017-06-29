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
 * Interface utilizada para la implementacion de las sentencias
 * sobre la tabla ADM_RESPONSABILIDADES.
 * 03-MAR-2015
 */
public interface AdmResponsabilidadI {
     
    public int setAdmResponsabilidad(AdmResponsabilidadBean rb, EDriver driver, EApps app) throws SqlAppsException;
    
    public int updateAdmResponsabilidad(AdmResponsabilidadBean rb, EDriver driver, EApps app) throws SqlAppsException;
      
    public ListIterator<AdmResponsabilidadBean> getAdmResponsabilidad(EDriver driver, EApps app) throws SqlAppsException;
    /**
     * Metodo utilizado para regresar una lista de responsabilidades
     * asignadas a un usuario utilzando las tablas de ADM_RESPONSABILIDADES
     * y ADM_USUARIORESPONSABILDADES filtrando por el campo usuario_id
     * @param usuario_id
     * @return una lista de responsabildades asingadas al usuario del parametro
     * @throws SqlSiafesException     /**
     * Metodo utilizado para regresar una lista de responsabilidades
     * asignadas a un usuario utilzando las tablas de ADM_RESPONSABILIDADES
     * y ADM_USUARIORESPONSABILDADES filtrando por el campo usuario_id
     * @param usuario_id
     * @return una lista de responsabildades asingadas al usuario del parametro
     * @throws SqlSiafesException 
     */
    
    /**
     * Metodo utilizado para regresar una lista de responsabilidades
 asignadas a un usuario utilzando las tablas de ADM_RESPONSABILIDADES
 y ADM_USUARIORESPONSABILDADES filtrando por el campo usuario_id
     * @param usuario_id
     * @return una lista de responsabildades asingadas al usuario del parametro
     * @throws SqlAppsException
     */
    public ListIterator<AdmResponsabilidadBean> getAdmResponsabilidadUsuario(int usuario_id, EDriver driver, EApps app) throws SqlAppsException;
   
    /**
     * Metodo utilizado para filtrar 
     * @param valor
     * @return un listado filtrado de la lista de las responsabilidads por usuario
     * @throws SqlAppsException 
     */
    public ListIterator<AdmResponsabilidadBean> getAdmResponsabilidadFiltro(int usuario_id, String valor, EDriver driver, EApps app) throws SqlAppsException;

        
}
