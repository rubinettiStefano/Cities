<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
import="java.util.List,com.generation.cities.model.entities.*"	    
%>
<%
	City city = (City) request.getAttribute("city");
	String error = (String)request.getAttribute("error");
	if(error==null)
	    error="";
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
		<jsp:include page="Menu.jsp"></jsp:include>
		<br/>
		<h1 class="city-colors"> Details Page for: <b> <%=city.ID %> - <%=city.name %></b></h1>
		<b style="color:red;margin-left:20px;font-size:150%"><%=error %></b>
		<div>
			<h2> General Information</h2>
			<div style="margin-left:10px;">
				ID: <b><%=city.ID %></b> <br/>
				Name: <b><%=city.name %></b> <br/>
				Geometry: <b><%=city.w %> x <%=city.h %></b> blocks <br/>
			</div>
			<h2> Buildings</h2>
			<div class="w3-row tableheader">
				<div class="w3-quarter">	
					ID - Name - Type
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
					<div class="w3-quarter"><b><%=b.ID%></b> - <%=b.name%> - <%=b.type%></div>
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
						&nbsp;
					</div>
					<div class="w3-quarter">
						<a href="Index?cmd=deletebuilding&id=<%=b.ID%>&cityid=<%=b.cityID%>" class="w3-btn city-colors">
						Delete
						</a>
					</div>
				</div>
				--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			<%
				}
			%>
			<a href="Index" class="w3-btn city-colors">Back to home page</a>
		</div>
	</body>
</html>