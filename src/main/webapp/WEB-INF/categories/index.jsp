<%@page import="java.util.List"%>
<%@page import="com.alesjdev.mvcsystem.models.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>MVC System</title>
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
        List <Category> categoryList = (List <Category>)request.getAttribute("categoryList");
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
                <div class="col-md-offset-2 col-md-6">
                    
                    <% if (!message.isEmpty()) { %>
                        <div class="alert alert-success alert-dismissible fade in">
                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            <%= message %>                       
                        </div>
                    <% request.getSession().removeAttribute("message"); %>
                    <% } %>
                    
                    <!-- Table with categories -->
                    <div class="content-box-large">
                        <div class="panel-heading">
                            <div class="panel-title">Category list</div>
                          
                        </div>
                        <div class="panel-body">
                            <table class="table">
                                <thead>
                                    <tr>                                       
                                        <th>ID</th>
                                        <th>Name</th>                                                                             
                                    </tr>
                                </thead>
                                
                                <tbody>
                                    <% for (Category cat : categoryList) { %>
                                    <tr>
                                        <td>
                                            <%= cat.getCategoryId() %>
                                        </td>
                                        <td>
                                            <%= cat.getCategoryName()%>
                                        </td>
                                        <td class="text-right">
                                            
                                            <a href="CategoryController?action=update&catId=<%= cat.getCategoryId() %>"
                                               class="btn btn-warning">
                                                <span class="glyphicon glyphicon-pencil"></span>
                                            </a>
                                            <a href="CategoryController?action=delete&catId=<%= cat.getCategoryId() %>"
                                               class="btn btn-danger">
                                                <span class="glyphicon glyphicon-trash"></span>
                                            </a>                                            
                                        </td>
                                    </tr>
                                    <% } %>
                                </tbody>
                            </table>
                                <a href="CategoryController?action=new" method="GET" class="btn btn-primary">
                                    <span class="glyphicon glyphicon-plus"></span>
                                </a>
                        </div>
                    </div>
                    <!-- End of table with categories -->
                    
                </div>
            </div>
        </div>
        <jsp:include page="../layouts/footer.jsp" />
    </body>
</html>