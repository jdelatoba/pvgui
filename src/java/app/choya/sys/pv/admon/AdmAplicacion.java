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
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import app.choya.sys.pv.dao.EApps;
import app.choya.sys.pv.dao.EDriver;
import app.choya.sys.pv.dao.GenericDAO;
import app.choya.sys.pv.utils.SqlAppsException;
import java.util.LinkedList;

/**
 *
 * @author carlos.rembao
 */
public class AdmAplicacion extends GenericDAO implements AdmAplicacionI {

    private String sql = "";
    private ResultSet rs = null;
    private PreparedStatement ps = null;
    private Statement stmt = null;
    ArrayList<String> lista_campo = new ArrayList<>();
    private String[] aCampo = {"APLICACION_ID", "NOMBRE", "DIR_FORMAS", "DIR_REPORTES", "USUARIO_APP", "FECHA_CREACION", "CREADO_POR", "FECHA_MODIFICACION", "MODIFICADO_POR", "DIR_BIN", "DIR_SQL"};

    public AdmAplicacion() {
        for (String a : aCampo) {
            lista_campo.add(a);
        }
    }

    @Override
    public int setAdmAplicacion(AdmAplicacionBean ab) throws SqlAppsException {
        int resultado = 0;
        getConexion(EDriver.ORACLE, EApps.PV);

        sql = "INSERT INTO ADM_APLICACIONES \n"
                + "(APLICACION_ID\n"
                + ",NOMBRE\n"
                + ",DIR_FORMAS\n"
                + ",DIR_REPORTES\n"
                + ",USUARIO_APP\n"
                + ",FECHA_CREACION\n"
                + ",CREADO_POR\n"
                + ",FECHA_MODIFICACION\n"
                + ",MODIFICADO_POR\n"
                + ",DIR_BIN\n"
                + ",DIR_SQL)\n"
                + "VALUES (ADM_APLICACIONES_S.NEXTVAL\n"
                + ",?\n"
                + ",?\n"
                + ",?\n"
                + ",?\n"
                + ",SYSDATE\n"
                + ",?\n"
                + ",SYSDATE\n"
                + ",?\n"
                + ",?\n"
                + ",?)";
        try {

            ps = getPrepareStatementWithKey(sql, "APLICACION_ID");

            ps.setString(1, ab.getNombre());
            ps.setString(2, ab.getDir_formas());
            ps.setString(3, ab.getDir_reportes());
            ps.setString(4, ab.getUsuario_app());
            ps.setInt(5, ab.getCreado_por());
            ps.setInt(6, ab.getModificado_por());
            ps.setString(7, ab.getDir_bin());
            ps.setString(8, ab.getDir_sql());

            if (ps.executeUpdate() > 0) {

                // getGeneratedKeys() regresa el valor de la llave generado por la secuencia
                // en nuestro caso APLICACION_ID
                rs = ps.getGeneratedKeys();

                if (rs != null && rs.next()) {

                    //asignamos el valor de APLICACION_ID que se genero con la secuencia
                    resultado = rs.getInt(1);
                }

            }

            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            Logger.getLogger(AdmAplicacion.class.getName()).log(Level.SEVERE, null, ex);
            throw new SqlAppsException(ex);
        } finally {
            try {
                closeResultSet();
                closePStmt();
                closeConexion();

            } catch (SQLException ex) {
                Logger.getLogger(AdmUsuarios.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                throw new SqlAppsException(ex);
            }

        }

        return resultado;
    }

    @Override
    public int updateAdmAplicacion(AdmAplicacionBean ab) throws SqlAppsException {

        int resultado = 0;
        getConexion(EDriver.ORACLE, EApps.PV);

        sql = "UPDATE ADM_APLICACIONES \n"
                + "SET NOMBRE = ?\n"
                + ",DIR_FORMAS = ?\n"
                + ",DIR_REPORTES = ?\n"
                + ",USUARIO_APP = ?\n"
                + ",FECHA_MODIFICACION = SYSDATE\n"
                + ",MODIFICADO_POR = ?\n"
                + ",DIR_BIN = ?\n"
                + ",DIR_SQL = ?\n"
                + "WHERE APLICACION_ID = ?";

        try {
            ps = getPrepareStatement(sql);
            ps.setString(1, ab.getNombre());
            ps.setString(2, ab.getDir_formas());
            ps.setString(3, ab.getDir_reportes());
            ps.setString(4, ab.getUsuario_app());
            ps.setInt(5, ab.getModificado_por());
            ps.setString(6, ab.getDir_bin());
            ps.setString(7, ab.getDir_sql());
            ps.setInt(8, ab.getAplicacion_id());

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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AdmAplicacionBean getAdmAplicacionById(int aplicacion_id) throws SqlAppsException {
        AdmAplicacionBean ab = new AdmAplicacionBean();

        getConexion(EDriver.ORACLE, EApps.PV);

        sql = "SELECT APLICACION_ID\n"
                + ",NOMBRE\n"
                + ",DIR_FORMAS\n"
                + ",DIR_REPORTES\n"
                + ",USUARIO_APP\n"
                + ",TO_CHAR(FECHA_CREACION,'DD/MM/YYYY HH24:MI:SS') AS FECHA_CREACION\n"
                + ",CREADO_POR\n"
                + ",TO_CHAR(FECHA_MODIFICACION,'DD/MM/YYYY HH24:MI:SS') AS FECHA_MODIFICACION\n"
                + ",MODIFICADO_POR\n"
                + ",DIR_BIN\n"
                + ",DIR_SQL\n"
                + "FROM ADM_APLICACIONES\n"
                + "WHERE APLICACION_ID = ?";

        try {
            ps = getPrepareStatement(sql);
            ps.setInt(1, aplicacion_id);

            rs = ps.executeQuery();

            while (rs.next()) {
                ab.setAplicacion_id(rs.getInt(1));
                ab.setNombre(rs.getString(2));
                ab.setDir_formas(rs.getString(3));
                ab.setDir_reportes(rs.getString(4));
                ab.setUsuario_app(rs.getString(5));
                ab.setFecha_creacion(rs.getString(6));
                ab.setCreado_por(rs.getInt(7));
                ab.setFecha_modificacion(rs.getString(8));
                ab.setModificado_por(rs.getInt(9));
                ab.setDir_bin(rs.getString(10));
                ab.setDir_sql(rs.getString(11));

            }
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    }

    @Override
    public ListIterator<AdmAplicacionBean> getAdmAplicacion() throws SqlAppsException {

        LinkedList<AdmAplicacionBean> lista = new LinkedList<>();
        getConexion(EDriver.ORACLE, EApps.PV);

        sql = "SELECT APLICACION_ID\n"
                + ", NOMBRE\n"
                + ", DIR_FORMAS\n"
                + ", DIR_REPORTES\n"
                + ", USUARIO_APP\n"
                + ", TO_CHAR(FECHA_CREACION,'DD/MM/YYYY HH24:MI:SS') FECHA_CREACION\n"
                + ", CREADO_POR\n"
                + ", TO_CHAR(FECHA_MODIFICACION,'DD/MM/YYYY HH24:MI:SS') FECHA_MODIFICACION\n"
                + ", MODIFICADO_POR\n"
                + ", DIR_BIN\n"
                + ", DIR_SQL\n"
                + "FROM ADM_APLICACIONES ORDER BY 2;";

        return lista.listIterator();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<AdmAplicacionBean> getAdmAplicacion(String ordenaPor) throws SqlAppsException {
        LinkedList<AdmAplicacionBean> lista = new LinkedList<>();

        int ordenBy = lista_campo.indexOf(ordenaPor);
        //validar ordenamiento por default por NOMBRE = 1 
        if (ordenBy == -1) {
            ordenBy = 1;
        } else {
            ordenBy++;
        }
        getConexion(EDriver.ORACLE, EApps.PV);

        sql = "SELECT APLICACION_ID\n"
                + ", NOMBRE\n"
                + ", DIR_FORMAS\n"
                + ", DIR_REPORTES\n"
                + ", USUARIO_APP\n"
                + ", TO_CHAR(FECHA_CREACION,'DD/MM/YYYY HH24:MI:SS') FECHA_CREACION\n"
                + ", CREADO_POR\n"
                + ", TO_CHAR(FECHA_MODIFICACION,'DD/MM/YYYY HH24:MI:SS') FECHA_MODIFICACION\n"
                + ", MODIFICADO_POR\n"
                + ", DIR_BIN\n"
                + ", DIR_SQL\n"
                + "FROM ADM_APLICACIONES ORDER BY " + ordenBy;

        try {

            stmt = getStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                AdmAplicacionBean ab = new AdmAplicacionBean();
                ab.setAplicacion_id(rs.getInt("APLICACION_ID"));
                ab.setNombre(rs.getString("NOMBRE"));
                ab.setDir_formas(rs.getString("DIR_FORMAS"));
                ab.setDir_reportes(rs.getString("DIR_REPORTES"));
                ab.setUsuario_app(rs.getString("USUARIO_APP"));
                ab.setFecha_creacion(rs.getString("FECHA_CREACION"));
                ab.setCreado_por(rs.getInt("CREADO_POR"));
                ab.setFecha_modificacion(rs.getString("FECHA_MODIFICACION"));
                ab.setModificado_por(rs.getInt("MODIFICADO_POR"));
                ab.setDir_bin(rs.getString("DIR_BIN"));
                ab.setDir_sql(rs.getString("DIR_SQL"));

                lista.add(ab);

            }

        } catch (SQLException ex) {
            Logger.getLogger(AdmAplicacion.class.getName()).log(Level.SEVERE, null, ex);
            throw new SqlAppsException(ex);
        } finally {
            try {
                closeResultSet();
                closeStmt();

            } catch (SQLException ex) {
                Logger.getLogger(AdmAplicacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion();
        }

        //throw new UnsupportedOperationException("Not
        return lista.listIterator();
    }

    @Override
    public ListIterator<AdmAplicacionBean> getAdmAplicacion(String ordenaPor, int desde, int hasta) throws SqlAppsException {
        LinkedList<AdmAplicacionBean> lista = new LinkedList<>();

        int ordenBy = lista_campo.indexOf(ordenaPor);
        //validar ordenamiento por default por NOMBRE = 1 
        if (ordenBy == -1) {
            ordenBy = 1;
        } else {
            ordenBy++;
        }
        getConexion(EDriver.ORACLE, EApps.PV);

        sql = "SELECT APLICACION_ID\n"
                + ", NOMBRE\n"
                + ", DIR_FORMAS\n"
                + ", DIR_REPORTES\n"
                + ", USUARIO_APP\n"
                + ", TO_CHAR(FECHA_CREACION,'DD/MM/YYYY HH24:MI:SS') FECHA_CREACION\n"
                + ", CREADO_POR\n"
                + ", TO_CHAR(FECHA_MODIFICACION,'DD/MM/YYYY HH24:MI:SS') FECHA_MODIFICACION\n"
                + ", MODIFICADO_POR\n"
                + ", DIR_BIN\n"
                + ", DIR_SQL\n"
                + "FROM (SELECT a.*, ROWNUM rnum FROM \n"
                + "(SELECT * FROM ADM_APLICACIONES ORDER BY 1) a \n"
                + "WHERE ROWNUM <= ?) where rnum >= ? ORDER BY "+ordenBy;

        try {
            ps = getPrepareStatement(sql);
            ps.setInt(1, hasta);
            ps.setInt(2, desde);
            
            rs = ps.executeQuery();

            while (rs.next()) {
                AdmAplicacionBean ab = new AdmAplicacionBean();
                ab.setAplicacion_id(rs.getInt("APLICACION_ID"));
                ab.setNombre(rs.getString("NOMBRE"));
                ab.setDir_formas(rs.getString("DIR_FORMAS"));
                ab.setDir_reportes(rs.getString("DIR_REPORTES"));
                ab.setUsuario_app(rs.getString("USUARIO_APP"));
                ab.setFecha_creacion(rs.getString("FECHA_CREACION"));
                ab.setCreado_por(rs.getInt("CREADO_POR"));
                ab.setFecha_modificacion(rs.getString("FECHA_MODIFICACION"));
                ab.setModificado_por(rs.getInt("MODIFICADO_POR"));
                ab.setDir_bin(rs.getString("DIR_BIN"));
                ab.setDir_sql(rs.getString("DIR_SQL"));

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

        //throw new UnsupportedOperationException("Not
        return lista.listIterator();
    }

}
