<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<div class="modal-content">
	<jsp:include page="../elements/locationSelector.jsp" />
	<div class="row">
		<div class="col s3">
			<div class="input-field">
				<select id="insert-brand" tabindex="4">
					<c:forEach
						items="${applicationScope['searchCriteriaData'].getBrands()}"
						var="it">
						<option value="${it}">${it}</option>
					</c:forEach>
				</select> <label><s:message code="vehicle.insert.label.brand" /></label>
			</div>
		</div>
		<div class="col s3">
			<div class="input-field">
				<label for="insert-model"><s:message code="vehicle.insert.label.model" /></label> <input type="text" id="insert-model" tabindex="5">
			</div>
		</div>
		<div class="col s3">
			<div class="file-field input-field">
				<div class="btn blue-grey lighten-1">
					<span><s:message code="vehicle.insert.button.image.front" /></span> <input type="file" id="frontView" tabindex="9">
				</div>
				<div class="file-path-wrapper">
					<input id="frontViewPath" class="file-path validate" type="text" onchange="validateFile(this)"  tabindex="10">
				</div>
			</div>
		</div>
		<div class="col s3">
			<div class="file-field input-field">
				<div class="btn blue-grey lighten-1">
					<span><s:message code="vehicle.insert.button.image.back" /></span> <input type="file" id="backView" tabindex="11">
				</div>
				<div class="file-path-wrapper">
					<input id="backViewPath" class="file-path validate" type="text" onchange="validateFile(this)" tabindex="12">
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col s3">
			<div class="input-field">
				<select id="insert-bodyType" tabindex="6">
					<c:forEach
						items="${applicationScope['searchCriteriaData'].getBodyTypes()}"
						var="it">
						<option value="${it}">${it}</option>
					</c:forEach>
				</select> <label><s:message code="vehicle.insert.label.body.type" /></label>
			</div>
		</div>
		<div class="col s3">
			<div class="input-field">
				<label for="insert-year"><s:message code="vehicle.insert.label.year" /></label> <input type="number"
					id="insert-year" tabindex="7">
			</div>
		</div>
		<div class="col s3">
			<div class="file-field input-field">
				<div class="btn blue-grey lighten-1">
					<span><s:message code="vehicle.insert.button.image.left" /></span> <input type="file" id="leftView" tabindex="13">
				</div>
				<div class="file-path-wrapper">
					<input id="leftViewPath" class="file-path validate" type="text" onchange="validateFile(this)" tabindex="14">
				</div>
			</div>
		</div>
		<div class="col s3">
			<div class="file-field input-field">
				<div class="btn blue-grey lighten-1">
					<span><s:message code="vehicle.insert.button.image.right" /></span> <input type="file" id="rightView" tabindex="15">
				</div>
				<div class="file-path-wrapper">
					<input id="rightViewPath" class="file-path validate" type="text" onchange="validateFile(this)" tabindex="16">
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col s6">
			<div class="input-field">
				<label for="insert-price"><s:message code="vehicle.insert.label.price" /></label> <input type="number"
					id="insert-price" tabindex="8">
			</div>
		</div>
		<div class="col s6">
			<div class="file-field input-field">
				<div class="btn blue-grey lighten-1">
					<span><s:message code="vehicle.insert.button.image.main" /></span> <input type="file" id="mainView">
				</div>
				<div class="file-path-wrapper">
					<input id="mainViewPath" class="file-path validate" type="text" onchange="validateFile(this)">
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col s6">
			<a id="insert-confirmation" class="btn-large green accent-3" onclick="insertVehicle()">
				<i class="primary-text cabinet-title"><s:message code="vehicle.insert.button.submit" /></i>
			</a>	
		</div>
		<div class="col s6">
			<a id="insert-confirmation" class="btn-large pink accent-1" onclick="closeInsertVehicle()">
				<i class="primary-text cabinet-title"><s:message code="vehicle.insert.button.cancel" /></i>
			</a>	
		</div>
	</div>
</div>
