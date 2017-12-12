<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>
<!doctype html>
<html lang="nl">
	<head>
		<vdab:head title="Artikels zoeken op naam"/>
		<style>
			td {
				text-align: right;
			}
			td:nth-child(2) {
				text-align: left;
			}
		</style>
	</head>
	<body>
		<vdab:menu/>
		<h1>Artikels zoeken op naam.</h1>
		<form>
			<label for="naam">Naam:<span>${fout.naam}</span></label>
			<input name="naam" type="search" value="${param.naam}" required autofocus>
			<input type="submit" value="Zoeken">
		</form>
		<c:if test="${not empty param and empty fout and empty artikels}">
			Geen artikels gevonden.
		</c:if>
		<c:if test="${not empty artikels}">
			<table>
				<thead>
					<tr><th>Nummer</th><th>Naam</th><th>Aankoopprijs</th><th>Verkoopprijs</th></tr>
				</thead>
				<tbody>
					<c:forEach items="${artikels}" var="artikel">
						<tr>
							<td>${artikel.id}</td>
							<td>${artikel.naam}</td>
							<td><fmt:formatNumber value="${artikel.aankoopprijs}"
							minFractionDigits="2" maxFractionDigits="2"/></td>
							<td><fmt:formatNumber value="${artikel.verkoopprijs}"
							minFractionDigits="2" maxFractionDigits="2"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</body>
</html>
