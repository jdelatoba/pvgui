/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.admon;
import java.io.Serializable;
import app.choya.sys.pv.admon.Auditable;
/**
 *
 * @author Icosio
 *Bean AdmAccesoFuncion para la tabla ADM_ACCESOS_FUNIONES
 * se le agregaron los campos de auditoria.
 */
public class AdmAccesoFuncionBean implements Serializable, Auditable{
private int acceso_id;// pk id consecutivo   ped. ver si es secuencia       NOT NULL NUMBER 
private int responsabilidad_id;// fk con la id de la tabla adm_responsabilidades  NOT NULL NUMBER 
private int funcion_id; //fk con el id de la tabla adm_funciones         NOT NULL NUMBER 
private String entrada;// fecha de entrada a la pantalla en la uqe se trabajara            NOT NULL DATE   
private String salida;// fecha de salida de la pantalla en la que se esta trabajando                      DATE   
private int creado_por;
private String fecha_creacion;
private int modificado_por;
private String fecha_modificacion;

    public int getAcceso_id() {
        return acceso_id;
    }

    public void setAcceso_id(int acceso_id) {
        this.acceso_id = acceso_id;
    }

    public int getResponsabilidad_id() {
        return responsabilidad_id;
    }

    public void setResponsabilidad_id(int responsabilidad_id) {
        this.responsabilidad_id = responsabilidad_id;
    }

    public int getFuncion_id() {
        return funcion_id;
    }

    public void setFuncion_id(int funcion_id) {
        this.funcion_id = funcion_id;
    }

    public String getEntrada() {
         
        if(entrada != null)
            return entrada;
        else
            return "";
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getSalida() {
         
        if(salida != null)
            return salida;
        else
            return "";
    }

    public void setSalida(String salida) {
        this.salida = salida;
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
        return fecha_creacion;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getModificado_por() {
        return modificado_por;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setModificado_por(int modificado_por) {
        this.modificado_por = modificado_por;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getFecha_modificacion() {
        return fecha_modificacion;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setFecha_modificacion(String fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
