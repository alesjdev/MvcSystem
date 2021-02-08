<%@page import="java.util.List"%>
<%@page import="com.alesjdev.mvcsystem.models.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>MVC System - Show products in category</title>
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
        // Validate and obtain list of products.
        List <Product> productList = null;
        if(request.getAttribute("productList") != null){
            productList = (List <Product>)request.getAttribute("productList");
        }
        
        // Validate and obtain category
        Category category = null;       
        if(request.getAttribute("category") != null){          
            category = (Category)request.getAttribute("category");
        }
    %>
    
    <body>       
        <jsp:include page="../layouts/header.jsp" />
        <div class="page-content">
            <div class="row">               
                <jsp:include page="../layouts/sidebar.jsp" />               
                <div class="col-md-10">
                    
                    <!-- Table with products -->
                    <div class="content-box-large">
                        <div class="panel-heading">
                            <div class="panel-title">Products in category <%= category %></div>
                          
                        </div>
                        <div class="panel-body">
                            <table class="table">
                                <thead>
                                    <tr>                                       
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Supplier</th>
                                        <th>Unit price</th>
                                        <th>Stock</th>                                       
                                    </tr>
                                </thead>
                                
                                <tbody>
                                    <% for (Product prod : productList) { %>
                                    <tr>
                                        <td>
                                            <%= prod.getProductId() %>
                                        </td>
                                        <td>
                                            <%= prod.getProductName() %>
                                        </td>                                       
                                        <td>
                                            <%= prod.getSupplier() %>
                                        </td>
                                        <td>
                                            <%= prod.getProductUnitPrice() %>
                                        </td>
                                        <td>
                                            <%= prod.getProductStock() %>
                                        </td>                                    
                                    </tr>
                                    <% } %>
                                </tbody>
                            </table>
                                <div class="col-sm-12 text-left">
                                    <button class="btn btn-primary" onclick="history.back()">Ok</button>
                                </div>
                        </div>
                    </div>
                    <!-- End of table with products -->
                    
                </div>
            </div>
        </div>
        <br>
        <jsp:include page="../layouts/footer.jsp" />
    </body>
</html>
