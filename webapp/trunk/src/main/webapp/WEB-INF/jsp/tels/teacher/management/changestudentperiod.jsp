<%@ include file="include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="../../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet" type="text/css" />
<link href="../../<spring:theme code="teacherprojectstylesheet" />" media="screen" rel="stylesheet" type="text/css" />
    
<script type="text/javascript" src="../../javascript/tels/general.js"></script>
<script type="text/javascript" src="../../javascript/tels/effects.js"></script>

    
<title><spring:message code="application.title" /></title>
</head>
<body>

<h1>Change Student's Period</h1>
 
<div id="popUpWindowTeacherPeriod">
	
	<form:form method="post" action="changestudentperiod.html" commandName="changePeriodParameters" id="changestudentperiod">
		Student: ${changePeriodParameters.student.sdsUser.firstName} ${changePeriodParameters.student.sdsUser.lastName }<br>
		Change From: ${changePeriodParameters.projectcode} <br>
		Change To:
		<form:select path="projectcodeTo" id="projectcodeTo">
			<c:forEach items="${changePeriodParameters.run.periods}" var="period">
				<form:option value="${period.name}">
					${period.name}
				</form:option>
			</c:forEach>
		</form:select>	
	<br>
	
    <div id="teacherPeriodButtons">
    
		    <input type="image" id="teachersave" src="../../<spring:theme code="register_save" />" 
		    onmouseover="swapImage('teachersave','../../<spring:theme code="register_save_roll" />')" 
		    onmouseout="swapImage('teachersave','../../<spring:theme code="register_save" />')"/>
		    
		    <a href="index.html" onclick="javascript:window.close()">
		    <input type="image" id="teachercancel" src="../../<spring:theme code="register_cancel" />" 
		    onmouseover="swapImage('teachercancel','../../<spring:theme code="register_cancel_roll" />')" 
		    onmouseout="swapImage('teachercancel','../../<spring:theme code="register_cancel" />')"
		    /> </a>
    </div>
	</form:form>
 	</div>

</body>
</html>