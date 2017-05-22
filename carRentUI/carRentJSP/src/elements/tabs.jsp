<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<div class="col s12" id="viewTab"></div>
<div class="central-view col s12" id="replyTab">
	<jsp:include page="../forms/replyForm.jsp" />
</div>
<div class="central-view col s12" id="aboutTab">
	<p id="tab-uri" hidden>/about</p>
	<jsp:include page="../general/about.jsp" />
</div>
<div class="central-view col s12" id="loginTab">
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