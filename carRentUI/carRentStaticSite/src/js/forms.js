function submitRequest(reqOptions) {
	$.ajax({
        type: "POST",
        contentType: "application/json",
        url: SERVER_URL + reqOptions.uri,
        data: JSON.stringify(reqOptions.requestBody),
        beforeSend: function(request) {
            request.setRequestHeader($("meta[name='_csrf_header']").attr("content"),
            		$("meta[name='_csrf']").attr("content"));
        },
        success: reqOptions.onSuccess,
        error: reqOptions.onError
	})
}

function insertVehicle() {
	if($('.invalid-file').length) {
		Materialize.toast(i18n["toast_vehicle_validation_failure"], 5000, 'toast-green')
		return
	}
	var formData = formFiles()
	$.ajax({
        type: "POST",
        url: SERVER_URL + POST_VEHICLE_URI,
        data: formData,
        processData: false,
        contentType: false,
        beforeSend: function(request) {
            request.setRequestHeader($("meta[name='_csrf_header']").attr("content"),
            		$("meta[name='_csrf']").attr("content"));
        },
        success: function() {
        	$('#insert-vehicle-modal').modal('close')
        	Materialize.toast(i18n["toast_vehicle_insert_success"], 5000, 'toast-green')
        },
        error: function() {
        	Materialize.toast(i18n["toast_server_error"], 5000, 'toast-red')
        }
	})
}

function archiveVehicle(vehicleId) {
	var requestBody = {
		"id": $("#vehicleId").val()
	}
	submitRequest({
		"uri": ARCHIVE_VEHICLE_URI,
		"requestBody": requestBody,
		"onSuccess": function() {
			$(".admin-vehicle-option.green").addClass('disabled')
			$(".admin-vehicle-option.yellow").addClass('disabled')
			$(".admin-vehicle-option.red").css('display', 'none')
			$(".disabled-order-btn").css('display', 'inline-block')
			Materialize.toast(i18n["toast_archivation_success"], 5000, 'toast-green')
		},
		"onError": function() {
			Materialize.toast(i18n["toast_server_error"], 5000, 'toast-red')
		}
	})
}

function updateVehiclePrice() {
	var newPrice = $("#newPrice").val()
	var requestBody = {
		"id": $("#vehicleId").val(),
		"price": newPrice
	}
	submitRequest({
		"uri": UPDATE_VEHICLE_PRICE_URI,
		"requestBody": requestBody,
		"onSuccess": function() {
			$('#priceVal').html(newPrice)
			$('#modal-new-price-form').modal('close')
			Materialize.toast(i18n["toast_price_update_success"], 5000, 'toast-green')
		},
		"onError": function() {
			Materialize.toast(i18n["toast_server_error"], 5000, 'toast-red')
		}
	})
}

function realocateVehicle() {
	var locationVal = $('#modal-realocate-form  select#insert-location').val()
	var requestBody = {
		"id": $("#vehicleId").val(),
		"location": locationVal
	}
	submitRequest({
		"uri": REALOCATE_VEHICLE_URI,
		"requestBody": requestBody,
		"onSuccess": function(data) {
			$('#locationVal').html(data)
			$('#modal-realocate-form').modal('close')
			Materialize.toast(i18n["toast_location_update_success"], 5000, 'toast-green')
		},
		"onError": function() {
			Materialize.toast(i18n["toast_server_error"], 5000, 'toast-red')
		}
	})
}

function submitReply() {
	var requestBody = {
		"authorName": $("#first_name").val(),
		"text": $("#reply").val()
	}
	submitRequest({
		"uri": SUBMIT_REPLY_URI,
		"requestBody": requestBody,
		"onSuccess": function() {
			$('#modal-success').modal()
        	$("#first_name").val("")
        	$("#reply").val("")
        	$('#modal-success').modal('open')
		},
		"onError": function() {
			Materialize.toast(i18n["toast_server_error"], 5000, 'toast-red')
		}
	})
}

function preorderVehicle() {
	var requestBody = {
		"vehicleId": $('#vehicleId').attr('value')
	}
	var preorderedVehicles = JSON.parse(sessionStorage.getItem('preorderedVehicles'))
	if(!preorderedVehicles) {
		preorderedVehicles = [requestBody.vehicleId]
		
	} else {
		if(!preorderedVehicles.includes(requestBody.vehicleId)) {
			preorderedVehicles.push(requestBody.vehicleId)
		}
	}
	sessionStorage.setItem('preorderedVehicles', JSON.stringify(preorderedVehicles))
	submitRequest({
		"uri": PREORDER_VEHICLE_URI,
		"requestBody": requestBody,
		"onSuccess": function() {
			$(".vehicle-option").css('display', 'none')
			$(".disabled-order-btn").css('display', 'inline-block')
			spawnBasket()
			
			Materialize.toast(i18n["toast_preorder_success"], 5000, 'toast-green')
		},
		"onError": function() {
			Materialize.toast(i18n["toast_server_error"], 5000, 'toast-red')
		}
	})
}

function orderContract() {
	var preorderedVehicles = JSON.parse(sessionStorage.getItem('preorderedVehicles'))
	if(preorderedVehicles.length == 0) {
		$('#modal-order-form').modal('close')
		Materialize.toast(i18n["toast_contract_order_no_vehicles"], 5000, 'toast-green')
		return
	}
	var requestBody = {
		"openDate": $('#from-date').val(),
		"closeDate": $('#to-date').val(),
		"vehicleIds": preorderedVehicles
	}
	submitRequest({
		"uri": SUBMIT_CONTRACT_URI,
		"requestBody": requestBody,
		"onSuccess": function() {
			sessionStorage.removeItem('preorderedVehicles')
			$('#modal-order-form').modal('close')
			switchContent(SERVER_URL + USER_CABINET_URI, $('#loginTab'))
			Materialize.toast(i18n["toast_contract_order_success"], 5000, 'toast-green')
			killBasket()
		},
		"onError": function() {
			Materialize.toast(i18n["toast_server_error"], 5000, 'toast-red')
		}
	})
}

function eliminateRemoved() {
	var removedVehicles = []
	var preorderedVehicles = JSON.parse(sessionStorage.getItem('preorderedVehicles'))
	var removedEl = $('a.yellow.btn').each(function(idx) {
		var id = $(this).data('vehicle-id')
		removedVehicles.push(id)
	})
	var requestBody = {
		"openDate": $('#from-date').val(),
		"closeDate": $('#to-date').val(),
		"vehicleIds": removedVehicles
	}
	submitRequest({
		"uri": ELIMINATE_REMOVED_URI,
		"requestBody": requestBody,
		"onSuccess": function() {
			preorderedVehicles = preorderedVehicles.filter(function(val) {
				return !removedVehicles.includes(val)
			})
			sessionStorage.setItem('preorderedVehicles', JSON.stringify(preorderedVehicles))
			if(preorderedVehicles.length == 0) {
				killBasket()
			}
			if($('#vehicleId').length) {
				var onPageVehicleId = parseInt($('#vehicleId').val()) 
				if(removedVehicles.includes(onPageVehicleId)) {
					$(".vehicle-option").css('display', 'inline-block')
					$(".disabled-order-btn").css('display', 'none')
				}
			}
			$('#modal-order-form').modal('close')
		},
		"onError": function() {
			Materialize.toast(i18n["toast_server_error"], 5000, 'toast-red')
		}
	})
}

function registerUser() {
	var requestBody = {
		"checkPassword": $('#reg-repeat-password').val(),
		"user": {
			"username": $('#reg-username').val(),
			"password": $('#reg-password').val(),
			"email": $('#reg-email').val(),
			"name": $('#reg-name').val(),
			"surname": $('#reg-surname').val(),
			"patronymic": $('#reg-patronymic').val(),
			"contactNumber": $('#reg-contact-number').val()
		}
	}
	submitRequest({
		"uri": REGISTER_USER_URI,
		"requestBody": requestBody,
		"onSuccess": function() {
			$('#user-modal').modal('close')
			$('ul.tabs').tabs('select_tab', 'loginTab')
			Materialize.toast(i18n["toast_registration_success"], 5000, 'toast-green')
		},
		"onError": function() {
			Materialize.toast(i18n["toast_server_error"], 5000, 'toast-red')
		}
	})
}

function addFunds() {
	var fundsVal = parseInt($('#funds-amount').val())
	var requestBody = {
		"amount": fundsVal
	}
	submitRequest({
		"uri": ADD_FUNDS_URI,
		"requestBody": requestBody,
		"onSuccess": function() {
			var balance = parseInt($('#user-balance').text())
			$('#modal-funds').modal('close')
			$('#sidebar-spawner').sideNav('hide')
			$('#user-balance').text(balance + fundsVal)
			Materialize.toast(i18n["toast_funds_success"], 5000, 'toast-green')
		},
		"onError": function() {
			Materialize.toast(i18n["toast_server_error"], 5000, 'toast-red')
		}
	})
}

function changePassword() {
	var requestBody = {
		"oldPassword": $('#fp-old-password').val(),
		"newPassword": $('#fp-new-password').val(),
		"repeatNewPassword": $('#fp-repeat').val()
	}
	submitRequest({
		"uri": CHANGE_PASSWORD_URI,
		"requestBody": requestBody,
		"onSuccess": function() {
			$('#user-modal').modal('close')
			$('ul.tabs').tabs('select_tab', 'loginTab')
			Materialize.toast(i18n["toast_update_password_success"], 5000, 'toast-green')
		},
		"onError": function() {
			Materialize.toast(i18n["toast_server_error"], 5000, 'toast-red')
		}
	})
}

function performPayment(element) {
	var contractId = $(element).data('contract')
	var requestBody = {
		"contractId": contractId
	}
	submitRequest({
		"uri": PERFORM_PAYMENT_URI,
		"requestBody": requestBody,
		"onSuccess": function() {
			var userBalance = parseInt($('#user-balance').text())
			var price = parseInt($('#sum-to-pay').text())
			$('#user-balance').text(userBalance - price)
			$('li.active div.collapsible-body div#payment-option').remove()
			$('li.active div.collapsible-body div#hidden-msg').css('display', 'inline')
			$('#confirmation-modal').modal('close')
			Materialize.toast(i18n["toast_payment_success"], 5000, 'toast-green')
		},
		"onError": function(e) {
			if(e.status == '402') {
				$('#confirmation-modal').modal('close')
				Materialize.toast(i18n["toast_payment_insufficient_funds"], 5000, 'toast-green')
			} else {
				Materialize.toast(i18n["toast_server_error"], 5000, 'toast-red')
			}
		}
	})
}

function performShutdown(element) {
	var contractId = $(element).data('contract-id')
	var requestBody = {
		"contractId": contractId
	}
	submitRequest({
		"uri": PERFORM_SHUTDOWN_URI,
		"requestBody": requestBody,
		"onSuccess": function() {
			var headerText = $('div.collapsible-header.active').html()
			$('div.collapsible-header.active').html(headerText + '<b>' + i18n["toast_contract_shutdown_closed"] + '</b>')
			$('li.active div.collapsible-body div#contract-view-footer').remove()
			$('li.active div.collapsible-body div#hidden-msg').css('display', 'inline')
			$('#confirmation-modal').modal('close')
			Materialize.toast(i18n["toast_contract_shutdown_success"], 5000, 'toast-green')
		},
		"onError": function(e) {
			Materialize.toast(i18n["toast_server_error"], 5000, 'toast-red')
		}
	})
}

function removeReply(id) {
	var requestBody = {
		"replyId": id
	}
	submitRequest({
		"uri": REMOVE_REPLY_URI,
		"requestBody": requestBody,
		"onSuccess": function() {
			$('div#reply-' + id).remove()
			Materialize.toast(i18n["toast_reply_delete_success"], 5000, 'toast-green')
		},
		"onError": function(e) {
			Materialize.toast(i18n["toast_server_error"], 5000, 'toast-red')
		}
	})
}

function formFiles() {
	var formData = new FormData()
	formData.append('location', $('#insert-location').val())
	formData.append('brand', $('#insert-brand').val())
	formData.append('model', $('#insert-model').val())
	formData.append('bodyType', $('#insert-bodyType').val())
	formData.append('year', $('#insert-year').val())
	formData.append('price', $('#insert-price').val())
	formData.append('mainImage', $('#mainView')[0].files[0])
	formData.append('frontImage', $('#frontView')[0].files[0])
	formData.append('leftImage', $('#leftView')[0].files[0])
	formData.append('rightImage', $('#rightView')[0].files[0])
	formData.append('backImage', $('#backView')[0].files[0])
	return formData
}