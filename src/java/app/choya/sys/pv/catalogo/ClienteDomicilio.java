/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.catalogo;

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
 * @author 
 */
public class ClienteDomicilio extends GenericDAO implements  ClienteDomicilioI{
       private UtilsDao util_dao = new UtilsDao();

    @Override
    public int setClienteDomicilio(ClienteDomicilioBean cdb) throws SqlAppsException {
        int resultado = 0;
        int id = 0;

        getConexion(EDriver.MYSQL, EApps.PV);
    
        String sql = "INSERT INTO cat_cliente_domicilio\n"
                + "(id,\n"
                + "id_cliente,\n"
                + "domicilio,\n"
                + "num_ext,\n"
                + "num_int,\n"
                + "colonia,\n"
                + "codigo_postal,\n"
                + "localidad,\n"
                + "municipio,\n"
                + "entidad,\n"
                + "pais,\n"
                + "telefono,\n"
                + "celular,\n"
                + "email,\n"
                + "comentario,\n"
                + "aplica_retencion,\n"
                + "no_precio,\n"
                + "limite_credito,\n"
                + "dias_credito)\n"
                + "VALUES\n"
                + "(?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?)";

        try {
            //nuevo consecutivo a insertar
            id = util_dao.nextVal("s_cat_cliente_domicilio");

            PreparedStatement ps = getPrepareStatement(sql);

            ps.setInt(1, id);
            ps.setInt(2, cdb.getId_cliente());
            ps.setString(3, cdb.getDomicilio());
            ps.setString(4, cdb.getNum_ext());
            ps.setString(5, cdb.getNum_int());
            ps.setString(6, cdb.getColonia());
            ps.setString(7, cdb.getCodigo_postal());
            ps.setString(8, cdb.getLocalidad());
            ps.setString(9, cdb.getMunicipio());
            ps.setString(10, cdb.getEntidad());
            ps.setString(11, cdb.getPais());
            ps.setString(12, cdb.getTelefono());
            ps.setString(13, cdb.getCelular());
            ps.setString(14, cdb.getEmail());
            ps.setString(15, cdb.getComentario());
            ps.setInt(16, cdb.getAplica_retencion());
            ps.setInt(17, cdb.getNo_precio());
            ps.setDouble(18, cdb.getLimite_credito());
            ps.setInt(19, cdb.getDias_credito());

            resultado = ps.executeUpdate();
            if (resultado != 0) {
                resultado = id;
            }

        } catch (SQLException ex) {
            System.out.println("el método setClienteDomicilio " + ex.getMessage());
            throw new SqlAppsException(ex);
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDomicilio.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion();
        }
        return resultado;
    }

    @Override
    public int updateClienteDomicilio(ClienteDomicilioBean cdb) throws SqlAppsException {

        int resultado = 0;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "UPDATE cat_cliente_domicilio\n"
                + "SET\n"
                + "id_cliente = ?,\n"
                + "domicilio = ?,\n"
                + "num_ext = ?,\n"
                + "num_int = ?,\n"
                + "colonia = ?,\n"
                + "codigo_postal = ?,\n"
                + "localidad = ?,\n"
                + "municipio = ?,\n"
                + "entidad = ?,\n"
                + "pais = ?,\n"
                + "telefono = ?,\n"
                + "celular = ?,\n"
                + "email = ?,\n"
                + "comentario = ?,\n"
                + "aplica_retencion = ?,\n"
                + "no_precio = ?,\n"
                + "limite_credito = ?,\n"
                + "dias_credito = ?\n"                
                + "WHERE id = ?";

        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);
            
            ps.setInt(1, cdb.getId_cliente());
            ps.setString(2, cdb.getDomicilio());
            ps.setString(3, cdb.getNum_ext());
            ps.setString(4, cdb.getNum_int());
            ps.setString(5, cdb.getColonia());
            ps.setString(6, cdb.getCodigo_postal());
            ps.setString(7, cdb.getLocalidad());
            ps.setString(8, cdb.getMunicipio());
            ps.setString(9, cdb.getEntidad());
            ps.setString(10, cdb.getPais());
            ps.setString(11, cdb.getTelefono());
            ps.setString(12, cdb.getCelular());
            ps.setString(13, cdb.getEmail());
            ps.setString(14, cdb.getComentario());
            ps.setInt(15, cdb.getAplica_retencion());
            ps.setInt(16, cdb.getNo_precio());
            ps.setDouble(17, cdb.getLimite_credito());
            ps.setInt(18, cdb.getDias_credito());
            ps.setInt(19, cdb.getId());
            

            resultado = ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("el método updateClienteDomicilio " + ex.getMessage());
            throw new SqlAppsException(ex);

        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDomicilio.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion();
        }

        return resultado;

    }

    @Override
    public ListIterator<ClienteDomicilioBean> getClienteDomicilioLista() throws SqlAppsException {
        ResultSet rs = null;
        LinkedList<ClienteDomicilioBean> lista = new LinkedList<>();

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "SELECT id,\n"
                + "id_cliente,\n"
                + "domicilio,\n"
                + "num_ext,\n"
                + "num_int,\n"
                + "colonia,\n"
                + "codigo_postal,\n"
                + "localidad,\n"
                + "municipio,\n"
                + "entidad,\n"
                + "pais,\n"
                + "telefono,\n"
                + "celular,\n"
                + "email,\n"
                + "comentario,\n"
                + "aplica_retencion,\n"
                + "no_precio,\n"
                + "limite_credito,\n"
                + "dias_credito\n"
                + "FROM cat_cliente_domicilio";

        try {
            Statement stmt = getStatement();

            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                ClienteDomicilioBean cdb = new ClienteDomicilioBean();
                cdb.setId(rs.getInt("id"));
                cdb.setId_cliente(rs.getInt("id_cliente"));
                cdb.setDomicilio(rs.getString("domicilio"));
                cdb.setNum_ext(rs.getString("num_ext"));
                cdb.setNum_int(rs.getString("num_int"));
                cdb.setColonia(rs.getString("colonia"));
                cdb.setCodigo_postal(rs.getString("codigo_postal"));
                cdb.setLocalidad(rs.getString("localidad"));
                cdb.setMunicipio(rs.getString("municipio"));
                cdb.setEntidad(rs.getString("entidad"));
                cdb.setPais(rs.getString("pais"));
                cdb.setTelefono(rs.getString("telefono"));
                cdb.setCelular(rs.getString("celular"));
                cdb.setEmail(rs.getString("email"));
                cdb.setComentario(rs.getString("comentario"));
                cdb.setAplica_retencion(rs.getInt("aplica_retencion"));
                cdb.setNo_precio(rs.getInt("no_precio"));
                cdb.setLimite_credito(rs.getDouble("limite_credito"));
                cdb.setDias_credito(rs.getInt("dias_credito"));
                                
                lista.add(cdb);
            }
        } catch (SQLException ex) {
            System.out.println("el método getClienteDomicilioLista " + ex.getMessage());
            throw new SqlAppsException(ex);
        } finally {
            try {

                closeStmt();
                if (rs != null) {
                    rs.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(ClienteDomicilio.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion();

        }

        return lista.listIterator();
    }

    @Override
    public ListIterator<ClienteDomicilioBean> getClienteDomicilioLista(int id) throws SqlAppsException {

        ResultSet rs = null;
        LinkedList<ClienteDomicilioBean> lista = new LinkedList<>();

        getConexion(EDriver.MYSQL, EApps.PV);

         String sql = "SELECT id,\n"
                + "id_cliente,\n"
                + "domicilio,\n"
                + "num_ext,\n"
                + "num_int,\n"
                + "colonia,\n"
                + "codigo_postal,\n"
                + "localidad,\n"
                + "municipio,\n"
                + "entidad,\n"
                + "pais,\n"
                + "telefono,\n"
                + "celular,\n"
                + "email,\n"
                + "comentario,\n"
                + "aplica_retencion,\n"
                + "no_precio,\n"
                + "limite_credito,\n"
                + "dias_credito,\n"
                + "FROM cat_cliente_domicilio";
         
        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();

             while (rs.next()) {

                ClienteDomicilioBean cdb = new ClienteDomicilioBean();
                cdb.setId(rs.getInt("id"));
                cdb.setId_cliente(rs.getInt("id_cliente"));
                cdb.setDomicilio(rs.getString("domicilio"));
                cdb.setNum_ext(rs.getString("num_ext"));
                cdb.setNum_int(rs.getString("num_int"));
                cdb.setColonia(rs.getString("colonia"));
                cdb.setCodigo_postal(rs.getString("codigo_postal"));
                cdb.setLocalidad(rs.getString("localidad"));
                cdb.setMunicipio(rs.getString("municipio"));
                cdb.setEntidad(rs.getString("entidad"));
                cdb.setPais(rs.getString("pais"));
                cdb.setTelefono(rs.getString("telefono"));
                cdb.setCelular(rs.getString("celular"));
                cdb.setComentario(rs.getString("comentario"));
                cdb.setAplica_retencion(rs.getInt("aplica_retencion"));
                cdb.setNo_precio(rs.getInt("no_precio"));
                cdb.setLimite_credito(rs.getDouble("limite_credito"));
                cdb.setDias_credito(rs.getInt("dias_credito"));
                                
                lista.add(cdb);
            }
        } catch (SQLException ex) {

            System.out.println("el método getClienteDomicilioLista(id) a lanzado el siguiente error " + ex.getMessage());
            throw new SqlAppsException(ex);

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteDomicilio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDomicilio.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion();

        }

        return lista.listIterator();
    }

    @Override
    public ClienteDomicilioBean getClienteDomicilio(int id) throws SqlAppsException {
        ResultSet rs = null;
        ClienteDomicilioBean cdb = new ClienteDomicilioBean();

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "SELECT id,\n"
                + "id_cliente,\n"
                + "domicilio,\n"
                + "num_ext,\n"
                + "num_int,\n"
                + "colonia,\n"
                + "codigo_postal,\n"
                + "localidad,\n"
                + "municipio,\n"
                + "entidad,\n"
                + "pais,\n"
                + "telefono,\n"
                + "celular,\n"
                + "email,\n"
                + "comentario,\n"
                + "aplica_retencion,\n"
                + "no_precio,\n"
                + "limite_credito,\n"
                + "dias_credito,\n"
                + "FROM cat_cliente_domicilio";
         
        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {
                
                cdb.setId(rs.getInt("id"));
                cdb.setId_cliente(rs.getInt("id_cliente"));
                cdb.setDomicilio(rs.getString("domicilio"));
                cdb.setNum_ext(rs.getString("num_ext"));
                cdb.setNum_int(rs.getString("num_int"));
                cdb.setColonia(rs.getString("colonia"));
                cdb.setCodigo_postal(rs.getString("codigo_postal"));
                cdb.setLocalidad(rs.getString("localidad"));
                cdb.setMunicipio(rs.getString("municipio"));
                cdb.setEntidad(rs.getString("entidad"));
                cdb.setPais(rs.getString("pais"));
                cdb.setTelefono(rs.getString("telefono"));
                cdb.setCelular(rs.getString("celular"));
                cdb.setComentario(rs.getString("comentario"));
                cdb.setAplica_retencion(rs.getInt("aplica_retencion"));
                cdb.setNo_precio(rs.getInt("no_precio"));
                cdb.setLimite_credito(rs.getDouble("limite_credito"));
                cdb.setDias_credito(rs.getInt("dias_credito"));
                                
               

            }
        } catch (SQLException ex) {

            System.out.println("el método getClienteDomicilioLista(id) a lanzado el siguiente error " + ex.getMessage());
            throw new SqlAppsException(ex);

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteDomicilio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDomicilio.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion();

        }
        
        return cdb;
    }

    @Override
    public int deleteClienteDomicilio(int id) throws SqlAppsException {
        
        int resultado = 0;
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "UPDATE cat_cliente_domicilio set estatus ='D' WHERE  sid = ?";
        
        
        try {
            PreparedStatement ps;
            ps = getPrepareStatement(sql);
            ps.setInt(1, id);
            
            resultado = ps.executeUpdate();
            
            
        } catch (SQLException ex) {
            
            System.out.println("el método deleteClienteDomicilio a lanzado el siguiente error "+ex.getMessage());
           throw new SqlAppsException(ex);
            
            
        }finally{
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDomicilio.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConexion();
        }
        
        
        return resultado;
        
    }

    
}
