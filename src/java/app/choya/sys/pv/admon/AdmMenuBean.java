/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.admon;
import java.io.Serializable;
/**
 *
 * @author Icosio
 */
public class AdmMenuBean implements Serializable, Auditable {
private int menu_id;//              MENU_ID            NOT NULL NUMBER        
private int aplicacion_id;//        APLICACION_ID      NOT NULL NUMBER        
private String nombre; //           NOMBRE             NOT NULL VARCHAR2(100) 
private String fecha_modificacion;//FECHA_MODIFICACION NOT NULL DATE          
private int modificado_por;//       MODIFICADO_POR     NOT NULL NUMBER(15)    
private String fecha_creacion; //   FECHA_CREACION     NOT NULL DATE          
private int creado_por; //          CREADO_POR         NOT NULL NUMBER(15)    
private String descripcion; //      DESCRIPCION                 VARCHAR2(240)

    public AdmMenuBean(){
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
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

    public String getDescripcion() {
        if (descripcion != null) {
            return descripcion;
        } else {
            return "";
        }       
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
          if (fecha_creacion != null) {
            return fecha_creacion;
        } else {
            return "";
        }       
        
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
