<%@ include file="include.jsp"%>

<!-- $Id: signup.jsp 323 2007-04-21 18:08:49Z hiroki $ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="<spring:theme code="registerstylesheet"/>" media="screen" rel="stylesheet" type="text/css" />
  
<title><spring:message code="signup.title" /></title>

<script type="text/javascript" src="./javascript/pas/utils.js"></script> 
<script type="text/javascript" src="./javascript/tels/general.js"></script>

</head>

<body>

<div id="centeredDiv">

<%@ include file="headermain.jsp"%>

<div id="registrationTitle">
	<h1 class="blueText">New Account Registration</h1>
</div>

<div id="boxNewAccountReg">

<div id="questionPromptReg" class="center">What sort of account would you like to create?</div>

<div id="newAccountButtons">
<ul>
	<li>
	<a href="student/registerstudent.html">
	<img id="createstudentacct" src="<spring:theme code="create_student_account" />" 
    onmouseover="swapImage('createstudentacct','<spring:theme code="create_student_account_rollover" />')" 
    onmouseout="swapImage('createstudentacct','<spring:theme code="create_student_account" />')"/></a>
	</li>
</ul>
<ul>
	<li><a href="teacher/registerteacher.html"> 
	<img id="createteacheracct" src="<spring:theme code="create_teacher_account" />" 
    onmouseover="swapImage('createteacheracct','<spring:theme code="create_teacher_account_rollover" />')" 
    onmouseout="swapImage('createteacheracct','<spring:theme code="create_teacher_account" />')"/></a> 
	</li>

</ul>
</div>

<div id="newAccountDetails">
	<h4><em> <spring:message code="register.which-account" /></em> </h4>
	<ul>
	<h5><li id="registerDescAcctSpacing"> <spring:message code="register.student-account-desc" /></li>
	<li id="registerDescAcctSpacing"> <spring:message code="register.teacher-account-desc" /></li></h5>
	</ul>
</div>

</div>   <!--  end of boxNewAccountReg -->

	<p id="homelink"><a href="index.html">Return to Home Page</a></p>
	
</div>   <!-- end of centered div-->
   
</body>
</html>


