<%@ include file="../common-taglibs.jsp" %>

<tiles:insertDefinition name="default-page">

    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <tiles:putAttribute name="main">

        <h1>Student Details</h1>

        <table>
            <tr>
                <td>
                    Username
                </td>
                <td>
                    ${student.userDetails.username}
                </td>
            </tr>
            <tr>
                <td>
                    Name
                </td>
                <td>
                    ${student.userDetails.firstname}
                </td>
            </tr>
        </table>

        <a href="selectPedagogicalPlanForStudent.html?username=${student.userDetails.username}">Assign mission</a>

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
        
    </tiles:putAttribute>
</tiles:insertDefinition>