<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<p id="token">${_csrf.token}</p>
<div id="cabinetTabBody">
	<c:choose>
		<c:when test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
			<jsp:include page="../admin/admin-cabinet.jsp" />
		</c:when>
		<c:when test="${pageContext.request.isUserInRole('ROLE_USER')}">
			<jsp:include page="../user/user-cabinet.jsp" />
		</c:when>
		<c:otherwise>
			<jsp:include page="../forms/login.jsp" />
		</c:otherwise>
	</c:choose>
</div>
<jsp:include page="../elements/sideNavigation.jsp" />
<div id="vehicle-options">
	<jsp:include page="../elements/vehicleOptions.jsp" />
</div>
<jsp:include page="../elements/userForms.jsp" />