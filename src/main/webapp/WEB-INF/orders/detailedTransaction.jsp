<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="com.alesjdev.mvcsystem.models.*"%>
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
        Order order = (Order)request.getAttribute("order");
        List <OrderDetail> orderDetails = order.getDetails();
        double totalWithoutDiscount=0.0;
    %>
    
    <body>       
        <jsp:include page="../layouts/header.jsp" />
        <div class="page-content">
            <div class="row">               
                <jsp:include page="../layouts/sidebar.jsp" />               
                <div class="col-md-10">
                  
                    <!-- Panel with order details -->
                    <div class="content-box-large">
                        <div class="content-box-header panel-heading">
                            <div class="panel-title"> Detailed transaction for order ID: <%= order.getOrderId() %> </div>                          
                        </div>
                        <div class="panel-body">
                            
                            <!-- Back button -->
                            <div class="col-sm-12 text-center">
                                <button class="btn btn-primary" onclick="history.back()">Back to Order list</button>
                            </div>
                            <br><br><hr>
                            
                            <!-- Header -->
                            <div class="row">
                                <div class="col-sm-3">
                                    <b>Order date:
                                        <h4><%= new SimpleDateFormat("dd-MM-yyyy").format(order.getOrderDate()) %></h4></b>
                                </div>
                                <div class="col-sm-3">
                                    <b>Client:
                                    <h4><%= order.getClient() %></h4></b>
                                </div>
                                <div class="col-sm-3">
                                    <b>Employee:
                                    <h4><%= order.getEmployee() %></h4></b>
                                </div>
                            </div>
                            
                            <hr>
                            
                            <!-- Shopping details -->
                            <div class="row">
                                <table class="table">
                                    <thead>
                                        <tr>                                       
                                            <th>Product</th>
                                            <th>Quantity</th>
                                            <th>Unit Price</th>
                                            <th>Amount</th>                          
                                        </tr>
                                    </thead>
                                    <tbody>  
                                        <% for(OrderDetail details : orderDetails) { %>
                                        <tr>

                                            <td>
                                                <%= details.getProduct() %>
                                            </td>
                                            <td>
                                                <%= details.getQuantity() %>
                                            </td>
                                            <td>
                                                <%= details.getProduct().getProductUnitPrice() %>
                                            </td>
                                            <td>
                                                <%= details.getAmount() %>
                                                <% totalWithoutDiscount+=details.getAmount(); %>
                                            </td>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table>
                            </div>
                                    
                            <hr>
                            
                            <!-- Total, discount applied and total after discount -->
                            <div class="row">
                                <div class="col-sm-offset-9 col-sm-2 text-right" style="font-size: 20px;">
                                    Total:
                                </div>
                                <div class="col-sm-1 text-left" style="font-size: 20px;">
                                    <%= totalWithoutDiscount %> £
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-offset-9 col-sm-2 text-right" style="font-size: 20px;">
                                    Discount applied:
                                </div>
                                <div class="col-sm-1 text-left" style="font-size: 20px;">
                                    <%= order.getDiscount() %> %
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-offset-9 col-sm-2 text-right" style="font-size: 30px;">
                                    To pay:
                                </div>
                                <div class="col-sm-1 text-left" style="font-size: 30px;">
                                    <%= order.getSimplifiedAmount() %> £
                                </div>
                            </div>
                        </div>
                    </div>
              
                </div>
            </div>
        </div>
        <br>
        <jsp:include page="../layouts/footer.jsp" />
    </body>
</html>