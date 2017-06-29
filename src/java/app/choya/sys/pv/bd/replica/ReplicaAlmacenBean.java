/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.bd.replica;

import java.io.Serializable;

/**
 *
 * @author Rembao
 */
public class ReplicaAlmacenBean implements Serializable{

    private int id;
    private char tipo_sentencia;
    private String sql;
    private String resultado;
    private int almacen_origen;
    private int almacen_destino;
    private String fecha_ejecucion;
    private String mensaje;
    private int creado_por;
    private String fecha_creacion;
    
    public ReplicaAlmacenBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getTipo_sentencia() {
        return tipo_sentencia;
    }

    public void setTipo_sentencia(char tipo_sentencia) {
        this.tipo_sentencia = tipo_sentencia;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public int getAlmacen_origen() {
        return almacen_origen;
    }

    public void setAlmacen_origen(int almacen_origen) {
        this.almacen_origen = almacen_origen;
    }

    public int getAlmacen_destino() {
        return almacen_destino;
    }

    public void setAlmacen_destino(int almacen_destino) {
        this.almacen_destino = almacen_destino;
    }

    public String getFecha_ejecucion() {
        return fecha_ejecucion;
    }

    public void setFecha_ejecucion(String fecha_ejecucion) {
        this.fecha_ejecucion = fecha_ejecucion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
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
    
    
    
}
