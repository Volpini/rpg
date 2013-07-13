<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${!empty success}">
	<ul class="greenNotification notificationMessage">
		<li class="closeNotificationMessage">x</li>
		<li>
			<fmt:message key="${success}"/>
		</li>
	</ul>
</c:if>
<c:if test="${!empty errors}">
	<ul class="errorForm notificationMessage">
		<li class="closeNotificationMessage">x</li>
			<c:forEach var="error" items="${errors}">
			<li>
				<fmt:message key="${controller}.${error.category}"/> - ${error.message}
			</li>
		</c:forEach>
	</ul>
</c:if>
<style>
	.closeNotificationMessage{
		float : right;
		margin-right: 5px;
		font-weight: bold;
		font-size: 14px;
		cursor: pointer;
		margin-top : 0px !important;
	}
	.closeNotificationMessage:hover{
		color: white;
	}
</style>
<script>
	head(function() {
		$(".closeNotificationMessage").click(function() {
			$(".notificationMessage").animate({opacity: 0}, 200, function() {
				$(this).remove();
			});
		});
	});
</script>