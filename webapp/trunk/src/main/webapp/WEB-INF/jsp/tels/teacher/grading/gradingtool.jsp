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

<!-- use apaches string utils -->
<script type='text/javascript' src='/webapp/dwr/interface/StringUtilsJS.js'></script>
<script type='text/javascript' src='/webapp/dwr/engine.js'></script>
<script type="text/javascript">
		
		//create tab
	    var tabView = new YAHOO.widget.TabView('periodTabs'); 
	    
		tabView.set('activeIndex', ${tabIndex});								        
	    tabView.addListener('activeTabChange', handleTabClick); 
	    
	    /**
	     * When tabs change handle a click
	     */
	     function handleTabClick(e) { 
	     	 var nextVar = document.getElementById('nextStepLink');  
	     	 var tabIndex = tabView.getTabIndex( e.newValue );
	     	 nextVar.href = 'gradingtool.html?GRADE_TYPE=step&runId=${runId}&podUUID=${nextStep.podUUID}&tabIndex='+tabIndex;
	     	 
	     	 //previousStep
	     	 var previousVar = document.getElementById('previousStepLink');
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
										   constraintoviewport: true,
										 } );
			
				teacherScoreErrorDialog.setHeader("<center>Score Error</center>"); 
				teacherScoreErrorDialog.render(document.body); 
				teacherScoreErrorDialog.show();
			
			}

</script>

<body class=" yui-skin-sam">
<%@ include file="gradingtoolHeader.jsp"%>
<h2>Grading Tool</h2>

<div id="tableProjectView">
<table class="view">
  <tr>
  	<td><em>Project:</em> </td>
    <td>${projectTitle} (${curnitId})</td>
	<td></td>
  </tr>
  <tr>
  	<td><em>View:</em></td>
    <td id="viewStep"><strong>Act ${activity.number+1}, Step ${step.number+1}: ${step.title}</strong></td>
    <td id="gradeStepLinks">
	    <c:if test="${!empty previousStep}"> 
	    <a id='previousStepLink' href="gradingtool.html?GRADE_TYPE=step&runId=${runId}&podUUID=${previousStep.podUUID}&tabIndex=${tabIndex}">View Previous Step</a>
	    &nbsp &nbsp
	    </c:if>
	    <a href="gradebystep.html?runId=${runId}">View Step Menu</a>
	    &nbsp &nbsp 
	    <c:if test="${!empty nextStep}">
	    <a id='nextStepLink' href="gradingtool.html?GRADE_TYPE=step&runId=${runId}&podUUID=${nextStep.podUUID}&tabIndex=${tabIndex}">View Next Step</a>
	    </c:if>
	</td>
  <tr>
    <td></td>
    <td></td>
	<td></td>
  </tr>
  </table>

</div>  
<br>
<br>
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
										
										<td width="45%" >
										<div align="center" class="tdHeader" ><strong class="headerFont">Teacher Feedback</strong></div>
										</td>
										<td >
										<div align="center" class="tdHeader" ><strong class="headerFont">Score</strong></div>
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
									<tr><td colspan="3" align="center">No work submitted yet.</td></tr>
								</c:when>
								<c:otherwise>
									<!-- do the rest of the table -->
									
										<c:forEach var="rimFromStep" varStatus="rimListStatus" items="${step.rim}">
											<tr>
						                          		<td class="questionField">
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
																	
																				<span id="pushbutton-${scoreAnnotation.entityUUID}_${workgroupId}" class="yui-button yui-push-button"><em class="first-child">
																						<button type="submit" name="pushbutton-${scoreAnnotation.entityUUID}_${workgroupId}" onClick="javascript:doSubmit(this,'${scoreAnnotation.entityUUID}','null','${period}','${workgroupId}','${runId}')">Save</button></em>
																				</span>
																		<div class="saved-${scoreAnnotation.entityUUID}_${workgroupId}" style="display: inline; width: 12%;"></div>																   
																		</div>	   
																   </td>   		
															</c:if>
						                          		
						                     </tr>
						                     <tr>
						                          		<td>
						                          		<!--answer -->
						                          			<div class="answerDiv">
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
																      		no response.
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
<div id="myLogger"></div>
<script type="text/javascript">
var myLogReader = new YAHOO.widget.LogReader("myLogger");
</script>
<div id="container"></div>
</body>
</html>
