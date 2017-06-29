/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.admon;

import java.io.Serializable;

/**
 *
 * @author developer
 */
public class AdmBitacoraBean implements Serializable {

    private int creado_por;
    private String fecha_creacion;
    private int modificado_por;
    private String fecha_modificacion;
    private String dirip;
    private String hostname;

    public AdmBitacoraBean() {
    }

    public int getCreado_por() {
        return creado_por;
    }

    public void setCreado_por(int creado_por) {
        this.creado_por = creado_por;
    }

    public String getFecha_creacion() {
        if (fecha_creacion != null) {
            return fecha_creacion;
        } else {
            return "";
        }
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public int getModificado_por() {
        return modificado_por;
    }

    public void setModificado_por(int modificado_por) {
        this.modificado_por = modificado_por;
    }

    public String getFecha_modificacion() {
        if (fecha_modificacion != null) {
            return fecha_modificacion;
        } else {
            return "";
        }
    }

    public void setFecha_modificacion(String fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

    public String getDirip() {
        if (dirip != null) {
            return dirip;
        } else {
            return "";
        }
    }

    public void setDirip(String dirip) {
        this.dirip = dirip;
    }

    public String getHostname() {
        if (hostname != null) {
            return hostname;
        } else {
            return "";
        }
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

}
