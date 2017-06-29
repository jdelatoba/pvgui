/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.bitacora;


import app.choya.sys.pv.dao.EApps;
import app.choya.sys.pv.dao.EDriver;
import app.choya.sys.pv.dao.GenericDAO;
import app.choya.sys.pv.utils.SqlAppsException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rembao
 */
public class BitacoraAcceso extends GenericDAO{

    static final Logger LOGGER = Logger.getLogger(BitacoraAcceso.class.getName());
    
    public BitacoraAcceso() {
    }
    
    public int setBitacoraAcceso(BitacoraAccesoBean bb) throws SqlAppsException{
        int resultado = 0;
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "INSERT INTO audi_bitacora_acceso\n" +
        "(id,\n" +
        "usuario_id,\n" +
        "ip,\n" +
        "host)\n" +
        "VALUES\n" +
        "(null,\n" +
        "?,\n" +
        "?,\n" +
        "?)";
        
        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);
            ps.setInt(1, bb.getUsuario_id());
            ps.setString(2, bb.getIp());
            ps.setString(3, bb.getHost());
            
            resultado = ps.executeUpdate();
        
        
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, BitacoraAcceso.class.getName() + "-- metodo: setBitacoraAcceso", LOGGER);
        } finally{
            
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(BitacoraAcceso.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
        
        }
        
        return resultado;
    }
    
    
}
