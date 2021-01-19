function acceptOrder(orderKey, button) {
    $.ajax({
        url: 'http://localhost:8080/barber/accept-order',
        type: 'POST',
        data: JSON.stringify(orderKey),
        contentType : "application/json",
        dataType: 'json',
        success: function (response) {
            console.log('success');
            button.parentNode.parentNode.remove();
        },
        error: function () {
            //toast
            console.log('error')
        }
    });
}