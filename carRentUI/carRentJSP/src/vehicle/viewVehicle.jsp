<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="${pageContext.request.contextPath}/resources/js/initScript.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/loading/loadingScripts.js"></script>
</head>
<body>
	<div id="page-meta-info" data-tab-id="viewTab" data-tab-uri="/vehicle/${vehicle.uri}"></div>
	<div id="prev-invocation" data-invocation="viewVehicles($page:1)"></div>
	<div class="container">
		<div class="row">
			<c:forEach begin="1" end="4" varStatus="loop">
				<div class="col s3">
					<img class="materialboxed vehicle-image vehicle-short-view"
						src="${pageContext.request.contextPath}${vehicle.images[loop.index].imagePath}">
				</div>
			</c:forEach>
		</div>
		<div class="row">
			<div class="col s8">
				<img class="materialboxed vehicle-image vehicle-image-view"
					src="${pageContext.request.contextPath}${vehicle.images[0].imagePath}">
				<div id="vehicle-options">
					<jsp:include page="../elements/vehicleOptions.jsp" />
				</div>
			</div>
			<div class="col s4">
				<input type="hidden" id="vehicleId" value="${vehicle.id}" />
				<p class="primary-text characteristics-title">${vehicle.brand.name}
					${vehicle.model}</p>
					
				<table class="striped centered">
					<tr>
						<td><s:message code="vehicle.view.location" /></td>
						<td><span id="locationVal"><s:message code="${vehicle.location.name}" /></span></td>
					</tr>
					<tr>
						<td><s:message code="vehicle.view.body.type" /></td>
						<td><span id="bodyTypeVal">${vehicle.bodyType.name}</span></td>
					</tr>
					<tr>
						<td><s:message code="vehicle.view.year" /></td>
						<td><span id="yearVal">${vehicle.manufactureYear}</span></td>
					</tr>
					<tr>
						<td><s:message code="vehicle.view.price" /></td>
						<td><span id="priceVal">${vehicle.price}</span> $ <s:message code="vehicle.view.price.suffix" /></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/resources/js/loading/viewVehicleLoadingScript.js"></script>
</body>
</html>