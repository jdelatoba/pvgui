/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.configuracion.servlet;

import app.choya.sys.pv.configuracion.Impuesto;
import app.choya.sys.pv.configuracion.ImpuestoBean;
import app.choya.sys.pv.configuracion.ImpuestoI;
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
@WebServlet(name = "ImpuestoController", urlPatterns = {"/admon/ImpuestoController"})
public class ImpuestoController extends HttpServlet {
    
    static final Logger LOGGER = Logger.getLogger(ImpuestoController.class.getName());

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
            
            String impuesto_desc = (request.getParameter("impuesto") != null && request.getParameter("impuesto").compareTo("") != 0) ? request.getParameter("impuesto") : "0"; 
            impuesto_desc = impuesto_desc.replace(",", "");
            double impuesto = Double.parseDouble(impuesto_desc);
            
            int activo = (request.getParameter("activo") != null && request.getParameter("activo").compareTo("") != 0) ? Integer.parseInt(request.getParameter("activo")) : 0;
            int impreso = (request.getParameter("impreso") != null && request.getParameter("impreso").compareTo("") != 0) ? Integer.parseInt(request.getParameter("impreso")) : 0;
            
            int t_impuesto = (request.getParameter("t_impuesto") != null && request.getParameter("t_impuesto").compareTo("") != 0) ? Integer.parseInt(request.getParameter("t_impuesto")) : 0;
            
            int trasladado = 0;
            int retenido = 0;
            
            if(t_impuesto == 1){
                trasladado = 1;
                retenido = 0;
            }else{
                trasladado = 0;
                retenido = 1;
            }
            
            int orden = (request.getParameter("orden") != null && request.getParameter("orden").compareTo("") != 0) ? Integer.parseInt(request.getParameter("orden")) : 0;
            int impuesto_id = (request.getParameter("impuesto_id") != null && request.getParameter("impuesto_id").compareTo("") != 0) ? Integer.parseInt(request.getParameter("impuesto_id")) : 0;
            
            ImpuestoBean ib = new ImpuestoBean();
            
            ib.setId(id);
            ib.setDescripcion(descripcion);
            ib.setImpuesto(impuesto);
            ib.setActivo(activo);
            ib.setImpreso(impreso);
            ib.setTrasladado(trasladado);
            ib.setRetenido(retenido);
            ib.setOrden(orden);
            
            ImpuestoI imp = new Impuesto();
            
            int resultado = 0;
            System.out.println("operacion "+operacion); 
            switch (operacion) {
                case "actualizar":
                case "guardar": {
                    try{
                        if(ib.getId() == 0){
                            resultado = imp.setImpuesto(ib);
                            
                            out.println("<input type=\"hidden\" id=\"resultado\" name=\"resultado\" value=\""+resultado+"\" />");
                        }else{
                            resultado = imp.updateImpuesto(ib);
                            out.println("<input type=\"hidden\" id=\"resultado\" name=\"resultado\" value=\""+resultado+"\" />");
                        }    
                    } catch (SqlAppsException ex) {
                        
                        out.println("<input type='text' id='resultado' name='resultado' value='"+resultado+"' />");
                        out.println("<input type='text' id='mensaje' name='mensaje' value=\""+ex.getMensaje()+"\" />");
                        out.println("<input type='text' id='codigo_error' name='codigo_error' value='"+ex.getCodigo_error()+"' />");
                        //System.out.println(System.getProperty("java.home"));
                        throw new SqlAppsException(ex, ImpuestoController.class.getName() + "-- metodo: setImpuesto", LOGGER);
                        
                        
                    }
                }
                break;
                case "eliminar_impuesto":
                    
                    try{
                        resultado = imp.deleteImpuesto(id);
                        out.println("<input type=\"hidden\" id=\"resultado\" name=\"resultado\" value=\""+resultado+"\" />");
                    } catch (SqlAppsException ex) {
                        
                        out.println("<input type='text' id='resultado' name='resultado' value='"+resultado+"' />");
                        out.println("<input type='text' id='mensaje' name='mensaje' value=\""+ex.getMensaje()+"\" />");
                        out.println("<input type='text' id='codigo_error' name='codigo_error' value='"+ex.getCodigo_error()+"' />");
                        throw new SqlAppsException(ex, ImpuestoController.class.getName() + "-- metodo: deleteImpuesto", LOGGER);
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
            Logger.getLogger(ImpuestoController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ImpuestoController.class.getName()).log(Level.SEVERE, null, ex);
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
