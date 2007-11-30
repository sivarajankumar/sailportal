<%@ include file="include.jsp"%>

<!-- $Id: previewproject.jsp 323 2007-04-21 18:08:49Z hiroki $ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="<spring:theme code="registerstylesheet"/>" media="screen" rel="stylesheet" type="text/css" />
  
<title>Preview A Project</title>

<script type="text/javascript" src="./javascript/pas/utils.js"></script> 
<script type="text/javascript" src="./javascript/tels/general.js"></script>

</head>

<body>

<div id="centeredDiv">

<%@ include file="headermain.jsp"%>

<div id="previewProjectTitle">
	<h1 class="blueText">Preview A Project</h1>
</div>

<div id="boxPreviewProject">

<div id="previewProjectHeader2">Want an instant preview of a WISE 3.0 project, without needing to register? Just click a link below.</div>

<div id="previewProjectDetails">
	<div>NOTE:</div>
	<ul>
		<li>Clicking a link below will start the download of a secure Java-based program.  
				This download process can take several minutes, depending on your connection bandwidth.</li>
		<li>Click the <em>Trust</em> button to launch the Java program.</li>
		<li>In Preview mode you can explore the project and create work, but none of your work will be saved. 
				When students use a WISE 3.0 project all of their work is saved to a personalized account.</li>
		<li>Review the <a href="#">WISE 3.0 System Requirements</a> for details on minimum hardware/software requirements across Windows and 
				Macintosh computers.</li>
	</ul>
</div>

<table id="previewProjectTable" width="80%" border="1" cellpadding="5" summary="Displays W3 Projects that can be instantly previewed (no user data saved; registration not required).">
   <tr>
    <th>Title</th>
    <th>Curriculum</th>
    <th>Grade</th>
    <th>Action</th>
  </tr>
  <tr>
    <td>Airbags: Take the Hit!</td>
    <td>Physics</td>
    <td>Grades 10-12</td>
    <td class="center"><a href="#" style="color:#999999">Preview Project</td>
  </tr>
  <tr>
    <td>Placeholder</td>
    <td>Biology</td>
    <td>Grades 6-8</td>
    <td class="center"><a href="#" style="color:#999999">Preview Project</td>
  </tr>
  <tr>
    <td>Placeholder</td>
    <td>Biology</td>
    <td>Grades 9-11</td>
    <td class="center"><a href="#" style="color:#999999">Preview Project</td>
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


