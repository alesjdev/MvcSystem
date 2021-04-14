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
        User user = (User) request.getSession().getAttribute("user");
        String accountType = user.getAccountType() == 0 ? "Manager account" : "Employee account";
    %>
    <body>       
        <jsp:include page="/WEB-INF/layouts/header.jsp" />
        <div class="page-content">
            <div class="row">               
                <jsp:include page="/WEB-INF/layouts/sidebar.jsp" />               
                <div class="col-md-10">
                    <div class="row">
                        <div class="content-box-large">
                            <div class="panel-heading">
                                <div class="panel-title"><u>Account manager</u></div>
                            </div>
                            <div class="panel-body">
                                <h4>Account username/email:</h4><h3><b> <%= user.getUsername()%></h3></b><br>
                                <h4>Account password:</h4><h3><b>  <%= user.getPassword()%></h3></b><br>
                                <h4>Account type:</h4><h3 style="color:<%= accountType.equals("Manager account") ? "green" : "blue"%>">
                                    <b>  <%= accountType%></h3></b><br>
                                <h4>Account options:</h4>
                                <a href="#"
                                   class="btn btn-warning">
                                    Edit (disabled)
                                </a>

                                <form onsubmit="return confirm('Are you sure you want to delete this account?');" 
                                      action="UserController" method="POST" style="display:inline">
                                    <input type="hidden" name="action" value="deleteUser">
                                    <input type="hidden" name="accId" value="<%= user.getUserId() %>">
                                    <button type="submit" class="btn btn-danger">
                                        Delete account
                                    </button>
                                </form> 
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>   
        <jsp:include page="/WEB-INF/layouts/footer.jsp" />
    </body>

    <script>
        function confirmation() {
            var txt;
            var r = confirm("Are you sure you want to delete this account?");
            if (r == true) {
                txt = "Account has been successfully removed from the database.";
            } else {
                txt = "Account deletion cancelled.";
            }
            alert(txt);
        }
    </script>
</html>
