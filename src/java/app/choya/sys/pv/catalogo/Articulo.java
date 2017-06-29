/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.catalogo;

import app.choya.sys.pv.bitacora.BitacoraArticulo;
import app.choya.sys.pv.bitacora.BitacoraArticuloBean;
import app.choya.sys.pv.dao.EApps;
import app.choya.sys.pv.dao.EDriver;
import app.choya.sys.pv.dao.GenericDAO;
import app.choya.sys.pv.utils.SqlAppsException;
import app.choya.sys.pv.utils.UtilsDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Rembao
 */
public class Articulo extends GenericDAO implements ArticuloI {

    static final Logger LOGGER = Logger.getLogger(Articulo.class.getName());

    public Articulo() {
        /*try {
            LogManager.getLogManager().readConfiguration(
                    new FileInputStream("./log.properties"));
        } catch (IOException ex) {
            Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
        }

        Logger.getLogger(Articulo.class.getName());
        //.log(Level.SEVERE, "EL metodo setArticulo ha lanzado una excepción ", ex);*/

    }

    @Override
    public int setArticulo(ArticuloBean ab) throws SqlAppsException {

        int resultado = 0;
        int secuencia = 0;
        //getConexion(EDriver.MYSQL, EApps.PV);
        PreparedStatement ps = null;
        Connection cnn = getConexionSinCommit(EDriver.MYSQL, EApps.PV);
        
        String sql = "INSERT INTO cat_articulo\n"
                + "(id,\n"
                + "clave,\n"
                + "clave_alterna,\n"
                + "descripcion,\n"
                + "categoria_id,\n"
                + "unidad_compra_id,\n"
                + "unidad_venta_id,\n"
                + "factor,\n"
                + "inventario_minimo,\n"
                + "inventario_maximo,\n"
                + "localizacion,\n"
                + "precio_compra,\n"
                + "neto,\n"
                + "precio_unidad_compra,\n"
                + "precio_unidad_venta,\n"
                + "margen_utilidad1,\n"
                + "precio_venta1,\n"
                + "precio_venta_neto1,\n"
                + "margen_utilidad2,\n"
                + "precio_venta2,\n"
                + "precio_venta_neto2,\n"
                + "margen_utilidad3,\n"
                + "precio_venta3,\n"
                + "precio_venta_neto3,\n"
                + "margen_utilidad4,\n"
                + "precio_venta4,\n"
                + "precio_venta_neto4,\n"
                + "serie,\n"
                + "estatus,\n"
                + "creado_por,\n"
                + "modificado_por, servicio, sucursal, impuesto, departamento_id, caracteristicas)\n"
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
                + "?,?,?, ?,?,?)";

        try {
            ps = getPrepareStatement(sql);

            //secuencia = new UtilsDao().nextVal("s_cat_articulo");
            
            secuencia = new UtilsDao().nextVal("s_cat_articulo", cnn);

            ps.setInt(1, secuencia);
            ps.setString(2, ab.getClave());
            ps.setString(3, ab.getClave_alterna());
            ps.setString(4, ab.getDescripcion());
            ps.setInt(5, ab.getCategoria_id());
            ps.setInt(6, ab.getUnidad_compra_id());
            ps.setInt(7, ab.getUnidad_venta_id());
            ps.setInt(8, ab.getFactor());
            ps.setInt(9, ab.getInventario_minimo());
            ps.setInt(10, ab.getInventario_maximo());
            ps.setString(11, ab.getLocalizacion());
            ps.setDouble(12, ab.getPrecio_compra());
            ps.setInt(13, ab.getNeto());
            ps.setDouble(14, ab.getPrecio_unidad_compra());
            ps.setDouble(15, ab.getPrecio_unidad_venta());

            ps.setDouble(16, ab.getMargen_utilidad1());
            ps.setDouble(17, ab.getPrecio_venta1());
            ps.setDouble(18, ab.getPrecio_venta_neto1());

            ps.setDouble(19, ab.getMargen_utilidad2());
            ps.setDouble(20, ab.getPrecio_venta2());
            ps.setDouble(21, ab.getPrecio_venta_neto2());

            ps.setDouble(22, ab.getMargen_utilidad3());
            ps.setDouble(23, ab.getPrecio_venta3());
            ps.setDouble(24, ab.getPrecio_venta_neto3());

            ps.setDouble(25, ab.getMargen_utilidad4());
            ps.setDouble(26, ab.getPrecio_venta4());
            ps.setDouble(27, ab.getPrecio_venta_neto4());

            ps.setString(28, ab.getSerie());
            ps.setString(29, ab.getEstatus());
            ps.setInt(30, ab.getCreado_por());
            ps.setInt(31, ab.getModificado_por());
            ps.setInt(32, ab.getServicio());
            ps.setInt(33, ab.getSucursal());
            ps.setInt(34, ab.getImpuesto());
            ps.setInt(35, ab.getDepartamento_id());
            ps.setString(36, ab.getCaracteristica());

            resultado = ps.executeUpdate();
            
            if (resultado != 0) {
                resultado = secuencia;
            }
            
            InvArticuloBean ib = new InvArticuloBean();
            ib.setArticulo_id(secuencia);
            ib.setExistencia(0);
            ib.setCreado_por(ab.getCreado_por());
            ib.setModificado_por(ab.getModificado_por());
            
            new InvArticulo().setInvArticulo(ib, cnn);
            
            commit();

        } catch (SQLException ex) {
            rollback();
            throw new SqlAppsException(ex, "El metodo ha lanzado el siguiete error "+ex.getMessage(), LOGGER);

        } finally {
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            closeConexion(cnn);
        }

        return resultado;
        
    }

    @Override
    public int updateArticulo(ArticuloBean ab) throws SqlAppsException {
        int resultado = 0;

        Connection cnn = getConexionSinCommit(EDriver.MYSQL, EApps.PV);
        
        JSONObject estado = getArticuloEstado(ab.getId(), cnn);
        double existencia = getExistencia(ab.getId(), cnn);

        String sql = "UPDATE cat_articulo\n" +
        "SET\n" +
        "clave = ?,\n" +
        "clave_alterna = ?,\n" +
        "descripcion = ?,\n" +
        "categoria_id = ?,\n" +
        "unidad_compra_id = ?,\n" +
        "unidad_venta_id = ?,\n" +
        "factor = ?,\n" +
        "inventario_minimo = ?,\n" +
        "inventario_maximo = ?,\n" +
        "precio_compra = ?,\n" +
        "neto = ?,\n" +
        "precio_unidad_compra = ?,\n" +
        "precio_unidad_venta = ?,\n" +
        "margen_utilidad1 = ?,\n" +
        "precio_venta1 = ?,\n" +
        "precio_venta_neto1 = ?,\n" +
        "margen_utilidad2 = ?,\n" +
        "precio_venta2 = ?,\n" +
        "precio_venta_neto2 = ?,\n" +
        "margen_utilidad3 = ?,\n" +
        "precio_venta3 = ?,\n" +
        "precio_venta_neto3 = ?,\n" +
        "margen_utilidad4 = ?,\n" +
        "precio_venta4 = ?,\n" +
        "precio_venta_neto4 = ?,\n" +
        "modificado_por = ?,\n" +
        "servicio = ?,\n" +
        "departamento_id = ?,\n" +
        "caracteristicas = ?\n" +
        "WHERE id = ?";
        
        

        try {
            PreparedStatement ps = getPrepareStatement(sql);

            ps.setString(1, ab.getClave());
            ps.setString(2, ab.getClave_alterna());
            ps.setString(3, ab.getDescripcion());
            ps.setInt(4, ab.getCategoria_id());
            ps.setInt(5, ab.getUnidad_compra_id());
            ps.setInt(6, ab.getUnidad_venta_id());
            ps.setInt(7, ab.getFactor());
            ps.setInt(8, ab.getInventario_minimo());
            ps.setInt(9, ab.getInventario_maximo());
            ps.setDouble(10, ab.getPrecio_compra());
            ps.setInt(11, ab.getNeto());
            ps.setDouble(12, ab.getPrecio_unidad_compra());
            ps.setDouble(13, ab.getPrecio_unidad_venta());
            ps.setDouble(14, ab.getMargen_utilidad1());
            ps.setDouble(15, ab.getPrecio_venta1());
            ps.setDouble(16, ab.getPrecio_venta_neto1());

            ps.setDouble(17, ab.getMargen_utilidad2());
            ps.setDouble(18, ab.getPrecio_venta2());
            ps.setDouble(19, ab.getPrecio_venta_neto2());

            ps.setDouble(20, ab.getMargen_utilidad3());
            ps.setDouble(21, ab.getPrecio_venta3());
            ps.setDouble(22, ab.getPrecio_venta_neto3());

            ps.setDouble(23, ab.getMargen_utilidad4());
            ps.setDouble(24, ab.getPrecio_venta4());
            ps.setDouble(25, ab.getPrecio_venta_neto4());
            
            ps.setInt(26, ab.getModificado_por());
            ps.setInt(27, ab.getServicio());
            ps.setInt(28, ab.getDepartamento_id());
            ps.setString(29, ab.getCaracteristica());
            ps.setInt(30, ab.getId());

            resultado = ps.executeUpdate();
            
            BitacoraArticuloBean ba = new BitacoraArticuloBean();
            ba.setArticulo_id(ab.getId());
            ba.setAccion(4);
            ba.setAntes(existencia);
            ba.setDespues(existencia);
            ba.setEstado(estado.toJSONString());
            ba.setSql_text(ps.toString());
            ba.setHost(ab.getHost());
            ba.setIp(ab.getIp());
            ba.setUsuario_id(ab.getModificado_por());
            ba.setComentario("ARTÍCULO MODIFICADO");
            
            new BitacoraArticulo().setBitacoraEditarArticulo(ba, cnn);
            //new BitacoraArticulo().
            
            commit();
        } catch (SQLException ex) {
            rollback();
            System.out.println("el metodo updateArticulo ha lanzado el siguiente error " + ex.getMessage());
            throw new SqlAppsException(ex);
            //Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                closePStmt();
            } catch (SQLException ex) {
                Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion(cnn);

        }

        return resultado;
        
    }
    
    
    

    @Override
    public int deleteArticulo(ArticuloBean ab) throws SqlAppsException {

        int resultado = 0;

        //getConexion(EDriver.MYSQL, EApps.PV);

        Connection cnn = getConexionSinCommit(EDriver.MYSQL, EApps.PV);
        
        String sql = "UPDATE cat_articulo set estatus = 'D', modificado_por = ? WHERE id = ?";

        PreparedStatement ps = null;
        try {
            //ps = getPrepareStatement(sql);
            ps = cnn.prepareStatement(sql);
            ps.setInt(1, ab.getModificado_por());
            ps.setInt(2, ab.getId());

            resultado = ps.executeUpdate();
//            if(resultado != 0){
//                BitacoraArticuloBean bb = new BitacoraArticuloBean();
//                bb.setAccion(2);
//                bb.setUsuario_id(ab.getCreado_por());
//                
//           }
            
            
            commit();
        } catch (SQLException ex) {
            rollback();
            System.out.println("el metodo deleteArticulo ha lanzado el siguiente error " + ex.getMessage());
            throw new SqlAppsException(ex);
            //Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(ps != null){
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion(cnn);

        }

        return resultado;

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    public int deleteArticulo(ArticuloBean ab, String ip, String host) throws SqlAppsException {

        int resultado = 0;
        double existencia = 0d;
        //getConexion(EDriver.MYSQL, EApps.PV);

        Connection cnn = getConexionSinCommit(EDriver.MYSQL, EApps.PV);
        
        String sql = "UPDATE cat_articulo set estatus = 'D', modificado_por = ? WHERE id = ?";

        PreparedStatement ps = null;
        try {
            //ps = getPrepareStatement(sql);
            ps = cnn.prepareStatement(sql);
            ps.setInt(1, ab.getModificado_por());
            ps.setInt(2, ab.getId());

            resultado = ps.executeUpdate();
            if(resultado != 0){
                
                JSONObject estado = new InvArticulo().getEstadoInvArticulo(ab.getId(), cnn);
                existencia = getExistencia(ab.getId(),cnn);
                BitacoraArticuloBean bb = new BitacoraArticuloBean();
                bb.setAccion(2);
                bb.setEstado(estado.toJSONString());
                bb.setUsuario_id(ab.getCreado_por());
                bb.setIp(ip);
                bb.setHost(host);
                bb.setSql_text(ps.toString());
                bb.setComentario("ARTICULO ELIMINADO POR USUARIO");
                bb.setAntes(existencia);
                bb.setDespues(existencia);
                bb.setArticulo_id(ab.getId());
                
                new BitacoraArticulo().setBitacoraEliminarArticulo(bb, cnn);
            }
            
            
            commit();
        } catch (SQLException ex) {
            rollback();
            System.out.println("el metodo deleteArticulo ha lanzado el siguiente error " + ex.getMessage());
            throw new SqlAppsException(ex);
            //Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(ps != null){
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
            }

            closeConexion(cnn);

        }

        return resultado;

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public ListIterator<ArticuloBean> getArticulo() throws SqlAppsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<ArticuloBean> getArticulo(String campo, String buscar) throws SqlAppsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<ArticuloBean> getArticulo(int id) throws SqlAppsException {

        LinkedList<ArticuloBean> lista = new LinkedList<>();
        
        Locale locale = new Locale("en", "US");      
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        DecimalFormat df = new DecimalFormat("###,###.##");
        
        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "SELECT a.id,\n"
                + "a.clave,\n"
                + "a.clave_alterna,\n"
                + "a.servicio,\n"
                + "a.descripcion,\n"
                + "a.departamento_id,\n"
                + "a.unidad_compra_id,\n"
                + "a.unidad_venta_id,\n"
                + "a.factor,\n"
                + "a.inventario_minimo,\n"
                + "a.inventario_maximo,\n"
                + "a.localizacion,\n"
                + "a.impuesto,\n"
                + "a.precio_compra,\n"
                + "a.neto,\n"
                + "a.precio_unidad_compra,\n"
                + "a.precio_unidad_venta,\n"
                + "a.margen_utilidad1,\n"
                + "a.margen_utilidad2,\n"
                + "a.margen_utilidad3,\n"
                + "a.margen_utilidad4,\n"
                + "a.precio_venta1,\n"
                + "a.precio_venta2,\n"
                + "a.precio_venta3,\n"
                + "a.precio_venta4,\n"
                + "a.precio_venta_neto1,\n"
                + "a.precio_venta_neto2,\n"
                + "a.precio_venta_neto3,\n"
                + "a.precio_venta_neto4,\n"
                + "a.serie,\n"
                + "a.creado_por,\n"
                + "date_format(a.fecha_creacion,'%d/%m/%Y %H:%i:%s') fecha_creacion,\n"
                + "a.modificado_por,\n"
                + "date_format(a.fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion,\n"
                + "a.estatus,\n"
                + "a.sucursal,\n"
                + "a.caracteristicas,\n"
                + "b.id as categoria_id,\n"
                + "b.descripcion as categoria,\n"
                + "c.id as departamento_id,\n"
                + "c.descripcion as departamento,\n"
                + "d.descripcion as unidad_compra,\n"
                + "e.descripcion as unidad_venta,\n"
                + "f.existencia\n"
                + "FROM cat_articulo a\n"
                + "LEFT JOIN cat_categoria b\n"
                + "ON a.categoria_id = b.id\n"
                + "LEFT JOIN cat_departamento c\n"
                + "ON a.departamento_id = c.id\n"
                + "LEFT JOIN cat_unidad d\n"
                + "ON a.unidad_compra_id = d.id\n"
                + "LEFT JOIN cat_unidad e\n"
                + "ON a.unidad_venta_id = e.id\n"
                + "LEFT JOIN inv_articulo f\n"
                + "ON a.id = f.articulo_id\n"
                + "where a.id = ? and a.estatus ='A'";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getPrepareStatement(sql);

            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                ArticuloBean ab = new ArticuloBean();

                ab.setId(rs.getInt("id"));
                ab.setClave(rs.getString("clave"));
                ab.setClave_alterna(rs.getString("clave_alterna"));
                ab.setServicio(rs.getInt("servicio"));
                ab.setDescripcion(rs.getString("descripcion"));
                ab.setDepartamento_id(rs.getInt("departamento_id"));
                ab.setDepartamento(rs.getString("departamento"));
                ab.setUnidad_compra_id(rs.getInt("unidad_compra_id"));
                ab.setUnidad_compra(rs.getString("unidad_compra"));
                ab.setUnidad_venta_id(rs.getInt("unidad_venta_id"));
                ab.setUnidad_venta(rs.getString("unidad_venta"));
                ab.setFactor(rs.getInt("factor"));
                ab.setInventario_maximo(rs.getInt("inventario_maximo"));
                ab.setInventario_minimo(rs.getInt("inventario_minimo"));
                ab.setLocalizacion(rs.getString("localizacion"));
                ab.setImpuesto(rs.getInt("impuesto"));
                ab.setPrecio_compra(rs.getDouble("precio_compra"));
                ab.setS_precio_compra(df.format(rs.getDouble("precio_compra")));
                
                //System.out.println("Precio compra "+df.format(rs.getDouble("precio_compra")));
                ab.setNeto(rs.getInt("neto"));
                ab.setPrecio_unidad_compra(rs.getDouble("precio_unidad_compra"));
                ab.setPrecio_unidad_venta(rs.getDouble("precio_unidad_venta"));
                ab.setMargen_utilidad1(rs.getDouble("margen_utilidad1"));
                ab.setMargen_utilidad2(rs.getDouble("margen_utilidad2"));
                ab.setMargen_utilidad3(rs.getDouble("margen_utilidad3"));
                ab.setMargen_utilidad4(rs.getDouble("margen_utilidad4"));
                ab.setPrecio_venta1(rs.getDouble("precio_venta1"));
                ab.setPrecio_venta2(rs.getDouble("precio_venta2"));
                ab.setPrecio_venta3(rs.getDouble("precio_venta3"));
                ab.setPrecio_venta4(rs.getDouble("precio_venta4"));
                ab.setPrecio_venta_neto1(rs.getDouble("precio_venta_neto1"));
                ab.setPrecio_venta_neto2(rs.getDouble("precio_venta_neto2"));
                ab.setPrecio_venta_neto3(rs.getDouble("precio_venta_neto3"));
                ab.setPrecio_venta_neto4(rs.getDouble("precio_venta_neto4"));
                ab.setSerie(rs.getString("serie"));
                ab.setSucursal(rs.getInt("sucursal"));
                ab.setCategoria_id(rs.getInt("categoria_id"));
                ab.setCategoria(rs.getString("categoria"));
                ab.setDepartamento_id(rs.getInt("departamento_id"));
                ab.setDepartamento(rs.getString("departamento"));
                ab.setUnidad_compra(rs.getString("unidad_compra"));
                ab.setUnidad_venta(rs.getString("unidad_venta"));
                ab.setExistencia(rs.getInt("existencia"));
                ab.setCaracteristica(rs.getString("caracteristicas"));

                lista.add(ab);

            }

        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Articulo.class.getName() + "-- metodo: getArticulo", LOGGER);
        } finally {
            if (rs != null) {
                try {
                    closeResultSet();
                } catch (SQLException ex) {
                    Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (ps != null) {
                try {
                    closePStmt();
                } catch (SQLException ex) {
                    Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            closeConexion();
        }

        return lista.listIterator();
    }

    @Override
    public ArticuloBean getArticuloBean(int id) throws SqlAppsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<ArticuloBean> buscarArticulo(String buscar, int todos_dep, int todos_cat, int con_existencia, int sin_existencia) throws SqlAppsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JSONObject getListaArticulo(String buscar, String tipo_busqueda, int categorias, int departamentos, int categoria_id, int departamento_id, int con_existencia, int sin_existencia) throws SqlAppsException {

        ResultSet rs = null;
        Statement stmt = null;
        int contador = 0;
        JSONObject articulo = new JSONObject();
        JSONArray data = new JSONArray();

        getConexion(EDriver.MYSQL, EApps.PV);
        
        DecimalFormat df = new DecimalFormat("###,###.##");

        String sql = "SELECT a.id,\n"
                + "a.clave,\n"
                + "a.clave_alterna,\n"
                + "a.servicio,\n"
                + "a.descripcion,\n"
                + "a.departamento_id,\n"
                + "a.unidad_compra_id,\n"
                + "a.unidad_venta_id,\n"
                + "a.factor,\n"
                + "a.inventario_minimo,\n"
                + "a.inventario_maximo,\n"
                + "a.localizacion,\n"
                + "a.impuesto,\n"
                + "a.precio_compra,\n"
                + "a.neto,\n"
                + "a.precio_unidad_compra,\n"
                + "a.precio_unidad_venta,\n"
                + "a.margen_utilidad1,\n"
                + "a.margen_utilidad2,\n"
                + "a.margen_utilidad3,\n"
                + "a.margen_utilidad4,\n"
                + "a.precio_venta1,\n"
                + "a.precio_venta2,\n"
                + "a.precio_venta3,\n"
                + "a.precio_venta4,\n"
                + "a.precio_venta_neto1,\n"
                + "a.precio_venta_neto2,\n"
                + "a.precio_venta_neto3,\n"
                + "a.precio_venta_neto4,\n"
                + "a.serie,\n"
                + "a.creado_por,\n"
                + "date_format(a.fecha_creacion,'%d/%m/%Y %H:%i:%s') fecha_creacion,\n"
                + "a.modificado_por,\n"
                + "date_format(a.fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion,\n"
                + "a.estatus,\n"
                + "a.sucursal,\n"
                + "b.id as categoria_id,\n"
                + "b.descripcion as categoria,\n"
                + "c.id as departamento_id,\n"
                + "c.descripcion as departamento,\n"
                + "d.descripcion as unidad_compra,\n"
                + "e.descripcion as unidad_venta,\n"
                + "f.existencia\n"
                + "FROM cat_articulo a\n"
                + "LEFT JOIN cat_categoria b\n"
                + "ON a.categoria_id = b.id\n"
                + "LEFT JOIN cat_departamento c\n"
                + "ON a.departamento_id = c.id\n"
                + "LEFT JOIN cat_unidad d\n"
                + "ON a.unidad_compra_id = d.id\n"
                + "LEFT JOIN cat_unidad e\n"
                + "ON a.unidad_venta_id = e.id\n"
                + "LEFT JOIN inv_articulo f\n"
                + "ON a.id = f.articulo_id \n";
                
                if(sin_existencia == 0 && con_existencia == 1){
                
                    sql = sql +  "and f.existencia > 0\n";
                }
                if(sin_existencia == 1 && con_existencia == 0){
                
                    sql = sql +  "and f.existencia = 0\n";
                }
                sql = sql + "WHERE\n";
                if(tipo_busqueda.compareTo("clave") == 0){        
                    sql = sql + " a.clave like '%"+buscar+"%'\n";
                }else{
                    sql = sql + " a.descripcion like '%"+buscar+"%'\n";
                }
                if(categorias == 0){
                    sql = sql + " and a.categoria_id = "+categoria_id+"\n";
                }
                if(departamentos == 0){
                    sql = sql + " and a.departamento_id = "+departamento_id+"\n";
                }
                sql = sql +" and a.estatus = 'A' ORDER BY a.descripcion";
                
                //System.out.println("sql "+sql);
                
        try {
            stmt = getStatement();
            
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                JSONArray articulos = new JSONArray();
                JSONObject articuloJSON = new JSONObject();
                
                articuloJSON.put("id", rs.getInt("id"));
                articuloJSON.put("clave", rs.getString("clave"));
                articuloJSON.put("clave_alterna", rs.getString("clave_alterna"));
                articuloJSON.put("servicio",rs.getString("servicio"));
                articuloJSON.put("descripcion",rs.getString("descripcion"));
                articuloJSON.put("departamento_id",rs.getInt("departamento_id"));
                articuloJSON.put("unidad_compra_id",rs.getInt("unidad_compra_id"));
                articuloJSON.put("unidad_venta_id",rs.getInt("unidad_venta_id"));
                articuloJSON.put("factor",rs.getInt("factor"));
                articuloJSON.put("inventario_minimo",rs.getInt("inventario_minimo"));
                articuloJSON.put("inventario_maximo",rs.getInt("inventario_maximo"));
                articuloJSON.put("localizacion",rs.getString("localizacion"));
                articuloJSON.put("impuesto",rs.getInt("impuesto"));
                articuloJSON.put("precio_compra",rs.getDouble("precio_compra"));
                articuloJSON.put("s_precio_compra",df.format(rs.getDouble("precio_compra")));
                articuloJSON.put("neto",rs.getInt("neto"));
                articuloJSON.put("precio_unidad_compra",rs.getDouble("precio_unidad_compra"));
                articuloJSON.put("s_precio_unidad_compra",df.format(rs.getDouble("precio_unidad_compra")));
                articuloJSON.put("precio_unidad_venta",rs.getDouble("precio_unidad_venta"));
                articuloJSON.put("s_precio_unidad_venta",df.format(rs.getDouble("precio_unidad_venta")));
                articuloJSON.put("margen_utilidad1",rs.getDouble("margen_utilidad1"));
                articuloJSON.put("margen_utilidad2",rs.getDouble("margen_utilidad2"));
                articuloJSON.put("margen_utilidad3",rs.getDouble("margen_utilidad3"));
                articuloJSON.put("margen_utilidad4",rs.getDouble("margen_utilidad4"));
                articuloJSON.put("precio_venta1",rs.getDouble("precio_venta1"));
                articuloJSON.put("s_precio_venta1",df.format(rs.getDouble("precio_venta1")));
                articuloJSON.put("precio_venta2",rs.getDouble("precio_venta2"));
                articuloJSON.put("s_precio_venta2",df.format(rs.getDouble("precio_venta2")));
                articuloJSON.put("precio_venta3",rs.getDouble("precio_venta3"));
                articuloJSON.put("s_precio_venta3",df.format(rs.getDouble("precio_venta3")));
                articuloJSON.put("precio_venta4",rs.getDouble("precio_venta4"));
                articuloJSON.put("s_precio_venta4",df.format(rs.getDouble("precio_venta4")));
                articuloJSON.put("precio_venta_neto1",rs.getDouble("precio_venta_neto1"));
                articuloJSON.put("precio_venta_neto2",rs.getDouble("precio_venta_neto2"));
                articuloJSON.put("precio_venta_neto3",rs.getDouble("precio_venta_neto3"));
                articuloJSON.put("precio_venta_neto4",rs.getDouble("precio_venta_neto4"));
                articuloJSON.put("serie", rs.getString("serie"));
                articuloJSON.put("creado_por",rs.getInt("creado_por"));
                articuloJSON.put("fecha_creacion",rs.getString("fecha_creacion"));
                articuloJSON.put("modificado_por",rs.getInt("modificado_por"));
                articuloJSON.put("fecha_modificacion",rs.getString("fecha_modificacion"));
                articuloJSON.put("estatus",rs.getString("estatus"));
                articuloJSON.put("sucursal",rs.getString("sucursal"));
                articuloJSON.put("categoria_id", rs.getInt("categoria_id"));
                articuloJSON.put("categoria", rs.getString("categoria"));
                articuloJSON.put("departamento_id", rs.getInt("departamento_id"));
                articuloJSON.put("departamento",rs.getString("departamento"));
                articuloJSON.put("unidad_compra",rs.getString("unidad_compra"));
                articuloJSON.put("unidad_venta",rs.getString("unidad_venta"));
                articuloJSON.put("existencia",rs.getInt("existencia"));
                
                
                data.add(articuloJSON);

                contador++;

            }
            
            articulo.put("data", data);
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Articulo.class.getName() + "-- metodo: getProveedorLista", LOGGER);
        } finally {
            try {

                
                if (rs != null) {
                    rs.close();
                }
                
                if(stmt != null){
                    stmt.close();
                } 

            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Articulo.class.getName() + "-- metodo: getProveedorLista", LOGGER);
            }

            closeConexion();

        }
        
        return articulo;

    }
    
    
    public JSONObject getLista() throws SqlAppsException {

        ResultSet rs = null;
        Statement stmt = null;
        int contador = 0;
        JSONObject articulo = new JSONObject();
        JSONArray data = new JSONArray();

        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "SELECT a.id,\n"
                + "a.clave,\n"
                + "a.clave_alterna,\n"
                + "a.servicio,\n"
                + "a.descripcion,\n"
                + "a.departamento_id,\n"
                + "a.unidad_compra_id,\n"
                + "a.unidad_venta_id,\n"
                + "a.factor,\n"
                + "a.inventario_minimo,\n"
                + "a.inventario_maximo,\n"
                + "a.localizacion,\n"
                + "a.impuesto,\n"
                + "a.precio_compra,\n"
                + "a.neto,\n"
                + "a.precio_unidad_compra,\n"
                + "a.precio_unidad_venta,\n"
                + "a.margen_utilidad1,\n"
                + "a.margen_utilidad2,\n"
                + "a.margen_utilidad3,\n"
                + "a.margen_utilidad4,\n"
                + "a.precio_venta1,\n"
                + "a.precio_venta2,\n"
                + "a.precio_venta3,\n"
                + "a.precio_venta4,\n"
                + "a.precio_venta_neto1,\n"
                + "a.precio_venta_neto2,\n"
                + "a.precio_venta_neto3,\n"
                + "a.precio_venta_neto4,\n"
                + "a.serie,\n"
                + "a.creado_por,\n"
                + "date_format(a.fecha_creacion,'%d/%m/%Y %H:%i:%s') fecha_creacion,\n"
                + "a.modificado_por,\n"
                + "date_format(a.fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion,\n"
                + "a.estatus,\n"
                + "a.sucursal,\n"
                + "b.id as categoria_id,\n"
                + "b.descripcion as categoria,\n"
                + "c.id as departamento_id,\n"
                + "c.descripcion as departamento,\n"
                + "d.descripcion as unidad_compra,\n"
                + "e.descripcion as unidad_venta,\n"
                + "f.existencia\n"
                + "FROM cat_producto a\n"
                + "LEFT JOIN cat_categoria b\n"
                + "ON a.categoria_id = b.id\n"
                + "LEFT JOIN cat_departamento c\n"
                + "ON a.departamento_id = c.id\n"
                + "LEFT JOIN cat_unidad d\n"
                + "ON a.unidad_compra_id = d.id\n"
                + "LEFT JOIN cat_unidad e\n"
                + "ON a.unidad_venta_id = e.id\n"
                + "LEFT JOIN inv_articulo f\n"
                + "ON a.id = f.articulo_id WHERE a.estatus = 'A'\n";
                
                
                
        try {
            stmt = getStatement();
            
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                JSONArray articulos = new JSONArray();
                JSONObject articuloJSON = new JSONObject();
                
                articuloJSON.put("id", rs.getInt("id"));
                articuloJSON.put("clave", rs.getString("clave"));
                articuloJSON.put("clave_alterna", rs.getString("clave_alterna"));
                articuloJSON.put("servicio",rs.getString("servicio"));
                articuloJSON.put("descripcion",rs.getString("descripcion"));
                articuloJSON.put("departamento_id",rs.getInt("departamento_id"));
                articuloJSON.put("unidad_compra_id",rs.getInt("unidad_compra_id"));
                articuloJSON.put("unidad_venta_id",rs.getInt("unidad_venta_id"));
                articuloJSON.put("factor",rs.getInt("factor"));
                articuloJSON.put("inventario_minimo",rs.getInt("inventario_minimo"));
                articuloJSON.put("inventario_maximo",rs.getInt("inventario_maximo"));
                articuloJSON.put("localizacion",rs.getString("localizacion"));
                articuloJSON.put("impuesto",rs.getInt("impuesto"));
                articuloJSON.put("precio_compra",rs.getDouble("precio_compra"));
                articuloJSON.put("neto",rs.getInt("neto"));
                articuloJSON.put("precio_unidad_compra",rs.getDouble("precio_unidad_compra"));
                articuloJSON.put("precio_unidad_venta",rs.getDouble("precio_unidad_venta"));
                articuloJSON.put("margen_utilidad1",rs.getDouble("margen_utilidad1"));
                articuloJSON.put("margen_utilidad2",rs.getDouble("margen_utilidad2"));
                articuloJSON.put("margen_utilidad3",rs.getDouble("margen_utilidad3"));
                articuloJSON.put("margen_utilidad4",rs.getDouble("margen_utilidad4"));
                articuloJSON.put("precio_venta1",rs.getDouble("precio_venta1"));
                articuloJSON.put("precio_venta2",rs.getDouble("precio_venta2"));
                articuloJSON.put("precio_venta3",rs.getDouble("precio_venta3"));
                articuloJSON.put("precio_venta4",rs.getDouble("precio_venta4"));
                articuloJSON.put("precio_venta_neto1",rs.getDouble("precio_venta_neto1"));
                articuloJSON.put("precio_venta_neto2",rs.getDouble("precio_venta_neto2"));
                articuloJSON.put("precio_venta_neto3",rs.getDouble("precio_venta_neto3"));
                articuloJSON.put("precio_venta_neto4",rs.getDouble("precio_venta_neto4"));
                articuloJSON.put("serie", rs.getString("serie"));
                articuloJSON.put("creado_por",rs.getInt("creado_por"));
                articuloJSON.put("fecha_creacion",rs.getString("fecha_creacion"));
                articuloJSON.put("modificado_por",rs.getInt("modificado_por"));
                articuloJSON.put("fecha_modificacion",rs.getString("fecha_modificacion"));
                articuloJSON.put("estatus",rs.getString("estatus"));
                articuloJSON.put("sucursal",rs.getString("sucursal"));
                articuloJSON.put("categoria_id", rs.getInt("categoria_id"));
                articuloJSON.put("categoria", rs.getString("categoria"));
                articuloJSON.put("departamento_id", rs.getInt("departamento_id"));
                articuloJSON.put("departamento",rs.getString("departamento"));
                articuloJSON.put("unidad_compra",rs.getString("unidad_compra"));
                articuloJSON.put("unidad_venta",rs.getString("unidad_venta"));
                articuloJSON.put("existencia",rs.getInt("existencia"));
                
                
                data.add(articuloJSON);

                contador++;

            }
            
            articulo.put("data", data);
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Articulo.class.getName() + "-- metodo: getProveedorLista", LOGGER);
        } finally {
            try {

                
                if (rs != null) {
                    rs.close();
                }
                
                if(stmt != null){
                    stmt.close();
                } 

            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Articulo.class.getName() + "-- metodo: getProveedorLista", LOGGER);
            }

            closeConexion();

        }
        
        return articulo;

    }
    
    
    
    public JSONObject getArticuloComp(int id) throws SqlAppsException {

        ResultSet rs = null;
        PreparedStatement ps = null;
        
        JSONObject articuloJSON = new JSONObject();
        
        getConexion(EDriver.MYSQL, EApps.PV);

        String sql = "SELECT a.id,\n"
                + "a.clave,\n"
                + "a.clave_alterna,\n"
                + "a.servicio,\n"
                + "a.descripcion,\n"
                + "a.departamento_id,\n"
                + "a.unidad_compra_id,\n"
                + "a.unidad_venta_id,\n"
                + "a.factor,\n"
                + "a.inventario_minimo,\n"
                + "a.inventario_maximo,\n"
                + "a.localizacion,\n"
                + "a.impuesto,\n"
                + "a.precio_compra,\n"
                + "a.neto,\n"
                + "a.precio_unidad_compra,\n"
                + "a.precio_unidad_venta,\n"
                + "a.margen_utilidad1,\n"
                + "a.margen_utilidad2,\n"
                + "a.margen_utilidad3,\n"
                + "a.margen_utilidad4,\n"
                + "a.precio_venta1,\n"
                + "a.precio_venta2,\n"
                + "a.precio_venta3,\n"
                + "a.precio_venta4,\n"
                + "a.precio_venta_neto1,\n"
                + "a.precio_venta_neto2,\n"
                + "a.precio_venta_neto3,\n"
                + "a.precio_venta_neto4,\n"
                + "a.serie,\n"
                + "a.creado_por,\n"
                + "date_format(a.fecha_creacion,'%d/%m/%Y %H:%i:%s') fecha_creacion,\n"
                + "a.modificado_por,\n"
                + "date_format(a.fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion,\n"
                + "a.estatus,\n"
                + "a.sucursal,\n"
                + "b.id as categoria_id,\n"
                + "b.descripcion as categoria,\n"
                + "c.id as departamento_id,\n"
                + "c.descripcion as departamento,\n"
                + "d.descripcion as unidad_compra,\n"
                + "e.descripcion as unidad_venta,\n"
                + "f.existencia\n"
                + "FROM cat_producto a\n"
                + "LEFT JOIN cat_categoria b\n"
                + "ON a.categoria_id = b.id\n"
                + "LEFT JOIN cat_departamento c\n"
                + "ON a.departamento_id = c.id\n"
                + "LEFT JOIN cat_unidad d\n"
                + "ON a.unidad_compra_id = d.id\n"
                + "LEFT JOIN cat_unidad e\n"
                + "ON a.unidad_venta_id = e.id\n"
                + "LEFT JOIN inv_articulo f\n"
                + "ON a.id = f.articulo_id WHERE a.id = ? and a.estatus = 'A'\n";
                
                
                
        try {
            ps = getPrepareStatement(sql);
            ps.setInt(1, id);
            
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                
                articuloJSON.put("id", rs.getInt("id"));
                articuloJSON.put("clave", rs.getString("clave"));
                articuloJSON.put("clave_alterna", rs.getString("clave_alterna"));
                articuloJSON.put("servicio",rs.getString("servicio"));
                articuloJSON.put("descripcion",rs.getString("descripcion"));
                articuloJSON.put("departamento_id",rs.getInt("departamento_id"));
                articuloJSON.put("unidad_compra_id",rs.getInt("unidad_compra_id"));
                articuloJSON.put("unidad_venta_id",rs.getInt("unidad_venta_id"));
                articuloJSON.put("factor",rs.getInt("factor"));
                articuloJSON.put("inventario_minimo",rs.getInt("inventario_minimo"));
                articuloJSON.put("inventario_maximo",rs.getInt("inventario_maximo"));
                articuloJSON.put("localizacion",rs.getString("localizacion"));
                articuloJSON.put("impuesto",rs.getInt("impuesto"));
                articuloJSON.put("precio_compra",rs.getDouble("precio_compra"));
                articuloJSON.put("neto",rs.getInt("neto"));
                articuloJSON.put("precio_unidad_compra",rs.getDouble("precio_unidad_compra"));
                articuloJSON.put("precio_unidad_venta",rs.getDouble("precio_unidad_venta"));
                articuloJSON.put("margen_utilidad1",rs.getDouble("margen_utilidad1"));
                articuloJSON.put("margen_utilidad2",rs.getDouble("margen_utilidad2"));
                articuloJSON.put("margen_utilidad3",rs.getDouble("margen_utilidad3"));
                articuloJSON.put("margen_utilidad4",rs.getDouble("margen_utilidad4"));
                articuloJSON.put("precio_venta1",rs.getDouble("precio_venta1"));
                articuloJSON.put("precio_venta2",rs.getDouble("precio_venta2"));
                articuloJSON.put("precio_venta3",rs.getDouble("precio_venta3"));
                articuloJSON.put("precio_venta4",rs.getDouble("precio_venta4"));
                articuloJSON.put("precio_venta_neto1",rs.getDouble("precio_venta_neto1"));
                articuloJSON.put("precio_venta_neto2",rs.getDouble("precio_venta_neto2"));
                articuloJSON.put("precio_venta_neto3",rs.getDouble("precio_venta_neto3"));
                articuloJSON.put("precio_venta_neto4",rs.getDouble("precio_venta_neto4"));
                articuloJSON.put("serie", rs.getString("serie"));
                articuloJSON.put("creado_por",rs.getInt("creado_por"));
                articuloJSON.put("fecha_creacion",rs.getString("fecha_creacion"));
                articuloJSON.put("modificado_por",rs.getInt("modificado_por"));
                articuloJSON.put("fecha_modificacion",rs.getString("fecha_modificacion"));
                articuloJSON.put("estatus",rs.getString("estatus"));
                articuloJSON.put("sucursal",rs.getString("sucursal"));
                articuloJSON.put("categoria_id", rs.getInt("categoria_id"));
                articuloJSON.put("categoria", rs.getString("categoria"));
                articuloJSON.put("departamento_id", rs.getInt("departamento_id"));
                articuloJSON.put("departamento",rs.getString("departamento"));
                articuloJSON.put("unidad_compra",rs.getString("unidad_compra"));
                articuloJSON.put("unidad_venta",rs.getString("unidad_venta"));
                articuloJSON.put("existencia",rs.getInt("existencia"));
                
                
               

            }
            
            
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Articulo.class.getName() + "-- metodo: getProveedorLista", LOGGER);
        } finally {
            try {

                
                if (rs != null) {
                    rs.close();
                }
                
                closePStmt();
                      

            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Articulo.class.getName() + "-- metodo: getProveedorLista", LOGGER);
            }

            closeConexion();

        }
        
        return articuloJSON;

    }
    
    
    
    
    public JSONObject getArticuloEstado(int id, Connection cnn) throws SqlAppsException {

        ResultSet rs = null;
        PreparedStatement ps = null;
        
        
        JSONArray articuloArray = new JSONArray();
        JSONObject json = new JSONObject();
        
        

        String sql = "SELECT a.id,\n"
                + "a.clave,\n"
                + "a.clave_alterna,\n"
                + "a.servicio,\n"
                + "a.descripcion,\n"
                + "a.departamento_id,\n"
                + "a.unidad_compra_id,\n"
                + "a.unidad_venta_id,\n"
                + "a.factor,\n"
                + "a.inventario_minimo,\n"
                + "a.inventario_maximo,\n"
                + "a.localizacion,\n"
                + "a.impuesto,\n"
                + "a.precio_compra,\n"
                + "a.neto,\n"
                + "a.precio_unidad_compra,\n"
                + "a.precio_unidad_venta,\n"
                + "a.margen_utilidad1,\n"
                + "a.margen_utilidad2,\n"
                + "a.margen_utilidad3,\n"
                + "a.margen_utilidad4,\n"
                + "a.precio_venta1,\n"
                + "a.precio_venta2,\n"
                + "a.precio_venta3,\n"
                + "a.precio_venta4,\n"
                + "a.precio_venta_neto1,\n"
                + "a.precio_venta_neto2,\n"
                + "a.precio_venta_neto3,\n"
                + "a.precio_venta_neto4,\n"
                + "a.serie,\n"
                + "a.creado_por,\n"
                + "date_format(a.fecha_creacion,'%d/%m/%Y %H:%i:%s') fecha_creacion,\n"
                + "a.modificado_por,\n"
                + "date_format(a.fecha_modificacion,'%d/%m/%Y %H:%i:%s') fecha_modificacion,\n"
                + "a.estatus,\n"
                + "a.sucursal,\n"
                + "a.caracteristicas,\n"
                + "b.id as categoria_id,\n"
                + "b.descripcion as categoria,\n"
                + "c.id as departamento_id,\n"
                + "c.descripcion as departamento,\n"
                + "d.descripcion as unidad_compra,\n"
                + "e.descripcion as unidad_venta,\n"
                + "f.existencia\n"
                + "FROM cat_articulo a\n"
                + "LEFT JOIN cat_categoria b\n"
                + "ON a.categoria_id = b.id\n"
                + "LEFT JOIN cat_departamento c\n"
                + "ON a.departamento_id = c.id\n"
                + "LEFT JOIN cat_unidad d\n"
                + "ON a.unidad_compra_id = d.id\n"
                + "LEFT JOIN cat_unidad e\n"
                + "ON a.unidad_venta_id = e.id\n"
                + "LEFT JOIN inv_articulo f\n"
                + "ON a.id = f.articulo_id WHERE a.id = ? and a.estatus = 'A'\n";
                
                
                
        try {
            ps = cnn.prepareStatement(sql);
            ps.setInt(1, id);
            
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                JSONObject articuloJSON = new JSONObject();
                
                articuloJSON.put("id", rs.getInt("id"));
                articuloJSON.put("clave", rs.getString("clave"));
                articuloJSON.put("clave_alterna", rs.getString("clave_alterna"));
                articuloJSON.put("servicio",rs.getString("servicio"));
                articuloJSON.put("descripcion",rs.getString("descripcion"));
                articuloJSON.put("departamento_id",rs.getInt("departamento_id"));
                articuloJSON.put("unidad_compra_id",rs.getInt("unidad_compra_id"));
                articuloJSON.put("unidad_venta_id",rs.getInt("unidad_venta_id"));
                articuloJSON.put("factor",rs.getInt("factor"));
                articuloJSON.put("inventario_minimo",rs.getInt("inventario_minimo"));
                articuloJSON.put("inventario_maximo",rs.getInt("inventario_maximo"));
                articuloJSON.put("localizacion",rs.getString("localizacion"));
                articuloJSON.put("impuesto",rs.getInt("impuesto"));
                articuloJSON.put("precio_compra",rs.getDouble("precio_compra"));
                articuloJSON.put("neto",rs.getInt("neto"));
                articuloJSON.put("precio_unidad_compra",rs.getDouble("precio_unidad_compra"));
                articuloJSON.put("precio_unidad_venta",rs.getDouble("precio_unidad_venta"));
                articuloJSON.put("margen_utilidad1",rs.getDouble("margen_utilidad1"));
                articuloJSON.put("margen_utilidad2",rs.getDouble("margen_utilidad2"));
                articuloJSON.put("margen_utilidad3",rs.getDouble("margen_utilidad3"));
                articuloJSON.put("margen_utilidad4",rs.getDouble("margen_utilidad4"));
                articuloJSON.put("precio_venta1",rs.getDouble("precio_venta1"));
                articuloJSON.put("precio_venta2",rs.getDouble("precio_venta2"));
                articuloJSON.put("precio_venta3",rs.getDouble("precio_venta3"));
                articuloJSON.put("precio_venta4",rs.getDouble("precio_venta4"));
                articuloJSON.put("precio_venta_neto1",rs.getDouble("precio_venta_neto1"));
                articuloJSON.put("precio_venta_neto2",rs.getDouble("precio_venta_neto2"));
                articuloJSON.put("precio_venta_neto3",rs.getDouble("precio_venta_neto3"));
                articuloJSON.put("precio_venta_neto4",rs.getDouble("precio_venta_neto4"));
                articuloJSON.put("serie", rs.getString("serie"));
                articuloJSON.put("creado_por",rs.getInt("creado_por"));
                articuloJSON.put("fecha_creacion",rs.getString("fecha_creacion"));
                articuloJSON.put("modificado_por",rs.getInt("modificado_por"));
                articuloJSON.put("fecha_modificacion",rs.getString("fecha_modificacion"));
                articuloJSON.put("estatus",rs.getString("estatus"));
                articuloJSON.put("sucursal",rs.getString("sucursal"));
                articuloJSON.put("categoria_id", rs.getInt("categoria_id"));
                articuloJSON.put("categoria", rs.getString("categoria"));
                articuloJSON.put("departamento_id", rs.getInt("departamento_id"));
                articuloJSON.put("departamento",rs.getString("departamento"));
                articuloJSON.put("unidad_compra",rs.getString("unidad_compra"));
                articuloJSON.put("unidad_venta",rs.getString("unidad_venta"));
                articuloJSON.put("existencia",rs.getInt("existencia"));
                articuloJSON.put("caracteristicas",rs.getString("caracteristicas"));
                
                
               articuloArray.add(articuloJSON);

            }
            
            json.put("articulos", articuloArray);
            
        } catch (SQLException ex) {
            throw new SqlAppsException(ex, Articulo.class.getName() + "-- metodo: getArticuloEstado", LOGGER);
        } finally {
            try {

                
                if (rs != null) {
                    rs.close();
                }
                
                if (ps != null){
                    ps.close();
                }
                      

            } catch (SQLException ex) {
                throw new SqlAppsException(ex, Articulo.class.getName() + "-- metodo: getArticuloEstado", LOGGER);
            }

            

        }
        
        return json;

    }
    
    
    public int setExistenciaArticulo(int articulo_id, int existencia){
        
        int resultado = 0;
        
        getConexion(EDriver.MYSQL, EApps.PV);
        
        String sql = "INSERT INTO inv_articulo "
                + " VALUES (null, ?, ?, 'A', 'A', 1"
                + ", current_timestamp, 1, current_timestamp)";
        
        try {
            
            PreparedStatement ps = getPrepareStatement(sql);
            
            ps.setInt(1, articulo_id);
            ps.setInt(2, existencia);
            
            resultado = ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return resultado;
    
    }
    
    
    public ListIterator<ArticuloBean> getExistenciaArticulo(int articulo_id) throws SqlAppsException{
        
        LinkedList<ArticuloBean> lista = new LinkedList<>();
        
        getConexion(EDriver.MYSQL, EApps.PV);
        ResultSet rs = null;
        PreparedStatement ps = null;
        String sql = "select \n" +
        "a.id,\n" +
        "a.descripcion,\n" +
        "b.existencia\n" +
        "from cat_articulo a\n" +
        "LEFT JOIN inv_articulo b\n" +
        "ON a.id = b.articulo_id\n" +
        "WHERE a.id = ?";
        
        try {
            ps = getPrepareStatement(sql);
            ps.setInt(1, articulo_id);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                ArticuloBean ab = new ArticuloBean();
                ab.setId(rs.getInt("id"));
                ab.setDescripcion(rs.getString("descripcion"));
                ab.setExistencia(rs.getInt("existencia"));
                
                lista.add(ab);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            closeConexion();
        }
        
        return lista.listIterator();
    
    }
    
    
    public double getExistencia(int articulo_id, Connection cnn) throws SqlAppsException{
        
        double existencia = 0;
        
        ResultSet rs = null;
        PreparedStatement ps = null;
        String sql = "select \n" +
        "a.id,\n" +
        "a.descripcion,\n" +
        "b.existencia\n" +
        "from cat_articulo a\n" +
        "LEFT JOIN inv_articulo b\n" +
        "ON a.id = b.articulo_id\n" +
        "WHERE a.id = ?";
        
        try {
            ps = cnn.prepareStatement(sql);
            ps.setInt(1, articulo_id);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                existencia = rs.getDouble("existencia");
                
               
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Articulo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
        }
        
        return existencia;
    
    }

}
