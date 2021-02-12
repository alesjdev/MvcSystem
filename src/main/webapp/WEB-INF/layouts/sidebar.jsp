<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="col-md-2">
    <div class="sidebar content-box" style="display: block;">
        <ul class="nav">
            <!-- Main menu -->
            <li><a href="<%= request.getContextPath() %>"><i class="glyphicon glyphicon-home"></i> Home</a></li>
        </ul>
    </div>
    <div class="sidebar content-box" style="display: block;">
        <ul class="nav">
            <li class="text-center" style="color: #0088cc;"><h5><b>Administration menu</b></h5></li>
            <li><a href="<%= request.getContextPath() %>/CategoryController"><i class="glyphicon glyphicon-tags"></i> Categories</a></li>
            <li><a href="<%= request.getContextPath() %>/EmployeeController"><i class="glyphicon glyphicon-user"></i> Employees</a></li>
            <li><a href="<%= request.getContextPath() %>/SupplierController"><i class="glyphicon glyphicon-briefcase"></i> Suppliers</a></li>
            <li><a href="<%= request.getContextPath() %>/ClientController"><i class="glyphicon glyphicon-user"></i> Clients</a></li>
            <li><a href="<%= request.getContextPath() %>/ProductController"><i class="glyphicon glyphicon-list-alt"></i> Products</a></li>
        </ul>
    </div>
    <div class="sidebar content-box" style="display: block;">
        <ul class="nav">
            <!-- POS & Orders Menu -->
            <li class="text-center" style="color: #0088cc;"><h5><b>Sales menu</b></h5></li>
            <li><a href="<%= request.getContextPath() %>/PosController"><i class="glyphicon glyphicon-shopping-cart"></i> POS</a></li>
                        <li><a href="<%= request.getContextPath() %>/OrderController"><i class="glyphicon glyphicon-file"></i> Orders</a></li>
        </ul>
    </div>
    <div class="sidebar content-box" style="display: block;">    
        <ul class="nav">    
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