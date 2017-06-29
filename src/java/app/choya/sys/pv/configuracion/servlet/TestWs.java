/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.configuracion.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 *
 * @author Rembao
 */
@WebServlet(name = "TestWs", urlPatterns = {"/ws/wsTest"})
public class TestWs extends HttpServlet {

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
        //response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            
            
            String usuario = "adminmatriz";
            String contrasena = "adminpv";
            
            int resultado = 0;
            
            resultado = agregarSucursal("Matriz La Paz"
                    , 1
                    , 15
                    , 1
                    , 1
                    , 1
                    ,usuario
                    , contrasena, "192.168.1.1", "prueba");
            
            
            out.print("resultado de alta "+resultado);
            
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

    private static Integer agregarSucursal(java.lang.String descripcion, java.lang.Integer tipo, java.lang.Integer syncTiempo, java.lang.Integer syncProveedores, java.lang.Integer idUsuario, java.lang.Integer syncClientes, java.lang.String usuario, java.lang.String contrasena, java.lang.String ip, java.lang.String host) {
        nube.app.choya.sys.pv.ws.WsNubePVChoya_Service service = new nube.app.choya.sys.pv.ws.WsNubePVChoya_Service();
        nube.app.choya.sys.pv.ws.WsNubePVChoya port = service.getWsNubePVChoyaPort();
        return port.agregarSucursal(descripcion, tipo
                , syncTiempo
                , syncProveedores
                , idUsuario
                , syncClientes, usuario, contrasena, ip, host);
    }

    

    

}
