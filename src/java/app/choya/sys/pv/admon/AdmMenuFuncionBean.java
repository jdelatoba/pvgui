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
public class AdmMenuFuncionBean implements Serializable, Auditable {

    private int menu_funcion_id;
    private int menu_id; //         MENU_ID            NOT NULL NUMBER      consecutivo
    private int menu_secuencia; //  MENU_SECUENCIA     NOT NULL NUMBER        
    private String prompt; //       PROMPT             NOT NULL VARCHAR2(30)  
    private String tipo_accion; //  TIPO_ACCION        NOT NULL VARCHAR2(1)   
    private int accion_id;//        ACCION_ID          NOT NULL NUMBER        
    private String fecha_modificacion;//FECHA_MODIFICACION NOT NULL DATE          
    private int modificado_por; //  MODIFICADO_POR     NOT NULL NUMBER(15)    
    private String fecha_creacion;//FECHA_CREACION     NOT NULL DATE          
    private int creado_por; //      CREADO_POR         NOT NULL NUMBER(15)    
    private String descripcion;//   DESCRIPCION                 VARCHAR2(240) 
    private AdmFuncionBean funcion;
    private AdmMenuBean menu;

    public AdmMenuFuncionBean() {
    }

    public int getMenu_funcion_id() {
        return menu_funcion_id;
    }

    public void setMenu_funcion_id(int menu_funcion_id) {
        this.menu_funcion_id = menu_funcion_id;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public AdmFuncionBean getFuncion() {
        return funcion;
    }

    public void setFuncion(AdmFuncionBean funcion) {
        this.funcion = funcion;
    }

    public AdmMenuBean getMenu() {
        return menu;
    }

    public void setMenu(AdmMenuBean menu) {
        this.menu = menu;
    }

    public int getMenu_secuencia() {
        return menu_secuencia;
    }

    public void setMenu_secuencia(int menu_secuencia) {
        this.menu_secuencia = menu_secuencia;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getTipo_accion() {
        return tipo_accion;
    }

    public void setTipo_accion(String tipo_accion) {
        this.tipo_accion = tipo_accion;
    }

    public int getAccion_id() {
        return accion_id;
    }

    public void setAccion_id(int accion_id) {
        this.accion_id = accion_id;
    }

    public String getDescripcion() {
        return descripcion;
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
