<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="com.alesjdev.mvcsystem.models.*"%>
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
        // Get the categories & supplier lists (and the product if updating)
        List<Supplier> supplierList = (List<Supplier>)request.getAttribute("suppList");
        List<Category> categoryList = (List<Category>)request.getAttribute("catList");
        
        String formType = (String) request.getAttribute("formType");
        Product prod = null;
        if(formType.equals("update")){
            prod = (Product)request.getAttribute("product");
        }
    %>

    <body>       
        <jsp:include page="../layouts/header.jsp" />
        <div class="page-content">
            <div class="row">               
                <jsp:include page="../layouts/sidebar.jsp" />               
                <div class="col-md-10">
                    
                    <!-- Start of Form Box -->
                    <div class="content-box-large">
                        <div class="panel-heading">
                            <div class="panel-title"><%= formType.substring(0, 1).toUpperCase() + formType.substring(1) %> product</div>                           
                        </div>
                        <div class="panel-body">
                            
                            <form class="form-horizontal" role="form" action="ProductController" method="POST">
                                <!-- Product ID -->
                                <div class="form-group">
                                    <input type="hidden" name="action" value="<%= formType %>">
                                    <label for="productId" class="col-sm-2 control-label">Product ID</label>
                                    <div class="col-sm-10">
                                        <input type="number" class="form-control" name="prodId" id="productId" 
                                            <% if(formType.equals("new")) { %>
                                                placeholder="Insert product ID"
                                            <% } else if (formType.equals("update")) { %>
                                                value="<%= prod.getProductId() %>" readonly
                                            <% } %>   
                                        >
                                    </div>
                                </div>
                                <!-- Product Name -->
                                <div class="form-group">
                                    <label for="productName" class="col-sm-2 control-label">Product name</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="prodName" id="productName"
                                            <% if(formType.equals("new")) { %>
                                                placeholder="Insert product name"
                                            <% } else if (formType.equals("update")) { %>
                                                value="<%= prod.getProductName() %>"
                                            <% } %>    
                                        >
                                    </div>
                                </div>
                                <!-- Product Category -->
                                <div class="form-group">
                                    <label for="productCategory" class="col-sm-2 control-label">Product Category</label>
                                    <div class="col-sm-10">
                                        <select class="form-control" name="prodCategory" id="productCategory">
                                            <% for (Category cat : categoryList) { %>
                                                <option value="<%= cat.getCategoryId() %>"
                                                    <% if( formType.equals("update") && (prod.getCategory().getCategoryId() == cat.getCategoryId()) ){ %>
                                                        selected
                                                    <% } %>
                                                >
                                                    <%= cat %>
                                                </option>
                                            <% } %>    
                                        </select>
                                    </div>
                                </div>
                                <!-- Product Supplier -->
                                <div class="form-group">
                                    <label for="productSupplier" class="col-sm-2 control-label">Product Supplier</label>
                                    <div class="col-sm-10">
                                        <select class="form-control" name="prodSupplier" id="productSupplier">
                                            <% for (Supplier supp : supplierList) { %>
                                                <option value="<%= supp.getSupplierId() %>"
                                                    <% if( formType.equals("update") && (prod.getSupplier().getSupplierId() == supp.getSupplierId()) ){ %>
                                                        selected
                                                    <% } %>
                                                >
                                                    <%= supp %>
                                                </option>
                                            <% } %>    
                                        </select>
                                    </div>
                                </div>
                                <!-- Product Unit Price -->
                                <div class="form-group">
                                    <label for="productUnitPrice" class="col-sm-2 control-label">Product Unit Price</label>
                                    <div class="col-sm-10">
                                        <input type="number" step="0.01" min="0" class="form-control" name="prodUnitPrice" id="productUnitPrice" 
                                            <% if(formType.equals("new")) { %>
                                                placeholder="Insert product unit price"
                                            <% } else if (formType.equals("update")) { %>
                                                value="<%= prod.getProductUnitPrice() %>"
                                            <% } %>   
                                        />
                                    </div>
                                </div>
                                <!-- Product Stock -->
                                <div class="form-group">
                                    <label for="productStock" class="col-sm-2 control-label">Product Stock</label>
                                    <div class="col-sm-10">
                                        <input type="number" class="form-control" name="prodStock" id="productStock" 
                                            <% if(formType.equals("new")) { %>
                                                placeholder="Insert product stock"
                                            <% } else if (formType.equals("update")) { %>
                                                value="<%= prod.getProductStock() %>"
                                            <% } %>   
                                        >
                                    </div>
                                </div>                                
                                <!-- Submit button -->
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <button type="submit" class="btn btn-primary">
                                            <%= formType.substring(0, 1).toUpperCase() + formType.substring(1) %> product
                                        </button>
                                    </div>
                                </div>
                            </form>
                            <div class="col-sm-12 text-center">
                                <button class="btn btn-danger" onclick="history.back()">Cancel</button>
                            </div>
                        </div>
                    </div>
                    <!-- End of form box -->
                    
                </div>
            </div>
        </div>
        <jsp:include page="../layouts/footer.jsp" />
    </body>
</html>
