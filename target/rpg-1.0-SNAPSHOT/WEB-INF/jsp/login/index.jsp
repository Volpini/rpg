<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" tagdir="/WEB-INF/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<tiles:insertTemplate template="/WEB-INF/jsp/templates/login.jsp">
    <tiles:putAttribute name="body">
        <form action="<c:url value='/${controller}' />" method="post">
			<div>
				<table>
					<thead>
						<tr>
							<th colspan="2"><center><span>Login</span></center></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								<label>
									<fmt:message key="${controller}.${controller}"/>
								</label>
								<input type="text" name="systemUser.email" value="${systemUser.email}" id="user_email" autofocus="true"placeholder="<fmt:message key="${controller}.placeholderEmail" />" />
							</td>
							<td>
								<label>
									<fmt:message key="${controller}.password"/>
								</label>
								<input type="password" name="systemUser.password" id="senha" placeholder="<fmt:message key="${controller}.placeholderPassword" />" />
								<button type="submit" class="btn btn-info"><fmt:message key="${controller}.entrar" /></button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
        </form>
    </tiles:putAttribute>
</tiles:insertTemplate>