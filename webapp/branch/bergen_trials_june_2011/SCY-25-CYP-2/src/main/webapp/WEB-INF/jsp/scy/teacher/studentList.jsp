<%@ include file="../common-taglibs.jsp" %>

<tiles:insertDefinition name="default-page">

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <tiles:putAttribute name="main">
    

        

        <c:choose>
            <c:when test="${fn:length(users) > 0}">
                <table id="teachersTable">
                    <h1>Users</h1>

                    <c:if test="${(message != 'null')}">
                        <h2><strong style="color:#ff0000;">${message}</strong></h2> 
                        <br/>
                    </c:if>
                    
                    <tr>
                        <th></th>
                        <th>User name</th>
                        <th>First name</th>
                        <th>Last name</th>
                    </tr>
                    <c:forEach var="student" items="${users}">
                        <tr  class="${oddEven.oddEven}">
                            <td><img
                                    src="/webapp/common/filestreamer.html?username=${student.userDetails.username}&showIcon"/>
                            </td>
                            <td><a href="viewStudentDetails.html?username=${student.userDetails.username}">${student.userDetails.username}</a></td>
                            <td>${student.userDetails.firstName} </td>
                            <td>${student.userDetails.lastName}</td>
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
        <s:uploadFile listener="uploadMultipleUsers"/>




    </div>
</tiles:putAttribute>
</tiles:insertDefinition>


