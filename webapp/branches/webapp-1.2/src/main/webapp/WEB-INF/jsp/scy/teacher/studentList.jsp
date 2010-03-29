<%@ include file="../common-taglibs.jsp" %>

<tiles:insertDefinition name="default-page">

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <tiles:putAttribute name="main">
    <div id="centeredDiv">

        

        <c:choose>
            <c:when test="${fn:length(students) > 0}">
                <table id="teachersTable" border="2">
                    <h5>Students</h5>
                    <tr>
                        <th></th>
                        <th>User name</th>
                        <th>First name</th>
                        <th>Last name</th>
                    </tr>
                    <c:forEach var="student" items="${students}">
                        <tr>
                            <td><img
                                    src="/webapp/common/filestreamer.html?username=${student.userDetails.username}&showIcon"/>
                            </td>
                            <td><a href="viewStudentDetails.html?username=${student.userDetails.username}">${student.userDetails.username}</a></td>
                            <td>${student.userDetails.firstname} </td>
                            <td>${student.userDetails.lastname}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="4">
                            <a href="addNewStudent.html">Add new Student</a>
                        </td>
                    </tr>
                </table>
                <br>
            </c:when>
        </c:choose>
        <s:uploadFile listener="eu.scy.controllers.teacher.UploadMultipleUsers"/>


    </div>

    </div>
</tiles:putAttribute>
</tiles:insertDefinition>



