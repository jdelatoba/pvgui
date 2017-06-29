/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.administracion.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Informatica
 */
@WebServlet(name = "AdministracionCatalogo", urlPatterns = {"/administracion/catalogos"})
public class AdministracionCatalogo extends HttpServlet {

    String tipo_catalogo = "";

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
        tipo_catalogo = request.getParameter("tipo_catalogo");

        try (PrintWriter out = response.getWriter()) {

            
            System.out.println("Tipo catalogo "+tipo_catalogo);
            String dependencia_id = "";
            dependencia_id = request.getParameter("dependencia_id");

            if (dependencia_id == null || dependencia_id.compareTo("") == 0) {
                dependencia_id = "-1";
            }

            String direccion_id = "";
            direccion_id = request.getParameter("direccion_id");
            if (direccion_id == null || direccion_id.compareTo("") == 0) {
                direccion_id = "-1";
            }
            
            String departamento_id = "";
            departamento_id = request.getParameter("departamento_id");
            if (departamento_id == null || departamento_id.compareTo("") == 0) {
                departamento_id = "-1";
            }

            System.out.println("entrando a catalogo "+tipo_catalogo);
            switch (tipo_catalogo) {

                case "direccion":

                    request.getRequestDispatcher("/WEB-INF/views/jspf/direccion.jsp?dependencia_id=" + dependencia_id + "&direccion_id=" + direccion_id).include(request, response);

                    break;
                case "departamento":

                    request.getRequestDispatcher("/WEB-INF/views/jspf/departamento.jsp?dependencia_id=" + dependencia_id + "&direccion_id=" + direccion_id + "&departamento_id=" + departamento_id).include(request, response);
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
