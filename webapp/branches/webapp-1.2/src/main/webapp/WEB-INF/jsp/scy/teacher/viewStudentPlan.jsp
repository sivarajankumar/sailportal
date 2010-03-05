<%@ include file="../common-taglibs.jsp" %>

<tiles:insertDefinition name="default-page">

    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <tiles:putAttribute name="main">

        <h1>${studentPlan.pedagogicalPlan.name}</h1>
        <div id="user_details">
            <img src="/webapp/common/filestreamer.html?username=${studentPlan.user.userDetails.username}&showIcon"/>&nbsp;<strong>${studentPlan.user.userDetails.firstname}&nbsp;${studentPlan.user.userDetails.lastname}</strong>
        </div>

        <c:choose>
            <c:when test="${fn:length(studentPlan.studentPlannedActivities) > 0}">
                <table id="studentPlanTable" border="2">
                    <tr>
                        <th>Planned Activity</th>
                        <th>Start date</th>
                        <th>End date</th>
                        <th>Note</th>
                        <th>Associated ELO</th>
                        <th>Activity</th>
                        <th>Input to LAS</th>
                        <th>Collaborators</th>
                    </tr>
                    <c:forEach var="studentPlannedActivity" items="${studentPlan.studentPlannedActivities}">
                        <tr>
                            <td><a href="viewStudentActivityDetails.html?eloId=${studentPlannedActivity.assoicatedELO.missionMapId}&username=${studentPlan.user.userDetails.username}">${studentPlannedActivity.name}</a></td>
                            <td>${studentPlannedActivity.startDate}</td>
                            <td>${studentPlannedActivity.endDate}</td>
                            <td>${studentPlannedActivity.note}</td>
                            <td>${studentPlannedActivity.assoicatedELO.name}</td>
                            <td>${studentPlannedActivity.assoicatedELO.producedBy.name}</td>
                            <td>${studentPlannedActivity.assoicatedELO.inputTo.name}</td>

                            <td>
                                <c:forEach var="member" items="${studentPlannedActivity.members}">
                                    <img src="/webapp/common/filestreamer.html?username=${member.userDetails.username}&showIcon"/>
                                </c:forEach>
                            </td>

                        </tr>
                    </c:forEach>
                </table>
                <br>
            </c:when>
        </c:choose>

    </tiles:putAttribute>
</tiles:insertDefinition>