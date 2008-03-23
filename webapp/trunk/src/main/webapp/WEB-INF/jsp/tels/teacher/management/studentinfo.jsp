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

<script type="text/javascript">

		
</script>


<div id="centeredDiv">

<div id="L3Label">Student Info</div> 

<dl id="list2">
	First Name: <c:out value="${userInfoMap['First Name']}"/><br>
	Last Name: <c:out value="${userInfoMap['Last Name']}"/><br>
	Last Login: <c:out value="${userInfoMap['Last Login']}"/><br>
	Sign Up Date: <c:out value="${userInfoMap['Sign Up Date']}"/><br>
	Gender: <c:out value="${userInfoMap['Gender']}"/><br>
	Birthday: <c:out value="${userInfoMap['Birthday']}"/><br>
	Number of Logins: <c:out value="${userInfoMap['Number of Logins']}"/><br>

<!--	<dt>Full Name:</dt>-->
<!--	<dd>${userDetails.firstname} ${userDetails.lastname}</dd>-->
<!--	<dt>Username:</dt>-->
<!--	<dd></dd>-->
<!--	<dt class="listTitle2">Joined WISE on:</dt>-->
<!--	<dd">${userDetails.signupdate} test</dd>-->
<!--	<dt class="listTitle2">Birthday:</dt> -->
<!--	<dd>${userDetails.birthday}</dd>-->
</dl>


</div>     <!--End of Centered Div-->
 
</body>
</html>