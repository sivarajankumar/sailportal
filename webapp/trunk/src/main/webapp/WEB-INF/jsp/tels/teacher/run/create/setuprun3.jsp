<%@ include file="include.jsp"%>
<!--
  * Copyright (c) 2006 Encore Research Group, University of Toronto
  * 
  * This library is free software; you can redistribute it and/or
  * modify it under the terms of the GNU Lesser General Public
  * License as published by the Free Software Foundation; either
  * version 2.1 of the License, or (at your option) any later version.
  *
  * This library is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
  * Lesser General Public License for more details.
  *
  * You should have received a copy of the GNU Lesser General Public
  * License along with this library; if not, write to the Free Software
  * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
-->

<!-- $Id: setupRun3.jsp 357 2007-05-03 00:49:48Z archana $ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link href="<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"
  type="text/css" />
<script src="./javascript/tels/prototype.js" type="text/javascript" ></script>
<script src="./javascript/tels/scriptaculous.js" type="text/javascript" ></script>
<script type="text/javascript">
function checkIfTextAreaEmpty (form) {
if(form.manualCheckbox.checked==true){
	form.manualPeriods.disabled=false;
	for(i=0;i<form.options.length;i++){
	   form.options[i].disabled=true;
	   form.options[i].checked=false;
	}
}else{
	form.manualPeriods.disabled=true;
	for(i=0;i<form.options.length;i++){
	   form.options[i].disabled=false;
	}	
}
}
// End -->
</script>

<script src="./javascript/tels/general.js" type="text/javascript" ></script>
<script src="./javascript/tels/effects.js" type="text/javascript" ></script>
<script src="./javascript/tels/prototype.js" type="text/javascript" ></script>
<script src="./javascript/tels/scriptaculous.js" type="text/javascript" ></script>

<title><spring:message code="teacher.setup-project-run-step-three" /></title>

</head>

<!-- Support for Spring errors object -->
<spring:bind path="runParameters.periodNames">
  <c:forEach var="error" items="${status.errorMessages}">
    <c:choose>
      <c:when test="${fn:length(error) > 0}" >
        <script type="text/javascript">
          <!--
            alert("${error}");
          //-->
        </script>
      </c:when>
    </c:choose>
  </c:forEach>
</spring:bind>

<body>

<div id="centeredDiv">

<%@ include file="../../../headerteacherprojects.jsp"%>

<%@ include file="../../../L2projectsnohighlight.jsp"%>

<div id="titleBarSetUpRun">
    	<h1 class="blueText"><spring:message code="teacher.setup-project-classroom-run" /></h1></div>


<div id="setUpRunBox">

<div id="stepNumber">Step 3 of 6:<span class="blueText">&nbsp Selection Period(s)</span></div>

<h3 style="color:rgb(0,0,255);"><b style="color:#000000;"><spring:message code="teacher.setup-project-run-step3" /> </b>
<spring:message code="teacher.setup-project-run-step3-desc" />
</h3>
<p style="width:950px;font-size:1em;">
<spring:message code="teacher.select-classroom-periods" />
<i><spring:message code="navigate.next" /></i>
</p>

<div>
<form:form method="post" commandName="runParameters">

    <div style="width:950px;">
      <label for="periods"><spring:message code="periods" /></label><br />
          <c:forEach items="${periodNames}" var="periodName">
            <form:checkbox path="periodNames" value="${periodName}" />
            <spring:message code="defaultPeriodNames.${periodName}" /><br/>
          </c:forEach>      
    </div>


<div align="center">
<input type="submit" name="_target1" value="<spring:message code="navigate.back"/>" />
<input type="submit" name="_cancel" value="<spring:message code="navigate.cancel"/>" />
<input type="submit" name="_target3" value="<spring:message code="navigate.next"/>" />
</div>
</form:form>
</div>


</div>
</div>

</body>
</html>
