<%@ include file="../common-taglibs.jsp" %>
<sec:authorize ifAllGranted="ROLE_USER">
	<sec:authorize ifAllGranted="ROLE_STUDENT">
		<a href="student/index.html"><spring:message code="header.student"/></a>
	</sec:authorize>
	<sec:authorize ifAllGranted="ROLE_TEACHER">
		<a href="teacher/index.html"><spring:message code="header.teacher"/></a>
	</sec:authorize>
	<sec:authorize ifAllGranted="ROLE_ADMINISTRATOR">
		<a href="admin/index.html"><spring:message code="header.admin"/></a>
	</sec:authorize>
	<span class="loginstatus">
	signed in in as <strong><sec:authentication property="principal.username"/></strong>
	</span>
	<a class="logout" href="<c:url value="/j_spring_security_logout"/>"><spring:message code="log.out"/></a>
</sec:authorize>
<sec:authorize ifNotGranted="ROLE_USER">
	<span class="loginstatus">not signed in</span> <a href="<c:url value="/webapp/login.html"/>"
													  class="login"><spring:message code="log.in"/></a>
</sec:authorize>