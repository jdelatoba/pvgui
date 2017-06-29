/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.admon;

import java.io.Serializable;
/**
 *
 * @author Informatica
 */
public class AdmUsuarioDetalleBean extends AdmUsuariosBean implements Serializable{
    
    private String dependencia;
    private String direccion;
    private String departamento;

    public AdmUsuarioDetalleBean(){
    
    }
    
    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    
    
}
