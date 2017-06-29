/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Informatica
 */
public interface DAO {
    
    /**
     * GetConexion crea una nueva conexion de base de datos
     * @return  regresa un Connection de la base de datos
     */
    public Connection getConexion();
    
    /**
     * GetConexion crea una nueva conexion de base de datos
     * @param bd  nombre de la base de datos
     * @param usuario nombre de usuario de la base de datos
     * @param passwd contraseña de la base de datos
     * @param dataSource nombre del datasource de la base de datos
     * @return  regresa un objecto Connetion de la base de datos
     */
    
    public Connection getConexion(String bd, String usuario
            , String passwd, String dataSource);
    
    
    
    /**
     * 
     * @param driver Tipo Enumerado  posibles valores ORACLE
     * , MYSQL, POSTGRESQL, SQLSERVER para el driver utilizado
     * @param app  nombre de la aplicacion
     * @return  regresa un objecto Connetion de la base de datos
     **/
    public Connection getConexion(EDriver driver, EApps app);
    
    
    /**
     * metodo para crear el una conexion sin autocommit
     * @param driver Tipo Enumerado  posibles valores ORACLE
     * , MYSQL, POSTGRESQL, SQLSERVER para el driver utilizado
     * @param app  nombre de la aplicacion
     * @return  regresa un objecto Connetion de la base de datos
     **/
    public java.sql.Connection getConexionSinCommit(EDriver eDriver, EApps app);
    
    
    /**
     * cierra la conexion de la base de datos
     */
    public void closeConexion();
    
    /**
     * closeConexion cierra la conexion 
     * de base de datos que le pasamos por parametro 
     * @param cnn es la conexión de base de datos que se cerrara. 
     */
    
    public void closeConexion(Connection cnn);
    
    public Statement getStatement() throws SQLException;
    
    public PreparedStatement getPrepareStatement(String sql) throws SQLException;
    
    public PreparedStatement getPrepareStatementWithKey(String sql, String secuencia) throws SQLException;
    
    public ResultSet executeQuery(String sql) throws SQLException;
    
    public void closeStmt() throws SQLException;
    
    public void closePStmt() throws SQLException;
    
    public PreparedStatement getPrepareStatementWithKey(String sql) throws SQLException;
    
    public void closeResultSet() throws SQLException;
    
    
}
