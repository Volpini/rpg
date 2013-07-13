<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="notificacoes" tagdir="/WEB-INF/tags/notificacao" %>
<%@ taglib prefix="grid" tagdir="/WEB-INF/tags/grid" %>

<tiles:insertTemplate template="/WEB-INF/jsp/templates/main.jsp">
	<tiles:putAttribute name="title">
		<fmt:message key="error.error404" />
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
	</tiles:putAttribute>
</tiles:insertTemplate>