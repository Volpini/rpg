<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="classe" required="false" %>
<%@ attribute name="value" type="org.joda.time.LocalDateTime" required="false" %>
<%@ attribute name="disabled" required="false" type="java.lang.String" %>
<%@ attribute name="maxDate" required="false" %>
<%@ attribute name="minDate" required="false" %>
<%@ attribute name="type" required="false" %>

<script type="text/javascript">
	head.ready(function(){
		try {
			setTimeout(function(){
				if( !$("#${id}").hasClass("field-inactive") && ${empty disabled && type != 'hidden'}){				
					$("#${id}").datepicker({
						dateFormat: 'dd/mm/yy',
						altField: "#data_${id}",
						minDate: "${minDate}",
						maxDate: "${maxDate}",
						showOn: "button",
						buttonText: "Calendário",
						showButtonPanel: true,
						onClose: function(dateText, inst) {
							var value = $("#${id}").val();
							$("#data_${id}").val( value + " 00:00");
						}
					});
				}else{
					$("#${id}").removeClass("ui-campoData");
				}
			}, 200);
			
		} catch (e) {
			$("#${id}").attr("disabled","disabled");
		}
		$("#${id}").mask("99/99/9999");
	});
</script>
<c:set var="valueData" scope="request"><joda:format value="${value}" pattern="dd/MM/yyyy"/></c:set>
<input class="ui-campoData" type="${empty type ? 'text' : type }" id="${id}" value="${valueData}" placeholder="__/__/____" maxlength="10" ${disabled} />
<input type="hidden" name="${name}" id="data_${id}" value="<joda:format value="${value}" pattern="dd/MM/yyyy HH:mm"/>" class="${classe}"/>