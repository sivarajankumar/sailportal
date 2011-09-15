<%@ include file="common-taglibs.jsp" %>
<%@ include file="common-taglibs.jsp" %>
<tiles:insertDefinition name="default-page">
    <tiles:putAttribute name="main">
        <style type="text/css">
            .feedbackHeader {
                background-image: url(/webapp/themes/scy/default/images/feedback_header.png);
                background-repeat: no-repeat;
                color: #ffffff;
                height: 50px;
                background-color: #333333 !important;
                font-weight: bold;
                font-size: 25px;
                text-align: center;
                padding-top: 20px;
            }
        </style>

        <center><h2><spring:message code="SHOWCASE_PORTFOLIO"/> - ${studentUserDetails.firstName} ${studentUserDetails.lastName}</h2></center>

        <center><h2>${elo.myname}</h2>
            <a href="${snippetURL}">
                <img src="${elo.thumbnail}"/>
            </a>
        </center>


        <a href="portfolioShowcase.html?missionRuntimeURI=${missionRuntimeURI}"><spring:message
                code="BACK_TO_MAIN_MENU"/></a>

    </tiles:putAttribute>
</tiles:insertDefinition>