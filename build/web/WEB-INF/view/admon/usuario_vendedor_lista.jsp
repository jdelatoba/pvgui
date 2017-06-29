<%-- 
    Document   : usuario_vendedor_lista
    Created on : 13-mar-2017, 16:50:52
    Author     : Rembao
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<style type="text/css">
    
    td.details-control {
        background: url('./resources/images/details_open.png') no-repeat center center;
        cursor: pointer;
    }
    tr.shown td.details-control {
        background: url('./resources/images/details_close.png') no-repeat center center;
    }
    
</style>
<script type="text/javascript">
    
     /* Funcion para el formateo de el detalle  */
    
    $(document).ready(function () {

        var table = $('#tabla_vendedores').DataTable( {
        "processing": true,
            //"serverSide": true,
            "ajax": {
               url:  "../pv/admon/VendedorListaJSON",
               type: "POST"
            },
            "scrollY":        300,
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
            
            { "data": "vendedor" },
            {
                "className":      "nombre",
                "data":           "nombre"
            },
            
            {
                "className":      'action-control',
                "orderable":      false,
                "targets":          -1,
                "data":           null,
                "defaultContent": '<a class=\"btn btn-success\" id=\"seleccionar\" href=\"#seleccionar\" title=\"Seleccionar registro\" aria-label=\"Seleccionar registro\"><i class=\"fa fa-check\" aria-hidden=\"true\"></i></a>'
            }
            
            
        ],
        "order": [[1, 'asc']]
        
    } );
    
  
    $('#tabla_vendedores tbody').on( 'click', 'tr', function () {
        
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    } );
    
    
    $('#tabla_vendedores tbody').on( 'click', '#seleccionar', function () {
        var $item = $(this).closest("tr")   // busca el row mas cercano <tr> 
                       .find(".id-control")     // busca la class = "id-control"
                       .text(); // saca el valor del texto de la columna
               
        var $nombre = $(this).closest("tr")   // busca el row mas cercano <tr> 
                       .find(".nombre")     // busca la class = "rol"
                       .text(); // saca el valor del texto de la columna       
        
        $("#id_vendedor").val($item);
        $("#vendedor").val($nombre);
        $("#moperacion").modal("hide");
        
        
    } );
    
    
    
    $("#aceptar").click(function(e){
        $("#moperacion").modal("hide");


    });
        
    });

</script>
<div class="row">
    <div class="col-xs-12">&nbsp;</div>
</div>

<div class="row">
    <div class="col-xs-12">
        <table id="tabla_vendedores" class="display" cellspacing="0" width="100%">
        <thead>
            <tr>
                
                <th>No.</th>
                <th>Vendedor</th>
                <th>Nombre</th>
                <th></th>
            </tr>
        </thead>
        <tfoot>
            <tr>
                <th>No.</th>
                <th>Vendedor</th>
                <th>Nombre</th>
                <th></th>
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
                    <button type="button" class="btn btn-success" id="aceptar" name="aceptar">Aceptar</button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
                    
                  </div>
            </div>
        </div>
    </div>

<input type="hidden" id="operacion" name="operacion" />
<input type="hidden" id="id" name="id" />
