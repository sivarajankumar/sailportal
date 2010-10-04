<%@ include file="../../common-taglibs.jsp" %>
<tiles:insertDefinition name="default-page">
    <tiles:putAttribute name="main">

        <table>
            <tr>
                <td>Username</td>
                <td>${user.userDetails.username}</td>
            </tr>
            <tr>
                <td>First name</td>
                <td>${user.userDetails.firstName}</td>

            </tr>
            <tr>
                <td>Lase name</td>
                <td>${user.userDetails.lastName}</td>

            </tr>
        </table>
    </tiles:putAttribute>
</tiles:insertDefinition>