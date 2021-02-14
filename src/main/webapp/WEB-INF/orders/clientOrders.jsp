<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.alesjdev.mvcsystem.models.*"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>MVC System - Client Orders</title>
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
        List <Order> clientOrders = (List <Order>)request.getAttribute("clientOrders");
        String clientName = (String)request.getAttribute("clientName");
    %>
    
    <body>       
        <jsp:include page="../layouts/header.jsp" />
        <div class="page-content">
            <div class="row">               
                <jsp:include page="../layouts/sidebar.jsp" />               
                <div class="col-md-10">

                    <!-- Table with Orders -->
                    <div class="content-box-large">
                        <div class="panel-heading">
                            <div class="panel-title">Orders made by <%= clientName %></div>                          
                        </div>
                        <div class="panel-body">
                            <div class="col-sm-12 text-center" style="margin-bottom: 25px;">
                                <button class="btn btn-primary" onclick="history.back()">Back to client list</button>
                            </div>
                            <table class="table">
                                <thead>
                                    <tr>                                       
                                        <th>ID</th>
                                        <th>Date</th>
                                        <th>Employee</th>
                                        <th>Discount applied</th>
                                        <th>Amount</th>                           
                                    </tr>
                                </thead>
                                
                                <tbody>  
                                    <% for(Order order : clientOrders) { %>
                                    <tr>
                                        
                                        <td>
                                            <%= order.getOrderId() %>
                                        </td>
                                        <td>
                                            <%= new SimpleDateFormat("dd-MM-yyyy").format(order.getOrderDate()) %>
                                        </td>
                                        <td>
                                            <%= order.getEmployee() %>
                                        </td>
                                        <td>
                                            <%= order.getDiscount() %> %
                                        </td>
                                        <td>
                                            <%= order.getSimplifiedAmount() %>
                                        </td>
                                        
                                        <td class="text-right">
                                            <a href="OrderController?action=seeDetail&orderId=<%= order.getOrderId() %>" class="btn btn-primary">
                                                <span class="glyphicon glyphicon-search"></span>
                                            </a>
                                        </td>
                                        
                                    </tr>
                                    <% } %>                                 
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
