fetch('/api/stats/parkingSlots')
    .then(response => response.json())
    .then(data => {
        const ctx = document.getElementById('chart_parkingSlots').getContext('2d');
        const chart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: ['Свободные', 'Занятые'],
            datasets: [{
                label: 'Статистика занятости слотов на текущий момент',
                data: [data.available, data.occupied],
                backgroundColor: [
                    'rgba(0, 255, 0, 0.2)',
                    'rgba(255, 0, 0, 0.2)'
                ],
                borderColor: [
                    'rgba(0, 255, 0, 1)',
                    'rgba(255, 0, 0, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            responsive: false,
            width: 300,
            height: 300,
            plugins: {
                legend: {
                    display: true,
                    position: 'bottom'
                }
            }
        }
    });
});
