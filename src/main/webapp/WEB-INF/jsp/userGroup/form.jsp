<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@ taglib prefix="vld" tagdir="/WEB-INF/tags/validacao"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<vld:validacao/>
<style>
	.tabelaReport{
		width : 100%;
		margin-top : -5px;
	}
	.tabelaReport tbody tr td{
		padding : 5px !important;
	}
	.tabelaReport tbody tr td:first-child{
		text-align : left;
	}
	.btn-slct{
		background : lightgrey;
		margin-left : 10px;
	}
</style>
<script>
	function selectAll(indiceElemento){
		if(indiceElemento != null){
			$(".controller_"+indiceElemento+" input[type='checkbox']").attr("checked", true);
		}else{
			$("input[type='checkbox']").attr("checked", true);
		}
	}
	function deselectAll(indiceElemento){
		if(indiceElemento != null){
			$(".controller_"+indiceElemento+" input[type='checkbox']").attr("checked", false);
		}else{
			$("input[type='checkbox']").attr("checked", false);
		}
	}
</script>
<input type="hidden" name="userGroup.id" value="${userGroup.id}"/>
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
                    <fmt:message key="${controller}.nome" />
					<input type="text" name="userGroup.name" value="${userGroup.name}" ${disabled} />
                </label>
            </td>
            <td>
                <label>
                    <fmt:message key="${controller}.description" />
					<input type="text" name="userGroup.description" value="${userGroup.description}" ${disabled} />
                </label>
            </td>
        </tr>
    </tbody>
</table>
<table class="blocoForm">
    <tbody>
        <tr>
            <th class="tituloBloco" colspan="2">
				<fmt:message key="${controller}.subtitulo2" />
				<button type="button" class="btn btn-slct" onclick="selectAll();"><fmt:message key="${controller}.selectAll1" /></button>
				<button type="button" class="btn btn-slct" onclick="deselectAll();"><fmt:message key="${controller}.deselectAll1" /></button>
            </th>
        </tr>
		<c:set var="indice" value="0" />
		<c:forEach items="${controllerGroups}" var="controllerGroup">
			<tr>
				<td colspan="2" style="padding : 0 15px 0 15px">
					<table class="blocoForm">
						<tbody>
							<tr>
								<th class="tituloBloco" colspan="2">
									${controllerGroup.name}
									<button type="button" class="btn btn-slct" onclick="selectAll(${controllerGroup.id});"><fmt:message key="${controller}.selectAll2" /></button>
									<button type="button" class="btn btn-slct" onclick="deselectAll(${controllerGroup.id});"><fmt:message key="${controller}.deselectAll2" /></button>
								</th>
							</tr>
							<tr>
								<td colspan="2">
									<table class="tabelaReport controller_${controllerGroup.id}">
										<thead>
											<tr>
												<th><fmt:message key="${controller}.controlador" /></th>
												<th><fmt:message key="${controller}.adicionar" /></th>
												<th><fmt:message key="${controller}.visualizar" /></th>
												<th><fmt:message key="${controller}.editar" /></th>
												<th><fmt:message key="${controller}.deletar" /></th>
												<th><fmt:message key="${controller}.grid" /></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${controllerGroup.controllers}" var="controllerC">
												<c:set value="true" var="controleExibicao" />
												<c:forEach items="${userGroup.permissions}" var="permission">
													<c:choose>
														<c:when test="${permission.cont.id == controllerC.id}">
															<tr>
																<td style="width : 300px !important;">
																	<fmt:message key="${controllerC.label}" />
																	<input type="hidden" name="userGroup.permissions[${indice}].id" value="${permission.id}" />
																	<input type="hidden" name="userGroup.permissions[${indice}].cont.id" value="${controllerC.id}" />
																</td>
																<td>
																	<c:choose>
																		<c:when test="${permission.pAdd}">										
																			<input type="checkbox" name="userGroup.permissions[${indice}].pAdd" checked="checked" ${disabled} />
																		</c:when>
																		<c:otherwise>
																			<input type="checkbox" name="userGroup.permissions[${indice}].pAdd" ${disabled} />
																		</c:otherwise>
																	</c:choose>
																</td>
																<td>
																	<c:choose>
																		<c:when test="${permission.pView}">										
																			<input type="checkbox" name="userGroup.permissions[${indice}].pView" checked="checked" ${disabled} />
																		</c:when>
																		<c:otherwise>
																			<input type="checkbox" name="userGroup.permissions[${indice}].pView" ${disabled} />
																		</c:otherwise>
																	</c:choose>
																</td>
																<td>
																	<c:choose>
																		<c:when test="${permission.pEdit}">
																			<input type="checkbox" name="userGroup.permissions[${indice}].pEdit" checked="checked" ${disabled} />
																		</c:when>
																		<c:otherwise>
																			<input type="checkbox" name="userGroup.permissions[${indice}].pEdit" ${disabled} />
																		</c:otherwise>
																	</c:choose>
																</td>
																<td>
																	<c:choose>
																		<c:when test="${permission.pDel}">
																			<input type="checkbox" name="userGroup.permissions[${indice}].pDel" checked="checked" ${disabled} />
																		</c:when>
																		<c:otherwise>
																			<input type="checkbox" name="userGroup.permissions[${indice}].pDel" ${disabled} />
																		</c:otherwise>
																	</c:choose>
																</td>
																<td>
																	<c:choose>
																		<c:when test="${permission.pGrid}">
																			<input type="checkbox" name="userGroup.permissions[${indice}].pGrid" checked="checked" ${disabled} />
																		</c:when>
																		<c:otherwise>
																			<input type="checkbox" name="userGroup.permissions[${indice}].pGrid" ${disabled} />
																		</c:otherwise>
																	</c:choose>
																</td>
															</tr>
														</c:when>
														<c:otherwise>
															<c:forEach items="${userGroup.permissions}" var="p">
																<c:if test="${p.cont.id == controllerC.id}">
																	<c:set value="false" var="controleExibicao" />
																</c:if>
															</c:forEach>
														</c:otherwise>
													</c:choose>
												</c:forEach>
												<c:if test="${controleExibicao}">
													<tr>
														<td style="width : 300px !important;">
															<fmt:message key="${controllerC.label}" />
															<input type="hidden" name="userGroup.permissions[${indice}].id" value="${permission.id}" />
															<input type="hidden" name="userGroup.permissions[${indice}].cont.id" value="${controllerC.id}" />
														</td>
														<td><input type="checkbox" name="userGroup.permissions[${indice}].pAdd" ${disabled} /></td>
														<td><input type="checkbox" name="userGroup.permissions[${indice}].pView" ${disabled} /></td>
														<td><input type="checkbox" name="userGroup.permissions[${indice}].pEdit" ${disabled} /></td>
														<td><input type="checkbox" name="userGroup.permissions[${indice}].pDel" ${disabled} /></td>
														<td><input type="checkbox" name="userGroup.permissions[${indice}].pGrid" ${disabled} /></td>
													</tr>
												</c:if>
												<c:set var="indice" value="${indice + 1}" />
											</c:forEach>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<form:botoesForm/>
<script>
	head(function(){
		$(".tabelaReport tbody tr:odd").addClass("stripedRow");
		$(".tabelaReport tbody tr").each(function(){
			$(this).hover(function(){
				$(this).toggleClass("hoverRow", 50);
			});
		});		
	});
</script>
