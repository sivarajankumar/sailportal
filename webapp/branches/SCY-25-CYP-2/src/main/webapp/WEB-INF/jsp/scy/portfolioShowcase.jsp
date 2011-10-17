<%@ page import="java.net.URLEncoder" %>
<%@ include file="common-taglibs.jsp" %>
<tiles:insertDefinition name="default-page">
    <tiles:putAttribute name="main">

    <script type="text/javascript">
dojo.require("dojox.widget.FisheyeList");
dojo.require("dojo.parser")

function renderHtmlLabel(item){
    var item = item.id;
	var labelString = document.getElementById(item).getElementsByTagName("div")[0].innerHTML;

	var regex1 = /&lt;/g;

	labelString = labelString.replace(regex1, "<");

	var regex2 = /&gt;/g;

	labelString = labelString.replace(regex2, ">");

	document.getElementById(item).getElementsByTagName("div")[0].innerHTML = labelString;

	//alert(labelString);

}

</script>
    <style type="text/css">
            .feedbackEloContainer{
                border:2px solid #ff9933;
                margin:2px;
                /*background-color:#cc6600;*/
            }

            .feedbackEloContainer .thumbContainer{
                margin:2px;
                padding:2px;
                text-align:center;
                /*background-color:#e5994c;*/
            }

            .feedbackEloContainer .eloInfoContainer{
                padding:2px;
            }

            .soria .dijitAccordionTitle {
               background-color:#6bcfdf !important;
                background-image:url() !important;

            }

            .soria .dijitAccordionTitle-selected{
                background-color:#03a5be !important;
                background-image:url() !important;
                color:#ffffff;
            }

            .greenBackgrounds{
                background-color:#03a5be !important;
            }

            .lightGreenBackgrounds{
                background-color:#6bcfdf !important;
            }

            .greenBorders{
                border-color:#03a5be !important;
            }

            .feedbackHeader{
                background-image:url(/webapp/themes/scy/default/images/feedback_header.png);
                background-repeat:no-repeat;
                color:#ffffff;
                height:50px;
                background-color:#333333 !important;
                font-weight:bold;
                font-size:25px;
                text-align:center;
                padding-top:20px;
            }


             /* FISHEYE START */
            .dojoxFisheyeListItemLabel {

                font-family: Arial, Helvetica, sans-serif;

                background-color: #666666;

                border: 2px solid #333333;

                color:#ffffff;

                padding: 2px;

                text-align: center;

                position: absolute;

                display: none;

                white-space:nowrap;

            }

            .dojoxFisheyeListItemLabel.dojoxFishSelected {

                display: block;

            }

            .dojoxFisheyeListItemImage {

                border: 0px;

                position: absolute;

            }

            .dojoxFisheyeListItem {

                position: absolute;

                z-index: 2;

                width:40px;
                height:40px;
                /*background-color:#cccccc;*/

            }

            .dojoxFisheyeListBar {

                position: relative;

            }



                    .dojoxFisheyeListBar {

                        margin: 0 auto;

                        text-align: center;

                    }

                    .outerbar {

                        background-color: #666;

                        text-align: center;

                        position: absolute;

                        left: 0px;

                        top: 0px;

                        width: 100%;

                        border-bottom:2px solid #333;

                    }



            #fisheye1 ul, #fisheye1 ul li{

            text-align:left !important;

            }



            #fisheye1 p{

            margin:0;

            padding:0;

            }

            .assessedtrue{
                background-color:#007b06;
            }

            .assessedfalse{
                background-color:#666666;
            }

             /* FISHEYE END */


        </style>

        <div style="border:4px solid #cc6600;width:786px;height:95%;padding:4px;" class="greenBorders">
            <center><h2>${studentUserDetails.firstName} ${studentUserDetails.lastName}</h2></center>
            <center><h2><spring:message code="SHOWCASE_PORTFOLIO"/> </h2></center>
            <br/>
            <br>
            <center>
                ${pedagogicalPlanTransfer.missionShowcaseDescription}
            </center>
            <br/>
        <div dojoType="dojox.layout.ContentPane" style="width:100%;height:90%;" id="eportfolioPane" parseOnLoad="true" executeScripts="true">

            <center>
                <h2><spring:message code="FEATURED_LEARNING_PRODUCTS"/></h2>
                <spring:message code="CLICK_ON_THE_PICTURE_FOR_MORE_INFO"/>
            </center>


            <div dojoType="dojox.widget.FisheyeList"
		itemWidth="80" itemHeight="80"
		itemMaxWidth="150" itemMaxHeight="150"
		orientation="horizontal"
		effectUnits="2"
		itemPadding="10"
		attachEdge="top"
		labelEdge="bottom"
		id="fisheye1"
        conservativeTrigger="true">





                <c:choose>
                    <c:when test="${fn:length(anchorElosWithStatuses) > 0}">
                        <c:forEach var="status" items="${anchorElosWithStatuses}">
                            <c:if test="${status.eloHasBeenAdded}">
                                <div dojoType="dojox.widget.FisheyeListItem" onMouseEnter="renderHtmlLabel(this)" onclick="location.href='/webapp/viewEloInfo.html?anchorEloURI=${status.anchorElo.uri}&amp;eloURI=${status.addedElo.uri}&amp;missionRuntimeURI=${missionRuntimeURI}'"  label="<strong>${status.addedElo.myname}</strong>" iconSrc="${status.anchorElo.thumbnail}" isContainer="true" style="cursor:pointer; margin:3px;border:3px solid #ffffff;" class="assessed${status.eloHasBeenAdded}"><div>Yata</div></div>
                            </c:if>
                            <c:if test="${!status.eloHasBeenAdded}">
                                <div dojoType="dojox.widget.FisheyeListItem" onMouseEnter="renderHtmlLabel(this)" onclick="location.href='/webapp/viewEloInfo.html?anchorEloURI=${status.anchorElo.uri}&amp;eloURI=${status.addedElo.uri}&amp;missionRuntimeURI=${missionRuntimeURI}'"  label="<strong>${status.anchorElo.myname}</strong>" iconSrc="${status.anchorElo.thumbnail}" isContainer="true" style="cursor:pointer; margin:3px;border:3px solid #ffffff;" class="assessed${status.eloHasBeenAdded}"></div>
                            </c:if>

                        </c:forEach>
                    </c:when>
                </c:choose>


                </div>
            <br/>
            <br/>
            <p>
                <center><spring:message code="CLICK_ON_THE_IMAGES_TO_SEE_DETAILS"/></center>
            </p>
            
            <br/><br/>

            <p>
                <center>
                   <h2><a href="http://scycom.collide.info"><spring:message code="INTSTRUCTIONS_TO_TRY_SCY_LAB"/></a></h2>
                </center>
                <center>
                    <a href="/webapp/student/registerstudent.html"><spring:message code="TRY_SCY_LAB"></spring:message></a>
                </center>
            </p>
            </div>



        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>