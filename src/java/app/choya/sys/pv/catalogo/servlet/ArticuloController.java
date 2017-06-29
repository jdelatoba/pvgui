/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.catalogo.servlet;

import app.choya.sys.pv.catalogo.Articulo;
import app.choya.sys.pv.catalogo.ArticuloBean;
import app.choya.sys.pv.catalogo.ArticuloCompatible;
import app.choya.sys.pv.catalogo.Categoria;
import app.choya.sys.pv.catalogo.CategoriaBean;
import app.choya.sys.pv.catalogo.InvArticulo;
import app.choya.sys.pv.catalogo.InvArticuloBean;
import app.choya.sys.pv.configuracion.Impuesto;
import app.choya.sys.pv.utils.SqlAppsException;
import com.lowagie.text.pdf.PdfName;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Rembao
 */
@WebServlet(name = "ArticuloController", urlPatterns = {"/pv/ArticuloController"})
public class ArticuloController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            HttpSession session = request.getSession(false);
            
            int usuario_id = (int) session.getAttribute("usuario_id");
            String serie = (String) session.getAttribute("serie");
            int sucursal = (int) session.getAttribute("sucursal");
            String ip = (String) session.getAttribute("ip");
            String host = (String) session.getAttribute("host");
            String operacion = (request.getParameter("operacion") != null && request.getParameter("operacion").compareTo("") != 0) ? request.getParameter("operacion") : "";
            int id = (request.getParameter("id") != null && request.getParameter("id").compareTo("") != 0) ? Integer.parseInt(request.getParameter("id")) : 0;
            String clave = (request.getParameter("clave") != null && request.getParameter("clave").compareTo("")  != 0) ? request.getParameter("clave") : "";
            String clave_alterna  = (request.getParameter("clave_alterna") != null && request.getParameter("clave_alterna").compareTo("")  != 0) ? request.getParameter("clave_alterna") : "";
            String descripcion  = (request.getParameter("descripcion") != null && request.getParameter("descripcion").compareTo("")  != 0) ? request.getParameter("descripcion") : "";
            int servicio = (request.getParameter("servicio") != null && request.getParameter("servicio").compareTo("") != 0) ? Integer.parseInt(request.getParameter("servicio")) : 0;
            int categoria_id = (request.getParameter("categoria_id") != null && request.getParameter("categoria_id").compareTo("") != 0) ? Integer.parseInt(request.getParameter("categoria_id")) : 0;
            int departamento_id = (request.getParameter("departamento_id") != null && request.getParameter("departamento_id").compareTo("") != 0) ? Integer.parseInt(request.getParameter("departamento_id")) : 0;
            int unidad_compra_id = (request.getParameter("unidad_compra_id") != null && request.getParameter("unidad_compra_id").compareTo("") != 0) ? Integer.parseInt(request.getParameter("unidad_compra_id")) : 0;
            int unidad_venta_id = (request.getParameter("unidad_venta_id") != null && request.getParameter("unidad_venta_id").compareTo("") != 0) ? Integer.parseInt(request.getParameter("unidad_venta_id")) : 0;
            int factor = (request.getParameter("factor") != null && request.getParameter("factor").compareTo("") != 0) ? Integer.parseInt(request.getParameter("factor")) : 1;
            int inventario_minimo = (request.getParameter("inventario_minimo") != null && request.getParameter("inventario_minimo").compareTo("") != 0) ? Integer.parseInt(request.getParameter("inventario_minimo")) : 0;
            int inventario_maximo = (request.getParameter("inventario_maximo") != null && request.getParameter("inventario_maximo").compareTo("") != 0) ? Integer.parseInt(request.getParameter("inventario_maximo")) : 0;
            String pc = (request.getParameter("precio_compra") != null && request.getParameter("precio_compra").compareTo("") != 0) ? request.getParameter("precio_compra") : "0.0"; 
            pc = pc.replace(",", "");
            double precio_compra = Double.parseDouble(pc);
            int neto = (request.getParameter("neto") != null && request.getParameter("neto").compareTo("") != 0) ? Integer.parseInt(request.getParameter("neto")) : 0;
            double precio_unidad_compra = (request.getParameter("precio_unidad_compra") != null && request.getParameter("precio_unidad_compra").compareTo("") != 0) ? Double.parseDouble(request.getParameter("precio_unidad_compra")) : 0.0d;
            double precio_unidad_venta = (request.getParameter("precio_unidad_venta") != null && request.getParameter("precio_unidad_venta").compareTo("") != 0) ? Double.parseDouble(request.getParameter("precio_unidad_venta")) : 0.0d;
            
            double margen_utilidad = (request.getParameter("margen_utilidad") != null && request.getParameter("margen_utilidad").compareTo("") != 0) ? Double.parseDouble(request.getParameter("margen_utilidad")) : 0.0d;
            double margen_utilidad1 = (request.getParameter("margen_utilidad1") != null && request.getParameter("margen_utilidad1").compareTo("") != 0) ? Double.parseDouble(request.getParameter("margen_utilidad1")) : 0.0d;
            double margen_utilidad2 = (request.getParameter("margen_utilidad2") != null && request.getParameter("margen_utilidad2").compareTo("") != 0) ? Double.parseDouble(request.getParameter("margen_utilidad2")) : 0.0d;
            double margen_utilidad3 = (request.getParameter("margen_utilidad3") != null && request.getParameter("margen_utilidad3").compareTo("") != 0) ? Double.parseDouble(request.getParameter("margen_utilidad3")) : 0.0d;
            double margen_utilidad4 = (request.getParameter("margen_utilidad4") != null && request.getParameter("margen_utilidad4").compareTo("") != 0) ? Double.parseDouble(request.getParameter("margen_utilidad4")) : 0.0d;
            
            //System.out.println("margen_utilidad "+margen_utilidad1);
            
            double precio_venta1 = (request.getParameter("precio_venta1") != null && request.getParameter("precio_venta1").compareTo("") != 0) ? Double.parseDouble(request.getParameter("precio_venta1")) : 0.0d;
            double precio_venta2 = (request.getParameter("precio_venta2") != null && request.getParameter("precio_venta2").compareTo("") != 0) ? Double.parseDouble(request.getParameter("precio_venta2")) : 0.0d;
            double precio_venta3 = (request.getParameter("precio_venta3") != null && request.getParameter("precio_venta3").compareTo("") != 0) ? Double.parseDouble(request.getParameter("precio_venta3")) : 0.0d;
            double precio_venta4 = (request.getParameter("precio_venta4") != null && request.getParameter("precio_venta4").compareTo("") != 0) ? Double.parseDouble(request.getParameter("precio_venta4")) : 0.0d;
            
            double precio_venta_neto1 = (request.getParameter("precio_venta_neto1") != null && request.getParameter("precio_venta_neto1").compareTo("") != 0) ? Double.parseDouble(request.getParameter("precio_venta_neto1")) : 0.0d;
            double precio_venta_neto2 = (request.getParameter("precio_venta_neto2") != null && request.getParameter("precio_venta_neto2").compareTo("") != 0) ? Double.parseDouble(request.getParameter("precio_venta_neto2")) : 0.0d;
            double precio_venta_neto3 = (request.getParameter("precio_venta_neto3") != null && request.getParameter("precio_venta_neto3").compareTo("") != 0) ? Double.parseDouble(request.getParameter("precio_venta_neto3")) : 0.0d;
            double precio_venta_neto4 = (request.getParameter("precio_venta_neto4") != null && request.getParameter("precio_venta_neto4").compareTo("") != 0) ? Double.parseDouble(request.getParameter("precio_venta_neto4")) : 0.0d;
            
            String caracteristica = (request.getParameter("caracteristica") != null && request.getParameter("caracteristica").compareTo("") != 0) ? request.getParameter("caracteristica") : "";
            
            //System.out.println("operacion "+operacion);
            DecimalFormat df = new DecimalFormat("#.###");
            double impuesto = 0.0d;
            try {
                impuesto = new Impuesto().getValorImpuesto(1);
            } catch (SqlAppsException ex) {
                Logger.getLogger(ArticuloController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            switch (operacion) {
                case "guardar":
                case "actualizar":
                    ArticuloBean ab = new ArticuloBean();
                    ab.setId(id);
                    ab.setClave(clave);
                    ab.setClave_alterna(clave_alterna);
                    ab.setDescripcion(descripcion);
                    ab.setServicio(servicio);
                    ab.setCategoria_id(categoria_id);
                    ab.setDepartamento_id(departamento_id);
                    ab.setUnidad_compra_id(unidad_compra_id);
                    ab.setUnidad_venta_id(unidad_venta_id);
                    ab.setFactor(factor);
                    ab.setInventario_maximo(inventario_maximo);
                    ab.setInventario_minimo(inventario_minimo);
                    ab.setPrecio_compra(precio_compra);
                    ab.setNeto(neto);
                    ab.setPrecio_unidad_compra(precio_unidad_compra);
                    ab.setPrecio_unidad_venta(precio_unidad_venta);
                    ab.setMargen_utilidad1(margen_utilidad1);
                    ab.setMargen_utilidad2(margen_utilidad2);
                    ab.setMargen_utilidad3(margen_utilidad3);
                    ab.setMargen_utilidad4(margen_utilidad4);
                    ab.setPrecio_venta1(precio_venta1);
                    ab.setPrecio_venta2(precio_venta2);
                    ab.setPrecio_venta3(precio_venta3);
                    ab.setPrecio_venta4(precio_venta4);
                    ab.setPrecio_venta_neto1(precio_venta_neto1);
                    ab.setPrecio_venta_neto2(precio_venta_neto2);
                    ab.setPrecio_venta_neto3(precio_venta_neto3);
                    ab.setPrecio_venta_neto4(precio_venta_neto4);
                    ab.setSerie(serie);
                    ab.setEstatus("A");
                    ab.setSucursal(sucursal);
                    ab.setCreado_por(usuario_id);
                    ab.setModificado_por(usuario_id);
                    ab.setImpuesto(1);
                    ab.setCaracteristica(caracteristica);
                    ab.setIp(ip);
                    ab.setHost(host);
                    
                    Articulo articulo = new Articulo();
                    
                    InvArticuloBean ib = null;
                    int resultado = 0;
                    if(ab.getId() == 0){
                        try {
                            //guardar
                            resultado = articulo.setArticulo(ab);
                            out.println("<input type=\"hidden\" id=\"resultado\" name=\"resultado\" value=\""+resultado+"\" />");
                        } catch (SqlAppsException ex) {
                            out.println("<input type='hidden' id='resultado' name='resultado' value='"+resultado+"' />");
                            out.println("<input type='hidden' id='mensaje' name='mensaje' value=\""+ex.getMensaje()+"\" />");
                            out.println("<input type='hidden' id='codigo_error' name='codigo_error' value='"+ex.getCodigo_error()+"' />");
                            Logger.getLogger(ArticuloController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{
                        try {
                            resultado = articulo.updateArticulo(ab);
                            out.println("<input type=\"hidden\" id=\"resultado\" name=\"resultado\" value=\""+resultado+"\" />");
                        } catch (SqlAppsException ex) {
                            out.println("<input type='hidden' id='resultado' name='resultado' value='"+resultado+"' />");
                            out.println("<input type='hidden' id='mensaje' name='mensaje' value=\""+ex.getMensaje()+"\" />");
                            out.println("<input type='hidden' id='codigo_error' name='codigo_error' value='"+ex.getCodigo_error()+"' />");
                            
                            Logger.getLogger(ArticuloController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                case "cargar_categorias":
                        request.getRequestDispatcher("../WEB-INF/view/catalogos/categoria_lista.jsp").include(request, response);
                    break;
                case "cargar_departamentos":
                        request.getRequestDispatcher("../WEB-INF/view/catalogos/departamento_lista.jsp").include(request, response);
                    break;    
                case "cargar_unidades_compra":
                        request.getRequestDispatcher("../WEB-INF/view/catalogos/unidad_compra_lista.jsp").include(request, response);
                    break;
                case "cargar_unidades_venta":
                        request.getRequestDispatcher("../WEB-INF/view/catalogos/unidad_venta_lista.jsp").include(request, response);
                    break;
                
                case "carga_valores_catalogo":
                    Categoria categoria = new Categoria();
                        {
                            try {
                                CategoriaBean cb = categoria.getCategoriaResume(categoria_id);

                                out.print("<input type='text' id='rdepartamento_id' name='rdepartamento_id' value='"+cb.getDepartamento_id()+"'>");
                                out.print("<input type='text' id='rdepartamento' name='rdepartamento' value='"+cb.getDepartamento()+"'>");
                            } catch (SqlAppsException ex) {
                                Logger.getLogger(ArticuloController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    
                    
                    break;
                case "calcula_precio_unidad":
                    
                    //calculando precio
                    double unidad_pcompra = 0.0d;
                    double unidad_pventa = 0.0d;
                    if(neto == 1){ //con impuesto
                            
                            
                            unidad_pcompra =  (precio_compra / (1 + impuesto));
                            
                            
                            unidad_pventa = (unidad_pcompra / factor);
                            
                            out.print("<input type='hidden' id='unidad_pcompra' name='unidad_pcompra' value='"+df.format(unidad_pcompra)+"'>");
                            out.print("<input type='hidden' id='unidad_pventa' name='unidad_pventa' value='"+df.format(unidad_pventa)+"'>");
                            
                            
                        
                        
                    }else{
                        
                            unidad_pcompra = precio_compra;
                            unidad_pventa = (unidad_pcompra / factor);
                            
                            out.print("<input type='hidden' id='unidad_pcompra' name='unidad_pcompra' value='"+df.format(unidad_pcompra)+"'>");
                            out.print("<input type='hidden' id='unidad_pventa' name='unidad_pventa' value='"+df.format(unidad_pventa)+"'>");
                            
                    
                    }
                    
                    
                    precio_venta_neto1 = 0.0d;
                    margen_utilidad1 = (margen_utilidad1 / 100);
                    precio_venta1 = unidad_pventa + (unidad_pventa * margen_utilidad1);
                    precio_venta_neto1 = precio_venta1 + (precio_venta1 * impuesto);
                    
                    precio_venta_neto2 = 0.0d;
                    margen_utilidad2 = (margen_utilidad2 / 100);
                    precio_venta2 = unidad_pventa + (unidad_pventa * margen_utilidad2);
                    precio_venta_neto2 = precio_venta2 + (precio_venta2 * impuesto);
                    
                    precio_venta_neto3 = 0.0d;
                    margen_utilidad3 = (margen_utilidad3 / 100);
                    precio_venta3 = unidad_pventa + (unidad_pventa * margen_utilidad3);
                    precio_venta_neto3 = precio_venta3 + (precio_venta3 * impuesto);
                    
                    precio_venta_neto4 = 0.0d;
                    margen_utilidad4 = (margen_utilidad4 / 100);
                    precio_venta4 = unidad_pventa + (unidad_pventa * margen_utilidad4);
                    precio_venta_neto4 = precio_venta4 + (precio_venta4 * impuesto);
                    
                    out.print("<input type='hidden' id='precio_venta_utilidad1' name='precio_venta_utilidad1' value='"+df.format(precio_venta1)+"'>");
                    out.print("<input type='hidden' id='precio_venta_neto_utilidad1' name='precio_venta_neto_utilidad1' value='"+df.format(precio_venta_neto1)+"'>");
                    out.print("<input type='hidden' id='precio_venta_utilidad2' name='precio_venta_utilidad2' value='"+df.format(precio_venta2)+"'>");
                    out.print("<input type='hidden' id='precio_venta_neto_utilidad2' name='precio_venta_neto_utilidad2' value='"+df.format(precio_venta_neto2)+"'>");
                    out.print("<input type='hidden' id='precio_venta_utilidad3' name='precio_venta_utilidad3' value='"+df.format(precio_venta3)+"'>");
                    out.print("<input type='hidden' id='precio_venta_neto_utilidad3' name='precio_venta_neto_utilidad3' value='"+df.format(precio_venta_neto3)+"'>");
                    out.print("<input type='hidden' id='precio_venta_utilidad4' name='precio_venta_utilidad4' value='"+df.format(precio_venta4)+"'>");
                    out.print("<input type='hidden' id='precio_venta_neto_utilidad4' name='precio_venta_neto_utilidad4' value='"+df.format(precio_venta_neto4)+"'>");
                    
                    
                            
                    
                    
                    break;
                    
                case "calcular_precio_porcentaje":
                        double precio_venta_neto = 0.0d;
                        margen_utilidad = (margen_utilidad / 100);
                        precio_unidad_venta = precio_unidad_venta + (precio_unidad_venta * margen_utilidad);
                        
                        precio_venta_neto = precio_unidad_venta + (precio_unidad_venta * impuesto);
                        
                        
                        out.print("<input type='hidden' id='precio_venta_utilidad' name='precio_venta_utilidad' value='"+df.format(precio_unidad_venta)+"'>");
                        out.print("<input type='hidden' id='precio_venta_neto_utilidad' name='precio_venta_neto_utilidad' value='"+df.format(precio_venta_neto)+"'>");
                        
                    
                    break;
                case "ajustar_articulo":
                    request.setAttribute("id", id);
                    request.getRequestDispatcher("/WEB-INF/view/catalogos/articulo_ajustar.jsp").include(request, response);
                    break;
                case "ajustar_existencia":
                    
                    int articulo_id_existencia = (request.getParameter("articulo_id_existencia") != null && request.getParameter("articulo_id_existencia").compareTo("") != 0) ? Integer.parseInt(request.getParameter("articulo_id_existencia")) : 0;
                    String descripcion_existencia = (request.getParameter("descripcion_existencia") != null && request.getParameter("descripcion_existencia").compareTo("") != 0)  ? request.getParameter("descripcion_existencia") : "";
                    double existencia = (request.getParameter("existencia") != null && request.getParameter("existencia").compareTo("") != 0) ? Double.parseDouble(request.getParameter("existencia")) : 0d;
                    double existencia_anterior = (request.getParameter("existencia_anterior") != null && request.getParameter("existencia_anterior").compareTo("") != 0) ? Double.parseDouble(request.getParameter("existencia_anterior")) : 0d;
                    String comentario = (request.getParameter("comentario") != null && request.getParameter("comentario").compareTo("") != 0) ? request.getParameter("comentario") : "";
                    
                    ib = new InvArticuloBean();
                    ib.setExistencia(existencia);
                    ib.setArticulo_id(articulo_id_existencia);
                    ib.setDescripcion(descripcion_existencia);
                    ib.setModificado_por(usuario_id);
                    ib.setCreado_por(usuario_id);
                    ib.setAntes(existencia_anterior);
                    ib.setDespues(existencia);
                    ib.setComentario(comentario);
                    
                    
            {
                int resultado_existencia = 0;
                try {
                    resultado_existencia = new InvArticulo().ajustarExistencia(ib, (String) session.getAttribute("ip"), (String) session.getAttribute("host"));
                    //System.out.println("resultado de actualizar existencia ----->"+resultado_existencia);
                    out.println("<input type=\"hidden\" id=\"resultado\" name=\"resultado\" value=\""+resultado_existencia+"\" />");
                
                } catch (SqlAppsException ex) {
                    
                    out.println("<input type='text' id='resultado' name='resultado' value='"+resultado_existencia+"' />");
                    out.println("<input type='text' id='mensaje' name='mensaje' value=\""+ex.getMensaje()+"\" />");
                    out.println("<input type='text' id='codigo_error' name='codigo_error' value='"+ex.getCodigo_error()+"' />");
                            
                    Logger.getLogger(ArticuloController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;
                    
                    
                case "eliminar_articulo":
                    
                    //System.out.println("entre a eliminar articulo_id :"+id);
                    
                    {
                    
                        ArticuloBean abean = new ArticuloBean();
                        
                        abean.setId(id);
                        abean.setCreado_por(usuario_id);
                        abean.setModificado_por(usuario_id);
                        
                        int resultado_eliminar = 0;        
                        try {
                         resultado_eliminar =    new Articulo().deleteArticulo(abean, ip, host);

                            //System.out.println("resultado de eliminar "+resultado_eliminar);
                            out.println("<input type=\"hidden\" id=\"resultado\" name=\"resultado\" value=\""+resultado_eliminar+"\" />");
                        } catch (SqlAppsException ex) {
                            out.println("<input type='text' id='resultado' name='resultado' value='"+resultado_eliminar+"' />");
                            out.println("<input type='text' id='mensaje' name='mensaje' value=\""+ex.getMensaje()+"\" />");
                            out.println("<input type='text' id='codigo_error' name='codigo_error' value='"+ex.getCodigo_error()+"' />");
                            Logger.getLogger(ArticuloController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
                    }
                    
                    break;
                case "buscar_articulo":
                    request.getRequestDispatcher("/WEB-INF/view/catalogos/articulo_lista.jsp").include(request, response);
                    break;
                case "buscar_articulo_compatible":
                    
                    
                    request.getRequestDispatcher("/WEB-INF/view/catalogos/articulo_busca_compatible.jsp").include(request, response);
                    break;
                case "nuevo_articulo":
                    request.getRequestDispatcher("/WEB-INF/view/catalogos/articulo.jsp").include(request, response);
                    break;
                    
                case "agregar_articulo_compatible":
                    System.out.println("entre a agregar articulo compatible");
                    
                    
                    String articulos[] = request.getParameterValues("articulos[]");
                    ArrayList<String> articulosArray = new ArrayList();
                    for(String item: articulos){
                        //System.out.println("llenado el articulosArray "+item);
                        articulosArray.add(item);
                    }
                    if(id == 0){
                            
                            
                            //verificar si existe array temporal
                            ArrayList<String> articulo_temp = null;

                            boolean encontrado = false;
                            if(session.getAttribute("articulos_temp") != null){
                                //System.out.println("entre a articulos_temp");
                                articulo_temp = (ArrayList<String>) session.getAttribute("articulos_temp");
                                //System.out.println("tamaño de  articulos_temp "+articulo_temp.size());
                                //System.out.println("tamaño de articuloArray "+articulosArray.size());
                                for(String temp :articulo_temp){
                                    //System.out.println("articulo temporal  :"+temp);
                                    for(String item :articulosArray){


                                        if(item.compareTo(temp) == 0){
                                            encontrado = true;

                                        }
                                    }
                                    if(!encontrado){
                                        articulosArray.add(temp);
                                    }
                                    encontrado = false;
                                }

                            }
                        
                            JSONArray articulosJSONArray = new JSONArray();
                            JSONObject jsonArticulo = new JSONObject();


                            for(String item: articulosArray){
                                JSONObject articuloJSON = new JSONObject();


                                ArticuloCompatible a = new ArticuloCompatible();
                                if(item != null && item.compareTo("") != 0){

                                    try {

                                        articuloJSON = a.getArticuloCompatibleInfoById(Integer.parseInt(item));
                                        articulosJSONArray.add(articuloJSON);

                                    } catch (SqlAppsException ex) {
                                        Logger.getLogger(ArticuloController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    //System.out.println("articulos  "+item);
                                }
                            }
                            jsonArticulo.put("data",articulosJSONArray);
                            session.setAttribute("articulos", jsonArticulo);
                            session.setAttribute("articulos_temp", articulosArray);
                        
                    }else{
                        
                        //buscar si tiene articulo compatible
                        
                    
                        
                    }
                        
                        
                        
                        
                        
                    break;
                case "cargar_articulo_compatible_sin_id":{
                
                }
                break;    
                default:
                    break;
            }
        }
    }
    
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
