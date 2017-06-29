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
public class Unidad extends GenericDAO implements UnidadI {
    
    static final Logger LOGGER = Logger.getLogger(Unidad.class.getName());
    
    @Override
    public int setUnidad(UnidadBean ub) throws SqlAppsException {

        int resultado = 0;

        int secuencia = 0;
        Connection cnn = getConexionSinCommit(EDriver.MYSQL, EApps.PV);

        String sql = "INSERT INTO cat_unidad\n"
                + "(id,\n"
                + "descripcion,\n"
                + "creado_por,\n"
                + "modificado_por,\n"
                + "serie, abrev)\n"
                + "VALUES\n"
                + "(?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?, ?)";

        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);
            secuencia = new UtilsDao().nextVal("s_cat_unidad",cnn);
            
            ps.setInt(1, secuencia);
            ps.setString(2, ub.getDescripcion());
            ps.setInt(3, ub.getCreado_por());
            ps.setInt(4, ub.getModificado_por());
            ps.setString(5, ub.getSerie());
            ps.setString(6, ub.getAbrev());

            resultado = ps.executeUpdate();
            commit();
            if (resultado != 0) {
                resultado = secuencia;
            }
            

        } catch (SQLException ex) {
            rollback();
            throw new SqlAppsException(ex, Unidad.class.getName() + "-- metodo: setMoneda", LOGGER);
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Unidad.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion();
        }

        return resultado;
        
        
    }

    @Override
    public int updateUnidad(UnidadBean ub) throws SqlAppsException {
        int resultado = 0;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "UPDATE cat_unidad\n"
                + "SET\n"
                + "descripcion = ?,\n"
                + "modificado_por = ?,\n"
                + "serie = ?, abrev = ?\n"
                + "WHERE id = ?";

        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setString(1, ub.getDescripcion());
            ps.setInt(2, ub.getModificado_por());
            ps.setString(3, ub.getSerie());
            ps.setString(4, ub.getAbrev());
            ps.setInt(5, ub.getId());

            resultado = ps.executeUpdate();

        } catch (SQLException ex) {

            throw new SqlAppsException(ex, Unidad.class.getName() + "-- metodo: updateUnidad", LOGGER);
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Unidad.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion();
        }

        return resultado;
        
    }

    @Override
    public int deleteUnidad(int id) throws SqlAppsException {
        int resultado = 0;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "UPDATE cat_unidad set estatus = 'D' WHERE id = ?";

        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setInt(1, id);

            resultado = ps.executeUpdate();

        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Unidad.class.getName() + "-- metodo: deleteUnidad", LOGGER);
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Unidad.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion();
        }

        return resultado;
        
    }

    @Override
    public ListIterator<UnidadBean> getUnidad() throws SqlAppsException {

        LinkedList<UnidadBean> lista = new LinkedList<>();

        getConexion(EDriver.MYSQL, EApps.PV);
        Statement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT id,\n"
                + "descripcion,\n"
                + "creado_por,\n"
                + "date_format(fecha_creacion,'%d/%m/%Y') fecha_creacion,\n"
                + "modificado_por,\n"
                + "date_format(fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion,\n"
                + "serie,\n"
                + "estatus, abrev\n"
                + "FROM cat_unidad \n"
                + "WHERE estatus = 'A' order by descripcion";

        try {
            stmt = getStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                UnidadBean ub = new UnidadBean();

                ub.setId(rs.getInt("id"));
                ub.setDescripcion(rs.getString("descripcion"));
                ub.setCreado_por(rs.getInt("creado_por"));
                ub.setFecha_creacion(rs.getString("fecha_creacion"));
                ub.setModificado_por(rs.getInt("modificado_por"));
                ub.setFecha_modificacion(rs.getString("fecha_modificacion"));
                ub.setSerie(rs.getString("serie"));
                ub.setEstatus(rs.getString("estatus"));
                ub.setAbrev(rs.getString("abrev"));

                lista.add(ub);
            }

        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Unidad.class.getName() + "-- metodo: set lost.Moneda", LOGGER);
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Unidad.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Unidad.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();

        }

        return lista.listIterator();

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UnidadBean getUnidadBean(int id) throws SqlAppsException {
        
        UnidadBean ub = new UnidadBean();
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
       getConexion(EDriver.MYSQL, EApps.PV);
        ResultSet rs = null;

        String sql = "SELECT id,\n"
                + "descripcion,\n"
                + "creado_por,\n"
                + "date_format(fecha_creacion,'%d/%m/%Y') fecha_creacion,\n"
                + "modificado_por,\n"
                + "date_format(fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion,\n"
                + "serie,\n"
                + "estatus, abrev\n"
                + "FROM cat_unidad WHERE id = ? AND estatus = 'A'";
        
        PreparedStatement ps;
        
        
        try {
            ps = getPrepareStatement(sql);
            
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                ub.setId(rs.getInt("id"));
                ub.setDescripcion(rs.getString("descripcion"));
                ub.setCreado_por(rs.getInt("creado_por"));
                ub.setModificado_por(rs.getInt("modificado_por"));
                ub.setFecha_creacion(rs.getString("fecha_creacion"));
                ub.setFecha_modificacion(rs.getString("fecha_modificacion"));
                ub.setSerie(rs.getString("serie"));
                ub.setEstatus(rs.getString("estatus"));
                ub.setAbrev(rs.getString("abrev"));
            }
        } catch (SQLException ex) {
            
            throw new SqlAppsException(ex, Unidad.class.getName() + "-- metodo: getUnidadBean", LOGGER);
        }finally{
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Unidad.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Unidad.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion();
        
        }
        
       
        
        
        return ub;

            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<UnidadBean> getUnidad(int id) throws SqlAppsException {
        LinkedList<UnidadBean> lista = new LinkedList<>();

        getConexion(EDriver.MYSQL, EApps.PV);
        Statement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT id,\n"
                + "descripcion,\n"
                + "creado_por,\n"
                + "date_format(fecha_creacion,'%d/%m/%Y') fecha_creacion,\n"
                + "modificado_por,\n"
                + "date_format(fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion,\n"
                + "serie,\n"
                + "estatus, abrev\n"
                + "FROM cat_unidad \n"
                + "WHERE id = ? AND estatus = 'A' order by descripcion";

        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                UnidadBean ub = new UnidadBean();

                ub.setId(rs.getInt("id"));
                ub.setDescripcion(rs.getString("descripcion"));
                ub.setCreado_por(rs.getInt("creado_por"));
                ub.setFecha_creacion(rs.getString("fecha_creacion"));
                ub.setModificado_por(rs.getInt("modificado_por"));
                ub.setFecha_modificacion(rs.getString("fecha_modificacion"));
                ub.setSerie(rs.getString("serie"));
                ub.setEstatus(rs.getString("estatus"));
                ub.setAbrev(rs.getString("abrev"));

                lista.add(ub);
            }

        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Unidad.class.getName() + "-- metodo: getUnidad(id)", LOGGER);
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Unidad.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Unidad.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();

        }

        return lista.listIterator();

    }

    @Override
    public JSONObject getUnidadListaJSON(int draw) throws SqlAppsException {
        
        int contador = 0;
        JSONObject monedas = new JSONObject();
        JSONArray data = new JSONArray();
        
        getConexion(EDriver.MYSQL, EApps.PV);
        Statement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT id,\n"
                + "descripcion, abrev,\n"
                + "creado_por,\n"
                + "date_format(fecha_creacion,'%d/%m/%Y') fecha_creacion,\n"
                + "modificado_por,\n"
                + "date_format(fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion,\n"
                + "serie,\n"
                + "estatus, abrev\n"
                + "FROM cat_unidad \n"
                + "WHERE estatus = 'A' Order by descripcion";
        
        try {
            stmt = getStatement();
            
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                
                JSONObject unidadJSON = new JSONObject();
                unidadJSON.put("id", rs.getInt("id"));
                unidadJSON.put("descripcion",rs.getString("descripcion"));
                unidadJSON.put("abrev", rs.getString("abrev"));
                unidadJSON.put("creado_por", rs.getInt("creado_por"));
                unidadJSON.put("fecha_creacion", rs.getString("fecha_creacion"));
                unidadJSON.put("modificado_por",rs.getInt("modificado_por"));
                unidadJSON.put("fecha_modificacion", rs.getString("fecha_modificacion"));
                unidadJSON.put("serie",rs.getString("serie"));
                unidadJSON.put("estatus",rs.getString("estatus"));
                
                data.add(unidadJSON);
                
                contador++;
                
            }
            
            monedas.put("data", data);
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Unidad.class.getName() + "-- metodo: getUnidadJSON", LOGGER);
        } finally {
            
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Unidad.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
            try {

                closeStmt();
                if (rs != null) {
                    rs.close();
                }

            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Unidad.class.getName() + "-- metodo: getUnidadJSON", LOGGER);
            }

            closeConexion();

        }
        
        
        return monedas;
    }

}
