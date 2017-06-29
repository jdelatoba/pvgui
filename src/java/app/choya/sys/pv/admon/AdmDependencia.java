/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.admon;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import app.choya.sys.pv.dao.EApps;
import app.choya.sys.pv.dao.EDriver;
import app.choya.sys.pv.dao.GenericDAO;

/**
 *
 * @author developer
 */
public class AdmDependencia extends GenericDAO implements AdmDependenciaI {

    @Override
    public ListIterator<AdmDependenciaBean> getListadoDependencia() {
        LinkedList<AdmDependenciaBean> lista = new LinkedList<AdmDependenciaBean>();

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "SELECT dependencia_id\n"
                + ",nombre\n"
                + "FROM adm_dependencias\n"
                + "ORDER BY nombre";

        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                AdmDependenciaBean db = new AdmDependenciaBean();
                db.setDependencia_id(rs.getInt("dependencia_id"));
                db.setNombre(rs.getString("nombre"));
                lista.add(db);

            }
        } catch (SQLException sqlex) {
            System.out.println("****************** Inicia Error getListadoDependencia ********************");
            System.out.println("Error al llamar al metodo getListadoDependencia metodo: getListadoDependencia");
            System.out.println("Nombre Clase: DependenciaBean metodo: getListadoDependencia");
            System.out.println("Mensaje de Error: " + sql);
            System.out.println("Mensaje de Error: " + sqlex.getMessage());
            System.out.println("Mensaje de Error: " + sqlex.getSQLState());

        } finally {
            try {
                closeStmt();
                closeResultSet();
                closeConexion();
            } catch (SQLException ex) {
                Logger.getLogger(AdmDependencia.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return lista.listIterator();
    }

    @Override
    public int setAdmDependencia(AdmDependenciaBean db) {

        getConexion(EDriver.MYSQL, EApps.PV);

        int resultado = 0;

        String sql = "INSERT INTO adm_dependencias\n"
                + "(dependencia_id,\n"
                + "nombre,\n"
                + "calle1,\n"
                + "calle2,\n"
                + "calle3,\n"
                + "no_exterior,\n"
                + "fecha_creacion,\n"
                + "modificado_por,\n"
                + "no_interior,\n"
                + "codigo_postal,\n"
                + "colonia,\n"
                + "telefono1,\n"
                + "telefono2,\n"
                + "correo,\n"
                + "responsable_id,\n"
                + "creado_por)\n"
                + "VALUES\n"
                + "(null,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "current_timestamp(),\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?)";

        try {
            PreparedStatement ps = getPrepareStatementWithKey(sql);
            ps.setString(1, db.getNombre());
            ps.setString(2, db.getCalle1());
            ps.setString(3, db.getCalle2());
            ps.setString(4, db.getCalle3());
            ps.setString(5, db.getNo_exterior());
            ps.setInt(6, db.getCreado_por());
            ps.setString(7, db.getNo_interior());
            ps.setString(8, db.getCodigo_postal());
            ps.setString(9, db.getColonia());
            ps.setString(10, db.getTelefono1());
            ps.setString(11, db.getTelefono2());
            ps.setString(12, db.getCorreo());
            ps.setInt(13, db.getResponsable_id());
            ps.setInt(14, db.getCreado_por());

            resultado = ps.executeUpdate();

            if (resultado > 0) {

                ResultSet rs = ps.getGeneratedKeys();
                while (rs != null && rs.next()) {
                    resultado = rs.getInt(1);
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(AdmDependencia.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                closePStmt();
                closeStmt();
                closeResultSet();
                closeConexion();
            } catch (SQLException ex) {
                Logger.getLogger(AdmDependencia.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return resultado;

    }

    @Override
    public int updateAdmDependencia(AdmDependenciaBean db) {

        int resultado = 0;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "UPDATE adm_dependencias\n"
                + "SET\n"
                + "nombre = ?,\n"
                + "calle1 = ?,\n"
                + "calle2 = ?,\n"
                + "calle3 = ?,\n"
                + "no_exterior = ?,\n"
                + "fecha_creacion = current_timestamp(),\n"
                + "modificado_por = ?,\n"
                + "no_interior = ?,\n"
                + "codigo_postal = ?,\n"
                + "fecha_modificacion = current_timestamp(),\n"
                + "colonia = ?,\n"
                + "telefono1 = ?,\n"
                + "telefono2 = ?,\n"
                + "correo = ?,\n"
                + "responsable_id = ?,\n"
                + "creado_por = ?\n"
                + "WHERE dependencia_id = ?";
        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setString(1, db.getNombre());
            ps.setString(2, db.getCalle1());
            ps.setString(3, db.getCalle2());
            ps.setString(4, db.getCalle3());
            ps.setString(5, db.getNo_exterior());
            ps.setInt(6, db.getModificado_por());
            ps.setString(7, db.getNo_interior());
            ps.setString(8, db.getCodigo_postal());
            ps.setString(9, db.getColonia());
            ps.setString(10, db.getTelefono1());
            ps.setString(11, db.getTelefono2());
            ps.setString(12, db.getCorreo());
            ps.setInt(13, db.getResponsable_id());
            ps.setInt(14, db.getCreado_por());
            ps.setInt(15, db.getDependencia_id());

            resultado = ps.executeUpdate();

            return resultado;
        } catch (SQLException ex) {
            Logger.getLogger(AdmDependencia.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                closePStmt();
                closeConexion();
            } catch (SQLException ex) {
                Logger.getLogger(AdmDependencia.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return resultado;
    }

    @Override
    public ListIterator<AdmDependenciaBean> getListadoAdmDependencia() {
        LinkedList<AdmDependenciaBean> lista = new LinkedList<AdmDependenciaBean>();

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "SELECT dependencia_id,\n"
                + "nombre,\n"
                + "calle1,\n"
                + "calle2,\n"
                + "calle3,\n"
                + "no_exterior,\n"
                + "fecha_creacion,\n"
                + "modificado_por,\n"
                + "no_interior,\n"
                + "codigo_postal,\n"
                + "fecha_modificacion,\n"
                + "colonia,\n"
                + "telefono1,\n"
                + "telefono2,\n"
                + "correo,\n"
                + "responsable_id,\n"
                + "creado_por\n"
                + "FROM adm_dependencias order by nombre";

        try {
            Statement stmt =  getStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                AdmDependenciaBean db = new AdmDependenciaBean();
                db.setDependencia_id(rs.getInt("dependencia_id"));
                db.setNombre(rs.getString("nombre"));
                lista.add(db);

            }
        } catch (SQLException sqlex) {
            System.out.println("****************** Inicia Error getListadoAdmDependencia ********************");
            System.out.println("Error al llamar al metodo getListadoAdmDependencia metodo: getListadoAdmDependencia");
            System.out.println("Nombre Clase: DependenciaBean metodo: getListadoAdmDependencia");
            System.out.println("Mensaje de Error: " + sql);
            System.out.println("Mensaje de Error: " + sqlex.getMessage());
            System.out.println("Mensaje de Error: " + sqlex.getSQLState());

        } finally {
            try {
                closeStmt();
                closeResultSet();
                closeConexion();
            } catch (SQLException ex) {
                Logger.getLogger(AdmDependencia.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return lista.listIterator();
    }
    
    
    @Override
    public AdmDependenciaBean getAdmDependencia(int dependencia_id) {
        AdmDependenciaBean db = new AdmDependenciaBean();

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "SELECT dependencia_id,\n"
                + "nombre,\n"
                + "calle1,\n"
                + "calle2,\n"
                + "calle3,\n"
                + "no_exterior,\n"
                + "fecha_creacion,\n"
                + "modificado_por,\n"
                + "no_interior,\n"
                + "codigo_postal,\n"
                + "fecha_modificacion,\n"
                + "colonia,\n"
                + "telefono1,\n"
                + "telefono2,\n"
                + "correo,\n"
                + "responsable_id,\n"
                + "creado_por\n"
                + "FROM adm_dependencias\n"
                + "WHERE dependencia_id = ?\n"
                + "ORDER BY nombre";

        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setInt(1, dependencia_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                
                db.setDependencia_id(rs.getInt("dependencia_id"));
                db.setNombre(rs.getString("nombre"));
                db.setCalle1(rs.getString("calle1"));
                db.setCalle2(rs.getString("calle2"));
                db.setCalle3(rs.getString("calle3"));
                db.setNo_exterior(rs.getString("no_exterior"));
                db.setFecha_creacion(rs.getString("fecha_creacion"));
                db.setModificado_por(rs.getInt("modificado_por"));
                db.setNo_interior(rs.getString("no_interior"));
                db.setCodigo_postal(rs.getString("codigo_postal"));
                db.setFecha_modificacion(rs.getString("fecha_modificacion"));
                db.setColonia(rs.getString("colonia"));
                db.setTelefono1(rs.getString("telefono1"));
                db.setTelefono2(rs.getString("telefono2"));
                db.setCorreo(rs.getString("correo"));
                db.setResponsable_id(rs.getInt(rs.getInt("responsable_id")));
                db.setCreado_por(rs.getInt("creado_por"));

            }
        } catch (SQLException sqlex) {
            System.out.println("****************** Inicia Error getAdmDependencia ********************");
            System.out.println("Error al llamar al metodo getAdmDependencia metodo: getAdmDependencia");
            System.out.println("Nombre Clase: Dependencia metodo: getAdmDependencia");
            System.out.println("Mensaje de Error: " + sql);
            System.out.println("Mensaje de Error: " + sqlex.getMessage());
            System.out.println("Mensaje de Error: " + sqlex.getSQLState());

        } finally {
            try {
                closePStmt();
                closeResultSet();
                closeConexion();
            } catch (SQLException ex) {
                Logger.getLogger(AdmDependencia.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return db;
    }
    
    
    public int doEliminarDependencia(int dependencia_id) throws SQLException {
        int resultado = 0;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "DELETE FROM adm_dependencias WHERE dependencia_id = ?";

        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setInt(1, dependencia_id);
            resultado = ps.executeUpdate();
        } catch (SQLException sqlex) {
            System.out.println("****************** Inicia Error doEliminarDependencia ********************");
            System.out.println("Error al llamar al metodo doEliminarDependencia metodo: doEliminarDependencia");
            System.out.println("Nombre Clase: Dependencia metodo: doEliminarDependencia");
            System.out.println("Mensaje de Error: " + sql);
            System.out.println("Mensaje de Error: " + sqlex.getMessage());
            System.out.println("Mensaje de Error: " + sqlex.getSQLState());
            throw new SQLException(sqlex);

        } finally {
            try {
                closePStmt();
                closeConexion();
            } catch (SQLException ex) {
                Logger.getLogger(AdmDependencia.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return resultado;

    }
     


}
