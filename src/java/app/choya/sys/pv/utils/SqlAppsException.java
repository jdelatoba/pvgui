/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.utils;

import app.choya.sys.pv.catalogo.Articulo;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlos.rembao
 */
public final class SqlAppsException extends SQLException {

    private int codigo_error;
    private String mensaje;
    private String sql;
    private String estado;

    public SqlAppsException() {
        super();
        setCodigo_error(getErrorCode());
        setMensaje(getMessage());
        setEstado(getSQLState());
        setCodigo_error(getErrorCode());
    }

    public SqlAppsException(String message) {
        super(message);
        setCodigo_error(getErrorCode());
        setMensaje(getMessage());
        setEstado(getSQLState());
        setCodigo_error(getErrorCode());
        
    }
    
    public SqlAppsException(SQLException ex) {
        super(ex.getMessage());
        setCodigo_error(ex.getErrorCode());
        setMensaje(ex.getMessage());
        setEstado(ex.getSQLState());
        setCodigo_error(ex.getErrorCode());
        
    }
    
    
    public SqlAppsException(SQLException ex, String source, Logger logger) {
        super(ex.getMessage());
        setCodigo_error(ex.getErrorCode());
        setMensaje(ex.getMessage());
        setEstado(ex.getSQLState());
        setCodigo_error(ex.getErrorCode());
        logger.log(Level.WARNING, source, ex);
        
    }
    
    public SqlAppsException(SQLException ex, Object ob) {
        super(ex.getMessage());
        setCodigo_error(ex.getErrorCode());
        setMensaje(ex.getMessage());
        setEstado(ex.getSQLState());
        setCodigo_error(ex.getErrorCode());
        
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
