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
<link href="../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"
    type="text/css" />
<title><spring:message code="application.title" /></title>
<script type="text/javascript" src=".././javascript/tels/general.js"></script>
<script language="JavaScript">

function popup(URL) {
  window.open(URL, 'Select Team', 'toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=0,width=300,height=300,left = 570,top = 300');
}
</script>
</head>
<%@ include file="header.jsp"%>

<body>
<div id="columns">
<h3 style="color:rgb(100,0,0);"><spring:message code="student.project-menu" /></h3>
<div id="xsnazzy" class="bgcolorSeaBlue" style="width:220px;position:relative;
	top:20px;" >
	<b class="xtop"><b class="xb1"></b><b class="xb2"></b><b class="xb3"></b><b class="xb4"></b></b>
	<div class="border1">
		<h3 >
		<spring:message code="student.user" /><spring:message code="colon" /> 
		    <authz:authentication operation="username" />
		</h3>
		<h5>
		<spring:message code="msg" /><spring:message code="colon" />
		<spring:message code="good.morning" /><authz:authentication operation="username" />
		</h5>
		
		<div >
		<spring:message code="teacher.currentlogin" /> 
		 <%= new java.util.Date().getHours() %>
		 <spring:message code="colon" />
		 <%= new java.util.Date().getMinutes() %>
		<br />
		<spring:message code="teacher.lastlogin" /> 
		 <%= new java.util.Date() %>
		<br />
		<spring:message code="wise.language" />
		<spring:message code="colon" />
		English
		<br />
		</div>
		
		<h3><spring:message code="wise.account-options" /></h3>
		<ul class="none">
		<li>
		<a href="#"	
		onmouseover="swapImage('addproject','../<spring:theme code="student_add_project_roll" />');"
		onmouseout="swapImage('addproject','../<spring:theme code="student_add_project" />');"  
		onclick="javascript:popup('addproject.html')">
		<img id="addproject" src="../<spring:theme code="student_add_project" />" style="border:0px;"/>
		</a>
		</li>
		<li>
		<a href="#" 
		onmouseover="swapImage('studentchangepwd','../<spring:theme code="student_change_password_roll" />');"
		onmouseout="swapImage('studentchangepwd','../<spring:theme code="student_change_password" />');"  
		onclick="javascript:alert('This page is not available yet')">
		<img id="studentchangepwd" src="../<spring:theme code="student_change_password" />" style="border:0px;"/>
		</a>
		</li>
		<li>
		<a href="#" 
		onmouseover="swapImage('studentchangelang','../<spring:theme code="student_change_lang_roll" />');" 
		onmouseout="swapImage('studentchangelang','../<spring:theme code="student_change_lang" />');" 
		onclick="javascript:alert('This page is not available yet')">
		<img id="studentchangelang" src="../<spring:theme code="student_change_lang" />" style="border:0px;"/>
		</a>
		</li>
		<li>
		<a href="#" 
		onmouseover="swapImage('studentsignout','../<spring:theme code="sign_out_roll" />');" 
		onmouseout="swapImage('studentsignout','../<spring:theme code="sign_out" />');" 
		onclick="javascript:alert('This page is not available yet')">
		<img id="studentsignout" src="../<spring:theme code="sign_out" />" style="border:0px;"/>
		</a>
		</li>
		</ul>
		
	</div>
	<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b class="xb2"></b><b class="xb1"></b></b>
	
 </div>
 
<!--  COMMENTED THIS OUT because clicking on them takes you to page not found page 
 <div id="right2" class="widthAdj3 north_5" >
 
<ul id="tabnav" class="widthAdj3" style="position:relative;top:-27px;">
<li class="currentProjects bgColorLightBlue borderBottom"> <a href="enterprojectcode.html"><spring:message code="student.current-projects" /></a></li>
<li class="archivedProjects "> <a href="#"><spring:message code="student.archived-projects" /> </a></li>
</ul>

-->

<div style="width:660px;position:relative;bottom:385px;left:250px;" >

<div>
<h1>Current Runs</h1>
<table border="1">
  <thead>
    <tr>
      <th><spring:message code="run.name.heading" /></th>
      <th>Teacher name</th>
      <th><spring:message code="run.options.heading" /></th>
    </tr>
  </thead>
  <c:forEach var="run" items="${current_run_list}">
  <tr>
    <td>${run.sdsOffering.name}</td>
    <td><c:forEach var="owner" items="${run.owners}">${owner.userDetails.username}</c:forEach></td>
    <td>
    <a href="#"	onmouseover="swapImage('runproject','../<spring:theme code="run_project_roll" />');" 
    onmouseout="swapImage('runproject','../<spring:theme code="run_project" />');"
    onclick="javascript:popup('selectteam.html?runId=' + ${run.id} )">
	  <img id="runproject" src="../<spring:theme code="run_project" />" style="border:0px;" />
	</a>
    </td>
   </tr>
  </c:forEach>
</table>

<h1>Archived Runs</h1>
<table border="1">
  <thead>
    <tr>
      <th><spring:message code="run.name.heading" /></th>
      <th>Teacher name</th>
      <th><spring:message code="run.options.heading" /></th>
    </tr>
  </thead>
  <c:forEach var="run" items="${ended_run_list}">
  <tr>
    <td>${run.sdsOffering.name}</td>
    <td><c:forEach var="owner" items="${run.owners}">${owner.userDetails.username}</c:forEach></td>
    <td>
    You cannot Run an "Archived Run". You will be able to view your work on this archived run later.
    </td>
   </tr>
  </c:forEach>
</table>

</div>


<!-- ARCHANA- this doesn't work when there are more than 1 run that the student is associated to...the tables overlap.
  also, the run project button is no longer clickable for some reason.

<c:forEach var="run" items="${run_list}">

<c:forEach var="owner" items="${run.owners}">

<h3 id="left1">Projects With ${owner.userDetails.username}</h3><br />
<table border="1" id="left1" class=" south">
<tr> 
<td> <spring:message code="wise.name" /> </td>
<td> ${run.sdsOffering.name} </td> 
<td> <img src="../<spring:theme code="run_project" />" padding="0" />  </td>
</tr>
<tr> 
<td> <spring:message code="wise.period" /> </td>
<td>
<c:forEach var="period" items="${run.periods}"> 
${period.name} 

</c:forEach>
</td>
<td> <img src="../<spring:theme code="change_period" />" padding="0" /></td>
</tr>
<tr>
<td> <spring:message code="wise.team" /> </td>
<td>
 <c:forEach var="period" items="${run.periods}">
     <c:choose>
       <c:when test="${fn:length(period.members) == 0}" >
         None
       </c:when>
       <c:otherwise>
         <c:forEach var="member" items="${period.members}">${member.userDetails.firstname} ${member.userDetails.lastname}</c:forEach>
       </c:otherwise>
     </c:choose>
</c:forEach>
</td>
<td> <img src="../<spring:theme code="report_problem" />" /></td>
</tr>

<tr>
<td> <spring:message code="wise.last-use" /> </td>
<td> Tuesday, 5/23/04 </td>
<td> <img src="../<spring:theme code="archive_project" />" /></td>
</tr>
</table>

</c:forEach>
</c:forEach>
 -->

</div>

</body>
</html>


<!-- 
<h3 id="left1"> Projects with UserName1 </h3><br />
<table border="1" padding="0" id="left1" class=" south">
<tr> 
<td> <spring:message code="wise.name" /> </td>
<td> Project Name 1 </td> 
<td> <img src="../<spring:theme code="run_project" />" padding="0" />  </td>
</tr>
<tr> 
<td> <spring:message code="wise.period" /> </td>
<td> 2 </td>
<td> <img src="../<spring:theme code="change_period" />" padding="0" /></td>
</tr>
<tr>
<td> <spring:message code="wise.team" /> </td>
<td> Username 1 + Username 2 (sn1, sn2) </td>
<td> <img src="../<spring:theme code="report_problem" />" padding="0"  /></td>
</tr>

<tr>
<td> <spring:message code="wise.last-use" /> </td>
<td> Tuesday, 5/23/04 </td>
<td> <img src="../<spring:theme code="archive_project" />" padding="0"  /></td>
</tr>
</table>

<h3 class="south_1 right4"> Projects with UserName2 </h3>
<table border="1" class="right4 south_1">

<tr> 
<td> <spring:message code="wise.name" /> </td>
<td> Project Name 1 </td> 
<td> <img src="../<spring:theme code="run_project" />" /></td>

</tr>
<tr> 
<td> <spring:message code="wise.period" /> </td>
<td> 2 </td>
<td> <img src="../<spring:theme code="change_period" />" /></td>
</tr>
<tr>
<td> <spring:message code="wise.team" /> </td>
<td> Username 1 + Username 2 (sn1, sn2) </td>
<td> <img src="../<spring:theme code="report_problem" />" /></td>
</tr>
<tr>
<td> <spring:message code="wise.last-use" /> </td>
<td> Tuesday, 5/23/04 </td>
<td> <img src="../<spring:theme code="archive_project" />" /></td>
</tr>
</table>
 -->
 




<!-- 
  <c:forEach var="run" items="${run_list}">
  <tr>
    <td>${run.sdsOffering.name}</td>
    <td><table border="1">
          <tr>
            <th><spring:message code="run.period" /></th>
            <th><spring:message code="teacher.project-code" /></th>
            <th><spring:message code="run.students" /></th>
          </tr>
          <c:forEach var="period" items="${run.periods}">
            <tr>
              <td>${period.name}</td>
              <td>${run.runcode}-${period.name}</td>
              <td>
                <c:choose>
                  <c:when test="${fn:length(period.members) == 0}" >
                    None
                  </c:when>
                  <c:otherwise>
                    <c:forEach var="member" items="${period.members}">${member.userDetails.firstname} ${member.userDetails.lastname}</c:forEach>
                  </c:otherwise>
                </c:choose>
              </td>
            </tr>
          </c:forEach>
        </table></td>
    <td>
      <c:choose>
        <c:when test="${fn:length(workgroup_map[run]) == 0}" >
        <spring:message code="no.workgroups" />
        </c:when>
        <c:otherwise>
            <c:forEach var="workgroup" items="${workgroup_map[run]}">
              <a href="${http_transport.baseUrl}/offering/${run.sdsOffering.sdsObjectId}/jnlp/${workgroup.sdsWorkgroup.sdsObjectId}">${workgroup.sdsWorkgroup.name}</a><br />
            </c:forEach>
        </c:otherwise>
      </c:choose>
    </td>
   </tr>
  </c:forEach>
</table>
  
<table border="1">
  <thead>
    <tr>
      <th><spring:message code="run.name.heading" /></th>
      <th>Teacher name</th>
      <th><spring:message code="run.options.heading" /></th>
    </tr>
  </thead>
  <c:forEach var="run" items="${run_list}">
  <tr>
  
    <td>${run.sdsOffering.name}</td>
    <td><c:forEach var="owner" items="${run.owners}">${owner.userDetails.username}</c:forEach></td>
    <td>
    <a href="#"	onclick="javascript:popup('selectteam.html?runId=' + ${run.id} )">
	  <img id="runproject" src="../<spring:theme code="run_project" />" />
	</a>
       
      <c:choose>
        <c:when test="${fn:length(workgroup_map[run]) == 0}" >
        <spring:message code="not.in.workgroup.yet" />
        </c:when>
        <c:otherwise>
            <c:forEach var="workgroup" items="${workgroup_map[run]}">
              <a href="${http_transport.baseUrl}/offering/${run.sdsOffering.sdsObjectId}/jnlp/${workgroup.sdsWorkgroup.sdsObjectId}">
		        <img id="runproject" src="../<spring:theme code="run_project" />"  />              
              </a><br />
            </c:forEach>
        </c:otherwise>
      </c:choose>
    
              
    </td>
   </tr>
  </c:forEach>
</table>
  -->