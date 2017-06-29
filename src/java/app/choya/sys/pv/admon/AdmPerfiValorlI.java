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
 * interface utilizada para implementar las sentencias
 * utilizadas sobre la tabla ADM_PERFIL_VALORES
 * 03 - MAR -2015
 * 
 */

public interface AdmPerfiValorlI {
    
     public int setAdmPerfilValor(AdmPerfilValorBean pb) throws SqlAppsException;
    
    public int updateAdmPerfilValor(AdmPerfilValorBean pb) throws SqlAppsException;
      
    public ListIterator<AdmPerfilValorBean> getAdmPerfilValor() throws SqlAppsException;
    
}
