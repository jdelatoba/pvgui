/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.admon;

import app.choya.sys.pv.dao.EApps;
import app.choya.sys.pv.dao.EDriver;
import app.choya.sys.pv.dao.GenericDAO;
import app.choya.sys.pv.utils.SqlAppsException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import app.choya.sys.pv.utils.UtilsDao;

/**
 *
 * @author carlos.rembao
 */
public class Autenticacion extends GenericDAO implements AutenticacionI {

    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = "";
    private Connection conn = null;

    
    /**
     * 
     * @param usuario
     * @param contrasena
     * @param driver
     * @param app
     * @return el metodo regresa 
     *  1 en caso de exito
     *  0 el password esta expirado
     *  -1 el usuario no existe
     *  -2 si el usuario con estatus baja
     *  -3 el password no es valido
     * @throws SqlAppsException 
     */
    @Override
    public int doLogin(String usuario
            , String contrasena
            , EDriver driver
            , EApps app) throws SqlAppsException {
        int resultado = 0;

        
        getConexion(driver, app);
        
        switch(driver){
            case ORACLE:
                System.out.println("entre en oracle ");
                System.out.println("usuario "+usuario);
                System.out.println("contrasena "+contrasena);
                    sql = "SELECT F_LOGIN(?,?) RESULTADO FROM DUAL";
                break;
            case MYSQL:
                System.out.println("entre en mysql ");
                System.out.println("usuario "+usuario);
                System.out.println("contrasena "+contrasena);
                    sql = "SELECT usuario_id as RESULTADO FROM adm_usuarios "
                            + "WHERE "
                            + "usuario = ? "
                            + "AND password = ?";
                    
                    contrasena = new UtilsDao().hash(contrasena);
                break;
            default:
                break;
        }

        

        try {
            ps = getPrepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, contrasena);

            rs = ps.executeQuery();

            while (rs.next()) {
                resultado = rs.getInt("RESULTADO");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Autenticacion.class.getName()).log(Level.SEVERE, null, ex);
            throw new SqlAppsException(ex);
        }finally{
            try {
                closeResultSet();
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Autenticacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
        }

        return resultado;
        
    }
    
    

    @Override
    public int updateContrasena(int usuario_id 
            , String contrasena_anterior
            , String contrasena_nueva) throws SqlAppsException{
        
        int resultado = 0;
        
        getConexion(EDriver.ORACLE, EApps.PV);
        
        return resultado;
        
    }
    
    
    public void prueba() throws SQLException{
    
        conn = getConexionSinCommit(EDriver.ORACLE, EApps.PV);
        
        try{
            //preparestatement
            
            conn.commit();
        }catch(Exception e){
            conn.rollback();
        }
        
        
        
    }

}
