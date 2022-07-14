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
		<h2 class="city-colors sectiontitle">New Citizen Form</h2>
		<form action="Index" method="get">
			
			<span class="tableheader">ID</span>
			<input type="text" name="id" class="w3-input" style="width:90%"/>
			<span class="tableheader">Name</span>
			<input type="text" name="name" class="w3-input" style="width:90%"/>
			<span class="tableheader">Surname</span>
			<input type="text" name="surname" class="w3-input" style="width:90%"/>
			<span class="tableheader">Profession</span>
			<input type="text" name="profession" class="w3-input" style="width:90%"/>
			<span class="tableheader">Date Of Birth</span>
			<input type="text" name="dob" class="w3-input" style="width:90%"/>
			<span class="tableheader">Gender</span>
			<input type="text" name="gender" class="w3-input" style="width:90%" />
			<span class="tableheader">Salary</span>
			<input type="number" name="salary" class="w3-input" style="width:90%"/>
			<span class="tableheader">ID of the Body</span>
			<input type="text" name="bodyid" class="w3-input" />
			<input type="hidden" name="cmd" value="newcitizen" />
			<br/>
			<input type="submit" class="w3-btn city-colors" value="SAVE" />
			<b style="color:red"><%=error %></b>
		</form>
		
	</body>
</html>