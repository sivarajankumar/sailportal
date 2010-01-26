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
<%@ include file="../teacher/projects/styles.jsp"%>

<style type="text/css">
a.runArchiveLink, a.messageArchiveLink, a.messageReplyLink {
	color:blue;
	cursor:pointer;
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

// sends a new or reply message
function sendMessage() {
	var originalMessageId = document.getElementById("compose_originalMessageId").value;
	var recipient = document.getElementById("compose_recipient").value;
	var subject = document.getElementById("compose_subject").value;
	var body = document.getElementById("compose_body").value;
	var postData = "recipient=" + recipient + "&subject=" + subject + "&body=" + body;
	if (originalMessageId != "-1") {
		postData += "&originalMessageId=" + originalMessageId;
	}

	var callback = {
			success:function(o){
				if (o.responseText != null && o.responseText == "success") {
					document.getElementById("compose_originalMessageId").value = "-1";
					document.getElementById("compose_recipient").value = "";
					document.getElementById("compose_subject").value = "";
					document.getElementById("compose_body").value = "";
					document.getElementById("composeMessageFeedbackDiv").innerHTML = 
						"message to " + recipient + " was sent successfully!";
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

// voids any currently-composed message
function cancelMessage() {
	
}

// pre-populates the compose message form to prepare for the reply.
	/*
	document.getElementById("compose_originalMessageId").value = originalMessageId;
	document.getElementById("compose_recipient").value = to;
	document.getElementById("compose_recipient").setAttribute("readonly", "readonly");
	var originalSubject = document.getElementById("message_subject_"+originalMessageId).innerHTML;
	document.getElementById("compose_subject").value = "Re:" + originalSubject;
	document.getElementById("compose_subject").setAttribute("readonly", "readonly");
	*/
function doReply(originalMessageId, to) {
	alert('reply is not yet implemented');
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
		From: <c:out value="${message.sender.userDetails.username}"/><br/>
		Subject: <span id="message_subject_${message.id}">${message.subject}</span><br/>
		<c:out value="${message.body}" /><br/></div>
		<div id="message_action_div_${message.id}">
			<a class="messageArchiveLink" onclick="archiveMessage('${message.id}', '${message.sender.userDetails.username}', 'true');">Archive</a> | 
			<a class="messageReplyLink" onclick="doReply('${message.id}','${message.sender.userDetails.username}');">Reply</a><br/><br/>
		</div>
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
		From: <c:out value="${message.sender.userDetails.username}"/><br/>
		Subject:  <span id="message_subject_${message.id}">${message.subject}</span><br/>
		<c:out value="${message.body}" /><br/></div>
		<div id="message_action_div_${message.id}">
			<a class="messageArchiveLink" onclick="archiveMessage('${message.id}', '${message.sender.userDetails.username}', 'false');">Mark as Unread</a> | 
			<a class="messageReplyLink" onclick="doReply('${message.id}','${message.sender.userDetails.username}');">Reply</a><br/><br/>
		</div>
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
	<input type="button" value="Send" onclick="sendMessage()" />
</div>
</body>
</html>