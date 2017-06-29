/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.configuracion;

import java.io.Serializable;

/**
 *
 * @author Rembao
 */
public class EmpresaBean implements Serializable {

    private int id;
    private String nombre;
    private String domicilio;
    private String ciudad;
    private String num_ext;
    private String num_int;
    
    private String entidad;
    private String codigo_postal;
    private String telefono;
    private String celular;
    private String email;
    private String nombre_fiscal;
    private String rfc;
    private String curp;
    private String adicional;
    private String regimen_fiscal;
    
    private String domicilio_fiscal;
    private String colonia_fiscal;
    private String num_ext_fiscal;
    private String num_int_fiscal;
    private String codigo_postal_fiscal;
    private String localidad_fiscal;
    private String ciudad_fiscal;
    private String entidad_fiscal;
    private String pais_fiscal;
    private String serie;
    private String estatus;
    
    private int creado_por;
    private String fecha_creacion;
    private int modificado_por;
    private String fecha_modificacion;
    private int sucursal;
    
    public EmpresaBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre_fiscal() {
        return nombre_fiscal;
    }

    public void setNombre_fiscal(String nombre_fiscal) {
        this.nombre_fiscal = nombre_fiscal;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getAdicional() {
        return adicional;
    }

    public void setAdicional(String adicional) {
        this.adicional = adicional;
    }

    public String getRegimen_fiscal() {
        return regimen_fiscal;
    }

    public void setRegimen_fiscal(String regimen_fiscal) {
        this.regimen_fiscal = regimen_fiscal;
    }

    public String getDomicilio_fiscal() {
        return domicilio_fiscal;
    }

    public void setDomicilio_fiscal(String domicilio_fiscal) {
        this.domicilio_fiscal = domicilio_fiscal;
    }

    public String getColonia_fiscal() {
        return colonia_fiscal;
    }

    public void setColonia_fiscal(String colonia_fiscal) {
        this.colonia_fiscal = colonia_fiscal;
    }

    public String getNum_ext_fiscal() {
        return num_ext_fiscal;
    }

    public void setNum_ext_fiscal(String num_ext_fiscal) {
        this.num_ext_fiscal = num_ext_fiscal;
    }

    public String getNum_int_fiscal() {
        return num_int_fiscal;
    }

    public void setNum_int_fiscal(String num_int_fiscal) {
        this.num_int_fiscal = num_int_fiscal;
    }

    public String getCodigo_postal_fiscal() {
        return codigo_postal_fiscal;
    }

    public void setCodigo_postal_fiscal(String codigo_postal_fiscal) {
        this.codigo_postal_fiscal = codigo_postal_fiscal;
    }

    public String getLocalidad_fiscal() {
        return localidad_fiscal;
    }

    public void setLocalidad_fiscal(String localidad_fiscal) {
        this.localidad_fiscal = localidad_fiscal;
    }

    public String getCiudad_fiscal() {
        return ciudad_fiscal;
    }

    public void setCiudad_fiscal(String ciudad_fiscal) {
        this.ciudad_fiscal = ciudad_fiscal;
    }

    public String getEntidad_fiscal() {
        return entidad_fiscal;
    }

    public void setEntidad_fiscal(String entidad_fiscal) {
        this.entidad_fiscal = entidad_fiscal;
    }

    public String getPais_fiscal() {
        return pais_fiscal;
    }

    public void setPais_fiscal(String pais_fiscal) {
        this.pais_fiscal = pais_fiscal;
    }

    public String getNum_ext() {
        return num_ext;
    }

    public void setNum_ext(String num_ext) {
        this.num_ext = num_ext;
    }

    public String getNum_int() {
        return num_int;
    }

    public void setNum_int(String num_int) {
        this.num_int = num_int;
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

    public int getSucursal() {
        return sucursal;
    }

    public void setSucursal(int sucursal) {
        this.sucursal = sucursal;
    }
    
    
    
    
}
