<%-- 
    Document   : funcion
    Created on : 14-mar-2017, 17:07:23
    Author     : Rembao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="funcion" class="app.choya.sys.pv.configuracion.Funcion"  />
<!DOCTYPE html>

<script type="text/javascript">

    $(document).ready(function (e) {
        
        
        
        $("input[type=text]").focus(function () {
            $(this).select();
        });

        //$("input[type=text]").blur(function (e) {
            
        //    $(this).val($.trim($(this).val().toUpperCase()));
        //});
        
        
        $("#descripcion").blur(function (e) {
            
            $("#descripcion").val($.trim($("#descripcion").val().toUpperCase()));
        });
        
        $("#comentario").blur(function (e) {
            
            $("#comentario").val($.trim($("#comentario").val().toUpperCase()));
        });
        
        
        
        $("#form_funcion").validate({
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
                url: {
                    required: true,
                    minlength: 5,
                    maxlength: 150
                }
            },
            
            messages: {
                descripcion: {
                    required: "&nbsp;Por favor introduzca un nombre valido para la funcion",
                    minlength: "&nbsp;El número de caracteres no es valido, se espera de 3 a 50 caracteres"
                },
                url: {
                    required: "&nbsp;Por favor introduzca el URL de la funcion",
                    minlength: "&nbsp;El número de caracteres no es valido, se espera de 5 a 150 caracteres"
                }
                

            },
            submitHandler: function (form) {

                $("#modal_mensaje").html("<span class='fa fa-cog fa-spin fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">Espere un momento por favor...</label></div>");
                $("#moperacion").modal("show");

                var id = $("#id").val();
                var descripcion = $("#descripcion").val();
                var url = $("#url").val();
                var comentario = $("#comentario").val();
                var operacion = "";
                
                if(id == 0){
                    operacion = "guardar";
                }else{
                    operacion = "actualizar";
                }
                
                formData = {
                    id: id,
                    descripcion: descripcion,
                    url: url,
                    comentario: comentario,
                    operacion: operacion
                };

                $.post("./admon/FuncionController.pv", formData, function (data) {
                    $("#operaciones").html(data);

                    if ((parseInt($("#resultado").val()) != 0)) {
                        $("#modal_mensaje").html("<span style=\"color: green\" class=\"fa fa-check-circle fa-3x\"></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">El registro se ha guardado correctamente.</label></div>");
                        $("#id").val($("#resultado").val());
                        $("#no_funcion").val($("#resultado").val());
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
  margin: 18px 20px 18px 20px;
}

</style>

<div class="row"><div class="col-xs-12">&nbsp;</div></div>
<form method="post" id="form_navega" name="form_navega">
    <input type="hidden" id="funcion" name="funcion" />
</form>

<form class="form-horizontal" method="post" id="form_funcion" name="form_funcion">
    
    <c:choose>
        <c:when test="${requestScope.usuario_id != null &&  requestScope.usuario_id != 0}">
            <c:forEach var="fbean" items="${funcion.getFuncion(requestScope.funcion_id)}" >
                <c:set var="id" value="${fbean.id}" />
                <c:set var="menu_id" value="${fbean.menu_id}" />
                <c:set var="menu" value="${fbean.menu}" />
                <c:set var="descripcion" value="${fbean.descripcion}" />
                <c:set var="url" value="${fbean.url}" />
                <c:set var="comentario" value="${fbean.comentario}" />
                <c:set var="serie" value="${fbean.serie}" />
                <c:set var="estatus" value="${fbean.estatus}" />

            </c:forEach>
        </c:when>
        <c:otherwise>

            <c:set var="id" value="0" />
            <c:set var="menu_id" value="0" />
            <c:set var="menu" value="" />
            <c:set var="descripcion" value="" />
            <c:set var="url" value="" />
            <c:set var="comentario" value="" />
            <c:set var="serie" value="A" />
            <c:set var="estatus" value="A" />

        </c:otherwise>
    </c:choose>
                
                
    <div id="operaciones"></div>
    <div class="panel panel-default">
        <div class="panel-heading">Funciones</div>
        <div class="panel-body">

           
                <!-- contenido del panel -->

                <input type="hidden" id="id" name="id" value="${id}" />
                <div class="row"><div class="col-xs-12">&nbsp;</div></div>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="no_funcion" class="col-md-12 col-lg-2">Núm.<label style="color: red">*</label></label>
                            <div class="col-md-12 col-lg-10">
                                <div class="controls form-inline">
                                    <input type="text" class="form-control" id="no_funcion" name="no_funcion" value="${id}" placeholder="" size="20" maxlength="20" disabled="disabled"/>

                                </div>
                            </div>
                        </div>
                    </div>                

                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="descripcion" class="col-md-12 col-lg-2">Función<label style="color: red">*</label></label>
                            <div class="col-md-12 col-lg-10">
                                <div class="controls form-inline">
                                    <input type="text" class="form-control" id="descripcion" name="descripcion" value="${descripcion}" placeholder="" size="50" maxlength="200" />

                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="url" class="col-md-12 col-lg-2">URL<label style="color: red">*</label></label>
                            <div class="col-md-12 col-lg-10">
                                <div class="controls form-inline">
                                    <input type="text" class="form-control" id="url" name="url" value="${url}" placeholder="" size="50" maxlength="50" />

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row"><div class="col-xs-12"><hr style="width: 95%; color: #EEEEEE; height: 0.2px; background-color:#EEEEEE;"></div></div>                
                    
                    
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="comentario" class="col-md-12 col-lg-2">Comentario</label>
                            <div class="col-md-12 col-lg-10">
                                <div class="controls form-inline">
                                    <input type="text" class="form-control" id="comentario" name="comentario" value="${comentario}" placeholder="" size="50" maxlength="200" />

                                </div>
                            </div>
                        </div>
                    </div>
                </div> <!-- fin row principal -->
                
                
                <div class="row">
                    <div class="col-xs-12 text-right">
                        <button type="submit" id="guardar" class="btn btn-success"><span class="fa fa-floppy-o"></span> Guardar </button>
                        <button type="button" id="cancelar" class="btn btn-danger"><span class="fa fa-times"></span> Cancelar </button>
                        <button type="button" id="nuevo" class="btn btn-default"><span class="fa fa-file-o"></span> Nuevo </button>
                    </div>

                </div>
                                    
                
        </div>    <!-- panel-body -->
    </div> <!-- panel-heading -->
    
    
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
    
    
</form>