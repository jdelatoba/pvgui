/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.configuracion.servlet;

import app.choya.sys.pv.configuracion.UsuarioBean;
import app.choya.sys.pv.configuracion.Usuario;
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
@WebServlet(name = "UsuarioController", urlPatterns = {"/admon/UsuarioController.pv"})
public class UsuarioController extends HttpServlet {

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
            String operacion = (request.getParameter("operacion") != null && request.getParameter("operacion").compareTo("") != 0) ? request.getParameter("operacion") : "";
            
            int id = (request.getParameter("id") != null && request.getParameter("id").compareTo("") != 0) ? Integer.parseInt(request.getParameter("id")) : 0;
            String nombre = (request.getParameter("nombre") != null && request.getParameter("nombre").compareTo("") != 0) ? request.getParameter("nombre") : "";
            String user = (request.getParameter("usuario") != null && request.getParameter("usuario").compareTo("") != 0) ? request.getParameter("usuario") : "";
            String password = (request.getParameter("password") != null && request.getParameter("password").compareTo("") != 0) ? request.getParameter("password") : "";
            
            String domicilio = (request.getParameter("domicilio") != null && request.getParameter("domicilio").compareTo("") != 0) ? request.getParameter("domicilio") : "";
            String ciudad = (request.getParameter("ciudad") != null && request.getParameter("ciudad").compareTo("") != 0) ? request.getParameter("ciudad") : "";
            String email = (request.getParameter("email") != null && request.getParameter("email").compareTo("") != 0) ? request.getParameter("email") : "";
            String telefono = (request.getParameter("telefono") != null && request.getParameter("telefono").compareTo("") != 0) ? request.getParameter("telefono") : "";
            String celular = (request.getParameter("celular") != null && request.getParameter("celular").compareTo("") != 0) ? request.getParameter("celular") : "";
            int id_rol = (request.getParameter("id_rol") != null && request.getParameter("id_rol").compareTo("") != 0) ? Integer.parseInt(request.getParameter("id_rol")) : 0;
            int id_vendedor = (request.getParameter("id_vendedor") != null && request.getParameter("id_vendedor").compareTo("") != 0) ? Integer.parseInt(request.getParameter("id_vendedor")) : 0;
            
            String comentario = (request.getParameter("comentario") != null && request.getParameter("comentario").compareTo("") != 0) ? request.getParameter("comentario") : "";
            
            UsuarioBean ub = new UsuarioBean();
            
            ub.setId(id);
            ub.setNombre(nombre);
            ub.setUsuario(user);
            ub.setPassword(password);
            ub.setDomicilio(domicilio);
            ub.setCiudad(ciudad);
            ub.setEmail(email);
            ub.setTelefono(telefono);
            ub.setCelular(celular);
            ub.setId_rol(id_rol);
            ub.setId_vendedor(id_vendedor);
            ub.setComentario(comentario);
            ub.setCreado_por((int) session.getAttribute("usuario_id"));
            ub.setModificado_por((int) session.getAttribute("usuario_id"));
            ub.setSerie((String) session.getAttribute("serie"));
            ub.setSucursal((int) session.getAttribute("sucursal"));
            
            Usuario usuario = new Usuario();
            
            int resultado = 0;
            System.out.println("operacion --- "+operacion);
            System.out.println("id --"+ub.getId());
            switch (operacion) {
                case "actualizar":
                case "guardar": {
                    //System.out.println("entre al case guardar");
                    try{
                        if(ub.getId() == 0){
                            System.out.println("entre al if de guardar");
                            resultado = usuario.setUsuario(ub);
                        }else{
                            resultado = usuario.updateUsuario(ub);
                        }
                        out.println("<input type=\"hidden\" id=\"resultado\" name=\"resultado\" value=\""+resultado+"\" />");
                       
                        
                    } catch (SqlAppsException ex) {
                        
                        out.println("<input type='hidden' id='resultado' name='resultado' value='"+resultado+"' />");
                        out.println("<input type='hidden' id='mensaje' name='mensaje' value=\""+ex.getMensaje()+"\" />");
                        out.println("<input type='hidden' id='codigo_error' name='codigo_error' value='"+ex.getCodigo_error()+"' />");
                        System.out.println(System.getProperty("java.home"));
                        Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, "What!!!", ex);
                        
                        
                    }
                }
                break;
                case "carga_lista_rol":
                    request.getRequestDispatcher("/WEB-INF/view/admon/rol_lista.jsp").include(request, response);
                    break;
                case "carga_lista_vendedores":
                    request.getRequestDispatcher("/WEB-INF/view/admon/usuario_vendedor_lista.jsp").include(request, response);
                    break;
                case "actualizar_password":
                    {
                        try {
                            resultado = usuario.updatePassword(ub);
                            out.println("<input type=\"hidden\" id=\"resultado\" name=\"resultado\" value=\""+resultado+"\" />");
                        } catch (SqlAppsException ex) {

                                out.println("<input type='hidden' id='resultado' name='resultado' value='"+resultado+"' />");
                                out.println("<input type='hidden' id='mensaje' name='mensaje' value=\""+ex.getMensaje()+"\" />");
                                out.println("<input type='hidden' id='codigo_error' name='codigo_error' value='"+ex.getCodigo_error()+"' />");
                                //System.out.println(System.getProperty("java.home"));
                                Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, "What!!!", ex);


                            }
                    }
                    break;
                case "eliminar_usuario":
                    if(ub.getId() != 0){
                        System.out.println("Eliminar Usuarios "+ub.getId());
                        try {
                            resultado = usuario.deleteUsuario(ub);
                            out.println("<input type=\"hidden\" id=\"resultado\" name=\"resultado\" value=\""+resultado+"\" />");
                        } catch (SqlAppsException ex) {
                            out.println("<input type='hidden' id='resultado' name='resultado' value='"+resultado+"' />");
                            out.println("<input type='hidden' id='mensaje' name='mensaje' value=\""+ex.getMensaje()+"\" />");
                            out.println("<input type='hidden' id='codigo_error' name='codigo_error' value='"+ex.getCodigo_error()+"' />");
                                //System.out.println(System.getProperty("java.home"));
                            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                            
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
