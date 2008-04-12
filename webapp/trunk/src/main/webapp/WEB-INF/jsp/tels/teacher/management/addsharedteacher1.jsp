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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<script type="text/javascript" src="../../javascript/tels/general.js"></script>
<script type="text/javascript" src="../../javascript/tels/effects.js"></script>
<script type="text/javascript" src=".././javascript/tels/general.js"></script>
    
<title><spring:message code="application.title" /></title>

<script type='text/javascript' src='/webapp/dwr/engine.js'></script>
</head>
<body>

Step 1: Type in the name of the teacher you wish to grant permissions
	
	<form:form method="post" commandName="lookupUserParameters">
		Please Type in Username: 
		<input type="text" name="usernameToLookup"/>
		<input type="image" id="save" src="../../<spring:theme code="register_save" />" 
	    	onmouseover="swapSaveImage('save',1)"onmouseout="swapSaveImage('save',0)"   />
		
	</form:form>
</body>
</html>