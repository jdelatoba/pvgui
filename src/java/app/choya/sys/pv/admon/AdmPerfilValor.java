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
 * Clase AdmPerfilValor utilizada para la manipulacion de la informacion
 * en ela tabla de  ADM_PERFIL_VALORES
 */
public class AdmPerfilValor extends GenericDAO implements AdmPerfilValorI
{
    private String sql = "";
    private ResultSet rs = null;
    private PreparedStatement ps = null;
    private Statement stmt = null;

    @Override
    public int setAdmPerfilValor(AdmPerfilValorBean pb) throws SqlAppsException {
       int resultado = 0;
       getConexion(EDriver.ORACLE, EApps.PV);
       
       sql = "INSERT INTO ADM_PERFIL_VALORES ("
                + "PERFIL_ID \n" 
                + ",NIVEL_ID \n" 
                + ",VALOR_NIVEL \n" 
                + ",VALOR_PERFIL \n" 
                + ",CREADO_POR \n" 
                + ",FECHA_CREACION \n" 
                + ",MODIFICADO_POR \n" 
                + ",FECHA_MODIFICACION)"
                    + "VALUES (? \n "
                            + ", ?\n"
                            + ", ?\n"
                            + ", ?\n"
                            + ", ?\n"
                            + ", SYSDATE\n"
                            + ", ?\n"
                            + ", SYSDATE\n)";
       
       try{
           
             ps.setInt(1,pb.getPerfil_id());
             ps.setInt(2,pb.getNivel_id());
             ps.setInt(3, pb.getValor_nivel());
             ps.setString(4,pb.getValor_perfil());
             ps.setInt(5,pb.getCreado_por());
             ps.setInt(6,pb.getModificado_por());
             
              resultado = ps.executeUpdate();
              
       }catch (SQLException ex) {
            Logger.getLogger(AdmPerfilValor.class.getName()).log(Level.SEVERE, null, ex);
            throw new SqlAppsException(ex);
        } finally {
            try {
                closeResultSet();
                closePStmt();
                closeConexion();

            } catch (SQLException ex) {
                Logger.getLogger(AdmPerfilValor.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                throw new SqlAppsException(ex);
            }

        }
        return resultado;
       
       
    }

    @Override
    public int updateAdmPerfilValor(AdmPerfilValorBean pb) throws SqlAppsException {
       int resultado = 0;
       getConexion(EDriver.ORACLE, EApps.PV);
       sql= "UPDATE ADM_VALOR_PERFILES \n"
               + "SET VALOR_ NIVEL = ?\n"
               + ",VALOR_PERFIL = ?\n"
               + "CREADO_POR  = ?\n" 
                + ",FECHA_CREACION = ?\n" 
                + ",MODIFICADO_POR = ?\n" 
                + ",FECHA_MODIFICACION = ? \n"
               + "WHERE PERFIL_ID = ?\n"
               + "AND NIVEL_ID = ?\n";
       
       
       try{
           ps = getPrepareStatement(sql);
           
            ps.setInt(1, pb.getValor_nivel());
            ps.setString(2, pb.getValor_perfil());
            ps.setInt(3,pb.getCreado_por());
            ps.setString(4,pb.getFecha_creacion());
            ps.setInt(5,pb.getModificado_por());
            ps.setString(6,pb.getFecha_modificacion());
            
            ps.setInt(7,pb.getPerfil_id());
            ps.setInt(8,pb.getNivel_id());
                        
           resultado = ps.executeUpdate();
       }catch (SQLException ex) {
            Logger.getLogger(AdmPerfilValor.class.getName()).log(Level.SEVERE, null, ex);
            throw new SqlAppsException(ex);
        } finally {
            try {
                closeResultSet();
                closePStmt();
                closeConexion();

            } catch (SQLException ex) {
                Logger.getLogger(AdmPerfilValor.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                throw new SqlAppsException(ex);
            }

        }
        return resultado;
    }

    @Override
    public ListIterator<AdmPerfilValorBean> getAdmPerfilValor() throws SqlAppsException {
        LinkedList<AdmPerfilValorBean> lista = new LinkedList<>();
        
        getConexion(EDriver.ORACLE, EApps.PV);    
        sql= "SELECT PERFIL_ID \n"
                + ",NIVEL_ID \n" 
                + ",VALOR_NIVEL \n" 
                + ",VALOR_PERFIL \n"
                + ",CREADO_POR \n" 
                + ",FECHA_CREACION \n" 
                + ",MODIFICADO_POR \n" 
                + ",FECHA_MODIFICACION \n"
                + "FROM ADM_PERFIL_VALORES";
        try{
                      
                stmt = getStatement();
                rs = stmt.executeQuery(sql);
                      
                while(rs.next()){
                    AdmPerfilValorBean pb = new AdmPerfilValorBean();
                        
                    pb.setPerfil_id(rs.getInt( "PERFIL_ID" ));
                    pb.setNivel_id(rs.getInt("NIVEL_ID" ));
                    pb.setValor_nivel(rs.getInt("VALOR_NIVEL" ));
                    pb.setValor_perfil(rs.getString("VALOR_PERFIL"));
                    pb.setCreado_por(rs.getInt("CREADO_POR"));
                    pb.setFecha_creacion(rs.getString("FECHA_CREACION"));
                    pb.setModificado_por(rs.getInt("MODIFICADO_POR"));
                    pb.setFecha_modificacion(rs.getString("FECHA_MODIFICACION"));
                    
                    lista.add(pb);
                }
        }catch (SQLException ex) {
            Logger.getLogger(AdmPerfilValor.class.getName()).log(Level.SEVERE, null, ex);
            throw new SqlAppsException(ex);
        } finally {
            try {
                closeResultSet();
                closePStmt();
                closeConexion();

            } catch (SQLException ex) {
                Logger.getLogger(AdmPerfilValor.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                throw new SqlAppsException(ex);
            }

        }
         return lista.listIterator();  
    }

    @Override
    public AdmPerfilValorBean getAdmPerfilValor(int perfil_id) throws SqlAppsException {
        
        AdmPerfilValorBean pb = new AdmPerfilValorBean();       
        getConexion(EDriver.ORACLE, EApps.PV);    
        
        sql="SELECT PERFIL_ID \n"
                + ",NIVEL_ID \n" 
                + ",VALOR_NIVEL \n" 
                + ",VALOR_PERFIL \n"
                + ",CREADO_POR \n" 
                + ",FECHA_CREACION \n" 
                + ",MODIFICADO_POR \n" 
                + ",FECHA_MODIFICACION \n"
                + "FROM ADM_PERFIL_VALORES \n"
                + "WHERE PERFIL_ID = ? \n";
        try{
                ps = getPrepareStatement(sql);
                ps.setInt(1,perfil_id );
               
                rs = ps.executeQuery(sql);
           
                while(rs.next()){
                                      
                    pb.setPerfil_id(rs.getInt("PERFIL_ID"));
                    pb.setNivel_id(rs.getInt("NIVEL_ID"));
                    pb.setValor_nivel(rs.getInt("VALOR_NIVEL"));
                    pb.setValor_perfil(rs.getString("VALOR_PERFIL"));
                    pb.setCreado_por(rs.getInt("CREADO_POR"));
                    pb.setFecha_creacion(rs.getString("FECHA_CREACION"));
                    pb.setModificado_por(rs.getInt("MODIFICADO_POR"));
                    pb.setFecha_modificacion(rs.getString("FECHA_MODIFICACION"));
                                       
                }
        }catch (SQLException ex) {
            Logger.getLogger(AdmPerfilValor.class.getName()).log(Level.SEVERE, null, ex);
            throw new SqlAppsException(ex);
        } finally {
            try {
                closeResultSet();
                closePStmt();
                closeConexion();

            } catch (SQLException ex) {
                Logger.getLogger(AdmPerfilValor.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                throw new SqlAppsException(ex);
            }

        }
        return pb;
    }

    
    @Override
    public AdmPerfilValorBean getAdmPerfilValor(int perfil_id, int nivel_id, String valor) throws SqlAppsException {
    
        AdmPerfilValorBean pb = new AdmPerfilValorBean();       
        getConexion(EDriver.ORACLE, EApps.PV);    
        
        sql="SELECT PERFIL_ID \n"
                + ",NIVEL_ID \n" 
                + ",VALOR_NIVEL \n" 
                + ",VALOR_PERFIL \n"
                + ",CREADO_POR \n" 
                + ",FECHA_CREACION \n" 
                + ",MODIFICADO_POR \n" 
                + ",FECHA_MODIFICACION \n"
                + "FROM ADM_PERFIL_VALORES \n"
                + "WHERE PERFIL_ID = ? \n"
                + "AND NIVEL_ID = ?\n"
                + "AND VALOR = ?\n";
        try{
                ps = getPrepareStatement(sql);
                ps.setInt(1,perfil_id );
                ps.setInt(2,nivel_id);
                ps.setString(3, sql);
                
                rs = ps.executeQuery(sql);
           
                while(rs.next()){
                                      
                    pb.setPerfil_id(rs.getInt("PERFIL_ID"));
                    pb.setNivel_id(rs.getInt("NIVEL_ID"));
                    pb.setValor_nivel(rs.getInt("VALOR_NIVEL"));
                    pb.setValor_perfil(rs.getString("VALOR_PERFIL"));
                    pb.setCreado_por(rs.getInt("CREADO_POR"));
                    pb.setFecha_creacion(rs.getString("FECHA_CREACION"));
                    pb.setModificado_por(rs.getInt("MODIFICADO_POR"));
                    pb.setFecha_modificacion(rs.getString("FECHA_MODIFICACION"));
                                       
                }
        }catch (SQLException ex) {
            Logger.getLogger(AdmPerfilValor.class.getName()).log(Level.SEVERE, null, ex);
            throw new SqlAppsException(ex);
        } finally {
            try {
                closeResultSet();
                closePStmt();
                closeConexion();

            } catch (SQLException ex) {
                Logger.getLogger(AdmPerfilValor.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                throw new SqlAppsException(ex);
            }

        }
        return pb;    }
    
}
