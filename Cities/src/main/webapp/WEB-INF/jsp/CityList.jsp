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
		<title>Cities List</title>
	</head>
	<body>
	
		<h2 class="city-colors sectiontitle">OUR Cities</h2>
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
						<a href="Index?cmd=city&id=<%=c.ID %>">
							<%=c.ID %> 
						</a> 	
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
	
	
	</body>
</html>