/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.administracion;

import app.choya.sys.pv.bitacora.BitacoraAcceso;
import java.sql.CallableStatement;
import java.sql.SQLException;
import app.choya.sys.pv.dao.GenericDAO;

import app.choya.sys.pv.dao.EApps;
import app.choya.sys.pv.dao.EDriver;
import app.choya.sys.pv.utils.SqlAppsException;
import app.choya.sys.pv.utils.UtilsDao;
import java.util.logging.Logger;


/**
 *
 * @author Rembao
 */
public class Autentificacion extends GenericDAO {

    static final Logger LOGGER = Logger.getLogger(Autentificacion.class.getName());
    
    public Autentificacion() {
    }

    public int doAutentifica(String us, String ctra) throws SQLException {
        CallableStatement cstmt = null;
        int resultado = 0;
        try{
            getConexion(EDriver.MYSQL, EApps.PV);

            String sql = "{CALL sp_autentifica(?, ?, ?)}";
            
            cstmt = getCallableStatement(sql);
            
            cstmt.registerOutParameter(3, java.sql.Types.NUMERIC, 2);
            
            cstmt.setString(1, us);
            cstmt.setString(2, new UtilsDao().hash(ctra));
            
            cstmt.execute();

            resultado = cstmt.getInt(3);
            
        }catch(SQLException ex){
            throw new SqlAppsException(ex, Autentificacion.class.getName() + "-- metodo: doAutentificaTemp", LOGGER);
        
        }finally{
            if(cstmt != null){
                cstmt.close();
            }
            closeConexion();
        }    
        
        return resultado;
    }

    

}
