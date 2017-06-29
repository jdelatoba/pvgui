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
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Rembao
 */
public class Vendedor extends GenericDAO implements VendedorI {

    private UtilsDao util_dao = new UtilsDao();

    static final Logger LOGGER = Logger.getLogger(Vendedor.class.getName());

    @Override
    public int setVendedor(VendedorBean vb) throws SqlAppsException {

        int resultado = 0;
        int id = 0;
        
        int secuencia = 0;

        Connection cnn = getConexionSinCommit(EDriver.MYSQL, EApps.PV);

        String sql = "INSERT INTO conf_vendedor\n"
                + "(id,\n"
                + "vendedor,\n"
                + "nombre,\n"
                + "domicilio,\n"
                + "ciudad,\n"
                + "telefono,\n"
                + "celular,\n"
                + "email,\n"
                + "puesto,\n"
                + "comision,\n"
                + "grupo_id,\n"
                + "creado_por,\n"
                + "modificado_por)\n"
                + "VALUES\n"
                + "(?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?)";

        

        PreparedStatement ps;
        try {
            
            ps = getPrepareStatement(sql);
            
            UtilsDao ut = new UtilsDao();
            secuencia = ut.nextVal("s_conf_vendedor",cnn);
            
            ps.setInt(1, secuencia);
            
            ps.setString(2, vb.getVendedor());
            ps.setString(3, vb.getNombre());
            ps.setString(4, vb.getDomicilio());
            ps.setString(5, vb.getCiudad());
            ps.setString(6, vb.getTelefono());
            ps.setString(7, vb.getCelular());
            ps.setString(8, vb.getEmail());
            ps.setString(9, vb.getPuesto());
            ps.setDouble(10, vb.getComision());
            ps.setInt(11, vb.getGrupo_id());
            ps.setInt(12, vb.getCreado_por());
            ps.setInt(13, vb.getModificado_por());

            resultado = ps.executeUpdate();
            commit();
            if (resultado != 0) {
                resultado = secuencia;
            }

        } catch (SQLException ex) {
            rollback();
            throw new SqlAppsException(ex, Vendedor.class.getName() + "-- metodo: setVendedor", LOGGER);
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Vendedor.class.getName() + "-- metodo: setVendedor", LOGGER);
            }
            closeConexion();
        }
        return resultado;

    }

    @Override
    public int updateVendedor(VendedorBean vb) throws SqlAppsException {

        int resultado = 0;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "UPDATE conf_vendedor\n"
                + "SET\n"
                + "vendedor = ?,\n"
                + "nombre = ?,\n"
                + "domicilio = ?,\n"
                + "ciudad = ?,\n"
                + "telefono = ?,\n"
                + "celular = ?,\n"
                + "email = ?,\n"
                + "puesto = ?,\n"
                + "comision = ?,\n"
                + "grupo_id = ?,\n"
                + "modificado_por = ?\n"
                + "WHERE id = ?";
        
        
        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setString(1, vb.getVendedor());
            ps.setString(2, vb.getNombre());
            ps.setString(3, vb.getDomicilio());
            ps.setString(4, vb.getCiudad());
            ps.setString(5, vb.getTelefono());
            ps.setString(6, vb.getCelular());
            ps.setString(7, vb.getEmail());
            ps.setString(8, vb.getPuesto());
            ps.setDouble(9, vb.getComision());
            ps.setInt(10, vb.getGrupo_id());
            ps.setInt(11, vb.getModificado_por());
            ps.setInt(12, vb.getId());
            
            resultado = ps.executeUpdate();
            
            
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Vendedor.class.getName() + "-- metodo: updateVendedor", LOGGER);

        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Vendedor.class.getName() + "-- metodo: updateVendedor", LOGGER);
            }
            closeConexion();
        }

        return resultado;

    }

    @Override
    public int deleteVendedor(VendedorBean vb) throws SqlAppsException {
        int resultado = 0;
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "UPDATE conf_vendedor\n" +
        "SET\n" +
        "estatus = 'D'\n" +
        ",modificado_por = ?\n" +
        "WHERE id = ?";
        
        
        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setInt(1, vb.getModificado_por());
            ps.setInt(2, vb.getId());
            
            resultado = ps.executeUpdate();
            
        } catch (SQLException ex) {

            throw new SqlAppsException(ex, Vendedor.class.getName(), LOGGER);

        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Vendedor.class.getName(), LOGGER);
            }
            closeConexion();
        }

        return resultado;
    }

    @Override
    public JSONObject getListaVendedor(int draw) throws SqlAppsException {
        
        
        ResultSet rs = null;
        int contador = 0;
        JSONObject vendedores = new JSONObject();
        JSONArray data = new JSONArray();
        Statement stmt;
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "SELECT a.id,\n" +
        "a.vendedor,\n" +
        "a.nombre,\n" +
        "a.domicilio,\n" +
        "a.ciudad,\n" +
        "a.telefono,\n" +
        "a.celular,\n" +
        "a.email,\n" +
        "a.puesto,\n" +
        "a.comision,\n" +
        "a.grupo_id,\n" +
        "a.creado_por,\n" +
        "date_format(a.fecha_creacion,'%d/%m/%Y %H:%i:%s') fecha_creacion,\n" +
        "a.modificado_por,\n" +
        "date_format(a.fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion,\n" +
        "a.serie,\n" +
        "a.estatus,\n" +
        "b.descripcion grupo\n" +
        "FROM conf_vendedor a\n" +
        "LEFT JOIN conf_vendedor_grp b\n" +
        "ON a.grupo_id = b.id\n" +
        "WHERE a.estatus = 'A'\n" +
        "ORDER BY a.nombre";
        
        try {
            stmt = getStatement();
            
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
            
                JSONObject vendedorJSON = new JSONObject();
                
                vendedorJSON.put("id", rs.getInt("id"));
                vendedorJSON.put("vendedor", rs.getString("vendedor"));
                vendedorJSON.put("nombre", rs.getString("nombre"));
                vendedorJSON.put("domicilio",rs.getString("domicilio"));
                vendedorJSON.put("ciudad", rs.getString("ciudad"));
                vendedorJSON.put("telefono", rs.getString("telefono"));
                vendedorJSON.put("celular", rs.getString("celular"));
                vendedorJSON.put("email", rs.getString("email"));
                vendedorJSON.put("puesto", rs.getString("puesto"));
                vendedorJSON.put("comision", rs.getDouble("comision"));
                vendedorJSON.put("grupo_id", rs.getInt("grupo_id"));
                vendedorJSON.put("creado_por", rs.getInt("creado_por"));
                vendedorJSON.put("fecha_creacion", rs.getString("fecha_creacion"));
                vendedorJSON.put("modificado_por", rs.getInt("modificado_por"));
                vendedorJSON.put("fecha_modificacion", rs.getString("fecha_modificacion"));
                vendedorJSON.put("serie", rs.getString("serie"));
                vendedorJSON.put("estatus", rs.getString("estatus"));
                vendedorJSON.put("grupo", rs.getString("grupo"));
                
                data.add(vendedorJSON);
                
                contador++;
            }
            
            vendedores.put("data",data);
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Vendedor.class.getName() + "-- metodo: getListaVendedor", LOGGER);
        } finally {
            try {

                closeStmt();
                if (rs != null) {
                    rs.close();
                }

            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Vendedor.class.getName() + "-- metodo: getListaVendedor", LOGGER);
            }

            closeConexion();

        }
        
        return vendedores;
    }

    @Override
    public ListIterator<VendedorBean> getListaVendedor() throws SqlAppsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<VendedorBean> getListaVendedorById(int id) throws SqlAppsException {
        
        LinkedList<VendedorBean> lista = new LinkedList<>();
        ResultSet rs = null;
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "SELECT a.id,\n" +
        "a.vendedor,\n" +
        "a.nombre,\n" +
        "a.domicilio,\n" +
        "a.ciudad,\n" +
        "a.telefono,\n" +
        "a.celular,\n" +
        "a.email,\n" +
        "a.puesto,\n" +
        "a.comision,\n" +
        "a.grupo_id,\n" +
        "a.creado_por,\n" +
        "date_format(a.fecha_creacion,'%d/%m/%Y %H:%i:%s') fecha_creacion,\n" +
        "a.modificado_por,\n" +
        "date_format(a.fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion,\n" +
        "a.serie,\n" +
        "a.estatus,\n" +
        "b.descripcion grupo\n" +
        "FROM conf_vendedor a\n" +
        "LEFT JOIN conf_vendedor_grp b\n" +
        "ON a.grupo_id = b.id\n" +
        "WHERE a.id = ? AND a.estatus = 'A'\n" +
        "ORDER BY a.nombre";
        
        try {
            PreparedStatement ps = getCallableStatement(sql);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                VendedorBean vb = new VendedorBean();
                vb.setId(rs.getInt("id"));
                vb.setVendedor(rs.getString("vendedor"));
                vb.setNombre(rs.getString("nombre"));
                vb.setDomicilio(rs.getString("domicilio"));
                vb.setCiudad(rs.getString("ciudad"));
                vb.setTelefono(rs.getString("telefono"));
                vb.setCelular(rs.getString("celular"));
                vb.setEmail(rs.getString("email"));
                vb.setPuesto(rs.getString("puesto"));
                vb.setComision(rs.getDouble("comision"));
                vb.setGrupo_id(rs.getInt("grupo_id"));
                vb.setGrupo(rs.getString("grupo"));
                vb.setFecha_creacion(rs.getString("fecha_creacion"));
                vb.setCreado_por(rs.getInt("creado_por"));
                vb.setFecha_modificacion(rs.getString("fecha_modificacion"));
                vb.setModificado_por(rs.getInt("modificado_por"));
                vb.setSerie(rs.getString("serie"));
                vb.setEstatus(rs.getString("estatus"));
                
                lista.add(vb);
            
            }
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Vendedor.class.getName() + "-- metodo: getListaVendedorById", LOGGER);
        } finally {
            try {

                closeStmt();
                if (rs != null) {
                    rs.close();
                }

            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Vendedor.class.getName() + "-- metodo: getListaVendedorById", LOGGER);
            }

            closeConexion();

        }
        
        
        return lista.listIterator();

        
    }

    @Override
    public VendedorBean getVendedor(int id) throws SqlAppsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
