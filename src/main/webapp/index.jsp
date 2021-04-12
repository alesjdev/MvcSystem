<%@page import="com.alesjdev.mvcsystem.models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>MVC System - Main page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Bootstrap -->
        <link href="<%= request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- styles -->
        <link href="<%= request.getContextPath()%>/css/styles.css" rel="stylesheet">

    </head>
    <%
        User user = (User)request.getSession().getAttribute("user");
    %>
    <body>       
        <jsp:include page="/WEB-INF/layouts/header.jsp" />
        <div class="page-content">
            <div class="row">               
                <jsp:include page="/WEB-INF/layouts/sidebar.jsp" />               
                <div class="col-md-10 text-center">
                    
                    <h2 style="color:cornflowerblue;"><b>Welcome to the system.</b></h2>
                    <h4>
                        <%
                            if(user==null){
                        %>
                        You aren't logged in. Either <a href="<%=request.getContextPath()%>/signin.jsp">Log in</a> now or
                        <a href="<%=request.getContextPath()%>/signup.jsp">Create an account</a> (you'll need a verification code)
                        <%
                            } else {
                        %>
                        You are logged in as <span style='color:green;'><%= user.getUsername() %></span>
                        <% } %>
                    </h4>    
                    
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/layouts/footer.jsp" />
    </body>
</html>
