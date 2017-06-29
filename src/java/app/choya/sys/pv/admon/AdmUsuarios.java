/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.admon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import app.choya.sys.pv.dao.EApps;
import app.choya.sys.pv.dao.EDriver;
import app.choya.sys.pv.dao.GenericDAO;
import app.choya.sys.pv.utils.UtilsDao;
import app.choya.sys.pv.utils.SqlAppsException;
import java.io.Serializable;

/**
 *
 * @author developer
 */
public class AdmUsuarios extends GenericDAO implements AdmUsuariosI {

    private String sql = "";
    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement ps = null;

    private UtilsDao utilsDao = new UtilsDao();

    @Override
    public int setAdmUsuarios(AdmUsuariosBean ub, EDriver driver, EApps app) throws SqlAppsException {

        int resultado = 0;

        try {
            getConexion(driver, app);

            sql = "INSERT INTO adm_usuarios\n"
                    + "(usuario_id,\n"
                    + "usuario,\n"
                    + "nombre,\n"
                    + "apellido_paterno,\n"
                    + "apellido_materno,\n"
                    + "dependencia_id,\n"
                    + "direccion_id,\n"
                    + "departamento_id,\n"
                    + "correo,\n"
                    + "descripcion,\n"
                    + "password,\n"
                    + "fecha_inicio,\n"
                    + "fecha_termino,\n"
                    + "fecha_ultimo_acceso,\n"
                    + "fecha_password,\n"
                    + "expirado,\n"
                    + "dirip,\n"
                    + "oficina,\n"
                    + "adscripcion,\n"
                    + "telefono,\n"
                    + "extension,\n"
                    + "creado_por,\n"
                    + "fecha_creacion,\n"
                    + "modificado_por,\n"
                    + "fecha_modificacion)\n"
                    + "VALUES\n"
                    + "(null,\n -- <{usuario_id: }>"
                    + "?, \n -- <{usuario: }>"
                    + "?, \n -- <{nombre: }>"
                    + "?, \n -- <{apellido_paterno: }>"
                    + "?, \n -- <{apellido_materno: }>"
                    + "?, \n -- <{dependencia_id: }>"
                    + "?, \n -- <{direccion_id: }>"
                    + "?, \n -- <{departamento_id: }>"
                    + "?, \n -- <{correo: }>"
                    + "?, \n -- <{descripcion: }>"
                    + "?, \n -- <{password: }>"
                    + "?, \n -- <{fecha_inicio: }>"
                    + "?, \n -- <{fecha_termino: }>"
                    + "?, \n -- <{fecha_ultimo_acceso: }>"
                    + "?, \n -- <{fecha_password: }>"
                    + "?, \n -- <{expirado: }>"
                    + "?, \n -- <{dirip: }>"
                    + "?, \n -- <{oficina: }>"
                    + "?, \n -- <{adscripcion: }>"
                    + "?, \n -- <{telefono: }>"
                    + "?, \n -- <{extension: }> "
                    + "?, \n -- <{creado_por: }> "
                    + "CURRENT_TIMESTAMP, \n -- <{fecha_modificacion: }> "
                    + "?, \n -- <{modificado_por: }> "
                    + "CURRENT_TIMESTAMP, \n -- <{fecha_modificacion: }> "
                    + ");";

            ps = getPrepareStatementWithKey(sql);

            ps.setString(1, ub.getUsuario());
            ps.setString(2, ub.getNombre());
            ps.setString(3, ub.getApellido_paterno());
            ps.setString(4, ub.getApellido_materno());
            ps.setInt(5, ub.getDependencia_id());
            ps.setInt(6, ub.getDireccion_id());
            ps.setInt(7, ub.getDepartamento_id());
            ps.setString(8, ub.getCorreo());
            ps.setString(9, ub.getDescripcion());
            ps.setString(10, utilsDao.hash(ub.getPassword()));
            ps.setString(11, utilsDao.getFechaToMysql(ub.getFecha_inicio()));
            ps.setString(12, utilsDao.getFechaToMysql(ub.getFecha_termino()));
            ps.setString(13, utilsDao.getFechaToMysql(ub.getFecha_ultimo_acceso()));
            ps.setString(14, utilsDao.getFechaToMysql(ub.getFecha_password()));
            ps.setString(15, ub.getExpirado());
            ps.setString(16, ub.getDirip());
            ps.setString(17, ub.getOficina());
            ps.setString(18, ub.getAdscripcion());
            ps.setString(19, ub.getTelefono());
            ps.setString(20, ub.getExtension());
            ps.setInt(21, ub.getCreado_por());
            ps.setInt(22, ub.getModificado_por());

            // si el executeUpdate es exitoso, el metodo executeUpdate regresara
            // el valor de la llave primaria generado por la secuencia
            if (ps.executeUpdate() > 0) {

                // getGeneratedKeys() regresa el valor de la llave generado por la secuencia
                // en nuestro caso usuario_id
                rs = ps.getGeneratedKeys();

                if (rs != null && rs.next()) {

                    //asignamos el valor de usuario_id que se genero con la secuencia
                    resultado = rs.getInt(1);
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(AdmAplicacion.class.getName()).log(Level.SEVERE, null, ex);
            throw new SqlAppsException(ex);
        } finally {
            try {
                closeResultSet();
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(AdmAplicacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion();
        }
        return resultado;

    }

    @Override
    public int updateAdmUsuarios(AdmUsuariosBean ub, EDriver driver, EApps app) throws SqlAppsException {
        int resultado = 0;

        try {
            conn = getConexion(driver, app);

            sql = "UPDATE adm_usuarios "
                    + " SET descripcion = ?"
                    + ",fecha_inicio = ?"
                    + ",fecha_termino = ?"
                    + ",fecha_modificacion = current_timestamp()"
                    + ",modificado_por = ?"
                    + ",expirado = ?"
                    + ",dirip = ?"
                    + ",oficina = ?"
                    + ",adscripcion = ?"
                    + ",nombre = ?"
                    + ",apellido_paterno = ?"
                    + ",apellido_materno = ?"
                    + ",dependencia_id = ?"
                    + ",direccion_id = ?"
                    + ",departamento_id = ?"
                    + ",correo = ? "
                    + " WHERE usuario_id = ?";

            ps = conn.prepareStatement(sql);

            ps.setString(1, ub.getDescripcion());
            ps.setString(2, ub.getFecha_inicio());
            ps.setString(3, ub.getFecha_termino());
            ps.setInt(4, ub.getModificado_por());
            ps.setString(5, ub.getExpirado());
            ps.setString(6, ub.getDirip());
            ps.setString(7, ub.getOficina());
            ps.setString(8, ub.getAdscripcion());
            ps.setString(9, ub.getNombre());
            ps.setString(10, ub.getApellido_paterno());
            ps.setString(11, ub.getApellido_materno());
            ps.setInt(12, ub.getDependencia_id());
            ps.setInt(13, ub.getDireccion_id());
            ps.setInt(14, ub.getDepartamento_id());
            ps.setString(15, ub.getCorreo());
            ps.setInt(16, ub.getUsuario_id());

            resultado = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AdmAplicacion.class.getName()).log(Level.SEVERE, null, ex);
            throw new SqlAppsException(ex);
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(AdmAplicacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion();
        }
        return resultado;
    }

    @Override
    public AdmUsuariosBean getAdmUsuario(int usuario_id, EDriver driver, EApps app) throws SqlAppsException {

        AdmUsuariosBean ab = new AdmUsuariosBean();

        getConexion(driver, app);

        sql = "SELECT usuario_id\n"
                + ",usuario\n"
                + ",IFNULL(nombre,'') nombre\n"
                + ",IFNULL(apellido_paterno,'') apellido_paterno\n"
                + ",IFNULL(apellido_materno,'') apellido_materno\n"
                + ",dependencia_id\n"
                + ",direccion_id\n"
                + ",departamento_id\n"
                + ",correo\n"
                + ",IFNULL(descripcion,'') descripcion\n"
                + ",IFNULL(DATE_FORMAT(fecha_inicio,'%d/%m/%Y'),'') fecha_inicio\n"
                + ",IFNULL(DATE_FORMAT(fecha_termino,'%d/%m/%Y'),'') fecha_termino\n"
                + ",IFNULL(DATE_FORMAT(fecha_ultimo_acceso,'%d/%m/%Y %M:%i:%s'),'') fecha_ultimo_acceso\n"
                + ",IFNULL(DATE_FORMAT(fecha_password,'%d/%m/%Y'),'') fecha_password\n"
                + ",IFNULL(DATE_FORMAT(fecha_creacion,'%d/%m/%Y'),'') fecha_creacion\n"
                + ",creado_por\n"
                + ",IFNULL(DATE_FORMAT(fecha_modificacion,'%d/%m/%Y'),'') fecha_modificacion\n"
                + ",modificado_por\n"
                + ",expirado\n"
                + ",IFNULL(dirip,'') dirip\n"
                + ",IFNULL(oficina,'') oficina\n"
                + ",IFNULL(adscripcion,'') adscripcion\n"
                + "FROM adm_usuarios\n"
                + "WHERE usuario_id = ?";

        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setInt(1, usuario_id);

            rs = ps.executeQuery();

            while (rs.next()) {

                ab.setUsuario_id(rs.getInt("usuario_id"));
                ab.setUsuario(rs.getString("usuario"));
                ab.setNombre(rs.getString("nombre"));
                ab.setApellido_paterno(rs.getString("apellido_paterno"));
                ab.setApellido_materno(rs.getString("apellido_materno"));
                ab.setDependencia_id(rs.getInt("dependencia_id"));
                ab.setDireccion_id(rs.getInt("direccion_id"));
                ab.setDepartamento_id(rs.getInt("departamento_id"));
                ab.setCorreo(rs.getString("correo"));
                ab.setDescripcion(rs.getString("descripcion"));
                ab.setFecha_inicio(rs.getString("fecha_inicio"));
                ab.setFecha_termino(rs.getString("fecha_termino"));
                ab.setFecha_ultimo_acceso(rs.getString("fecha_ultimo_acceso"));
                ab.setFecha_password(rs.getString("fecha_password"));
                ab.setFecha_creacion(rs.getString("fecha_creacion"));
                ab.setCreado_por(rs.getInt("creado_por"));
                ab.setFecha_modificacion(rs.getString("fecha_modificacion"));
                ab.setModificado_por(rs.getInt("modificado_por"));
                ab.setExpirado(rs.getString("expirado"));
                ab.setDirip(rs.getString("dirip"));
                ab.setOficina(rs.getString("oficina"));
                ab.setAdscripcion(rs.getString("adscripcion"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(AdmAplicacion.class.getName()).log(Level.SEVERE, null, ex);
            throw new SqlAppsException(ex);
        } finally {
            try {
                closeResultSet();
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(AdmAplicacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion();
        }
        return ab;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<AdmUsuariosBean> getAdmUsuario(String nombre, String apellido, EDriver driver, EApps app) throws SqlAppsException {
        AdmUsuariosBean ab = new AdmUsuariosBean();

        LinkedList<AdmUsuariosBean> lista = new LinkedList<>();

        getConexion(driver, app);

        sql = "SELECT usuario_id\n"
                + ",usuario\n"
                + ",IFNULL(nombre,'') nombre\n"
                + ",IFNULL(apellido_paterno,'') apellido_paterno\n"
                + ",IFNULL(apellido_materno,'') apellido_materno\n"
                + ",dependencia_id\n"
                + ",direccion_id\n"
                + ",departamento_id\n"
                + ",correo\n"
                + ",IFNULL(descripcion,'') descripcion\n"
                + ",IFNULL(DATE_FORMAT(fecha_inicio,'%d/%m/%Y'),'') fecha_inicio\n"
                + ",IFNULL(DATE_FORMAT(fecha_termino,'%d/%m/%Y'),'') fecha_termino\n"
                + ",IFNULL(DATE_FORMAT(fecha_ultimo_acceso,'%d/%m/%Y %M:%i:%s'),'') fecha_ultimo_acceso\n"
                + ",IFNULL(DATE_FORMAT(fecha_password,'%d/%m/%Y'),'') fecha_password\n"
                + ",IFNULL(DATE_FORMAT(fecha_creacion,'%d/%m/%Y'),'') fecha_creacion\n"
                + ",creado_por\n"
                + ",IFNULL(DATE_FORMAT(fecha_modificacion,'%d/%m/%Y'),'') fecha_modificacion\n"
                + ",modificado_por\n"
                + ",expirado\n"
                + ",IFNULL(dirip,'') dirip\n"
                + ",IFNULL(oficina,'') oficina\n"
                + ",IFNULL(adscripcion,'') adscripcion\n"
                + "FROM adm_usuarios\n"
                + "WHERE nombre LIKE ? AND apellido_paterno LIKE ?";

        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, apellido);

            rs = ps.executeQuery();

            while (rs.next()) {

                ab.setUsuario_id(rs.getInt("usuario_id"));
                ab.setUsuario(rs.getString("usuario"));
                ab.setNombre(rs.getString("nombre"));
                ab.setApellido_paterno(rs.getString("apellido_paterno"));
                ab.setApellido_materno(rs.getString("apellido_materno"));
                ab.setDependencia_id(rs.getInt("dependencia_id"));
                ab.setDireccion_id(rs.getInt("direccion_id"));
                ab.setDepartamento_id(rs.getInt("departamento_id"));
                ab.setCorreo(rs.getString("correo"));
                ab.setDescripcion(rs.getString("descripcion"));
                ab.setFecha_inicio(rs.getString("fecha_inicio"));
                ab.setFecha_termino(rs.getString("fecha_termino"));
                ab.setFecha_ultimo_acceso(rs.getString("fecha_ultimo_acceso"));
                ab.setFecha_password(rs.getString("fecha_password"));
                ab.setFecha_creacion(rs.getString("fecha_creacion"));
                ab.setCreado_por(rs.getInt("creado_por"));
                ab.setFecha_modificacion(rs.getString("fecha_modificacion"));
                ab.setModificado_por(rs.getInt("modificado_por"));
                ab.setExpirado(rs.getString("expirado"));
                ab.setDirip(rs.getString("dirip"));
                ab.setOficina(rs.getString("oficina"));
                ab.setAdscripcion(rs.getString("adscripcion"));

                lista.add(ab);

            }

        } catch (SQLException ex) {
            Logger.getLogger(AdmAplicacion.class.getName()).log(Level.SEVERE, null, ex);
            throw new SqlAppsException(ex);
        } finally {
            try {
                closeResultSet();
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(AdmAplicacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion();
        }
        return lista.listIterator();
    }

    @Override
    public ListIterator<AdmUsuariosBean> getAdmUsuario(String usuario, EDriver driver, EApps app) throws SqlAppsException {

        LinkedList<AdmUsuariosBean> lista = new LinkedList<>();

        getConexion(driver, app);

        sql = "SELECT usuario_id\n"
                + ",usuario\n"
                + ",IFNULL(nombre,'') nombre\n"
                + ",IFNULL(apellido_paterno,'') apellido_paterno\n"
                + ",IFNULL(apellido_materno,'') apellido_materno\n"
                + ",dependencia_id\n"
                + ",direccion_id\n"
                + ",departamento_id\n"
                + ",correo\n"
                + ",IFNULL(descripcion,'') descripcion\n"
                + ",IFNULL(DATE_FORMAT(fecha_inicio,'%d/%m/%Y'),'') fecha_inicio\n"
                + ",IFNULL(DATE_FORMAT(fecha_termino,'%d/%m/%Y'),'') fecha_termino\n"
                + ",IFNULL(DATE_FORMAT(fecha_ultimo_acceso,'%d/%m/%Y %M:%i:%s'),'') fecha_ultimo_acceso\n"
                + ",IFNULL(DATE_FORMAT(fecha_password,'%d/%m/%Y'),'') fecha_password\n"
                + ",IFNULL(DATE_FORMAT(fecha_creacion,'%d/%m/%Y'),'') fecha_creacion\n"
                + ",creado_por\n"
                + ",IFNULL(DATE_FORMAT(fecha_modificacion,'%d/%m/%Y'),'') fecha_modificacion\n"
                + ",modificado_por\n"
                + ",expirado\n"
                + ",IFNULL(dirip,'') dirip\n"
                + ",IFNULL(oficina,'') oficina\n"
                + ",IFNULL(adscripcion,'') adscripcion\n"
                + "FROM adm_usuarios\n"
                + "WHERE usuario = ?";

        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setString(1, usuario);

            rs = ps.executeQuery();

            while (rs.next()) {

                AdmUsuariosBean ab = new AdmUsuariosBean();
                ab.setUsuario_id(rs.getInt("usuario_id"));
                ab.setUsuario(rs.getString("usuario"));
                ab.setNombre(rs.getString("nombre"));
                ab.setApellido_paterno(rs.getString("apellido_paterno"));
                ab.setApellido_materno(rs.getString("apellido_materno"));
                ab.setDependencia_id(rs.getInt("dependencia_id"));
                ab.setDireccion_id(rs.getInt("direccion_id"));
                ab.setDepartamento_id(rs.getInt("departamento_id"));
                ab.setCorreo(rs.getString("correo"));
                ab.setDescripcion(rs.getString("descripcion"));
                ab.setFecha_inicio(rs.getString("fecha_inicio"));
                ab.setFecha_termino(rs.getString("fecha_termino"));
                ab.setFecha_ultimo_acceso(rs.getString("fecha_ultimo_acceso"));
                ab.setFecha_password(rs.getString("fecha_password"));
                ab.setFecha_creacion(rs.getString("fecha_creacion"));
                ab.setCreado_por(rs.getInt("creado_por"));
                ab.setFecha_modificacion(rs.getString("fecha_modificacion"));
                ab.setModificado_por(rs.getInt("modificado_por"));
                ab.setExpirado(rs.getString("expirado"));
                ab.setDirip(rs.getString("dirip"));
                ab.setOficina(rs.getString("oficina"));
                ab.setAdscripcion(rs.getString("adscripcion"));

                lista.add(ab);

            }

        } catch (SQLException ex) {
            Logger.getLogger(AdmAplicacion.class.getName()).log(Level.SEVERE, null, ex);
            throw new SqlAppsException(ex);
        } finally {
            try {
                closeResultSet();
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(AdmAplicacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion();
        }
        return lista.listIterator();
    }

    public AdmUsuarioDetalleBean getAdmUsuarioDetalle(int usuario_id, EDriver driver, EApps app) throws SqlAppsException {

        AdmUsuarioDetalleBean ab = new AdmUsuarioDetalleBean();

        getConexion(driver, app);

        sql = "SELECT a.usuario_id\n"
                + ",a.usuario\n"
                + ",IFNULL(a.nombre,'') nombre\n"
                + ",IFNULL(a.apellido_paterno,'') apellido_paterno\n"
                + ",IFNULL(a.apellido_materno,'') apellido_materno\n"
                + ",a.dependencia_id\n"
                + ",a.direccion_id\n"
                + ",a.departamento_id\n"
                + ",a.correo\n"
                + ",IFNULL(a.descripcion,'') descripcion\n"
                + ",IFNULL(DATE_FORMAT(a.fecha_termino,'%d/%m/%Y'),'') fecha_termino\n"
                + ",IFNULL(DATE_FORMAT(a.fecha_ultimo_acceso,'%d/%m/%Y %M:%i:%s'),'') fecha_ultimo_acceso\n"
                + ",IFNULL(DATE_FORMAT(a.fecha_password,'%d/%m/%Y'),'') fecha_password\n"
                + ",IFNULL(DATE_FORMAT(a.fecha_creacion,'%d/%m/%Y'),'') fecha_creacion\n"
                + ",a.creado_por\n"
                + ",IFNULL(DATE_FORMAT(a.fecha_modificacion,'%d/%m/%Y'),'') fecha_modificacion\n"
                + ",a.modificado_por\n"
                + ",a.expirado\n"
                + ",IFNULL(a.dirip,'') dirip\n"
                + ",IFNULL(a.oficina,'') oficina\n"
                + ",IFNULL(a.adscripcion,'') adscripcion\n"
                + ",b.nombre as dependencia\n"
                + ",c.nombre as direccion\n"
                + ",d.nombre as departamento\n"
                + "FROM adm_usuarios a\n"
                + "LEFT JOIN adm_dependencias b\n"
                + "ON a.dependencia_id = b.dependencia_id\n"
                + "LEFT JOIN adm_direcciones c\n"
                + "ON a.direccion_id = c.direccion_id\n"
                + "LEFT JOIN adm_departamentos d\n"
                + "ON a.departamento_id = d.departamento_id\n"
                + "WHERE usuario_id = ?";

        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setInt(1, usuario_id);

            rs = ps.executeQuery();

            while (rs.next()) {

                ab.setUsuario_id(rs.getInt("usuario_id"));
                ab.setUsuario(rs.getString("usuario"));
                ab.setNombre(rs.getString("nombre"));
                ab.setApellido_paterno(rs.getString("apellido_paterno"));
                ab.setApellido_materno(rs.getString("apellido_materno"));
                ab.setDependencia_id(rs.getInt("dependencia_id"));
                ab.setDireccion_id(rs.getInt("direccion_id"));
                ab.setDepartamento_id(rs.getInt("departamento_id"));
                ab.setCorreo(rs.getString("correo"));
                ab.setDescripcion(rs.getString("descripcion"));
                ab.setFecha_inicio(rs.getString("fecha_inicio"));
                ab.setFecha_termino(rs.getString("fecha_termino"));
                ab.setFecha_ultimo_acceso(rs.getString("fecha_ultimo_acceso"));
                ab.setFecha_password(rs.getString("fecha_password"));
                ab.setFecha_creacion(rs.getString("fecha_creacion"));
                ab.setCreado_por(rs.getInt("creado_por"));
                ab.setFecha_modificacion(rs.getString("fecha_modificacion"));
                ab.setModificado_por(rs.getInt("modificado_por"));
                ab.setExpirado(rs.getString("expirado"));
                ab.setDirip(rs.getString("dirip"));
                ab.setOficina(rs.getString("oficina"));
                ab.setAdscripcion(rs.getString("adscripcion"));
                ab.setDependencia(rs.getString("dependencia"));
                ab.setDireccion(rs.getString("direccion"));
                ab.setDepartamento(rs.getString("departamento"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(AdmAplicacion.class.getName()).log(Level.SEVERE, null, ex);
            throw new SqlAppsException(ex);
        } finally {
            try {
                closeResultSet();
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(AdmAplicacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion();
        }
        return ab;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
