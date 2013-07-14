<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@ taglib prefix="vld" tagdir="/WEB-INF/tags/validacao"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<vld:validacao/>
<input type="hidden" name="systemUser.id" value="${systemUser.id}" />
<input type="hidden" name="systemUser.password" value="${systemUser.password}" />
<table class="blocoForm">
	<tbody>
		<tr>
			<th class="tituloBloco" colspan="2">
				<label>
					<fmt:message key="${controller}.subtitulo1" />
				</label>
			</th>
		</tr>
		<tr>
			<td>
				<label>
					<fmt:message key="${controller}.email" />
					<input type="text" name="systemUser.email" value="${systemUser.email}" ${disabled}/>
				</label>
			</td>
			<td>
				<label>
					<fmt:message key="${controller}.nome" />
					<input type="text" name="systemUser.name" id="user_name" value="${systemUser.name}" ${disabled} />
				</label>
			</td>
		</tr>
	</tbody>
</table>
<table class="blocoForm">
	<tbody>
		<tr>
			<th class="tituloBloco" colspan="2">
				<label>
					<fmt:message key="${controller}.subtitulo2" />
				</label>
			</th>
		</tr>
		<tr>
			<td>
				<label>
					<fmt:message key="${controller}.group" />
					<form:select options="${groups}" name="systemUser.userGroup.id" value="${systemUser.userGroup.id}" />
				</label>
			</td>
		</tr>
	</tbody>
</table>
<form:botoesForm/>