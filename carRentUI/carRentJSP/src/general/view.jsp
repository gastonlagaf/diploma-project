<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/ext/materialize-src/css/materialize.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/style/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/style/footer.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/style/view-vehicle.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/style/primary-content.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/style/search-criteria.css">
<script
	src="${pageContext.request.contextPath}/resources/js/i18n/messages_${pageContext.response.locale}.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/ext/jquery-src/jquery-3.1.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/ext/materialize-src/js/materialize.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/ext/chart-js/chart.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/initScript.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/headerScripts.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/searchCriteriaScripts.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/mainViewerScripts.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/authentificationScripts.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/searchScript.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/forms.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/chartScripts.js"></script>
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<title><s:message code="tab.title" /></title>
</head>
<body>
	<jsp:include page="../elements/header.jsp" />
	<main>
		<jsp:include page="../elements/tabs.jsp" />
		<jsp:include page="../elements/basket.jsp" />
		<jsp:include page="../elements/sideNavigation.jsp" />
		<jsp:include page="../elements/userForms.jsp" />
	</main>
	<jsp:include page="../elements/footer.jsp" />
</body>
</html>