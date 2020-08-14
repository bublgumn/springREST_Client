$('#myModalEdit').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget)
    var getId = button.data('editid')
    var getFirstName = button.data('editfirstname')
    var getLastName = button.data('editlastname')
    var getAge = button.data('editage')
    var getEmail = button.data('editemail')

    var modal = $(this)
    modal.find('.editinputid input').val(getId)
    modal.find('.editinputfirstname input').val(getFirstName)
    modal.find('.editinputlastname input').val(getLastName)
    modal.find('.editinputage input').val(getAge)
    modal.find('.editinputemail input').val(getEmail)
});

$('#myModalDelete').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget)
    var getId = button.data('deleteid')
    var getFirstName = button.data('deletefirstname')
    var getLastName = button.data('deletelastname')
    var getAge = button.data('deleteage')
    var getEmail = button.data('deleteemail')
    let getRole = button.data('deleterole')

    var modal = $(this)
    modal.find('.deleteinputid input').val(getId)
    modal.find('.deleteinputfirstname input').val(getFirstName)
    modal.find('.deleteinputlastname input').val(getLastName)
    modal.find('.deleteinputage input').val(getAge)
    modal.find('.deleteinputemail input').val(getEmail)
    if (getRole == 2) {
        modal.find('.deleteinputrole option.role0').text('ADMIN')
        modal.find('.deleteinputrole option.role1').text('USER')
    } else {
        modal.find('.deleteinputrole option.role0').text('USER')
        modal.find('.deleteinputrole option.role1').text('')
    }
});

$(function () {
    $('#formAddNewUser').submit(function (addUser) {
        addUser.preventDefault();
        let user = {};
        let role;
        $(this).serializeArray().map(function (element) {
            if (element.name === 'role') {
                role = element.value;
                return;
            }
            user[element.name] = element.value;
        });
        $.ajax(
            {
                contentType: 'application/json',
                url: `/admin/addNewUser/${role}`,
                type: 'POST',
                data: JSON.stringify(user),
                success: function (userNew) {
                    let valueRoles = userNew.role.length > 1;
                    $('#formAddNewUser')[0].reset();
                    $('#allUsers').append(`
                    <tr id="${userNew.id}">
                        <td>${userNew.id}</td>
                        <td>${userNew.firstname}</td>
                        <td>${userNew.lastname}</td>'
                        <td>${userNew.age}</td>
                        <td>${userNew.email}</td>
                        <td>${valueRoles ? userNew.role[0].name.slice(5) + " "
                        + userNew.role[1].name.slice(5) : userNew.role[0].name.slice(5)}
                        <td>
                            <button class="btn btn-info" data-toggle="modal" data-target="#myModalEdit" 
                            data-editid=${userNew.id} data-editfirstname=${userNew.firstname} 
                            data-editlastname=${userNew.lastname} data-editage=${userNew.age}
                            data-editemail=${userNew.email}>Edit                            
                            </button>
                        </td>
                        <td>
                            <button class="btn btn-danger" data-toggle="modal" 
                            data-target="#myModalDelete" data-deleteid=${userNew.id}
                            data-deletefirstname=${userNew.firstname} data-deletelastname=${userNew.lastname}
                            data-deleteage=${userNew.age} data-deleteemail=${userNew.email}
                            data-deleterole=${userNew.role.length}>Delete
                            </button>
                        </td>
                    </tr>`);
                    $('[href="#AdminTable"]').tab('show');
                },
                error: function (error) {
                    alert('Error add User!');
                }
            });
    });
});

$(function () {
    $('#myModalDelete').submit(function (deleteUser) {
        deleteUser.preventDefault();
        let idUser;
        $('#formForDeleteUser').serializeArray().map(function (element) {
            if (element.name === 'id') {
                idUser = element.value;
            }
        });
        $.ajax(
            {
                url: `/admin/delete/${idUser}`,
                type: 'DELETE',
                success: function () {
                    $(`#` + idUser).remove();
                    $(".modal").modal("hide");
                },
                error: function (error) {
                    alert('Error delete User!');
                }
            });
    });
});

$(function () {
    $('#myModalEdit').submit(function (editUser) {
        editUser.preventDefault();
        let user = {};
        let role;
        $('#formForEditUserNow').serializeArray().map(function (element) {
            if (element.name === 'role') {
                role = element.value;
                return;
            }
            user[element.name] = element.value;
        });
        console.log(user);
        $.ajax(
            {
                contentType: 'application/json',
                url: `admin/editUser/${role}`,
                type: 'PUT',
                data: JSON.stringify(user),
                success: function () {
                    $('#formForEditUserNow')[0].reset();
                    $(`#${user.id}`).html(`
                        <td>${user.id}</td>
                        <td>${user.firstname}</td>
                        <td>${user.lastname}</td>'
                        <td>${user.age}</td>
                        <td>${user.email}</td>
                        <td>${role == "1" ? "admin user" : "user"}</td>
                        <td>
                            <button class="btn btn-info" data-toggle="modal" data-target="#myModalEdit" 
                            data-editid=${user.id} data-editfirstname=${user.firstname} 
                            data-editlastname=${user.lastname} data-editage=${user.age}
                            data-editemail=${user.email}>Edit                            
                            </button>
                        </td>
                        <td>
                            <button class="btn btn-danger" data-toggle="modal" 
                            data-target="#myModalDelete" data-deleteid=${user.id}
                            data-deletefirstname=${user.firstname} data-deletelastname=${user.lastname}
                            data-deleteage=${user.age} data-deleteemail=${user.email}
                            data-deleterole=${role === 1 ? "2" : "1"}>Delete
                            </button>
                        </td>
                        `);
                    $(".modal").modal("hide");
                },
            });
    });
});
