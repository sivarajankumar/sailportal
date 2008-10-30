<%@ include file="include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>

<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<title><spring:message code="run.list" /></title>
</head>
<body>

${fn:length(brainstorm.workgroupsThatRequestHelp)} students requested for help on this brainstorm:
<br />
students that requested help: <br/>
<c:forEach var='wg' items='${brainstorm.workgroupsThatRequestHelp}'>
  ${wg.sdsWorkgroup.name} <br/>
</c:forEach>
</body>
</html>


