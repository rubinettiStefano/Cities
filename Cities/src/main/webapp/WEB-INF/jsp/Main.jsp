<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.List,com.generation.cities.model.entities.*"%>
<%
	List<City> cities = (List<City>) request.getAttribute("cities");
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


	<jsp:include page="Menu.jsp"></jsp:include>

	<br />

	<jsp:include page="CityList.jsp"></jsp:include>
</body>
</html>