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
		Please confirm the changes below.
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
						<div id='graderange'>Grade Range: ${currentGraderange}</div>
						<div id='totaltime'>Total Time: ${currentTotaltime}</div>
						<div id='comptime'>Computer Time: ${currentComptime}</div>
						<div id='contact'>Contact: ${currentContact}</div>
						<div id='summary'>Summary: ${currentSummary}</div>
						<div id='techreqs'>Technical Requirements: ${currentTechreqs}</div>
						<div id='lessonplan'>Lesson Plan: ${currentLessonplan}</div>
					</td>
					<td id='replacementMetaTd'>
						<form:form method='post' action='publishprojectmetadata.html' id='publishMetadataForm'  commandName='publishProjectMetadataParameters' autocomplete='off'>
							<label for='replacementTitle'>Title: </label>
							<form:input path='title' id='replacementTitle' disabled="true"/><br/>
							<label for='replacementAuthor'>Author: </label>
							<form:input path='author' id='replacementAuthor' disabled="true"/><br/>
							<label for='replacementSubject'>Subject: </label>
							<form:input path='subject' id='replacementSubject' disabled="true"/><br/>
							<label for='replacementGraderange'>Grade Range: </label>
							<form:input path='graderange' id='replacementGraderange' disabled="true"/><br/>
							<label for='replacementTotaltime'>Total Time: </label>
							<form:input path='totaltime' id='replacementTotaltime' disabled="true"/><br/>
							<label for='replacementComptime'>Computer Time: </label>
							<form:input path='comptime' id='replacementComptime' disabled="true"/><br/>
							<label for='replacementContact'>Contact: </label>
							<form:input path='contact' id='replacementContact' disabled="true"/><br/>
							<label for='replacementSummary'>Summary: </label>
							<form:textarea path='summary' id='replacementSummary' disabled="true"/><br/>
							<label for='replacementTechreqs'>Technical Requirements: </label>
							<form:textarea path='techreqs' id='replacementTechreqs' disabled="true"/><br/>
							<label for='replacementLessonplan'>Lesson Plan: </label>
							<form:textarea rows='10' path='lessonplan' id='replacementLessonplan' disabled="true"/><br/>
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