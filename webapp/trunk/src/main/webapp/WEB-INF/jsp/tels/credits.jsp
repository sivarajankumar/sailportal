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

<%@ include file="headermain_returntohomepage.jsp"%>

<h1 id="previewProjectTitle" class="blueText">Credits</h1>

<div id="boxPreviewProject">

<div id="creditsIntroText">The WISE 3.0 open-source system stems from the dedicated efforts of wide-ranging community of 
software development teams. The multidisiplenary colloboration of these teams, spanning a range of technolo are a reflection of the colloborative effort  </div>

<div id="creditsTeamBox">
	<h2 id="creditsTeamHeader">University of California, Berkeley</h2>
	<h6>(reverse alphabetical order)</h6> 
	<ul id="creditsTeamType">
		<li><a href="mailto:hirochan@berkeley.edu">Hiroki Terashima</a> &nbsp; (Lead Programmer)</li>
		<li><a href="mailto:hirochan@berkeley.edu">Tony Perritano</a> &nbsp; (Senior Programmer)</li>
		<li><a href="mailto:mlinn@berkeley.edu">Marcia Linn</a>  &nbsp;(WISE Director)</li>
		<li><a href="mailto:geoffreykwan@gmail.com">Geoffrey Kwan</a> &nbsp;(Programmer)</li>
		<li><a href="mailto:dougkirk@berkeley.edu">Doug Kirpatrick</a> &nbsp;(Teacher/Classroom Manager)</li>
		<li><a href="#">Patrick ?</a> &nbsp;(Asst. Programmer)</li>
		<li><a href="#">Sally ?</a> &nbsp;(Asst. Programmer))</li>
		<li><a href="mailto:fish771@yahoo.com">Matt Fishbach</a> &nbsp;(Lead Interface/Interactive Design)</li>
		<li><a href="mailto:kbenneman@berkeley.edu">Kathy Benneman</a> &nbsp;(Program Manager)</li>

		
	</ol>
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


