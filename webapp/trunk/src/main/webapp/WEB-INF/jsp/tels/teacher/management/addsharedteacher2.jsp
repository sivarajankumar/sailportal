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

Step 2: Check the permissions you wish 
<br>
${sharedOwner.userDetails.firstname} ${sharedOwner.userDetails.lastname} to have

	<c:choose>
		<c:when test="${fn:length(allRoles) == 0}">
			No roles available
		</c:when>
		<c:otherwise>
	        <c:forEach items="${allRoles}" var="role">
	          <form:checkbox path="${allRoles}" value="${role}" /> ${role}<br/>
	        </c:forEach> 
		</c:otherwise>
	</c:choose>
</body>
</html>