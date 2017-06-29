<%-- 
    Document   : proveedor
    Created on : 22-feb-2017, 17:03:06
    Author     : Rembao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="pb" class="app.choya.sys.pv.catalogo.Proveedor"  />
<!DOCTYPE html>

<script type="text/javascript">

    $(document).ready(function (e) {

        $("#limite_credito").numeric();
        $("#dias_credito").numeric();
        $("#codigo_postal").numeric();


        $("input[type=text]").focus(function () {
            $(this).select();
        });
        
        

        $("input[type=text]").blur(function (e) {
            $(this).val($.trim($(this).val().toUpperCase()));
        });
        

        $("#limite_credito").blur(function (e) {
            if ($("#limite_credito").val() == "") {
                $("#limite_credito").val("0.00");
            } else {

                $("#limite_credito").formatCurrency();
                var limite_credito = $("input[id='limite_credito']").val();
                $("input[id='limite_credito']").val(limite_credito.replace("$", ""));
            }
        });
        
        
        $("#nuevo").click(function(e){
            $("#mensaje_comando").html("<span class='fa fa-exclamation-triangle fa-3x'></span>&nbsp;<label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">¿Desea agregar un nuevo vendedor?\n\nLos cambios que no ha guardado no tendran efecto en el registro.</label></div>");
            $("#mcomando").modal("show");
        });
        
        
        $("#aceptar_comando").click(function(e){
            
            $("#funcion_forma").val("proveedores");
            $('#form_navega').attr('action', "../pv/Hub");
            $("#form_navega").submit();
        });
        
        $("#buscar_proveedor").click(function(){
           
           formData = {
                    operacion: "buscar_proveedor"

                };

            $.post("../pv/proveedor.pv", formData, function (data) {
                $("#function-container").html(data);
            });
        });

        $("#form_proveedor").validate({
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
                nombre: {
                    required: true,
                    maxlength: 250
                },
                rfc: {
                    required: true,
                    minlength: 12,
                    maxlength: 13
                },
                tipo: {
                    required: true
                },
                email: {
                    email: true
                },
                codigo_postal: {
                    digits: true
                },
                limite_credito: {
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
                nombre: "&nbsp;Por favor introduzca un nombre valido de Proveedor",
                rfc: {
                    required: "&nbsp;Por favor introduzca el RFC del Proveedor",
                    minlength: "&nbsp;El número de caracteres no es valido, se espera de 12 a 13 caracteres"
                },
                tipo: "&nbsp;Por favor Seleccione un tipo valido",
                codigo_postal: "&nbsp;Pof favor, Introduzca un número valido.",
                email: "&nbsp;Por favor introduzca un correo electronico valido.",
                limite_credito: "&nbsp;Por favor, Introduzca un monto valido.",
                dias_credito: "&nbsp;Pof favor, Introduzca un número valido."

            },
            submitHandler: function (form) {

                $("#modal_mensaje").html("<span class='fa fa-cog fa-spin fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">Espere un momento por favor...</label></div>");
                $("#moperacion").modal("show");

                var id = $("#id").val();
                var representante = $("#representante").val();
                var nombre = $("#nombre").val();
                var alias = $("#alias").val();
                var rfc = $("#rfc").val();
                var curp = $("#curp").val();
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
                var operacion = "";
                
                if(id == 0){
                    operacion = "guardar";
                }else{
                    operacion = "actualizar";
                }
                
                formData = {
                    id: id,
                    representante: representante,
                    nombre: nombre,
                    alias: alias,
                    rfc: rfc,
                    curp: curp,
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
                    operacion: operacion
                };

                $.post("/puntoventa/pv/proveedor.pv", formData, function (data) {
                    $("#operaciones").html(data);

                    if ((parseInt($("#resultado").val()) !== 0)) {
                        $("#modal_mensaje").html("<span style=\"color: green\" class=\"fa fa-check-circle fa-3x\"></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">El registro se ha guardado correctamente.</label></div>");
                        $("#id").val($("#resultado").val());
                        $("#no_proveedor").val($("#resultado").val());
                    } else {
                        $("#modal_mensaje").html("<span style=\"color: red\" class=\"fa fa-exclamation-triangle fa-3x\"></span>&nbsp;<label for=\"modal_mensaje\" style=\"font-size: 16px; font-weight: bolder\">El sistema ha lanzado el siguiente error: Codigo Error: "+$("#codigo_error").val() +" "+ $("#mensaje").val() + ".</label></div>");

                    }
                });
                //asdasd$(form).submit();
            }

        });  // fin $("#form_proveedor").validate

        /*$("#nuevo").click(function(){
           
           $("#funcion").val("proveedores");
           $("#form_navega").attr("action", "../puntoventa/Hub");
           $("#form_navega").submit();
           //$("#form_proveedor")[0].reset(); 
            
        });*/
    });

    function validarRFC($rfc) {
        $regex = '/^[A-Z]{3,4}([0-9]{2})(1[0-2]|0[1-9])([0-3][0-9])([ -]?)([A-Z0-9]{4})$/';
        return preg_match($regex, $rfc);
    }//fin función

</script>

<div class="row"><div class="col-xs-12">&nbsp;</div></div>
<!--<form method="post" id="form_navega" name="form_navega">
    <input type="hidden" id="funcion_forma" name="funcion_forma" />
</form>-->

<form class="form-horizontal" method="post" id="form_proveedor" name="form_proveedor">

    

    <c:choose>
        <c:when test="${requestScope.id_proveedor != null &&  requestScope.id_proveedor != 0}">
            <c:forEach var="pbean" items="${pb.getProveedorLista(requestScope.id_proveedor)}" >
                <c:set var="id" value="${pbean.id}" />
                <c:set var="representante" value="${pbean.representante}" />
                <c:set var="nombre" value="${pbean.nombre}" />
                <c:set var="alias" value="${pbean.alias}" />
                <c:set var="rfc" value="${pbean.rfc}" />
                <c:set var="curp" value="${pbean.curp}" />
                <c:set var="domicilio" value="${pbean.domicilio}" />
                <c:set var="no_ext" value="${pbean.no_ext}" />
                <c:set var="no_int" value="${pbean.no_int}" />
                <c:set var="colonia" value="${pbean.colonia}" />
                <c:set var="codigo_postal" value="${pbean.codigo_postal}" />
                <c:set var="localidad" value="${pbean.localidad}" />
                <c:set var="municipio" value="${pbean.municipio}" />
                <c:set var="entidad" value="${pbean.entidad}" />
                <c:set var="pais" value="${pbean.pais}" />
                <c:set var="telefono" value="${pbean.telefono}" />
                <c:set var="celular" value="${pbean.celular}" />
                <c:set var="email" value="${pbean.email}" />
                <c:set var="comentario" value="${pbean.comentario}" />
                <c:set var="limite_credito" value="${pbean.limite_credito}" />
                <c:set var="dias_credito" value="${pbean.dias_credito}" />
                <c:set var="serie" value="${pbean.serie}" />
                <c:set var="estatus" value="${pbean.estatus}" />

            </c:forEach>
        </c:when>
        <c:otherwise>

            <c:set var="id" value="0" />
            <c:set var="idx" value="111" />
            <c:set var="representante" value="" />
            <c:set var="nombre" value="" />
            <c:set var="alias" value="" />
            <c:set var="rfc" value="" />
            <c:set var="curp" value="" />
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
            <c:set var="limite_credito" value="0.00" />
            <c:set var="dias_credito" value="0" />

        </c:otherwise>
    </c:choose>

    
    <div id="operaciones"></div>
    
    <div class="panel panel-default">
        
        <div class="panel-heading">
                <h3>Proveedores</h3>
        </div>
        <div class="panel-body">
            
            <!-- Nav tabs -->
            <div class="nav-tabs-horizontal">
                <ul class="nav nav-tabs" role="tablist">
                    
                    <li class="nav-item">
                        <a class="nav-link active"  href="javascript: void(0);" data-toggle="tab" data-target="#tab-general" role="tab">
                            <i class="icmn-magazine"></i>
                            Datos Generales
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="javascript: void(0);" data-toggle="tab" data-target="#tab-domicilio" role="tab">
                            <i class="icmn-file-text2"></i>
                            Domicilio
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="javascript: void(0);" data-toggle="tab" data-target="#tab-credito" role="tab">
                            <i class="icmn-file-text2"></i>
                            Credito
                        </a>
                    </li>
                    

                </ul> <!-- nav nav-tabs -->
                <input type="hidden" id="id" name="id" value="${id}" />
                        <input type="hidden" id="idx" name="idx" value="${idx}" />
                <div class="tab-content padding-vertical-20">
                    <div role="tabpanel" class="tab-pane fade in active" id="tab-general">
                        <div class="row">
                            <div class="col-md-12 col-lg-6">
                                <div class="form-group">
                                    <div class="col-md-12 col-lg-3">
                                        <label for="no_proveedor" class="form-control-label">Núm.<label style="color: red">*</label></label>
                                    </div>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="no_proveedor" name="no_proveedor" value="${id}" placeholder="" size="20" maxlength="20" disabled=""/>

                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-12 col-lg-6">
                                <div class="form-group">
                                    <div class="col-md-12 col-lg-3">
                                        <label for="representante" class="form-control-label">Representante</label>
                                    </div>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="representante" name="representante" value="${representante}" placeholder="" size="50" maxlength="250"/>

                                        </div>
                                    </div>
                                </div>
                            </div>                
                        </div>
                        <div class="row"><div class="col-xs-12">&nbsp;</div></div>
                        
                        <div class="row">
                            <div class="col-md-12 col-lg-6">
                                <div class="form-group">
                                    <label for="nombre" class="col-md-12 col-lg-3">Nombre<label style="color: red">*</label></label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="nombre" name="nombre" value="${nombre}" placeholder="" size="35" maxlength="250" />

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12  col-lg-6">
                                <div class="form-group">
                                    <label for="nombre" class="col-md-12 col-lg-3">Alias</label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="alias" name="alias" value="${alias}" placeholder="" size="50" maxlength="150" />

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row"><div class="col-xs-12">&nbsp;</div></div>
                        <div class="row">                    
                            <div class="col-md-12 col-lg-6">
                                <div class="form-group">
                                    <label for="rfc" class="col-md-12 col-lg-3">R.F.C. <label style="color: red">*</label></label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="rfc" name="rfc" value="${rfc}" placeholder="" size="35" maxlength="13" />

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row"><div class="col-xs-12">&nbsp;</div></div>                    
                        <div class="row">                    
                            <div class="col-md-12 col-lg-6">
                                <div class="form-group">
                                    <label for="curp" class="col-md-12 col-lg-3">CURP <label style="color: red">*</label></label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="curp" name="curp" value="${curp}" placeholder="" size="35" maxlength="18" />

                                        </div>
                                    </div>
                                </div>
                            </div>

                                                 
                        </div>


                    </div> <!-- tab-general -->

                    <div role="tabpanel" class="tab-pane fade" id="tab-domicilio">
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
                        <div class="row"><div class="col-xs-12">&nbsp;</div></div>                    
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
                        <div class="row"><div class="col-xs-12">&nbsp;</div></div>                    
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
                        </div>
                        <div class="row"><div class="col-xs-12">&nbsp;</div></div>                    
                        <div class="row"> 
                            <div class="col-md-12 col-lg-6">
                                <div class="form-group">
                                    <label for="localidad" class="col-md-12 col-lg-3">Localidad</label>
                                    <div class="col-xs-md col-lg-9">
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
                        </div>
                        <div class="col-xs-12"><div class="col-xs-12">&nbsp;</div></div>
                        <div class="row">
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
                        </div>
                        <div class="col-xs-12"><div class="col-xs-12">&nbsp;</div></div>                    
                        <div class="row">                    
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
                        <div class="col-xs-12"><div class="col-xs-12">&nbsp;</div></div>
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

                    </div> <!-- tab-domicilio -->   


                    <div role="tabpanel" class="tab-pane fade" id="tab-credito">

                        
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
                                            <input type="text" class="form-control" id="dias_credito" name="dias_credito" value="${dias_credito}" placeholder="" maxlength="2"  style="text-align:right"/>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div> <!-- row -->    

                    </div> <!-- tab-credito -->   
                </div>  <!-- tab-content -->  
                <!--<div class="row">
                    <div class="col-xs-12 text-right">
                        <button type="submit" id="guardar" class="btn btn-success"><span class="fa fa-floppy-o"></span> Guardar </button>
                        <button type="button" id="cancelar" class="btn btn-danger"><span class="fa fa-times"></span> Cancelar </button>
                        <button type="button" id="nuevo" class="btn btn-default"><span class="fa fa-file-o"></span> Nuevo </button>
                    </div>

                </div> -->
            </div> <!-- nav-tabs-horizontal -->
            
            
            <div class="row">
                <div class="col-xs-12">
                    <div class="form-actions">
                      <button type="submit" class="btn btn-primary width-150" id="guardar" name="guardar"><span class="fa fa-floppy-o"></span>&nbsp;Guardar</button>
                      <button type="button" class="btn btn-default width-150" id="nuevo" name="nuevo"><span class="fa fa fa-file-o"></span>&nbsp;Nuevo</button>
                      <button type="button" class="btn btn-default" id="buscar_proveedor" name="buscar_proveedor"><span class="fa fa-search"></span>&nbsp;Buscar</button>
                      <button type="button" class="btn btn-danger" id="cancelar" name="cancelar"><span class="fa fa-close"></span>&nbsp;Cancelar</button>
                    </div>
                </div>
            </div>
        </div> <!-- panel-body -->
    </div> <!-- panel -->

    



</form>
