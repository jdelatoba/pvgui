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
public class Proveedor extends GenericDAO implements ProveedorI {

    private UtilsDao util_dao = new UtilsDao();

    static final Logger LOGGER = Logger.getLogger(Proveedor.class.getName());

    @Override
    public int setProveedor(ProveedorBean pb) throws SqlAppsException {
        int resultado = 0;
        int id = 0;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "INSERT INTO cat_proveedor\n"
                + "(id,\n"
                + "representante,\n"
                + "nombre,\n"
                + "alias,\n"
                + "rfc,\n"
                + "curp,\n"
                + "domicilio,\n"
                + "no_ext,\n"
                + "no_int,\n"
                + "colonia,\n"
                + "codigo_postal,\n"
                + "localidad,\n"
                + "municipio,\n"
                + "entidad,\n"
                + "pais,\n"
                + "telefono,\n"
                + "celular,\n"
                + "email,\n"
                + "comentario,\n"
                + "limite_credito,\n"
                + "dias_credito,\n"
                + "serie)\n"
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

        try {
            //nuevo consecutivo a insertar
            id = util_dao.nextVal("s_cat_proveedor");

            PreparedStatement ps = getPrepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, pb.getRepresentante());
            ps.setString(3, pb.getNombre());
            ps.setString(4, pb.getAlias());
            ps.setString(5, pb.getRfc());
            ps.setString(6, pb.getCurp());
            ps.setString(7, pb.getDomicilio());
            ps.setString(8, pb.getNo_ext());
            ps.setString(9, pb.getNo_int());
            ps.setString(10, pb.getColonia());
            ps.setString(11, pb.getCodigo_postal());
            ps.setString(12, pb.getLocalidad());
            ps.setString(13, pb.getMunicipio());
            ps.setString(14, pb.getEntidad());
            ps.setString(15, pb.getPais());
            ps.setString(16, pb.getTelefono());
            ps.setString(17, pb.getCelular());
            ps.setString(18, pb.getEmail());
            ps.setString(19, pb.getComentario());
            ps.setDouble(20, pb.getLimite_credito());
            ps.setInt(21, pb.getDias_credito());
            ps.setString(22, pb.getSerie());

            resultado = ps.executeUpdate();
            if (resultado != 0) {
                resultado = id;
            }

        } catch (SQLException ex) {

            //Logger.getLogger(Proveedor.class.getName()).log(Level.WARNING, null, ex);
            throw new SqlAppsException(ex, Proveedor.class.getName() + "-- metodo: setProveedor", LOGGER);
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Proveedor.class.getName() + "-- metodo: setProveedor", LOGGER);
            }
            closeConexion();
        }
        return resultado;
    }

    @Override
    public int updateProveedor(ProveedorBean pb) throws SqlAppsException {

        int resultado = 0;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "UPDATE cat_proveedor\n"
                + "SET\n"
                + "representante = ?,\n"
                + "nombre = ?,\n"
                + "alias = ?,\n"
                + "rfc = ?,\n"
                + "curp = ?,\n"
                + "domicilio = ?,\n"
                + "no_ext = ?,\n"
                + "no_int = ?,\n"
                + "colonia = ?,\n"
                + "codigo_postal = ?,\n"
                + "localidad = ?,\n"
                + "municipio = ?,\n"
                + "entidad = ?,\n"
                + "pais = ?,\n"
                + "telefono = ?,\n"
                + "celular = ?,\n"
                + "email = ?,\n"
                + "comentario = ?,\n"
                + "limite_credito = ?,\n"
                + "dias_credito = ?,\n"
                + "serie = ?\n"
                + "WHERE id = ?";

        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);

            ps.setString(1, pb.getRepresentante());
            ps.setString(2, pb.getNombre());
            ps.setString(3, pb.getAlias());
            ps.setString(4, pb.getRfc());
            ps.setString(5, pb.getCurp());
            ps.setString(6, pb.getDomicilio());
            ps.setString(7, pb.getNo_ext());
            ps.setString(8, pb.getNo_int());
            ps.setString(9, pb.getColonia());
            ps.setString(10, pb.getCodigo_postal());
            ps.setString(11, pb.getLocalidad());
            ps.setString(12, pb.getMunicipio());
            ps.setString(13, pb.getEntidad());
            ps.setString(14, pb.getPais());
            ps.setString(15, pb.getTelefono());
            ps.setString(16, pb.getCelular());
            ps.setString(17, pb.getEmail());
            ps.setString(18, pb.getComentario());
            ps.setDouble(19, pb.getLimite_credito());
            ps.setInt(20, pb.getDias_credito());
            ps.setString(21, pb.getSerie());
            ps.setInt(22, pb.getId());

            resultado = ps.executeUpdate();

        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Proveedor.class.getName() + "-- metodo: updateProveedor", LOGGER);

        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Proveedor.class.getName() + "-- metodo: updateProveedor", LOGGER);
            }
            closeConexion();
        }

        return resultado;

    }

    @Override
    public ListIterator<ProveedorBean> getProveedorLista() throws SqlAppsException {
        ResultSet rs = null;
        LinkedList<ProveedorBean> lista = new LinkedList<>();

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "SELECT id,\n"
                + "representante,\n"
                + "nombre,\n"
                + "alias,\n"
                + "rfc,\n"
                + "curp,\n"
                + "domicilio,\n"
                + "no_ext,\n"
                + "no_int,\n"
                + "colonia,\n"
                + "codigo_postal,\n"
                + "localidad,\n"
                + "municipio,\n"
                + "entidad,\n"
                + "pais,\n"
                + "telefono,\n"
                + "celular,\n"
                + "email,\n"
                + "comentario,\n"
                + "limite_credito,\n"
                + "dias_credito,\n"
                + "serie\n"
                + "FROM cat_proveedor";

        try {
            Statement stmt = getStatement();

            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                ProveedorBean pb = new ProveedorBean();
                pb.setId(rs.getInt("id"));
                pb.setRepresentante(rs.getString("representante"));
                pb.setNombre(rs.getString("nombre"));
                pb.setAlias(rs.getString("alias"));
                pb.setRfc(rs.getString("rfc"));
                pb.setCurp(rs.getString("curp"));
                pb.setDomicilio(rs.getString("domicilio"));
                pb.setNo_ext(rs.getString("no_ext"));
                pb.setNo_int(rs.getString("no_ext"));
                pb.setColonia(rs.getString("colonia"));
                pb.setCodigo_postal(rs.getString("codigo_postal"));
                pb.setLocalidad(rs.getString("localidad"));
                pb.setMunicipio(rs.getString("municipio"));
                pb.setEntidad(rs.getString("entidad"));
                pb.setPais(rs.getString("pais"));
                pb.setTelefono(rs.getString("telefono"));
                pb.setCelular(rs.getString("celular"));
                pb.setEmail(rs.getString("email"));
                pb.setComentario(rs.getString("comentario"));
                pb.setLimite_credito(rs.getDouble("limite_credito"));
                pb.setDias_credito(rs.getInt("dias_credito"));
                pb.setSerie(rs.getString("serie"));

                lista.add(pb);
            }
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Proveedor.class.getName() + "-- metodo: getProveedorLista", LOGGER);
        } finally {
            try {

                closeStmt();
                if (rs != null) {
                    rs.close();
                }

            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Proveedor.class.getName() + "-- metodo: getProveedorLista", LOGGER);
            }

            closeConexion();

        }

        return lista.listIterator();
    }

    @Override
    public ListIterator<ProveedorBean> getProveedorLista(int id) throws SqlAppsException {

        ResultSet rs = null;
        LinkedList<ProveedorBean> lista = new LinkedList<>();

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "SELECT id,\n"
                + "representante,\n"
                + "nombre,\n"
                + "alias,\n"
                + "rfc,\n"
                + "curp,\n"
                + "domicilio,\n"
                + "no_ext,\n"
                + "no_int,\n"
                + "colonia,\n"
                + "codigo_postal,\n"
                + "localidad,\n"
                + "municipio,\n"
                + "entidad,\n"
                + "pais,\n"
                + "telefono,\n"
                + "celular,\n"
                + "email,\n"
                + "comentario,\n"
                + "limite_credito,\n"
                + "dias_credito,\n"
                + "serie\n"
                + "FROM cat_proveedor WHERE id = ?";

        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {
                ProveedorBean pb = new ProveedorBean();
                pb.setId(rs.getInt("id"));
                pb.setRepresentante(rs.getString("representante"));
                pb.setNombre(rs.getString("nombre"));
                pb.setAlias(rs.getString("alias"));
                pb.setRfc(rs.getString("rfc"));
                pb.setCurp(rs.getString("curp"));
                pb.setDomicilio(rs.getString("domicilio"));
                pb.setNo_ext(rs.getString("no_ext"));
                pb.setNo_int(rs.getString("no_ext"));
                pb.setColonia(rs.getString("colonia"));
                pb.setCodigo_postal(rs.getString("codigo_postal"));
                pb.setLocalidad(rs.getString("localidad"));
                pb.setMunicipio(rs.getString("municipio"));
                pb.setEntidad(rs.getString("entidad"));
                pb.setPais(rs.getString("pais"));
                pb.setTelefono(rs.getString("telefono"));
                pb.setCelular(rs.getString("celular"));
                pb.setEmail(rs.getString("email"));
                pb.setComentario(rs.getString("comentario"));
                pb.setLimite_credito(rs.getDouble("limite_credito"));
                pb.setDias_credito(rs.getInt("dias_credito"));
                pb.setSerie(rs.getString("serie"));

                lista.add(pb);

            }
        } catch (SQLException ex) {

            throw new SqlAppsException(ex, Proveedor.class.getName() + "-- metodo: getProveedirLista(id)", LOGGER);

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    throw new SqlAppsException(ex, Proveedor.class.getName() + "-- metodo: getProveedirLista(id)", LOGGER);
                }
            }
            try {
                closePStmt();
            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Proveedor.class.getName() + "-- metodo: getProveedirLista(id)", LOGGER);
            }
            closeConexion();

        }

        return lista.listIterator();
    }

    @Override
    public ProveedorBean getProveedor(int id) throws SqlAppsException {
        ResultSet rs = null;
        ProveedorBean pb = new ProveedorBean();

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "SELECT id,\n"
                + "representante,\n"
                + "nombre,\n"
                + "alias,\n"
                + "rfc,\n"
                + "curp,\n"
                + "domicilio,\n"
                + "no_ext,\n"
                + "no_int,\n"
                + "colonia,\n"
                + "codigo_postal,\n"
                + "localidad,\n"
                + "municipio,\n"
                + "entidad,\n"
                + "pais,\n"
                + "telefono,\n"
                + "celular,\n"
                + "email,\n"
                + "comentario,\n"
                + "limite_credito,\n"
                + "dias_credito,\n"
                + "serie\n"
                + "FROM cat_proveedor WHERE id = ?";

        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {

                pb.setId(rs.getInt("id"));
                pb.setRepresentante(rs.getString("representante"));
                pb.setNombre(rs.getString("nombre"));
                pb.setAlias(rs.getString("alias"));
                pb.setRfc(rs.getString("rfc"));
                pb.setCurp(rs.getString("curp"));
                pb.setDomicilio(rs.getString("domicilio"));
                pb.setNo_ext(rs.getString("no_ext"));
                pb.setNo_int(rs.getString("no_ext"));
                pb.setColonia(rs.getString("colonia"));
                pb.setCodigo_postal(rs.getString("codigo_postal"));
                pb.setLocalidad(rs.getString("localidad"));
                pb.setMunicipio(rs.getString("municipio"));
                pb.setEntidad(rs.getString("entidad"));
                pb.setPais(rs.getString("pais"));
                pb.setTelefono(rs.getString("telefono"));
                pb.setCelular(rs.getString("celular"));
                pb.setEmail(rs.getString("email"));
                pb.setComentario(rs.getString("comentario"));
                pb.setLimite_credito(rs.getDouble("limite_credito"));
                pb.setDias_credito(rs.getInt("dias_credito"));
                pb.setSerie(rs.getString("serie"));

            }
        } catch (SQLException ex) {

            throw new SqlAppsException(ex, Proveedor.class.getName() + "-- metodo: getProveedor(id)", LOGGER);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    throw new SqlAppsException(ex, Proveedor.class.getName() + "-- metodo: getProveedor(id)", LOGGER);
                }
            }
            try {
                closePStmt();
            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Proveedor.class.getName() + "-- metodo: getProveedor(id)", LOGGER);
            }
            closeConexion();

        }

        return pb;
    }

    @Override
    public int deleteProveedor(int id) throws SqlAppsException {

        int resultado = 0;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "UPDATE cat_proveedor set estatus ='D' WHERE id = ?";

        try {
            PreparedStatement ps;
            ps = getPrepareStatement(sql);
            ps.setInt(1, id);

            resultado = ps.executeUpdate();

        } catch (SQLException ex) {

            throw new SqlAppsException(ex, Proveedor.class.getName(), LOGGER);

        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Proveedor.class.getName(), LOGGER);
            }
            closeConexion();
        }

        return resultado;

    }

    @Override
    public JSONObject getProveedorJSON(int draw) throws SqlAppsException {
        ResultSet rs = null;
        int contador = 0;
        JSONObject proveedor = new JSONObject();
        JSONArray data = new JSONArray();

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "SELECT id,\n"
                + "representante,\n"
                + "nombre,\n"
                + "alias,\n"
                + "rfc,\n"
                + "curp,\n"
                + "domicilio,\n"
                + "no_ext,\n"
                + "no_int,\n"
                + "colonia,\n"
                + "codigo_postal,\n"
                + "localidad,\n"
                + "municipio,\n"
                + "entidad,\n"
                + "pais,\n"
                + "telefono,\n"
                + "celular,\n"
                + "email,\n"
                + "comentario,\n"
                + "limite_credito,\n"
                + "dias_credito,\n"
                + "serie\n"
                + "FROM cat_proveedor WHERE estatus ='A' ORDER BY nombre";

        try {
            Statement stmt = getStatement();

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                JSONArray proveedores = new JSONArray();
                //JSONObject proveedorJSON = new JSONObject();
                /*ProveedorBean pb = new ProveedorBean();
                pb.setId(rs.getInt("id"));
                pb.setRepresentante(rs.getString("representante"));
                pb.setNombre(rs.getString("nombre"));
                pb.setAlias(rs.getString("alias"));
                pb.setRfc(rs.getString("rfc"));
                pb.setCurp(rs.getString("curp"));
                pb.setDomicilio(rs.getString("domicilio"));
                pb.setNo_ext(rs.getString("no_ext"));
                pb.setNo_int(rs.getString("no_int"));
                pb.setColonia(rs.getString("colonia"));
                pb.setCodigo_postal(rs.getString("codigo_postal"));
                pb.setLocalidad(rs.getString("localidad"));
                pb.setMunicipio(rs.getString("municipio"));
                pb.setEntidad(rs.getString("entidad"));
                pb.setPais(rs.getString("pais"));
                pb.setTelefono(rs.getString("telefono"));
                pb.setCelular(rs.getString("celular"));
                pb.setEmail(rs.getString("email"));
                pb.setComentario(rs.getString("comentario"));
                pb.setLimite_credito(rs.getDouble("limite_credito"));
                pb.setDias_credito(rs.getInt("dias_credito"));
                pb.setSerie(rs.getString("serie"));*/

                //proveedorJSON.put("id", rs.getInt("id"));
                //proveedorJSON.put("representante", rs.getString("representante"));
                //proveedorJSON.put("nombre", rs.getString("nombre"));
                //proveedorJSON.put("alias", rs.getString("alias"));
                //proveedorJSON.put("rfc", rs.getString("rfc"));
//                proveedorJSON.put("curp", rs.getString("curp"));
//                proveedorJSON.put("domicilio", rs.getString("domicilio"));
//                proveedorJSON.put("no_ext", rs.getString("no_ext"));
//                proveedorJSON.put("no_int", rs.getString("no_int"));
//                proveedorJSON.put("colonia", rs.getString("colonia"));
//                proveedorJSON.put("codigo_postal", rs.getString("codigo_postal"));
//                proveedorJSON.put("localidad", rs.getString("localidad"));
//                proveedorJSON.put("municipio", rs.getString("municipio"));
//                proveedorJSON.put("entidad", rs.getString("entidad"));
//                proveedorJSON.put("pais", rs.getString("pais"));
                //proveedorJSON.put("telefono", rs.getString("telefono"));
//                proveedorJSON.put("celular", rs.getString("celular"));
//                proveedorJSON.put("email", rs.getString("email"));
//                proveedorJSON.put("comentario", rs.getString("comentario"));
//                proveedorJSON.put("limite_credito", rs.getString("limite_credito"));
//                proveedorJSON.put("dias_credito", rs.getString("dias_credito"));
//                proveedorJSON.put("serie", rs.getString("serie"));
                //proveedores.add(proveedorJSON);
                proveedores.add(rs.getInt("id"));
                proveedores.add(rs.getString("nombre"));
                proveedores.add(rs.getString("alias"));
                proveedores.add(rs.getString("telefono"));

                data.add(proveedores);
                contador++;

            }

            proveedor.put("draw", draw);
            proveedor.put("recordsTotal", contador);
            proveedor.put("recordsFiltered", contador);
            proveedor.put("data", data);

        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Proveedor.class.getName() + "-- metodo: getProveedorLista", LOGGER);
        } finally {
            try {

                closeStmt();
                if (rs != null) {
                    rs.close();
                }

            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Proveedor.class.getName() + "-- metodo: getProveedorLista", LOGGER);
            }

            closeConexion();

        }

        return proveedor;
    }

    public JSONObject getProveedorJSON2(int draw) throws SqlAppsException {
        ResultSet rs = null;
        int contador = 0;
        JSONObject proveedor = new JSONObject();
        JSONArray data = new JSONArray();

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "SELECT id,\n"
                + "representante,\n"
                + "nombre,\n"
                + "alias,\n"
                + "rfc,\n"
                + "curp,\n"
                + "CONCAT(domicilio,' ',no_ext,' ',no_int,' ',colonia,' C.P.',codigo_postal,' ',localidad,' ',municipio) as domicilio_desc,\n"
                + "domicilio,\n"
                + "no_ext,\n"
                + "no_int,\n"
                + "colonia,\n"
                + "codigo_postal,\n"
                + "localidad,\n"
                + "municipio,\n"
                + "entidad,\n"
                + "pais,\n"
                + "telefono,\n"
                + "celular,\n"
                + "email,\n"
                + "comentario,\n"
                + "limite_credito,\n"
                + "dias_credito,\n"
                + "serie\n"
                + "FROM cat_proveedor WHERE estatus = 'A' order by nombre";

        try {
            Statement stmt = getStatement();

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                JSONArray proveedores = new JSONArray();
                JSONObject proveedorJSON = new JSONObject();

                proveedorJSON.put("id", rs.getInt("id"));
                proveedorJSON.put("representante", rs.getString("representante"));
                proveedorJSON.put("nombre", rs.getString("nombre"));
                proveedorJSON.put("alias", rs.getString("alias"));
                proveedorJSON.put("rfc", rs.getString("rfc"));
//                proveedorJSON.put("curp", rs.getString("curp"));
                proveedorJSON.put("domicilio_desc", rs.getString("domicilio_desc"));
//                proveedorJSON.put("no_ext", rs.getString("no_ext"));
//                proveedorJSON.put("no_int", rs.getString("no_int"));
//                proveedorJSON.put("colonia", rs.getString("colonia"));
//                proveedorJSON.put("codigo_postal", rs.getString("codigo_postal"));
//                proveedorJSON.put("localidad", rs.getString("localidad"));
//                proveedorJSON.put("municipio", rs.getString("municipio"));
//                proveedorJSON.put("entidad", rs.getString("entidad"));
//                proveedorJSON.put("pais", rs.getString("pais"));
                proveedorJSON.put("telefono", rs.getString("telefono"));
//                proveedorJSON.put("celular", rs.getString("celular"));
                    proveedorJSON.put("email", rs.getString("email"));
//                proveedorJSON.put("comentario", rs.getString("comentario"));
                proveedorJSON.put("limite_credito", rs.getString("limite_credito"));
                proveedorJSON.put("dias_credito", rs.getString("dias_credito"));
//                proveedorJSON.put("serie", rs.getString("serie"));
                data.add(proveedorJSON);

                //data.add(proveedores);
                contador++;

            }

            //proveedor.put("draw",draw);
            //proveedor.put("recordsTotal",contador);
            //proveedor.put("recordsFiltered", contador);
            proveedor.put("data", data);

        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Proveedor.class.getName() + "-- metodo: getProveedorLista", LOGGER);
        } finally {
            try {

                closeStmt();
                if (rs != null) {
                    rs.close();
                }

            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Proveedor.class.getName() + "-- metodo: getProveedorLista", LOGGER);
            }

            closeConexion();

        }

        return proveedor;
    }

}
