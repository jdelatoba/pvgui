/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.configuracion.servlet;

import app.choya.sys.pv.configuracion.Menu;
import app.choya.sys.pv.configuracion.MenuBean;
import app.choya.sys.pv.configuracion.MenuI;
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
@WebServlet(name = "MenuController", urlPatterns = {"/admon/MenuController.pv"})
public class MenuController extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(MenuController.class.getName());
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
            String descripcion = (request.getParameter("descripcion") != null && request.getParameter("descripcion").compareTo("") != 0) ? request.getParameter("descripcion") : "";
            String comentario = (request.getParameter("comentario") != null && request.getParameter("comentario").compareTo("") != 0) ? request.getParameter("comentario") : "";
            System.out.println("id "+id);
            MenuI menu = new Menu();
            MenuBean mb = new MenuBean();
            mb.setId(id);
            mb.setDescripcion(descripcion);
            mb.setComentario(comentario);
            mb.setCreado_por(usuario_id);
            mb.setModificado_por(usuario_id);
            mb.setSerie(serie);
            
            int resultado = 0;
            System.out.println("operacion "+operacion);
            String url = "";
            switch (operacion) {
                case "actualizar":
                case "guardar": {
                    try{
                        if(mb.getId() == 0){
                            resultado = menu.setMenu(mb);
                        }else{
                            resultado = menu.updateMenu(mb);
                        }
                        out.println("<input type=\"hidden\" id=\"resultado\" name=\"resultado\" value=\""+resultado+"\" />");
                    } catch (SqlAppsException ex) {
                        
                        out.println("<input type='hidden' id='resultado' name='resultado' value='"+resultado+"' />");
                        out.println("<input type='hidden' id='mensaje' name='mensaje' value=\""+ex.getMensaje()+"\" />");
                        out.println("<input type='hidden' id='codigo_error' name='codigo_error' value='"+ex.getCodigo_error()+"' />");
                        throw new SqlAppsException(ex, MenuController.class.getName(), LOGGER);
                        
                        
                    }
                }
                break;
                case "eliminar_menu":
                    try{
                      resultado =  menu.deleteMenu(mb);
                      out.println("<input type=\"hidden\" id=\"resultado\" name=\"resultado\" value=\""+resultado+"\" />");
                    } catch (SqlAppsException ex) {
                        
                        out.println("<input type='hidden' id='resultado' name='resultado' value='"+resultado+"' />");
                        out.println("<input type='hidden' id='mensaje' name='mensaje' value=\""+ex.getMensaje()+"\" />");
                        out.println("<input type='hidden' id='codigo_error' name='codigo_error' value='"+ex.getCodigo_error()+"' />");
                        throw new SqlAppsException(ex, MenuController.class.getName(), LOGGER);
                        
                        
                    }
                    break;
                case "editar_menu":
                    request.setAttribute("url","../admon/menu.jsp");
                    request.setAttribute("id", id);
                    request.getRequestDispatcher("../WEB-INF/view/main/hub.jsp").forward(request, response);
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
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
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
