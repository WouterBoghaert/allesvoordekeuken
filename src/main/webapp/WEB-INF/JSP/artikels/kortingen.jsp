<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>
<!doctype html>
<html lang="nl">
	<head>
		<vdab:head title="Kortingen voor ${empty artikel ? 'onze artikelen' :  artikel.naam}"/>
	</head>
	<body>
		<vdab:menu/>
		<h1>Kortingen voor ${empty artikel ? 'onze artikelen' :  artikel.naam}</h1>
		<c:if test="${not empty artikels}">
			<p>Klik op een artikel om de kortingen te zien</p>
			<ul>
				<c:forEach items="${artikels}" var="art">
					<c:url value="" var="detailURL">
						<c:param name="id" value="${art.id}"/>
					</c:url>
					<li><a href="${detailURL}" title="${art.naam}">${art.naam}</a></li>
				</c:forEach>
			</ul>
		</c:if>
		<c:if test="${not empty artikel}">
			<c:if test="${empty artikel.kortingen}">
				Er werden geen kortingen voor dit artikel gevonden.
			</c:if>
			<dl>
				<c:forEach items="${artikel.kortingen}" var="korting">
					<dt>Vanaf aantal: </dt>
					<dd>${korting.vanafAantal}</dd>
					<dt>Korting (%): </dt>
					<dd><fmt:formatNumber value="${korting.kortingsPercentage}"
					minFractionDigits="2" maxFractionDigits="2"/></dd><br>
				</c:forEach>
			</dl>
		</c:if>
		<c:if test="${empty artikel and not empty param}">
			Dit artikel werd niet gevonden.
		</c:if>
		<c:if test="${empty artikel and empty artikels and empty param}">
			Er is iets misgegaan, contacteer de helpdesk.
		</c:if>
	</body>
</html>