<%-- 
    Document   : menu
    Created on : 14-mar-2017, 19:57:50
    Author     : Rembao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="menu" class="app.choya.sys.pv.configuracion.Menu"  />
<!DOCTYPE html>

<script type="text/javascript">

    $(document).ready(function (e) {
        
        
        
        $("input[type=text]").focus(function () {
            $(this).select();
        });

        $("input[type=text]").blur(function (e) {
            $(this).val($.trim($(this).val().toUpperCase()));
        });
        
        
        
        $("#form_menu").validate({
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
                }
            },
            
            messages: {
                descripcion: {
                    required: "&nbsp;Por favor introduzca un nombre valido para el menú",
                    minlength: "&nbsp;El número de caracteres no es valido, se espera de 3 a 50 caracteres"
                }
            },
            submitHandler: function (form) {
                
                
                $("#modal_mensaje").html("<span class='fa fa-cog fa-spin fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">Espere un momento por favor...</label></div>");
                $("#moperacion").modal("show");

                var id = $("#id").val();
                var descripcion = $("#descripcion").val();
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
                    comentario: comentario,
                    operacion: operacion
                };

                $.post("./admon/MenuController.pv", formData, function (data) {
                    $("#operaciones").html(data);

                    if ((parseInt($("#resultado").val()) != 0)) {
                        
                        if(operacion == "guardar"){
                            $("#modal_mensaje").html("<span style=\"color: green\" class=\"fa fa-check-circle fa-3x\"></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">El registro se ha guardado correctamente.</label></div>");
                            $("#id").val($("#resultado").val());
                            $("#no_menu").val($("#resultado").val());
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
        
        //$("#nuevo").click(function(e){
        //    $("#nuevo_mensaje").html("<span class='fa fa-exclamation-triangle fa-3x'></span>&nbsp;<label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">¿Desea crear un nuevo menú?\n\nLos cambios que no ha guardado no tendran efecto en el registro.</label></div>");
        //    $("#mnuevo").modal("show");
            
            
        //});
        
        $("#nuevo").click(function(e){
            $("#mensaje_comando").html("<span class='fa fa-exclamation-triangle fa-3x'></span>&nbsp;<label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">¿Desea crear un nuevo menú?\n\nLos cambios que no ha guardado no tendran efecto en el registro.</label></div>");
            $("#mcomando").modal("show");
        });
        
        $("#recargar").click(function(e){
            $("#funcion").val("menus_lista");
            $('#form_navega').attr('action', "./Hub");
            $("#form_navega").submit();
        });
        
        
        $("#aceptar_comando").click(function(e){
            $("#funcion").val("menus");
            $('#form_navega').attr('action', "./Hub");
            $("#form_navega").submit();
        });
        
       
        
        
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


<form method="post" id="form_navega" name="form_navega">
    <input type="hidden" id="funcion" name="funcion" />
</form>
        
<form class="form-horizontal" method="post" id="form_menu" name="form_menu">
    
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
    <c:choose>
        <c:when test="${requestScope.menu_id != null &&  requestScope.menu_id != 0}">
            <c:forEach var="mbean" items="${menu.getListaMenu(requestScope.menu_id)}" >
                <c:set var="id" value="${mbean.id}" />
                <c:set var="descripcion" value="${mbean.descripcion}" />
                <c:set var="comentario" value="${mbean.comentario}" />
                

            </c:forEach>
        </c:when>
        <c:otherwise>

            <c:set var="id" value="0" />
            <c:set var="descripcion" value="" />
            <c:set var="comentario" value="" />

        </c:otherwise>
    </c:choose>
                
                
    
    <div class="panel panel-default">
        <div class="panel-heading">Menus</div>
        <div class="panel-body">
            <!-- contenido del panel -->

                <input type="hidden" id="id" name="id" value="${id}" />
                <div class="row"><div class="col-xs-12">&nbsp;</div></div>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="no_menu" class="col-md-12 col-lg-2">Núm.<label style="color: red">*</label></label>
                            <div class="col-md-12 col-lg-10">
                                <div class="controls form-inline">
                                    <input type="text" class="form-control" id="no_menu" name="no_menu" value="${id}" placeholder="" size="20" maxlength="20" disabled="disabled"/>

                                </div>
                            </div>
                        </div>
                    </div>                

                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="descripcion" class="col-md-12 col-lg-2">Menú<label style="color: red">*</label></label>
                            <div class="col-md-12 col-lg-10">
                                <div class="controls form-inline">
                                    <input type="text" class="form-control" id="descripcion" name="descripcion" value="${descripcion}" placeholder="" size="50" maxlength="200" />

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
                                    
                    <!-- <div class="row">
                    <div class="col-xs-12 text-right">
                        <button type="submit" id="guardar" class="btn btn-success"><span class="fa fa-floppy-o"></span> Guardar </button>
                        <button type="button" id="cancelar" class="btn btn-danger"><span class="fa fa-times"></span> Cancelar </button>
                        <button type="button" id="nuevo" class="btn btn-default"><span class="fa fa-file-o"></span> Nuevo </button>
                    </div>

                </div> -->               
                                    
                </div> <!-- fin del row principal -->                    
            
            
        </div> <!-- panel-body -->
        
    </div> <!-- panel -->
    
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