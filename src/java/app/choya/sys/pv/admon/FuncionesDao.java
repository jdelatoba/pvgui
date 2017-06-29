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
public class FuncionesDao extends GenericDAO {

    public List<AdmFuncionBean> getList() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<AdmFuncionBean> res = null;
        
        try {
            getConexion(EDriver.ORACLE, EApps.PV);
            ps = getPrepareStatement("select f.*,a.nombre aplicacion\n"
                    + "from adm_funciones f\n"
                    + "join adm_aplicaciones a on f.aplicacion_id=a.aplicacion_id\n"
                    + "order by f.funcion_id");
            rs = ps.executeQuery();
            res = new ArrayList();
            while (rs.next()) {
                AdmFuncionBean f = new AdmFuncionBean();
                f.setFuncion_id(rs.getInt("funcion_id"));
                f.setNombre(rs.getString("nombre"));
                f.setAplicacion_id(rs.getInt("aplicacion_id"));
                f.setAplicacion(rs.getString("aplicacion"));
                f.setForma(rs.getString("forma"));
                f.setParametros(rs.getString("parametros"));
                res.add(f);
            }
            Logger.getLogger(FuncionesDao.class.getName()).log(Level.INFO, "getFuncionList()");
        } catch (SQLException e) {
            Logger.getLogger(FuncionesDao.class.getName()).log(Level.SEVERE, "getFuncionList()", e);
            System.out.println("error getFuncionList()");
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
        return res;
    }

    public List<AdmFuncionBean> getList(String query) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<AdmFuncionBean> res = null;

        try {
            getConexion(EDriver.ORACLE, EApps.PV);
            ps = getPrepareStatement("select f.*,a.nombre aplicacion\n"
                    + "from adm_funciones f\n"
                    + "join adm_aplicaciones a on f.aplicacion_id=a.aplicacion_id\n"
                    + "where upper(f.nombre) like upper(?)"
                    + "order by f.funcion_id");
            ps.setString(1, '%' + query + '%');
            rs = ps.executeQuery();
            res = new ArrayList();
            while (rs.next()) {
                AdmFuncionBean f = new AdmFuncionBean();
                f.setFuncion_id(rs.getInt("funcion_id"));
                f.setNombre(rs.getString("nombre"));
                f.setAplicacion_id(rs.getInt("aplicacion_id"));
                f.setAplicacion(rs.getString("aplicacion"));
                f.setForma(rs.getString("forma"));
                f.setParametros(rs.getString("parametros"));
                res.add(f);
            }
            System.out.println("getFuncionList()");
        } catch (SQLException e) {
            System.out.println("error getFuncionList()");
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
        return res;
    }

    public List<AdmFuncionBean> getDataTableList(String query, String start, String length, String order) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<AdmFuncionBean> res = null;

        try {
            getConexion(EDriver.ORACLE, EApps.PV);
            ps = getPrepareStatement("select * from (select rownum rnum,a.* from (\n"
                    + "select f.*,a.nombre aplicacion,(select count(*) from adm_funciones) count\n"
                    + "from adm_funciones f\n"
                    + "join adm_aplicaciones a on f.aplicacion_id=a.aplicacion_id\n"
                    + "where f.funcion_id like upper(?) or f.nombre like upper(?) or a.nombre like upper(?) or f.forma like upper(?)"
                    + "order by " + order + "\n"
                    + ") a\n"
                    + "where rownum<=" + (Integer.valueOf(start) + Integer.valueOf(length))
                    + ") where rnum>=" + start);
            ps.setString(1, '%' + query + '%');
            ps.setString(2, '%' + query + '%');
            ps.setString(3, '%' + query + '%');
            ps.setString(4, '%' + query + '%');
            rs = ps.executeQuery();
            res = new ArrayList();
            while (rs.next()) {
                AdmFuncionBean f = new AdmFuncionBean();
                f.setFuncion_id(rs.getInt("funcion_id"));
                f.setNombre(rs.getString("nombre"));
                f.setAplicacion_id(rs.getInt("aplicacion_id"));
                f.setAplicacion(rs.getString("aplicacion"));
                f.setForma(rs.getString("forma"));
                f.setParametros(rs.getString("parametros"));
                res.add(f);
            }
            System.out.println(String.format("getFuncionList() %s %s %s %s", query, start, length, order));
        } catch (SQLException e) {
            System.out.println("error getFuncionList()");
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
        return res;
    }

    public int getCount(String query) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            getConexion(EDriver.ORACLE, EApps.PV);
            ps = getPrepareStatement("select count(*)\n"
                    + "from adm_funciones f\n"
                    + "join adm_aplicaciones a on f.aplicacion_id=a.aplicacion_id\n"
                    + "where f.funcion_id like upper(?) or f.nombre like upper(?) or a.nombre like upper(?) or f.forma like upper(?)");
            ps.setString(1, "%" + query + "%");
            ps.setString(2, "%" + query + "%");
            ps.setString(3, "%" + query + "%");
            ps.setString(4, "%" + query + "%");
            rs = ps.executeQuery();
            rs.next();
            count = rs.getInt(1);
            System.out.println("getCount() "+query);
        } catch (SQLException e) {
            System.out.println("error getCount()");
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
        return count;
    }

    public boolean guardar(AdmFuncionBean f) {
        boolean estado = false;
        Connection cnn = null;
        PreparedStatement st = null;
        try {
            getConexion(EDriver.ORACLE, EApps.PV);
            st = getPrepareStatement("INSERT INTO adm_funciones (funcion_id,"
                    + "aplicacion_id,"
                    + "nombre,"
                    + "forma,"
                    + "parametros,"
                    + "CREADO_POR,"
                    + "MODIFICADO_POR,"
                    + "FECHA_CREACION,"
                    + "FECHA_MODIFICACION) VALUES (?,?,?,?,?,?,?,SYSDATE,SYSDATE)");

            st.setInt(1, f.getFuncion_id());
            st.setInt(2, f.getAplicacion_id());
            st.setString(3, f.getNombre());
            st.setString(4, f.getForma());
            st.setString(5, f.getParametros());
            st.setInt(6, f.getCreado_por());
            st.setInt(7, f.getModificado_por());

            if (st.executeUpdate() > 0) {
                estado = true;
            }

        } catch (SQLException ex) {
            estado = false;
            Logger.getLogger(FuncionesDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (cnn != null) {
                    cnn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return estado;
    }
}
