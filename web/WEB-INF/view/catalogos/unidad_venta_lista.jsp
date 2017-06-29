<%-- 
    Document   : unidad_lista
    Created on : 07-may-2017, 18:45:15
    Author     : Rembao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="unidad" class="app.choya.sys.pv.configuracion.Unidad"  />
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
    
    $(document).ready(function () {

        var table = $('#tabla_unidades').DataTable( {
        "processing": true,
            //"serverSide": true,
            "ajax": {
               url:  "../pv/UnidadAListaJSON",
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
            {
                "className":      'unidad',
                "data":           "descripcion"
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
    
  
    $('#tabla_unidades tbody').on( 'click', 'tr', function () {
        
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    } );
    
    
    $('#tabla_unidades tbody').on( 'click', '#seleccionar', function () {
        var $item = $(this).closest("tr")   // busca el row mas cercano <tr> 
                       .find(".id-control")     // busca la class = "id-control"
                       .text(); // saca el valor del texto de la columna
               
        var $unidad = $(this).closest("tr")   // busca el row mas cercano <tr> 
                       .find(".unidad")     // busca la class = "rol"
                       .text(); // saca el valor del texto de la columna       
        
        $("#unidad_venta_id").val($item);
        $("#unidad_venta").val($unidad);
        $("#precio_compra_sin_impuesto2_label").text(" X "+$unidad);
        $("#moperacion").modal("hide");
    });
    
    
        
    
       
        
});

</script>
<div class="row">
    <div class="col-xs-12">&nbsp;</div>
</div>

<div class="row">
    <div class="col-xs-12">
        <table id="tabla_unidades" class="table table-hover nowrap" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>Id.</th>
                <th>Unidad</th>
                
                <th></th>
            </tr>
        </thead>
        <tfoot>
            <tr>
                <th>Id.</th>
                <th>Unidad</th>
                
                <th></th>
            </tr>
        </tfoot>
        
</table>
    </div>
    
</div>

