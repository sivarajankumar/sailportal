<%@ include file="../include.jsp"%>
<!--
  * Copyright (c) 2006 Encore Research Group, University of Toronto
  * 
  * This library is free software; you can redistribute it and/or
  * modify it under the terms of the GNU Lesser General Public
  * License as published by the Free Software Foundation; either
  * version 2.1 of the License, or (at your option) any later version.
  *
  * This library is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
  * Lesser General Public License for more details.
  *
  * You should have received a copy of the GNU Lesser General Public
  * License along with this library; if not, write to the Free Software
  * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
-->

<!-- $Id: index.jsp 2668 2010-01-14 21:29:35Z shadowtorn $ -->

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="en">
<head>
<script type="text/javascript" src="javascript/tels/jquery-1.4.1.min.js" ></script>
<%@ include file="../teacher/projects/styles.jsp"%>

<style type="text/css">
a.runArchiveLink, a.messageArchiveLink, a.messageReplyLink {
	color:blue;
	cursor:pointer;
}

div.messageDiv {
	margin-bottom:10px;
}

div.replyDiv {
	display:none;
}

div#composeMessageFeedbackDiv {
	color:orange;
}

div.replyFeedbackDiv {
	color:orange;
}

</style>
<script type="text/javascript">
// asynchronously archives a message
function archiveMessage(messageId, sender, isRead) {
	debugger;
	var messageDiv = document.getElementById('message_' + messageId);
	
	var callback = {
		success:function(o){
			/* move message from archived->new or new->archived */
			if (isRead=="true") {   // move from new->archived
				var message_text_div = document.getElementById("message_action_div_"+messageId);
				message_text_div.innerHTML = 		
					"<a class=\"messageArchiveLink\" onclick=\"archiveMessage('"+messageId+"', '"+sender+"', 'false');\">Mark as Unread</a> | "+ 
					"<a class=\"messageReplyLink\" onclick=\"alert('Sorry, replying to a message is not yet implemented');\">Reply</a><br/><br/>";
				document.getElementById("newMessageDiv").removeChild(messageDiv);				
				document.getElementById("archivedMessageDiv").appendChild(messageDiv);
			} else {  // move from archived->new
				var message_text_div = document.getElementById("message_action_div_"+messageId);
				message_text_div.innerHTML = 		
					"<a class=\"messageArchiveLink\" onclick=\"archiveMessage('"+messageId+"', '"+sender+"', 'true');\">Archive</a> | "+ 
					"<a class=\"messageReplyLink\" onclick=\"alert('Sorry, replying to a message is not yet implemented');\">Reply</a><br/><br/>";
				document.getElementById("archivedMessageDiv").removeChild(messageDiv);
				document.getElementById("newMessageDiv").appendChild(messageDiv);
			}

			// update the counters for both new and archived messages
			var newMessageCount = document.getElementById("newMessageDiv").getElementsByClassName("messageDiv").length;
			document.getElementById("newMessageCountDiv").innerHTML = "You have "+newMessageCount+" new message(s).";
			var archivedMessageCount = document.getElementById("archivedMessageDiv").getElementsByClassName("messageDiv").length;
			document.getElementById("archivedMessageCountDiv").innerHTML = "You have "+archivedMessageCount+" archived message(s).";
		},
		failure:function(o){
			/* set failure message */
			messageDiv.innerHTML = '<font color="992244">Unable to update message on server! Refresh this page to try again.</font>';
		},
		scope:this
	};	

	var action="archive";
	if (isRead == "false") {
		action="unarchive";
	}
	YAHOO.util.Connect.asyncRequest('POST', '/webapp/message.html?action='+action+'&messageId='+messageId, callback, null);
}

// sends a new (if originalMessageId is -1) or reply (if originalMessageId is set) message
function sendMessage(originalMessageId) {
	debugger;
	var recipient = null;
	var subject = null;
	var body = null;
	var postData = null;
	if (originalMessageId == "-1") {   // new message
		originalMessageId = document.getElementById("compose_originalMessageId").value;
		recipient = document.getElementById("compose_recipient").value;
		subject = document.getElementById("compose_subject").value;
		body = document.getElementById("compose_body").value;
		postData = "recipient=" + recipient + "&subject=" + subject + "&body=" + body;
	} else {
		recipient = $("#message_from_"+originalMessageId).html();
		subject = $("#reply_subject_"+originalMessageId).html();
		body =	$("#reply_body_"+originalMessageId).val();
		postData = "recipient=" + recipient + "&subject=" + subject + "&body=" + body;
		postData += "&originalMessageId=" + originalMessageId;
	}

	var callback = {
			success:function(o){
				if (o.responseText != null && o.responseText == "success") {
					if (originalMessageId == "-1") {
						clearComposeMessageForm();
						$("#composeMessageFeedbackDiv").html("message to " + recipient + " was sent successfully!");
					} else {
						showReplyForm(originalMessageId, false);
						$("#replyFeedbackDiv_"+originalMessageId).html("reply to " + recipient + " was sent successfully!");
												
					}
					// add the message to sentMessagesDiv.
					var dateString = getDateString();
		
					var sentMessageDiv = document.createElement("div");
					sentMessageDiv.setAttribute("class", "messageDiv");
					sentMessageDiv.innerHTML = "<div id=\"message_text_div_10\">" +
			  	  	"Date: "+dateString+"<br/>" +
			  	  	"To: "+recipient+"<br/>" +
					"Subject: <span>"+subject+"</span><br/>"+
			    	body+"</div>";
			    	document.getElementById("sentMessageDiv").appendChild(sentMessageDiv);					
				} else {
					if (o.responseText != null && o.responseText == "recipient not found") {
						alert("The recipient could not be found. Please verify that you have entered the recipient's exact username.");
					} else {
						alert("Unknown problem sending message. Please talk to WISE staff.");
					}
				}
			},
			failure:function(o){
			},
			scope:this
		};	
	
	YAHOO.util.Connect.asyncRequest('POST', '/webapp/message.html?action=compose', callback, postData);
}

function clearComposeMessageForm() {
	document.getElementById("compose_originalMessageId").value = "-1";
	document.getElementById("compose_recipient").value = "";
	document.getElementById("compose_subject").value = "";
	document.getElementById("compose_body").value = "";
}

function getDateString() {
	var currentTime = new Date();
	var month = currentTime.getMonth() + 1;
	var day = currentTime.getDate();
	var year = currentTime.getFullYear();
	var hours = currentTime.getHours() % 12;
	var minutes = currentTime.getMinutes();
	if (minutes < 10){
		minutes = "0" + minutes;
	}
	var amPm = "";
	if(hours > 11){
		amPm = "PM";
	} else {
	 	amPm = "AM";
	}
	return month + "/" + day + "/" + year + " " +
		hours + ":" + minutes + " " + amPm;
	
}

function showReplyForm(originalMessageId, doShow) {
	if (doShow) {
		$("#replyDiv_"+originalMessageId).css("display", "block");
	} else {
		$("#replyDiv_"+originalMessageId).css("display", "none");
	}
}

function sendReply(originalMessageId) {
	sendMessage(originalMessageId);
}

</script>
</head>
<body>
<h3>New Messages</h3>
<div id="newMessageCountDiv"><c:out value="You have ${fn:length(unreadMessages)} new message(s)."></c:out></div>
<div id="newMessageDiv">
<c:forEach var="message" items="${unreadMessages}" >
    <div class="messageDiv" id="message_${message.id}">
    	<div id="message_text_div_${message.id}">
  	  	Date: <fmt:formatDate value="${message.date}" type="both" dateStyle="short" timeStyle="short" /><br/>
		From: <span id="message_from_${message.id}">${message.sender.userDetails.username}</span><br/>
		Subject: <span id="message_subject_${message.id}">${message.subject}</span><br/>
		<c:out value="${message.body}" /><br/></div>
		<div id="message_action_div_${message.id}">
			<a class="messageArchiveLink" onclick="archiveMessage('${message.id}', '${message.sender.userDetails.username}', 'true');">Archive</a> | 
			<a class="messageReplyLink" onclick="showReplyForm('${message.id}', true);">Reply</a><br/><br/>
		</div>
		<div class="replyDiv" id="replyDiv_${message.id}">
			Subject: <span id="reply_subject_${message.id}">Re: ${message.subject}</span><br/>
			<textarea cols="75" rows="5" id="reply_body_${message.id}" ></textarea>
			<input type="button" value="Send" onclick="sendReply('${message.id}')" />
			<input type="button" value="Cancel" onclick="showReplyForm('${message.id}',false)" />
		</div>
		<div class="replyFeedbackDiv" id="replyFeedbackDiv_${message.id}"></div>
	</div>
</c:forEach>
</div>

<h3>Archived Messages</h3>
<div id="archivedMessageCountDiv"><c:out value="You have ${fn:length(readMessages)} archived message(s)."></c:out></div>
<div id="archivedMessageDiv">
<c:forEach var="message" items="${readMessages}" >
    <div class="messageDiv" id="message_${message.id}">
    	<div id="message_text_div_${message.id}">
  	  	Date: <fmt:formatDate value="${message.date}" type="both" dateStyle="short" timeStyle="short" /><br/>
		From: <span id="message_from_${message.id}">${message.sender.userDetails.username}</span><br/>
		Subject:  <span id="message_subject_${message.id}">${message.subject}</span><br/>
		<c:out value="${message.body}" /><br/></div>
		<div id="message_action_div_${message.id}">
			<a class="messageArchiveLink" onclick="archiveMessage('${message.id}', '${message.sender.userDetails.username}', 'false');">Mark as Unread</a> | 
			<a class="messageReplyLink" onclick="showReplyForm('${message.id}', true);">Reply</a><br/><br/>
		</div>
		<div class="replyDiv" id="replyDiv_${message.id}">
			Subject: <span id="reply_subject_${message.id}">Re: ${message.subject}</span><br/>
			<textarea cols="75" rows="5" id="reply_body_${message.id}" ></textarea>
			<input type="button" value="Send" onclick="sendReply('${message.id}')" />
			<input type="button" value="Cancel" onclick="showReplyForm('${message.id}',false)" />
		</div>
		<div class="replyFeedbackDiv" id="replyFeedbackDiv_${message.id}"></div>
	</div>
</c:forEach>
</div>

<h3>Sent Messages</h3>
<div id="sentMessageCountDiv"><c:out value="You have ${fn:length(sentMessages)} sent message(s)."></c:out></div>
<div id="sentMessageDiv">
<c:forEach var="message" items="${sentMessages}" >
    <div class="messageDiv" id="message_${message.id}">
    	<div id="message_text_div_${message.id}">
  	  	Date: <fmt:formatDate value="${message.date}" type="both" dateStyle="short" timeStyle="short" /><br/>
		To: <c:out value="${message.recipient.userDetails.username}"/><br/>
		Subject:  <span id="message_subject_${message.id}">${message.subject}</span><br/>
		<c:out value="${message.body}" /><br/></div>
	</div>
</c:forEach>
</div>

<h3>Compose Message</h3>
<div id="composeMessageFeedbackDiv"></div>
<div id="composeMessageDiv">
	<input type="hidden" id="compose_originalMessageId" value="-1" />
	<table>
		<tr><td>To:</td><td><input type="text" id="compose_recipient"/></td></tr>
		<tr><td>Subject:</td><td><input type="text" id="compose_subject" /></td></tr>
		<tr><td>Message:</td><td><textarea cols="75" rows="10" id="compose_body" ></textarea></td></tr>
	</table>
	<br/>
	<input type="button" value="Send" onclick="sendMessage('-1')" />
</div>
</body>
</html>