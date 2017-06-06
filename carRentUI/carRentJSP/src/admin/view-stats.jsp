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
		data-tab-uri="/admin/statistics"></div>
	<div id="prev-invocation" data-invocation="viewUserCabinet()"></div>
	<div class="row">
		<div class="col s6">
			<canvas id="countries-chart"></canvas>
		</div>
		<div class="col s6">
			<br/><br/>
			<p class="primary-text cabinet-title">
				<i>
					<s:message code="stats.search.country" />
				</i>
			</p>
		</div>
	</div>
	<div class="row">
		<div class="col s6">
			<canvas id="brands-chart"></canvas>
		</div>
		<div class="col s6">
			<br/><br/>
			<p class="primary-text cabinet-title">
				<i>
					<s:message code="stats.search.brand" />
				</i>
			</p>
		</div>
	</div>
	<div class="row">
		<div class="col s6">
			<canvas id="body-types-chart"></canvas>
		</div>
		<div class="col s6">
			<br/><br/>
			<p class="primary-text cabinet-title">
				<i>
					<s:message code="stats.search.body.type" />
				</i>
			</p>
		</div>
	</div>
	<div class="row">
		<div class="col s6">
			<canvas id="ordered-countries-chart"></canvas>
		</div>
		<div class="col s6">
			<br/>
			<p class="primary-text cabinet-title">
				<i>
					<s:message code="stats.contract.orders" />
				</i>
			</p>
		</div>
	</div>
	<div class="row">
		<div class="col s6">
			<canvas id="ordered-body-types-chart"></canvas>
		</div>
		<div class="col s6">
			<canvas id="ordered-brand-chart"></canvas>
		</div>
	</div>
	<script>
		var stats = {
			"countries": ${viewStats['countries']},
			"brands": ${viewStats['brands']},
			"bodyType": ${viewStats['bodyTypes']},
			"orderedBodyType": ${viewStats['orderedBodyTypes']},
			"orderedBrands": ${viewStats['orderedBrands']},
			"orderedCountries": ${viewStats['orderedCountries']}
		} 
		initCharts()
	</script>
</body>