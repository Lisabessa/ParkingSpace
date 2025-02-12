document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById('entityForm');

    form.addEventListener('submit', function(event) {
        event.preventDefault();

        const vehicleRegistrationNumber = document.getElementById('vehicleRegistrationNumber').value;
        const vehicleModel = document.getElementById('vehicleModel').value;
        const vehicleColor = document.getElementById('vehicleColor').value;
        const firstName = document.getElementById('firstName').value;
        const lastName = document.getElementById('lastName').value;
        const phoneNumber = document.getElementById('phoneNumber').value;

        const userData = {
            vehicleRegistrationNumber: vehicleRegistrationNumber,
            vehicleModel: vehicleModel,
            vehicleColor: vehicleColor,
            firstName: firstName,
            lastName: lastName,
            phoneNumber: phoneNumber
        };

        fetch('/api/users', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        })
            .then(response => {
                if (response.ok) {
                    alert('Пользователь успешно создан!');
                    window.location.href = "/users";
                } else {
                    response.text().then(message => {
                        alert('Ошибка при создании пользователя: ' + message);
                    });
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Ошибка при создании пользователя: ' + error);
            });
    });
});
