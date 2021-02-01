<%@page import="java.util.List"%>
<%@page import="com.alesjdev.mvcsystem.models.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>MVC System</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Bootstrap -->
        <link href="<%= request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- styles -->
        <link href="<%= request.getContextPath()%>/css/styles.css" rel="stylesheet">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
    </head>
    
    <%              
        String message = "";
        
        if(request.getAttribute("errorMessage") != null){           
            message = (String)request.getAttribute("errorMessage");
        } else {
            response.sendRedirect("index.jsp");
        }
    %>
    
    <body>       
        <jsp:include page="/WEB-INF/layouts/header.jsp" />
        <div class="page-content">
            <div class="row">               
                <jsp:include page="/WEB-INF/layouts/sidebar.jsp" />               
                <div class="col-md-10">
                    
                    <h2 style="color:crimson;">Error:</h2>
                    <h3> <%= message %> </h3>
                    
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/layouts/footer.jsp" />
    </body>
</html>
