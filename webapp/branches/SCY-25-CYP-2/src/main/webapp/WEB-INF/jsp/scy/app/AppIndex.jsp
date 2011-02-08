<%@ include file="../common-taglibs.jsp" %>

<tiles:insertDefinition name="default-page">
    <tiles:putAttribute name="main">
        <div id="centeredDiv">
            <center><h1>Welcome to SCY!</h1></center>

            <c:if test="${rickRoll}">
                <object width="425" height="344"><param name="movie" value="http://www.youtube.com/v/oHg5SJYRHA0&hl=en_US&fs=1&"></param><param name="allowFullScreen" value="true"></param><param name="allowscriptaccess" value="always"></param><embed src="http://www.youtube.com/v/oHg5SJYRHA0&hl=en_US&fs=1&" type="application/x-shockwave-flash" allowscriptaccess="always" allowfullscreen="true" width="425" height="344"></embed></object>
            </c:if>

            <c:if test="${showProfilePicture}">
                <img src="/webapp/common/filestreamer.html?username=${currentUser.userDetails.username}&showIcon"/>
            </c:if>                                                                               


    <sec:authorize ifAllGranted="ROLE_USER">
        <sec:authorize ifAllGranted="ROLE_STUDENT">
            <h2>Select mission</h2>
            <table>
                <tr>
                    <td>
                        <s:runtimeMissionController runtimeELOService="${runtimeELOService}" userService="${userService}"/>
                    </td>
                </tr>

            </table>
        </sec:authorize>
        <sec:authorize ifAllGranted="ROLE_TEACHER">

            <c:choose>
            <c:when test="${fn:length(missionTransporters) > 0}">
                <table id="pedagogicalPlansTable" width="100%">
                    <tr >
                        <th>
                            Select which mission to work with
                        </th>
                        <th>
                            Number of portfolios to assess
                        </th>
                        <th>
                            Number of active students
                        </th>
                    </tr>
                    <c:forEach var="missionTransporter" items="${missionTransporters}">
                        <tr class="${oddEven.oddEven}">
                            <td>
                                <a href="/webapp/app/scyauthor/viewPedagogicalPlan.html?uri=${missionTransporter.uri}">${missionTransporter.elo.title}</a>
                            </td>
                            <td>
                                3
                            </td>
                            <td>
                                15
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <br/>
                <div class="createNewPedPlan">
                    <a href="/webapp/app/scyauthor/createnewPedplansteps/PedPlanNameController.html">Create new pedagogical plan</a>
                </div>



                <br>
            </c:when>
        </c:choose>



        </sec:authorize>
	</sec:authorize>


        </div>
    </tiles:putAttribute>
</tiles:insertDefinition> 
