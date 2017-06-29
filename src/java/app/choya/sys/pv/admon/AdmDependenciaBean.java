/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app.choya.sys.pv.admon;

import java.io.Serializable;

/**
 *
 * @author developer
 */
public class AdmDependenciaBean implements Serializable{
    
    private int dependencia_id;
    private String nombre;
    private String calle1;
    private String calle2;
    private String calle3;
    private String no_exterior;
    private String no_interior;
    private String codigo_postal;
    private String colonia;
    private String telefono1;
    private String telefono2;
    private int responsable_id;
    private String correo;
    private int creado_por;
    private String fecha_creacion;
    private int modificado_por;
    private String fecha_modificacion;
    
    
    public AdmDependenciaBean(){}

    public int getDependencia_id() {
        return dependencia_id;
    }

    public void setDependencia_id(int dependencia_id) {
        this.dependencia_id = dependencia_id;
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

    public String getCalle1() {
        if(calle1 != null)
            return calle1;
        else
            return "";
        
    }

    public void setCalle1(String calle1) {
        this.calle1 = calle1;
    }

    public String getCalle2() {
       
        if(calle2 != null)
            return calle2;
        else
            return "";
    }

    public void setCalle2(String calle2) {
        this.calle2 = calle2;
    }

    public String getCalle3() {
        
        if(calle3 != null)
            return calle3;
        else
            return "";
    }

    public void setCalle3(String calle3) {
        this.calle3 = calle3;
    }

    public String getNo_exterior() {
        if(no_exterior != null)
            return no_exterior;
        else
            return "";
  
    }

    public void setNo_exterior(String no_exterior) {
        this.no_exterior = no_exterior;
    }

    public String getNo_interior() {
        if(no_interior != null)
            return no_interior;
        else
            return "";
    }

    public void setNo_interior(String no_interior) {
        this.no_interior = no_interior;
    }

    public String getCodigo_postal() {
        if(codigo_postal != null)
            return codigo_postal;
        else
            return "";
        
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public String getColonia() {
        if(colonia != null)
        return colonia;
        else
            return "";
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getTelefono1() {
        if(telefono1 != null)
        return telefono1;
        else
            return "";
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        if(telefono2 != null)
        return telefono2;
        else
            return "";
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public int getResponsable_id() {
        return responsable_id;
    }

    public void setResponsable_id(int responsable_id) {
        this.responsable_id = responsable_id;
    }

    public String getCorreo() {
        if(correo != null)
        return correo;
        else
            return "";
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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
