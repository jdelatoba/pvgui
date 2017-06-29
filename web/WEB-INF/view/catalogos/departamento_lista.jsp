<%-- 
    Document   : departamento_lista
    Created on : 13-may-2017, 13:39:09
    Author     : Rembao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="categoria" class="app.choya.sys.pv.catalogo.Categoria"  />
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

        var table = $('#tabla_categorias').DataTable( {
        "processing": true,
            //"serverSide": true,
            "ajax": {
               url:  "../pv/CategoriaListaJSON",
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
                "className":      'categoria',
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
    
  
    $('#tabla_categorias tbody').on( 'click', 'tr', function () {
        
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    } );
    
    
    $('#tabla_categorias tbody').on( 'click', '#seleccionar', function () {
        var $item = $(this).closest("tr")   // busca el row mas cercano <tr> 
                       .find(".id-control")     // busca la class = "id-control"
                       .text(); // saca el valor del texto de la columna
               
        var $categoria = $(this).closest("tr")   // busca el row mas cercano <tr> 
                       .find(".categoria")     // busca la class = "rol"
                       .text(); // saca el valor del texto de la columna       
        
        //$("#categoria_id").val($item);
        //$("#categoria").val($categoria);
        //$("#moperacion").modal("hide");
        
        formData = {
            
            categoria_id : $item,
            operacion : "carga_valores_catalogo"
            
        };
        
        $.post("../pv/ArticuloController",formData,function (data){
           
           $("#div_carga_categoria").html(data);
           $("#categoria_id").val($item);
           $("#categoria").val($categoria);
           $("#departamento_id").val($("#rdepartamento_id").val());
           $("#departamento").val($("#rdepartamento").val());
           $("#moperacion").modal("hide");
        });
        
        
        
        
        
        
    } );
    
    
        $('#tabla_categorias tbody').on( 'click', '#eliminar', function () {
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
        <table id="tabla_categorias" class="table table-hover nowrap" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>Id.</th>
                <th>Categoria</th>
                
                <th></th>
            </tr>
        </thead>
        <tfoot>
            <tr>
                <th>Id.</th>
                <th>Categoria</th>
                
                <th></th>
            </tr>
        </tfoot>
        
</table>
    </div>
    
</div>
<div id="div_carga_categoria"></div>