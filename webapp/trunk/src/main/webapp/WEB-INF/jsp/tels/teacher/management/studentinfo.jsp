<%@ include file="include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="../../<spring:theme code="yui-fonts-min-stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="yui-container-stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />

<script type="text/javascript" src="../.././javascript/tels/general.js"></script>

<title>Student Info</title>

<!-- FOR LATER REFACTOR <script src="../../../javascript/tels/custom-yui/changegroupdnd.js" type="text/javascript"> </script> -->

<%@ include file="../grading/styles.jsp"%>
<script type="text/javascript" src="../.././javascript/tels/yui/yahoo/yahoo.js"></script>
<script type="text/javascript" src="../.././javascript/tels/yui/event/event.js"></script>  
<script type="text/javascript" src="../.././javascript/tels/yui/connection/connection.js"></script> 
<script type="text/javascript" src="../.././javascript/tels/utils.js"></script>
<script type="text/javascript" src="../.././javascript/tels/teacher/management/viewmystudents.js"></script>

<link href="../../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="teacherprojectstylesheet" />" media="screen" rel="stylesheet" type="text/css" />
<link href="../../<spring:theme code="viewmystudentsstylesheet"/>" media="screen" rel="stylesheet" type="text/css" /><link href="../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet" type="text/css" />


</head>

<body class="yui-skin-sam">

<div id="studentInfoNameHeader">student information</div> 

<table id="studentInfoTable">
	<tr>
		<th>Full Name:</th>
		<td><c:out value="${userInfoMap['First Name']}"/>&nbsp;<c:out value="${userInfoMap['Last Name']}"/> </td>
	</tr>
	<tr>
		<th>WISE Username:</th>
		<td>[Need Student's UserName here]</td>
	</tr>
	<tr>
		<th>Most Recent Sign In:</th>
		<td><c:out value="${userInfoMap['Last Login']}" /> [Need to Revise]</td>
			</tr>
	<tr>
		<th>WISE Registration Date:</th>
		<td><c:out value="${userInfoMap['Sign Up Date']}"/> [Need to Revise]</td>
	</tr>
	<tr>
		<th>Gender:</th>
		<td><span style="text-transform:lowercase;"><c:out value="${userInfoMap['Gender']}"/></span></td> 
	</tr>
	<tr>
		<th>Birthday:<br/><span class="studentInfoSmallText">(as entered during registration)</span></th>
		<td><c:out value="${userInfoMap['Birthday']}"/>[Need to Revise]</td>
	</tr>
	<tr>
		<th>Number of Logins:</th>
		<td><c:out value="${userInfoMap['Number of Logins']}"/></td>
	</tr>
</table>

<div class="center" >
 		 <a href="#" onclick="self.close();return false;">
 		 <img src="../../themes/tels/default/images/teacher/Close-Window.png" alt="Close Terms of Use" class="imgNoBorder" /></a> 
</div> 
    
 
</body>
</html>