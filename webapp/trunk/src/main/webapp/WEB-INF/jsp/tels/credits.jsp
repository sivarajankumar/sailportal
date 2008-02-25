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
software development teams. The multidisciplinary collaboration of these teams, spanning a range of technolo are a reflection of the colloborative effort  </div>

<table border="1" cellpadding="4" id="creditsTeamTable">
	<tr>  
		<td COLSPAN=2>University of California, Berkeley</td>  
	</tr>
	<tr>
		<td>Program Director</td> 
		<td>
		<ul><li>Marica Linn <a href="mlinn@berkeley.edu">email</a> <a href="#">bio</a></li></ul>
		</td> 	
	</tr> 
	<tr>
		<td>Programming Team</td> 
		<td>
			<ul>
			<li><p>(reverse alphabetical order)</p></li>
			<li>Hiroki Terashima <a href="mailto:hirochan@berkeley.edu">email</a> <a href="#">bio</a></li>
			<li>Tony Perritano <a href="mailto:hirochan@berkeley.edu">email</a> <a href="#">bio</a></li>
			<li>Geoffrey Kwan <a href="mailto:geoffreykwan@gmail.com">email</a>  <a href="#">bio</a></li>
			</ul>
			</td> 	
	</tr>
	<tr>
		<td>Programming Support</td> 
		<td>
			<ul>
			<li>Sally Ahn, Patrick Lawler, Archana Raghunathan</li>
			</ul>
			</td> 	
	</tr>
	<tr>
		<td>Interactive/Interface Design</td> 
		<td>
			<ul><li>Matt Fishbach <a href="mailto:fish771@yahoo.com">email</a> <a href="#">bio</a></li></ul>
		</td> 
	</tr>
	<tr>
		<td>Project Management</td> 
		<td>
			<ul><li>Kathy Benneman <a href="mailto:kbenneman@berkeley.edu">email</a> <a href="#">bio</a></li></ul>
		</td>  
	</tr>
	<tr>
		<td>Classroom/Teacher Support</td> 
		<td>
			<ul><li>Doug Kirkpatrick <a href="dougkirk@berkeley.edu">email</a> <a href="#">bio</a></li></ul>
		</td>  
	</tr>
	<tr>
	<td>Research Team & Contributors</td>  
		<td>
			<ul>
			<li><p>(reverse alphabetical order)</p></li>
			<li>Name A, Name B, Name C, Name D, Name E, etc etc.</li>
			</ul>
			</td> 	
	</tr>
		
	</table>

</div>   <!--  end of boxNewAccountReg -->

<div style="text-align:center;"><a href="index.html"> <img id="return"
	src="<spring:theme code="return_to_homepage" />"
	onmouseover="swapImage('return', '<spring:theme code="return_to_homepage_roll" />');"
	onmouseout="swapImage('return', '<spring:theme code="return_to_homepage" />');" /></a></div>
	
</div>   <!-- end of centered div-->
   
</body>
</html>


