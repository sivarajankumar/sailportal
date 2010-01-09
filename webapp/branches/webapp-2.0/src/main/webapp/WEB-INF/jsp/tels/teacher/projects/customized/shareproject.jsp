<%@ include file="../../include.jsp" %> 

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

<!-- $Id: overview.jsp 997 2007-09-05 16:52:39Z archana $ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >

<html lang="en">
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8"></meta>

<link href="../../../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../../<spring:theme code="teacherprojectstylesheet" />" media="screen" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../../.././javascript/tels/general.js"></script>
 
<title><spring:message code="teacher.pro.custom.sharepro.1"/></title>
<script type="text/javascript">
//extend Array prototype
Array.prototype.contains = function(obj) {
	  var i = this.length;
	  while (i--) {
	    if (this[i] === obj) {
	      return true;
	    }
	  }
	  return false;
}

var teacherUsernamesString = "${teacher_usernames}";
var teacherUsernames = teacherUsernamesString.split(":");
teacherUsernames = teacherUsernames.sort();

// updates the search input box with the specified text
function updateInputBox(text) {
	document.getElementById("sharedOwnerUsernameInput").value=text;
}
function autocomplete(username) {
	var matchedUsernameUL = document.getElementById("matchedUsernames");
	matchedUsernameUL.innerHTML = "";
	if (username.length > 0) {
		var resultArray = findStringsContaining(username, teacherUsernames);
		for (k=0; k < resultArray.length; k++) {
			var matchedUsernameLI = document.createElement("<li>");
			matchedUsernameLI.innerHTML = "<a onclick='updateInputBox(\""+resultArray[k]+"\")'>" + resultArray[k] + "</a>";
			matchedUsernameUL.appendChild(matchedUsernameLI);
		}
	}
}


// returns an array of strings that contain what
function findStringsContaining(what, all_array) {
	var resultArray = new Array();
	for (i=0; i < all_array.length; i++) {
		if (all_array[i].toLowerCase().indexOf(what.toLowerCase()) > -1) {
			resultArray.push(all_array[i]);
		}
	}	
	return resultArray;
}
</script>

<!-- SuperFish drop-down menu from http://www.electrictoolbox.com/jquery-superfish-menus-plugin/  -->

<link rel="stylesheet" type="text/css" href="../../../themes/tels/default/styles/teacher/superfish.css" media="screen">
<script type="text/javascript" src="../../../javascript/tels/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="../../../javascript/tels/superfish.js"></script>

<script type="text/javascript">
    
            // initialise plugins
            jQuery(function(){
                jQuery('ul.sf-menu').superfish();
            });
    
</script>

</head>

<body>

<div id="centeredDiv">

<%@ include file="../../headerteacher.jsp"%> 

<div id="navigationSubHeader2">Sharing a Project<span id="navigationSubHeader1">projects</span></div> 

<div style="text-align:center;">   <!--This bad boy ensures centering of block level elements in IE. -->

<h2 id="titleBar" class="headerText"><spring:message code="teacher.pro.custom.sharepro.2"/></h2> 

<div class="sharedprojectHeadline1"><spring:message code="teacher.pro.custom.sharepro.3"/></div>

 <table id="customProjectTable" border="1" cellpadding="0" cellspacing="0">
				    <tr>
				        <th><spring:message code="teacher.pro.custom.index.19"/></th>
				        <th><spring:message code="teacher.pro.custom.index.20"/></th>
				        <th><spring:message code="teacher.pro.custom.index.21"/></th>
				        <th><spring:message code="teacher.pro.custom.index.22"/></th>
						<th><spring:message code="teacher.pro.custom.index.23"/></th>
						<th><spring:message code="teacher.pro.custom.index.24"/></th>
						<th><spring:message code="teacher.pro.custom.index.25"/></th>
						<th><spring:message code="teacher.pro.custom.index.26"/></th>
				    </tr>
				  
				  <tr id="customProjectR2">
				    <td class="customProjectTitle">${project.name}</td>
				    <td class="dataText">${project.projectInfo.subject} ${project.projectInfo.keywords}</td>
				    <td class="dataText">${project.id}</td>
				    <td class="dataText">${project.projectInfo.projectLiveCycle }</td>
				    <td class="smallText1">UC Berkeley library project</td>
				    <td class="dataText">${project.projectInfo.gradeLevel}</td>
				    <td class="dataTime">[6 hours]</td>
				    <td class="dataTime">[5 hours]</td>
				  </tr>
				   
				</table>
				
<div class="sharedprojectHeadline1"><spring:message code="teacher.pro.custom.sharepro.4"/></div>			

<table id="sharedProjectPermissions">

	<tr>
		<th><spring:message code="teacher.pro.custom.sharepro.5"/></th>
		<th><spring:message code="teacher.pro.custom.sharepro.6"/></th> 
	</tr>
	<tr>
		<c:choose>
			<c:when test="${fn:length(project.owners) == 0 }">
			</c:when>
			<c:otherwise>
				<c:forEach var="owner" items="${project.owners }">
					<td class="sharedUserName">${owner.userDetails.username}</td>
					<td><spring:message code="teacher.pro.custom.sharepro.7"/></td>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</tr>
	
	<c:choose>
		<c:when test="${fn:length(project.sharedowners) == 0}">
		</c:when>
		<c:otherwise>
			<c:forEach var="sharedowner" items="${project.sharedowners}">
				<form:form method="post" id="${sharedowner.userDetails.username}"
					commandName="${sharedowner.userDetails.username}">
					<form:hidden path="sharedOwnerUsername" />
					<tr>
						<td>${sharedowner.userDetails.username}</td>
						<td align="left">		
						<form:radiobutton path="permission"
							onclick="javscript:this.form.submit();" value="ROLE_READ_PROJECT" />Can Run the project<br />
						<form:radiobutton path="permission"
							onclick="javscript:this.form.submit();" value="ROLE_WRITE_PROJECT" />Can Run + Edit the project<br />
						<sec:authorize ifAllGranted="ROLE_USER">
						   <sec:authorize ifAllGranted="ROLE_ADMINISTRATOR">
							<form:radiobutton path="permission"
								onclick="javscript:this.form.submit();" value="ROLE_SHARE_PROJECT" />Can Run + Edit + Share the project<br />
							</sec:authorize>
						   <sec:authorize ifNotGranted="ROLE_ADMINISTRATOR">
								<sec:accesscontrollist domainObject="${project}" hasPermission="16">												
							        <form:radiobutton path="permission"
								        onclick="javscript:this.form.submit();" value="ROLE_SHARE_PROJECT" />Can Run + Edit + Share the project<br />								
								</sec:accesscontrollist>					
							</sec:authorize>							
					    </sec:authorize>									
						</td>
						<td><!-- <a href='#' onclick="return confirm('Proceed with removing this shared teacher?');">Remove this User</a> -->
						<a href='#'
							onclick="alert('Remove Shared Teacher is not yet implemented.');"><spring:message
							code="teacher.run.shareprojectrun.16" /></a></td>
					</tr>
				</form:form>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	
	<tr>
		<td id="sharingSearchBox" colspan=2>
			<div id="sharingSearchBoxHelp"><spring:message code="teacher.pro.custom.sharepro.12"/></div>
				<form:form method="post" commandName="addSharedTeacherParameters">
					<form:input path="sharedOwnerUsername" id="sharedOwnerUsernameInput" onkeyup="autocomplete(this.value)" size="25"/>
					<input type="submit" value="<spring:message code="teacher.pro.custom.sharepro.13"/>"></input>
				</form:form>
				<ul id="matchedUsernames">
				</ul>
		</td>
	</tr>

</table> 

<h5><a href="../customized/index.html#actionsCurrent"><spring:message code="teacher.pro.custom.sharepro.14"/>&nbsp;<em><spring:message code="teacher.pro.custom.sharepro.15"/></em></a></h5>

</div>
</div>
<c:if test="${not empty message}">
<script type="text/javascript">
 alert("${message}");
</script>
</c:if>

</body>
</html>

