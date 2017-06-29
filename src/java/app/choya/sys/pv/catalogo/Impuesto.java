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
 * @author Rembao
 */
public class Impuesto extends GenericDAO implements ImpuestoI {

    @Override
    public int setImpuesto(ImpuestoBean ib) throws SqlAppsException {

        int resultado = 0;
        int secuencia = 0;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "INSERT INTO cat_impuesto\n"
                + "(id,\n"
                + "descripcion,\n"
                + "impuesto,\n"
                + "activo,\n"
                + "impreso,\n"
                + "impuesto_local,\n"
                + "trasladado,\n"
                + "retenido,\n"
                + "orden,\n"
                + "creado_por,\n"
                + "modificado_por,\n"
                + "serie,\n"
                + "estatus)\n"
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
                + "?,?)";

        try {
            PreparedStatement ps = getPrepareStatement(sql);

            secuencia = new UtilsDao().nextVal("s_cat_impuesto");

            ps.setInt(1, secuencia);
            ps.setString(2, ib.getDescripcion());
            ps.setDouble(3, ib.getImpuesto());
            ps.setInt(4, ib.getActivo());
            ps.setInt(5, ib.getImpreso());
            ps.setInt(6, ib.getImpuesto_local());
            ps.setInt(7, ib.getTrasladado());
            ps.setInt(8, ib.getRetenido());
            ps.setInt(9, ib.getOrden());
            ps.setInt(10, ib.getCreado_por());
            ps.setInt(11, ib.getModificado_por());
            ps.setString(12, ib.getSerie());
            ps.setString(13, ib.getEstatus());

            resultado = ps.executeUpdate();

            if (resultado != 0) {
                resultado = secuencia;
            }

        } catch (SQLException ex) {
            System.out.println("el metodo setImpuesto ha lanzado el siguiente error " + ex.getMessage());
            throw new SqlAppsException(ex);
            //Logger.getLogger(Impuesto.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Impuesto.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion();
        }

        return resultado;

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateImpuesto(ImpuestoBean ib) throws SqlAppsException {

        int resultado = 0;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "UPDATE cat_impuesto\n"
                + "SET\n"
                + "descripcion = ?,\n"
                + "impuesto = ?,\n"
                + "activo = ?,\n"
                + "impreso = ?,\n"
                + "impuesto_local = ?,\n"
                + "trasladado = ?,\n"
                + "retenido = ?,\n"
                + "orden = ?,\n"
                + "modificado_por = ?,\n"
                + "serie = ?\n"
                + "WHERE id = ?";

        try {

            PreparedStatement ps = getPrepareStatement(sql);
            ps.setString(1, ib.getDescripcion());
            ps.setDouble(2, ib.getImpuesto());
            ps.setInt(3, ib.getActivo());
            ps.setInt(4, ib.getImpreso());
            ps.setInt(5, ib.getImpuesto_local());
            ps.setInt(6, ib.getTrasladado());
            ps.setInt(7, ib.getRetenido());
            ps.setInt(8, ib.getOrden());
            ps.setInt(9, ib.getModificado_por());
            ps.setString(10, ib.getSerie());
            ps.setInt(11, ib.getId());

            resultado = ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("El metodo updateImpuesto ha lanzado el siguiente error " + ex.getMessage());
            throw new SqlAppsException(ex);
            //Logger.getLogger(Impuesto.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Impuesto.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion();
        }
        return resultado;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteImpuesto(int id) throws SqlAppsException {

        int resultado = 0;

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "UPDATE cat_impuesto set estatus = 'D' WHERE id = ?";

        PreparedStatement ps;
        try {
            ps = getPrepareStatement(sql);
            ps.setInt(1, id);

            resultado = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("El metodo deleteImpuesto ha lanzado el siguiente error " + ex.getMessage());
            throw new SqlAppsException(ex);
            //Logger.getLogger(Impuesto.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Impuesto.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion();
        }

        return resultado;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<ImpuestoBean> getImpuesto() throws SqlAppsException {

        LinkedList<ImpuestoBean> lista = new LinkedList<>();

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "SELECT id,\n"
                + "descripcion,\n"
                + "impuesto,\n"
                + "activo,\n"
                + "impreso,\n"
                + "impuesto_local,\n"
                + "trasladado,\n"
                + "retenido,\n"
                + "orden,\n"
                + "creado_por,\n"
                + "date_format(fecha_creacion,'%d/%m/%Y') as fecha_creacion,\n"
                + "modificado_por,\n"
                + "date_format(fecha_modificacion,'%d/%m/%Y %H:%i:%s') as fecha_modificacion,\n"
                + "serie,\n"
                + "estatus\n"
                + "FROM cat_impuesto order by descripcion";
        
        Statement stmt;
        ResultSet rs = null;
        try {
            stmt = getStatement();
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                ImpuestoBean ib = new ImpuestoBean();
                
                ib.setId(rs.getInt("id"));
                ib.setDescripcion(rs.getString("descripcion"));
                ib.setImpuesto(rs.getDouble("impuesto"));
                ib.setActivo(rs.getInt("activo"));
                ib.setImpreso(rs.getInt("impreso"));
                ib.setImpuesto_local(rs.getInt("impuesto_local"));
                ib.setTrasladado(rs.getInt("trasladado"));
                ib.setRetenido(rs.getInt("retenido"));
                ib.setOrden(rs.getInt("orden"));
                ib.setCreado_por(rs.getInt("creado_por"));
                ib.setFecha_creacion(rs.getString("fecha_creacion"));
                ib.setModificado_por(rs.getInt("modificado_por"));
                ib.setFecha_modificacion(rs.getString("fecha_modificacion"));
                ib.setSerie(rs.getString("serie"));
                ib.setEstatus(rs.getString("estatus"));
                
                lista.add(ib);
                
            }
        } catch (SQLException ex) {
            System.out.println("el metodo getImpuesto ha lanzado el siguiente error "+ex.getMessage());
            throw new SqlAppsException(ex);
            //Logger.getLogger(Impuesto.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            try {
                if(rs != null) 
                    rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Impuesto.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Impuesto.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
        }

        return lista.listIterator();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImpuestoBean getImpuesto(int id) throws SqlAppsException {
        
        ImpuestoBean ib = new ImpuestoBean();
        
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "SELECT id,\n"
                + "descripcion,\n"
                + "impuesto,\n"
                + "activo,\n"
                + "impreso,\n"
                + "impuesto_local,\n"
                + "trasladado,\n"
                + "retenido,\n"
                + "orden,\n"
                + "creado_por,\n"
                + "date_format(fecha_creacion,'%d/%m/%Y') as fecha_creacion,\n"
                + "modificado_por,\n"
                + "date_format(fecha_modificacion,'%d/%m/%Y %H:%i:%s') as fecha_modificacion,\n"
                + "serie,\n"
                + "estatus\n"
                + "FROM cat_impuesto WHERE id = ?";
        ResultSet rs = null;
        try {
            PreparedStatement ps = getPrepareStatement(sql);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                ib.setId(rs.getInt("id"));
                ib.setDescripcion(rs.getString("descripcion"));
                ib.setImpuesto(rs.getDouble("impuesto"));
                ib.setActivo(rs.getInt("activo"));
                ib.setImpreso(rs.getInt("impreso"));
                ib.setImpuesto_local(rs.getInt("impuesto_local"));
                ib.setTrasladado(rs.getInt("trasladado"));
                ib.setRetenido(rs.getInt("retenido"));
                ib.setOrden(rs.getInt("orden"));
                ib.setCreado_por(rs.getInt("creado_por"));
                ib.setFecha_creacion(rs.getString("fecha_creacion"));
                ib.setModificado_por(rs.getInt("modificado_por"));
                ib.setFecha_modificacion(rs.getString("fecha_modificacion"));
                ib.setSerie(rs.getString("serie"));
                ib.setEstatus(rs.getString("estatus"));
                
                
                
            }
            
            
            
        } catch (SQLException ex) {
            System.out.println("el metodo ha lanzado el siguiente error "+ex.getMessage());
            throw new SqlAppsException(ex);
        }finally{
        
            try {
                if(rs != null) 
                    rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Impuesto.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Impuesto.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            closeConexion();
        }
        
        return ib;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    

}
