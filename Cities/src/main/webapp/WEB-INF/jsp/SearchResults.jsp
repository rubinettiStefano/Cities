<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
	import="java.util.List,com.generation.cities.model.entities.*"
	    
%>
<%
	List<City> cities = (List<City>) request.getAttribute("cities");
	List<Body> bodies = (List<Body>) request.getAttribute("bodies");
	List<Citizen> citizens = (List<Citizen>) request.getAttribute("citizens");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>City Manager 1.1 - Search Results</title>
		<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
		<link rel="stylesheet" href="/Cities/city.css">
	</head>
	<body>
		<h1 class="w3-teal">City Manager 1.1 - Search Results </h1>
		
		<form action="Index" method="get">
			<!--  terzo input: pulsante per avviare la ricerca, per INVIARE la request -->
			<input type="submit" class="w3-btn w3-teal" value="GO" />
			<!--  primo input: un parametro nascosto che verrà inviato a Index. Nome parametro=cmd, valore=search -->
			<input type="hidden" name="cmd" value="search" />
			<!--  secondo input: parametro VISIBILE inserito dall'utente, nome key, valore= quello che inserirà l'utente -->
			<input type="text" class="w3-input" style="display:inline-block;width:80%" placeholder="search for..." name="key" />
			
			
		</form>


		<h2>Cities found containing: <b><%=request.getParameter("key") %></b></h2>
		<div >
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
									--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
					
			<%
				}
			%>
		</div>
		
		
		<h2>Bodies found containing: <b><%=request.getParameter("key") %></b></h2>
		
		<div>
			<div class="w3-row tableheader">
				<div class="w3-quarter">	
					  City
				</div>
				<div class="w3-quarter">	
					ID - Name
				</div>
				<div class="w3-quarter">	
					Geometry
				</div>
				<div class="w3-quarter">	
					Size
				</div>
			</div>
			<%
				for(Body b :bodies)
				{
			%>
				<div class="w3-row">
					<div class="w3-quarter"><%= b.city.name%></div>
					<div class="w3-quarter"><%=b.ID%> - <%=b.name%></div>
					<div class="w3-quarter">From (<%=b.left%>,<%=b.bottom%>) To (<%=b.right%>,<%=b.top%>)</div>
					<div class="w3-quarter"><%= b.getArea()%> Units</div>
				</div>
				--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			<%
				}
			%>
		</div>
		
		
		<h2>Citizens found containing: <b><%=request.getParameter("key") %></b></h2>
		
		<div >
			<div class="w3-row tableheader">
				<div class="w3-quarter">	
					ID
				</div>
				<div class="w3-quarter">	
					Name
				</div>
				<div class="w3-quarter">	
					Surname
				</div>
				<div class="w3-quarter">	
					Residence
				</div>
			</div>
			<%
				for(Citizen c : citizens)
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
							 <%=c.surname %>
						</div>
						<div class="w3-quarter">	
							<%=c.body.city.name %>-<%=c.body.name %> 
						</div>
					</div>
									--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
					
			<%
				}
			%>
		</div>
	</body>
</html>