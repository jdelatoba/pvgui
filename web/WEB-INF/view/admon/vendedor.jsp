<%-- 
    Document   : vendedor
    Created on : 12-mar-2017, 12:30:21
    Author     : Rembao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="vendedor" class="app.choya.sys.pv.configuracion.Vendedor"  />
<!DOCTYPE html>

<script type="text/javascript">

    $(document).ready(function (e) {
        
        
        $("input[type=text]").focus(function () {
            $(this).select();
        });

        $("input[type=text]").blur(function (e) {
            $(this).val($.trim($(this).val().toUpperCase()));
        });
        
        
        
        $("#comision").numeric();
        
        $("#comision").blur(function (e) {
            if ($("#comision").val() == "") {
                $("#comision").val("0.00");
            } else {

                $("#comision").formatCurrency();
                var comision = $("input[id='comision']").val();
                $("input[id='comision']").val(comision.replace("$", ""));
            }
        });
        
        $("#form_vendedor").validate({
            ignore: [],
            invalidHandler: function (event, validator) {
                $("#moperacion").modal("show");
                $("#modal_mensaje").html("<span style=\"color: red\" class=\"fa fa-exclamation-triangle fa-3x\"></span>&nbsp;<label for=\"modal_mensaje\" style=\"font-size: 16px; font-weight: bolder\">Los datos no son validos o falta información, por favor revise los campos del formulario.</label></div>");

            },
             rules: {
                vendedor: {
                    required: true,
                    minlength: 3,
                    maxlength: 50
                },
                nombre: {
                    required: true,
                    minlength: 3,
                    maxlength: 150
                },
                correo: {
                    required: true,
                    email: true
                },
                puesto: {
                    required: true,
                    minlength: 3,
                    maxlength: 100
                },
                grupo: {
                    required: true
                },
                comision:{
                    number : true
                }
            },
            
            messages: {
                vendedor: {
                    required: "&nbsp;Por favor introduzca un alias valido para el vendedor",
                    minlength: "&nbsp;El número de caracteres no es valido, se espera de 3 a 50 caracteres"
                },
                nombre: {
                    required: "&nbsp;Por favor introduzca un nombre valido para el vendedor",
                    minlength: "&nbsp;El número de caracteres no es valido, se espera de 3 a 150 caracteres"
                },
                puesto: {
                    required: "&nbsp;Por favor introduzca puesto para el vendedor",
                    minlength: "&nbsp;El número de caracteres no es valido, se espera de 3 a 100 caracteres"
                },
                correo: {
                    required: "&nbsp;Por favor introduzca un correo electronico valido.",
                    email:"&nbsp;Por favor introduzca un correo electronico valido."
                },
                grupo: {
                    required: "&nbsp;Por favor introduzca grupo para el vendedor"
                },
                comision: "&nbsp;Por favor introduzca un número valido"
            },
            submitHandler: function (form) {

                $("#modal_mensaje").html("<span class='fa fa-cog fa-spin fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">Espere un momento por favor...</label></div>");
                $("#moperacion").modal("show");
                
                var id = $("#id").val();
                var vendedor = $("#vendedor").val();
                var nombre = $("#nombre").val();
                var domicilio = $("#domicilio").val();
                var telefono = $("#telefono").val();
                var celular = $("#celular").val();
                var email = $("#correo").val();
                var puesto = $("#puesto").val();
                var comision = $("#comision").val();
                var grupo = $("#grupo").val();
                var grupo_id = $("#grupo_id").val();
                var operacion = "";
                
                
                if(id == 0){
                    operacion = "guardar";
                }else{
                    operacion = "actualizar";
                }
                
                formData = {
                    id : id,
                    vendedor : vendedor,
                    nombre : nombre,
                    domicilio : domicilio,
                    telefono : telefono,
                    celular : celular,
                    email : email,
                    puesto : puesto,
                    comision : comision,
                    grupo_id : grupo_id,
                    operacion : operacion
                };
                
                $.post("../admon/VendedorController.pv", formData, function (data) {
                    
                    $("#operaciones").html(data);
                   
                    if ((parseInt($("#resultado").val()) != 0)) {
                        
                        if(operacion == "guardar"){
                            $("#modal_mensaje").html("<span style=\"color: green\" class=\"fa fa-check-circle fa-3x\"></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">El registro se ha guardado correctamente.</label></div>");
                            $("#id").val($("#resultado").val());
                            $("#no_vendedor").val($("#resultado").val());
                        }else{
                            
                            $("#modal_mensaje").html("<span style=\"color: green\" class=\"fa fa-check-circle fa-3x\"></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">El registro se ha actualizado correctamente.</label></div>");
                        }
                    } else {
                        $("#modal_mensaje").html("<span style=\"color: red\" class=\"fa fa-exclamation-triangle fa-3x\"></span>&nbsp;<label for=\"modal_mensaje\" style=\"font-size: 16px; font-weight: bolder\">El sistema ha lanzado el siguiente error: Codigo Error: "+$("#codigo_error").val() +" "+ $("#mensaje").val() + ".</label></div>");

                    }
                    
                    
                });
                
                
            }

        });  // fin $("#form_vendedor").validate
        
        
        $("#nuevo").click(function(e){
            $("#mensaje_comando").html("<span class='fa fa-exclamation-triangle fa-3x'></span>&nbsp;<label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">¿Desea agregar un nuevo vendedor?\n\nLos cambios que no ha guardado no tendran efecto en el registro.</label></div>");
            $("#mcomando").modal("show");
        });
        
        $("#recargar").click(function(e){
            $("#funcion_forma").val("vendedores_lista");
            $('#form_navega').attr('action', "../pv/Hub");
            $("#form_navega").submit();
        });
        
        
        $("#aceptar_comando").click(function(e){
            $("#funcion_forma").val("vendedores");
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
    <c:when test="${requestScope.vendedor_id != null &&  requestScope.vendedor_id != 0}">
        <c:forEach var="vbean" items="${vendedor.getListaVendedorById(requestScope.vendedor_id)}" >
            <c:set var="id" value="${vbean.id}" />
            <c:set var="vendedor" value="${vbean.vendedor}" />
            <c:set var="domicilio" value="${vbean.domicilio}" />
            <c:set var="nombre" value="${vbean.nombre}" />
            <c:set var="ciudad" value="${vbean.ciudad}" />
            <c:set var="telefono" value="${vbean.telefono}" />
            <c:set var="celular" value="${vbean.celular}" />
            <c:set var="email" value="${vbean.email}" />
            <c:set var="puesto" value="${vbean.puesto}" />
            <c:set var="comision" value="${vbean.comision}" />
            <c:set var="grupo_id" value="${vbean.grupo_id}" />
            <c:set var="grupo" value="${vbean.grupo}" />
            <c:set var="serie" value="${vbean.serie}" />
            <c:set var="estatus" value="${vbean.estatus}" />

        </c:forEach>
    </c:when>
    <c:otherwise>

        <c:set var="id" value="0" />
        <c:set var="vendedor" value="" />
        <c:set var="nombre" value="" />
        <c:set var="domicilio" value="" />
        <c:set var="ciudad" value="" />
        <c:set var="telefono" value="" />
        <c:set var="celular" value="" />
        <c:set var="email" value="" />
        <c:set var="puesto" value="" />
        <c:set var="comision" value="0.00" />
        <c:set var="grupo_id" value="0" />
        <c:set var="grupo" value="" />
        <c:set var="serie" value="A" />
        <c:set var="estatus" value="A" />

    </c:otherwise>
</c:choose>

<div class="row"><div class="col-xs-12">&nbsp;</div></div>
<form method="post" id="form_navega" name="form_navega">
    <input type="hidden" id="funcion_forma" name="funcion_forma" />
</form>

<form class="form-horizontal" method="post" id="form_vendedor" name="form_vendedor">
    
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
        <div class="panel-heading">Vendedor</div>
        <div class="panel-body">

           
                <!-- contenido del panel -->

                <input type="hidden" id="id" name="id" value="${id}" />
                <div class="row"><div class="col-xs-12">&nbsp;</div></div>
                
                <div class="row">
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="no_vendedor" class="col-md-12 col-lg-2">Núm.<label style="color: red">*</label></label>
                            <div class="col-md-12 col-lg-10">
                                <div class="controls form-inline">
                                    <input type="text" class="form-control" id="no_vendedor" name="no_vendedor" value="${id}" placeholder="" size="20" maxlength="20" disabled="disabled"/>

                                </div>
                            </div>
                        </div>
                    </div>                
                    
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="vendedor" class="col-md-12 col-lg-2">Alias<label style="color: red">*</label></label>
                            <div class="col-md-12 col-lg-10">
                                <div class="controls form-inline">
                                    <input type="text" class="form-control" id="vendedor" name="vendedor" value="${vendedor}" placeholder="" size="50" maxlength="50" />

                                </div>
                            </div>
                        </div>
                    </div>
                                    
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="nombre" class="col-md-12 col-lg-2">Nombre<label style="color: red">*</label></label>
                            <div class="col-md-12 col-lg-10">
                                <div class="controls form-inline">
                                    <input type="text" class="form-control" id="nombre" name="nombre" value="${nombre}" placeholder="" size="50" maxlength="150" />

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
                                    <input type="text" class="form-control" id="domicilio" name="domicilio" value="${domicilio}" placeholder="" size="50" maxlength="150" />

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="telefono" class="col-md-12 col-lg-2">teléfono</label>
                            <div class="col-md-12 col-lg-10">
                                <div class="controls form-inline">
                                    <input type="text" class="form-control" id="telefono" name="email" value="${telefono}" placeholder="" size="30" maxlength="45" />

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
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="correo" class="col-md-12 col-lg-2">Correo<label style="color: red">*</label></label>
                            <div class="col-md-12 col-lg-10">
                                <div class="controls form-inline">
                                    <input type="text" class="form-control" id="correo" name="correo" value="${email}" placeholder="" size="50" maxlength="150" />

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="puesto" class="col-md-12 col-lg-2">Puesto<label style="color: red">*</label></label>
                            <div class="col-md-12 col-lg-10">
                                <div class="controls form-inline">
                                    <input type="text" class="form-control" id="puesto" name="puesto" value="${puesto}" placeholder="" size="50" maxlength="100" />

                                </div>
                            </div>
                        </div>
                    </div>                
                    <div class="row"><div class="col-xs-12"><hr style="width: 95%; color: #EEEEEE; height: 0.2px; background-color:#EEEEEE;"></div></div>                                                
                
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="comision" class="col-md-12 col-lg-2">Comisión</label>
                            <div class="col-md-12 col-lg-10">
                                <div class="controls form-inline">
                                    <div class="input-group">
                                    <input type="text" class="form-control" id="comision" name="comision" value="${comision}" placeholder="" size="10" maxlength="50" />
                                    <span class="input-group-addon">%</span>
                                    </div>
                                </div>
                            </div>
                                    
                        </div>
                    </div>
                                    
                    <div class="row"><div class="col-xs-12"><hr style="width: 95%; color: #EEEEEE; height: 0.2px; background-color:#EEEEEE;"></div></div>                                                
                    
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="grupo_id" class="col-md-12 col-lg-2">Grupo<label style="color: red">*</label></label>
                            <div class="col-md-12 col-lg-10">
                                <div class="controls form-inline">
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="grupo" name="grupo" value="${grupo}" placeholder="" size="30" maxlength="50" />
                                        <span class="input-group-btn">
                                            <button class="btn btn-default"  id="btn_grupo" type="button">Ir</button>
                                        </span>
                                        <input type="hidden" class="form-control" id="grupo_id" name="grupo_id" value="${grupo_id}" />
                                    </div> <!-- fin input group -->
                                </div>
                            </div>
                        </div>
                    </div>
                    
                
                </div> <!-- fin row principal -->  
                
                <!-- <div class="row">
                    <div class="col-xs-12 text-right">
                        <button type="submit" id="guardar" class="btn btn-success"><span class="fa fa-floppy-o"></span> Guardar </button>
                        <button type="button" id="cancelar" class="btn btn-danger"><span class="fa fa-times"></span> Cancelar </button>
                        <button type="button" id="nuevo" class="btn btn-default"><span class="fa fa-file-o"></span> Nuevo </button>
                    </div>

                </div> -->
                
                
        </div> <!-- panel-body -->
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

