<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Hello Controller Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

        <style>
            th {
                background-color: #449D44;
                color: #E7FFE1;
            }

        </style>

    </head>
    <body>
        <div class="container">
            <h1>Address Book</h1>
            <hr/>


            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}">Home</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/address/search"><span class="glyphicon glyphicon-search"></span> Search</a></li>
                </ul>    
            </div>

            <div class="row">

                <div class="col-md-6">

                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <th>First</th>
                                <th>Last</th>
                                <th>Edit</th>
                                <th><i class="glyphicon glyphicon-remove"></i> Delete</th>
                            </tr>
                        </thead>
                        <c:forEach items="${searchResults}" var="address">
                            <tr>
                                <td><a href="show/${address.id}">${address.firstName}</a></td>
                                <td>${address.lastName}</td>
                                <td><a href="edit/${address.id}">Edit</a></td>
                                <td><a href="delete/${address.id}">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>   

                <div class="col-md-6">

                    <form method="POST" action="search" class="form-horizontal">


                        <div class="row">

                            <div class="col-md-6">
                                <div class="input-group">
                                    <span class="input-group-addon">Search by:</span>
                                    <select name="searchBy" class="form-control">
                                        <option value="lastName">Last Name</option>
                                        <option value="state">State</option>
                                        <option value="city">City</option>
                                        <option value="zipCode">Zip Code</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class = "input-group">
                                    <div class="input-group">
                                        <input name="search" type="text" class="form-control" placeholder="Search for...">
                                        <span class="input-group-btn">
                                            <button class="btn btn-secondary" type="submit"><span class="glyphicon glyphicon-search"></span></button>
                                        </span>
                                    </div>
                                </div>
                            </div>

                        </div>

                    </form>

                </div>

            </div>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

