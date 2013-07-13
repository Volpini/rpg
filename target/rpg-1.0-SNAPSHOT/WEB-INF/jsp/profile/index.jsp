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
		<form action="<c:url value="/profile/edit" />" method="post">
			<input type="hidden" name="systemUser.id" value="${systemUser.id}"/>
			<input type="hidden" name="systemUser.password" value="${systemUser.password}"/>
			<input type="hidden" name="systemUser.userGroup.id" value="${systemUser.userGroup.id}"/>
			<input type="hidden" name="systemUser.costCenterPermission.id" value="${systemUser.costCenterPermission.id}"/>
			<input type="hidden" name="systemUser.email" value="${systemUser.email}"/>
			<table class="blocoForm">
				<tbody>
					<tr>
						<td class="tituloBloco" colspan="2">
							<fmt:message key="${controller}.subtitulo1"/>
						</td>
					</tr>
					<tr>
						<td>
							<label>
								<fmt:message key="${controller}.name"/>
								<input type="text" name="systemUser.name" value="${systemUser.name}"/>
							</label>
						</td>
						<td>
							<label>
								<fmt:message key="${controller}.email"/>
								<input type="text" name="systemUser.email" value="${systemUser.email}" disabled="disabled" />
							</label>
						</td>
					</tr>
					<tr>
						<td>
							<label>
								<a href="<c:url value="/profile/changePassword"/>"><fmt:message key="${controller}.changePassword"/></a>
							</label>
						</td>
						<td></td>
					</tr>
				</tbody>
			</table>
			<form:botoesForm/>		
		</form>
	</tiles:putAttribute>
</tiles:insertTemplate>