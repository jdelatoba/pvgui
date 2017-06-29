/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.administracion;

import java.io.Serializable;

/**
 *
 * @author Rembao
 */
public class RolAgendaBean implements Serializable{
    
    private int dueno_id;
    private String nombre_dueno;
    
    
    public RolAgendaBean(){}

    public int getDueno_id() {
        return dueno_id;
    }

    public void setDueno_id(int dueno_id) {
        this.dueno_id = dueno_id;
    }

    public String getNombre_dueno() {
        return nombre_dueno;
    }

    public void setNombre_dueno(String nombre_dueno) {
        this.nombre_dueno = nombre_dueno;
    }
    
    
    
}
