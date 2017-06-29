/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.bitacora;

import app.choya.sys.pv.catalogo.InvArticuloBean;
import app.choya.sys.pv.dao.GenericDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rembao
 */
public class BitacoraArticulo extends GenericDAO{

    public BitacoraArticulo() {
    }
    
    
    public int setBitacoraAjusteInventario(BitacoraArticuloBean bb, Connection cnn){
        int resultado = 0;
        
        PreparedStatement ps = null;
        
        
        String sql = "INSERT INTO audi_bitacora_articulo\n" +
        "(id,\n" +
        "usuario_id,\n" +
        "ip,\n" +
        "host,\n" +
        "accion,\n" +
        "sql_text,\n" +
        "estado, antes, despues,articulo_id, comentario)\n" +
        "VALUES\n" +
        "(null,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,?,?,?, ?)";
        
        try {
            ps = cnn.prepareStatement(sql);
            ps.setInt(1, bb.getUsuario_id());
            ps.setString(2, bb.getIp());
            ps.setString(3, bb.getHost());
            ps.setInt(4, bb.getAccion());
            ps.setString(5, bb.getSql_text());
            ps.setString(6, bb.getEstado());
            ps.setDouble(7, bb.getAntes());
            ps.setDouble(8, bb.getDespues());
            ps.setInt(9, bb.getArticulo_id());
            ps.setString(10, bb.getComentario());
            
            //System.out.println("ps "+ps);
            resultado = ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(BitacoraArticulo.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BitacoraArticulo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        }
        
        return resultado;
    }
    
    
    
    public int setBitacoraEliminarArticulo(BitacoraArticuloBean bb, Connection cnn){
        int resultado = 0;
        
        PreparedStatement ps = null;
        
        
        String sql = "INSERT INTO audi_bitacora_articulo\n" +
        "(id,\n" +
        "usuario_id,\n" +
        "ip,\n" +
        "host,\n" +
        "accion,\n" +
        "sql_text,\n" +
        "estado, antes, despues,articulo_id, comentario)\n" +
        "VALUES\n" +
        "(null,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,?,?,?, ?)";
        
        try {
            ps = cnn.prepareStatement(sql);
            ps.setInt(1, bb.getUsuario_id());
            ps.setString(2, bb.getIp());
            ps.setString(3, bb.getHost());
            ps.setInt(4, bb.getAccion());
            ps.setString(5, bb.getSql_text());
            ps.setString(6, bb.getEstado());
            ps.setDouble(7, bb.getAntes());
            ps.setDouble(8, bb.getDespues());
            ps.setInt(9, bb.getArticulo_id());
            ps.setString(10, bb.getComentario());
            
            //System.out.println("ps "+ps);
            resultado = ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(BitacoraArticulo.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BitacoraArticulo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        }
        
        return resultado;
    }
    
    
    
    public int setBitacoraEditarArticulo(BitacoraArticuloBean bb, Connection cnn){
        int resultado = 0;
        
        PreparedStatement ps = null;
        
        
        String sql = "INSERT INTO audi_bitacora_articulo\n" +
        "(id,\n" +
        "usuario_id,\n" +
        "ip,\n" +
        "host,\n" +
        "accion,\n" +
        "sql_text,\n" +
        "estado, antes, despues,articulo_id, comentario)\n" +
        "VALUES\n" +
        "(null,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,?,?,?, ?)";
        
        try {
            ps = cnn.prepareStatement(sql);
            ps.setInt(1, bb.getUsuario_id());
            ps.setString(2, bb.getIp());
            ps.setString(3, bb.getHost());
            ps.setInt(4, bb.getAccion());
            ps.setString(5, bb.getSql_text());
            ps.setString(6, bb.getEstado());
            ps.setDouble(7, bb.getAntes());
            ps.setDouble(8, bb.getDespues());
            ps.setInt(9, bb.getArticulo_id());
            ps.setString(10, bb.getComentario());
            
            //System.out.println("ps "+ps);
            resultado = ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(BitacoraArticulo.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BitacoraArticulo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        }
        
        return resultado;
    }
    
}
