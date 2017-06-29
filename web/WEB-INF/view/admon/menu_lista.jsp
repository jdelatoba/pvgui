<%-- 
    Document   : menu_lista
    Created on : 15-mar-2017, 10:03:05
    Author     : Rembao
--%>


<script type="text/javascript">
    
     /* Funcion para el formateo de el detalle  */
    
    $(document).ready(function () {

        var table = $('#tabla_menus').DataTable( {
        "processing": true,
            //"serverSide": true,
            "ajax": {
               url:  "./admon/MenuListaJSON.pv",
               type: "GET"
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
                "className":      'id-control',
                "data":           "id"
            },
            
            { "data": "descripcion" },
            { "data": "comentario" },
            //{
            //    "className":      'action-control',
            //    "orderable":      false,
            //    "targets":          -1,
            //    "data":           null,
            //    "defaultContent": '<a class=\"btn btn-default\" id=\"editar\" href=\"#editar\" title=\"Editar registro\" aria-label=\"Editar registro\"><i class=\"fa fa-pencil\" aria-hidden=\"true\"></i></a><a class=\"btn btn-danger\" href=\"#eliminar\" id=\"eliminar\" title=\"Eliminar registro\" aria-label=\"Eliminar registro\"><i class=\"fa fa-trash\" aria-hidden=\"true\"></i></a>'
            //}
            
            
        ],
        "order": [[1, 'asc']]
        
    } );
    
  
    $('#tabla_menus tbody').on( 'click', 'tr', function () {
        
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
    
    
    //$('#tabla_menus tbody').on( 'click', '#editar', function () {
    //    var $item = $(this).closest("tr")   // busca el row mas cercano <tr> 
    //                   .find(".id-control")     // busca la class = "id-control"
    //                   .text(); // saca el valor del texto de la columna
    //           
    //    $("#id").val($item); //guardar el id del registro para la operacion
    //    $("#operacion").val("editar");
    //    $("#modal_mensaje").html("<span class='fa fa-question-circle fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">¿Desea editar el registro seleccionado?</label></div>");
    //    $("#moperacion").modal("show");
        
        
    //} );
    
    
        //$('#tabla_menus tbody').on( 'click', '#eliminar', function () {
        //    var $item = $(this).closest("tr")   // busca el row mas cercano <tr> 
        //                   .find(".id-control")     // busca la class = "id-control"
        //                   .text(); // saca el valor del texto de la columna
            //alert($item);

        //} );
    
        $("#editar").click(function(e){
           if($("#id").val() != ""){ 
                $("#operacion").val("editar");
                $("#modal_mensaje_comando").html("<span class='fa fa-question-circle fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">¿Desea editar el registro seleccionado?</label></div>");
                $("#mcomando").modal("show");
            }else{
                $("#modal_mensaje_comando").html("<span class='fa fa-info-circle fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">Debe seleccionar al menos un registro de la tabla.</label></div>");
                $("#mcomando").modal("show");
                
            }    
        
        });
        
        $("#eliminar").click(function(e){
           if($("#id").val() != ""){ 
                $("#operacion").val("eliminar");
                $("#modal_mensaje_comando").html("<span class='fa fa-question-circle fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">¿Desea eliminar el registro seleccionado?</label></div>");
                $("#mcomando").modal("show");
            }else{
                $("#modal_mensaje_comando").html("<span class='fa fa-info-circle fa-3x fa-fw'></span><label for=\"modal_mensaje\" style=\"font-size: 18px; font-weight: bolder\">Debe seleccionar al menos un registro de la tabla.</label></div>");
                $("#mcomando").modal("show");
                
            }
        
        });
        
        $("#agregar").click(function(e){
           $("#funcion").val("menus");
           $('#form_tabla_menu').attr('action', "./Hub");
           $("#form_tabla_menu").submit();
        
        });
    
    
        $("#aceptar").click(function(e){
             $("#moperacion").modal("hide");
        });
        
        $("#recargar").click(function(e){
             table.ajax.reload();
        });
        
        
        $("#aceptar_comando").click(function(e){
             $("#mcomando").modal("hide");
             
             
            if($("#operacion").val() == "editar"){
               
                $("#funcion").val("editar_menu");
                $('#form_tabla_menu').attr('action', "./Hub");
                $("#form_tabla_menu").submit();
            }
                
            if($("#operacion").val() == "eliminar"){
                $("#operacion").val("cerrar_modal");
                var id = $("#id").val();
                formData = {
                    id : $("#id").val(),
                    operacion : "eliminar_menu"
                    
                };
                
                $.post("./admon/MenuController.pv", formData, function (data) {
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
<form class="form-horizontal" method="post" id="form_tabla_menu" name="form_tabla_menu">
    <div class="row">
        <div class="col-xs-12">&nbsp;</div>
    </div>    
    <div class="row">
        <div class="col-xs-12">
            <div class="btn-group" role="group" aria-label="">
              <button type="button" class="btn btn-default" id="agregar" name="agregar"><span class="fa fa-plus-circle"></span>&nbsp;Agregar</button>
              <button type="button" class="btn btn-default" id="editar" name="editar"><span class="fa fa fa-pencil"></span>&nbsp;Editar</button>
              <button type="button" class="btn btn-default" id="eliminar" name="eliminar"><span class="fa fa fa-trash"></span>&nbsp;Eliminar</button>
              <button type="button" class="btn btn-default" id="recargar" name="recargar"><span class="fa fa-refresh"></span>&nbsp;Recargar</button>
            </div>
            
        </div>
    </div>   
<div class="row">
    <div class="col-xs-12">&nbsp;</div>
</div>

<div class="row">
    <div class="col-xs-12">
        <table id="tabla_menus" class="display" cellspacing="0" width="100%">
        <thead>
            <tr>
                
                <th>No.</th>
                <th>Menú</th>
                <th>Comentario</th>
               
            </tr>
        </thead>
        <tfoot>
            <tr>
                <th>No.</th>
                <th>Menú</th>
                <th>Comentario</th>
                
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
    </div>

<input type="hidden" id="operacion" name="operacion" />
<input type="hidden" id="funcion" name="funcion" />
<input type="hidden" id="id" name="id" />
<div id="operaciones"></div>
</form>