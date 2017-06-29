/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app.choya.sys.pv.admon;

/**
 *
 * @author developer
 */
public interface Auditable {
    
    public int getCreado_por();
    public void setCreado_por(int creado_por);
    public String getFecha_creacion();
    public void setFecha_creacion(String fecha_creacion);
    public int getModificado_por();
    public void setModificado_por(int modificado_por);
    public String getFecha_modificacion();
    public void setFecha_modificacion(String fecha_modificacion);
    public String getDirip();
    public void setDirip(String dirip);
    public String getHostname();
    public void setHostname(String hostname);
    
}
