<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://vdab.be/tags" prefix="vdab" %>
<!doctype html>
<html lang="nl">
	<head>
		<vdab:head title="Lijst van artikels"/>
	</head>
	<body>
		<vdab:menu/>
		<h1>Lijst van alle artikels</h1>
		<c:if test="${not empty artikels}">
			<table>
				<thead>
					<tr>
						<th>Artikel</th><th>Groep</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${artikels}" var="artikel">
						<tr>
							<td>${artikel.naam}</td>
							<td>${artikel.artikelgroep.naam}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
		<c:if test="${empty artikels}">
			Geen artikels gevonden.
		</c:if>
	</body>
</html>