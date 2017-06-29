/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.configuracion;

import java.io.Serializable;

/**
 *
 * @author Rembao
 */
public class PermisoBean implements Serializable {

    private int id;
    private int rol_id;
    private int funcion_id;
    private String clave;
    private String descripcion;
    private int creado_por;
    private String fecha_creacion;
    private int modificado_por;
    private String fecha_modificado;
    
    public PermisoBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRol_id() {
        return rol_id;
    }

    public void setRol_id(int rol_id) {
        this.rol_id = rol_id;
    }

    public int getFuncion_id() {
        return funcion_id;
    }

    public void setFuncion_id(int funcion_id) {
        this.funcion_id = funcion_id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCreado_por() {
        return creado_por;
    }

    public void setCreado_por(int creado_por) {
        this.creado_por = creado_por;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
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

    public String getFecha_modificado() {
        return fecha_modificado;
    }

    public void setFecha_modificado(String fecha_modificado) {
        this.fecha_modificado = fecha_modificado;
    }
    
    
    
    
    
}
