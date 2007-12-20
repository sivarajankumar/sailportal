<%@ include file="include.jsp"%>
<!--
  * Copyright (c) 2006 Encore Research Group, University of Toronto
  * 
  * This library is free software; you can redistribute it and/or
  * modify it under the terms of the GNU Lesser General Public
  * License as published by the Free Software Foundation; either
  * version 2.1 of the License, or (at your option) any later version.
  *
  * This library is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
  * Lesser General Public License for more details.
  *
  * You should have received a copy of the GNU Lesser General Public
  * License along with this library; if not, write to the Free Software
  * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
-->

<!-- $Id: login.jsp 341 2007-04-26 22:58:44Z hiroki $ -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="../../<spring:theme code="studentforgotstylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />    
<link href="../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />

<script type="text/javascript" src="../../javascript/general.js"></script>	

<title>Student - Search for Username via Project Code</title>

</head>

<body>

<div id="centeredDiv">
    	
<%@ include file="headermain.jsp"%>

<div id="lostTitleBar"><h1 class="blueText">Student Lost Username/Password</h1></div>
  
<div align="center">
  	
  	<br /> 
	<h1>Run a Search for Your Username</h1>

	<h4>Fill in your Project Code below (get this from your teacher if needed), then click the Search button.</h4>
	
	<br />  
	<div id="errorMessageFormat">
			<!-- Support for Spring errors object -->
			<spring:bind path="reminderParameters.*">
			  	<c:forEach var="error" items="${status.errorMessages}">
			    	<b><br /><c:out value="${error}"/></b>
				  </c:forEach>
			</spring:bind>
	</div>
	
	<form name="projectCode" method="post" commandName="reminderParameters">
			<label id="projectCodeLabel" for="send_projectcode">Project Code:</label>
 			<input type="text" name="projectCode" id="projectCode" />
    	 	<input type="submit" value="Search"/>
 
		</form>
	
		<br/><br/><br/><br/><br/><br/><br/>
		<a href="../../index.html"> 
		<img id="return" src="../../<spring:theme code="return_to_homepage" />"
		onmouseover="swapImage('return', '../../<spring:theme code="return_to_homepage_roll" />');"
		onmouseout="swapImage('return', '../../<spring:theme code="return_to_homepage" />');" /></a>
	
<div>

</div>   <!--END OF CENTERED DIV-->

</body>
</html>
