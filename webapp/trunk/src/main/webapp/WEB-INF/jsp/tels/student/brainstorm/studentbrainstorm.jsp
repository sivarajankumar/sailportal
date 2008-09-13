<%@ include file="include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<script type="text/javascript" src="../.././javascript/tels/yui/yahoo/yahoo.js"></script>
<script type="text/javascript" src="../.././javascript/tels/yui/event/event.js"></script>
<script type="text/javascript" src="../.././javascript/tels/yui/connection/connection.js"></script>

<script type="text/javascript">
	
	var sortByTime = 'true';

	function popUp(URL, name){
		window.open(URL, name, 'toolbar=0,scrollbars=1,location=0,statusbar=0,menubar=0,resizable=1,width=600,height=500,left = 450,top = 150');
	};
	
	function responsePopUp(workgroupId, brainstormId){
		popUp('brainstormresponse.html?workgroupId=' + workgroupId + '&brainstormId=' + brainstormId, 'TeamResponse');
	};
	
	function addCommentPopUp(workgroupId, answerId){
		popUp('addcomment.html?workgroupId=' + workgroupId + '&answerId=' + answerId, 'AddComment');
	};
	
	function addRevisionPopUp(workgroupId, answerId){
		popUp('addrevision.html?workgroupId=' + workgroupId + '&answerId=' + answerId, 'AddRevision');
	};
	
	function requestHelp(workgroupId, brainstormId) {
		var isChecked = document.getElementById('requesthelp_' + workgroupId + '_' + brainstormId).checked;
		if (isChecked) {
			var URL='requesthelp.html';
			var data='mark=1&workgroupId=' + workgroupId + '&brainstormId=' + brainstormId;
			var callback = 
				{
					success:function(o){alert('you have requested help from your teacher on this brainstorm');},
					failure:function(o){}
				};
			YAHOO.util.Connect.asyncRequest('POST', URL, callback, data);
		} else {
			var URL='requesthelp.html';
			var data='mark=0&workgroupId=' + workgroupId + '&brainstormId=' + brainstormId;
			var callback = 
				{
					success:function(o){alert('you have retracted your request for help from your teacher on this brainstorm');},
					failure:function(o){}
				};
			YAHOO.util.Connect.asyncRequest('POST', URL, callback, data);
		}
		
	};
	
	function markAnswerAsHelpful(workgroupId, answerId) {
		var isChecked = document.getElementById('helpful_' + workgroupId + '_' + answerId).checked;
		if (isChecked) {
			var URL='markanswerhelpful.html';
			var data='mark=1&workgroupId=' + workgroupId + '&answerId=' + answerId;
			var callback = 
				{
					success:function(o){},
					failure:function(o){}
				};
			YAHOO.util.Connect.asyncRequest('POST', URL, callback, data);
		} else {
			var URL='markanswerhelpful.html';
			var data='mark=0&workgroupId=' + workgroupId + '&answerId=' + answerId;
			var callback = 
				{
					success:function(o){},
					failure:function(o){}
				};
			YAHOO.util.Connect.asyncRequest('POST', URL, callback, data);
		}
	};
	
	function refreshResponses(){
		alert("code here to refresh responses");
	};
	
	function sortBy(type){
		if(type=='help'){
			sortByTime='false';
		} else {
			sortByTime='true';
		};
		alert(sortByTime);
	}
</script>
</head>


<body>

<div align="centered">
	<div id="questionHead">Question for SUBGROUP (#of students)</div>
	<div id="questionPrompt">Question:
			<span name="brainstormQuestion">${brainstorm.question.prompt}</span>
			<div>To answer this Q&A question, click the Create A Response button below</div>
	</div>
	
	<div id="studentResponseHead">
		<div>Student Responses 
			<c:choose>
				<c:when test="${fn:length(brainstorm.answers)==1}">
					(1 posting)
				</c:when>
				<c:otherwise>
					(${fn:length(brainstorm.answers)} postings)
				</c:otherwise>
			</c:choose>
		</div>  
		<input id="createResponse" type="button" value="Create A Response" onclick="responsePopUp('${workgroup.id}', '${brainstorm.id}')"/>
		<c:set var="thisworkgrouprequestedhelp" value="false" />
		<c:forEach var="workgroupThatRequestedHelp" varStatus="wtrh" items="${brainstorm.workgroupsThatRequestHelp}">
		    <c:if test="${workgroupThatRequestedHelp == workgroup}">
		        <c:set var="thisworkgrouprequestedhelp" value="true" />
			</c:if>
		</c:forEach>
		<c:choose>
		    <c:when test="${thisworkgrouprequestedhelp == true}">
		    	<input id="requesthelp_${workgroup.id}_${brainstorm.id}" checked="checked" type="checkbox" value="helpful" onclick="javascript:requestHelp('${workgroup.id}', '${brainstorm.id}')">Request Help</input>
		    </c:when>
		    <c:otherwise>
		        <input id="requesthelp_${workgroup.id}_${brainstorm.id}" type="checkbox" value="helpful" onclick="javascript:requestHelp('${workgroup.id}', '${brainstorm.id}')">Request Help</input>
		    </c:otherwise>
		</c:choose>
		<div id="info"><a href="#">info</a></div>
	</div>
	
	<div id="studentResponseBody">
		<a href="#" onclick="sortBy('time')">Sort By Time</a>
		<a href="#" onclick="sortBy('help')">Sort By Helpfulness</a>
		<a href="#">Show/Hide All Comments and Revisions</a>
		<input id="newResponses" type="button" value="Show New Responses" onclick="refreshResponses()"/>
		<div id="numNewResponses">0 new responses received</div>
	</div>
	
	<div id="studentPosts">
		<c:forEach var="answer" varStatus="answerStatus" items="${brainstorm.answers}">
			<br/><br/>
			<div id="${answer.id}" name="answer">
				<c:set var="count" value="1"/>
				<c:forEach var="revisionLast" varStatus="revisionLastStatus" items="${answer.revisions}">
					<c:if test="${revisionLastStatus.last=='true'}">
						<div id="${revisionLast.id}" name="revision">
							<div name="revisionHead">
                                <c:choose>
									<c:when test="${answer.anonymous}">
                                    <i>Anonymous</i>
                                	</c:when>
									<c:otherwise>
										<c:forEach var="student" varStatus="studentStatus" items="${answer.workgroup.members}">
											${student.userDetails.firstname} ${student.userDetails.lastname}
											<c:if test="${studentStatus.last=='false'}"> & </c:if>
										</c:forEach>
            						</c:otherwise>
								</c:choose>
								${revisionLast.timestamp}     <br/>
								${fn:length(answer.workgroupsThatFoundAnswerHelpful)} students found this post helpful
							</div>
							<div name="revisionBody">
								<c:if test="${fn:length(answer.revisions)>1}">
									<b>Revision ${count} </b>
								</c:if>
								${revisionLast.body}
							</div>
						</div>
						<div name="revisionFoot">
							<a href="#" onclick="addCommentPopUp('${workgroup.id}', '${answer.id}')">Add a Comment</a>
							<c:if test="${answer.workgroup.id==workgroup.id}">
								<a href="#" onclick="addRevisionPopUp('${workgroup.id}', '${answer.id}')">Revise this Response</a>
							</c:if>
							<c:if test="${answer.workgroup.id!=workgroup.id}">
							     <c:set var="thisworkgroupfoundthisanswerhelpful" value="false" />
							     <c:forEach var="workgroupThatFoundThisAnswerHelpful" varStatus="wktftah" items="${answer.workgroupsThatFoundAnswerHelpful}">
							         <c:if test="${workgroupThatFoundThisAnswerHelpful == workgroup}">
							         	<c:set var="thisworkgroupfoundthisanswerhelpful" value="true" />
							         </c:if>
							     </c:forEach>
							     <c:choose>
							         <c:when test="${thisworkgroupfoundthisanswerhelpful == true}">
							         	  <input id="helpful_${workgroup.id}_${answer.id}" checked="checked" type="checkbox" value="helpful" onclick="javascript:markAnswerAsHelpful('${workgroup.id}', '${answer.id}')">I found this response helpful</input>
							         </c:when>
							         <c:otherwise>
							          	<input id="helpful_${workgroup.id}_${answer.id}" type="checkbox" value="helpful" onclick="javascript:markAnswerAsHelpful('${workgroup.id}', '${answer.id}')">I found this response helpful</input>
							         </c:otherwise>
							     </c:choose>
							</c:if>
						</div>
					</c:if>
					<c:set var="count" value="${count+1}"/>
				</c:forEach>
				<c:if test="${fn:length(answer.revisions) > 1}">
					<div name="otherRevisions">
						Revisions
						<c:set var="count" value="1"/>
						<c:forEach var="revisionRest" varStatus="revisionRestStatus" items="${answer.revisions}">
							<c:if test="${revisionRestStatus.last=='false'}">
								<div id="otherRevisionInfo">
									Revision ${count} ${revisionRest.body}
									<c:choose>
										<c:when test="${answer.anonymous=='true'}">
											<i>Anonymous</i>
										</c:when>
										<c:otherwise>
											<c:forEach var="student" varStatus="studentStatus" items="${answer.workgroup.members}">
												${student.userDetails.firstname} ${student.userDetails.lastname}
												<c:if test="${studentStatus.last=='false'}">, </c:if>
											</c:forEach>
										</c:otherwise>
									</c:choose>
									${revisionRest.timestamp}
								</div>
							</c:if>
							<c:set var="count" value="${count+1}"/>
						</c:forEach>
					</div>
				</c:if>
				
				<div id="comments">
				    <c:if test="${fn:length(answer.comments) > 0}">
					${fn:length(answer.comments)} comments <a href="#" onclick="addCommentPopUp('${workgroup.id}', '${answer.id}')">Add a Comment</a>
						<c:forEach var="comment" items="${answer.comments}">
							<div id="comment_${comment.id}">
								${comment.body} (
								<c:choose>
									<c:when test="${comment.anonymous=='true'}">
										Anonymous
									</c:when>
									<c:otherwise>
										<c:forEach var="student" varStatus="studentStatus" items="${comment.workgroup.members}">
											${student.userDetails.firstname} ${student.userDetails.lastname},
										</c:forEach>
									</c:otherwise>
								</c:choose>
								${comment.timestamp} )
							</div>
						</c:forEach>
					</c:if>
				</div>
			</div>
		</c:forEach>
	</div>


</div> <!-- end centered div -->
<!--  
<p>
brainstorm: ${brainstorm}
<br/></p>

<p>
brainstorm question: ${brainstorm.question.prompt}
<br/></p>

<p>
number of brainstorm answers: ${fn:length(brainstorm.answers)}
<br/></p>

<c:forEach var="answer" varStatus="answerStatus" items="${brainstorm.answers}">
<b>answer # ${answerStatus.index + 1}</b>
<br/>
number of revisions for this answer: ${fn:length(answer.revisions)}
<c:forEach var="revision" varStatus="revisionStatus" items="${answer.revisions}">
<br/>
revision # ${revisionStatus.index + 1}
<br/>
posted on: ${revision.timestamp}
<br/>
body: ${revision.body}
</c:forEach>
<br/><br/>

number of comments for this answer: ${fn:length(answer.comments)}
<c:forEach var="comment" varStatus="commentStatus" items="${answer.comments}">
<br/>
comment # ${commentStatus.index + 1}
<br/>
posted on: ${comment.timestamp}
<br/>
body: ${comment.body}
</c:forEach>
</c:forEach>
-->
</body>
</html>
