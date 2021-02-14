<%@page import="com.alesjdev.mvcsystem.models.*"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>MVC System - Search results</title>
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
        List <Category> catList = (List <Category>)request.getAttribute("catList");
        List <Product> prodList = (List <Product>)request.getAttribute("prodList");
    %>
    
    <body>       
        <jsp:include page="../layouts/header.jsp" />
        <div class="page-content">
            <div class="row">               
                <jsp:include page="../layouts/sidebar.jsp" />               
                <div class="col-md-10">
                    <!-- Categories div -->
                    <div class="col-md-6">
                        <div class="content-box-large">
                            <div class="panel-heading">
                                <div class="panel-title">Search results in categories</div>                          
                            </div>
                            <div class="panel-body">
                                <table class="table"> 
                                    <% for(Category cat : catList) { %>
                                    <tr>
                                        <td>
                                            <a href="CategoryController?action=showProducts&catId=<%= cat.getCategoryId() %>" 
                                               class="btn btn-link">
                                                <%= cat.getCategoryName() %>
                                            </a>
                                        </td>
                                    </tr>
                                    <% } %>
                                </table>                               
                            </div>
                        </div>
                    </div>
                    <!-- Products div -->
                    <div class="col-md-6">
                        <div class="content-box-large">
                            <div class="panel-heading">
                                <div class="panel-title">Search results in products</div>                          
                            </div>
                            <div class="panel-body">
                                <table class="table"> 
                                    <% for(Product prod : prodList) { %>
                                    <tr>
                                        <td>
                                            <a href="ProductController?action=update&prodId=<%= prod.getProductId() %>" 
                                               class="btn btn-link">
                                                <%= prod.getProductName() %>
                                            </a>
                                        </td>
                                    </tr>
                                    <% } %>
                                </table>                               
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