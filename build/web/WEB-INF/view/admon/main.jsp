<%-- 
    Document   : main
    Created on : 18-mar-2017, 15:13:45
    Author     : Rembao
--%>

<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="javax.servlet.http.HttpSession" %>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<script type="text/javascript">
    
</script>

<%
                Date fecha = Calendar.getInstance().getTime();
                //SimpleDateFormat dt = new SimpleDateFormat("hh:mm:ss");
                DateFormat df = new SimpleDateFormat("HH:mm:ss");
                String date = df.format(fecha);
                
                String hora = date.substring(0, 2);
                String saludo = "";
                switch(hora){
                    case "00":
                    case "01":
                    case "02":
                    case "03":
                    case "04":    
                    case "19":
                    case "20":
                    case "21":
                    case "22":
                    case "23":
                        saludo = "Buenas noches, ";
                        break;
                    case "05":
                    case "06":
                    case "07":
                    case "08":
                    case "09":    
                    case "10":
                    case "11":
                        saludo = "Buenos días, ";
                        break;
                    case "12":
                    case "13":
                    case "14":
                    case "15":
                    case "16":    
                    case "17":
                    case "18":
                        saludo = "Buenas tardes, ";
                        break;
                    default:
                        break;
                    
                       
                }
                
System.out.println("saludo "+saludo);
System.out.println("hora  "+hora);
%>

<div class="row"><div class="col-xs-12">&nbsp;</div></div>
<form method="post" id="form_navega" name="form_navega">
    <input type="hidden" id="funcion" name="funcion" />
</form>


<form class="form-horizontal" method="post" id="form-main" name="form-main">
    <c:set var="nombre" value="${sessionScope.nombre}" />
    <c:set var="hora" value="<%=date%>" />
    <div id="operaciones"></div>
    
    <div class="row">
    <div class="col-xs-12">
        <h4 style="color: #58595c"> <%=saludo%> ${nombre}</h4>
    </div>
        
    </div>
    
    <div class="row">
        
        
        <div class="col-xs-12 col-sm-3">
            
            <div class="panel panel-default">
                <div class="row"><div class="col-xs-12">&nbsp;</div></div>
                <div class="row">
                    <div class="col-xs-3 ">
                        <i class="fa fa-user-circle fa-4x fa-fw" style="color: #ecbb5f"></i>
                    </div>

                    <div class="col-xs-8 text-right">
                        <div class="huge">&nbsp;</div>
                        <div style="font-weight: bolder">Administración del Sistema</div>
                    </div>
                    <div class="col-xs-1">&nbsp;</div>
                </div>
                <hr style="width: 70%; color: #EEEEEE; height: 0.2px; background-color:#EEEEEE; margin: 0px 10px 10px 20px; padding: 0px 10px 0px 20px">               
                
                <div class="row">
                    <div class="col-xs-8" style="text-align: center"><a href="#" >Clic Aquí</a></div>
                    <div class="col-xs-4"><i class="fa fa-chevron-circle-right fa-fw" style="color: #ecbb5f"></i></div>
                </div>
                <div class="row"><div class="col-xs-12">&nbsp;</div></div>
            </div>
               
        </div>
        
        <div class="col-xs-12 col-sm-3">
            
            <div class="panel panel-default">
                <div class="row"><div class="col-xs-12">&nbsp;</div></div>
                <div class="row">
                    <div class="col-xs-3 ">
                        <i class="fa fa-money fa-4x fa-fw" style="color: #9fcdae"></i>
                    </div>

                    <div class="col-xs-8 text-right">
                        <div class="huge">&nbsp;</div>
                        <div style="font-weight: bolder">Módulo de Ventas - Caja</div>
                    </div>
                    <div class="col-xs-1">&nbsp;</div>
                </div>
                <hr style="width: 70%; color: #EEEEEE; height: 0.2px; background-color:#EEEEEE; margin: 0px 10px 10px 20px; padding: 0px 10px 0px 20px">               
                
                <div class="row">
                    <div class="col-xs-8" style="text-align: center"><a href="#" >Clic Aquí</a></div>
                    <div class="col-xs-4"><i class="fa fa-chevron-circle-right fa-fw" style="color: #9fcdae"></i></div>
                </div>
                <div class="row"><div class="col-xs-12">&nbsp;</div></div>
            </div>
               
        </div>
    </div>
        
    <!-- <div class="alert alert-dismissible alert-info">
  <button type="button" class="close" data-dismiss="alert">&times;</button>
  <strong>Heads up!</strong> This <a href="#" class="alert-link">alert needs your attention</a>, but it's not super important.
</div>   -->                 
    <!--<div class="alert alert-dismissible alert-success">
  <button type="button" class="close" data-dismiss="alert">&times;</button>
  <strong>Well done!</strong> You successfully read <a href="#" class="alert-link">this important alert message</a>.
</div>  -->                 
</form>    