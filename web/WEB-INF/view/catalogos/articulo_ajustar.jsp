<%-- 
    Document   : articulo_ajustar
    Created on : 02-jun-2017, 13:49:23
    Author     : Rembao
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="articulo" class="app.choya.sys.pv.catalogo.Articulo" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript">
    
    
    $("input[type=text]").focus(function () {
            $(this).select();
        });
        
        
    $("#existencia").numeric();
    
    
    
    
</script>

<form name="frm-ajuste" id="frm-ajuste" method="post" class="form-horizontal">
    
    <c:forEach var="ab" items="${articulo.getExistenciaArticulo(requestScope.id)}" >
        
        
        <c:set var="id" value="${ab.id}" />
        <c:set var="descripcion" value="${ab.descripcion}" />
        <c:set var="existencia" value="${ab.existencia}" />
        <c:set var="existencia_anterior" value="${ab.existencia}" />
        
    </c:forEach>
    
    
    <input type="hidden" id="articulo_id_existencia" name="articulo_id_existencia" value="${id}"/>
    

            <div class="row">
                <div class="col-xs-12">
                    <div class="form-group">
                        <div class="col-md-12 col-lg-3">
                            <label for="descripcion" class="form-control-label">Artículo</label>
                        </div>
                        <div class="col-md-12 col-lg-9">
                            <div class="controls form-inline">
                                <input type="text" class="form-control" id="descripcion_existencia" name="descripcion_existencia" value="${descripcion}" placeholder="" size="50" maxlength="50" disabled=""/>

                            </div>
                        </div>
                    </div>
                </div>

                                
            </div>
            <div class="row">
                <div class="col-xs-12">&nbsp;</div>

            </div>                    
            <div class="row">
                <div class="col-xs-12">
                    <div class="form-group">
                        <div class="col-md-12 col-lg-3">
                            <label for="existencia" class="form-control-label">Existencia</label>
                        </div>
                        <div class="col-md-12 col-lg-9">
                            <div class="controls form-inline">
                                <input type="text" class="form-control" id="existencia" name="existencia" value="${existencia}" placeholder="" size="20" maxlength="20"/>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12">&nbsp;</div>

            </div>                    
            <div class="row">
                <div class="col-xs-12">
                    <div class="form-group">
                        <div class="col-md-12 col-lg-3">
                            <label for="comentario" class="form-control-label">Comentario</label>
                        </div>
                        <div class="col-md-12 col-lg-9">
                            <div class="controls form-inline">
                                <input type="text" class="form-control" id="comentario" name="comentario" value="" placeholder="" size="50" maxlength="100"/>

                            </div>
                        </div>
                    </div>
                </div>

                                
            </div>                    
                                
            <div class="row">
                <div class="col-xs-12">
                    <div id="mensaje_existencia"></div>
                </div>
            </div>
            <input type="hidden" id="existencia_anterior"  name="existencia_anterior" value="${existencia_anterior}"    />                   
        
</form>                            