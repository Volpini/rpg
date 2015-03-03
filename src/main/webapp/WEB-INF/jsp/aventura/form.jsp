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
        <tr colspan="2">
            <td>
                <label>
                    <fmt:message key="${controller}.nome" />
                    <input type="text" name="aventura.nome" value="${aventura.nome}" ${disabled} />
                </label>
            </td>

        </tr>
        <tr colspan="2">
            <td>
                <label>
                    <fmt:message key="${controller}.descricao" />
                    <textarea name="aventura.descricao"  ${disabled}>${aventura.descricao}</textarea>
                </label>
            </td>

        </tr>
        <tr>
            <td>
                <label>
                    <fmt:message key="${controller}.personagens" />
                    <form:multipleSelect name="aventura.personagens[].id" options="${personagens}" value="${aventura.personagens}" disabled="${disabled}"/>
                </label>
            </td>
            <td>
                <label>
                    <fmt:message key="${controller}.campanha" />
                    <input type="text"  value="${campanha.nome}" disabled />
                </label>
            </td>
        </tr>
    </tbody>
</table>
<form:botoesForm/>


