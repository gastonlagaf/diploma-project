<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<div class="navbar fixed header-main">
		<nav class="nav-extended blue-grey lighten-1">
			<div class="nav-wrapper">
				<a class="brand-logo right primary-text"><i class="brand-text"><s:message code="header.title" /></i></a>
		     	<ul id="nav-mobile" class="left hide-on-med-and-down">
		        	<li>
		        		<a accesskey="q" class="primary-text" id="sidebar-spawner" data-activates="slide-out"><s:message code="header.menu" /></a>
		        	</li>
		      	</ul>
			</div>
			<div class="nav-content">
	      		<ul class="tabs tabs-transparent">
	        		<li class="tab navigation-view-btn header-nav-text"><a accesskey="1" id="vehicle-inset" href="#viewTab"><s:message code="header.tab.view" /></a></li>
	        		<li class="tab navigation-reply-btn header-nav-text"><a accesskey="2" id="reply-inset" href="#replyTab"><s:message code="header.tab.reply" /></a></li>
	        		<li class="tab navigation-about-btn header-nav-text"><a accesskey="3" id="about-inset" href="#aboutTab"><s:message code="header.tab.about" /></a></li>
	        		<li class="tab navigation-cabinet-btn header-nav-text"><a accesskey="4" id="cabinet-inset" href="#loginTab"><s:message code="header.tab.cabinet" /></a></li>
	      		</ul>
	    	</div>
		</nav>
	</div>
</body>
</html>