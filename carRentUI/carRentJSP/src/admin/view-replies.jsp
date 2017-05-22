<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<head>
<script
	src="${pageContext.request.contextPath}/resources/js/initScript.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/loading/loadingScripts.js"></script>
</head>
<body>
	<div id="page-meta-info" data-tab-id="loginTab"
		data-tab-uri="/admin/replies/page/${page}"></div>
	<div id="prev-invocation" data-invocation="viewUserCabinet()"></div>
	<div class="container">
		<c:forEach items="${viewResponse.replies}" var="el">
			<div class="row" id="reply-${el.id}">
				<div class="col s12">
					<div class="card grey">
						<div class="card-content white-text">
							<div class="chip">${el.authorName}</div>
							<p>${el.text}</p>
						</div>
						<div class="card-action white">
							<a class="red-text" onclick="removeReply(${el.id})"><s:message code="admin.replies.button.delete" /></a>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
		<c:if test="${viewResponse.totalPages != 0}">
			<ul class="pagination">
				<c:choose>
					<c:when test="${page == 1}">
						<li class="disabled"><a href="#!"><i
								class="material-icons">chevron_left</i></a></li>
					</c:when>
					<c:otherwise>
						<li class="waves-effect"><a
							onclick="viewReplies(${page - 1})"><i class="material-icons">chevron_left</i></a></li>
					</c:otherwise>
				</c:choose>
				<c:forEach begin="1" end="${viewResponse.totalPages}"
					varStatus="loop">
					<c:choose>
						<c:when test="${page == loop.index}">
							<li class="active"><a>${loop.index}</a></li>
						</c:when>
						<c:otherwise>
							<li><a onclick="viewReplies(${loop.index})">${loop.index}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${page == viewResponse.totalPages}">
						<li class="disabled"><a href="#!"><i
								class="material-icons">chevron_right</i></a></li>
					</c:when>
					<c:otherwise>
						<li class="waves-effect"><a
							onclick="viewReplies(${page + 1})"><i class="material-icons">chevron_right</i></a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</c:if>
	</div>
</body>