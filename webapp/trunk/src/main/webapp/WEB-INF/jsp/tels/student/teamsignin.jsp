<%@ include file="../include.jsp"%>
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

<!-- $Id$ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>

<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet" type="text/css" />

<script src=".././javascript/tels/general.js" type="text/javascript" ></script>
<script src=".././javascript/tels/prototype.js" type="text/javascript" ></script>
<script src=".././javascript/tels/scriptaculous.js" type="text/javascript" ></script>
<script src=".././javascript/effects.js" type="text/javascript" ></script>

<title><spring:message code="application.title" /></title>
</head>

<body onunload="opener.location.reload()">
<c:if test="${closeokay}">
<c:out value="hi" />
</c:if>
<!--  <h1>Team Sign In</h1> -->

<div id="teamSelect" class="teamMargin2">

	<div id="teamSelectHeader"><authz:authentication operation="username" /> is already signed in. <br/>All other teammates should sign in below.</div>

	<form:form method="post" action="teamsignin.html" commandName="teamSignInForm" id="teamSignInForm" >
			<table id="multiUserSignIn" border="0" cellspacing="0" cellpadding="2">
		  		<tr>
		  			<td><label for="username1">Username 1:</label></td>
		     		<td><form:input disabled="true" path="username1" id="username1" /></td>
		     		<td><em>signed in</em></td>
				</tr>
				<tr><td style="padding:7px;"></td><td></td><td></td><td></td></tr>
		  		<tr>
		  		<c:forEach var="teammate_index" begin="2" end="3" step="1">
		    		<td><label for="username${teammate_index}">Username ${teammate_index}:</label></td>
		        	<td><form:input path="username${teammate_index}" id="username${teammate_index}"/></td>
		        	<td class="errorMsgStyle"><form:errors path="username${teammate_index}" /></td>
		        </tr>
			
				<tr>
		 			<td><label for="password${teammate_index}">Password:</label></td>
		        	<td><form:password path="password${teammate_index}" id="password${teammate_index}"/></td>
		        	<td class="errorMsgStyle"><form:errors path="password${teammate_index}" /></td>
		        </tr>
   				<tr><td style="padding:7px;"></td><td></td><td></td><td></td></tr>
		  </c:forEach>
			</table>
			
	 <div id="finalRunProjectButton" onclick="setTimeout('self.close()', 5000);">
 	    <input type="image" name=_finish" value="Run Project" id="runproject" src="../<spring:theme code="run_project" />" 
    		onmouseover="swapImage('runproject','../<spring:theme code="run_project_roll" />')" 
    		onmouseout="swapImage('runproject','../<spring:theme code="run_project" />')" />
	</div>
					
	</form:form>

</div>

</body>
</html>
