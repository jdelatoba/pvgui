<%-- 
    Document   : grupo
    Created on : 28-mar-2017, 05:08:50
    Author     : jdelatoba
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="grupo" class="app.choya.sys.pv.configuracion.Grupo"  />
<!DOCTYPE html>

<script type="text/javascript">

    $(document).ready(function (e) {
        
        
        
        $("input[type=text]").focus(function () {
            $(this).select();
        });

        $("input[type=text]").blur(function (e) {
            $(this).val($.trim($(this).val().toUpperCase()));
        });
        
        
        $("#form_grupo").validate({
            ignore: [],
            invalidHandler: function (event, validator) {
                $("#moperacion").modal("show");
                $("#modal_mensaje").html("<span style=\"color: red\" class=\"fa fa-exclamation-triangle fa-3x\"></span>&nbsp;<label for=\"modal_mensaje\" style=\"font-size: 16px; font-weight: bolder\">Los datos no son validos o falta información, por favor revise los campos del formulario.</label></div>");

            },
            rules: {
                descrpcion: {
                    required: true,
                    minlength: 3,
                    maxlength: 150
                },
               
            },
            
            messages: {
                descripcion: {
                    required: "&nbsp;Por favor introduzca una descripcion para el grupo",
                    minlength: "&nbsp;El número de caracteres no es valido, se espera de 3 a 150 caracteres"
                }              
                

            },
            submitHandler: function (form) {

                $("#modal_mensaje").html("<span class='fa fa-cog fa-spin fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">Espere un momento por favor...</label></div>");
                $("#moperacion").modal("show");

                var id = $("#id").val();
                var descripcion = $("#descripcion").val();
                var operacion = "";
                
                if(id == 0){
                    operacion = "guardar";
                }else{
                    operacion = "actualizar";
                }
                
                formData = {
                    id: id,
                    descripcion: descripcion,
                    operacion : operacion
                };

                $.post("../admon/GrupoController", formData, function (data) {
                    $("#operaciones").html(data);
                    
                    if ((parseInt($("#resultado").val()) != 0)) {
                        
                        if(operacion == "guardar"){
                            $("#modal_mensaje").html("<span style=\"color: green\" class=\"fa fa-check-circle fa-3x\"></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">El registro se ha guardado correctamente.</label></div>");
                            $("#id").val($("#resultado").val());
                            $("#no_usuario").val($("#resultado").val());
                        }else{
                            
                            $("#modal_mensaje").html("<span style=\"color: green\" class=\"fa fa-check-circle fa-3x\"></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">El registro se ha actualizado correctamente.</label></div>");
                        }
                    } else {
                        $("#modal_mensaje").html("<span style=\"color: red\" class=\"fa fa-exclamation-triangle fa-3x\"></span>&nbsp;<label for=\"modal_mensaje\" style=\"font-size: 16px; font-weight: bolder\">El sistema ha lanzado el siguiente error: Codigo Error: "+$("#codigo_error").val() +" "+ $("#mensaje").val() + ".</label></div>");

                    }
                    
                    
                });
                //asdasd$(form).submit();*/
            }

        });  // fin $("#form_proveedor").validate
        
    });
    
</script>


<style type="text/css">
   
    
     
  
 hr {
  -moz-border-bottom-colors: none;
  -moz-border-image: none;
  -moz-border-left-colors: none;
  -moz-border-right-colors: none;
  -moz-border-top-colors: none;
  border-color: #EEEEEE -moz-use-text-color #FFFFFF;
  border-style: solid none;
  border-width: 1px 0;
  margin: 18px 20px 18px 0px;
}


</style>
    <c:choose>
         <c:when test="${requestScope.grupo_id != null &&  requestScope.grupo_id != 0}">
            <c:forEach var="ebean" items="${grupo.getGrupo()}" >
                <c:set var="id" value="${ebean.id}" />
                <c:set var="descripcion" value="${ebean.descripcion}" />
            </c:forEach>
        </c:when>
    <c:otherwise>    
                <c:set var="id" value="0" />
                <c:set var="descripcion" value="" />
             
    </c:otherwise>
</c:choose>

<div class="row"><div class="col-xs-12">&nbsp;</div></div>
<!--<form method="post" id="form_navega" name="form_navega">
    <input type="hidden" id="funcion" name="funcion" />
</form> -->


<form class="form-horizontal" method="post" id="form_grupo" name="form_grupo">

    <div id="operaciones"></div>
    
    <div class="row"><div class="col-xs-12">
        <div class="btn-group" role="group" aria-label="">
            <button type="submit" class="btn btn-default" id="guardar" name="guardar"><span class="fa fa-floppy-o"></span><c:if test="${id == 0}">&nbsp;Guardar</c:if><c:if test="${id != 0}">&nbsp;Actualizar</c:if></button>
          <button type="button" class="btn btn-default" id="nuevo" name="nuevo"><span class="fa fa fa-file-o"></span>&nbsp;Nuevo</button>
          <button type="button" class="btn btn-default" id="recargar" name="recargar"><span class="fa fa-refresh"></span>&nbsp;Recargar</button>
        </div>
        <hr style="width: 100%; color: #d0cdcb; height: 0.2px; background-color:#000000;">
                  
    </div>
    </div>
    
    <div class="panel panel-default">
        <div class="panel-heading">Catalogo de Grupos</div>
        <div class="panel-body">

             <!--   </ul> <!-- nav nav-tabs -->
                <!-- contenido del panel -->
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane fade in active" id="tab-general">
                        <input type="hidden" id="id" name="id" value="${id}" />
                        <div class="row"><div class="col-xs-12">&nbsp;</div></div>
                        <div class="row">
                            <div class="col-xs-12 col-lg-6">
                                <div class="form-group">
                                    <label for="id" class="col-md-12 col-lg-3">Núm.<label style="color: red">*</label></label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="id" name="id" value="${id}" placeholder="" size="20" maxlength="20" disabled="disabled"/>

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-lg-6">
                                <div class="form-group">
                                    <label for="descripcion" class="col-md-12 col-lg-3">Descripcion<label style="color: red">*</label></label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="descripcion" name="nombre" value="${descripcion}" placeholder="" size="40" maxlength="250" />

                                        </div>
                                    </div>
                                </div>
                            </div>
                
                        </div> <!-- fin del row principal contenido -->
                
                    </div> <!-- tab-general -->
                   
                </div> <!-- fin tab-content -->
        </div> <!-- fin del panel-body -->
    </div> <!-- fin del panel-heading -->
    
    
    <div class="modal fade" id="moperacion" tabindex="-1" role="dialog" aria-labelledby="modal-operaciones">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Punto de Venta</h4>
                </div>
                <div class="modal-body">
                    <div class="row">

                        <div  class="col-xs-12">
                            <div id="modal_mensaje">
                                <label  style="font-size: 18px; font-weight: bolder"><span class="fa fa-spinner fa-pulse fa-3x fa-fw"></span>&nbsp;  Espere un momento por favor.</label>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                    
                  </div>
            </div>
        </div>
    </div>
    
    
    <div class="modal fade" id="mcomando" tabindex="-1" role="dialog" aria-labelledby="modal-comando">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Punto de Venta</h4>
                </div>
                <div class="modal-body">
                    <div class="row">

                        <div  class="col-xs-12">
                            <div id="mensaje_comando">
                                <label  style="font-size: 18px; font-weight: bolder"><span class="fa fa-spinner fa-pulse fa-3x fa-fw"></span>&nbsp;  Espere un momento por favor.</label>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" id="aceptar_comando" name="aceptar_nuevo">Aceptar</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                    
                  </div>
            </div>
        </div>
    </div>
</form>    