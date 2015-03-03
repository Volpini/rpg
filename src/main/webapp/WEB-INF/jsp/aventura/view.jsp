<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="grid" tagdir="/WEB-INF/tags/grid" %>

<tiles:insertTemplate template="/WEB-INF/jsp/templates/main.jsp">
	<tiles:putAttribute name="title">
		<fmt:message key="${controller}.titulo.view" />
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
            <form>
		<jsp:include page="form.jsp" />
            </form>
            
            <grid:grid control="aventura" method="gridyByCampanha" template="aventura" params="{'idCampanha':'${campanha.id}'}"/>


	</tiles:putAttribute>
</tiles:insertTemplate>