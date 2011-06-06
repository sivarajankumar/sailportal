<%@ include file="../common-taglibs.jsp" %>
<tiles:insertDefinition name="default-page">
    <tiles:putAttribute name="main">

        <div id="centeredDiv">

            <%@ include file="../header.jsp" %>

            <div style="text-align:center;">
                <!--This bad boy ensures centering of block level elements in IE (avoiding margin:auto bug).  Oh how I hate IE-->

                <h1 id="registrationTitle" class="blueText"><spring:message
                        code="student.registerstudentconfirm.1"/></h1>

                <!--div id="subtitleConfirm">
                            <h4><spring:message code="student.registerstudentconfirm.2"/></h4>
                            <h4><spring:message code="student.registerstudentconfirm.3"/> <span id="usernameConfirm">${username}</span></h4>
                            <ul>
                            <li><spring:message code="student.registerstudentconfirm.4"/></li>
                            <li><spring:message code="student.registerstudentconfirm.5"/>&nbsp;<span style="color:#6666FF;font-weight:bold;text-decoration:underline;">John</span> <span style="color:#6666FF;font-weight:bold;text-decoration:underline;">S</span>mith with a birthday on <span style="color:#6666FF;font-weight:bold;text-decoration:underline;">3/24</span> would have the Username "<span style="color:#CC3333;font-weight:bold;text-decoration:underline;">JohnS324</span>" <br />
                                <span class="smallText"><spring:message code="student.registerstudentconfirm.7"/></span> </li>
                            </ul>
                </div-->

                <table>
                    <tr>
                        <td>First name</td>
                        <td align="right">${firstName}</td>
                    </tr>
                    <tr>
                        <td>Last name</td>
                        <td align="right">${lastName}</td>
                    </tr>
                    <tr>
                        <td>Username</td>
                        <td align="right">${username}</td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td align="right">${password}</td>
                    </tr>
                </table>


                <table id="confirmationButtons" border="0" cellpadding="5" cellspacing="5">
                    <tr>
                        <td><a href="registerstudent.html"
                               onmouseout="MM_swapImgRestore()"
                               onmouseover="MM_swapImage('Register Another Student','','../themes/tels/default/images/student/Register-Another-Roll.png',1)">
                            <img src="../themes/tels/default/images/student/Register-Another.png"
                                 alt="Register Another Student"
                                 width="161" height="52" id="Register Another Student"/></a></td>

                        <td><a href="../login.html"
                               onmouseout="MM_swapImgRestore()"
                               onmouseover="MM_swapImage('Return to Home Page','','../themes/tels/default/images/Go-To-Home-Page-Roll.png',1)">
                            <img src="../themes/tels/default/images/Go-To-Home-Page.png" alt="Go to Home Page & Sign In"
                                 width="161" height="52" id="Return to Home Page"/></a></td>
                    </tr>
                    <tr>
                        <td class="width1"><spring:message code="student.registerstudentconfirm.8"/></td>
                        <td class="width2"><spring:message code="student.registerstudentconfirm.9"/> <em><spring:message
                                code="student.registerstudentconfirm.10"/></em> <spring:message
                                code="student.registerstudentconfirm.11"/></td>
                    </tr>
                </table>

            </div>

        </div>
        <!-- /* End of the CenteredDiv */-->

    </tiles:putAttribute>
</tiles:insertDefinition>



