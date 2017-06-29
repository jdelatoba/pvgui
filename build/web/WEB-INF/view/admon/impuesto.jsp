<%-- 
    Document   : impuesto
    Created on : 28-mar-2017, 9:29:00
    Author     : Rembao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="impuestos" class="app.choya.sys.pv.configuracion.Impuesto"  />
<!DOCTYPE html>



<script type="text/javascript">

    $(document).ready(function (e) {
        
        $('.selectpicker').selectpicker();
        
        $("input[type=text]").focus(function () {
            $(this).select();
        });

        $("input[type=text]").blur(function (e) {
            $(this).val($.trim($(this).val().toUpperCase()));
        });
        
        
        
        $("#impuesto").numeric();
        
        $("#impuesto").blur(function (e) {
            if ($("#impuesto").val() == "") {
                $("#impuesto").val("0.00");
            } else {

                $("#impuesto").formatCurrency();
                var impuesto = $("input[id='impuesto']").val();
                $("input[id='impuesto']").val(impuesto.replace("$", ""));
            }
        });
        
        
        
        $("#form_impuesto").validate({
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
                impuesto: {
                    required: true,
                    number : true
                },
                activo:{
                    required: true
                },
                impreso:{
                    required: true
                },
                t_impuesto: {
                    required: true
                },
                orden: {
                    required: true
                }
            },
            
            messages: {
                descripcion: {
                    required: "&nbsp;Por favor introduzca un nombre valido para el impuesto",
                    minlength: "&nbsp;El número de caracteres no es valido, se espera de 3 a 50 caracteres",
                    maxlength: "&nbsp;El número de caracteres no es valido, se espera de 3 a 50 caracteres"
                },
                impuesto: {
                    required: "&nbsp;Por favor introduzca el porcentaje de impuesto",
                    number: "&nbsp;El número no es valido"
                },
                activo: "&nbsp;Por favor seleccione opción.",
                impreso: "&nbsp;Por favor seleccione opción.",
                t_impuesto: "&nbsp;Por favor seleccione opción.",
                orden: "&nbsp;Por favor introduzca número valido."
                

            },
            submitHandler: function (form) {
                $("#modal_mensaje").html("<span class='fa fa-cog fa-spin fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">Espere un momento por favor...</label></div>");
                $("#moperacion").modal("show");
                
                var id = $("#id").val();
                var descripcion = $("#descripcion").val();
                var impuesto = $("#impuesto").val();
                var activo = $("#activo").val();
                var impreso = $("#impreso").val();
                var t_impuesto = $("#t_impuesto").val();
                var orden = $("#orden").val();
                var operacion = "";
                
                if(id == 0){
                    operacion = "guardar";
                }else{
                    operacion = "actualizar";
                }
                formData = {
                    id: id,
                    descripcion: descripcion,
                    impuesto: impuesto,
                    activo : activo,
                    impreso : impreso, 
                    t_impuesto : t_impuesto,
                    orden : orden,
                    operacion : operacion
                };
                
                $.post("../admon/ImpuestoController", formData, function (data) {
                    
                    $("#operaciones").html(data);
                   
                    if ((parseInt($("#resultado").val()) != 0)) {
                        
                        if(operacion == "guardar"){
                            $("#modal_mensaje").html("<span style=\"color: green\" class=\"fa fa-check-circle fa-3x\"></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">El registro se ha guardado correctamente.</label></div>");
                            $("#id").val($("#resultado").val());
                            $("#no_impuesto").val($("#resultado").val());
                        }else{
                            
                            $("#modal_mensaje").html("<span style=\"color: green\" class=\"fa fa-check-circle fa-3x\"></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">El registro se ha actualizado correctamente.</label></div>");
                        }
                    } else {
                        $("#modal_mensaje").html("<span style=\"color: red\" class=\"fa fa-exclamation-triangle fa-3x\"></span>&nbsp;<label for=\"modal_mensaje\" style=\"font-size: 16px; font-weight: bolder\">El sistema ha lanzado el siguiente error: Codigo Error: "+$("#codigo_error").val() +" "+ $("#mensaje").val() + ".</label></div>");

                    }
                    
                    
                });
                
            }
        }); //fin validate
        
        
        $("#nuevo").click(function(e){
            $("#mensaje_comando").html("<span class='fa fa-exclamation-triangle fa-3x'></span>&nbsp;<label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">¿Desea agregar un nuevo impuesto?\n\nLos cambios que no ha guardado no tendran efecto en el registro.</label></div>");
            $("#mcomando").modal("show");
        });
        
        $("#recargar").click(function(e){
            $("#funcion_forma").val("impuestos_lista");
            $('#form_navega').attr('action', "../pv/Hub");
            $("#form_navega").submit();
        });
        
        
        $("#aceptar_comando").click(function(e){
            $("#funcion_forma").val("impuestos");
            $('#form_navega').attr('action', "../pv/Hub");
            $("#form_navega").submit();
        });
        
        
        
        $("#btn_grupo").click(function(){
            
            $("#modal_mensaje").html("<span class='fa fa-cog fa-spin fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">Espere un momento por favor...</label></div>");
            $("#moperacion").modal("show");
            formData = {
                        grupo_id :$("#grupo_id").val(),
                        operacion: "carga_lista_grupo_vendedores"
                    };

            $.post("../admon/VendedorController.pv", formData, function (data) {
                $("#modal_mensaje").html(data);
            });
            
        });    
        
        
    });
</script>

       

<style type="text/css">
   
    
  .toggle.ios, .toggle-on.ios, .toggle-off.ios { border-radius: 20px; }
  .toggle.ios .toggle-handle { border-radius: 20px; }

     
  
    .panel .panel-defautl  hr {
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
        
        <c:when test="${requestScope.impuesto_id != null &&  requestScope.impuesto_id != 0}">
            <c:forEach var="ibean" items="${impuestos.getListaImpuesto(requestScope.impuesto_id)}" >
                <c:set var="id" value="${ibean.id}" />
                <c:set var="descripcion" value="${ibean.descripcion}" />
                <c:set var="impuesto" value="${ibean.impuesto}" />
                <c:set var="activo" value="${ibean.activo}" />
                <c:set var="impreso" value="${ibean.impreso}" />
                <c:set var="trasladado" value="${ibean.trasladado}" />
                <c:set var="retenido" value="${ibean.retenido}" />
                <c:set var="t_impuesto" value="1" />
                <c:if test="${trasladado == 1}" >
                    <c:set var="t_impuesto" value="1" />
                </c:if>
                
                <c:if test="${retenido == 1}" >
                    <c:set var="t_impuesto" value="0" />
                </c:if>
                
                <c:set var="orden" value="${ibean.orden}" />
                <c:set var="serie" value="${ibean.serie}" />
                <c:set var="estatus" value="${ibean.estatus}" />

            </c:forEach>
        </c:when>
        <c:otherwise>

            <c:set var="id" value="0" />
                <c:set var="descripcion" value="" />
                <c:set var="impuesto" value="0" />
                <c:set var="activo" value="1" />
                <c:set var="impreso" value="1" />
                <c:set var="trasladado" value="1" />
                <c:set var="retenido" value="0" />
                <c:set var="orden" value="0" />
                <c:set var="serie" value="A" />
                <c:set var="estatus" value="A" />
            <c:set var="t_impuesto" value="1" />
            
        </c:otherwise>
</c:choose>

<div class="row"><div class="col-xs-12">&nbsp;</div></div>
<form method="post" id="form_navega" name="form_navega">
    <input type="hidden" id="funcion_forma" name="funcion_forma" />
</form>


<form class="form-horizontal" method="post" id="form_impuesto" name="form_impuesto">

    <div id="operaciones"></div>
    
    <div class="row">
        <div class="col-xs-12">
            <div class="btn-group" role="group" aria-label="">
              <button type="submit" class="btn btn-default" id="guardar" name="guardar"><span class="fa fa-floppy-o"></span>&nbsp;Guardar</button>
              <button type="button" class="btn btn-default" id="nuevo" name="nuevo"><span class="fa fa fa-file-o"></span>&nbsp;Nuevo</button>
              <button type="button" class="btn btn-default" id="recargar" name="recargar"><span class="fa fa-refresh"></span>&nbsp;Recargar</button>
            </div>
        
                  
        </div>
        
    </div>
    
    
    <div class="row"><div class="col-xs-12">&nbsp;</div></div>
    <div class="panel panel-default">
        <div class="panel-heading">Catálogo de Impuestos</div>
        <div class="panel-body">
            
            <!-- contenido del panel -->

                <input type="hidden" id="id" name="id" value="${id}" />
                <input type="hidden" id="opcion" name="opcion" />
                <div class="row"><div class="col-xs-12">&nbsp;</div></div>
                <div class="row">
                    <div class="col-xs-12 col-lg-6">
                        <div class="form-group">
                            <label for="no_impuesto" class="col-md-12 col-lg-3">Núm.<label style="color: red">*</label></label>
                            <div class="col-md-12 col-lg-9">
                                <div class="controls form-inline">
                                    <input type="text" class="form-control" id="no_impuesto" name="no_impuesto" value="${id}" placeholder="" size="20" maxlength="20" disabled="disabled"/>

                                </div>
                            </div>
                        </div>
                    </div>
                </div> <!-- row -->
                <div class="row">
                    <div class="col-xs-12 col-lg-6">
                        <div class="form-group">
                                <label for="descripcion" class="col-md-12 col-lg-3">Descripcion<label style="color: red">*</label></label>
                                <div class="col-md-12 col-lg-9">
                                    <div class="controls form-inline">
                                        <input type="text" class="form-control" id="descripcion" name="descripcion" value="${descripcion}" placeholder="" size="40" maxlength="50" />

                                    </div>
                                </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-lg-6">
                        <div class="form-group">
                                <label for="impuesto" class="col-md-12 col-lg-3">Impuestos<label style="color: red">*</label></label>
                                <div class="col-md-12 col-lg-9">
                                    
                                    
                                    <div class="controls form-inline">
                                        <div class="input-group">
                                        <input type="text" class="form-control" id="impuesto" name="impuesto" value="${impuesto}" placeholder="" size="10" maxlength="50" />
                                        <span class="input-group-addon">%</span>
                                        </div>
                                    </div>
                                    
                                </div>
                        </div>
                    </div>                    
                </div>
                <div class="row">
                    <div class="col-xs-12 col-lg-6">
                        <div class="form-group">
                            <label for="activo" class="col-xs-12 col-sm-3">Activo<label style="color: red">*</label></label>
                            <div class="col-xs-12 col-sm-9">
                                <select name="activo" id="activo" class="selectpicker" data-live-seaerch="true">
                                    <option value="">Seleccione</option>
                                    <option value="1" <c:if test="${activo == 1}"> selected </c:if>  >SI</option>
                                    <option value="2" <c:if test="${activo == 0}"> selected </c:if>  >NO</option>
                                </select>

                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-lg-6">
                        <div class="form-group">
                            <label for="impreso" class="col-xs-12 col-sm-3">Impreso<label style="color: red">*</label></label>
                            <div class="col-xs-12 col-sm-9">
                                <select name="impreso" id="impreso" class="selectpicker" data-live-seaerch="true">
                                    <option value="">Seleccione</option>
                                    <option value="1" <c:if test="${impreso == 1}"> selected </c:if>>SI</option>
                                    <option value="2" <c:if test="${impreso == 0}"> selected </c:if>>NO</option>
                                </select>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-lg-6">
                        <div class="form-group">
                            <label for="t_impuesto" class="col-xs-12 col-sm-3">Tipo<label style="color: red">*</label></label>
                            <div class="col-xs-12 col-sm-9">
                                <select name="t_impuesto" id="t_impuesto" class="selectpicker" data-live-seaerch="true">
                                    <option value="">Seleccione</option>
                                    <option value="1" <c:if test="${t_impuesto == 1}"> selected </c:if>>Trasladado</option>
                                    <option value="2" <c:if test="${t_impuesto == 0}"> selected </c:if>>Retenido</option>
                                </select>

                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-lg-6">
                        <div class="form-group">
                                <label for="orden" class="col-md-12 col-lg-3">Orden<label style="color: red">*</label></label>
                                <div class="col-md-12 col-lg-9">


                                    <div class="controls form-inline">
                                        <input type="text" class="form-control" id="orden" name="orden" value="${orden}" placeholder="" size="10" maxlength="5" />
                                        
                                       
                                    </div>

                                </div>
                        </div>
                    </div>
                </div>
                
        
        </div> <!-- fin panel-body -->
    </div> <!-- panel panel-default -->    
    
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

  