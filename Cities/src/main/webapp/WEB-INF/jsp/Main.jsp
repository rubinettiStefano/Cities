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
		<link rel="stylesheet" href="/Cities/city.css">
	</head>
	<body>
		<h1 class="w3-teal"> City Manager 1.0 - Cities List </h1>
		
		<form action="Index" method="get">
			<!--  primo input: un parametro nascosto che verrà inviato a Index. Nome parametro=cmd, valore=search -->
			<input type="hidden" name="cmd" value="search" />
			<!--  secondo input: parametro VISIBILE inserito dall'utente, nome key, valore= quello che inserirà l'utente -->
			<input type="text" class="w3-input" style="display:inline-block;width:80%" placeholder="search for..." name="key" />
			<!--  terzo input: pulsante per avviare la ricerca, per INVIARE la request -->
			<input type="submit" class="w3-btn w3-teal" value="GO" />
		</form>


		
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
		</div>
	</body>
</html>