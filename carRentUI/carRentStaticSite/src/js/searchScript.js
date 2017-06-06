function searchVehicles(page, element) {
	var searchEntity = formSearchEntity()
	$('#main-viewer').animate({
		opacity : 0
	}, 500, requestSearchResult(searchEntity, element, page))
}

function viewSearchPage(page) {
	var searchEntity = formSearchEntity()
	var requestUrl = SERVER_URL + SEARCH_VEHICLE_URI + "/page/" + page
			+ "?searchEntity=" + JSON.stringify(searchEntity)
	$('#main-viewer').animate({
		opacity : 0
	}, 500, $.ajax({
		url : requestUrl,
		success : function(data) {
			var vehiclesList = $(data).filter('#mainContentView')
			setContent($('#mainContentView'), vehiclesList.html())
			$('#main-viewer').animate({
				opacity : 1
			}, 500)
		}
	}))
}

function requestSearchResult(searchEntity, curElement, page) {
	var requestUrl = SERVER_URL + SEARCH_VEHICLE_URI + "/page/" + page + "?searchEntity="
			+ JSON.stringify(searchEntity)
	$.ajax({
		url : requestUrl,
		success : function(data) {
			var vehiclesList = $(data).filter('#mainContentView')
			var citiesList = $(data).filter('select#city')
			var locationsList = $(data).filter('select#location')
			setContent($('#mainContentView'), vehiclesList.html())
			if(curElement) {
				if ($(curElement).val() != "") {
					switch ($(curElement).attr('id')) {
					case 'country':
						$('select#city').removeAttr('disabled')
						$('select#location').attr('disabled', 'disabled')
						setContent($('select#city'), citiesList.html())
						setContent($('select#location'), locationsList.html())
						break
					case 'city':
						$('select#location').removeAttr('disabled')
						setContent($('select#location'), locationsList.html())
						break
					}
				} else {
					switch ($(curElement).attr('id')) {
					case 'country':
						$('select#city').attr('disabled', 'disabled')
						$('select#location').attr('disabled', 'disabled')
						setContent($('select#city'), citiesList.html())
						setContent($('select#location'), locationsList.html())
						break
					case 'city':
						$('select#location').attr('disabled', 'disabled')
						setContent($('select#location'), locationsList.html())
						break
					}
				}
			}
			$('select').material_select()
			$('#main-viewer').animate({
				opacity : 1
			}, 500)
		}
	})
}

function getInsertData(curElement) {
	var requestUrl = SERVER_URL + GET_INSERT_DATA
	var country = $('#insert-country').val()
	var city = $('#insert-city').val()
	if (country != "") {
		requestUrl += "?country=" + country
		if (city != "" && $(curElement).attr('id') == 'insert-city') {
			requestUrl += "&city=" + city
		}
	}
	$.ajax({
		url : requestUrl,
		success : function(data) {
			var citiesList = $(data).filter('select#insert-city')
			var locationsList = $(data).filter('select#insert-location')
			if ($(curElement).val() != "") {
				switch ($(curElement).attr('id')) {
				case 'insert-country':
					$('select#insert-city').removeAttr('disabled')
					$('select#insert-location').attr('disabled', 'disabled')
					setContent($('select#insert-city'), citiesList.html())
					setContent($('select#insert-location'), locationsList
							.html())
					break
				case 'insert-city':
					$('select#insert-location').removeAttr('disabled')
					setContent($('select#insert-location'), locationsList
							.html())
					break
				}
			} else {
				switch ($(curElement).attr('id')) {
				case 'insert-country':
					$('select#insert-city').attr('disabled', 'disabled')
					$('select#insert-location').attr('disabled', 'disabled')
					setContent($('select#insert-city'), citiesList.html())
					setContent($('select#insert-location'), locationsList
							.html())
					break
				case 'insert-city':
					$('select#insert-location').attr('disabled', 'disabled')
					setContent($('select#insert-location'), locationsList
							.html())
					break
				}
			}
			$('select').material_select()
		}
	})
}

function formSearchEntity() {
	var country = $('#country').val()
	var city = $('#city').val()
	var location = $('#location').val()
	if(country == "") {
		city = location = ""
	} else if(city == "") {
		location = ""
	}
	var searchEntity = {
		"country" : country,
		"city" : city,
		"location" : location,
		"brand" : $('#brand').val(),
		"bodyType" : $('#body-type').val(),
		"fromYear" : $('#fromYear').val(),
		"toYear" : $('#toYear').val()
	}
	for ( var key in searchEntity) {
		if (searchEntity[key] == "") {
			searchEntity[key] = null
		}
	}
	return searchEntity
}