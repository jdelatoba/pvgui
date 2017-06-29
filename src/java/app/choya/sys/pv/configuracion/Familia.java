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
 * @author jdelatoba
 */
public class Familia extends GenericDAO implements FamiliaI{
    static final Logger LOGGER = Logger.getLogger(Familia.class.getName());
    @Override
    public int setFamilia(FamiliaBean fb)  throws SqlAppsException {
         int resultado = 0;
        int secuencia = 0;
        String serie = "";
        Connection cnn = getConexionSinCommit(EDriver.MYSQL, EApps.PV);
          String sql = "INSERT INTO conf_familia\n"
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
            secuencia = new UtilsDao().nextVal("s_conf_familia",cnn);
            ps.setInt(1, secuencia);
            ps.setString(2, fb.getDescripcion());
            ps.setString(3, fb.getSerie());
            ps.setString(4, fb.getEstatus());
            ps.setInt(5, fb.getCreado_por());
            ps.setString(6, fb.getFecha_creacion());
            ps.setInt(7, fb.getModificado_por());
            ps.setString(8, fb.getFecha_modificacion());
             resultado = ps.executeUpdate();
            commit();
            if (resultado != 0) {
                resultado = secuencia;
            }

        } catch (SQLException ex) {
            rollback();
            
            throw new SqlAppsException(ex, Familia.class.getName() + "-- metodo: setFamilia", LOGGER);
          } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Familia.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion();
        }

        return resultado;
        
    }
    
    @Override
    public int updateFamilia(FamiliaBean fb) throws SqlAppsException {
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

            ps.setString(1, fb.getDescripcion());
            ps.setString(2, fb.getSerie());
            ps.setString(3, fb.getEstatus());
            ps.setInt(4, fb.getModificado_por());
            ps.setString(5, fb.getFecha_modificacion());
            ps.setInt(6, fb.getId());

            resultado = ps.executeUpdate();

        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Familia.class.getName() + "-- metodo: updateFamilia", LOGGER);
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Familia.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion();
        }
        return resultado;

     }
     @Override
    public int deleteFamilia(int id) throws SqlAppsException {
         int resultado = 0;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "UPDATE conf_familia set estatus = 'D' WHERE id = ?";

        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);

            ps.setInt(1, id);

            resultado = ps.executeUpdate();

        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Familia.class.getName() + "-- metodo: deleteFamilia", LOGGER);
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Familia.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion();

        }

        return resultado;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public ListIterator<FamiliaBean> getFamiliaLista() throws SqlAppsException {
      
       LinkedList<FamiliaBean> lista = new LinkedList<>();
      
        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "SELECT id,\n"
                + "descripcion,\n"
                + "creado_por,\n"
                + "date_format(fecha_creacion,'%d/%m/%Y') fecha_creacion,\n"
                + "modificado_por,\n"
                + "date_format(fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion,\n"
                + "serie,\n"
                + "estatus\n"
                + "FROM conf_familia WHERE estatus = 'A' order by descripcion";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = getPrepareStatement(sql);
          // ps.setInt(1, id);
            
            rs = ps.executeQuery();
             while(rs.next()){
                 FamiliaBean fb = new FamiliaBean();
                   fb.setId(rs.getInt("id"));
                   fb.setDescripcion(rs.getString("descripcion"));
                   fb.setCreado_por(rs.getInt("creado_por"));
                   fb.setModificado_por(rs.getInt("modificado_por"));
                   fb.setFecha_creacion(rs.getString("fecha_creacion"));
                   fb.setFecha_modificacion(rs.getString("fecha_modificacion"));
                   fb.setSerie(rs.getString("serie"));
                   fb.setEstatus(rs.getString("estatus"));
                
                lista.add(fb);
             }
             } catch (SQLException ex) {
            
            throw new SqlAppsException(ex, Familia.class.getName() + "-- metodo: getFamiliaLista", LOGGER);
        }finally{
            try {
                if(rs != null)
                    rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Familia.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Familia.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
        }

        return lista.listIterator();
    }

    public ListIterator<FamiliaBean> getFamilia(int id) throws SqlAppsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public JSONObject getFamiliaJSON(int draw) throws SqlAppsException {
        ResultSet rs = null;
        int contador = 0;
        JSONObject Familias = new JSONObject();
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
                + "FROM conf_familia WHERE estatus = 'A' order by descripcion";
        
        try {
            stmt = getStatement();
            
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                
                JSONObject FamiliaJSON = new JSONObject();
                
                FamiliaJSON.put("id", rs.getInt("id"));
                FamiliaJSON.put("descripcion", rs.getString("descripcion"));
                FamiliaJSON.put("creado_por", rs.getInt("creado_por"));
                FamiliaJSON.put("fecha_creacion", rs.getString("fecha_creacion"));
                FamiliaJSON.put("modificado_por", rs.getInt("modificado_por"));
                FamiliaJSON.put("fecha_modificacion", rs.getString("fecha_modificacion"));
                FamiliaJSON.put("serie", rs.getString("serie"));
                FamiliaJSON.put("estatus", rs.getString("estatus"));
                
                data.add(FamiliaJSON);
                
                contador++;
            }
            
            Familias.put("data", data);
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Familia.class.getName() + "-- metodo: getFamiliaJSON", LOGGER);
        } finally {
            
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Familia.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
            try {

                closeStmt();
                if (rs != null) {
                    rs.close();
                }

            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Familia.class.getName() + "-- metodo: getFamiliaJSON", LOGGER);
            }

            closeConexion();

        }
        
        
        
        return Familias;
        
    }

    @Override
    public ListIterator<FamiliaBean> getFamiliaLista(int id) throws SqlAppsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<FamiliaBean> getFamilia() throws SqlAppsException {
     LinkedList<FamiliaBean> lista = new LinkedList<>();
      
        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "SELECT id,\n"
                + "descripcion,\n"
                + "creado_por,\n"
                + "date_format(fecha_creacion,'%d/%m/%Y') fecha_creacion,\n"
                + "modificado_por,\n"
                + "date_format(fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion,\n"
                + "serie,\n"
                + "estatus\n"
                + "FROM conf_familia WHERE estatus = 'A' order by descripcion";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = getPrepareStatement(sql);
          // ps.setInt(1, id);
            
            rs = ps.executeQuery();
             while(rs.next()){
                 FamiliaBean fb = new FamiliaBean();
                   fb.setId(rs.getInt("id"));
                   fb.setDescripcion(rs.getString("descripcion"));
                   fb.setCreado_por(rs.getInt("creado_por"));
                   fb.setModificado_por(rs.getInt("modificado_por"));
                   fb.setFecha_creacion(rs.getString("fecha_creacion"));
                   fb.setFecha_modificacion(rs.getString("fecha_modificacion"));
                   fb.setSerie(rs.getString("serie"));
                   fb.setEstatus(rs.getString("estatus"));
                
                lista.add(fb);
             }
             } catch (SQLException ex) {
            
            throw new SqlAppsException(ex, Familia.class.getName() + "-- metodo: getFamiliaLista", LOGGER);
        }finally{
            try {
                if(rs != null)
                    rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Familia.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Familia.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
        }

        return lista.listIterator();
    }

    @Override
    public JSONObject getGrupoJSON(int draw) throws SqlAppsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
