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
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/address/search"><span class="glyphicon glyphicon-search"></span> Search</a></li>
                </ul>    
            </div>

            <div class="row">

                <div class="col-md-6">

                    <table class="table table-bordered table-hover" id="address-table">
                        <thead>
                            <tr>
                                <th>First</th>
                                <th>Last</th>
                                <th><span class="glyphicon glyphicon-edit"></span> Edit</th>
                                <th><i class="glyphicon glyphicon-remove"></i> Delete</th>
                            </tr>
                        </thead>
                        <c:forEach items="${addresses}" var="address">
                            <tr id="address-row-${address.id}">
                                <td><a data-address-id="${address.id}" data-toggle="modal" data-target="#showAddressModal">${address.firstName}</a></td>
                                <td>${address.lastName}</td>
                                <td><a data-address-id="${address.id}" data-toggle="modal" data-target="#editAddressModal">Edit</a></td>
                                <td><a data-address-id="${address.id}" class="delete-link">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>   

                <div class="col-md-6">

                    <form method="POST" class="form-horizontal">

                        <div class="form-group">
                            <label for="first-name" class="col-md-4 control-lable">First:</label>
                            <div class="col-md-8">
                                <input type="text" id="first-name" />
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="last-name" class="col-md-4 control-lable">Last:</label>
                            <div class="col-md-8">
                                <input type="text" id="last-name" />
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="street-name" class="col-md-4 control-lable">Street Name:</label>
                            <div class="col-md-8">
                                <input type="text" id="street-name" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="street-number" class="col-md-4 control-lable">Street Number:</label>
                            <div class="col-md-8">
                                <input type="text" id="street-number" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="city" class="col-md-4 control-lable">City:</label>
                            <div class="col-md-8">
                                <input type="text" id="city" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="state" class="col-md-4 control-lable">State:</label>
                            <div class="col-md-8">
                                <input type="text" id="state" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="zip" class="col-md-4 control-lable">Zip:</label>
                            <div class="col-md-8">
                                <input type="text" id="zip" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="col-md-4 control-label"></label>
                            <div class="col-md-8">
                                <input type="submit" class="btn pull-right btn-success" id="create-submit"/>
                            </div>
                        </div>

                    </form>

                    <div id="add-address-validation-errors">
                    </div>

                </div>

            </div>

        </div>

        <div id="showAddressModal" class="modal fade" role="dialog">
          <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Contact Details</h4>
              </div>
              <div class="modal-body">
                        <table class="table table-bordered">
                            <tr>
                                <th>First Name</th>
                                <td id="address-first-name"></td>
                            </tr>

                            <tr>
                                <th>Last Name</th>
                                <td id="address-last-name"></td>
                            </tr>

                            <tr>
                                <th>Street Name</th>
                                <td id="address-street-name"></td>
                            </tr>

                            <tr>
                                <th>Street Number</th>
                                <td id="address-street-number"></td>
                            </tr>

                            <tr>
                                <th>City</th>
                                <td id="address-city"></td>
                            </tr>

                            <tr>
                                <th>State</th>
                                <td id="address-state"></td>
                            </tr>

                            <tr>
                                <th>Zip</th>
                                <td id="address-zip"></td>
                            </tr>
                        </table>
                    </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              </div>
            </div>

          </div>
        </div>

        <div id="editAddressModal" class="modal fade" role="dialog">
          <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Contact Details</h4>
              </div>
              <div class="modal-body">
                        <table class="table table-bordered">
                            <input type="hidden" id="edit-id" />

                            <tr>
                                <th>First Name</th>
                                <td>
                                    <input type="text" id="edit-address-first-name" />
                                </td>
                            </tr>

                            <tr>
                                <th>Last Name</th>
                                <td>
                                    <input type="text" id="edit-address-last-name" />
                                </td>
                            </tr>

                            <tr>
                                <th>Street Name</th>
                                <td>
                                    <input type="text" id="edit-address-street-name" />
                                </td>
                            </tr>

                            <tr>
                                <th>Street Number</th>
                                <td>
                                    <input type="text" id="edit-address-street-number" />
                                </td>                           
                            </tr>

                            <tr>
                                <th>City</th>
                                <td>
                                    <input type="text" id="edit-address-city" />
                                </td>                            
                            </tr>

                            <tr>
                                <th>State</th>
                                <td>
                                    <input type="text" id="edit-address-state" />
                                </td>
                            </tr>

                            <tr>
                                <th>Zip</th>
                                <td>
                                    <input type="text" id="edit-address-zip" />
                                </td>                           
                            </tr>
                        </table>
                    </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-danger" id="edit-address-button"  >Save</button>
              </div>
                    <div id="add-address-edit-validation-errors">
                    </div>
            </div>

          </div>
        </div>


        <script>
            var contextRoot = '${pageContext.request.contextPath}';
        </script>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/app.js"></script>

    </body>
</html>

