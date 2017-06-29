<%-- 
    Document   : menu
    Created on : 11-abr-2017, 13:31:02
    Author     : Rembao
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="/WEB-INF/view/jspf/checkSession.jsp"  %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Punto de Venta</title>
        
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.cerulean.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-select.min.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/DataTables/jquery.dataTables.min.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-select.min.css">
        <link href='http://fonts.googleapis.com/css?family=Noto+Sans' rel='stylesheet' type='text/css'>

        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
        
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap-select.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.validate.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.formatCurrency-1.4.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.formatCurrency.all.js"></script>
        
        <script src="${pageContext.request.contextPath}/resources/js/jquery.numeric.js"></script>
        <script src="${pageContext.request.contextPath}/resources/DataTables/jquery.dataTables.min.js"></script>

        <script type="text/javascript">

            $(document).ready(function () {
                
                $(window).keydown(function (event) {
                    if (event.keyCode == 13) {
                        event.preventDefault();
                        return false;
                    }
                });
                
                $('[data-toggle=offcanvas]').click(function () {
                    $('.row-offcanvas').toggleClass('active');



                });



                $('#collapseAdministracion').on('shown.bs.collapse', function () {
                    
                    $(this).prev().find(".glyphicon").removeClass("glyphicon-triangle-left").addClass("glyphicon-triangle-bottom");
                });

                //The reverse of the above on hidden event:

                $('#collapseAdministracion').on('hidden.bs.collapse', function () {
                    $(this).prev().find(".glyphicon").removeClass("glyphicon-triangle-bottom").addClass("glyphicon-triangle-left");
                });
                
                
                $('[data-toggle="tooltip"]').tooltip();


            });

            function seleccionaFuncion(funcion){
                var funcion_sel = funcion;
                
                
                
                $("#funcion").val(funcion_sel);
                $('#frm-menu-lateral').attr('action', "../pv/Hub");
                $("#frm-menu-lateral").submit();
                
                
                
            }



        </script>

        <style type="text/css">

            


            body {
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
                /*font-family: 'Noto Sans', sans-serif;*/
                /*padding-top: 60px;*/
                /*modificacion */
                /*background-color: #f5f2f0; */
                background-color: #ffffff;
            }
            
            .navbar {
                    margin-bottom: 0;
                }

            ul.menu_main li.active a {
                color: #ffffff !important;
                
            }

            ul.menu_main li a {
                /*color: #acbad5 !important;*/
                color: #9d9d9d !important;
            }

            ul.menu_main li a:hover {
                background-color: #456e9d;
                color: #ffffff !important;
            }

            .row-height {
                height: 100%;
            }


            a.list-group-item:hover,
            a.list-group-item:focus {
                color: #555;
                text-decoration: none;
                font-weight: bold;
                background-color: #f5f2f0;
                border-right:10px solid #337ab7;
                border-top:2px solid #f5f5f5;
            }



            .list-group .list-group-item {
                color: #000 !important;
                background-color: #f5f5f5 !important;
                border-color: #f5f5f5     !important;
                font-size: 12px;
                font-weight: initial;

            }
            
            

            .list-group-item {
                border: 0 !important;
                margin: 0px 0px 0px 10px;
                padding: 10px 0px;
                font-size: 12px;
                font-weight: initial;
            }

            
            /**.list-group-item, .list-group-item:hover
            {
                color: #ffffff !important;

            }*/
            
            /*.list-group-item, .list-group-item:focus
            {
                color: #cc8977 !important;
                

            }*/

          
            
            #sidebar {
                height: 100%;
                padding-right: 0;
                /*padding-top: 20px;*/
            }
            #sidebar .nav {
                width: 95%;
            }


            /* collapsed sidebar styles */
            @media screen and (max-width: 767px) {
                .row-offcanvas {
                    position: relative;
                    -webkit-transition: all 0.25s ease-out;
                    -moz-transition: all 0.25s ease-out;
                    transition: all 0.25s ease-out;
                }
                .row-offcanvas-right
                .sidebar-offcanvas {
                    right: -41.6%;
                }

                .row-offcanvas-left
                .sidebar-offcanvas {
                    left: -41.6%;
                }
                .row-offcanvas-right.active {
                    right: 41.6%;
                }
                .row-offcanvas-left.active {
                    left: 41.6%;
                }
                .sidebar-offcanvas {
                    position: absolute;
                    top: 0;
                    width: 41.6%;
                }
                #sidebar {
                    padding-top:0;
                }
            }

            /*input[type=text] {
                background-color: #f5f2f0;
                color: #67615c;
            }*/
             .panel-body {
                background:#f5f5f5;}

            /*input[type=text]:blur {
                background-color: #f5f2f0;
                color: #67615c;
            }

            input[type=text]:focus {
                background-color: #fffeff;
                color: #67615c;
            }*/


            .nopadding {
                padding: 0px 0px 10px 0px !important;
                /*margin: 0 !important;*/
                /*padding: 0px 0px 0px 10px !important;*/
                margin: 0px 0px 0px 10px !important;
                /*background-color: #67615c;*/
                background-color: #f5f5f5;
                height: 100%;
            }

            .nopadding_contenedor {
                padding: 0px 0px 0px 10px !important;
                /*margin: 0 !important;*/
                /*padding: 0px 0px 0px 10px !important;*/
                margin: 0px 0px 0px 10px !important;
                /*background-color: #f5f2f0;*/
                height: 100%;

            }

            

        </style>
    </head>
    <body>
        <div class="container-full">

            <!-- top navbar -->
            <!--<div class="navbar navbar-default navbar-fixed-top" role="navigation">-->
            <div class="navbar navbar-default" role="navigation">
                <div class="container-fluid">

                    <div class="navbar-header">

                        <button type="button" class="navbar-toggle collapsed" data-toggle="offcanvas" data-target=".sidebar-nav" aria-expanded="false">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand"  href="#" style="margin-bottom: 10px">
                            <img alt="Brand" src="../resources/img/logo_empresa2.png" width="200px" height=36px" > 

                        </a>
                        <!--<p class="navbar-text"><label style="font-size: 16px; font-weight: bold;">Refrigeración y Equipos de Baja California</p>-->
                    </div>


                    <div class="collapse navbar-collapse" id="pv-hub-navbar-collapse-1">

                        <!--<form class="navbar-form navbar-left" role="search">
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="Buscar">
                            </div>
                            <button type="submit" class="btn btn-default">Buscar</button>
                        </form>-->

                        <ul class="nav navbar-nav navbar-right">

                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Panel de Usuario <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <!-- <li><a role="button" data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample">Action</a></li>
                                     <li><a href="#">Another action</a></li>
                                     <li><a href="#">Something else here</a></li>
                                     <li role="separator" class="divider"></li>-->
                                    <li><a href="#">Cerrar Sesión</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>

                </div>
            </div>

            
                <div class="row row-offcanvas row-offcanvas-left">

                    <!-- sidebar -->
                    <div class="col-xs-6 col-sm-2 sidebar-offcanvas nopadding" id="sidebar" nopaddingrole="navigation">
                        <form name="frm-menu-lateral" id="frm-menu-lateral" method="POST">
                            <div class="list-group" >
                                
                                <a class="list-group-item"  role="button" data-toggle="collapse" href="#collapseAdministracion" aria-expanded="false" aria-controls="collapseAdministracion" ><i class="fa fa-user-circle fa-2x fa-fw" aria-hidden="true" style="vertical-align: middle;"></i>&nbsp; Administración<span class="glyphicon glyphicon-triangle-left pull-right" aria-hidden="true" style="vertical-align: middle !important;  font-size: 8px; margin-top: 8px; margin-right: 8px"></span></a>
                                <div class="collapse" id="collapseAdministracion">
                                    <a class="list-group-item " href="#" onclick="seleccionaFuncion('empresas')" data-toggle="tooltip" data-placement="rigth" title="Información de la Empresa, Nombre, RFC, Domicilio Fiscal Etc..."><i class="fa fa-angle-right" aria-hidden="true" style="vertical-align: middle;"  ></i>&nbsp; Empresa</a>
                                    <a class="list-group-item" href="#" onclick="seleccionaFuncion('usuarios')"><i class="fa fa-angle-right" aria-hidden="true" style="vertical-align: middle;"></i>&nbsp; Usuarios</a>
                                    <a class="list-group-item" href="#" onclick="seleccionaFuncion('roles')"><i class="fa fa-angle-right" aria-hidden="true" style="vertical-align: middle;"></i>&nbsp; Roles</a>
                                    <a class="list-group-item" href="#" onclick="seleccionaFuncion('vendedores')"><i class="fa fa-angle-right" aria-hidden="true" style="vertical-align: middle;"></i>&nbsp; Vendedores</a>
                                    <a class="list-group-item" href="#" onclick="seleccionaFuncion('grupos')"><i class="fa fa-angle-right" aria-hidden="true" style="vertical-align: middle;"></i>&nbsp; Grupos</a>
                                    <a class="list-group-item" href="#" onclick="seleccionaFuncion('familias')"><i class="fa fa-angle-right" aria-hidden="true" style="vertical-align: middle;"></i>&nbsp; Familias</a>
                                    <a class="list-group-item" href="#" onclick="seleccionaFuncion('impresoras')"><i class="fa fa-angle-right" aria-hidden="true" style="vertical-align: middle;"></i>&nbsp; Impresoras</a>
                                    <a class="list-group-item" href="#" onclick="seleccionaFuncion('impuestos')"><i class="fa fa-angle-right" aria-hidden="true" style="vertical-align: middle;"></i>&nbsp; Impuestos</a>
                                    <a class="list-group-item" href="#" onclick="seleccionaFuncion('monedas')"><i class="fa fa-angle-right" aria-hidden="true" style="vertical-align: middle;"></i>&nbsp; Moneda</a>
                                    <a class="list-group-item" href="#" onclick="seleccionaFuncion('unidades')"><i class="fa fa-angle-right" aria-hidden="true" style="vertical-align: middle;"></i>&nbsp; Unidades</a>
                                </div>
                                <a class="list-group-item" role="button" data-toggle="collapse" href="#collapseOperacion" aria-expanded="false" aria-controls="collapseAdministracion"><i class="fa fa-briefcase fa-2x fa-fw" aria-hidden="true"></i>&nbsp; Operaciones</a>
                                <div class="collapse" id="collapseOperacion">
                                    <a class="list-group-item" href="#" onclick="seleccionaFuncion('proveedores')"></i>&nbsp; Proveedores</a>
                                    <a class="list-group-item" href="#" onclick="seleccionaFuncion('clientes')"></i>&nbsp; Clientes</a>
                                    <a class="list-group-item" href="#" onclick="seleccionaFuncion('articulos')"></i>&nbsp; Artículos</a>
                                </div>

                                <a class="list-group-item" href="#"><i class="fa fa-money fa-2x fa-fw" aria-hidden="true"></i>&nbsp; Ventas</a>

                                <a class="list-group-item" href="#"><i class="fa fa-cog fa-fw" aria-hidden="true"></i>&nbsp; Settings</a>
                            </div>


                            <input type="hidden" name="funcion" id="funcion" />
                            <input type="hidden" name="zombie" id="zombie" value="zombie"/>
                        </form>
                    </div>

                    <!-- main area -->
                    <div class="col-xs-12 col-sm-9 nopadding_contenedor">
                    
                    <div id="contenedor">
                        <div id="contenedor-funcion">
                            <jsp:include  page="${requestScope.url}"></jsp:include>
                            <div class="collapse" id="collapseExample">
                                <div class="well">
                                    ...
                                </div>
                            </div>
                        </div>
                    </div>
                            
                            

                </div> <!-- div fin del contenedor -->
                </div><!--/.row-->
            
        </div><!--/.page-container-->
    </body>
</html>
