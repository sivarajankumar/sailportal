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
<link rel="stylesheet" type="text/css" href="../.././javascript/tels/yui/reset/reset-min.css"> 
<link rel="stylesheet" type="text/css" href="../.././javascript/tels/yui/base/base-min.css"> 
 
<!--CSS for Controls:--> 
<link rel="stylesheet" type="text/css" href="../.././javascript/tels/yui/container/assets/skins/sam/container.css"> 
<link rel="stylesheet" type="text/css" href="../.././javascript/tels/yui/button/assets/skins/sam/button.css"> 
<link rel="stylesheet" type="text/css" href="../.././javascript/tels/yui/logger/assets/skins/sam/logger.css"> 
<link rel="stylesheet" type="text/css" href="../.././javascript/tels/yui/tabview/assets/skins/sam/tabview.css"> 
 
 
<!--JavaScript source files for the entire YUI Library:--> 
 
<!--Utilities (also aggregated in yahoo-dom-event.js and utilities.js; see readmes in the 
YUI download for details on each of the aggregate files and their contents):--> 
<script type="text/javascript" src="../.././javascript/tels/yui/yahoo/yahoo-min.js"></script> 
<script type="text/javascript" src="../.././javascript/tels/yui/dom/dom-min.js"></script> 
<script type="text/javascript" src="../.././javascript/tels/yui/event/event-min.js"></script> 
<script type="text/javascript" src="../.././javascript/tels/yui/element/element-beta-min.js"></script> 
<script type="text/javascript" src="../.././javascript/tels/yui/animation/animation-min.js"></script> 
<script type="text/javascript" src="../.././javascript/tels/yui/connection/connection-min.js"></script> 
 
<!--YUI's UI Controls:--> 
<script type="text/javascript" src="../.././javascript/tels/yui/ontainer/container-min.js"></script> 
<script src="../.././javascript/tels/yui/button/button-beta-min.js" type="text/javascript" />  
<script type="text/javascript" src="../.././javascript/tels/yui/logger/logger-min.js"></script> 
<script type="text/javascript" src="../.././javascript/tels/yui/tabview/tabview-min.js"></script> 
 
	<script type="text/javascript">
<!--

function doSubmit(bob ) {
alert(bob);
}
 

    var tabView = new YAHOO.widget.TabView('periodTabs'); 
-->
</script>
<script type="text/javascript">
<!--

 
 YAHOO.util.Event.onContentReady("pushbuttonsfrommarkup", function () {

//   var oPushButton5 = new YAHOO.widget.Button("pushbutton_${period.key}", { onclick: { fn: onButtonClick } });
        
  function onButtonClick(p_oEvent) {
			alert('herye');
            YAHOO.log("You clicked button: " + this.get("id"), "info", "example1");
        
   }
-->
</script>



</head>
<body id="yahoo-com" class=" yui-skin-sam">


<h3 align="center">Grading tool</h3>

<div style="align: left;"><c:forEach var="entry" items="${stepAggregate.sessionBundles}">
<h2>${step.title}</h2>

<div id="periodTabs" class="yui-navset"> 

<!-- create the tabs -->
		<ul class="yui-nav"> 
			<c:forEach var="period" varStatus="periodStat" items="${periods}">
			
			<c:choose>
				<c:when test="${periodStat.first=='true'}">
					<li class="selected">
			 	</c:when>
			    <c:otherwise>
			    <li>
			    </c:otherwise>
			      
			 </c:choose>
				 <li><a href="${period.key}"><em>${period.key}</em></a></li> 
			 </c:forEach> 
		 </ul>   
		 
		 
<!-- create the tab content -->

<div class="yui-content"> 

<c:forEach var="period" varStatus="periodStat" items="${periods}">
<div id="${period.key}">
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
				<c:forEach var="sockPart" items="${entry.value.ESessionBundle.sockParts}">
					<c:if test="${sockPart.rimName == someRim.rimname}">

						<td><c:forEach var="sockEntry" items="${sockPart.sockEntries}">
		  			 	Rim Name: ${someRim.rimname} + ${sockEntry.value}
		  			 	</c:forEach></td>

					</c:if>
				</c:forEach>
				<td>
				<p id="example" class="editable">
				<!--  create the annotation text boc -->
				<c:forEach var="annotationGroup" items="${stepAggregate.annotationBundles[entry.key].EAnnotationBundle.annotationGroups}">
				<c:forEach var="annotation" items="${annotationGroup.annotations}">
					<c:if test="${annotation.entityName == someRim.rimname}">
					
						<c:set var="foundAnnotation" value="${annotation}"/>
					
					</c:if>
					</c:forEach>
				</c:forEach>
				<div id="comment1" ><textarea name="comment" id="${foundAnnotation.entityUUID}" cols="35" rows="7">
					${foundAnnotation.entityUUID}:    ${fn:trim(foundAnnotation.contents)}
				</textarea>
				 <fieldset id="pushbuttonsfrommarkup">
				${foundAnnotation.entityUUID}:<span id="pushbutton_${period.key}" class="yui-button yui-push-button"><em class="first-child"><button type="submit" name="button5" onClick="javascript:doSubmit(${annotation.entityUUID},)">Save Comment</button></em></span>
				</fieldset>
				</div>
				</td>
			</tr>
		</c:forEach>

	</table>
	</div>
</c:forEach></div>
<!-- end period iterator -->
</c:forEach> 
</div> 
</body>
</html>
