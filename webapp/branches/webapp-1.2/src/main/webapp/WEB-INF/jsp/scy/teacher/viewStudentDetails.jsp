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
                           <td><strong>First name</strong></td>
                           <td><s:ajaxTextField model="${student.userDetails}" property="firstName"/></td>
                       </tr>
                       <tr>
                           <td><strong>Last name</strong></td>
                           <td><s:ajaxTextField model="${student.userDetails}" property="lastName"/></td>
                       </tr>

                   </table>

               </td>
               <td>
                    <s:editUserRoles user="${student}" availableAuthorities="${availableAuthorities}" successView="/teacher/viewStudentDetails.html"/>           
               </td>
           </tr>
       </table>


        <a href="selectPedagogicalPlanForStudent.html?username=${student.userDetails.username}">Select mission to assign</a> <br/>

        <a href="viewStudentDetails.html?username=${student.userDetails.username}&pedPlan=published">Assign published mission</a>



        <c:choose>
            <c:when test="${fn:length(studentPlans) > 0}">
                <table id="studentPlanTable" border="2">
                    <tr>
                        <th>Name</th>
                    </tr>
                    <c:forEach var="studentPlan" items="${studentPlans}">
                        <tr>
                            <td><a href="viewStudentPlan.html?studentPlanId=${studentPlan.id}">${studentPlan.pedagogicalPlan.name}</a></td>
                        </tr>
                    </c:forEach>
                </table>
                <br>
            </c:when>
        </c:choose>

        <c:choose>
            <c:when test="${fn:length(assignedPedagogicalPlans) > 0}">
                    <c:forEach var="assignedPedagogicalPlan" items="${assignedPedagogicalPlans}">
                        <table border="2">
                            <tr>
                                <td><strong>Name</strong></td>
                                <td>${assignedPedagogicalPlan.pedagogicalPlan.name}</td>
                            </tr>
                        <tr>
                            <td><strong>Use criteria based assessment</strong></td>
                            <td><s:ajaxCheckBox model="${assignedPedagogicalPlan}" property="useCriteriaBasedAssessment"/> </td>
                        </tr>


                    </c:forEach>
            </c:when>
        </c:choose>


        <div>
            <a href="studentBuddies.html?username=${student.userDetails.username}">Buddies</a>
        </div>
        
    </tiles:putAttribute>
</tiles:insertDefinition>