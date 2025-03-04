function deleteEntity(button) {
    const id = button.getAttribute('idEntity');

    if (confirm('Вы уверены, что хотите удалить эту роль?')) {
        fetch('/api/roles/' + id, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    alert('Роль успешно удалена.');
                    window.location.reload();
                } else {
                    response.text().then(message => {
                        alert('Ошибка при удалении роли: ' + message);
                    });
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Ошибка при удалении роли: ' + error);
            });
    }
}
