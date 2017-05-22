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
	<div id="page-meta-info" data-tab-id="loginTab" data-tab-uri="/user/cabinet"></div>
	<div class="container">
		<p class="primary-text">
			<b><i>${sessionScope.user.surname} ${sessionScope.user.name}
				${sessionScope.user.patronymic} (<s:message code="user.cabinet.balance" />: <span id="user-balance">${sessionScope.user.account.balance}</span>)</i></b>
		</p>
		<div class="divider"></div>
		<c:choose>
			<c:when test="${!sessionScope.user.contracts.isEmpty()}">
				<div id="confirmation-modal" class="modal">
					<div class="modal-content">
						<p class="primary-text brand-text"><i><s:message code="user.cabinet.message.payment.confirmation" /> <span id="sum-to-pay"></span> ?</i></p>
					</div>
					<div class="modal-footer">
						<a id="payment-btn" class="btn green accent-3" onclick="performPayment(this)"><s:message code="user.cabinet.button.payment.pay" /></a>
						<a class="btn red accent-3 modal-close"><s:message code="user.cabinet.button.payment.cancel" /></a>
					</div>
				</div>
				<p class="primary-text brand-text"><i><s:message code="user.cabinet.contract.title" /></i></p>
				<ul class="collapsible" data-collapsible="accordion">
					<c:forEach items="${sessionScope.user.contracts}" var="el">
						<li>
							<div class="collapsible-header">
								<i class="material-icons">description</i>
								<s:message code="user.cabinet.contract" /> (${el.openDate} - ${el.closeDate})
							</div>
							<div class="collapsible-body">
								<table class="highlight">
									<tbody>
										<tr>
											<td><s:message code="user.cabinet.contract.vehicles" /></td>
											<td>
												<c:forEach items="${el.vehicles}" var="vehicle">
													<a onclick="viewContractVehicle('${vehicle.uri}')">
														${vehicle.brand.name} ${vehicle.model} (${vehicle.location.name})</a><br/>
												</c:forEach>
											</td>
										</tr>
									</tbody>
								</table>
								<div class="divider"></div><br/>
								<c:choose>
									<c:when test="${!el.paid}">
									<div id="payment-option">
										<a class="btn blue-grey lighten-2 right" onclick="openConfirmationModal(${el.totalPrice}, ${el.id})">
											<s:message code="user.cabinet.button.perform.payment" />
										</a>
										<p class="contract-total-price primary-text brand-text">
											<i><s:message code="user.cabinet.contract.label.price.total" />:</i> ${el.totalPrice} $
										</p>
									</div>
									<div id="hidden-msg">
										<p class="contract-total-price primary-text brand-text">
											<i><s:message code="user.cabinet.contract.message.paid" /></i>
										</p>
									</div>
									</c:when>
									<c:otherwise>
										<p class="contract-total-price primary-text brand-text">
											<i><s:message code="user.cabinet.contract.message.paid" /></i>
										</p>
									</c:otherwise>
								</c:choose>
							</div>
						</li>
					</c:forEach>
				</ul>
			</c:when>
			<c:otherwise>
				<s:message code="user.cabinet.contract.absent" />
			</c:otherwise>
		</c:choose>
	</div>
	<script src="${pageContext.request.contextPath}/resources/js/loading/viewCabinetLoadingScript.js"></script>
</body>