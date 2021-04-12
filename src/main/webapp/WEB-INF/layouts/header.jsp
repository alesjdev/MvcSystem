<%@page import="com.alesjdev.mvcsystem.models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User userHeader = (User) request.getSession().getAttribute("user");
%>
<div class="header">
    <div class="container">
        <div class="row">
            <div class="col-md-5">
                <!-- Logo -->
                <div class="logo">
                    <h1><a href="<%= request.getContextPath() %>">MVC System</a></h1>
                </div>
            </div>
                
            <% 
                if(userHeader!=null){
                    if (userHeader.getAccountType()==0){ 
            %>      
            <div class="col-md-5">
                <div class="row">  
                    <div class="col-lg-12">
                        <form action="SearchController" method="POST">
                            <div class="input-group form">
                                <input type="text" name="searchCriteria" class="form-control" placeholder="Search..." required>
                                <span class="input-group-btn">
                                    <input type="submit" class="btn btn-primary" value="Search">
                                </span>
                            </div>
                        </form>
                    </div>            
                </div>
            </div>
            <% }} %>
            
        </div>
    </div>
</div>
