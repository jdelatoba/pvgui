<%-- 
    Document   : unidad
    Created on : 31-mar-2017, 4:47:29
    Author     : Rembao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="unidad" class="app.choya.sys.pv.configuracion.Unidad"  />
<!DOCTYPE html>



<script type="text/javascript">

    $(document).ready(function (e) {
        
        
        $("input[type=text]").focus(function () {
            $(this).select();
        });

        $("input[type=text]").blur(function (e) {
            $(this).val($.trim($(this).val().toUpperCase()));
        });
        
        
        
        $("#nuevo").click(function(e){
            $("#mensaje_comando").html("<span class='fa fa-exclamation-triangle fa-3x'></span>&nbsp;<label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">¿Desea agregar un nuevo vendedor?\n\nLos cambios que no ha guardado no tendran efecto en el registro.</label></div>");
            $("#mcomando").modal("show");
        });
        
        $("#recargar").click(function(e){
            $("#funcion_forma").val("unidades_lista");
            
            
            $('#form_navega').attr('action', "../pv/Hub");
            $("#form_navega").submit();
        });
        
        
        $("#aceptar_comando").click(function(e){
            $("#funcion_forma").val("unidades");
            $('#form_navega').attr('action', "../pv/Hub");
            $("#form_navega").submit();
        });
        
        
        $("#form_moneda").validate({
            ignore: [],
            invalidHandler: function (event, validator) {
                $("#moperacion").modal("show");
                $("#modal_mensaje").html("<span style=\"color: red\" class=\"fa fa-exclamation-triangle fa-3x\"></span>&nbsp;<label for=\"modal_mensaje\" style=\"font-size: 16px; font-weight: bolder\">Los datos no son validos o falta información, por favor revise los campos del formulario.</label></div>");

            },
             rules: {
                descripcion: {
                    required: true,
                    minlength: 3,
                    maxlength: 50
                },
                abrev: {
                    required: true,
                    minlength: 2,
                    maxlength: 4
                }
            },
            
            messages: {
                descripcion: {
                    required: "&nbsp;Por favor introduzca nombre de la unidad",
                    minlength: "&nbsp;El número de caracteres no es valido, se espera de 3 a 50 caracteres"
                },
                abrev: {
                    required: "&nbsp;Por favor introduzca abreviatura",
                    minlength: "&nbsp;El número de caracteres no es valido, se espera de 2 a 4 caracteres",
                    maxlength: "&nbsp;El número de caracteres no es valido, se espera de 2 a 4 caracteres",
                }
            },
            submitHandler: function (form) {
                $("#modal_mensaje").html("<span class='fa fa-cog fa-spin fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">Espere un momento por favor...</label></div>");
                $("#moperacion").modal("show");
                
                
                var id = $("#id").val();
                var descripcion = $("#descripcion").val();
                var abrev = $("#abrev").val();
                
                var operacion = "";
                
                
                if(id == 0){
                    operacion = "guardar";
                }else{
                    operacion = "actualizar";
                }
                
                formData = {
                    id : id,
                    descripcion: descripcion,
                    abrev : abrev,
                    operacion : operacion
                    
                };
                
                $.post("../admon/UnidadController", formData, function (data) {
                    
                    $("#operaciones").html(data);
                   
                    if ((parseInt($("#resultado").val()) != 0)) {
                        
                        if(operacion == "guardar"){
                            $("#modal_mensaje").html("<span style=\"color: green\" class=\"fa fa-check-circle fa-3x\"></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">El registro se ha guardado correctamente.</label></div>");
                            $("#id").val($("#resultado").val());
                            $("#no_unidad").val($("#resultado").val());
                        }else{
                            
                            $("#modal_mensaje").html("<span style=\"color: green\" class=\"fa fa-check-circle fa-3x\"></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">El registro se ha actualizado correctamente.</label></div>");
                        }
                    } else {
                        $("#modal_mensaje").html("<span style=\"color: red\" class=\"fa fa-exclamation-triangle fa-3x\"></span>&nbsp;<label for=\"modal_mensaje\" style=\"font-size: 16px; font-weight: bolder\">El sistema ha lanzado el siguiente error: Codigo Error: "+$("#codigo_error").val() +" "+ $("#mensaje").val() + ".</label></div>");

                    }
                    
                    
                });
                
            }
        }); // fin validate
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
  margin: 18px 20px 18px 20px;
}

</style>


<c:choose>
    <c:when test="${requestScope.unidad_id != null &&  requestScope.unidad_id != 0}">
        <c:forEach var="ubean" items="${unidad.getUnidad(requestScope.unidad_id)}" >
            <c:set var="id" value="${ubean.id}" />
            <c:set var="descripcion" value="${ubean.descripcion}" />
            <c:set var="abrev" value="${ubean.abrev}" />
            <c:set var="serie" value="${ubean.serie}" />
            <c:set var="estatus" value="${ubean.estatus}" />

        </c:forEach>
    </c:when>
    <c:otherwise>

        <c:set var="id" value="0" />
        <c:set var="descripcion" value="" />
        <c:set var="abrev" value="" />
        <c:set var="serie" value="A" />
        <c:set var="estatus" value="A" />

    </c:otherwise>
</c:choose>

<div class="row"><div class="col-xs-12">&nbsp;</div></div>
<form method="post" id="form_navega" name="form_navega">
    <input type="hidden" id="funcion_forma" name="funcion_forma" />
</form>


<form class="form-horizontal" method="post" id="form_moneda" name="form_moneda">
    
    <div id="operaciones"></div>
    <div class="row"><div class="col-xs-12">
        <div class="btn-group" role="group" aria-label="">
          <button type="submit" class="btn btn-default" id="guardar" name="guardar"><span class="fa fa-floppy-o"></span>&nbsp;Guardar</button>
          <button type="button" class="btn btn-default" id="nuevo" name="nuevo"><span class="fa fa fa-file-o"></span>&nbsp;Nuevo</button>
          <button type="button" class="btn btn-default" id="recargar" name="recargar"><span class="fa fa-refresh"></span>&nbsp;Recargar</button>
        </div>
    </div>
    </div>
    <div class="row"><div class="col-xs-12">&nbsp;</div></div>
    <div class="panel panel-default">
        <div class="panel-heading">Unidad</div>
        <div class="panel-body">
            
            
            <!-- contenido del panel -->

                <input type="hidden" id="id" name="id" value="${id}" />
                <div class="row"><div class="col-xs-12">&nbsp;</div></div>
                
                <div class="row">
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="no_unidad" class="col-md-12 col-lg-2">Núm.<label style="color: red">*</label></label>
                            <div class="col-md-12 col-lg-10">
                                <div class="controls form-inline">
                                    <input type="text" class="form-control" id="no_unidad" name="no_unidad" value="${id}" placeholder="" size="20" maxlength="20" disabled="disabled"/>

                                </div>
                            </div>
                        </div>
                    </div>
                                    
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="descripcion" class="col-md-12 col-lg-2">Descripción<label style="color: red">*</label></label>
                            <div class="col-md-12 col-lg-10">
                                <div class="controls form-inline">
                                    <input type="text" class="form-control" id="descripcion" name="descripcion" value="${descripcion}" placeholder="" size="50" maxlength="50" />

                                </div>
                            </div>
                        </div>
                    </div>
                                    
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="abrev" class="col-md-12 col-lg-2">Abreviatura<label style="color: red">*</label></label>
                            <div class="col-md-12 col-lg-10">
                                <div class="controls form-inline">
                                        <input type="text" class="form-control" id="abrev" name="abrev" value="${abrev}" placeholder="" size="10" maxlength="4" />
                                    
                                </div>
                            </div>
                        </div>
                    </div>
                    
                                    
                                    
                                    
                </div>                    
        </div> <!-- fin panel-body -->    
    </div> <!-- fin panel-default -->
    
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