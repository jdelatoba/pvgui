/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.admon;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Rembao
 */
public class BitacoraUsuarioBean implements Serializable {
    
    private int id;
    private Date fecha;
    private String sql;
    private int tipo_sql;
    private int funcion;
    private String datos;
    
    
    public BitacoraUsuarioBean(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public int getTipo_sql() {
        return tipo_sql;
    }

    public void setTipo_sql(int tipo_sql) {
        this.tipo_sql = tipo_sql;
    }

    public int getFuncion() {
        return funcion;
    }

    public void setFuncion(int funcion) {
        this.funcion = funcion;
    }
    
    
    
}
