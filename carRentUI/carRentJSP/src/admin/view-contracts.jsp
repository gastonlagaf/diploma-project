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
	<div id="page-meta-info" data-tab-id="loginTab" data-tab-uri="/admin/contracts/page/${page}"></div>
	<div id="prev-invocation" data-invocation="viewUserCabinet()"></div>
	<div id="confirmation-modal" class="modal modal-fixed-footer">
		<div class="modal-content">
			<p class="primary-text brand-text"><i><s:message code="admin.contract.shutdown.confirm" /></i></p>
		</div>
		<div class="modal-footer">
			<a id="shutdown-btn" class="btn green accent-3" data-contract-id="" onclick="performShutdown(this)"><s:message code="admin.contract.shutdown.yes" /></a>
			<a class="btn red accent-3 modal-close"><s:message code="admin.contract.shutdown.no" /></a>
		</div>
	</div>
	<div class="container">
		<ul class="collapsible" data-collapsible="accordion">
			<c:forEach items="${contracts}" var="el">
				<li>
					<div class="collapsible-header">
						<i class="material-icons">description</i> <s:message code="admin.contract.element.title" /> (${el.openDate} -
						${el.closeDate}) 
						<c:if test="${el.closed}">
							<b><s:message code="admin.contract.label.closed" /></b>
						</c:if>
					</div>
					<div class="collapsible-body">
						<table class="highlight">
							<tbody>
								<tr>
									<td><s:message code="admin.contract.customer.name" /></td>
									<td>${el.user.surname} ${el.user.name} ${el.user.patronymic}</td>
								</tr>
								<tr>
									<td><s:message code="admin.contract.customer.email" /></td>
									<td>${el.user.email}</td>
								</tr>
								<tr>
									<td><s:message code="admin.contract.customer.number" /></td>
									<td>${el.user.contactNumber}</td>
								</tr>
								<tr>
									<td><s:message code="admin.contract.customer.vehicles" /></td>
									<td><c:forEach items="${el.vehicles}" var="vehicle">
											<a onclick="viewContractVehicle('${vehicle.uri}')">
												${vehicle.brand.name} ${vehicle.model}
												(${vehicle.location.name})</a>
										</c:forEach></td>
								</tr>
							</tbody>
						</table>
						<div class="divider"></div>
						<br />
						<c:choose>
							<c:when test="${!el.closed}">
								<div id="contract-view-footer">
									<a class="btn blue-grey lighten-1 right" onclick="openShutdownConfirmationModal(${el.id})"><s:message code="admin.contract.button.shutdown" /></a>
									<c:choose>
										<c:when test="${!el.paid}">
											<p class="contract-total-price primary-text brand-text">
												<i><s:message code="admin.contract.label.await.payment" /></i>
											</p>
										</c:when>
										<c:otherwise>
											<p class="contract-total-price primary-text brand-text">
												<i><s:message code="admin.contract.label.paid.payment" /></i>
											</p>
										</c:otherwise>
									</c:choose>
								</div>
								<div id="hidden-msg">
									<p class="contract-total-price primary-text brand-text">
										<i><s:message code="admin.contract.label.closed" /></i>
									</p>
								</div>
							</c:when>
							<c:otherwise>
								<p class="contract-total-price primary-text brand-text">
									<i><s:message code="admin.contract.label.closed" /></i>
								</p>
							</c:otherwise>
						</c:choose>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
	<script src="${pageContext.request.contextPath}/resources/js/loading/viewCabinetLoadingScript.js"></script>
</body>