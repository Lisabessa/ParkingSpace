document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById('entityForm');

    form.addEventListener('submit', function(event) {
        event.preventDefault();

        const id = document.getElementById('idParkingSlot').value;
        const slotCode = document.getElementById('slotCode').value;
        const isAvailable = document.getElementById('isAvailable').value;

        const parkingSlotData = {
            id: id,
            slotCode: slotCode,
            isAvailable: isAvailable
        };

        fetch('/api/parkingSlots/' + id, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(parkingSlotData)
        })
            .then(response => {
                if (response.ok) {
                    alert('Данные о парковочном слоте успешно обновлены.');
                    window.location.href = "/parkingSlots";
                } else {
                    response.text().then(message => {
                        alert('Ошибка при изменении данных парковочного слота: ' + message);
                    });
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Ошибка при изменении данных парковочного слота: ' + error);
            });
    });
});
