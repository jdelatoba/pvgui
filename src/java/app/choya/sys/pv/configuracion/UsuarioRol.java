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
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rembao
 */
public class UsuarioRol extends GenericDAO{
    
    static final Logger LOGGER = Logger.getLogger(UsuarioRol.class.getName());
    
    public UsuarioRol(){}
    
    public int setUsuarioRol(int usuario_id, int rol_id, int creado_por) throws SqlAppsException{
    
        int resultado = 0;
        int secuencia = 0;
        Connection cnn = getConexionSinCommit(EDriver.MYSQL, EApps.PV);
        
        String sql = "INSERT INTO conf_usuario_rol\n" +
        "(id,\n" +
        "usuario_id,\n" +
        "rol_id,\n" +
        "creado_por,\n" +
        "fecha_creacion,\n" +
        "modificado_por,\n" +
        "fecha_modificacion)\n" +
        "VALUES\n" +
        "(?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "current_timestamp(),\n" +
        "?,\n" +
        "current_timestamp())";
        
        PreparedStatement ps;
        
        UtilsDao ut = new UtilsDao();
            secuencia = ut.nextVal("s_conf_usuario_rol",cnn);
        try {
            ps = getPrepareStatement(sql);
            
            ps.setInt(1, secuencia);
            ps.setInt(2, usuario_id);
            ps.setInt(3, rol_id);
            ps.setInt(4, creado_por);
            ps.setInt(5, creado_por);
            
            resultado = ps.executeUpdate();
            commit();
            
        } catch (SQLException ex) {
            rollback();
            throw new SqlAppsException(ex, UsuarioRol.class.getName() + "-- metodo: setUsuarioRol", LOGGER);
        }finally{
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioRol.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion(cnn);
        }
        
        
        
        
        return resultado;
    
    }
    
    public ListIterator<UsuarioRolBean> getRolesPorUsuario(int usuario_id) throws SqlAppsException{
        LinkedList<UsuarioRolBean> lista = new LinkedList<>();
        ResultSet rs = null;
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "select \n" +
        "a.id\n" +
        ",a.usuario_id\n" +
        ",a.rol_id\n" +
        ",b.descripcion as rol\n" +
        "FROM conf_usuario_rol a\n" +
        "LEFT JOIN conf_rol b\n" +
        "ON a.rol_id = b.id\n" +
        "WHERE usuario_id = ?";
        
        
        try {
            PreparedStatement ps = getPrepareStatement(sql);
            
            ps.setInt(1, usuario_id);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                UsuarioRolBean ub = new UsuarioRolBean();
                
                ub.setId(rs.getInt(rs.getInt("id")));
                ub.setUsuario_id(rs.getInt("usuario_id"));
                ub.setRol(rs.getString("rol"));
                
                lista.add(ub);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioRol.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioRol.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioRol.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
        }
        
        
        return lista.listIterator();
    }
    
}
