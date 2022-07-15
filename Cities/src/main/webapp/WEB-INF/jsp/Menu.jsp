<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="com.generation.cities.model.entities.User"	
	
%>
<%
	User loggedUser = (User) request.getSession().getAttribute("loggedUser");
%>

<h1>
	<a href="Index"><img title="Home Page" src="img/logo.png"></a>
</h1>
<div style="margin-left: 20px">
	Logged as : <b><%= loggedUser.email %></b> - <a href="Index?cmd=logout" style="color:red"> Logout </a>
	<form action="Index" method="get">
		<input type="hidden" name="cmd" value="search" /> <input type="text"
			class="w3-input" style="display: inline-block; width: 200px"
			placeholder="search for..." name="key" /> <input type="submit"
			class="w3-btn city-colors" value="GO" /> <a
			href="Index?cmd=formnewcity" class="w3-btn city-colors"> Insert
			New City </a> <a href="Index?cmd=formnewbuilding"
			class="w3-btn city-colors"> Insert New Building </a> <a
			href="Index?cmd=formnewcitizen" class="w3-btn city-colors">
			Register Citizen </a>
	</form>
</div>
<div style="display: none">
	<%
out.println("PARAMETRI");
	for(String key : request.getParameterMap().keySet())
	    out.println(key+":"+request.getParameter(key));

%>
</div>

