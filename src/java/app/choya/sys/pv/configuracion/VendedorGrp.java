/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.configuracion;

import static app.choya.sys.pv.configuracion.Vendedor.LOGGER;
import app.choya.sys.pv.dao.EApps;
import app.choya.sys.pv.dao.EDriver;
import app.choya.sys.pv.dao.GenericDAO;
import app.choya.sys.pv.utils.SqlAppsException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Rembao
 */
public class VendedorGrp extends GenericDAO implements VendedorGrpI {

    @Override
    public JSONObject getListaVendedorGrp(int draw) throws SqlAppsException {
        
        ResultSet rs = null;
        int contador = 0;
        JSONObject grupos = new JSONObject();
        JSONArray data = new JSONArray();
        Statement stmt;
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "SELECT\n" +
        "id\n" +
        ",descripcion\n" +
        ",serie\n" +
        ",estatus\n" +
        ",creado_por\n" +
        ",date_format(fecha_creacion,'%d/%m/%Y %H:%i:%s') fecha_creacion\n" +
        ",modificado_por\n" +
        ",date_format(fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion\n" +
        "FROM conf_vendedor_grp\n" +
        "ORDER BY descripcion";
        
        try {
            stmt = getStatement();
            
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                
                JSONObject grupoJSON = new JSONObject();
                
                grupoJSON.put("id",rs.getInt("id"));
                grupoJSON.put("descripcion", rs.getString("descripcion"));
                grupoJSON.put("serie", rs.getString("serie"));
                grupoJSON.put("estatus", rs.getString("estatus"));
                grupoJSON.put("creado_por", rs.getInt("creado_por"));
                grupoJSON.put("fecha_creacion", rs.getString("fecha_creacion"));
                grupoJSON.put("modificado_por", rs.getInt("modificado_por"));
                
                data.add(grupoJSON);
                
            
            
            }
            
            grupos.put("data", data);
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Vendedor.class.getName() + "-- metodo: getListaVendedorGrp", LOGGER);
        } finally {
            try {

                closeStmt();
                if (rs != null) {
                    rs.close();
                }

            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Vendedor.class.getName() + "-- metodo: getListaVendedorGrp", LOGGER);
            }

            closeConexion();

        }
        
        
        return grupos;
    }
    
}
