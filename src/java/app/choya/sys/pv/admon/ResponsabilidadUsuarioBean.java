/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.admon;

import java.io.Serializable;
import app.choya.sys.pv.admon.AdmUsuarioResponsabilidadBean;

/**
 *
 * @author carlos.rembao
 */
public class ResponsabilidadUsuarioBean
        extends AdmUsuarioResponsabilidadBean
        implements Serializable {

    private int aplicacion_id;
    private String nombre;
    private int menu_id;
    private int grupo_reporte_id;

    public ResponsabilidadUsuarioBean() {
    }

    public int getAplicacion_id() {
        return aplicacion_id;
    }

    public void setAplicacion_id(int aplicacion_id) {
        this.aplicacion_id = aplicacion_id;
    }

    public String getNombre() {
        if (nombre != null) {
            return nombre;
        } else {
            return "";
        }
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public int getGrupo_reporte_id() {
        return grupo_reporte_id;
    }

    public void setGrupo_reporte_id(int grupo_reporte_id) {
        this.grupo_reporte_id = grupo_reporte_id;
    }

}
