<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="com.alesjdev.mvcsystem.models.Employee"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>MVC System - Employees</title>
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
        // Validate and obtain list of employees.
        List <Employee> employeeList = null;
        if(request.getAttribute("employeeList") != null){
            employeeList = (List <Employee>)request.getAttribute("employeeList");
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
                    
                    <!-- Table with employees -->
                    <div class="content-box-large">
                        <div class="panel-heading">
                            <div class="panel-title">Employee list</div>
                          
                        </div>
                        <div class="panel-body">
                            <table class="table">
                                <thead>
                                    <tr>                                       
                                        <th>ID</th>
                                        <th>First name</th>
                                        <th>Last name</th>
                                        <th>D.O.B.</th>
                                        <th>Extension</th>
                                        <th>Reports to</th>                                       
                                    </tr>
                                </thead>
                                
                                <tbody>
                                    <% for (Employee emp : employeeList) { %>
                                    <tr>
                                        <td>
                                            <%= emp.getEmployeeId() %>
                                        </td>
                                        <td>
                                            <%= emp.getEmployeeFirstName()%>
                                        </td>
                                        <td>
                                            <%= emp.getEmployeeLastName() %>
                                        </td>
                                        <td>
                                            <%= new SimpleDateFormat("dd-MM-yyyy").format(emp.getEmployeeDob()) %>
                                        </td>
                                        <td>
                                            <%= emp.getEmployeeExtension()%>
                                        </td>
                                        <td>
                                            <%= emp.getEmployeeBoss() %>
                                        </td>
                                      
                                        <!-- Edit & Delete buttons -->
                                        <td class="text-right">
                                            
                                            <a href="EmployeeController?action=update&empId=<%= emp.getEmployeeId() %>"
                                               class="btn btn-warning">
                                                <span class="glyphicon glyphicon-pencil"></span>
                                            </a>
                                            
                                               <form action="EmployeeController" method="POST" style="display:inline">
                                                <input type="hidden" name="action" value="delete">
                                                <input type="hidden" name="empId" value="<%= emp.getEmployeeId() %>">
                                                <button type="submit" class="btn btn-danger">
                                                    <span class="glyphicon glyphicon-trash"></span>
                                                </button>
                                            </form>                                                
                                        </td>
                                        
                                    </tr>
                                    <% } %>
                                </tbody>
                            </table>
                                <a href="EmployeeController?action=new" method="GET" class="btn btn-primary">
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