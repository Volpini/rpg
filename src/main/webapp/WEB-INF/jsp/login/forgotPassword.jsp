<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@ taglib prefix="form" tagdir="/WEB-INF/tags/validacao/"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<tiles:insertTemplate template="/WEB-INF/jsp/templates/login.jsp">
    <tiles:putAttribute name="body">
		<form action="<c:url value="/forgotPassword" />" method="post">
			<table>
				<tbody>
					<tr>
						<td>
							<label>
								<fmt:message key="${controller}.inserirEmail"/>
							</label>
						</td>
						<td>
							<input type="text" name="email" style="width: 210px;"/>
						</td>
						<td>
                            <button type="submit" class="btn"><fmt:message key="${controller}.enviarSenha" /></button>
                        </td>
					</tr>
				</tbody>
			</table>
		</form>
    </tiles:putAttribute>
</tiles:insertTemplate>