<%@page import="java.util.List"%>
<%@page import="com.alesjdev.mvcsystem.models.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>MVC System - Clients</title>
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
        Client cli = null;
        if(formType.equals("update")){
            cli = (Client)request.getAttribute("client");
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
                            <div class="panel-title"><%= formType.substring(0, 1).toUpperCase() + formType.substring(1) %> client</div>                           
                        </div>
                        <div class="panel-body">
                            
                            <form class="form-horizontal" role="form" action="ClientController" method="POST">
                                <!-- Client ID -->
                                <div class="form-group">
                                    <input type="hidden" name="action" value="<%= formType %>">
                                    <label for="clientId" class="col-sm-2 control-label">Client ID</label>
                                    <div class="col-sm-10">
                                        <input type="number" class="form-control" name="cliId" id="clientId" 
                                            <% if(formType.equals("new")) { %>
                                                placeholder="Insert client ID"
                                            <% } else if (formType.equals("update")) { %>
                                                value="<%= cli.getClientId() %>" readonly
                                            <% } %>   
                                        >
                                    </div>
                                </div>
                                <!-- Client Company Name -->
                                <div class="form-group">
                                    <label for="clientCompanyName" class="col-sm-2 control-label">Client Company</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="cliCompanyName" id="clientCompanyName"
                                            <% if(formType.equals("new")) { %>
                                                placeholder="Insert client company"
                                            <% } else if (formType.equals("update")) { %>
                                                value="<%= cli.getClientCompanyName() %>"
                                            <% } %>    
                                        >
                                    </div>
                                </div>
                                <!-- Client Contact Name -->
                                <div class="form-group">
                                    <label for="clientContactName" class="col-sm-2 control-label">Client Contact name</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="cliContactName" id="clientContactName"
                                            <% if(formType.equals("new")) { %>
                                                placeholder="Insert client contact name"
                                            <% } else if (formType.equals("update")) { %>
                                                value="<%= cli.getClientContactName() %>"
                                            <% } %>    
                                        >
                                    </div>
                                </div>
                                <!-- Client Address -->
                                <div class="form-group">
                                    <label for="clientAddress" class="col-sm-2 control-label">Client Address</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="cliAddress" id="clientAddress"
                                            <% if(formType.equals("new")) { %>
                                                placeholder="Insert client address"
                                            <% } else if (formType.equals("update")) { %>
                                                value="<%= cli.getClientAddress() %>"
                                            <% } %>    
                                        >
                                    </div>
                                </div>
                                <!-- Client Email -->
                                <div class="form-group">
                                    <label for="clientEmail" class="col-sm-2 control-label">Client Email</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="cliEmail" id="clientEmail"
                                            <% if(formType.equals("new")) { %>
                                                placeholder="Insert client email address"
                                            <% } else if (formType.equals("update")) { %>
                                                value="<%= cli.getClientEmail() %>"
                                            <% } %>    
                                        >
                                    </div>
                                </div>
                                <!-- Client Phone Number -->
                                <div class="form-group">
                                    <label for="clientPhoneNumber" class="col-sm-2 control-label">Client Phone Number</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="cliPhoneNumber" id="clientPhoneNumber"
                                            <% if(formType.equals("new")) { %>
                                                placeholder="Insert client phone number"
                                            <% } else if (formType.equals("update")) { %>
                                                value="<%= cli.getClientPhoneNumber() %>"
                                            <% } %>    
                                        >
                                    </div>
                                </div>
                                <!-- Submit button -->
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <button type="submit" class="btn btn-primary">
                                            <%= formType.substring(0, 1).toUpperCase() + formType.substring(1) %> client
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <!-- End of form box -->
                    
                </div>
            </div>
        </div>
        <jsp:include page="../layouts/footer.jsp" />F
    </body>
</html>
