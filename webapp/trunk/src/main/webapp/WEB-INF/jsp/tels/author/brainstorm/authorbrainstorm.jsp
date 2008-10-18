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

<div id="authorQuote">You unlock this door with the key of imagination.  
Beyond it is another dimension - a dimension of sound, a dimension of sight, a dimension of mind. 
You're moving into a land of both shadow and substance, of things and ideas. You've just crossed over into...the Authoring Zone.</div>

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


	  <div class="authorSectionHeader2" style="color:#FF0000;" >Starting the Q&amp;A Discussion:</div>
	  <div class="authorOptionsBlock" style="color:#FF0000;"  >
      	<form:radiobutton path="gated" value="true" /><b>Open:</b> the Q&amp;A discussion step is available at all times. <br/>
      	<form:radiobutton path="gated" value="false" /><b>Start Manually:</b> the Q&amp;A is activated manually by the teacher<br/>
      	<form:radiobutton path="gated" value="false" /><b>Start on Date:</b>  the Q&amp;A activates automatically on a date set by the teacher.<br/>
      </div>
      
      <div class="authorSectionHeader2">Initial Student Response:</div>
      <div class="authorOptionsBlock" >
      	<form:radiobutton path="gated" value="true" /><b>Gated:</b> the student must submit a Response before seeing Responses from other students.<br/>
      	<form:radiobutton path="gated" value="false" /><b>Open:</b> the student can see Responses from other students immediately.<br/>
      </div>
      
<div class="authorSectionHeader">Display Options</div>
       <div class="authorSectionHeader2">When responses are submitted by students, how should they be labeled?</div>
       <div class="authorOptionsBlock">
      		<c:forEach items="${displayNameOptions}" var="displayNameOption">
      		<form:radiobutton path="displayNameOption" value="${displayNameOption}" />${displayNameOption}<br/>      
      		</c:forEach>
      </div>
   

<div class="authorSectionHeader">Grouping Options</div>

       <div style="color:#FF0000;" class="authorSectionHeader2">Select a choice:</div>
       
       <div style="color:#FF0000;" class="authorOptionsBlock" >
      		<form:radiobutton path="gated" value="true" /><b>One Group per period:</b> students in a class period participate in a single group discussion.<br/>
      		<form:radiobutton path="gated" value="true" /><b>Multiple Groups per period:</b> students in a class period work in smaller discussion groups.<br/>
      	</div>	
      		<div style="color:#FF0000;" id="subgroupSelectionBox">
      			<div style="margin:0px;font-size:.8em;font-weight:bold;">Number of groups in each class period: 
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
      			<div style="color:#FF0000;" style="color:#3333FF;" class="authorOptionsBlock">
      			Need Table Here listing "Group 1" "Group 2" etc up to the number of selected groups.  <br/>
      			The Author should be able to customize these default group names if desired.</div>
      			
      			<div id="subgroupNotice1">
      				Note: as students register for the project run they will be randomly assigned to discussion groups.  
      				The classroom teacher can manually adjust these grouping assignments before (or during) the project run.
      			</div>
      		</div>

<div class="authorSectionHeader">Content for Q&amp;A Step</div>

  <div>
      <div class="authorSectionHeader2">Question</div>
      <div class="authorOptionsBlock">Type the question you want students to see in the box below.  Use HTML formatting to control the look and feel.</div>
      <div style="margin:0 0 0 25px;">
      	<form:textarea  rows="10" cols="110" path="question.prompt" id="promptTextArea" ></form:textarea>
      	<form:errors path="question.prompt" />
      </div>	
   </div>

<div id="responseButtons">
	<input type="submit" name="save" value="Save" />
	<input type="reset" onclick="javascript:window.close()" name="cancel" value="Cancel" />
</div>

<br/>
<button id="updatePromptPreviewButton" onclick="javascript:updatePromptPreview()" >Update preview</button>

    <div class="authorSectionHeader2">Question Preview </div>
    <div class="authorOptionsBlock">Your question will appear to students as follows:</div>
	<div id="promptPreview">${brainstorm.question.prompt}</div>

	<br/>

	<div style="color:#FF0000;" class="authorSectionHeader2">Starter Question</div>
	<div style="color:#FF0000;" class="authorOptionsBlock2" style="font-size:.7em;">If you want to provide a Starter Sentence (a prompt) to the student type it below:</div>
	<div style="margin:0 0 0 25px;"><textarea rows="2" cols="110" ></textarea></div>
    
    <div style="color:#FF0000;" id="subgroupBox2">
    	<div><b>Starter Sentence Style:</b></div>
    	<div id="subgroupBox2bullets">
    		<form:radiobutton path="gated" value="true" /><b>Link:</b> A link called "Show me a Starter Sentence" is displayed to student.  When the link is clicked the starter sentence appears in the Response Field.<br/>
      		<form:radiobutton path="gated" value="true" /><b>Auto:</b> The starter sentence is automatically displayed in the Response Box (student can edit/overwrite this starter sentence).<br/>
    	</div>
    </div>
    
    <br/>
    <div style="color:#FF0000;" class="authorSectionHeader2">Response Box Size</div>
	<div style="color:#FF0000;" class="authorOptionsBlock">Size of student response box: 
      					<span style="color:#3333FF;">
      					<select name="responseboxsize" id="responseboxsize=" tabindex="1">
      					<option value="3">3 rows</option>
      					<option selected="selected" value="6">6 rows</option>
      					<option value="9">9 rows</option>
      					<option value="12">12 rows</option>
      					</select>
      					</span>
      </div>

	<br/>
	<div style="color:#FF0000;" class="authorSectionHeader2">Final Teacher Response</div>
	<div style="color:#FF0000;" class="authorOptionsBlock2">The teacher can post this pre-written final answer at the conclusion of the Q&amp;A discussion.</div>
	<table id="prewrittenResponsesTable">
		<tr>
			<td><div style="margin:0 0 0 25px;"><textarea rows="3" cols="90" ></textarea></div><td>
			<td>
				<ul>
					<li><a href="#">Insert an Image</a><li>
				</ul>
			</td>
		</tr>
	</table>
    
    <br/>
  	<div style="color:#FF0000;" class="authorSectionHeader2">Sample Student Response 1</div>
	<div style="color:#FF0000;" class="authorOptionsBlock2">The teacher can post this sample student response at any time during the Q&amp;A discussion.</div>
	<table id="prewrittenResponsesTable">
		<tr>
			<td><div style="margin:0 0 0 25px;"><textarea rows="3" cols="90" ></textarea></div><td>
			<td>
				<ul>
					<li><a href="#">Insert an Image</a><li>
					<li><a href="#">Delete this student response</a></li>
				</ul>
			</td>
		</tr>
	</table>
		
	<br/>
	<div class="authorOptionsBlock2"><input type="submit" name="save" value="Create Another Sample Student Response" /></div>
	
</div>    <!-- end of centered div-->

</form:form>

</body>
</html>