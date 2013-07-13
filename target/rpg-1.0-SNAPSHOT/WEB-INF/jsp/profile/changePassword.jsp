<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="notificacoes" tagdir="/WEB-INF/tags/notificacao" %>
<%@ taglib prefix="grid" tagdir="/WEB-INF/tags/grid" %>
<%@ taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@ taglib prefix="vld" tagdir="/WEB-INF/tags/validacao/"%>

<tiles:insertTemplate template="/WEB-INF/jsp/templates/main.jsp">
	<tiles:putAttribute name="title">
		<fmt:message key="${controller}.titulo.index" />
	</tiles:putAttribute>
	<tiles:putAttribute name="body">
		<vld:validacao/>
		<form action="<c:url value="/profile/changePassword" />" method="post">
			<table class="blocoForm">
				<tr>
					<td class="tituloBloco" colspan="2">
						<fmt:message key="${controller}.subtitulo2"/>
					</td>
				</tr>
				<tr>
					<td>
						<label>
							<fmt:message key="${controller}.actualPassword"/>
							<input type="password" name="actualPassword" />
						</label>
					</td>
					<td>

					</td>
				</tr>
				<tr>
					<td>
						<label>
							<fmt:message key="${controller}.newPassword"/>
							<input type="password" name="newPassword" />
						</label>
					</td>
					<td>
						<label>
							<fmt:message key="${controller}.repeatNewPassword"/>
							<input type="password" name="repeatPassword" />
						</label>
					</td>
				</tr>
			</table>
			<form:botoesForm/>		
		</form>
	</tiles:putAttribute>
</tiles:insertTemplate>