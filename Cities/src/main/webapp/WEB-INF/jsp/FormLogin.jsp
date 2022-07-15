<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.List,com.generation.cities.model.entities.*"%>
<%
	String error = (String) request.getAttribute("error");
	if(error==null)
		error = "";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>City Manager 1.0 - List of managed cities</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="/Cities/city.css">
</head>
<body>
	<div class="w3-row">
		<div class="w3-quarter">&nbsp;</div>
		<div class="w3-half">
			<img title="Home Page" src="img/logo.png">
			<h2 style="padding-left: 0px">Insert your credentials</h2>
			<br />

			<form method="post" action="Index">
				Email <input type="text" name="email" class="w3-input" /> Password
				<input type="password" name="password" class="w3-input" /> <br />
				<input type="hidden" name="cmd" value="login" /> <input
					type="submit" value="LOGIN" class="w3-btn w3-input city-colors" />
				<b style="color: red"> <%=error %></b>
			</form>
		</div>
	</div>

</body>
</html>