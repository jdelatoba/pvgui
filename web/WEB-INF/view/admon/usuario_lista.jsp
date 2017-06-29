<%-- 
    Document   : usuario_lista
    Created on : 14-mar-2017, 9:37:29
    Author     : Rembao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<script type="text/javascript">
    
    
    $(document).ready(function () {

        var table = $('#tabla_usuarios').DataTable( {
        "processing": true,
            //"serverSide": true,
            "ajax": {
               url:  "../admon/UsuarioListaJSON.pv",
               type: "GET"
            },
            "scrollY":        400,
            "scrollCollapse": true,
            "language": {
                            "lengthMenu": "Mostrar _MENU_ registros por página",
                            "zeroRecords": "Ups!! No he encontrado registros.. tal vez para la proxima",
                            "info": "Mostrando página _PAGE_ de _PAGES_",
                            "infoEmpty": "Ups!! No he encontrado registros..",
                            "infoFiltered": "(filtrado de un total de _MAX_ registros)",
                            "search": "Buscar",
                            "loadingRecords": "Espere un momento por favor - Cargando datos...",
                            "paginate": {
                                "first": "Primer",
                                "last": "Ultimo",
                                "next": "Siguiente",
                                "previous": "Anterior"
                            }
            },
            
        "columns": [
            {
                "className":      'id-control',
                "data":           "id"
            },
            
            { "data": "nombre" },
            {
                "className":      'usuario-control',
                "data":           "usuario"
            },
            
            { "data": "rol" },
            { "data": "vendedor" }
                
        ],
        "order": [[1, 'asc']]
        
    } );
    
  
    $('#tabla_usuarios tbody').on( 'click', 'tr', function () {
        
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }else {
            var $item = $(this).closest("tr")   // busca el row mas cercano <tr> 
                       .find(".id-control")     // busca la class = "id-control"
                       .text(); // saca el valor del texto de la columna
            var $usuario    = $(this).closest("tr")   // busca el row mas cercano <tr> 
                       .find(".usuario-control")     // busca la class = "id-control"
                       .text(); // saca el valor del texto de la columna   
            $("#id").val($item); //guardar el id del registro para la operacion
            $("#id_usuario").val($item); //guardar el id del registro para la operacion
            $("#usuario").val($usuario);
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });
    
    
    /*$('#tabla_usuarios tbody').on( 'click', '#editar', function () {
        var $item = $(this).closest("tr")   // busca el row mas cercano <tr> 
                       .find(".id-control")     // busca la class = "id-control"
                       .text(); // saca el valor del texto de la columna
               
        $("#id").val($item); //guardar el id del registro para la operacion
        $("#operacion").val("editar_usuario");
        $("#modal_mensaje").html("<span class='fa fa-question-circle fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">¿Desea editar el registro seleccionado?</label></div>");
        $("#moperacion").modal("show");
        
        
    } );*/
    
    
        /*$('#tabla_usuarios tbody').on( 'click', '#eliminar', function () {
            var $item = $(this).closest("tr")   // busca el row mas cercano <tr> 
                           .find(".id-control")     // busca la class = "id-control"
                           .text(); // saca el valor del texto de la columna
            //alert($item);

        } );
    
        $("#aceptar").click(function(e){
            $("#moperacion").modal("hide");


        });*/
        
        $("#editar").click(function(e){
           if($("#id").val() != ""){ 
                $("#operacion").val("editar");
                $("#modal_mensaje_comando").html("<span class='fa fa-question-circle fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">¿Desea editar el registro seleccionado?</label></div>");
                $("#mcomando").modal("show");
            }else{
                $("#modal_mensaje_comando").html("<span class='fa fa-info-circle fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">Debe seleccionar al menos un registro de la tabla.</label></div>");
                $("#mcomando").modal("show");
                
            }    
        
        });
        
        $("#eliminar").click(function(e){
           if($("#id").val() != ""){ 
                $("#operacion").val("eliminar");
                $("#modal_mensaje_comando").html("<span class='fa fa-question-circle fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">¿Desea eliminar el registro seleccionado?</label></div>");
                $("#mcomando").modal("show");
            }else{
                $("#modal_mensaje_comando").html("<span class='fa fa-info-circle fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">Debe seleccionar al menos un registro de la tabla.</label></div>");
                $("#mcomando").modal("show");
                
            }
        
        });
        
        $("#agregar").click(function(e){
           $("#funcion_forma").val("usuarios");
           $('#form_tabla_usuario').attr('action', "../pv/Hub");
           $("#form_tabla_usuario").submit();
        
        });
    
    
        $("#aceptar").click(function(e){
             $("#moperacion").modal("hide");
        });
        
        $("#recargar").click(function(e){
             table.ajax.reload();
        });
        
        
        $("#aceptar_comando").click(function(e){
             $("#mcomando").modal("hide");
            
             
            if($("#operacion").val() == "editar"){
               
                $("#funcion_forma").val("editar_usuario");
                $('#form_tabla_usuario').attr('action', "../pv/Hub");
                $("#form_tabla_usuario").submit();
            }
                
            if($("#operacion").val() == "eliminar"){
                $("#operacion").val("cerrar_modal");
                var id = $("#id").val();
                formData = {
                    id : $("#id").val(),
                    operacion : "eliminar_usuario"
                    
                };
                
                $.post("../admon/UsuarioController.pv", formData, function (data) {
                    $("#operaciones").html(data);
                    if ((parseInt($("#resultado").val()) != 0)) {
                        
                        $("#moperacion").modal("show"); 
                        $("#modal_mensaje").html("<span style=\"color: green\" class=\"fa fa-check-circle fa-3x\"></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">El registro se ha eliminado.</label></div>");
                        table.ajax.reload();
                        
                    } else {
                        $("#moperacion").modal("show");
                        $("#modal_mensaje").html("<span style=\"color: red\" class=\"fa fa-exclamation-triangle fa-3x\"></span>&nbsp;<label for=\"modal_mensaje\" style=\"font-size: 16px; font-weight: bolder\">El sistema ha lanzado el siguiente error: Codigo Error: "+$("#codigo_error").val() +" "+ $("#mensaje").val() + ".</label></div>");

                    }
                    
                }); //fin $.post
                
                
            }
        
        });
        
        $("#cambiar_password").click(function(){
            if($("#id").val() != ""){ 
                $("#operacion").val("cambiar_password");
                $("#password").val("");
                $("#repassword").val("");
                //$("#modal_mensaje_comando").html("<span class='fa fa-question-circle fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">¿Desea eliminar el registro seleccionado?</label></div>");
                $("#mcambiopass").modal("show");
            }else{
                $("#modal_mensaje_comando").html("<span class='fa fa-info-circle fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">Debe seleccionar al menos un registro de la tabla.</label></div>");
                $("#mcomando").modal("show");
                
            }
            
        });
        
        
        
        
        $("#form_password").validate({
            ignore: [],
            invalidHandler: function (event, validator) {
                //$("#moperacion").modal("show");
                //$("#modal_mensaje").html("<span style=\"color: red\" class=\"fa fa-exclamation-triangle fa-3x\"></span>&nbsp;<label for=\"modal_mensaje\" style=\"font-size: 16px; font-weight: bolder\">Los datos no son validos o falta información, por favor revise los campos del formulario.</label></div>");

            },
            rules: {
                password:{
                    required: true,
                    minlength: 8,
                    maxlength: 20
                },
                repassword:{
                    equalTo: "#password"
                }
            },
            
            messages: {
                password: "&nbsp;Por favor introduzca un password valido",
                repassword: "&nbsp;Los password no coinciden",
            },
            submitHandler: function (form) {
                $("#mcambiopass").modal("hide");
                $("#modal_mensaje_pass").html("<span class='fa fa-cog fa-spin fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">Espere un momento por favor...</label></div>");
                $("#moperacion_pass").modal("show");

                var id = $("#id").val();
                var usuario = $("#usuario").val();
                var password = $("#password").val();
                var operacion = "";
                
                
                formData = {
                    id: id,
                    usuario: usuario,
                    password: password,
                    operacion: "actualizar_password"
                };

                $.post("../admon/UsuarioController.pv", formData, function (data) {
                    $("#operaciones_pass").html(data);
                    
                    if ((parseInt($("#resultado").val()) != 0)) {
                            $("#modal_mensaje_pass").html("<span style=\"color: green\" class=\"fa fa-check-circle fa-3x\"></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">El registro se ha actualizado correctamente.</label></div>");
                    } else {
                        $("#modal_mensaje_pass").html("<span style=\"color: red\" class=\"fa fa-exclamation-triangle fa-3x\"></span>&nbsp;<label for=\"modal_mensaje\" style=\"font-size: 16px; font-weight: bolder\">El sistema ha lanzado el siguiente error: Codigo Error: "+$("#codigo_error").val() +" "+ $("#mensaje").val() + ".</label></div>");

                    }
                    
                    
                });
                //asdasd$(form).submit();*/
            }

        });  // fin $("#form_proveedor").validate
        
        
        
    
 });   

</script>
<form class="form-horizontal" method="post" id="form_tabla_usuario" name="form_tabla_usuario">
    <div class="row">
        <div class="col-xs-12">&nbsp;</div>
    </div>
    
    <div class="row">
        
        <div class="col-xs-12 col-lg-9" >
            <div class="btn-group" role="group" aria-label="">
              <button type="button" class="btn btn-default" id="agregar" name="agregar"><span class="fa fa-plus-circle"></span>&nbsp;Agregar</button>
              <button type="button" class="btn btn-default" id="editar" name="editar"><span class="fa fa fa-pencil"></span>&nbsp;Editar</button>
              <button type="button" class="btn btn-default" id="cambiar_password" name="editar"><span class="fa fa-key"></span>&nbsp;Cambiar Contraseña</button>
              <button type="button" class="btn btn-default" id="eliminar" name="eliminar"><span class="fa fa fa-trash"></span>&nbsp;Eliminar</button>
              <button type="button" class="btn btn-default" id="recargar" name="recargar"><span class="fa fa-refresh"></span>&nbsp;Recargar</button>
            </div>
            
        </div>
        <div class="col-xs-12 col-lg-3" style="text-align: right">
            <h4>Catálogo de Usuarios</h4>
        </div>
    </div>   
<div class="row">
    <div class="col-xs-12">&nbsp;</div>
</div>

<div class="row">
    <div class="col-xs-12">
        <table id="tabla_usuarios" class="display" cellspacing="0" width="100%">
        <thead>
             <thead>
                <th>No.</th>
                <th>Nombre</th>
                <th>Usuario</th>
                <th>Rol</th>
                <th>Vendedor</th>
                
            </tr>
        </thead>
        <tfoot>
            <tr>
                <th>No.</th>
                <th>Nombre</th>
                <th>Usuario</th>
                <th>Rol</th>
                <th>Vendedor</th>
               
            </tr>
        </tfoot>
        
</table>
    </div>
    
</div>



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
                    <button type="button" class="btn btn-default" id="aceptar" name="aceptar">Aceptar</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                    
                  </div>
            </div>
        </div>
    </div>
    
    
    
    <div class="modal fade" id="mcomando" tabindex="-1" role="dialog" aria-labelledby="modal-eliminar">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Punto de Venta</h4>
                </div>
                <div class="modal-body">
                    <div class="row">

                        <div  class="col-xs-12">
                            <div id="modal_mensaje_comando">
                                <label  style="font-size: 18px; font-weight: bolder"><span class="fa fa-spinner fa-pulse fa-3x fa-fw"></span>&nbsp;  Espere un momento por favor.</label>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" id="aceptar_comando" name="aceptar_comando">Aceptar</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                    
                  </div>
            </div>
        </div>
    </div>
    
    
    
    

<input type="hidden" id="operacion" name="operacion" />
<input type="hidden" id="funcion_forma" name="funcion_forma" />
<input type="hidden" id="id" name="id" />

<div id="operaciones"></div>
</form>
<form class="form-horizontal" method="post" id="form_password" name="form_password">
    <div id="operaciones_pass"></div>
    <input type="hidden" id="id_usuario" name="id_usuario" />
    <div class="modal fade" id="mcambiopass" tabindex="-1" role="dialog" aria-labelledby="modal-mcambiopass">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Punto de Venta</h4>
                </div>
                <div class="modal-body">
                    
                    <div class="row"><div class="col-xs-12">
                        <h3>Cambio de Contraseña</h3>
                    </div></div>
                    
                    <div class="row"><div class="col-xs-12">&nbsp;</div></div>
                    <div class="row">

                        <div class="col-xs-12">
                        <div class="form-group">
                            <label for="usuario" class="col-md-12 col-lg-3">Usuario<label style="color: red">*</label></label>
                            <div class="col-md-12 col-lg-9">
                                <div class="controls form-inline">
                                    <input type="text" class="form-control" id="usuario" name="usuario" value="" placeholder="" size="30" maxlength="50" disabled="disabled" />

                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="mensaje_password">
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="password" class="col-md-12 col-lg-3">Password<label style="color: red">*</label></label>
                            <div class="col-md-12 col-lg-9">
                                <div class="controls form-inline">
                                    <input type="password" class="form-control" id="password" name="password" value="${password}" placeholder="" size="30" maxlength="50" />

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="repassword" class="col-md-12 col-lg-3">Confirmar<label style="color: red">*</label></label>
                            <div class="col-md-12 col-lg-9">
                                <div class="controls form-inline">
                                    <input type="password" class="form-control" id="repassword" name="repassword" value="${repassword}" placeholder="" size="30" maxlength="50" />

                                </div>
                            </div>
                        </div>
                    </div>
                    </div> <!-- fin mensaje password -->                
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-default" id="aceptar_password" name="aceptar_password">Aceptar</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                    
                  </div>
            </div>
        </div>
    </div>
                                    
     <div class="modal fade" id="moperacion_pass" tabindex="-1" role="dialog" aria-labelledby="modal-operaciones">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Punto de Venta</h4>
                </div>
                <div class="modal-body">
                    <div class="row">

                        <div  class="col-xs-12">
                            <div id="modal_mensaje_pass">
                                <label  style="font-size: 18px; font-weight: bolder"><span class="fa fa-spinner fa-pulse fa-3x fa-fw"></span>&nbsp;  Espere un momento por favor.</label>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Aceptar</button>
                   
                    
                  </div>
            </div>
        </div>
    </div>                               
                                    
    
</form>