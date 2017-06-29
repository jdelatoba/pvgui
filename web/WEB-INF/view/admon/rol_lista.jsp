<%-- 
    Document   : rol_lista
    Created on : 11-mar-2017, 14:27:06
    Author     : Rembao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="ubean" class="app.choya.sys.pv.configuracion.Usuario"  />
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

        var table = $('#tabla_roles').DataTable( {
        "processing": true,
            //"serverSide": true,
            "ajax": {
               url:  "../pv/admon/RolListaJSON",
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
                "className":      'rol',
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
    
  
    $('#tabla_roles tbody').on( 'click', 'tr', function () {
        
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    } );
    
    
    $('#tabla_roles tbody').on( 'click', '#seleccionar', function () {
        var $item = $(this).closest("tr")   // busca el row mas cercano <tr> 
                       .find(".id-control")     // busca la class = "id-control"
                       .text(); // saca el valor del texto de la columna
               
        var $rol = $(this).closest("tr")   // busca el row mas cercano <tr> 
                       .find(".rol")     // busca la class = "rol"
                       .text(); // saca el valor del texto de la columna       
        
        $("#id_rol").val($item);
        $("#rol").val($rol);
        $("#moperacion").modal("hide");
        
        
    } );
    
    
        $('#tabla_roles tbody').on( 'click', '#eliminar', function () {
            var $item = $(this).closest("tr")   // busca el row mas cercano <tr> 
                           .find(".id-control")     // busca la class = "id-control"
                           .text(); // saca el valor del texto de la columna
           

        } );
    
       
        
    });

</script>
<div class="row">
    <div class="col-xs-12">&nbsp;</div>
</div>

<div class="row">
    <div class="col-xs-12">
        <table id="tabla_roles" class="display" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>No.</th>
                <th>Rol</th>
                <th></th>
            </tr>
        </thead>
        <tfoot>
            <tr>
                <th>No.</th>
                <th>Rol</th>
                <th></th>
            </tr>
        </tfoot>
        
</table>
    </div>
    
</div>

