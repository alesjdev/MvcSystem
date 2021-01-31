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
        Properties prop = PropertiesLoader.getProperties();
        String display = prop.getProperty("dbaccess");
    %>
    
    <body>
        <h1>Hello World!</h1>
        <h2> <%= display %> </h2>
    </body>
</html>
