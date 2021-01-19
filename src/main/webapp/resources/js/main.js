function onDateChanged() {
    let date = $('#dateInput').val();
    $.ajax( {
        url: 'http://localhost:8080/main/get-barbers/for-day/' + date,
        type: 'GET',
        success: function (response) {
            if (response.length > 0) {
                let select = document.getElementById('barberInput');
                select.innerHTML = '';
                select.append(new Option("Выбрать мастера", "0"));
                for (let barber of response) {
                    select.append(new Option(barber.name, '' + barber.id));
                }

                select.removeAttribute('disabled');
                document.getElementById('timeInput').setAttribute('disabled', 'true');
            }
        }
    });
}

function onBarberChanged() {
    let selectedValue = $('#barberInput').val();
    if(selectedValue === '0') {
        document.getElementById('timeInput').setAttribute('disabled', 'true');
    } else {
        $.ajax({
            url: `http://localhost:8080/main/get-barber-hours/${selectedValue}/${$('#dateInput').val()}`,
            type: 'GET',
            success: function (response) {
                let select = document.getElementById('timeInput');
                select.innerHTML = '';
                select.append(new Option("Выбрать время", "-1"));
                for (let hour of response) {
                    select.append(new Option(`${hour}:00`, hour));
                }
            }
        });
        document.getElementById('timeInput').removeAttribute('disabled');
    }
}

function createOrder() {
    let serviceId = $('#serviceInput').val();
    let date = $('#dateInput').val();
    let barberId = $('#barberInput').val();
    let hour = $('#timeInput').val();
    let email = $('#emailInput').val();
    let comment = $('#commentInput').val();


    let curDate = new Date();
    let dateOnly = new Date(curDate.getFullYear(), curDate.getMonth(), curDate.getDate());
    let ddate = new Date(date);


    let jsonObj = {
        serviceId: serviceId,
        date: date,
        barberId: barberId,
        hour: hour,
        email: email,
        comment: comment
    };

    if (serviceId === '0' || date === '' || barberId === '0' || hour === '-1' || email === '') {
        //toast
        console.log('Empty field')
    }  else if(dateOnly > ddate || dateOnly === ddate && curDate.getHours() >= hour) {
        //toast
        console.log('Incorrect date')
    }else {
        $.ajax({
            url: 'http://localhost:8080/main/order/new',
            type: 'POST',
            data: JSON.stringify(jsonObj),
            contentType : "application/json",
            dataType: 'json',
            success: function (response) {
                $('#modal').modal('hide');
            },
            error: function () {
                //toast
                console.log('Bad response')
            }
        });
    }
}