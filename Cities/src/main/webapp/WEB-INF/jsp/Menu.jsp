<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
	import="java.util.List,com.generation.cities.model.entities.*"
	    
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
	
			<h1> <a href="Index"><img title="Home Page" src="img/logo.png"></a> </h1>
			<div style="margin-left:20px">
				<form action="Index" method="get">
					<input type="hidden" name="cmd" value="search" />
					<input type="text" class="w3-input" style="display:inline-block;width:200px" placeholder="search for..." name="key" />
					<input type="submit" class="w3-btn city-colors" value="GO" />
					
					<a href="Index?cmd=formnewcity" class="w3-btn city-colors">
						Insert New City
					</a>
					<a href="Index?cmd=formnewbulding" class="w3-btn city-colors">
						Insert New Building
					</a>
					<a href="Index?cmd=formnewcitizen" class="w3-btn city-colors">
						Register Citizen
					</a>
				</form>
			</div>
	</body>
</html>