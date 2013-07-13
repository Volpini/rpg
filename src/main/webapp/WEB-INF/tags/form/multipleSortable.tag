<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute required="true" name="name"  type="java.lang.String" %>
<%@ attribute required="true" name="options" type="java.lang.Object" %>
<%@ attribute required="true" name="id" type="java.lang.String" %>
<%@ attribute required="true" name="object" type="java.lang.String" %>
<%@ attribute required="false" name="value" type="java.lang.Object" %>
<%@ attribute required="false" name="disabled" type="java.lang.String" %>
<%@ attribute required="false" name="clazz" type="java.lang.String" %>

<script>
	head(function(){
		$("#${id}").select2({tags: ${options}});
		$("#${id}").select2("container").find("ul.select2-choices").sortable({
			containment: 'parent',
			start: function() { $("#${id}").select2("onSortStart"); },
			update: function() { $("#${id}").select2("onSortEnd"); }
		});
		$("form").submit(function(){
			elemento = $("#${id}");
			explodedValue = elemento.val().split(",");					
			for(var cont = 0; cont < explodedValue.length; cont++){
				elemento.parent().append("<input type='hidden' name='${name}.position' value='"+cont+"' />");
				elemento.parent().append("<input type='hidden' name='${name}.${object}.id' value='"+explodedValue[cont]+"' />");
			}
		});
	});
</script>
<c:set var="multiValue" />
<c:forEach var="item" items="${value}" varStatus="c">
	<c:choose>
		<c:when test="${c.count == 1}">
			<c:set var="multiValue" value="${item.objectId}" />
		</c:when>
		<c:otherwise>
			<c:set var="multiValue" value="${multiValue},${item.objectId}" />
		</c:otherwise>
	</c:choose>
</c:forEach>
<input type="hidden" id="${id}" value="${multiValue}" />