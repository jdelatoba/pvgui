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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rembao
 */
public class EmpresaMatriz extends GenericDAO implements EmpresaMatrizI{
    
    static final Logger LOGGER = Logger.getLogger(EmpresaMatriz.class.getName());
    
    @Override
    public int setEmpresaMatriz(EmpresaBean eb) throws SqlAppsException {
        
        int resultado = 0;
        int secuencia = 0;
        Connection cnn =  getConexionSinCommit(EDriver.MYSQL, EApps.MTX);
        
        
        String sql = "INSERT INTO conf_sucursal\n" +
        "(id,\n" +
        "serie,\n" +
        "nombre,\n" +
        "domicilio,\n" +
        "num_ext,\n" +
        "num_int,\n" +
        "ciudad,\n" +
        "entidad,\n" +
        "codigo_postal,\n" +
        "telefono,\n" +
        "celular,\n" +
        "email,\n" +
        "nombre_fiscal,\n" +
        "rfc,\n" +
        "adicional,\n" +
        "regimen_fiscal,\n" +
        "domicilio_fiscal,\n" +
        "num_ext_fiscal,\n" +
        "num_int_fiscal,\n" +
        "colonia_fiscal,\n" +
        "localidad_fiscal,\n" +
        "codigo_postal_fiscal,\n" +
        "ciudad_fiscal,\n" +
        "entidad_fiscal,\n" +
        "pais_fiscal,\n" +
        "creado_por,\n" +
        "modificado_por)\n" +
        "VALUES\n" +
        "(?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?,\n" +
        "?)";
        
        try {
            
            PreparedStatement ps = getPrepareStatement(sql);
            UtilsDao ut = new UtilsDao();

            secuencia = ut.nextVal("s_conf_sucursal",cnn);
            ps.setInt(1, secuencia);
            ps.setString(2, eb.getSerie());
            ps.setString(3, eb.getNombre());
            ps.setString(4, eb.getDomicilio());
            ps.setString(5, eb.getNum_ext());
            ps.setString(6, eb.getNum_int());
            ps.setString(7, eb.getCiudad());
            ps.setString(8, eb.getEntidad());
            ps.setString(9, eb.getCodigo_postal());
            ps.setString(10, eb.getTelefono());
            ps.setString(11, eb.getCelular());
            ps.setString(12, eb.getEmail());
            ps.setString(13, eb.getNombre_fiscal());
            ps.setString(14, eb.getRfc());
            ps.setString(15, eb.getAdicional());
            ps.setString(16, eb.getRegimen_fiscal());
            ps.setString(17, eb.getDomicilio_fiscal());
            ps.setString(18, eb.getNum_ext_fiscal());
            ps.setString(19, eb.getNum_int_fiscal());
            ps.setString(20, eb.getColonia_fiscal());
            ps.setString(21, eb.getLocalidad_fiscal());
            ps.setString(22, eb.getCodigo_postal_fiscal());
            ps.setString(23, eb.getCiudad_fiscal());
            ps.setString(24, eb.getEntidad_fiscal());
            ps.setString(25, eb.getPais_fiscal());
            ps.setInt(26, eb.getCreado_por());
            ps.setInt(27, eb.getModificado_por());
            
            
            resultado = ps.executeUpdate();
            commit();
            if (resultado != 0) {
                resultado = secuencia;
            }

        } catch (SQLException ex) {
            rollback();
            throw new SqlAppsException(ex, EmpresaMatriz.class.getName() + "-- metodo: setEmpresaMatriz", LOGGER);

        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                throw new SqlAppsException(ex, EmpresaMatriz.class.getName() + "-- metodo: setEmpresaMatriz", LOGGER);
            }
            
            closeConexion();
        }

        return resultado;
    }
    
    
    

    @Override
    public int updateEmpresaMatriz(EmpresaBean eb) throws SqlAppsException {
        
        int resultado = 0;
        PreparedStatement ps = null;
        Connection cnn = getConexionSinCommit(EDriver.MYSQL, EApps.MTX);
        
        
        String sql = "UPDATE conf_sucursal\n" +
        "SET\n" +
        "nombre = ?,\n" +
        "domicilio = ?,\n" +
        "num_ext = ?,\n" +
        "num_int = ?,\n" +
        "ciudad = ?,\n" +
        "entidad = ?,\n" +
        "codigo_postal = ?,\n" +
        "telefono = ?,\n" +
        "celular = ?,\n" +
        "email = ?,\n" +
        "nombre_fiscal = ?,\n" +
        "rfc = ?,\n" +
        "adicional = ?,\n" +
        "regimen_fiscal = ?,\n" +
        "domicilio_fiscal = ?,\n" +
        "num_ext_fiscal = ?,\n" +
        "num_int_fiscal = ?,\n" +
        "colonia_fiscal = ?,\n" +
        "localidad_fiscal = ?,\n" +
        "codigo_postal_fiscal = ?,\n" +
        "ciudad_fiscal = ?,\n" +
        "entidad_fiscal = ?,\n" +
        "pais_fiscal = ?,\n" +
        "modificado_por = ?\n" +
        "WHERE id = ?";
        
        try {
            ps = cnn.prepareStatement(sql);
            ps.setString(1, eb.getNombre());
            ps.setString(2, eb.getDomicilio());
            ps.setString(3, eb.getNum_ext());
            ps.setString(4, eb.getNum_int());
            ps.setString(5, eb.getCiudad());
            ps.setString(6, eb.getEntidad());
            ps.setString(7, eb.getCodigo_postal());
            ps.setString(8, eb.getTelefono());
            ps.setString(9, eb.getCelular());
            ps.setString(10, eb.getEmail());
            ps.setString(11, eb.getNombre_fiscal());
            ps.setString(12, eb.getRfc());
            ps.setString(13, eb.getAdicional());
            ps.setString(14, eb.getRegimen_fiscal());
            ps.setString(15, eb.getDomicilio_fiscal());
            ps.setString(16, eb.getNum_ext_fiscal());
            ps.setString(17, eb.getNum_int_fiscal());
            ps.setString(18, eb.getColonia_fiscal());
            ps.setString(19, eb.getLocalidad_fiscal());
            ps.setString(20, eb.getCodigo_postal_fiscal());
            ps.setString(21, eb.getCiudad_fiscal());
            ps.setString(22, eb.getEntidad_fiscal());
            ps.setString(23, eb.getPais_fiscal());
            ps.setInt(24, eb.getModificado_por());
            ps.setInt(25, eb.getId());
            
            resultado = ps.executeUpdate();
            commit();
            
        } catch (SQLException ex) {
            rollback();
            throw new SqlAppsException(ex, EmpresaMatriz.class.getName() + "-- metodo: updateSucursal", LOGGER);

        } finally {
            try {
                if(ps != null){
                    ps.close();
                }
            } catch (SQLException ex) {
                throw new SqlAppsException(ex, EmpresaMatriz.class.getName() + "-- metodo: updateSucursal", LOGGER);
            }
            
            if(cnn != null){
                try {
                    cnn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmpresaMatriz.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return resultado;
    }
    
    
    
    public String getSerie(int secuencia) throws SqlAppsException{
        
        String serie = "";
        ResultSet rs = null;
        
        getConexion(EDriver.MYSQL, EApps.MTX);
        
        String sql = "SELECT serie FROM conf_serie WHERE id = ?";
        
        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setInt(1, secuencia);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                serie = rs.getString("serie");
            }
            
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, EmpresaMatriz.class.getName() + "-- metodo: getSerie", LOGGER);
        }finally{
           
                try {
                     if(rs != null){
                        rs.close();
                     }
                } catch (SQLException ex) {
                    Logger.getLogger(EmpresaMatriz.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(EmpresaMatriz.class.getName()).log(Level.SEVERE, null, ex);
            }
                closeConexion();
            
        }
        
        return serie;
        
        
        
    }
    
}
