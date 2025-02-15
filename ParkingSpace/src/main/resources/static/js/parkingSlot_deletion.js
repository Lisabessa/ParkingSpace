function deleteEntity(button) {
    const id = button.getAttribute('idEntity');

    if (confirm('Вы уверены, что хотите удалить этот парковочный слот?')) {
        fetch('/api/parkingSlots/' + id, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    alert('Парковочный слот успешно удален.');
                    window.location.reload();
                } else {
                    response.text().then(message => {
                        alert('Ошибка при удалении парковочного слота: ' + message);
                    });
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Ошибка при удалении парковочного слота: ' + error);
            });
    }
}
