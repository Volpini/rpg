<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@ taglib prefix="vld" tagdir="/WEB-INF/tags/validacao"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<vld:validacao/>
<input type="hidden" name="campanha.id" value="${campanha.id}"/>
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
                    <input type="text" name="campanha.name" value="${campanha.name}" ${disabled} />
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
                <textarea name="campanha.descricao" value="${campanha.descricao}" />
            </td>
            
        </tr>
    </tbody>
</table>
<form:botoesForm/>