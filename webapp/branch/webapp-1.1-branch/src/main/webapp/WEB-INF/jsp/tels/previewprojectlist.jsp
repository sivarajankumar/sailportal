<%@ include file="include.jsp"%>

<!-- $Id$ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="<spring:theme code="registerstylesheet"/>" media="screen" rel="stylesheet" type="text/css" />

<title>Preview A Project</title>

<script type="text/javascript" src="./javascript/pas/utils.js"></script> 
<script type="text/javascript" src="./javascript/tels/general.js"></script>

</head>

<body>

<div id="centeredDiv">

<%@ include file="headermain_nousername.jsp"%>

<div style="text-align:center;">   
<!--This bad boy ensures centering of block level elements in IE (avoiding margin:auto bug).  -->

<h1 id="previewProjectTitle" class="blueText"><spring:message code="previewprojectlist.1"/></h1>

<div id="boxPreviewProject">

<div id="previewProjectHeader2"><spring:message code="previewprojectlist.2"/></div>

<div id="previewProjectDetails">
	<ul>
		<li><spring:message code="previewprojectlist.3"/> <em><spring:message code="previewprojectlist.4"/></em>&nbsp;<spring:message code="previewprojectlist.5"/> <em><spring:message code="previewprojectlist.6"/></em> <spring:message code="previewprojectlist.7"/></li>
		<li><spring:message code="previewprojectlist.8"/> <em><spring:message code="previewprojectlist.9"/></em>&nbsp;<spring:message code="previewprojectlist.10"/> <em><spring:message code="previewprojectlist.11"/></em> <spring:message code="previewprojectlist.12"/></li>
		<li><spring:message code="previewprojectlist.13"/></li>
		<li><spring:message code="previewprojectlist.14"/> <a href="http://www.telscenter.org/confluence/display/WPSD/Classroom+computer+lab+requirements+to+run+SAIL+projects"><spring:message code="previewprojectlist.15"/></a> <spring:message code="previewprojectlist.16"/></li>
		<li ><span style="font-weight:bold; color:#FF0000;"><spring:message code="previewprojectlist.17"/></span> <spring:message code="previewprojectlist.18"/> <a href="http://www.telscenter.org/confluence/display/WPSD/Check+your+System+for+Compatibility+with+WISE+3"><spring:message code="previewprojectlist.19"/></a> <spring:message code="previewprojectlist.20"/></li>
	</ul> 
</div>

<table id="previewProjectTable" width="80%" border="1" cellpadding="5" summary="Displays W3 Projects that can be instantly previewed (no user data saved; registration not required).">
   <tr>
    <th><spring:message code="previewprojectlist.21"/></th>
    <th><spring:message code="previewprojectlist.22"/></th>
    <th><spring:message code="previewprojectlist.23"/></th>
    <th><spring:message code="previewprojectlist.24"/></th>
  </tr>
<c:forEach var="project" items="${projectList}">
  <tr>
  <td><c:out value="${project.projectInfo.name}"/></td>
  <td>${project.projectInfo.subject}, ${project.projectInfo.keywords}</td>
  <td>${project.projectInfo.gradeLevel}</td>
  <td id="previewActionLinks"><a href="<c:url value="previewproject.html"><c:param name="projectId" value="${project.id}"/></c:url>">
	       Preview Project
      </a> <br /> 
      <a href="<c:url value="http://tels-develop.soe.berkeley.edu:8080/maven-jnlp-snapshot/jnlp-tests/jardiff/javachecker-1.1.jnlp"></c:url>">
           Check My Computer's Compatibility with this Project
      </a>
  </td>
  </tr>
</c:forEach>
</table>

</div>   <!--  end of boxNewAccountReg -->

<div style="text-align:center;"><a href="index.html"> <img id="return"
	src="<spring:theme code="return_to_homepage" />"
	onmouseover="swapImage('return', '<spring:theme code="return_to_homepage_roll" />');"
	onmouseout="swapImage('return', '<spring:theme code="return_to_homepage" />');" /></a></div>

</div>
</div>   <!-- end of centered div-->
   
</body>
</html>


