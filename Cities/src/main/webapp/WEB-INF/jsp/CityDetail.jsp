<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
import="java.util.List,com.generation.cities.model.entities.*"	    
%>
<%
	City city = (City) request.getAttribute("city");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>City Details</title>
		<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
		<link rel="stylesheet" href="/Cities/city.css">
	</head>
	<body>
		<form action="Index" method="get">
			<!--  terzo input: pulsante per avviare la ricerca, per INVIARE la request -->
			<input type="submit" class="w3-btn w3-teal" value="GO" />
			<!--  primo input: un parametro nascosto che verrà inviato a Index. Nome parametro=cmd, valore=search -->
			<input type="hidden" name="cmd" value="search" />
			<!--  secondo input: parametro VISIBILE inserito dall'utente, nome key, valore= quello che inserirà l'utente -->
			<input type="text" class="w3-input" style="display:inline-block;width:80%" placeholder="search for..." name="key" />
			
			<a href="Index?cmd=formnewcity" class="w3-btn w3-teal">
				NEW CITY
			</a>

		</form>
	
	
		<h1 class="w3-teal"> Details Page for: <b> <%=city.ID %> - <%=city.name %></b></h1>
		<div>
			<h2> General Information</h2>
			ID: <b><%=city.ID %></b> <br/>
			Name: <b><%=city.name %></b> <br/>
			Geometry: <b><%=city.w %> x <%=city.h %></b> blocks <br/>
		
			<h2> Buildings</h2>
			<div class="w3-row tableheader">
				<div class="w3-quarter">	
					ID - Name
				</div>
				<div class="w3-quarter">	
					Type
				</div>
				<div class="w3-quarter">	
					Geometry
				</div>
				<div class="w3-quarter">	
					Citizens
				</div>
			</div>
			<%
				for(Body b : city.bodies)
				{
			%>
				<div class="w3-row">
					<div class="w3-quarter"><b><%=b.ID%></b> - <%=b.name%></div>
					<div class="w3-quarter"><%=b.type%></div>
					<div class="w3-quarter">(<%=b.left%>,<%=b.bottom%>)-(<%=b.right%>,<%=b.top%>)</div>
					<div class="w3-quarter">
						<%
							for(Citizen c : b.citizens)
							{
						%>
								<%=c.ID%> <%=c.name%> <%=c.surname%>  <br/>
						<%
							}
						%>
					</div>
				</div>
				--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			<%
				}
			%>
			<a href="Index">Back to home page</a>
		</div>
	</body>
</html>