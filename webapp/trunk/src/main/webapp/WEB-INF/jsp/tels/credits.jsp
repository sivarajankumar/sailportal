<%@ include file="include.jsp"%>

<!-- $Id: previewproject.jsp 323 2007-04-21 18:08:49Z hiroki $ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet" type="text/css" />
<link href="<spring:theme code="registerstylesheet"/>" media="screen" rel="stylesheet" type="text/css" />

  
<title>Preview A Project</title>

<script type="text/javascript" src="./javascript/pas/utils.js"></script> 
<script type="text/javascript" src="./javascript/tels/general.js"></script>

</head>

<body>

<div id="centeredDiv">

<%@ include file="headermain_nousername.jsp"%>

<div id="previewProjectTitle">
	<h1 class="blueText">Credits</h1>
</div>

<div id="boxPreviewProject">

<div id="previewProjectHeader2">The WISE 3.0 development project includes a wide variety of partners in the open-source community... MORE TO COME. </div>

<div id="previewProjectDetails">
	<div>UC Berkeley Team Members</div>
	<p>Name 1, Name 2, etc...</p>
</div>

<div id="previewProjectDetails">
	<div>University of Toronto</div>
	<p>Name 1, Name 2, etc...</p>
</div>

<div id="previewProjectDetails">
	<div>Concord Consortium</div>
	<p>Name 1, Name 2, etc...</p>
</div>



</div>   <!--  end of boxNewAccountReg -->

<div style="text-align:center;"><a href="index.html"> <img id="return"
	src="<spring:theme code="return_to_homepage" />"
	onmouseover="swapImage('return', '<spring:theme code="return_to_homepage_roll" />');"
	onmouseout="swapImage('return', '<spring:theme code="return_to_homepage" />');" /></a></div>
	
</div>   <!-- end of centered div-->
   
</body>
</html>


