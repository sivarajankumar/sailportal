<%@ include file="../common-taglibs.jsp" %>

<tiles:insertDefinition name="default-page">
    <tiles:putAttribute name="main">
        <div id="centeredDiv">
            <h1>Welcome to SCY!</h1>

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

        </div>
    </tiles:putAttribute>
</tiles:insertDefinition> 
