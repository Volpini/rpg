<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@ taglib prefix="vld" tagdir="/WEB-INF/tags/validacao"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<vld:validacao/>
<input type="hidden" name="personagem.id" value="${personagem.id}"/>
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
                    <fmt:message key="${controller}.name" />
					<input type="text" name="personagem.name" value="${personagem.name}" ${disabled} />
                </label>
            </td>
            <td>
                <label>
                    <fmt:message key="${controller}.systemUser" />
					<form:select name="personagem.systemUser.id" options="${systemUsers}" value="${personagem.systemUser.id}" disabled="${disabled}" />
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
			<td colspan="2">
				<label>
					<fmt:message key="${controller}.talento" />
					<form:multipleSelect name="personagem.talentos[].id" options="${talentos}" value="${personagem.talentos}" disabled="${disabled}"/>
				</label>
			</td>
		</tr>
	</tbody>
</table>
<form:botoesForm/>