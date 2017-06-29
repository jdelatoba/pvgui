<%-- 
    Document   : cliente
    Created on : 04-mar-2017, 18:57:26
    Author     : Rembao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="cb" class="app.choya.sys.pv.catalogo.Cliente"  />
<!DOCTYPE html>

<script type="text/javascript">

    $(document).ready(function (e) {

        $("#limite_credito").numeric();
        $("#dias_credito").numeric();
        $("#codigo_postal").numeric();
        
        $("#limite_credito").blur(function (e) {
            if ($("#limite_credito").val() == "") {
                $("#limite_credito").val("0.00");
            } else {

                $("#limite_credito").formatCurrency();
                var limite_credito = $("input[id='limite_credito']").val();
                $("input[id='limite_credito']").val(limite_credito.replace("$", ""));
            }
        });


        $("input[type=text]").focus(function () {
            $(this).select();
        });
        
        

        $("input[type=text]").blur(function (e) {
            $(this).val($.trim($(this).val().toUpperCase()));
        });
        
        

        $.validator.addMethod("validarRFC", function (value, element) {
            var re = new RegExp('^[A-Z]{3,4}([0-9]{2})(1[0-2]|0[1-9])([0-3][0-9])([A-Z0-9]{3})');
            return this.optional(element) || re.test(value);
        }, "&nbsp;R.F.C. Invalido!");
        
        //$("#rfc").rules("add", {validarRFC: ""});

        $("#form_cliente").validate({
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
                clave: {
                    required: true,
                    maxlength: 20
                },
                nombre: {
                    required: true,
                    maxlength: 250
                },
                representante:{
                    required: true,
                    maxlength: 250
                },
                rfc:{
                    required: true,
                    maxlength: 13,
                    validarRFC: true
                },
                domicilio:{
                    required: true,
                    maxlength: 250
                },
                no_ext:{
                    required: true,
                    maxlength: 20
                },
                colonia:{
                    required: true,
                    maxlength: 150
                },
                codigo_postal:{
                    required: true,
                    number: true,
                    maxlength: 5
                },
                localidad:{
                    required: true,
                    maxlength: 150
                },
                municipio:{
                    required: true,
                    maxlength: 150
                },
                entidad:{
                    required: true,
                    maxlength: 50
                },
                pais:{
                    required: true,
                    maxlength: 150
                },
                email:{
                    required: true,
                    email: true,
                    maxlength: 150
                },
                limite_credito:{
                    number: true
                },
                dias_credito: {
                    number: true
                }
                
            },
            //errorPlacement: function (error, element) {
            //    if ($(element).is('select')) {
            //        element.next().after(error); // coloca los mensajes de error para los elementos select de lado derecho
            //    } else {
            //        error.insertAfter(element);  // coloca los mensajes de error
            //    }
            //},
            messages: {
                clave: {
                    required: "&nbsp;Por favor introduzca clave del cliente",
                    maxlength: "Longitud del campo clave es invalido max - 20 caracteres"
                },
                nombre: {
                    required: "&nbsp;Por favor introduzca un nombre valido",
                    maxlength: "Longitud del campo nombre es invalido max - 250 caracteres"
                },
                representante: {
                    required: "&nbsp;Por favor introduzca un nombre valido para representante",
                    maxlength: "Longitud del campo representante es invalido max - 250 caracteres"
                },
                rfc: {
                    required: "&nbsp;Por favor introduzca R.F.C.",
                    maxlength: "&nbsp;Longitud del campo nombre es invalido max - 13 caracteres"
                },
                domicilio: {
                    required: "&nbsp;Por favor introduzca domicilio del cliente",
                    maxlength: "&nbsp;Longitud del campo domicilio es invalido max - 150 caracteres"
                },
                no_ext:{
                    required: "&nbsp;Por favor introduzca número exterior del cliente",
                    maxlength: "&nbsp;Longitud del campo número exterior es invalido max - 20 caracteres"
                },
                colonia:{
                    required: "&nbsp;Por favor introduzca colonia del cliente",
                    maxlength: "Longitud del campo colinia es invalido max - 150 caracteres"
                },
                codigo_postal:{
                    required: "&nbsp;Por favor introduzca código postal del cliente",
                    maxlength: "&nbsp;Longitud del campo código postal es invalido max - 5 caracteres",
                    number: "&nbsp;El campo código postal es númerico"
                },
                localidad:{
                    required: "&nbsp;Por favor introduzca el nombre de la localidad",
                    maxlength: "Longitud del campo localidad es invalido max - 150 caracteres"
                },
                municipio:{
                    required: "&nbsp;Por favor introduzca el nombre del municipio",
                    maxlength: "Longitud del campo municipio es invalido max - 150 caracteres"
                },
                entidad:{
                    required: "&nbsp;Por favor introduzca el nombre de la entidad",
                    maxlength: "Longitud del campo entidad es invalido max - 50 caracteres"
                },
                pais:{
                    required: "&nbsp;Por favor introduzca el nombre del pais",
                    maxlength: "Longitud del campo pais es invalido max - 50 caracteres"
                },
                email:{
                    email: "&nbsp;Por favor introduzca un correo electronico valido.",
                    required: "&nbsp;Por favor introduzca el correo del cliente",
                    maxlength: "Longitud del campo correo es invalido max - 150 caracteres"
                },
                limite_credito: "&nbsp;Por favor, Introduzca un monto valido.",
                dias_credito: "&nbsp;Pof favor, Introduzca un número valido."
                
                

            },
            submitHandler: function (form) {

                $("#modal_mensaje").html("<span class='fa fa-cog fa-spin fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">Espere un momento por favor...</label></div>");
                $("#moperacion").modal("show");

                var id = $("#id").val();
                var clave = $("#clave").val();
                var representante = $("#representante").val();
                var nombre = $("#nombre").val();
                var rfc = $("#rfc").val();
                var curp = $("#curp").val();
                var id_domicilio = $("#id_domicilio").val();
                var domicilio = $("#domicilio").val();
                var no_ext = $("#no_ext").val();
                var no_int = $("#no_int").val();
                var colonia = $("#colonia").val();
                var codigo_postal = $("#codigo_postal").val();
                var localidad = $("#localidad").val();
                var municipio = $("#municipio").val();
                var entidad = $("#entidad").val();
                var pais = $("#pais").val();
                var telefono = $("#telefono").val();
                var celular = $("#celular").val();
                var email = $("#email").val();
                var comentario = $("#comentario").val();
                var limite_credito = $("#limite_credito").val();
                var dias_credito = $("#dias_credito").val();
                
                
                var aplica_retencion = "";
                
                if($("#aplica_retencion").is(':checked')){
                    aplica_retencion = "1";
                }else{
                    aplica_retencion = "0";
                    
                }
                var no_precio = $("#no_precio").val();
                var operacion = "";
                
                if(id == 0){
                    operacion = "guardar";
                }else{
                    operacion = "actualizar";
                }
                
                formData = {
                    id: id,
                    clave : clave,
                    representante: representante,
                    nombre: nombre,
                    rfc: rfc,
                    curp: curp,
                    id_domicilio : id_domicilio,
                    domicilio: domicilio,
                    no_ext: no_ext,
                    no_int: no_int,
                    colonia: colonia,
                    codigo_postal: codigo_postal,
                    localidad: localidad,
                    municipio: municipio,
                    entidad: entidad,
                    pais: pais,
                    telefono: telefono,
                    celular: celular,
                    email: email,
                    comentario: comentario,
                    limite_credito: limite_credito,
                    dias_credito: dias_credito,
                    aplica_retencion: aplica_retencion,
                    no_precio: no_precio,
                    operacion: operacion
                };

                $.post("/puntoventa/pv/Cliente.pv", formData, function (data) {
                    $("#operaciones").html(data);

                    if ((parseInt($("#resultado").val()) !== 0)) {
                        $("#modal_mensaje").html("<span style=\"color: green\" class=\"fa fa-check-circle fa-3x\"></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">El registro se ha guardado correctamente.</label></div>");
                        $("#id").val($("#id_rcliente").val());
                        $("#id_domicilio").val($("#id_rdomicilio").val());
                        $("#no_cliente").val($("#id_rcliente").val());
                        
                        
                        //cargamos el listado de domicilio alternos
                        formData = {
                            id : $("#id").val(),
                            operacion : "carga_domicilio_alternos"
                        };
                        
                        $.post("/puntoventa/pv/Cliente.pv", formData, function(data){
                           $("#domicilio_alterno").html(data); 
                        });
                        
                        
                    } else {
                        $("#modal_mensaje").html("<span style=\"color: red\" class=\"fa fa-exclamation-triangle fa-3x\"></span>&nbsp;<label for=\"modal_mensaje\" style=\"font-size: 16px; font-weight: bolder\">El sistema ha lanzado el siguiente error: Codigo Error: "+$("#codigo_error").val() +" "+ $("#mensaje").val() + ".</label></div>");

                    }
                });
                //asdasd$(form).submit();*/
            }

        });  // fin $("#form_cliente").validate

        $("#nuevo").click(function(){
           
           $("#funcion").val("proveedores");
           $("#form_navega").attr("action", "../puntoventa/Hub");
           $("#form_navega").submit();
           //$("#form_cliente")[0].reset(); 
            
        });
        
        
        
        
        if($("#id").val() != 0){
            formData = {
                id : $("#id").val(),
                operacion : "carga_domicilio_alternos"
            };

            $.post("/puntoventa/pv/Cliente.pv", formData, function(data){
               $("#domicilio_alterno").html(data); 
            });
        }
        
        
        
        
        
        
    });

    

</script>

<style type="text/css">
    
    td.details-control {
        background: url('/puntoventa/resources/images/details_open.png') no-repeat center center;
        cursor: pointer;
        
    }
    tr.shown td.details-control {
        background: url('/puntoventa/resources/images/details_close.png') no-repeat center center;
    }
    
    th {font-size: 12px;}
    td {font-size: 11px;}
    
    /* Important part */
    .modal-dialog, .modal-lg, .modal-domi{
        overflow-y: initial !important
    }
    .body-domi{
        height: 400px;
        overflow-y: auto;
    }
    
</style>

<div class="row"><div class="col-xs-12">&nbsp;</div></div>
<form method="post" id="form_navega" name="form_navega">
    <input type="hidden" id="funcion" name="funcion" />
</form>

<form class="form-horizontal" method="post" id="form_cliente" name="form_cliente">

    <c:choose>
        <c:when test="${requestScope.cliente_id != null &&  requestScope.cliente_id != 0}">
            <c:forEach var="cbean" items="${cb.getClientes()}" >
                <c:set var="id" value="${cbean.id}" />
                <c:set var="clave" value="${cbean.clave}" />
                <c:set var="representante" value="${cbean.representante}" />
                <c:set var="nombre" value="${cbean.nombre}" />
                <c:set var="rfc" value="${cbean.rfc}" />
                <c:set var="curp" value="${cbean.curp}" />
                <c:set var="id_domicilio" value="${cbean.id_domicilio}" />
                <c:set var="domicilio" value="${cbean.domicilio}" />
                <c:set var="no_ext" value="${cbean.no_ext}" />
                <c:set var="no_int" value="${cbean.no_int}" />
                <c:set var="colonia" value="${cbean.colonia}" />
                <c:set var="codigo_postal" value="${cbean.codigo_postal}" />
                <c:set var="localidad" value="${cbean.localidad}" />
                <c:set var="municipio" value="${cbean.municipio}" />
                <c:set var="entidad" value="${cbean.entidad}" />
                <c:set var="pais" value="${cbean.pais}" />
                <c:set var="telefono" value="${cbean.telefono}" />
                <c:set var="celular" value="${cbean.celular}" />
                <c:set var="email" value="${cbean.email}" />
                <c:set var="comentario" value="${cbean.comentario}" />
                <c:set var="aplica_retencion" value="${cbean.aplica_retencion}" />
                <c:set var="no_precio" value="${cbean.no_precio}" />
                <c:set var="limite_credito" value="${cbean.limite_credito}" />
                <c:set var="dias_credito" value="${cbean.dia_credito}" />
                
            </c:forEach>
        </c:when>
        <c:otherwise>

                <c:set var="id" value="12" />
                <c:set var="clave" value="" />
                <c:set var="representante" value="" />
                <c:set var="nombre" value="" />
                <c:set var="rfc" value="" />
                <c:set var="curp" value="" />
                <c:set var="id_domicilio" value="0" />
                <c:set var="domicilio" value="" />
                <c:set var="no_ext" value="" />
                <c:set var="no_int" value="" />
                <c:set var="colonia" value="" />
                <c:set var="codigo_postal" value="" />
                <c:set var="localidad" value="" />
                <c:set var="municipio" value="" />
                <c:set var="entidad" value="" />
                <c:set var="pais" value="" />
                <c:set var="telefono" value="" />
                <c:set var="celular" value="" />
                <c:set var="email" value="" />
                <c:set var="comentario" value="" />
                <c:set var="aplica_retencion" value="0" />
                <c:set var="no_precio" value="1" />
                <c:set var="limite_credito" value="0.00" />
                <c:set var="dias_credito" value="0" />

        </c:otherwise>
    </c:choose>

    
    <div id="operaciones"></div>
    <div class="panel panel-default">
        
        <div class="panel-body">
            
    <fieldset>
        <legend>Clientes</legend>
            
            <!-- Nav tabs -->
            <div>
                <ul class="nav nav-tabs" role="tablist">

                    <li role="proveedor-tab" class="active"><a href="#tab-general" aria-controls="tab-general" role="tab" data-toggle="tab">Datos Generales</a></li>
                    <li role="proveedor-tab"><a href="#tab-domicilio" aria-controls="tab-domicilio" role="tab" data-toggle="tab">Domicilio</a></li>
                    <li role="proveedor-tab"><a href="#tab-credito" aria-controls="tab-credito" role="tab" data-toggle="tab">Crédito/Impuestos</a></li>

                </ul> <!-- nav nav-tabs -->

                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane fade in active" id="tab-general">



                        <input type="hidden" id="id" name="id" value="${id}" />
                        <input type="hidden" id="idx" name="idx" value="${idx}" />
                        <div class="row"><div class="col-xs-12">&nbsp;</div></div>
                        <div class="row">
                            <div class="col-xs-12 col-lg-6">
                                <div class="form-group">
                                    <label for="no_cliente" class="col-md-12 col-lg-3">Id<label style="color: red">*</label></label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="no_cliente" name="no_cliente" value="${id}" placeholder="" size="20" maxlength="20" disabled="disabled"/>

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-lg-6">
                                <div class="form-group">
                                    <label for="clave" class="col-md-12 col-lg-3">Clave<label style="color: red">*</label></label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="clave" name="clave" value="${clave}" placeholder="" size="20" maxlength="20"/>

                                        </div>
                                    </div>
                                </div>
                            </div>                
                        </div>                    
                        <div class="row">                    
                            <div class="col-xs-12 col-lg-6">
                                <div class="form-group">
                                    <label for="representante" class="col-md-12 col-lg-3">Representante</label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="representante" name="representante" value="${representante}" placeholder="" size="40" maxlength="250" />

                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-xs-12 col-lg-6">
                                <div class="form-group">
                                    <label for="nombre" class="col-md-12 col-lg-3">Nombre<label style="color: red">*</label></label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="nombre" name="nombre" value="${nombre}" placeholder="" size="40" maxlength="250" />

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">                    
                            <div class="col-xs-12 col-lg-6">
                                <div class="form-group">
                                    <label for="rfc" class="col-md-12 col-lg-3">R.F.C. <label style="color: red">*</label></label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="rfc" name="rfc" value="${rfc}" placeholder="" size="20" maxlength="13" />

                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-xs-12 col-lg-6">
                                <div class="form-group">
                                    <label for="curp" class="col-md-12 col-lg-3">CURP <label style="color: red">*</label></label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="curp" name="curp" value="${curp}" placeholder="" size="30" maxlength="18" />

                                        </div>
                                    </div>
                                </div>
                            </div>

                                                    
                        </div>


                    </div> <!-- tab-general -->

                    <div role="tabpanel" class="tab-pane fade" id="tab-domicilio">
                        
                    <div class="row"><div class="col-xs-12">&nbsp;</div></div>    
                    <div class="row">
                                        <div class="col-md-12 col-lg-6" >
                                            <div class="form-group">
                                                <label for="domicilio" class="col-md-12 col-lg-3">Domicilio</label>
                                                <div class="col-md-12 col-lg-9">
                                                    <div class="controls form-inline">
                                                        <input type="text" class="form-control" id="domicilio" name="domicilio" value="${domicilio}" placeholder="" size="80" maxlength="250" />

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>    
                                    <div class="row">
                                        <div class="col-md-12 col-lg-6" >
                                            <div class="form-group">
                                                <label for="no_ext" class="col-md-12 col-lg-3">No. Exterior</label>
                                                <div class="col-md-12 col-lg-9">
                                                    <div class="controls form-inline">
                                                        <input type="text" class="form-control" id="no_ext" name="no_ext" value="${no_ext}" placeholder="" size="20" maxlength="20" />

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-12 col-lg-6">
                                            <div class="form-group">
                                                <label for="no_int" class="col-md-12 col-lg-3">No. Interior</label>
                                                <div class="col-md-12 col-lg-9">
                                                    <div class="controls form-inline">
                                                        <input type="text" class="form-control" id="no_int" name="no_int" value="${no_int}" placeholder="" maxlength="20"  />

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>                    
                        <div class="row">                    
                            <div class="col-md-12 col-lg-6">
                                <div class="form-group">
                                    <label for="colonia" class="col-md-12 col-lg-3">Colonia</label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="colonia" name="colonia" value="${colonia}" placeholder=""  size="30" maxlength="150" />

                                        </div>
                                    </div>
                                </div>
                            </div> <!-- fin colonia -->
                            <div class="col-md-12 col-lg-6">
                                <div class="form-group">
                                    <label for="colonia" class="col-md-12 col-lg-3">Código Postal</label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="codigo_postal" name="codigo_postal" value="${codigo_postal}" placeholder="" size="20" maxlength="5" />

                                        </div>
                                    </div>
                                </div>
                            </div> <!-- codigo postal -->


                            <div class="col-md-12 col-lg-6">
                                <div class="form-group">
                                    <label for="localidad" class="col-md-12 col-lg-3">Localidad</label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="localidad" name="localidad" value="${localidad}" placeholder=""  size="30" maxlength="150" />

                                        </div>
                                    </div>
                                </div>
                            </div> <!-- fin localidad -->
                            <div class="col-md-12 col-lg-6">
                                <div class="form-group">
                                    <label for="municipio" class="col-md-12 col-lg-3">Municipio</label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="municipio" name="municipio" value="${municipio}" placeholder=""  size="30" maxlength="150" />

                                        </div>
                                    </div>
                                </div>
                            </div><!-- municipio -->

                            <div class="col-md-12 col-lg-6">
                                <div class="form-group">
                                    <label for="entidad" class="col-md-12 col-lg-3">Entidad</label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="entidad" name="entidad" value="${entidad}" placeholder=""  size="30" maxlength="150" />

                                        </div>
                                    </div>
                                </div>
                            </div> <!-- fin entidad -->
                            <div class="col-md-12 col-lg-6">
                                <div class="form-group">
                                    <label for="pais" class="col-md-12 col-lg-3">País</label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="pais" name="pais" value="${pais}" placeholder=""  size="30" maxlength="150" />

                                        </div>
                                    </div>
                                </div>
                            </div><!-- pais -->

                            <div class="col-md-12 col-lg-6">
                                <div class="form-group">
                                    <label for="telefono" class="col-md-12 col-lg-3">Teléfono</label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="telefono" name="telefono" value="${telefono}" placeholder=""  size="20" maxlength="50" />

                                        </div>
                                    </div>
                                </div>
                            </div> <!-- fin telefono -->
                            <div class="col-md-12 col-lg-6">
                                <div class="form-group">
                                    <label for="celular" class="col-md-12 col-lg-3">Celular</label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="celular" name="celular" value="${celular}" placeholder=""  size="20" maxlength="50" />

                                        </div>
                                    </div>
                                </div>
                            </div><!-- celular -->


                        </div>   <!-- row -->

                        <div class="row">
                            <div class="col-md-12 col-lg-6" >
                                <div class="form-group">
                                    <label for="email" class="col-md-12 col-lg-3">Correo Electrónico</label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="email" name="email" value="${email}" placeholder="" size="80" maxlength="150" />

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div> <!-- row email -->

                        <div class="row">
                            <div class="col-md-12 col-lg-6" >
                                <div class="form-group">
                                    <label for="comentario" class="col-md-12 col-lg-3">Comentario</label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="comentario" name="comentario" value="${comentario}" placeholder="" size="80" maxlength="250" />

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div> <!-- row comentario -->
                    
                    
                    
                    
                    
                    
                    
                        
                        <input type="hidden" id="id_domicilio" name="id_domicilio" value="${id_domicilio}" />
                        <div class="row"><div class="col-xs-12">&nbsp;</div></div>
                        
                        
                        
                        

                    </div> <!-- tab-domicilio -->   


                    <div role="tabpanel" class="tab-pane fade" id="tab-credito">

                        <div class="row"><div class="col-xs-12">&nbsp;</div></div>
                        <div class="row">
                            <div class="col-md-12 col-lg-6" >
                                <div class="form-group">
                                    <label for="limite_credito" class="col-md-12 col-lg-3">Límite Crédito</label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="limite_credito" name="limite_credito" value="${limite_credito}" placeholder="" size="20" maxlength="20" style="text-align:right"/>

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12 col-lg-6">
                                <div class="form-group">
                                    <label for="dias_credito" class="col-md-12 col-lg-3">Días Crédito</label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="dias_credito" name="dias_credito" value="${dias_credito}" placeholder="" maxlength="3"  style="text-align:right"/>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div> <!-- row -->    
                    <div class="row">
                            <div class="col-md-12 col-lg-6" >
                                <div class="form-group">
                                    <label for="limite_credito" class="col-md-12 col-lg-3">Aplican Retenciones</label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="checkbox" value="${aplica_retencion}" id="aplica_retencion" name="aplica_retencion" />

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12 col-lg-6" >
                                <div class="form-group">
                                    <label for="no_precio" class="col-md-12 col-lg-3">No. Precio</label>
                                    <div class="col-md-12 col-lg-9">
                                        <select name="no_precio" id="no_precio" class="selectpicker" data-live-seaerch="true" >
                                            <option value="">Seleccione</option>
                                            <option value="1" <c:if test="${no_precio == 1}"> selected </c:if>  >1</option>
                                            <option value="2" <c:if test="${no_precio == 2}"> selected </c:if>  >2</option>
                                            <option value="3" <c:if test="${no_precio == 3}"> selected </c:if>  >3</option>
                                            <option value="4" <c:if test="${no_precio == 4}"> selected </c:if>  >4</option>
                                        </select>
                                    </div>
                                </div>
                            </div>                
                    </div>  <!-- fin row -->                    
                    </div> <!-- tab-credito -->   
                </div>  <!-- tab-content -->  
                <div class="row">
                    <div class="col-xs-12 text-right">
                        <button type="submit" id="guardar" class="btn btn-default"><span class="fa fa-floppy-o"></span> Guardar </button>
                        <button type="button" id="cancelar" class="btn btn-default"><span class="fa fa-times"></span> Cancelar </button>
                        <button type="button" id="nuevo" class="btn btn-default"><span class="fa fa-file-o"></span> Nuevo </button>
                        
                    </div>

                </div>
            </div> 
            </fieldset>                                
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


</form>            

<div id="domicilio_alterno"></div>                                        
