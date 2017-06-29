/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.catalogo.servlet;

import app.choya.sys.pv.catalogo.Articulo;
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
@WebServlet(name = "ArticuloListaJSON", urlPatterns = {"/pv/ArticuloListaJSON"})
public class ArticuloListaJSON extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            
            Articulo articulo = new Articulo();
            HttpSession session = request.getSession(false);
            
            int categorias = (request.getParameter("categorias") != null && request.getParameter("categorias").compareTo("") != 0) ? Integer.parseInt(request.getParameter("categorias")) : 0;
            int departamentos = (request.getParameter("departamentos") != null && request.getParameter("departamentos").compareTo("") != 0) ? Integer.parseInt(request.getParameter("departamentos")) : 0;
            int con_existencia = (request.getParameter("con_existencia") != null && request.getParameter("con_existencia").compareTo("") != 0) ? Integer.parseInt(request.getParameter("con_existencia")) : 0;
            int sin_existencia = (request.getParameter("sin_existencia") != null && request.getParameter("sin_existencia").compareTo("") != 0) ? Integer.parseInt(request.getParameter("sin_existencia")) : 0;
            String buscar = (request.getParameter("buscar") != null && request.getParameter("buscar").compareTo("") != 0) ? request.getParameter("buscar") : "";
            int departamento_id = (request.getParameter("departamento_id") != null && request.getParameter("departamento_id").compareTo("") != 0) ? Integer.parseInt(request.getParameter("departamento_id")) : 0;
            int categoria_id = (request.getParameter("categoria_id") != null && request.getParameter("categoria_id").compareTo("") != 0) ? Integer.parseInt(request.getParameter("categoria_id")) : 0;
            String busca_por = (request.getParameter("busca_por") != null && request.getParameter("busca_por").compareTo("") != 0) ? request.getParameter("busca_por") : "";
            String search = request.getParameter("search");
            
            System.out.println("buscando "+search);
            //int draw = (int) session.getAttribute("draw");
            int draw = 1;
            System.out.println("valor de draw "+draw);
            draw++;
            session.setAttribute("draw", draw);
            //System.out.println("categorias "+categorias);
            //System.out.println("departamentos "+departamentos);
            //System.out.println("con_existencia "+con_existencia);
            //System.out.println("sin_existencia "+sin_existencia);
            //System.out.println("buscar "+buscar);
            //System.out.println("departamentos_id "+departamento_id);
            //System.out.println("categoria_id "+categoria_id);
            //System.out.println("buscar_por "+busca_por);
            try {
                JSONObject json = articulo.getListaArticulo(buscar, busca_por, categorias, departamentos, categoria_id, departamento_id, con_existencia, sin_existencia);
                //JSONObject json = articulo.getLista();
                System.out.println("mostrando json "+json.toJSONString());
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
