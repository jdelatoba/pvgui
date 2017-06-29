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
public interface AdmPerfilValorI {
    
     public int setAdmPerfilValor(AdmPerfilValorBean pb) throws SqlAppsException;
     public int updateAdmPerfilValor(AdmPerfilValorBean pb) throws SqlAppsException;
     public ListIterator<AdmPerfilValorBean> getAdmPerfilValor() throws SqlAppsException;     
     public AdmPerfilValorBean getAdmPerfilValor(int perfil_id) throws SqlAppsException;     

    /**
     *Metodo utilizado para hacer una consulta especifica por el ID del perfil su nivel y valor
     * @param perfil_id  consecutivo del perfil en la tabla ADM_PERFILES
     * @param nivel_id consecutivo utilizado en la tabla de ADM_PERFIL_VALOR para hacer referencia al nivel de perfil
     * @param valor
     * @return
     * @throws SqlAppsException
     */
    public AdmPerfilValorBean getAdmPerfilValor(int perfil_id,int nivel_id, String valor) throws SqlAppsException;
     
}
