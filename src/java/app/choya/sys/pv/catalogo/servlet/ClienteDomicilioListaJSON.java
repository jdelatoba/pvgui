/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.catalogo.servlet;

import app.choya.sys.pv.catalogo.Clientes;
import app.choya.sys.pv.catalogo.Proveedor;
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
import org.json.simple.JSONObject;

/**
 *
 * @author Rembao
 */
@WebServlet(name = "ClienteDomicilioListaJSON", urlPatterns = {"/pv/catalogos/ClienteDomicilioListaJSON"})
public class ClienteDomicilioListaJSON extends HttpServlet {

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
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            
            Clientes clientes = new Clientes();
            HttpSession session = request.getSession(false);
            
            String search = request.getParameter("search");
            int draw = (int) session.getAttribute("draw");
            //System.out.println("valor de draw "+draw);
            draw++;
            session.setAttribute("draw", draw);
            int id_cliente = 0;
            id_cliente = (request.getParameter("id_cliente") != null && request.getParameter("id_cliente").compareTo("") != 0) ? Integer.parseInt(request.getParameter("id_cliente")) : 0;
            
            
            System.out.println("Entre a ClienteDomicilioListaJSON id_cliente "+id_cliente);
            try {
                JSONObject json = clientes.getClientesDomicilioListaJSON(id_cliente);
                out.print(json.toJSONString());
                
               
            } catch (SqlAppsException ex) {
                Logger.getLogger(ProveedorListaJSON.class.getName()).log(Level.SEVERE, null, ex);
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
