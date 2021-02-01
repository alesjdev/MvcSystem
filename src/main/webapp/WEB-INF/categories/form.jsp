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
        List<Category> categoryList = (List<Category>) request.getAttribute("categoryList");
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
                            <div class="panel-title">Create new category</div>                           
                        </div>
                        <div class="panel-body">
                            <form class="form-horizontal" role="form" action="CategoryController" method="POST">
                                <div class="form-group">
                                    <input type="hidden" name="action" value="new">
                                    <label for="categoryId" class="col-sm-2 control-label">Category ID</label>
                                    <div class="col-sm-10">
                                        <input type="number" class="form-control" name="catId" id="categoryId" placeholder="Insert ID">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="categoryName" class="col-sm-2 control-label">Category Name</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="catName" id="categoryName" placeholder="Insert name">
                                    </div>
                                </div>                               
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <button type="submit" class="btn btn-primary">Create</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <!-- End of form box -->
                    
                </div>
            </div>
        </div>
        <jsp:include page="../layouts/footer.jsp" />
    </body>
</html>
