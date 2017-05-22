<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<div class="search-criteria">
		<p class="search-title primary-text"><s:message code="search.title" /></p>
		<div class="input-field col s12">
			<select id="country" onchange="searchVehicles(this)">
				<option class="disabled-option" value="" disabled selected><s:message code="search.country.select" /></option>
				<option value=""><s:message code="ALL" /></option>
				<c:forEach
					items="${applicationScope['searchCriteriaData'].getCountries()}"
					var="it">
					<option value="${it}"><s:message code="${it}" /></option>
				</c:forEach>
			</select> <label><s:message code="search.label.country" /></label>
		</div>
		<div class="input-field col s12">
			<select disabled id="city" onchange="searchVehicles(this)">
				<option class="disabled-option" value="" disabled selected><s:message code="search.city.select" /></option>
			</select> <label><s:message code="search.label.city" /></label>
		</div>
		<div class="input-field col s12">
			<select disabled id="location" onchange="searchVehicles(this)">
				<option class="disabled-option" value="" disabled selected><s:message code="search.location.select" /></option>
			</select> <label><s:message code="search.label.location" /></label>
		</div>
		<div class="input-field col s12">
			<select id="brand" onchange="searchVehicles(this)">
				<option value="" disabled selected><s:message code="search.brand.select" /></option>
				<option value=""><s:message code="ALL" /></option>
				<c:forEach
					items="${applicationScope['searchCriteriaData'].getBrands()}"
					var="it">
					<option value="${it}">${it}</option>
				</c:forEach>
			</select> <label><s:message code="search.label.brand" /></label>
		</div>
		<div class="input-field col s12">
			<select id="body-type" onchange="searchVehicles(this)">
				<option value="" disabled selected><s:message code="search.body.type.select" /></option>
				<option value=""><s:message code="ALL" /></option>
				<c:forEach
					items="${applicationScope['searchCriteriaData'].getBodyTypes()}"
					var="it">
					<option value="${it}">${it}</option>
				</c:forEach>
			</select> <label><s:message code="search.label.body.type" /></label>
		</div>
		<div class="input-field col s6">
			<input id="fromYear" min="2000" type="number" onchange="searchVehicles(this)"> <label
				for="fromYear"><s:message code="search.label.year.from" /></label>
		</div>
		<div class="input-field col s6">
			<input id="toYear" min="2000" type="number"  onchange="searchVehicles(this)"> <label
				for="toYear"><s:message code="search.label.year.to" /></label>
		</div>
		<script>
			$(document).ready(function() {
				$('select').material_select();
			});
		</script>
	</div>
</body>