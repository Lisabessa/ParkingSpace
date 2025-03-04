document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById('entityForm');

    form.addEventListener('submit', function(event) {
        event.preventDefault();

        const startTime = document.getElementById('startTime').value;
        const endTime = document.getElementById('endTime').value;
        const vehicle_id = document.getElementById('vehicleId').value;
        const parking_slot_id = document.getElementById('parkingSlotId').value;
        const status = document.getElementById('status').value;
        const price = document.getElementById('price').value;

        const reservationData = {
            startTime: startTime,
            endTime: endTime,
            vehicle: { id: vehicle_id },
            parkingSlot: { id: parking_slot_id },
            status: status,
            price: price
        };

        fetch('/api/reservations', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(reservationData)
        })
            .then(response => {
                if (response.ok) {
                    alert('Бронирование успешно создано.');
                    window.location.href = "/reservations";
                } else {
                    response.text().then(message => {
                        alert('Ошибка при создании бронирования: ' + message);
                    });
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Ошибка при создании бронирования: ' + error);
            });
    });
});
