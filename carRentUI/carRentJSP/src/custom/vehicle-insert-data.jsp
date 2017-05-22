<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<c:choose>
	<c:when test="${not empty locations}">
		<select id="insert-location">
			<option disabled selected value=""><s:message code="search.location" /></option>
			<c:forEach items="${locations}" var="it">
				<option value="${it}">${it}</option>
			</c:forEach>
		</select>
	</c:when>
	<c:otherwise>
		<select id="insert-location">
			<option value=""><s:message code="search.location.select" /></option>
		</select>
	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${not empty cities}">
		<select id="insert-city">
			<option disabled selected value=""><s:message code="search.country.select" /></option>
			<c:forEach items="${cities}" var="it">
				<option value="${it}">${it}</option>
			</c:forEach>
		</select>
	</c:when>
	<c:otherwise>
		<select id="insert-city">
			<option value=""><s:message code="search.city.select" /></option>
		</select>
	</c:otherwise>
</c:choose>