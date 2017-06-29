/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.admon;

import java.util.ListIterator;
import app.choya.sys.pv.dao.EApps;
import app.choya.sys.pv.dao.EDriver;
import app.choya.sys.pv.dao.GenericDAO;
import app.choya.sys.pv.utils.SqlAppsException;
import java.util.LinkedList;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author carlos.rembao
 */
public class ResponsabilidadController extends GenericDAO implements ResponsabilidadI {

    public ResponsabilidadController() {
    }

    private String sql = "";
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public ListIterator<ResponsabilidadUsuarioBean> getListaReponsabilidadByUsuarioId(
            int usuario_id, EDriver driver, EApps app) throws SqlAppsException {

        LinkedList<ResponsabilidadUsuarioBean> lista = new LinkedList<>();

        getConexion(driver, app);

        sql = "SELECT A.USUARIO_ID\n"
                + ",A.RESPONSABILIDAD_ID\n"
                + ",A.FECHA_INICIO\n"
                + ",A.FECHA_TERMINO\n"
                + ",B.APLICACION_ID\n"
                + ",B.NOMBRE\n"
                + ",B.MENU_ID\n"
                + ",B.GRUPO_REPORTE_ID\n"
                + "FROM ADM_USUARIOS_RESPONSABILIDAD A\n"
                + ",V_RESPONSABILIDAD_VALIDA B\n"
                + "WHERE A.RESPONSABILIDAD_ID = B.RESPONSABILIDAD_ID\n"
                + "AND A.FECHA_INICIO <= SYSDATE\n"
                + "AND NVL(FECHA_TERMINO,'31/12/20') > SYSDATE\n"
                + "AND A.USUARIO_ID = ?\n"
                + "ORDER BY A.RESPONSABILIDAD_ID";

        try {
            //prepara y precompila la sentencia
            ps = getPrepareStatement(sql);
            //asigna valor a las variable enlazadas
            ps.setInt(1, usuario_id);

            //ejecuta query
            rs = ps.executeQuery();
            while (rs.next()) {

                ResponsabilidadUsuarioBean rb = new ResponsabilidadUsuarioBean();
                rb.setUsuario_id(rs.getInt("USUARIO_ID"));
                rb.setResponsabilidad_id(rs.getInt("RESPONSABILIDAD_ID"));
                rb.setFecha_inicio(rs.getString("FECHA_INICIO"));
                rb.setFecha_termino(rs.getString("FECHA_TERMINO"));
                rb.setAplicacion_id(rs.getInt("APLICACION_ID"));
                rb.setNombre(rs.getString("NOMBRE"));
                rb.setMenu_id(rs.getInt("MENU_ID"));
                rb.setGrupo_reporte_id(rs.getInt("GRUPO_REPORTE_ID"));
                lista.add(rb);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ResponsabilidadController.class.getName()).log(Level.SEVERE, null, ex);
            throw new SqlAppsException(ex);
        } finally {
            try {
                closeResultSet();
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(ResponsabilidadController.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion();
        }

        return lista.listIterator();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AdmResponsabilidadBean getInfoResponsabilidad(
            int responsabilidad_id
            , EDriver driver
            , EApps app) throws SqlAppsException {

        AdmResponsabilidadBean ab = new AdmResponsabilidadBean();
        getConexion(driver, app);

        sql = "SELECT RESPONSABILIDAD_ID\n"
                + ",APLICACION_ID\n"
                + ",NOMBRE\n"
                + ",MENU_ID\n"
                + ",GRUPO_REPORTE_ID\n"
                + "FROM ADM_RESPONSABILIDADES\n"
                + "WHERE RESPONSABILIDAD_ID = 1";

        try {
            //ps = getPrepareStatement(sql);
            //ps.setInt(1, responsabilidad_id);
            Statement stmt = getStatement();
            rs = stmt.executeQuery(sql);

            //rs = ps.executeQuery();
            
            while(rs.next()){
                ab.setResponsabilidad_id(rs.getInt(1));
                ab.setAplicacion_id(rs.getInt(2));
                ab.setNombre(rs.getString(3));
                ab.setMenu_id(rs.getInt(4));
                ab.setGrupo_reporte_id(rs.getInt(5));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResponsabilidadController.class.getName()).log(Level.SEVERE, null, ex);
            throw new SqlAppsException(ex);
        }finally{
            try {
                closeResultSet();
                closeStmt();
            } catch (SQLException ex) {
                Logger.getLogger(ResponsabilidadController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
        }

        return ab;
    }

}
