<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
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
	<div id="page-meta-info" data-tab-id="loginTab" data-tab-uri="/user/login"></div>
	<div class="container">
		<div class="row">
			<form id="loginForm" method="POST">
				<div class="card blue-grey lighten-1 login-form">
					<div class="card-content white-text">
						<div class="input-field login-form">
							<input id="username" name="auth_username" type="text" class="login-input"> <label
								for="username"><s:message code="login.label.username" /></label>
						</div>
						<div class="input-field login-form">
							<input id="password" name="auth_password" type="password" class="login-input">
							<label for="password"><s:message code="login.label.password" /></label>
						</div>
					</div>
					<a>
						<div class="card-action white hoverable" onclick="tryLogin()">
							<label class="login-button"><s:message code="login.button.submit" />
							<i class="material-icons">perm_identity</i></label>
						</div>
					</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>