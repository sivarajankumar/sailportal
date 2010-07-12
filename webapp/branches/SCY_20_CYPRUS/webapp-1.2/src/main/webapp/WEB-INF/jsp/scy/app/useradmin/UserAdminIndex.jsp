<%@ include file="../../common-taglibs.jsp" %>
    <tiles:insertDefinition name="default-page">
    <tiles:putAttribute name="main">

        <div id="centeredDiv">
            <h1>User Administration</h1>
            <table>
                <tr>
                    <th>
                        Username
                    </th>
                    <th>
                        First name
                    </th>
                    <th>
                        Last name
                    </th>
                </tr>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>
                            <a href="EditStudent.html?username=${user.userDetails.username}">
                                <c:out value="${user.userDetails.username}"/>
                            </a>
                        </td>
                        <td>

                        </td>
                        <td>

                        </td>
                    </tr>
                </c:forEach>
            </table>


        </div>

    </tiles:putAttribute>
</tiles:insertDefinition>