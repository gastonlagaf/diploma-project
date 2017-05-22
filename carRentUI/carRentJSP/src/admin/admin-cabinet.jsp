<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<head>
<script
	src="${pageContext.request.contextPath}/resources/js/initScript.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/loading/loadingScripts.js"></script>
</head>
<body>
	<div id="page-meta-info" data-tab-id="loginTab"
		data-tab-uri="/user/cabinet"></div>
	<div id="insert-vehicle-modal" class="modal bottom-sheet">
		<jsp:include page="../forms/insertVehicle.jsp" />
	</div>
	<div class="container">
		<p class="primary-text cabinet-title">
			<i><s:message code="admin.cabinet.title" /></i>
		</p>
		<div class="row admin-options">
			<div class="col s6">
				<div class="cabinet-menu collection">
					<a class="collection-item" onclick="viewContracts(1)"><i class="material-icons large">class</i><s:message code="admin.cabinet.contracts" /></a>
					<a class="collection-item" onclick="viewStatistics()"><i class="material-icons large">trending_up</i><s:message code="admin.cabinet.stats" /></a>
				</div>
			</div>
			<div class="col s6">
				<div class="cabinet-menu collection">
					<a class="collection-item" onclick="viewInsertVehicle()"><i class="material-icons large">note_add</i><s:message code="admin.cabinet.vehicle.add" /></a>
					<a class="collection-item" onclick="viewReplies(1)"><i class="material-icons large">thumbs_up_down</i><s:message code="admin.cabinet.replies" /></a>
				</div>
			</div>
		</div>
	</div>
</body>