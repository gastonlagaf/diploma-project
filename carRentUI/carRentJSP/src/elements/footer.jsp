<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<footer class="page-footer blue-grey lighten-1">
		<div class="container">
			<div class="row">
				<div class="col s6">
					<h5 class="white-text primary-text"><s:message code="footer.title" /></h5>
				</div>
				<div class="col l6 s6">
					<h5 class="white-text primary-text"><i><s:message code="footer.motto" /></i></h5>
				</div>
			</div>
		</div>
		<div class="footer-copyright">
			<div class="container">
				© 2017 <i class="white-text">youJustDontKnow Ltd.</i> <a
					class="grey-text text-lighten-4 right" target="_blank" href="https://github.com/gastonlagaf/diploma-project"><s:message code="footer.contribute" /></a>
			</div>
		</div>
	</footer>
</body>
</html>