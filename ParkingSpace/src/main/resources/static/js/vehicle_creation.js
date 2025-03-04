document.addEventListener("DOMContentLoaded", function (){
    const form = document.getElementById('entityForm');

    form.addEventListener('submit', function (event){
        event.preventDefault();

        const vehicleRegistrationNumber = document.getElementById('vehicleRegistrationNumber').value;
        const vehicleModel = document.getElementById('vehicleModel').value;
        const vehicleColor = document.getElementById('vehicleColor').value;
        const user_id = document.getElementById('userId').value;

        const vehicleData = {
            vehicleRegistrationNumber: vehicleRegistrationNumber,
            vehicleModel: vehicleModel,
            vehicleColor: vehicleColor,
            user: { id: user_id }
        };

        fetch('api/vehicles', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(vehicleData)
        })
            .then(response => {
                if (response.ok) {
                    alert('Данные ТС успешно внесены в базу.');
                    window.location.href = "/vehicles";
                } else {
                    response.text().then(message => {
                        alert('Ошибка при внесении данных о ТС в базу: ' + message);
                    });
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Ошибка при внесении данных о ТС в базу: ' + error);
            });
    });
});

