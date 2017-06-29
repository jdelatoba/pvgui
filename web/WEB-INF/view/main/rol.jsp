<%-- 
    Document   : rol
    Created on : 23-may-2017, 20:12:11
    Author     : Rembao
--%>

<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="javax.servlet.http.HttpSession" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head lang="en">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ include file="../jspf/checkSession.jsp"  %>
        <title>Punto de Venta</title>

        <link href="../assets/common/img/favicon.144x144.png" rel="apple-touch-icon" type="image/png" sizes="144x144">
        <link href="../assets/common/img/favicon.114x114.png" rel="apple-touch-icon" type="image/png" sizes="114x114">
        <link href="../assets/common/img/favicon.72x72.png" rel="apple-touch-icon" type="image/png" sizes="72x72">
        <link href="../assets/common/img/favicon.57x57.png" rel="apple-touch-icon" type="image/png">
        <link href="../assets/common/img/favicon.png" rel="icon" type="image/png">
        <link href="favicon.ico" rel="shortcut icon">

        <!-- HTML5 shim and Respond.js for < IE9 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Vendors Styles -->
        <!-- v1.0.0 -->
        <link rel="stylesheet" type="text/css" href="../assets/vendors/bootstrap/dist/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="../assets/vendors/jscrollpane/style/jquery.jscrollpane.css">
        <link rel="stylesheet" type="text/css" href="../assets/vendors/ladda/dist/ladda-themeless.min.css">
        <link rel="stylesheet" type="text/css" href="../assets/vendors/select2/dist/css/select2.min.css">
        <link rel="stylesheet" type="text/css" href="../assets/vendors/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css">
        <link rel="stylesheet" type="text/css" href="../assets/vendors/fullcalendar/dist/fullcalendar.min.css">
        <link rel="stylesheet" type="text/css" href="../assets/vendors/cleanhtmlaudioplayer/src/player.css">
        <link rel="stylesheet" type="text/css" href="../assets/vendors/cleanhtmlvideoplayer/src/player.css">
        <link rel="stylesheet" type="text/css" href="../assets/vendors/bootstrap-sweetalert/dist/sweetalert.css">
        <link rel="stylesheet" type="text/css" href="../assets/vendors/summernote/dist/summernote.css">
        <link rel="stylesheet" type="text/css" href="../assets/vendors/owl.carousel/dist/assets/owl.carousel.min.css">
        <link rel="stylesheet" type="text/css" href="../assets/vendors/ionrangeslider/css/ion.rangeSlider.css">
        <!-- <link rel="stylesheet" type="text/css" href="../assets/vendors/datatables/media/css/dataTables.bootstrap4.min.css"> -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/DataTables/jquery.dataTables.min.css">
        <link rel="stylesheet" type="text/css" href="../assets/vendors/c3/c3.min.css">
        <link rel="stylesheet" type="text/css" href="../assets/vendors/chartist/dist/chartist.min.css">

        <!-- Clean UI Styles -->
        <link rel="stylesheet" type="text/css" href="../assets/common/css/source/main.css">

        <!-- Vendors Scripts -->
        <!-- v1.0.0 -->
        <script src="../assets/vendors/jquery/jquery.min.js"></script>
        <script src="../assets/vendors/tether/dist/js/tether.min.js"></script>
        <script src="../assets/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
        <script src="../assets/vendors/jquery-mousewheel/jquery.mousewheel.min.js"></script>
        <script src="../assets/vendors/jscrollpane/script/jquery.jscrollpane.min.js"></script>
        <script src="../assets/vendors/spin.js/spin.js"></script>
        <script src="../assets/vendors/ladda/dist/ladda.min.js"></script>
        <script src="../assets/vendors/select2/dist/js/select2.full.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.validate.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.formatCurrency-1.4.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.formatCurrency.all.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.numeric.js"></script>
        <script src="../assets/vendors/jquery-typeahead/dist/jquery.typeahead.min.js"></script>
        <script src="../assets/vendors/jquery-mask-plugin/dist/jquery.mask.min.js"></script>
        <script src="../assets/vendors/autosize/dist/autosize.min.js"></script>
        <script src="../assets/vendors/bootstrap-show-password/bootstrap-show-password.min.js"></script>
        <script src="../assets/vendors/moment/min/moment.min.js"></script>
        <script src="../assets/vendors/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"></script>
        <script src="../assets/vendors/fullcalendar/dist/fullcalendar.min.js"></script>
        <script src="../assets/vendors/cleanhtmlaudioplayer/src/jquery.cleanaudioplayer.js"></script>
        <script src="../assets/vendors/cleanhtmlvideoplayer/src/jquery.cleanvideoplayer.js"></script>
        <script src="../assets/vendors/bootstrap-sweetalert/dist/sweetalert.min.js"></script>
        <script src="../assets/vendors/remarkable-bootstrap-notify/dist/bootstrap-notify.min.js"></script>
        <script src="../assets/vendors/summernote/dist/summernote.min.js"></script>
        <script src="../assets/vendors/owl.carousel/dist/owl.carousel.min.js"></script>
        <script src="../assets/vendors/ionrangeslider/js/ion.rangeSlider.min.js"></script>
        <script src="../assets/vendors/nestable/jquery.nestable.js"></script>
        <script src="../assets/vendors/datatables/media/js/jquery.dataTables.min.js"></script>
        <script src="../assets/vendors/datatables/media/js/dataTables.bootstrap4.min.js"></script>
        <script src="../assets/vendors/datatables-fixedcolumns/js/dataTables.fixedColumns.js"></script>
        <script src="../assets/vendors/datatables-responsive/js/dataTables.responsive.js"></script>
        <script src="../assets/vendors/editable-table/mindmup-editabletable.js"></script>
        <script src="../assets/vendors/d3/d3.min.js"></script>
        <script src="../assets/vendors/c3/c3.min.js"></script>
        <script src="../assets/vendors/chartist/dist/chartist.min.js"></script>
        <script src="../assets/vendors/peity/jquery.peity.min.js"></script>
        <!-- v1.0.1 -->
        <script src="../assets/vendors/chartist-plugin-tooltip/dist/chartist-plugin-tooltip.min.js"></script>
        <!-- v1.1.1 -->
        <script src="../assets/vendors/gsap/src/minified/TweenMax.min.js"></script>
        <script src="../assets/vendors/hackertyper/hackertyper.js"></script>
        <script src="../assets/vendors/jquery-countTo/jquery.countTo.js"></script>

        <!-- Clean UI Scripts -->
        <script src="../assets/common/js/common.js"></script>


        <script type="text/javascript">

            /*function seleccionaFuncion(funcion) {
                formData = {
                    operacion: "cargar_funcion",
                    funcion: funcion
                };

                $.post("../pv/Hub.view", formData, function (data) {
                    $("#function-container").html(data);
                });

            }*/
            
            
            function seleccionaRol(rol){
                
                $("#rol_id").val(rol);
                $("#funcion").val("selecciona_rol");
                $("#frm_rol").attr("action","../pv/Hub.view");
                //$("#frmrol").attr("action","../app/MainController.do");
                $("#frm_rol").submit();
                
            }


            $(document).ready(function () {



            });

        </script>


        <%       Date fecha = Calendar.getInstance().getTime();
            //SimpleDateFormat dt = new SimpleDateFormat("hh:mm:ss");
            DateFormat df = new SimpleDateFormat("HH:mm:ss");
            String date = df.format(fecha);

            String hora = date.substring(0, 2);
            String saludo = "";
            switch (hora) {
                case "00":
                case "01":
                case "02":
                case "03":
                case "04":
                case "19":
                case "20":
                case "21":
                case "22":
                case "23":
                    saludo = "Buenas noches, ";
                    break;
                case "05":
                case "06":
                case "07":
                case "08":
                case "09":
                case "10":
                case "11":
                    saludo = "Buenos días, ";
                    break;
                case "12":
                case "13":
                case "14":
                case "15":
                case "16":
                case "17":
                case "18":
                    saludo = "Buenas tardes, ";
                    break;
                default:
                    break;

            }
        %>
    </head>
    <body class="theme-default">
        <c:set var="nombre" value="${sessionScope.nombre}" />
        <c:set var="usuario" value="${sessionScope.usuario}"/>
        <c:set var="hora" value="<%=date%>" />
        <!-- inicia el menu izquierdo -->
        <nav class="left-menu" left-menu>
            <div class="logo-container">
                <a href="#" class="logo">
                    <img src="../resources/img/logo_empresa2.png" alt="logo" />
                    <img class="logo-inverse" src="../assets/common/img/logo-inverse.png" alt="Gobierno de Baja California Sur" />
                </a>


            </div>

            <div class="left-menu-inner scroll-pane">
                <ul class="left-menu-list left-menu-list-root list-unstyled">

                    <li>
                        <section class="panel profile-user" style="background-image: url(../assets/common/img/temp/photos/9.jpeg)">
                            <div class="panel-body">
                                <div class="profile-user-title text-center">
                                    <a class="avatar" href="javascript:void(0);">
                                        <img src="../assets/common/img/temp/avatars/nobody.jpg" alt="${nombre}">
                                        
                                    </a>
                                    <br />
                                    <div class="btn-group btn-group-justified" aria-label="" role="group">
                                        <div class="btn-group">
                                            <button type="button" class="btn width-150 swal-btn-success">Editar</button>
                                        </div>
                                        <div class="btn-group">
                                            <button type="button" class="btn width-150 swal-btn-success-2">Contraseña</button>
                                        </div>
                                    </div>
                                    <br />
                                    <p>Ultima Activodad: 13 May 2016 7:26PM</p>
                                    <p>
                                        <span class="donut donut-success"></span>
                                        Online
                                    </p>
                                </div>
                            </div>
                        </section>
                    </li>








                </ul>
            </div>
        </nav>
        <!-- fin del menu izquierdo -->
        <!-- inicio del top menu -->
        <nav class="top-menu">
            <div class="menu-icon-container hidden-md-up">
                <div class="animate-menu-button left-menu-toggle">
                    <div><!-- --></div>
                </div>
            </div>
            <div class="menu">
                <div class="menu-user-block">
                    <div class="dropdown dropdown-avatar">
                        <a href="javascript: void(0);" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                            <span class="avatar" href="javascript:void(0);">
                                <img src="" alt="Alternative text to the image">
                            </span>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-right" aria-labelledby="" role="menu">
                            <a class="dropdown-item" href="javascript:void(0)"><i class="dropdown-icon icmn-user"></i> Perfil</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="javascript:void(0)"><i class="dropdown-icon icmn-exit"></i> Cerrar Sesión</a>
                        </ul>
                    </div>
                </div>
                <div class="menu-info-block">
                    <div class="left">
                        
                    </div>

                    

                </div>
            </div>
        </nav>
        <!-- fin del top menu -->
        <section class="page-content">
            <div class="page-content-inner">

                <!-- Dashboard -->
                <div id="function-container">
                    <!-- Profile Header -->
                    <nav class="top-submenu top-submenu-with-background">
                        <div class="profile-header">
                            <div class="profile-header-info">
                                <div class="row">
                                    <div class="col-xl-8">
                                        
                                        <div class="profile-header-title">
                                            <h2>${nombre}<small>@${usuario}</small></h2>
                                            <p>Punto de Venta</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </nav>
                    <!-- End Profile Header -->
                </div>

                <!-- profile  -->

                <div class="row">
                    <div class="col-xl-12">
                        
                        <form name="frm_rol" id="frm_rol" method="POST">
                            <input type="hidden" name="rol_id" id="rol_id" />
                            <input type="hidden" name="funcion" id="funcion" />
                        <section class="panel profile-user-content">
                            <div class="panel-heading">
                            <h3 class="panel-title">Roles de Usuario</h3>
                          </div>
                            <div class="panel-body">
                                <div class="margin-bottom-50">
                                    <div class="widget widget-four background-transparent">
                                        <div class="row">
                                            
                                            <c:set var="contador" value="0" />
                                            <c:forEach var="rol_usuario" items="${sessionScope.lista_roles}" >
                                                <c:set var="contador" value="${contador + 1}" />
                                                
                                                
                                                
                                                <div class="col-xl-3 col-lg-6 col-sm-6 col-xs-12">
                                                    <div class="step-block ${rol_usuario.css}">
                                                        <span class="step-digit ">
                                                            <i class="${rol_usuario.imagen}"><!-- --></i>
                                                        </span>
                                                        <div class="step-desc">
                                                            <a href="#" onclick="javascript: seleccionaRol(${rol_usuario.rol_id})" <c:if test="${rol_usuario.css != null}">style="color: #ffffff"</c:if> >
                                                                <span class="step-title">${rol_usuario.rol}</span>
                                                            </a>
                                                            <p>
                                                               &nbsp;
                                                            </p>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                
                                            </c:forEach>
                                            
                                            
                                            
                                            
                                            
                                            
                                            

                                        </div>
                                    </div>
                                </div>  
                            </div>

                        </section>
                            
                        </form>    
                    </div>                

                </div>


            </div>


            <!-- Page Scripts -->
            <script>
                $(function () {

                    ///////////////////////////////////////////////////////////
                    // COUNTERS
                    $('.counter-init').countTo({
                        speed: 1500
                    });

                    ///////////////////////////////////////////////////////////
                    // ADJUSTABLE TEXTAREA
                    autosize($('#textarea'));

                    ///////////////////////////////////////////////////////////
                    // CUSTOM SCROLL
                    if (!cleanUI.hasTouch) {
                        $('.custom-scroll').each(function () {
                            $(this).jScrollPane({
                                autoReinitialise: true,
                                autoReinitialiseDelay: 100
                            });
                            var api = $(this).data('jsp'),
                                    throttleTimeout;
                            $(window).bind('resize', function () {
                                if (!throttleTimeout) {
                                    throttleTimeout = setTimeout(function () {
                                        api.reinitialise();
                                        throttleTimeout = null;
                                    }, 50);
                                }
                            });
                        });
                    }



                    ///////////////////////////////////////////////////////////
                    // CAROUSEL WIDGET
                    $('.carousel-widget').carousel({
                        interval: 4000
                    });

                    $('.carousel-widget-2').carousel({
                        interval: 6000
                    });








                });
            </script>
            <!-- End Page Scripts -->
        </section>

        <div class="main-backdrop"><!-- --></div>

    </body>
</html>


