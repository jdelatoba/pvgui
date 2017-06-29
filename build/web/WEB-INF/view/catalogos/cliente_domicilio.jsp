<%-- 
    Document   : cliente_domicilio
    Created on : 20-abr-2017, 0:16:16
    Author     : Rembao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="cb" class="app.choya.sys.pv.catalogo.Cliente"  />
<!DOCTYPE html>

                                
                                        
                                        
<script type="text/javascript">
    
    /* Funcion para el formateo de el detalle  */
    function format ( d ) {
        // `d` es la información orginal de objecto data para el reglon 
        return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;" width="100%">'+
            '<tr>'+
                '<td width="20%" style="font-weight: bold">Estado</td>'+
                '<td>'+d.entidad+'</td>'+
            '</tr>'+
            '<tr>'+
                '<td width="20%" style="font-weight: bold">Pais</td>'+
                '<td>'+d.pais+'</td>'+
            '</tr>'+
            '<tr>'+
                '<td width="20%" style="font-weight: bold">Telefono:</td>'+
                '<td>'+d.telefono+'</td>'+
            '</tr>'+
            '<tr>'+
                '<td width="20%" style="font-weight: bold">Celular:</td>'+
                '<td>'+d.celular+'</td>'+
            '</tr>'+
            '<tr>'+
                '<td width="20%" style="font-weight: bold">Correo:</td>'+
                '<td>'+d.email+'</td>'+
            '</tr>'+
            '<tr>'+
                '<td width="20%" style="font-weight: bold">Comentario:</td>'+
                '<td>'+d.comentario+'</td>'+
            '</tr>'+
        '</table>';
    }

    
    
    $(document).ready(function () {
                                
        $("#aceptar_domicilio").click(function(){
            if($("#form_cliente_domicilio").valid()){
                
                alert("enviando submit");
               //$("#mdomicilio").modal("hide");
               $("#form_cliente_domicilio").submit();
            }
        });                        
                                
        $("#form_cliente_domicilio").validate({
            ignore: [],
            invalidHandler: function (event, validator) {
                $(".tab-content").find("div.tab-pane:hidden:has(div.has-error)").each(function (index, tab) {
                    var id = $(tab).attr("id");
                    $('a[href="#' + id + '"]').tab('show');
                });
                $("#moperacion_dom").modal("show");
                $("#modal_mensaje_dom").html("<span style=\"color: red\" class=\"fa fa-exclamation-triangle fa-3x\"></span>&nbsp;<label for=\"modal_mensaje_dom\" style=\"font-size: 16px; font-weight: bolder\">Los datos no son validos o falta información, por favor revise los campos del formulario.</label></div>");

            },
            rules: {
                domicilio_dom:{
                    required: true,
                    maxlength: 250
                },
                no_ext_dom:{
                    required: true,
                    maxlength: 20
                },
                colonia_dom:{
                    required: true,
                    maxlength: 150
                },
                codigo_postal_dom:{
                    required: true,
                    number: true,
                    maxlength: 5
                },
                localidad_dom:{
                    required: true,
                    maxlength: 150
                },
                municipio_dom:{
                    required: true,
                    maxlength: 150
                },
                entidad_dom:{
                    required: true,
                    maxlength: 50
                },
                pais_dom:{
                    required: true,
                    maxlength: 150
                },
                email_dom:{
                    required: true,
                    email: true,
                    maxlength: 150
                }
                
            },
            messages: {
                domicilio_dom: {
                    required: "&nbsp;Por favor introduzca domicilio del cliente",
                    maxlength: "&nbsp;Longitud del campo domicilio es invalido max - 150 caracteres"
                },
                no_ext_dom:{
                    required: "&nbsp;Por favor introduzca número exterior del cliente",
                    maxlength: "&nbsp;Longitud del campo número exterior es invalido max - 20 caracteres"
                },
                colonia_dom:{
                    required: "&nbsp;Por favor introduzca colonia del cliente",
                    maxlength: "Longitud del campo colinia es invalido max - 150 caracteres"
                },
                codigo_postal_dom:{
                    required: "&nbsp;Por favor introduzca código postal del cliente",
                    maxlength: "&nbsp;Longitud del campo código postal es invalido max - 5 caracteres",
                    number: "&nbsp;El campo código postal es númerico"
                },
                localidad_dom:{
                    required: "&nbsp;Por favor introduzca el nombre de la localidad",
                    maxlength: "Longitud del campo localidad es invalido max - 150 caracteres"
                },
                municipio_dom:{
                    required: "&nbsp;Por favor introduzca el nombre del municipio",
                    maxlength: "Longitud del campo municipio es invalido max - 150 caracteres"
                },
                entidad_dom:{
                    required: "&nbsp;Por favor introduzca el nombre de la entidad",
                    maxlength: "Longitud del campo entidad es invalido max - 50 caracteres"
                },
                pais_dom:{
                    required: "&nbsp;Por favor introduzca el nombre del pais",
                    maxlength: "Longitud del campo pais es invalido max - 50 caracteres"
                },
                email_dom:{
                    email: "&nbsp;Por favor introduzca un correo electronico valido.",
                    required: "&nbsp;Por favor introduzca el correo del cliente",
                    maxlength: "Longitud del campo correo es invalido max - 150 caracteres"
                }
                
                

            },
            submitHandler: function (form) {
                
                console.log("entre al submitHandler");

                $("#modal_mensaje_dom").html("<span class='fa fa-cog fa-spin fa-3x fa-fw'></span><label for=\"modal_mensaje_dom\" style=\"font-size: 18px; font-weight: bolder\">Espere un momento por favor...</label></div>");
                $("#moperacion_dom").modal("show");
                
                var id_cliente_dom = $("#id_cliente_dom").val();
                console.log("id_cliente_dom "+id_cliente_dom);
                var id_domicilio = $("#id_domicilio_mod").val();
                console.log("id_domicilio_mod "+id_domicilio);
                
                var domicilio = $("#domicilio_dom").val();
                var no_ext = $("#no_ext_dom").val();
                var no_int = $("#no_int_dom").val();
                var colonia = $("#colonia_dom").val();
                var codigo_postal = $("#codigo_postal_dom").val();
                console.log("codigo_postal_dom "+codigo_postal);
                var localidad = $("#localidad_dom").val();
                var municipio = $("#municipio_dom").val();
                var entidad = $("#entidad_dom").val();
                var pais = $("#pais_dom").val();
                var telefono = $("#telefono_dom").val();
                var celular = $("#celular_dom").val();
                var email = $("#email_dom").val();
                var comentario = $("#comentario_dom").val();
                
                
                
                var operacion = "";
                
                if(id_domicilio == 0){
                    operacion = "guardar_domicilio";
                }else{
                    operacion = "actualizar_domicilio";
                }
                
                formData = {
                    id: id_cliente_dom,
                    id_domicilio : id_domicilio,
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
                    operacion: operacion
                };

                $.post("/puntoventa/pv/Cliente.pv", formData, function (data) {
                    $("#operaciones_dom").html(data);
                    
                    if ((parseInt($("#res").val()) !== 0)) {
                        $("#modal_mensaje_dom").html("<span style=\"color: green\" class=\"fa fa-check-circle fa-3x\"></span><label for=\"modal_mensaje_dom\" style=\"font-size: 18px; font-weight: bolder\">El registro se ha guardado correctamente.</label></div>");
                        $("#id_domicilio_mod").val($("#res").val());
                        
                        
                        formData = {
                            id : $("#id").val(),
                            operacion : "carga_domicilio_alternos"
                        };

                        $.post("/puntoventa/pv/Cliente.pv", formData, function(data){
                           $("#domicilio_alterno").html(data); 
                        });
                        
                        
                    } else {
                        $("#modal_mensaje_dom").html("<span style=\"color: red\" class=\"fa fa-exclamation-triangle fa-3x\"></span>&nbsp;<label for=\"modal_mensaje_dom\" style=\"font-size: 16px; font-weight: bolder\">El sistema ha lanzado el siguiente error: Codigo Error: "+$("#codigo_error").val() +" "+ $("#mensaje").val() + ".</label></div>");

                    }
                });
                //asdasd$(form).submit();*/
            }

        });  // fin $("#form_cliente").validate

                                
                                
                                

        var table = $('#tabla_domicilios').DataTable( {
            "processing": true,
                //"serverSide": true,
                "ajax": {
                   url:  "../pv/catalogos/ClienteDomicilioListaJSON",
                   type: "POST",
                   data :{
                       id_cliente : $("#id").val()
                   }
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

                {
                    "className":      'id-control',
                    "data":           "id_domicilio"
                },

                { "data": "domicilio" },
                { "data": "localidad" },
                { "data": "municipio" },



            ],
            "order": [[1, 'asc']]

        } );


        $('#tabla_domicilios tbody').on( 'click', 'tr', function () {

            if ( $(this).hasClass('selected') ) {
                $(this).removeClass('selected');
            }
            else {
                var $item = $(this).closest("tr")   // busca el row mas cercano <tr> 
                           .find(".id-control")     // busca la class = "id-control"
                           .text(); // saca el valor del texto de la columna
                $("#id_domicilio_mod").val($item); //guardar el id del registro para la operacion  
                table.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }

            //alert($("#id_domicilio_mod").val());

        } );



        $("#editar").click(function(e){

           if($("#id_domicilio_mod").val() != ""){ 
                $("#operacion").val("editar");
                $("#modal_mensaje_comando").html("<span class='fa fa-question-circle fa-3x fa-fw'></span><label for=\"modal_mensaje_comando\" style=\"font-size: 18px; font-weight: bolder\">¿Desea editar el registro seleccionado?</label></div>");
                $("#mcomando").modal("show");
            }else{
                $("#modal_mensaje_comando").html("<span class='fa fa-info-circle fa-3x fa-fw'></span><label for=\"modal_mensaje_comando\" style=\"font-size: 18px; font-weight: bolder\">Debe seleccionar al menos un registro de la tabla.</label></div>");
                $("#mcomando").modal("show");

            }    

        });



        $("#eliminar").click(function(e){
           if($("#id_domicilio_mod").val() != ""){ 
                $("#operacion").val("eliminar");
                $("#modal_mensaje_comando").html("<span class='fa fa-question-circle fa-3x fa-fw'></span><label for=\"modal_mensaje_comando\" style=\"font-size: 18px; font-weight: bolder\">¿Desea eliminar el registro seleccionado?</label></div>");
                $("#mcomando").modal("show");
            }else{
                $("#modal_mensaje_comando").html("<span class='fa fa-info-circle fa-3x fa-fw'></span><label for=\"modal_mensaje_comando\" style=\"font-size: 18px; font-weight: bolder\">Debe seleccionar al menos un registro de la tabla.</label></div>");
                $("#mcomando").modal("show");

            }

        });

        $("#agregar").click(function(e){

            $("#mdomicilio").modal("show");
           //$("#funcion_forma").val("proveedores");
           //$('#form_tabla_unidad').attr('action', "../pv/Hub");
           //$("#form_tabla_unidad").submit();

        });


        $("#aceptar").click(function(e){
             $("#moperacion_dom").modal("hide");
        });

        $("#recargar").click(function(e){
             table.ajax.reload();
        });



        // Agregar evento - listener para abrir y cerrar los detalles
        $('#tabla_domicilios tbody').on('click', 'td.details-control', function () { 

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


            if($("#operacion").val() == "editar"){

                $("#funcion_forma").val("editar_proveedor");
                $('#form_tabla_proveedor').attr('action', "../pv/Hub");
                $("#form_tabla_proveedor").submit();
            }


            if($("#operacion").val() == "eliminar"){
                $("#operacion").val("cerrar_modal");
                var id = $("#id").val();
                formData = {
                    id : $("#id").val(),
                    operacion : "eliminar_proveedor"

                };

                $.post("/puntoventa/pv/proveedor.pv", formData, function (data) {
                    $("#operaciones_dom").html(data);
                    if ((parseInt($("#resultado").val()) != 0)) {

                        $("#moperacion_dom").modal("show"); 
                        $("#modal_mensaje_dom").html("<span style=\"color: green\" class=\"fa fa-check-circle fa-3x\"></span><label for=\"modal_mensaje_dom\" style=\"font-size: 18px; font-weight: bolder\">El registro se ha eliminado.</label></div>");
                        table.ajax.reload();

                    } else {
                        $("#moperacion_dom").modal("show");
                        $("#modal_mensaje_dom").html("<span style=\"color: red\" class=\"fa fa-exclamation-triangle fa-3x\"></span>&nbsp;<label for=\"modal_mensaje_dom\" style=\"font-size: 16px; font-weight: bolder\">El sistema ha lanzado el siguiente error: Codigo Error: "+$("#codigo_error").val() +" "+ $("#mensaje").val() + ".</label></div>");

                    }

                }); //fin $.post


            }
        });

    });

</script>
<form class="form-horizontal" method="post" id="form_cliente_domicilio" name="form_cliente_domicilio">       
                  
 <c:set var="id_cliente_dom" value="${requestScope.id_cliente_dom}" />
    
 <div id="operaciones_dom"></div>
    <input type="hidden" value="${id_cliente_dom}" name="id_cliente_dom" id="id_cliente_dom" />
    <input type="hidden" id="id_domicilio_mod" name="id_domicilio_mod" />
    <div class="row">
        <div class="col-xs-12">&nbsp;</div>
    </div>    
    <div class="row">
        <div class="col-xs-12">
            <div class="btn-group" role="group" aria-label="">
              <button type="button" class="btn btn-default" id="agregar" name="agregar"><span class="fa fa-plus-circle"></span>&nbsp;Agregar Domicilio</button>
              <button type="button" class="btn btn-default" id="editar" name="editar"><span class="fa fa fa-pencil"></span>&nbsp;Editar</button>
              <button type="button" class="btn btn-default" id="eliminar" name="eliminar"><span class="fa fa fa-trash"></span>&nbsp;Eliminar</button>

            </div>

        </div>
    </div>    
                    <div class="row"><div class="col-xs-12">&nbsp;</div></div>
                    <div class="row">
                        <div class="col-xs-12">
                            <table id="tabla_domicilios" class="display" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th></th>
                                        <th>No</th>
                                        <th>Domicilio</th>
                                        <th>Localidad</th>
                                        <th>Municipio</th>
                                        
                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                        <th></th>
                                        <th>No.</th>
                                        <th>Domicilio</th>
                                        <th>Localidad</th>
                                        <th>Municipio</th>
                                        
                                    </tr>
                                </tfoot>

                            </table>
                        </div>

                    </div>
                    
                    
                    
                    <div class="modal  fade" id="mdomicilio" tabindex="-1" role="dialog" aria-labelledby="mdomicilio">
                        <div class="modal-dialog modal-lg modal-domi" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close" id="close_mdomicilio"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="myModalDomicilio">Domicilio Cliente</h4>
                                </div>
                                <div class="modal-body body-domi">
                                    <div class="row">
                                        <div class="col-md-12 col-lg-6" >
                                            <div class="form-group">
                                                <label for="domicilio_dom" class="col-md-12 col-lg-3">Domicilio</label>
                                                <div class="col-md-12 col-lg-9">
                                                    <div class="controls form-inline">
                                                        <input type="text" class="form-control" id="domicilio_dom" name="domicilio_dom" value="${domicilio_dom}" placeholder="" size="80" maxlength="250" />

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>    
                                    <div class="row">
                                        <div class="col-md-12 col-lg-6" >
                                            <div class="form-group">
                                                <label for="no_ext_dom" class="col-md-12 col-lg-3">No. Exterior</label>
                                                <div class="col-md-12 col-lg-9">
                                                    <div class="controls form-inline">
                                                        <input type="text" class="form-control" id="no_ext_dom" name="no_ext_dom" value="${no_ext_dom}" placeholder="" size="20" maxlength="20" />

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-12 col-lg-6">
                                            <div class="form-group">
                                                <label for="no_int" class="col-md-12 col-lg-3">No. Interior</label>
                                                <div class="col-md-12 col-lg-9">
                                                    <div class="controls form-inline">
                                                        <input type="text" class="form-control" id="no_int" name="no_int" value="${no_int_dom}" placeholder="" maxlength="20"  />

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>                    
                        <div class="row">                    
                            <div class="col-md-12 col-lg-6">
                                <div class="form-group">
                                    <label for="colonia_dom" class="col-md-12 col-lg-3">Colonia</label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="colonia_dom" name="colonia_dom" value="${colonia_dom}" placeholder=""  size="30" maxlength="150" />

                                        </div>
                                    </div>
                                </div>
                            </div> <!-- fin colonia -->
                            <div class="col-md-12 col-lg-6">
                                <div class="form-group">
                                    <label for="codigo_postal_dom" class="col-md-12 col-lg-3">Código Postal</label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="codigo_postal_dom" name="codigo_postal_dom" value="${codigo_postal_dom}" placeholder="" size="20" maxlength="5" />

                                        </div>
                                    </div>
                                </div>
                            </div> <!-- codigo postal -->


                            <div class="col-md-12 col-lg-6">
                                <div class="form-group">
                                    <label for="localidad_dom" class="col-md-12 col-lg-3">Localidad</label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="localidad_dom" name="localidad_dom" value="${localidad_dom}" placeholder=""  size="30" maxlength="150" />

                                        </div>
                                    </div>
                                </div>
                            </div> <!-- fin localidad -->
                            <div class="col-md-12 col-lg-6">
                                <div class="form-group">
                                    <label for="municipio_dom" class="col-md-12 col-lg-3">Municipio</label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="municipio_dom" name="municipio_dom" value="${municipio_dom}" placeholder=""  size="30" maxlength="150" />

                                        </div>
                                    </div>
                                </div>
                            </div><!-- municipio -->

                            <div class="col-md-12 col-lg-6">
                                <div class="form-group">
                                    <label for="entidad_dom" class="col-md-12 col-lg-3">Entidad</label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="entidad_dom" name="entidad_dom" value="${entidad_dom}" placeholder=""  size="30" maxlength="150" />

                                        </div>
                                    </div>
                                </div>
                            </div> <!-- fin entidad -->
                            <div class="col-md-12 col-lg-6">
                                <div class="form-group">
                                    <label for="pais_dom" class="col-md-12 col-lg-3">País</label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="pais_dom" name="pais_dom" value="${pais_dom}" placeholder=""  size="30" maxlength="150" />

                                        </div>
                                    </div>
                                </div>
                            </div><!-- pais -->

                            <div class="col-md-12 col-lg-6">
                                <div class="form-group">
                                    <label for="telefono" class="col-md-12 col-lg-3">Teléfono</label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="telefono" name="telefono" value="${telefono_dom}" placeholder=""  size="20" maxlength="50" />

                                        </div>
                                    </div>
                                </div>
                            </div> <!-- fin telefono -->
                            <div class="col-md-12 col-lg-6">
                                <div class="form-group">
                                    <label for="celular" class="col-md-12 col-lg-3">Celular</label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="celular" name="celular" value="${celular_dom}" placeholder=""  size="20" maxlength="50" />

                                        </div>
                                    </div>
                                </div>
                            </div><!-- celular -->


                        </div>   <!-- row -->

                        <div class="row">
                            <div class="col-md-12 col-lg-6" >
                                <div class="form-group">
                                    <label for="email_dom" class="col-md-12 col-lg-3">Correo Electrónico</label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="email_dom" name="email_dom" value="${email_dom}" placeholder="" size="80" maxlength="150" />

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div> <!-- row email -->

                        <div class="row">
                            <div class="col-md-12 col-lg-6" >
                                <div class="form-group">
                                    <label for="comentario_dom" class="col-md-12 col-lg-3">Comentario</label>
                                    <div class="col-md-12 col-lg-9">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="comentario_dom" name="comentario_dom" value="${comentario_dom}" placeholder="" size="80" maxlength="250" />

                                        </div>
                                    </div>
                                </div>
                            </div>
                </div> <!-- row comentario -->

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" id="aceptar_domicilio" name="aceptar_domicilio">Aceptar</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>

                                  </div>
                            </div>
                        </div>
                    </div> <!-- fin mdomicilio -->
                    
                    
    <div class="modal fade" id="moperacion_dom" tabindex="-1" role="dialog" aria-labelledby="modal-operaciones">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Punto de Venta</h4>
                </div>
                <div class="modal-body">
                    <div class="row">

                        <div  class="col-xs-12">
                            <div id="modal_mensaje_dom">
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
                    
</form>
