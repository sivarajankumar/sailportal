<%@ include file="include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>

<%@ include file="yahooUIStyles.jsp"%>

<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<title><spring:message code="run.list" /></title>

<script type="text/javascript" src="../.././javascript/tels/general.js"></script>

<script language="JavaScript">
	function popup(URL, title) 
  	{window.open(URL, title, 'toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=1,width=640,height=480,left = 320,top = 240');}
</script>

<script type="text/javascript">

	if(navigator.appName != "Microsoft Internet Explorer") {
		loadingImage = new Image();
		loadingImage.src = "/webapp/themes/tels/default/images/rel_interstitial_loading.gif";
	}
	
    YAHOO.namespace("example.container");

    function init() {

        if (!YAHOO.example.container.wait) {

            // Initialize the temporary Panel to display while waiting for external content to load

            YAHOO.example.container.wait = 
                    new YAHOO.widget.Panel("wait",  
                                                    { width: "240px", 
                                                      fixedcenter: true, 
                                                      close: false, 
                                                      draggable: false, 
                                                      zindex:4,
                                                      modal: true,
                                                      visible: false
                                                    } 
                                                );

            //YAHOO.example.container.wait.setHeader("Loading, please wait...");
            YAHOO.example.container.wait.setBody("<table><tr align='center'>Loading, please wait...</tr><tr align='center'><img src=/webapp/themes/tels/default/images/rel_interstitial_loading.gif /></tr><table>");
            YAHOO.example.container.wait.render(document.body);

        }

        // Define the callback object for Connection Manager that will set the body of our content area when the content has loaded



        var callback = {
            success : function(o) {
                //content.innerHTML = o.responseText;
                //content.style.visibility = "visible";
                YAHOO.example.container.wait.hide();
            },
            failure : function(o) {
                //content.innerHTML = o.responseText;
                //content.style.visibility = "visible";
                //content.innerHTML = "CONNECTION FAILED!";
                YAHOO.example.container.wait.hide();
            }
        }
    
        // Show the Panel
        YAHOO.example.container.wait.show();
        
        // Connect to our data source and load the data
        //var conn = YAHOO.util.Connect.asyncRequest("GET", "assets/somedata.php?r=" + new Date().getTime(), callback);
    }

    YAHOO.util.Event.on("studentScoreSummary", "click", init);
    
    
		(function() {
   		 var tabView = new YAHOO.widget.TabView('tabSystem');})();
 </script>

<link href="../../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="teacherrunstylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
</head>


<body class="yui-skin-sam">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="centeredDiv">

<%@ include file="headerteacherprojects.jsp"%>

<%@ include file="L2projects_myprojectruns.jsp"%>

<style type="text/css">
.yui-skin-sam .yui-navset .yui-nav li,
.yui-skin-sam .yui-navset .yui-navset-top .yui-nav li {
    margin:0 0.8em 0 0; /* space between tabs */
    padding:5px 0 0; /* gecko: make room for overflow */
    zoom:1;
}
</style>

<div id="tabSystem" class="yui-navset">
    <ul style="font-weight:bold; font-size:.7em; letter-spacing:1px;" class="yui-nav">
        <li style="margin:0 .4em 0 0px;" class="selected"><a href="#currentRuns"><em><spring:message code="teacher.run.myprojectruns.1A"/></em></a></li>
        <li><a href="#archivedRuns"><em><spring:message code="teacher.run.myprojectruns.1B"/></em></a></li>
    </ul>            
    <div class="yui-content">
        <div id="currentRuns">
        		<div id="subHeader"><spring:message code="teacher.run.myprojectruns.2"/></div>
        	       		
        		<div id="runBox">
				
				<table id="currentRunTable" border="1" cellpadding="0" cellspacing="0" >
				    <tr>
				       <th style="width:450px;"class="tableHeaderMain"><spring:message code="teacher.run.myprojectruns.3"/></th>
				       <th style="width:325px;" class="tableHeaderMain"><spring:message code="teacher.run.myprojectruns.4"/></th>      
				       <th style="width:200px;" class="tableHeaderMain"><spring:message code="teacher.run.myprojectruns.5"/></th>
				    </tr>
				  <c:forEach var="run" items="${current_run_list}">
				  
				  <tr id="runTitleRow">
				    <td id="titleCell">
				    	<div id="runTitle">${run.sdsOffering.name}</div>
				    	<c:forEach var="sharedowner" items="${run.sharedowners}">
				    	    <center><c:if test="${sharedowner == user}"><spring:message code="teacher.run.myprojectruns.6"/></c:if></center>
				    	</c:forEach>
				      		
				      	
					</td>
												
				    <td style="vertical-align:top; padding:0px;" >
				    	<table id="currentRunInfoTable" border="0" cellpadding="0" cellspacing="0">
				          <tr>
				            <th class="tableInnerHeader"><spring:message code="teacher.run.myprojectruns.7"/></th>
				            <th class="tableInnerHeader"><spring:message code="teacher.run.myprojectruns.8"/></th>
				            <th class="tableInnerHeaderRight"><spring:message code="teacher.run.myprojectruns.9"/></th>
				          </tr>
				          <c:forEach var="period" items="${run.periods}">
				            <tr>
				              <td style="width:20%;" class="tableInnerData">${period.name}</td>
				              <td style="width:45%;" class="tableInnerData">${run.runcode}-${period.name}</td>
				              <td style="width:35%;" class="tableInnerDataRight">
				                <a href="../management/viewmystudents.html?runId=${run.id}&periodName=${period.name}">${fn:length(period.members)}&nbsp;<spring:message code="teacher.run.myprojectruns.10"/></a></td>
				            </tr>
				          </c:forEach>
				        </table>
				        
				        <table id="runTitleTable">
				      			<tr>
				      				<td class="runTitleTableHeader"><spring:message code="teacher.run.myprojectruns.11"/></td>
				      				<td>${run.project.id}</td>
				      			</tr>
				      			<tr>
				      				<td class="runTitleTableHeader"><spring:message code="teacher.run.myprojectruns.12"/></td>
				      				<td>UC Berkeley library project</td>
				      			</tr>
				      			<tr>
				      				<td class="runTitleTableHeader"><spring:message code="teacher.run.myprojectruns.13"/></td>
				      				<td><fmt:formatDate value="${run.starttime}" type="date" dateStyle="short" /></td>
				      			</tr>
						</table>
				     </td> 
				    <td style="vertical-align:top; padding:1px 0;">
					    <ul id="actionList">
					    	<li><a href="../projects/projectinfo.html?projectId=${run.project.id}"><spring:message code="teacher.run.myprojectruns.14"/></a></li>
					    	<li><a style="color:#cccccc;" href="#"><spring:message code="teacher.run.myprojectruns.15"/></a></li>
					    	<!-- grade-by-step disabled until it is optimized 
   	                        <li><a href="../grading/gradebystep.html?runId=${run.id}">Grade by Step</a></li>
   	                        -->
   	                        <li><a style="color:#cccccc;" href="#"><spring:message code="teacher.run.myprojectruns.16"/></a></li>
   	                        
						    <li><a href="../grading/selectworkgroup.html?runId=${run.id}"><spring:message code="teacher.run.myprojectruns.17"/></a></li>				    	
		                    <authz:accesscontrollist domainObject="${run}" hasPermission="16">
    					      <li><a href="shareprojectrun.html?runId=${run.id}"><spring:message code="teacher.run.myprojectruns.18"/></a></li> 
  	                        </authz:accesscontrollist>
  	                        
  	                        <!-- student score summary disabled until it is optimized 
					    	<li><a href="../grading/currentscore.html?runId=${run.id}" id="studentScoreSummary">Student Score Summary</a></li>
					    	-->
					    	<li><a style="color:#cccccc;" href="#"><spring:message code="teacher.run.myprojectruns.19"/></a></li>

					    	<!-- teacher grading progress disabled until it is optimized 
					    	<li><a style="color:#cccccc;" href="#">Teacher Grading Progress</a></li>
					    	-->
					    	<li><a style="color:#cccccc;" href="#"><spring:message code="teacher.run.myprojectruns.20"/></a></li>
					    	
					    	<li><a style="color:#cccccc;" href="#"><spring:message code="teacher.run.myprojectruns.21"/></a></li>
					    	<li><a href="../../contactwiseproject.html?projectId=${run.project.id}"><spring:message code="teacher.run.myprojectruns.22"/></a></li>
		                    <authz:accesscontrollist domainObject="${run}" hasPermission="16">					    	
					    	  <li><a href="#" onclick="javascript:popup('manage/archiveRun.html?runId=${run.id}')"><spring:message code="teacher.run.myprojectruns.23"/></a></li>
					    	</authz:accesscontrollist>
					    	
					    </ul>
					</td>
				   </tr>
				  </c:forEach>
				</table>
				</div>
        </div><!-- end current runs tab -->
        <div id="archivedRuns">
        <h5 style="margin:3px 0 0 0;" id="subHeader"><spring:message code="teacher.run.myprojectruns.24"/><br />
        					<spring:message code="teacher.run.myprojectruns.25"/></h5>
        		<div id="runBox">
				
				<table id="currentRunTable" border="1" cellpadding="0" cellspacing="0" >
				    <tr>
				       <th style="width:48%;" class="tableHeaderMain archive"><spring:message code="teacher.run.myprojectruns.26"/></th>
				       <th style="width:34%;" class="tableHeaderMain archive"><spring:message code="teacher.run.myprojectruns.27"/></th>      
				       <th style="width:18%;" class="tableHeaderMain archive"><spring:message code="teacher.run.myprojectruns.28"/></th>
				    </tr>
				  <c:forEach var="run" items="${ended_run_list}">
				  
				  <tr id="runTitleRow">
				    <td id="titleCell">
				    	<div id="runTitle">${run.sdsOffering.name}</div>
				      	<div id="titleSubHeader">&middot; [DATA NEEDED: project type/project id] "library project, ID 21351"&middot;</div>
				    	<div id="titleSubHeader">&middot; [DATA NEEDED: run creation date]  "created 4/12/07" &middot;</div>
				    	<div id="titleSubHeader">&middot; [DATA NEEDED: date archived] "archived 4/12/07" &middot;</div>				    	   	 
				    </td>
				    <td style="vertical-align:top; padding:0px;">
				    	<table id="currentRunInfoTable" border="0" cellpadding="0" cellspacing="0">
				          <tr>
				            <th class="tableInnerHeader"><spring:message code="teacher.run.myprojectruns.29"/></th>
				            <th class="tableInnerHeader"><spring:message code="teacher.run.myprojectruns.30"/></th>
				            <th class="tableInnerHeaderRight"><spring:message code="teacher.run.myprojectruns.31"/></th>
				          </tr>
				          <c:forEach var="period" items="${run.periods}">
				            <tr>
				              <td style="width:20%;" class="tableInnerData">${period.name}</td>
				              <td style="width:45%;" class="tableInnerData">${run.runcode}-${period.name}</td>
				              <td style="width:35%;" class="tableInnerDataRight">
				                ${fn:length(period.members)} <spring:message code="teacher.run.myprojectruns.32"/>
				                
				              </td>
				            </tr>
				          </c:forEach>
				        </table>
				    </td>
				    <td style="vertical-align:top; padding:1px 0;">
					    <ul id="actionList">
					    	<li><a style="color:#cccccc;" href="#"><spring:message code="teacher.run.myprojectruns.33"/></a></li>
					    	<li><a href="#" onclick="javascript:popup('manage/startRun.html?runId=${run.id}')"><spring:message code="teacher.run.myprojectruns.34"/></a></li>
					    	<li><a style="color:#cccccc;" href="#"><spring:message code="teacher.run.myprojectruns.35"/></a></li>
					    	<li><a style="color:#cccccc;" href="#"><spring:message code="teacher.run.myprojectruns.36"/></a></li>
					    	<li><a style="color:#cccccc;" href="#"><spring:message code="teacher.run.myprojectruns.37"/></a></li>
					    </ul>
					</td>
				   </tr>
				  </c:forEach>
				</table>
				</div>




</body>
</html>

