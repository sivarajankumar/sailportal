<%@ include file="include.jsp"%>
<!-- 
 * Copyright (c) 2007 Regents of the University of California (Regents). Created
 * by TELS, Graduate School of Education, University of California at Berkeley.
 *
 * This software is distributed under the GNU Lesser General Public License, v2.
 *
 * Permission is hereby granted, without written agreement and without license
 * or royalty fees, to use, copy, modify, and distribute this software and its
 * documentation for any purpose, provided that the above copyright notice and
 * the following two paragraphs appear in all copies of this software.
 *
 * REGENTS SPECIFICALLY DISCLAIMS ANY WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE. THE SOFTWAREAND ACCOMPANYING DOCUMENTATION, IF ANY, PROVIDED
 * HEREUNDER IS PROVIDED "AS IS". REGENTS HAS NO OBLIGATION TO PROVIDE
 * MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
 *
 * IN NO EVENT SHALL REGENTS BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
 * SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
 * ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
 * REGENTS HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
-->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<%@page
	import="org.telscenter.sail.webapp.domain.grading.GradeWorkAggregate"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link href="<spring:theme code="registerstylesheet" />" media="screen"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../.././javascript/tels/yui/reset/reset-min.css"> 
<link rel="stylesheet" type="text/css" href="../.././javascript/tels/yui/reset/reset-min.css"> 
<link rel="stylesheet" type="text/css" href="../.././javascript/tels/yui/base/base-min.css"> 
<link rel="stylesheet" type="text/css" href="../.././javascript/tels/yui/fonts/fonts-min.css"> 
<link rel="stylesheet" type="text/css" href="../.././javascript/tels/yui/grids/grids-min.css"> 
 
<!--CSS for Controls:--> 
<link rel="stylesheet" type="text/css" href="../.././javascript/tels/yui/container/assets/skins/sam/container.css"> 
<link rel="stylesheet" type="text/css" href="../.././javascript/tels/yui/menu/assets/skins/sam/menu.css"> 
<link rel="stylesheet" type="text/css" href="../.././javascript/tels/yui/autocomplete/assets/skins/sam/autocomplete.css"> 
<link rel="stylesheet" type="text/css" href="../.././javascript/tels/yui/button/assets/skins/sam/button.css"> 
<link rel="stylesheet" type="text/css" href="../.././javascript/tels/yui/calendar/assets/skins/sam/calendar.css"> 
<link rel="stylesheet" type="text/css" href="../.././javascript/tels/yui/colorpicker/assets/skins/sam/colorpicker.css"> 
<link rel="stylesheet" type="text/css" href="../.././javascript/tels/yui/datatable/assets/skins/sam/datatable.css"> 
<link rel="stylesheet" type="text/css" href="../.././javascript/tels/yui/editor/assets/skins/sam/editor.css"> 
<link rel="stylesheet" type="text/css" href="../.././javascript/tels/yui/logger/assets/skins/sam/logger.css"> 
<link rel="stylesheet" type="text/css" href="../.././javascript/tels/yui/tabview/assets/skins/sam/tabview.css"> 
<link rel="stylesheet" type="text/css" href="../.././javascript/tels/yui/treeview/assets/skins/sam/treeview.css"> 
<style type="text/css">
<!--
.style1 {
	color: #FF0033;
	font-weight: bold;
}
-->
</style>
 
<!--JavaScript source files for the entire YUI Library:--> 
 
<!--Utilities (also aggregated in yahoo-dom-event.js and utilities.js; see readmes in the 
YUI download for details on each of the aggregate files and their contents):--> 
<script type="text/javascript" src="../.././javascript/tels/yui/yahoo/yahoo-min.js"></script> 
<script type="text/javascript" src="../.././javascript/tels/yui/dom/dom-min.js"></script> 
<script type="text/javascript" src="../.././javascript/tels/yui/event/event-min.js"></script> 
<script type="text/javascript" src="../.././javascript/tels/yui/element/element-beta-min.js"></script> 
<script type="text/javascript" src="../.././javascript/tels/yui/animation/animation-min.js"></script> 
<script type="text/javascript" src="../.././javascript/tels/yui/connection/connection-min.js"></script> 
<script type="text/javascript" src="../.././javascript/tels/yui/datasource/datasource-beta-min.js"></script> 
<script type="text/javascript" src="../.././javascript/tels/yui/dragdrop/dragdrop-min.js"></script> 
<script type="text/javascript" src="../.././javascript/tels/yui/history/history-beta-min.js"></script> 
<script type="text/javascript" src="../.././javascript/tels/yui/imageloader/imageloader-experimental-min.js"></script> 
<script type="text/javascript" src="../.././javascript/tels/yui/yuiloader/yuiloader-beta-min.js"></script> 
 
<!--YUI's UI Controls:--> 
<script type="text/javascript" src="../.././javascript/tels/yui/container/container-min.js"></script> 
<script type="text/javascript" src="../.././javascript/tels/yui/menu/menu-min.js"></script> 
<script type="text/javascript" src="../.././javascript/tels/yui/autocomplete/autocomplete-min.js"></script> 
<script type="text/javascript" src="../.././javascript/tels/yui/button/button-beta-min.js"></script> 
<script type="text/javascript" src="../.././javascript/tels/yui/calendar/calendar-min.js"></script> 
<script type="text/javascript" src="../.././javascript/tels/yui/colorpicker/colorpicker-beta-min.js"></script> 
<script type="text/javascript" src="../.././javascript/tels/yui/datatable/datatable-beta-min.js"></script> 
<script type="text/javascript" src="../.././javascript/tels/yui/editor/editor-beta-min.js"></script> 
<script type="text/javascript" src="../.././javascript/tels/yui/logger/logger-min.js"></script> 
<script type="text/javascript" src="../.././javascript/tels/yui/slider/slider-min.js"></script> 
<script type="text/javascript" src="../.././javascript/tels/yui/tabview/tabview-min.js"></script> 
<script type="text/javascript" src="../.././javascript/tels/yui/treeview/treeview-min.js"></script> 
<script type="text/javascript">
		var div = document.getElementById('container');
		var handleSuccess = function(o){
			YAHOO.log("The success handler was called.  tId: " + o.tId + ".", "info", "example");
			if(o.responseText !== undefined){
				div.innerHTML = "<li>Transaction id: " + o.tId + "</li>";
				div.innerHTML += "<li>HTTP status: " + o.status + "</li>";
				div.innerHTML += "<li>Status code message: " + o.statusText + "</li>";
				div.innerHTML += "<li>HTTP headers received: <ul>" + o.getAllResponseHeaders + "</ul></li>";
				div.innerHTML += "<li>PHP response: " + o.responseText + "</li>";
				div.innerHTML += "<li>Argument object: Array ([0] => " + o.argument[0] +
								 " [1] => " + o.argument[1] + " )</li>";
			}
		};

		var handleFailure = function(o){
				YAHOO.log("The failure handler was called.  tId: " + o.tId + ".", "info", "example");
		
			if(o.responseText !== undefined){
				div.innerHTML = "<li>Transaction id: " + o.tId + "</li>";
				div.innerHTML += "<li>HTTP status: " + o.status + "</li>";
				div.innerHTML += "<li>Status code message: " + o.statusText + "</li>";
			}
		};

		var callback =
		{
		  success:handleSuccess,
		  failure:handleFailure,
		  argument:['foo','bar']
		};

	
	function doSubmit(podId,rimName,period) {
			YAHOO.log('podId' + podId);
			YAHOO.log('rimName' + rimName);
			YAHOO.log('pe' + period);
			
			
			
			var savedText = 'saved-'+podId+'_'+rimName+'_'+period;
			var commentedText = 'comment-'+podId+'_'+rimName+'_'+period;
			
			YAHOO.log( "SAVED " + savedText);
			 //alert('found: ' + YAHOO.util.Dom.getElementsByClassName(savedText, 'div').length + ' elements');
			
			var el = YAHOO.util.Dom.getElementsByClassName(savedText, 'div');
			
			var tel = YAHOO.util.Dom.getElementsByClassName(commentedText, 'textarea');
			
			/*
			* Remember to encode the key-value string if and when
			* the string contains special characters.
			*/
			var sUrl = "gradingsubmit.html";
			var postData = 'workgroupId=3434&runId=${runId}&podId='+podId+'&rimName='+rimName+'&annotationContent='+tel[0].value;
			
			var request = YAHOO.util.Connect.asyncRequest('POST', sUrl, null, postData);	
			YAHOO.log('comente '+ tel[0].value);
			for(var i = 0; i < el.length; i++) {
				el[i].innerHTML = "<span class='style1'>saved!</span>";
			} 
			
			/*
			//alert('Element' + document.getElementById(bob+'_saved') );
			var el = document.getElementById(podId+'_saved');
			YAHOO.log( "el: " + el + "html: " + el.innerHTML);
			
			el.innerHTML = "<span class='style1'>saved!</span>";
			//alert('found: ' + YAHOO.util.Dom.getElementsByClassName('bar', 'div')).length + ' elements'); 
			*/
			
			
			
		};
	//create tab
    var tabView = new YAHOO.widget.TabView('periodTabs'); 
    tabView.set('activeIndex', 0); 
    
    //create logger
    var myContainer = document.body.appendChild(document.createElement("div")); 
	var myLogReader = new YAHOO.widget.LogReader(myContainer); 
</script>

<body class=" yui-skin-sam">

<h3 align="center">Grading tool</h3>

<h2>Step title</h2>
<!-- 
aggregate.key = period
aggregate.value = aggregate
 -->

<div id="periodTabs" class="yui-navset"> 
		<!-- create the tabs nav -->
		<ul class="yui-nav"> 
			<c:forEach var="aggregate" varStatus="astatus" items="${stepAggregate}">
				 <li><a href="${aggregate.key.name}"><em>${aggregate.key.name}</em></a></li> 
			 </c:forEach> 
		 </ul>   
		 <!-- create the tabs content -->
		<div class="yui-content">
			 <c:forEach var="aggregate" varStatus="astatus" items="${stepAggregate}">
			 <c:set var="period" value="${fn:replace(aggregate.key.name, ' ', '-')}"/>
			<div>
				<!-- Actual Tab 
					${sessionBundles.key} = workGroup
					${sessionBundles.value} = sessionBundle
				 -->
					<c:forEach var="sessionBundles" items="${aggregate.value.sessionBundles}">
						<!-- get the workgroup id -->
						<c:set var="workgroupId" value="${sessionBundles.key.id}"/>
						<table width="853" border="1">
						<!-- table header -->
									<tr>
										<td width="492">
										<!--  print member anmes -->
										<div align="center">
										
										<strong>Group:<c:forEach var="user" varStatus="userStatus"
											items="${sessionBundles.key.members}">
										 		${user.userDetails.username}
										 		   <c:if test="${userStatus.last=='false'}">
							     					&
							    				</c:if>
										 	</c:forEach> </strong></div>
										</td>
										<td width="268">
										<div align="center"><strong>Teacher Feedback</strong></div>
										</td>
							
									</tr>
								<!-- 1st row prompt, 2nd answer and feedback box -->
								<c:forEach var="sockPart" items="${sessionBundles.value.ESessionBundle.sockParts}">
									<c:forEach var="rimFromStep" items="${aggregate.value.step.rim}">
											<c:if test="${sockPart.rimName == rimFromStep.rimname}">
											<tr>
				                          		<td>Display prompt ${rimFromStep.prompt}</td>
				                          		<td></td>
				             				</tr>
											<tr>
												<!-- The users entrie -->
												<td>
												<c:forEach var="sockEntry" items="${sockPart.sockEntries}">
		  			 									${sockEntry.value}
		  			 							</c:forEach>
		  			 							</td>
		  			 							<!-- The teacher box -->
		  			 							<!-- check for annotation -->
		  			 							
												<td>
													
													<c:forEach var="annotationGroup" items="${aggregate.value.annotationBundles[sessionBundles.key].EAnnotationBundle.annotationGroups}">
													<c:set var="done" value="false"/>
														<c:forEach var="annotation" items="${annotationGroup.annotations}">
															
															<c:if test="${done == false}">
																	<c:choose>
																      <c:when  test="${annotation.entityName == sockPart.rimName}">got it.
																      	<c:set var="done" value="true"/>
																      	<c:set var="foundAnnotation" value="${annotation}"/>
																      </c:when>
																    </c:choose>
														    </c:if>
															</c:forEach>
														</c:forEach>
												
												
													
												
													<div id="div_${sockPart.podId}_${sockPart.rimName}" >
													<textarea class="comment-${sockPart.podId}_${sockPart.rimName}_${period}" cols="35" rows="3"><c:if test="${done == true}">${fn:trim(foundAnnotation.contents)}</c:if></textarea>
													<fieldset id="pushbuttonsfrommarkup">
														<span id="pushbutton-${sockPart.podId}_${sockPart.rimName}_${period}" class="yui-button yui-push-button"><em class="first-child">
															<button type="submit" name="button5" onClick="javascript:doSubmit('${sockPart.podId}','${sockPart.rimName}','${period}')">Save Comment</button></em>
														</span>
													</fieldset>
													</div><div class="saved-${sockPart.podId}_${sockPart.rimName}_${period}" >not saved</div>
												
												</td>
											</tr>
                    						</c:if>
									</c:forEach>
								</c:forEach>
							</table>
						
					</c:forEach> 
		
		
		
				</div>	 
			</c:forEach> 
		</div>
		<!-- end create tab content -->
</div>
<div id="myLogger"></div>
<script type="text/javascript">
var myLogReader = new YAHOO.widget.LogReader("myLogger");
</script>
<div id="container"></div>
</body>
</html>
