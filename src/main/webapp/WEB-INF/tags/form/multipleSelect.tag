<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute required="true" name="name"  type="java.lang.String" %>
<%@ attribute required="true" name="options" type="java.lang.Object" %>
<%@ attribute required="false" name="id" type="java.lang.String" %>
<%@ attribute required="false" name="value" type="java.lang.Object" %>
<%@ attribute required="false" name="disabled" type="java.lang.String" %>
<%@ attribute required="false" name="clazz" type="java.lang.String" %>

<c:set var="selected" value="" />
<select name="${name}" id="${id}" class="${clazz}" ${disabled} multiple>
	<c:forEach var="option" items="${options}">
		<c:forEach items="${value}" var="valor">
			<c:if test="${option.id == valor.id}">
				<c:set var="selected" value="selected" />
			</c:if>
		</c:forEach>	
		<option value="${option.id}" ${selected}><c:out value="${option.forSelect}"/></option>
		<c:set var="selected" value="" />
	</c:forEach>
</select>