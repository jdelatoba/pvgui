/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.administracion.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import mx.gob.bcs.agenda.catalogo.Funcionario;
//import mx.gob.bcs.agenda.catalogo.servlets.FuncionarioController;

/**
 *
 * @author Rembao
 */
@WebServlet(name = "updateContrasenaNva", urlPatterns = {"/app/updateContrasenaNva"})
public class updateContrasenaNva extends HttpServlet {

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
        
        int funcionario_id = (Integer) session.getAttribute("funcionario_id");
        System.out.println("actualizando funcionio id "+funcionario_id);
        String contrasena = request.getParameter("contrasena") != null && request.getParameter("contrasena").compareTo("") != 0 ? request.getParameter("contrasena") : "";
        String re_contrasena = request.getParameter("re_contrasena") != null && request.getParameter("re_contrasena").compareTo("") != 0 ? request.getParameter("re_contrasena") : "";
        int resultado  = 0;
        
        try (PrintWriter out = response.getWriter()) {
            if (session.getAttribute("funcionario_id") == null) {

                request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);

            } else {
                if(contrasena.compareTo(re_contrasena) == 0){
                    try {
                       //resultado =  new Funcionario().updateContrasena(funcionario_id, contrasena, contrasena);
                       resultado = 0;
                       throw new SQLException();
                       //out.print("<input type='hidden' id='resultado' name='resultado' value='"+resultado+"'/>");
                    } catch (SQLException ex) {
                       //Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
                       out.print("<input type=\"hidden\" id=\"resultado\" name=\"resultado\" value=\""+resultado+"\" />" );
                       out.print("<input type=\"hidden\" id=\"mensaje_error\" name=\"mensaje_error\" value=\""+ex.getMessage()+"\" />" );
                       out.print("<input type=\"hidden\" id=\"sql_error\" name=\"sql_error\" value=\""+ex.getErrorCode()+"\" />" );
                    }

                }
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
