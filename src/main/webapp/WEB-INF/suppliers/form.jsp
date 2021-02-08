<%@page import="java.util.List"%>
<%@page import="com.alesjdev.mvcsystem.models.Supplier"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>MVC System - Suppliers</title>
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
        String formType = (String) request.getAttribute("formType");
        Supplier supp = null;
        if(formType.equals("update")){
            supp = (Supplier)request.getAttribute("supplier");
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
                            <div class="panel-title"><%= formType.substring(0, 1).toUpperCase() + formType.substring(1) %> supplier</div>                           
                        </div>
                        <div class="panel-body">
                            
                            <form class="form-horizontal" role="form" action="SupplierController" method="POST">
                                <!-- Supplier ID -->
                                <div class="form-group">
                                    <input type="hidden" name="action" value="<%= formType %>">
                                    <label for="supplierId" class="col-sm-2 control-label">Supplier ID</label>
                                    <div class="col-sm-10">
                                        <input type="number" class="form-control" name="suppId" id="supplierId" 
                                            <% if(formType.equals("new")) { %>
                                                placeholder="Insert supplier ID"
                                            <% } else if (formType.equals("update")) { %>
                                                value="<%= supp.getSupplierId() %>" readonly
                                            <% } %>   
                                        >
                                    </div>
                                </div>
                                <!-- Supplier Name -->
                                <div class="form-group">
                                    <label for="supplierName" class="col-sm-2 control-label">Supplier name</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="suppName" id="supplierName"
                                            <% if(formType.equals("new")) { %>
                                                placeholder="Insert supplier name"
                                            <% } else if (formType.equals("update")) { %>
                                                value="<%= supp.getSupplierName() %>"
                                            <% } %>    
                                        >
                                    </div>
                                </div>
                                <!-- Supplier Contact Name -->
                                <div class="form-group">
                                    <label for="supplierContactName" class="col-sm-2 control-label">Supplier contact name</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="suppContactName" id="supplierContactName"
                                            <% if(formType.equals("new")) { %>
                                                placeholder="Insert supplier contact name"
                                            <% } else if (formType.equals("update")) { %>
                                                value="<%= supp.getSupplierContactName() %>"
                                            <% } %>    
                                        >
                                    </div>
                                </div>
                                <!-- Supplier Phone Number -->
                                <div class="form-group">
                                    <label for="supplierPhoneNumber" class="col-sm-2 control-label">Supplier phone number</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="suppPhoneNumber" id="supplierPhoneNumber"
                                            <% if(formType.equals("new")) { %>
                                                placeholder="Insert supplier phone number"
                                            <% } else if (formType.equals("update")) { %>
                                                value="<%= supp.getSupplierPhoneNumber() %>"
                                            <% } %>    
                                        >
                                    </div>
                                </div>
                                <!-- Submit button -->
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <button type="submit" class="btn btn-primary">
                                            <%= formType.substring(0, 1).toUpperCase() + formType.substring(1) %> supplier
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
        <jsp:include page="../layouts/footer.jsp" />F
    </body>
</html>
