<%@ include file="../common-taglibs.jsp" %>

<tiles:insertDefinition name="default-page">

    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <tiles:putAttribute name="main">

        <h1>Student Details</h1>

        <div id="user_details">
            <img src="/webapp/common/filestreamer.html?username=${student.userDetails.username}&showIcon"/>&nbsp;<strong>${student.userDetails.firstname}&nbsp;${student.userDetails.lastname}</strong>
        </div>

        <a href="selectPedagogicalPlanForStudent.html?username=${student.userDetails.username}">Select mission to assign</a> <br/>

        <a href="viewStudentDetails.html?username=${student.userDetails.username}&pedPlan=published">Assign published mission</a>

        <c:choose>
            <c:when test="${fn:length(studentPlans) > 0}">
                <table id="studentPlanTable" border="2">
                    <tr>
                        <th>Name</th>
                    </tr>
                    <c:forEach var="studentPlan" items="${studentPlans}">
                        <tr>
                            <td><a href="viewStudentPlan.html?studentPlanId=${studentPlan.id}">${studentPlan.pedagogicalPlan.name}</a></td>
                        </tr>
                    </c:forEach>
                </table>
                <br>
            </c:when>
        </c:choose>

        <div>
            <a href="studentBuddies.html?username=${student.userDetails.username}">Buddies</a>
        </div>
        
    </tiles:putAttribute>
</tiles:insertDefinition>