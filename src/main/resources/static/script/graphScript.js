// code to make the pie chart to show different expenses
const pieChartElement = document.getElementById('pie-chart').getContext('2d');
const pieChart = new Chart(pieChartElement, {
    type: 'pie',
    data: {
        // category names
        labels: ['Car', 'Insurance', 'Rent', 'Groceries', 'Utilities', 'Entertainment', 'Other'],
        datasets: [{
            // amount in each category
            label: 'Expenses',
            data: [100, 200, 300, 400, 500, 600, 700],
            // the different colors of each slice
            backgroundColor: [
                'rgb(103,232,103)',
                'rgb(96,201,35)',
                'rgb(0, 155, 0)',
                'rgb(0, 100, 0)',
                'rgb(63,143,89)',
                'rgb(59,146,166)',
                'rgb(0, 255, 192)'
            ],
            // provides a pop-up when hovered over
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


// code to make the bar graph to show how much money can go into a budget
const barChartElement = document.getElementById('bar-chart').getContext('2d');
const barChart = new Chart(barChartElement, {
    type: 'bar',
    data: {
        // y-axis labels of different categories
        labels: ['House', 'Food', 'Bills', 'Transportation', 'Miscellaneous'],
        datasets: [{
            // x-axis labels of how much is in each category
            label: 'Budget',
            data: [1400, 150, 475, 100, 50],
            // the color for each category
            backgroundColor: [
                'rgb(0, 100, 0)',
                'rgb(0, 100, 0)',
                'rgb(0, 100, 0)',
                'rgb(0, 100, 0)',
                'rgb(0, 100, 0)'
            ],
            // provides a pop-up when hovered over
            hoverOffset: 4
        }]
    },
    options: {
        title: {
            display: true,
            text: 'Budget For The Month'
        },
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
            legend: {
                position: 'top'
            }
        }
    }
});


// code to make the line graph to show how savings can increase as experience increases
const lineChartElement = document.getElementById('line-chart').getContext('2d');
const lineChart = new Chart(lineChartElement, {
    type: 'line',
    data: {
        // y-axis labels of each month
        labels: ['MAR', 'APR', 'MAY', 'JUN', 'JUL', 'AUG', 'SEP', 'OCT', 'NOV', 'DEC', 'JAN', 'FEB'],
        datasets: [{
            // x-axis labels of how much was saved in each month
            label: 'Savings',
            data: [5, 27, 20, 30, 26, 58, 29, 42, 60, 94, 102, 115],
            backgroundColor: [
                // color of each month (darker green is for when
                // there was a decrease in savings from the
                // previous month)
                'rgb(0, 255, 192)',
                'rgb(0, 255, 192)',
                'rgb(0, 100, 0)',
                'rgb(0, 255, 192)',
                'rgb(0, 100, 0)',
                'rgb(0, 255, 192)',
                'rgb(0, 100, 0)',
                'rgb(0, 255, 192)',
                'rgb(0, 255, 192)',
                'rgb(0, 255, 192)',
                'rgb(0, 255, 192)',
                'rgb(0, 255, 192)',
            ],
            // provides a pop-up when hovered over
            hoverOffset: 4
        }]
    },
    options: {
        title: {
            display: true,
            text: 'History of Saving'
        },
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
            legend: {
                position: 'top'
            }
        }
    }
});