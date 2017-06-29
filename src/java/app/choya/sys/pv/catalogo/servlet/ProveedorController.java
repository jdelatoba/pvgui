/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.catalogo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import app.choya.sys.pv.catalogo.Proveedor;
import app.choya.sys.pv.catalogo.ProveedorBean;
import app.choya.sys.pv.catalogo.ProveedorI;
import app.choya.sys.pv.utils.SqlAppsException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rembao
 */
@WebServlet(name = "ProveedorController", urlPatterns = {"/pv/proveedor.pv"})
public class ProveedorController extends HttpServlet {

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
            //System.out.println("entre a ProveedorController");
            int id = (request.getParameter("id") != null && request.getParameter("id").compareTo("") != 0) ? Integer.parseInt(request.getParameter("id")) : 0;
            String representante = (request.getParameter("representante") != null && request.getParameter("representante").compareTo("") != 0) ? request.getParameter("representante") : "";
            String nombre = (request.getParameter("nombre") != null && request.getParameter("nombre").compareTo("") != 0) ? request.getParameter("nombre") : "";
            String alias = (request.getParameter("alias") != null && request.getParameter("alias").compareTo("") != 0) ? request.getParameter("alias") : "";
            String rfc = (request.getParameter("rfc") != null && request.getParameter("rfc").compareTo("") != 0) ? request.getParameter("rfc") : "";
            String curp = (request.getParameter("curp") != null && request.getParameter("curp").compareTo("") != 0) ? request.getParameter("curp") : "";

            String domicilio = (request.getParameter("domicilio") != null && request.getParameter("domicilio").compareTo("") != 0) ? request.getParameter("domicilio") : "";
            String no_ext = (request.getParameter("no_ext") != null && request.getParameter("no_ext").compareTo("") != 0) ? request.getParameter("no_ext") : "";
            String no_int = (request.getParameter("no_int") != null && request.getParameter("no_int").compareTo("") != 0) ? request.getParameter("no_int") : "";
            String colonia = (request.getParameter("colonia") != null && request.getParameter("colonia").compareTo("") != 0) ? request.getParameter("colonia") : "";
            String codigo_postal = (request.getParameter("codigo_postal") != null && request.getParameter("codigo_postal").compareTo("") != 0) ? request.getParameter("codigo_postal") : "";
            String localidad = (request.getParameter("localidad") != null && request.getParameter("localidad").compareTo("") != 0) ? request.getParameter("localidad") : "";
            String municipio = (request.getParameter("municipio") != null && request.getParameter("municipio").compareTo("") != 0) ? request.getParameter("municipio") : "";

            String entidad = (request.getParameter("entidad") != null && request.getParameter("entidad").compareTo("") != 0) ? request.getParameter("entidad") : "";
            String pais = (request.getParameter("pais") != null && request.getParameter("pais").compareTo("") != 0) ? request.getParameter("pais") : "";
            String telefono = (request.getParameter("telefono") != null && request.getParameter("telefono").compareTo("") != 0) ? request.getParameter("telefono") : "";
            String celular = (request.getParameter("celular") != null && request.getParameter("celular").compareTo("") != 0) ? request.getParameter("celular") : "";
            String email = (request.getParameter("email") != null && request.getParameter("email").compareTo("") != 0) ? request.getParameter("email") : "";
            String comentario = (request.getParameter("comentario") != null && request.getParameter("comentario").compareTo("") != 0) ? request.getParameter("comentario") : "";
            
            String lc = (request.getParameter("limite_credito") != null && request.getParameter("limite_credito").compareTo("") != 0) ? request.getParameter("limite_credito") : "0.0"; 
            lc = lc.replace(",", "");
            double limite_credito = Double.parseDouble(lc);
            //double limite_credito = (request.getParameter("limite_credito") != null && request.getParameter("limite_credito").compareTo("") != 0) ? Double.parseDouble(request.getParameter("limite_credito")) : 0;
            
            int dias_credito = (request.getParameter("dias_credito") != null && request.getParameter("dias_credito").compareTo("") != 0) ? Integer.parseInt(request.getParameter("dias_credito")) : 0;

            String operacion = (request.getParameter("operacion") != null && request.getParameter("operacion").compareTo("") != 0) ? request.getParameter("operacion") : "";

            ProveedorBean pb = new ProveedorBean();
            pb.setId(id);
            pb.setRepresentante(representante);
            pb.setNombre(nombre);
            pb.setAlias(alias);
            pb.setRfc(rfc);
            pb.setCurp(curp);
            pb.setDomicilio(domicilio);
            pb.setNo_ext(no_ext);
            pb.setNo_int(no_int);
            pb.setColonia(colonia);
            pb.setCodigo_postal(codigo_postal);
            pb.setLocalidad(localidad);
            pb.setMunicipio(municipio);
            pb.setEntidad(entidad);
            pb.setPais(pais);
            pb.setTelefono(telefono);
            pb.setCelular(celular);
            pb.setEmail(email);
            pb.setComentario(comentario);
            pb.setLimite_credito(limite_credito);
            pb.setDias_credito(dias_credito);

            ProveedorI proveedor = new Proveedor();
            int resultado = 0;
            //System.out.println("operacion "+operacion); 
            switch (operacion) {
                case "actualizar":
                case "guardar": {
                    try{
                        
                        if(pb.getId() == 0){
                            resultado = proveedor.setProveedor(pb);
                        }else{
                            resultado = proveedor.updateProveedor(pb);
                        }
                        out.println("<input type=\"hidden\" id=\"resultado\" name=\"resultado\" value=\""+resultado+"\" />");
                    } catch (SqlAppsException ex) {
                        
                        out.println("<input type='text' id='resultado' name='resultado' value='"+resultado+"' />");
                        out.println("<input type='text' id='mensaje' name='mensaje' value=\""+ex.getMensaje()+"\" />");
                        out.println("<input type='text' id='codigo_error' name='codigo_error' value='"+ex.getCodigo_error()+"' />");
                        //System.out.println(System.getProperty("java.home"));
                        //Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, "What!!!", ex);
                        
                        
                    }
                }
                break;
                
                case "eliminar_proveedor":{
                        try{
                            
                        resultado = proveedor.deleteProveedor(id);
                        out.println("<input type=\"hidden\" id=\"resultado\" name=\"resultado\" value=\""+resultado+"\" />");
                    } catch (SqlAppsException ex) {
                        
                        out.println("<input type='text' id='resultado' name='resultado' value='"+resultado+"' />");
                        out.println("<input type='text' id='mensaje' name='mensaje' value=\""+ex.getMensaje()+"\" />");
                        out.println("<input type='text' id='codigo_error' name='codigo_error' value='"+ex.getCodigo_error()+"' />");
                        //System.out.println(System.getProperty("java.home"));
                        //Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, "What!!!", ex);
                        
                        
                    }
                    }
                    break;
                case "buscar_proveedor":
                    request.getRequestDispatcher("/WEB-INF/view/catalogos/proveedor_lista.jsp").include(request, response);
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
