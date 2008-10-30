
function Answers(){
	this.answers = [];
};

Answers.prototype.setAnswers = function(xmlAnswers){
	var xAnswers = xmlAnswers.getElementsByTagName('answer');
	if(xAnswers!=null && xAnswers[0]!=null){
		for(x=0;x<xAnswers.length;x++){
			this.setAnswer(xAnswers[x]);
		};
	};	
};

Answers.prototype.getAnswers = function(){
	return this.answers;
};

Answers.prototype.setAnswer = function(xmlAnswer){
	this.answers.push(new Answer(xmlAnswer));
};

Answers.prototype.addAnswer = function(answer){
	this.answers.push(answer);
};

Answers.prototype.getAnswerById = function(id){
	var foundAnswer;
	for(aa=0;aa<this.answers.length;aa++){
		if(this.answers[aa].getId()==id){
			return this.answers[aa];
		};
	};
};

Answers.prototype.sort = function(order){
	this.answers.sort(order);
};

Answers.prototype.toString = function(){
	var outAnswers = "Answers: ";
	for(l=0;l<this.answers.length;l++){
		outAnswers = outAnswers + this.answers[l].toString();
	};
	return outAnswers;
};

function Answer(xmlAnswer){
	this.id;
	this.revisions = [];
	this.workgroup;
	this.comments = [];
	this.anonymous;
	this.helpfulWorkgroups= [];
	
	this.setAnswer(xmlAnswer);
};

Answer.prototype.setAnswer = function(xmlAnswer){
	this.setId(xmlAnswer.childNodes[0]);
	this.setRevisions(xmlAnswer.getElementsByTagName('revisions'));
	this.setWorkgroup(xmlAnswer.childNodes[4]);
	this.setComments(xmlAnswer.getElementsByTagName('comments'));
	this.setAnonymous(xmlAnswer.childNodes[1]);
	this.setHelpfulWorkgroups(xmlAnswer.getElementsByTagName('helpfulworkgroups'));
};

Answer.prototype.setId = function(xmlId){
	if(xmlId!=null){
		this.id=xmlId.childNodes[0].nodeValue;
	};
};

Answer.prototype.setRevisions = function(xmlRevisions){
	if(xmlRevisions!=null && xmlRevisions[0]!=null){
		var revisions = xmlRevisions[0].getElementsByTagName('revision');
		if(revisions!=null && revisions[0]!=null){
			for(h=0;h<revisions.length;h++){
				this.revisions.push(new Revision(revisions[h]));
			};
		};
	};
};

Answer.prototype.setWorkgroup = function(xmlWorkgroup){
	if(xmlWorkgroup!=null){
		this.workgroup=(new Workgroup(xmlWorkgroup));
	};
};

Answer.prototype.setComments = function(xmlComments){
	if(xmlComments!=null && xmlComments[0]!=null){
		var comments = xmlComments[0].getElementsByTagName('comment');
		if(comments!=null && comments[0]!=null){
			for(j=0;j<comments.length;j++){
				this.comments.push(new Comment(comments[j]));
			};
		};
	};
};

Answer.prototype.setAnonymous = function(xmlAnon){
	if(xmlAnon!=null){
		this.anonymous=booleanValueOf(xmlAnon.childNodes[0].nodeValue);
	};
};

function booleanValueOf(v){
	if(v=='true'){
		return true;
	} else {
		return false;
	}
};

Answer.prototype.setHelpfulWorkgroups = function(xmlHelpfulWorkgroups){
	if(xmlHelpfulWorkgroups!=null && xmlHelpfulWorkgroups[0]!=null){
		var helpfulWorkgroups = xmlHelpfulWorkgroups[0].getElementsByTagName('workgroup');
		if(helpfulWorkgroups!=null && helpfulWorkgroups[0]!=null){
			for(af=0;af<helpfulWorkgroups.length;af++){
				this.helpfulWorkgroups.push(new Workgroup(helpfulWorkgroups[af]));
			};
		};
	};
};

Answer.prototype.addRevision = function(revision){
	this.revisions.push(revision);
};

Answer.prototype.addComment = function(comment){
	this.comments.push(comment);
};

Answer.prototype.getId = function(){
	return this.id;
};

Answer.prototype.getRevisions = function(){
	return this.revisions;
};

Answer.prototype.getWorkgroup = function(){
	return this.workgroup;
};

Answer.prototype.getComments = function(){
	return this.comments;
};

Answer.prototype.isAnonymous = function(){
	return this.anonymous;
};

Answer.prototype.getHelpfulWorkgroups = function(){
	return this.helpfulWorkgroups;
};

Answer.prototype.getLatestRevision = function(){
	return this.revisions[this.revisions.length-1];
};

Answer.prototype.getOtherRevisions = function(){
	var others = [];
	for(r=0;r<this.revisions.length - 1;r++){
		others.push(this.revisions[r]);
	};
	return others;
};

Answer.prototype.toString = function(){
	var outAnswer = "(Answer: id=" + this.id + " anon=" + this.anonymous + "(Revisions: ";
	for(y=0;y<this.revisions.length;y++){
		outAnswer = outAnswer + this.revisions[y].toString();
	};
	outAnswer = outAnswer + ") (Comments: ";
	for(d=0;d<this.comments.length;d++){
		outAnswer = outAnswer + this.comments[d].toString();
	};
	outAnswer = outAnswer + ") " + this.workgroup.toString() + " (Helpful workgroups: ";
	for(s=0;s<this.helpfulWorkgroups.length;s++){
		outAnswer = outAnswer + this.helpfulWorkgroups[s].toString();
	};
	outAnswer = outAnswer + ") )";
	return outAnswer;
};

function Revision(xmlRevision){
	this.timestamp;
	this.body;
	this.id;
	this.setRevision(xmlRevision);
};

Revision.prototype.setRevision = function(xmlRevision){
	this.setId(xmlRevision.getElementsByTagName('id'));
	this.setBody(xmlRevision.getElementsByTagName('body'));
	this.setTimestamp(xmlRevision.getElementsByTagName('timestamp'));
};

Revision.prototype.setId = function(xmlId){
	if(xmlId!=null){
		this.id=xmlId[0].childNodes[0].nodeValue;
	};
};

Revision.prototype.setBody = function(xmlBody){
	if(xmlBody!=null){
		this.body=xmlBody[0].textContent;
	};
};

Revision.prototype.setTimestamp = function(xmlTimestamp){
	if(xmlTimestamp!=null){
		this.timestamp=xmlTimestamp[0].childNodes[0].nodeValue;
	};
};

Revision.prototype.toString = function(){
	var outRevision = "(Revision: id=" + this.id + " body=" + this.body + " timestamp=" + this.timestamp + ")";
	return outRevision;
};

Revision.prototype.getId = function(){
	return this.id;
};

Revision.prototype.getBody = function(){
	return this.body;
};

Revision.prototype.getTimestamp = function(){
	return this.timestamp;
};

function Comment(xmlComment){
	this.id;
	this.workgroup;
	this.timestamp;
	this.body;
	this.anonymous;
	this.setComment(xmlComment);
};

Comment.prototype.setComment = function(xmlComment){
	this.setId(xmlComment.childNodes[0]);
	this.setWorkgroup(xmlComment.childNodes[2]);
	this.setTimestamp(xmlComment.getElementsByTagName('timestamp'));
	this.setBody(xmlComment.getElementsByTagName('body'));
	this.setAnonymous(xmlComment.getElementsByTagName('anon'));
};

Comment.prototype.setId = function(xmlId){
	if(xmlId!=null){
		this.id=xmlId.childNodes[0].nodeValue;
	};
};

Comment.prototype.setWorkgroup = function(xmlWorkgroup){
	if(xmlWorkgroup!=null){
		this.workgroup = new Workgroup(xmlWorkgroup);
	};
};

Comment.prototype.setTimestamp = function(xmlTimestamp){
	if(xmlTimestamp!=null){
		this.timestamp=xmlTimestamp[0].childNodes[0].nodeValue;
	};
};

Comment.prototype.setBody = function(xmlBody){
	if(xmlBody!=null){
		this.body=xmlBody[0].childNodes[0].nodeValue;
	};
};

Comment.prototype.setAnonymous = function(xmlAnon){
	if(xmlAnon!=null || xmlAnon[0]!=null){
		this.anonymous=booleanValueOf(xmlAnon[0].childNodes[0].nodeValue);
	};
};

Comment.prototype.toString = function(){
	var outComment = "(Comment: id=" + this.id + " anon=" + this.anonymous + " workgroup=" + this.workgroup.toString();
	outComment = outComment + " body=" + this.body + " timestamp=" + this.timestamp + ")";
	return outComment;
};

Comment.prototype.getId = function(){
	return this.id;
};

Comment.prototype.getWorkgroup = function(){
	return this.workgroup;
};

Comment.prototype.getTimestamp = function(){
	return this.timestamp;
};

Comment.prototype.getBody = function(){
	return this.body;
};

Comment.prototype.isAnonymous = function(){
	return this.anonymous;
};

function Workgroup(xmlWorkgroup){
	this.id;
	this.members = [];
	this.setWorkgroup(xmlWorkgroup);
};

Workgroup.prototype.setWorkgroup = function(xmlWorkgroup){
	this.setId(xmlWorkgroup.childNodes[0]);
	this.setMembers(xmlWorkgroup.getElementsByTagName('student'));
};

Workgroup.prototype.setId = function(xmlId){
	if(xmlId!=null){
		this.id=xmlId.childNodes[0].nodeValue;
	};
};

Workgroup.prototype.setMembers = function(xmlMembers){
	if(xmlMembers!=null && xmlMembers[0]!=null){
		for(a=0;a<xmlMembers.length;a++){
			this.members.push(new Member(xmlMembers[a]));
		};
	};
};

Workgroup.prototype.toString = function(){
	var outWorkgroup = "(Workgroup: id=" + this.id + " members=";
	for(e=0;e<this.members.length;e++){
		outWorkgroup = outWorkgroup + this.members[e].toString();
	};
	outWorkgroup = outWorkgroup + ")";
	return outWorkgroup;
};

Workgroup.prototype.getId = function(){
	return this.id;
};

Workgroup.prototype.getMembers = function(){
	return this.members;
};

function Member(xmlMember){
	this.id;
	this.firstname;
	this.lastname;
	this.setMember(xmlMember);
};

Member.prototype.setMember = function(xmlMember){
	this.setId(xmlMember.getElementsByTagName('id'));
	this.setFirstname(xmlMember.getElementsByTagName('firstname'));
	this.setLastname(xmlMember.getElementsByTagName('lastname'));
};

Member.prototype.setId = function(xmlId){
	if(xmlId!=null){
		this.id=xmlId[0].childNodes[0].nodeValue;
	};
};

Member.prototype.setFirstname = function(xmlFirst){
	if(xmlFirst!=null){
		this.firstname=xmlFirst[0].childNodes[0].nodeValue;
	};
};

Member.prototype.setLastname = function(xmlLast){
	if(xmlLast!=null){
		this.lastname=xmlLast[0].childNodes[0].nodeValue;
	};
};

Member.prototype.toString = function(){
	var outMember = "(Member: id=" + this.id + " firstname=" + this.firstname + " lastname=" + this.lastname + ")";
	return outMember;
};

Member.prototype.getId = function(){
	return this.id;
};

Member.prototype.getFirstname = function(){
	return this.firstname;
};

Member.prototype.getLastname = function(){
	return this.lastname;
};

/**
* functions for building elements and inserting them into
* the appropriate place for the student brainstorm
**/

function createAttribute(doc, node, type, val){
	var attribute = doc.createAttribute(type);
	attribute.nodeValue = val;
	node.setAttributeNode(attribute);
};

function createElement(doc, type, attrArgs){
	var newElement = doc.createElement(type);
	if(attrArgs!=null){
		for(var option in attrArgs){
			createAttribute(doc, newElement, option, attrArgs[option]);
		};
	};
	return newElement;
};

function createAnswerElements(doc, answers, workgroupId){
	var answerElements = [];
	var answerArray = answers.getAnswers();
	
	for(w=0;w<answerArray.length;w++){
		answerElements.push(createAnswerElement(doc, answerArray[w], workgroupId));
	};
	return answerElements;
};

function createAnswerElement(doc, answer, workgroupId){
	var answerElement = createElement(doc, 'div', {id:answer.getId(), name:'answer', class:'studentResponse'});
	var answerTable = createElement(doc,'table', {id:'revisionBoxTable'});
	answerElement.appendChild(answerTable);

	answerTable.appendChild(createLatestRevisionElement(doc, workgroupId, answer));
	if(answer.getRevisions().length>1){
		answerTable.appendChild(createOtherRevisionElements(doc, answer));
	};
	if(answer.getComments().length > 0){
		answerTable.appendChild(createCommentsElement(doc, workgroupId, answer));
	};

	return answerElement;
}

function createLatestRevisionElement(doc, workgroupId, answer){
	var revisionElement = createElement(doc, 'tr', {id:answer.getLatestRevision().getId(), class:'currentResponseBoxTR', name:'revision'});
	var revisionTable = createElement(doc, 'table', {class:'currentResponseBoxInsetTable'});
	revisionElement.appendChild(revisionTable);
	revisionTable.appendChild(createRevisionHead(doc, answer));
	revisionTable.appendChild(createRevisionBody(doc, workgroupId, answer));
	
	return revisionElement;
};

function createRevisionHead(doc, answer){
	var head = createElement(doc, 'thead');
	var headRow = createElement(doc, 'tr');
	var cell1 = createElement(doc, 'th', {class:'headerStudentName'});
	var cell2 = createElement(doc, 'th', {class:'headerResponseInfo'});
	var cell3 = createElement(doc, 'th', {class:'headerResponseInfo'});
	var divNames = createElement(doc, 'div');
	var divTime = createElement(doc, 'div');
	var divHelpful = createElement(doc, 'div');
	var namesText;
	if(answer.isAnonymous()){
		namesText = doc.createTextNode("Anonymous");
	} else {
		namesText = doc.createTextNode(getNames(answer.getWorkgroup()));
	};
	var timeText = doc.createTextNode(answer.getLatestRevision().getTimestamp());
	var helpfulText = doc.createTextNode(answer.getHelpfulWorkgroups().length + ' students found this post helpful');
	
	divNames.appendChild(namesText);
	divTime.appendChild(timeText);
	divHelpful.appendChild(helpfulText);
	cell1.appendChild(divNames);
	cell2.appendChild(divTime);
	cell3.appendChild(divHelpful);
	headRow.appendChild(cell1);
	headRow.appendChild(cell2);
	headRow.appendChild(cell3);
	head.appendChild(headRow);
	return head;
};

function createRevisionBody(doc, workgroupId, answer){
	var tbody = createElement(doc, 'tbody');
	if(answer.getRevisions().length > 1){
		var revRow = createElement(doc, 'td', {class:'currentRevisionNumber'});
		revRow.appendChild(doc.createTextNode('Revision ' + answer.getRevisions().length));
		tbody.appendChild(revRow);
	};
	
	var revHead = createElement(doc, 'tr');
	var revBody = createElement(doc, 'tr');
	var revFoot = createElement(doc, 'tr');
	var cell1 = createElement(doc, 'td');
	var cell2 = createElement(doc, 'td');
	var divAddComment = createElement(doc, 'div');
	var divVar = createElement(doc, 'div');
	
	divAddComment.innerHTML = '<a href="#" onclick="addCommentPopUp(' + workgroupId + ',' + answer.getId() + ')">Add a Comment</a>';
	if(answer.getWorkgroup().getId()==workgroupId){
		divVar.innerHTML ='<a href="#" onclick="addRevisionPopUp(' + workgroupId + ',' + answer.getId() + ')">Revise this Response</a>';
	} else {
		var helpful;
		var fHelp = foundHelpful(workgroupId, answer);
		var helpfulTxt = createElement(doc, 'label', {});		
		helpfulTxt.innerHTML = 'I found this response helpful';
		if(fHelp){
			helpful = createElement(doc, 'input', {type:'checkbox', id:'helpful_' + workgroupId + '_' + answer.getId(), checked:'checked', value:'helpful', onclick:'javascript:markAnswerAsHelpful(' + workgroupId +', ' + answer.getId() + ')'});
		} else {
			helpful = createElement(doc, 'input', {type:'checkbox', id:'helpful_' + workgroupId + '_' + answer.getId(), value:'helpful', onclick:'javascript:markAnswerAsHelpful(' + workgroupId +', ' + answer.getId() + ')'});
		};
		divVar.appendChild(helpful);
		divVar.appendChild(helpfulTxt);
	};
	

	cell1.appendChild(divAddComment);
	cell2.appendChild(divVar);
	revFoot.appendChild(cell1);
	revFoot.appendChild(cell2);
	revHead.appendChild(revRow);
	var revPre = createElement(doc, 'td', {class:'currentResponseText', colSpan:'3'});
	revPre.appendChild(doc.createTextNode(answer.getLatestRevision().getBody()));
	revBody.appendChild(revPre);
	tbody.appendChild(revHead);
	tbody.appendChild(revBody);
	tbody.appendChild(revFoot);
	return tbody;
};

function foundHelpful(workgroupId, answer){
	var workgroups = answer.getHelpfulWorkgroups();
	var helpful = false;
	for(ab=0;ab<workgroups.length;ab++){
		if(workgroups[ab].getId()==workgroupId){
			helpful=true;
		};
	};	
	return helpful;
};

function createOtherRevisionElements(doc, answer){
	var otherRevisions = answer.getOtherRevisions();
	var others = createElement(doc, 'tr');
	var othersTable = createElement(doc, 'table', {id:'revisionTable'});
	var head = createElement(doc, 'tr');
	var headDiv = createElement(doc, 'td', {class:'revisionTableHeader'});
	
	headDiv.appendChild(doc.createTextNode('Revisions'));
	head.appendChild(headDiv);
	othersTable.appendChild(head);
	others.appendChild(othersTable);
	
	var names;
	if(answer.isAnonymous()){
		names = 'Anonymous'; 
	} else {
		names = getNames(answer.getWorkgroup());
	};
	for(o=0;o<otherRevisions.length;o++){
		var newRow = createElement(doc, 'tr');
		var newDiv = createElement(doc, 'td');
		var newText = (o+1) + ': ' + otherRevisions[o].getBody() + ' (' + names + ', ' + otherRevisions[o].getTimestamp() + ')';
		newDiv.appendChild(doc.createTextNode(newText));
		newRow.appendChild(newDiv);
		othersTable.appendChild(newRow);
	};
	return others;
};

function createCommentsElement(doc, workgroupId, answer){
	var comments = answer.getComments();
	var commentElement = createElement(doc, 'tr', {name:'comments', id:answer.getId()});
	var commentTable = createElement(doc, 'table');
	var head = createElement(doc, 'tr');
	var numOfComments = createElement(doc, 'div');
	var divAddComment = createElement(doc, 'div');
	
	numOfComments.appendChild(doc.createTextNode(comments.length + ' comments'));
	divAddComment.innerHTML = '<a href="#" onclick="addCommentPopUp(' + workgroupId + ',' + answer.getId() + ')">Add a Comment</a>';
	head.appendChild(numOfComments);
	head.appendChild(divAddComment);
	commentTable.appendChild(head);
	commentElement.appendChild(commentTable);
	
	for(i=0;i<comments.length;i++){
		var workgroup = comments[i].getWorkgroup();
		var names;
		if(comments[i].isAnonymous()){
			names="Anonymous";
		} else {
			names=getNames(workgroup);
		};
		var newComment = createElement(doc, 'tr');
		var newDiv = createElement(doc, 'div');
		newDiv.appendChild(doc.createTextNode(comments[i].getBody() + '  (' + names + ', ' + comments[i].getTimestamp() + ')'));
		newComment.appendChild(newDiv);
		commentTable.appendChild(newComment);
	};
	return commentElement;
};

function getNames(workgroup){
	var names = "";
	var members = workgroup.getMembers();
	for(u=0;u<members.length;u++){
		names = names + members[u].getFirstname() + " " + members[u].getLastname();
		if(u!=members.length-1){
			names = names + " & ";
		};
	};
	return names;
};