<%@ include file="../common-taglibs.jsp" %>

<tiles:insertDefinition name="default-page">

    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <tiles:putAttribute name="main">

        <h1>Select mission for ${student.userDetails.firstName}</h1>

        <c:choose>
            <c:when test="${fn:length(pedagogicalPlans) > 0}">
                <table id="pedagogicalPlanTable" border="2">
                    <tr>
                        <th>Name</th>
                        <th>Select</th>
                    </tr>
                    <c:forEach var="pedagogicalPlan" items="${pedagogicalPlans}">
                        <tr>
                            <td>${pedagogicalPlan.name}</td>
                            <td><a href="viewStudentDetails.html?username=${student.userDetails.username}&pedPlanId=${pedagogicalPlan.id}">Assign</a> </td>
                        </tr>
                    </c:forEach>
                </table>
                <br>
            </c:when>
        </c:choose>

    </tiles:putAttribute>
</tiles:insertDefinition>