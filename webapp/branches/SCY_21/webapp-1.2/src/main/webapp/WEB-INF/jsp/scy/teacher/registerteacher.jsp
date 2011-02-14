<%@ include file="../common-taglibs.jsp" %>
<tiles:insertDefinition name="default-page">
    <tiles:putAttribute name="main">


        <div id="centeredDiv">

            <div style="text-align:center;">
                <!--This bad boy ensures centering of block level elements in IE (avoiding margin:auto bug).  Oh how I hate IE-->

                <h1 id="registrationTitle" class="blueText">Teacher registration</h1>

                <div id="subtitleStudentReg">To create a teacher account, please fill in the following fields</div>

                <!-- Support for Spring errors object -->
                <div id="regErrorMessages">
                    <spring:bind path="userDetails.*">
                        <c:forEach var="error" items="${status.errorMessages}">
                            <br/><c:out value="${error}"/>
                        </c:forEach>
                    </spring:bind>
                </div>

                <form:form id="studentRegForm" commandName="userDetails" method="post" action="registerteacher.html">
                    <table>
                        <tr>
                            <td>
                                <spring:message code="student.registerstudent.4"/>
                            </td>
                            <td>
                                <form:input path="firstName" id="firstName" size="25" maxlength="25" tabindex="1"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Last name
                            </td>
                            <td>
                                <form:input path="lastName" id="lastName" size="25" maxlength="25" tabindex="1"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Username
                            </td>
                            <td>
                                <form:input path="username" id="username" size="25" maxlength="25" tabindex="1"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Password
                            </td>
                            <td>
                                <form:password path="password" id="password" size="25" maxlength="25" tabindex="6"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <div id="regButtons">
                                    <input type="image" id="save" src="../themes/tels/default/images/CreateAccount.png"
                                           onmouseover="swapImage('save','../themes/tels/default/images/CreateAccountRoll.png')"
                                           onmouseout="swapImage('save','../themes/tels/default/images/CreateAccount.png')"
                                            />
                                    <a href="../index.html"><input type="image" id="cancel"
                                                                   src="../<spring:theme code="register_cancel" />"
                                                                   onmouseover="swapImage('cancel','../<spring:theme code="register_cancel_roll" />')"
                                                                   onmouseout="swapImage('cancel','../<spring:theme code="register_cancel" />')"
                                            /> </a></div>
                            </td>
                        </tr>
                    </table>

                    <!--This unusually placed script gets the cursor into the First Name field immediately on page load (MattFish)-->
                    <script type="text/javascript">
                        document.getElementById('firstname').focus();
                    </script>


                </form:form>

            </div>

        </div>
        <!-- /* End of the CenteredDiv */-->

    </tiles:putAttribute>
</tiles:insertDefinition>


