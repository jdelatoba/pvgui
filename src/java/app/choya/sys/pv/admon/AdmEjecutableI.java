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
 * interface de la tabla ADM_EJECUTABLES
 */
public interface AdmEjecutableI {
    
    public int setAdmEjecutable(AdmEjecutableBean eb) throws SiafesException;
    
    public int updateAdmEjecutable(AdmEjecutableBean eb) throws SiafesException;
      
    public ListIterator<AdmEjecutableBean> getAdmEjecutable() throws SiafesException;     
}
