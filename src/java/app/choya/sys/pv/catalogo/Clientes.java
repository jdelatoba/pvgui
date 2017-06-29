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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
 *
 * @author Rembao
 */
public class Clientes extends GenericDAO{
    
    
    
    static final Logger LOGGER = Logger.getLogger(Clientes.class.getName());
    
    
    public int setClientes(ClienteBean cb, ClienteDomicilioBean db) throws SqlAppsException{
        
        int resultado = 0;
        int id_domicilio = 0;
        int id_cliente = 0;
        
        Connection cnn = getConexionSinCommit(EDriver.MYSQL, EApps.PV);
        
        
        String sql1 = "INSERT INTO cat_cliente\n" +
        "(id,\n" +
        "clave,\n" +
        "representante,\n" +
        "nombre,\n" +
        "rfc,\n" +
        "curp,\n" +
        "creado_por,\n" +
        "modificado_por)\n" +
        "VALUES\n" +
        "(?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?)";
        
        
        PreparedStatement ps = null;
        
        try {
            ps = getPrepareStatement(sql1);
            UtilsDao ut = new UtilsDao();
            id_cliente = ut.nextVal("s_cat_cliente",cnn);
            
            
            ps.setInt(1, id_cliente);
            ps.setString(2, cb.getClave());
            ps.setString(3, cb.getRepresentante());
            ps.setString(4, cb.getNombre());
            ps.setString(5, cb.getRfc());
            ps.setString(6, cb.getCurp());
            ps.setInt(7, cb.getCreado_por());
            ps.setInt(8, cb.getModificado_por());
            
            resultado = ps.executeUpdate();
            
            
            if(id_cliente != 0){
                
                String sql2 = "INSERT INTO cat_cliente_domicilio\n" +
                "(id,\n" +
                "id_cliente,\n" +
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
                "dias_credito,\n" +
                "creado_por,\n" +
                "modificado_por)\n" +
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
                "?)";
                
                ps = getPrepareStatement(sql2);
                
                
                
                
                id_domicilio = ut.nextVal("s_cat_cliente_domicilio",cnn);
                ps.setInt(1, id_domicilio);
                ps.setInt(2, id_cliente);
                ps.setString(3, db.getDomicilio());
                ps.setString(4, db.getNum_ext());
                ps.setString(5, db.getNum_int());
                ps.setString(6, db.getColonia());
                ps.setString(7, db.getCodigo_postal());
                ps.setString(8, db.getLocalidad());
                ps.setString(9, db.getMunicipio());
                ps.setString(10, db.getEntidad());
                ps.setString(11, db.getPais());
                ps.setString(12, db.getTelefono());
                ps.setString(13, db.getCelular());
                ps.setString(14, db.getEmail());
                ps.setString(15, db.getComentario());
                ps.setInt(16, db.getAplica_retencion());
                ps.setInt(17, db.getNo_precio());
                ps.setDouble(18, db.getLimite_credito());
                ps.setInt(19, db.getDias_credito());
                ps.setInt(20, db.getCreado_por());
                ps.setInt(21, db.getModificado_por());
                
                
                resultado = ps.executeUpdate();
                commit();
                
                
            } 
            
        } catch (SQLException ex) {
            rollback();
            throw new SqlAppsException(ex, Clientes.class.getName() + "-- metodo: setClientes", LOGGER);
        } finally{
            
            try {
                if(ps != null){
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
        }
            
            
        
        
        return resultado;
    
    
    }
    
    
    
    public ClienteResultadoBean setClienteDomicilio(ClienteBean cb, ClienteDomicilioBean db) throws SqlAppsException{
        
        ClienteResultadoBean cliente = new ClienteResultadoBean();
        int resultado = 0;
        int id_domicilio = 0;
        int id_cliente = 0;
        
        Connection cnn = getConexionSinCommit(EDriver.MYSQL, EApps.PV);
        
        
        String sql1 = "INSERT INTO cat_cliente\n" +
        "(id,\n" +
        "clave,\n" +
        "representante,\n" +
        "nombre,\n" +
        "rfc,\n" +
        "curp,\n" +
        "creado_por,\n" +
        "modificado_por)\n" +
        "VALUES\n" +
        "(?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?)";
        
        
        PreparedStatement ps = null;
        
        try {
            ps = getPrepareStatement(sql1);
            UtilsDao ut = new UtilsDao();
            id_cliente = ut.nextVal("s_cat_cliente",cnn);
            
            
            ps.setInt(1, id_cliente);
            ps.setString(2, cb.getClave());
            ps.setString(3, cb.getRepresentante());
            ps.setString(4, cb.getNombre());
            ps.setString(5, cb.getRfc());
            ps.setString(6, cb.getCurp());
            ps.setInt(7, cb.getCreado_por());
            ps.setInt(8, cb.getModificado_por());
            
            ps.executeUpdate();
            cliente.setId_cliente(id_cliente);
            
            if(id_cliente != 0){
                
                String sql2 = "INSERT INTO cat_cliente_domicilio\n" +
                "(id,\n" +
                "id_cliente,\n" +
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
                "dias_credito,\n" +
                "creado_por,\n" +
                "modificado_por)\n" +
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
                "?)";
                
                ps = getPrepareStatement(sql2);
                
                
                
                
                id_domicilio = ut.nextVal("s_cat_cliente_domicilio",cnn);
                ps.setInt(1, id_domicilio);
                ps.setInt(2, id_cliente);
                ps.setString(3, db.getDomicilio());
                ps.setString(4, db.getNum_ext());
                ps.setString(5, db.getNum_int());
                ps.setString(6, db.getColonia());
                ps.setString(7, db.getCodigo_postal());
                ps.setString(8, db.getLocalidad());
                ps.setString(9, db.getMunicipio());
                ps.setString(10, db.getEntidad());
                ps.setString(11, db.getPais());
                ps.setString(12, db.getTelefono());
                ps.setString(13, db.getCelular());
                ps.setString(14, db.getEmail());
                ps.setString(15, db.getComentario());
                ps.setInt(16, db.getAplica_retencion());
                ps.setInt(17, db.getNo_precio());
                ps.setDouble(18, db.getLimite_credito());
                ps.setInt(19, db.getDias_credito());
                ps.setInt(20, db.getCreado_por());
                ps.setInt(21, db.getModificado_por());
                
                
                resultado = ps.executeUpdate();
                cliente.setId_domicilio(id_domicilio);
                cliente.setResultado(1);
                commit();
                
                
            } 
            
        } catch (SQLException ex) {
            rollback();
            throw new SqlAppsException(ex, Clientes.class.getName() + "-- metodo: setClientes", LOGGER);
        } finally{
            
            try {
                if(ps != null){
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
        }
            
            
        
        
        return cliente;
    
    
    }
    
    
    public int updateClientes(ClienteBean cb, ClienteDomicilioBean db) throws SqlAppsException{
        
        int resultado = 0;
        
        Connection cnn = getConexionSinCommit(EDriver.MYSQL, EApps.PV);
        
        String sql1 = "UPDATE cat_cliente\n" +
        "SET\n" +
        "clave = ?,\n" +
        "representante = ?,\n" +
        "nombre = ?,\n" +
        "rfc = ?,\n" +
        "curp = ?,\n" +
        "modificado_por = ?\n" +
        "WHERE id = ?";
        
        PreparedStatement ps = null;
        
        
        try {
            ps = getPrepareStatement(sql1);
            ps.setString(1, cb.getClave());
            ps.setString(2, cb.getRepresentante());
            ps.setString(3, cb.getNombre());
            ps.setString(4, cb.getRfc());
            ps.setString(5, cb.getCurp());
            ps.setInt(6, cb.getModificado_por());
            ps.setInt(7, cb.getId());
            
            resultado = ps.executeUpdate();
            
            if(resultado != 0){
                
                String sql2 = "UPDATE cat_cliente_domicilio\n" +
                "SET\n" +
                "domicilio = ?,\n" +
                "num_ext = ?,\n" +
                "num_int = ?,\n" +
                "colonia = ?,\n" +
                "codigo_postal = ?,\n" +
                "localidad = ?,\n" +
                "municipio = ?,\n" +
                "entidad = ?,\n" +
                "pais = ?,\n" +
                "telefono = ?,\n" +
                "celular = ?,\n" +
                "email = ?,\n" +
                "comentario = ?,\n" +
                "aplica_retencion = ?,\n" +
                "no_precio = ?,\n" +
                "limite_credito = ?,\n" +
                "dias_credito = ?,\n" +
                "modificado_por = ?\n" +
                "WHERE id = ?";
                
                ps = getPrepareStatement(sql2);
                ps.setString(1, db.getDomicilio());
                ps.setString(2, db.getNum_ext());
                ps.setString(3, db.getNum_int());
                ps.setString(4, db.getColonia());
                ps.setString(5, db.getCodigo_postal());
                ps.setString(6, db.getLocalidad());
                ps.setString(7, db.getMunicipio());
                ps.setString(8, db.getEntidad());
                ps.setString(9, db.getPais());
                ps.setString(10,db.getTelefono());
                ps.setString(11, db.getCelular());
                ps.setString(12, db.getEmail());
                ps.setString(13, db.getComentario());
                ps.setInt(14, db.getAplica_retencion());
                ps.setInt(15, db.getNo_precio());
                ps.setDouble(16, db.getLimite_credito());
                ps.setInt(17, db.getDias_credito());
                ps.setInt(18, db.getModificado_por());
                ps.setInt(19, db.getId());
                
                //System.out.println(ps);
                resultado = ps.executeUpdate();
            
            }
           commit();
        } catch (SQLException ex) {
            resultado = 0;
            rollback();
            throw new SqlAppsException(ex, Clientes.class.getName() + "-- metodo: updateClientes", LOGGER);
            
        } finally{
            
            try {
                if(ps != null){
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
        }
        
        
        return resultado;
    
    
    }
    
    
    
    public ListIterator<ClientesBean> getClientes() throws SqlAppsException{
    
        LinkedList<ClientesBean> lista = new LinkedList<>();
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        Statement stmt = null;
        ResultSet rs = null;
        
        String sql = "select\n" +
        "a.id\n" +
        ",a.serie\n" +
        ",a.clave\n" +
        ",a.representante\n" +
        ",a.nombre\n" +
        ",a.rfc\n" +
        ",a.curp\n" +
        ",a.creado_por\n" +
        ",a.fecha_creacion\n" +
        ",a.modificado_por\n" +
        ",a.fecha_modificacion\n" +
        ",b.id as id_domicilio\n" +
        ",b.domicilio\n" +
        ",b.num_ext\n" +
        ",b.num_int\n" +
        ",b.colonia\n" +
        ",b.codigo_postal\n" +
        ",b.localidad\n" +
        ",b.municipio\n" +
        ",b.entidad\n" +
        ",b.pais\n" +
        ",b.telefono\n" +
        ",b.celular\n" +
        ",b.email\n" +
        ",b.comentario\n" +
        ",b.aplica_retencion\n" +
        ",b.no_precio\n" +
        ",b.limite_credito\n" +
        ",b.dias_credito\n" +
        "FROM cat_cliente a\n" +
        "LEFT JOIN cat_cliente_domicilio b\n" +
        "ON a.id = b.id_cliente\n" +
        "WHERE a.estatus = 'A'\n" +
        "order by a.nombre";
        
        
        try {
            stmt = getStatement();
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                
                ClientesBean cb = new ClientesBean();
                
                cb.setId(rs.getInt("id"));
                cb.setSerie(rs.getString("serie"));
                cb.setClave(rs.getString("clave"));
                cb.setRepresentante(rs.getString("representante"));
                cb.setNombre(rs.getString("nombre"));
                cb.setRfc(rs.getString("rfc"));
                cb.setCurp(rs.getString("curp"));
                cb.setCreado_por(rs.getInt("creado_por"));
                cb.setFecha_creacion(rs.getString("fecha_creacion"));
                cb.setModificado_por(rs.getInt("modificado_por"));
                cb.setFecha_modificacion(rs.getString("fecha_modificacion"));
                cb.setId_domicilio(rs.getInt("id_domicilio"));
                cb.setDomicilio(rs.getString("domicilio"));
                cb.setNum_ext(rs.getString("num_ext"));
                cb.setNum_int(rs.getString("num_int"));
                cb.setColonia(rs.getString("colonia"));
                cb.setCodigo_postal(rs.getString("codigo_postal"));
                cb.setLocalidad(rs.getString("localidad"));
                cb.setMunicipio(rs.getString("municipio"));
                cb.setEntidad(rs.getString("entidad"));
                cb.setPais(rs.getString("pais"));
                cb.setTelefono(rs.getString("telefono"));
                cb.setCelular(rs.getString("celular"));
                cb.setEmail(rs.getString("email"));
                cb.setComentario(rs.getString("comentario"));
                cb.setAplica_retencion(rs.getInt("aplica_retencion"));
                cb.setNo_precio(rs.getInt("no_precio"));
                cb.setLimite_credito(rs.getDouble("limite_credito"));
                cb.setDias_credito(rs.getInt("dias_credito"));
                
                lista.add(cb);
                
                
            
            }
            
            
        } catch (SQLException ex) {
            
            throw new SqlAppsException(ex, Clientes.class.getName() + "-- metodo: getClientes", LOGGER);
        }finally{
            
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            closeConexion();
        
        }
        
        
        return lista.listIterator();
    }
    
    
    
    public ListIterator<ClientesBean> getClientes(int id) throws SqlAppsException{
    
        LinkedList<ClientesBean> lista = new LinkedList<>();
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "select\n" +
        "a.id\n" +
        ",a.serie\n" +
        ",a.clave\n" +
        ",a.representante\n" +
        ",a.nombre\n" +
        ",a.rfc\n" +
        ",a.curp\n" +
        ",a.creado_por\n" +
        ",a.fecha_creacion\n" +
        ",a.modificado_por\n" +
        ",a.fecha_modificacion\n" +
        ",b.id as id_domicilio\n" +
        ",b.domicilio\n" +
        ",b.num_ext\n" +
        ",b.num_int\n" +
        ",b.colonia\n" +
        ",b.codigo_postal\n" +
        ",b.localidad\n" +
        ",b.municipio\n" +
        ",b.entidad\n" +
        ",b.pais\n" +
        ",b.telefono\n" +
        ",b.celular\n" +
        ",b.email\n" +
        ",b.comentario\n" +
        ",b.aplica_retencion\n" +
        ",b.no_precio\n" +
        ",b.limite_credito\n" +
        ",b.dias_credito\n" +
        "FROM cat_cliente a\n" +
        "LEFT JOIN cat_cliente_domicilio b\n" +
        "ON a.id = b.id_cliente\n" +
        "WHERE a.id = ? AND a.estatus = 'A'";
        
        
        
        try {
            ps = getPrepareStatement(sql);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                ClientesBean cb = new ClientesBean();
                
                cb.setId(rs.getInt("id"));
                cb.setSerie(rs.getString("serie"));
                cb.setClave(rs.getString("clave"));
                cb.setRepresentante(rs.getString("representante"));
                cb.setNombre(rs.getString("nombre"));
                cb.setRfc(rs.getString("rfc"));
                cb.setCurp(rs.getString("curp"));
                cb.setCreado_por(rs.getInt("creado_por"));
                cb.setFecha_creacion(rs.getString("fecha_creacion"));
                cb.setModificado_por(rs.getInt("modificado_por"));
                cb.setFecha_modificacion(rs.getString("fecha_modificacion"));
                cb.setId_domicilio(rs.getInt("id_domicilio"));
                cb.setDomicilio(rs.getString("domicilio"));
                cb.setNum_ext(rs.getString("num_ext"));
                cb.setNum_int(rs.getString("num_int"));
                cb.setColonia(rs.getString("colonia"));
                cb.setCodigo_postal(rs.getString("codigo_postal"));
                cb.setLocalidad(rs.getString("localidad"));
                cb.setMunicipio(rs.getString("municipio"));
                cb.setEntidad(rs.getString("entidad"));
                cb.setPais(rs.getString("pais"));
                cb.setTelefono(rs.getString("telefono"));
                cb.setCelular(rs.getString("celular"));
                cb.setEmail(rs.getString("email"));
                cb.setComentario(rs.getString("comentario"));
                cb.setAplica_retencion(rs.getInt("aplica_retencion"));
                cb.setNo_precio(rs.getInt("no_precio"));
                cb.setLimite_credito(rs.getDouble("limite_credito"));
                cb.setDias_credito(rs.getInt("dias_credito"));
                
                lista.add(cb);
                
                
            
            }
            
            
        } catch (SQLException ex) {
            
            throw new SqlAppsException(ex, Clientes.class.getName() + "-- metodo: getClientes", LOGGER);
        }finally{
            
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
        
        }
        
        
        return lista.listIterator();
    }
    
    
    
    public JSONObject getClientesListaJSON() throws SqlAppsException{
    
        
        
        
        Statement stmt = null;
        ResultSet rs = null;
        int contador = 0;
        JSONObject cliente = new JSONObject();
        JSONArray data = new JSONArray();
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "select\n" +
        "a.id\n" +
        ",a.serie\n" +
        ",a.clave\n" +
        ",a.representante\n" +
        ",a.nombre\n" +
        ",a.rfc\n" +
        ",a.curp\n" +
        ",a.creado_por\n" +
        ",a.fecha_creacion\n" +
        ",a.modificado_por\n" +
        ",a.fecha_modificacion\n" +
        ",b.id as id_domicilio\n" +
        ",b.domicilio\n" +
        ",b.num_ext\n" +
        ",b.num_int\n" +
        ",b.colonia\n" +
        ",b.codigo_postal\n" +
        ",b.localidad\n" +
        ",b.municipio\n" +
        ",b.entidad\n" +
        ",b.pais\n" +
        ",b.telefono\n" +
        ",b.celular\n" +
        ",b.email\n" +
        ",b.comentario\n" +
        ",b.aplica_retencion\n" +
        ",b.no_precio\n" +
        ",b.limite_credito\n" +
        ",b.dias_credito\n" +
        "FROM cat_cliente a\n" +
        "LEFT JOIN cat_cliente_domicilio b\n" +
        "ON a.id = b.id_cliente\n" +
        "WHERE a.estatus = 'A' ORDER BY a.nombre";
        
        
        
        try {
            stmt = getStatement();
            
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                
                JSONObject clienteJSON = new JSONObject();
                
                clienteJSON.put("id", rs.getInt("id"));
                clienteJSON.put("serie",rs.getString("serie"));
                clienteJSON.put("clave", rs.getString("clave"));
                clienteJSON.put("representante", rs.getString("representante"));
                clienteJSON.put("nombre", rs.getString("nombre"));
                clienteJSON.put("rfc", rs.getString("rfc"));
                clienteJSON.put("curp", rs.getString("curp"));
                clienteJSON.put("creado_por", rs.getString("creado_por"));
                clienteJSON.put("fecha_creacion", rs.getString("fecha_creacion"));
                clienteJSON.put("modificado_por", rs.getString("modificado_por"));
                clienteJSON.put("fecha_modificacion", rs.getString("fecha_modificacion"));
                clienteJSON.put("id_domicilio", rs.getInt("id_domicilio"));
                clienteJSON.put("domicilio", rs.getString("domicilio"));
                clienteJSON.put("num_ext", rs.getString("num_ext"));
                clienteJSON.put("num_int", rs.getString("num_int"));
                clienteJSON.put("colonia", rs.getString("colonia"));
                clienteJSON.put("codigo_postal", rs.getString("codigo_postal"));
                clienteJSON.put("localidad", rs.getString("localidad"));
                clienteJSON.put("municipio", rs.getString("municipio"));
                clienteJSON.put("entidad", rs.getString("entidad"));
                clienteJSON.put("pais", rs.getString("pais"));
                clienteJSON.put("telefono", rs.getString("telefono"));
                clienteJSON.put("celular", rs.getString("celular"));
                clienteJSON.put("email", rs.getString("email"));
                clienteJSON.put("comentario", rs.getString("comentario"));
                clienteJSON.put("aplica_retencion", rs.getInt("aplica_retencion"));
                clienteJSON.put("no_precio", rs.getInt("no_precio"));
                clienteJSON.put("limite_credito", rs.getDouble("limite_credito"));
                clienteJSON.put("dias_credito", rs.getInt("dias_credito"));

                data.add(clienteJSON);
                
                contador++;
                    
                
            
            }
            
            cliente.put("data", data);
            
        } catch (SQLException ex) {
            
            throw new SqlAppsException(ex, Clientes.class.getName() + "-- metodo: getClientes", LOGGER);
        }finally{
            
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
        
        }
        
        
        return cliente;
    }
    
    
    
    public JSONObject getClientesDomicilioListaJSON(int id_cliente) throws SqlAppsException{
    
        
        
        
        PreparedStatement ps  = null;
        ResultSet rs = null;
        int contador = 0;
        JSONObject cliente = new JSONObject();
        JSONArray data = new JSONArray();
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "select\n" +
        "a.id as id_cliente\n" +
        ",b.id as id_domicilio\n" +
        ",concat(b.domicilio,' Núm. Int. ',b.num_ext,' Núm. Ext. ',b.num_int,' C.P. ',b.colonia) as domicilio\n" +
        ",b.num_ext\n" +
        ",b.num_int\n" +
        ",b.colonia\n" +
        ",b.codigo_postal\n" +
        ",b.localidad\n" +
        ",b.municipio\n" +
        ",b.entidad\n" +
        ",b.pais\n" +
        ",b.telefono\n" +
        ",b.celular\n" +
        ",b.email\n" +
        ",b.comentario\n" +
        ",b.aplica_retencion\n" +
        ",b.no_precio\n" +
        ",b.limite_credito\n" +
        ",b.dias_credito\n" +
        "FROM cat_cliente a\n" +
        "LEFT JOIN cat_cliente_domicilio b\n" +
        "ON a.id = b.id_cliente\n" +
        "WHERE a.id = ? AND a.estatus = 'A' " 
        + "ORDER BY b.id";
        
        
        
        try {
            ps = getPrepareStatement(sql);
            ps.setInt(1, id_cliente);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                JSONObject clienteJSON = new JSONObject();
                
                clienteJSON.put("id_cliente", rs.getInt("id_cliente"));
                clienteJSON.put("id_domicilio", rs.getInt("id_domicilio"));
                clienteJSON.put("domicilio", rs.getString("domicilio"));
                clienteJSON.put("num_ext", rs.getString("num_ext"));
                clienteJSON.put("num_int", rs.getString("num_int"));
                clienteJSON.put("colonia", rs.getString("colonia"));
                clienteJSON.put("codigo_postal", rs.getString("codigo_postal"));
                clienteJSON.put("localidad", rs.getString("localidad"));
                clienteJSON.put("municipio", rs.getString("municipio"));
                clienteJSON.put("entidad", rs.getString("entidad"));
                clienteJSON.put("pais", rs.getString("pais"));
                clienteJSON.put("telefono", rs.getString("telefono"));
                clienteJSON.put("celular", rs.getString("celular"));
                clienteJSON.put("email", rs.getString("email"));
                clienteJSON.put("comentario", rs.getString("comentario"));
                clienteJSON.put("aplica_retencion", rs.getInt("aplica_retencion"));
                clienteJSON.put("no_precio", rs.getInt("no_precio"));
                clienteJSON.put("limite_credito", rs.getDouble("limite_credito"));
                clienteJSON.put("dias_credito", rs.getInt("dias_credito"));

                data.add(clienteJSON);
                
                contador++;
                    
                
            
            }
            
            cliente.put("data", data);
            
        } catch (SQLException ex) {
            
            throw new SqlAppsException(ex, Clientes.class.getName() + "-- metodo: getClientes", LOGGER);
        }finally{
            
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
        
        }
        
        
        return cliente;
    }
    
    
    
    
    
    public int setDomicilioAlterno(ClienteDomicilioBean db) throws SqlAppsException{
        
        int resultado = 0;
        int id_domicilio = 0;
        int id_cliente = 0;
        
        Connection cnn = getConexionSinCommit(EDriver.MYSQL, EApps.PV);
        
        
        PreparedStatement ps = null;
        
        try {
            
            UtilsDao ut = new UtilsDao();
            
            
            
                
                String sql = "INSERT INTO cat_cliente_domicilio\n" +
                "(id,\n" +
                "id_cliente,\n" +
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
                "dias_credito,\n" +
                "creado_por,\n" +
                "modificado_por)\n" +
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
                "?)";
                
                ps = getPrepareStatement(sql);
                
                
                
                
                id_domicilio = ut.nextVal("s_cat_cliente_domicilio",cnn);
                
                ps.setInt(1, id_domicilio);
                ps.setInt(2, db.getId_cliente());
                ps.setString(3, db.getDomicilio());
                ps.setString(4, db.getNum_ext());
                ps.setString(5, db.getNum_int());
                ps.setString(6, db.getColonia());
                ps.setString(7, db.getCodigo_postal());
                ps.setString(8, db.getLocalidad());
                ps.setString(9, db.getMunicipio());
                ps.setString(10, db.getEntidad());
                ps.setString(11, db.getPais());
                ps.setString(12, db.getTelefono());
                ps.setString(13, db.getCelular());
                ps.setString(14, db.getEmail());
                ps.setString(15, db.getComentario());
                ps.setInt(16, db.getAplica_retencion());
                ps.setInt(17, db.getNo_precio());
                ps.setDouble(18, db.getLimite_credito());
                ps.setInt(19, db.getDias_credito());
                ps.setInt(20, db.getCreado_por());
                ps.setInt(21, db.getModificado_por());
                
                System.out.println(ps);
                resultado = ps.executeUpdate();
                commit();
                
                
            
            
        } catch (SQLException ex) {
            rollback();
            throw new SqlAppsException(ex, Clientes.class.getName() + "-- metodo: setDomicilioAlterno", LOGGER);
        } finally{
            
            try {
                if(ps != null){
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
        }
            
            
        
        
        return resultado;
    
    
    }
    

    
}


