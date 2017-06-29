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
public class AdmPerfil extends GenericDAO implements AdmPerfilI{
    private String sql = "";
    private ResultSet rs = null;
    private PreparedStatement ps = null;
    private Statement stmt = null;

    
    
    @Override
    public int setAdmPerfil(AdmPerfilBean pb) throws SqlAppsException {
        
        int resultado = 0;
        getConexion(EDriver.ORACLE, EApps.PV);

        sql = " INSERT INTO ADM_PERFILES ( "
               + "PERFIL_ID\n" +
                ",APLICACION_ID \n" +
                ",NOMBRE \n" +
                ",DESCRIPCION \n" +
                ",SQL_VALIDACION \n" +
                ",COL_VALOR \n" +
                ",COL_VALOR_VISIBLE \n" +
                ",COL_DESC \n" +
                ",COL_DESC_VISIBLE \n" +
                ",A_NIVEL_SITIO  \n" +
                ",A_NIVEL_RESPONSABILIDAD  \n" +
                ",A_NIVEL_USUARIO \n" +
                ",FECHA_INICIO \n" +
                ",FECHA_TERMINO  \n" +
                ",CREADO_POR \n" +
                ",FECHA_CREACION \n" +
                ",MODIFICADO_POR \n" +
                ",FECHA_MODIFICACION)"
                        + "VALUES( ADM_PERFILES_S.NEXTVAL \n"
                        + ", ?\n"
                        + ", ?\n"
                        + ", ?\n"
                        + ", ?\n"
                        + ", ?\n"
                        + ", ?\n"
                        + ", ?\n"
                        + ", ?\n"
                        + ", ?\n"
                        + ", ?\n"
                        + ", ?\n"
                        + ", SYSDATE \n"
                        + ", ?\n"
                        + ", ?\n"
                        + ", SYSDATE \n"
                        + ", ?\n"
                        + ", SYSDATE )";
        try{
            ps = getPrepareStatementWithKey(sql, "PERFIL_ID");
            
            ps.setInt(1,pb.getAplicacion_id());
            ps.setString(2, pb.getNombre());
            ps.setString(3, pb.getDescripcion());
            ps.setString(4, pb.getSql_validacion());
            ps.setString(5, pb.getCol_valor());
            ps.setString(6, pb.getCol_valor_visible());
            ps.setString(7, pb.getCol_desc());
            ps.setString(8, pb.getCol_desc_visible());
            ps.setString(9, pb.getA_nivel_sitio());
            ps.setString(10, pb.getA_nivel_responsabilidad());
            ps.setString(11, pb.getA_nivel_usuario());
            ps.setString(12, pb.getFecha_termino());
            ps.setInt(13, pb.getCreado_por());
            ps.setInt(14, pb.getModificado_por());
            

            
             resultado = ps.executeUpdate();
             
             
        }catch (SQLException ex) {
            Logger.getLogger(AdmPerfil.class.getName()).log(Level.SEVERE, null, ex);
            throw new SqlAppsException(ex);
        } finally {
            try {
                closeResultSet();
                closePStmt();
                closeConexion();

            } catch (SQLException ex) {
                Logger.getLogger(AdmPerfil.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                throw new SqlAppsException(ex);
            }

        }
        return resultado;
    }

    @Override
    public int updateAdmPerfil(AdmPerfilBean pb) throws SqlAppsException {
        int resultado = 0;
        getConexion(EDriver.ORACLE,EApps.PV);
        
        sql="UPDATE ADM_PERFILES \n"
                + "SET  APLICACION_ID = ?\n" +
                  ",NOMBRE = ?\n" +
                  ",DESCRIPCION = ?\n" +
                    ",SQL_VALIDACION = ?\n" +
                    ",COL_VALOR = ?\n" +
                    ",COL_VALOR_VISIBLE= ?\n" +
                    ",COL_DESC = ?\n" +
                    ",COL_DESC_VISIBLE= ?\n" +
                    ",A_NIVEL_SITIO= ?\n" +
                    ",A_NIVEL_RESPONSABILIDAD = ?\n" +
                    ",A_NIVEL_USUARIO= ?\n" +
                    ",FECHA_INICIO= ?\n" +
                    ",FECHA_TERMINO= ?\n" +
                    ",CREADO_POR= ?\n" +
                    ",FECHA_CREACION= ?\n" +
                    ",MODIFICADO_POR= ?\n" +
                    ",FECHA_MODIFICACION = ?\n"
                + "WHERE PERFIL_ID = ?\n";
        
        try{
            ps = getPrepareStatement(sql);
            
            ps.setInt(1, pb.getAplicacion_id());
            ps.setString(2,pb.getNombre());
            ps.setString(3,pb.getDescripcion());
            ps.setString(4,pb.getSql_validacion());
            ps.setString(5,pb.getCol_valor());
            ps.setString(6,pb.getCol_valor_visible());
            ps.setString(7,pb.getCol_desc());            
            ps.setString(8,pb.getCol_desc_visible());
            ps.setString(9,pb.getA_nivel_sitio());
            ps.setString(10,pb.getA_nivel_responsabilidad());
            ps.setString(11,pb.getA_nivel_usuario());
            ps.setString(12,pb.getFecha_inicio());
            ps.setString(13,pb.getFecha_termino());
            ps.setInt(14,pb.getCreado_por());
            ps.setString(15,pb.getFecha_creacion());
            ps.setInt(16,pb.getModificado_por());
            ps.setString(17,pb.getFecha_modificacion());                        
            ps.setInt(18,pb.getPerfil_id());
            
            resultado = ps.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(AdmPerfil.class.getName()).log(Level.SEVERE, null, ex);
            throw new SqlAppsException(ex);
        } finally {
            try {
                closeResultSet();
                closePStmt();
                closeConexion();

            } catch (SQLException ex) {
                Logger.getLogger(AdmPerfil.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                throw new SqlAppsException(ex);
            }

        }
        return resultado;
    }

    @Override
    public ListIterator<AdmPerfilBean> getAdmPerfil() throws SqlAppsException {
         
        LinkedList<AdmPerfilBean> lista = new LinkedList<>();
        
        getConexion(EDriver.ORACLE, EApps.PV);    
        
        sql="SELECT PERFIL_ID,\n" 
            +"APLICACION_ID,\n" 
            +"NOMBRE,\n" 
            +"DESCRIPCION,\n" 
            +"SQL_VALIDACION,\n" 
            +"COL_VALOR,\n" 
            +"COL_VALOR_VISIBLE,\n" 
            +"COL_DESC, \n" 
            +"COL_DESC_VISIBLE,\n" 
            +"A_NIVEL_SITIO,\n" 
            +"A_NIVEL_RESPONSABILIDAD,\n" 
            +"A_NIVEL_USUARIO,\n" 
            +"FECHA_INICIO,\n" 
            +"FECHA_TERMINO,\n" 
            +"CREADO_POR,\n" 
            +"FECHA_CREACION,\n" 
            +"MODIFICADO_POR,\n" 
            +"FECHA_MODIFICACION \n"
                + "FROM ADM_PERFILES";
        
                
           try{
               
                stmt = getStatement();
                rs = stmt.executeQuery(sql);
           
                while(rs.next()){
                    AdmPerfilBean pb = new AdmPerfilBean();
                    
                    pb.setPerfil_id(rs.getInt( "PERFIL_ID" ));
                    pb.setAplicacion_id(rs.getInt("APLICACION_ID" ));
                    pb.setNombre(rs.getString( "NOMBRE" ));
                    pb.setDescripcion(rs.getString("DESCRIPCION" ));
                    pb.setSql_validacion(rs.getString ("SQL_VALIDACION" ));                    
                    pb.setCol_valor(rs.getString("COL_VALOR" ));
                    pb.setCol_valor_visible(rs.getString("COL_VALOR_VISIBLE" ));
                    pb.setCol_desc(rs.getString("COL_DESC" ));
                    pb.setCol_desc_visible(rs.getString("COL_DESC_VISIBLE" ));
                    pb.setA_nivel_sitio(rs.getString("A_NIVEL_SITIO" ));
                    pb.setA_nivel_responsabilidad(rs.getString("A_NIVEL_RESPONSABILIDAD" ));
                    pb.setA_nivel_usuario(rs.getString("A_NIVEL_USUARIO" ));
                    pb.setFecha_inicio(rs.getString("FECHA_INICIO" ));
                    pb.setFecha_termino(rs.getString("FECHA_TERMINO" ));
                    pb.setCreado_por(rs.getInt("CREADO_POR" ));
                    pb.setFecha_creacion(rs.getString("FECHA_CREACION" ));
                    pb.setModificado_por(rs.getInt("MODIFICADO_POR" ));
                    pb.setFecha_modificacion(rs.getString("FECHA_MODIFICACION"));
                    
                    
                    lista.add(pb);
                    
               }
               
           }catch (SQLException ex) {
            Logger.getLogger(AdmPerfil.class.getName()).log(Level.SEVERE, null, ex);
            throw new SqlAppsException(ex);
        } finally {
            try {
                closeResultSet();
                closePStmt();
                closeConexion();
            } catch (SQLException ex) {
                Logger.getLogger(AdmPerfil.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                throw new SqlAppsException(ex);
            }
        }
          
         return lista.listIterator();  
                  
    }

    @Override
    public ListIterator<AdmPerfilBean> getAdmPerfil(String nombre) throws SqlAppsException {
         
        LinkedList<AdmPerfilBean> lista = new LinkedList<>();
        
        getConexion(EDriver.ORACLE, EApps.PV);    
        
        sql="SELECT PERFIL_ID,\n" 
            +"APLICACION_ID,\n" 
            +"NOMBRE,\n" 
            +"DESCRIPCION,\n" 
            +"SQL_VALIDACION,\n" 
            +"COL_VALOR,\n" 
            +"COL_VALOR_VISIBLE,\n" 
            +"COL_DESC, \n" 
            +"COL_DESC_VISIBLE,\n" 
            +"A_NIVEL_SITIO,\n" 
            +"A_NIVEL_RESPONSABILIDAD,\n" 
            +"A_NIVEL_USUARIO,\n" 
            +"FECHA_INICIO,\n" 
            +"FECHA_TERMINO,\n" 
            +"CREADO_POR,\n" 
            +"FECHA_CREACION,\n" 
            +"MODIFICADO_POR,\n" 
            +"FECHA_MODIFICACION \n"
                + "FROM ADM_PERFILES \n"
                + "WHERE NOMBRE = ?";
        
                
           try{
               
                ps = getPrepareStatement(sql);
                ps.setString(1,nombre );
                
                rs = ps.executeQuery(sql);
           
                while(rs.next()){
                    AdmPerfilBean pb = new AdmPerfilBean();
                    
                    pb.setPerfil_id(rs.getInt( "PERFIL_ID" ));
                    pb.setAplicacion_id(rs.getInt("APLICACION_ID" ));
                    pb.setNombre(rs.getString( "NOMBRE" ));
                    pb.setDescripcion(rs.getString("DESCRIPCION" ));
                    pb.setSql_validacion(rs.getString ("SQL_VALIDACION" ));                    
                    pb.setCol_valor(rs.getString("COL_VALOR" ));
                    pb.setCol_valor_visible(rs.getString("COL_VALOR_VISIBLE" ));
                    pb.setCol_desc(rs.getString("COL_DESC" ));
                    pb.setCol_desc_visible(rs.getString("COL_DESC_VISIBLE" ));
                    pb.setA_nivel_sitio(rs.getString("A_NIVEL_SITIO" ));
                    pb.setA_nivel_responsabilidad(rs.getString("A_NIVEL_RESPONSABILIDAD" ));
                    pb.setA_nivel_usuario(rs.getString("A_NIVEL_USUARIO" ));
                    pb.setFecha_inicio(rs.getString("FECHA_INICIO" ));
                    pb.setFecha_termino(rs.getString("FECHA_TERMINO" ));
                    pb.setCreado_por(rs.getInt("CREADO_POR" ));
                    pb.setFecha_creacion(rs.getString("FECHA_CREACION" ));
                    pb.setModificado_por(rs.getInt("MODIFICADO_POR" ));
                    pb.setFecha_modificacion(rs.getString("FECHA_MODIFICACION"));
                    
                    
                    lista.add(pb);
                    
               }
               
           }catch (SQLException ex) {
            Logger.getLogger(AdmPerfil.class.getName()).log(Level.SEVERE, null, ex);
            throw new SqlAppsException(ex);
        } finally {
            try {
                closeResultSet();
                closePStmt();
                closeConexion();
            } catch (SQLException ex) {
                Logger.getLogger(AdmPerfil.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                throw new SqlAppsException(ex);
            }
        }
          
         return lista.listIterator();  
    }

}
