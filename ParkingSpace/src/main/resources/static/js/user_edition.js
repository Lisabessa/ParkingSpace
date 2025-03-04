document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById('entityForm');

    form.addEventListener('submit', function(event) {
        event.preventDefault();

        const id = document.getElementById('idUser').value;
        const firstName = document.getElementById('firstName').value;
        const lastName = document.getElementById('lastName').value;
        const phoneNumber = document.getElementById('phoneNumber').value;
        const role_id = document.getElementById('roleId').value;
        const login = document.getElementById('login').value;
        const password = document.getElementById('password').value;

        const userData = {
            id: id,
            firstName: firstName,
            lastName: lastName,
            phoneNumber: phoneNumber,
            role: { id: role_id },
            login: login,
            password: password
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
