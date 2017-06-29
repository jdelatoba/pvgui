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
public class AdmDepartamentoBean implements Serializable{
    
    private int departamento_id;
    private int direccion_id;
    private int dependencia_id;
    private String departamento;
    private AdmBitacoraBean bitacora;
    
    public AdmDepartamentoBean(){
    }

    public int getDepartamento_id() {
        return departamento_id;
    }

    public void setDepartamento_id(int departamento_id) {
        this.departamento_id = departamento_id;
    }

    public int getDireccion_id() {
        return direccion_id;
    }

    public void setDireccion_id(int direccion_id) {
        this.direccion_id = direccion_id;
    }

    public int getDependencia_id() {
        return dependencia_id;
    }

    public void setDependencia_id(int dependencia_id) {
        this.dependencia_id = dependencia_id;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public AdmBitacoraBean getBitacora() {
        return bitacora;
    }

    public void setBitacora(AdmBitacoraBean bitacora) {
        this.bitacora = bitacora;
    }
    
    
    
}
