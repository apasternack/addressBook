/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {            //JQuery function that runs when the DOM has been fully built in memory


    $('#create-submit').on('click', function (e) {                 //any object with a css selector that matches in ' ' make an array out of it

        e.preventDefault();

        var addressData = JSON.stringify({
            firstName: $('#first-name').val(),
            lastName: $('#last-name').val(),
            streetName: $('#street-name').val(),
            streetNumber: $('#street-number').val(),
            city: $('#city').val(),
            state: $('#state').val(),
            zip: $('#zip').val()

        });





        $.ajax({
            url: contextRoot + "/address/",
            type: "POST",
            data: addressData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");            //setting the accept header because we are sending a JSON file
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success:
                    function (data, status) {
                        console.log(data);
                        var tableRow = buildAddressRow(data);
                        $('#address-table').append($(tableRow));
                        $('#first-name').val(''); //clears user inputed values from our form
                        $('#last-name').val('');
                        $('#street-name').val('');
                        $('#street-number').val('');
                        $('#city').val('');
                        $('#state').val('');
                        $('#zip').val('');
                    },
            error: function (data, status) {

                var errors = data.responseJSON.errors;

                console.log(errors);

                $.each(errors, function (index, error) {

                    $('#add-address-validation-errors').append(error.fieldName + ": " + error.message + "<br />");


                });
            }



        });

//        alert("alert after ajax");

    });

    $('#showAddressModal').on('show.bs.modal', function (e) {

        var link = $(e.relatedTarget);
        var addressId = link.data('address-id');
        $.ajax({
            url: contextRoot + "/address/" + addressId,
            type: 'GET',
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            },
            success: function (data, status) {

                $('#address-first-name').text(data.firstName);
                $('#address-last-name').text(data.lastName);
                $('#address-street-name').text(data.streetName);
                $('#address-street-number').text(data.streetNumber);
                $('#address-city').text(data.city);
                $('#address-state').text(data.state);
                $('#address-zip').text(data.zip);
            },
            error: function (data, status) {

            }


        });
    });

    $('#editAddressModal').on('show.bs.modal', function (e) {

        var link = $(e.relatedTarget);
        var addressId = link.data('address-id');
        $.ajax({
            url: contextRoot + "/address/" + addressId,
            type: 'GET',
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            },
            success: function (data, status) {

                $('#edit-address-first-name').val(data.firstName);
                $('#edit-address-last-name').val(data.lastName);
                $('#edit-address-street-name').val(data.streetName);
                $('#edit-address-street-number').val(data.streetNumber);
                $('#edit-address-city').val(data.city);
                $('#edit-address-state').val(data.state);
                $('#edit-address-zip').val(data.zip);
                $('#edit-id').val(data.id);

            },
            error: function (data, status) {

            }


        });
    });

    $('#edit-address-button').on('click', function (e) {

        var addressData = JSON.stringify({
            id: $('#edit-id').val(),
            firstName: $('#edit-address-first-name').val(),
            lastName: $('#edit-address-last-name').val(),
            streetName: $('#edit-address-street-name').val(),
            streetNumber: $('#edit-address-street-number').val(),
            city: $('#edit-address-city').val(),
            state: $('#edit-address-state').val(),
            zip: $('#edit-address-zip').val()

        });
        $.ajax({
            url: contextRoot + "/address/",
            type: "PUT",
            data: addressData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json"); //setting the accept header because we are sending a JSON file
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {

                $('#editAddressModal').modal('hide');

                var tableRow = buildAddressRow(data);

                $('#address-row-' + data.id).replaceWith($(tableRow));

            },
            error: function (data, status) {
                var errors = data.responseJSON.errors;

                console.log(errors);

                $.each(errors, function (index, error) {

                    $('#add-address-edit-validation-errors').append(error.fieldName + ": " + error.message + "<br />");

                });
            }



        });
//        alert("alert after ajax");

    });


    $(document).on('click', '.delete-link', function (e) {     //we need to do this because '.delete-link' is NOT in the DOM....created via JS, SOOO here we bind to the document instead of '.delete-link' and then pass '.delete-link' as a parameter to .on which delegates, meaning, tells the DOM to communicate to '.delete-link' which is not part of the DOM

        e.preventDefault();

        var addressId = $(e.target).data('address-id');

        $.ajax({
            type: "DELETE",
            url: contextRoot + "/address/" + addressId,
            success: function (data, status) {
                console.log(addressId);
                $('#address-row-' + addressId).remove();
            },
            error: function (data, status) {

            }




        });

    });


    function buildAddressRow(data) {

        return "<tr id='address-row-" + data.id + "'>  \n\
                <td><a data-address-id='" + data.id + "' data-toggle='modal' data-target='#showAddressModal'>" + data.firstName + "</a></td>  \n\
                <td> " + data.lastName + "</td>    \n\
                <td> <a data-address-id='" + data.id + "' data-toggle='modal' data-target='#editAddressModal'>Edit</a>  </td>   \n\
                <td> <a data-address-id='" + data.id + "' class='delete-link'>Delete</a>  </td>   \n\
                </tr>  ";
    }

});
