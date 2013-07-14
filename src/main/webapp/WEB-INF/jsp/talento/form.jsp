<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@ taglib prefix="vld" tagdir="/WEB-INF/tags/validacao"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<vld:validacao/>
<input type="hidden" name="talento.id" value="${talento.id}"/>
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
					<input type="text" name="talento.name" value="${talento.name}" ${disabled} />
                </label>
            </td>
			<td></td>
        </tr>
		<tr>
			<td colspan="2">
                <label>
                    <fmt:message key="${controller}.descricao" />
					<textarea name="talento.descricao">${talento.descricao}</textarea>
                </label>
            </td>
		</tr>
    </tbody>
</table>
<form:botoesForm/>