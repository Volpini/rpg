<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute required="true" name="name"  type="java.lang.String" %>
<%@ attribute required="true" name="options" type="java.lang.Object" %>
<%@ attribute required="false" name="id" type="java.lang.String" %>
<%@ attribute required="false" name="value" type="java.lang.String" %>
<%@ attribute required="false" name="disabled" type="java.lang.String" %>
<%@ attribute required="false" name="isEnum" type="java.lang.Boolean" %>
<%@ attribute required="false" name="clazz" type="java.lang.String" %>

<select name="${name}" id="${id}" class="${clazz}" ${disabled} >
	<option value=""></option>
	<c:forEach var="option" items="${options}">
		<c:if test="${not isEnum || empty isEnum}">
			<c:choose>
				<c:when test="${option.id == value}">
					<option value="${option.id}" selected="selected"><c:out value="${option.forSelect}"/></option>
				</c:when>
				<c:otherwise>
					<option value="${option.id}"><c:out value="${option.forSelect}"/></option>
				</c:otherwise>
			</c:choose>
		</c:if>
		<c:if test="${!(not isEnum || empty isEnum)}">
			<c:choose>
				<c:when test="${option == value}">
					<option value="${option.value}" selected="selected"><c:out value="${option.name}"/></option>
				</c:when>
				<c:otherwise>
					<option value="${option.value}"><c:out value="${option.name}"/></option>
				</c:otherwise>
			</c:choose>
		</c:if>
	</c:forEach>
</select>