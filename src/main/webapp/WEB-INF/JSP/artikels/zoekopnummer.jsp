<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>
<!doctype html>
<html lang="nl">
	<head>
		<vdab:head title='${empty artikel ? "Zoek artikel op nummer" : artikel.naam}'/>
	</head>
	<body>
		<vdab:menu/>
		<h1>Zoek artikel op nummer.</h1>
		<form>
			<label for="id">Artikelnummer:<span>${fouten.id}</span></label>
			<input type="number" name="id" required autofocus min="1" value="${param.id}">
			<input type="submit" value="Zoeken">
		</form>
		<c:if test="${not empty artikel}">
			<table>
				<tr>
					<th>Naam</th>
					<th>Aankoopprijs</th>
					<th>Verkoopprijs</th>
					<th>% Winst</th>
				<tr>
				<tr>
					<td>${artikel.naam}</td>
					<td><fmt:formatNumber value="${artikel.aankoopprijs}" minFractionDigits="2" maxFractionDigits="2"/> &euro;</td>
					<td><fmt:formatNumber value="${artikel.verkoopprijs}" minFractionDigits="2" maxFractionDigits="2"/> &euro;</td>
					<td><fmt:formatNumber value="${artikel.winstPercentage}" minFractionDigits="2" maxFractionDigits="2"/>%</td>
				</tr>
			</table>
		</c:if>
		<c:if test="${not empty param and empty fouten and empty artikel}">
			Dit artikelnummer komt niet voor in de database.
		</c:if>
	</body>
</html>