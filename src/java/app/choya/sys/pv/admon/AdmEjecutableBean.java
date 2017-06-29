/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.admon;
import java.io.Serializable;
import app.choya.sys.pv.admon.Auditable;
/**
 *
 * @author Icosio
 * Bean de la tabla ADM_EJECUTABLES 
 */
public class AdmEjecutableBean implements Serializable, Auditable{
private int aplicacion_id;          //APLICACION_ID      NOT NULL NUMBER(4)     
private int ejecutable_id;          //EJECUTABLE_ID      NOT NULL NUMBER(15)    
private String nombre;              //NOMBRE             NOT NULL VARCHAR2(30)  
private String descripcion;         //DESCRIPCION                 VARCHAR2(240) 
private String metodo_ejecucion;    //METODO_EJECUCION   NOT NULL VARCHAR2(1)   
private String archivo_ejecutable;  //ARCHIVO_EJECUTABLE          VARCHAR2(30)  
private String fecha_creacion;       //FECHA_CREACION     NOT NULL DATE          
private int creado_por;             //CREADO_POR         NOT NULL NUMBER(15)    
private String fecha_modificacion;  //FECHA_MODIFICACION NOT NULL DATE          
private int modificado_por;         // MODIFICADO_POR     NOT NULL NUMBER(15) 

    public AdmEjecutableBean(){
    }
    
    public int getAplicacion_id() {
        return aplicacion_id;
    }

    public void setAplicacion_id(int aplicacion_id) {
        this.aplicacion_id = aplicacion_id;
    }

    public int getEjecutable_id() {
        return ejecutable_id;
    }

    public void setEjecutable_id(int ejecutable_id) {
        this.ejecutable_id = ejecutable_id;
    }

    public String getNombre() {
          if(nombre != null)
            return nombre;
        else
            return "";
        
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
          if(descripcion != null)
            return descripcion;
        else
            return "";
        
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMetodo_ejecucion() {
        
        if(metodo_ejecucion != null)
            return metodo_ejecucion;
        else
            return "";
       
    }

    public void setMetodo_ejecucion(String metodo_ejecucion) {
        this.metodo_ejecucion = metodo_ejecucion;
    }

    public String getArchivo_ejecutable() {
                if(archivo_ejecutable != null)
            return archivo_ejecutable;
        else
            return "";
       
    }

    public void setArchivo_ejecutable(String archivo_ejecutable) {
        this.archivo_ejecutable = archivo_ejecutable;
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
        return fecha_modificacion;
    }

    @Override
    public void setFecha_modificacion(String fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

    @Override
    public String getDirip() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDirip(String dirip) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getHostname() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setHostname(String hostname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
