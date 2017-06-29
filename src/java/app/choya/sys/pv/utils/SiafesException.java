/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app.choya.sys.pv.utils;

import java.sql.SQLException;

/**
 *
 * @author developer
 */
public class SiafesException extends Exception{
    
    
    private int codigo_error;
    private String mensaje;
    private String sql;
    private String estado;
    
    public SiafesException(String message){
        super(message);
    }

    public SiafesException(Exception e){
       if(e instanceof SQLException){
          SQLException sqle =  (SQLException) e;
           setCodigo_error(sqle.getErrorCode());
           setMensaje(sqle.getMessage());
           setEstado(sqle.getSQLState());
           setCodigo_error(sqle.getErrorCode());
       }else{
           setMensaje(e.getMessage());
       }
    }
    
    public int getCodigo_error() {
        return codigo_error;
    }

    public void setCodigo_error(int codigo_error) {
        this.codigo_error = codigo_error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
