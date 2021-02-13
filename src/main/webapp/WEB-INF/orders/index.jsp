<%@page import="com.alesjdev.mvcsystem.models.*"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>MVC System - Orders</title>
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
        Order completedOrder = (Order)request.getSession().getAttribute("completedOrder");
    %>
    
    <body>       
        <jsp:include page="../layouts/header.jsp" />
        <div class="page-content">
            <div class="row">               
                <jsp:include page="../layouts/sidebar.jsp" />               
                <div class="col-md-10">
                    
                    <% if(completedOrder != null) { %>
                        <div class="alert alert-success alert-dismissible fade in">
                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            Order with ID <%= completedOrder.getOrderId() %> was successfully created.
                        </div>
                    <% request.getSession().removeAttribute("completedOrder"); %>
                    <% request.getSession().removeAttribute("order"); %>
                    <% } %>
                  
                    <!-- Table with Orders -->
                    <div class="content-box-large">
                        <div class="panel-heading">
                            <div class="panel-title">Order list</div>                          
                        </div>
                        <div class="panel-body">
                            <table class="table">
                                <thead>
                                    <tr>                                       
                                        <th>ID</th>
                                        <th>Date</th>
                                        <th>Client</th>
                                        <th>Amount</th>                           
                                    </tr>
                                </thead>
                                
                                <tbody>                                   
                                    <tr>
                                        <!-- TODO -->
                                        <td>
                                            <%= "asd" %>
                                        </td>
                                        <td>
                                            <%= "asd" %>
                                        </td>
                                        <td>
                                            <%= "asd" %>
                                        </td>
                                        <td>
                                            <%= "asd" %>
                                        </td>
                                        
                                        <td class="text-right">
                                            <a href="#" class="btn btn-primary">
                                                <span class="glyphicon glyphicon-search"></span>
                                            </a>
                                        </td>
                                        <!-- /TODO -->
                                    </tr>                                   
                                </tbody>
                            </table>                               
                        </div>
                    </div>
                    <!-- End of table with orders -->                    
                </div>
            </div>
        </div>
        <br>
        <jsp:include page="../layouts/footer.jsp" />
    </body>
</html>