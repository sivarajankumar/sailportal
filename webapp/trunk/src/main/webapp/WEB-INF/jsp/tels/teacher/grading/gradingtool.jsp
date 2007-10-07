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
 <link href="../../<spring:theme code="teachergradingtooluistylesheet"/>" media="screen" rel="stylesheet"
  type="text/css" /> 
<style>
@charset "UTF-8";
/* CSS Document */

table {
	border-collapse: collapse;
	border:3;
	border-color: #2647A0;
	padding: 0px;
		
} 

tr, td {
	border-collapse: collapse;
	border:1px;
	padding: 0px;
	margin: 0px
}

#tableByStep {
	border: 0px solid #666666;
	border-collapse: collapse;
	background-color: #FFFFFF;
	padding: 0px;
	margin: 0px;
	cell-padding:5px;
	}

#tableByStep .column1 {
	color:#000000;
	padding: 5px;
}

#tableByStep tr {
	border-bottom: 1px solid #FFFFFF;
}

#gradingProjectInfo {
	padding: 5px;
	}
	
#gradingViewInfo {
	font-size: 1.2em;
	font-weight: bold;
	padding: 5px;
	}
	
#gradingMiniSteps {
	font-size:.8em;
	padding: 5px;
	}
	

#gradingMainTable td {
	border: 1px solid #000000;
	padding:5px;
	}

.tdHeader {
	height:22px;
	padding:7px;
	background-color: #2647A0;
	font-size:1.0em;
	color: #FFFFFF;
	}

.promptTd {
	vertical-align:top;
	background-color:#D3E1E4;

}

.answerDiv {
	height: 100%;
	vertical-align:top;
	
}
.textareaTd {
vertical-align:bottom;
padding: 5px;
<!-- border: px solid #000000;-->
}
textarea {
width: 100%;
background-color:#FFF1F8;
border: 1px solid #000000;
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
	
	function doSubmit(button,podId,rimName,period,workgroupId,runId) {
			YAHOO.log('podId' + podId);
			YAHOO.log('rimName' + rimName);
			YAHOO.log('pe' + period);
			YAHOO.log('runId' + runId)
			
			YAHOO.log( 'button:' + button );
			
			//button.disabled = 'true';
			
			var savedText = 'saved-'+podId+'_'+rimName+'_'+period+'_'+workgroupId;
			var commentedText = 'comment-'+podId+'_'+rimName+'_'+period+'_'+workgroupId;
			var scoreText = 'score-'+podId+'_'+rimName+'_'+period+'_'+workgroupId;
			YAHOO.log( "SAVED " + scoreText);
			 //alert('found: ' + YAHOO.util.Dom.getElementsByClassName(savedText, 'div').length + ' elements');
			
			var el = YAHOO.util.Dom.getElementsByClassName(savedText, 'div');
			
			var tel = YAHOO.util.Dom.getElementsByClassName(commentedText, 'textarea');
			
			var scoreElement = YAHOO.util.Dom.getElementsByClassName(scoreText, 'input');
			
			var score = scoreElement[0].value;
			
			YAHOO.log("score "+ scoreElement[0].value );
			/*
			* Remember to encode the key-value string if and when
			* the string contains special characters.
			*/
			var sUrl = "gradingsubmit.html";
			var postData = 'workgroupId='+workgroupId+'&runId='+runId+'&podId='+podId+'&rimName='+rimName+'&annotationContent='+tel[0].value+'&score='+score;
			
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

<table id="tableByStep">
  <tr>
  	<td class="column1"><strong>Project: </strong></td>
    <td id="gradingProjectInfo">${projectTitle} (${curnitId})</td>
	<td></td>
  </tr>
  <tr>
  	<td class="column1"><strong>View:</strong></td>
    <td id="gradingViewInfo">Act ${activity.number+1}, Step ${step.number+1}: ${step.title}
    </td>
    <td><a href="gradebystep.html?runId=${runId}">Return to Step Menu </a> &nbsp &nbsp <c:if test="${!empty nextStep}"><a href="gradingtool.html?GRADE_TYPE=step&amp;runId=${runId}&amp;podUUID=${nextStep.podUUID}"> View Next Step</a></c:if></td>
  <tr>
    <td class="column1"></td>
    <td id="gradingMiniSteps"></td>
	<td></td>
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
				 <li><a href="${aggregate.key.name}"><em>Period ${aggregate.key.name}</em></a></li> 
			 </c:forEach> 
		 </ul>   
		 <!-- create the tabs content -->
		<div class="yui-content">
			 <c:forEach var="aggregate" varStatus="astatus" items="${stepAggregate}">
			
			 <c:set var="period" value="${fn:replace(aggregate.key.name, ' ', '-')}"/>
			<div>
				<!-- Actual Tab 
					${workgroupAggregateObj} = workgroupAggregateObj
				 -->
				 <c:if test="${empty aggregate.value}">There are no workgroups for this period</c:if>
					<c:forEach var="workgroupAggregateObj" varStatus="workgroupAggregateObjStatus" items="${aggregate.value}">
						<!-- get the workgroup id -->
						
						<c:set var="workgroupId" value="${workgroupAggregateObj.workgroup.id}"/>
						<div align="center">
						<table width="100%" border="1">
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
										
										<td width="45%" >
										<div align="center" class="tdHeader" ><strong class="headerFont">Teacher Feedback</strong></div>
										</td>
										<td >
										<div align="center" class="tdHeader" ><strong class="headerFont">Score</strong></div>
										</td>
									</tr>
							<!-- End Table Header -->
							<!-- for no work in work group -->
								<c:set var="noWorkFound" value="true"/>
								<c:forEach var="sockPart" varStatus="partStatus" items="${workgroupAggregateObj.sessionBundle.ESessionBundle.sockParts}">
									<c:forEach var="rimFromStep" items="${step.rim}">
											<c:if test="${sockPart.rimName == rimFromStep.rimname}">
												<c:set var="noWorkFound" value="false"/>
											</c:if>
									</c:forEach>
								</c:forEach>
							
						
							<c:if test="${noWorkFound == true}">
								<tr><td colspan="3" align="center">There is no work for this workgroup</td></tr>
							</c:if>
							<!-- no work found -->
								<c:set var="cellCounter" value="0"/>
								<c:forEach var="sockPart" varStatus="partStatus" items="${workgroupAggregateObj.sessionBundle.ESessionBundle.sockParts}">
									<c:forEach var="rimFromStep" items="${step.rim}">
											<c:if test="${sockPart.rimName == rimFromStep.rimname}">
												<c:set var="cellCounter" value="${cellCounter+1}"/>
											</c:if>
									</c:forEach>
								</c:forEach>
								<c:set var="count" value="0"/>
								<c:forEach var="sockPart" varStatus="partStatus" items="${workgroupAggregateObj.sessionBundle.ESessionBundle.sockParts}">
								
									
									<c:forEach var="rimFromStep" items="${step.rim}">
										
											<c:if test="${sockPart.rimName == rimFromStep.rimname}">
											<c:set var="count" value="${count+1}"/>
											<tr>
				                          		<td class="promptTd">
				                          		
				                          		<c:choose>
												      <c:when test="${empty rimFromStep.prompt}">
												      There is no question
												      </c:when>
												
												      <c:otherwise>
																${rimFromStep.prompt}
												      </c:otherwise>
												   </c:choose>
				                          		
				                          		
				                          		
				                          		<!-- print out part if more than one element -->
				                          		</td>
				                          		<c:choose>
												      <c:when test="${count == 1}">
												     	<td rowspan="${cellCounter*2}" class="textareaTd">
												     	<!-- do annotation magic here -->
												     	<c:set var="commentDone" value="false"/>
												     		<c:forEach var="annotationGroup" items="${workgroupAggregateObj.annotationBundle.EAnnotationBundle.annotationGroups}">
																	
																	<c:forEach var="annotation" items="${annotationGroup.annotations}">
																		<c:if test="${annotationGroup.annotationSource == 'http://telscenter.org/annotation/comment'}">
																		
																			<c:if test="${annotation.entityUUID == step.podUUID}">
																			<c:if test="${commentDone == false}">
																				<c:set var="comment" value="${annotation.contents}"/>
																				<c:set var="commentDone" value="true"/>
																			</c:if>
																			</c:if>
																		</c:if>
																		</c:forEach>
																	</c:forEach>
														    <div id="div_${sockPart.podId}_${sockPart.rimName}_${workgroupId}" >
																	<textarea id="comment-${sockPart.podId}_${sockPart.rimName}_${period}_${workgroupId}" class="comment-${sockPart.podId}_${sockPart.rimName}_${period}_${workgroupId}" cols="45" rows="6"  onKeyPress="enableButton(this,'${sockPart.podId}','${sockPart.rimName}','${period}')" >${comment}</textarea>
															</div>
												     	
												     	</td>
												      </c:when>
												
												      <c:otherwise>
														  <!-- no cell-->
<!--												      <td >nope</td>-->
												      </c:otherwise>
												   </c:choose>
				                          	
				                          		<c:choose>
												      <c:when test="${count == 1}">
												     	<td rowspan="${cellCounter*2}">
												     		<c:set var="scoreDone" value="false"/>
												     		<c:forEach var="annotationGroup" items="${workgroupAggregateObj.annotationBundle.EAnnotationBundle.annotationGroups}">
																	
																	<c:forEach var="annotation" items="${annotationGroup.annotations}">
																		<c:if test="${annotationGroup.annotationSource == 'http://telscenter.org/annotation/score'}">
																			<c:if test="${annotation.entityUUID == step.podUUID}">
																			<c:if test="${scoreDone == false}">
																				<c:set var="score" value="${annotation.contents}"/>
																				<c:set var="scoreDone" value="true"/>
																			</c:if>
																			</c:if>
																		</c:if>
									
																	</c:forEach>
															</c:forEach>
															<input class="score-${sockPart.podId}_${sockPart.rimName}_${period}_${workgroupId}" type="text" size="5" value="${score}"> 
																	<span id="pushbutton-${sockPart.podId}_${sockPart.rimName}_${period}_${workgroupId}" class="yui-button yui-push-button"><em class="first-child">
																			<button type="submit" name="pushbutton-${sockPart.podId}_${sockPart.rimName}_${period}_${workgroupId}" onClick="javascript:doSubmit(this,'${sockPart.podId}','${sockPart.rimName}','${period}','${workgroupId}','${runId}')">Save</button></em>
																	</span>
															<div class="saved-${sockPart.podId}_${sockPart.rimName}_${period}_${workgroupId}" style="display: inline; width: 12%;"></div>
														     	
												     	
												     	</td>
												      </c:when>
												
												      <c:otherwise>
														  <!-- no cell-->
<!--												      <td >nope</td>-->
												      </c:otherwise>
												   </c:choose>
				                          	
				                          		
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
		  			 							
												<!-- no row -->
												<!-- no row -->
												</tr>
                    						</c:if>
									</c:forEach>
								</c:forEach>
									
							</table>
							</div>
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
