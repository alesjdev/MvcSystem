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
        List<Employee> empList = (List<Employee>)request.getAttribute("empList");
        String formType = (String) request.getAttribute("formType");
        Employee emp = null;
        if(formType.equals("update")){
            emp = (Employee)request.getAttribute("employee");
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
                            <div class="panel-title"><%= formType.substring(0, 1).toUpperCase() + formType.substring(1) %> employee</div>                           
                        </div>
                        <div class="panel-body">
                            
                            <form class="form-horizontal" role="form" action="EmployeeController" method="POST">
                                <!-- Employee ID -->
                                <div class="form-group">
                                    <input type="hidden" name="action" value="<%= formType %>">
                                    <label for="employeeId" class="col-sm-2 control-label">Employee ID</label>
                                    <div class="col-sm-10">
                                        <input type="number" class="form-control" name="empId" id="employeeId" 
                                            <% if(formType.equals("new")) { %>
                                                placeholder="Insert employee ID"
                                            <% } else if (formType.equals("update")) { %>
                                                value="<%= emp.getEmployeeId() %>" readonly
                                            <% } %>   
                                        >
                                    </div>
                                </div>
                                <!-- Employee First Name -->
                                <div class="form-group">
                                    <label for="employeeFirstName" class="col-sm-2 control-label">Employee first name</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="empFirstName" id="employeeFirstName"
                                            <% if(formType.equals("new")) { %>
                                                placeholder="Insert employee first name"
                                            <% } else if (formType.equals("update")) { %>
                                                value="<%= emp.getEmployeeFirstName() %>"
                                            <% } %>    
                                        >
                                    </div>
                                </div>
                                <!-- Employee Last Name -->
                                <div class="form-group">
                                    <label for="employeeLastName" class="col-sm-2 control-label">Employee last name</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="empLastName" id="employeeLastName"
                                            <% if(formType.equals("new")) { %>
                                                placeholder="Insert employee last name"
                                            <% } else if (formType.equals("update")) { %>
                                                value="<%= emp.getEmployeeLastName() %>"
                                            <% } %>    
                                        >
                                    </div>
                                </div>
                                <!-- Employee DOB -->
                                <div class="form-group">
                                    <label for="employeeDOB" class="col-sm-2 control-label">Employee date of birth</label>
                                    <div class="col-sm-10">
                                        <input type="date" class="form-control" name="empDob" id="employeeDOB" placeholder="Insert employee date of birth"
                                            <% if(formType.equals("new")) { %>
                                                value="<%= new SimpleDateFormat("yyyy-MM-dd") %>"
                                            <% } else if (formType.equals("update")) { %>
                                                value="<%= new SimpleDateFormat("yyyy-MM-dd").format(emp.getEmployeeDob()) %>"
                                            <% } %>    
                                        >
                                    </div>
                                </div>
                                <!-- Employee Reports To -->
                                <div class="form-group">
                                    <label for="employeeReportsTo" class="col-sm-2 control-label">Employee supervisor ID</label>
                                    <div class="col-sm-10">
                                        <select class="form-control" name="empReportsTo" id="employeeReportsTo">
                                            <% for (Employee empl : empList) { %>
                                            <option value="<%= empl.getEmployeeId() %>"
                                            <% if( formType.equals("update") && ((emp.getEmployeeReportsTo() == empl.getEmployeeId())) ){ %>
                                                selected
                                            <% } %>
                                                >
                                                <%= empl.toString() %>
                                            </option>
                                            <% } %>    
                                        </select>
                                    </div>
                                </div>
                                <!-- Employee Extension -->
                                <div class="form-group">
                                    <label for="employeeExtension" class="col-sm-2 control-label">Employee extension code</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="empExtension" id="employeeExtension"
                                            <% if(formType.equals("new")) { %>
                                                placeholder="Insert employee extension code"
                                            <% } else if (formType.equals("update")) { %>
                                                value="<%= emp.getEmployeeExtension() %>"
                                            <% } %>    
                                        >
                                    </div>
                                </div>
                                                                       
                                <!-- Submit button -->
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <button type="submit" class="btn btn-primary">
                                            <%= formType.substring(0, 1).toUpperCase() + formType.substring(1) %> employee
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
        <jsp:include page="../layouts/footer.jsp" />
    </body>
</html>
