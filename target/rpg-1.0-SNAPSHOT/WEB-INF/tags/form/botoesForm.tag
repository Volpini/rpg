<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="botoesForm">
	<c:choose>
		<c:when test="${viewType.isView}" >
			<a href="<c:url value="/${controller}" />" class='btn btn-primary btnForm'><fmt:message key="botao.voltar"/></a>
		</c:when>
		<c:when test="${viewType.isAdd}" >
			<button type="submit" class='btn btn-success btnForm'><fmt:message key="botao.cadastrar" /></button>
			<a href="<c:url value="/${controller}" />" class='btn btn-primary btnForm'><fmt:message key="botao.voltar"/></a>
		</c:when>
		<c:when test="${viewType.isEdit}" >
			<button type="submit" class='btn btn-success btnForm'><fmt:message key="botao.alterar" /></button>
			<a href="<c:url value="/${controller}" />" class='btn btn-primary btnForm'><fmt:message key="botao.voltar"/></a>
		</c:when>
		<c:otherwise>
			<button type="submit" class='btn btn-success btnForm'><fmt:message key="botao.alterar" /></button>
			<a href="<c:url value="/${controller}" />" class='btn btn-primary btnForm'><fmt:message key="botao.voltar"/></a>
		</c:otherwise>
	</c:choose>
</div>