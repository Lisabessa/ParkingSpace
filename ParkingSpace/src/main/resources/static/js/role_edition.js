document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById('entityForm');

    form.addEventListener('submit', function(event) {
        event.preventDefault();

        const id = document.getElementById('idRole').value;
        const role = document.getElementById('role').value;
        const code = document.getElementById('code').value;

        const roleData = {
            id: id,
            role: role,
            code: code
        };

        fetch('/api/roles/' + id, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(roleData)
        })
            .then(response => {
                if (response.ok) {
                    alert('Данные роли успешно обновлены.');
                    window.location.href = "/roles";
                } else {
                    response.text().then(message => {
                        alert('Ошибка при изменении данных роли: ' + message);
                    });
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Ошибка при изменении данных роли: ' + error);
            });
    });
});
