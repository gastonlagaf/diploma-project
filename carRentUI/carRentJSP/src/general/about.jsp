<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="${pageContext.request.contextPath}/resources/js/initScript.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/loading/loadingScripts.js"></script>
</head>
<body>
	<div id="page-meta-info" data-tab-id="aboutTab" data-tab-uri="/about"></div>
	<div class="container">
		<div class="row">
			<div class="col s4">
				<img class="center author-img" src="${pageContext.request.contextPath}/resources/images/author.jpg"></img>
			</div>
			<div class="col s8">
				<p class="about-title primary-text "><s:message code="about.title" /></p>
				<p class="about-text"><s:message code="about.text" /></p>
				<p class="left about-text"><s:message code="about.author" /></p>
			</div>
		</div>
	</div>
</body>
</html>