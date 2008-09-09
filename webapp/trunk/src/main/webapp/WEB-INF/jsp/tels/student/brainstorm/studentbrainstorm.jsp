<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<%@page
	import="org.telscenter.sail.webapp.domain.grading.GradeWorkAggregate"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
</head>
<body>
<p>
brainstorm: ${brainstorm}
<br/></p>

<p>
brainstorm question: ${brainstorm.question.prompt}
<br/></p>

<p>
brainstorm answers: ${brainstorm.answers}
<br/></p>

<c:foreach var="answer" items="${brainstorm.answers}">
<p>
brainstorm comments: ${answer.comments}
</p>
<p>
brainstorm revisions: ${answer.revisions}
</p>



</c:foreach>
</body>
</html>
