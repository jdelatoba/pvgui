/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.catalogo;

import java.io.Serializable;

/**
 *
 * @author Rembao
 */
public class ArticuloBean implements Serializable{
    
    
    private int id;
    private String clave;
    private String clave_alterna;
    private int servicio;
    private String descripcion;
    private int categoria_id;
    private String categoria;
    private int departamento_id;
    private String departamento;
    private int unidad_compra_id;
    private String unidad_compra;
    private int unidad_venta_id;
    private String unidad_venta;
    private int factor;
    private int inventario_minimo;
    private int inventario_maximo;
    private String localizacion;
    private double precio_compra;
    private String s_precio_compra;
    private int neto;
    private double precio_unidad_compra;
    private String s_precio_unidad_compra;
    private double precio_unidad_venta;
    private String s_precio_unidad_venta;
    private double utilidad1;
    private double precio_venta1;
    private String s_precio_venta1;
    private double precio_venta_neto1;
    private String s_precio_venta_neto1;
    private double utilidad2;
    private double precio_venta2;
    private String s_precio_venta2;
    private double precio_venta_neto2;
    private String s_precio_venta_neto2;
    private double utilidad3;
    private double precio_venta3;
    private String s_precio_venta3;
    private double precio_venta_neto3;
    private String s_precio_venta_neto3;
    private double utilidad4;
    private double precio_venta4;
    private String s_precio_venta;
    private double precio_venta_neto4;
    private String s_precio_venta_neto4;
    private String serie;
    private String estatus;
    private int creado_por;
    private String fecha_creacion;
    private int modificado_por;
    private String fecha_modificacion;
    private int sucursal;
    private int impuesto;
    private double margen_utilidad1;
    private double margen_utilidad2;
    private double margen_utilidad3;
    private double margen_utilidad4;
    private int existencia;
    private String caracteristica;
    private String ip;
    private String host;
    
    
    public ArticuloBean(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClave_alterna() {
        return clave_alterna;
    }

    public void setClave_alterna(String clave_alterna) {
        this.clave_alterna = clave_alterna;
    }

    public int getServicio() {
        return servicio;
    }

    public void setServicio(int servicio) {
        this.servicio = servicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }

    public int getUnidad_compra_id() {
        return unidad_compra_id;
    }

    public void setUnidad_compra_id(int unidad_compra_id) {
        this.unidad_compra_id = unidad_compra_id;
    }

    public int getUnidad_venta_id() {
        return unidad_venta_id;
    }

    public void setUnidad_venta_id(int unidad_venta_id) {
        this.unidad_venta_id = unidad_venta_id;
    }

    public int getFactor() {
        return factor;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }

    public int getInventario_minimo() {
        return inventario_minimo;
    }

    public void setInventario_minimo(int inventario_minimo) {
        this.inventario_minimo = inventario_minimo;
    }

    public int getInventario_maximo() {
        return inventario_maximo;
    }

    public void setInventario_maximo(int inventario_maximo) {
        this.inventario_maximo = inventario_maximo;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public double getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(double precio_compra) {
        this.precio_compra = precio_compra;
    }

    public int getNeto() {
        return neto;
    }

    public void setNeto(int neto) {
        this.neto = neto;
    }

    public double getPrecio_unidad_compra() {
        return precio_unidad_compra;
    }

    public void setPrecio_unidad_compra(double precio_unidad_compra) {
        this.precio_unidad_compra = precio_unidad_compra;
    }

    public double getPrecio_unidad_venta() {
        return precio_unidad_venta;
    }

    public void setPrecio_unidad_venta(double precio_unidad_venta) {
        this.precio_unidad_venta = precio_unidad_venta;
    }

    public double getUtilidad1() {
        return utilidad1;
    }

    public void setUtilidad1(double utilidad1) {
        this.utilidad1 = utilidad1;
    }

    public double getPrecio_venta1() {
        return precio_venta1;
    }

    public void setPrecio_venta1(double precio_venta1) {
        this.precio_venta1 = precio_venta1;
    }

    public double getPrecio_venta_neto1() {
        return precio_venta_neto1;
    }

    public void setPrecio_venta_neto1(double precio_venta_neto1) {
        this.precio_venta_neto1 = precio_venta_neto1;
    }

    public double getUtilidad2() {
        return utilidad2;
    }

    public void setUtilidad2(double utilidad2) {
        this.utilidad2 = utilidad2;
    }

    public double getPrecio_venta2() {
        return precio_venta2;
    }

    public void setPrecio_venta2(double precio_venta2) {
        this.precio_venta2 = precio_venta2;
    }

    public double getPrecio_venta_neto2() {
        return precio_venta_neto2;
    }

    public void setPrecio_venta_neto2(double precio_venta_neto2) {
        this.precio_venta_neto2 = precio_venta_neto2;
    }

    public double getUtilidad3() {
        return utilidad3;
    }

    public void setUtilidad3(double utilidad3) {
        this.utilidad3 = utilidad3;
    }

    public double getPrecio_venta3() {
        return precio_venta3;
    }

    public void setPrecio_venta3(double precio_venta3) {
        this.precio_venta3 = precio_venta3;
    }

    public double getPrecio_venta_neto3() {
        return precio_venta_neto3;
    }

    public void setPrecio_venta_neto3(double precio_venta_neto3) {
        this.precio_venta_neto3 = precio_venta_neto3;
    }

    public double getUtilidad4() {
        return utilidad4;
    }

    public void setUtilidad4(double utilidad4) {
        this.utilidad4 = utilidad4;
    }

    public double getPrecio_venta4() {
        return precio_venta4;
    }

    public void setPrecio_venta4(double precio_venta4) {
        this.precio_venta4 = precio_venta4;
    }

    public double getPrecio_venta_neto4() {
        return precio_venta_neto4;
    }

    public void setPrecio_venta_neto4(double precio_venta_neto4) {
        this.precio_venta_neto4 = precio_venta_neto4;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getDepartamento_id() {
        return departamento_id;
    }

    public void setDepartamento_id(int departamento_id) {
        this.departamento_id = departamento_id;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getUnidad_compra() {
        return unidad_compra;
    }

    public void setUnidad_compra(String unidad_compra) {
        this.unidad_compra = unidad_compra;
    }

    public String getUnidad_venta() {
        return unidad_venta;
    }

    public void setUnidad_venta(String unidad_venta) {
        this.unidad_venta = unidad_venta;
    }

    public int getSucursal() {
        return sucursal;
    }

    public void setSucursal(int sucursal) {
        this.sucursal = sucursal;
    }

    public int getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(int impuesto) {
        this.impuesto = impuesto;
    }

    public double getMargen_utilidad1() {
        return margen_utilidad1;
    }

    public void setMargen_utilidad1(double margen_utilidad1) {
        this.margen_utilidad1 = margen_utilidad1;
    }

    public double getMargen_utilidad2() {
        return margen_utilidad2;
    }

    public void setMargen_utilidad2(double margen_utilidad2) {
        this.margen_utilidad2 = margen_utilidad2;
    }

    public double getMargen_utilidad3() {
        return margen_utilidad3;
    }

    public void setMargen_utilidad3(double margen_utilidad3) {
        this.margen_utilidad3 = margen_utilidad3;
    }

    public double getMargen_utilidad4() {
        return margen_utilidad4;
    }

    public void setMargen_utilidad4(double margen_utilidad4) {
        this.margen_utilidad4 = margen_utilidad4;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public String getS_precio_unidad_compra() {
        return s_precio_unidad_compra;
    }

    public void setS_precio_unidad_compra(String s_precio_unidad_compra) {
        this.s_precio_unidad_compra = s_precio_unidad_compra;
    }

    public String getS_precio_unidad_venta() {
        return s_precio_unidad_venta;
    }

    public void setS_precio_unidad_venta(String s_precio_unidad_venta) {
        this.s_precio_unidad_venta = s_precio_unidad_venta;
    }

    public String getS_precio_venta1() {
        return s_precio_venta1;
    }

    public void setS_precio_venta1(String s_precio_venta1) {
        this.s_precio_venta1 = s_precio_venta1;
    }

    public String getS_precio_venta_neto1() {
        return s_precio_venta_neto1;
    }

    public void setS_precio_venta_neto1(String s_precio_venta_neto1) {
        this.s_precio_venta_neto1 = s_precio_venta_neto1;
    }

    public String getS_precio_venta2() {
        return s_precio_venta2;
    }

    public void setS_precio_venta2(String s_precio_venta2) {
        this.s_precio_venta2 = s_precio_venta2;
    }

    public String getS_precio_venta_neto2() {
        return s_precio_venta_neto2;
    }

    public void setS_precio_venta_neto2(String s_precio_venta_neto2) {
        this.s_precio_venta_neto2 = s_precio_venta_neto2;
    }

    public String getS_precio_venta3() {
        return s_precio_venta3;
    }

    public void setS_precio_venta3(String s_precio_venta3) {
        this.s_precio_venta3 = s_precio_venta3;
    }

    public String getS_precio_venta_neto3() {
        return s_precio_venta_neto3;
    }

    public void setS_precio_venta_neto3(String s_precio_venta_neto3) {
        this.s_precio_venta_neto3 = s_precio_venta_neto3;
    }

    public String getS_precio_venta() {
        return s_precio_venta;
    }

    public void setS_precio_venta(String s_precio_venta) {
        this.s_precio_venta = s_precio_venta;
    }

    public String getS_precio_venta_neto4() {
        return s_precio_venta_neto4;
    }

    public void setS_precio_venta_neto4(String s_precio_venta_neto4) {
        this.s_precio_venta_neto4 = s_precio_venta_neto4;
    }

    public String getS_precio_compra() {
        return s_precio_compra;
    }

    public void setS_precio_compra(String s_precio_compra) {
        this.s_precio_compra = s_precio_compra;
    }

    public String getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
    
    
    
    
    
}
