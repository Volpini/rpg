<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" tagdir="/WEB-INF/tags/validacao/" %>
<!DOCTYPE html>
<html>
	<head>
		<title><fmt:message key="login.titulo" /> - RPG</title>
		<link rel="shortcut icon" href="<c:url value='/img/favicon.ico' />" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="<c:url value='/css/jquery-layout.css' />" />
		<link rel="stylesheet/less" type="text/css" href="<c:url value='/css/login.css' />" />
		<link rel="stylesheet/less" type="text/css" href="<c:url value='/css/validationPopups.css' />" />
		<link rel="stylesheet" type="text/css" href="<c:url value='/css/bootstrap.css'/>" />
		<script src="<c:url value='/js/head.js' />" type="text/javascript"></script>
	</head>
	<body style="display:none">
		<div class="ui-layout-north">
			<span id="logoTopo"><a href="" onclick="javascript:void(0);"><img src="<c:url value='/img/dnd_logo.png' />"></a></span>
		</div>
		<div class="ui-layout-center">
			<article id="login">
				<section>
					<form:validacao />
					<div id="form-login">
						<tiles:insertAttribute name="body"/>
					</div>
				</section>
			</article>
		</div>
		<script type="text/javascript">
			head.js("<c:url value='/js/jquery.js'/>", "<c:url value='/js/jquery-layout.js'/>");
			head.js("<c:url value='/js/less.js'/>", "<c:url value='/js/jquery-tmpl.js'/>");
			head(function() {
				$("body").css("display", "block");
				$("body").layout({
					resizable   : false,
					closable    : false,
					north__size : 130
				});
			});
		</script>
	</body>
</html>