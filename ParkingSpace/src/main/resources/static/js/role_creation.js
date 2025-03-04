document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById('entityForm');

    form.addEventListener('submit', function(event) {
        event.preventDefault();

        const role = document.getElementById('role').value;
        const code = document.getElementById('code').value;

        const roleData = {
            role: role,
            code: code
        };

        fetch('/api/roles', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(roleData)
        })
            .then(response => {
                if (response.ok) {
                    alert('Роль успешно создана.');
                    window.location.href = "/roles";
                } else {
                    response.text().then(message => {
                        alert('Ошибка при создании роли: ' + message);
                    });
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Ошибка при создании роли: ' + error);
            });
    });
});
