<%@ include file="include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<script type="text/javascript" src="../.././javascript/tels/yui/yahoo/yahoo.js"></script>
<script type="text/javascript" src="../.././javascript/tels/yui/event/event.js"></script>
<script type="text/javascript" src="../.././javascript/tels/yui/connection/connection.js"></script>
<script type="text/javascript" src="../.././javascript/tels/brainstorm.js"></script>

<script type="text/javascript">
	
	var sortOrder = 0;
	var pageManager;

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
		pageManager.refreshPage();
		getNumberOfPosts();
	};
	
	function sortBy(type){
		if(type=='help'){
			sortOrder=2;
		} else {
			if(sortOrder==1 || sortOrder==2){
				sortOrder=0;
			} else {
				sortOrder=1;
			};
		};
		pageManager.setSortOrder(sortOrder);
	};

	function getNumberOfPosts(){
		var numOfPostElement = document.getElementById('numOfPosts');
		var numOfPosts = document.getElementsByName('answer').length + document.getElementsByName('comment').length;
		if(numOfPosts==1){
			numOfPostElement.innerHTML = 'Student Responses (1 posting)';
		} else {
			numOfPostElement.innerHTML = 'Student Responses (' + numOfPosts + ' postings)';
		};
	};
	
     function PollNewPosts(brainstormId){
     	this.brainstormId=brainstormId;
		this.callback = {
			customevents:{
				onComplete:this.complete
			},
			success:this.handleSuccess,
			failure:this.handleFailure,
			scope:this
		};
     };
	
	PollNewPosts.prototype.start = function(){
		YAHOO.util.Connect.setPollingInterval(30000);
		YAHOO.util.Connect.asyncRequest('GET', 'pollnewposts.html?brainstormId=' + this.brainstormId, this.callback);
	};
	
	PollNewPosts.prototype.handleSuccess = function(o){
		var out = "";
		var newPosts = Number(o.responseText) - (document.getElementsByName('revision').length + document.getElementsByName('comment').length + document.getElementsByName('otherRevisions').length);
		if(newPosts > 0){
			if(newPosts == 1){
				out = "1 new response received";
			} else {
				out = newPosts + " new responses received";
			};
		} else {
			out = "0 new responses received";
		};
		document.getElementById('numNewResponses').innerHTML=out;
	};
	
	PollNewPosts.prototype.handleFailure = function(o){
		//do nothing - this is running in the background every minute
	};
	
	PollNewPosts.prototype.complete = function(eventType, args){
		this.start();
	};
	
	/**
	* PageManager manages adding and removing Answers, Revisions
	* and Comments for a given brainstorm as well as creating and
	* updating Answer tables and refreshing the elements of a page
	*/
	function PageManager(brainstormId, workgroupId, order){
		this.id = brainstormId;
		this.workgroupId = workgroupId;
		this.order = order;
		this.answers;
	
		this.callback = {
			success:this.handleSuccess,
			failure:this.handleFailure,
			scope:this
		};
		
		this.getAllPosts = function(){
			YAHOO.util.Connect.asyncRequest('GET', 'getallposts.html?brainstormId=' + this.id, this.callback);
		};
			
		this.getAllPosts();
	};
	
	PageManager.prototype.handleFailure = function(o){
		alert('failure');
		//error stuff goes here
	};
	
	PageManager.prototype.handleSuccess = function(o){
		var xmlDoc = o.responseXML;
		if(xmlDoc==null){
			this.handlFailure(o);
		};
		this.answers = new Answers();
		this.answers.setAnswers(xmlDoc);
		this.updatePage();
	};
	
	//gets all posts from server and builds answer tables from new data
	PageManager.prototype.refreshPage = function(){
		this.getAllPosts();
	};
	
	PageManager.prototype.getAnswerById = function(id){
		return this.answers.getAnswerById(id);
	};
	
	PageManager.prototype.addAnswer = function(answer){
		this.answers.addAnswer(answer);
		this.updatePage();
	};
	
	PageManager.prototype.addRevision = function(revision, answerId){
		var answer = this.answers.getAnswerById(answerId);
		answer.addRevision(revision);
		this.updatePage();
	};
	
	PageManager.prototype.addComment = function(comment, answerId){
		var answer = this.answers.getAnswerById(answerId);
		answer.addComment(comment);
		this.updatePage();
	};
	
	//builds answer tables from existing data without a request to server
	PageManager.prototype.updatePage = function(){
		var orderedElements;
		if(this.order==0){
			this.answers.sort(newestFirst);
		} else if(this.order==1){
			this.answers.sort(oldestFirst);
		} else {
			//order by helpfulness-right now order as in 0
			//this.answers.sort(helpfulness);
			this.answers.sort(newestFirst);
		};
		orderedElements = createAnswerElements(document, this.answers, this.workgroupId);
		this.buildPage(orderedElements);
		
		function oldestFirst(a, b){
			if(a.getId() < b.getId()){return -1};
			if(a.getId() == b.getId()){return 0};
			if(a.getId() > b.getId()){return 1};
		};
		
		function newestFirst(a, b){
			if(a.getId() < b.getId()){return 1};
			if(a.getId() == b.getId()){return 0};
			if(a.getId() > b.getId()){return -1};
		};
		
		function helpfulness(a, b){
			//TODO
		};
	};
	
	PageManager.prototype.setSortOrder = function(order){
		this.order = order;
	};
	
	PageManager.prototype.buildPage = function(elements){
		var answerNodes = document.getElementsByName('answer');
		var parent = document.getElementById('responseTableBody');
		
		for(xx=0;xx<answerNodes.length;xx++){
			if(answerNodes[xx].getAttribute('id')!=0){
				answerNodes[xx].parentNode.removeChild(answerNodes[xx]);
			};
		};
		
		for(cc=0;cc<elements.length;cc++){
			parent.appendChild(elements[cc]);
		};
	};
	
	pageManager = new PageManager('${brainstorm.id}', '${workgroup.id}', sortOrder);

</script>
</head>


<body>

<div align="centered">

	<table id="questionTable">
		<thead>
			<tr>
				<th><div>Question for SUBGROUP (# of students)</div></th>
				<th><a href="#">click to hide/show</a></th>
			</tr>
		</thead>
		<tbody>
			<table id="questionPrompt">
				<tr>
					<td>Question:</td>
					<td><span name="questionPrompt">${brainstorm.question.prompt}</span></td>
				</tr>
				<tr>
					<td></td>
					<td><div>To answer this Q&A question, click the Create A Response button below</div></td>
			</table>
		</tbody>
	</table>
	<table id="studentResponseTable">
		<thead>
			<tr>
				<th><div id="numOfPosts"></div></th>
				<th><input type="button" value="Create A Response" onclick="responsePopUp(${workgroup.id}, ${brainstorm.id})"></input></th>
				<th>
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
				</th>
				<th><div><a href="#">info</a></div></th>
			</tr>
		</thead>
		
		<tbody id="responseTableBody">
<!-- CONDITIONAL ON WHETHER STUDENTS CAN SEE OTHER STUDENTS' POSTS OR NOT -->
		<c:choose>
			<c:when test="${cannotseeresponses}">
				<tr id="0" name="answer">
					<div>You cannot see other students' posts until you post your own response.</div>
				</tr>
			</c:when>
			<c:otherwise>	
				<tr id="0" name="answer">
					<td>
						<a href="#" onclick="sortBy('time')">Sort By Time</a>
						<a href="#" onclick="sortBy('help')">Sort By Helpfulness</a>
					</td>
					<td><a href="#">Show/Hide All Comments and Revisions</a></td>
					<td><input type="button" value="Show New Responses" onclick="refreshResponses()"/></td>
					<td><div id="numNewResponses">0 new responses received</div></td>
				</tr>
			</c:otherwise>
		</c:choose>
		</tbody>
	</table>
<script>
	getNumberOfPosts();
	var poll = new PollNewPosts('${brainstorm.id}');
	poll.start();
</script>


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
