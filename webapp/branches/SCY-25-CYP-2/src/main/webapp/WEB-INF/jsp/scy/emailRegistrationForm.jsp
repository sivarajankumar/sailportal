<%@ include file="common-taglibs.jsp" %>
<tiles:insertDefinition name="default-page">
    <tiles:putAttribute name="main">


        <div id="centeredDiv">

            <div style="text-align:center;">
                <form:form id="studentRegForm" commandName="userDetails" method="post" action="emailRegistrationForm.html">
                    <table>
                        <tr>
                            <td>
                                email
                            </td>
                            <td>
                                <input id="email" type="text" name="email">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input type="submit">
                            </td>
                        </tr>
                    </table>

                    <script type="text/javascript">
                        document.getElementById('email').focus();
                    </script>


                </form:form>

            </div>

        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>


