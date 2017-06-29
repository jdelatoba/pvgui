<%-- 
    Document   : configuracion
    Created on : 21-jun-2017, 19:03:58
    Author     : Rembao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="sucursal" class="app.choya.sys.pv.nube.Sucursal"  />
<!DOCTYPE html>
<script type="text/javascript">
    
    $(document).ready(function(){
        
        
        
     if($("#id").val() != 0){
         
         $("#pass").hide();
     }   
    
    $("#aceptar").click(function(e){
             $("#moperacion").modal("hide");
        });
    
    $("#form_nube").validate({
            ignore: [],
            invalidHandler: function (event, validator) {
                $(".tab-content").find("div.tab-pane:hidden:has(div.has-error)").each(function (index, tab) {
                    var id = $(tab).attr("id");
                    $('a[href="#' + id + '"]').tab('show');
                });
                $("#moperacion").modal("show");
                $("#modal_mensaje").html("<span style=\"color: red\" class=\"fa fa-exclamation-triangle fa-3x\"></span>&nbsp;<label for=\"modal_mensaje\" style=\"font-size: 16px; font-weight: bolder\">Los datos no son validos o falta información, por favor revise los campos del formulario.</label></div>");

            },
            rules: {
                
                descripcion: {
                    required: true,
                    maxlength: 150
                },
                usuario: {
                    required: true,
                    minlength: 5,
                    maxlength: 50
                },
                contrasena:{
                    required: function(element){
                        return $("#id").val() == 0;
                    },
                    minlength: 5,
                    maxlength: 50
                },
                
                sync_tiempo: {
                    required: true,
                    number: true
                }
            },
            messages: {
                descripcion:{
                    required:"&nbsp;Por favor introduzca el nombre de la sucursal",
                    minlength: "&nbsp;El número de caracteres no es valido, se espera de 5 a 150 caracteres",
                    maxlength: "&nbsp;El número de caracteres no es valido, se espera de 5 a 150 caracteres"
                },
                usuario :{
                    required:"&nbsp;Por favor introduzca el nombre de usuario",
                    minlength: "&nbsp;El número de caracteres no es valido, se espera de 5 a 50 caracteres",
                    maxlength: "&nbsp;El número de caracteres no es valido, se espera de 5 a 50 caracteres"
                },
                contrasena:{
                    required:"&nbsp;Por favor introduzca la contraseña",
                    minlength: "&nbsp;El número de caracteres no es valido, se espera de 5 a 50 caracteres",
                    maxlength: "&nbsp;El número de caracteres no es valido, se espera de 5 a 50 caracteres"
                },
                sync_tiempo:{
                    required:"&nbsp;Por favor introduzca la frecuencia  con la que se actulizara los datos a la nube"
                    
                }
                
            },
             submitHandler: function (form) {

                $("#modal_mensaje").html("<span class='fa fa-cog fa-spin fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">Espere un momento por favor...</label></div>");
                $("#moperacion").modal("show");
                
                var id = $("#id").val();
                if(id == 0){
                    operacion = "guardar";
                }else{
                    operacion = "actualizar";
                }
                
                formData = {
                    
                    id: id,
                    descripcion : $("#descripcion").val(),
                    tipo : $("#tipo").val(),
                    usuario : $("#usuario").val(),
                    contrasena : $("#contrasena").val(),
                    sync_tiempo : $("#sync_tiempo").val(),
                    sync_proveedores : $('input[name=sync_proveedores]:checked').val(),
                    sync_clientes : $('input[name=sync_clientes]:checked').val(),
                    //estatus : $('input[name=estatus]:checked').val(),
                    operacion : operacion
                    
                };
                
                $.post("../nube/SucursalController",formData,function (data) {
                    
                    $("#operaciones").html(data);
                    
                    if ((parseInt($("#resultado").val()) > 0)) {
                        $("#modal_mensaje").html("<span style=\"color: green\" class=\"fa fa-check-circle fa-3x\"></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">La sucursal se ha registrado correctamente.</label></div>");
                        if(operacion == "guardar"){
                            $("#id").val($("#resultado").val());
                            $("#num_sucursal").val($("#resultado").val());
                            $("#pass").hide();
                            //$("#estatus").prop('checked', true);
                            $("#cestatus_activo").attr("checked","checked");
                            
                        }   
                        //$("#no_proveedor").val($("#resultado").val());
                    } else {
                        $("#modal_mensaje").html("<span style=\"color: red\" class=\"fa fa-exclamation-triangle fa-3x\"></span>&nbsp;<label for=\"modal_mensaje\" style=\"font-size: 16px; font-weight: bolder\">El sistema ha lanzado el siguiente error: Codigo Error: "+$("#codigo_error").val() +" "+ $("#mensaje").val() + ", El WS dice "+$("#mensaje_ws").val()+".</label></div>");

                    }
                    
                }); // fin del post
            }
        });
    
    }); //fin document ready
</script>

<form class="form-horizontal" method="post" id="form_nube" name="form_nube">
    <c:choose>
        <c:when test="${requestScope.id_sucursal != null && requestScope.id_sucursal != 0}">
            
            <c:forEach var="sb" items="${sucursal.getSucursalLocal(requestScope.id_sucursal)}">
                <c:set var="id" value="${sb.id}" />
                <c:set var="descripcion" value="${sb.descripcion}" />
                <c:set var="sync_tiempo" value="${sb.sync_tiempo}" />
                <c:set var="sync_proveedores" value="${sb.sync_proveedores}" />
                <c:set var="sync_clientes" value="${sb.sync_clientes}" />
                <%--<c:set var="estatus" value="${sb.estatus}" /> --%>
                <c:set var="usuario" value="${sb.usuario}" />
                
            </c:forEach>
            
        </c:when>
        <c:otherwise>
            <c:set var="id" value="0" />
            <c:set var="descripcion" value="" />
            <c:set var="sync_tiempo" value="15" />
            <c:set var="sync_proveedores" value="1" />
            <c:set var="sync_clientes" value="1" />
            <%-- <c:set var="estatus" value="D" /> --%>
            <c:set var="usuario" value="" />
            
        </c:otherwise>
    </c:choose>
    

<div id="operaciones"></div>
<input type="hidden" id="id" name='id' value='${id}' />
<div class="panel panel-default">
        
        <div class="panel-heading">
                <h3>Configuración para conexion a la nube</h3>
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-xs-12">
                    <div class="form-group">
                        <div class="col-md-12 col-lg-2">
                            <label for="no_sucursal" class="form-control-label">No. Sucursal</label>
                        </div>
                        <div class="col-md-12 col-lg-10">
                            <div class="controls form-inline">
                                <input type="text" class="form-control" id="num_sucursal" name="num_sucursal" value="${id}" placeholder="" size="20" maxlength="20" disabled=""/>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row"><div class="col-xs-12">&nbsp;</div></div>                    
            <div class="row">
                <div class="col-xs-12">
                    <div class="form-group">
                        <div class="col-md-12 col-lg-2">
                            <label for="descripcion" class="form-control-label">Nombre</label>
                        </div>
                        <div class="col-md-12 col-lg-10">
                            <div class="controls form-inline">
                                <input type="text" class="form-control" id="descripcion" name="descripcion" value="${descripcion}" placeholder="" size="50" maxlength="150"/>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row"><div class="col-xs-12">&nbsp;</div></div>                    
            <div class="row">
                <div class="col-xs-12">
                    <div class="form-group">
                        <div class="col-md-12 col-lg-2">
                            <label for="tipo" class="form-control-label">Tipo</label>
                        </div>
                        <div class="col-md-12 col-lg-10">
                            <div class="controls form-inline">
                                <select name="tipo" id="tipo"  class="form-control">
                                    <option value="1">Matriz</option>
                                    <option value="2">Sucursal</option>
                                </select> 

                            </div>
                        </div>
                    </div>
                </div>
            </div>                    
            <div class="row"><div class="col-xs-12">&nbsp;</div></div>
            <div class="row">
                <div class="col-xs-12">
                    <div class="form-group">
                        <div class="col-md-12 col-lg-2">
                            <label for="usuario" class="form-control-label">usuario</label>
                        </div>
                        <div class="col-md-12 col-lg-10">
                            <div class="controls form-inline">
                                <input type="text" class="form-control" id="usuario" name="usuario" value="${usuario}" placeholder="" size="20" maxlength="50"/>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row"><div class="col-xs-12">&nbsp;</div></div>
            <div class="row" id="pass">
                <div class="col-xs-12">
                    <div class="form-group">
                        <div class="col-md-12 col-lg-2">
                            <label for="contrasena" class="form-control-label">Contraseña</label>
                        </div>
                        <div class="col-md-12 col-lg-10">
                            <div class="controls form-inline">
                                <input type="password" class="form-control" id="contrasena" name="contrasena" value="" placeholder="" size="20" maxlength="50"/>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row"><div class="col-xs-12">&nbsp;</div></div>
            <div class="row">
                <div class="col-xs-12">
                    <div class="form-group">
                        <div class="col-md-12 col-lg-2">
                            <label for="sync_tiempo" class="form-control-label">Frecuencia de Sincronización</label>
                        </div>
                        <div class="col-md-12 col-lg-10">
                            <div class="controls form-inline">
                                <input type="text" class="form-control" id="sync_tiempo" name="sync_tiempo" value="${sync_tiempo}" placeholder="" size="20" maxlength="20"/>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row"><div class="col-xs-12">&nbsp;</div></div>
            
            
            <div class="row">
                <div class="col-xs-12">
                    <div class="form-group">
                        <div class="col-md-12 col-lg-2">
                            <label for="sync_proveedores_label" class="form-control-label">Sincronizar Proveedores</label>
                        </div>
                        <div class="col-md-12 col-lg-10">
                            <div class="btn-group" data-toggle="buttons">
                                <c:choose>
                                <c:when test="${sync_proveedores == 0}" >
                                    <label class="btn btn-default-outline">
                                        <input type="radio" name="sync_proveedores" value="1">
                                        SI
                                    </label>
                                    <label class="btn btn-default-outline active">
                                        <input type="radio" name="sync_proveedores" value="0" checked>
                                        No
                                    </label>
                                </c:when>
                                <c:otherwise>
                                        <label class="btn btn-default-outline active">
                                            <input type="radio" name="sync_proveedores" value="1" checked>
                                        SI
                                    </label>
                                    <label class="btn btn-default-outline">
                                        <input type="radio" name="sync_proveedores" value="0" >
                                        No
                                    </label>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            
            <div class="row">
                <div class="col-xs-12">
                    <div class="form-group">
                        <div class="col-md-12 col-lg-2">
                            <label for="sync_clientes_label" class="form-control-label">Sincronizar Clientes</label>
                        </div>
                        <div class="col-md-12 col-lg-10">
                            <div class="btn-group" data-toggle="buttons">
                                <c:choose>
                                <c:when test="${sync_clientes == 0}" >
                                    <label class="btn btn-default-outline">
                                        <input type="radio" name="sync_clientes" value="1">
                                        SI
                                    </label>
                                    <label class="btn btn-default-outline active">
                                        <input type="radio" name="sync_clientes" value="0" checked>
                                        No
                                    </label>
                                </c:when>
                                <c:otherwise>
                                        <label class="btn btn-default-outline active">
                                            <input type="radio" name="sync_clientes" value="1" checked>
                                        SI
                                    </label>
                                    <label class="btn btn-default-outline">
                                        <input type="radio" name="sync_clientes" value="0" >
                                        No
                                    </label>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <%-- <div class="row"><div class="col-xs-12">&nbsp;</div></div>
            <div class="row">
                <div class="col-xs-12">
                    <div class="form-group">
                        <div class="col-md-12 col-lg-2">
                            <label for="estatus" class="form-control-label">Estatus</label>
                        </div>
                        <div class="col-md-12 col-lg-10">
                            <div class="btn-group" data-toggle="buttons">
                                <c:choose>
                                <c:when test="${estatus != 'A'}" >
                                    <label class="btn btn-success-outline">
                                        <input type="radio" name="estatus" id="cestatus_activo" value="1">
                                        Activo
                                    </label>
                                    <label class="btn btn-danger-outline active">
                                        <input type="radio" name="estatus" id="cestatus_inactivo" value="0" checked>
                                        Inactivo
                                    </label>
                                </c:when>
                                <c:otherwise>
                                        <label class="btn btn-success-outline active">
                                            <input type="radio" name="estatus" id="cestatus_activo" value="1" checked>
                                        Activo
                                    </label>
                                    <label class="btn btn-danger-outline">
                                        <input type="radio" name="estatus" id="cestatus_inactivo" value="0" >
                                        Inactivo
                                    </label>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
            </div> --%>
            
            
            <div class="row">
                <div class="col-xs-12">
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary width-150">Guardar</button>
                        
                    </div>
                    
                </div>
            </div>
        </div> <!-- fin panel-body -->
</div><!-- fin panel panel-default -->

</form>