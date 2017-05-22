$(document).ready(function() {
	var loadedContentBefore = sessionStorage.getItem('awaitForHeader')
	if (loadedContentBefore) {
		var tabId = sessionStorage.getItem('tabId')
		setContent($('#' + tabId), loadedContentBefore)
		$('ul.tabs').tabs('select_tab', tabId)
		history.replaceState('', '', sessionStorage.getItem('urlAwaited'))
		sessionStorage.removeItem('tabAwaited')
		sessionStorage.removeItem('urlAwaited')
		sessionStorage.removeItem('awaitForHeader')
	}
	if ($('#viewTab').children().length == 0) {
		$.ajax({
			url : SERVER_URL + GET_VEHICLES_URI + '1',
			success : function(data) {
				setContent($('#viewTab'), data)
				$('#viewTab').animate({
					opacity : 1
				}, 500)
			}
		})
	}
})

window.onpopstate = function(event) {
	var tabId = $('a.active').attr('href')
	var prevInvocation = $(tabId + " #prev-invocation").data('invocation')
	if (prevInvocation) {
		executeMethod(prevInvocation)
	}
}

function executeMethod(prevInvocation) {
	var result = {
		"method" : prevInvocation.substring(0, prevInvocation.indexOf('(')),
		"args" : []
	}
	var curIndex = 0
	while ((curIndex = prevInvocation.indexOf('$', ++curIndex)) != -1) {
		var tokenEnd = prevInvocation.indexOf(':', curIndex)
		var defaultValEndIdx = prevInvocation.indexOf(',', curIndex)
		if (defaultValEndIdx == -1) {
			defaultValEndIdx = prevInvocation.indexOf(')', curIndex)
		}
		var token = prevInvocation.substring(curIndex + 1, tokenEnd)
		var valFromSession = sessionStorage.getItem(token)
		sessionStorage.removeItem(token)
		if (valFromSession) {
			result.args.push(valFromSession)
		} else {
			var defaultVal = prevInvocation.substring(tokenEnd + 1,
					defaultValEndIdx)
			result.args.push(defaultVal)
		}
	}
	var foo = window[result.method]
	if (typeof foo == "function") {
		foo.apply(null, result.args)
	}
}

function changeLocale(el) {
	var localeValue = $(el).data('value')
	$.ajax({
		url : SERVER_URL + CHANGE_LOCALE_URI + localeValue,
		success : function() {
			location.reload()
		},
		error: function() {
			Materialize.toast(i18n["toast_server_error"], 5000, 'toast-red')
		}
	})
}

function viewVehicles(page) {
	var mainContainer = $('#viewTab')
	switchContent(SERVER_URL + GET_VEHICLES_URI + page, mainContainer)
	sessionStorage.setItem("page", page)
}

function navBarOpenContracts() {
	$('#sidebar-spawner').sideNav('hide')
	$('ul.tabs').tabs('select_tab', 'loginTab')
	viewContracts(1)
}

function viewContractVehicle(uri) {
	viewVehicle(uri)
	$('ul.tabs').tabs('select_tab', 'viewTab')
}

function viewVehicle(uri) {
	switchContent(SERVER_URL + GET_VEHICLE_URI + uri, $('#viewTab'))
}

function viewContracts(page) {
	switchContent(SERVER_URL + GET_CONTRACTS_URI + page, $('#loginTab'))
	sessionStorage.setItem('contract_page', page)
}

function viewUserCabinet() {
	switchContent(SERVER_URL + USER_CABINET_URI, $('#loginTab'))
}

function viewReplies(page) {
	switchContent(SERVER_URL + GET_REPLIES_URI + page, $('#loginTab'))
	sessionStorage.setItem('reply_page', page)
}

function viewStatistics() {
	switchContent(SERVER_URL + GET_STATISTICS_URI, $('#loginTab'))
}

function switchContent(url, element) {
	element.animate({
		opacity : 0
	}, 500, executeRequest(url, '', element))
}

function executeRequest(requestUrl, title, element) {
	$.ajax({
		url : requestUrl,
		success : function(data) {
			setContent(element, data)
			element.animate({
				opacity : 1
			}, 500)
			window.history.pushState('', title, requestUrl)
		}
	})
}

function performOptionChangeModal(btn) {
	if ($(btn).hasClass('red')) {
		removeVehicleFromContract(btn)
	} else {
		returnVehicleToContract(btn)
	}
}

function removeVehicleFromContract(btn) {
	var id = $(btn).data('vehicle-id')
	var preorderedVehicles = JSON.parse(sessionStorage
			.getItem('preorderedVehicles'))
	preorderedVehicles = preorderedVehicles.filter(function(val) {
		return val != id
	})
	sessionStorage.setItem('preorderedVehicles', JSON
			.stringify(preorderedVehicles))
	$(btn).removeClass('red')
	$(btn).addClass('yellow')
	$(btn).text(i18n["toast_contract_vehicle_return"])
}

function returnVehicleToContract(btn) {
	var id = $(btn).data('vehicle-id')
	var preorderedVehicles = JSON.parse(sessionStorage
			.getItem('preorderedVehicles'))
	if (!preorderedVehicles.includes(id)) {
		preorderedVehicles.push(id)
	}
	sessionStorage.setItem('preorderedVehicles', JSON
			.stringify(preorderedVehicles))
	$(btn).removeClass('yellow')
	$(btn).addClass('red')
	$(btn).text(i18n["toast_contract_vehicle_remove"])
}

function openContractModal() {
	$.ajax({
		url : SERVER_URL + GET_CONTRACT_FORM,
		success : function(data) {
			$('#modal-order-form').modal()
			var content = $(data).filter('#modal-order-form')
			$('#modal-order-form').html(content.html())

			var totalSum = getTotalSum()
			$('#total-val').text(totalSum * 24 + " $")
			var yesterday = new Date((new Date()).valueOf()-1000*60*60*24);
			$('.datepicker').pickadate({
				selectMonths : true,
				selectYears : 15,
				container : 'body',
				format : 'yyyy-mm-dd',
				disable: [
					{ from: [0,0,0], to: yesterday }
				]
			})
			$('#modal-order-form').modal('open')
		},
		error : function() {
			Materialize.toast(i18n["toast_server_error"],5000, 'toast-green')
			}
		})
}

function openCabinetTab() {
	$('#sidebar-spawner').sideNav('hide')
	$('ul.tabs').tabs('select_tab', 'loginTab')
}

function openUpdatePriceModal() {
	$('#modal-new-price-form').modal()
	$('#modal-new-price-form').modal('open')
}

function openUpdateLocationModal() {
	$('#modal-realocate-form').modal()
	$('#modal-realocate-form').modal('open')
	$('select').material_select();
}

function viewInsertVehicle() {
	$('select').material_select();
	$('#insert-vehicle-modal').modal()
	$('#insert-vehicle-modal').modal('open')
}

function closeInsertVehicle() {
	$('#insert-vehicle-modal').modal('close')
}

function openUserModal() {
	$('#sidebar-spawner').sideNav('hide')
	$('#user-modal').modal()
	$('#user-modal').modal('open')
}

function openAddFundsModal() {
	$('#modal-funds').modal()
	$('#modal-funds').modal('open')
}

function openConfirmationModal(price, contract) {
	$('#confirmation-modal').modal()
	$('#sum-to-pay').html(price + '$')
	$('#payment-btn').data('contract', contract)
	$('#confirmation-modal').modal('open')
}

function openShutdownConfirmationModal(id) {
	$('#confirmation-modal').modal()
	$('#shutdown-btn').data('contract-id', id)
	$('#confirmation-modal').modal('open')
}

function getTotalSum() {
	var totalSum = 0
	$('[id=price-per-vehicle]').each(function(idx, val) {
		totalSum += parseInt($(val).html())
	})
	return totalSum
}

function calculateSum() {
	var from = new Date($('#from-date').val())
	var to = new Date($('#to-date').val())
	if (to & from) {
		var days = (to.getTime() - from.getTime()) / (1000 * 60 * 60 * 24)
		if (days <= 0) {
			Materialize.toast(i18n["toast_contract_order_invalid"], 5000, 'toast-green')
			$('#to-date').val('')
		} else {
			var total = getTotalSum()
			$('#total-val').text(total * days * 24 + " $")
		}
	}
}

function setContent(where, what) {
	where.html(what)
}

function spawnBasket() {
	$(".btn-basket").css('display', 'block')
	$(".btn-basket").animate({
		opacity : 1
	}, 250)
}

function killBasket() {
	$(".btn-basket").animate({
		opacity : 0
	}, 250)
	$(".btn-basket").css('display', 'none')
}

function validateFile(element) {
	var fileName = $(element).val()
	if(!fileName.endsWith('.jpg')) {
		$(element).addClass('invalid-file')
		$(element).val(validation_insert_vehicle_images)
	} else {
		if($(element).hasClass('invalid-file')) {
			$(element).removeClass('invalid-file')
		}
	}
}