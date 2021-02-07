<%@page import="com.alesjdev.mvcsystem.servlets.PropertiesLoader"%>
<%@page import="java.util.Properties"%>
<% Properties prop = PropertiesLoader.getProperties(); %>
<footer>
    <div class="container">

        <div class="copy text-center">
            Copyright 2021 <a href='https://<%= prop.getProperty("webadress") %>'><%= prop.getProperty("webadress") %></a>
        </div>
    </div>
</footer>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://code.jquery.com/jquery.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%= request.getContextPath() %>/bootstrap/js/bootstrap.min.js"></script>
<script src="<%= request.getContextPath() %>/js/custom.js"></script>