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
public class AdmPerfilValorBean implements Serializable, Auditable{
private int perfil_id;       
private int nivel_id;
private int valor_nivel;
private String valor_perfil;
private int creado_por ;
private String fecha_creacion;
private int modificado_por;
private String fecha_modificacion;

    public AdmPerfilValorBean(){
        
    }

    public int getPerfil_id() {
        return perfil_id;
    }

    public void setPerfil_id(int perfil_id) {
        this.perfil_id = perfil_id;
    }

    public int getNivel_id() {
        return nivel_id;
    }

    public void setNivel_id(int nivel_id) {
        this.nivel_id = nivel_id;
    }

    public int getValor_nivel() {
        return valor_nivel;
    }

    public void setValor_nivel(int valor_nivel) {
        this.valor_nivel = valor_nivel;
    }

    public String getValor_perfil() {
         if (valor_perfil != null) {
            return valor_perfil;
        } else {
            return "";
        }         
    
    }

    public void setValor_perfil(String valor_perfil) {
        this.valor_perfil = valor_perfil;
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
