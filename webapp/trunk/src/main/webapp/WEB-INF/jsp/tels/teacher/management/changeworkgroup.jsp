<%@ include file="include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link href="<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"
    type="text/css" />
    
<script type="text/javascript" src="./javascript/tels/rotator.js"></script>
    
<title><spring:message code="application.title" /></title>
</head>

<body>

<h2><spring:message code="changeworkgroup.menu" /></h2>

<!-- Support for Spring errors object -->
<spring:bind path="changeWorkgroupParameters.*">
  <c:forEach var="error" items="${status.errorMessages}">
    <b>
      <br /><c:out value="${error}"/>
    </b>
  </c:forEach>
</spring:bind>


<h1><spring:message code="changeworkgroup.message" /></h1>

<form:form method="post" action="changeworkgroup.html" commandName="changeWorkgroupParameters" id="changeWorkgroups" >
       <form:select path="workgroupTo" id="workgroupTo">       
          <c:forEach items="${workgroupsTo}" var="workgroupTo">
            <form:option value="${workgroupTo}">
               ${workgroupTo.sdsWorkgroup.name}
            </form:option>
          </c:forEach>
 <!--         <form:option value="${null}">
             New Workgroup
          </form:option>   --> 
        </form:select> 
      <input type="image" id="save" src="../<spring:theme code="register_save" />" 
    onmouseover="swapSaveImage('save',1)" 
    onmouseout="swapSaveImage('save',0)"
    /></form:form>
</body>
</html>