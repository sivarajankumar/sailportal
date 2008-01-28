<%@ include file="include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet" type="text/css" />
<link href="<spring:theme code="homepagestylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
    
<script type="text/javascript" src="./javascript/tels/rotator.js"></script>
    
<title>Contact WISE General Issues</title>
</head>

<body>

<div id="centeredDiv">

<%@ include file="headermain_nousername.jsp"%>


<div id="pageTitle">Contact WISE</div>
     
<br /> <br />

<div id="pageSubtitleConfirm">

	<div>MESSAGE SENT</div>
	<div>Your message has been successfully sent to the WISE team.</div>
	<div>We will respond as quickly as possible.</div>

</div>

<br /> <br /> 


<div id="returnHomePageButton">
	<a href="index.html"> 
	<img id="return" src="<spring:theme code="return_to_homepage" />"
	onmouseover="swapImage('return', '<spring:theme code="return_to_homepage_roll" />');"
	onmouseout="swapImage('return', '<spring:theme code="return_to_homepage" />');" /></a></div>


</div>   <!--End of the CenteredDiv -->

</body>
</html>