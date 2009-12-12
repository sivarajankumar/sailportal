<%@ include file="./include.jsp"%>
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

<!-- $Id: index.jsp 2576 2009-11-21 02:17:46Z supersciencefish $ -->

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="en">
<head>


<%@ include file="./projects/styles.jsp"%>

<!-- SuperFish drop-down menu from http://www.electrictoolbox.com/jquery-superfish-menus-plugin/  -->

<link rel="stylesheet" type="text/css" href=".././themes/tels/default/styles/teacher/superfish.css" media="screen">
<script type="text/javascript" src=".././javascript/tels/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src=".././javascript/tels/superfish.js"></script>

<script type="text/javascript">
    
            // initialise plugins
            jQuery(function(){
                jQuery('ul.sf-menu').superfish();
            });
    
            </script>

<!-- Core + Skin CSS --> 
<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.5.1/build/menu/assets/skins/sam/menu.css"> 
 
<!-- Dependencies -->  
<script type="text/javascript" src="http://yui.yahooapis.com/2.5.1/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
<script type="text/javascript" src="http://yui.yahooapis.com/2.5.1/build/container/container_core-min.js"></script> 
 
<!-- Source File --> 
<!--<script type="text/javascript" src="http://yui.yahooapis.com/2.5.1/build/menu/menu-min.js"></script> -->
<!--The line above was commented out to switch from Slide animation to instant pop-up-->

<link href="../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../<spring:theme code="teacherhomepagestylesheet" />" media="screen" rel="stylesheet" type="text/css" />
<link href="../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />

<script src=".././javascript/tels/general.js" type="text/javascript" ></script>
<!--Disabled the next three scripts as they are conflicting with the SuperFish menu.  Need to debug.-->
<!--  <script src=".././javascript/tels/prototype.js" type="text/javascript" ></script> -->
<!--  <script src=".././javascript/tels/effects.js" type="text/javascript" ></script>   -->
<!--  <script src=".././javascript/tels/scriptaculous.js" type="text/javascript" ></script>  -->

<title><spring:message code="application.title" /></title>

<!--NOTE: the following scripts has CONDITIONAL items that only apply to IE (MattFish)-->
<!--[if lt IE 7]>
<script defer type="text/javascript" src="../javascript/tels/iefixes.js"></script>
<![endif]-->

<script type='text/javascript'>
var isTeacherIndex = true; //global var used by spawned pages (i.e. archive run)
</script>


<script type="text/javascript">

/***********************************************
* ADDED BY MATTFISH  DEC 2009
* IFrame SSI script II- © Dynamic Drive DHTML code library (http://www.dynamicdrive.com)
* Visit DynamicDrive.com for hundreds of original DHTML scripts
* This notice must stay intact for legal use
***********************************************/

//Input the IDs of the IFRAMES you wish to dynamically resize to match its content height:
//Separate each ID with a comma. Examples: ["myframe1", "myframe2"] or ["myframe"] or [] for none:
var iframeids=["dynamicFrame"]

//Should script hide iframe from browsers that don't support this script (non IE5+/NS6+ browsers. Recommended):
var iframehide="yes"

var getFFVersion=navigator.userAgent.substring(navigator.userAgent.indexOf("Firefox")).split("/")[1]
var FFextraHeight=parseFloat(getFFVersion)>=0.1? 16 : 0 //extra height in px to add to iframe in FireFox 1.0+ browsers

function resizeCaller() {
var dyniframe=new Array()
for (i=0; i<iframeids.length; i++){
if (document.getElementById)
resizeIframe(iframeids[i])
//reveal iframe for lower end browsers? (see var above):
if ((document.all || document.getElementById) && iframehide=="no"){
var tempobj=document.all? document.all[iframeids[i]] : document.getElementById(iframeids[i])
tempobj.style.display="block"
}
}
}

function resizeIframe(frameid){
var currentfr=document.getElementById(frameid)
if (currentfr && !window.opera){
currentfr.style.display="block"
if (currentfr.contentDocument && currentfr.contentDocument.body.offsetHeight) //ns6 syntax
currentfr.height = currentfr.contentDocument.body.offsetHeight+FFextraHeight; 
else if (currentfr.Document && currentfr.Document.body.scrollHeight) //ie5+ syntax
currentfr.height = currentfr.Document.body.scrollHeight;
if (currentfr.addEventListener)
currentfr.addEventListener("load", readjustIframe, false)
else if (currentfr.attachEvent){
currentfr.detachEvent("onload", readjustIframe) // Bug fix line
currentfr.attachEvent("onload", readjustIframe)
}
}
}

function readjustIframe(loadevt) {
var crossevt=(window.event)? event : loadevt
var iframeroot=(crossevt.currentTarget)? crossevt.currentTarget : crossevt.srcElement
if (iframeroot)
resizeIframe(iframeroot.id);
}

function loadintoIframe(iframeid, url){
if (document.getElementById)
document.getElementById(iframeid).src=url
}

if (window.addEventListener)
window.addEventListener("load", resizeCaller, false)
else if (window.attachEvent)
window.attachEvent("onload", resizeCaller)
else
window.onload=resizeCaller

</script>

</head>

        <!-- Page-specific script -->

        <script type="text/javascript">

            /*
                 Initialize and render the MenuBar when its elements are ready 
                 to be scripted.
            */

            YAHOO.util.Event.onContentReady("quickToolsActionMenu", function () {

                /*
                     Instantiate a MenuBar:  The first argument passed to the 
                     constructor is the id of the element in the page 
                     representing the MenuBar; the second is an object literal 
                     of configuration properties.
                */

                var oMenuBar = new YAHOO.widget.MenuBar("quickToolsActionMenu", { 
                                                            autosubmenudisplay: true, 
                                                            hidedelay: 250, 
                                                            lazyload: true });

                /*
                     Define an array of object literals, each containing 
                     the data necessary to create a submenu.
                */

                var aSubmenuData = [
                
                    {
                        id: "quickToolsActionsList", 
                        itemdata: [ 
                            { text: "<spring:message code="teacher.index.41"/>", url: "http://www.excite.com" },
                            { text: "<spring:message code="teacher.index.42"/>", url: "http://news.google.com" },
                            { text: "<spring:message code="teacher.index.43"/>", url: "http://www.nytimes.com" },    
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

            /**
             * Asynchronously updates the run with the given id on the server and 
             * displays the appropriate method when completed.
             */
            function extendReminder(id){
				var runLI = document.getElementById('extendReminder_' + id);
				var callback = {
						success:function(o){
							runLI.innerHTML = '<font color="24DD24">You will be reminded to archive project run id ' + id + ' again in 30 days.</font>';
						},
						failure:function(o){
							runLI.innerHTML = '<font color="DD2424">Unable to update the project run with id ' + id + ' on server.</font>';
						},
						scope:this
				};

				runLI.innerHTML = 'Updating run on server...';
				YAHOO.util.Connect.asyncRequest('GET', 'run/manage/extendremindertime.html?runId=' + id, callback, null);
            };
        </script>
    
<body class="yui-skin-sam"> 

<div id="centeredDiv">

<%@ include file="../headerteacherhome.jsp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>







<table id="teacherHomeTable1">
		<tr>
				<td id="welcomePanel">
				<div class="panelStyleWelcome">

				<div id="headerTeacherHome">Welcome</div>

				<table id="teacherWelcomeBoxTable1" cellpadding="3" cellspacing="0">
						<tr class="tableRowBorder">
								<td class="tableColor"><spring:message code="teacher.index.3" /></td>
								<c:set var="current_date" value="<%= new java.util.Date() %>" />
								<td><fmt:formatDate value="${current_date}" type="both" dateStyle="short" timeStyle="short" /></td>
								<td class="tableColor"><spring:message code="teacher.index.4" /></td>
								<td><c:choose>
										<c:when test="${user.userDetails.lastLoginTime == null}">
												<spring:message code="teacher.index.5" />
										</c:when>
										<c:otherwise>
												<fmt:formatDate value="${user.userDetails.lastLoginTime}" type="both" dateStyle="short" timeStyle="short" />
										</c:otherwise>
								</c:choose></td>
						</tr>
				</table>

				<table id="teacherWelcomeBoxTable2" cellpadding="3" cellspacing="0">
						<tr>
								<td class="tableColor"><spring:message code="teacher.index.6" /></td>
								<td></td>
						</tr>
						<tr>
								<td>
								<ul class="announcementsList">
										<li><b> <c:choose>
												<c:when test="${(current_date.hours>=3) && (current_date.hours<12)}">
														<spring:message code="teacher.index.7" />
												</c:when>
												<c:when test="${(current_date.hours>=12) && (current_date.hours<18)}">
														<spring:message code="teacher.index.8" />
												</c:when>
												<c:otherwise>
														<spring:message code="teacher.index.9" />
												</c:otherwise>
										</c:choose> </b></li>

										<c:forEach var="run" items="${run_list}">
												<c:if test='${(run.archiveReminderTime.time - current_date.time) < 0}'>
														<li id='extendReminder_${run.id}'>Your project run <i>${run.name}</i> has been open since
														${run.starttime}. Do you want to archive it now? [ <a
																onclick="window.open('run/manage/archiveRun.html?runId=${run.id}&runName=${run.name}', title, 'toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=1,width=640,height=480,left = 320,top = 240')"><font
																color='blue'>Yes</font></a>/ <a onclick='extendReminder("${run.id}")'><font color='blue'>Remind Me
														Later</font></a>].</li>
												</c:if>
										</c:forEach>

								</ul>
								</td>
						</tr>
				</table>
				</div>
				</td>

				<td style="width: 6px;"></td>

				<td id="dashboardPanel">

				<div class="panelStyleAbout">

				<div id="headerTeacherHome"><spring:message code="teacher.index.13" /></div>

				<table id="dashboardSections" cellspacing="0" cellpadding="0">
						<tr>
								<td><a href="../teacher/index.html" class="tooltip">Home
										<span>Your current location. Includes announcements, links to online community/collaboration tools, and a full listing of your project runs.</span></a></td>
								<td><a href="../teacher/projects/index.html" class="tooltip">Projects
										<span>Search the WISE project library, set up project runs for your classroom, and work on custom-authored & shared projects.</span></a></td>
								<td><a href="../teacher/grading/overview.html" class="tooltip">Grading
										<span>View and grade student work using an assortment of time-saving tools.</span></a></td>
								<td><a href="../teacher/management/overview.html" class="tooltip">Management
										<span>Manage your students, shared projects, account settings, and more.</span></a></td>
								<td><a href="../teacher/help/overview.html" class="tooltip">Help
										<span>Review guidelines and a searchable help-index for tips on running WISE 4.0 smoothly in your classroom.</span></a></td>

						</tr>
				</table>
				</div>

				<div class="panelStyleCommunity">
				<div id="headerTeacherHome">Community Tools</div>
				<div id="communityTagline">Collaborating with others?</div>
				<ul>
						<li>Launch your <a href="#" class="lineThrough">Community Overview</a></li>
						<li>Quicklink to <a href="#" class="lineThrough">MySharedProjects Forum</a></li>
						<li>Quicklink to <a href="#" class="lineThrough">ProjectsSharedWithMe Forum</a></li>
						<li>Quicklink to <a href="#" class="lineThrough">Mentor Forum</a></li>
						<li>Quicklink to <a href="#" class="lineThrough">Professional Development Group</a></li>
				</ul>
				</div>


				</td>
		</tr>
</table>


<table id="teacherHomeTable1">
		<tr>
				<td id="myprojectrunsPanel">

				<div class="panelStyleMyProjectRuns">

				<div id="headerTeacherHome">My Project Runs</div>

				<iframe id="dynamicFrame" src="run/projectruntabs.html" scrolling="no" marginwidth="0" marginheight="0" frameborder="0"
						vspace="0" hspace="0" style="overflow: visible;	overflow-y: hidden;	width: 100%;	display: none;	margin-top: 5px;"></iframe>

				</div>
				</td>
		</tr>
</table>


</div>   <!-- End of centeredDiv-->

</body>

</html>









