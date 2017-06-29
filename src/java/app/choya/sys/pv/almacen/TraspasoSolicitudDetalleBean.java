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
public class TraspasoSolicitudDetalleBean implements Serializable {
    
    private int id;
    private int id_traspaso_solicitud;
    private int id_articulo;
    private int cantidad;
    private int sugerida;
    private int existencia_origen;
    private int existencia_destino;
    private String estatus;
    private int creado_por;
    private String fecha_creacion;
    private int modificado_por;
    private String fecha_modificacion;
    
    public TraspasoSolicitudDetalleBean(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_traspaso_solicitud() {
        return id_traspaso_solicitud;
    }

    public void setId_traspaso_solicitud(int id_traspaso_solicitud) {
        this.id_traspaso_solicitud = id_traspaso_solicitud;
    }

    public int getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(int id_articulo) {
        this.id_articulo = id_articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getSugerida() {
        return sugerida;
    }

    public void setSugerida(int sugerida) {
        this.sugerida = sugerida;
    }

    public int getExistencia_origen() {
        return existencia_origen;
    }

    public void setExistencia_origen(int existencia_origen) {
        this.existencia_origen = existencia_origen;
    }

    public int getExistencia_destino() {
        return existencia_destino;
    }

    public void setExistencia_destino(int existencia_destino) {
        this.existencia_destino = existencia_destino;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
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
    
}
