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
                    <input type="text" name="campanha.nome" value="${campanha.nome}" ${disabled} />
                </label>
            </td>
            <td>
                <label>
                    <fmt:message key="${controller}.dataInicio" />
                    <form:datepicker type="text" id="dataInicio" name="campanha.dataInicio" value="${dataInicio}" disabled="${disabled}"/>
                </label>
            </td>
        </tr>
        <tr colspan="2">
            <td>
                <label>
                    <fmt:message key="${controller}.descricao" />
                    <textarea name="campanha.descricao"  ${disabled}>${campanha.descricao}</textarea>
                </label>
            </td>
            <td>
                <label>
                    <fmt:message key="${controller}.dataCadastro" />
                    <form:datepicker type="text" id="dataCadastro" name="campanha.dataCadastro" value="${dataCadastro}" disabled="true" />
                </label>
            </td>
        </tr>
        <tr>
            <td>
                <label>
                    <fmt:message key="${controller}.personagens" />
                    <form:multipleSelect name="campanha.personagens[].id" options="${personagens}" value="${campanha.personagens}" disabled="${disabled}"/>
                </label>
            </td>
            <td>
                <label>
                    <fmt:message key="${controller}.mestre" />
                    <input type="text"  value="${campanha.mestre.name}" disabled />
                    <input type="hidden" name="campanha.mestre.id" value="${campanha.mestre.id}"/>
                </label>
            </td>
        </tr>
    </tbody>
</table>
<form:botoesForm/>


