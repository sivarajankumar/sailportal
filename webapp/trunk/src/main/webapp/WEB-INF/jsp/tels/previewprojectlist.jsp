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

<h1 id="previewProjectTitle" class="blueText">Preview A Project</h1>

<div id="boxPreviewProject">

<div id="previewProjectHeader2">Want to instantly preview a project, without needing to register?</div>

<div id="previewProjectDetails">
	<ul>
		<li>Click <em>Preview Project</em> below to begin downloading a secure Java-based preview.   
				This download process can take several minutes, depending on your connection bandwidth. 
				Click the <em>Trust</em> button to launch the Java program.</li>
		<li>Click <em>Check My Computer</em> to see if your computer has the technical requirements to run a particular project.  
				A secure Java-based diagnostic program will begin loading. 
				Click the <em>Trust</em> button to launch the Java program.</li>
		<li>In Preview mode you can explore the project and create work, but none of this temporary work will be saved. 
				When registered teachers or students use a WISE 3.0 project all work is saved to their personalized account.</li>
		<li>Review the <a href="http://www.telscenter.org/confluence/display/WPSD/Classroom+computer+lab+requirements+to+run+SAIL+projects">WISE 3.0 System Requirements</a> for details on minimum hardware/software requirements across Windows and 
				Macintosh computers.</li>
		<li ><span style="font-weight:bold; color:#FF0000;">Macintosh Users:</span> A known problem with Java may stall your download/opening of the Java "JNLP" file.  
		<br/>Please visit the <a href="http://www.telscenter.org/confluence/display/WPSD/Check+your+System+for+Compatibility+with+WISE+3">Check Your System for Compatibility with WISE 3</a> help section.
		Then follow the instructions in the "Applying Macintosh Java patch" section.</li>
	</ul> 
</div>

<table id="previewProjectTable" width="80%" border="1" cellpadding="5" summary="Displays W3 Projects that can be instantly previewed (no user data saved; registration not required).">
   <tr>
    <th>Title</th>
    <th>Curriculum</th>
    <th>Grade</th>
    <th>Action</th>
  </tr>
<c:forEach var="project" items="${projectList}">
  <tr>
  <td><c:out value="${project.curnit.sdsCurnit.name}"/></td>
  <td>[curriculum goes here]</td>
  <td>[grade goes here]</td>
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


