/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.catalogo;

import app.choya.sys.pv.bitacora.BitacoraArticulo;
import app.choya.sys.pv.bitacora.BitacoraArticuloBean;
import app.choya.sys.pv.dao.EApps;
import app.choya.sys.pv.dao.EDriver;
import app.choya.sys.pv.dao.GenericDAO;
import app.choya.sys.pv.utils.SqlAppsException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Rembao
 */
public class InvArticulo extends GenericDAO{
    
    private static final Logger LOGGER = Logger.getLogger(InvArticulo.class.getName());
    
    public int setInvArticulo(InvArticuloBean ab, Connection cnn) throws SqlAppsException{
    
        int resultado = 0;
        
        PreparedStatement ps = null;
        String sql = "INSERT INTO inv_articulo\n" +
        "(id,\n" +
        "articulo_id,\n" +
        "existencia,\n" +
        
        "creado_por,\n" +
        "modificado_por)\n" +
        "VALUES\n" +
        "(null,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?)";
        
        try {
            ps = cnn.prepareStatement(sql);
            
            ps.setInt(1, ab.getArticulo_id());
            ps.setDouble(2, ab.getExistencia());
            ps.setInt(3, ab.getCreado_por());
            ps.setInt(4, ab.getModificado_por());
            
            resultado = ps.executeUpdate();
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, InvArticulo.class.getName() + " El metodo setInvArticulo lanzado el siguiente error", LOGGER);
        }finally{
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InvArticulo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        
        
        return resultado;
    }
    
    
    public int deleteInvArticulo(InvArticuloBean ib, Connection cnn) throws SqlAppsException {
    
        int resultado = 0;
        
        PreparedStatement ps = null;
        
        
        String sql = "UPDATE inv_articulo set estatus = 'D', modificado_por = ? WHERE articulo_id = ?";
        
        try {
            ps = cnn.prepareStatement(sql);
            
            ps.setInt(1, ib.getModificado_por());
            ps.setInt(2, ib.getArticulo_id());
            
            resultado = ps.executeUpdate();
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, InvArticulo.class.getName() + " El metodo deleteInvArticulo lanzado el siguiente error", LOGGER);
        }finally{
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InvArticulo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        
        }
        
        
        return resultado;
    }
    
    
    public int ajustarExistencia(InvArticuloBean ib, String ip, String host) throws SqlAppsException{
    
        int resultado = 0;
        
        Connection cnn = getConexionSinCommit(EDriver.MYSQL, EApps.PV);
        
        JSONObject estado = getEstadoInvArticulo(ib.getArticulo_id(), cnn);
        PreparedStatement ps = null;
        String sql = "update inv_articulo \n" +
        "set existencia = ?,\n" +
        "modificado_por = ?\n" +
        "where articulo_id = ?";
        
        try {
            ps = getPrepareStatement(sql);
            ps.setDouble(1, ib.getExistencia());
            ps.setInt(2, ib.getModificado_por());
            ps.setInt(3, ib.getArticulo_id());
            
            resultado = ps.executeUpdate();
            
            BitacoraArticuloBean bb = new BitacoraArticuloBean();
            bb.setAccion(1);
            bb.setEstado(estado.toJSONString());
            bb.setUsuario_id(ib.getCreado_por());
            bb.setIp(ip);
            bb.setHost(host);
            bb.setSql_text(ps.toString());
            bb.setAntes(ib.getAntes());
            bb.setDespues(ib.getExistencia());
            bb.setArticulo_id(ib.getArticulo_id());
            bb.setComentario(ib.getComentario());
            //bb.setAntes(resultado);
            
            new BitacoraArticulo().setBitacoraAjusteInventario(bb,cnn);
            
            commit();
        } catch (SQLException ex) {
            rollback();
            Logger.getLogger(InvArticulo.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InvArticulo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        
        return resultado;
    }
    
    public JSONObject getEstadoInvArticulo(int articulo_id, Connection cnn){
        
        JSONObject json = new JSONObject();
        JSONArray articulos = new JSONArray();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        
        String sql = "SELECT a.id,\n" +
        "a.articulo_id,\n" +
        "a.existencia,\n" +
        "a.estatus,\n" +
        "a.serie,\n" +
        "a.creado_por,\n" +
        "date_format(a.fecha_creacion,'%d/%m/%Y %H:%i:%s') fecha_creacion,\n" +
        "a.modificado_por,\n" +
        "date_format(a.fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion,\n" +
        "b.clave,\n" +
        "b.clave_alterna,\n" +
        "b.descripcion\n" +
        "FROM inv_articulo a\n" +
        "LEFT JOIN cat_articulo b\n" +
        "ON a.articulo_id = b.id\n" +
        "where a.articulo_id = ?";
        
        try {
            ps = cnn.prepareStatement(sql);
            
            ps.setInt(1, articulo_id);

            rs = ps.executeQuery();

            while(rs.next()){
                
                
                JSONObject articuloJSON = new JSONObject();
                
                articuloJSON.put("id", rs.getInt("id"));
                articuloJSON.put("articulo_id", rs.getInt("articulo_id"));
                articuloJSON.put("existencia", rs.getDouble("existencia"));
                articuloJSON.put("estatus", rs.getString("estatus"));
                articuloJSON.put("serie", rs.getString("serie"));
                articuloJSON.put("creado_por", rs.getInt("creado_por"));
                articuloJSON.put("fecha_creacion", rs.getString("fecha_creacion"));
                articuloJSON.put("modificado_por", rs.getInt("modificado_por"));
                articuloJSON.put("fecha_modificacion", rs.getString("fecha_modificacion"));
                articuloJSON.put("clave", rs.getString("clave"));
                articuloJSON.put("clave_alterna", rs.getString("clave_alterna"));
                articuloJSON.put("descripcion", rs.getString("descripcion"));
                
                articulos.add(articuloJSON);
                


            }
            
            json.put("articulos", articulos);
            
        } catch (SQLException ex) {
            Logger.getLogger(InvArticulo.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(rs != null){
            
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InvArticulo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InvArticulo.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }
        }

        
        
        return json;
    }
    
}
