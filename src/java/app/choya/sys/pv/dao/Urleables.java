/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.dao;

/**
 *
 * @author Informatica
 */
public abstract class Urleables {
    
    
    

    
    private static final String SYS_USR = "pventa"; 
    private static final String SYS_PASS = "ChoyaSyspv";
    
    //private static final String SYS_IP = "10.12.1.50";
    private static final String SYS_IP = "200.36.49.230";
    //private static final String SYS_IP = "localhost";
    private static final String SYS_DATASOURCE = "puntoventa";
    private static final String SYS_PORT = "3306";
    
    
    private static final String MTX_USR = "pvmatrix"; 
    private static final String MTX_PASS = "ChoyaSyspv";
    
    //private static final String MTX_IP = "10.12.1.22";
    private static final String MTX_IP = "187.216.110.245";
    private static final String MTX_DATASOURCE = "pvmatrix";
    private static final String MTX_PORT = "3306";
    
    
    public static String getSYS_USR() {
        return SYS_USR;
    }

    public static String getSYS_PASS() {
        return SYS_PASS;
    }

    public static String getSYS_IP() {
        return SYS_IP;
    }

    public static String getSYS_DATASOURCE() {
        return SYS_DATASOURCE;
    }

    public static String getSYS_PORT() {
        return SYS_PORT;
    }

    public static String getMTX_USR() {
        return MTX_USR;
    }

    public static String getMTX_PASS() {
        return MTX_PASS;
    }

    public static String getMTX_IP() {
        return MTX_IP;
    }

    public static String getMTX_DATASOURCE() {
        return MTX_DATASOURCE;
    }

    public static String getMTX_PORT() {
        return MTX_PORT;
    }
    
    
    
    

    
    
    
    
    
    
    
}
