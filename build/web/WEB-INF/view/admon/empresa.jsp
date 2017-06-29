<%-- 
    Document   : empresa
    Created on : 20-mar-2017, 4:04:50
    Author     : Rembao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="empresa" class="app.choya.sys.pv.configuracion.Empresa"  />
<!DOCTYPE html>

<script type="text/javascript">

    $(document).ready(function (e) {
        
        $("input[type=text]").focus(function () {
            $(this).select();
        });

        $("input[type=text]").blur(function (e) {
            $(this).val($.trim($(this).val().toUpperCase()));
        });
        
        
        $("#form_empresa").validate({
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
                email: {
                    required: true,
                    email: true
                },
                nombre_fiscal: {
                    maxlength: 250,
                    required: true
                },
                rfc: {
                    maxlength: 13,
                    minlength: 12,
                    required: true
                },
                regimen_fiscal:{
                    required: true
                },
                domicilio_fiscal:{
                    required: true,
                    maxlength: 250
                },
                no_ext_fiscal:{
                    required: true,
                    maxlength: 20
                },
                colonia_fiscal:{
                    required: true,
                    maxlength: 40
                },
                localidad_fiscal:{
                    required: true,
                    maxlength: 150
                },
                codigo_postal_fiscal:{
                    required: true,
                    maxlength:5
                },
                ciudad_fiscal: {
                    required: true,
                    maxlength: 150
                },
                entidad_fiscal:{
                    required: true,
                    maxlength: 50
                },
                pais_fiscal:{
                    required: true,
                    maxlength: 50
                }
            },
            
            messages: {
                nombre: {
                    required: "&nbsp;Por favor introduzca un nombre para la empresa",
                    minlength: "&nbsp;El número de caracteres no es valido, se espera de 3 a 200 caracteres"
                },
                email: "&nbsp;Por favor introduzca un correo electronico valido.",
                nombre_fiscal: "&nbsp;Por favor,introduzca un nombre fiscal.",
                rfc: {
                    required: "&nbsp;Por favor,introduzca el RFC de la empresa.",
                    maxlength: "&nbsp;El número de caracteres no es valido, se espera de 12 a 13 caracteres",
                    minlength: "&nbsp;El número de caracteres no es valido, se espera de 12 a 13 caracteres"
                },
                regimen_fiscal: "&nbsp;Por favor,introduzca un regimen fiscal.",
                domicilio_fiscal: {
                    required: "&nbsp;Por favor,introduzca el domicilio.",
                    maxlength: "&nbsp;El número de caracteres no es valido, se espera de 1 a 250 caracteres"
                },
                no_ext_fiscal: {
                    required: "&nbsp;Por favor,introduzca el no. exterior.",
                    maxlength: "&nbsp;El número de caracteres no es valido, se espera de 1 a 20 caracteres"
                },
                colonia_fiscal: {
                    required: "&nbsp;Por favor,introduzca el nombre de la colonia.",
                    maxlength: "&nbsp;El número de caracteres no es valido, se espera de 1 a 20 caracteres"
                },
                localidad_fiscal: {
                    required: "&nbsp;Por favor,introduzca el nombre de la localidad.",
                    maxlength: "&nbsp;El número de caracteres no es valido, se espera de 1 a 150 caracteres"
                },
                codigo_postal_fiscal: {
                    required: "&nbsp;Por favor,introduzca el código postal.",
                    maxlength: "&nbsp;El número de caracteres no es valido, se espera maximo 5 caracteres"
                },
                ciudad_fiscal: {
                    required: "&nbsp;Por favor,introduzca el nombre de la ciudad.",
                    maxlength: "&nbsp;El número de caracteres no es valido, se espera de 1 a 150 caracteres"
                },
                entidad_fiscal: {
                    required: "&nbsp;Por favor,introduzca el nombre del estado.",
                    maxlength: "&nbsp;El número de caracteres no es valido, se espera de 1 a 50 caracteres"
                },
                pais_fiscal: {
                    required: "&nbsp;Por favor,introduzca el nombre del pais.",
                    maxlength: "&nbsp;El número de caracteres no es valido, se espera de 1 a 50 caracteres"
                }
                

            },
            submitHandler: function (form) {

                $("#modal_mensaje").html("<span class='fa fa-cog fa-spin fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">Espere un momento por favor...</label></div>");
                $("#moperacion").modal("show");

                var id = $("#id").val();
                var nombre = $("#nombre").val();
                var domicilio = $("#domicilio").val();
                var ciudad = $("#ciudad").val();
                var estado = $("#estado").val();
                var codigo_postal = $("#codigo_postal").val();
                var telefono = $("#telefono").val();
                var celular = $("#celular").val();
                var email = $("#email").val();
                var nombre_fiscal = $("#nombre_fiscal").val();
                var rfc = $("#rfc").val();
                var curp = $("#curp").val();
                var adicional = $("#adicional").val();
                var regimen_fiscal = $("#regimen_fiscal").val();
                var domicilio_fiscal = $("#domicilio_fiscal").val();
                var no_ext_fiscal = $("#no_ext_fiscal").val();
                var no_int_fiscal = $("#no_int_fiscal").val();
                var colonia_fiscal = $("#colonia_fiscal").val();
                var localidad_fiscal = $("#localidad_fiscal").val();
                var codigo_postal_fiscal = $("#codigo_postal_fiscal").val();
                var ciudad_fiscal = $("#ciudad_fiscal").val();
                var entidad_fiscal = $("#entidad_fiscal").val();
                var pais_fiscal = $("#pais_fiscal").val();
                
                
                var operacion = "";
                
                if(id == 0){
                    operacion = "guardar";
                }else{
                    operacion = "actualizar";
                }
                
                formData = {
                    id: id,
                    nombre: nombre,
                    domicilio: domicilio,
                    ciudad: ciudad,
                    estado: estado,
                    codigo_postal: codigo_postal,
                    telefono: telefono,
                    celular: celular,
                    email: email,
                    nombre_fiscal: nombre_fiscal,
                    rfc: rfc,
                    curp: curp,
                    adicional: adicional,
                    regimen_fiscal: regimen_fiscal,
                    domicilio_fiscal: domicilio_fiscal,
                    no_ext_fiscal: no_ext_fiscal,
                    no_int_fiscal: no_int_fiscal,
                    colonia_fiscal: colonia_fiscal,
                    localidad_fiscal: localidad_fiscal,
                    codigo_postal_fiscal: codigo_postal_fiscal,
                    ciudad_fiscal: ciudad_fiscal,
                    entidad_fiscal: entidad_fiscal,
                    pais_fiscal: pais_fiscal,
                    operacion: operacion
                };

                $.post("../admon/EmpresaController", formData, function (data) {
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
            <c:forEach var="ebean" items="${empresa.getEmpresa()}" >
                    <c:set var="id" value="${ebean.id}" />
                    <c:set var="serie" value="${ebean.serie}" />
                    <c:set var="estatus" value="${ebean.estatus}" />

                    <c:set var="nombre" value="${ebean.nombre}" />
                    <c:set var="domicilio" value="${ebean.domicilio}" />
                    <c:set var="num_ext" value="${ebean.num_ext}" />
                    <c:set var="num_int" value="${ebean.num_int}" />
                    <c:set var="ciudad" value="${ebean.ciudad}" />
                    <c:set var="estado" value="${ebean.entidad}" />
                    <c:set var="codigo_postal" value="${ebean.codigo_postal}" />
                    <c:set var="telefono" value="${ebean.telefono}" />
                    <c:set var="celular" value="${ebean.celular}" />
                    <c:set var="email" value="${ebean.email}" />

                    <c:set var="nombre_fiscal" value="${ebean.nombre_fiscal}" />
                    <c:set var="rfc" value="${ebean.rfc}" />
                    <c:set var="curp" value="${ebean.curp}" />
                    <c:set var="adicional" value="${ebean.adicional}" />
                    <c:set var="regimen_fiscal" value="${ebean.regimen_fiscal}" />

                    <c:set var="domicilio_fiscal" value="${ebean.domicilio_fiscal}" />
                    <c:set var="num_ext_fiscal" value="${ebean.num_ext_fiscal}" />
                    <c:set var="num_int_fiscal" value="${ebean.num_int_fiscal}" />
                    <c:set var="codigo_postal_fiscal" value="${ebean.codigo_postal_fiscal}" />
                    <c:set var="colonia_fiscal" value="${ebean.colonia_fiscal}" />
                    <c:set var="ciudad_fiscal" value="${ebean.ciudad_fiscal}" />
                    <c:set var="localidad_fiscal" value="${ebean.localidad_fiscal}" />
                    <c:set var="entidad_fiscal" value="${ebean.entidad_fiscal}" />
                    <c:set var="pais_fiscal" value="${ebean.pais_fiscal}" />
                
                
            </c:forEach>
            
            <c:if test="${id == null}" >
                    
                    <c:set var="id" value="" />
                    <c:set var="serie" value="" />
                    <c:set var="estatus" value="" />

                    <c:set var="nombre" value="" />
                    <c:set var="domicilio" value="" />
                    <c:set var="num_ext" value="" />
                    <c:set var="num_int" value="" />
                    <c:set var="ciudad" value="" />
                    <c:set var="estado" value="" />
                    <c:set var="codigo_postal" value="" />
                    <c:set var="telefono" value="" />
                    <c:set var="celular" value="" />
                    <c:set var="email" value="" />

                    <c:set var="nombre_fiscal" value="" />
                    <c:set var="rfc" value="" />
                    <c:set var="curp" value="" />
                    <c:set var="adicional" value="" />
                    <c:set var="regimen_fiscal" value="" />

                    <c:set var="domicilio_fiscal" value="" />
                    <c:set var="num_ext_fiscal" value="" />
                    <c:set var="num_int_fiscal" value="" />
                    <c:set var="codigo_postal_fiscal" value="" />
                    <c:set var="colonia_fiscal" value="" />
                    <c:set var="ciudad_fiscal" value="" />
                    <c:set var="localidad_fiscal" value="" />
                    <c:set var="entidad_fiscal" value="" />
                    <c:set var="pais_fiscal" value="" />
                </c:if>


<div class="row">
    <div class="col-xs-12">&nbsp;</div></div>

    


<form class="form-horizontal" method="post" id="form_empresa" name="form_empresa">

    <div id="operaciones"></div>
    
    
    
        
    <div class="panel panel-default">
        
        <div class="panel-heading">
                <h3>Empresa</h3>
        </div>
        <div class="panel-body">
            <div class="nav-tabs-horizontal">
                 <ul class="nav nav-tabs" role="tablist">

                     <li class="nav-item">
                        <a class="nav-link active"  href="javascript: void(0);" data-toggle="tab" data-target="#tab-general" role="tab">
                            <i class="icmn-magazine"></i>
                            Datos Generales
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="javascript: void(0);" data-toggle="tab" data-target="#tab-fiscal" role="tab">
                            <i class="icmn-file-text2"></i>
                            Datos Fiscales
                        </a>
                    </li>
                    
                    
                    

                </ul> <!-- nav nav-tabs -->
            </div>    
                <!-- contenido del panel -->
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane fade in active" id="tab-general">
                        <input type="hidden" id="id" name="id" value="${id}" />
                        
                        <div class="row"><div class="col-xs-12">&nbsp;</div></div>
                        <div class="row">
                            
                            
                            
                            <div class="col-xs-12 col-lg-6">
                                
                                <div class="form-group">
                                    <div class="col-md-12 col-lg-3">
                                        <label for="no_usuario" class="form-control-label">Núm.<label style="color: red">*</label></label>
                                    </div>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="no_usuario" name="no_usuario" value="${id}" placeholder="" size="20" maxlength="20" disabled="disabled"/>

                                        </div>
                                    </div>
                                </div>
                                
                                
                            </div>
                            <div class="col-xs-12 col-lg-6">
                                
                                <div class="form-group">
                                    <div class="col-md-12 col-lg-3">
                                        <label for="nombre" class="form-control-label">Nombre<label style="color: red">*</label></label>
                                    </div>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="nombre" name="nombre" value="${nombre}" placeholder="" size="40" maxlength="250"/>

                                        </div>
                                    </div>
                                </div>
                                
                                
                                
                            </div>
                        </div>
                        <div class="row"><div class="col-xs-12">&nbsp;</div></div>
                        <div class="row">                    
                            <div class="col-xs-12 col-lg-6">
                                <div class="form-group">
                                    <label for="domicilio" class="col-md-12 col-lg-3">Domicilio</label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="domicilio" name="domicilio" value="${domicilio}" placeholder="" size="40" maxlength="250" />

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-lg-6">
                                <div class="form-group">
                                    <label for="ciudad" class="col-md-12 col-lg-3">Ciudad</label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="ciudad" name="ciudad" value="${ciudad}" placeholder="" size="40" maxlength="150" />

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                                            
                        <div class="row"><div class="col-xs-12">&nbsp;</div></div>
                        <div class="row">                    
                                            
                            <div class="col-xs-12 col-lg-6">
                                <div class="form-group">
                                    <label for="estado" class="col-md-12 col-lg-3">Estado</label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="estado" name="estado" value="${estado}" placeholder="" size="40" maxlength="100" />

                                        </div>
                                    </div>
                                </div>
                            </div>
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
                                    <label for="telefono" class="col-md-12 col-lg-3">Teléfono</label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="telefono" name="telefono" value="${telefono}" placeholder=""  size="20" maxlength="20" />

                                        </div>
                                    </div>
                                </div>
                            </div> <!-- fin telefono -->
                            <div class="col-md-12 col-lg-6">
                                <div class="form-group">
                                    <label for="celular" class="col-md-12 col-lg-3">Celular</label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="celular" name="celular" value="${celular}" placeholder=""  size="20" maxlength="20" />

                                        </div>
                                    </div>
                                </div>
                            </div><!-- celular -->
                        </div>    
                        <div class="row"><div class="col-xs-12">&nbsp;</div></div>
                        <div class="row">     
                            <div class="col-md-12 col-lg-6" >
                                <div class="form-group">
                                    <label for="email" class="col-md-12 col-lg-3">Correo Electrónico <label style="color: red">*</label></label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="email" name="email" value="${email}" placeholder="" size="80" maxlength="150" />

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div> <!-- fin del row principal contenido -->
                
                    </div> <!-- tab-general -->
                    <div role="tabpanel" class="tab-pane fade" id="tab-fiscal">
                        <div class="row"><div class="col-xs-12">&nbsp;</div></div>
                        <div class="row">
                            <div class="col-xs-12 col-lg-6">
                                    <div class="form-group">
                                        <label for="nombre_fiscal" class="col-md-12 col-lg-3">Nombre<label style="color: red">*</label></label>
                                        <div class="col-md-12 col-lg-9">
                                            <div class="controls form-inline">
                                                <input type="text" class="form-control" id="nombre_fiscal" name="nombre_fiscal" value="${nombre_fiscal}" placeholder="" size="50" maxlength="250" />

                                            </div>
                                        </div>
                                    </div>
                            </div>
                            <div class="col-xs-12 col-lg-6">
                                <div class="form-group">
                                    <label  class="col-md-12 col-lg-3">&nbsp;</label>
                                </div>    
                            </div>
                        </div>
                        <div class="row"><div class="col-xs-12">&nbsp;</div></div>
                                        
                        <div class="row">                        
                            <div class="col-xs-12 col-lg-6">
                                    <div class="form-group">
                                        <label for="rfc" class="col-md-12 col-lg-3">R.F.C. <label style="color: red">*</label></label>
                                        <div class="col-md-12 col-lg-9">
                                            <div class="controls form-inline">
                                                <input type="text" class="form-control" id="rfc" name="rfc" value="${rfc}" placeholder="" size="30" maxlength="13" />

                                            </div>
                                        </div>
                                    </div>
                            </div>

                            <div class="col-xs-12 col-lg-6">
                                <div class="form-group">
                                    <label for="curp" class="col-md-12 col-lg-3">CURP </label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="curp" name="curp" value="${curp}" placeholder="" size="30" maxlength="18" />

                                        </div>
                                    </div>
                                </div>
                            </div>                    
                        </div>
                        <div class="row"><div class="col-xs-12">&nbsp;</div></div>                    
                        <div class="row">
                            <div class="col-xs-12 col-lg-6">
                                <div class="form-group">
                                    <label for="adicinal" class="col-md-12 col-lg-3">Info Adicional</label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="adicional" name="adicional" value="${adicional}" placeholder="" size="100" maxlength="250" />

                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="row"><div class="col-xs-12">&nbsp;</div></div>                    
                        <div class="row">
                            <div class="col-xs-12 col-lg-6">
                                <div class="form-group">
                                    <label for="regimen_fiscal" class="col-md-12 col-lg-3">Régimen Fiscal <label style="color: red">*</label></label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="regimen_fiscal" name="regimen_fiscal" value="${regimen_fiscal}" placeholder="" size="100" maxlength="250" />

                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="row"><div class="col-xs-12"><hr style="width: 95%; color: #EEEEEE; height: 0.2px; background-color:#EEEEEE;"></div></div>                    
                        
                        <div class="row">
                            <div class="col-xs-12 col-lg-6">
                                <div class="form-group">
                                    <label for="domicilio_fiscal" class="col-md-12 col-lg-3">Domicilio <label style="color: red">*</label></label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="domicilio_fiscal" name="domicilio_fiscal" value="${domicilio_fiscal}" placeholder="" size="100" maxlength="250" />

                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="row"><div class="col-xs-12">&nbsp;</div></div>                    
                        <div class="row">
                            <div class="col-md-12 col-lg-6" >
                                <div class="form-group">
                                    <label for="no_ext_fiscal" class="col-md-12 col-lg-3">No. Exterior <label style="color: red">*</label></label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="no_ext_fiscal" name="no_ext_fiscal" value="${num_ext_fiscal}" placeholder="" size="20" maxlength="20" />

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12 col-lg-6">
                                <div class="form-group">
                                    <label for="no_int_fiscal" class="col-md-12 col-lg-3">No. Interior</label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="no_int_fiscal" name="no_int_fiscal" value="${num_int_fiscal}" placeholder="" maxlength="20"  />

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row"><div class="col-xs-12">&nbsp;</div></div>                    
                        <div class="row">
                            <div class="col-md-12 col-lg-6" >
                                <div class="form-group">
                                    <label for="colonia_fiscal" class="col-md-12 col-lg-3">Colonia <label style="color: red">*</label></label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="colonia_fiscal" name="colonia_fiscal" value="${colonia_fiscal}" placeholder="" size="40" maxlength="150" />

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12 col-lg-6">
                                <div class="form-group">
                                    <label for="localidad_fiscal" class="col-md-12 col-lg-3">Localidad <label style="color: red">*</label></label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="localidad_fiscal" name="localidad_fiscal" value="${localidad_fiscal}" placeholder="" size="40" maxlength="150"  />

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row"><div class="col-xs-12">&nbsp;</div></div>                    
                        <div class="row">
                            <div class="col-md-12 col-lg-6" >
                                <div class="form-group">
                                    <label for="codigo_postal_fiscal" class="col-md-12 col-lg-3">Código Postal <label style="color: red">*</label></label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="codigo_postal_fiscal" name="codigo_postal_fiscal" value="${codigo_postal_fiscal}" placeholder="" size="20" maxlength="5" />

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12 col-lg-6">
                                <div class="form-group">
                                    <label for="ciudad_fiscal" class="col-md-12 col-lg-3">Ciudad <label style="color: red">*</label></label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="ciudad_fiscal" name="ciudad_fiscal" value="${ciudad_fiscal}" placeholder="" size="40" maxlength="150"  />

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row"><div class="col-xs-12">&nbsp;</div></div>                    
                        <div class="row">
                            <div class="col-md-12 col-lg-6" >
                                <div class="form-group">
                                    <label for="entidad_fiscal" class="col-md-12 col-lg-3">Estado <label style="color: red">*</label></label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="entidad_fiscal" name="entidad_fiscal" value="${entidad_fiscal}" placeholder="" size="40" maxlength="50" />

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12 col-lg-6">
                                <div class="form-group">
                                    <label for="apis_fiscal" class="col-md-12 col-lg-3">Pais <label style="color: red">*</label></label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="pais_fiscal" name="pais_fiscal" value="${pais_fiscal}" placeholder="" size="40" maxlength="50"  />

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>                    
                    </div> <!-- tab-fiscal -->
                </div> <!-- fin tab-content -->
                
                
                <div class="row">
                <div class="col-xs-12">
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary width-150" id="guardar" name="guardar">Guardar</button>
                        
                    </div>
                    
                </div>
            </div>
        </div> <!-- fin del panel-body -->
    </div> <!-- fin del panel panel-default -->
    
    
    
</form>    