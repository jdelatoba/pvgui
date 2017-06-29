/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.configuracion;

import app.choya.sys.pv.dao.EApps;
import app.choya.sys.pv.dao.EDriver;
import app.choya.sys.pv.dao.GenericDAO;
import app.choya.sys.pv.utils.SqlAppsException;
import app.choya.sys.pv.utils.UtilsDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Rembao
 */
public class Impuesto extends GenericDAO implements ImpuestoI{

    static final Logger LOGGER = Logger.getLogger(Impuesto.class.getName());
    
    @Override
    public int setImpuesto(ImpuestoBean ib) throws SqlAppsException {
        
        int resultado = 0;
        int secuencia = 0;
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "INSERT INTO cat_impuesto\n" +
        "(id,\n" +
        "descripcion,\n" +
        "impuesto,\n" +
        "activo,\n" +
        "impreso,\n" +
        "trasladado,\n" +
        "retenido,\n" +
        "orden,\n" +
        "creado_por,\n" +
        "modificado_por)\n" +
        "VALUES\n" +
        "(?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?)";
        
        
        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);
            
            UtilsDao ut = new UtilsDao();

            secuencia = ut.nextVal("s_conf_impuesto");
            ps.setInt(1, secuencia);
            ps.setString(2, ib.getDescripcion());
            ps.setDouble(3, ib.getImpuesto());
            ps.setInt(4, ib.getActivo());
            ps.setInt(5, ib.getImpreso());
            ps.setInt(6, ib.getTrasladado());
            ps.setInt(7, ib.getRetenido());
            ps.setInt(8, ib.getOrden());
            ps.setInt(9, ib.getCreado_por());
            ps.setInt(10, ib.getModificado_por());
            
            resultado = ps.executeUpdate();

            if (resultado != 0) {
                resultado = secuencia;
            }
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Impuesto.class.getName() + "-- metodo: setImpuesto", LOGGER);
            
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Impuesto.class.getName() + "-- metodo: setImpuesto", LOGGER);
            }
            closeConexion();
        }

        return resultado;
        
    }

    @Override
    public int updateImpuesto(ImpuestoBean ib) throws SqlAppsException {
        int resultado = 0;
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "UPDATE cat_impuesto\n" +
        "SET\n" +
        "descripcion = ?,\n" +
        "impuesto = ?,\n" +
        "activo = ?,\n" +
        "impreso = ?,\n" +
        "trasladado = ?,\n" +
        "retenido = ?,\n" +
        "orden = ?,\n" +
        "modificado_por = ?\n" +
        "WHERE id = ?";
        
        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setString(1, ib.getDescripcion());
            ps.setDouble(2, ib.getImpuesto());
            ps.setInt(3, ib.getActivo());
            ps.setInt(4, ib.getImpreso());
            ps.setInt(5, ib.getTrasladado());
            ps.setInt(6, ib.getRetenido());
            ps.setInt(7, ib.getOrden());
            ps.setInt(8, ib.getModificado_por());
            ps.setInt(9, ib.getId());
            
            resultado = ps.executeUpdate();
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Impuesto.class.getName() + "-- metodo: updateImpuesto", LOGGER);
            
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Impuesto.class.getName() + "-- metodo: updateImpuesto", LOGGER);
            }
            closeConexion();
        }

        return resultado;
    }

    @Override
    public ListIterator<ImpuestoBean> getListaImpuesto() throws SqlAppsException {
        
        LinkedList<ImpuestoBean> lista = new LinkedList<>();
        Statement stmt = null;
        ResultSet rs = null;
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "SELECT id,\n" +
        "descripcion,\n" +
        "impuesto,\n" +
        "activo,\n" +
        "impreso,\n" +
        "impuesto_local,\n" +
        "trasladado,\n" +
        "retenido,\n" +
        "orden,\n" +
        "creado_por,\n" +
        "date_format(fecha_creacion,'%d/%m/%Y %H:%i:%s') fecha_creacion,\n" +
        "modificado_por,\n" +
        "date_format(fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion,\n" +
        "serie,\n" +
        "estatus\n" +
        "FROM cat_impuesto";
        
        try {
            stmt = getStatement();
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                
                ImpuestoBean ib = new ImpuestoBean();
                ib.setId(rs.getInt("id"));
                ib.setDescripcion(rs.getString("descripcion"));
                ib.setImpuesto(rs.getDouble("impuesto"));
                ib.setActivo(rs.getInt("activo"));
                ib.setImpreso(rs.getInt("impreso"));
                ib.setTrasladado(rs.getInt("trasladado"));
                ib.setRetenido(rs.getInt("retenido"));
                ib.setOrden(rs.getInt("orden"));
                ib.setCreado_por(rs.getInt("creado_por"));
                ib.setFecha_creacion(rs.getString("fecha_creacion"));
                ib.setModificado_por(rs.getInt("modificado_por"));
                ib.setFecha_modificacion(rs.getString("fecha_modificacion"));
                ib.setSerie(rs.getString("serie"));
                ib.setEstatus(rs.getString("estatus"));
                
                lista.add(ib);
            
            }
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Impuesto.class.getName() + "-- metodo: getListaImpuesto", LOGGER);
            
        } finally {
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Impuesto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Impuesto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
            closeConexion();
        }
        
        
        
        
        
        return lista.listIterator();
        
    }

    @Override
    public ListIterator<ImpuestoBean> getListaImpuesto(int id) throws SqlAppsException {
        
        LinkedList<ImpuestoBean> lista = new LinkedList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "SELECT id,\n" +
        "descripcion,\n" +
        "impuesto,\n" +
        "activo,\n" +
        "impreso,\n" +
        "impuesto_local,\n" +
        "trasladado,\n" +
        "retenido,\n" +
        "orden,\n" +
        "creado_por,\n" +
        "date_format(fecha_creacion,'%d/%m/%Y %H:%i:%s') fecha_creacion,\n" +
        "modificado_por,\n" +
        "date_format(fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion,\n" +
        "serie,\n" +
        "estatus\n" +
        "FROM cat_impuesto "
                + "WHERE id = ? ORDER BY descripcion";
        
        try {
            ps = getPrepareStatement(sql);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                ImpuestoBean ib = new ImpuestoBean();
                ib.setId(rs.getInt("id"));
                ib.setDescripcion(rs.getString("descripcion"));
                ib.setImpuesto(rs.getDouble("impuesto"));
                ib.setActivo(rs.getInt("activo"));
                ib.setImpreso(rs.getInt("impreso"));
                ib.setTrasladado(rs.getInt("trasladado"));
                ib.setRetenido(rs.getInt("retenido"));
                ib.setOrden(rs.getInt("orden"));
                ib.setCreado_por(rs.getInt("creado_por"));
                ib.setFecha_creacion(rs.getString("fecha_creacion"));
                ib.setModificado_por(rs.getInt("modificado_por"));
                ib.setFecha_modificacion(rs.getString("fecha_modificacion"));
                ib.setSerie(rs.getString("serie"));
                ib.setEstatus(rs.getString("estatus"));
                
                lista.add(ib);
            
            }
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Impuesto.class.getName() + "-- metodo: getListaImpuesto", LOGGER);
            
        } finally {
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Impuesto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Impuesto.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            closeConexion();
        }
        
        
        
        
        
        return lista.listIterator();
    }

    @Override
    public JSONObject getImpuestoJSON(int id) throws SqlAppsException {
        
        JSONObject impuestos = new JSONObject();
        JSONArray data = new JSONArray();
        int contador = 0;
        
        Statement stmt = null;
        ResultSet rs = null;
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "SELECT id,\n" +
        "descripcion,\n" +
        "impuesto,\n" +
        "activo,\n" +
        "impreso,\n" +
        "impuesto_local,\n" +
        "trasladado,\n" +
        "retenido,\n" +
        "orden,\n" +
        "creado_por,\n" +
        "date_format(fecha_creacion,'%d/%m/%Y %H:%i:%s') fecha_creacion,\n" +
        "modificado_por,\n" +
        "date_format(fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion,\n" +
        "serie,\n" +
        "estatus\n" +
        "FROM cat_impuesto\n" +
        "WHERE estatus = 'A' ORDER BY orden, descripcion";
        
        try {
            stmt = getStatement();
            
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                
                JSONObject impuestoJSON = new JSONObject();
                
                
                impuestoJSON.put("id", rs.getInt("id"));
                impuestoJSON.put("descripcion", rs.getString("descripcion"));
                impuestoJSON.put("impuesto", rs.getDouble("impuesto"));
                
                impuestoJSON.put("activo", rs.getInt("activo"));
                impuestoJSON.put("impreso", rs.getInt("impreso"));
                impuestoJSON.put("trasladado", rs.getInt("trasladado"));
                impuestoJSON.put("retenido", rs.getInt("retenido"));
                impuestoJSON.put("orden", rs.getInt("orden"));
                impuestoJSON.put("creado_por", rs.getInt("creado_por"));
                impuestoJSON.put("fecha_creacion", rs.getString("fecha_creacion"));
                impuestoJSON.put("modificado_por", rs.getInt("modificado_por"));
                impuestoJSON.put("fecha_modificacion", rs.getString("fecha_modificacion"));
                impuestoJSON.put("serie", rs.getString("serie"));
                impuestoJSON.put("estatus", rs.getString("estatus"));
                
                data.add(impuestoJSON);
                
                contador++;
            
            }
            
            impuestos.put("data", data);
            
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Impuesto.class.getName() + "-- metodo: getListaImpuesto", LOGGER);
            
        } finally {
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Impuesto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
            try {
                if(stmt != null){
                
                    stmt.close();
                }
                    
                
            } catch (SQLException ex) {
                Logger.getLogger(Impuesto.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            closeConexion();
        }
        
        
        
        
        
        return impuestos;
    }

    @Override
    public int deleteImpuesto(int id) throws SqlAppsException {
        int resultado = 0;
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "UPDATE cat_impuesto set estatus = 'D' WHERE id = ?";
        
        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setInt(1, id);
                    
            resultado = ps.executeUpdate();
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Impuesto.class.getName() + "-- metodo: deleteImpuesto", LOGGER);
        } finally{
        
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Impuesto.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
        }
        
        
        
        
        return resultado;
    }
    
    
    public double getValorImpuesto(int id) throws SqlAppsException{
    
        double impuesto = 0.0d;
        ResultSet rs = null;
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "SELECT impuesto/100 impuesto "
                + " FROM cat_impuesto "
                + " WHERE id = ?";
        
        try {
            
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                impuesto = rs.getDouble("impuesto");
            }
            
            
        } catch (SQLException ex) {
            System.out.println("el metodo ha lanzado el siguiente error "+ex.getMessage());
            throw new SqlAppsException(ex);
        }finally{
        
            try {
                if(rs != null) 
                    rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Impuesto.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Impuesto.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
        }
        
        return impuesto;
    }
    
    
    
    
}
