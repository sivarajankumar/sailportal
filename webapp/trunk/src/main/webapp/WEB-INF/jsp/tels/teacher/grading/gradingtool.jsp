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
<link href="../../<spring:theme code="teachergradingstylesheet"/>" media="screen" rel="stylesheet"
  type="text/css" />

<link rel="stylesheet" type="text/css" href="../.././javascript/tels/yui/reset/reset-min.css"> 
<link rel="stylesheet" type="text/css" href="../.././javascript/tels/yui/reset/reset-min.css"> 
<link rel="stylesheet" type="text/css" href="../.././javascript/tels/yui/base/base-min.css"> 
<link rel="stylesheet" type="text/css" href="../.././javascript/tels/yui/fonts/fonts-min.css"> 
 
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
 
.style1 {
	color: #FF0033;
	font-weight: bold;
} 
table.view {
	border-width: 0px 0px 0px 0px;
	border-spacing: 0px;
	border-style: none none none none;
	border-color: #EDF5FF #EDF5FF #EDF5FF #EDF5FF;
	border-collapse: collapse;
	background-color: white;
}
table.view th {
	border-width: 0px 0px 0px 0px;
	padding: 0px 0px 0px 0px;
	border-style: none none none none;
	border-color: none none none none;
	background-color: white;
}
table.view td {
	border-width: 0px 0px 0px 0px;
	padding: 2px 2px 2px 2px;
	border-style: none none none none;
	border-color: none none none none;
	background-color: white;
}
table.sample {
	border-width: 0px 0px 0px 0px;
	border-spacing: 0px;
	border-style: none none none none;
	border-color: #EDF5FF #EDF5FF #EDF5FF #EDF5FF;
	border-collapse: collapse;
	background-color: #EDF5FF;
}
table.sample th {
	border-width: 0px 0px 0px 0px;
	padding: 0px 0px 0px 0px;
	border-style: none none none none;
	border-color: none none none none;
	background-color: #EDF5FF;
}
table.sample td {
	border-width: 0px 0px 0px 0px;
	padding: 2px 2px 2px 2px;
	border-style: none none none none;
	border-color: none none none none;
	background-color: #EDF5FF;
}
.tdHeader {
	background-color: #2647A0;
}
.headerFont {
	color: #FFFFFF;
}
.promptDiv {
	border-width: 1px 1px 1px 1px;
	border-style: solid solid solid solid;
	background-color: white;
	border-top-color: #000000;
	border-right-color: #000000;
	border-bottom-color: #000000;
	border-left-color: #000000;
}
.answerDiv {
	border-width: 0px 0px 0px 0px;
	border-style: solid solid solid solid;
	background-color: white;
	border-top-color: #000000;
	border-right-color: #000000;
	border-bottom-color: #000000;
	border-left-color: #000000;
	height: 100%;
}

textarea {
width: 100%;
}

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

	function enableButton( textarea,podId,rimName,period) {
		
		var buttonText = 'pushbutton-'+podId+'_'+rimName+'_'+period;
		var savedText = 'saved-'+podId+'_'+rimName+'_'+period;
		
		var buttons = document.getElementById(buttonText);
		var names = document.getElementsByTagName(buttonText);
		for(var i = 0; i < names.length; i++) {
				YAHOO.log( 'dfdfd' + names[i]);
			}
			
		//set the textarea background color white
		textarea.style.backgroundColor ="#FFFFFF";
		
	//	YAHOO.util.Dom.setStyle(commentedText, 'background-color', 'white'); 
		
		//make the comment "not saved"
		var savedEl = YAHOO.util.Dom.getElementsByClassName(savedText, 'div');
		YAHOO.log( 'saved ' + savedEl );
		for(var i = 0; i < savedEl.length; i++) {
			savedEl[i].innerHTML = "not saved";
		}
			
		
	};
	
	function doSubmit(button,podId,rimName,period,workgroupId) {
			YAHOO.log('podId' + podId);
			YAHOO.log('rimName' + rimName);
			YAHOO.log('pe' + period);
			
			YAHOO.log( 'button:' + button );
			
			//button.disabled = 'true';
			
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
			var postData = 'workgroupId='+workgroupId+'&runId=${runId}&podId='+podId+'&rimName='+rimName+'&annotationContent='+tel[0].value;
			
			var request = YAHOO.util.Connect.asyncRequest('POST', sUrl, null, postData);	
			
			
			//change the comment
			YAHOO.log('comente '+ tel[0].value);
			for(var i = 0; i < el.length; i++) {
				el[i].innerHTML = "saved!";
			}
			
		
			YAHOO.util.Dom.setStyle(commentedText, 'background-color', 'white'); 
		
			
		};
	//create tab
    var tabView = new YAHOO.widget.TabView('periodTabs'); 
    tabView.set('activeIndex', 0); 
    
    //create logger
    var myContainer = document.body.appendChild(document.createElement("div")); 
	var myLogReader = new YAHOO.widget.LogReader(myContainer); 
</script>

<body class=" yui-skin-sam">
<%@ include file="gradingtoolHeader.jsp"%>
<h2>Grading Tool</h2>

<table class="view">
  <tr>
  	<td>Project: </td>
    <td><div align="left">${projectTitle} (${curnitId})</div></td>
    <td></td>
  </tr>
    <tr>
    <td>View:</td>
    <td><div align="left">Act ${activity.number+1},Step ${step.number+1}: ${step.title}</td>
    <td>   <a href="gradebystep.html?runId=${runId}">All Steps</a> <c:if test="${nextStep != null}"><a href="gradingtool.html?GRADE_TYPE=step&runId=${runId}&podUUID=${nextStep.podUUID}">Next Step</a></c:if></div></td>
  </tr>
  </table>


<!-- 
aggregate.key = period
aggregate.value = set of workgroupWorkAggregate
 -->

<div id="periodTabs" class="yui-navset"> 
		<!-- create the tabs nav -->
		<ul class="yui-nav"> 
			<c:forEach var="aggregate" varStatus="astatus" items="${stepAggregate}">
				<c:if test="${!empty aggregate.value}">
				 <li><a href="${aggregate.key.name}"><em>Period ${aggregate.key.name}</em></a></li> 
				 </c:if>
			 </c:forEach> 
		 </ul>   
		 <!-- create the tabs content -->
		<div class="yui-content">
			 <c:forEach var="aggregate" varStatus="astatus" items="${stepAggregate}">
			 <c:if test="${!empty aggregate.value}">
			 <c:set var="period" value="${fn:replace(aggregate.key.name, ' ', '-')}"/>
			<div>
				<!-- Actual Tab 
					${workgroupAggregateObj} = workgroupAggregateObj
				 -->
					<c:forEach var="workgroupAggregateObj" varStatus="workgroupAggregateObjStatus" items="${aggregate.value}">
						<!-- get the workgroup id -->
						<c:set var="workgroupId" value="${workgroupAggregateObj.workgroup.id}"/>
						<div align="center">
						<table width="100%" border="1" class="sample">
						<!-- table header -->
									<tr>
										<td width="40%">
										<!--  print member anmes -->
										<div align="center" class="tdHeader">
										<strong class="headerFont">Group:
										<c:forEach var="user" varStatus="userStatus"
											items="${workgroupAggregateObj.workgroup.members}">
										 		${user.userDetails.username}
										 		   <c:if test="${userStatus.last=='false'}">
							     					&
							    				</c:if>
										 	</c:forEach> </strong>
										 </div>
										</td>
										
										<td width="45%">
										<div align="center" class="tdHeader" ><strong class="headerFont">Teacher Feedback</strong></div>
										</td>
										<td >
										<div align="center" class="tdHeader" ><strong class="headerFont">Score</strong></div>
										</td>
									</tr>
									
								<c:set var="count" value="1"/>
								<c:forEach var="sockPart" varStatus="partStatus" items="${workgroupAggregateObj.sessionBundle.ESessionBundle.sockParts}">
									<c:forEach var="rimFromStep" items="${step.rim}">
											<c:if test="${sockPart.rimName == rimFromStep.rimname}">
											<tr>
				                          		<td>
				                          		<div class="promptDiv">
				                          		 <c:choose>
											        <c:when test="${fn:length(step.rim) > 1}">
											            Part ${count}: ${rimFromStep.prompt}
											            <c:set var="count" value="${count + 1}"/>
											        </c:when>
											        <c:otherwise>
											           ${rimFromStep.prompt}
											        </c:otherwise>
											    </c:choose>
				                          		<!-- print out part if more than one element -->
				                          		</div>
				                          		</td>
				                          		<td></td>
				                          		<td></td>
				             				</tr>
											<tr>
												<!-- The users entrie -->
												<td>
												
												<c:forEach var="sockEntry" items="${sockPart.sockEntries}">
												<div class="answerDiv">
		  			 									${sockEntry.value}
		  			 							</div>
		  			 							</c:forEach>
		  			 							
		  			 							</td>
		  			 							<!-- The teacher box -->
		  			 							<!-- check for annotation -->
		  			 							
												<td>
													
													<c:forEach var="annotationGroup" items="${workgroupAggregateObj.annotationBundle.EAnnotationBundle.annotationGroups}">
													<c:set var="done" value="false"/>
														<c:forEach var="annotation" items="${annotationGroup.annotations}">
															
															<c:if test="${done == false}">
																	<c:choose>
																      <c:when  test="${annotation.entityName == sockPart.rimName}">
																      	<c:set var="done" value="true"/>
																      	<c:set var="foundAnnotation" value="${annotation}"/>
																      </c:when>
																    </c:choose>
														    </c:if>
															</c:forEach>
														</c:forEach>
												
												
													
												
													<div id="div_${sockPart.podId}_${sockPart.rimName}" >
													<textarea id="comment-${sockPart.podId}_${sockPart.rimName}_${period}" class="comment-${sockPart.podId}_${sockPart.rimName}_${period}" cols="45" rows="6" style="background-color:#FFCCCC" onKeyPress="enableButton(this,'${sockPart.podId}','${sockPart.rimName}','${period}')"><c:if test="${done == true}">${fn:trim(foundAnnotation.contents)}</c:if></textarea>
														<span id="pushbutton-${sockPart.podId}_${sockPart.rimName}_${period}" class="yui-button yui-push-button"><em class="first-child">
															<button type="submit" name="pushbutton-${sockPart.podId}_${sockPart.rimName}_${period}" onClick="javascript:doSubmit(this,'${sockPart.podId}','${sockPart.rimName}','${period}','${workgroupId}')">Save Comment</button></em>
														</span>
														<div class="saved-${sockPart.podId}_${sockPart.rimName}_${period}" style="display: inline; width: 12%;">not saved</div>
													</div>
												
												</td>
												<td></td>
											</tr>
                    						</c:if>
									</c:forEach>
								</c:forEach>
									
							</table>
							</div>
					</c:forEach> 
		
		
		
				</div>	 
				</c:if>
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
