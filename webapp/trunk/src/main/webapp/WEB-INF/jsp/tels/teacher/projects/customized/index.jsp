<%@ include file="../../include.jsp"%>
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

<!-- $Id: projectlibrary.jsp 1850 2008-04-01 01:22:32Z mattf $ -->

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="en">
<head>

<%@ include file="../styles.jsp"%>

<!-- Core + Skin CSS --> 
<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.5.1/build/menu/assets/skins/sam/menu.css"> 
	 
<!-- Dependencies -->  
<script type="text/javascript" src="http://yui.yahooapis.com/2.5.1/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
<script type="text/javascript" src="http://yui.yahooapis.com/2.5.1/build/container/container_core-min.js"></script> 
 
<!-- Source File --> 
<script type="text/javascript" src="http://yui.yahooapis.com/2.5.1/build/menu/menu-min.js"></script> 

<link href="../../../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../../<spring:theme code="teacherprojectstylesheet" />" media="screen" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../../.././javascript/tels/yui/general.js"></script>
<script type="text/javascript" src="../../.././javascript/tels/yui/menubar.js"></script>

<title>My Customized / Shared Projects</title>
</head>

<body class="yui-skin-sam"> 

<div id="centeredDiv">

<%@ include file="headerteacherprojects.jsp"%>

<%@ include file="L2projects_projectlibrary.jsp"%>

<h2 id="titleBar" class="headerText">My Customized / Shared Projects</h2> 

<!--<div id="projectInfoInstructions">Click any tab below for more information.</div>-->

<div id="projectInfoTabs" class="yui-navset">
    <ul class="yui-nav" >
        <li style="margin-left:4px;"><a href="#tab1"><em>current customized projects</em></a></li>
        <li style="margin-left:4px;"><a href="#tab2"><em>projects shared with me</em></a></li>
        <li style="margin-left:4px;"><a href="#tab3"><em>archived projects</em></a></li>
        
    </ul>     
           
    <div class="yui-content" style="background-color:#FFFFFF;">
    
        <div id="tab1">
      
          <table id="customProjectTable" border="1" cellpadding="0" cellspacing="0" >
				    <tr>
				        <th style="width:250px;" >project title</th>
				        <th>project id</th>
				        <th>created on</th>
						<th>project source</th>
						<th>subjects/keywords</th>
						<th>grade range</th>
						<th>total</br>time</th>
						<th>computer</br>time</th>
						<th>usage</th>    
				       <th style="width:200px;">ACTIONS</th>
				    </tr>
				  
				  <tr id="customProjectR2">
				    <td class="customProjectTitle">[Project Title here]</td>
				    <td>[Created On Date here]</td>
				    <td>[Project ID here]</td>
				    <td>[Project Source here]</td>
				    <td class="smallText">[Subjects/Keywords here]</td>
				    <td class="smallText">Grade Range here]</td>
				    <td class="smallText">[Total Time here ID here]</td>
				    <td class="smallText">[Computer Time here]</td>
				    <td class="smallText">[Usage Time here]</td>
				    <td style="vertical-align:top; padding:1px 0;">
					    <ul id="actionList">
					    	<li><a style="color:#cccccc;" href="#">View Project Info</a></li>
					    	<li><a style="color:#cccccc;" href="#">Edit Periods</a></li>
					    	<li><a href="../grading/gradebystep.html?runId=${run.id}">Grade by Step</a></li>
						    <li><a href="../grading/selectworkgroup.html?runId=${run.id}">Grade by Team</a></li>				    	
					    	<li><a href="../grading/currentscore.html?runId=${run.id}" id="studentScoreSummary">Student Score Summary</a></li>
					    	<li><a style="color:#cccccc;" href="#">Teacher Grading Progress</a></li>
					    	<li><a style="color:#cccccc;" href="#">Send Msg to Student(s)</a></li>
					    	<li><a href="../../contactwiseproject.html?projectId=${run.project.id}">Report a Problem</a></li>
					    	<li><a href="#" onclick="javascript:popup('manage/archiveRun.html?runId=${run.id}')">Archive this Run</a></li>
					    </ul></td>
				   </tr>
			
				</table>

<br/><br/>    

<div id="productsandservices" class="yuimenubar yuimenubarnav">
    <div class="bd">
        <ul class="first-of-type">
            <li class="yuimenubaritem first-of-type">
                <a class="yuimenubaritemlabel" href="#communication">Communication</a>
            </li>
            <li class="yuimenubaritem">
                <a class="yuimenubaritemlabel" href="http://shopping.yahoo.com">Shopping</a>
            </li>
            <li class="yuimenubaritem">
                <a class="yuimenubaritemlabel" href="http://entertainment.yahoo.com">Entertainment</a>
            </li>
            <li class="yuimenubaritem">
                <a class="yuimenubaritemlabel" href="#">Information</a>
            </li>
        </ul>
    </div>
</div>





  	  
	    </div>       <!--	    End of Tab 1 content-->
        
        <div id="tab2">
            <h5>Projects shared with Current User appear here.</h5>
            <p> </p>
        </div>
        
        <div id="tab3">
            <h5>Archived versions of customizes projects go here.</h5>
            <p> </p>
        </div>
        
     
    </div>
</div>
  
	
</div>

<script type="text/javascript">
    var tabView = new YAHOO.widget.TabView('projectInfoTabs');
    tabView.set('activeIndex', 0);
</script>

</body>
</html>
