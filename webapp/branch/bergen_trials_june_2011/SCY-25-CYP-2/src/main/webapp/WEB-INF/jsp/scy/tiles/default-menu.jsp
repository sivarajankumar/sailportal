<%@ include file="../common-taglibs.jsp" %>
<sec:authorize ifAllGranted="ROLE_USER">

	<sec:authorize ifAllGranted="ROLE_STUDENT">
		<li><a href="/webapp/app/feedback/feedbackindex.html">SCYFeedback</a></li>
	</sec:authorize>

	<sec:authorize ifAllGranted="ROLE_STUDENT">
		<li><a href="/webapp/app/eportfolio/EPortfolioIndex.html">SCYEportfolio</a></li>
	</sec:authorize>

	<sec:authorize ifAllGranted="ROLE_TEACHER">
		<li><a href="/webapp/teacher/studentList.html">User list</a></li>
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
		<li><a href="/webapp/app/scyauthor/ScyAuthorIndex.html">SCYAuthor Finetune</a></li>
	</sec:authorize>

	<sec:authorize ifAllGranted="ROLE_TEACHER">
		<li><a href="/webapp/app/scyauthorruntime/ScyAuthorRuntimeIndex.html">SCYAuthor Runtime</a></li>
	</sec:authorize>


	<sec:authorize ifAllGranted="ROLE_USER">
		<li><a href="/webapp/editors/userProfileEditor.html">Profile</a></li>
	</sec:authorize>
</sec:authorize>