<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<tiles:insertTemplate template="/WEB-INF/jsp/templates/main.jsp">
	<tiles:putAttribute name="title">
		<fmt:message key="${controller}.titulo.add" />
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
		<form action="<c:url value='/${controller}/add'/>" method="post">
			<jsp:include page="form.jsp" />
		</form>
	</tiles:putAttribute>
</tiles:insertTemplate>