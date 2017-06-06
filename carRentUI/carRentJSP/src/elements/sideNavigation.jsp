<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<div id="modal-funds" class="modal">
		<div class="modal-content">
			<p class="primary-text cabinet-title">
				<i><s:message code="sidenav.funds.modal.title" /></i>
			</p>
			<div class="input-field">
				<input id="funds-amount" type="number" min="0"> 
				<label for="funds-amount"><s:message code="sidenav.funds.modal.input" /></label>
			</div>
		</div>
		<div class="modal-footer">
			<a onclick="addFunds()"	class="waves-effect waves-light btn green accent-3"><s:message code="sidenav.funds.modal.button" /></a>
		</div>
	</div>
	<ul id="slide-out" class="side-nav">
		<li>
			<div class="userView">
				<div class="background">
					<img
						src="${pageContext.request.contextPath}/resources/images/profile-background.jpg">
				</div>
				<c:if test="${sessionScope.user != null}">
					<span class="white-text name">${sessionScope.user.surname} ${sessionScope.user.name}</span>
					<span class="white-text email">${sessionScope.user.email}</span>
				</c:if>
			</div>
		</li>
		<c:choose>
			<c:when test="${sessionScope.user != null}">
				<c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
					<li><a onclick="navBarOpenContracts()" href="#" class="waves-effect"><i class="material-icons">class</i><s:message code="sidenav.contracts" /></a></li>
				</c:if>
				<li><a onclick="openUserModal()" class="waves-effect"><i class="material-icons">lock</i><s:message code="sidenav.password.change" /></a></li>
				<li><a onclick="openAddFundsModal()" class="waves-effect"><i class="material-icons">credit_card</i><s:message code="sidenav.funds" /></a></li>
				<li><div class="divider"></div></li>
				<li><a class="waves-effect" onclick="logoutUser()"><i class="material-icons">recent_actors</i><s:message code="sidenav.logout" /></a></li>
			</c:when>
			<c:otherwise>
				<li><a onclick="openCabinetTab()" class="waves-effect" id="invoiceViewListener"><i class="material-icons">perm_identity</i><s:message code="sidenav.sign.in" /></a></li>
				<li><a onclick="openUserModal()" class="waves-effect" id="invoiceViewListener"><i class="material-icons">recent_actors</i><s:message code="sidenav.register" /></a></li>
			</c:otherwise>
		</c:choose>
		<li><div class="divider"></div></li>		
		<li><p class="primary-text locale-selection-title"><i><s:message code="sidenav.locale.title" /></i></p><li>
		<li><div class="divider"></div></li>
		<li><a href="#" data-value="en" onclick="changeLocale(this)" class="locale-option blue-grey lighten-1 waves-effect">English</a></li>
		<li><a href="#" data-value="ru" onclick="changeLocale(this)" class="locale-option blue-grey darken-1 waves-effect">Русский</a></li>
	</ul>
</body>
</html>