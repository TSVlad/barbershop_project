function activateBarber(id) {
    $.ajax({
        url: 'http://localhost:8080/admin/archived-workers/' + id,
        type: 'DELETE',
        success: function (response) {
            $('#tr_' + id).remove();
        }
    });
}