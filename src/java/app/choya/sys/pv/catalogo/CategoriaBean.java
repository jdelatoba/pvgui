/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.catalogo;

import java.io.Serializable;

/**
 *
 * @author Rembao
 */
public class CategoriaBean implements Serializable{

    private int id;
    private int departamento_id;
    private String descripcion;
    private int creado_por;
    private String fecha_creacion;
    private int modificado_por;
    private String fecha_modificacion;
    private String serie;
    private String estatus;
    private String departamento;
    
    public CategoriaBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepartamento_id() {
        return departamento_id;
    }

    public void setDepartamento_id(int departamento_id) {
        this.departamento_id = departamento_id;
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

    public String getFecha_modificacion() {
        return fecha_modificacion;
    }

    public void setFecha_modificacion(String fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    
    
    
}
