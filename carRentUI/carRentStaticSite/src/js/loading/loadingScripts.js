window.onload = function() {
	if(document.getElementsByClassName('header-main').length == 0) {
		var content = document.body.innerHTML
		var tabId = document.getElementById(PAGE_META).dataset.tabId
		var tabUri = document.getElementById(PAGE_META).dataset.tabUri
		sessionStorage.setItem('tabId', tabId)
		sessionStorage.setItem('awaitForHeader', content)
		sessionStorage.setItem('urlAwaited', SERVER_URL + tabUri)
		location.href = SERVER_URL
	}
}