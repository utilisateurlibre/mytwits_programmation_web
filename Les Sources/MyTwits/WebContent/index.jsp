<%@page import="com.mytwits.service.Twit"%>
<%@page import="java.util.List"%>
<%@page import="com.mytwits.service.TwitDao"%>
<%@page import="com.mytwits.action.Login"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Login</title>
<link href="styles.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/icon.ico" /> 
<script type="text/javascript" src="iepngfix_tilebg.js"></script> 
<script type="text/javascript" src="iepngfix.js"></script> 
</head>
<body>
<div id="mainWrap">
<div id="mainPanel">
 <div id="menu">
  <ul>
   <li><a href="#"><span>Home</span></a></li>
   <li><div class="blank"></div></li>
   <li><a href="#"><span>About&nbsp;Us</span></a></li>
   <li><div class="blank"></div></li>
   <li><a href="#"><span>Support</span></a></li>
   <li><div class="blank"></div></li>
   <li><a href="#"><span>Forum</span></a></li>
   <li><div class="blank"></div></li>
   <li><a href="#"><span>Development</span></a></li>
   <li><div class="blank"></div></li>
   <li><a href="#"><span>Conact&nbsp;Us</span></a></li>
  </ul>
 </div>
  <div id="logoWrap"><h1>Contacts Project</h1></div>
  <div id="loginPanel">

<h2>Add twit</h2>

<%if (request.getSession().getAttribute("utilisateur") == null) { %>
<% } else { %>
<form action="TwitsServlet" method=post >
Text: <TEXTAREA NAME="twit"></TEXTAREA>
      <INPUT TYPE=SUBMIT VALUE="Send">
</form>
<table>
<%
	TwitDao dao = new TwitDao();
	List<Twit> listTwits = dao.listAll();
	for (Twit twit : listTwits) { %>
		<tr><td style="font-weight: bold;"><%=twit.getNomUser()%></td><td><%=twit.getText()%></td></tr>
		
	<% } %>
</table> 
<% } %>
</div>
</body>
</html>