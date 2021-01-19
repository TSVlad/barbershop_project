function openEditModal(barber) {
    $('#login-input').val(barber.userInfo.username);
    $('#name-input').val(barber.name);
    $('#password-input').val('');
    $('#id-input').val(barber.id);
    $('#user-id-input').val(barber.userInfo.id);
    $("#workersModal").modal('show');
}

function openAddModal() {
    $('#login-input').val('');
    $('#name-input').val('');
    $('#password-input').val('');
    $('#id-input').val('0');
    $('#user-id-input').val('0');
    $('#workersModal').modal('show');
}

function closeModal() {
    $('#workersModal').modal('hide');
}

function deleteBarber(barberId) {
    $.ajax({
        url: 'http://localhost:8080/admin/active-workers/' + barberId,
        type: 'DELETE',
        success: function (response) {
            console.log(response);
            $('#tr_' + barberId).remove();
        }
    });
}

function saveBarber() {
    const name = $('#name-input').val();
    const username = $('#login-input').val();
    const password = $('#password-input').val();
    const id = $('#id-input').val();
    const user_id = $('#user-id-input').val();

    const isNew = id === '0';

    const barber = {
        id: id,
        name: name,
        userInfo: {
            id: user_id,
            username: username,
            password: password,
            isActive: true,
            roles: []
        }
    };

    if (name !== '' && username !== '' && password !== '') {
        $.ajax({
            url: 'http://localhost:8080/admin/active-workers/save',
            type: 'POST',
            data: JSON.stringify(barber),
            contentType : "application/json",
            dataType: 'json',
            success: function (response) {
                if (!isNew) {
                    $(`#tr_${response.id} .name-val`).html(response.name);
                    $(`#tr_${response.id} .username-val`).html(response.userInfo.username);
                    $(`#tr_${response.id} .buttons .editButton`).attr('onclick', `openEditModal(${JSON.stringify(response)})`);
                } else {
                    $('#table-body').html($('#table-body').html() + `
                        <tr id="tr_${response.id}">
                            <td class="name-val">${response.name}</td>
                            <td class="username-val">${response.userInfo.username}</td>
                            <td class="buttons">
                                <button type="button" class="btn btn-primary editButton">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-fill" viewBox="0 0 16 16">
                                        <path d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"/>
                                    </svg>
                                </button>
    
                                <button type="button" class="btn btn-danger" onclick="deleteBarber(${response.id})">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-square-fill" viewBox="0 0 16 16">
                                        <path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm3.354 4.646L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 1 1 .708-.708z"/>
                                    </svg>
                                </button>
                            </td>
                    </tr>
                    `);

                    $(`#tr_${response.id} .buttons .editButton`).attr('onclick', `openEditModal(${JSON.stringify(response)})`);
                }
                closeModal();
            }
        });
    }
}