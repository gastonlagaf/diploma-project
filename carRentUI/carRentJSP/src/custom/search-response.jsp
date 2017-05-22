<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<script
	src="${pageContext.request.contextPath}/resources/js/initScript.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/loading/loadingScripts.js"></script>
<select disabled id="city">
	<option class="disabled-option" value="" disabled selected><s:message
			code="search.city" /></option>
	<option value=""><s:message code="ALL" /></option>
	<c:forEach items="${searchResult.cities}" var="el">
		<option value="${el}"><s:message code="${el}" /></option>
	</c:forEach>
</select>
<select disabled id="location">
	<c:choose>
		<c:when test="${searchResult.locations.isEmpty() }">
			<option class="disabled-option" value="" disabled selected><s:message
					code="search.location" /></option>
		</c:when>
		<c:otherwise>
			<option class="disabled-option" value="" disabled selected><s:message
					code="search.location.select" /></option>
		</c:otherwise>
	</c:choose>
	<option value=""><s:message code="ALL" /></option>
	<c:forEach items="${searchResult.locations}" var="el">
		<option value="${el}"><s:message code="${el}" /></option>
	</c:forEach>
</select>
<div id="mainContentView" class="mainContentView">
	<!-- <c:forEach items="${searchResult.vehiclesPage.vehicles}" var="el">
		<div class="col s12 vehicle-row-big z-depth-4"
			onclick="viewVehicle('${el.uri}')">
			<div class="col s2">
				<img class="vehicle-image"
					src="${pageContext.request.contextPath}${el.images[0].imagePath}" />
			</div>
			<div class="col s10">
				<label class="vehicle-title-small primary-text">${el.brand.name}
					${el.model} (${el.manufactureYear}) ${el.location.name}</label>
			</div>
		</div>
	</c:forEach>  -->
	<c:forEach begin="0" end="${searchResult.vehiclesPage.vehicles.size() / 3}" varStatus="loop">
		<div class="row">
			<c:forEach begin="${loop.index * 3}" end="${loop.index * 3 + 2}"
				varStatus="innerLoop">
				<c:if test="${searchResult.vehiclesPage.vehicles[innerLoop.index] != null}">
					<div class="col s4">
						<div class="vehicle-card card white waves-effect"
							onclick="viewVehicle('${searchResult.vehiclesPage.vehicles[innerLoop.index].uri}')">
							<div class="card-content">
								<label class="vehicle-text vehicle-title-card black-text">${searchResult.vehiclesPage.vehicles[innerLoop.index].brand.name}<br />${searchResult.vehiclesPage.vehicles[innerLoop.index].model}</label>
								<img class="vehicle-image"
									src="${pageContext.request.contextPath}${searchResult.vehiclesPage.vehicles[innerLoop.index].images[0].imagePath}" />
							</div>
							<div class="card-action blue-grey lighten-1">
								<label class="vehicle-card-action vehicle-text white-text">Year:
									${searchResult.vehiclesPage.vehicles[innerLoop.index].manufactureYear}</label><br />
									<label class="vehicle-card-action vehicle-text white-text"><s:message code="vehicles.price" />: ${searchResult.vehiclesPage.vehicles[innerLoop.index].price} <s:message code="vehicles.label.per.hour" /></label>
								</label>
							</div>
						</div>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</c:forEach>
	<c:if test="${searchResult.vehiclesPage.pages != 0}">
		<ul class="pagination">
			<c:choose>
				<c:when test="${page == 1}">
					<li class="disabled"><a href="#!"><i
							class="material-icons">chevron_left</i></a></li>
				</c:when>
				<c:otherwise>
					<li class="waves-effect"><a onclick="viewVehicles(${page - 1})"><i
							class="material-icons">chevron_left</i></a></li>
				</c:otherwise>
			</c:choose>
			<c:forEach begin="1" end="${searchResult.vehiclesPage.pages}"
				varStatus="loop">
				<c:choose>
					<c:when test="${page == loop.index}">
						<li class="active"><a>${loop.index}</a></li>
					</c:when>
					<c:otherwise>
						<li><a onclick="viewVehicles(${loop.index})">${loop.index}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${page == searchResult.vehiclesPage.pages}">
					<li class="disabled"><a href="#!"><i
							class="material-icons">chevron_right</i></a></li>
				</c:when>
				<c:otherwise>
					<li class="waves-effect"><a onclick="viewVehicles(${page + 1})"><i
							class="material-icons">chevron_right</i></a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</c:if>
</div>