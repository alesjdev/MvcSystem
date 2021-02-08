<%@page import="java.util.List"%>
<%@page import="com.alesjdev.mvcsystem.models.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>MVC System - Products</title>
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
        
        // Validate and obtain message
        String message = "";       
        if(request.getSession().getAttribute("message") != null){          
            message = (String)request.getSession().getAttribute("message");
        }
    %>
    
    <body>       
        <jsp:include page="../layouts/header.jsp" />
        <div class="page-content">
            <div class="row">               
                <jsp:include page="../layouts/sidebar.jsp" />               
                <div class="col-md-10">
                    
                    <% if (!message.isEmpty()) { %>
                        <div class="alert alert-success alert-dismissible fade in">
                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            <%= message %>                       
                        </div>
                    <% request.getSession().removeAttribute("message"); %>
                    <% } %>
                    
                    <!-- Table with products -->
                    <div class="content-box-large">
                        <div class="panel-heading">
                            <div class="panel-title">Product list</div>
                          
                        </div>
                        <div class="panel-body">
                            <table class="table">
                                <thead>
                                    <tr>                                       
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Category</th>
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
                                            <%= prod.getCategory() %>
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
                                      
                                        <!-- Edit & Delete buttons -->
                                        <td class="text-right">
                                            
                                            <a href="ProductController?action=update&prodId=<%= prod.getProductId() %>"
                                               class="btn btn-warning">
                                                <span class="glyphicon glyphicon-pencil"></span>
                                            </a>
                                            
                                               <form action="ProductController" method="POST" style="display:inline">
                                                <input type="hidden" name="action" value="delete">
                                                <input type="hidden" name="prodId" value="<%= prod.getProductId() %>">
                                                <button type="submit" class="btn btn-danger">
                                                    <span class="glyphicon glyphicon-trash"></span>
                                                </button>
                                            </form>                                                
                                        </td>
                                        
                                    </tr>
                                    <% } %>
                                </tbody>
                            </table>
                                <a href="ProductController?action=new" method="GET" class="btn btn-primary">
                                    <span class="glyphicon glyphicon-plus"></span>
                                </a>
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