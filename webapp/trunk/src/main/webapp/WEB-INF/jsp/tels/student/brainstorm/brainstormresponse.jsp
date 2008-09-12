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
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<script type="text/javascript" src="../.././javascript/tels/yui/yahoo/yahoo.js"></script>
<script type="text/javascript" src="../.././javascript/tels/yui/event/event.js"></script>
<script type="text/javascript" src="../.././javascript/tels/yui/connection/connection.js"></script>


<script type="text/javascript">

function validateOptions(){	
	var options = document.getElementsByName('postName');
	for(x=0;x<options.length;x++){
		if(options[x].checked){
			return options[x].value;
		};
	};
	return false;
};

function validate(){
	var optionsPassed = validateOptions();
	var responseText = document.getElementById('responseText').value;
	if(!optionsPassed){
		alert("Please select one of the posting options (either Anonymous or with Team member names).");
		return false;
	} else if(responseText == null || responseText == ""){
		alert("Please enter a response before submitting.");
		return false;
	} else {
		return true;
	};
};

function submit(){
	if(validate()){
		post();
	};
};

function post(){
	var URL='postresponse.html';
	var data='option=' + validateOptions() + '&text=' + document.getElementById('responseText').value + '&workgroupId=${workgroup.id}&brainstormId=${brainstorm.id}';
	var callback = {
		success:function(o){
			var xmlDoc = o.responseXML;
			if(xmlDoc==null){
				callback.failure(o);
				self.close();
			};
			var freshAnswer = xmlDoc.getElementsByTagName('answer');
			var parentDoc = window.opener.document;
			var newAnswer = parentDoc.createElement('div');
			var newRevision = parentDoc.createElement('div');
			var idAttributeAns = parentDoc.createAttribute('id');
			var nameAttributeAns = parentDoc.createAttribute('name');
			idAttributeAns.nodeValue=freshAnswer[0].childNodes[0].childNodes[0].nodeValue;
			nameAttributeAns.nodeValue = 'answer';
			var idAttributeRev = parentDoc.createAttribute('id');
			var nameAttributeRev = parentDoc.createAttribute('name');
			nameAttributeRev.nodeValue = 'revision';
			var revHead = parentDoc.createElement('div');
			var revHeadAttribute = parentDoc.createAttribute('name');
			revHeadAttribute.nodeValue = 'revisionHead';
			idAttributeRev.nodeValue = freshAnswer[0].childNodes[2].childNodes[0].childNodes[0].childNodes[0].nodeValue;
			var revBody = parentDoc.createElement('div');
			var revBodyAttribute = parentDoc.createAttribute('name');
			revBodyAttribute.nodeValue = 'revisionBody';
			var revFoot = parentDoc.createElement('div');
			var revFootAttribute = parentDoc.createAttribute('name');
			revFootAttribute.nodeValue='revisionFoot';
			
			var revHeadText = parentDoc.createTextNode(getRevHeadText(freshAnswer));
			var revBodyText = parentDoc.createTextNode(getRevBodyText(freshAnswer));
			revHead.appendChild(revHeadText);
			revBody.appendChild(revBodyText);
			revFoot.innerHTML = '<a href="#" onclick="addCommentPopUp(${workgroup.id}, ${answer.id})">Add a Comment</a> <a href="#">Revise this Response</a>';
			newAnswer.appendChild(revHead);
			newAnswer.appendChild(revBody);
			newAnswer.appendChild(revFoot);
			
			var newAnswerId = freshAnswer[0].childNodes[0].childNodes[0].nodeValue;
			var answers = parentDoc.getElementsByName('answer');
			for(z=0;z<answers.length;z++){
				if(newAnswerId > answers[z].getAttribute('id') || answers.length-1 == z){
					inserted = true;
					answers[z].parentNode.insertBefore(newAnswer,answers[z].nextSibling);
					self.close();
				};
			};
		},
		failure:function(o){alert('failed to update to server');}
	};
	YAHOO.util.Connect.asyncRequest('POST', URL, callback, data);
};

function getRevHeadText(answer){
	var returnString = "";
	var names = "";
	var anon = answer[0].childNodes[1].childNodes[0].nodeValue;
	var timestamp = answer[0].childNodes[2].childNodes[0].childNodes[1].childNodes[0].nodeValue;
	if(anon=='true'){
		names="Anonymous";
	} else {
		names=getNames(answer[0].childNodes[3].childNodes[1]);
	}
	returnString = names + " " + timestamp + "    HELPFULNESS SCORE";
	return returnString;
};

function getRevBodyText(answer){
	return answer[0].childNodes[2].childNodes[0].childNodes[2].childNodes[0].nodeValue;
};

function getNames(members){
	var names = "";
	var students = members.childNodes;
	for(n=0;n<students.length;n++){
		names = names + students[n].childNodes[1].childNodes[0].nodeValue + " " + students[n].childNodes[2].childNodes[0].nodeValue;
		if(students.length-1 != n){
			names = names + ", ";
		};
	};
	return names;
};
</script>
</head>
<body>

<div align="centered">

	<div id="head">Create A Response</div>
	<div>Subgroup "SUBGROUP NAME" (# students)<br>Your response will be seen by all students in your subgroup</div>
	<div id="question">
		Question
		<span id="questionBox">${brainstorm.question.prompt}</span>
	</div>
	<div id="response">
		Response
		<textarea id="responseText" cols="45" rows="8"></textarea>
	</div>
	<div id="selectPostType">
		<div>How would you like your response labeled?</div>
		<label for="radioAnon"><input type="radio" name="postName" id="radioAnon" value="0"/>Label anonymously as: "Anonymous"</label>
		<label for="radioTeam"><input type="radio" name="postName" id="radioTeam" value="1"/>Label with student names: 
			<c:forEach var="student" varStatus="studentStatus" items="${workgroup.members}">
				${student.userDetails.firstname} ${student.userDetails.lastname}
				<c:if test="${studentStatus.last=='false'}">, </c:if>
			</c:forEach>
		</label>
	</div>
	
	<div>
		<input id="buh-bye" type="button" value="CANCEL" onclick="self.close()"/>
		<input id="submitResponse" type="button" value="SUBMIT RESPONSE" onclick="submit()"/>
	</div>
	
</div> <!-- end centered div -->

</body>
</html>