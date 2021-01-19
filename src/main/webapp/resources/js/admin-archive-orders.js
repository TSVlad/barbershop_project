function getOrders() {
    const date = $('#date-input').val();

    $.ajax({
        url: `http://localhost:8080/admin/archive-orders/for-date/${date}`,
        type: 'GET',
        success: function (response) {
            const tableElement = document.getElementById("order-table-body");
            tableElement.innerHTML = '';
            for (let order of response) {
                tableElement.innerHTML = tableElement.innerHTML + `<tr>
                    <td>${order.barber.name}</td>
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