<%@ include file="../common-taglibs.jsp" %>

<tiles:insertDefinition name="default-page">

    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <tiles:putAttribute name="main">

        <h1>User Details</h1>

       <table>
           <tr>
               <td width="50%">
                   <table width="100%">
                       <tr>
                           <td colspan="2">
                               <img src="/webapp/common/filestreamer.html?username=${student.userDetails.username}&showIcon"/>
                           </td>
                       </tr>
                       <tr>
                           <td width="20%"><strong>First name</strong></td>
                           <td><s:ajaxTextField model="${student.userDetails}" property="firstName"/></td>
                       </tr>
                       <tr>
                           <td><strong>Last name</strong></td>
                           <td><s:ajaxTextField model="${student.userDetails}" property="lastName"/></td>
                       </tr>

                       <tr>
                           <td><strong>User name</strong></td>
                           <td><strong>${student.userDetails.username}</strong></td>
                       </tr>

                       <tr>
                           <td><strong>Password</strong></td>
                           <td><s:ajaxPasswordField model="${student.userDetails}" property="password"/></td>
                       </tr>
                       <tr>
                           <td><strong>Language</strong></td>
                           <td><s:ajaxTextField model="${student.userDetails}" property="locale"/></td>
                       </tr>

                   </table>

               </td>
               <td>
               </td>
           </tr>
       </table>
        <br/>
        <h1>Roles</h1>

        <table>
            <tr>
                <td>
                    <s:editUserRoles user="${student}" availableAuthorities="${availableAuthorities}" successView="/teacher/viewStudentDetails.html"/>
                </td>
            </tr>
        </table>

        <a href="viewStudentDetails.html?username=${student.userDetails.username}&action=delete">Delete</a> |
        <a href="studentBuddies.html?username=${student.userDetails.username}">Buddies</a>

    </tiles:putAttribute>
</tiles:insertDefinition>