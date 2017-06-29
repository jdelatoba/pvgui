/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app.choya.sys.pv.admon;

import java.util.ListIterator;

/**
 *
 * @author developer
 */
public interface AdmDependenciaI {
    
    public ListIterator<AdmDependenciaBean> getListadoDependencia();
    
    public int setAdmDependencia(AdmDependenciaBean db);
    
    public int updateAdmDependencia(AdmDependenciaBean db);
    
    public ListIterator<AdmDependenciaBean> getListadoAdmDependencia();
    
    public AdmDependenciaBean getAdmDependencia(int dependencia_id);
    
}
