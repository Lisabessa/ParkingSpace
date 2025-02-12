document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById('entityForm');

    form.addEventListener('submit', function(event) {
        event.preventDefault();

        const id = document.getElementById('idUser').value;
        const vehicleRegistrationNumber = document.getElementById('vehicleRegistrationNumber').value;
        const vehicleModel = document.getElementById('vehicleModel').value;
        const vehicleColor = document.getElementById('vehicleColor').value;
        const firstName = document.getElementById('firstName').value;
        const lastName = document.getElementById('lastName').value;
        const phoneNumber = document.getElementById('phoneNumber').value;

        const userData = {
            id: id,
            vehicleRegistrationNumber: vehicleRegistrationNumber,
            vehicleModel: vehicleModel,
            vehicleColor: vehicleColor,
            firstName: firstName,
            lastName: lastName,
            phoneNumber: phoneNumber
        };

        fetch('/api/users/' + id, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        })
            .then(response => {
                if (response.ok) {
                    alert('Данные пользователя успешно обновлены.');
                    window.location.href = "/users";
                } else {
                    response.text().then(message => {
                        alert('Ошибка при изменении данных пользователя: ' + message);
                    });
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Ошибка при изменении данных пользователя: ' + error);
            });
    });
});
