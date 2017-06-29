/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.nube;

import app.choya.sys.pv.configuracion.Empresa;
import app.choya.sys.pv.configuracion.Usuario;
import app.choya.sys.pv.dao.EApps;
import app.choya.sys.pv.dao.EDriver;
import app.choya.sys.pv.dao.GenericDAO;
import app.choya.sys.pv.utils.SqlAppsException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Rembao
 */
public class Sucursal extends GenericDAO{

    public Sucursal() {
    }
    
    public int setSucursal(SucursalBean sb) throws SqlAppsException{
        PreparedStatement ps = null;
        int resultado = 0;
        int num_sucursal = 0;
        
        Connection cnn = getConexionSinCommit(EDriver.MYSQL, EApps.PV);
        
        num_sucursal = agregarSucursal(sb.getDescripcion(), sb.getTipo(), sb.getSync_tiempo(), sb.getSync_proveedores(), sb.getCreado_por(), sb.getSync_clientes(), sb.getUsuario(), sb.getContrasena(), sb.getIp(), sb.getHost());
        
        if(num_sucursal > 0 ){
            String sql = "INSERT INTO conf_sucursal\n" +
            "(id,\n" +
            "descripcion,\n" +
            "tipo,\n" +
            "sync_tiempo,\n" +
            "sync_proveedores,\n" +
            "sync_clientes,\n" +
            "estatus,\n" +
            "creado_por,\n" +
            "modificado_por,\n" +
            "usuario,\n" +
            "contrasena)\n" +
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
            "?)";


            try {
                
                ps = cnn.prepareStatement(sql);
                ps.setInt(1, num_sucursal); // numero de sucursal se asigna desde la nube
                ps.setString(2, sb.getDescripcion());
                ps.setInt(3, sb.getTipo());
                ps.setInt(4, sb.getSync_tiempo());
                ps.setInt(5, sb.getSync_proveedores());
                ps.setInt(6, sb.getSync_clientes());
                ps.setString(7, "A");
                ps.setInt(8, sb.getCreado_por());
                ps.setInt(9, sb.getModificado_por());
                ps.setString(10, sb.getUsuario());
                ps.setString(11, sb.getContrasena());
                
                resultado = ps.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(Sucursal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
        
        }
        
        
        return resultado;
        
    }
    
    
    
    public JSONObject setSucursalM2(SucursalBean sb) throws SqlAppsException{
        PreparedStatement ps = null;
        long num = 0l;
        int num_sucursal = 0;
        JSONObject json = new JSONObject();
        String resultado;
        Connection cnn = getConexionSinCommit(EDriver.MYSQL, EApps.PV);
        
        resultado = agregarSucursalM2(sb.getDescripcion(), sb.getTipo(), sb.getSync_tiempo(), sb.getSync_proveedores(), sb.getCreado_por(), sb.getSync_clientes(), sb.getUsuario(), sb.getContrasena(), sb.getIp(), sb.getHost());
        JSONParser parser = new JSONParser();
        try {
            
            json = (JSONObject) parser.parse(resultado);
            JSONObject data = (JSONObject) json.get("data");
            System.out.println("resultado              "+data.get("resultado"));
            System.out.println("mensaje                "+data.get("mensaje"));
            System.out.println("exception_codigo_error "+data.get("exception_codigo_error"));
            System.out.println("exception_mensaje      "+data.get("exception_mensaje"));
            num_sucursal  = (int)(long) data.get("resultado");
            
            
        } catch (ParseException ex) {
            Logger.getLogger(Sucursal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(num_sucursal > 0 ){
            String sql = "INSERT INTO conf_sucursal\n" +
            "(id,\n" +
            "descripcion,\n" +
            "tipo,\n" +
            "sync_tiempo,\n" +
            "sync_proveedores,\n" +
            "sync_clientes,\n" +
            "estatus,\n" +
            "creado_por,\n" +
            "modificado_por,\n" +
            "usuario,\n" +
            "contrasena)\n" +
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
            "?)";
            
            

            try {
                
                Empresa empresa = new Empresa();
                empresa.setNumSucursal(num_sucursal, cnn);
            
                Usuario usuario = new Usuario();
                usuario.setSucursalUsuario(num_sucursal, cnn);
            
                System.out.println("numero de sucursal a insertar "+num_sucursal);
                ps = cnn.prepareStatement(sql);
                ps.setInt(1, num_sucursal); // numero de sucursal se asigna desde la nube
                ps.setString(2, sb.getDescripcion());
                ps.setInt(3, sb.getTipo());
                ps.setInt(4, sb.getSync_tiempo());
                ps.setInt(5, sb.getSync_proveedores());
                ps.setInt(6, sb.getSync_clientes());
                ps.setString(7, "A");
                ps.setInt(8, sb.getCreado_por());
                ps.setInt(9, sb.getModificado_por());
                ps.setString(10, sb.getUsuario());
                ps.setString(11, sb.getContrasena());
                
                int res = ps.executeUpdate();
                System.out.println("resultado de insertar sucursal "+res);
            commit();    
            } catch (SQLException ex) {
                rollback();
                Logger.getLogger(Sucursal.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
            
            
            }
        }
        
        
        
        return json;
        
    }
    
    
    public ListIterator<SucursalBean> getSucursalLocal(int id) throws SqlAppsException{
        LinkedList<SucursalBean> lista = new LinkedList<>();
        ResultSet rs = null;
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "SELECT id,\n" +
        "descripcion,\n" +
        "tipo,\n" +
        "sync_tiempo,\n" +
        "sync_proveedores,\n" +
        "sync_clientes,\n" +
        "estatus,\n" +
        "creado_por,\n" +
        "fecha_creacion,\n" +
        "modificado_por,\n" +
        "fecha_modificacion,\n" +
        "usuario,\n" +
        "contrasena\n" +
        "FROM conf_sucursal\n" +
        "WHERE id = ?";
        
        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            
            while(rs.next()){
                SucursalBean sb = new SucursalBean();
                sb.setId(rs.getInt("id"));
                sb.setDescripcion(rs.getString("descripcion"));
                sb.setSync_tiempo(rs.getInt("sync_tiempo"));
                sb.setSync_proveedores(rs.getInt("sync_proveedores"));
                sb.setSync_clientes(rs.getInt("sync_clientes"));
                sb.setEstatus(rs.getString("estatus"));
                sb.setCreado_por(rs.getInt("creado_por"));
                sb.setModificado_por(rs.getInt("modificado_por"));
                sb.setFecha_creacion(rs.getString("fecha_creacion"));
                sb.setFecha_modificacion(rs.getString("fecha_modificacion"));
                sb.setUsuario(rs.getString("usuario"));
                sb.setContrasena(rs.getString("contrasena"));
                
                lista.add(sb);
                
            
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Sucursal.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        
            if(rs != null){
            
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Sucursal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Sucursal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            closeConexion();
            
        }
        
        
        
        
        return lista.listIterator();
    }

    private static Integer agregarSucursal(java.lang.String descripcion, java.lang.Integer tipo, java.lang.Integer syncTiempo, java.lang.Integer syncProveedores, java.lang.Integer idUsuario, java.lang.Integer syncClientes, java.lang.String usuario, java.lang.String contrasena, java.lang.String ip, java.lang.String host) {
        nube.app.choya.sys.pv.ws.WsNubePVChoya_Service service = new nube.app.choya.sys.pv.ws.WsNubePVChoya_Service();
        nube.app.choya.sys.pv.ws.WsNubePVChoya port = service.getWsNubePVChoyaPort();
        return port.agregarSucursal(descripcion, tipo, syncTiempo, syncProveedores, idUsuario, syncClientes, usuario, contrasena, ip, host);
    }

    private static String agregarSucursalM2(java.lang.String descripcion, int tipo, int syncTiempo, int syncProveedores, int idUsuario, int syncClientes, java.lang.String usuario, java.lang.String contrasena, java.lang.String ip, java.lang.String host) {
        nube.app.choya.sys.pv.ws.WsNubePVChoya_Service service = new nube.app.choya.sys.pv.ws.WsNubePVChoya_Service();
        nube.app.choya.sys.pv.ws.WsNubePVChoya port = service.getWsNubePVChoyaPort();
        return port.agregarSucursalM2(descripcion, tipo, syncTiempo, syncProveedores, idUsuario, syncClientes, usuario, contrasena, ip, host);
    }

    
    
}
