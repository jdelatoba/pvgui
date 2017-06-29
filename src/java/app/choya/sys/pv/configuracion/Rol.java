/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.configuracion;

import app.choya.sys.pv.catalogo.Proveedor;
import app.choya.sys.pv.dao.EApps;
import app.choya.sys.pv.dao.EDriver;
import app.choya.sys.pv.dao.GenericDAO;
import app.choya.sys.pv.utils.SqlAppsException;
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
public class Rol extends GenericDAO implements RolI{

    static final Logger LOGGER = Logger.getLogger(Rol.class.getName());
    
    @Override
    public JSONObject getRolJSON(int draw) throws SqlAppsException {
        ResultSet rs = null;
        int contador = 0;
        JSONObject roles = new JSONObject();
        JSONArray data = new JSONArray();

        getConexion(EDriver.MYSQL, EApps.PV);
        
        
        Statement stmt;
       
        
        String sql = "SELECT id,\n" +
        "descripcion,\n" +
        "estatus,\n" +
        "serie,\n" +
        "creado_por,\n" +
        "date_format(fecha_creacion,'%d/%m/%Y %H:%i:%s') fecha_creacion,\n" +
        "modificado_por,\n" +
        "date_format(fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion\n" +
        "FROM conf_rol order by descripcion";
        
        
        try {
            stmt = getStatement();
            
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
            
                
                JSONObject rolJSON = new JSONObject();
                
                
                rolJSON.put("id",rs.getInt("id"));
                rolJSON.put("descripcion", rs.getString("descripcion"));
                rolJSON.put("estatus", rs.getString("estatus"));
                rolJSON.put("serie",rs.getString("serie"));
                rolJSON.put("creado_por",rs.getInt("creado_por"));
                rolJSON.put("fecha_creacion",rs.getString("fecha_creacion"));
                rolJSON.put("modificado_por",rs.getInt("modificado_por"));
                rolJSON.put("fecha_modificacion",rs.getString("fecha_modificacion"));
                
                data.add(rolJSON);
                
                contador++;
            }
            
            roles.put("data",data);
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Rol.class.getName() + "-- metodo: getRolJSON", LOGGER);
        } finally {
            try {

                closeStmt();
                if (rs != null) {
                    rs.close();
                }

            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Rol.class.getName() + "-- metodo: getRolJSON", LOGGER);
            }

            closeConexion();

        }
        

        return roles;
    }
    
    
    
}
