function getOrders() {
    const date = $('#date-input').val();
    const username = $('#username-input').val();

    $.ajax({
        url: `http://localhost:8080/barber/archive-orders/for-user-and-date/${username}/${date}`,
        type: 'GET',
        success: function (response) {
            const tableElement = document.getElementById("order-table-body");
            tableElement.innerHTML = '';
            for (let order of response) {
                tableElement.innerHTML = tableElement.innerHTML + `<tr>
                    <td>${order.date}</td>
                    <td>${order.hour}:00</td>
                    <td>${order.service.name}</td>
                    <td>${order.client.email}</td>
                    <td>${order.status}</td>
                </tr>`;
            }
        }
    });
}