<%@ include file="common-taglibs.jsp" %>
<tiles:insertDefinition name="default-page">
	<tiles:putAttribute name="main">
		<p><a href="student/registerstudent.html" title="<spring:message code="signup.3"/>">Create new Student
			account</a></p>

		<p><a href="teacher/registerteacher.html" title="<spring:message code="signup.4"/>">Create new Teacher
			account</a></p>
	</tiles:putAttribute>
</tiles:insertDefinition>
