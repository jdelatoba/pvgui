/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.configuracion.servlet;

import app.choya.sys.pv.configuracion.Vendedor;
import app.choya.sys.pv.configuracion.VendedorBean;
import app.choya.sys.pv.configuracion.VendedorI;
import app.choya.sys.pv.utils.SqlAppsException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rembao
 */
@WebServlet(name = "VendedorController", urlPatterns = {"/admon/VendedorController.pv"})
public class VendedorController extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(VendedorController.class.getName());
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
            throws ServletException, IOException, SqlAppsException {
        
        
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            HttpSession session = request.getSession(false);
            
            int usuario_id = (int) session.getAttribute("usuario_id");
            String serie = (String) session.getAttribute("serie");
            
            String operacion = (request.getParameter("operacion") != null && request.getParameter("operacion").compareTo("") != 0) ? request.getParameter("operacion") : "";
            
            int id = (request.getParameter("id") != null && request.getParameter("id").compareTo("") != 0) ? Integer.parseInt(request.getParameter("id")) : 0;
            String nombre = (request.getParameter("nombre") != null && request.getParameter("nombre").compareTo("") != 0) ? request.getParameter("nombre") : "";
            String vendedor = (request.getParameter("vendedor") != null && request.getParameter("vendedor").compareTo("") != 0) ? request.getParameter("vendedor") : "";
            
            String domicilio = (request.getParameter("domicilio") != null && request.getParameter("domicilio").compareTo("") != 0) ? request.getParameter("domicilio") : "";
            String ciudad = (request.getParameter("ciudad") != null && request.getParameter("ciudad").compareTo("") != 0) ? request.getParameter("ciudad") : "";
            
            String telefono = (request.getParameter("telefono") != null && request.getParameter("telefono").compareTo("") != 0) ? request.getParameter("telefono") : "";
            String celular = (request.getParameter("celular") != null && request.getParameter("celular").compareTo("") != 0) ? request.getParameter("celular") : "";
            String email = (request.getParameter("email") != null && request.getParameter("email").compareTo("") != 0) ? request.getParameter("email") : "";
            String puesto = (request.getParameter("puesto") != null && request.getParameter("puesto").compareTo("") != 0) ? request.getParameter("puesto") : "";
            
            String com = (request.getParameter("comision") != null && request.getParameter("comision").compareTo("") != 0) ? request.getParameter("comision") : "0"; 
            com = com.replace(",", "");
            double comision = Double.parseDouble(com);
            
            int grupo_id = (request.getParameter("grupo_id") != null && request.getParameter("grupo_id").compareTo("") != 0) ? Integer.parseInt(request.getParameter("grupo_id")) : 0;
            
            VendedorI vend = new Vendedor();
            
            VendedorBean vb = new VendedorBean();
            vb.setId(id);
            vb.setVendedor(vendedor);
            vb.setNombre(nombre);
            vb.setDomicilio(domicilio);
            vb.setCiudad(ciudad);
            vb.setTelefono(telefono);
            vb.setCelular(celular);
            vb.setEmail(email);
            vb.setPuesto(puesto);
            vb.setGrupo_id(grupo_id);
            vb.setCreado_por(usuario_id);
            vb.setModificado_por(usuario_id);
            vb.setSerie(serie);
            vb.setComision(comision);
            
            
            int resultado = 0;
            System.out.println("operacion "+operacion); 
            switch (operacion) {
                case "actualizar":
                case "guardar": {
                    try{
                        if(vb.getId() == 0){
                            resultado = vend.setVendedor(vb);
                            out.println("<input type=\"hidden\" id=\"resultado\" name=\"resultado\" value=\""+resultado+"\" />");
                        }else{
                            resultado = vend.updateVendedor(vb);
                            out.println("<input type=\"hidden\" id=\"resultado\" name=\"resultado\" value=\""+resultado+"\" />");
                        }    
                    } catch (SqlAppsException ex) {
                        
                        out.println("<input type='text' id='resultado' name='resultado' value='"+resultado+"' />");
                        out.println("<input type='text' id='mensaje' name='mensaje' value=\""+ex.getMensaje()+"\" />");
                        out.println("<input type='text' id='codigo_error' name='codigo_error' value='"+ex.getCodigo_error()+"' />");
                        //System.out.println(System.getProperty("java.home"));
                        throw new SqlAppsException(ex, VendedorController.class.getName() + "-- metodo: setVendedor", LOGGER);
                        
                        
                    }
                }
                break;
                case "carga_lista_grupo_vendedores":
                    request.getRequestDispatcher("../WEB-INF/view/admon/grupo_lista.jsp").include(request, response);
                    break;
                case "eliminar_vendedor":
                    try{
                      resultado =  vend.deleteVendedor(vb);
                      out.println("<input type=\"hidden\" id=\"resultado\" name=\"resultado\" value=\""+resultado+"\" />");
                    } catch (SqlAppsException ex) {
                        
                        out.println("<input type='hidden' id='resultado' name='resultado' value='"+resultado+"' />");
                        out.println("<input type='hidden' id='mensaje' name='mensaje' value=\""+ex.getMensaje()+"\" />");
                        out.println("<input type='hidden' id='codigo_error' name='codigo_error' value='"+ex.getCodigo_error()+"' />");
                        throw new SqlAppsException(ex, VendedorController.class.getName() + "-- metodo: deleteVendedor", LOGGER);
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
        try {
            processRequest(request, response);
        } catch (SqlAppsException ex) {
            Logger.getLogger(VendedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SqlAppsException ex) {
            Logger.getLogger(VendedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
