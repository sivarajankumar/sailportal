<%@ include file="../common-taglibs.jsp" %>
<tiles:insertDefinition name="default-page">
    <tiles:putAttribute name="main">

        <div id="centeredDiv">

            <table>
                <tr>
                    <td>First name</td>
                    <td>${firstname}</td>
                </tr>
                <tr>
                    <td>Last name</td>
                    <td>${lastname}</td>
                </tr>
                <tr>
                    <td>Username</td>
                    <td>${username}</td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td>${password}</td>
                </tr>
            </table>

            <a href="/webapp/index.html">Click to log in</a>


        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
