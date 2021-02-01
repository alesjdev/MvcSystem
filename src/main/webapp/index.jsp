<%@page import="com.alesjdev.mvcsystem.models.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.alesjdev.mvcsystem.dao.JdbcDaoCategory"%>
<%@page import="com.alesjdev.mvcsystem.dao.IDaoCategory"%>
<%@page import="com.alesjdev.mvcsystem.servlets.PropertiesLoader"%>
<%@page import="java.util.Properties"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    <%
        request.getRequestDispatcher("/CategoryController").forward(request, response);
    %>
    
    <body>
        
    </body>
</html>
