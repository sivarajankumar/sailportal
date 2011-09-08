<%@ include file="common-taglibs.jsp" %>

<tiles:insertDefinition name="default-page">
    <tiles:putAttribute name="main">
        <div id="centeredDiv">
            <center><h1><spring:message code="WELCOME_TO_SCY"/>!</h1></center>


            ${portfolio.missionName}
            ${portfolio.owner}

        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>   