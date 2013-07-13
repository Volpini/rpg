<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<tiles:insertTemplate template="/WEB-INF/jsp/templates/main.jsp">
	<tiles:putAttribute name="title">Bem vindo</tiles:putAttribute>
	<tiles:putAttribute name="body">Controle de itens</tiles:putAttribute>
	<tiles:putAttribute name="footer"></tiles:putAttribute>
</tiles:insertTemplate>