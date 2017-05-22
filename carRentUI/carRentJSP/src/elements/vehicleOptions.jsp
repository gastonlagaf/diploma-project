<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<c:choose>
		<c:when test="${vehicle.preordered}">
			<a id="btn-reserved" class="btn disabled vehicle-option"><s:message
					code="vehicle.button.reserved" /></a>
		</c:when>
		<c:when test="${pageContext.request.isUserInRole('ROLE_USER')}">
			<a
				class="btn purple accent-2 waves-effect waves-light vehicle-option"
				onclick="preorderVehicle()" id="order-btn"><s:message
					code="vehicle.button.order" /></a>
			<a class="btn disabled disabled-order-btn vehicle-option"><s:message
					code="vehicle.button.added" /></a>
		</c:when>
		<c:when test="${sessionScope.user == null}">
			<a class="btn disabled vehicle-option"><s:message
					code="vehicle.button.login.first" /></a>
		</c:when>
		<c:when test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
			<a onclick="openUpdatePriceModal()" data-position="top"
				data-delay="50"
				data-tooltip="<s:message	code="vehicle.button.update.price" />"
				class="btn green waves-effect waves-light admin-vehicle-option tooltipped"><i
				class="material-icons">payment</i></a>
			<a onclick="openUpdateLocationModal()" data-position="top"
				data-delay="50"
				data-tooltip="<s:message code="vehicle.button.realocate" />"
				class="btn yellow waves-effect waves-light admin-vehicle-option tooltipped"><i
				class="material-icons">import_export</i></a>
			<a onclick="archiveVehicle()" data-position="top" data-delay="50"
				data-tooltip="<s:message code="vehicle.button.archive" />"
				class="btn red waves-effect waves-light admin-vehicle-option tooltipped"><i
				class="material-icons">present_to_all</i></a>
			<a
				class="btn disabled disabled-order-btn admin-vehicle-option tooltipped"
				data-position="top" data-delay="50"
				data-tooltip="<s:message
					code="vehicle.button.already.archived" />"><i
				class="material-icons">present_to_all</i></a>

			<!-- Update Modals -->

			<!-- Location -->
			<div id="modal-realocate-form" class="modal">
				<div class="modal-content">
					<p class="primary-text characteristics right">
						<i><s:message code="vehicle.update.label.realocate" /></i>
					</p>
					<jsp:include page="../elements/locationSelector.jsp" />
				</div>
				<div class="modal-footer">
					<a onclick="realocateVehicle()"
						class="modal-action modal-close btn blue-grey lighten-1"> <s:message
							code="vehicle.update.button.update" />
					</a> <a class="modal-action modal-close btn red"> <s:message
							code="vehicle.update.button.cancel" />
					</a>
				</div>
			</div>

			<!-- Price -->
			<div id="modal-new-price-form" class="modal">
				<div class="modal-content">
					<div class="row">
						<div class="col s2">
							<p class="primary-text characteristics right">
								<i><s:message code="vehicle.update.label.price" /></i>
							</p>
						</div>
						<div class="col s10">
							<div class="input-field">
								<input type="text" id="newPrice">
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<a onclick="updateVehiclePrice()"
						class="modal-action modal-close btn blue-grey lighten-1"> <s:message
							code="vehicle.update.button.update" />
					</a> <a class="modal-action modal-close btn red"> <s:message
							code="vehicle.update.button.cancel" />
					</a>
				</div>
			</div>
		</c:when>
	</c:choose>
</body>
</html>