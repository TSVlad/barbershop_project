let service = {
    id: null,
    name: null,
    price: null,
    isActive: true
};

function deleteService(id) {
    $.ajax({
        url: 'http://localhost:8080/admin/service/delete/' + id,
        type: 'DELETE',
        success: function (response) {
            $('#tr_' + id).remove();
        }
    });
}

function onEditClick(id, name, price) {
    $('#serviceIdInput').val(id);
    $('#serviceNameInput').val(name);
    $('#servicePriceInput').val(price);
    $('#serviceModalWindow').modal('show');
}

function onAddClick() {
    $('#serviceIdInput').val(null);
    $('#serviceNameInput').val(null);
    $('#servicePriceInput').val(null);
    $('#serviceModalWindow').modal('show');
}

function onSaveClick() {
    let idVal = $('#serviceIdInput').val();
    let nameVal = $('#serviceNameInput').val();
    let priceVal = $('#servicePriceInput').val();
    if (nameVal !== "" && priceVal !=="") {
        service.id = idVal === "" ? 0 : parseInt(idVal);
        service.name = nameVal;
        service.price = parseInt(priceVal);

        $.ajax({
            url: 'http://localhost:8080/admin/service/save',
            type: 'POST',
            data: JSON.stringify(service),
            contentType : "application/json",
            dataType: 'json',
            success: function (response) {
                if($('#tr_' + response.id).length) {
                    $('#tr_' + response.id + ' .td_name').text(response.name);
                    $('#tr_' + response.id + ' .td_price').text(response.price);
                    $('#editButton_' + response.id).attr('onclick', 'onEditClick(' + response.id + ', \'' + response.name + '\', ' + response.price + ')');
                } else {
                    $('#serviceTbody').html(
                        $('#serviceTbody').html() +
                        '<tr id="tr_' + response.id + '">\n' +
                        '                            <td class="td_name">' + response.name + '</td>\n' +
                        '                            <td class="td_price">' + response.price + '</td>\n' +
                        '                            <td>\n' +
                        '                                <button id="editButton_' + response.id + '" type="button" class="btn btn-primary" onclick="onEditClick(' + response.id + ', \'' + response.name + '\', ' + response.price + ')">\n' +
                        '                                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">\n' +
                        '                                        <path fill-rule="evenodd" d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"></path>\n' +
                        '                                    </svg>\n' +
                        '                                </button>\n' +
                        '\n' +
                        '                                <button type="button" class="btn btn-danger" onclick="deleteService(' + response.id + ')">\n' +
                        '                                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-trash-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">\n' +
                        '                                        <path fill-rule="evenodd" d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5a.5.5 0 0 0-1 0v7a.5.5 0 0 0 1 0v-7z"></path>\n' +
                        '                                    </svg>\n' +
                        '                                </button>\n' +
                        '                            </td>\n' +
                        '                        </tr>'
                    )
                }
                $('#serviceModalWindow').modal('hide');
            }
        });
    }
}