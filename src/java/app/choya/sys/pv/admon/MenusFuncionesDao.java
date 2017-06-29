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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import app.choya.sys.pv.dao.EApps;
import app.choya.sys.pv.dao.EDriver;
import app.choya.sys.pv.dao.GenericDAO;

/**
 *
 * @author Usuario
 */
public class MenusFuncionesDao extends GenericDAO {

    public List<AdmMenuFuncionBean> getMenuList(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<AdmMenuFuncionBean> menus = null;

        try {
            getConexion(EDriver.ORACLE, EApps.PV);
            ps = getPrepareStatement("select *\n"
                    + "from adm_menu_funciones f\n"
                    + "where f.menu_id=?\n"
                    + "order by f.menu_secuencia\n");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            menus = new ArrayList();
            while (rs.next()) {
                AdmMenuFuncionBean menu = new AdmMenuFuncionBean();
                menu.setAccion_id(rs.getInt("accion_id"));
                menu.setCreado_por(rs.getInt("creado_por"));
                menu.setFecha_creacion(rs.getString("fecha_creacion"));
                menu.setFecha_modificacion(rs.getString("fecha_modificacion"));
                menu.setMenu_id(rs.getInt("menu_id"));
                menu.setMenu_secuencia(rs.getInt("menu_secuencia"));
                menu.setModificado_por(rs.getInt("modificado_por"));
                menu.setPrompt(rs.getString("prompt"));
                menu.setTipo_accion(rs.getString("tipo_accion"));
                menus.add(menu);
                if (menu.getTipo_accion().equals("M")) {
                    MenusFuncionesDao mfd=new MenusFuncionesDao();
                    List<AdmMenuFuncionBean> menus2 = mfd.getMenuList(menu.getAccion_id());
                    for (AdmMenuFuncionBean fm : menus2) {
                        menus.add(fm);
                    }
                }
            }
            System.out.println("getMenuList(): " + id);
        } catch (SQLException e) {
            System.out.println("error getMenuList() " + id);
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return menus;
    }

    public List<AdmMenuFuncionBean> getMenu(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<AdmMenuFuncionBean> menus = null;

        try {
            getConexion(EDriver.ORACLE, EApps.PV);
            ps = getPrepareStatement("select mf.*,nvl(f.forma,'#') forma,f.nombre fnombre,m.nombre mnombre,m.descripcion mdesc\n"
                    + "from adm_menu_funciones mf\n"
                    + "left join adm_funciones f on f.funcion_id=mf.accion_id\n"
                    + "left join adm_menus m on m.menu_id=mf.accion_id\n"
                    + "where mf.menu_id=?\n"
                    + "order by mf.menu_secuencia");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            menus = new ArrayList();
            while (rs.next()) {
                AdmMenuFuncionBean menu = new AdmMenuFuncionBean();
                menu.setAccion_id(rs.getInt("accion_id"));
                menu.setCreado_por(rs.getInt("creado_por"));
                menu.setFecha_creacion(rs.getString("fecha_creacion"));
                menu.setFecha_modificacion(rs.getString("fecha_modificacion"));
                menu.setMenu_id(rs.getInt("menu_id"));
                menu.setMenu_secuencia(rs.getInt("menu_secuencia"));
                menu.setModificado_por(rs.getInt("modificado_por"));
                menu.setPrompt(rs.getString("prompt"));
                menu.setTipo_accion(rs.getString("tipo_accion"));
                menu.setDescripcion(rs.getString("descripcion"));

                AdmFuncionBean f = new AdmFuncionBean();
                if ("F".equals(menu.getTipo_accion())) {
                    f.setFuncion_id(menu.getAccion_id());
                    f.setForma(rs.getString("forma"));
                    f.setNombre(rs.getString("fnombre"));
                }
                menu.setFuncion(f);
                
                AdmMenuBean m = new AdmMenuBean();
                if ("M".equals(menu.getTipo_accion())) {
                    m.setMenu_id(menu.getAccion_id());
                    m.setNombre(rs.getString("mnombre"));
                    m.setDescripcion(rs.getString("mdesc"));
                }
                menu.setMenu(m);

                menus.add(menu);
            }
            Logger.getLogger(MenusFuncionesDao.class.getName()).log(Level.INFO, "getMenu(): " + id);
        } catch (SQLException e) {
            Logger.getLogger(MenusFuncionesDao.class.getName()).log(Level.SEVERE, "getMenu(): " + id);
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return menus;
    }

    public static String getMenuHTML(int id, int deep) {
        String html = "<ul class='nav nav-sidebar " + (deep > 0 ? "submenu" : "") + "'>";
        MenusFuncionesDao mfd= new MenusFuncionesDao();
        List<AdmMenuFuncionBean> menus = mfd.getMenu(id);
        for (AdmMenuFuncionBean m : menus) {
            if (m.getTipo_accion().equals("F")) {
                html += "<li><a class='ajax' href='" + m.getFuncion().getForma() + "'>" + m.getPrompt() + "</a></li>";
            } else {
                html += "<li><a href='#'>" + m.getPrompt() + " <span class='pull-right glyphicon glyphicon-menu-right'></span></a>" + getMenuHTML(m.getAccion_id(), deep + 1) + "</li>";
            }
        }
        html += "</ul>";
        return html;
    }

}
