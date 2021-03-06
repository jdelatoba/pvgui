<%-- 
    Document   : articulo_busca_compatible
    Created on : 07-jun-2017, 18:38:32
    Author     : Rembao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="ab" class="app.choya.sys.pv.catalogo.Articulo"  />
<!DOCTYPE html>

<style type="text/css">

    td.details-control {
        background: url('/pvgui/resources/images/details_open.png') no-repeat center center;
        cursor: pointer;
    }
    tr.shown td.details-control {
        background: url('/pvgui/resources/images/details_close.png') no-repeat center center;
    }

</style>

<script type="text/javascript">
    
    var articulos = [];

    /* Funcion para el formateo de el detalle  */
    function format(d) {
        // `d` es la información orginal de objecto data para el reglon 
        return '<table cellpadding="5" cellspacing="0" border="0" width="100%" style="padding-left:50px;">' +
                '<tr>' +
                '<td><b>Inventario Minimo:</b></td>' +
                '<td>' + d.inventario_minimo + '</td>' +
                '<td><b>Inventario Maximo</b></td>' +
                '<td>' + d.inventario_maximo + '</td>' +
                '</tr>' +
                '<tr>' +
                '<td><b>Margen de Utilidad 1</b></td>' +
                '<td>' + d.margen_utilidad1 + '%</td>' +
                '<td><b>Precio Venta 1:</b></td>' +
                '<td>' + d.precio_venta1 + '</td>' +
                '</tr>' +
                '<tr>' +
                '<td><b>Margen de Utilidad 2</b></td>' +
                '<td>' + d.margen_utilidad2 + '%</td>' +
                '<td><b>Precio Venta 2:</b></td>' +
                '<td>' + d.precio_venta2 + '</td>' +
                '</tr>' +
                '<tr>' +
                '<td><b>Margen de Utilidad 3</b></td>' +
                '<td>' + d.margen_utilidad3 + '%</td>' +
                '<td><b>Precio Venta 3:</b></td>' +
                '<td>' + d.precio_venta3 + '</td>' +
                '</tr>' +
                '<tr>' +
                '<td><b>Margen de Utilidad 4</b></td>' +
                '<td>' + d.margen_utilidad4 + '%</td>' +
                '<td><b>Precio Venta 4:</b></td>' +
                '<td>' + d.precio_venta4 + '</td>' +
                '</tr>' +
                '<tr>' +
                '<td><b>Categoria</b></td>' +
                '<td>' + d.categoria + '</td>' +
                '<td><b>Departamento:</b></td>' +
                '<td>' + d.departamento + '</td>' +
                '</tr>' +
                '</table>';
    }



    $(document).ready(function () {
        var table = "";

        table = $('#tabla_articulos').DataTable({
            "processing": true,
            "ajax": {
                "url": "../pv/ArticuloListaJSON",
                "type": "POST"

            },
            "scrollY": 400,
            "scrollCollapse": true,
            "language": {
                "lengthMenu": "Mostrar _MENU_ registros por página",
                "zeroRecords": "No se encontro información para mostrar",
                "info": "Mostrando página _PAGE_ de _PAGES_",
                "infoEmpty": "No se encontro información para mostrar",
                "infoFiltered": "(filtrado de un total de _MAX_ registros)",
                "processing": "Cargando. Por favor espere un momento...",
                "search": "Buscar",
                "loadingRecords": "Espere un momento por favor - Cargando datos...",
                "decimal": ".",
                "thousands": ",",
                "paginate": {
                    "first": "Primer",
                    "last": "Ultimo",
                    "next": "Siguiente",
                    "previous": "Anterior"
                }
            }


        });



        $("#categorias").change(function () {
            if ($(this).is(":checked")) {
                $("#btn_categoria").attr('disabled', 'disabled');
            } else {
                $("#btn_categoria").removeAttr('disabled');

            }


        });

        $("#departamentos").change(function () {
            if ($(this).is(":checked")) {
                $("#btn_departamento").attr('disabled', 'disabled');
            } else {
                $("#btn_departamento").removeAttr('disabled');

            }


        });


        $("#btn_categoria").click(function () {

            $("#modal_mensaje").html("<span class='fa fa-cog fa-spin fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">Espere un momento por favor...</label></div>");
            $("#moperacion").modal("show");

            formData = {
                operacion: "cargar_categorias"
            };

            $.post("../pv/ArticuloController", formData, function (data) {
                $("#modal_mensaje").html(data);
            });

        });


        $("#btn_departamento").click(function () {

            $("#modal_mensaje").html("<span class='fa fa-cog fa-spin fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">Espere un momento por favor...</label></div>");
            $("#moperacion").modal("show");

            formData = {
                operacion: "cargar_departamentos"
            };

            $.post("../pv/ArticuloController", formData, function (data) {
                $("#modal_mensaje").html(data);
            });

        });

        $("#btn_buscar").click(function () {


            if ($("#buscar").val() != "") {
                var categorias = 0;
                if ($("#categorias").is(":checked")) {
                    categorias = $('input[name=categorias]:checked').val();
                }
                var departamentos = 0;

                if ($("#departamentos").is(":checked")) {
                    departamentos = $('input[name=departamentos]:checked').val();
                }
                var con_existencia = 0;

                if ($("#con_existencia").is(":checked")) {

                    con_existencia = $('input[name=con_existencia]:checked').val();
                }

                var sin_existencia = 0;

                if ($("#sin_existencia").is(":checked")) {

                    sin_existencia = $('input[name=sin_existencia]:checked').val();
                }

                var buscar = $("#buscar").val();

                var departamento_id = $("#departamento_id").val();
                var categoria_id = $("#categoria_id").val();
                var busca_por = $('input[name=busca_por]:checked').val();

                table.destroy();
                table = $('#tabla_articulos').DataTable({
                    "processing": true,
                    "ajax": {
                        "url": "../pv/ArticuloListaJSON",
                        "type": "POST",
                        "data": {
                            "categorias": categorias,
                            "departamentos": departamentos,
                            "buscar": buscar,
                            "departamento_id": departamento_id,
                            "categoria_id": categoria_id,
                            "busca_por": busca_por,
                            "con_existencia": con_existencia,
                            "sin_existencia": sin_existencia
                        }

                    },
                    "scrollY": 400,
                    "scrollCollapse": true,
                    "language": {
                        "lengthMenu": "Mostrar _MENU_ registros por página",
                        "zeroRecords": "Ups!! No he encontrado registros.. tal vez para la proxima",
                        "info": "Mostrando página _PAGE_ de _PAGES_",
                        "infoEmpty": "Ups!! No he encontrado registros..",
                        "infoFiltered": "(filtrado de un total de _MAX_ registros)",
                        "search": "Buscar",
                        "processing": "Cargando. Por favor espere un momento...",
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
                            "className": 'details-control',
                            "orderable": false,
                            "data": null,
                            "defaultContent": ''
                        },
                        {
                            "className": 'id-control',
                            "data": "id"
                        },
                        {"data": "clave"},
                        {"data": "descripcion"},
                        {"data": "existencia"},
                        {"data": "s_precio_compra"}


                    ],
                    "order": [[1, 'asc']]

                });


            } //fin del if buscar




        });



        $('#tabla_articulos tbody').on('click', 'tr', function () {
            
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
                
                var $del = $(this).closest("tr")   // busca el row mas cercano <tr> 
                        .find(".id-control")     // busca la class = "id-control"
                        .text(); // saca el valor del texto de la columna
                var articulos_temp = [];
                var contador = 0;
                var alen = articulos.length;
                for(i = 0; i < alen; i++){
                    if(articulos[i] != $del ){
                        articulos_temp[contador] = articulos[i];
                        contador++;
                    }
                    
                }
                
                articulos = articulos_temp;
                
            } else {
                var $item = $(this).closest("tr")   // busca el row mas cercano <tr> 
                        .find(".id-control")     // busca la class = "id-control"
                        .text(); // saca el valor del texto de la columna
                
                        
                    articulos[articulos.length] = $item;
                    
                $("#id_articulo_compatible").val($item);
                //table.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }

        });



        // Agregar evento - listener para abrir y cerrar los detalles
        $('#tabla_articulos tbody').on('click', 'td.details-control', function () {


            var tr = $(this).closest('tr');
            var row = table.row(tr);

            if (row.child.isShown()) {
                // Este reglon esta abierto - se cierrar
                row.child.hide();
                tr.removeClass('shown');
            } else {
                // Se abre reglon para mostrar los detalles
                row.child(format(row.data())).show();
                tr.addClass('shown');
            }
        });


        
        $("#seleccionar_articulo").click(function (e) {
                
                //alert(articulos);
                //alert($("#id").val());
                
                if ($("#id_articulo_compatible").val() != "") {
                    $("#mensaje_articulo_compatible").html("<label  style='font-size: 18px; font-weight: bolder'><span class='fa fa-spinner fa-pulse fa-3x fa-fw'></span>&nbsp;  Espere un momento por favor.</label>");
                    
                    formData = {
                      articulos: articulos,
                      id: $("#id").val(),
                      operacion : "agregar_articulo_compatible"      
                        
                    };
                    
                    $.post("../pv/ArticuloController",formData, function(data){
                        $("#operaciones").html(data);
                        
                        $("#mensaje_articulo_compatible").html("<span style=\"color: green\" class=\"fa fa-check-circle fa-3x\"></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">El registro se ha guardado correctamente.</label></div>");
                        //table_compatible.ajax.reload();
                        //$("#mcompatible").modal("hide");
                    });
                    

                } else {
                    $("#modal_mensaje_comando").html("<span class='fa fa-info-circle fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">Debe seleccionar al menos un registro de la tabla.</label></div>");
                    $("#mcomando").modal("show");

                }

            });


        

        
        
        
        $("#recargar").click(function(e){
            table.ajax.reload();
        });

        
        
        
}); // fin del document.ready



</script>



    

            <div class="row">
                <div class="form-group">
                    <div class="col-xs-12 col-md-2">
                        <span class="input-group-btn" data-toggle="buttons"> 

                            <label class="btn btn-primary-outline active">
                                <input type="radio" name="busca_por" id="busca_por1" title="Busqueda por clave/clave alterna" value="clave" checked> <i class="icmn-key"></i>
                            </label>
                            <label class="btn btn-primary-outline">
                                <input type="radio" name="busca_por" id="busca_por2" title="Busqueda por descripción" value="descripcion"> <i class="icmn-list"></i>
                            </label>
                        </span>
                    </div>    
                    <div class="col-xs-12 col-md-10">
                        <div class="input-group" >

                            <input type="text" class="form-control" placeholder="" id="buscar" name="buscar">
                            <span class="input-group-btn">

                                <a href="#" class="btn btn-success" id="btn_buscar" >
                                    Buscar<i class="icmn-search"></i>
                                </a>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12">&nbsp;</div>
            </div>

            <div class="row">
                <div class="col-xs-12 col-lg-4">
                    <div class="form-group">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="categorias" id="categorias" value="1" checked>
                                Todas las Categorias
                            </label>
                        </div>
                        <div class="controls form-inline">
                            <div class="input-group">
                                <input type="hidden" class="form-control" id="categoria_id" name="categoria_id" value="${categoria_id}" placeholder="" size="30" maxlength="50"/>
                                <input type="text" class="form-control" id="categoria" name="categoria" value="${categoria}" placeholder="" size="30" maxlength="50" readonly="readonly"/>
                                <span class="input-group-btn">
                                    <button class="btn btn-default"  id="btn_categoria" type="button" disabled>Ir</button>
                                </span>
                            </div> <!-- fin input group -->
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-lg-4">
                    <div class="form-group">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="departamentos" id="departamentos" value="1" checked>
                                Todas los Departamentos
                            </label>
                        </div>
                        <div class="controls form-inline">
                            <div class="input-group">
                                <input type="hidden" class="form-control" id="departamento_id" name="departamento_id" value="${departamento_id}" placeholder="" size="30" maxlength="50"/>
                                <input type="text" class="form-control" id="departamento" name="departamento" value="${departamento}" placeholder="" size="30" maxlength="50" readonly="readonly"/>
                                <span class="input-group-btn">
                                    <button class="btn btn-default"  id="btn_departamento" type="button" disabled>Ir</button>
                                </span>
                            </div> <!-- fin input group -->
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-lg-4">
                    <div class="form-group">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="con_existencia" id="con_existencia" value="1" checked>
                                Con Existencia
                            </label>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="sin_existencia" id="sin_existencia" value="1" checked>
                                Sin Existencia
                            </label>
                        </div>

                    </div>
                </div>                        
            </div>

                                <div class="row"><div id="mensaje_articulo_compatible" class="col-xs-12" style="text-align: center">&nbsp;</div></div>
            <div class="btn-group margin-inline" aria-label="" role="group">
                <button type="button" id="seleccionar_articulo" name="seleccionar_articulo" class="btn btn-primary-outline">
                    <i class="icmn-checkmark4" aria-hidden="true"></i>
                    Seleccionar
                </button>
                <button type="button" id="cancelar_test" name="cancelar_test" class="btn btn-primary-outline">
                    <i class="icmn-checkmark4" aria-hidden="true"></i>
                    Cancelar
                </button>
                
            </div>


            <div class="row">
                <div class="col-xs-12">
                    <table id="tabla_articulos" name="tabla_articulos" class="table nowrap" cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th></th>
                                <th>No.</th>
                                <th>Clave/Alterna</th>
                                <th>Descripcion</th>
                                <th>Existencia</th>
                                <th>Precio</th>

                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <th></th>
                                <th>No.</th>
                                <th>Clave/Alterna</th>
                                <th>Descripcion</th>
                                <th>Existencia</th>
                                <th>Precio</th>

                            </tr>
                        </tfoot>

                    </table>
                </div> <!-- fin col -->

            </div> <!-- fin row contenedor tabla -->                             

                                        








    <input type="hidden" id="operacion" name="operacion" />
    <input type="hidden" id="funcion_forma" name="funcion_forma" />
    <input type="hidden" id="id_articulo_compatible" name="id_articulo_compatible" />
    



  


                                
                                

                               