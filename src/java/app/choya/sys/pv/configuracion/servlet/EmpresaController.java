/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.choya.sys.pv.configuracion.servlet;

import app.choya.sys.pv.configuracion.Empresa;
import app.choya.sys.pv.configuracion.EmpresaBean;
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
@WebServlet(name = "EmpresaController", urlPatterns = {"/admon/EmpresaController"})
public class EmpresaController extends HttpServlet {

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
            
            System.out.println("Entre a empresas controller");
            HttpSession session = request.getSession(false);
            String operacion = (request.getParameter("operacion") != null && request.getParameter("operacion").compareTo("") != 0) ? request.getParameter("operacion") : "";
            
            int id = (request.getParameter("id") != null && request.getParameter("id").compareTo("") != 0) ? Integer.parseInt(request.getParameter("id")) : 0;
            String nombre = (request.getParameter("nombre") != null && request.getParameter("nombre").compareTo("") != 0) ? request.getParameter("nombre") : "";
            String domicilio = (request.getParameter("domicilio") != null && request.getParameter("domicilio").compareTo("") != 0) ? request.getParameter("domicilio") : "";
            String ciudad = (request.getParameter("ciudad") != null && request.getParameter("ciudad").compareTo("") != 0) ? request.getParameter("ciudad") : "";
            String estado = (request.getParameter("estado") != null && request.getParameter("estado").compareTo("") != 0) ? request.getParameter("estado") : "";
            String codigo_postal = (request.getParameter("codigo_postal") != null && request.getParameter("codigo_postal").compareTo("") != 0) ? request.getParameter("codigo_postal") : "";
            String telefono = (request.getParameter("telefono") != null && request.getParameter("telefono").compareTo("") != 0) ? request.getParameter("telefono") : "";
            String celular = (request.getParameter("celular") != null && request.getParameter("celular").compareTo("") != 0) ? request.getParameter("celular") : "";
            String email = (request.getParameter("email") != null && request.getParameter("email").compareTo("") != 0) ? request.getParameter("email") : "";
            String nombre_fiscal = (request.getParameter("nombre_fiscal") != null && request.getParameter("nombre_fiscal").compareTo("") != 0) ? request.getParameter("nombre_fiscal") : "";
            String rfc = (request.getParameter("rfc") != null && request.getParameter("rfc").compareTo("") != 0) ? request.getParameter("rfc") : "";
            String curp = (request.getParameter("curp") != null && request.getParameter("curp").compareTo("") != 0) ? request.getParameter("curp") : "";
            String adicional = (request.getParameter("adicional") != null && request.getParameter("adicional").compareTo("") != 0) ? request.getParameter("adicional") : "";
            String regimen_fiscal = (request.getParameter("regimen_fiscal") != null && request.getParameter("regimen_fiscal").compareTo("") != 0) ? request.getParameter("regimen_fiscal") : "";
            String domicilio_fiscal = (request.getParameter("domicilio_fiscal") != null && request.getParameter("domicilio_fiscal").compareTo("") != 0) ? request.getParameter("domicilio_fiscal") : "";
            String no_ext_fiscal = (request.getParameter("no_ext_fiscal") != null && request.getParameter("no_ext_fiscal").compareTo("") != 0) ? request.getParameter("no_ext_fiscal") : "";
            String no_int_fiscal = (request.getParameter("no_int_fiscal") != null && request.getParameter("no_int_fiscal").compareTo("") != 0) ? request.getParameter("no_int_fiscal") : "";
            String colonia_fiscal = (request.getParameter("colonia_fiscal") != null && request.getParameter("colonia_fiscal").compareTo("") != 0) ? request.getParameter("colonia_fiscal") : "";
            String localidad_fiscal = (request.getParameter("localidad_fiscal") != null && request.getParameter("localidad_fiscal").compareTo("") != 0) ? request.getParameter("localidad_fiscal") : "";
            String codigo_postal_fiscal = (request.getParameter("codigo_postal_fiscal") != null && request.getParameter("codigo_postal_fiscal").compareTo("") != 0) ? request.getParameter("codigo_postal_fiscal") : "";
            String ciudad_fiscal = (request.getParameter("ciudad_fiscal") != null && request.getParameter("ciudad_fiscal").compareTo("") != 0) ? request.getParameter("ciudad_fiscal") : "";
            String entidad_fiscal = (request.getParameter("entidad_fiscal") != null && request.getParameter("entidad_fiscal").compareTo("") != 0) ? request.getParameter("entidad_fiscal") : "";
            String pais_fiscal = (request.getParameter("pais_fiscal") != null && request.getParameter("pais_fiscal").compareTo("") != 0) ? request.getParameter("pais_fiscal") : "";
            int sucursal = (Integer) session.getAttribute("sucursal");
            int usuario_id = (Integer) session.getAttribute("usuario_id");
            
            EmpresaBean eb = new EmpresaBean();
            eb.setId(id);
            eb.setNombre(nombre);
            eb.setDomicilio(domicilio);
            eb.setCiudad(ciudad);
            eb.setEntidad(estado);
            eb.setCodigo_postal(codigo_postal);
            eb.setTelefono(telefono);
            eb.setCelular(celular);
            eb.setEmail(email);
            eb.setNombre_fiscal(nombre_fiscal);
            eb.setRfc(rfc);
            eb.setCurp(curp);
            eb.setAdicional(adicional);
            eb.setRegimen_fiscal(regimen_fiscal);
            eb.setDomicilio_fiscal(domicilio_fiscal);
            eb.setNum_ext_fiscal(no_ext_fiscal);
            eb.setNum_int_fiscal(no_int_fiscal);
            eb.setColonia_fiscal(colonia_fiscal);
            eb.setLocalidad_fiscal(localidad_fiscal);
            eb.setCodigo_postal_fiscal(codigo_postal_fiscal);
            eb.setCiudad_fiscal(ciudad_fiscal);
            eb.setEntidad_fiscal(entidad_fiscal);
            eb.setPais_fiscal(pais_fiscal);
            eb.setSerie("A");
            eb.setSucursal(sucursal);
            eb.setCreado_por(usuario_id);
            eb.setModificado_por(usuario_id);
            
            Empresa empresa = new Empresa();
            
            int resultado = 0;
            //System.out.println("operacion --- "+operacion);
            //System.out.println("id --"+eb.getId());
            switch (operacion) {
                case "actualizar":
                case "guardar": {
                    try{
                        //if(eb.getId() == 0){
                        //    resultado = empresa.setEmpresa(eb);
                        //}else{
                            resultado = empresa.updateEmpresa(eb);
                        //}
                        out.println("<input type=\"hidden\" id=\"resultado\" name=\"resultado\" value=\""+resultado+"\" />");
                       
                        
                    } catch (SqlAppsException ex) {
                        
                        out.println("<input type='hidden' id='resultado' name='resultado' value='"+resultado+"' />");
                        out.println("<input type='hidden' id='mensaje' name='mensaje' value=\""+ex.getMensaje()+"\" />");
                        out.println("<input type='hidden' id='codigo_error' name='codigo_error' value='"+ex.getCodigo_error()+"' />");
                        //System.out.println(System.getProperty("java.home"));
                        Logger.getLogger(EmpresaController.class.getName()).log(Level.SEVERE, "What!!!", ex);
                    }
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
