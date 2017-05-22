<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<div class="row">
	<div class="col s4">
		<div class="input-field">
			<select id="insert-country" tabindex="1" onchange="getInsertData(this)">
				<option disabled selected><s:message code="search.country.select" /></option>
				<c:forEach
					items="${applicationScope['searchCriteriaData'].getCountries()}"
					var="it">
					<option value="${it}">${it}</option>
				</c:forEach>
			</select> <label><s:message code="vehicle.insert.label.country" /></label>
		</div>
	</div>
	<div class="col s4">
		<div class="input-field">
			<select disabled id="insert-city" tabindex="2" onchange="getInsertData(this)">
				<option value=""><s:message code="search.city.select" /></option>
			</select> <label><s:message code="vehicle.insert.label.city" /></label>
		</div>
	</div>
	<div class="col s4">
		<div class="input-field">
			<select disabled id="insert-location" tabindex="3">
				<option value=""><s:message code="search.location.select" /></option>
			</select> <label><s:message code="vehicle.insert.label.location" /></label>
		</div>
	</div>
</div>