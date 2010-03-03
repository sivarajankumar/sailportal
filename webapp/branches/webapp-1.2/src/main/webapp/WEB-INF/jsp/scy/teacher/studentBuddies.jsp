<%@ include file="../common-taglibs.jsp" %>

<tiles:insertDefinition name="default-page">

    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <tiles:putAttribute name="main">

        <h1>Buddies for ${student.userDetails.username}</h1>

        <table>
            <tr>
                <td>Host</td>
                <td>${openfireHost}</td>
            </tr>
        </table>

        <h2>Buddies</h2>
        <c:choose>
            <c:when test="${fn:length(buddies) > 0}">
                <table id="buddyTable" border="2">
                    <tr>
                        <th>Buddy</th>
                    </tr>
                    <c:forEach var="buddy" items="${buddies}">
                        <tr>
                            <td>${buddy.name}</td>
                            <td><a href="studentBuddies.html?username=${student.userDetails.username}&removeBuddy&buddy=${buddy.name}">remove buddy</a> </td>
                        </tr>
                    </c:forEach>
                </table>
                <br>
            </c:when>
        </c:choose>

        <h2>Students</h2>
        <c:choose>
            <c:when test="${fn:length(students) > 0}">
                <table id="buddyTable" border="2">
                    <tr>
                        <th>Student</th>
                    </tr>
                    <c:forEach var="nonBuddy" items="${students}">
                        <tr>
                            <td>${nonBuddy.userDetails.username}</td>
                            <td><a href="studentBuddies.html?username=${student.userDetails.username}&addBuddy&potentialBuddy=${nonBuddy.userDetails.username}">Make buddy</a> </td>
                        </tr>
                    </c:forEach>
                </table>
                <br>
            </c:when>
        </c:choose>
    </tiles:putAttribute>
</tiles:insertDefinition>