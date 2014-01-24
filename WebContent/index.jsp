<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.dao.*, com.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String email = request.getParameter("email");
String password = request.getParameter("password");
if(email != null && password!=null){
	User user = null;
	user = UserDao.instance.login(email, password);
	if(user != null){
		session.setAttribute("user", user);
		response.sendRedirect("./home.jsp");
	}
	
}
	
	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="css/index.css" />
<title>Liquid Benchmark</title>
<link rel="shortcut icon" type="image/x-icon" href="favicon.ico" />
</head>
<body SCROLL=NO>
<div id="top">

</div>

<div id="middle">
<div id="siteName">Liquid <br/>Benchmark</div>
<div id="description">

</div>

<div id="menu">
	<span id="sSignin" class="activeMenu">login</span>
    <span id="sSignup">new user</span>
</div>

<form id="signin" method="post" >
        <p>
        <label class="b">Email</label><br/>
        <input id="email" name="email" type="email" class="textEntry" spellcheck="false" />
        <span id="UserNameRequired" title="Email is required." class="failureNotification">*</span><br/>
        </p><p>
        <label class="b">Password</label><br/>
        <input id="password" name="password" type="password" class="passwordEntry" />
        <span id="PasswordRequired" title="Password is required." class="failureNotification">*</span>
        <p/><p>
        <span id="LoginInfo" class="failureNotification"></span>
        <input id="rememberme" name="rememberme" type="checkbox" value="rememberme"/>
        <label id="RememberMeLabel" for="rememberme" class="inline">Keep me logged in</label>
        <br/>
		</p>    
		<p>
        <input id="bsignin" type="submit" value="login"/>
        </p>
        <br/>
        <p>
	    <a class="forgetpassword" href="/forgetpassword">Forget password</a>
		</p>

</form>
<div id="signup">
  
	<p>
        <label class="b">* Email</label><span id="mEmail" class="message"></span><br/>
        <input id="newemail" type="email" class="passwordEntry"  spellcheck="false"/>
    </p>     
    <p>
       <label class="b">* Name</label><span id="mName" class="message"></span><br/>
        <input id="name" type="text" class="textEntry"  spellcheck="false"/>
    </p>
     <p>
       <label class="b">&nbsp;&nbsp;Surname</label><br/>
        <input id="surname" type="text" class="textEntry"  spellcheck="false"/>
    </p>
    <p>
        <label class="b">* Password</label><span id="mPassword" class="message"></span><br/>
        <input id="newpassword" type="password" class="passwordEntry" />
    </p>
    <p>
    <input id="bsignup" type="button" value=" create account "/></p>
	</div>
</div>



<script type="text/javascript" src="js/index.js"></script>
</body>
</html>