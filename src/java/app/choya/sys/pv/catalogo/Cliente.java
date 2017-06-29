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
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;

/**
 *
 * @author Condese
 */
public class Cliente extends GenericDAO implements ClienteI{
    private UtilsDao util_dao = new UtilsDao();

    static final Logger LOGGER = Logger.getLogger(Cliente.class.getName());

    @Override
    public int setCliente(ClienteBean cb) throws SqlAppsException {
        int resultado = 0;
        int id = 0;
        int secuencia = 0;

        Connection cnn = getConexionSinCommit(EDriver.MYSQL, EApps.PV);

        String sql = "INSERT INTO cat_cliente\n" +
        "(id,\n" +
        "clave,\n" +
        "representante,\n" +
        "nombre,\n" +
        "rfc,\n" +
        "curp,\n" +
        "creado_por,\n" +
        "modificado_por,\n" +
        "domicilio,\n" +
        "num_ext,\n" +
        "num_int,\n" +
        "colonia,\n" +
        "codigo_postal,\n" +
        "localidad,\n" +
        "municipio,\n" +
        "entidad,\n" +
        "pais,\n" +
        "telefono,\n" +
        "celular,\n" +
        "email,\n" +
        "comentario,\n" +
        "aplica_retencion,\n" +
        "no_precio,\n" +
        "limite_credito,\n" +
        "dia_credito)\n" +
        "VALUES\n" +
        "(?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?)";

        PreparedStatement ps;
        try {
            
            ps = getPrepareStatement(sql);
            
            UtilsDao ut = new UtilsDao();
            secuencia = ut.nextVal("s_cat_cliente",cnn);
            
            ps.setInt(1, secuencia);

            ps.setInt(1, id);
            ps.setString(2, cb.getClave());
            ps.setString(3, cb.getRepresentante());
            ps.setString(4, cb.getNombre());
            ps.setString(5, cb.getRfc());
            ps.setString(6, cb.getCurp());
            ps.setInt(7, cb.getCreado_por());
            ps.setInt(8, cb.getModificado_por());
            ps.setString(9, cb.getDomicilio());
            ps.setString(10, cb.getNum_ext());
            ps.setString(11, cb.getNum_int());
            ps.setString(12, cb.getColonia());
            ps.setString(13, cb.getCodigo_postal());
            ps.setString(14, cb.getLocalidad());
            ps.setString(15, cb.getMunicipio());
            ps.setString(16, cb.getEntidad());
            ps.setString(17, cb.getPais());
            ps.setString(18, cb.getTelefono());
            ps.setString(19, cb.getCelular());
            ps.setString(20, cb.getEmail());
            ps.setString(21, cb.getComentario());
            ps.setInt(22, cb.getAplica_retencion());
            ps.setDouble(23, cb.getLimite_credito());
            ps.setInt(24, cb.getDia_credito());
            
            resultado = ps.executeUpdate();
            if (resultado != 0) {
                resultado = id;
            }
            commit();

        } catch (SQLException ex) {
            rollback();
            System.out.println("el método setCliente " + ex.getMessage());
            throw new SqlAppsException(ex);
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion();
        }
        return resultado;
    }
    
    
    
    

    @Override
    public int updateCliente(ClienteBean cb) throws SqlAppsException {

        int resultado = 0;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "UPDATE cat_cliente\n"
                + "SET\n"
                + "serie = ?,\n"
                + "estatus = ?,\n"
                + "clave = ?,\n"
                + "representante = ?,\n"
                + "nombre = ?,\n"
                + "rfc = ?,\n"
                + "curp = ?,\n"
                + "imagen = ?,\n"
                + "modificado_por = ?,\n"
                + "fecha_modificacion = ?\n"
                + "WHERE id = ?";

        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);

            ps.setString(1, cb.getSerie());
            ps.setString(2, cb.getEstatus());
            ps.setString(3, cb.getClave());
            ps.setString(4, cb.getRepresentante());
            ps.setString(5, cb.getNombre());
            ps.setString(6, cb.getRfc());
            ps.setString(7, cb.getCurp());
            ps.setByte(8, cb.getImagen());
            ps.setInt(9, cb.getModificado_por());
            ps.setString(10, cb.getFecha_modificacion());
            ps.setInt(11, cb.getId());
            

            resultado = ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("el método updateCliente " + ex.getMessage());
            throw new SqlAppsException(ex);

        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion();
        }

        return resultado;

    }

    @Override
    public ListIterator<ClienteBean> getClienteLista() throws SqlAppsException {
        ResultSet rs = null;
        LinkedList<ClienteBean> lista = new LinkedList<>();

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "SELECT id,\n"
                + "serie,\n"
                + "estatus,\n"
                + "clave,\n"
                + "representante,\n"
                + "nombre,\n"
                + "rfc,\n"
                + "curp,\n"
                + "imagen,\n"
                + "creado_por,\n"
                + "fecha_creacion,\n"
                + "modificado_por,\n"
                + "fecha_modificacion\n"
                + "FROM cat_cliente";

        try {
            Statement stmt = getStatement();

            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                ClienteBean cb = new ClienteBean();
                cb.setId(rs.getInt("id"));
                cb.setSerie(rs.getString("serie"));
                cb.setEstatus(rs.getString("estatus"));
                cb.setClave(rs.getString("clave"));
                cb.setRepresentante(rs.getString("representante"));
                cb.setNombre(rs.getString("nombre"));
                cb.setRfc(rs.getString("rfc"));
                cb.setCurp(rs.getString("curp"));
                cb.setImagen(rs.getByte("imagen"));
                cb.setCreado_por(rs.getInt("creado_por"));
                cb.setFecha_creacion(rs.getString("fecha_creacion"));
                cb.setModificado_por(rs.getInt("modificado_por"));
                cb.setFecha_modificacion(rs.getString("fecha_modificacion"));
                
                lista.add(cb);
            }
        } catch (SQLException ex) {
            System.out.println("el método getClienteLista " + ex.getMessage());
            throw new SqlAppsException(ex);
        } finally {
            try {

                closeStmt();
                if (rs != null) {
                    rs.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion();

        }

        return lista.listIterator();
    }

    @Override
    public ListIterator<ClienteBean> getClienteLista(int id) throws SqlAppsException {

        ResultSet rs = null;
        LinkedList<ClienteBean> lista = new LinkedList<>();

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "SELECT id,\n"
                + "serie,\n"
                + "estatus,\n"
                + "clave,\n"
                + "representante,\n"
                + "nombre,\n"
                + "rfc,\n"
                + "curp,\n"
                + "imagen,\n"
                + "creado_por,\n"
                + "fecha_creacion,\n"
                + "modificado_por,\n"
                + "fecha_modificacion\n"
                + "FROM cat_cliente WHERE id = ?";

        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {
                ClienteBean cb = new ClienteBean();
                cb.setId(rs.getInt("id"));
                cb.setSerie(rs.getString("serie"));
                cb.setEstatus(rs.getString("estatus"));
                cb.setClave(rs.getString("clave"));
                cb.setRepresentante(rs.getString("representante"));
                cb.setNombre(rs.getString("nombre"));
                cb.setRfc(rs.getString("rfc"));
                cb.setCurp(rs.getString("curp"));
                cb.setImagen(rs.getByte("imagen"));
                cb.setCreado_por(rs.getInt("creado_por"));
                cb.setFecha_creacion(rs.getString("fecha_creacion"));
                cb.setModificado_por(rs.getInt("modificado_por"));
                cb.setFecha_modificacion(rs.getString("fecha_modificacion"));
               
                lista.add(cb);

            }
        } catch (SQLException ex) {

            System.out.println("el método getClienteLista(id) a lanzado el siguiente error " + ex.getMessage());
            throw new SqlAppsException(ex);

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Proveedor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion();

        }

        return lista.listIterator();
    }

    @Override
    public ClienteBean getCliente(int id) throws SqlAppsException {
        ResultSet rs = null;
        ClienteBean cb = new ClienteBean();

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "SELECT id,\n"
                + "serie,\n"
                + "estatus,\n"
                + "clave,\n"
                + "representante,\n"
                + "nombre,\n"
                + "rfc,\n"
                + "curp,\n"
                + "imagen,\n"
                + "creado_por,\n"
                + "fecha_creacion,\n"
                + "modificado_por,\n"
                + "fecha_modificacion\n"
                + "FROM cat_cliente WHERE id = ?";

        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {
                
                cb.setId(rs.getInt("id"));
                cb.setSerie(rs.getString("serie"));
                cb.setEstatus(rs.getString("estatus"));
                cb.setClave(rs.getString("clave"));
                cb.setRepresentante(rs.getString("representante"));
                cb.setNombre(rs.getString("nombre"));
                cb.setRfc(rs.getString("rfc"));
                cb.setCurp(rs.getString("curp"));
                cb.setImagen(rs.getByte("imagen"));
                cb.setCreado_por(rs.getInt("creado_por"));
                cb.setFecha_creacion(rs.getString("fecha_creacion"));
                cb.setModificado_por(rs.getInt("modificado_por"));
                cb.setFecha_modificacion(rs.getString("fecha_modificacion"));
                          

            }
        } catch (SQLException ex) {

            System.out.println("el método getClienteLista(id) a lanzado el siguiente error " + ex.getMessage());
            throw new SqlAppsException(ex);

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion();

        }
        
        return cb;
    }

    @Override
    public int deleteCliente(int id) throws SqlAppsException {
        
        int resultado = 0;
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "UPDATE cat_cliente set estatus ='D' WHERE  id = ?";
        
        
        try {
            PreparedStatement ps;
            ps = getPrepareStatement(sql);
            ps.setInt(1, id);
            
            resultado = ps.executeUpdate();
            
            
        } catch (SQLException ex) {
            
            System.out.println("el método deleteCliente a lanzado el siguiente error "+ex.getMessage());
           throw new SqlAppsException(ex);
            
            
        }finally{
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion();
        }
        
        
        return resultado;
        
    }

    @Override
    public JSONObject getClienteListaJSON(int draw) throws SqlAppsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
}


