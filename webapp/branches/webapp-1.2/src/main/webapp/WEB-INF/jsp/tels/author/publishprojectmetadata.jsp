<%@ include file="../include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>Publish Metadata</title>

<script type="text/javascript" src="../javascript/tels/yui_3.0.0b1/build/yui/yui-min.js"></script>

<script>	
/**
 * Given the @param id of the project, searches through projects until
 * it finds a match, then sets the form's projectId element, and populates
 * the values of the html elements with the project's metadata.
 */
function changeMetadata(id){
	//cycle through projects
	<c:forEach var="project" items="${projects}">
		if('${project.id}'==id){
			//set hidden form element with this project's id
			document.getElementById('projectId').value = id;
		};
	</c:forEach>
};

/**
 * Calls the changeMetadata function with th value of the currently selected option
 */
function projectSelectionChanged(){
	changeMetadata(document.getElementById('projectSelect').options[document.getElementById('projectSelect').selectedIndex].value);
};
</script>
</head>
<body>
<div id='mainDiv'>
	<div id='topDiv'>
		Select a project from the list of projects found that you have authored below. When clicking on
		a project, the current metadata for that project will appear next to the list of projects. Make
		sure that you are updating the metadata for the correct project. <b>This operation cannot be undone!</b>
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
				<tr><td id='projectHeadTD'>Projects</td><td id='metadataHeadTd'>Current Metadata</td></tr>
			</thead>
			<tbody>
				<tr>
					<td id='projectListTd'>
						<br/>
						<select id='projectSelect' size='10' onchange='projectSelectionChanged()'>
							<c:forEach var="project" items="${projects}">
								<option id="${project.id}" value="${project.id}">${project.name}</option>
							</c:forEach>
						</select>
					</td>
					<td id='projectMetadataTd'>
						<div id='title'/>
						<div id='author'/>
						<div id='subject'/>
						<div id='duration'/>
						<div id='summary'/>
					</td>
				</tr>
				<tr>
					<td id='replacementMetaTd'>
						<br/>
						Replace with:<br/>
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