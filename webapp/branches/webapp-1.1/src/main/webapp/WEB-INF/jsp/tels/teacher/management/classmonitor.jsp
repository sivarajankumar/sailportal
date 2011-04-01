<%@ include file="include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">

<link href="../../<spring:theme code="yui-fonts-min-stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="yui-container-stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />


<title>Student Progress Monitor</title>

<head>
<%@ include file="../grading/styles.jsp"%>
<script type="text/javascript" src="../.././javascript/tels/yui/yahoo/yahoo.js"></script>
<script type="text/javascript" src="../.././javascript/tels/yui/event/event.js"></script>  
<script type="text/javascript" src="../.././javascript/tels/yui/connection/connection.js"></script> 
<script type="text/javascript" src="../.././javascript/tels/general.js"></script>
<script type="text/javascript" src="../.././javascript/tels/utils.js"></script>
<script type="text/javascript" src="../.././javascript/tels/prototype.js"></script>
<script type="text/javascript" src="../.././javascript/tels/jsProgressBarHandler.js"></script>
<script type="text/javascript" src="../.././javascript/tels/teacher/management/viewmystudents.js"></script>

<script type="text/javascript">
	
	function popup(URL) {
  		window.open(URL, 'ProgressMonitorMeanings', 'toolbar=0,scrollbars=1,location=0,statusbar=0,menubar=0,resizable=1,width=400,height=320,left = 450,top = 150');
  	}

	var activeIndex = ${tab};
	// this is for the tabView
    var tabView = new YAHOO.widget.TabView('tabSystem');
	tabView.set('activeIndex', ${tab});		        
	tabView.addListener('activeTabChange', handleTabClick);
  
	function handleTabClick(e) {
		document.getElementById('refreshProgress').href = 'classmonitor.html?runId=${run.id}&tab=' + tabView.getTabIndex(e.newValue);
		activeIndex = tabView.getTabIndex(e.newValue);
	};  
  
    //YAHOO.util.Event.onDOMReady(initLoading);

    YAHOO.namespace("example.container");

    function initLoading() {

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
    
            YAHOO.example.container.wait.setHeader("Loading. Please wait...");
            YAHOO.example.container.wait.setBody("<img src=/webapp/themes/tels/default/images/rel_interstitial_loading.gif />");
            YAHOO.example.container.wait.render(document.body);

        }

        // Define the callback object for Connection Manager that will set the body of our content area when the content has loaded
        var callback = {
            success : function(o) {
                YAHOO.example.container.wait.hide();
            },
            failure : function(o) {
                YAHOO.example.container.wait.hide();
            }
        }
    
        // Show the Panel
        YAHOO.example.container.wait.show();
        
    }
    
    var totalGroups = [];
    var medLocPercent = [];
    var medStep = [];
	var medRawActual = [];
	var medRawPossible = [];
	var medTeacherActual = [];
	var medTeacherPossible = [];
	var medAutoActual = [];
	var medAutoPossible = [];
	for(x=0;x<'${run.periods}'.length;x++){
		totalGroups[x] = 0;
		medLocPercent[x] = [];
		medStep[x] = []
		medRawActual[x] = [];
		medRawPossible[x] = [];
		medTeacherActual[x] = [];
		medTeacherPossible[x] = [];
		medAutoActual[x] = [];
		medAutoPossible[x] = [];
	};   
    
    /**
     * ProgressCellInfo Object for making asychronous GET requests
     * to fill in data for progress cells
     **/
     function ProgressCellInfo(runId, workgroupId, tabIndex){
     	this.runId = runId;
     	this.workgroupId = workgroupId;
     	this.tabIndex = tabIndex;
     	
		this.callback = {
			customevents:{
			onComplete:this.complete
			},
			success:this.handleSuccess,
			failure:this.handleFailure,
			scope:this
		};
     };
     
     ProgressCellInfo.prototype.startRequest = function(){
		YAHOO.util.Connect.asyncRequest('GET', 'progresscellinfo.html?runId=' + this.runId + '&workgroupId=' + this.workgroupId, this.callback);	     
     };
     
     ProgressCellInfo.prototype.complete = function(eventType, args){
		requestManager.finished(this);
	};
	
	ProgressCellInfo.prototype.handleSuccess = function(o){
		var xmlDoc = o.responseXML;
		if(xmlDoc == null){
			this.handleFailure;
		};
		var entries = xmlDoc.getElementsByTagName('entry');
		var currentStep = xmlDoc.getElementsByTagName('currentstep');
		var percentComplete = xmlDoc.getElementsByTagName('percentcomplete');
		var skipped = xmlDoc.getElementsByTagName('skipped');
		var skippedActivity = xmlDoc.getElementsByTagName('skippedactivity');
		var rawActual = xmlDoc.getElementsByTagName('rawactual');
		var rawPossible = xmlDoc.getElementsByTagName('rawpossible');
		var teacherActual = xmlDoc.getElementsByTagName('teacheractual');
		var teacherPossible = xmlDoc.getElementsByTagName('teacherpossible');
		var autoActual = xmlDoc.getElementsByTagName('autoactual');
		var autoPossible = xmlDoc.getElementsByTagName('autopossible');
		
		//update elements for this cell
		var stepactivitynums = getStepAndActivityNumbers(currentStep[0].childNodes[0].nodeValue, entries);
		document.getElementById('progress_' + this.workgroupId).innerHTML = '';
		document.getElementById('activityStep_' + this.workgroupId).innerHTML = 'Activity: ' + stepactivitynums[0] + ', Step: ' + stepactivitynums[1];
		document.getElementById('percentComplete_' + this.workgroupId).innerHTML = percentComplete[0].childNodes[0].nodeValue + '% of steps';
		myJsProgressBarHandler.setPercentage('progressBar_' + this.workgroupId, '+' +percentComplete[0].childNodes[0].nodeValue);
		
		var flagged = "<ul>";
		for(x=0;x<skipped.length;x++){
			flagged = flagged + "<li>A" + skipped[x].childNodes[0].childNodes[0].nodeValue + ", Step " + skipped[x].childNodes[1].childNodes[0].nodeValue + " (skipped)</li>"; 
		};
		for(x=0;x<skippedActivity.length;x++){
			flagged = flagged + "<li>Activity " + skippedActivity[x].childNodes[0].nodeValue + " (all skipped)</li>";
		};
		flagged = flagged + "</ul>";
		document.getElementById('flagged_' + this.workgroupId).innerHTML = flagged;
		document.getElementById('rawPercent_' + this.workgroupId).innerHTML = getPercentage(rawActual[0].childNodes[0].nodeValue, rawPossible[0].childNodes[0].nodeValue) + '%';
		document.getElementById('rawScore_' + this.workgroupId).innerHTML = '(' + rawActual[0].childNodes[0].nodeValue + '/' + rawPossible[0].childNodes[0].nodeValue + ')';
		document.getElementById('teacherPercent_' + this.workgroupId).innerHTML = getPercentage(teacherActual[0].childNodes[0].nodeValue, teacherPossible[0].childNodes[0].nodeValue) + '%';
		document.getElementById('teacherScore_' + this.workgroupId).innerHTML = '(' + teacherActual[0].childNodes[0].nodeValue + '/' + teacherPossible[0].childNodes[0].nodeValue + ')';
		document.getElementById('autoPercent_' + this.workgroupId).innerHTML = getPercentage(autoActual[0].childNodes[0].nodeValue, autoPossible[0].childNodes[0].nodeValue) + '%';
		document.getElementById('autoScore_' + this.workgroupId).innerHTML = '(' + autoActual[0].childNodes[0].nodeValue + '/' + autoPossible[0].childNodes[0].nodeValue + ')';
		
		//update medians and totals
		totalGroups[this.tabIndex] = totalGroups[this.tabIndex] + 1;
		medLocPercent[this.tabIndex].push(parseInt(percentComplete[0].childNodes[0].nodeValue));
		if(currentStep[0].childNodes[0].nodeValue=='None'){
			medStep[this.tabIndex].push(-1);
		} else {
			medStep[this.tabIndex].push(parseInt(currentStep[0].childNodes[0].nodeValue));
		};
		//find median step and activity
		var medStepActivity = [];
		medStep[this.tabIndex].sort(function(a,b){if(a<b){return -1};if(a==b){return 0};if(a>b){return 1};});
		if((medStep[this.tabIndex].length % 2) == 0){
			medStepActivity = getStepAndActivityNumbers(medStep[this.tabIndex][medStep[this.tabIndex].length/2], entries);
		} else {
			medStepActivity = getStepAndActivityNumbers(medStep[this.tabIndex][(medStep[this.tabIndex].length - 1)/2], entries);
		};
		medRawActual[this.tabIndex].push(parseInt(rawActual[0].childNodes[0].nodeValue));
		medRawPossible[this.tabIndex].push(parseInt(rawPossible[0].childNodes[0].nodeValue));
		medTeacherActual[this.tabIndex].push(parseInt(teacherActual[0].childNodes[0].nodeValue));
		medTeacherPossible[this.tabIndex].push(parseInt(teacherPossible[0].childNodes[0].nodeValue));
		medAutoActual[this.tabIndex].push(parseInt(autoActual[0].childNodes[0].nodeValue));
		medAutoPossible[this.tabIndex].push(parseInt(autoPossible[0].childNodes[0].nodeValue));
		
		document.getElementById('totalGroups_' + this.tabIndex).innerHTML = '[' + totalGroups[this.tabIndex] + '] teams';
		document.getElementById('medPercent_' + this.tabIndex).innerHTML = 'Median: ' + getMedian(medLocPercent[this.tabIndex]) + '% of steps';
		document.getElementById('medActivityStep_' + this.tabIndex).innerHTML = 'Median: A' + medStepActivity[0] + ', Step' + medStepActivity[1];
		document.getElementById('medRawPercent_' + this.tabIndex).innerHTML = getPercentage(getMedian(medRawActual[this.tabIndex]), getMedian(medRawPossible[this.tabIndex])) + '%';
		document.getElementById('medRawRatio_' + this.tabIndex).innerHTML = '(' + Math.round(getMedian(medRawActual[this.tabIndex])) + '/' + Math.round(getMedian(medRawPossible[this.tabIndex])) + ')';
		document.getElementById('medTeacherPercent_' + this.tabIndex).innerHTML = getPercentage(getMedian(medTeacherActual[this.tabIndex]), getMedian(medTeacherPossible[this.tabIndex])) + '%';
		document.getElementById('medTeacherRatio_' + this.tabIndex).innerHTML = '(' + Math.round(getMedian(medTeacherActual[this.tabIndex])) + '/' + Math.round(getMedian(medTeacherPossible[this.tabIndex])) + ')';						
		document.getElementById('medAutoPercent_' + this.tabIndex).innerHTML = getPercentage(getMedian(medAutoActual[this.tabIndex]), getMedian(medAutoPossible[this.tabIndex])) + '%';
		document.getElementById('medAutoRatio_' + this.tabIndex).innerHTML = '(' + Math.round(getMedian(medAutoActual[this.tabIndex])) + '/' + Math.round(getMedian(medAutoPossible[this.tabIndex])) + ')';						
		
	};
	
	ProgressCellInfo.prototype.handleFailure = function(o){
		document.getElementById('progress_' + this.workgroupId).innerHTML = '<b>Error retrieving team data</b>';
		document.getElementById('activityStep_' + this.workgroupId).innerHTML = '';
		document.getElementById('percentComplete_' + this.workgroupId).innerHTML = '';
		document.getElementById('flagged_' + this.workgroupId).innerHTML = '--';
		document.getElementById('rawPercent_' + this.workgroupId).innerHTML = '--';
		document.getElementById('rawScore_' + this.workgroupId).innerHTML = '';
		document.getElementById('teacherPercent_' + this.workgroupId).innerHTML = '--';
		document.getElementById('teacherScore_' + this.workgroupId).innerHTML = '';
		document.getElementById('autoPercent_' + this.workgroupId).innerHTML = '--';
		document.getElementById('autoScore_' + this.workgroupId).innerHTML = '';
	};
	
	//given a number array - returns the median
	function getMedian(a){
		a.sort(function(a,b){if(a<b){return -1};if(a==b){return 0};if(a>b){return 1};});
		if((a.length % 2) == 0){
			return (a[a.length/2] + a[(a.length/2) -1]) / 2;
		} else {
			return a[(a.length - 1)/2];
		};
	};
	
	//given unique activity number/step number combo and the map
	//returns an array with individual activity number and step number
	function getStepAndActivityNumbers(stepNum, entries){
		var a = [];
		if(stepNum==-1){
			a[0] = 0;
			a[1] = 0;
		} else {
			for(x=0;x<entries.length;x++){
				if(stepNum == entries[x].childNodes[0].childNodes[0].nodeValue){
					a[0] = entries[x].childNodes[1].childNodes[0].nodeValue;
					a[1] = entries[x].childNodes[2].childNodes[0].nodeValue;
				}
			};
		};
		return a;
	};
	
	//given numerator and denominator values, returns percentage
	function getPercentage(n,d){
		if(n == 0 || d == 0){
			return 0;
		} else {
			return Math.round((n/d) * 100);
		};
	};

	/**
	* Request Manager - manages the asynchronous GET requests of given object (o)
	* and keeps five (this.MAX) running at any given time - prioritizes
	* requests for the currently selected tab
	**/
	function RequestManager(){
		this.MAX = 5;
		this.activeRequests = [];
		this.queuedRequests = [];
	};

	RequestManager.prototype.addRequest = function(o){
		if(this.queuedRequests[o.tabIndex] == null){
			this.queuedRequests[o.tabIndex] = [];
		}
		this.queuedRequests[o.tabIndex].push(o);
	};
	
	RequestManager.prototype.finished = function(o){
		this.activeRequests.splice(this.activeRequests.indexOf(o),1);
		this.nextRequest();
	};
	
	RequestManager.prototype.nextRequest = function(){
		if(this.queuedRequests[activeIndex] != null && this.queuedRequests[activeIndex].length > 0){
			var queuedCell = this.queuedRequests[activeIndex].shift();
			this.activeRequests.push(queuedCell);
			queuedCell.startRequest();
		} else {
			for(x=0;x<this.queuedRequests.length;x++){
				if(this.queuedRequests[x] != null && this.queuedRequests[x].length > 0){
					var queuedCell = this.queuedRequests[x].shift();
					this.activeRequests.push(queuedCell);
					queuedCell.startRequest();
					break;
				}
			}
		}
	};
	
	RequestManager.prototype.start = function(){
		for(z=0;z<this.MAX;z++){
			this.nextRequest();
		}
	};
	
	var requestManager = new RequestManager();
	
	function generateReport(runId, workgroupId){

		var reportType = document.getElementById('selectReport_' + workgroupId).options[document.getElementById('selectReport_' + workgroupId).selectedIndex].value;
		var url = '../reports/' + reportType + '.html?runId=' + runId + '&workgroupId=' + workgroupId;
	
		if(reportType != null && reportType != ""){
			popupReport(url);
		};		
	};
	function popupReport(URL) {
  		window.open(URL, 'report', 'toolbar=0,scrollbars=1,location=0,statusbar=0,menubar=0,resizable=1,width=800,height=640,left = 450,top = 150');
  	};
</script>





<link href="../../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="teacherprojectstylesheet" />" media="screen" rel="stylesheet" type="text/css" />
<link href="../../<spring:theme code="viewmystudentsstylesheet"/>" media="screen" rel="stylesheet" type="text/css" />
<link href="../../<spring:theme code="teachermanagementstylesheet" />" media="screen" rel="stylesheet" type="text/css" />

</head>

<body class="yui-skin-sam">

<div id="centeredDiv">

<%@ include file="headerteachermanagement.jsp"%>

<%@ include file="L2management_classmonitor_ajax.jsp"%>

<div>
	<div id="studentProgressTools">
		<ul>
			<li><a href="#">PAUSE ALL STUDENT SCREENS</a></li>
			<li><a href="#">SEND MESSAGE TO TEAM(S)</a></li>
			<li><a href="#">LOCK THIS PROGRESS SCREEN</a></li>
		</ul>
	</div>
	<div id="L3Label">Student Progress Monitor</div> 
	<div id="studentProgressProjectTitle">${run.project.curnit.sdsCurnit.name}<span class="ProjectIDTag">(Project ID: ${run.project.id})</span></div> 
	<div id="studentProgressLinks"><span class="nonLink">Generated [ ${date} ]</span><span class="link"><a id="refreshProgress" href="classmonitor.html?runId=${run.id}&tab=${tab}">Refresh Progress Monitor</a></span><span class="link"><a href="projectpickerclassmonitor.html">Select a Different Project</a></span></div>
	
</div>

<c:set var="tIndex" value="0"/>

<div id="tabSystem" class="yui-navset">
	<ul class="yui-nav" style="font-size:.8em; text-transform:uppercase;">
		<c:forEach var="period" items="${run.periods}">
			<li style="margin-right:4px;"><a href="${period.name}"><em>Period ${period.name}</em></a></li>
		</c:forEach>	
	</ul>
	<div class="yui-content" style="background-color:#FFFFFF;">
		<c:forEach var="period" varStatus="pStatus" items="${run.periods}">
			<div>
				<c:choose>
					<c:when test="${empty workgroups[period.id]}">
						<div id="noWorkgroups">
							No Workgroups found for this period.
						</div>
					</c:when>
					<c:otherwise>
						<table id="progressTable" style="margin-bottom:20px; margin-top:10px;" summary="project picker screen for management area">
							<thead style="margin:0; padding:0;">
								<tr style="margin:0; padding:0;">
									<th scope="col"><spring:message code="teacher.manage.studentprogress.1"/></th> 
									<th scope="col"><a href="javascript:popup('classmonitormeaning.html#currentlocation')"><spring:message code="teacher.manage.studentprogress.2"/></a></th>
									<th scope="col"><a href="javascript:popup('classmonitormeaning.html#flaggeditems')"><spring:message code="teacher.manage.studentprogress.3"/></a></th>
									<th style="width:10%;" scope="col"><a href="javascript:popup('classmonitormeaning.html#rawscore')"><spring:message code="teacher.manage.studentprogress.4"/></a></th>
									<th style="width:10%;" scope="col"><a href="javascript:popup('classmonitormeaning.html#teachergraded')"><spring:message code="teacher.manage.studentprogress.5"/></a></th>
									<th style="width:10%;" scope="col"><a href="javascript:popup('classmonitormeaning.html#autograded')"><spring:message code="teacher.manage.studentprogress.6"/></a></th>
									<th scope="col">Reports</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="workgroup" items="${workgroups[period.id]}">
									<tr>
										<td style=font-weight:bold;" scope="row">
											<ul>
												<c:forEach var="student" items="${workgroup.members}">
													<li>${student.userDetails.firstname} ${student.userDetails.lastname}</li>
												</c:forEach>
											</ul>
										</td>
										<td>
											<span class="progressBar" id="progressBar_${workgroup.id}"> 0%</span>
											<script type="text/javascript">
												requestManager.addRequest(new ProgressCellInfo(${run.id}, ${workgroup.id}, ${tIndex}));
											</script>
											<div id="progress_${workgroup.id}" style="margin-bottom:12px;">
												<i>Loading student progress...</i></font><br><img src=/webapp/themes/tels/default/images/rel_interstitial_loading.gif />
											</div>
											<div>
												<div id="activityStep_${workgroup.id}" style="float:left;"></div>
												<div id="percentComplete_${workgroup.id}" style="float:right;"></div>
											</div>											
										</td>
										<td>
											<div id="flagged_${workgroup.id}" align="center">
												<i>loading...</i>
											</div>
										</td>
										<td>
											<div id="rawPercent_${workgroup.id}" class="percentValue"><i>loading...</i></div>
											<div id="rawScore_${workgroup.id}" class="ratioValue""></div>
										</td>
										<td>
											<div id="teacherPercent_${workgroup.id}" class="percentValue"><i>loading...</i></div>
											<div id="teacherScore_${workgroup.id}" class="ratioValue""></div>
										</td>
										<td style="background-color:#666666;">
											<div id="autoPercent_${workgroup.id}" class="percentValue"><i>loading...</i></div>
											<div id="autoScore_${workgroup.id}" class="ratioValue""></div>
										</td>
										<td>
											<div id="generateReport" align="center">
												<div id="reports">
													<a href="javascript:popupReport('../reports/stepactivitygraph.html?runId=${run.id}&workgroupId=${workgroup.id}');">Step activity (over time)</a><br>
													<a href="javascript:popupReport('../reports/totaltimeperstep.html?runId=${run.id}&workgroupId=${workgroup.id}');">Total time spent per step</a><br>
												</div>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<td style=font-weight:bold;" scope="row">
										<div id="totalGroups_${tIndex}">
											[0] teams
										</div>
									</td>
									<td>
										<div>
											<div id="medActivityStep_${tIndex}" style="float:left;">Median: </div>
											<div id="medPercent_${tIndex}" style="float:right;">Median: 0% of steps</div>
										</div>
									</td>
									<td></td>
									<td>
										<div id="medRawPercent_${tIndex}" class="percentValue">0%</div>
										<div id="medRawRatio_${tIndex}" class="ratioValue"">(0/0)</div>
									</td>
									<td>
										<div id="medTeacherPercent_${tIndex}" class="percentValue">0%</div>
										<div id="medTeacherRatio_${tIndex}" class="ratioValue"">(0/0)</div>
									</td>
									<td style="background-color:#666666;">
										<div id="medAutoPercent_${tIndex}" class="percentValue">0%</div>
										<div id="medAutoRatio_${tIndex}" class="ratioValue">(0/0)</div>
									</td>
									<td>
									</td>
								</tr>
							</tfoot>
						</table>					
					</c:otherwise>
				</c:choose>
				<c:set var="tIndex" value="${tIndex+1}"/>
			</div>
		</c:forEach>
		
	<script type="text/javascript">
		requestManager.start();
	</script>

</div> <!--  end of yui-content Div -->

</div> <!-- end of tabSystem Div -->


</div>     <!--End of Centered Div-->

<!-- 
// THE DEBUGGING CONSOLE...UNCOMMENT TO DISPLAY

<div id="myLogger"></div>
<script type="text/javascript">
var myLogReader = new YAHOO.widget.LogReader("myLogger");
</script>
 -->
 
</body>
</html>