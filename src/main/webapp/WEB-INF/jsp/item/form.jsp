<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@ taglib prefix="vld" tagdir="/WEB-INF/tags/validacao"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<vld:validacao/>
<input type="hidden" name="item.id" value="${item.id}"/>
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
					<input type="text" name="item.name" value="${item.name}" ${disabled} />
                </label>
            </td>
			<td></td>
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
                    <fmt:message key="${controller}.livro" />
					<form:select name="item.livro.id" options="${livros}" value="${item.livro.id}" disabled="${disabled}" />
                </label>
            </td>
			<td>
				<label>
					<fmt:message key="${controller}.pagina" />
					<input type="text" name="item.pagina" value="${item.pagina}" ${disabled} />
				</label>
			</td>
		</tr>
	</tbody>
</table>
<form:botoesForm/>