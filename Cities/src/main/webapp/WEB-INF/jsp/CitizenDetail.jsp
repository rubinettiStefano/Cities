<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.List,com.generation.cities.model.entities.*"%>
<%
	Citizen citizen = (Citizen) request.getAttribute("citizen");

	if(citizen==null)
	{
	    out.println("Expected attribute named citizen of type Citizen. Got null!. <br />");
	    out.println("Parameter data: <br />");
	    for(String key:request.getParameterMap().keySet())
			out.println(key+":"+request.getParameter(key)+"<br />");
	    return;
	}


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
	<br />
	<h1 class="city-colors">
		Details Page for: <b> <%=citizen.ID %> - <%=citizen.name %> - <%=citizen.surname %></b>
	</h1>
	<div>
		<h2>General Information</h2>
		<div style="margin-left: 10px;">
			Date Of Birth:
			<%=citizen.dob%>
			<br /> Profession:
			<%=citizen.profession%>
			<br /> Gender:
			<%=citizen.gender%>
			<br /> Address:
			<%=citizen.body.city.name%>
			-
			<%=citizen.body.address%>
			-
			<%=citizen.body.name%>
			<br /> <a
				href="Index?cmd=deletecitizen&id=<%=citizen.ID%>&bodyid=<%=citizen.bodyID%>&cityid=<%=citizen.body.cityID%>"
				class="w3-btn city-colors"> Delete </a>
		</div>
	</div>

</body>
</html>