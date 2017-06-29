<%@page  import="javax.servlet.http.HttpSession" %>

<%
    HttpSession sesion = request.getSession(false);
    sesion.setMaxInactiveInterval(36000);
    
    sesion.setAttribute("draw", 1);
    
    //sesion.setAttribute("usuario_id", 1);
    //sesion.setAttribute("serie", "A");
    
    sesion.setAttribute("sucursal",1);
    
    if(sesion.getAttribute("usuario_id") == null){
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
        rd.forward(request, response);  
    } 
    

%>
    