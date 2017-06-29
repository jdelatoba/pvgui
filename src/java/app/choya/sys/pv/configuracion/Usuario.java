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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Condese
 */
public class Usuario extends GenericDAO implements UsuarioI {

    static final Logger LOGGER = Logger.getLogger(Usuario.class.getName());

    @Override
    public int setUsuario(UsuarioBean ub) throws SqlAppsException {

        int resultado = 0;
        int secuencia = 0;
        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "INSERT INTO conf_usuario\n"
                + "(id,\n"
                + "nombre,\n"
                + "domicilio,\n"
                + "ciudad,\n"
                + "telefono,\n"
                + "celular,\n"
                + "activo,\n"
                + "usuario,\n"
                + "password,\n"
                + "id_rol,\n"
                + "id_vendedor,\n"
                + "serie,\n"
                
                + "creado_por,\n"
                + "modificado_por,\n"
                + "email,\n"
                + "comentario, sucursal)\n"
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
                + "?,?)\n";

        PreparedStatement ps = null;
        try {
            ps = getPrepareStatement(sql);

            UtilsDao ut = new UtilsDao();

            secuencia = ut.nextVal("s_conf_usuario");
            ps.setInt(1, secuencia);
            ps.setString(2, ub.getNombre());
            ps.setString(3, ub.getDomicilio());
            ps.setString(4, ub.getCiudad());
            ps.setString(5, ub.getTelefono());
            ps.setString(6, ub.getCelular());
            ps.setString(7, ub.getActivo());
            ps.setString(8, ub.getUsuario());
            ps.setString(9, ut.hash(ub.getPassword()));
            ps.setInt(10, ub.getId_rol());
            //ps.setByte(11, ub.getFoto());
            ps.setInt(11, ub.getId_vendedor());
            ps.setString(12, ub.getSerie());
            //ps.setString(13, ub.getEstatus());
            ps.setInt(13, ub.getCreado_por());
            ps.setInt(14, ub.getModificado_por());
            ps.setString(15, ub.getEmail());
            
            ps.setString(16, ub.getComentario());
            ps.setInt(17, ub.getSucursal());

            resultado = ps.executeUpdate();

            if (resultado != 0) {
                resultado = secuencia;
            }
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Usuario.class.getName() + "-- metodo: setUsuario", LOGGER);
            
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Usuario.class.getName() + "-- metodo: setUsuario", LOGGER);
            }
            closeConexion();
        }

        return resultado;
        
    }

    /**
     *
     * @param ub
     * @return
     */
    @Override
    public int updateUsuario(UsuarioBean ub) {
        int resultado = 0;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "UPDATE conf_usuario\n"
                + "SET\n"
                + "nombre=?,\n"
                + "domicilio=?,\n"
                + "ciudad=?,\n"
                + "email=?,\n"
                + "telefono=?,\n"
                + "celular=?,\n"
                + "id_rol=?,\n"
                + "id_vendedor=?,\n"
                + "comentario=?,\n"
                + "modificado_por=?\n"
                + "WHERE id =?";
        
        
        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);

            ps.setString(1, ub.getNombre());
            ps.setString(2, ub.getDomicilio());
            ps.setString(3, ub.getCiudad());
            ps.setString(4, ub.getEmail());
            ps.setString(5, ub.getTelefono());
            ps.setString(6, ub.getCelular());
            
            //ps.setString(8, ub.getPassword());
            ps.setInt(7, ub.getId_rol());
            ps.setInt(8, ub.getId_vendedor());
            ps.setString(9, ub.getComentario());
            ps.setInt(10, ub.getModificado_por());
            ps.setInt(11, ub.getId());

            resultado = ps.executeUpdate();

        } catch (SQLException ex) {
            try {
                System.out.println("el método updateUsuario a lanzado el siguiente error " + ex.getMessage());
                throw new SqlAppsException(ex);
                //Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SqlAppsException ex1) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion();
        }

        return resultado;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    public int deleteUsuario(UsuarioBean ub) throws SqlAppsException {

        int resultado = 0;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "UPDATE conf_usuario \n"
                + "set estatus = 'D',\n"
                + "modificado_por = ?\n"
                + "WHERE id = ?";

        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);
            ps.setInt(1, ub.getModificado_por());
            ps.setInt(2, ub.getId());

            resultado = ps.executeUpdate();

        } catch (SQLException ex) {

            System.out.println("el método deleteCatUsuario a lanzado el siguiente error " + ex.getMessage());
            throw new SqlAppsException(ex);
            //Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion();
        }

        return resultado;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<UsuarioBean> getUsuarioLista() throws SqlAppsException {
        LinkedList<UsuarioBean> lista = new LinkedList<>();

        Statement stmt = null;
        ResultSet rs = null;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "SELECT id,\n"
                + "nombre,\n"
                + "dominicilio,\n"
                + "ciudad,\n"
                + "telefono,\n"
                + "celular,\n"
                + "activo,\n"
                + "usuario,\n"
                + "password,\n"
                + "id_rol,\n"
                + "foto,\n"
                + "id_vendedor,\n"
                + "serie,\n"
                + "estatus\n"
                + "FROM conf_usuarios WHERE estatus = 'A'";

        try {
            stmt = getStatement();

            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                UsuarioBean ub = new UsuarioBean();
                ub.setId(rs.getInt("id"));
                ub.setNombre(rs.getString("nombre"));
                ub.setDomicilio(rs.getString("domicilio"));
                ub.setCiudad(rs.getString("ciudad"));
                ub.setTelefono(rs.getString("telefono"));
                ub.setCelular(rs.getString("celular"));
                ub.setActivo(rs.getString("activo"));
                ub.setUsuario(rs.getString("usuario"));
                ub.setPassword(rs.getString("password"));
                ub.setId_rol(rs.getInt("id_rol"));
                ub.setFoto(rs.getByte("foto"));
                ub.setId_vendedor(rs.getInt("id_vendedor"));
                ub.setSerie(rs.getString("serie"));
                ub.setEstatus(rs.getString("estatus"));
                ub.setCreado_por(rs.getInt("creado_por"));
                ub.setFecha_creacion(rs.getString("fecha_creacion"));
                ub.setModificado_por(rs.getInt("modificado_por"));
                ub.setFecha_modificacion(rs.getString("modificado_por"));

                lista.add(ub);
            }

        } catch (SQLException ex) {
            System.out.println("el método getUsuario ha lanzado el siguiente error " + ex.getMessage());
            throw new SqlAppsException(ex);
            //Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion();
        }

        return lista.listIterator();

        
    }

    @Override
    public UsuarioBean getUsuario(int id) throws SqlAppsException {

        Statement stmt = null;
        ResultSet rs = null;
        UsuarioBean ub = new UsuarioBean();

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "SELECT id,\n"
                + "nombre,\n"
                + "dominicilio,\n"
                + "ciudad,\n"
                + "telefono,\n"
                + "celular,\n"
                + "activo,\n"
                + "usuario,\n"
                + "password,\n"
                + "id_rol,\n"
                + "foto,\n"
                + "id_vendedor,\n"
                + "serie,\n"
                + "estatus\n"
                + "FROM conf_usuario"
                + "WHERE id = ? "
                + "AND estatus = 'A'";

        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            while (rs.next()) {

                ub.setId(rs.getInt("id"));
                ub.setNombre(rs.getString("nombre"));
                ub.setDomicilio(rs.getString("domicilio"));
                ub.setCiudad(rs.getString("ciudad"));
                ub.setTelefono(rs.getString("telefono"));
                ub.setCelular(rs.getString("celular"));
                ub.setActivo(rs.getString("activo"));
                ub.setUsuario(rs.getString("usuario"));
                ub.setPassword(rs.getString("password"));
                ub.setId_rol(rs.getInt("id_rol"));
                ub.setFoto(rs.getByte("foto"));
                ub.setId_vendedor(rs.getInt("id_vendedor"));
                ub.setSerie(rs.getString("serie"));
                ub.setEstatus(rs.getString("estatus"));
                ub.setCreado_por(rs.getInt("creado_por"));
                ub.setFecha_creacion(rs.getString("fecha_creacion"));
                ub.setModificado_por(rs.getInt("modificado_por"));
                ub.setFecha_modificacion(rs.getString("modificado_por"));

            }
        } catch (SQLException ex) {
            System.out.println("El método getUsuario(id) a lanzado el siguiente erro " + ex.getMessage());
            throw new SqlAppsException(ex);
            //Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion();

        }

        return ub;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    

    @Override
    public JSONObject getUsuarioJSON(int draw) throws SqlAppsException {
        
        Statement stmt = null;
        ResultSet rs = null;
        int contador = 0;
        JSONObject usuarios = new JSONObject();
        JSONArray data = new JSONArray();

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "SELECT a.id,\n" +
        "a.nombre,\n" +
        "a.domicilio,\n" +
        "a.ciudad,\n" +
        "a.telefono,\n" +
        "a.celular,\n" +
        "a.activo,\n" +
        "a.usuario,\n" +
        "a.id_rol,\n" +
        "a.foto,\n" +
        "a.id_vendedor,\n" +
        "a.serie,\n" +
        "a.estatus,\n" +
        "b.descripcion rol,\n" +
        "c.nombre vendedor\n" +
        "FROM conf_usuario a\n" +
        "LEFT JOIN conf_rol b\n" +
        "ON a.id_rol = b.id \n" +
        "LEFT JOIN conf_vendedor c\n" +
        "ON a.id_vendedor = c.id\n" +
        "WHERE a.estatus = 'A'";
        
        try {
            stmt = getStatement();

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                
                JSONObject usuarioJSON = new JSONObject();
                
                usuarioJSON.put("id", rs.getInt("id"));
                usuarioJSON.put("nombre", rs.getString("nombre"));
                usuarioJSON.put("domicilio", rs.getString("domicilio"));
                usuarioJSON.put("ciudad", rs.getString("ciudad"));
                usuarioJSON.put("telefono", rs.getString("telefono"));
                usuarioJSON.put("celular", rs.getString("celular"));
                usuarioJSON.put("activo", rs.getString("activo"));
                usuarioJSON.put("usuario", rs.getString("usuario"));
                usuarioJSON.put("id_rol", rs.getInt("id_rol"));
                usuarioJSON.put("rol", rs.getString("rol"));
                usuarioJSON.put("id_vendedor", rs.getInt("id_vendedor"));
                usuarioJSON.put("vendedor", rs.getString("vendedor"));
                usuarioJSON.put("serie", rs.getString("serie"));
                
                
                
                data.add(usuarioJSON);
                
                contador++;

               
            }
            
            usuarios.put("data", data);

        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Usuario.class.getName() + "-- metodo: getUsuarioJSON", LOGGER);
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
               throw new SqlAppsException(ex, Usuario.class.getName() + "-- metodo: getUsuarioJSON", LOGGER);
            }

            try {
                if(stmt != null){
                
                    stmt.close();
                }
            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Usuario.class.getName() + "-- metodo: getUsuarioJSON", LOGGER);
            }

            closeConexion();
        }
        
        return usuarios;

    }

    @Override
    public ListIterator<UsuarioBean> getUsuarioLista(int id) throws SqlAppsException {
        
        LinkedList<UsuarioBean> lista = new LinkedList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "SELECT a.id,\n" +
        "a.nombre,\n" +
        "a.domicilio,\n" +
        "a.ciudad,\n" +
        "a.telefono,\n" +
        "a.celular,\n" +
        "a.activo,\n" +
        "a.usuario,\n" +
        "a.password,\n" +
        "a.id_rol,\n" +
        "a.foto,\n" +
        "a.id_vendedor,\n" +
        "a.serie,\n" +
        "a.estatus,\n" +
        "a.email,\n" +
        "a.creado_por,\n" +
        "date_format(a.fecha_creacion,'%d/%m/%Y %H:%i:%s') fecha_creacion,\n" +
        "a.modificado_por,\n" +
        "date_format(a.fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion,\n" +
        "a.comentario\n" +
        ",b.descripcion rol\n" +
        ",c.nombre vendedor\n" +
        "FROM conf_usuario a\n" +
        "LEFT JOIN conf_rol b\n" +
        "ON a.id_rol = b.id\n" +
        "LEFT JOIN conf_vendedor c\n" +
        "ON a.id_vendedor = c.id\n" +
        "WHERE a.id = ? AND a.estatus = 'A'";

        try {
            ps = getPrepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {

                UsuarioBean ub = new UsuarioBean();
                ub.setId(rs.getInt("id"));
                ub.setNombre(rs.getString("nombre"));
                ub.setDomicilio(rs.getString("domicilio"));
                ub.setCiudad(rs.getString("ciudad"));
                ub.setTelefono(rs.getString("telefono"));
                ub.setCelular(rs.getString("celular"));
                ub.setActivo(rs.getString("activo"));
                ub.setUsuario(rs.getString("usuario"));
                ub.setPassword(rs.getString("password"));
                ub.setId_rol(rs.getInt("id_rol"));
                ub.setFoto(rs.getByte("foto"));
                ub.setId_vendedor(rs.getInt("id_vendedor"));
                ub.setSerie(rs.getString("serie"));
                ub.setEstatus(rs.getString("estatus"));
                ub.setCreado_por(rs.getInt("creado_por"));
                ub.setFecha_creacion(rs.getString("fecha_creacion"));
                ub.setModificado_por(rs.getInt("modificado_por"));
                ub.setFecha_modificacion(rs.getString("modificado_por"));
                ub.setEmail(rs.getString("email"));
                ub.setRol(rs.getString("rol"));
                ub.setVendedor(rs.getString("vendedor"));
                ub.setComentario(rs.getString("comentario"));

                lista.add(ub);
            }

        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Usuario.class.getName() + "-- metodo: getUsuarioLista(id)", LOGGER);
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
               throw new SqlAppsException(ex, Usuario.class.getName() + "-- metodo: getUsuarioLista(id)", LOGGER);
            }

            try {
                closePStmt();
            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Usuario.class.getName() + "-- metodo: getUsuarioLista(id)", LOGGER);
            }

            closeConexion();
        }

        return lista.listIterator();
        
    }

    @Override
    public int updatePassword(UsuarioBean ub) throws SqlAppsException {
        
        int resultado = 0;
        
        getConexion(EDriver.MYSQL, EApps.PV);
        UtilsDao util = new UtilsDao();
        String sql = "UPDATE conf_usuario set password = ?, modificado_por = ? WHERE id = ?";
        
        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setString(1, util.hash(ub.getPassword().toUpperCase()));
            ps.setInt(2, ub.getModificado_por());
            ps.setInt(3, ub.getId());
            
            resultado = ps.executeUpdate();
            
        } catch (SQLException ex) {

            System.out.println("el método updatePassword a lanzado el siguiente error " + ex.getMessage());
            throw new SqlAppsException(ex);
            
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion();
        }

        return resultado;
        
    }
    
    @Override
    public UsuarioBean getUsuarioInfo(String usuario) throws SqlAppsException {
        
        UsuarioBean ub = new UsuarioBean();
        ResultSet rs = null;
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "select a.id\n" +
        ",a.nombre\n" +
        ",a.domicilio\n" +
        ",a.ciudad\n" +
        ",a.telefono\n" +
        ",a.celular\n" +
        ",a.usuario\n" +
        ",a.id_rol\n" +
        ",a.id_vendedor\n" +
        ",a.activo\n" +
        ",a.serie\n" +
        ",a.email\n" +
        ",a.estatus\n" +        
        ",b.descripcion rol\n" +
        ",c.nombre vendedor\n" +
        "from conf_usuario a\n" +
        "LEFT JOIN conf_rol b\n" +
        "ON a.id_rol = b.id\n" +
        "LEFT JOIN conf_vendedor c\n" +
        "ON a.id_vendedor = c.id \n" +
        "WHERE a.usuario = ?";
        
        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setString(1, usuario);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                ub.setId(rs.getInt("id"));
                ub.setNombre(rs.getString("nombre"));
                ub.setDomicilio(rs.getString("domicilio"));
                ub.setCiudad(rs.getString("ciudad"));
                ub.setTelefono(rs.getString("telefono"));
                ub.setCelular(rs.getString("celular"));
                ub.setActivo(rs.getString("activo"));
                ub.setUsuario(rs.getString("usuario"));
                ub.setId_rol(rs.getInt("id_rol"));
                ub.setId_vendedor(rs.getInt("id_vendedor"));
                ub.setSerie(rs.getString("serie"));
                ub.setEstatus(rs.getString("estatus"));
                ub.setRol(rs.getString("rol"));
                ub.setVendedor(rs.getString("vendedor"));
            }
            
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Usuario.class.getName() + "-- metodo: getUsuarioInfo", LOGGER);
            
        } finally {
            
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            try {
                closePStmt();
            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Usuario.class.getName() + "-- metodo: getUsuarioInfo", LOGGER);
            }
            closeConexion();
        }
        
        
        return ub;
    }
    
    
    public int setSucursalUsuario(int sucursal, Connection cnn) throws SqlAppsException{
    
        int resultado = 0;
        PreparedStatement ps = null;
        
        String sql = "UPDATE conf_usuario set sucursal = ?";
        
        try {
            ps = getPrepareStatement(sql);
            
            ps.setInt(1, sucursal);
            
            
            resultado = ps.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        
        
        
        return resultado;
    }
    

}
