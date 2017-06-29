/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "Concentrador", urlPatterns = {"/pv/Hub.view"})
public class Concentrador extends HttpServlet {

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
        HttpSession session = request.getSession(false);
       
        try (PrintWriter out = response.getWriter()) {
            String funcion = (request.getParameter("funcion") != null && request.getParameter("funcion").compareTo("") != 0)? request.getParameter("funcion") : "";
            funcion = (request.getParameter("funcion_forma") != null && request.getParameter("funcion_forma").compareTo("") != 0)? request.getParameter("funcion_forma") : funcion;

            String url = "../login.jsp";

            int id = (request.getParameter("id") != null && request.getParameter("id").compareTo("") != 0) ? Integer.parseInt(request.getParameter("id")) : 0;
            System.out.println("funcion -----> "+funcion);
            
            switch(funcion){
                case "rol":
                    request.getRequestDispatcher("/WEB-INF/view/main/rol.jsp").forward(request, response);
                    break;
                case "main": // pagina principal de la aplicacion
                    request.getRequestDispatcher("/WEB-INF/view/main/index.jsp").forward(request, response);
                    break;
                case "clientes":
                    request.getRequestDispatcher("/WEB-INF/view/catalogos/articulo_lista.jsp").include(request,response);
                    break;
                case "selecciona_rol":
                    
                    if(request.getParameter("rol_id") != null && request.getParameter("rol_id").compareTo("") != 0){
                        session.setAttribute("rol_id", request.getParameter("rol_id"));
                    }
                    request.getRequestDispatcher("/WEB-INF/view/main/index.jsp").forward(request, response);
                    
                    break;
                case "cargar_funcion":
                        String url_funcion = (request.getParameter("url") != null && request.getParameter("url").compareTo("") != 0) ? request.getParameter("url") : "../login.jsp";
                        System.out.println("url funcion "+url_funcion);
                        
                        request.getRequestDispatcher(url_funcion).include(request, response);
                    
                    break;
                //Catálogo de artículos    
                
                case "articulos":
                    if(session.getAttribute("articulos_temp") != null){
                        session.setAttribute("articulos_temp", null);
                    }
                    
                    if(session.getAttribute("articulos") != null){
                        session.setAttribute("articulos", null);
                    }
                    request.getRequestDispatcher("/WEB-INF/view/catalogos/articulo.jsp").include(request, response);
                    break;
                
                case "editar_articulo":
                        int id_articulo = (request.getParameter("id_articulo") != null && request.getParameter("id_articulo").compareTo("") != 0) ? Integer.parseInt(request.getParameter("id_articulo")) : 0;
                        request.setAttribute("id_articulo", id_articulo);
                        request.getRequestDispatcher("/WEB-INF/view/catalogos/articulo.jsp").include(request, response);
                    break;
                
                case "agregar_articulo":
                        request.getRequestDispatcher("/WEB-INF/view/catalogos/articulo.jsp").include(request, response);
                    break;
                    
                //catalogo de proveedores    
                case "proveedores":
                    request.getRequestDispatcher("/WEB-INF/view/catalogos/proveedor_lista.jsp").include(request, response);
                    break;
                
                case "agregar_proveedor":
                    request.getRequestDispatcher("/WEB-INF/view/catalogos/proveedor.jsp").include(request, response);
                    break;
                case "editar_proveedor":
                        int id_proveedor = (request.getParameter("id_proveedor") != null && request.getParameter("id_proveedor").compareTo("") != 0) ? Integer.parseInt(request.getParameter("id_proveedor")) : 0;
                        request.setAttribute("id_proveedor",id_proveedor);
                        request.getRequestDispatcher("/WEB-INF/view/catalogos/proveedor.jsp").include(request, response);
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
