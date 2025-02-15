document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById('entityForm');

    form.addEventListener('submit', function(event) {
        event.preventDefault();

        const slotCode = document.getElementById('slotCode').value;
        const isAvailable = document.getElementById('isAvailable').value;

        const parkingSlotData = {
            slotCode: slotCode,
            isAvailable: isAvailable
        };

        fetch('/api/parkingSlots', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(parkingSlotData)
        })
            .then(response => {
                if (response.ok) {
                    alert('Парковочный слот успешно создан.');
                    window.location.href = "/parkingSlots";
                } else {
                    response.text().then(message => {
                        alert('Ошибка при создании парковочного слота: ' + message);
                    });
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Ошибка при создании парковочного слота: ' + error);
            });
    });
});
