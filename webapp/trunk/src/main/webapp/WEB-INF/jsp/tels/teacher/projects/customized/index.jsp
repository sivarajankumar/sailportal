<%@ include file="../../include.jsp"%>
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
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
  * Lesser General Public License for more details.
  *
  * You should have received a copy of the GNU Lesser General Public
  * License along with this library; if not, write to the Free Software
  * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
-->

<!-- $Id: projectlibrary.jsp 1850 2008-04-01 01:22:32Z mattf $ -->

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="en">
<head>

<%@ include file="../styles.jsp"%>

<!-- Core + Skin CSS --> 
<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.5.1/build/menu/assets/skins/sam/menu.css"> 
 
<!-- Dependencies -->  
<script type="text/javascript" src="http://yui.yahooapis.com/2.5.1/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
<script type="text/javascript" src="http://yui.yahooapis.com/2.5.1/build/container/container_core-min.js"></script> 
 
<!-- Source File --> 
<script type="text/javascript" src="http://yui.yahooapis.com/2.5.1/build/menu/menu-min.js"></script> 

<link href="../../../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../../<spring:theme code="teacherprojectstylesheet" />" media="screen" rel="stylesheet" type="text/css" />


<title><spring:message code="teacher.pro.custom.index.1"/></title>
</head>

        <!-- Page-specific script -->

        <script type="text/javascript">

            /*
                 Initialize and render the MenuBar when its elements are ready 
                 to be scripted.
            */

            YAHOO.util.Event.onContentReady("customProjectActionsMenu", function () {

                /*
                     Instantiate a MenuBar:  The first argument passed to the 
                     constructor is the id of the element in the page 
                     representing the MenuBar; the second is an object literal 
                     of configuration properties.
                */

                var oMenuBar = new YAHOO.widget.MenuBar("customProjectActionsMenu", { 
                                                            autosubmenudisplay: true, 
                                                            hidedelay: 500, 
                                                            lazyload: true });

                /*
                     Define an array of object literals, each containing 
                     the data necessary to create a submenu.
                */

                var aSubmenuData = [
                
                    {
                        id: "actionsCurrent", 
                        itemdata: [ 
                            { text: "<spring:message code="teacher.pro.custom.index.2"/>", url: "../../../author/authorproject.html?projectId=1" },
                            { text: "<spring:message code="teacher.pro.custom.index.3"/>", url: "#" },
                            { text: "<spring:message code="teacher.pro.custom.index.4"/>", url: "#" },
                            { text: "<spring:message code="teacher.pro.custom.index.5"/>", url: "#" },
                            { text: "<spring:message code="teacher.pro.custom.index.6"/>", url: "#" },
                            { text: "<spring:message code="teacher.pro.custom.index.7"/>",  url: "shareproject.html" },
                            { text: "<spring:message code="teacher.pro.custom.index.8"/>", url: "#" },
                            { text: "<spring:message code="teacher.pro.custom.index.9"/>", url: "#" },
                            { text: "<spring:message code="teacher.pro.custom.index.10"/>", url: "#" },     
                        ]
                                      
                    },                   
                                             
                ];        
                   
                
                var ua = YAHOO.env.ua,
                    oAnim;  // Animation instance


                /*
                     "beforeshow" event handler for each submenu of the MenuBar
                     instance, used to setup certain style properties before
                     the menu is animated.
                */

                function onSubmenuBeforeShow(p_sType, p_sArgs) {

                    var oBody,
                        oElement,
                        oShadow,
                        oUL;
                

                    if (this.parent) {

                        oElement = this.element;

                        /*
                             Get a reference to the Menu's shadow element and 
                             set its "height" property to "0px" to syncronize 
                             it with the height of the Menu instance.
                        */

                        oShadow = oElement.lastChild;
                        oShadow.style.height = "0px";

                        
                        /*
                            Stop the Animation instance if it is currently 
                            animating a Menu.
                        */ 
                    
                        if (oAnim && oAnim.isAnimated()) {
                        
                            oAnim.stop();
                            oAnim = null;
                        
                        }


                        /*
                            Set the body element's "overflow" property to 
                            "hidden" to clip the display of its negatively 
                            positioned <ul> element.
                        */ 

                        oBody = this.body;


                        //  Check if the menu is a submenu of a submenu.

                        if (this.parent && 
                            !(this.parent instanceof YAHOO.widget.MenuBarItem)) {
                        

                            /*
                                There is a bug in gecko-based browsers where 
                                an element whose "position" property is set to 
                                "absolute" and "overflow" property is set to 
                                "hidden" will not render at the correct width when
                                its offsetParent's "position" property is also 
                                set to "absolute."  It is possible to work around 
                                this bug by specifying a value for the width 
                                property in addition to overflow.
                            */

                            if (ua.gecko) {
                            
                                oBody.style.width = oBody.clientWidth + "px";
                            
                            }
                            
                            
                            /*
                                Set a width on the submenu to prevent its 
                                width from growing when the animation 
                                is complete.
                            */
                            
                            if (ua.ie == 7) {

                                oElement.style.width = oElement.clientWidth + "px";

                            }
                        
                        }

    
                        oBody.style.overflow = "hidden";


                        /*
                            Set the <ul> element's "marginTop" property 
                            to a negative value so that the Menu's height
                            collapses.
                        */ 

                        oUL = oBody.getElementsByTagName("ul")[0];

                        oUL.style.marginTop = ("-" + oUL.offsetHeight + "px");
                    
                    }

                }


                /*
                    "tween" event handler for the Anim instance, used to 
                    synchronize the size and position of the Menu instance's 
                    shadow and iframe shim (if it exists) with its 
                    changing height.
                */

                function onTween(p_sType, p_aArgs, p_oShadow) {

                    if (this.cfg.getProperty("iframe")) {
                    
                        this.syncIframe();
                
                    }
                
                    if (p_oShadow) {
                
                        p_oShadow.style.height = this.element.offsetHeight + "px";
                    
                    }
                
                }


                /*
                    "complete" event handler for the Anim instance, used to 
                    remove style properties that were animated so that the 
                    Menu instance can be displayed at its final height.
                */

                function onAnimationComplete(p_sType, p_aArgs, p_oShadow) {

                    var oBody = this.body,
                        oUL = oBody.getElementsByTagName("ul")[0];

                    if (p_oShadow) {
                    
                        p_oShadow.style.height = this.element.offsetHeight + "px";
                    
                    }


                    oUL.style.marginTop = "";
                    oBody.style.overflow = "";
                    

                    //  Check if the menu is a submenu of a submenu.

                    if (this.parent && 
                        !(this.parent instanceof YAHOO.widget.MenuBarItem)) {


                        // Clear widths set by the "beforeshow" event handler

                        if (ua.gecko) {
                        
                            oBody.style.width = "";
                        
                        }
                        
                        if (ua.ie == 7) {

                            this.element.style.width = "";

                        }
                    
                    }
                    
                }


                /*
                     "show" event handler for each submenu of the MenuBar 
                     instance - used to kick off the animation of the 
                     <ul> element.
                */

                function onSubmenuShow(p_sType, p_sArgs) {

                    var oElement,
                        oShadow,
                        oUL;
                
                    if (this.parent) {

                        oElement = this.element;
                        oShadow = oElement.lastChild;
                        oUL = this.body.getElementsByTagName("ul")[0];
                    

                        /*
                             Animate the <ul> element's "marginTop" style 
                             property to a value of 0.
                        */

                        oAnim = new YAHOO.util.Anim(oUL, 
                            { marginTop: { to: 0 } },
                            .5, YAHOO.util.Easing.easeOut);


                        oAnim.onStart.subscribe(function () {
        
                            oShadow.style.height = "100%";
                        
                        });
    

                        oAnim.animate();

    
                        /*
                            Subscribe to the Anim instance's "tween" event for 
                            IE to syncronize the size and position of a 
                            submenu's shadow and iframe shim (if it exists)  
                            with its changing height.
                        */
    
                        if (YAHOO.env.ua.ie) {
                            
                            oShadow.style.height = oElement.offsetHeight + "px";


                            /*
                                Subscribe to the Anim instance's "tween"
                                event, passing a reference Menu's shadow 
                                element and making the scope of the event 
                                listener the Menu instance.
                            */

                            oAnim.onTween.subscribe(onTween, oShadow, this);
    
                        }
    

                        /*
                            Subscribe to the Anim instance's "complete" event,
                            passing a reference Menu's shadow element and making 
                            the scope of the event listener the Menu instance.
                        */
    
                        oAnim.onComplete.subscribe(onAnimationComplete, oShadow, this);
                    
                    }
                
                }


                /*
                     Subscribe to the "beforerender" event, adding a submenu 
                     to each of the items in the MenuBar instance.
                */

                oMenuBar.subscribe("beforeRender", function () {

                    if (this.getRoot() == this) {

                        this.getItem(0).cfg.setProperty("submenu", aSubmenuData[0]);
                        this.getItem(1).cfg.setProperty("submenu", aSubmenuData[1]);
                        this.getItem(2).cfg.setProperty("submenu", aSubmenuData[2]);
                        this.getItem(3).cfg.setProperty("submenu", aSubmenuData[3]);

                    }

                });


                /*
                     Subscribe to the "beforeShow" and "show" events for 
                     each submenu of the MenuBar instance.
                */
                
                oMenuBar.subscribe("beforeShow", onSubmenuBeforeShow);
                oMenuBar.subscribe("show", onSubmenuShow);


                /*
                     Call the "render" method with no arguments since the 
                     markup for this MenuBar instance is already exists in 
                     the page.
                */

                oMenuBar.render();         
            
            });

        </script>

<!--USED TO SHOW/HIDE A DIV ELEMENT-->
<script type="text/javascript">
<!--
    function toggle_visibility(id) {
       var e = document.getElementById(id);
       if(e.style.display == 'none')
          e.style.display = 'block';
       else
          e.style.display = 'none';
    }
//-->
</script>


<body class="yui-skin-sam"> 

<div id="centeredDiv">

<%@ include file="headerteacherprojects.jsp"%>

<%@ include file="L2projects_projectlibrary.jsp"%>

<h2 id="titleBar" class="headerText"><spring:message code="teacher.pro.custom.index.11"/></h2> 

<!--<div id="projectInfoInstructions">Click any tab below for more information.</div>-->
<div id="projectInfoTabs" class="yui-navset">
    <ul class="yui-nav" >
        <li style="margin-left:4px;"><a href="#tab1"><em><spring:message code="teacher.pro.custom.index.12"/></em></a></li>
        <li style="margin-left:4px;"><a href="#tab2"><em><spring:message code="teacher.pro.custom.index.13"/></em></a></li>
        <li style="margin-left:4px;"><a href="#tab3"><em><spring:message code="teacher.pro.custom.index.14"/></em></a></li>
        
    </ul>     
<div class="yui-content" style="background-color: #FFFFFF;">

<div id="tab1">

<table id="customProjectsButtons">
	<tr>
		<td><a href="#"><spring:message
			code="teacher.pro.custom.index.15" /></a></td>
		<td><a href="#"><spring:message
			code="teacher.pro.custom.index.16" /></a></td>
		<td><a href="#" onclick="toggle_visibility('toggleAllCurrent');"><spring:message
			code="teacher.pro.custom.index.17" /></a></td>
		<td><a href="#"><spring:message
			code="teacher.pro.custom.index.18" /></a></td>
	</tr>
</table>

<table id="customProjectTable" border="1" cellpadding="0"
	cellspacing="0">
	<tr>
		<th><spring:message code="teacher.pro.custom.index.19" /></th>
		<th><spring:message code="teacher.pro.custom.index.20" /></th>
		<th><spring:message code="teacher.pro.custom.index.21" /></th>
		<th><spring:message code="teacher.pro.custom.index.22" /></th>
		<th><spring:message code="teacher.pro.custom.index.23" /></th>
		<th><spring:message code="teacher.pro.custom.index.24" /></th>
		<th><spring:message code="teacher.pro.custom.index.25" /></th>
		<th><spring:message code="teacher.pro.custom.index.26" /></th>
		<th></th>
	</tr>
	<c:choose>
		<c:when test="${fn:length(ownedProjectsList) == 0}">
		</c:when>
		<c:otherwise>
		    <h5>These are projects that you own</h5>
			<c:forEach var="project" items="${ownedProjectsList}">
				<tr id="customProjectR2">
					<td class="customProjectTitle">${project.curnit.sdsCurnit.name}
					<br>
					This project is shared with:<br>
					<c:forEach var="sharedowner" items="${project.sharedowners}">
					  <c:out value="${sharedowner.userDetails.firstname}"/>
					  <c:out value="${sharedowner.userDetails.lastname}"/><br>
					</c:forEach>
					</td>
					<td class="dataText">${project.projectInfo.subject}</td>
					<td class="dataText">${project.curnit.id}</td>
					<td class="dataText">;;;</td>
					<td class="smallText1">UC Berkeley library project</td>
					<td class="dataText">${project.projectInfo.gradeLevel }</td>
					<td class="dataTime">;;;</td>
					<td class="dataTime">${project.projectInfo.projectLiveCycle.toString}</td>
					<td class="actionMenuButton">
					<div id="customProjectActionsMenu"
						class="yuimenubar yuimenubarnav customProjectsMenuCSS">
					<div class="bd">
					<ul class="first-of-type">
						<li class="yuimenubaritem first-of-type" style="width: 100%;">
						<a class="yuimenubaritemlabel" href="#actionsCurrent">ACTIONS</a>
						</li>
					</ul>
					<a href="shareproject.html?projectId=${project.id}">share this
					project</a></div>
					</div>
					</td>
				</tr>
				<tr>
					<td id="customProjectSummaryHeader" colspan="10">
					<div><a href="#"
						onclick="toggle_visibility('toggleProjectSummaryCurrent');"><spring:message
						code="teacher.pro.custom.index.27" /></a></div>
					<div id="toggleAllCurrent">
					<div id="toggleProjectSummaryCurrent">
					<div id="customProjectSummaryData"><b>Project Summary:</b>
					${project.projectInfo.description }</div>
					<div id="customProjectSummaryData"><b>Keywords:</b>
					${project.projectInfo.keywords }</div>
					<div id="customProjectSummaryData"><b>Sharing:</b> off</div>
					</div>
					</div>
					</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>



	<tr></tr>
</table>

</div>
<!--	    End of Tab 1 content-->

<div id="tab2">

<table id="customProjectsButtons">
	<tr>
		<td><a href="#"><spring:message
			code="teacher.pro.custom.index.15" /></a></td>
		<td><a href="#"><spring:message
			code="teacher.pro.custom.index.16" /></a></td>
		<td><a href="#" onclick="toggle_visibility('toggleAllShared');"><spring:message
			code="teacher.pro.custom.index.17" /></a></td>
	</tr>
</table>

<table id="customProjectTable" border="1" cellpadding="0"
	cellspacing="0">
	<tr>
		<th style="background-color: #0000CC;"><spring:message
			code="teacher.pro.custom.index.19" /></th>
		<th style="background-color: #0000CC;"><spring:message
			code="teacher.pro.custom.index.20" /></th>
		<th style="background-color: #0000CC;"><spring:message
			code="teacher.pro.custom.index.21" /></th>
		<th style="background-color: #0000CC;"><spring:message
			code="teacher.pro.custom.index.22" /></th>
		<th style="background-color: #0000CC;"><spring:message
			code="teacher.pro.custom.index.23" /></th>
		<th style="background-color: #0000CC;"><spring:message
			code="teacher.pro.custom.index.24" /></th>
		<th style="background-color: #0000CC;"><spring:message
			code="teacher.pro.custom.index.25" /></th>
		<th style="background-color: #0000CC;"><spring:message
			code="teacher.pro.custom.index.26" /></th>
		<th style="background-color: #0000CC;"></th>
	</tr>


	<c:choose>
		<c:when test="${fn:length(sharedProjectsList) == 0}">
		</c:when>
		<c:otherwise>
			<c:forEach var="project" items="${sharedProjectsList}">
				<tr id="customProjectR2">
					<td class="customProjectTitle">${project.name}
					<br>
					This project is owned by:<br>
					<c:forEach var="projectowner" items="${project.owners}">
					  <c:out value="${projectowner.userDetails.firstname}" />
					  <c:out value="${projectowner.userDetails.lastname}" />
					  <br>					  
					</c:forEach>
					</td>
					<td class="dataText">${project.projectInfo.subject}</td>
					<td class="dataText">${project.curnit.id}</td>
					<td class="dataText">;;;</td>
					<td class="smallText1">UC Berkeley library project</td>
					<td class="dataText">${project.projectInfo.gradeLevel }</td>
					<td class="dataTime">;;;</td>
					<td class="dataTime">${project.projectInfo.projectLiveCycle.toString}</td>
					<td class="actionMenuButton">
					<div id="customProjectActionsMenu"
						class="yuimenubar yuimenubarnav customProjectsMenuCSS">
					<div class="bd">
					<a href="../../../previewproject.html?projectId=${project.id}">Preview project</a><br>
					<authz:accesscontrollist domainObject="${project}" hasPermission="2">												
					<li><a href="../../../author/authorproject.html?projectId=${project.id}"><spring:message
							code="edit.curnit" /></a></li>
					</authz:accesscontrollist>
					</div>
					</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>

	<tr>
		<td id="customProjectSummaryHeader" colspan="10">
		<div><a href="#"
			onclick="toggle_visibility('toggleProjectSummaryCurrent');"><spring:message
			code="teacher.pro.custom.index.27" /></a></div>
		<div id="toggleAllCurrent">
		<div id="toggleProjectSummaryCurrent">
		<div id="customProjectSummaryData"><b>Project Summary:</b>
		${project.projectInfo.description }</div>
		<div id="customProjectSummaryData"><b>Keywords:</b>
		${project.projectInfo.keywords}</div>
		<div id="customProjectSummaryData"><b>Sharing:</b> off</div>
		</div>
		</div>
		</td>
	</tr>
	<tr></tr>
</table>

</div>

<div id="tab3">

<table id="customProjectTable" border="1" cellpadding="0"
	cellspacing="0">
	<tr>
		<th style="background-color: #CC0000;"><spring:message
			code="teacher.pro.custom.index.19" /></th>
		<th style="background-color: #CC0000;"><spring:message
			code="teacher.pro.custom.index.20" /></th>
		<th style="background-color: #CC0000;"><spring:message
			code="teacher.pro.custom.index.21" /></th>
		<th style="background-color: #CC0000;"><spring:message
			code="teacher.pro.custom.index.22" /></th>
		<th style="background-color: #CC0000;"><spring:message
			code="teacher.pro.custom.index.23" /></th>
		<th style="background-color: #CC0000;"><spring:message
			code="teacher.pro.custom.index.24" /></th>
		<th style="background-color: #CC0000;"><spring:message
			code="teacher.pro.custom.index.25" /></th>
		<th style="background-color: #CC0000;"><spring:message
			code="teacher.pro.custom.index.26" /></th>
		<th style="background-color: #CC0000;"></th>
	</tr>

	<c:choose>
		<c:when test="${fn:length(sharedProjectsList) == 0}">
		</c:when>
		<c:otherwise>
			<c:forEach var="project" items="${sharedProjectsList}">
			<!-- <authz:accesscontrollist domainObject="${project}" hasPermission="16"> -->
				<tr id="customProjectR2">
					<td class="customProjectTitle">${project.curnit.sdsCurnit.name}</td>
					<td class="dataText">${project.projectInfo.subject}</td>
					<td class="dataText">${project.curnit.id}</td>
					<td class="dataText">;;;</td>
					<td class="smallText1">UC Berkeley library project</td>
					<td class="dataText">${project.projectInfo.gradeLevel }</td>
					<td class="dataTime">;;;</td>
					<td class="dataTime">${project.projectInfo.projectLiveCycle.toString}</td>
					<td class="actionMenuButton">
					<div id="customProjectActionsMenu"
						class="yuimenubar yuimenubarnav customProjectsMenuCSS">
					<div class="bd">
					<ul class="first-of-type">
						<li class="yuimenubaritem first-of-type" style="width: 100%;">
						<a class="yuimenubaritemlabel" href="#actionsCurrent">ACTIONS</a>
						</li>
					</ul>
					<a href="shareproject.html?projectId=${project.id}">share this
					project</a></div>
					</div>
					</td>
				</tr>
			<!-- </authz:accesscontrollist> -->
			</c:forEach>
		</c:otherwise>
	</c:choose>

	<tr>
		<td id="customProjectSummaryHeader" colspan="10">
		<div><a href="#"
			onclick="toggle_visibility('toggleCustomProjectArchive');"><spring:message
			code="teacher.pro.custom.index.27" /></a></div>

		<div id="toggleCustomProjectArchive">
		<div id="customProjectSummaryData"><b>Project Summary:</b>
		Laoreet dignissim consequat nulla eu, dolore consectetuer aliquam
		nulla iriure commodo, ut, enim lobortis nisl delenit, nostrud facilisi
		et praesent sed qui, ea. Velit suscipit eu blandit, hendrerit, aliquip
		dolor et, veniam, facilisis lobortis aliquam enim et ut, velit eros
		iriure sed dolor feugait, odio dolore. In, commodo luptatum accumsan
		velit illum laoreet consequat, ipsum ut duis duis dolore illum nulla
		exerci tation, praesent. Odio dolore eros ullamcorper hendrerit et
		feugiat hendrerit esse consequat exerci.</div>
		<div id="customProjectSummaryData"><b>Keywords:</b> Biology,
		Evolution, Mitosis, Meiosis, Environmental Selective Pressures,
		Genetics, Cloning, Asexual Reproduction, Sexual Reproduction</div>
		</div>
		</td>
	</tr>
</table>

</div>


</div>
</div>


  
	
</div>

<script type="text/javascript">
    var tabView = new YAHOO.widget.TabView('projectInfoTabs');
    tabView.set('activeIndex', 0);
</script>

</body>
</html>
