<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<script>
	head.ready(function(){
		$("#grid").gridy({
			url : '<c:url value="/${controller}/gridy" />',
			columns : ${gridColunas},
			finds   : ${gridBusca},
			hoverFx 	 : true,
			evenOdd 	 : true,
			searchFocus	 : false,
			searchButtonLabel : "<fmt:message key='grid.botaoBusca' />",
			searchText 	 : "<fmt:message key='grid.placeholderBusca' />",
			noResultText : "<fmt:message key='grid.nenhumResultado' />",
			loadingText  : "<fmt:message key='grid.carregando' />",
			statusText   : "<fmt:message key='grid.status' />",
			buttonTitle  : "<fmt:message key='grid.botaoPaginaTexto' />",
			buttonNextTitle : "<fmt:message key='grid.botaoProximaPagina' />",
			buttonLastTitle : "<fmt:message key='grid.botaoUltimaPagina' />",
			buttonBackTitle : "<fmt:message key='grid.botaoPaginaAnterior' />",
			buttonFirstTitle : "<fmt:message key='grid.botaoPrimeiraPagina' />",
			buttonMax : 6
		});
		
		/* Gambiarra para não excluir nada do Sistema só por teclar enter no campo de pesquisa do Gridy */
		$("[name='search']").keydown(function(event){
			if(event.keyCode == 13){
				event.preventDefault();
				$(this).next().trigger("click");
			}
		});
		$(".formGrid select").addClass("noSelect2");
	});
	
	function confirmaExclusao(){
		if(confirm('<fmt:message key="notificacao.confirmar.excluir" />')){
			return true;
		}else{
			return false;
		}
	}
</script>
<style>
	#pagina{
		margin-top : -10px;
	}
</style>
<form action="${controller}/del" method="post" class="formGrid">
	<input type="hidden" name="_method" value="DELETE" />
	<div id="pageTitleGrid">
		<h2><tiles:insertAttribute name="title"/></h2>
	</div>
	<c:if test="${controller != 'expensesPlanning'}">
		<div id="addNovo">
			<a href="${controller}/add">
				<img src="img/icone_add.png" />
				<fmt:message key="${controller}.add" />
			</a>
		</div>
	</c:if>
	<table id="grid"></table>

	<script id="template" type="text/x-jquery-tmpl">
		<tr>
			<c:forEach items="${colunas}" var="coluna">
				<td>
					<a href='<c:url value="/${controller}/view/\${id}" />'>
						\${${coluna.value}}
					</a>
				</td>
			</c:forEach>
			<td>
				<a href='<c:url value="/${controller}/edit/\${id}" />' class="btn btn-warning"><fmt:message key="grid.editar" /></a>
				<button type='submit' value='\${id}' name="${controller}.id" class="btn btn-danger buttonDel" onclick="return confirmaExclusao();"><fmt:message key="grid.excluir" /></button>
			</td>
		</tr>
	</script>
</form>
