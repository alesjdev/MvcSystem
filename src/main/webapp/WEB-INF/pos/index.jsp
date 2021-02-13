<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.time.Instant"%>
<%@page import="java.util.Date"%>
<%@page import="com.alesjdev.mvcsystem.models.*"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>MVC System - POS</title>
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
        // Get lists
        List <Employee> employeeList = (List<Employee>)request.getAttribute("employeeList");
        List <Product> productList = (List<Product>)request.getAttribute("productList");
        List <Client> clientList = (List<Client>)request.getAttribute("clientList");
        
        //Get current order
        Order order = (Order)request.getSession().getAttribute("order");
    %>
    
    <body>       
        <jsp:include page="../layouts/header.jsp" />
        <div class="page-content">
            <div class="row">               
                <jsp:include page="../layouts/sidebar.jsp" />               
                <div class="col-md-10">
                    <div class="row">
                        <div class="col-md-12 panel-primary">
                            <!-- Panel Header -->
                            <div class="content-box-header panel-heading">
                                <div class="panel-title">
                                    System P.O.S.
                                </div>                              
                            </div>
                            
                            <!-- Panel Body -->
                            <div class="content-box-large box-with-header">
                                
                                <!-- Form Start -->
                                <form action="PosController" method="POST">
                                    <input type="hidden" name="action" value="finishAndPay">
                                    <!-- Main row with selectors and payment -->
                                    <div class="row">
                                        <!-- Selectors div -->
                                        <div class="col-sm-6" style="margin-top: 15px;">                                         
                                            <div class="row">
                                                <!-- Date div -->
                                                <div class="col-sm-6">
                                                    Order date:
                                                    <input type="date" class="form-control" value="<%= order.getOrderDate().toString() %>">
                                                </div>
                                            </div>
                                            <div class="row" style="margin-top: 15px;">
                                                <!-- Employee div -->
                                                <div class="col-sm-6">
                                                    Choose employee
                                                    <select name="employeeId" id="" class="form-control">
                                                        <option value="" selected disabled>Select an employee</option>
                                                        <% for(Employee emp : employeeList) { %>
                                                            <option value="<%= emp.getEmployeeId() %>"><%= emp.toString() %></option>
                                                        <% } %>
                                                    </select>
                                                </div>
                                                <!-- Client div -->
                                                <div class="col-sm-6">
                                                    Choose client
                                                    <select name="clientId" id="" class="form-control">
                                                        <option value="" selected disabled>Select a client</option>
                                                        <% for(Client cli : clientList) { %>
                                                            <option value="<%= cli.getClientId() %>"><%= cli %></option>
                                                        <% } %>
                                                    </select>
                                                </div>
                                            </div>
                                            
                                        </div>
                                        
                                        <!-- Payment div -->
                                        <div class="col-sm-6" style="margin-top: 15px;">
                                            <div class="col-sm-12 text-center">
                                                <input type="submit" class="btn btn-lg btn-success" value="Finish and Pay">
                                            </div>
                                            <div class="col-sm-12 text-center">
                                                <h2 style="font-size:80px; color: #ff9900;">
                                                    <%= order.getSimplifiedAmount() %> £
                                                </h2>
                                            </div>
                                        </div>
                                    </div>                                    
                                </form>
                                
                                <br><hr>
                                
                                <!-- Product handler row -->
                                <div class="row">
                                    <!-- Row to select and add products -->
                                    <form action="PosController" method="POST">
                                        <input type="hidden" name="action" value="addProduct">
                                        
                                        <div class="col-sm-3">
                                            Add Product ID
                                            <input type="number" step="1" class="form-control" placeholder="Search by ID">
                                        </div>
                                        
                                        <div class="col-sm-3">
                                            Choose Product
                                            <select name="prodId" id="" class="form-control">
                                                <option value="" selected disabled>Select a product</option>
                                                <% for(Product prod : productList) { %>
                                                    <option value="<%= prod.getProductId() %>"><%= prod %></option>
                                                <% } %>
                                            </select>
                                        </div>
                                        <div class="col-sm-3">
                                            Quantity
                                            <input type="number" name="prodQuantity" step="1" class="form-control" placeholder="Choose quantity to add">
                                        </div>
                                        
                                        <div class="col-sm-3 text-center">
                                            <br>
                                            <input type="submit" class="btn btn-primary" value="Add product">
                                        </div>                                       
                                    </form>
                                    
                                </div>
                                <hr>
                                
                                <!-- Table to display added products -->
                                <div class="row">
                                    <div class="col-sm-12">
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
                                                <% for(OrderDetail details : order.getDetails()) { %>
                                                <tr>
                                                    <td><%= details.getProduct().getProductName() %></td>
                                                    <td><%= details.getQuantity() %></td>
                                                    <td><%= details.getProduct().getProductUnitPrice() %></td>
                                                    <td><%= details.getSimplifiedAmount() %></td>
                                                </tr>
                                                <% } %>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <br>
                                
                                <!-- Back / Cancel button -->
                                <div class="col-sm-12 text-center">
                                    <a href="PosController?action=cancelOrder" class="btn btn-danger">Remove all items</a>
                                </div>
                                <br><br>
                                
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