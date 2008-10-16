<%@ include file="../../include.jsp"%>
<html>
<head>

<link href="../../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="homepagestylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />


<script type="text/javascript">
function updatePromptPreview() {
   var promptPreviewElement = this.document.getElementById("promptPreview");
   var promptTextAreaElement = this.document.getElementById("promptTextArea");
   promptPreviewElement.innerHTML = promptTextAreaElement.value;
}
</script>
</head>

<body>

<div id="centeredDiv">

<%@ include file="headermain.jsp"%> 

<c:if test="${brainstorm.run != null}">

<div class="authorQuote">You unlock this door with the key of imagination. 
Beyond it is another dimension - a dimension of sound, a dimension of sight, a dimension of mind. 
You're moving into a land of both shadow and substance, of things and ideas. You've just crossed over into...the Authoring Zone. &nbsp; &nbsp; ;-)</div>

<div class="bigTitle">Authoring Area</div>

<div>
	<table id="authorInfoTable">
	<tr>
		<th>Project Title</th>
		<td style="color:#3333FF;">[Need Project Title here]</td>
	</tr>
	<tr>
		<th>Project ID</th>
		<td style="color:#3333FF;">[Need Project ID here]</td>
	</tr>
	<tr>
		<th>Step Type</th>
		<td style="color:#3333FF;">[Need Step Type being authored here]</td>
	</tr>
	<tr>
		<th>Step Location</th>
		<td style="color:#3333FF;">[Need Activity/Step Location here. Ex: "Activity 1, Step 3"]</td>
	</tr>
	</table>
</div>

</c:if>

<form:form method="post" action="authorbrainstorm.html?brainstormId=${brainstorm.id}" commandName="brainstorm" id="brainstormform" >

<div class="authorSectionHeader">Gating Options for Q&amp;A Step</div>

	<div style="color:#FF0000;">
	  <div class="authorSectionHeader2">Starting the Q&amp;A Discussion:</div>
	  <div class="authorOptionsBlock" >
      	<form:radiobutton path="gated" value="true" /><b>Open:</b> the Q&amp;A discussion step is available at all times. <br/>
      	<form:radiobutton path="gated" value="false" /><b>Start Manually:</b> the Q&amp;A is activated manually by the teacher<br/>
      	<form:radiobutton path="gated" value="false" /><b>Start on Date:</b>  the Q&amp;A activates automatically on a date set by the teacher.<br/>
      </div>
    </div>
      
      <div class="authorSectionHeader2">Initial Student Response:</div>
      <div class="authorOptionsBlock" >
      	<form:radiobutton path="gated" value="true" /><b>Gated:</b> the student must submit a Response before seeing Responses from other students.<br/>
      	<form:radiobutton path="gated" value="false" /><b>Open:</b> the student can see Responses from other students immediately.<br/>
      <div>
      
<div class="authorSectionHeader">Display Options</div>
       <div class="authorSectionHeader2">When responses are submitted by students, how should they be labeled?</div>
       <div class="authorOptionsBlock">
      		<c:forEach items="${displayNameOptions}" var="displayNameOption">
      		<form:radiobutton path="displayNameOption" value="${displayNameOption}" />${displayNameOption}<br/>      
      		</c:forEach>
      </div>
      
<div class="authorSectionHeader">Grouping Options</div>
       <div class="authorSectionHeader2">Select a choice:</div>
       <div class="authorOptionsBlock" >
      		<form:radiobutton path="gated" value="true" /><b>One Group per period:</b> all students in a class period participate in a group discussion..<br/>
      		<form:radiobutton path="gated" value="true" /><b>Multiple Groups per period:</b> students in a class period work in smaller subgroups. Each student sees only the responses within their subgroup.<br/>
      	</div>	
      		<div id="subgroupSelectionBox">
      			<div>Number of subgroups in each class period: 
      					<span style="color:#3333FF;">
      					<select name="subgroups" id="subgroups" tabindex="1">
      					<option value="">select an option</option>
      					<option value="2">2 Groups per period</option>
      					<option value="3">3 Groups per period</option>
      					<option value="4">4 Groups per period</option>
      					<option value="5">5 Groups per period</option>
      					<option value="6">6 Groups per period</option>
      					<option value="7">7 Groups per period</option>
      					<option value="8">8 Groups per period</option>
      					</select>
      					</span>
      			</div>
      			<div  style="color:#3333FF;">
      			Need Table Here listing "Group 1" "Group 2" etc up to the number of selected groups.  <br/>
      			The Author should be able to customize these default names if desired.
      		</div>
      		

<div class="authorSectionHeader">Edit Content for Q&amp;A Step</div>

  <div>
      <h4>Question</h4>
      <p>Type your Question in the box below using HTML formatting.</p>
      <form:textarea rows="8" cols="110" path="question.prompt" id="promptTextArea" ></form:textarea>
      <form:errors path="question.prompt" />
   </div>


<div id="responseButtons">
	<input type="submit" name="save" value="Save" />
	<input type="reset" onclick="javascript:window.close()" name="cancel" value="Cancel" />
</div>
<br/>
<button id="updatePromptPreviewButton" onclick="javascript:updatePromptPreview()" >Update preview</button>

      <h2>Question Preview <span>Your question will appear to students as follows:</span></h2>
      
	<div id="promptPreview">
		${brainstorm.question.prompt}
	</div>


</form:form>



</div>

</body>
</html>