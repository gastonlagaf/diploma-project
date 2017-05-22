function logoutUser() {
	$.ajax({
        type: "POST",
        url: SERVER_URL + USER_LOGOUT_URI,
        beforeSend: function(request) {
            request.setRequestHeader($("meta[name='_csrf_header']").attr("content"),
            		$("meta[name='_csrf']").attr("content"));
        },
        success: function(data) {
        	requestOkActions(data)
        	$('ul.tabs').tabs('select_tab', 'loginTab')
        	killBasket()
		},
        error: function(e) {
			Materialize.toast(i18n["toast_server_error"], 5000, 'toast-red')
		}
	})
}

function tryLogin() {
	$.ajax({
        type: "POST",
        contentType: "application/x-www-form-urlencoded",
        url: SERVER_URL + USER_LOGIN_URI,
        data: $("#loginForm").serialize(),
        beforeSend: function(request) {
            request.setRequestHeader($("meta[name='_csrf_header']").attr("content"),
            		$("meta[name='_csrf']").attr("content"));
        },
        success: function(data) {
			requestOkActions(data)
			window.history.replaceState('', '', SERVER_URL + USER_CABINET_URI)
		},
        error: function(e) {
			if(e.status == '401') {
				Materialize.toast(i18n["toast_login_failure"], 5000, 'toast-red')
			} else {
				Materialize.toast(i18n["toast_server_error"], 5000, 'toast-red')
			}
		}
	})
}

function requestOkActions(data) {
	var navBar = $(data).filter('#slide-out')
	var cabinetView = $(data).filter('#cabinetTabBody')
	var userForm = $(data).filter('#user-modal')
	
	$('#loginTab').animate({opacity: 0}, 500, function() {
		$('#loginTab').html(cabinetView.html())
		$('#loginTab').animate({opacity: 1}, 500)
	})
	$('#sidebar-spawner').sideNav('destroy')
	$('#slide-out').html(navBar.html())
	$('#sidebar-spawner').sideNav()
	$('#user-modal').html(userForm.html())
	
	if(!$("#btn-reserved").length) {
		if($("#vehicle-options").length) {
			var vehicleButtons = $(data).filter('#vehicle-options')
			$("#vehicle-options").html(vehicleButtons)
			$('.tooltipped').tooltip({delay: 50})
		}
	}
	
	var token = $(data).filter('#token').html()
	$("meta[name='_csrf']").attr("content", token)
}