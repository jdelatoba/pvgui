<%-- 
    Document   : proveedor
    Created on : 22-feb-2017, 17:03:06
    Author     : Rembao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>



<div class="row"><div class="col-xs-12">&nbsp;</div></div>
<form class="form-horizontal" method="post" id="form_proveedor" name="form_proveedor">

    <!-- Nav tabs -->

    <div class="row">
        <div class="page-header page-header-margin">
            <h2>Proveedores <span class="fa fa-bus"></span></h2>
        </div>
    </div>    

    <div>
        <ul class="nav nav-tabs" role="tablist">

            <li role="proveedor-tab" class="active"><a href="#tab-general" aria-controls="tab-general" role="tab" data-toggle="tab">Datos Generales</a></li>
            <li role="proveedor-tab"><a href="#tab-domicilio" aria-controls="tab-domicilio" role="tab" data-toggle="tab">Domicilio</a></li>
            <li role="proveedor-tab"><a href="#tab-credito" aria-controls="tab-credito" role="tab" data-toggle="tab">Credito</a></li>

        </ul>

        <div class="tab-content">
            <div role="tabpanel" class="tab-pane fade in active" id="tab-general">


                <div class="panel panel-default no_top_border">
                    <div class="panel-body">


                        <div class="row">
                            <div class="col-xs-12">
                                <div class="form-group">
                                    <label for="no_proveedor" class="col-md-12 col-lg-2">Núm.<label style="color: red">*</label></label>
                                    <div class="col-md-12 col-lg-10">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="no_proveedor" name="no_proveedor" value="" placeholder="" size="20" maxlength="20" />

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12">
                                <div class="form-group">
                                    <label for="representante" class="col-md-12 col-lg-2">Representante<label style="color: red">*</label></label>
                                    <div class="col-md-12 col-lg-10">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="representante" name="representante" value="" placeholder="" size="50" maxlength="250" />

                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-xs-12">
                                <div class="form-group">
                                    <label for="nombre" class="col-md-12 col-lg-2">Nombre<label style="color: red">*</label></label>
                                    <div class="col-md-12 col-lg-10">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="nombre" name="nombre" value="" placeholder="" size="50" maxlength="250" />

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12">
                                <div class="form-group">
                                    <label for="nombre" class="col-md-12 col-lg-2">Alias</label>
                                    <div class="col-md-12 col-lg-10">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="alias" name="alias" value="" placeholder="" size="50" maxlength="150" />

                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-xs-12">
                                <div class="form-group">
                                    <label for="rfc" class="col-md-12 col-lg-2">R.F.C.</label>
                                    <div class="col-md-12 col-lg-10">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="rfc" name="rfc" value="" placeholder="" size="20" maxlength="50" />

                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-xs-12">
                                <div class="form-group">
                                    <label for="curp" class="col-md-12 col-lg-2">CURP</label>
                                    <div class="col-md-12 col-lg-10">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="curp" name="curp" value="" placeholder="" size="20" maxlength="50" />

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

            <div role="tabpanel" class="tab-pane fade" id="tab-domicilio">

                <div class="panel panel-default no_top_border">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="form-group">
                                    <label for="domicilio" class="col-md-12 col-lg-2">Domicilio</label>
                                    <div class="col-md-12 col-lg-10">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="domicilio" name="domicilio" value="" placeholder="" size="50" maxlength="250" />

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="form-group">
                                    <label for="no_ext" class="col-md-12 col-lg-2">No. Exterior</label>
                                    <div class="col-md-12 col-lg-10">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="no_ext" name="no_ext" value="" placeholder="" size="20" maxlength="20" />

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12">
                                <div class="form-group">
                                    <label for="no_int" class="col-md-12 col-lg-2">No. Interior</label>
                                    <div class="col-md-12 col-lg-10">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="no_int" name="no_int" value="" placeholder="" size="20" maxlength="20" />

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="form-group">
                                    <label for="colonia" class="col-md-12 col-lg-2">Colonia</label>
                                    <div class="col-md-12 col-lg-10">
                                        <div class="controls form-inline">
                                            <input type="text" class="form-control" id="colonia" name="colonia" value="" placeholder="" size="50" maxlength="150" />

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>    
                    </div>
                </div>
            </div>    

        </div>    

    </div>    




</form>
