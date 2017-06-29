/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import app.choya.sys.pv.dao.EApps;
import app.choya.sys.pv.dao.EDriver;
import app.choya.sys.pv.dao.GenericDAO;

/**
 *
 * @author dba
 */
public class UtilsDao extends GenericDAO{
    
    private Connection cnn = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs	= null;
    private CallableStatement    callStatement = null;
    private String sql = "";
    
    public UtilsDao() {
    }
    
    
    public int nextVal(String secuencia) throws SqlAppsException{
        int valor = 0;
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        sql = "{CALL nextval(?, ?)}";
        
        try {
            callStatement = getCallableStatement(sql);
            callStatement.registerOutParameter(2, java.sql.Types.INTEGER);
            
            callStatement.setString(1, secuencia);
            
            callStatement.execute();

            valor = callStatement.getInt(2);
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex);
        }finally{
            try {
                callStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(UtilsDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion();
        }
        
        
        
        
        return valor;
    }
    
    
    public int nextVal(String secuencia, Connection cnn) throws SqlAppsException{
        int valor = 0;
        
        //getConexion(EDriver.MYSQL, EApps.PV);
        
        sql = "{CALL nextval(?, ?)}";
        
        try {
            callStatement = cnn.prepareCall(sql);
            callStatement.registerOutParameter(2, java.sql.Types.INTEGER);
            
            callStatement.setString(1, secuencia);
            
            callStatement.execute();

            valor = callStatement.getInt(2);
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex);
        }finally{
            try {
                callStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(UtilsDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        
        
        
        return valor;
    }
    
    public String getCurrentTimeStamp(){
        String current_timestamp = "";
        
        try{
            getConexion(EDriver.MYSQL, EApps.PV);
            getStatement();
            
            sql = "SELECT CURRENT_TIMESTAMP FROM DUAL";
            
            rs = executeQuery(sql);
            
            while(rs.next()) current_timestamp = rs.getString("CURRENT_TIMESTAMP");
        
        }catch(SQLException sqle){
            System.out.println("****************** Inicia Error getCurrentTimeStamp ********************");
            System.out.println("Error al llamar al metodo getCurrentTimeStamp metodo: getCurrentTimeStamp");
            System.out.println("Nombre Clase: UtilsDao metodo: getCurrentTimeStamp");
            System.out.println("Mensaje de Error: "+sql);
            System.out.println("Mensaje de Error: "+sqle.getMessage());
            System.out.println("Mensaje de Error: "+sqle.getSQLState());
        } finally{
            try {
                closeResultSet();
                closeStmt();
                closeConexion();
            } catch (SQLException ex) {
                Logger.getLogger(UtilsDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
        
        
        return current_timestamp;
    }
    
    public String getFechaToMysql(String fecha){
         if(fecha == null || fecha.compareTo("") == 0 || fecha.compareTo(" ") == 0){
                fecha = null;
         }else{
                fecha = fecha.substring(6)+"-"+fecha.substring(3, 5)+"-"+fecha.substring(0, 2);
                System.out.println("fecha a mysql "+fecha);
         }
        
        return fecha;
    }
    
    public String getMysqlToFecha(String fecha){
         if(fecha == null || fecha.compareTo("") == 0){
                fecha = null;
         }else{
                fecha = fecha.substring(8)+"-"+fecha.substring(5, 7)+"-"+fecha.substring(0,4);
         }
        
        return fecha;
    }
    
     public String getMysqlToFechaForma(String fecha){
         if(fecha == null || fecha.compareTo("") == 0){
                fecha = null;
         }else{
                fecha = fecha.substring(8)+"/"+fecha.substring(5, 7)+"/"+fecha.substring(0,4);
         }
        
        return fecha;
    }
    
    
    private static final char[] HEXADECIMAL = { '0', '1', '2', '3',
        '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public  String hash(String stringToHash)  {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(stringToHash.getBytes());
            StringBuilder sb = new StringBuilder(2 * bytes.length);
            for (int i = 0; i < bytes.length; i++) {
                int low = (int)(bytes[i] & 0x0f);
                int high = (int)((bytes[i] & 0xf0) >> 4);
                sb.append(HEXADECIMAL[high]);
                sb.append(HEXADECIMAL[low]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            //exception handling goes here
            return null;
        }
    }

    
}
