<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<div id="modal-order-form" class="modal"></div>
	<div class="fixed-action-btn">
		<a onclick="openContractModal()" class="btn-floating btn-large waves-effect amber darken-1 btn-basket pulse">
			<i class="large material-icons">shopping_cart</i>
		</a>
		<c:if test="${sessionScope.basket != null}">
			<script>spawnBasket()</script>
		</c:if>
	</div>
</body>
</html>