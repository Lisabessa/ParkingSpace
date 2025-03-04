function deleteEntity(button) {
    const id = button.getAttribute('idEntity');

    if (confirm('Вы уверены, что хотите удалить это ТС?')) {
        fetch('/api/vehicles/' + id, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    alert('ТС успешно удалено.');
                    window.location.reload();
                } else {
                    response.text().then(message => {
                        alert('Ошибка при удалении ТС: ' + message);
                    });
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Ошибка при удалении ТС: ' + error);
            });
    }
}
