<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <title><tiles:insertAttribute  name="title"/> | RPG</title>
        <link rel="shortcut icon" href="<c:url value='/img/favicon.ico' />" type="image/x-icon" />
        <link rel="stylesheet" type="text/css" href="<c:url value='/css/jquery-layout.css'/>" />
        <link rel="stylesheet" type="text/css" href="<c:url value='/css/jquery-gridy.css'/>">
        <link rel="stylesheet" type="text/css" href="<c:url value='/css/bootstrap.css'/>">
		<link rel="stylesheet" type="text/css" href="<c:url value='/css/select2.css'/>">
        <link rel="stylesheet/less" type="text/css" href="<c:url value='/css/main.css'/>">
        <link rel="stylesheet/less" type="text/css" href="<c:url value='/css/validationPopups.css'/>">
        <link rel="stylesheet" type="text/css" href="<c:url value='/css/jquery-ui.css'/>">		
        <tiles:insertAttribute name="css" ignore="true"/>
        <script src="<c:url value='/js/head.js'/>" type="text/javascript"></script>
    </head>
    <body style="display:none">
        <div class="first-center">
            <div class="second-north">
				<span id="opcoesUsuario">
					<a id="gearOpcoes" onclick="javascript:void(0);">
						<img src="<c:url value='/img/gear_black.png' />" width="20" title="<fmt:message key='opcoesUsuario.opcoes' />" />
					</a>
				</span>
				<span id="logoTopo"><a href="#"><img src="<c:url value='/img/dnd_logo.png' />"></a></span>
            </div>
            <div class="second-center">
                <div class="third-center">
                    <div class="fourth-north">
                        <section id="menu-contexto">
                            <nav class="tabbable tabs">
                                <ul class="nav nav-tabs">
                                    <li><a href="#menu1" data-toggle="tab"><fmt:message key="menu.controle" /></a></li>
                                </ul>
                                <div class="tab-content">
                                    <div class="tab-pane" id="menu1">
                                        <ul>
                                            <li><a href="<c:url value='/item'/>"><fmt:message key="menu.controle.itens" /></a></li>
                                            <li><a href="<c:url value='/userGroup'/>"><fmt:message key="menu.controle.grupousuarios" /></a></li>
                                            <li><a href="<c:url value='/systemUser'/>"><fmt:message key="menu.controle.usuarios" /></a></li>
											<li><a href="<c:url value='/personagem'/>"><fmt:message key="menu.controle.personagens" /></a></li>
											<li><a href="<c:url value='/talento'/>"><fmt:message key="menu.controle.talentos" /></a></li>
											<li><a href="<c:url value='/livro'/>"><fmt:message key="menu.controle.livros" /></a></li>
                                        </ul>
                                    </div>
								</div>
                            </nav>
                        </section>
                        <section id="breadcrumb-section">
                            <ul class="breadcrumb">
                                <li>
                                    <a href="<c:url value='/systemUser/home'/>">Home</a> <span class="divider">/</span>
                                </li>
                                <li>
                                    <c:if test="${!empty breadCrumb.menu()}">
                                        <a href="<c:url value='/${controller}'/>"><fmt:message key="${breadCrumb.menu()}"/></a> <span class="divider">/</span>
                                    </c:if>
                                    <c:if test="${!empty breadCrumb.controller()}">
                                        <a href="<c:url value='/${controller}'/>"><fmt:message key="${breadCrumb.controller()}"/></a> <span class="divider">/</span>
                                    </c:if>
                                </li>
                                <li class="active">${viewType}</li>
                            </ul>
                        </section>				
                    </div>
                    <div class="fourth-center">
                        <section id="pagina">
                            <article>
                                <header>
                                    <c:if test="${!viewType.isGrid}">
                                        <h2><tiles:insertAttribute name="title"/></h2>
                                    </c:if>
                                </header>
                                <section>
                                    <tiles:insertAttribute name="body"/>
                                </section>
                                <footer>
                                    <tiles:insertAttribute name="footer" ignore="true"/>
                                </footer>
                            </article>
                        </section>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            head.js("<c:url value='/js/jquery.js'/>", "<c:url value='/js/jquery-layout.js'/>", "<c:url value='/js/jquery-ui.js'/>");
            head.js("<c:url value='/js/less.js'/>", "<c:url value='/js/jquery-gridy.js'/>", "<c:url value='/js/jquery-tmpl.js'/>", "<c:url value='/js/bootstrap.js'/>", "<c:url value='/js/util.js'/>", "<c:url value='/js/select2.min.js'/>");
            var firstLayout, secondLayout, thirdLayout, fourthLayout;
            head(function() {
                $("body").css("display", "block");
                firstLayout = $("body").layout({
                    slidable: false,
                    fxSpeed: 1,
                    spacing_closed: 12,
                    spacing_open: 8,
                    center__paneSelector: ".first-center",
                    /*east__paneSelector : ".first-east",
                     east__initClosed : true,*/
                    center__onresize: "secondLayout.resizeAll"
                });

                secondLayout = $(".first-center").layout({
                    resizable: false,
                    closable: false,
                    spacing_open: 0,
                    north__size: 130,
                    north__paneSelector: ".second-north",
                    center__paneSelector: ".second-center",
                    center__onresize: "thirdLayout.resizeAll"
                });

                secondLayout.allowOverflow("north");

                thirdLayout = $(".second-center").layout({
                    resizable: false,
                    closable: false,
                    spacing_open: 0,
                    center__paneSelector: ".third-center",
                    center__onresize: "fourthLayout.resizeAll"
                });

                fourthLayout = $(".third-center").layout({
                    resizable: false,
                    closable: false,
                    spacing_open: 0,
                    north__paneSelector: ".fourth-north",
                    center__paneSelector: ".fourth-center"
                });

                fourthLayout.allowOverflow("north");

                $("html").mousedown(function(event) {
                    var $target = $(event.target);
                    if ($target.parents("nav[class='tabbable tabs']").attr("class") != 'tabbable tabs') {
                        $(".tab-pane, .nav-tabs li").removeClass("active");
                    }
                });
                
				$("select:not(.noSelect2)").select2();
				$("#gearOpcoes").mouseenter(function(){
					$(this).find("img").attr("src", "<c:url value='/img/gear_white.png' />");
				}).mouseleave(function(){
					$(this).find("img").attr("src", "<c:url value='/img/gear_black.png' />");
				});
            });
        </script>
        <c:if test="${viewType.isView and controller != 'expensesPlanningReport'}">
            <script>
                /* Excluir botões de 'add' e 'remover' linhas que funcionam apenas para add e edit 
				 * 
				 */
                head(function() {
                    // Seletores devem ser separados, ou então irá excluir botões errados da página 
                    $("section#pagina section a:not(#botoesForm a):not(.select2-choice)").remove();
                    $("section#pagina section button:not(#botoesForm button)").remove();
                });
            </script>
        </c:if>
    </body>
</html>