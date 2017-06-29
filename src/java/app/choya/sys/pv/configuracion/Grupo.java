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
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.sql.Statement;

/**
 *
 * @author jdelatoba
 */
public class Grupo extends GenericDAO implements GrupoI {
  static final Logger LOGGER = Logger.getLogger(Grupo.class.getName());
  
    @Override
    public int setGrupo(GrupoBean gb) throws SqlAppsException {
        
        int resultado = 0;
        int secuencia = 0;
        String serie = "";
        Connection cnn = getConexionSinCommit(EDriver.MYSQL, EApps.PV);
          String sql = "INSERT INTO conf_vendedor_grp\n"
                   + "(id,\n"
                + "descripcion,\n"
                + "serie,\n"
                + "estatus,\n"
                + "creado_por,\n"
                + "fecha_creacion,\n"
                + "modificado_por,\n"
                + "fecha_modificacion)\n"
                + "VALUES\n"
                + "(?,\n"
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
            secuencia = new UtilsDao().nextVal("s_conf_vendedor_grp",cnn);

             ps.setInt(1, secuencia);
            ps.setString(2, gb.getDescripcion());
            ps.setString(3, gb.getSerie());
            ps.setString(4, gb.getEstatus());
            ps.setInt(5, gb.getCreado_por());
            ps.setString(6, gb.getFecha_creacion());
            ps.setInt(7, gb.getModificado_por());
            ps.setString(8, gb.getFecha_modificacion());
             resultado = ps.executeUpdate();
            commit();
            if (resultado != 0) {
                resultado = secuencia;
            }

        } catch (SQLException ex) {
            rollback();
            
            throw new SqlAppsException(ex, Grupo.class.getName() + "-- metodo: setGrupo", LOGGER);
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Grupo.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion();
        }

        return resultado;
        
    }
    @Override
    public int updateGrupo(GrupoBean gb) throws SqlAppsException {
      int resultado = 0;

        getConexion(EDriver.MYSQL, EApps.PV);
        
           String sql = "UPDATE conf_vendedor_grp\n"
                + "SET\n"
                + "descripcion = ?,\n"
                + "serie = ?,\n"
                + "estatus = ?,\n"
                + "modificado_por = ?,\n"
                + "fecha_modificacion = ?\n"
                + "WHERE id = ?";

        try {

            PreparedStatement ps = getPrepareStatement(sql);

            ps.setString(1, gb.getDescripcion());
            ps.setString(2, gb.getSerie());
            ps.setString(3, gb.getEstatus());
            ps.setInt(4, gb.getModificado_por());
            ps.setString(5, gb.getFecha_modificacion());
            ps.setInt(6, gb.getId());

            resultado = ps.executeUpdate();

        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Grupo.class.getName() + "-- metodo: updateGrupo", LOGGER);
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Grupo.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion();
        }
        return resultado;

        
    }

    @Override
    public int deleteGrupo(int id) throws SqlAppsException {
         int resultado = 0;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "UPDATE conf_vendedor_grp set estatus = 'D' WHERE id = ?";

        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);

            ps.setInt(1, id);

            resultado = ps.executeUpdate();

        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Grupo.class.getName() + "-- metodo: deleteGrupo", LOGGER);
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Grupo.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion();

        }

        return resultado;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public ListIterator<GrupoBean> getGrupoLista() throws SqlAppsException {
      
       LinkedList<GrupoBean> lista = new LinkedList<>();
      
        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "SELECT id,\n"
                + "descripcion,\n"
                + "creado_por,\n"
                + "date_format(fecha_creacion,'%d/%m/%Y') fecha_creacion,\n"
                + "modificado_por,\n"
                + "date_format(fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion,\n"
                + "serie,\n"
                + "estatus\n"
                + "FROM conf_vendedor_grp WHERE estatus = 'A' order by descripcion";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = getPrepareStatement(sql);
          // ps.setInt(1, id);
            
            rs = ps.executeQuery();
             while(rs.next()){
                 GrupoBean gb = new GrupoBean();
                   gb.setId(rs.getInt("id"));
                   gb.setDescripcion(rs.getString("descripcion"));
                   gb.setCreado_por(rs.getInt("creado_por"));
                   gb.setModificado_por(rs.getInt("modificado_por"));
                   gb.setFecha_creacion(rs.getString("fecha_creacion"));
                   gb.setFecha_modificacion(rs.getString("fecha_modificacion"));
                   gb.setSerie(rs.getString("serie"));
                   gb.setEstatus(rs.getString("estatus"));
                
                lista.add(gb);
             }
             } catch (SQLException ex) {
            
            throw new SqlAppsException(ex, Grupo.class.getName() + "-- metodo: getGrupoLista", LOGGER);
        }finally{
            try {
                if(rs != null)
                    rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Grupo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Grupo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
        }

        return lista.listIterator();
    }

    public ListIterator<GrupoBean> getGrupo(int id) throws SqlAppsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JSONObject getGrupoJSON(int draw) throws SqlAppsException {
        ResultSet rs = null;
        int contador = 0;
        JSONObject grupos = new JSONObject();
        JSONArray data = new JSONArray();
        Statement stmt = null;
        
        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "SELECT id,\n"
                + "descripcion,\n"
                + "creado_por,\n"
                + "date_format(fecha_creacion,'%d/%m/%Y') fecha_creacion,\n"
                + "modificado_por,\n"
                + "date_format(fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion,\n"
                + "serie,\n"
                + "estatus\n"
                + "FROM conf_vendedor_grp WHERE estatus = 'A' order by descripcion";
        
        try {
            stmt = getStatement();
            
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                
                JSONObject grupoJSON = new JSONObject();
                
                grupoJSON.put("id", rs.getInt("id"));
                grupoJSON.put("descripcion", rs.getString("descripcion"));
                grupoJSON.put("creado_por", rs.getInt("creado_por"));
                grupoJSON.put("fecha_creacion", rs.getString("fecha_creacion"));
                grupoJSON.put("modificado_por", rs.getInt("modificado_por"));
                grupoJSON.put("fecha_modificacion", rs.getString("fecha_modificacion"));
                grupoJSON.put("serie", rs.getString("serie"));
                grupoJSON.put("estatus", rs.getString("estatus"));
                
                data.add(grupoJSON);
                
                contador++;
            }
            
            grupos.put("data", data);
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Grupo.class.getName() + "-- metodo: getGrupoJSON", LOGGER);
        } finally {
            
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Grupo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
            try {

                closeStmt();
                if (rs != null) {
                    rs.close();
                }

            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Grupo.class.getName() + "-- metodo: getGrupoJSON", LOGGER);
            }

            closeConexion();

        }
        
        
        
        return grupos;
        
    }

    @Override
    public ListIterator<GrupoBean> getGrupoLista(int id) throws SqlAppsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<GrupoBean> getGrupo() throws SqlAppsException {
     LinkedList<GrupoBean> lista = new LinkedList<>();
      
        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "SELECT id,\n"
                + "descripcion,\n"
                + "creado_por,\n"
                + "date_format(fecha_creacion,'%d/%m/%Y') fecha_creacion,\n"
                + "modificado_por,\n"
                + "date_format(fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion,\n"
                + "serie,\n"
                + "estatus\n"
                + "FROM conf_vendedor_grp WHERE estatus = 'A' order by descripcion";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = getPrepareStatement(sql);
          // ps.setInt(1, id);
            
            rs = ps.executeQuery();
             while(rs.next()){
                 GrupoBean gb = new GrupoBean();
                   gb.setId(rs.getInt("id"));
                   gb.setDescripcion(rs.getString("descripcion"));
                   gb.setCreado_por(rs.getInt("creado_por"));
                   gb.setModificado_por(rs.getInt("modificado_por"));
                   gb.setFecha_creacion(rs.getString("fecha_creacion"));
                   gb.setFecha_modificacion(rs.getString("fecha_modificacion"));
                   gb.setSerie(rs.getString("serie"));
                   gb.setEstatus(rs.getString("estatus"));
                
                lista.add(gb);
             }
             } catch (SQLException ex) {
            
            throw new SqlAppsException(ex, Grupo.class.getName() + "-- metodo: getGrupoLista", LOGGER);
        }finally{
            try {
                if(rs != null)
                    rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Grupo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Grupo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
        }

        return lista.listIterator();
    }


    
    
}
