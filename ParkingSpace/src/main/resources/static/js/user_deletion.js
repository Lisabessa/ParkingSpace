function deleteEntity(button) {
    const id = button.getAttribute('idEntity');

    if (confirm('Вы уверены, что хотите удалить этого пользователя?')) {
        fetch('/api/users/' + id, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    alert('Пользователь успешно удален.');
                    window.location.reload();
                } else {
                    response.text().then(message => {
                        alert('Ошибка при удалении пользователя: ' + message);
                    });
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Ошибка при удалении пользователя: ' + error);
            });
    }
}
