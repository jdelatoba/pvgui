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
//import app.choya.sys.pv.utils.UtilsDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Rembao
 */
public class Empresa extends GenericDAO implements EmpresaI{

    static final Logger LOGGER = Logger.getLogger(Empresa.class.getName());
    
    @Override
    public int setEmpresa(EmpresaBean eb) throws SqlAppsException {
        
        int resultado = 0;
        int secuencia = 0;
        String serie = "";
        
        EmpresaMatriz matriz = new EmpresaMatriz();
        try{
                
                secuencia =  matriz.setEmpresaMatriz(eb);
                serie = matriz.getSerie(secuencia);
                
                Connection cnn = getConexionSinCommit(EDriver.MYSQL, EApps.PV);
                
                String sql = "INSERT INTO conf_empresa\n" +
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
                    //UtilsDao ut = new UtilsDao();

                    //secuencia = ut.nextVal("s_conf_empresa",cnn);
                    ps.setInt(1, secuencia);
                    ps.setString(2, serie);
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

                    //System.out.println("ps ------------------------>"+ps);
                    
                    resultado = ps.executeUpdate();
                    commit();
                    if (resultado != 0) {
                        resultado = secuencia;
                    }

                } catch (SQLException ex) {
                    rollback();
                    throw new SqlAppsException(ex, Empresa.class.getName() + "-- metodo: setEmpresa", LOGGER);

                } finally {
                    try {
                        closePStmt();
                    } catch (SQLException ex) {
                        throw new SqlAppsException(ex, Empresa.class.getName() + "-- metodo: setEmpresa", LOGGER);
                    }

                    closeConexion();
                }
        }catch(SqlAppsException ex){
             throw new SqlAppsException(ex, Empresa.class.getName() + "-- metodo: setEmpresa", LOGGER);
        
        }

        return resultado;
    }
    
    
    

    @Override
    public int updateEmpresa(EmpresaBean eb) throws SqlAppsException {
        
        int resultado = 0;
        PreparedStatement ps = null;
        
        
        
        Connection cnn = getConexionSinCommit(EDriver.MYSQL, EApps.PV);
        
        
        String sql = "UPDATE conf_empresa\n" +
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
        "modificado_por = ?,\n" +
        "curp = ?\n" +        
        "WHERE id = ?";
        
        try {
            
            new EmpresaMatriz().updateEmpresaMatriz(eb);
            
            
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
            ps.setString(25, eb.getCurp());
            ps.setInt(26, eb.getId());
            
            resultado = ps.executeUpdate();
            commit();
            
        } catch (SQLException ex) {
            rollback();
            throw new SqlAppsException(ex, Empresa.class.getName() + "-- metodo: updateEmpresa", LOGGER);

        } finally {
            try {
                if(ps != null){
                    ps.close();
                }
            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Empresa.class.getName() + "-- metodo: updateEmpresa", LOGGER);
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

    @Override
    public ListIterator<EmpresaBean> getEmpresa(int id) throws SqlAppsException {
        
        LinkedList<EmpresaBean> lista = new LinkedList<>();
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "SELECT id,\n" +
        "serie,\n" +
        "estatus,\n" +
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
        "curp,\n" +
        "sucursal,\n" +
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
        "date_format(fecha_creacion,'%d/%m/%Y %H:%i:%s') fecha_creacion,\n" +
        "modificado_por,\n" +
        "date_format(fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion\n" +
        "FROM conf_empresa\n" +
        "WHERE id = ? AND estatus = 'A' ORDER BY nombre";
        ResultSet rs = null;
        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                EmpresaBean eb = new EmpresaBean();
                eb.setId(rs.getInt("id"));
                eb.setSerie(rs.getString("serie"));
                eb.setEstatus(rs.getString("estatus"));
                eb.setNombre(rs.getString("nombre"));
                eb.setDomicilio(rs.getString("domicilio"));
                eb.setNum_ext(rs.getString("num_ext"));
                eb.setNum_int(rs.getString("num_int"));
                eb.setCiudad(rs.getString("ciudad"));
                eb.setEntidad(rs.getString("entidad"));
                eb.setCodigo_postal(rs.getString("codigo_postal"));
                eb.setTelefono(rs.getString("telefono"));
                eb.setCelular(rs.getString("celular"));
                eb.setEmail(rs.getString("email"));
                eb.setNombre_fiscal(rs.getString("nombre_fiscal"));
                eb.setRfc(rs.getString("rfc"));
                eb.setAdicional(rs.getString("adicional"));
                eb.setRegimen_fiscal(rs.getString("regimen_fiscal"));
                eb.setDomicilio_fiscal(rs.getString("domicilio_fiscal"));
                eb.setNum_ext_fiscal(rs.getString("num_ext_fiscal"));
                eb.setNum_int_fiscal(rs.getString("num_int_fiscal"));
                eb.setCodigo_postal_fiscal(rs.getString("codigo_postal_fiscal"));
                eb.setColonia_fiscal(rs.getString("colonia_fiscal"));
                eb.setCiudad_fiscal(rs.getString("ciudad_fiscal"));
                eb.setLocalidad_fiscal(rs.getString("localidad_fiscal"));
                eb.setEntidad_fiscal(rs.getString("entidad_fiscal"));
                eb.setPais_fiscal(rs.getString("pais_fiscal"));
                eb.setCreado_por(rs.getInt("creado_por"));
                eb.setFecha_creacion(rs.getString("fecha_creacion"));
                eb.setModificado_por(rs.getInt("modificado_por"));
                eb.setFecha_modificacion(rs.getString("fecha_modificacion"));
                eb.setSucursal(rs.getInt("sucursal"));
                eb.setCurp(rs.getString("curp"));
                lista.add(eb);
                
            }
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Empresa.class.getName() + "-- metodo: getEmpresa(id)", LOGGER);
        } finally{
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion();
        }
        
        return lista.listIterator();
    }

    @Override
    public int deleteEmpresa(int id) throws SqlAppsException {
        
        int resultado = 0;
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "UPDATE conf_empresa SET estatus = 'D' WHERE id = ?";
        
        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setInt(1, id);
            
            resultado = ps.executeUpdate();
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Empresa.class.getName() + "-- metodo: getEmpresa(id)", LOGGER);
        }finally{
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
            
        }
        
        
        return resultado;
    }

    @Override
    public ListIterator<EmpresaBean> getEmpresa() throws SqlAppsException {
        LinkedList<EmpresaBean> lista = new LinkedList<>();
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "SELECT id,\n" +
        "serie,\n" +
        "estatus,\n" +
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
        "curp,\n" +            
        "sucursal,\n" +
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
        "date_format(fecha_creacion,'%d/%m/%Y %H:%i:%s') fecha_creacion,\n" +
        "modificado_por,\n" +
        "date_format(fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion\n" +
        "FROM conf_empresa\n" +
        "ORDER BY nombre";
        ResultSet rs = null;
        Statement stmt = null;
        try {
            stmt = getStatement();
            
            
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                EmpresaBean eb = new EmpresaBean();
                eb.setId(rs.getInt("id"));
                eb.setSerie(rs.getString("serie"));
                eb.setEstatus(rs.getString("estatus"));
                eb.setNombre(rs.getString("nombre"));
                eb.setDomicilio(rs.getString("domicilio"));
                eb.setNum_ext(rs.getString("num_ext"));
                eb.setNum_int(rs.getString("num_int"));
                eb.setCiudad(rs.getString("ciudad"));
                eb.setEntidad(rs.getString("entidad"));
                eb.setCodigo_postal(rs.getString("codigo_postal"));
                eb.setTelefono(rs.getString("telefono"));
                eb.setCelular(rs.getString("celular"));
                eb.setEmail(rs.getString("email"));
                eb.setNombre_fiscal(rs.getString("nombre_fiscal"));
                eb.setRfc(rs.getString("rfc"));
                eb.setCurp(rs.getString("curp"));
                eb.setAdicional(rs.getString("adicional"));
                eb.setRegimen_fiscal(rs.getString("regimen_fiscal"));
                eb.setDomicilio_fiscal(rs.getString("domicilio_fiscal"));
                eb.setNum_ext_fiscal(rs.getString("num_ext_fiscal"));
                eb.setNum_int_fiscal(rs.getString("num_int_fiscal"));
                eb.setCodigo_postal_fiscal(rs.getString("codigo_postal_fiscal"));
                eb.setColonia_fiscal(rs.getString("colonia_fiscal"));
                eb.setCiudad_fiscal(rs.getString("ciudad_fiscal"));
                eb.setLocalidad_fiscal(rs.getString("localidad_fiscal"));
                eb.setEntidad_fiscal(rs.getString("entidad_fiscal"));
                eb.setPais_fiscal(rs.getString("pais_fiscal"));
                eb.setCreado_por(rs.getInt("creado_por"));
                eb.setFecha_creacion(rs.getString("fecha_creacion"));
                eb.setModificado_por(rs.getInt("modificado_por"));
                eb.setFecha_modificacion(rs.getString("fecha_modificacion"));
                eb.setSucursal(rs.getInt("sucursal"));
                lista.add(eb);
                
            }
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Empresa.class.getName() + "-- metodo: getEmpresa(id)", LOGGER);
        } finally{
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                if(stmt != null){
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion();
        }
        
        return lista.listIterator();
    }

    @Override
    public JSONObject getEmpresaJSON(int draw) throws SqlAppsException {
        
        Statement stmt = null;
        ResultSet rs = null;
        int contador = 0;
        JSONObject empresas = new JSONObject();
        JSONArray data = new JSONArray();

        getConexion(EDriver.MYSQL, EApps.PV);
        
        
        String sql = "SELECT id,\n" +
        "serie,\n" +
        "estatus,\n" +
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
        "curp,\n" +        
        "surcursal,\n" +        
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
        "date_format(fecha_creacion,'%d/%m/%Y %H:%i:%s') fecha_creacion,\n" +
        "modificado_por,\n" +
        "date_format(fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion\n" +
        "FROM conf_empresa\n" +
        "ORDER BY nombre";
       
        
        try {
            stmt = getStatement();
            
            
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                JSONObject empresaJSON = new JSONObject();
                
                
                empresaJSON.put("id",rs.getInt("id"));
                empresaJSON.put("serie",rs.getString("serie"));
                empresaJSON.put("estatus",rs.getString("estatus"));
                empresaJSON.put("nombre",rs.getString("nombre"));
                empresaJSON.put("domicilio",rs.getString("domicilio"));
                empresaJSON.put("num_ext",rs.getString("num_ext"));
                empresaJSON.put("num_int",rs.getString("num_int"));
                empresaJSON.put("ciudad",rs.getString("ciudad"));
                empresaJSON.put("entidad",rs.getString("entidad"));
                empresaJSON.put("codigo_postal",rs.getString("codigo_postal"));
                empresaJSON.put("telefono",rs.getString("telefono"));
                empresaJSON.put("celular",rs.getString("celular"));
                empresaJSON.put("email",rs.getString("email"));
                empresaJSON.put("nombre_fiscal",rs.getString("nombre_fiscal"));
                empresaJSON.put("rfc",rs.getString("rfc"));
                empresaJSON.put("adicional",rs.getString("adicional"));
                empresaJSON.put("regimen_fiscal",rs.getString("regimen_fiscal"));
                empresaJSON.put("domicilio_fiscal",rs.getString("domicilio_fiscal"));
                empresaJSON.put("num_ext_fiscal",rs.getString("num_ext_fiscal"));
                empresaJSON.put("num_int_fiscal",rs.getString("num_int_fiscal"));
                empresaJSON.put("codigo_postal_fiscal",rs.getString("codigo_postal_fiscal"));
                empresaJSON.put("colonia_fiscal",rs.getString("colonia_fiscal"));
                empresaJSON.put("ciudad_fiscal",rs.getString("ciudad_fiscal"));
                empresaJSON.put("localidad_fiscal",rs.getString("localidad_fiscal"));
                empresaJSON.put("entidad_fiscal",rs.getString("entidad_fiscal"));
                empresaJSON.put("pais_fiscal",rs.getString("pais_fiscal"));
                empresaJSON.put("creado_por",rs.getInt("creado_por"));
                empresaJSON.put("fecha_creacion",rs.getString("fecha_creacion"));
                empresaJSON.put("modificado_por",rs.getInt("modificado_por"));
                empresaJSON.put("fecha_modificacion",rs.getString("fecha_modificacion"));
                empresaJSON.put("sucursal", rs.getInt("sucursal"));
                empresaJSON.put("curp",rs.getString("curp"));
                data.add(empresaJSON);
                
                contador++;
                        
                
            }
            empresas.put("data", data);
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Empresa.class.getName() + "-- metodo: getEmpresaJSON", LOGGER);
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
               throw new SqlAppsException(ex, Empresa.class.getName() + "-- metodo: getEmpresaJSON", LOGGER);
            }

            try {
                if(stmt != null){
                
                    stmt.close();
                }
            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Empresa.class.getName() + "-- metodo: getEmpresaJSON", LOGGER);
            }

            closeConexion();
        }
        
        return empresas;
    }
    
   
    
    
    //actualizar el numero de sucursal de la empresa - cuando se asigne desde la nube.
    
    
    public int setNumSucursal(int num_sucursal, Connection cnn) throws SqlAppsException{
        int resultado = 0;
        
        PreparedStatement ps = null;
        
        String sql = "UPDATE conf_empresa set sucursal = ?";
        
        try {
            ps = cnn.prepareStatement(sql);
            ps.setInt(1, num_sucursal);
            
            resultado = ps.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(ps != null){
            
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return resultado;
    
    }
    
    
    
}
