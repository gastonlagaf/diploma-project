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
	<div id="page-meta-info" data-tab-id="replyTab" data-tab-uri="/reply"></div>
	<!-- Success modal -->
	<div id="modal-success" class="modal">
		<div class="modal-content">
			<h4 class="primary-text"><s:message code="reply.message.success" /></h4>
		</div>
		<div class="modal-footer">
			<a href="#!" class="modal-action modal-close btn-flat"><s:message code="reply.button.ok" /></a>
		</div>
	</div>
	<!-- Form body -->
	<div class="container">
		<div class="row">
			<div class="replyForm col s12">
				<div class="row">
					<div class="input-field col s3">
						<input id="first_name" type="text"> <label
							for="first_name"><s:message code="reply.label.name" /></label>
					</div>
				</div>
				<div class="row">
					<div class="input-field col s12">
						<textarea id="reply" class="materialize-textarea" rows="15"></textarea>
						<label for="textarea1"><s:message code="reply.label.text" /></label>
					</div>
				</div>
				<div class="row">
					<button class="btn waves-effect waves-light blue-grey lighten-1 right"
						onclick="submitReply()">
						<s:message code="reply.button.submit" /> <i class="material-icons right">send</i>
					</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>