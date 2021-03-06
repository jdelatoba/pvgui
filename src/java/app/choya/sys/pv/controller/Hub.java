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

/**
 *
 * @author Rembao
 */
@WebServlet(name = "Hub", urlPatterns = {"/Hub","/pv/Hub"})
public class Hub extends HttpServlet {

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
        
        String funcion = (request.getParameter("funcion") != null && request.getParameter("funcion").compareTo("") != 0)? request.getParameter("funcion") : "";
        funcion = (request.getParameter("funcion_forma") != null && request.getParameter("funcion_forma").compareTo("") != 0)? request.getParameter("funcion_forma") : funcion;
        
        String url = "../login.jsp";
        
        int id = (request.getParameter("id") != null && request.getParameter("id").compareTo("") != 0) ? Integer.parseInt(request.getParameter("id")) : 0;
        System.out.println("funcion "+funcion);
        System.out.println("id "+id);
        try (PrintWriter out = response.getWriter()) {
            
            switch(funcion){
                case "empresas": //pantalla para alta de empresas
                    request.setAttribute("url","../admon/empresa.jsp");
                    url = "/WEB-INF/view/main/hub.jsp";
                    break;
                case "usuarios": //pantalla para alta de usuarios
                    request.setAttribute("url","../admon/usuario.jsp");
                    url = "/WEB-INF/view/main/hub.jsp";
                    break;
                case "usuarios_lista": //pantalla para alta de usuarios
                    //System.out.println("entre a usuarios_lista");
                    request.setAttribute("url","../admon/usuario_lista.jsp");
                    url = "/WEB-INF/view/main/hub.jsp";
                    break;
                case "editar_usuario":
                    request.setAttribute("usuario_id", id);
                    request.setAttribute("url","../admon/usuario_editar.jsp");
                    url = "/WEB-INF/view/main/hub.jsp";
                    break;
                case "rol_lista": //modal para la seleccion de rol de usuario
                    request.setAttribute("url","../admon/rol_lista.jsp");
                    url = "./WEB-INF/view/main/hub.jsp";
                    break;
                case "funciones": //pantalla para alta de funciones
                    request.setAttribute("url","../admon/funcion.jsp");
                    url = "./WEB-INF/view/main/hub.jsp";
                    break;
                case "funciones_lista": //pantalla lista de funciones
                    request.setAttribute("url","../admon/funcion_lista.jsp");
                    url = "./WEB-INF/view/main/hub.jsp";
                    break;
                case "menus": //pantalla para alta de menus
                    request.setAttribute("url","../admon/menu.jsp");
                    request.setAttribute("toolbar","../jspf/btn_form_basico.jsp");
                    url = "./WEB-INF/view/main/hub.jsp";
                    break;    
                case "menus_lista": //pantalla para mostrar la lista de menus
                    request.setAttribute("url","../admon/menu_lista.jsp");
                    request.setAttribute("toolbar","../jspf/btn_form_lista_basico.jsp");
                    url = "./WEB-INF/view/main/hub.jsp";
                    break;
                case "editar_menu":
                    request.setAttribute("url","../admon/menu.jsp");
                    request.setAttribute("menu_id", id);
                    url = "./WEB-INF/view/main/hub.jsp";
                    break;
                case "vendedor_grupo_lista":  //modal para seleccion de grupo de vendedor 
                    request.setAttribute("url","../admon/grupo_lista.jsp");
                    url = "./WEB-INF/view/main/hub.jsp";
                    break;    
                case "vendedores": //pantalla para alta de vendedores
                    request.setAttribute("url","../admon/vendedor.jsp");
                    url = "/WEB-INF/view/main/hub.jsp";
                    break;
                case "vendedores_lista": //pantalla para probar la lista de vendedores
                    request.setAttribute("url","../admon/vendedor_lista.jsp");
                    url = "/WEB-INF/view/main/hub.jsp";
                    
                    break;
                case "editar_vendedor":
                    request.setAttribute("url","../admon/vendedor.jsp");
                    request.setAttribute("vendedor_id", id);
                    url = "/WEB-INF/view/main/hub.jsp";
                    break;
                case "impuestos":
                    request.setAttribute("url","../admon/impuesto.jsp");
                    url = "/WEB-INF/view/main/hub.jsp";
                    break;
                case "impuestos_lista":
                    request.setAttribute("url","../admon/impuesto_lista.jsp");
                    url = "/WEB-INF/view/main/hub.jsp";
                    break;
                case "editar_impuesto":
                    request.setAttribute("url","../admon/impuesto.jsp");
                    request.setAttribute("impuesto_id", id);
                    url = "/WEB-INF/view/main/hub.jsp";
                    break;
                case "monedas": //pantalla para alta de vendedores
                    request.setAttribute("url","../admon/moneda.jsp");
                    url = "/WEB-INF/view/main/hub.jsp";
                    break;
                case "monedas_lista":
                    
                    request.setAttribute("url","../admon/moneda_lista.jsp");
                    url = "/WEB-INF/view/main/hub.jsp";
                    break;
                case "editar_moneda":
                    
                    request.setAttribute("url","../admon/moneda.jsp");
                    request.setAttribute("moneda_id", id);
                    url = "/WEB-INF/view/main/hub.jsp";
                    break;    
                
                
                case "unidades": //pantalla para alta de vendedores
                    request.setAttribute("url","../admon/unidad.jsp");
                    url = "/WEB-INF/view/main/hub.jsp";
                    break;
                case "unidades_lista":
                    request.setAttribute("url","../admon/unidad_lista.jsp");
                    url = "/WEB-INF/view/main/hub.jsp";
                    break;
                case "editar_unidad":
                    
                    request.setAttribute("url","../admon/unidad.jsp");
                    request.setAttribute("unidad_id", id);
                    url = "/WEB-INF/view/main/hub.jsp";
                    break;    
                case "proveedores": //pantalla para alta de proveedores
                    request.setAttribute("url","../catalogos/proveedor.jsp");
                    url = "/WEB-INF/view/main/hub.jsp";
                    break;
                case "proveedores_lista":
                    request.setAttribute("url","../catalogos/proveedor_lista.jsp");
                    url = "/WEB-INF/view/main/hub.jsp";
                    break;
                case "editar_proveedor":
                    request.setAttribute("url","../catalogos/proveedor.jsp");
                    request.setAttribute("proveedor_id", id);
                    url = "/WEB-INF/view/main/hub.jsp";
                    break;
                case "articulos": //pantalla para alta de proveedores
                    request.setAttribute("url","../catalogos/articulo.jsp");
                    url = "/WEB-INF/view/main/hub.jsp";
                    break;    
                case "clientes": //pantalla para alta de proveedores
                    request.setAttribute("url","../catalogos/cliente.jsp");
                    url = "/WEB-INF/view/main/hub.jsp";
                    break;
                case "clientes_lista":
                    request.setAttribute("url","../catalogos/cliente_lista.jsp");
                    url = "/WEB-INF/view/main/hub.jsp";
                    break;
                case "editar_cliente":
                    request.setAttribute("url","../catalogos/cliente.jsp");
                    request.setAttribute("cliente_id", id);
                    url = "/WEB-INF/view/main/hub.jsp";
                    break;    
                    
                case "tabla_test":
                    request.setAttribute("url","../catalogos/tabla_test.jsp");
                    url = "./WEB-INF/view/main/hub.jsp";
                    break;
                case "logins":
                    //request.setAttribute("url","../login.jsp");
                    url = "./WEB-INF/view/login.jsp";
                    break;
                case "main":
                    //System.out.println("entre a main");
                    request.setAttribute("url","../admon/main.jsp");
                    url = "./WEB-INF/view/main/hub.jsp";
                    break;    
                case "grupos": //pantalla para alta grupos
                    request.setAttribute("url","../admon/grupo.jsp");
                    url = "/WEB-INF/view/main/hub.jsp";
                    break;
                case "familias": //pantalla para alta familias
                    request.setAttribute("url","../admon/grupo.jsp");
                    url = "/WEB-INF/view/main/hub.jsp";
                    break;
                default:
                    //url = "./WEB-INF/view/login.jsp";
                    url = "/login.jsp";
                    break;
                
            }
            /* TODO output your page here. You may use following sample code. */
            
            request.getRequestDispatcher(url).forward(request, response);
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
