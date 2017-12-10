<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>
<!doctype html>
<html lang="nl">
	<head>
		<vdab:head title="Artikel toevoegen"/>
	</head>
	<body>
		<vdab:menu/>
		<h1>Artikel toevoegen</h1>
		<form method="post" id="toevoegForm">
			<label for="naam">Naam:<span>${fouten.naam}</span></label>
			<input type="text" name="naam" required autofocus value="${param.naam}">
			<label for="aankoopprijs">Aankoopprijs:<span>${fouten.aankoopprijs}</span></label>
			<input type="number" name="aankoopprijs" min="0.01" step="0.01" value="${param.aankoopprijs}" required>
			<label for="verkoopprijs">Verkoopprijs:<span>${fouten.verkoopprijs}</span></label>
			<input type="number" name="verkoopprijs" min="${param.aankoopprijs}" step="0.01" value="${param.verkoopprijs}" required>
			<input type="submit" id="toevoegKnop" value="Toevoegen">
		</form>
		<script>
			document.getElementById("toevoegForm").addEventListener("submit", function(){
				document.getElementById("toevoegKnop").disabled="true";
			});
		</script>
	</body>
</html>
