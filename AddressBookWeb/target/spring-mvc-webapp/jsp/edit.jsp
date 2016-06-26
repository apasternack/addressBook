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

    </head>
    <body>
        <div class="container">
            <h1>Address Book</h1>
            <hr/>


            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}">Home</a></li>
                   <li role="presentation"><a href="${pageContext.request.contextPath}/address/search"><span class="glyphicon glyphicon-search"></span> Search</a></li>
                </ul>    
            </div> 

            <div class="row">

                <div class="col-md-6">

                    <form method="POST" action="./" class="form-horizontal">
                        <input type="hidden" name="id" value="${address.id}" />
                        <div class="form-group">
                            <label for="first-name" class="col-md-4 control-lable">First:</label>
                            <div class="col-md-8">
                                <input name="firstName" id="first-name" value="${address.firstName}" />
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="last-name" class="col-md-4 control-lable">Last:</label>
                            <div class="col-md-8">
                                <input name="lastName" id="last-name" value="${address.lastName}" />
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="street-name" class="col-md-4 control-lable">Street Name:</label>
                            <div class="col-md-8">
                                <input name="streetName" id="street-name" value="${address.streetName}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="street-number" class="col-md-4 control-lable">Street Number:</label>
                            <div class="col-md-8">
                                <input name="streetNumber" id="street-number" value="${address.streetNumber}" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="city" class="col-md-4 control-lable">City:</label>
                            <div class="col-md-8">
                                <input name="city" id="city" value="${address.city}" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="state" class="col-md-4 control-lable">State:</label>
                            <div class="col-md-8">
                                <input name="state" id="state"  value="${address.state}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="zip" class="col-md-4 control-lable">Zip:</label>
                            <div class="col-md-8">
                                <input name="zip" id="zip"  value="${address.zip}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="col-md-4 control-label"></label>
                            <div class="col-md-8">
                                <input type="submit" class="btn pull-right btn-success"/>
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

