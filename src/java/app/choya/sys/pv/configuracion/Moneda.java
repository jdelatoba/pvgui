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
import java.sql.Connection;
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
public class Moneda extends GenericDAO implements MonedaI {

    static final Logger LOGGER = Logger.getLogger(Moneda.class.getName());
    
    
    @Override
    public int setMoneda(MonedaBean mb) throws SqlAppsException {

        int resultado = 0;
        int secuencia = 0;
        Connection cnn = getConexionSinCommit(EDriver.MYSQL, EApps.PV);

        String sql = "INSERT INTO cat_moneda\n"
                + "(id,\n"
                + "descripcion,\n"
                + "tipo_cambio,\n"
                + "nacional,\n"
                + "creado_por,\n"
                + "modificado_por,\n"
                + "serie)\n"
                + "VALUES\n"
                + "(?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?)";

        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);
            secuencia = new UtilsDao().nextVal("s_cat_moneda",cnn);

            ps.setInt(1, secuencia);
            ps.setString(2, mb.getDescripcion());
            ps.setDouble(3, mb.getTipo_cambio());
            ps.setInt(4, mb.getNacional());
            ps.setInt(5, mb.getCreado_por());
            ps.setInt(6, mb.getModificado_por());
            ps.setString(7, mb.getSerie());

            resultado = ps.executeUpdate();
            commit();
            if (resultado != 0) {
                resultado = secuencia;
            }

        } catch (SQLException ex) {
            rollback();
            
            throw new SqlAppsException(ex, Moneda.class.getName() + "-- metodo: setMoneda", LOGGER);
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Moneda.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion();
        }

        return resultado;
        
    }

    @Override
    public int updateMoneda(MonedaBean mb) throws SqlAppsException {

        int resultado = 0;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "UPDATE cat_moneda\n"
                + "SET\n"
                + "descripcion = ?,\n"
                + "tipo_cambio = ?,\n"
                + "nacional = ?,\n"
                + "modificado_por = ?\n"
                + "WHERE id = ?";

        try {

            PreparedStatement ps = getPrepareStatement(sql);

            ps.setString(1, mb.getDescripcion());
            ps.setDouble(2, mb.getTipo_cambio());
            ps.setInt(3, mb.getNacional());
            ps.setInt(4, mb.getModificado_por());
            ps.setInt(5, mb.getId());

            resultado = ps.executeUpdate();

        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Moneda.class.getName() + "-- metodo: updateMoneda", LOGGER);
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Moneda.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion();
        }
        return resultado;

        
    }

    @Override
    public int deleteMoneda(int id) throws SqlAppsException {

        int resultado = 0;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "UPDATE cat_moneda set estatus = 'D' WHERE id = ?";

        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);

            ps.setInt(1, id);

            resultado = ps.executeUpdate();

        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Moneda.class.getName() + "-- metodo: deleteMoneda", LOGGER);
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Moneda.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion();

        }

        return resultado;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<MonedaBean> getMonedaLista() throws SqlAppsException {

        LinkedList<MonedaBean> lista = new LinkedList<>();

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "SELECT id,\n"
                + "descripcion,\n"
                + "tipo_cambio,\n"
                + "nacional,\n"
                + "creado_por,\n"
                + "date_format(fecha_creacion,'%d/%m/%Y') fecha_creacion,\n"
                + "modificado_por,\n"
                + "date_format(fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion,\n"
                + "serie,\n"
                + "estatus\n"
                + "FROM cat_moneda WHERE estatus = 'A' order by descripcion";
        ResultSet rs = null;
        Statement stmt = null;
        try {
            stmt = getStatement();
            
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                MonedaBean mb = new MonedaBean();
                
                mb.setId(rs.getInt("id"));
                mb.setDescripcion(rs.getString("descripcion"));
                mb.setTipo_cambio(rs.getDouble("tipo_cambio"));
                mb.setNacional(rs.getInt("nacional"));
                mb.setCreado_por(rs.getInt("creado_por"));
                mb.setModificado_por(rs.getInt("modificado_por"));
                mb.setFecha_creacion(rs.getString("fecha_creacion"));
                mb.setFecha_modificacion(rs.getString("fecha_modificacion"));
                mb.setSerie(rs.getString("serie"));
                mb.setEstatus(rs.getString("estatus"));
                
                lista.add(mb);
                
            }
        } catch (SQLException ex) {
            
            throw new SqlAppsException(ex, Moneda.class.getName() + "-- metodo: getMonedaLista", LOGGER);
        }finally{
            try {
                if(rs != null)
                    rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Moneda.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                if(stmt != null)
                    stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(Moneda.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
        }

        return lista.listIterator();
        
    }

    @Override
    public MonedaBean getMoneda(int id) throws SqlAppsException {
        
        MonedaBean mb = new MonedaBean();
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "SELECT id,\n"
                + "descripcion,\n"
                + "tipo_cambio,\n"
                + "nacional,\n"
                + "creado_por,\n"
                + "date_format(fecha_creacion,'%d/%m/%Y') fecha_creacion,\n"
                + "modificado_por,\n"
                + "date_format(fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion,\n"
                + "serie,\n"
                + "estatus\n"
                + "FROM cat_moneda "
                + "WHERE id = ? AND estatus = 'A' order by descripcion";
        ResultSet rs = null;
        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                
                mb.setId(rs.getInt("id"));
                mb.setDescripcion(rs.getString("descripcion"));
                mb.setTipo_cambio(rs.getDouble("tipo_cambio"));
                mb.setNacional(rs.getInt("nacional"));
                mb.setCreado_por(rs.getInt("creado_por"));
                mb.setModificado_por(rs.getInt("modificado_por"));
                mb.setFecha_creacion(rs.getString("fecha_creacion"));
                mb.setFecha_modificacion(rs.getString("fecha_modificacion"));
                mb.setSerie(rs.getString("serie"));
                mb.setEstatus(rs.getString("estatus"));
                
                
                
            }
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Moneda.class.getName() + "-- metodo: getMoneda", LOGGER);
        }finally{
            
            try {
                if(rs != null)
                    rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Moneda.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Moneda.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
        }
        
        
        
        return mb;
        
    }

    

    @Override
    public ListIterator<MonedaBean> getMonedaLista(int id) throws SqlAppsException {
        LinkedList<MonedaBean> lista = new LinkedList<>();

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "SELECT id,\n"
                + "descripcion,\n"
                + "tipo_cambio,\n"
                + "nacional,\n"
                + "creado_por,\n"
                + "date_format(fecha_creacion,'%d/%m/%Y') fecha_creacion,\n"
                + "modificado_por,\n"
                + "date_format(fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion,\n"
                + "serie,\n"
                + "estatus\n"
                + "FROM cat_moneda WHERE id = ? AND estatus = 'A' order by descripcion";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = getPrepareStatement(sql);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                MonedaBean mb = new MonedaBean();
                
                mb.setId(rs.getInt("id"));
                mb.setDescripcion(rs.getString("descripcion"));
                mb.setTipo_cambio(rs.getDouble("tipo_cambio"));
                mb.setNacional(rs.getInt("nacional"));
                mb.setCreado_por(rs.getInt("creado_por"));
                mb.setModificado_por(rs.getInt("modificado_por"));
                mb.setFecha_creacion(rs.getString("fecha_creacion"));
                mb.setFecha_modificacion(rs.getString("fecha_modificacion"));
                mb.setSerie(rs.getString("serie"));
                mb.setEstatus(rs.getString("estatus"));
                
                lista.add(mb);
                
            }
        } catch (SQLException ex) {
            
            throw new SqlAppsException(ex, Moneda.class.getName() + "-- metodo: getMonedaLista", LOGGER);
        }finally{
            try {
                if(rs != null)
                    rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Moneda.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Moneda.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
        }

        return lista.listIterator();
    }

    @Override
    public JSONObject getMonedaJSON(int draw) throws SqlAppsException {
        
        ResultSet rs = null;
        int contador = 0;
        JSONObject monedas = new JSONObject();
        JSONArray data = new JSONArray();
        Statement stmt = null;
        
        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "SELECT id,\n"
                + "descripcion,\n"
                + "tipo_cambio,\n"
                + "nacional,\n"
                + "creado_por,\n"
                + "date_format(fecha_creacion,'%d/%m/%Y') fecha_creacion,\n"
                + "modificado_por,\n"
                + "date_format(fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion,\n"
                + "serie,\n"
                + "estatus\n"
                + "FROM cat_moneda WHERE estatus = 'A' order by descripcion";
        
        try {
            stmt = getStatement();
            
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                
                JSONObject monedaJSON = new JSONObject();
                
                monedaJSON.put("id", rs.getInt("id"));
                monedaJSON.put("descripcion", rs.getString("descripcion"));
                monedaJSON.put("tipo_cambio", rs.getDouble("tipo_cambio"));
                monedaJSON.put("nacional",rs.getInt("nacional"));
                monedaJSON.put("creado_por", rs.getInt("creado_por"));
                monedaJSON.put("fecha_creacion", rs.getString("fecha_creacion"));
                monedaJSON.put("modificado_por", rs.getInt("modificado_por"));
                monedaJSON.put("fecha_modificacion", rs.getString("fecha_modificacion"));
                monedaJSON.put("serie", rs.getString("serie"));
                monedaJSON.put("estatus", rs.getString("estatus"));
                
                data.add(monedaJSON);
                
                contador++;
            }
            
            monedas.put("data", data);
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Moneda.class.getName() + "-- metodo: getMonedaJSON", LOGGER);
        } finally {
            
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Moneda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
            try {

                closeStmt();
                if (rs != null) {
                    rs.close();
                }

            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Moneda.class.getName() + "-- metodo: getMonedaJSON", LOGGER);
            }

            closeConexion();

        }
        
        
        
        return monedas;
        
    }

}
