<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="col-md-2">
    <div class="sidebar content-box" style="display: block;">
        <ul class="nav">
            <!-- Main menu -->
            <li><a href="<%= request.getContextPath() %>"><i class="glyphicon glyphicon-home"></i> Home</a></li>
            <li><a href="<%= request.getContextPath() %>/CategoryController"><i class="glyphicon glyphicon-tags"></i> Categories</a></li>
            <li><a href="<%= request.getContextPath() %>/EmployeeController"><i class="glyphicon glyphicon-list-alt"></i> Employees</a></li>
            <li><a href="<%= request.getContextPath() %>/SupplierController"><i class="glyphicon glyphicon-briefcase"></i> Suppliers</a></li>
            <li><a href="<%= request.getContextPath() %>/ClientController"><i class="glyphicon glyphicon-user"></i> Clients</a></li>
            <li><a href="<%= request.getContextPath() %>/ProductController"><i class="glyphicon glyphicon-shopping-cart"></i> Products</a></li>
            <li><a href="forms.html"><i class="glyphicon glyphicon-tasks"></i> Forms</a></li>
            <li class="submenu">
                <a href="#">
                    <i class="glyphicon glyphicon-list"></i> Pages
                    <span class="caret pull-right"></span>
                </a>
                <!-- Sub menu -->
                <ul>
                    <li><a href="login.html">Login</a></li>
                    <li><a href="signup.html">Signup</a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>