/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.catalogo;

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
public class Departamento extends GenericDAO implements DepartamentoI {

    @Override
    public int setDepartamento(DepartamentoBean db) throws SqlAppsException {
        int resultado = 0;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "INSERT INTO cat_departamento\n"
                + "(id,\n"
                + "descripcion,\n"
                + "creado_por,\n"
                + "fecha_creacion,\n"
                + "modificado_por,\n"
                + "fecha_modificacion,\n"
                + "estatus,"
                + "serie)\n"
                + "VALUES\n"
                + "(?,\n"
                + "?,\n"
                + "?,\n"
                + "current_timestamp(),\n"
                + "?,\n"
                + "current_timestamp(),\n"
                + "'A',?)";

        try {
            PreparedStatement ps = getPrepareStatement(sql);
            UtilsDao ut = new UtilsDao();
            int secuencia = ut.nextVal("s_cat_departamento");

            ps.setInt(1, secuencia);
            ps.setString(2, db.getDescripcion());
            ps.setInt(3, db.getCreado_por());
            ps.setInt(4, db.getModificado_por());
            //ps.setString(5, db.getEstatus());
            ps.setString(5, db.getSerie());

            resultado = ps.executeUpdate();
            if (resultado == 1) {
                resultado = secuencia;
            }

        } catch (SQLException ex) {
            System.out.println("el método setDepartamento lanzó el siguiento error " + ex.getMessage());
            throw new SqlAppsException(ex);

        } finally {
            try {
                closePStmt();
                closeConexion();
            } catch (SQLException ex) {
                Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return resultado;

    }

    @Override
    public int updateDepartamento(DepartamentoBean db) throws SqlAppsException {

        int resultado = 0;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "UPDATE cat_departamento\n"
                + "SET\n"
                + "descripcion = ?,\n"
                + "modificado_por = ?\n"
                + "WHERE id = ?";

        PreparedStatement ps;

        try {
            ps = getPrepareStatement(sql);
            ps.setString(1, db.getDescripcion());
            ps.setInt(2, db.getCreado_por());
            ps.setInt(3, db.getModificado_por());
            ps.setInt(4, db.getId());

            resultado = ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("el método updateDepartamento lanzó el siguiento error " + ex.getMessage());
            throw new SqlAppsException(ex);

        } finally {
            try {
                closePStmt();
                closeConexion();
            } catch (SQLException ex) {
                Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return resultado;
    }

    @Override
    public int deleteDepartamento(DepartamentoBean db) throws SqlAppsException {

        int resultado = 0;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "UPDATE cat_departamento "
                + "set estatus = 'D' "
                + ",modificado_por = ? "
                + "WHERE id = ?";

        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setInt(1, db.getModificado_por());
            ps.setInt(2, db.getId());

            resultado = ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("el método deleteDepartamento lanzó el siguiento error " + ex.getMessage());
            throw new SqlAppsException(ex);

        } finally {
            try {
                closePStmt();
                closeConexion();
            } catch (SQLException ex) {
                Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return resultado;

    }

    @Override
    public ListIterator<DepartamentoBean> getDepartamento() throws SqlAppsException {
        LinkedList<DepartamentoBean> lista = new LinkedList<>();
        ResultSet rs = null;
        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "SELECT id,\n"
                + "descripcion,\n"
                + "creado_por,\n"
                + "date_format(fecha_creacion,'%d/%m/%Y') fecha_creacion,\n"
                + "modificado_por,\n"
                + "date_format(fecha_modificacion,'%d/%m/%Y') fecha_modificacion,\n"
                + "estatus, serie\n"
                + "FROM cat_departamento";
        
        try {
            Statement stmt = getStatement();
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
            
                DepartamentoBean db = new DepartamentoBean();
                db.setId(rs.getInt("id"));
                db.setDescripcion(rs.getString("descripcion"));
                db.setCreado_por(rs.getInt("creado_por"));
                db.setFecha_creacion(rs.getString("fecha_creacion"));
                db.setSerie(rs.getString("serie"));
                
                lista.add(db);
                
            }
            
        } catch (SQLException ex) {
            System.out.println("el método a lanzadó el siguiente error "+ex.getMessage());
            throw new SqlAppsException(ex);
            //Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                closeStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion();
        }

        return lista.listIterator();
    }

    @Override
    public DepartamentoBean getDepartamento(int id) throws SqlAppsException {
        
        DepartamentoBean db = new DepartamentoBean();
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "SELECT id,\n"
                + "descripcion,\n"
                + "creado_por,\n"
                + "date_format(fecha_creacion,'%d/%m/%Y') fecha_creacion,\n"
                + "modificado_por,\n"
                + "date_format(fecha_modificacion,'%d/%m/%Y') fecha_modificacion,\n"
                + "estatus, serie\n"
                + "FROM cat_departamento WHERE id = ?";
        
        PreparedStatement ps;
        ResultSet rs = null;
        try {
            ps = getPrepareStatement(sql);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                db.setId(rs.getInt("id"));
                db.setDescripcion(rs.getString("descripcion"));
                db.setCreado_por(rs.getInt("creado_por"));
                db.setFecha_creacion(rs.getString("fecha_creacion"));
                db.setModificado_por(rs.getInt("modificado_por"));
                db.setFecha_modificacion(rs.getString("fecha_modificacion"));
                db.setEstatus(rs.getString("estatus"));
                db.setSerie(rs.getString("serie"));
                
            }
            
        } catch (SQLException ex) {
            System.out.println("el método lanzó el siguiente error "+ex.getMessage());
            throw new SqlAppsException(ex);
            //Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                rs.close();
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
        }
        
        
        return db;

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public JSONObject getDepartamentoListaJSON(int draw) throws SqlAppsException {
    
        Statement stmt = null;
        ResultSet rs = null;
        JSONObject departamento = new JSONObject();
        JSONArray data = new JSONArray();
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "";
        
        
        return departamento;
    
    }

}
