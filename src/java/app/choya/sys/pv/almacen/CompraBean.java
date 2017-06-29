/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.almacen;

import java.io.Serializable;

/**
 *
 * @author Condese
 */
public class CompraBean implements Serializable{

private int id;
private int id_proveedor;
private int id_solicita;
private int id_recibe;
private String estatus;
private String serie;
private String Observacion;
private int creado_por;
private String fecha_creacion;
private int modificado_por;
private String fecha_modificacion;
      
public CompraBean(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public int getId_solicita() {
        return id_solicita;
    }

    public void setId_solicita(int id_solicita) {
        this.id_solicita = id_solicita;
    }

    public int getId_recibe() {
        return id_recibe;
    }

    public void setId_recibe(int id_recibe) {
        this.id_recibe = id_recibe;
    }

  
    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
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
   
       public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion){
        this.Observacion = Observacion;
    }

}