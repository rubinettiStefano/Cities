<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
	import="java.util.List,com.generation.cities.model.entities.*"
	    
%>
<%
	List<City> cities = (List<City>) request.getAttribute("cities");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>City Manager 1.0 - List of managed cities</title>
		<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
		<link rel="stylesheet" href="city.css">
	</head>
	<body>
		<h1 class="w3-teal"> City Manager 1.0 - Cities List </h1>
		<div class="w3-half">
			<div class="w3-row tableheader">
				<div class="w3-quarter">	
					ID
				</div>
				<div class="w3-quarter">	
					Name
				</div>
				<div class="w3-quarter">	
					Width
				</div>
				<div class="w3-quarter">	
					Height
				</div>
			</div>
			<%
				for(City c : cities)
				{	 
			%>
					<div class="w3-row">
						<div class="w3-quarter">	
							<%=c.ID %> 
						</div>
						<div class="w3-quarter">	
							<%=c.name %>
						</div>
						<div class="w3-quarter">	
							 <%=c.w %>
						</div>
						<div class="w3-quarter">	
							<%=c.h %> 
						</div>
					</div>
			<%
				}
			%>
		</div>
	</body>
</html>