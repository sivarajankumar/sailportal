<%@ include file="../../include.jsp"%>
<html>
<head>
<script type="text/javascript">
function updatePromptPreview() {
   var promptPreviewElement = this.document.getElementById("promptPreview");
   var promptTextAreaElement = this.document.getElementById("promptTextArea");
   promptPreviewElement.innerHTML = promptTextAreaElement.value;
}
</script>
</head>
<body>

<c:if test="${brainstorm.run != null}">
<h1>Warning!!! This Brainstorm is currently associated with a run. Any changes you make will be visible to the students.</h1>
</c:if>

<h2>Edit Section</h2>
<form:form method="post" action="authorbrainstorm.html?brainstormId=${brainstorm.id}" commandName="brainstorm" id="brainstormform" >
  <div>
      <h4>Prompt</h4>
      <form:textarea rows="20" cols="60" path="question.prompt" id="promptTextArea" ></form:textarea>
      <form:errors path="question.prompt" />
      <h4>Gating Options</h4>
      <i>STUDENT RESPONSES</i><br/>
      <form:radiobutton path="gated" value="true" /><b>Gated:</b> the student must submit a Response before seeing Responses from other students.<br/>
      <form:radiobutton path="gated" value="false" /><b>Open:</b> the student can see Responses from other students immediately.<br/>
      <h4>Display Name Options</h4>
      <c:forEach items="${displayNameOptions}" var="displayNameOption">
      <form:radiobutton path="displayNameOption" value="${displayNameOption}" />${displayNameOption}<br />      
      </c:forEach>
  </div>

<div id="responseButtons">
	<input type="submit" name="save" value="Save" />
	<input type="reset" onclick="javascript:window.close()" name="cancel" value="Cancel" />
</div>

</form:form>

<br/>
<button id="updatePromptPreviewButton" onclick="javascript:updatePromptPreview()" >Update preview</button>

<h2>Preview Section</h2>
<div id="promptPreview">
${brainstorm.question.prompt}
</div>


</body>
</html>