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
		<br/>
		<h2 class="city-colors sectiontitle">New Building Form</h2>
		<form action="Index" method="get">
			
			<span class="tableheader">ID</span>
			<input type="text" name="id" class="w3-input" style="width:90%"/>
			<span class="tableheader">Type</span>
			<input type="text" name="type" class="w3-input" style="width:90%"/>
			<span class="tableheader">Name</span>
			<input type="text" name="name" class="w3-input" style="width:90%"/>
			<span class="tableheader">Address</span>
			<input type="text" name="address" class="w3-input" style="width:90%"/>
			<span class="tableheader">Left</span>
			<input type="number" name="left" class="w3-input" style="width:90%"/>
			<span class="tableheader">Bottom</span>
			<input type="number" name="bottom" class="w3-input" style="width:90%" />
			<span class="tableheader">Right</span>
			<input type="number" name="right" class="w3-input" style="width:90%"/>
			<span class="tableheader">Top</span>
			<input type="number" name="top" class="w3-input" style="width:90%"/>
			<span class="tableheader">ID of the parent City</span>
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