/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.catalogo.servlet;

import app.choya.sys.pv.catalogo.Cliente;
import app.choya.sys.pv.catalogo.ClienteBean;
import app.choya.sys.pv.catalogo.ClienteDomicilioBean;
import app.choya.sys.pv.catalogo.ClienteResultadoBean;
import app.choya.sys.pv.catalogo.Clientes;
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
@WebServlet(name = "ClienteController", urlPatterns = {"/pv/Cliente.pv"})
public class ClienteController extends HttpServlet {

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
            System.out.println("entre a ClienteController");
            
            HttpSession session = request.getSession(false);
            int usuario_id = (int) session.getAttribute("usuario_id");
            String serie = (String) session.getAttribute("serie");
            
            
            int id_cliente = (request.getParameter("id") != null && request.getParameter("id").compareTo("") != 0) ? Integer.parseInt(request.getParameter("id")) : 0;
            String representante = (request.getParameter("representante") != null && request.getParameter("representante").compareTo("") != 0) ? request.getParameter("representante") : "";
            String nombre = (request.getParameter("nombre") != null && request.getParameter("nombre").compareTo("") != 0) ? request.getParameter("nombre") : "";
            String rfc = (request.getParameter("rfc") != null && request.getParameter("rfc").compareTo("") != 0) ? request.getParameter("rfc") : "";
            String curp = (request.getParameter("curp") != null && request.getParameter("curp").compareTo("") != 0) ? request.getParameter("curp") : "";
            String clave = (request.getParameter("clave") != null && request.getParameter("clave").compareTo("") != 0) ? request.getParameter("clave") : "";
            int id_domicilio = (request.getParameter("id_domicilio") != null && request.getParameter("id_domicilio").compareTo("") != 0) ? Integer.parseInt(request.getParameter("id_domicilio")) : 0;
            String domicilio = (request.getParameter("domicilio") != null && request.getParameter("domicilio").compareTo("") != 0) ? request.getParameter("domicilio") : "";
            String no_ext = (request.getParameter("no_ext") != null && request.getParameter("no_ext").compareTo("") != 0) ? request.getParameter("no_ext") : "";
            String no_int = (request.getParameter("no_int") != null && request.getParameter("no_int").compareTo("") != 0) ? request.getParameter("no_int") : "";
            String colonia = (request.getParameter("colonia") != null && request.getParameter("colonia").compareTo("") != 0) ? request.getParameter("colonia") : "";
            String codigo_postal = (request.getParameter("codigo_postal") != null && request.getParameter("codigo_postal").compareTo("") != 0) ? request.getParameter("codigo_postal") : "";
            String localidad = (request.getParameter("localidad") != null && request.getParameter("localidad").compareTo("") != 0) ? request.getParameter("localidad") : "";
            String municipio = (request.getParameter("municipio") != null && request.getParameter("municipio").compareTo("") != 0) ? request.getParameter("municipio") : "";

            String entidad = (request.getParameter("entidad") != null && request.getParameter("entidad").compareTo("") != 0) ? request.getParameter("entidad") : "";
            String pais = (request.getParameter("pais") != null && request.getParameter("pais").compareTo("") != 0) ? request.getParameter("pais") : "";
            String telefono = (request.getParameter("telefono") != null && request.getParameter("telefono").compareTo("") != 0) ? request.getParameter("telefono") : "";
            String celular = (request.getParameter("celular") != null && request.getParameter("celular").compareTo("") != 0) ? request.getParameter("celular") : "";
            String email = (request.getParameter("email") != null && request.getParameter("email").compareTo("") != 0) ? request.getParameter("email") : "";
            String comentario = (request.getParameter("comentario") != null && request.getParameter("comentario").compareTo("") != 0) ? request.getParameter("comentario") : "";
            
            String lc = (request.getParameter("limite_credito") != null && request.getParameter("limite_credito").compareTo("") != 0) ? request.getParameter("limite_credito") : "0.0"; 
            lc = lc.replace(",", "");
            double limite_credito = Double.parseDouble(lc);
            
            int dias_credito = (request.getParameter("dias_credito") != null && request.getParameter("dias_credito").compareTo("") != 0) ? Integer.parseInt(request.getParameter("dias_credito")) : 0;
            int aplica_retencion = (request.getParameter("aplica_retencion") != null && request.getParameter("aplica_retencion").compareTo("") != 0) ? Integer.parseInt(request.getParameter("aplica_retencion")) : 0;
            int no_precio = (request.getParameter("no_precio") != null && request.getParameter("no_precio").compareTo("") != 0) ? Integer.parseInt(request.getParameter("no_precio")) : 0;

            String operacion = (request.getParameter("operacion") != null && request.getParameter("operacion").compareTo("") != 0) ? request.getParameter("operacion") : "";
        
            ClienteBean cb = new ClienteBean();
            
            cb.setId(id_cliente);
            cb.setClave(clave);
            cb.setRepresentante(representante);
            cb.setNombre(nombre);
            cb.setRfc(rfc);
            cb.setCurp(curp);
            cb.setCreado_por(usuario_id);
            cb.setModificado_por(usuario_id);
            cb.setSerie(serie);
            
            ClienteDomicilioBean db = new ClienteDomicilioBean();
            System.out.println("operacion "+operacion);
            System.out.println("domicilio "+domicilio);
            
            db.setId_cliente(id_cliente);
            db.setId(id_domicilio);
            db.setDomicilio(domicilio);
            db.setNum_ext(no_ext);
            db.setNum_int(no_int);
            db.setColonia(colonia);
            db.setCodigo_postal(codigo_postal);
            db.setLocalidad(localidad);
            db.setMunicipio(municipio);
            db.setEntidad(entidad);
            db.setPais(pais);
            db.setTelefono(telefono);
            db.setCelular(celular);
            db.setEmail(email);
            db.setComentario(comentario);
            db.setLimite_credito(limite_credito);
            db.setDias_credito(dias_credito);
            db.setAplica_retencion(aplica_retencion);
            db.setNo_precio(no_precio);
            db.setCreado_por(usuario_id);
            db.setModificado_por(no_precio);
            
            System.out.println("Entre a ClienteController --");
            System.out.println("operacion "+operacion);
            Clientes clientes = new Clientes();
            ClienteResultadoBean resultado = new ClienteResultadoBean();
            
            int res = 0;
            
            switch (operacion) {
                case "actualizar":
                case "guardar": {
                    try {

                        if(id_cliente == 0){
                            resultado = clientes.setClienteDomicilio(cb, db);
                        }

                        if(id_cliente != 0 && id_domicilio != 0){
                            resultado.setResultado(clientes.updateClientes(cb, db));
                            resultado.setId_cliente(cb.getId());
                            resultado.setId_domicilio(id_domicilio);
                        }

                        out.println("<input type=\"hidden\" id=\"id_rcliente\" name=\"id_rcliente\" value=\""+resultado.getId_cliente()+"\" />");
                        out.println("<input type=\"hidden\" id=\"id_rdomicilio\" name=\"id_rdomicilio\" value=\""+resultado.getId_domicilio()+"\" />");
                        out.println("<input type=\"hidden\" id=\"resultado\" name=\"resultado\" value=\""+resultado.getResultado()+"\" />");

                    } catch (SqlAppsException ex) {
                        out.println("<input type=\"hidden\" id=\"id_rcliente\" name=\"id_rcliente\" value=\""+resultado.getId_cliente()+"\" />");
                        out.println("<input type=\"hidden\" id=\"id_rdomicilio\" name=\"id_rdomicilio\" value=\""+resultado.getId_domicilio()+"\" />");
                        out.println("<input type=\"hidden\" id=\"resultado\" name=\"resultado\" value=\""+resultado.getResultado()+"\" />");
                        out.println("<input type='text' id='mensaje' name='mensaje' value=\""+ex.getMensaje()+"\" />");
                        out.println("<input type='text' id='codigo_error' name='codigo_error' value='"+ex.getCodigo_error()+"' />");
                    }
                    
                }
                break;
                case "guardar_domicilio":
                        {
                            try {
                                res = clientes.setDomicilioAlterno(db);
                                out.println("<input type=\"hidden\" id=\"res\" name=\"res\" value=\""+res+"\" />");
                            } catch (SqlAppsException ex) {
                                out.println("<input type=\"hidden\" id=\"resultado\" name=\"resultado\" value=\""+res+"\" />");
                                out.println("<input type='text' id='mensaje' name='mensaje' value=\""+ex.getMensaje()+"\" />");
                                out.println("<input type='text' id='codigo_error' name='codigo_error' value='"+ex.getCodigo_error()+"' />");
                                //Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        
                    break;
                case "carga_domicilio_alternos":
                    request.setAttribute("id_cliente_dom", id_cliente);
                    request.getRequestDispatcher("/WEB-INF/view/catalogos/cliente_domicilio.jsp").include(request, response);
                    break;
                
                default:
                    break;
            } //fin del switch
            
            
            
        
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
