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
public class AdmPerfilBean implements Serializable, Auditable {

    private int perfil_id;
    private int aplicacion_id;
    private String nombre;
    private String descripcion;
    private String sql_validacion;
    private String col_valor;
    private String col_valor_visible;
    private String col_desc; 
    private String col_desc_visible;
    private String a_nivel_sitio;
    private String a_nivel_responsabilidad;
    private String a_nivel_usuario;
    private String fecha_inicio;
    private String fecha_termino;
    private int creado_por;
    private String fecha_creacion;
    private int modificado_por;
    private String fecha_modificacion;

    public AdmPerfilBean(){
    }
            
    public int getPerfil_id() {
        return perfil_id;
    }

    public void setPerfil_id(int perfil_id) {
        this.perfil_id = perfil_id;
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

    public String getSql_validacion() {
        if (sql_validacion != null) {
            return sql_validacion;
        } else {
            return "";
        }
    }

    public void setSql_validacion(String sql_validacion) {
        this.sql_validacion = sql_validacion;
    }

    public String getCol_valor() {
        if (col_valor != null) {
            return col_valor;
        } else {
            return "";
        }

    }

    public void setCol_valor(String col_valor) {
        this.col_valor = col_valor;
    }

    public String getCol_valor_visible() {
        if (col_valor_visible != null) {
            return col_valor_visible;
        } else {
            return "";
        }
    }

    public void setCol_valor_visible(String col_valor_visible) {
        this.col_valor_visible = col_valor_visible;
    }

    public String getCol_desc() {
        if (col_desc != null) {
            return col_desc;
        } else {
            return "";
        }

    }

    public void setCol_desc(String col_desc) {
        this.col_desc = col_desc;
    }

    public String getCol_desc_visible() {
        if (col_desc_visible != null) {
            return col_desc_visible;
        } else {
            return "";
        }
    }

    public void setCol_desc_visible(String col_desc_visible) {
        this.col_desc_visible = col_desc_visible;
    }

    public String getA_nivel_sitio() {
        if (a_nivel_sitio != null) {
            return a_nivel_sitio;
        } else {
            return "";
        }
    }

    public void setA_nivel_sitio(String a_nivel_sitio) {
        this.a_nivel_sitio = a_nivel_sitio;
    }

    public String getA_nivel_responsabilidad() {
        if (a_nivel_responsabilidad != null) {
            return a_nivel_responsabilidad;
        } else {
            return "";
        }
    }

    public void setA_nivel_responsabilidad(String a_nivel_responsabilidad) {
        this.a_nivel_responsabilidad = a_nivel_responsabilidad;
    }

    public String getA_nivel_usuario() {
        if (a_nivel_usuario != null) {
            return a_nivel_usuario;
        } else {
            return "";
        }
    }

    public void setA_nivel_usuario(String a_nivel_usuario) {
        this.a_nivel_usuario = a_nivel_usuario;
    }

    public String getFecha_inicio() {
        if (fecha_inicio != null) {
            return fecha_inicio;
        } else {
            return "";
        }
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_termino() {
        if (fecha_termino != null) {
            return fecha_termino;
        } else {
            return "";
        }
    }

    public void setFecha_termino(String fecha_termino) {
        this.fecha_termino = fecha_termino;
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
