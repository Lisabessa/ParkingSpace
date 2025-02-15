function deleteEntity(button) {
    const id = button.getAttribute('idEntity');

    if (confirm('Вы уверены, что хотите удалить это бронирование?')) {
        fetch('/api/reservations/' + id, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    alert('Бронирование успешно удалено.');
                    window.location.reload();
                } else {
                    response.text().then(message => {
                        alert('Ошибка при удалении бронирования: ' + message);
                    });
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Ошибка при удалении бронирования: ' + error);
            });
    }
}
