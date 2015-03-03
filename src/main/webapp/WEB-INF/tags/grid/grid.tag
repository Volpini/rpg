<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ attribute required="false" name="method" type="java.lang.String" %>
<%@ attribute required="false" name="control" type="java.lang.String" %>
<%@ attribute required="false" name="params" type="java.lang.String" %>
<%@ attribute required="false" name="template" type="java.lang.String" %>
<%@ attribute required="false" name="addUrl" type="java.lang.String" %>

<c:if test="${empty control}" >
    <c:set var="control" value="${controller}" />
</c:if>
<c:if test="${empty addUrl}" >
    <c:set var="addUrl" value="${control}/add" />
</c:if>
<c:if test="${empty template}" >
    <c:set var="template" value="template" />
</c:if>
<c:if test="${empty method}" >
    <c:set var="method" value="gridy" />
</c:if>
<c:if test="${empty params}" >
    <c:set var="params" value="{}" />
</c:if>

<script>
    head.ready(function() {
        $("#grid").gridy({
            url: '<c:url value="/${control}/${method}" />',
            columns: ${gridColunas},
            finds: ${gridBusca},
            params: ${params},
            hoverFx: true,
            evenOdd: true,
            searchFocus: false,
            searchButtonLabel: "<fmt:message key='grid.botaoBusca' />",
            searchText: "<fmt:message key='grid.placeholderBusca' />",
            noResultText: "<fmt:message key='grid.nenhumResultado' />",
            loadingText: "<fmt:message key='grid.carregando' />",
            statusText: "<fmt:message key='grid.status' />",
            buttonTitle: "<fmt:message key='grid.botaoPaginaTexto' />",
            buttonNextTitle: "<fmt:message key='grid.botaoProximaPagina' />",
            buttonLastTitle: "<fmt:message key='grid.botaoUltimaPagina' />",
            buttonBackTitle: "<fmt:message key='grid.botaoPaginaAnterior' />",
            buttonFirstTitle: "<fmt:message key='grid.botaoPrimeiraPagina' />",
            buttonMax: 6,
            template: '${template}',
            done: function(){
                $('.hideBtnGrid').hide();
                $('.showBtnGrid').show();
                $('.hideAddDiv').hide();
            }
        });

        /* Gambiarra para não excluir nada do Sistema só por teclar enter no campo de pesquisa do Gridy */
        $("[name='search']").keydown(function(event) {
            if (event.keyCode == 13) {
                event.preventDefault();
                $(this).next().trigger("click");
            }
        });
        $(".formGrid select").addClass("noSelect2");
    });

    function confirmaExclusao() {
        if (confirm('<fmt:message key="notificacao.confirmar.excluir" />')) {
            return true;
        } else {
            return false;
        }
    }
</script>
<style>
    #pagina{
        margin-top : -10px;
    }
</style>
<form action="${control}/del" method="post" class="formGrid">
    <input type="hidden" name="_method" value="DELETE" />
    <c:if test="${viewType.isGrid}">
        <div id="pageTitleGrid">

                <h2><tiles:insertAttribute name="title"/></h2>

        </div>
    </c:if>
  
    <c:if test="${addDiv}">
        <div id="addNovo">
            <a href="<c:url value='${addUrl}'/>" class="gridNotRemove">
                <img src="<c:url value='/img/icone_add.png'/>" />
                <fmt:message key="${control}.add" />
            </a>
        </div>
    </c:if>
    <table id="grid"></table>

    <script id="${template}" type="text/x-jquery-tmpl">
		<tr>
        <c:forEach items="${colunas}" var="coluna">
				<td class="${dinamicClass}">
					<a href='<c:url value="/${control}/view/\${id}"  />'>
						\${${coluna.value}}
					</a>
				</td>
        </c:forEach>
			<td>

                <a href='<c:url value="/${control}/edit/\${id}" />' class="btn btn-warning \${classEdit}"><fmt:message key="grid.editar" /></a>
                <button type='submit' value='\${id}' name="${control}.id" class="btn btn-danger buttonDel \${classDelete}" onclick="return confirmaExclusao();"><fmt:message key="grid.excluir" /></button>

			</td>
		</tr>
    </script>
</form>
