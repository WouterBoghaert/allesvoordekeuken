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
			<label><input type="radio" name="soort" value="F" id="food" ${param.soort == "F" ? "checked" : ""}>Food<span>${fouten.soort}</span></label><br>
			<label for="houdbaarheid">Houdbaarheid:<span>${fouten.houdbaarheid}</span></label>
			<input type="number" min="1" step="1" value="${param.houdbaarheid}" name="houdbaarheid" id="houdbaarheid" required>
			<label><input type="radio" name="soort" value="NF" id="nonfood" ${param.soort == "NF" ? "checked" : ""}>Non-Food<span>${fouten.soort}</span></label><br>
			<label for="garantie">Garantie:<span>${fouten.garantie}</span></label>
			<input type="number" min="0" step="1" value="${param.garantie}" name="garantie" id="garantie" required>
			<label>Artikelgroep: <span>${fouten.artikelgroep}</span>
				<select name="artikelgroepId" size="${artikelgroepen.size()}" required>
					<c:forEach items="${artikelgroepen}" var="artikelgroep">
						<option value="${artikelgroep.id}"
						${artikelgroep.id == param.artikelgroepId ? "selected" : ""}>
						${artikelgroep.naam}</option>
					</c:forEach>
				</select>
			</label>
			<input type="submit" id="toevoegKnop" value="Toevoegen">
		</form>
		<script>
			document.getElementById("toevoegForm").addEventListener("submit", function(){
				document.getElementById("toevoegKnop").disabled="true";
			});
			document.getElementById('food').onclick =
				enableDisableInputs;
			document.getElementById('nonfood').onclick =
				enableDisableInputs;
			enableDisableInputs();
			function enableDisableInputs() {
				document.getElementById('houdbaarheid').disabled =
				! document.getElementById('food').checked;
				document.getElementById('garantie').disabled =
				! document.getElementById('nonfood').checked;
			}
		</script>
	</body>
</html>