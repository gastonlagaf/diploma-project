<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:choose>
	<c:when test="${pageContext.request.isUserInRole('ROLE_ADMIN') || pageContext.request.isUserInRole('ROLE_USER')}">
		<jsp:include page="../forms/changePassword.jsp" />
	</c:when>
	<c:otherwise>
		<jsp:include page="../forms/registration.jsp" />
	</c:otherwise>
</c:choose>