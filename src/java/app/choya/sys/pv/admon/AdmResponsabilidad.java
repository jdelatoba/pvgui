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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Icosio Clase AdmResponsabilidad donde se implementan los metodos para
 * la explotacion de la informacion sobre la tabla ADM_RESPONSABILIDADES
 * insercion, actualizacion, consulta y borrado de registros.
 */
public class AdmResponsabilidad extends GenericDAO implements AdmResponsabilidadI {

    private String sql = "";
    private ResultSet rs = null;
    private PreparedStatement ps = null;
    private Statement stmt = null;
    private ArrayList<AdmResponsabilidadBean> responsabilidades = new ArrayList<>();

    @Override
    public int setAdmResponsabilidad(AdmResponsabilidadBean rb, EDriver driver, EApps app) throws SqlAppsException {

        int resultado = 0;
        getConexion(driver, app);

        sql = "INSERT INTO adm_responsabilidades \n"
                + "( responsabilidad_id\n"
                + ",aplicacion_id \n"
                + ",nombre \n"
                + ",descripcion\n"
                + ",menu_id\n"
                + ",fecha_inicio\n"
                + ",fecha_termino\n"
                + ",fecha_creacion\n"
                + ",creado_por\n"
                + ",fecha_modificacion\n"
                + ",modificado_por\n"
                + ",grupo_reporte_id)\n"
                + "VALUES (\n"
                + "null\n"
                + ",?\n"
                + ",?\n"
                + ",?\n"
                + ",?\n"
                + ",current_timestamp()\n"
                + ",?\n"
                + ",current_timestamp()\n"
                + ",?\n"
                + ",current_timestamp()\n"
                + ",?\n"
                + ",?\n)";

        try {

            ps = getPrepareStatementWithKey(sql);

            ps.setInt(1, rb.getAplicacion_id());
            ps.setString(2, rb.getNombre());
            ps.setString(3, rb.getDescripcion());
            ps.setInt(4, rb.getMenu_id());
            ps.setString(5, rb.getFecha_termino());
            ps.setInt(6, rb.getCreado_por());
            ps.setInt(7, rb.getModificado_por());
            ps.setInt(8, rb.getGrupo_reporte_id());

            resultado = ps.executeUpdate();

            if (resultado > 0) {

                rs = ps.getGeneratedKeys();
                while (rs != null && rs.next()) {
                    resultado = rs.getInt(1);
                }

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

        return resultado;

    }

    @Override
    public int updateAdmResponsabilidad(AdmResponsabilidadBean rb, EDriver driver, EApps app) throws SqlAppsException {
        int resultado;

        getConexion(driver, app);

        sql = "UPDATE adm_responsabilidades"
                + "SET aplicacion_id = ?\n"
                + ",nombre = ?\n"
                + ",descripcion= ?\n"
                + ",menu_id= ?\n"
                + ",fecha_inicio= ?\n"
                + ",fecha_termino= ?\n"
                + ",fecha_creacion= ?\n"
                + ",creado_por= ?\n"
                + ",fecha_modificacion = current_timestamp()\n"
                + ",modificado_por= ?\n"
                + ",grupo_reporte_id = ?\n"
                + "WHERE responsabilidad_id = ?";
        try {

            ps = getPrepareStatement(sql);

            ps.setInt(1, rb.getAplicacion_id());
            ps.setString(2, rb.getNombre());
            ps.setString(3, rb.getDescripcion());
            ps.setInt(4, rb.getMenu_id());
            ps.setString(5, rb.getFecha_inicio());
            ps.setString(6, rb.getFecha_termino());
            ps.setString(7, rb.getFecha_creacion());
            ps.setInt(8, rb.getCreado_por());
            ps.setInt(9, rb.getModificado_por());
            ps.setInt(10, rb.getGrupo_reporte_id());
            ps.setInt(11, rb.getResponsabilidad_id());

            resultado = ps.executeUpdate();

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
        return resultado;
    }

    @Override
    public ListIterator<AdmResponsabilidadBean> getAdmResponsabilidad(EDriver driver, EApps app) throws SqlAppsException {

        LinkedList<AdmResponsabilidadBean> lista = new LinkedList<>();

        getConexion(driver, app);

        sql = "SELECT  responsabilidad_id\n"
                + ",aplicacion_id\n"
                + ",nombre\n"
                + ",descripcion\n"
                + ",menu_id\n"
                + ",DATE_FORMAT(fecha_inicio,'%d/%m/Y %H:%i:%s') fecha_inicio\n"
                + ",DATE_FORMAT(fecha_termino,'%d/%m/Y %H:%i:%s') fecha_termino\n"
                + ",DATE_FORMAT(fecha_creacion,'%d/%m/Y %H:%i:%s') fecha_creacion\n"
                + ",creado_por\n"
                + ",DATE_FORMAT(fecha_modificacion,'%d/%m/Y %H:%i:%s') fecha_modificacion\n"
                + ",modificado_por\n"
                + ",grupo_reporte_id\n"
                + "FROM adm_responsabilidades";

        try {

            stmt = getStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                AdmResponsabilidadBean rb = new AdmResponsabilidadBean();

                rb.setResponsabilidad_id(rs.getInt("responsabilidad_id"));
                rb.setAplicacion_id(rs.getInt("aplicacion_id"));
                rb.setNombre(rs.getString("nombre"));
                rb.setDescripcion(rs.getString("descripcion"));
                rb.setMenu_id(rs.getInt("menu_id"));
                rb.setFecha_inicio(rs.getString("fecha_inicio"));
                rb.setFecha_termino(rs.getString("fecha_termino"));
                rb.setFecha_creacion(rs.getString("fecha_creacion"));
                rb.setCreado_por(rs.getInt("creado_por"));
                rb.setFecha_modificacion(rs.getString("fecha_modificacion"));
                rb.setModificado_por(rs.getInt("modificado_por"));
                rb.setGrupo_reporte_id(rs.getInt("grupo_reporte_id"));

                lista.add(rb);

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
    public ListIterator<AdmResponsabilidadBean> getAdmResponsabilidadUsuario(int usuario_id, EDriver driver, EApps app) throws SqlAppsException {

        LinkedList<AdmResponsabilidadBean> lista = new LinkedList<>();
        getConexion(driver, app);

       sql = "SELECT  r.responsabilidad_id\n"
                + ",r.nombre\n"
               + ",r.menu_id\n"
                + "FROM adm_responsabilidades r, adm_usuarios_responsabilidad u\n"
                + "WHERE  r.responsabilidad_id = u.responsabilidad_id\n"
                + "AND ifnull(r.fecha_termino,NOW()) >= STR_TO_DATE(NOW(),'%Y-%m-%d')\n"
                + "AND ifnull(u.fecha_termino,NOW()) >= STR_TO_DATE(NOW(),'%Y-%m-%d')\n"
                + "AND u.usuario_id = ?";

        try {

            ps = getPrepareStatement(sql);
            ps.setInt(1, usuario_id);

            rs = ps.executeQuery();

            while (rs.next()) {

                AdmResponsabilidadBean rb = new AdmResponsabilidadBean();

                rb.setResponsabilidad_id(rs.getInt("responsabilidad_id"));
                rb.setNombre(rs.getString("nombre"));
                rb.setMenu_id(rs.getInt("menu_id"));
                
                
                lista.add(rb);
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
    public ListIterator<AdmResponsabilidadBean> getAdmResponsabilidadFiltro(int usuario_id, String valor, EDriver driver, EApps app) throws SqlAppsException {
        LinkedList<AdmResponsabilidadBean> lista = new LinkedList<>();
        getConexion(EDriver.ORACLE, EApps.PV);

        sql = "SELECT  r.responsabilidad_id\n"
                + ",r.nombre\n"
                + "from adm_responsabilidades r, adm_usuarios_responsabilidad u\n"
                + "where  r.responsabilidad_id = u.responsabilidad_id\n"
                + "and TO_DATE(nvl(r.fecha_termino,TO_DATE(SYSDATE,'DD/MM/YY')),'DD/MM/YY') <= to_dATE(SYSDATE,'DD/MM/YY')\n"
                + "and u.usuario_id = ?"
                + "and upper(r.nombre) like ?";

        try {
            valor = "%" + valor + "%";
            ps = getPrepareStatement(sql);
            ps.setInt(1, usuario_id);
            ps.setString(2, valor);

            rs = ps.executeQuery();

            while (rs.next()) {

                AdmResponsabilidadBean rb = new AdmResponsabilidadBean();

                rb.setResponsabilidad_id(rs.getInt("responsabilidad_id"));
                rb.setNombre(rs.getString("nombre"));

                lista.add(rb);
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    

    public ArrayList<AdmResponsabilidadBean> getResponsabilidades() {
        return this.responsabilidades;
    }

    public void setResponsabilidades(ArrayList<AdmResponsabilidadBean> responsabilidades) {
        this.responsabilidades = responsabilidades;
    }

    

}
