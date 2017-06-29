/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.admon;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import app.choya.sys.pv.dao.EApps;
import app.choya.sys.pv.dao.EDriver;
import app.choya.sys.pv.dao.GenericDAO;
import app.choya.sys.pv.utils.SqlAppsException;

/**
 *
 * @author Icosio
 */
public class AdmUsuarioResponsabilidad extends GenericDAO implements AdmUsuarioResponsabilidadI {
     private String sql = "";
    private ResultSet rs = null;
    private PreparedStatement ps = null;
    private Statement stmt = null;


    @Override
    public int setAdmUsuarioResponsabilidad(AdmUsuarioResponsabilidadBean ub, EDriver driver, EApps app) throws SqlAppsException {
        
         int resultado = 0;
        getConexion(driver, app);
        
        sql = "INSERT INTO ADM_USUARIO_RESPONSABILIDAD "
                + "(USUARIO_ID\n" +
                ",RESPONSABILIDAD_ID\n" +
                ",FECHA_MODIFICACION\n" +
                ",MODIFICADO_POR\n" +
                ",FECHA_CREACION\n" +
                ",CREADO_POR\n" +
                ",FECHA_INICIO\n" +
                ",FECHA_TERMINO\n" +
                ",DESCRIPCION)"
                + "VALUES ("
                + "?\n"
                + ",?\n"
                + ",SYSDATE"
                + ",?\n"
                + ",SYSDATE\n"
                + ",?\n"
                + ",SYSDATE\n"
                + ",?\n"             
                + ",?\n)";
        
        try{
            
             ps.setInt(1,ub.getUsuario_id());
             ps.setInt(2,ub.getResponsabilidad_id());
             ps.setInt(3,ub.getModificado_por());
             ps.setInt(4,ub.getCreado_por());
             ps.setString(5,ub.getFecha_termino());
             ps.setString(6,ub.getDescripcion());
            
            resultado = ps.executeUpdate();
            
        }catch (SQLException ex) {
            Logger.getLogger(AdmAplicacion.class.getName()).log(Level.SEVERE, null, ex);
            throw new SqlAppsException(ex);
        } finally {
            try {
                closeResultSet();
                closePStmt();
                closeConexion();

            } catch (SQLException ex) {
                Logger.getLogger(AdmUsuarios.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                throw new SqlAppsException(ex);
            }

        }
        return resultado;
    }

    @Override
    public int updateAdmUsuarioResponsabilidad(AdmUsuarioResponsabilidadBean ub, EDriver driver, EApps app) throws SqlAppsException {
        int resultado = 0;
        getConexion(driver, app);
        
        sql= "UPDATE ADM_USUARIO_RESPONSABILIDAD "
                + "SET USUARIO_ID = ?\n" 
                +",RESPONSABILIDAD_ID = ?\n" 
                +",FECHA_MODIFICACION = ?\n" 
                +",MODIFICADO_POR = ?\n" 
                +",FECHA_CREACION = ?\n" 
                +",CREADO_POR = ?\n" 
                +",FECHA_INICIO = ?\n"
                +",FECHA_TERMINO = ?\n" 
                +",DESCRIPCION = ?\n"
                + "WHERE USUARIO_ID = ?\n"
                + "AND RESPONSABILIDAD_ID = ?\n";
        
        try{
            
            ps.setInt(1, ub.getUsuario_id());
            ps.setInt(2, ub.getResponsabilidad_id());
            ps.setString(3,ub.getFecha_modificacion());
            ps.setInt(4,ub.getModificado_por());
            ps.setString(5,ub.getFecha_creacion());
            ps.setInt(6,ub.getCreado_por());
            ps.setString(7,ub.getFecha_inicio());
            ps.setString(8,ub.getFecha_termino());
            ps.setString(9, ub.getDescripcion());
            ps.setInt(10, ub.getUsuario_id());
            ps.setInt(11, ub.getResponsabilidad_id());
             
            resultado = ps.executeUpdate();
            
        }catch (SQLException ex) {
            Logger.getLogger(AdmAplicacion.class.getName()).log(Level.SEVERE, null, ex);
            throw new SqlAppsException(ex);
        } finally {
            try {
                closeResultSet();
                closePStmt();
                closeConexion();

            } catch (SQLException ex) {
                Logger.getLogger(AdmUsuarios.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                throw new SqlAppsException(ex);
            }

        }
        return resultado;
    }

    @Override
    public ListIterator<AdmUsuarioResponsabilidadBean> getAdmUsuarioResponsabilidad(EDriver driver, EApps app) throws SqlAppsException {
        
       LinkedList<AdmUsuarioResponsabilidadBean> lista = new LinkedList<>();        
       getConexion(driver, app);    
       
       sql="SELECT USUARIO_ID\n" +
                ",RESPONSABILIDAD_ID\n" +
                ",FECHA_MODIFICACION\n" +
                ",MODIFICADO_POR\n" +
                ",FECHA_CREACION\n" +
                ",CREADO_POR\n" +
                ",FECHA_INICIO\n" +
                ",FECHA_TERMINO\n" +
                ",DESCRIPCION \n"
               + "FROM ADM_USUARIOS_RESPONSABILIDAD";
       
       try {

            stmt = getStatement();
            rs = stmt.executeQuery(sql);
            
            while (rs.next()){
                AdmUsuarioResponsabilidadBean ub = new AdmUsuarioResponsabilidadBean();
                
                ub.setUsuario_id(rs.getInt("USUARIO_ID"));
                ub.setResponsabilidad_id(rs.getInt("RESPONSABILIDAD_ID"));
                ub.setFecha_modificacion(rs.getString("FECHA_MODIFICACION"));
                ub.setModificado_por(rs.getInt("MODIFICADO_POR"));
                ub.setFecha_creacion(rs.getString("FECHA_CREACION"));
                ub.setCreado_por(rs.getInt("CREADO_POR"));                
                ub.setFecha_inicio(rs.getString("FECHA_INICIO"));
                ub.setFecha_termino(rs.getString("FECHA_TERMINO"));
                ub.setDescripcion(rs.getString("DESCRIPCION"));
                
               lista.add(ub);
            }
       }catch (SQLException ex) {
            Logger.getLogger(AdmUsuarioResponsabilidad.class.getName()).log(Level.SEVERE, null, ex);
            throw new SqlAppsException(ex);
        } finally {
            try {
                closeResultSet();
                closePStmt();
                closeConexion();
            } catch (SQLException ex) {
                Logger.getLogger(AdmUsuarioResponsabilidad.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                throw new SqlAppsException(ex);
            }
        }
         return lista.listIterator();

    }
    
    
    
    public ListIterator<AdmUsuarioResponsabilidadBean> getAdmUsuarioResponsabilidadPorUsuario(int usuario_id, EDriver driver, EApps app) throws SqlAppsException {
        
       LinkedList<AdmUsuarioResponsabilidadBean> lista = new LinkedList<>();        
       getConexion(driver, app);    
       
       sql="SELECT USUARIO_ID\n" +
                ",RESPONSABILIDAD_ID\n" +
                ",FECHA_MODIFICACION\n" +
                ",MODIFICADO_POR\n" +
                ",FECHA_CREACION\n" +
                ",CREADO_POR\n" +
                ",FECHA_INICIO\n" +
                ",FECHA_TERMINO\n" +
                ",DESCRIPCION \n" +
                "FROM ADM_USUARIOS_RESPONSABILIDAD\n"+
                "WHERE USUARIO_ID = ?";
       
       try {

            ps = getPrepareStatement(sql);
            ps.setInt(1, usuario_id);
            
            rs = ps.executeQuery();
            
            while (rs.next()){
                AdmUsuarioResponsabilidadBean ub = new AdmUsuarioResponsabilidadBean();
                
                ub.setUsuario_id(rs.getInt("USUARIO_ID"));
                ub.setResponsabilidad_id(rs.getInt("RESPONSABILIDAD_ID"));
                ub.setFecha_modificacion(rs.getString("FECHA_MODIFICACION"));
                ub.setModificado_por(rs.getInt("MODIFICADO_POR"));
                ub.setFecha_creacion(rs.getString("FECHA_CREACION"));
                ub.setCreado_por(rs.getInt("CREADO_POR"));                
                ub.setFecha_inicio(rs.getString("FECHA_INICIO"));
                ub.setFecha_termino(rs.getString("FECHA_TERMINO"));
                ub.setDescripcion(rs.getString("DESCRIPCION"));
                
               lista.add(ub);
            }
       }catch (SQLException ex) {
            Logger.getLogger(AdmUsuarioResponsabilidad.class.getName()).log(Level.SEVERE, null, ex);
            throw new SqlAppsException(ex);
        } finally {
            try {
                closeResultSet();
                closePStmt();
                closeConexion();
            } catch (SQLException ex) {
                Logger.getLogger(AdmUsuarioResponsabilidad.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                throw new SqlAppsException(ex);
            }
        }
         return lista.listIterator();

    }

    
}
