var colors = ['#f44336', '#42a5f5', '#b2ebf2', '#66bb6a', '#ffca28',
		'#795548', '#9e9e9e', '#78909c', '#ffff00', '#76ff03', '#cddc39', '#7c4dff', '#ff4081']

function initCharts() {
	
	var ctx = document.getElementById('countries-chart')
	ctx = ctx.getContext('2d')
	var myChart = new Chart(ctx, stats.countries)
	
	var ctx = document.getElementById('brands-chart')
	ctx = ctx.getContext('2d')
	var myChart = new Chart(ctx, stats.brands)
	
	var ctx = document.getElementById('body-types-chart')
	ctx = ctx.getContext('2d')
	var myChart = new Chart(ctx, stats.bodyType)
	
	var ctx = document.getElementById('ordered-body-types-chart')
	ctx = ctx.getContext('2d')
	stats.orderedBodyType.data.datasets[0].backgroundColor = colors
	var myChart = new Chart(ctx, stats.orderedBodyType)
	
	var ctx = document.getElementById('ordered-brand-chart')
	ctx = ctx.getContext('2d')
	stats.orderedBrands.data.datasets[0].backgroundColor = colors
	var myChart = new Chart(ctx, stats.orderedBrands)
	
	var ctx = document.getElementById('ordered-countries-chart')
	ctx = ctx.getContext('2d')
	stats.orderedCountries.data.datasets[0].backgroundColor = colors
	var myChart = new Chart(ctx, stats.orderedCountries)

}