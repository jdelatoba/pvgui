/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.nube.servlets;

import app.choya.sys.pv.nube.Sucursal;
import app.choya.sys.pv.nube.SucursalBean;
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
@WebServlet(name = "SucursalController", urlPatterns = {"/nube/SucursalController"})
public class SucursalController extends HttpServlet {

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
        
        
            int id = (request.getParameter("id") != null && request.getParameter("id").compareTo("") != 0) ? Integer.parseInt(request.getParameter("id")) : 0;
            String descripcion = (request.getParameter("descripion") != null && request.getParameter("descripcion").compareTo("") != 0) ? request.getParameter("descripcion") : "";
            int tipo = (request.getParameter("tipo") != null && request.getParameter("tipo").compareTo("") != 0) ? Integer.parseInt(request.getParameter("tipo")) : 0;
            
            String usuario = (request.getParameter("usuario") != null && request.getParameter("usuario").compareTo("") != 0) ? request.getParameter("usuario") : "";
            String contrasena = (request.getParameter("contrasena") != null && request.getParameter("contrasena").compareTo("") != 0) ? request.getParameter("contrasena") : "";
            int sync_tiempo = (request.getParameter("sync_tiempo") != null && request.getParameter("sync_tiempo").compareTo("") != 0) ? Integer.parseInt(request.getParameter("sync_tiempo")) : 0;
            int sync_proveedores = (request.getParameter("sync_proveedores") != null && request.getParameter("sync_proveedores").compareTo("") != 0) ? Integer.parseInt(request.getParameter("sync_proveedores")) : 0;
            int sync_clientes = (request.getParameter("sync_clientes") != null && request.getParameter("sync_clientes").compareTo("") != 0) ? Integer.parseInt(request.getParameter("sync_clientes")) : 0;
            String estatus = (request.getParameter("estatus") != null && request.getParameter("estatus").compareTo("") != 0) ? request.getParameter("estatus") : "D";
            int usuario_id = (int) session.getAttribute("usuario_id");
            String serie = (String) session.getAttribute("serie");
            //int sucursal = (int) session.getAttribute("sucursal");
            String ip = (String) session.getAttribute("ip");
            String host = (String) session.getAttribute("host");
            String operacion = (request.getParameter("operacion") != null && request.getParameter("operacion").compareTo("") != 0) ? request.getParameter("operacion") : "";
            JSONObject resultados = new JSONObject();
            JSONObject data = new JSONObject();
            System.out.println("operacion "+operacion);
            switch (operacion) {
                case "guardar":
                case "actualizar":
                    SucursalBean sb = new SucursalBean();
                    sb.setId(id);
                    sb.setDescripcion(descripcion);
                    sb.setTipo(tipo);
                    sb.setUsuario(usuario);
                    sb.setContrasena(contrasena);
                    sb.setSync_tiempo(sync_tiempo);
                    sb.setSync_proveedores(sync_proveedores);
                    sb.setSync_clientes(sync_clientes);
                    sb.setEstatus(estatus);
                    sb.setCreado_por(usuario_id);
                    sb.setModificado_por(usuario_id);
                    sb.setIp(ip);
                    sb.setHost(host);
                    Sucursal sucursal = new Sucursal();
                    int resultado = 0;
                        if(sb.getId() == 0){
                            try {
                                resultados = sucursal.setSucursalM2(sb);
                                
                                data = (JSONObject) resultados.get("data");
                                
                                System.out.println("resultado              "+data.get("resultado"));
                                System.out.println("mensaje                "+data.get("mensaje"));
                                System.out.println("exception_codigo_error "+data.get("exception_codigo_error"));
                                System.out.println("exception_mensaje      "+data.get("exception_mensaje"));
                                
                                //actualizar el numero de sucursal del cliente
                                
                                
                                
                                
                                out.println("<input type=\"hidden\" id=\"resultado\" name=\"resultado\" value=\""+data.get("resultado")+"\" />");
                                out.println("<input type=\"hidden\" id=\"mensaje_ws\" name=\"mensaje_ws\" value=\""+data.get("mensaje")+"\" />");
                                out.println("<input type='hidden' id='mensaje' name='mensaje' value=\""+data.get("exception_codigo_error")+"\" />");
                                out.println("<input type='hidden' id='codigo_error' name='codigo_error' value='"+data.get("exception_mensaje")+"' />");
                                
                            } catch (SqlAppsException ex) {
                                out.println("<input type='hidden' id='resultado' name='resultado' value='"+data.get("resultado")+"' />");
                                out.println("<input type=\"hidden\" id=\"mensaje_ws\" name=\"mensaje_ws\" value=\""+data.get("mensaje")+"\" />");
                                out.println("<input type='hidden' id='mensaje' name='mensaje' value=\""+data.get("exception_codigo_error")+"\" />");
                                out.println("<input type='hidden' id='codigo_error' name='codigo_error' value='"+data.get("exception_mensaje")+"' />");
                                Logger.getLogger(SucursalController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }else{
                            //actualizar
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
