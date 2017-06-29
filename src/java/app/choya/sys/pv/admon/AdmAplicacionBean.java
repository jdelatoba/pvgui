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
public class AdmAplicacionBean implements Auditable, Serializable {

    private int aplicacion_id;
    private String nombre;
    private String dir_formas;
    private String dir_reportes;
    private String usuario_app;
    private String fecha_creacion;
    private int creado_por;
    private String fecha_modificacion;
    private int modificado_por;
    private String dir_bin;
    private String dir_sql;
    private String dirip;
    private String hostname;

    public AdmAplicacionBean() {
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

    public String getDir_formas() {
        if (dir_formas != null) {
            return dir_formas;
        } else {
            return "";
        }
    }

    public void setDir_formas(String dir_formas) {
        this.dir_formas = dir_formas;
    }

    public String getDir_reportes() {
        if (dir_reportes != null) {
            return dir_reportes;
        } else {
            return "";
        }
    }

    public void setDir_reportes(String dir_reportes) {
        this.dir_reportes = dir_reportes;
    }

    public String getUsuario_app() {
        if (usuario_app != null) {
            return usuario_app;
        } else {
            return "";
        }
    }

    public void setUsuario_app(String usuario_app) {
        this.usuario_app = usuario_app;
    }

    public String getDir_bin() {
        if (dir_bin != null) {
            return dir_bin;
        } else {
            return "";
        }
    }

    public void setDir_bin(String dir_bin) {
        this.dir_bin = dir_bin;
    }

    public String getDir_sql() {
        if (dir_sql != null) {
            return dir_sql;
        } else {
            return "";
        }
    }

    public void setDir_sql(String dir_sql) {
        this.dir_sql = dir_sql;
    }

    @Override
    public int getCreado_por() {
        return creado_por;
    }

    @Override
    public void setCreado_por(int creado_por) {
        this.creado_por = creado_por;
    }

    @Override
    public String getFecha_creacion() {
        return fecha_creacion;
    }

    @Override
    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    @Override
    public int getModificado_por() {
        return modificado_por;
    }

    @Override
    public void setModificado_por(int modificado_por) {
        this.modificado_por = modificado_por;
    }

    @Override
    public String getFecha_modificacion() {
        if (fecha_modificacion != null) {
            return fecha_modificacion;
        } else {
            return "";
        }
    }

    @Override
    public void setFecha_modificacion(String fecha_modificacion) {

        this.fecha_modificacion = fecha_modificacion;
    }

    @Override
    public String getDirip() {
        if (dirip != null) {
            return dirip;
        } else {
            return "";
        }
    }

    @Override
    public void setDirip(String dirip) {
        this.dirip = dirip;
    }

    @Override
    public String getHostname() {
        if (hostname != null) {
            return hostname;
        } else {
            return "";
        }
    }

    @Override
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

}
