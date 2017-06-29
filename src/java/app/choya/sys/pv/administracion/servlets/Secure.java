/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.administracion.servlets;

import java.io.IOException;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import app.choya.sys.pv.administracion.Autentificacion;
import app.choya.sys.pv.administracion.ControlPermiso;
import app.choya.sys.pv.bitacora.BitacoraAcceso;
import app.choya.sys.pv.bitacora.BitacoraAccesoBean;
import app.choya.sys.pv.configuracion.UsuarioBean;
import app.choya.sys.pv.configuracion.Usuario;
import app.choya.sys.pv.configuracion.UsuarioRolBean;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author Informatica
 */
@WebServlet(name = "Secure", urlPatterns = {"/pv/Secure.do"})
public class Secure extends HttpServlet {

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
        System.out.println("entre al servlet");
        String usuario = "";
        String contrasena = "";
        int rlogin = 0;
        
        usuario = request.getParameter("user");
        contrasena = request.getParameter("password");
        
        UsuarioBean ub = new UsuarioBean();
        HttpSession session = request.getSession(false);
        
        
        String ip = request.getRemoteAddr();
        String host = request.getRemoteHost();
        
        if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
        InetAddress inetAddress; 
        String hostA;
            try{
            
            inetAddress = InetAddress.getLocalHost();
            String ipAddress = inetAddress.getHostAddress();
            
            
            hostA = inetAddress.getHostName();
            ip = ipAddress;
            host = hostA;
            }catch(UnknownHostException uex){
                System.out.println(uex.getMessage());
            }
            
            
        }
        try {
            rlogin = new Autentificacion().doAutentifica(usuario.toUpperCase(), contrasena.toUpperCase());
            
            //System.out.println("rlogin "+rlogin);
            switch(rlogin){
                case 1:
                case 2:
                    //login exitoso
                    ub = new Usuario().getUsuarioInfo(usuario);
                    session.setAttribute("usuario_id", ub.getId());
                    session.setAttribute("usuario", ub.getUsuario());
                    session.setAttribute("nombre", ub.getNombre());
                    session.setAttribute("id_rol", ub.getId_rol());
                    session.setAttribute("id_vendedor", ub.getId_vendedor());
                    session.setAttribute("rol", ub.getRol());
                    session.setAttribute("vendedor", ub.getVendedor());
                    session.setAttribute("invalida_usuario", true);
                    session.setAttribute("sucursal", ub.getSucursal());
                    session.setAttribute("ip", ip);
                    session.setAttribute("host", host);
                    session.setMaxInactiveInterval(36000);
                    
                    //leer roles aqui
                    
                    ControlPermiso control = new ControlPermiso();
                    ListIterator<UsuarioRolBean> lista_roles;
                    
                    lista_roles = control.getRolesByUsuario(ub.getId());
                    session.setAttribute("lista_roles", lista_roles);
                    
                    
                    
                    if(rlogin == 1){
                        BitacoraAccesoBean bb = new BitacoraAccesoBean();
                        bb.setUsuario_id(ub.getId());
                        bb.setIp(ip);
                        bb.setHost(host);
                        
                        new BitacoraAcceso().setBitacoraAcceso(bb);
                        //login exitoso
                        //request.getRequestDispatcher("/WEB-INF/view/admon/main.jsp").forward(request, response);
                        //request.setAttribute("funcion", "main");
                        
                        request.getRequestDispatcher("../pv/Hub.view?funcion=rol").forward(request, response);
                    }else{
                        //contraseña temporal correcta - actualizar contrasena
                        request.getRequestDispatcher("/WEB-INF/views/admin/update_nvacontrasena.jsp").forward(request, response);
                    }
                    break;
                case -1:
                    // usuario invalido
                    request.getRequestDispatcher("/WEB-INF/view/login.jsp?action=1").forward(request, response);
                    break;
                case -2:
                    // contraseña invalida
                    request.getRequestDispatcher("/WEB-INF/view/login.jsp?action=2").forward(request, response);
                    break;
                case -3:
                    // usuario bloqueado
                    request.getRequestDispatcher("/WEB-INF/view/login.jsp?action=3").forward(request, response);
                    break;
                case -4:
                    // contraseña temporal incorrecta
                    request.getRequestDispatcher("/WEB-INF/view/login.jsp?action=4").forward(request, response);
                    break;
                case -5:
                    // nuevo usuario - contraseña incorrecta
                    request.getRequestDispatcher("/WEB-INF/view/login.jsp?action=5").forward(request, response);
                    break; 
                default:
                    request.getRequestDispatcher("/WEB-INF/views/login.jsp?action=6").forward(request, response);
                    break;
            }
            
        } catch (Exception ex) {
            Logger.getLogger(Secure.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("/WEB-INF/view/login.jsp?action=fail").forward(request, response);
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
