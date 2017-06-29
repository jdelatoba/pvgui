<%-- 
    Document   : producto
    Created on : 22-abr-2017, 15:06:10
    Author     : Rembao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="ab" class="app.choya.sys.pv.catalogo.Articulo"  />
<!DOCTYPE html>
<%-- <jsp:include  page="../jspf/libs.jsp"></jsp:include> --%>
<script type="text/javascript">
    
    $(document).ready(function(){
        
        $("#clave").focus();
        
        $("input[type=text]").focus(function () {
            $(this).select();
        });
        
        

        $("input[type=text]").blur(function (e) {
            $(this).val($.trim($(this).val().toUpperCase()));
        });
        
        $("#factor").numeric();
        $("#inventario_minimo").numeric();
        $("#inventario_maximo").numeric();
        $("#precio_compra").numeric();
        
        
        $("#factor").blur(function(){
           
           var neto = $('input[name=neto]:checked').val();
           formData = {
                
                precio_compra : $("#precio_compra").val(),
                factor : $("#factor").val(),
                neto : neto,
                margen_utilidad1: $("#margen_utilidad1").val(),
                margen_utilidad2: $("#margen_utilidad2").val(),
                margen_utilidad3: $("#margen_utilidad3").val(),
                margen_utilidad4: $("#margen_utilidad4").val(),
                operacion : "calcula_precio_unidad"
                
            };
            
            $.post("../pv/ArticuloController", formData, function (data) {
                $("#calculo_precio").html(data);
                
                $("#precio_unidad_compra").val($("#unidad_pcompra").val());
                $("#precio_unidad_venta").val($("#unidad_pventa").val());
                
                $("#precio_venta1").val($("#precio_venta_utilidad1").val());
                $("#precio_venta_neto1").val($("#precio_venta_neto_utilidad1").val());
                $("#precio_venta2").val($("#precio_venta_utilidad2").val());
                $("#precio_venta_neto2").val($("#precio_venta_neto_utilidad2").val());
                $("#precio_venta3").val($("#precio_venta_utilidad3").val());
                $("#precio_venta_neto3").val($("#precio_venta_neto_utilidad3").val());
                $("#precio_venta4").val($("#precio_venta_utilidad4").val());
                $("#precio_venta_neto4").val($("#precio_venta_neto_utilidad4").val());
            });
        
        });
        
        
        $("#button_neto label").click(function(){
            
            var valor = $(this).attr('for');
            
            var neto = 0;
            if(valor == "con_impuesto"){
                neto = 1;
            }else{
                neto = 0;
            }
            formData = {
                
                precio_compra : $("#precio_compra").val(),
                factor : $("#factor").val(),
                neto : neto,
                margen_utilidad1: $("#margen_utilidad1").val(),
                margen_utilidad2: $("#margen_utilidad2").val(),
                margen_utilidad3: $("#margen_utilidad3").val(),
                margen_utilidad4: $("#margen_utilidad4").val(),
                operacion : "calcula_precio_unidad"
                
            };
            
            $.post("../pv/ArticuloController", formData, function (data) {
                $("#calculo_precio").html(data);
                
                $("#precio_unidad_compra").val($("#unidad_pcompra").val());
                $("#precio_unidad_venta").val($("#unidad_pventa").val());
                
                $("#precio_venta1").val($("#precio_venta_utilidad1").val());
                $("#precio_venta_neto1").val($("#precio_venta_neto_utilidad1").val());
                $("#precio_venta2").val($("#precio_venta_utilidad2").val());
                $("#precio_venta_neto2").val($("#precio_venta_neto_utilidad2").val());
                $("#precio_venta3").val($("#precio_venta_utilidad3").val());
                $("#precio_venta_neto3").val($("#precio_venta_neto_utilidad3").val());
                $("#precio_venta4").val($("#precio_venta_utilidad4").val());
                $("#precio_venta_neto4").val($("#precio_venta_neto_utilidad4").val());
                
            });
        });
        
        
        
        $("#precio_compra").blur(function (e) {
            if ($("#precio_compra").val() == "") {
                $("#precio_compra").val("0.00");
            } else {

                $("#precio_compra").formatCurrency();
                var precio_compra = $("input[id='precio_compra']").val();
                $("input[id='precio_compra']").val(precio_compra.replace("$", ""));
            }
            
            var neto = $('input[name=neto]:checked').val();
            
            formData = {
                
                precio_compra : $("#precio_compra").val(),
                factor : $("#factor").val(),
                neto : neto,
                operacion : "calcula_precio_unidad"
                
            };
            
            $.post("../pv/ArticuloController", formData, function (data) {
                $("#calculo_precio").html(data);
                
                $("#precio_unidad_compra").val($("#unidad_pcompra").val());
                
                
                
                $("#precio_unidad_venta").val($("#unidad_pventa").val());
                
                
            });
            
            
            
            
        });
        
        
        
        
        
        
        $("#inventario").ionRangeSlider({
            type: "double",
            min: 0,
            max: 1000,
            from: $("#inventario_minimo").val(),
            to: $("#inventario_maximo").val(),
            from_shadow: true,
            to_shadow: true,
            grid: true,
            grid_num: 10,
            onStart: function (data) {
                $("#inventario_minimo").val(data.from);
                $("#inventario_maximo").val(data.to);
            },
            onChange:  function (data) {
                $("#inventario_minimo").val(data.from);
                $("#inventario_maximo").val(data.to);
            },
            onFinish:  function (data) {
                $("#inventario_minimo").val(data.from);
                $("#inventario_maximo").val(data.to);
            },
            onUpdate:  function (data) {
                $("#inventario_minimo").val(data.from);
                $("#inventario_maximo").val(data.to);
            }
        });
        
        $("#margen_utilidad1").ionRangeSlider({
            min: 0,
            max: 100,
            step: 1,
            grid: true,
            grid_num: 10,
            onFinish:  function (data) {
                
                //calcula_margen_utilidad
                var neto = $('input[name=neto]:checked').val();
            
                formData = {
                    margen_utilidad : $("#margen_utilidad1").val(),
                    precio_unidad_venta : $("#precio_unidad_venta").val(),
                    operacion : "calcular_precio_porcentaje"

                };
            
                $.post("../pv/ArticuloController", formData, function (data) {
                    $("#calculo_utilidad").html(data);

                    $("#precio_venta1").val($("#precio_venta_utilidad").val());
                    $("#precio_venta_neto1").val($("#precio_venta_neto_utilidad").val());

                });
                
            }
        });
        
        $("#margen_utilidad2").ionRangeSlider({
            min: 0,
            max: 100,
            step: 1,
            grid: true,
            grid_num: 10,
            onFinish:  function (data) {
                
                //calcula_margen_utilidad
                var neto = $('input[name=neto]:checked').val();
            
                formData = {
                    margen_utilidad : $("#margen_utilidad2").val(),
                    precio_unidad_venta : $("#precio_unidad_venta").val(),
                    operacion : "calcular_precio_porcentaje"

                };
            
                $.post("../pv/ArticuloController", formData, function (data) {
                    $("#calculo_utilidad").html(data);

                    $("#precio_venta2").val($("#precio_venta_utilidad").val());
                    $("#precio_venta_neto2").val($("#precio_venta_neto_utilidad").val());

                });
                
            }
        });
        
        $("#margen_utilidad3").ionRangeSlider({
            min: 0,
            max: 100,
            step: 1,
            grid: true,
            grid_num: 10,
            onFinish:  function (data) {
                
                //calcula_margen_utilidad
                var neto = $('input[name=neto]:checked').val();
            
                formData = {
                    margen_utilidad : $("#margen_utilidad3").val(),
                    precio_unidad_venta : $("#precio_unidad_venta").val(),
                    operacion : "calcular_precio_porcentaje"

                };
            
                $.post("../pv/ArticuloController", formData, function (data) {
                    $("#calculo_utilidad").html(data);

                    $("#precio_venta3").val($("#precio_venta_utilidad").val());
                    $("#precio_venta_neto3").val($("#precio_venta_neto_utilidad").val());

                });
                
            }
        });
        
        $("#margen_utilidad4").ionRangeSlider({
            min: 0,
            max: 100,
            step: 1,
            grid: true,
            grid_num: 10,
            onFinish:  function (data) {
                
                //calcula_margen_utilidad
                var neto = $('input[name=neto]:checked').val();
            
                formData = {
                    margen_utilidad : $("#margen_utilidad4").val(),
                    precio_unidad_venta : $("#precio_unidad_venta").val(),
                    operacion : "calcular_precio_porcentaje"

                };
            
                $.post("../pv/ArticuloController", formData, function (data) {
                    $("#calculo_utilidad").html(data);

                    $("#precio_venta4").val($("#precio_venta_utilidad").val());
                    $("#precio_venta_neto4").val($("#precio_venta_neto_utilidad").val());

                });
                
            }
        });
        
        $("#btn_categoria").click(function(){
            
            $("#modal_mensaje").html("<span class='fa fa-cog fa-spin fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">Espere un momento por favor...</label></div>");
            $("#moperacion").modal("show");
            
            formData = {
                        categoria_id :$("#categoria_id").val(),
                        operacion: "cargar_categorias"
                    };

            $.post("../pv/ArticuloController", formData, function (data) {
                $("#modal_mensaje").html(data);
            });
            
            
        });  //
        
        
        $("#btn_unidad_compra").click(function(){
            
            $("#modal_mensaje").html("<span class='fa fa-cog fa-spin fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">Espere un momento por favor...</label></div>");
            $("#moperacion").modal("show");
            
            formData = {
                        unidad_compra_id :$("#unidad_compra_id").val(),
                        operacion: "cargar_unidades_compra"
                    };

            $.post("../pv/ArticuloController", formData, function (data) {
                $("#modal_mensaje").html(data);
            });
            
            
        });
        
        $("#btn_unidad_venta").click(function(){
            
            $("#modal_mensaje").html("<span class='fa fa-cog fa-spin fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">Espere un momento por favor...</label></div>");
            $("#moperacion").modal("show");
            
            formData = {
                        unidad_venta_id :$("#unidad_venta_id").val(),
                        operacion: "cargar_unidades_venta"
                    };

            $.post("../pv/ArticuloController", formData, function (data) {
                $("#modal_mensaje").html(data);
            });
            
            
        });
        
        $("#aceptar").click(function(e){
             $("#moperacion").modal("hide");
        });
        
        
        $("#form_articulo").validate({
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
                    maxlength: 50
                }, 
                descripcion: {
                    required: true,
                    maxlength: 150
                },
                categoria: {
                    required: true
                },
                unidad_compra: {
                    required: true
                    
                },
                unidad_venta: {
                    required: true
                },
                factor: {
                    required: true,
                    number: true
                },
                inventario_minimo: {
                    required: true,
                    number: true
                },
                inventario_maximo: {
                    required: true,
                    number:true
                },
                precio_compra: {
                    required: true,
                    number: true
                },
                precio_unidad_compra: {
                    required: true
                },
                precio_unidad_venta: {
                    required: true
                },
                margen_utilidad1: {
                    required: true,
                    number: true
                },
                margen_utilidad2: {
                    required: true,
                    number: true
                },
                margen_utilidad3: {
                    required: true,
                    number: true
                },
                margen_utilidad4: {
                    required: true,
                    number: true
                },
                precio_venta1:{
                    required: true,
                    number: true
                },
                precio_venta2:{
                    required: true,
                    number: true
                },
                precio_venta3:{
                    required: true,
                    number: true
                },
                precio_venta4:{
                    required: true,
                    number: true
                },
                precio_venta_neto1: {
                    required: true,
                    number: true
                },
                precio_venta_neto2: {
                    required: true,
                    number: true
                },
                precio_venta_neto3: {
                    required: true,
                    number: true
                },
                precio_venta_neto4: {
                    required: true,
                    number: true
                }
            },
            messages: {
                clave: "&nbsp;Por favor introduzca la clave del articulo"
            },
             submitHandler: function (form) {

                $("#modal_mensaje").html("<span class='fa fa-cog fa-spin fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">Espere un momento por favor...</label></div>");
                $("#moperacion").modal("show");
                
                var id = $("#id").val();
                if(id == 0){
                    operacion = "guardar";
                }else{
                    operacion = "actualizar";
                }
                formData = {
                    
                    id: id,
                    clave : $("#clave").val(),
                    clave_alterna : $("#clave_alterna").val(),
                    descripcion : $("#descripcion").val(),
                    servicio : $('input[name=servicio]:checked').val(),
                    categoria_id : $("#categoria_id").val(),
                    departamento_id : $("#departamento_id").val(),
                    unidad_compra_id : $("#unidad_compra_id").val(),
                    unidad_venta_id : $("#unidad_venta_id").val(),
                    factor : $("#factor").val(),
                    inventario_minimo : $("#inventario_minimo").val(),
                    inventario_maximo : $("#inventario_maximo").val(),
                    precio_compra : $("#precio_compra").val(),
                    neto :  $('input[name=neto]:checked').val(),
                    precio_unidad_compra : $("#precio_unidad_compra").val(),
                    precio_unidad_venta : $("#precio_unidad_venta").val(),
                    margen_utilidad1 : $("#margen_utilidad1").val(),
                    margen_utilidad2 : $("#margen_utilidad2").val(),
                    margen_utilidad3 : $("#margen_utilidad3").val(),
                    margen_utilidad4 : $("#margen_utilidad4").val(),
                    precio_venta1 : $("#precio_venta1").val(),
                    precio_venta2 : $("#precio_venta2").val(),
                    precio_venta3 : $("#precio_venta3").val(),
                    precio_venta4 : $("#precio_venta4").val(),
                    precio_venta_neto1 : $("#precio_venta_neto1").val(),
                    precio_venta_neto2 : $("#precio_venta_neto2").val(),
                    precio_venta_neto3 : $("#precio_venta_neto3").val(),
                    precio_venta_neto4 : $("#precio_venta_neto4").val(),
                    caracteristica : $('textarea#caracteristica').val(),
                    operacion : operacion
                    
                };
                
                $.post("../pv/ArticuloController",formData,function (data) {
                    
                    $("#operaciones").html(data);
                    
                    if ((parseInt($("#resultado").val()) !== 0)) {
                        $("#modal_mensaje").html("<span style=\"color: green\" class=\"fa fa-check-circle fa-3x\"></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">El registro se ha guardado correctamente.</label></div>");
                        if(operacion == "guardar"){
                            $("#id").val($("#resultado").val());
                        }
                        //$("#no_proveedor").val($("#resultado").val());
                    } else {
                        $("#modal_mensaje").html("<span style=\"color: red\" class=\"fa fa-exclamation-triangle fa-3x\"></span>&nbsp;<label for=\"modal_mensaje\" style=\"font-size: 16px; font-weight: bolder\">El sistema ha lanzado el siguiente error: Codigo Error: "+$("#codigo_error").val() +" "+ $("#mensaje").val() + ".</label></div>");

                    }
                    
                }); // fin del post
            }
        });
        
        
        
        var table_compatible = "";
        var id_articulo_compatible = $("#id").val();
        table_compatible = $('#tabla_articulos_compatible').DataTable( {
                    "processing": true,
                        "ajax": {
                           "url": "../pv/ArticuloListaCompatibleJSON",
                            "type": "POST",
                            "data": {
                                "id": id_articulo_compatible
                            }
                           
                        },
                        "scrollY":        400,
                        "scrollCollapse": true,
                        "language": {
                                    "lengthMenu": "Mostrar _MENU_ registros por página",
                                    "zeroRecords": "No se encontro información que mostrar",
                                    "info": "Mostrando página _PAGE_ de _PAGES_",
                                    "infoEmpty": "",
                                    "infoFiltered": "(filtrado de un total de _MAX_ registros)",
                                    "search": "Buscar",
                                    "processing": "Cargando. Por favor espere un momento...",
                                    "loadingRecords": "Espere un momento por favor - Cargando datos...",
                                     "decimal": ".",
                                     "thousands": ",",
                                    "paginate": {
                                        "first": "Primer",
                                        "last": "Ultimo",
                                        "next": "Siguiente",
                                        "previous": "Anterior"
                                    }
                        },
                        "columns": [
                        
                        {
                            "className": 'id-control',
                            "data": "id"
                        },
                        {"data": "clave"},
                        {"data": "descripcion"},
                        {"data": "categoria"},
                        {"data": "departamento"}


                    ],
                    "order": [[1, 'asc']]
                
                
            }); 
            
            
            
            $("#buscar_articulo").click(function(e){
                
                
                formData = {
                    operacion: "buscar_articulo"

                };

                $.post("../pv/ArticuloController", formData, function (data) {
                    $("#function-container").html(data);
                });
            });
            
            $("#buscar_articulo_compatible").click(function(e){
                $("#mcompatible").modal("show");
                formData = {
                    operacion: "buscar_articulo_compatible"
                    
                };
                
                $.post("../pv/ArticuloController",formData, function(data){
                   $("#mensaje_compatible").html(data); 
                });
        
            });
            
            
            
         $("#nuevo").click(function(e){
            formData = {
                    funcion: "nuevo_articulo"

                };

            $.post("../pv/ArticuloController", formData, function (data) {
                $("#function-container").html(data);
            }); 
             
         });   
        
        
    });
    
    
    

    
    
</script>

<style type="text/css">
    
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
<!--<form method="post" id="form_navega" name="form_navega">
    <input type="hidden" id="funcion" name="funcion" />
</form> fin del form_navega -->

<form class="form-horizontal" method="post" id="form_articulo" name="form_articulo">
    
    
    <c:choose>
        <c:when test="${requestScope.id_articulo != null &&  requestScope.id_articulo != 0}">
            <c:forEach var="abean" items="${ab.getArticulo(requestScope.id_articulo)}" >
                
                <c:set var="id" value="${abean.id}" />
                <c:set var="clave" value="${abean.clave}" />
                <c:set var="clave_alterna" value="${abean.clave_alterna}" />
                <c:set var="servicio" value="${abean.servicio}" />
                <c:set var="descripcion" value="${abean.descripcion}" />
                <c:set var="categoria_id" value="${abean.categoria_id}" />
                <c:set var="categoria" value="${abean.categoria}" />
                <c:set var="departamento_id" value="${abean.departamento_id}" />
                <c:set var="departamento" value="${abean.departamento}" />
                <c:set var="unidad_compra_id" value="${abean.unidad_compra_id}" />
                <c:set var="unidad_compra" value="${abean.unidad_compra}" />
                <c:set var="unidad_venta_id" value="${abean.unidad_venta_id}" />
                <c:set var="unidad_venta" value="${abean.unidad_venta}" />
                <c:set var="factor" value="${abean.factor}" />
                <c:set var="precio_compra_sin_impuesto1_label" value="${abean.unidad_compra}" />
                <c:set var="precio_compra_sin_impuesto2_label" value="${abean.unidad_venta}" />
                <c:set var="inventario_minimo" value="${abean.inventario_minimo}" />
                <c:set var="inventario_maximo" value="${abean.inventario_maximo}" />
                <c:set var="localizacion" value="${abean.localizacion}" />
                <c:set var="impuesto" value="${abean.impuesto}" />
                <c:set var="precio_compra" value="${abean.precio_compra}" />
                <c:set var="neto" value="${abean.neto}" />
                <c:set var="precio_unidad_compra" value="${abean.precio_unidad_compra}" />
                <c:set var="precio_unidad_venta" value="${abean.precio_unidad_venta}" />
                <c:set var="margen_utilidad1" value="${abean.margen_utilidad1}" />
                <c:set var="margen_utilidad2" value="${abean.margen_utilidad2}" />
                <c:set var="margen_utilidad3" value="${abean.margen_utilidad3}" />
                <c:set var="margen_utilidad4" value="${abean.margen_utilidad4}" />
                <c:set var="precio_venta1" value="${abean.precio_venta1}" />
                <c:set var="precio_venta2" value="${abean.precio_venta2}" />
                <c:set var="precio_venta3" value="${abean.precio_venta3}" />
                <c:set var="precio_venta4" value="${abean.precio_venta4}" />
                <c:set var="precio_venta_neto1" value="${abean.precio_venta_neto1}" />
                <c:set var="precio_venta_neto2" value="${abean.precio_venta_neto2}" />
                <c:set var="precio_venta_neto3" value="${abean.precio_venta_neto3}" />
                <c:set var="precio_venta_neto4" value="${abean.precio_venta_neto4}" />
                <c:set var="caracteristica" value="${abean.caracteristica}" />
                <c:set var="serie" value="${abean.serie}" />
                
                
            </c:forEach>
            
        </c:when>
        
            <c:otherwise>
                <c:set var="id" value="0" />
                <c:set var="clave" value="" />
                <c:set var="clave_alterna" value="" />
                <c:set var="servicio" value="0" />
                <c:set var="descripcion" value="" />
                <c:set var="categoria_id" value="0" />
                <c:set var="categoria" value="" />
                <c:set var="departamento_id" value="0" />
                <c:set var="departamento" value="" />
                <c:set var="unidad_compra_id" value="0" />
                <c:set var="unidad_compra" value="" />
                <c:set var="unidad_venta_id" value="0" />
                <c:set var="unidad_venta" value="" />
                <c:set var="factor" value="1" />
                <c:set var="precio_compra_sin_impuesto1_label" value="PIEZA" />
                <c:set var="precio_compra_sin_impuesto2_label" value="PIEZA" />
                <c:set var="inventario_minimo" value="10" />
                <c:set var="inventario_maximo" value="200" />
                <c:set var="localizacion" value="" />
                <c:set var="impuesto" value="1" />
                <c:set var="precio_compra" value="0.00" />
                <c:set var="neto" value="1" />
                <c:set var="precio_unidad_compra" value="0.00" />
                <c:set var="precio_unidad_venta" value="0.00" />
                <c:set var="margen_utilidad1" value="0" />
                <c:set var="margen_utilidad2" value="0" />
                <c:set var="margen_utilidad3" value="0" />
                <c:set var="margen_utilidad4" value="0" />
                <c:set var="precio_venta1" value="0.00" />
                <c:set var="precio_venta2" value="0.00" />
                <c:set var="precio_venta3" value="0.00" />
                <c:set var="precio_venta4" value="0.00" />
                <c:set var="precio_venta_neto1" value="0.00" />
                <c:set var="precio_venta_neto2" value="0.00" />
                <c:set var="precio_venta_neto3" value="0.00" />
                <c:set var="precio_venta_neto4" value="0.00" />
                <c:set var="caracteristica" value="" />
                <c:set var="serie" value="1" />
                
                
            </c:otherwise>
    </c:choose>    

    
    <div id="operaciones"></div>
    <div id="calculo_precio"></div>
    <div id="calculo_utilidad"></div>
    <div class="panel panel-default">
        <div class="nav-tabs-horizontal">
        <div class="panel-heading">
                <h3>Artículos</h3>
        </div>
        <div class="panel-body">
            <!-- inicio nav-tabs -->
            <ul class="nav nav-tabs" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active"  href="javascript: void(0);" data-toggle="tab" data-target="#tab-datos" role="tab">
                            <i class="icmn-magazine"></i>
                            Datos
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="javascript: void(0);" data-toggle="tab" data-target="#tab-caracteristicas" role="tab">
                            <i class="icmn-file-text2"></i>
                            Caracteristicas
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="javascript: void(0);" data-toggle="tab" data-target="#tab-compatibles" role="tab">
                            <i class="icmn-file-text2"></i>
                            Compatibles
                        </a>
                    </li>
                    
            </ul> <!-- nav nav-tabs -->
            <div class="tab-content padding-vertical-20">
                
                <div role="tabpanel" class="tab-pane fade in active" id="tab-datos">
                    
                    
                    <input type="hidden" id="id" name="id" value="${id}" />
                    
                 
                                        
                <div class="row">
                    <div class="col-xs-12 col-md-6">
                        <div class="form-group">
                            <div class="col-md-12 col-lg-3">
                                <label for="no_articulo" class="form-control-label">Clave<label style="color: red">*</label></label>
                            </div>
                            <div class="col-md-12 col-lg-9">
                                <div class="controls form-inline">
                                    <input type="text" class="form-control" id="clave" name="clave" value="${clave}" placeholder="" size="20" maxlength="20"/>

                                </div>
                            </div>
                        </div>
                    </div>
                                   
                    <div class="col-xs-12 col-md-6">
                        <div class="form-group">
                            <div class="col-md-12 col-lg-3">
                                <label for="no_articulo" class="form-control-label">Clave Alterno</label>
                            </div>
                            <div class="col-md-12 col-lg-9">
                                <div class="controls form-inline">
                                    <input type="text" class="form-control" id="clave_alterna" name="clave_alterna" value="${clave_alterna}" placeholder="" size="20" maxlength="20"/>

                                </div>
                            </div>
                        </div>
                    </div>                
                </div>
                                    
                <div class="row">
                    <div class="col-xs-12">&nbsp;</div>
                </div>                    
                <div class="row">
                    <div class="col-xs-12 col-md-9">
                        <div class="form-group">
                            <label for="descripcion" class="col-xs-12 col-lg-2">Descripción<label style="color: red">*</label></label>
                            <div class="col-md-12 col-lg-9">
                                <div class="controls form-inline">
                                    <input type="text" class="form-control" id="descripcion" name="descripcion" value="${descripcion}" placeholder="" size="70" maxlength="150"/>

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-3">
                        <div class="btn-group" data-toggle="buttons">
                            <c:choose>
                            <c:when test="${servicio == 0}" >
                                <label class="btn btn-default-outline">
                                    <input type="radio" name="servicio" value="1">
                                    Servicio
                                </label>
                                <label class="btn btn-default-outline active">
                                    <input type="radio" name="servicio" value="0" checked>
                                    Artículo
                                </label>
                            </c:when>
                            <c:otherwise>
                                    <label class="btn btn-default-outline active">
                                        <input type="radio" name="servicio" value="1" checked>
                                    Servicio
                                </label>
                                <label class="btn btn-default-outline">
                                    <input type="radio" name="servicio" value="0" >
                                    Artículo
                                </label>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>                

                </div>
                <div class="row">
                    <div class="col-xs-12">&nbsp;</div>
                </div>                 
                <div class="row">
                    <div class="col-xs-12 col-lg-6">
                        <div class="form-group">
                            <label for="categoria_id" class="col-md-12 col-lg-3">Categoria<label style="color: red">*</label></label>
                            <div class="col-md-12 col-lg-9">
                                <div class="controls form-inline">
                                    <div class="input-group">
                                        <input type="hidden" class="form-control" id="categoria_id" name="categoria_id" value="${categoria_id}" placeholder="" size="30" maxlength="50"/>
                                        <input type="text" class="form-control" id="categoria" name="categoria" value="${categoria}" placeholder="" size="30" maxlength="50" readonly="readonly"/>
                                        <span class="input-group-btn">
                                            <button class="btn btn-default"  id="btn_categoria" type="button">Ir</button>
                                        </span>
                                    </div> <!-- fin input group -->
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-lg-6">
                        <div class="form-group">
                            <label for="Departamento" class="col-md-12 col-lg-3">Departamento</label>
                            <div class="col-md-12 col-lg-9">
                                <div class="controls form-inline">
                                    <input type="hidden" class="form-control" id="departamento_id" name="departamento_id" value="${departamento_id}" placeholder="" size="30" maxlength="50"/>
                                        <input type="text" class="form-control" id="departamento" name="departamento" value="${departamento}" placeholder="" size="30" maxlength="50" readonly="readonly"/>
                                </div> <!-- fin input group -->
                            </div>
                        </div>
                    </div>                
                </div>                        
                    <hr style="width: 100%; color: #d0cdcb; height: 0.2px; background-color:#000000;">
                    <div class="row">
                        <div class="col-xs-12 col-lg-4">
                            <div class="form-group">
                                <label for="categoria_id" class="col-md-12 col-lg-4">Ud. Compra<label style="color: red">*</label></label>
                                <div class="col-md-12 col-lg-8">
                                    <div class="controls form-inline">
                                        <div class="input-group">
                                            <input type="hidden" class="form-control" id="unidad_compra_id" name="unidad_compra_id" value="${unidad_compra_id}" placeholder="" size="10" maxlength="30"/>
                                            <input type="text" class="form-control" id="unidad_compra" name="unidad_compra" value="${unidad_compra}" placeholder="" size="10" maxlength="30" readonly="readonly"/>

                                            <span class="input-group-btn">
                                                <button class="btn btn-default"  id="btn_unidad_compra" type="button">Ir</button>
                                            </span>
                                            
                                        </div> <!-- fin input group -->
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 col-lg-4">
                            <div class="form-group">
                                <label for="unidad_venta_id" class="col-md-12 col-lg-4">Ud. Venta<label style="color: red">*</label></label>
                                <div class="col-md-12 col-lg-8">
                                    <div class="controls form-inline">
                                        <div class="input-group">
                                            <input type="hidden" class="form-control" id="unidad_venta_id" name="unidad_venta_id" value="${unidad_venta_id}" placeholder="" size="10" maxlength="30"/>
                                            <input type="text" class="form-control" id="unidad_venta" name="unidad_venta" value="${unidad_venta}" placeholder="" size="10" maxlength="30" readonly="readonly"/>

                                            <span class="input-group-btn">
                                                <button class="btn btn-default"  id="btn_unidad_venta" type="button">Ir</button>
                                            </span>
                                            
                                        </div> <!-- fin input group -->
                                    </div>
                                </div>
                            </div>
                        </div> <!-- fin col -->
                        <div class="col-xs-12 col-lg-4">
                            <div class="form-group">
                                <label for="factor" class="col-md-12 col-lg-3">Factor</label>
                                <div class="col-md-12 col-lg-9">
                                    <div class="input-group">
                                            <input type="text" class="form-control" id="factor" name="factor" value="${factor}" placeholder="" size="20" maxlength="50"/>
                                    </div> <!-- fin input group -->
                                </div>
                            </div>
                        </div>  <!-- fin col -->                    
                    </div> <!-- fin row -->
                    <div class="row"><div class="col-xs-12">&nbsp;</div></div>
                    <input type="hidden" class="form-control" id="inventario_minimo" name="inventario_minimo" value="${inventario_minimo}" placeholder="" size="20" maxlength="30"  style="text-align: right;" />
                    <input type="hidden" class="form-control" id="inventario_maximo" name="inventario_maximo" value="${inventario_maximo}" placeholder="" size="20" maxlength="30"  />
                    <input type="hidden" class="form-control" id="localizacion" name="localizacion" value="${localizacion}" placeholder="" size="20" maxlength="50"/>                   
                    
                    
                    <div class="row">
                        <div class="form-group">
                                <label for="inventario" class="col-md-12 col-lg-2">Inventario<label style="color: red">*</label></label>
                                <div class="col-md-12 col-lg-10">
                                        <div class="controls form-inline">
                                            <input type="text" class="form_control" id="inventario" name="inventario" value="${inventario}" />
                                        </div>    
                                </div>
                        </div>        
                    </div>
                <div class="row"><div class="col-xs-12">&nbsp;</div></div>                        
                <hr style="width: 100%; color: #d0cdcb; height: 0.2px; background-color:#000000;">                            
                <div class="row">
                        <div class="col-xs-12 col-lg-6">
                            <div class="form-group">
                                <label for="precio_compra" class="col-md-12 col-lg-4">Precio Compra<label style="color: red">*</label></label>
                                <div class="col-md-12 col-lg-8">
                                    <div class="controls form-inline">
                                            <input type="text" class="form-control" id="precio_compra" name="precio_compra" value="${precio_compra}" placeholder="" size="20" maxlength="30"  style="text-align: right;" />
                                    </div>
                                </div>
                            </div>
                        </div>
                                            
                        <div class="col-xs-12 col-lg-6">
                            <c:choose>
                                
                                <c:when test="${neto == 0}" >  
                                    
                                    <div  id="button_neto" class="btn-group" data-toggle="buttons">
                                        <label for="con_impuesto" class="btn btn-default-outline">
                                            <input type="radio" name="neto" value="1">
                                            Con Impuesto
                                        </label>
                                        <label for="sin_impuesto" class="btn btn-default-outline active">
                                            <input type="radio" name="neto" value="0" checked>
                                            Sin Impuesto
                                        </label>
                                    </div>
                                    
                                </c:when>
                                <c:otherwise>
                                    
                                    <div  id="button_neto" class="btn-group" data-toggle="buttons">
                                        <label for="con_impuesto" class="btn btn-default-outline active">
                                            <input type="radio" name="neto" value="1" checked>
                                            Con Impuesto
                                        </label>
                                        <label for="sin_impuesto"  class="btn btn-default-outline">
                                            <input type="radio" name="neto"  value="0">
                                            Sin Impuesto
                                        </label>
                                    </div>
                                        
                                </c:otherwise>
                            </c:choose>
                        </div>           
                </div>
                <div class="row"><div class="col-xs-12">&nbsp;</div></div>                    
                <div class="row">
                    <div class="col-xs-12 col-lg-6">
                            <div class="form-group">
                                <label for="precio_compra_sin_impuesto1_label" id="precio_compra_sin_impuesto1_label" class="col-md-12 col-lg-4">${unidad_compra}</label>
                                <div class="col-md-12 col-lg-8">
                                    <div class="controls form-inline">
                                            <input type="text" class="form-control" id="precio_unidad_compra" name="precio_unidad_compra" value="${precio_unidad_compra}"  style="text-align: right;"  placeholder="" size="20" maxlength="30" readonly="readonly"/>
                                    </div>
                                </div>
                                
                                
                            </div>
                    </div>
                    <div class="col-xs-12 col-lg-6">
                            <div class="form-group">
                                <label for="precio_compra_sin_impuesto2_label" id="precio_compra_sin_impuesto2_label" class="col-md-12 col-lg-4" >${unidad_venta}</label>
                                <div class="col-md-12 col-lg-8">
                                    <div class="controls form-inline">
                                            <input type="text" class="form-control" id="precio_unidad_venta" name="precio_unidad_venta" value="${precio_unidad_venta}" placeholder=""  style="text-align: right;"  size="20" maxlength="30" readonly="readonly"/>
                                    </div>
                                </div>
                                    
                                
                            </div>
                    </div>                

                </div>
                                    
                <hr style="width: 100%; color: #d0cdcb; height: 0.2px; background-color:#000000;">                                                
                    <div class="row">
                        <div class="col-xs-12">
                            <table id="tabla_utilidades" class="table table-hover nowrap" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th width="20%"></th>
                                    <th width="20%">1</th>
                                    <th width="20%">2</th>
                                    <th width="20%">3</th>
                                    <th width="20%">4</th>
                                    
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Margenes de Utilidad %</td>
                                    <td><input type="text" class="form-control" id="margen_utilidad1" name="margen_utilidad1" value="${margen_utilidad1}" placeholder="" size="20" maxlength="30"/></td>
                                    <td><input type="text" class="form-control" id="margen_utilidad2" name="margen_utilidad2" value="${margen_utilidad2}" placeholder="" size="20" maxlength="30"/></td>
                                    <td><input type="text" class="form-control" id="margen_utilidad3" name="margen_utilidad3" value="${margen_utilidad3}" placeholder="" size="20" maxlength="30"/></td>
                                    <td><input type="text" class="form-control" id="margen_utilidad4" name="margen_utilidad4" value="${margen_utilidad4}" placeholder="" size="20" maxlength="30"/></td>
                                </tr>
                                <tr>
                                    <td>Precio de Venta</td>
                                    <td><input type="text" class="form-control" id="precio_venta1" name="precio_venta1" value="${precio_venta1}" placeholder="" size="20" maxlength="30"  style="text-align: right;" readonly="readonly"/></td>
                                    <td><input type="text" class="form-control" id="precio_venta2" name="precio_venta2" value="${precio_venta2}" placeholder="" size="20" maxlength="30"  style="text-align: right;" readonly="readonly"/></td>
                                    <td><input type="text" class="form-control" id="precio_venta3" name="precio_venta3" value="${precio_venta3}" placeholder="" size="20" maxlength="30"  style="text-align: right;" readonly="readonly"/></td>
                                    <td><input type="text" class="form-control" id="precio_venta4" name="precio_venta4" value="${precio_venta4}" placeholder="" size="20" maxlength="30"  style="text-align: right;" readonly="readonly"/></td>
                                </tr>
                                <tr>
                                    <td>Precios de Venta Neto</td>
                                    <td><input type="text" class="form-control" id="precio_venta_neto1" name="precio_venta1" value="${precio_venta_neto1}" placeholder="" size="20" maxlength="30" style="text-align: right;" readonly="readonly"/></td>
                                    <td><input type="text" class="form-control" id="precio_venta_neto2" name="precio_venta_neto2" value="${precio_venta_neto2}" placeholder="" size="20" maxlength="30"  style="text-align: right;" readonly="readonly"/></td>
                                    <td><input type="text" class="form-control" id="precio_venta_neto3" name="precio_venta_neto3" value="${precio_venta_neto3}" placeholder="" size="20" maxlength="30"  style="text-align: right;" readonly="readonly"/></td>
                                    <td><input type="text" class="form-control" id="precio_venta_neto4" name="precio_venta_neto4" value="${precio_venta_neto4}" placeholder="" size="20" maxlength="30"  style="text-align: right;" readonly="readonly"/></td>
                                </tr>
                            </tbody>
                            </table>
                        </div>
    
                </div>
                </div> <!-- fin tab-datos -->
                <div role="tabpanel" class="tab-pane fade in" id="tab-caracteristicas">
                    
                    <div class="row">
                    <div class="col-xs-12">
                        <div class="form-group">
                            <div class="col-md-12 col-lg-3">
                                <label for="caracteristica" class="form-control-label">Caracteristica</label>
                            </div>
                            <div class="col-md-12 col-lg-9">
                                <div class="controls form-horizontal">
                                    <textarea class="form-control" rows="10" cols="100" id="caracteristica" name="caracteristica">${caracteristica}</textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                                   
                                    
                </div>
                    
                    
                </div> <!-- fin tab-caracteristicas -->
                       
                <div role="tabpanel" class="tab-pane fade in" id="tab-compatibles">
                    
                    <div class="row">
                        <div class="col-xs-12 col-md-6">
                            <div class="form-group">
                                <div class="col-md-12 col-lg-3">
                                    <label for="descripcion_compatible" class="form-control-label">Descripción<label style="color: red">*</label></label>
                                </div>
                                <div class="col-md-12 col-lg-9">
                                    <div class="controls form-inline">
                                        <input type="text" class="form-control" id="descripcion_compatible" name="descripcion_compatible" value="${descripcion}" placeholder="" size="50" disabled=""/>

                                    </div>
                                </div>
                            </div>
                        </div>
                                   
                                
                    </div>
                                        
                        <div class="row">

                            <div class="col-xs-12">
                               <div class="form-actions">
                                    <button type="button" class="btn btn-default width-150" id="buscar_articulo_compatible" name="buscar_articulo_compatible"><i class="icmn-search" aria-hidden="true"></i>Buscar Artículo</button>
                                    <button type="button" class="btn btn-danger"><i class="icmn-bin" aria-hidden="true"></i>Eliminar</button>
                                </div> 

                            </div>

                        </div>
                                        
                                        
                <div class="row">
                    <div class="col-xs-12">
                        <table id="tabla_articulos_compatible" name="tabla_articulos_compatible" class="table nowrap" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Clave/Alterna</th>
                                    <th>Descripcion</th>
                                    <th>Categoria</th>
                                    <th>Departamento</th>

                                </tr>
                            </thead>
                            <tfoot>
                                <tr>
                                    <th>No.</th>
                                    <th>Clave/Alterna</th>
                                    <th>Descripcion</th>
                                    <th>Categoria</th>
                                    <th>Departamento</th>

                                </tr>
                            </tfoot>

                        </table>
                    </div> <!-- fin col -->
    
                </div> <!-- fin row contenedor tabla -->                      
                                        
                    
                </div> <!-- fin tab-compatibles -->    
            </div>  <!-- fin tab-content --> 
            <div class="row">
                <div class="col-xs-12">
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary width-150">Guardar</button>
                        <button type="button" class="btn btn-default width-150" id="buscar_articulo">Buscar</button>
                        <button type="button" class="btn btn-default" id="nuevo" name="nuevo">Nuevo</button>
                    </div>
                    
                </div>
            </div> 
        
        </div> <!-- panel-body -->
    </div> <!-- nav tabs hor -->
    </div> <!-- fin panel -->

    <!--<div class="modal fade" id="moperacion" tabindex="-1" role="dialog" aria-labelledby="modal-operaciones">
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
    </div>-->
    
    
    <div class="modal modal-size-large fade" id="mcompatible" tabindex="-1" role="dialog" aria-labelledby="modal-compatible">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Punto de Venta</h4>
                </div>
                <div class="modal-body">
                    <div class="row">

                        <div  class="col-xs-12">
                            <div id="mensaje_compatible">
                                <label  style="font-size: 18px; font-weight: bolder"><span class="fa fa-spinner fa-pulse fa-3x fa-fw"></span>&nbsp;  Espere un momento por favor.</label>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" id="aceptar_compatible" name="aceptar_compatible">Aceptar</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                    
                  </div>
            </div>
        </div>
    </div>
    
    
</form> <!-- fin del form_articulo -->
        
<script type="text/javascript">
    
    $('#mcompatible').on('hidden.bs.modal', function () {
        
        
        //alert("ya cerro");
        table_compatible.destroy();
        table_compatible = $('#tabla_articulos_compatible').DataTable( {
                    "processing": true,
                        "ajax": {
                           "url": "../pv/ArticuloListaCompatibleJSON",
                            "type": "POST",
                            "data": {
                                "id": id_articulo_compatible
                            }
                           
                        },
                        "scrollY":        400,
                        "scrollCollapse": true,
                        "language": {
                                    "lengthMenu": "Mostrar _MENU_ registros por página",
                                    "zeroRecords": "No se encontro información que mostrar",
                                    "info": "Mostrando página _PAGE_ de _PAGES_",
                                    "infoEmpty": "",
                                    "infoFiltered": "(filtrado de un total de _MAX_ registros)",
                                    "search": "Buscar",
                                    "processing": "Cargando. Por favor espere un momento...",
                                    "loadingRecords": "Espere un momento por favor - Cargando datos...",
                                     "decimal": ".",
                                     "thousands": ",",
                                    "paginate": {
                                        "first": "Primer",
                                        "last": "Ultimo",
                                        "next": "Siguiente",
                                        "previous": "Anterior"
                                    }
                        },
                        "columns": [
                        
                        {
                            "className": 'id-control',
                            "data": "id"
                        },
                        {"data": "clave"},
                        {"data": "descripcion"},
                        {"data": "categoria"},
                        {"data": "departamento"}


                    ],
                    "order": [[1, 'asc']]
                
                
            }); 
    });
    
</script>
