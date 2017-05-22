$(document).ready(function () {
	$('#sidebar-spawner').sideNav();
	
	$('ul.tabs').tabs();
	$('ul.tabs').tabs({'onShow': function() {
		var tabId = $('a.active').attr('href')
		var url = SERVER_URL + $(tabId + " div#" + PAGE_META).data('tab-uri')
		history.pushState('', '', url)
	}});
})

function hideSideNav() {
	$('#sidebar-spawner').sideNav('hide');
}