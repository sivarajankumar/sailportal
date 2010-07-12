<%@ include file="../common-taglibs.jsp" %>

<tiles:insertDefinition name="default-page">
    <tiles:putAttribute name="main">
        <div id="centeredDiv">
            <center><h1>Welcome to SCY!</h1></center>

            <c:if test="${rickRoll}">
                <object width="425" height="344"><param name="movie" value="http://www.youtube.com/v/oHg5SJYRHA0&hl=en_US&fs=1&"></param><param name="allowFullScreen" value="true"></param><param name="allowscriptaccess" value="always"></param><embed src="http://www.youtube.com/v/oHg5SJYRHA0&hl=en_US&fs=1&" type="application/x-shockwave-flash" allowscriptaccess="always" allowfullscreen="true" width="425" height="344"></embed></object>
            </c:if>

            <c:if test="${showProfilePicture}">
                <img src="/webapp/common/filestreamer.html?username=${currentUser.userDetails.username}&showIcon"/>
            </c:if>                                                                               

            <p>
                <center>
                    Click to start <a href="/extcomp/scy-lab.jnlp">SCYLab</a>
                </center>
            </p>

    <sec:authorize ifAllGranted="ROLE_USER">
        <sec:authorize ifAllGranted="ROLE_STUDENT">
            <h2>What do you want to do?</h2>
            <table>
                <tr>
                    <td><a href="/extcomp/scy-lab.jnlp">Start SCYLab</a></td>
                </tr>
                <tr>
                    <td>
                        <a href="/webapp/app/feedback/ScyFeedbackIndex.html">View ELOs</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <s:dialog url="/webapp/app/feedback/uploadELOForFeedbackForm.html?username=${currentUser.userDetails.username}" title="Upload ELO" dialogHeader="Upload ELO"/>
                        <!--s:myCurrentActivity username="${currentUser.userDetails.username}"/-->
                    </td>
                </tr>

            </table>
        </sec:authorize>
        <sec:authorize ifAllGranted="ROLE_TEACHER">
            <h2>What do you want to do?</h2>
            <table>
                <tr>
                    <td><a href="/extcomp/scy-lab.jnlp">Start SCYLab</a></td>
                </tr>
                <tr>
                    <td>
                        <a href="/webapp/app/feedback/ScyFeedbackIndex.html">View ELOs</a>
                    </td>
                </tr>
            </table>        
        </sec:authorize>
	</sec:authorize>


        </div>
    </tiles:putAttribute>
</tiles:insertDefinition> 
