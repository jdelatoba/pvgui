/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Informatica
 */
public class GenericDAO extends Urleables implements DAO {

    private Connection conn;
    private String url;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private Statement stmt;
    private CallableStatement cstmt;

    @Override
    public Connection getConexion() {

        try {

            //registramos el controlador
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            url = "jdbc:oracle:thin:@" + getSYS_IP() + ":" + getSYS_PORT() + ":" + getSYS_DATASOURCE();
            conn = DriverManager.getConnection(url, getSYS_USR(), getSYS_PASS());

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    @Override
    public java.sql.Connection getConexion(String bd, String usuario, String passwd, String dataSource) {
        return conn;
    }

    @Override
    public java.sql.Connection getConexion(EDriver eDriver, EApps app) {

        //driver Atributo que va contener los nombre de clases para cargar 
        //el jdbc especifico para la base de datos que deseamos conectarnos
        String driver = "";

        try {
            switch (eDriver) {
                case ORACLE:
                    driver = "oracle.jdbc.driver.OracleDriver";
                    switch (app) {
                        
                        default:
                            break;
                    }
                    break;
                case MYSQL:
                    switch (app) {
                        case PV:
                            Class.forName("com.mysql.jdbc.Driver").newInstance();
                            url = "jdbc:mysql://"+ getSYS_IP() + ":" + getSYS_PORT() + "/" + getSYS_DATASOURCE();
                            conn = DriverManager.getConnection(url, getSYS_USR(), getSYS_PASS());
                            break;
                        case MTX:
                            Class.forName("com.mysql.jdbc.Driver").newInstance();
                            url = "jdbc:mysql://"+ getMTX_IP() + ":" + getMTX_PORT() + "/" + getMTX_DATASOURCE();
                            conn = DriverManager.getConnection(url, getMTX_USR(), getMTX_PASS());
                            break;
                        default:
                                break;
                    }
                   
                case SQLSERVER:

                    break;
                case POSTGRESQL:
                    break;

            }
            

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, "Controlador no encontrado", ex);

        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, "Error al crear nueva instancia del controlador", ex);
        } catch (SQLException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, "Error al iniciar sesión", ex);
        }
        return conn;
    }
    
    
    @Override
    public java.sql.Connection getConexionSinCommit(EDriver eDriver, EApps app) {

        //driver Atributo que va contener los nombre de clases para cargar 
        //el jdbc especifico para la base de datos que deseamos conectarnos
        String driver = "";

        try {
            switch (eDriver) {
                case ORACLE:
                    driver = "oracle.jdbc.driver.OracleDriver";
                    switch (app) {
                        
                        default:
                            break;
                    }
                    break;
                case MYSQL:
                    switch (app) {
                        case PV:
                            Class.forName("com.mysql.jdbc.Driver").newInstance();
                            url = "jdbc:mysql://"+ getSYS_IP() + ":" + getSYS_PORT() + "/" + getSYS_DATASOURCE();
                            conn = DriverManager.getConnection(url, getSYS_USR(), getSYS_PASS());
                            break;
                        case MTX:
                            Class.forName("com.mysql.jdbc.Driver").newInstance();
                            url = "jdbc:mysql://"+ getMTX_IP() + ":" + getMTX_PORT() + "/" + getMTX_DATASOURCE();
                            conn = DriverManager.getConnection(url, getMTX_USR(), getMTX_PASS());
                            break;
                        default:
                                break;
                    }
                case SQLSERVER:

                    break;
                case POSTGRESQL:
                    break;

            }
            conn.setAutoCommit(false);
           

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, "Controlador no encontrado", ex);

        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, "Error al crear nueva instancia del controlador", ex);
        } catch (SQLException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, "Error al iniciar sesión", ex);
        }
        return conn;
    }
    
    public void rollback(){
        try {
            if (conn != null || !conn.isClosed()) {
                conn.rollback();

            }
        } catch (SQLException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void commit(){
        try {
            if (conn != null || !conn.isClosed()) {
                conn.commit();

            }
        } catch (SQLException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void closeConexion() {
        try {
            if (conn != null || !conn.isClosed()) {
                conn.close();

            }
        } catch (SQLException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void closeConexion(Connection cnn) {
        try {
            if (cnn != null || !cnn.isClosed()) {
                System.out.println("conexion antes de cerrar " + cnn.toString());

                cnn.close();
                if (cnn != null || !cnn.isClosed()) {
                    System.out.println("sigue abierta " + cnn.toString());
                    cnn.close();
                }
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, "Error al cerrar la conexion a la base de datos", e);
        }
    }

    @Override
    public Statement getStatement() throws SQLException {
        if (conn != null) {
            stmt = conn.createStatement();
            return stmt;

        } else {
            return null;
        }
    }

    @Override
    public PreparedStatement getPrepareStatement(String sql) throws SQLException {

        if (conn != null) {
            pstmt = conn.prepareStatement(sql);
        } else {
            return null;
        }

        return pstmt;
    }

    @Override
    public PreparedStatement getPrepareStatementWithKey(String sql) throws SQLException {
        if (conn != null) {
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } else {
            return null;
        }

        return pstmt;
    }

    @Override
    public PreparedStatement getPrepareStatementWithKey(String sql, String secuencia) throws SQLException {
        if (conn != null) {
            pstmt = conn.prepareStatement(sql, new String[]{secuencia});
        } else {
            return null;
        }

        return pstmt;
    }
    
    

    @Override
    public void closeStmt() throws SQLException {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void closePStmt() throws SQLException {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void closeResultSet() throws SQLException {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        if (conn != null) {
            rs = stmt.executeQuery(sql);
        } else {
            return null;
        }

        return rs;
    }
    
    public CallableStatement getCallableStatement(String sql) throws SQLException{
        if (conn != null) {
            cstmt = conn.prepareCall(sql);
        } else {
            return null;
        }

        return cstmt;
    }
    
   
}
