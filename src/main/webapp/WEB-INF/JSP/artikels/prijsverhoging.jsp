<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>
<!doctype html>
<html lang="nl">
	<head>
		<vdab:head title="Algemene prijsverhoging"/>
	</head>
	<body>
		<vdab:menu/>
		<h1>Algemene prijsverhoging</h1>
		<form method="post" id="prijsverhogingForm">
			<label for="percentage">Percentage:<span>${fouten.percentage}</span></label>
			<input type="number" name="percentage" min="0.01" step="0.01" 
			value="${param.percentage}" autofocus required>
			<input type="submit" value="Prijs verhogen" id="prijsverhogingKnop">
		</form>
		<script>
			document.getElementById("prijsverhogingForm").addEventListener("submit", function() {
				document.getElementById("prijsverhogingKnop").disabled = true;
			});
		</script>
	</body>
</html>
