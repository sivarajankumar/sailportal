<%@ include file="../common-taglibs.jsp" %>
<tiles:insertDefinition name="default-page">
    <tiles:putAttribute name="main">

        <div id="centeredDiv">

            <table>
                <tr>
                    <td>First name</td>
                    <td>${firstName}</td>
                </tr>
                <tr>
                    <td>Last name</td>
                    <td>${lastName}</td>
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
