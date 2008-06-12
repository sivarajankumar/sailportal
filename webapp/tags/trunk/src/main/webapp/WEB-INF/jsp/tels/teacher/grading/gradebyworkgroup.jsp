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

	function openPremadeComments() {

		var premadeCommentsPanel = new YAHOO.widget.Panel("premadeComments",  
                                                    { width: "800px",
                                                      fixedcenter: true, 
                                                      close: true, 
                                                      draggable: true, 
                                                      zindex:4,
                                                      modal: true
                                                    } 
                                                );
		premadeCommentsPanel.setBody("<p>Hello</p>");
		premadeCommentsPanel.render(document.body);
		premadeCommentsPanel.show();
		//abc.render(document.body);
	}

	function popup(URL) {
  		window.open(URL, 'PremadeComments', 'toolbar=0,scrollbars=1,location=0,statusbar=0,menubar=0,resizable=1,width=800,height=740,left = 450,top = 150');
  	}
  	
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
	    //var tabView = new YAHOO.widget.TabView('periodTabs'); 
	    
		//tabView.set('activeIndex', ${tabIndex});								        
	    //tabView.addListener('activeTabChange', handleTabClick); 
	    
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


			//check the inputs of the score boxes
			StringUtilsJS.isNumeric(teacherScore, {
					  callback:function(teacherScoreResult) { 

					    // if the result is false display a dialog
					  	if( teacherScoreResult == false && !(teacherScore == "unscored")) {
					  		displayScoreDialog(teacherScore + " is not a valid score, please input a number"); 
					  	} else {
					  		if( teacherScore == "unscored" || eval(teacherScore) <=  eval(possibleScore) ) {
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

<%@ include file="L2grading_bygroup.jsp"%>

<div id="overviewHeaderGradingv2"><spring:message code="teacher.gradebyteam.1"/></div>

<div id="gradeTeamSelectedProject">${curnitmap.project.title}<span id="projectIdLabel">(Project ID XXXXXX [need data]</span></div>

<div id="currentTeamHeader" > 
   	<div id="currentTeamLabel">
   	    <c:forEach var="user" varStatus="userStatus" items="${aggregate.workgroup.members}">
			${user.userDetails.firstname} ${user.userDetails.lastname}
			<c:if test="${userStatus.last=='false'}">
			    ,
		    </c:if>
		</c:forEach>
   	    <span id="teamPeriodAndNumber">(period ${aggregate.workgroup.period.name}, team ${aggregate.workgroup.id})</span>
   	</div>
</div>
<div>   	
  <ul id="currentTeamNavList">
    <li class="currentTeamNavLink"><spring:message code="teacher.gradebyteam.2"/></li>
    <li class="currentTeamNavLink"><a href="selectworkgroup.html?runId=${runId}"><spring:message code="teacher.gradebyteam.3"/></a></li>
    <li class="currentTeamNavLink"><spring:message code="teacher.gradebyteam.4"/></li>	
  </ul>
</div>

 
<!-- 
aggregate.key = period
aggregate.value = set of workgroupWorkAggregate
 -->
<c:choose>
<c:when test="${empty aggregate}"> 
    <div id="noTeamsInPeriod" style="padding:20px 0;">
	    <spring:message code="teacher.gradebyteam.5"/>
	</div>
</c:when>
<c:otherwise>
  <c:set var="sessionbundles" value="${aggregate.sessionBundles}"/>
  <c:set var="curnitmap" value="${aggregate.curnitmap}"/>
  <c:set var="workgroupId" value="${aggregate.workgroup.id}"/>
  
  
   <c:forEach var="activity" varStatus="varAct" items="${curnitmap.project.activity}">
		<div id="stepTitle">Activity ${activity.number+1}: ${activity.title}</div>  
		<ul id="TeamSelectionList"> 
			<c:forEach var="step" varStatus="varStep" items="${activity.step}">
				<c:if test="${step.type == 'Note' || step.type == 'Student Assessment'}">
				<!--  for each grade-able step, show prompt, answer, and teacher's feedback input boxes -->
					<table id="gradingTeamTable"  border="1" class="sample">
						<!-- table header to display step title, teacher feedback, premade comment button, score header -->
						<tr id="groupHeaderRow">
							<td class="boldText" width="45%">
		    					<!--  print member anmes -->
		 					   <div  class="tdHeader">
		 					   	<class="headerFont">Step ${step.number+1}: ${step.title}</class>							    </div>
							</td>					
							<td  >
								<c:set var="commentDone" value="false"/>
								<c:set var="commentAnnotation" value=" "/>
								<c:forEach var="annotationGroup" items="${aggregate.annotationBundle.EAnnotationBundle.annotationGroups}">
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
								<div align="center" class="tdHeader" class="headerFont"><spring:message code="teacher.gradebyteam.6"/><span id="preMadeCommentsLink"><a href="javascript:popup('premadeComments.html?commentBox=comment-${commentAnnotation.entityUUID}_${workgroupId}')"><spring:message code="teacher.gradebyteam.7"/></a></span></div>
								</td>
								<td width="20%">
								<div align="center" class="tdHeader" class="headerFont"><spring:message code="teacher.gradebyteam.8"/></div>
								</td>
							</tr>
							
							<!-- table row to display this workgroup's work, teacher's feedback and score for this step -->
							<tr>
							<c:set var="noWorkFound" value="true"/>
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
																   <td id="teacherFeedbackTdTeam" rowspan="${fn:length(step.rim)*2}">
																   <div align="center">
																   	<c:set var="commentDone" value="false"/>
																   	<c:set var="commentAnnotation" value=" "/>
												     				<c:forEach var="annotationGroup" items="${aggregate.annotationBundle.EAnnotationBundle.annotationGroups}">
																	
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
															     		<c:forEach var="annotationGroup" items="${aggregate.annotationBundle.EAnnotationBundle.annotationGroups}">
																				
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
																				<div id="gradingScoreArea">
																					<input class="teacher-score-${scoreAnnotation.entityUUID}_${workgroupId}" id="scoreBoxStyling1" type="text" size="7" value="${score}"/>
																					<span id="scoreBoxStyling3">&nbsp;<spring:message code="teacher.gradebyteam.13"/>&nbsp; </span><input id="scoreBoxStyling2" class="possible-score-${scoreAnnotation.entityUUID}_${workgroupId}" disabled="true" readonly="true" type="text" size="1" value="${step.possibleScore}"/>
																				</div>
																				<div id="revisionRequiredArea" >
	     																			<form>
	     																				<input type="checkbox" name="checkBox" id="checkbox"/><span><spring:message code="teacher.gradebyteam.9"/></span>
	     																			</form>
    																			</div>
    																			
																				<div id="gradingSaveButton">
																					<span id="pushbutton-${scoreAnnotation.entityUUID}_${workgroupId}" class="yui-button yui-push-button">
																							<em class="first-child">
																							<button type="submit" name="pushbutton-${scoreAnnotation.entityUUID}_${workgroupId}" onClick="javascript:doSubmit(this,'${scoreAnnotation.entityUUID}','null','${period}','${workgroupId}','${runId}')"><spring:message code="teacher.gradebyteam.10"/> <br><spring:message code="teacher.gradebyteam.11"/></button></em>
																					</span>
																				</div>
																			
																				<div id="scoringHelpLink">
																					<a href="#"><spring:message code="teacher.gradebyteam.14"/></a>
																				</div>	
																		<div class="saved-${scoreAnnotation.entityUUID}_${workgroupId}" style="display: inline; width: 11%;"></div>																   
																		</div>	
																		
																		
																   </td>   		
															</c:if>
						                          		
						                     </tr>
						                     <tr>
						                          		<td>
						                          		<!--answer -->
						                          			<div id="stepStudentAnswerField" class="answerDiv">
							                          			<c:set var="sockPartFound" value="false"/>
							                          			<c:set var="sockEntryValue" value="" />
							                          			<c:forEach var="sessionBundle" varStatus="sessionBundleStatus" items="${aggregate.sessionBundles}">
							                          			    <c:forEach var="sockPart" varStatus="partStatus" items="${sessionBundle.ESessionBundle.sockParts}">
							                          				    <c:if test="${sockPart.rimName == rimFromStep.rimname}">
							                          					    <c:set var="sockPartFound" value="true"/>
							                          					    <c:forEach var="sockEntry" varStatus="sockStatus" items="${sockPart.sockEntries}">
							                          					        <c:if test="${sockStatus.index == fn:length(sockPart.sockEntries) - 1}">
																					<c:set var="sockEntryValue" value ="${sockEntry.value}" />
																				</c:if>
				  			 											    </c:forEach>
							                          				    </c:if>
							                          			    </c:forEach>
							                          			</c:forEach>
							                          			
							                          			<c:choose>
																      <c:when test="${sockPartFound == 'false'}">
																      		<span id="noStudentReponseYet"><spring:message code="teacher.gradebyteam.12"/></span>
																      </c:when>
																      <c:otherwise>
																                ${sockEntryValue}
																				<c:set var="sockPartFound" value="false"/>
																      </c:otherwise>
														  		 </c:choose>
						                          			</div>
						                          		</td>
						                          		
						                     </tr>
										</c:forEach>
							
							</tr>
							
						</table>
				
				    <!-- for no work in work group -->
					<c:forEach var="sessionBundle" varStatus="sessionBundleStatus" items="${aggregate.sessionBundles}">
					    <c:forEach var="sockPart" varStatus="partStatus" items="${sessionBundle.ESessionBundle.sockParts}">
							<c:forEach var="rimFromStep" items="${step.rim}">
								<c:if test="${sockPart.rimName == rimFromStep.rimname}">
									<c:set var="noWorkFound" value="false"/>
								</c:if>
							</c:forEach>
						</c:forEach>
					</c:forEach>
				</c:if>
			</c:forEach>
		</ul>
    </c:forEach>
  
</c:otherwise>
</c:choose>


<div id="currentTeamHeader" > 
   	<div id="currentTeamLabel">
   	    <c:forEach var="user" varStatus="userStatus" items="${aggregate.workgroup.members}">
			${user.userDetails.firstname} ${user.userDetails.lastname}
			<c:if test="${userStatus.last=='false'}">
			    ,
		    </c:if>
		</c:forEach>
   	    <span id="teamPeriodAndNumber">(period ${aggregate.workgroup.period.name}, team ${aggregate.workgroup.id})</span>
   	</div>
</div>
<div>   	
  <ul id="currentTeamNavList">
    <li class="currentTeamNavLink"><spring:message code="teacher.gradebyteam.2"/></li>
    <li class="currentTeamNavLink"><a href="selectworkgroup.html?runId=${runId}"><spring:message code="teacher.gradebyteam.3"/></a></li>
    <li class="currentTeamNavLink"><spring:message code="teacher.gradebyteam.4"/></li>	
  </ul>
</div>

<!-- uncomment this section if you want to display the yahoo logger
     remember to comment it before committing to repository
<div id="myLogger"></div>
<script type="text/javascript">
var myLogReader = new YAHOO.widget.LogReader("myLogger");
</script>
-->
<div id="container"></div>

</div>    <!--end of Centered Div-->

</body>
</html>
