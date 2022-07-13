<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
	import="java.util.List,com.generation.cities.model.entities.*"
	    
%>
<%
	String error = (String)request.getAttribute("error");
	if(error==null)
	    error="";
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
		
		<form action="Index" method="get">
			<!--  terzo input: pulsante per avviare la ricerca, per INVIARE la request -->
			<input type="submit" class="w3-btn w3-teal" value="GO" />
			<!--  primo input: un parametro nascosto che verrà inviato a Index. Nome parametro=cmd, valore=search -->
			<input type="hidden" name="cmd" value="search" />
			<!--  secondo input: parametro VISIBILE inserito dall'utente, nome key, valore= quello che inserirà l'utente -->
			<input type="text" class="w3-input" style="display:inline-block;width:80%" placeholder="search for..." name="key" />
			
		</form>

		<h2>New City Form</h2>
		<form action="Index" method="get">
		
			ID
			<input type="text" name="id" class="w3-input" />
			Type
			<input type="text" name="type" class="w3-input" />
			Name
			<input type="text" name="name" class="w3-input" />
			Left
			<input type="number" name="left" class="w3-input" />
			Bottom
			<input type="number" name="bottom" class="w3-input" />
			Right
			<input type="number" name="right" class="w3-input" />
			Top
			<input type="number" name="top" class="w3-input" />
			ID of the parent City
			<input type="text" name="cityid" class="w3-input" />
			<input type="hidden" name="cmd" value="newbuilding" />
			<br/>
			<input type="submit" class="w3-btn city-colors" value="SAVE" />
			<b style="color:red"><%=error %></b>
			
			<!-- 
					Firma:
					-cmd = newbody
					8 parametri inseriti dall'utente
					
			 -->
		</form>
		
	</body>
</html>