<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<div id="modal-order-form" class="modal modal-fixed-footer">
	<div class="modal-content">
		<p class="primary-text contract-title brand-text">
			<i><s:message code="order.title" /></i>
		</p>
		<table class="contract-table striped centered">
			<thead>
				<tr>
					<th><s:message code="order.header.vehicle" /></th>
					<th><s:message code="order.header.price" /></th>
					<th><s:message code="order.header.options" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${sessionScope.basket}" var="vehicle">
					<tr>
						<td>${vehicle.brand.name} ${vehicle.model}</td>
						<td><p id="price-per-vehicle">${vehicle.price}</p> $ <s:message code="order.label.per.hour" /></td>
						<td><a onclick="performOptionChangeModal(this)" data-vehicle-id="${vehicle.id}" class="waves-effect waves-light red btn"><s:message code="order.button.remove" /></a></td>
					</tr>
				</c:forEach>
				<tr>
					<td></td>
					<td class="primary-text brand-text right"><i><s:message code="order.label.total" />:</i></td>
					<td id="total-val" class="primary-text brand-text"><i></i></td>
				</tr>
			</tbody>
		</table>
		<div class="row contract-dates">
			<div class="input-field col s6">
				<label for="from-date"><s:message code="order.label.date.from" /></label>
				<input onchange="calculateSum()" id="from-date" type="date" class="datepicker">
			</div>
			<div class="input-field col s6">
				<label for="to-date"><s:message code="order.label.date.to" /></label>
				<input onchange="calculateSum()" id="to-date" type="date" class="datepicker">
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<a onclick="orderContract()"
			class="modal-action modal-close btn blue-grey lighten-1"><s:message code="order.button.submit" /></a>
		<a onclick="eliminateRemoved()"
			class="modal-action modal-close btn red"><s:message code="order.button.cancel" /></a>
	</div>
</div>