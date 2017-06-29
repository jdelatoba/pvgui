<%-- 
    Document   : index
    Created on : 02-may-2017, 16:32:42
    Author     : Rembao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <%@ include file="/WEB-INF/view/jspf/checkSession.jsp"  %>
    <jsp:useBean id="admon" class="app.choya.sys.pv.configuracion.AdmonMenu" />
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
   <!-- <script src="../assets/common/js/demo.temp.js"></script> -->
   
   
   <script type="text/javascript">
       
       function seleccionaFuncion(id_funcion,url){
          
          
            

            formData = {
                  funcion : "cargar_funcion",
                  id_funcion : id_funcion,
                  url: url
            };

            $.post("../pv/Hub.view",formData,function(data){

                $("#function-container").html(data);
            });
          
           
       }
       
    
       $(document).ready(function(){
       
        
    
       });
       
   </script>
   
</head>
<body class="theme-default">
 <!-- inicia el menu izquierdo -->
<nav class="left-menu" left-menu>
    <div class="logo-container">
        <a href="#" class="logo">
            <img src="../resources/img/logo_empresa2.png" alt="logo" />
            <img class="logo-inverse" src="../assets/common/img/logo-inverse.png" alt="Punto de venta" />
        </a>
    </div>
    
    <div class="left-menu-inner scroll-pane">
        <ul class="left-menu-list left-menu-list-root list-unstyled">
            
            
            
            <c:forEach var="menus" items="${admon.getMenuByRol(id_rol)}" >
                
                <li class="left-menu-list-submenu"> <!-- menú administración -->
                <a class="left-menu-link" href="javascript: void(0);">
                    <i class="left-menu-link-icon ${menus.imagen}"><!-- --></i>
                    ${menus.menu}
                </a>
                    <ul class="left-menu-list list-unstyled">
                        <c:forEach var="funciones" items="${admon.getFuncionByMenu(menus.id_menu)}" >
                            
                           <li>
                                <a class="left-menu-link" href="#" onclick="seleccionaFuncion(${funciones.id_funcion},'${funciones.url}')">
                                    ${funciones.funcion}
                                </a>
                            </li> 
                            
                            
                        </c:forEach>
                        
                        
                    </ul>
                </li>
            </c:forEach>
            
            <li class="left-menu-list-separator"><!-- --></li>
            
            <li>
                <a class="left-menu-link" href="#">
                    <i class="left-menu-link-icon icmn-profile"><!-- --></i>
                    Cerrar Sesión
                </a>
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
                        <img src="../assets/common/img/temp/avatars/nobody.jpg" alt="Alternative text to the image">
                    </span>
                </a>
                <ul class="dropdown-menu dropdown-menu-right" aria-labelledby="" role="menu">
                    <a class="dropdown-item" href="javascript:void(0)"><i class="dropdown-icon icmn-user"></i> Perfil</a>
                    <div class="dropdown-divider"></div>
                    <div class="dropdown-header">Main</div>
                    <a class="dropdown-item" href="javascript:void(0)"><i class="dropdown-icon icmn-circle-right"></i> Cambiar Rol</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="javascript:void(0)"><i class="dropdown-icon icmn-exit"></i> Cerrar Sesión</a>
                </ul>
            </div>
        </div>
        <div class="menu-info-block">
            <div class="left">
                <div class="header-buttons">
                    <div class="dropdown">
                        <a href="javascript: void(0);" class="dropdown-toggle dropdown-inline-button" data-toggle="dropdown" aria-expanded="false">
                            <i class="dropdown-inline-button-icon icmn-folder-open"></i>
                            <span class="hidden-lg-down">&nbsp;</span>
                            <span class="caret"></span>
                        </a>
                        
                    </div>
                    
                    
                </div>
            </div>
            
            <div class="right hidden-md-down margin-left-20">
                <div class="search-block">
                    <div class="form-input-icon form-input-icon-right">
                        <i class="icmn-search"></i>
                        <input type="text" class="form-control form-control-sm form-control-rounded" placeholder="Buscar...">
                        <button type="submit" class="search-block-submit "></button>
                    </div>
                </div>
            </div>
            
        </div>
    </div>
</nav>
<!-- fin del top menu -->
<section class="page-content">
<div class="page-content-inner">

    <!-- Dashboard -->
    <div id="function-container">
     <%--<jsp:include  page="../catalogos/articulo.jsp"></jsp:include> --%>
    <!-- End Dashboard -->
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
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                    
                  </div>
            </div>
        </div>
    </div>
    
</div>


<!-- Page Scripts -->
<script>
    $(function() {

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
            $('.custom-scroll').each(function() {
                $(this).jScrollPane({
                    autoReinitialise: true,
                    autoReinitialiseDelay: 100
                });
                var api = $(this).data('jsp'),
                        throttleTimeout;
                $(window).bind('resize', function() {
                    if (!throttleTimeout) {
                        throttleTimeout = setTimeout(function() {
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
