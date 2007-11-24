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
<title><spring:message code="viewmystudents.message" /></title>
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
<h2 id="headingPos">
View My Students
</h2>

<c:forEach var="run" items="${current_run_list}">
	<h3>${run.sdsOffering.name}</h3>
	<c:forEach var="period" items="${run.periods}">
		<h4>Period: ${period.name} &nbsp;<a href="#" onclick="javascript:popup('batchstudentchangepassword.html?groupId=${period.id}');">Change All Passwords</a></h4>
		<c:choose>
			<c:when test="${fn:length(period.members) == 0}">
				No Students Attached
			</c:when>
			<c:otherwise>
			    <h5>Workgroups:</h5>
			    <c:choose>
			      <c:when test="${fn:length(workgroup_map[period]) ==0}">
			        No workgroups exist in this period <br />
			      </c:when>
			      <c:otherwise>
                      <c:forEach var="workgroup" items="${workgroup_map[period]}">
					    <h5>Workgroup name: ${workgroup.sdsWorkgroup.name} | Workgroup id: ${workgroup.id}</h5>
					    <c:forEach var="workgroup_member" items="${workgroup.members}">
						${workgroup_member.userDetails.firstname} ${workgroup_member.userDetails.lastname} <a href="#" onclick="javascript:popup('changestudentpassword.html?userName=${workgroup_member.userDetails.username}');">Change Password</a>&nbsp;<a href="#" onclick="javascript:popup('changeworkgroup.html?offeringId=${run.id}&periodId=${period.id}&student=${workgroup_member.userDetails.username}&workgroupFrom=${workgroup.id}');">Change Workgroup</a><br />
					    </c:forEach>
				      </c:forEach>			      
			      </c:otherwise>
			    </c:choose>
				<h5>Students who are not in a workgroup in this period:</h5>
				<c:choose>
    		        <c:when test="${fn:length(grouplessStudents[period]) ==0}">
	  		          All students in this period are in a workgroup<br />
			        </c:when>
			        <c:otherwise>
				        <c:forEach var="mem" items="${grouplessStudents[period]}">
					        ${mem.userDetails.firstname} ${mem.userDetails.lastname} <a href="#" onclick="javascript:popup('changestudentpassword.html?userName=${mem.userDetails.username}');">Change Password</a>&nbsp<a href="#" onclick="javascript:popup('changeworkgroup.html?offeringId=${run.id}&periodId=${period.id}&student=${mem.userDetails.username}');">Change Workgroup</a><br />
				        </c:forEach>
				    </c:otherwise>
				</c:choose>
            </c:otherwise>
        </c:choose>
	</c:forEach>
</c:forEach>

</div>
</div> 

</body>
</html>