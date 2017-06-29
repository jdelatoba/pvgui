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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Condese
 */
public class CuentaBancaria extends GenericDAO implements CuentaBancariaI {
    
    @Override
    public int setCuentaBancaria(CuentaBancariaBean cbb) throws SqlAppsException {
         int resultado = 0;
        int secuencia = 0;
        getConexion(EDriver.MYSQL, EApps.PV);
        
         String sql = "INSERT INTO conf_cuenta_bancaria\n"
                 +"(id,\n"
                 +"cuenta,\n"
                 +"sucursal,\n"
                 +"clabe,\n"
                 +"id_banco,\n"
                 +"creado_por,\n"
                 +"fecha_creacion,\n"
                 +"modificado_por,\n"
                 +"fecha_modificacion,\n"
                 +"serie,\n"
                 +"estatus)\n"
                 +"VALUES\n"
                 +"(?,\n"
                 +"?,\n"
                 +"?,\n"
                 +"?,\n"
                 +"?,\n"
                 +"?,\n"
                 +"?,\n"
                 +"?,\n"
                 +"?,\n"
                 +"?,\n"                 
                 +"?)";
         
          PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);

            UtilsDao ut = new UtilsDao();

            secuencia = ut.nextVal("s_conf_cuenta_bancaria");
            ps.setInt(1, secuencia);
            ps.setString(2,cbb.getCuenta());
            ps.setString(3,cbb.getSucursal());
            ps.setString(4,cbb.getClabe());
            ps.setInt(5, cbb.getId_banco());
            ps.setInt(6,cbb.getCreado_por());
            ps.setString(7,cbb.getFecha_modificacion());
            ps.setInt(8, cbb.getModificado_por());
            ps.setString(9, cbb.getFecha_modificacion());
            ps.setString(10, cbb.getSerie());
            ps.setString(11,cbb.getEstatus());
            
             resultado = ps.executeUpdate();

            if (resultado != 0) {
                resultado = secuencia;
            }

        } catch (SQLException ex) {
            System.out.println("el mÃ©todo setCuentaBancaria a lanzado el siguiente error " + ex.getMessage());
            throw new SqlAppsException(ex);
            //Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(CuentaBancaria.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion();
        }

        return resultado;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int updateCuentaBancaria(CuentaBancariaBean cbb) throws SqlAppsException {
         int resultado = 0;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "UPDATE conf_cuenta_bancaria\n"
                +"SET\n"
                +"cuenta=?,\n"
                +"sucursal=?,\n"
                +"clabe=?,\n"
                +"id_banco=?,\n"
                +"creado_por=?,\n"
                +"fecha_creacion=?,\n"
                +"modificado_por=?,\n"
                +"fecha_modificacion=?,\n"
                +"serie=?,\n"
                +"estatus=?\n"
                +"WHERE id =?";
        PreparedStatement ps;
        
        try {
            ps = getPrepareStatement(sql);

            UtilsDao ut = new UtilsDao();

            ps.setString(2,cbb.getCuenta());
            ps.setString(3,cbb.getSucursal());
            ps.setString(4,cbb.getClabe());
            ps.setInt(5, cbb.getId_banco());
            ps.setInt(6,cbb.getCreado_por());
            ps.setString(7,cbb.getFecha_modificacion());
            ps.setInt(8, cbb.getModificado_por());
            ps.setString(9, cbb.getFecha_modificacion());
            ps.setString(10, cbb.getSerie());
            ps.setString(11,cbb.getEstatus());

            resultado = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("el mÃ©todo updateCuentaBancaria a lanzado el siguiente error " + ex.getMessage());
            throw new SqlAppsException(ex);
            //Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
            closeConexion();
            }
       return resultado;
    }            
    
    
    public int deleteCuentaBancaria(CuentaBancariaBean cbb) throws SqlAppsException {
      int resultado = 0;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "UPDATE conf_cuenta_bancaria\n"
                + "set activo = 'B',\n"
                + "modificado_por = ?,\n"
                + "fecha_modificacion = ?\n"
                + "WHERE id = ?";

        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);
            //ps.setString(1, ub.getActivo());
            
            ps.setInt(1, cbb.getModificado_por());
            ps.setString(2, cbb.getFecha_modificacion());
            ps.setInt(3, cbb.getId());

            resultado = ps.executeUpdate();

        } catch (SQLException ex) {

            System.out.println("el mÃ©todo deleteCatUsuario a lanzado el siguiente error " + ex.getMessage());
            throw new SqlAppsException(ex);
            //Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(CuentaBancaria.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion();
        }

        return resultado;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    @Override
    public ListIterator<CuentaBancariaBean> getCuentaBancaria() throws SqlAppsException {
       LinkedList<CuentaBancariaBean> lista = new LinkedList<>();
       
       Statement stmt = null;
       ResultSet rs = null;
        
       getConexion(EDriver.MYSQL, EApps.PV);
       
       String sql = "SELECT id,\n"
               +"cuenta,\n"
               +"sucursal,\n"
               +"clabe,\n"
               +"id_banco,\n"
               +"creado_por,\n"
               +"fecha_creacion,\n"
               +"modificado_por,\n"
               +"fecha_modificacion,\n"
               +"serie,\n"
               +"estatus\n"
               +"from conf_cuenta_bancaria";
       
        try {
            stmt = getStatement();
            
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                CuentaBancariaBean cbb = new CuentaBancariaBean();
                          
                cbb.setId(rs.getInt("id"));
                cbb.setCuenta(rs.getString("cuenta"));
                cbb.setSucursal(rs.getString("sucursal"));
                cbb.setClabe(rs.getString("clabe"));
                cbb.setId_banco(rs.getInt("id_banco"));
                cbb.setCreado_por(rs.getInt("creado_por"));
                cbb.setFecha_creacion(rs.getString("fecha_creacion"));
                cbb.setModificado_por(rs.getInt("modificado_por"));
                cbb.setFecha_modificacion(rs.getString("fecha_modificacion"));
                cbb.setSerie(rs.getString("serie"));
                cbb.setEstatus(rs.getString("estatus"));
                
                lista.add(cbb);
                
            }
             } catch (SQLException ex) {
            System.out.println("el mÃ©todo getUsuario ha lanzado el siguiente error "+ex.getMessage());
            throw new SqlAppsException(ex);
            //Logger.getLogger(Categoria.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            try {
                if(rs != null)
                    rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(CuentaBancaria.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                if(stmt != null)
                    stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(CuentaBancaria.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
        }

        return lista.listIterator();   
                
    }

    @Override
    public ListIterator<CuentaBancariaBean> getCuentaBancaria(int id) throws SqlAppsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
        
    
}
