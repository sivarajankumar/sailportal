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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link href="../../<spring:theme code="teacherrunstylesheet"/>" media="screen" rel="stylesheet"
  type="text/css" />
<title><spring:message code="run.list" /></title>
<script language="JavaScript">

function popup(URL, title) {
  window.open(URL, title, 'toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=1,width=300,height=300,left = 570,top = 300');
}
</script>

</head>

<body>
<%@ include file="managementHeader.jsp"%>
<div align="left">
<div id="runContent"> 
<br />
<h3 id="headingPos">
View My Students
</h3>

<c:forEach var="run" items="${current_run_list}">
	<h4>${run.sdsOffering.name}</h4>
	<c:forEach var="period" items="${run.periods}">
		<h5>Period: ${period.name} &nbsp;<a href="batchchangestudentpassword.html?period=${period}">Change All Passwords</a></h5>
		<c:choose>
			<c:when test="${fn:length(period.members) == 0}">
				No Students Attached
			</c:when>
			<c:otherwise>
            	<c:forEach var="member" items="${period.members}">
            		<a href="changestudentpassword.html?user=${member}">${member.userDetails.firstname} ${member.userDetails.lastname}</a>&nbsp;
            	</c:forEach>
            </c:otherwise>
        </c:choose>
	</c:forEach>
</c:forEach>

</div>
</div> 

</body>
</html>