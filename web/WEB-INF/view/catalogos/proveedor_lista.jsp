<%-- 
    Document   : proveedor_lista
    Created on : 02-mar-2017, 12:20:33
    Author     : Rembao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="pb" class="app.choya.sys.pv.catalogo.Proveedor"  />
<!DOCTYPE html>

<style type="text/css">
    
    td.details-control {
        background: url('/puntoventa/resources/images/details_open.png') no-repeat center center;
        cursor: pointer;
    }
    tr.shown td.details-control {
        background: url('/puntoventa/resources/images/details_close.png') no-repeat center center;
    }
    
</style>
<script type="text/javascript">
    
     /* Funcion para el formateo de el detalle  */
function format ( d ) {
    // `d` es la información orginal de objecto data para el reglon 
    return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
        '<tr>'+
            '<td>Domicilio:</td>'+
            '<td>'+d.domicilio_desc+'</td>'+
        '</tr>'+
        '<tr>'+
            '<td>Email</td>'+
            '<td>'+d.email+'</td>'+
        '</tr>'+
        '<tr>'+
            '<td>Limite de Credito:</td>'+
            '<td>'+d.limite_credito+'</td>'+
        '</tr>'+
        '<tr>'+
            '<td>Dias Credito:</td>'+
            '<td>'+d.dias_credito+'</td>'+
        '</tr>'+
    '</table>';
}

    
    
    $(document).ready(function () {

        var table = $('#tabla_proveedores').DataTable({
        "processing": true,
            //"serverSide": true,
            "ajax": {
               url:  "../pv/catalogos/ProveedorListaJSON",
               type: "POST"
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
                "className":      'details-control',
                "orderable":      false,
                "data":           null,
                "defaultContent": ''
            },
            /*{ "data": "id" },*/
            {
                "className":      'id-control',
                "data":           "id"
            },
            
            { "data": "nombre" },
            { "data": "alias" },
            { "data": "telefono" }
            /*{
                "className":      'action-control',
                "orderable":      false,
                "targets":          -1,
                "data":           null,
                "defaultContent": '<a class=\"btn btn-default\" id=\"editar\" href=\"#editar\" title=\"Editar registro\" aria-label=\"Editar registro\"><i class=\"fa fa-pencil\" aria-hidden=\"true\"></i></a><a class=\"btn btn-danger\" href=\"#eliminar\" id=\"eliminar\" title=\"Eliminar registro\" aria-label=\"Eliminar registro\"><i class=\"fa fa-trash\" aria-hidden=\"true\"></i></a>'
            }*/
            
            
        ],
        "order": [[1, 'asc']]
        
    } );
    
  
    $('#tabla_proveedores tbody').on( 'click', 'tr', function () {
        
        /*if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }*/
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            var $item = $(this).closest("tr")   // busca el row mas cercano <tr> 
                       .find(".id-control")     // busca la class = "id-control"
                       .text(); // saca el valor del texto de la columna
            $("#id").val($item); //guardar el id del registro para la operacion  
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
        
    } );
    
    
    
    $("#editar").click(function(e){
        
       
           if($("#id").val() != ""){ 
                $("#operacion").val("editar_proveedor");
                $("#modal_mensaje_comando").html("<span class='fa fa-question-circle fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">¿Desea editar el registro seleccionado?</label></div>");
                $("#mcomando").modal("show");
            }else{
                $("#modal_mensaje_comando").html("<span class='fa fa-info-circle fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">Debe seleccionar al menos un registro de la tabla.</label></div>");
                $("#mcomando").modal("show");
                
            }    
        
        });
        
    /*$('#tabla_proveedores tbody').on( 'click', '#editar', function () {
        var $item = $(this).closest("tr")   // busca el row mas cercano <tr> 
                       .find(".id-control")     // busca la class = "id-control"
                       .text(); // saca el valor del texto de la columna
               
        $("#id").val($item); //guardar el id del registro para la operacion
        $("#operacion").val("editar_proveedor");
        $("#modal_mensaje").html("<span class='fa fa-question-circle fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">¿Desea editar el registro seleccionado?</label></div>");
        $("#moperacion").modal("show");
        
        
        
    } );*/
    
    
        /*$('#tabla_proveedores tbody').on( 'click', '#eliminar', function () {
            var $item = $(this).closest("tr")   // busca el row mas cercano <tr> 
                           .find(".id-control")     // busca la class = "id-control"
                           .text(); // saca el valor del texto de la columna
            //alert($item);

        } );*/
        
        
        $("#eliminar").click(function(e){
           if($("#id").val() != ""){ 
                $("#operacion").val("eliminar_proveedor");
                $("#modal_mensaje_comando").html("<span class='fa fa-question-circle fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">¿Desea eliminar el registro seleccionado?</label></div>");
                $("#mcomando").modal("show");
            }else{
                $("#modal_mensaje_comando").html("<span class='fa fa-info-circle fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">Debe seleccionar al menos un registro de la tabla.</label></div>");
                $("#mcomando").modal("show");
                
            }
        
        });
        
        $("#agregar").click(function (e) {
            $("#operacion").val("agregar_proveedor");
            $("#modal_mensaje_comando").html("<span class='fa fa-question-circle fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">¿Desea agregar un nuevo artículo?</label></div>");
            $("#mcomando").modal("show");

        });
    
    
        $("#aceptar").click(function(e){
             $("#moperacion").modal("hide");
        });
        
        $("#recargar").click(function(e){
             table.ajax.reload();
        });
        
        
    
        // Agregar evento - listener para abrir y cerrar los detalles
        $('#tabla_proveedores tbody').on('click', 'td.details-control', function () { 

            //alert("selecccionado");
            var tr = $(this).closest('tr');
            var row = table.row( tr );

            if ( row.child.isShown() ) {
                // Este reglon esta abierto - se cierrar
                row.child.hide();
                tr.removeClass('shown');
            }
            else {
                // Se abre reglon para mostrar los detalles
                row.child( format(row.data()) ).show();
                tr.addClass('shown');
            }
        } );
    
    
        $("#aceptar_comando").click(function(e){
             $("#mcomando").modal("hide");
            
             
            if ($("#operacion").val() == "agregar_proveedor") {

                formData = {
                    funcion: "agregar_proveedor"

                };

                $.post("../pv/Hub.view", formData, function (data) {
                    $("#function-container").html(data);
                });


            } 
             
            if($("#operacion").val() == "editar_proveedor"){
               
                formData = {
                    id_proveedor: $("#id").val(),
                    funcion: "editar_proveedor"

                };

                $.post("../pv/Hub.view", formData, function (data) {
                    $("#function-container").html(data);
                });
            }
            
            
            if($("#operacion").val() == "eliminar_proveedor"){
                $("#operacion").val("cerrar_modal");
                var id = $("#id").val();
                formData = {
                    id : $("#id").val(),
                    operacion : "eliminar_proveedor"
                    
                };
                
                $.post("../pv/proveedor.pv", formData, function (data) {
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
        
    });

</script>
<form class="form-horizontal" method="post" id="form_tabla_proveedor" name="form_tabla_proveedor">
<div class="panel panel-default">
        <div class="panel-heading">
            <h3>Proveedores</h3>
        </div>
        <div class="panel-body">
                <div class="row">
                    <div class="col-xs-12">&nbsp;</div>
                </div>    
                <div class="btn-group margin-inline" aria-label="" role="group">
                    <button type="button" class="btn btn-primary-outline" id="editar" name="editar">
                      <i class="icmn-pencil" aria-hidden="true"></i>
                    Editar
                    </button>
                  <button type="button" id="eliminar" name="eliminar" class="btn btn-primary-outline">
                    <i class="icmn-minus2" aria-hidden="true"></i>
                      Eliminar
                  </button>
                  <button type="button" id="agregar" name="agregar" class="btn btn-primary-outline">
                    <i class="icmn-plus2" aria-hidden="true"></i>
                    Agregar
                  </button>
                    <button type="button" id="recargar" name="recargar" class="btn btn-primary-outline">
                        <i class="icmn-spinner8" aria-hidden="true"></i>
                        Recargar
                    </button>
                </div>

                    
            <div class="row">
                <div class="col-xs-12">&nbsp;</div>
            </div>

            <div class="row">
                <div class="col-xs-12">
                    <table id="tabla_proveedores" class="display" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th></th>
                            <th>No.</th>
                            <th>Nombre/Representante</th>
                            <th>Alias</th>
                            <th>Teléfono</th>

                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th></th>
                            <th>No.</th>
                            <th>Nombre/Representante</th>
                            <th>Alias</th>
                            <th>Teléfono</th>

                        </tr>
                    </tfoot>

            </table>
                </div>

            </div>



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
                </div> -->

            <input type="hidden" id="operacion" name="operacion" />
            <input type="hidden" id="funcion_forma" name="funcion_forma" />
            <input type="hidden" id="id" name="id" />
            <div id="operaciones"></div>
        </div> <!-- fin panel body -->
    </div> <!-- fin panel -->
</form>