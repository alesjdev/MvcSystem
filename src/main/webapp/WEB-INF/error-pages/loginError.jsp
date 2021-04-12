<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>MVC System - Login</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Bootstrap -->
        <link href="<%= request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- styles -->
        <link href="<%= request.getContextPath()%>/css/styles.css" rel="stylesheet">

        <link href="<%= request.getContextPath()%>/css/login.css" rel="stylesheet">
        
    </head>
    <% 
        if (request.getSession().getAttribute("user") != null)  {
            request.getSession().removeAttribute("user");
        }
    %>
    <body class="login-bg">
        <div class="container-fluid">
            <div class="page-content container">
                <div class="row center-block">
                    <div class="col-md-4 col-md-offset-4">
                        <div class="login-wrapper">
                            <div class="box">
                                <div class="alert alert-danger">
                                    <strong>Error:</strong> <%= pageContext.getException().getMessage() %>
                                </div>
                                <div class="content-wrap">
                                    <h6>Sign In</h6>
                                    <form action="UserController" method="POST">
                                        <input type="hidden" name="action" value="validateUser">
                                        <input class="form-control" type="text" name="username" placeholder="E-mail address">
                                        <input class="form-control" type="password" name="password" placeholder="Password">
                                        <div class="action">
                                            <input type="submit" value="Login" class="btn btn-primary signup">
                                        </div>
                                    </form>
                                </div>
                            </div>

                            <div class="already">
                                Don't have an account yet?<br>
                                <u>You must contact a manager for a sign-up code to be able to create an account.</u><br>
                                If you have one, you can <a href="<%=request.getContextPath()%>/signup.jsp" style="color:greenyellow;">Sign Up</a><br>                            
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://code.jquery.com/jquery.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="js/custom.js"></script>
    </body>
</html>