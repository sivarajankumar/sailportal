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
<%@page
	import="org.telscenter.sail.webapp.domain.grading.GradeWorkAggregate"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link href="<spring:theme code="registerstylesheet" />" media="screen"
	rel="stylesheet" type="text/css" />
	
	<script type="text/javascript">
<!--

function doSubmit(bob ) {
alert(bob);
}

-->
</script>
	
</head>
<body>

<h3 align="center">Grading tool</h3>

<div style="align: left;"><c:forEach var="entry" items="${stepAggregate.sessionBundles}">
<h4>${step.title}</h2>
	<table width="853" border="1">
		<tr>
			<td width="492">
			
			<div align="center"><strong> Group:<c:forEach var="user" varStatus="userStatus"
				items="${entry.key.members}">
			 		${user.userDetails.username}
			 		   <c:if test="${userStatus.last=='false'}">
     					&
    				</c:if>
			 	</c:forEach> </strong></div>
			</td>
			<td width="268">
			<div align="center"><strong>Teacher Feedback</strong></div>
			</td>

			<td width="71">
			<div align="center"><strong>Score</strong></div>
			</td>
		</tr>

		<c:forEach var="someRim" items="${step.rim}">

			<tr>
				<td>Prompt: ${someRim.prompt}</td>
				<td>&nbsp;</td>
				<td rowspan="2">&nbsp;</td>

			</tr>
			<tr>
				<c:forEach var="sockPart" items="${entry.value.sockParts}">
					<c:if test="${sockPart.rimName == someRim.rimname}">

						<td><c:forEach var="sockEntry" items="${sockPart.sockEntries}">
		  			 	${sockEntry.value}
		  			 	</c:forEach></td>

					</c:if>
				</c:forEach>
				<td>
				<p id="example" class="editable">
				<div id="comment1" height="100px"><textarea name="comment" id="comment" value="" cols="45" rows="15">${stepAggregate.annotationBundles[entry.key].bundle}
				</textarea></div>
				
				  <span id="pushbutton5" class="yui-button yui-push-button"><em class="first-child">
				  <button type="input" name="button5" onclick="javascript:doSubmit('${entry.key}');">Save</button></em></span>
            </div>
				</p>
				</td>
			</tr>
		</c:forEach>

	</table>
</c:forEach></div>


</body>
</html>
