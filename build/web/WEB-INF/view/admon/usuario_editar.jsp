<%-- 
    Document   : usuario_editar
    Created on : 16-mar-2017, 22:52:39
    Author     : Rembao
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="usuario" class="app.choya.sys.pv.configuracion.Usuario"  />
<!DOCTYPE html>

<script type="text/javascript">

    $(document).ready(function (e) {
        
        
        
        $("input[type=text]").focus(function () {
            $(this).select();
        });

        $("input[type=text]").blur(function (e) {
            $(this).val($.trim($(this).val().toUpperCase()));
        });
        
        
        $("#form_usuario").validate({
            ignore: [],
            invalidHandler: function (event, validator) {
                $("#moperacion").modal("show");
                $("#modal_mensaje").html("<span style=\"color: red\" class=\"fa fa-exclamation-triangle fa-3x\"></span>&nbsp;<label for=\"modal_mensaje\" style=\"font-size: 16px; font-weight: bolder\">Los datos no son validos o falta información, por favor revise los campos del formulario.</label></div>");

            },
            rules: {
                nombre: {
                    required: true,
                    minlength: 3,
                    maxlength: 200
                },
                usuario: {
                    required: true,
                    minlength: 3,
                    maxlength: 50
                },
                email: {
                    required: true,
                    email: true
                },
                rol: {
                    required: true
                }
            },
            
            messages: {
                nombre: {
                    required: "&nbsp;Por favor introduzca un nombre valido para el usuario",
                    minlength: "&nbsp;El número de caracteres no es valido, se espera de 3 a 200 caracteres"
                },
                usuario: {
                    required: "&nbsp;Por favor introduzca el RFC del Proveedor",
                    minlength: "&nbsp;El número de caracteres no es valido, se espera de 8 a 50 caracteres"
                },
                email: "&nbsp;Por favor introduzca un correo electronico valido.",
                rol: "&nbsp;Por favor, Seleccione un rol para el usuario."
                

            },
            submitHandler: function (form) {

                $("#modal_mensaje").html("<span class='fa fa-cog fa-spin fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">Espere un momento por favor...</label></div>");
                $("#moperacion").modal("show");

                var id = $("#id").val();
                var nombre = $("#nombre").val();
                var usuario = $("#usuario").val();
                //var password = $("#password").val();
                var domicilio = $("#domicilio").val();
                var ciudad = $("#ciudad").val();
                var email = $("#email").val();
                var telefono = $("#telefono").val();
                var celular = $("#celular").val();
                var id_rol = $("#id_rol").val();
                var id_vendedor = $("#id_vendedor").val();
                var comentario = $("#comentario").val();
                var operacion = "";
                
                if(id == 0){
                    operacion = "guardar";
                }else{
                    operacion = "actualizar";
                }
                
                formData = {
                    id: id,
                    nombre: nombre,
                    usuario: usuario,
                    //password: password,
                    domicilio: domicilio,
                    ciudad: ciudad,
                    email: email,
                    telefono: telefono,
                    celular: celular,
                    id_rol : id_rol,
                    id_vendedor : id_vendedor,
                    comentario: comentario,
                    operacion: operacion
                };

                $.post("../admon/UsuarioController.pv", formData, function (data) {
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
        
        
        $("#nuevo").click(function(e){
            $("#opcion").val("nuevo");
            $("#mensaje_comando").html("<span class='fa fa-exclamation-triangle fa-3x'></span>&nbsp;<label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">¿Desea agregar un nuevo usuarios?\n\nLos cambios que no ha guardado no tendran efecto en el registro.</label></div>");
            $("#mcomando").modal("show");
            
        });
        
        $("#recargar").click(function(e){
            $("#funcion_forma").val("usuarios_lista");
            $('#form_navega').attr('action', "../pv/Hub");
            $("#form_navega").submit();
        });
        
        
        $("#aceptar_comando").click(function(e){
            if($("#opcion").val() == "nuevo"){
                $("#funcion_forma").val("usuarios");
                $('#form_navega').attr('action', "../pv/Hub");
                $("#form_navega").submit();
            }
        });
        
        
        
        $("#btn_rol").click(function(){
            
            $("#modal_mensaje").html("<span class='fa fa-cog fa-spin fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">Espere un momento por favor...</label></div>");
            $("#moperacion").modal("show");
            formData = {
                        id_rol: $("#id_rol").val(),
                        operacion: "carga_lista_rol"
                    };

            $.post("../admon/UsuarioController.pv", formData, function (data) {
                $("#modal_mensaje").html(data);
            });
            
            
        });
        
        $("#btn_vendedor").click(function(){
            
            $("#modal_mensaje").html("<span class='fa fa-cog fa-spin fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">Espere un momento por favor...</label></div>");
            $("#moperacion").modal("show");
            formData = {
                        id_rol: $("#id_vendedor").val(),
                        operacion: "carga_lista_vendedores"
                    };

            $.post("../admon/UsuarioController.pv", formData, function (data) {
                $("#modal_mensaje").html(data);
            });
            
            
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

<c:choose>
        <c:when test="${requestScope.usuario_id != null &&  requestScope.usuario_id != 0}">
            <c:forEach var="ubean" items="${usuario.getUsuarioLista(requestScope.usuario_id)}" >
                <c:set var="id" value="${ubean.id}" />
                <c:set var="nombre" value="${ubean.nombre}" />
                <c:set var="ciudad" value="${ubean.ciudad}" />
                <c:set var="telefono" value="${ubean.telefono}" />
                <c:set var="celular" value="${ubean.celular}" />
                <c:set var="domicilio" value="${ubean.domicilio}" />
                
                <c:set var="activo" value="${ubean.activo}" />
                <c:set var="usuario" value="${ubean.usuario}" />
                <c:set var="password" value="${ubean.password}" />
                <c:set var="rol" value="${ubean.rol}" />
                <c:set var="id_rol" value="${ubean.id_rol}" />
                <c:set var="vendedor" value="${ubean.vendedor}" />
                <c:set var="id_vendedor" value="${ubean.id_vendedor}" />
                <c:set var="email" value="${ubean.email}" />
                <c:set var="comentario" value="${ubean.comentario}" />
                
                <c:set var="serie" value="${ubean.serie}" />
                <c:set var="estatus" value="${ubean.estatus}" />

            </c:forEach>
        </c:when>
        <c:otherwise>

            <c:set var="id" value="0" />
            <c:set var="nombre" value="" />
            <c:set var="ciudad" value="" />
            <c:set var="telefono" value="" />
            <c:set var="celular" value="" />
            <c:set var="activo" value="" />
            <c:set var="usuario" value="" />
            <c:set var="password" value="" />
            <c:set var="id_rol" value="" />
            <c:set var="id_vendedor" value="" />
            <c:set var="domicilio" value="" />
            <c:set var="email" value="" />
            <c:set var="comentario" value="" />
            <c:set var="serie" value="A" />
            <c:set var="estatus" value="A" />
            <c:set var="rol" value="" />
            <c:set var="vendedor" value="" />
            <c:set var="sucursal" value="" />
            
            
        </c:otherwise>
</c:choose>

<div class="row"><div class="col-xs-12">&nbsp;</div></div>
<form method="post" id="form_navega" name="form_navega">
    <input type="hidden" id="funcion_forma" name="funcion_forma" />
</form>


<form class="form-horizontal" method="post" id="form_usuario" name="form_usuario">

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
        <div class="panel-heading">Usuario</div>
        <div class="panel-body">

           
                <!-- contenido del panel -->

                <input type="hidden" id="id" name="id" value="${id}" />
                <input type="hidden" id="opcion" name="opcion" />
                <div class="row"><div class="col-xs-12">&nbsp;</div></div>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="no_usuario" class="col-md-12 col-lg-2">Núm.<label style="color: red">*</label></label>
                            <div class="col-md-12 col-lg-10">
                                <div class="controls form-inline">
                                    <input type="text" class="form-control" id="no_usuario" name="no_usuario" value="${id}" placeholder="" size="20" maxlength="20" disabled="disabled"/>

                                </div>
                            </div>
                        </div>
                    </div>                

                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="nombre" class="col-md-12 col-lg-2">Nombre<label style="color: red">*</label></label>
                            <div class="col-md-12 col-lg-10">
                                <div class="controls form-inline">
                                    <input type="text" class="form-control" id="nombre" name="nombre" value="${nombre}" placeholder="" size="50" maxlength="200" />

                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="row"><div class="col-xs-12"><hr style="width: 95%; color: #EEEEEE; height: 0.2px; background-color:#EEEEEE;"></div></div>                
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="usuario" class="col-md-12 col-lg-2">Usuario<label style="color: red">*</label></label>
                            <div class="col-md-12 col-lg-10">
                                <div class="controls form-inline">
                                    <input type="text" class="form-control" id="usuario" name="usuario" value="${usuario}" placeholder="" size="30" maxlength="50" disabled="disabled"/>

                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="row"><div class="col-xs-12"><hr style="width: 95%; color: #EEEEEE; height: 0.2px; background-color:#EEEEEE;"></div></div>                                
                                    
                                    
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="domicilio" class="col-md-12 col-lg-2">Domicilio</label>
                            <div class="col-md-12 col-lg-10">
                                <div class="controls form-inline">
                                    <input type="text" class="form-control" id="domicilio" name="domicilio" value="${domicilio}" placeholder="" size="50" maxlength="200" />

                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="ciudad" class="col-md-12 col-lg-2">Ciudad</label>
                            <div class="col-md-12 col-lg-10">
                                <div class="controls form-inline">
                                    <input type="text" class="form-control" id="ciudad" name="ciudad" value="${ciudad}" placeholder="" size="50" maxlength="100" />

                                </div>
                            </div>
                        </div>
                    </div>                
                                    
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="correo" class="col-md-12 col-lg-2">Correo<label style="color: red">*</label></label>
                            <div class="col-md-12 col-lg-10">
                                <div class="controls form-inline">
                                    <input type="text" class="form-control" id="email" name="email" value="${email}" placeholder="" size="50" maxlength="150" />

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="telefono" class="col-md-12 col-lg-2">teléfono</label>
                            <div class="col-md-12 col-lg-10">
                                <div class="controls form-inline">
                                    <input type="text" class="form-control" id="telefono" name="telefono" value="${telefono}" placeholder="" size="30" maxlength="45" />

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="celualar" class="col-md-12 col-lg-2">Celular</label>
                            <div class="col-md-12 col-lg-10">
                                <div class="controls form-inline">
                                    <input type="text" class="form-control" id="celular" name="celular" value="${celular}" placeholder="" size="30" maxlength="45" />

                                </div>
                            </div>
                        </div>
                    </div>
                                    
                    <div class="row"><div class="col-xs-12"><hr style="width: 95%; color: #EEEEEE; height: 0.2px; background-color:#EEEEEE;"></div></div>
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="rol" class="col-md-12 col-lg-2">Rol<label style="color: red">*</label></label>
                            <div class="col-md-12 col-lg-10">
                                <div class="controls form-inline">
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="rol" name="rol" value="${rol}" placeholder="" size="30" maxlength="50" />
                                        <span class="input-group-btn">
                                            <button class="btn btn-default"  id="btn_rol" type="button">Ir</button>
                                        </span>
                                        <input type="hidden" class="form-control" id="id_rol" name="id_rol" value="${id_rol}" />
                                    </div> <!-- fin input group -->
                                </div>
                            </div>
                        </div>
                    </div>                
                    
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="vendedor" class="col-md-12 col-lg-2">Vendedor</label>
                            <div class="col-md-12 col-lg-10">
                                <div class="controls form-inline">
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="vendedor" name="vendedor" value="${vendedor}" placeholder="" size="30" maxlength="50" />
                                        <span class="input-group-btn">
                                            <button class="btn btn-default" id="btn_vendedor" type="button">Ir</button>
                                        </span>
                                        <input type="hidden" class="form-control" id="id_vendedor" name="id_vendedor" value="${id_vendedor}" />
                                    </div> <!-- fin input group -->
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
                </div>
                
                <!--<div class="row">
                    <div class="col-xs-12 text-right">
                        <button type="submit" id="guardar" class="btn btn-success"><span class="fa fa-floppy-o"></span> Guardar </button>
                        <button type="button" id="cancelar" class="btn btn-danger"><span class="fa fa-times"></span> Cancelar </button>
                        <button type="button" id="nuevo" class="btn btn-default"><span class="fa fa-file-o"></span> Nuevo </button>
                    </div>

                </div> -->



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
