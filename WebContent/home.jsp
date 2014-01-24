<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.dao.*, com.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
//session = request.getSession(false);// don't create if it doesn't exist
try {
   

	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="css/home.css" />
<link type="text/css" rel="stylesheet" href="css/icontrols.css" />
<link type="text/css" rel="stylesheet" href="dist/themes/default/style.min.css" />
<title>Liquid benchmark</title>
</head>
<body>
<div id = "top">
<div id="messageBox"></div>
<span id = "sitename">Liquid Benchmark</span>
<div id="user">
<span id = "username" class="label"><% out.print(((User)session.getAttribute("user")).getName()); %></span>&nbsp;|&nbsp;<span id="logout" class="label">Logout</span>
</div>
</div>
<div id = "left">
<input id="searchTree" type="text" value="" placeholder="Search" /><br/>
<input id="newDomain" type="button" value="+" />
<div id = "tree"></div>
<br/>
<br/>

</div>
<div id = "right">
Please choose a subdomain
</div>
<div id="transparent"></div>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/jstree.min.js"></script>
<script type="text/javascript" src="js/lang/en.js"></script>
<script type="text/javascript" src="js/icontrols.js"></script>
<script type="text/javascript" src="js/home.js"></script>
</body>
</html>
<% }catch (Exception e){
	 response.sendRedirect("./index.jsp");
	
}%>