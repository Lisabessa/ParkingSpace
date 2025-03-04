document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById('entityForm');

    form.addEventListener('submit', function(event) {
        event.preventDefault();

        const id = document.getElementById('idVehicle').value;
        const vehicleRegistrationNumber = document.getElementById('vehicleRegistrationNumber').value;
        const vehicleModel = document.getElementById('vehicleModel').value;
        const vehicleColor = document.getElementById('vehicleColor').value;
        const user_id = document.getElementById('userId').value;

        const userData = {
            id: id,
            vehicleRegistrationNumber: vehicleRegistrationNumber,
            vehicleModel: vehicleModel,
            vehicleColor: vehicleColor,
            user: { id: user_id }
        };

        fetch('/api/vehicles/' + id, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        })
            .then(response => {
                if (response.ok) {
                    alert('Данные ТС успешно обновлены.');
                    window.location.href = "/vehicles";
                } else {
                    response.text().then(message => {
                        alert('Ошибка при изменении данных ТС: ' + message);
                    });
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Ошибка при изменении данных ТС: ' + error);
            });
    });
});
