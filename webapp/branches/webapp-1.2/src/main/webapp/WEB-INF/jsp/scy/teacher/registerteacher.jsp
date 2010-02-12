<%@ include file="../include.jsp" %>

<!--
* Copyright (c) 2006 Encore Research Group, University of Toronto
*
* This library is free software; you can redistribute it and/or
* modify it under the terms of the GNU Lesser General Public
* License as published by the Free Software Foundation; either
* version 2.1 of the License, or (at your option) any later version.
*
* This library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
* Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public
* License along with this library; if not, write to the Free Software
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
-->

<!-- $Id: registerstudent.jsp 989 2007-08-30 01:15:54Z MattFish $ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>

    <link href="../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet" type="text/css"/>
    <link href="../<spring:theme code="registerstylesheet"/>" media="screen" rel="stylesheet" type="text/css"/>

    <script src=".././javascript/tels/general.js" type="text/javascript"></script>
    <script src=".././javascript/tels/effects.js" type="text/javascript"></script>
    <script src=".././javascript/tels/scriptaculous.js" type="text/javascript"></script>

    <title><spring:message code="student.signup.title"/></title>
    <%@ include file="../admin/adminhead.jsp" %>


</head>

<body>
<%@ include file="../admin/adminheader.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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

</body>

</html>




