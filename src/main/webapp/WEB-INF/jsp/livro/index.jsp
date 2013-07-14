<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="vld" tagdir="/WEB-INF/tags/validacao/" %>
<%@ taglib prefix="grid" tagdir="/WEB-INF/tags/grid" %>

<tiles:insertTemplate template="/WEB-INF/jsp/templates/main.jsp">
	<tiles:putAttribute name="title">
		<fmt:message key="${controller}.titulo.index" />
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
		<vld:validacao/>
		<grid:grid/>
	</tiles:putAttribute>
</tiles:insertTemplate>