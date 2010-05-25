<%@ include file="../common-taglibs.jsp" %>
<sec:authorize ifAllGranted="ROLE_USER">
	<sec:authorize ifAllGranted="ROLE_STUDENT">
		<li><a href="/webapp/app/playful/elorefs.html">Playful Assessment</a></li>
	</sec:authorize>

	<sec:authorize ifAllGranted="ROLE_TEACHER">
		<li><a href="/webapp/teacher/studentList.html">Student list</a></li>
	</sec:authorize>

	<sec:authorize ifAllGranted="ROLE_ADMINISTRATOR">
		<li><a href="/webapp/admin/index.html"><spring:message code="header.admin"/></a></li>
	</sec:authorize>

	<sec:authorize ifAllGranted="ROLE_ADMINISTRATOR">
		<li><a href="/webapp/scenarios.html">Scenarios</a></li>
	</sec:authorize>

	<sec:authorize ifAllGranted="ROLE_ADMINISTRATOR">
		<li><a href="/webapp/app/useradmin/UserAdminIndex.html">Manage users</a></li>
	</sec:authorize>

	<sec:authorize ifAllGranted="ROLE_TEACHER">
		<li><a href="/webapp/app/scyauthor/ScyAuthorIndex.html">SCYAuthor</a></li>
	</sec:authorize>

	<sec:authorize ifAllGranted="ROLE_TEACHER">
		<li><a href="/webapp/app/scyauthorruntime/ScyAuthorRuntimeIndex.html">SCYAuthor Runtime</a></li>
	</sec:authorize>

	<sec:authorize ifAllGranted="ROLE_TEACHER">
		<li><a href="/webapp/app/tools/Toolbank.html">Tools</a></li>
	</sec:authorize>

	<sec:authorize ifAllGranted="ROLE_USER">
		<li><a href="/webapp/student/profile.html">Profile</a></li>
	</sec:authorize>
</sec:authorize>