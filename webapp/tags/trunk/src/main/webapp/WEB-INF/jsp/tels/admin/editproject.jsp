<%@ include file="../include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />


    
<script type="text/javascript" src="../../javascript/tels/general.js"></script>
<script type="text/javascript" src="../../javascript/tels/effects.js"></script>
<script type="text/javascript" src=".././javascript/tels/general.js"></script>
    
<title>Edit Project Page</title>

<script type='text/javascript' src='/webapp/dwr/engine.js'></script>
</head>
<body>
<%@ include file="adminheader.jsp"%>


<form:form method="post" action="editproject.html" 
	commandName="projectInfoParameters" id="editproject">
    <br/><br/>
	<b>Project Name:</b> ${project.curnit.sdsCurnit.name }<br/>
	<b>Projecd Id:</b> ${project.id}<br/><br/>
	<label for="iscurrentlabel">Is this project current or not current? (Current projects are eligible for creating new classroom runs)</label><br/>
    <form:select path="current" id="currentselect">           
            <form:option value="true">current</form:option>
            <form:option value="false">not current</form:option>
    </form:select>
    <br/><br/>

	<label for="familytaglabel">Familytag (what type of project is this?)</label><br/>
	    <form:select path="familytag" id="familytagselect">           
    		<c:forEach items="${familytags}" var="familytag">
            <form:option value="${familytag}">${familytag}</form:option>
          </c:forEach>
        </form:select>

    <br/><br/>
    <input type="submit" value="Submit" />
    <a href="manageallprojects.html"><input type="button" value="Cancel"></input></a>
</form:form>
    <p>Note: If the project is current and is a TELS project, it will show up in the Instant Preview page and the
       Project Library page. Otherwise, it will not show up in either of those pages. This might change at a later time as more types of projects are defined in the portal.</p>


</body>
</html>