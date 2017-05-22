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
	<div id="page-meta-info" data-tab-id="viewTab"
		data-tab-uri="/vehicles/page/${page}"></div>
	<div class="row">
		<div class="col s3">
			<jsp:include page="../elements/searchCriteria.jsp" />
		</div>
		<div class="col s9 main-workspace" id="main-viewer">
			<div id="mainContentView" class="mainContentView">
				<!-- <c:forEach items="${vehicles}" var="el">
					<div class="col s12 vehicle-row-big z-depth-4"
						onclick="viewVehicle('${el.uri}')">
						<div class="col s2">
							<img class="vehicle-image"
								src="${pageContext.request.contextPath}${el.images[0].imagePath}" />
						</div>
						<div class="col s10">
							<label class="vehicle-title-small primary-text">${el.brand.name}
								${el.model} (${el.manufactureYear}) ${el.location.name}</label>
						</div>
					</div>
				</c:forEach> -->
				<c:forEach begin="0" end="${vehicles.size() / 3}" varStatus="loop">
					<div class="row">
						<c:forEach begin="${loop.index * 3}" end="${loop.index * 3 + 2}"
							varStatus="innerLoop">
							<c:if test="${vehicles[innerLoop.index] != null}">
								<div class="col s4">
									<div class="vehicle-card card white waves-effect" onclick="viewVehicle('${vehicles[innerLoop.index].uri}')">
										<div class="card-content">
											<label class="vehicle-text vehicle-title-card black-text">${vehicles[innerLoop.index].brand.name}<br/>${vehicles[innerLoop.index].model}</label>
											<img class="vehicle-image"
												src="${pageContext.request.contextPath}${vehicles[innerLoop.index].images[0].imagePath}" />
										</div>
										<div class="card-action blue-grey lighten-1">
											 <label class="vehicle-card-action vehicle-text white-text"><s:message code="vehicles.year" />: ${vehicles[innerLoop.index].manufactureYear}</label><br/>
											 <label class="vehicle-card-action vehicle-text white-text"><s:message code="vehicles.price" />: ${vehicles[innerLoop.index].price} <s:message code="vehicles.label.per.hour" /></label>
										</div>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</c:forEach>
				<c:if test="${pages != 0}">
					<ul class="pagination">
						<c:choose>
							<c:when test="${page == 1}">
								<li class="disabled"><a href="#!"><i
										class="material-icons">chevron_left</i></a></li>
							</c:when>
							<c:otherwise>
								<li class="waves-effect"><a
									onclick="viewVehicles(${page - 1})"><i class="material-icons">chevron_left</i></a></li>
							</c:otherwise>
						</c:choose>
						<c:forEach begin="1" end="${pages}"
							varStatus="loop">
							<c:choose>
								<c:when test="${page == loop.index}">
									<li class="active"><a>${loop.index}</a></li>
								</c:when>
								<c:otherwise>
									<li><a onclick="viewVehicles(${loop.index})">${loop.index}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:choose>
							<c:when test="${page == pages}">
								<li class="disabled"><a href="#!"><i
										class="material-icons">chevron_right</i></a></li>
							</c:when>
							<c:otherwise>
								<li class="waves-effect"><a
									onclick="viewVehicles(${page + 1})"><i class="material-icons">chevron_right</i></a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>