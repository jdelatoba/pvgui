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
 * @version 
 * 16-feb-15 Bean de la tabla de ADM_ACCESOS 
 */
public class AdmAccesoBean implements Auditable, Serializable {
private int acceso_id;
private int usuario_id;
private String entrada;
private String salida;
private String osuser;
private int sid ;  
private int serial;
private int pid;
private String spid;
private String maquina;
private String sesion;
private String ias;

    public AdmAccesoBean(){
    }
    
    public int getAcceso_id() {
        return acceso_id;
    }

    public void setAcceso_id(int acceso_id) {
        this.acceso_id = acceso_id;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
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

    public String getOsuser() {
        if(osuser != null)
            return osuser;
        else
            return "";
        
    }

    public void setOsuser(String osuser) {
        this.osuser = osuser;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getSpid() {
         if(spid != null)
        return spid;
        else
            return "";
    }

    public void setSpid(String spid) {
        this.spid = spid;
    }

    public String getMaquina() {
        return maquina;
    }

    public void setMaquina(String maquina) {
        this.maquina = maquina;
    }

    public String getSesion() {
        return sesion;
    }

    public void setSesion(String sesion) {
        this.sesion = sesion;
    }

    public String getIas() {
        return ias;
    }

    public void setIas(String ias) {
        this.ias = ias;
    }


    @Override
    public int getCreado_por() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCreado_por(int creado_por) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getFecha_creacion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setFecha_creacion(String fecha_creacion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getModificado_por() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setModificado_por(int modificado_por) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getFecha_modificacion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setFecha_modificacion(String fecha_modificacion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
