/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.catalogo.servlet;

import app.choya.sys.pv.catalogo.ArticuloCompatible;
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
@WebServlet(name = "ArticuloListaCompatibleJSON", urlPatterns = {"/pv/ArticuloListaCompatibleJSON"})
public class ArticuloListaCompatibleJSON extends HttpServlet {

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
        HttpSession session = request.getSession(false);
        
        try (PrintWriter out = response.getWriter()) {
            
            System.out.println("buscando articulo compatibles ya seleccionados");
            int id = (request.getParameter("id") != null && request.getParameter("id").compareTo("") != 0) ? Integer.parseInt(request.getParameter("id")) : 0;
            
            System.out.println("buscando articulos compatible para el id_articulo "+id);
            ArticuloCompatible compatible = new ArticuloCompatible();
            if(id != 0){
                JSONObject articulo_compatible = new JSONObject();
                try {
                    articulo_compatible = compatible.getArticuloCompatibleByIdArticuloJSON(id);
                } catch (SqlAppsException ex) {
                    Logger.getLogger(ArticuloListaCompatibleJSON.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                out.print(articulo_compatible.toJSONString());
                System.out.println(articulo_compatible.toJSONString());
            }else{
                
                if(session.getAttribute("articulos") != null){
                
                    JSONObject json = (JSONObject) session.getAttribute("articulos");
                    System.out.println(json.toJSONString());
                    out.print(json.toJSONString());
                }else{
                    //respuesta json vacia
                    out.print("{\"data\":[]}");
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
