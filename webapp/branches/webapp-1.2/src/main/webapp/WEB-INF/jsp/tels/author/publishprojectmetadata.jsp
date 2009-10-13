<%@ include file="../include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>Publish Metadata</title>

<script type="text/javascript" src="../javascript/tels/yui_3.0.0b1/build/yui/yui-min.js"></script>
</head>
<body>
<div id='mainDiv'>
	<div id='topDiv'>
		Please confirm the changes below. <b>This operation cannot be undone!</b>
	</div>
	
	<!-- Support for Spring errors object -->
	<div id="regErrorMessages">
	<spring:bind path="publishProjectMetadataParameters.*">
	  <c:forEach var="error" items="${status.errorMessages}">
	        <br /><c:out value="${error}"/>
	    </c:forEach>
	</spring:bind>
	</div>

	<div id='currentProjectDiv'>
		<table id='projectMetadataTable'>
			<thead>
				<tr><td id='metadataHeadTd'>Current Metadata</td><td>Replace With</td></tr>
			</thead>
			<tbody>
				<tr>
					<td id='projectMetadataTd'>
						<div id='title'>Title: ${currentTitle}</div>
						<div id='author'>Author: ${currentAuthor}</div>
						<div id='subject'>Subject: ${currentSubject}</div>
						<div id='duration'>Duration: ${currentDuration}</div>
						<div id='summary'>Summary: ${currentSummary}</div>
					</td>
					<td id='replacementMetaTd'>
						<form:form method='post' action='publishprojectmetadata.html' id='publishMetadataForm'  commandName='publishProjectMetadataParameters'>
							<label for='replacementTitle'>Title: </label>
							<form:input path='title' id='replacementTitle' disabled="true"/><br/>
							<label for='replacementAuthor'>Author: </label>
							<form:input path='author' id='replacementAuthor' disabled="true"/><br/>
							<label for='replacementSubject'>Subject: </label>
							<form:input path='subject' id='replacementSubject' disabled="true"/><br/>
							<label for='replacementDuration'>Duration: </label>
							<form:input path='duration' id='replacementDuration' disabled="true"/><br/>
							<label for='replacementSummary'>Summary: </label>
							<form:textarea path='summary' id='replacementSummary' disabled="true"/><br/>
							<form:hidden path='projectId' id='projectId'/>
							<input type='submit' value='publish' id='submitButt'/>
						</form:form>
						<div id='replacementDiv'></div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
</body>
</html>