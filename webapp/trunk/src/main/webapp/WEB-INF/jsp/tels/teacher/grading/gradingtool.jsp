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
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<%@ include file="styles.jsp"%>

<link href="../../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="teachergradingstylesheet"/>" media="screen" rel="stylesheet" type="text/css" /> 


<!-- use apaches string utils -->

<script type='text/javascript' src='/webapp/dwr/interface/StringUtilsJS.js'></script>
<script type='text/javascript' src='/webapp/dwr/engine.js'></script>

</head>
<body class=" yui-skin-sam">
<script type="text/javascript">

	//preload image if browser is not IE because animated gif will just freeze if user is using IE
	if(navigator.appName != "Microsoft Internet Explorer") {
		loadingImage = new Image();
		loadingImage.src = "/webapp/themes/tels/default/images/rel_interstitial_loading.gif";
	}
	
	YAHOO.namespace("example.container");

    function init() {

        if (!YAHOO.example.container.wait) {

            // Initialize the temporary Panel to display while waiting for external content to load

            YAHOO.example.container.wait = 
                    new YAHOO.widget.Panel("wait",  
                                                    { width: "240px", 
                                                      fixedcenter: true, 
                                                      close: false, 
                                                      draggable: false, 
                                                      zindex:4,
                                                      modal: true,
                                                      visible: false
                                                    } 
                                                );

            //YAHOO.example.container.wait.setHeader("Loading, please wait...");
            YAHOO.example.container.wait.setBody("<table><tr align='center'>Loading, please wait...</tr><tr align='center'><img src=/webapp/themes/tels/default/images/rel_interstitial_loading.gif /></tr><table>");
            YAHOO.example.container.wait.render(document.body);

        }

        // Define the callback object for Connection Manager that will set the body of our content area when the content has loaded



        var callback = {
            success : function(o) {
                //content.innerHTML = o.responseText;
                //content.style.visibility = "visible";
                YAHOO.example.container.wait.hide();
            },
            failure : function(o) {
                //content.innerHTML = o.responseText;
                //content.style.visibility = "visible";
                //content.innerHTML = "CONNECTION FAILED!";
                YAHOO.example.container.wait.hide();
            }
        }
    
        // Show the Panel
        YAHOO.example.container.wait.show();
        
        // Connect to our data source and load the data
        //var conn = YAHOO.util.Connect.asyncRequest("GET", "assets/somedata.php?r=" + new Date().getTime(), callback);
    }

		YAHOO.util.Event.on("previousStepLinkTop", "click", init);
		YAHOO.util.Event.on("nextStepLinkTop", "click", init);
		YAHOO.util.Event.on("previousStepLinkBottom", "click", init);
		YAHOO.util.Event.on("nextStepLinkBottom", "click", init);
	
	
		//create tab
	    var tabView = new YAHOO.widget.TabView('periodTabs'); 
	    
		tabView.set('activeIndex', ${tabIndex});								        
	    tabView.addListener('activeTabChange', handleTabClick); 
	    
	    /**
	     * When tabs change handle a click
	     */
	     function handleTabClick(e) { 
	     	 var nextVar = document.getElementById('nextStepLinkTop');  
	     	 var tabIndex = tabView.getTabIndex( e.newValue );
	     	 nextVar.href = 'gradingtool.html?GRADE_TYPE=step&runId=${runId}&podUUID=${nextStep.podUUID}&tabIndex='+tabIndex;

	     	 var nextVar = document.getElementById('nextStepLinkBottom');  
	     	 var tabIndex = tabView.getTabIndex( e.newValue );
	     	 nextVar.href = 'gradingtool.html?GRADE_TYPE=step&runId=${runId}&podUUID=${nextStep.podUUID}&tabIndex='+tabIndex;

	     	 
	     	 //previousStep
	     	 var previousVar = document.getElementById('previousStepLinkTop');
	     	 if( previousVar != null ) {  
	     	 	var tabIndex = tabView.getTabIndex( e.newValue );
	     	 	previousVar.href = 'gradingtool.html?GRADE_TYPE=step&runId=${runId}&podUUID=${previousStep.podUUID}&tabIndex='+tabIndex;
	     	 }// if
	     	 
	     	 var previousVar = document.getElementById('previousStepLinkBottom');
	     	 if( previousVar != null ) {  
	     	 	var tabIndex = tabView.getTabIndex( e.newValue );
	     	 	previousVar.href = 'gradingtool.html?GRADE_TYPE=step&runId=${runId}&podUUID=${previousStep.podUUID}&tabIndex='+tabIndex;
	     	 }// if
	    } 
	     
	   
		
		
		/**
		 * handle success handler
		 */
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

		/**
		 * handle fail handler
		 */
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
				//YAHOO.log( 'dfdfd' + names[i]);
			}
			
		//set the textarea background color white
		textarea.style.backgroundColor ="#FFFFFF";
		
		//make the comment "not saved"
		var savedEl = YAHOO.util.Dom.getElementsByClassName(savedText, 'div');
		
		//YAHOO.log( 'saved ' + savedEl );
		for(var i = 0; i < savedEl.length; i++) {
			savedEl[i].innerHTML = "not saved";
		}
			
		
	};
	
	/**
	 * submits the annotation
	 */
	function doSubmit(button,podId,rimName,period,workgroupId,runId) {
			//YAHOO.log('podId' + podId);
			//YAHOO.log('rimName' + rimName);
			//YAHOO.log('pe' + period);
			//YAHOO.log('runId' + runId);
			//YAHOO.log( 'button:' + button );
			//button.disabled = 'true';
			
			var savedText = 'saved-'+podId+'_'+workgroupId;
			var commentedText = 'comment-'+podId+'_'+workgroupId;
			var teacherScore = 'teacher-score-'+podId+'_'+workgroupId;
			var possibleScore = 'possible-score-'+podId+'_'+workgroupId;
			
			// alert('found: ' + YAHOO.util.Dom.getElementsByClassName(savedText, 'div').length + ' elements');
			
			var savedMessageLabel = YAHOO.util.Dom.getElementsByClassName(savedText, 'div');
			
			var tel = YAHOO.util.Dom.getElementsByClassName(commentedText, 'textarea');
			
			var teacherScoreElement = YAHOO.util.Dom.getElementsByClassName(teacherScore, 'input');
			
			var possibleScoreElement = YAHOO.util.Dom.getElementsByClassName(possibleScore, 'input');
			
			var teacherScore = teacherScoreElement[0].value;
			
			var possibleScore = possibleScoreElement[0].value;
			
			var possibleScoreResult;


			//checkk the inputs of the score boxes
			StringUtilsJS.isNumeric(teacherScore, {
					  callback:function(teacherScoreResult) { 

					    // if the result is false display a dialog
					  	if( teacherScoreResult == false ) {
					  		displayScoreDialog(teacherScore + " is not a valid score, please input a number"); 
					  	} else {
						  	
					  		if( eval(teacherScore) <=  eval(possibleScore) ) {

								/*
								* Remember to encode the key-value string if and when
								* the string contains special characters.
								*/
								var sUrl = "gradingsubmit.html";
								var postData = 'workgroupId='+workgroupId+'&runId='+runId+'&podId='+podId+'&rimName='+rimName+'&annotationContent='+tel[0].value+'&teacherScore='+teacherScore+'&possibleScore='+possibleScore;
								
								var request = YAHOO.util.Connect.asyncRequest('POST', sUrl, null, postData);	
								
								//change the comment			
								savedMessageLabel[0].innerHTML = "information saved!";
								
								YAHOO.util.Dom.setStyle(commentedText, 'background-color', 'white'); 
					  		} else {
					  			displayScoreDialog(teacherScore + " cannot be greater than the possible score "+ possibleScore); 
					  		}
					  	}// if
					  }
				});
			};


			/**
			 * display score is incorrect dialog
			 *
			 * @param wrongScore - the incorrect input
			 */
			function displayScoreDialog(wrongScore) {
				// Instantiate the Dialog
				var teacherScoreErrorDialog = 
								new YAHOO.widget.SimpleDialog("possibleScoreErrorDialog", 
										 { width: "300px",
										   fixedcenter: true,
										   modal: true,
										   visible: false,
										   draggable: false,
										   close: true,
										   text: wrongScore,
										   icon: YAHOO.widget.SimpleDialog.ICON_WARN,
										   constraintoviewport: true
										 } );
			
				teacherScoreErrorDialog.setHeader("<center>Score Error</center>"); 
				teacherScoreErrorDialog.render(document.body); 
				teacherScoreErrorDialog.show();
			
			}
			
</script>





<div id="centeredDiv">

<%@ include file="headerteachergrading.jsp"%>

<%@ include file="L2grading_bystep.jsp"%>

<div id="overviewHeaderGradingv2">Grade By Step</div>

<div id="gradeStepSelectedProject">${projectTitle} <span id="projectIdLabel">(Project ID ${curnitId})</span></div>

<table id="currentStepTable" >
  <tr>
  	<td id="currentStepLabel">Act ${activity.number+1}, Step ${step.number+1}: <span style="font-weight:normal;">${step.title}</span></td>
    <td class="currentStepNavLink">
    
    <c:choose>
			<c:when test="${!empty previousStep}">
				<a id="previousStepLinkTop" href="gradingtool.html?GRADE_TYPE=step&runId=${runId}&podUUID=${previousStep.podUUID}&tabIndex=${tabIndex}">
    			Previous Step</a>
			</c:when>
			<c:otherwise>
				Previous Step
			</c:otherwise>
	</c:choose>

</td>
    <td class="currentStepNavLink"><a href="gradebystep.html?runId=${runId}">Return to Step Menu</a></td>
    <td class="currentStepNavLink"> 

	  <c:choose>
			<c:when test="${!empty nextStep}">
				<a id="nextStepLinkTop" href="gradingtool.html?GRADE_TYPE=step&runId=${runId}&podUUID=${nextStep.podUUID}&tabIndex=${tabIndex}">Next Step</a>
			</c:when>
			<c:otherwise>
				Next Step
			</c:otherwise>
	</c:choose>


</td>		
  </tr>
 </table>
 
 <!--
  <c:if test="${!empty previousStep}"> 
	    
	   	   </c:if>
	    <a href="gradebystep.html?runId=${runId}">View Step Menu</a>
	   	    <c:if test="${!empty nextStep}">
	    <a id='nextStepLink' href="gradingtool.html?GRADE_TYPE=step&runId=${runId}&podUUID=${nextStep.podUUID}&tabIndex=${tabIndex}">View Next Step</a>
	    </c:if>
-->

<!-- 
aggregate.key = period
aggregate.value = set of workgroupWorkAggregate
 -->

<div id="periodTabs" class="yui-navset"> 
		<!-- create the tabs nav -->
		<ul class="yui-nav" style="font-size:1.2em; text-transform:uppercase;"> 
			<c:forEach var="aggregate" varStatus="astatus" items="${stepAggregate}">
				 <li style="margin-right:4px;"><a href="${aggregate.key.name}"><em>Period ${aggregate.key.name}</em></a></li> 
			 </c:forEach> 
		 </ul>   
		 <!-- create the tabs content -->
		<div class="yui-content" style="background-color:#FFFFFF;">
			 <c:forEach var="aggregate" varStatus="astatus" items="${stepAggregate}">
			
			 <c:set var="period" value="${fn:replace(aggregate.key.name, ' ', '-')}"/>
			<div>
				<!-- Actual Tab 
					${workgroupAggregateObj} = workgroupAggregateObj
				 -->
				<c:if test="${empty aggregate.value}"> 
					<div id="noTeamsInPeriod" style="padding:20px 0;">
						This period has no registered student teams
					</div>
				</c:if>
					<c:forEach var="workgroupAggregateObj" varStatus="workgroupAggregateObjStatus" items="${aggregate.value}">
						<!-- get the workgroup id -->
						
						<c:set var="workgroupId" value="${workgroupAggregateObj.workgroup.id}"/>
						<div align="center">
						<table id="gradingTeamTable"  border="1" class="sample">
						<!-- table header -->
									<tr id="groupHeaderRow">
										<td class="boldText" width="45%">
										<!--  print member anmes -->
										<div  class="tdHeader">
										<class="headerFont">Group ${workgroupAggregateObj.workgroup.id}: 
										<c:forEach var="user" varStatus="userStatus"
											items="${workgroupAggregateObj.workgroup.members}">
										 		${user.userDetails.firstname} ${user.userDetails.lastname}
										 		   <c:if test="${userStatus.last=='false'}">
							     					&
							    				</c:if>
										 	</c:forEach>
										 </div>
										</td>
										
										<td width="35%" >
										<div align="center" class="tdHeader" ><class="headerFont">Teacher Feedback <span style="margin-left:10px;"><a href="#">open ready-made comments</a></span></div>
										</td>
										<td width="20%">
										<div align="center" class="tdHeader" ><class="headerFont">Score</div>
										</td>
									</tr>
							<!-- End Table Header -->
							<!-- for no work in work group -->
							<!--  
								
								<c:forEach var="sockPart" varStatus="partStatus" items="${workgroupAggregateObj.sessionBundle.ESessionBundle.sockParts}">
									<c:forEach var="rimFromStep" items="${step.rim}">
											<c:if test="${sockPart.rimName == rimFromStep.rimname}">
												<c:set var="noWorkFound" value="false"/>
											</c:if>
									</c:forEach>
								</c:forEach>
							
							<!-- no work found this is disabled -->
							<c:set var="noWorkFound" value="true"/>
							<c:choose>
								<c:when test="${noWorkFound == false}">
									<tr><td colspan="3" align="center">no student response yet</td></tr>
								</c:when>
								<c:otherwise>
									<!-- do the rest of the table -->
									
										<c:forEach var="rimFromStep" varStatus="rimListStatus" items="${step.rim}">
											<tr>
						                          		<td id="stepQuestionField" class="questionField">
						                          		<!-- prompt -->
						                          		<c:choose>
														        <c:when test="${fn:length(step.rim) > 1}">
														            <b>Part ${rimListStatus.count}:</b> ${rimFromStep.prompt}
														        </c:when>
														        <c:otherwise>
														           ${rimFromStep.prompt}
														        </c:otherwise>
														    </c:choose>

						                          		<!-- print out part if more than one element -->
						                          		</td>
						                          		
						                          		<!-- create the textbox -->
						                          		
															<c:if test="${rimListStatus.first}">
																   <td rowspan="${fn:length(step.rim)*2}">
																   <div align="center">
																   	<c:set var="commentDone" value="false"/>
																   	<c:set var="commentAnnotation" value=" "/>
												     				<c:forEach var="annotationGroup" items="${workgroupAggregateObj.annotationBundle.EAnnotationBundle.annotationGroups}">
																	
																	<c:forEach var="annotation" items="${annotationGroup.annotations}">
																		<c:if test="${annotationGroup.annotationSource == 'http://telscenter.org/annotation/comments'}">
																		
																			<c:if test="${annotation.entityUUID == step.podUUID}">
																			  <c:if test="${empty annotation.entityName}" >
																			     <c:if test="${commentDone == false}">
																				   <c:set var="commentAnnotation" value="${annotation}"/>
																				   <c:set var="commentDone" value="true"/>
																			     </c:if>
																			  </c:if>
																			</c:if>
																		</c:if>
																		</c:forEach>
																	</c:forEach>
																    <div id="div_${commentAnnotation.entityUUID}_${workgroupId}" >
																			<textarea id="comment-${commentAnnotation.entityUUID}_${workgroupId}" class="comment-${commentAnnotation.entityUUID}_${workgroupId}" cols="45" rows="6"  onkeypress="enableButton(this,'${commentAnnotation.entityUUID}','${workgroupId}','${period}')" >${commentAnnotation.contents}</textarea>
																	</div>
																   
																   </div>
																   </td>   		
															</c:if>
														  <!-- create the scoring -->
						                          		
						                          			<c:if test="${rimListStatus.first}">
																   <td rowspan="${fn:length(step.rim)*2}">
																   
																    <div align="center">
																	<c:set var="scoreDone" value="false"/>
																	<c:set var="scoreAnnotation" value=" "/>
															     		<c:forEach var="annotationGroup" items="${workgroupAggregateObj.annotationBundle.EAnnotationBundle.annotationGroups}">
																				
																				<c:forEach var="annotation" items="${annotationGroup.annotations}">
																					<c:if test="${annotationGroup.annotationSource == 'http://telscenter.org/annotation/score'}">
																						<c:if test="${annotation.entityUUID == step.podUUID}">
																						<c:if test="${scoreDone == false}">
																							<c:set var="score" value="${annotation.contents}"/>
																							<c:set var="scoreAnnotation" value="${annotation}"/>
																							<c:if test="${empty score}">
																								<c:set var="score" value="unscored"/>
																							</c:if>
																							<c:set var="scoreDone" value="true"/>
																						</c:if>
																						</c:if>
																					</c:if>
												
																				</c:forEach>
																		</c:forEach>
																				<input class="teacher-score-${scoreAnnotation.entityUUID}_${workgroupId}" type="text" size="7" value="${score}"/> out of <input class="possible-score-${scoreAnnotation.entityUUID}_${workgroupId}" DISABLED="true" READONLY="true" type="text" size="1" value="${step.possibleScore}"/>
																				
																				<div id="gradingSaveButton">
																					<span id="pushbutton-${scoreAnnotation.entityUUID}_${workgroupId}" class="yui-button yui-push-button">
																						
																							<em class="first-child">
																							<button type="submit" name="pushbutton-${scoreAnnotation.entityUUID}_${workgroupId}" onClick="javascript:doSubmit(this,'${scoreAnnotation.entityUUID}','null','${period}','${workgroupId}','${runId}')">
																						   		Save Feedback + Score</button></em>
																					</span>
																				</div>
																		<div class="saved-${scoreAnnotation.entityUUID}_${workgroupId}" style="display: inline; width: 12%;"></div>																   
																		</div>	   
																   </td>   		
															</c:if>
						                          		
						                     </tr>
						                     <tr>
						                          		<td>
						                          		<!--answer -->
						                          			<div id="stepStudentAnswerField" class="answerDiv">
							                          			<c:set var="sockPartFound" value="false"/>
							                          			<c:forEach var="sockPart" varStatus="partStatus" items="${workgroupAggregateObj.sessionBundle.ESessionBundle.sockParts}">
							                          				<c:if test="${sockPart.rimName == rimFromStep.rimname}">
							                          					<c:set var="sockPartFound" value="true"/>
							                          					<c:forEach var="sockEntry" varStatus="sockStatus" items="${sockPart.sockEntries}">
																							${sockEntry.value}
				  			 											</c:forEach>
							                          				</c:if>
							                          			</c:forEach>
							                          			
							                          			<c:choose>
																      <c:when test="${sockPartFound == 'false'}">
																      		<span id="noStudentReponseYet">no student response yet</span>
																      </c:when>
																      <c:otherwise>
																				<c:set var="sockPartFound" value="false"/>
																      </c:otherwise>
														  		 </c:choose>
						                          			</div>
						                          		</td>
						                          		
						                     </tr>
										</c:forEach>
								</c:otherwise>
							</c:choose>
							</table>
							</div>
					</c:forEach> 
		
		
		
				</div>	 
			</c:forEach> 
		</div>
		<!-- end create tab content -->
</div>

<table id="currentStepTable" >
  <tr>
  	<td id="currentStepLabel"></td>
    <td class="currentStepNavLink">
    
    <c:choose>
			<c:when test="${!empty previousStep}">
				<a id="previousStepLinkBottom" href="gradingtool.html?GRADE_TYPE=step&runId=${runId}&podUUID=${previousStep.podUUID}&tabIndex=${tabIndex}">
    			Previous Step</a>
			</c:when>
			<c:otherwise>
				Previous Step
			</c:otherwise>
	</c:choose>

</td>
    <td class="currentStepNavLink"><a href="gradebystep.html?runId=${runId}">Return to Step Menu</a></td>
    <td class="currentStepNavLink"> 

	  <c:choose>
			<c:when test="${!empty nextStep}">
				<a id="nextStepLinkBottom" href="gradingtool.html?GRADE_TYPE=step&runId=${runId}&podUUID=${nextStep.podUUID}&tabIndex=${tabIndex}">Next Step</a>
			</c:when>
			<c:otherwise>
				Next Step
			</c:otherwise>
	</c:choose>


</td>		
  </tr>
 </table>

<div id="myLogger"></div>
<script type="text/javascript">
var myLogReader = new YAHOO.widget.LogReader("myLogger");
</script>
<div id="container"></div>

</div>    <!--end of Centered Div-->

</body>
</html>
