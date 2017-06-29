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
public class AdmFuncionBean implements Serializable, Auditable{
    
private int funcion_id;//       FUNCION_ID         NOT NULL NUMBER         
private String nombre; //       NOMBRE             NOT NULL VARCHAR2(30)   
private int aplicacion_id;//    APLICACION_ID               NUMBER         
private String parametros; //   PARAMETROS                  VARCHAR2(2000) 
private String forma; //        FORMA              NOT NULL VARCHAR2(50)   
private String fecha_creacion;//FECHA_CREACION     NOT NULL DATE           
private int creado_por; //      CREADO_POR         NOT NULL NUMBER(15)     
private String fecha_modificacion;//   FECHA_MODIFICACION NOT NULL DATE           
private int modificado_por; //  MODIFICADO_POR     NOT NULL NUMBER(15)  
private String aplicacion;
    public AdmFuncionBean(){
    }

    public String getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }
    
    
    public int getFuncion_id() {
        return funcion_id;
    }

    public void setFuncion_id(int funcion_id) {
        this.funcion_id = funcion_id;
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

    public int getAplicacion_id() {
        return aplicacion_id;
    }

    public void setAplicacion_id(int aplicacion_id) {
        this.aplicacion_id = aplicacion_id;
    }

    public String getParametros() {
        if (parametros != null) {
            return parametros;
        } else {
            return "";
        }
    }

    public void setParametros(String parametros) {
        this.parametros = parametros;
    }

    public String getForma() {
        if (forma != null) {
            return forma;
        } else {
            return "";
        }
    }

    public void setForma(String forma) {
        this.forma = forma;
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
        return "1";
    }

    @Override
    public void setDirip(String dirip) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getHostname() {
        return "1";
    }

    @Override
    public void setHostname(String hostname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
