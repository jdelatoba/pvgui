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

import app.choya.sys.pv.utils.SqlAppsException;

/**
 *
 * @author Icosio
 */
public class AdmFuncion extends GenericDAO implements AdmFuncionI {

    private String sql = "";
    private ResultSet rs = null;
    private PreparedStatement ps = null;
    private Statement stmt = null;

    @Override
    public int setAdmFuncion(AdmFuncionBean fb, EDriver driver, EApps app) throws SqlAppsException {

        int resultado = 0;
        getConexion(driver, app);

        sql = "INSERT INTO adm_funciones \n"
                + "(funcion_id\n"
                + ",nombre \n"
                + ",aplicacion_id \n"
                + ",parametros\n"
                + ",forma\n"
                + ",fecha_creacion\n"
                + ",creado_por\n"
                + ",fecha_modificacion\n"
                + ",modificado_por)\n"
                + "VALUES (null\n"
                + ",?\n"
                + ",?\n"
                + ",?\n"
                + ",?\n"
                + ",current_timestamp()\n"
                + ",?\n"
                + ",current_timestamp()\n"
                + ",?)";
        try {

            ps = getPrepareStatementWithKey(sql);

            ps.setString(1, fb.getNombre());
            ps.setInt(2, fb.getAplicacion_id());
            ps.setString(3, fb.getParametros());
            ps.setString(4, fb.getForma());
            ps.setInt(5, fb.getCreado_por());
            ps.setInt(6, fb.getModificado_por());

            if (ps.executeUpdate() > 0) {
                rs = ps.getGeneratedKeys();

                if (rs != null && rs.next()) {
                    resultado = rs.getInt(1);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdmFuncion.class.getName()).log(Level.SEVERE, null, ex);
            throw new SqlAppsException(ex);
        } finally {
            try {
                closeResultSet();
                closePStmt();
                closeConexion();

            } catch (SQLException ex) {
                Logger.getLogger(AdmFuncion.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                throw new SqlAppsException(ex);
            }

        }

        return resultado;

    }

    @Override
    public int updateAdmFuncion(AdmFuncionBean fb, EDriver driver, EApps app) throws SqlAppsException {

        int resultado = 0;
        getConexion(driver, app);

        sql = "UPDATE adm_funciones \n"
                + " SET nombre = ?\n"
                + ",aplicacion_id = ?\n"
                + ",parametros = ?\n"
                + ",forma = ?\n"
                + ",fecha_modificacion = current_timestamp()\n"
                + ",modificado_por = ?\n"
                + "WHERE funcion_id = ?";

        try {
            ps = getPrepareStatement(sql);

            ps.setString(1, fb.getNombre());
            ps.setInt(2, fb.getAplicacion_id());
            ps.setString(3, fb.getParametros());
            ps.setString(4, fb.getForma());
            ps.setInt(5, fb.getModificado_por());
            ps.setInt(6, fb.getFuncion_id());

            resultado = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AdmFuncion.class.getName()).log(Level.SEVERE, null, ex);
            throw new SqlAppsException(ex);
        } finally {
            try {
                closePStmt();
                closeConexion();

            } catch (SQLException ex) {
                Logger.getLogger(AdmFuncion.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return resultado;
    }

    @Override
    public ListIterator<AdmFuncionBean> getAdmFuncion(EDriver driver, EApps app) throws SqlAppsException {

        LinkedList<AdmFuncionBean> lista = new LinkedList<>();

        getConexion(driver, app);

        sql = "SELECT  funcion_id\n"
                + ",nombre\n"
                + ",aplicacion_id\n"
                + ",parametros\n"
                + ",forma\n"
                + ",DATE_FORMAT(fecha_creacion, '%d/%m/%Y %H:%i:%s') fecha_creacion\n"
                + ",creado_por\n"
                + ",DATE_FORMAT(fecha_modificacion, '%d/%m/%Y %H:%i:%s') fecha_modificacion\n"
                + ",modificado_por\n"
                + "FROM adm_funciones";

        try {

            stmt = getStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                AdmFuncionBean fb = new AdmFuncionBean();

                fb.setFuncion_id(rs.getInt("funcion_id"));
                fb.setNombre(rs.getString("nombre"));
                fb.setAplicacion_id(rs.getInt("aplicacion_id"));
                fb.setParametros(rs.getString("parametros"));
                fb.setForma(rs.getString("forma"));
                fb.setFecha_creacion(rs.getString("fecha_creacion"));
                fb.setCreado_por(rs.getInt("creado_por"));
                fb.setFecha_modificacion(rs.getString("fecha_modificacion"));
                fb.setModificado_por(rs.getInt("modificado_por"));

                lista.add(fb);

            }

        } catch (SQLException ex) {
            Logger.getLogger(AdmResponsabilidad.class.getName()).log(Level.SEVERE, null, ex);
            throw new SqlAppsException(ex);
        } finally {
            try {
                closeResultSet();
                closePStmt();
                closeConexion();
            } catch (SQLException ex) {
                Logger.getLogger(AdmResponsabilidad.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                throw new SqlAppsException(ex);
            }
        }

        return lista.listIterator();
    }
    
    
    @Override
    public AdmFuncionBean getAdmFuncion(int funcion_id, EDriver driver, EApps app) throws SqlAppsException {

        AdmFuncionBean fb = new AdmFuncionBean();

        getConexion(driver, app);

        sql = "SELECT  funcion_id\n"
                + ",nombre\n"
                + ",aplicacion_id\n"
                + ",parametros\n"
                + ",forma\n"
                + ",DATE_FORMAT(fecha_creacion, '%d/%m/%Y %H:%i:%s') fecha_creacion\n"
                + ",creado_por\n"
                + ",DATE_FORMAT(fecha_modificacion, '%d/%m/%Y %H:%i:%s') fecha_modificacion\n"
                + ",modificado_por\n"
                + "FROM adm_funciones\n"
                + "WHERE funcion_id = ?";

        try {

            ps = getPrepareStatement(sql);
            ps.setInt(1, funcion_id);
            
            rs = ps.executeQuery();

            while (rs.next()) {

                

                fb.setFuncion_id(rs.getInt("funcion_id"));
                fb.setNombre(rs.getString("nombre"));
                fb.setAplicacion_id(rs.getInt("aplicacion_id"));
                fb.setParametros(rs.getString("parametros"));
                fb.setForma(rs.getString("forma"));
                fb.setFecha_creacion(rs.getString("fecha_creacion"));
                fb.setCreado_por(rs.getInt("creado_por"));
                fb.setFecha_modificacion(rs.getString("fecha_modificacion"));
                fb.setModificado_por(rs.getInt("modificado_por"));

                

            }

        } catch (SQLException ex) {
            Logger.getLogger(AdmResponsabilidad.class.getName()).log(Level.SEVERE, null, ex);
            throw new SqlAppsException(ex);
        } finally {
            try {
                closeResultSet();
                closePStmt();
                closeConexion();
            } catch (SQLException ex) {
                Logger.getLogger(AdmResponsabilidad.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                throw new SqlAppsException(ex);
            }
        }

        return fb;
    }

}
