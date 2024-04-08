const pieChartElement = document.getElementById('pie-chart').getContext('2d');
const pieChart = new Chart(pieChartElement, {
    type: 'pie',
    data: {
        labels: ['Car', 'Insurance', 'Rent', 'Groceries', 'Utilities', 'Entertainment', 'Other'],
        datasets: [{
            label: 'Expenses',
            data: [100, 200, 300, 400, 500, 600, 700],
            backgroundColor: [
                'rgb(103,232,103)',
                'rgb(96,201,35)',
                'rgb(0, 155, 0)',
                'rgb(0, 100, 0)',
                'rgb(63,143,89)',
                'rgb(59,146,166)',
                'rgb(0, 255, 192)'
            ],
            hoverOffset: 4
        }]
    },
    options: {
        title: {
            display: true,
            text: 'Expenses'
        },
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
            legend: {
                position: 'bottom'
            }
        }
    }
});
