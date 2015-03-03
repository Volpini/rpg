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
            <th class="tituloBloco">
                <label>
                    <fmt:message key="${controller}.habilidades" />
                </label>

            </th>
        </tr>
        <tr>
            <td>
                <c:forEach items="${personagem.habilidades}" var="pHabilidade" varStatus="i">
                    <label>
                        <c:out value="${pHabilidade.habilidade.nome}"/>
                        <input type="hidden" name="personagem.habilidades[].habilidade.id" value="${pHabilidade.habilidade.id}"/>
                        <input type="hidden" name="personagem.habilidades[].personagem.id" value="${personagem.id}"/>
                        <input type="text" size="2" max="2" width="10px" name="personagem.habilidades[].valor" value="${pHabilidade.valor}" ${disabled}/>
                    </label>
                </c:forEach>
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
<table class="blocoForm">
    <tbody>
        <tr>
            <th class="tituloBloco" colspan="2">
                <label>
                    <fmt:message key="${controller}.subtitulo3" />
                </label>
                <button type="button" class="btn btn-success" id="addNewItem"><fmt:message key="${controller}.novoItem" /></button>
            </th>
        </tr>
        <tr>
            <td colspan="2">
                <table class="tabelaItens" id="listaItens">
                    <tbody>
                        <c:forEach items="${personagem.itensPersonagem}" var="itemPersonagem">
                            <tr>
                                <td>
                                    <label>
                                        <fmt:message key="${controller}.item" />
                                        <form:select name="personagem.itensPersonagem[].item.id" options="${itens}" value="${itemPersonagem.item.id}" />
                                    </label>
                                </td>
                                <td>
                                    <label>
                                        <fmt:message key="${controller}.quantidade" />
                                        <input type="text" name="personagem.itensPersonagem[].quantidade" value="${itemPersonagem.quantidade}" />
                                    </label>
                                    <span>
                                        <button type="button" class="btn btn-danger" onclick="removeLinha(this);">X</button>
                                    </span>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </td>
        </tr>
    <script id="modeloItemPersonagem" type="template">
        <tr>
        <td>
        <label>
        <fmt:message key="${controller}.item" />
        <form:select name="personagem.itensPersonagem[].item.id" options="${itens}" clazz="noSelect2" />
        </label>
        </td>
        <td>
        <label>
        <fmt:message key="${controller}.quantidade" />
        <input type="text" name="personagem.itensPersonagem[].quantidade" />
        </label>
        <span>
        <button type="button" class="btn btn-danger" onclick="removeLinha(this);">X</button>
        </span>
        </td>
        </tr>
    </script>
</tbody>
<script>
    head(function(){
    $("#addNewItem").click(function(){
    var modelo = $("#modeloItemPersonagem").html();
    $("#listaItens tbody").append(modelo);
    $("#listaItens tbody select:last").removeClass("noSelect2").select2();
    });
    });
</script>
</table>
<form:botoesForm/>