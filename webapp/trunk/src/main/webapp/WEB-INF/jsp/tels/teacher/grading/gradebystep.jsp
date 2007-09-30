<%@ include file="include.jsp"%>
<!-- 
 * Copyright (c) 2007 Regents of the University of California (Regents). Created
 * by TELS, Graduate School of Education, University of California at Berkeley.
 *
 * This software is distributed under the GNU Lesser General Public License, v2.
 *
 * Permission is hereby granted, without written agreement and without license
 * or royalty fees, to use, copy, modify, and distribute this software and its
 * documentation for any purpose, provided that the above copyright notice and
 * the following two paragraphs appear in all copies of this software.
 *
 * REGENTS SPECIFICALLY DISCLAIMS ANY WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE. THE SOFTWAREAND ACCOMPANYING DOCUMENTATION, IF ANY, PROVIDED
 * HEREUNDER IS PROVIDED "AS IS". REGENTS HAS NO OBLIGATION TO PROVIDE
 * MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
 *
 * IN NO EVENT SHALL REGENTS BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
 * SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
 * ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
 * REGENTS HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
-->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />  
<link href="../../<spring:theme code="teachergradingstylesheet"/>" media="screen" rel="stylesheet"
  type="text/css" />
  <script type="text/javascript" src="../javascript/general.js"></script> 
</head>

<body>

<%@ include file="gradingtoolHeader.jsp"%>

<h4>Grade By Step</h4>


<div style="align:left;">
${curnitMap.project.title}
   <c:forEach var="someAct" varStatus="varAct" items="${curnitMap.project.activity}">
		<h3>Activity <c:out value="${varStep.count}" />: ${someAct.title}</h3>  
		<ul> 
			<c:forEach var="someStep" varStatus="varStep" items="${someAct.step}">
				<c:if test="${someStep.type == 'Note'}"><li><a href="gradingtool.html?GRADE_TYPE=step&runId=${runId}&podUUID=${someStep.podUUID}">Step  : ${someStep.title}</a>    (${someStep.type})</li></c:if>
				<c:if test="${someStep.type == 'Student Assessment'}"><li><a href="gradingtool.html?GRADE_TYPE=step&runId=${runId}&podUUID=${someStep.podUUID}">Step  : ${someStep.title}</a></li></c:if>
			</c:forEach>
		</ul>
    </c:forEach>
</div>


</body>
</html>
