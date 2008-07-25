<%@ include file="include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">

<link href="../../<spring:theme code="yui-fonts-min-stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="yui-container-stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />


<title><spring:message code="viewmystudents.message" /></title>

<!-- FOR LATER REFACTOR <script src="../../../javascript/tels/custom-yui/changegroupdnd.js" type="text/javascript"> </script> -->
<head>
<%@ include file="../grading/styles.jsp"%>
<script type="text/javascript" src="../.././javascript/tels/yui/yahoo/yahoo.js"></script>
<script type="text/javascript" src="../.././javascript/tels/yui/event/event.js"></script>  
<script type="text/javascript" src="../.././javascript/tels/yui/connection/connection.js"></script> 
<script type="text/javascript" src="../.././javascript/tels/general.js"></script>
<script type="text/javascript" src="../.././javascript/tels/utils.js"></script>
<script type="text/javascript" src="../.././javascript/tels/teacher/management/viewmystudents.js"></script>

<script>
// this is for the tabView
    var tabView
	function init() {
   		tabView = new YAHOO.widget.TabView('tabSystem');
		tabView.set('activeIndex', 0);
    }
    YAHOO.util.Event.onDOMReady(init);
</script>


<script>
    var tabView
	function init() {
   		tabView = new YAHOO.widget.TabView('tabSystem');
		tabView.set('activeIndex', ${tabIndex});
    }
    YAHOO.util.Event.onDOMReady(init);

        var newGroupCount = 0;
        var numPeriods = ${fn:length(viewmystudentsallperiods)};
        var workgroupchanges = new Array();
        
        var handleSuccess = function(o){

	if(o.responseText !== undefined){
	   setTimeout("window.location.reload()", 500);
	}
}

var callback =
{
  success: handleSuccess
}


function createNewWorkgroup(periodId, runId) {
  //alert(newGroupCount);
  var newWorkgroupId = -1 - newGroupCount;
  var workareaDiv = document.getElementById("div_for_new_workgroups_"+periodId);
  workareaDiv.innerHTML += "<div class='workarea' id='newgroup_div_"+periodId+"_"+newWorkgroupId+"'>"
                          +"<ul id='ul_"+periodId+"_newgroup_"+newWorkgroupId+"' class='draglist'><li>UNSAVED TEAM. Drag student names here, then click SAVE button (below) to confirm changes.</li></ul>"
                          +"</div>";
                          
   // need to iterate through all of the newgroups
   // and re-register DDTarget and DDList; not sure why,
   // but is needed HT                       
   for (q=0; q <= newGroupCount; q++) {
        var newWGId = - 1 - q;
        var ulid= "ul_"+periodId+"_newgroup_"+newWGId;
        new YAHOO.util.DDTarget(ulid);
        var ulElm = document.getElementById(ulid);
        var liElmsInUl = ulElm.getElementsByTagName("li");
        for (h=0; h<liElmsInUl.length; h++) {
            new YAHOO.example.DDList(liElmsInUl[h].id);
        }
   }
                              
  newGroupCount++;
}
</script>

<script type="text/javascript">

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
    
            YAHOO.example.container.wait.setHeader("Loading, please wait...");
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
    
    <c:forEach var="viewmystudentsperiod" items="${viewmystudentsallperiods}" >
          YAHOO.util.Event.on("saveButton_${viewmystudentsperiod.period.id}", "click", initLoading);
    </c:forEach>
		
</script>


<script type="text/javascript"><!--
(function() {

var Dom = YAHOO.util.Dom;
var Event = YAHOO.util.Event;
var DDM = YAHOO.util.DragDropMgr;

//////////////////////////////////////////////////////////////////////////////
// example app
//////////////////////////////////////////////////////////////////////////////
YAHOO.example.DDApp = {
    init: function() { 
         // this function initializes the drag-able lists and adds action listeners to the 
         // save button
         //alert(${fn:length(viewmystudentsallperiods)});
        <c:forEach var="viewmystudentsperiod" items="${viewmystudentsallperiods}" >
          Event.on("saveButton_${viewmystudentsperiod.period.id}", "click", this.showOrder);
          Event.on("switchButton", "click", this.switchStyles);
          new YAHOO.util.DDTarget("ul_${viewmystudentsperiod.period.id}_groupless");
          //alert("ul_${viewmystudentsperiod.period.name}");
          //alert(${fn:length(viewmystudentsperiod.grouplessStudents)});
          <c:forEach var="grouplessStudent" items="${viewmystudentsperiod.grouplessStudents}" >
            //alert("li_${grouplessStudent.id}");
            new YAHOO.example.DDList("li_${grouplessStudent.id}_groupless");
            //alert("li_${grouplessStudent.id}");
          </c:forEach>
          //alert(${fn:length(viewmystudentsperiod.workgroups)});
          <c:forEach var="workgroupInPeriod" items="${viewmystudentsperiod.workgroups}" >
            new YAHOO.util.DDTarget("ul_${viewmystudentsperiod.period.id}_workgroup_${workgroupInPeriod.id}");
            <c:forEach var="workgroupMember" items="${workgroupInPeriod.members}" >
              new YAHOO.example.DDList("li_${workgroupMember.id}_${workgroupInPeriod.id}");
            </c:forEach>
          </c:forEach>
        </c:forEach>
    },

    showOrder : function(event) {
        // this function saves the workgroup configurations of the group
        // it also popus the current workgroup configuration
        var elTarget = YAHOO.util.Event.getTarget(event);    
        var parseList = function(ul, title) {
            var items = ul.getElementsByTagName("li");
            var out = title + ": ";
            for (i=0;i<items.length;i=i+1) {
                out += items[i].id + " ";
            }
            return out;
        };
        
        //alert(elTarget.id);
        
        var changes = "";
        for (key in workgroupchanges) {
            changes += "userId: " + key + "workgroupFrom: " + workgroupchanges[key]["workgroupFrom"]
                    + "workgroupTo: " + workgroupchanges[key]["workgroupTo"] + "\n";
        }
        //alert(changes);
        
        <c:forEach var="viewmystudentsperiod" items="${viewmystudentsallperiods}" >
          var periodId=${viewmystudentsperiod.period.id}
          if (elTarget.id == "saveButton_"+periodId) {
            var grouplessUl=Dom.get("ul_"+periodId+"_groupless")
            var out = parseList(grouplessUl, "unassigned students")

            <c:forEach var="workgroupInPeriod" items="${viewmystudentsperiod.workgroups}" >
              var workgroupUl = document.getElementById("ul_"+periodId+"_workgroup_${workgroupInPeriod.id}");
              out += parseList(workgroupUl, "\nWorkgroup with id: ${workgroupInPeriod.id}");
            </c:forEach>
            //alert(out);
            var submitChangesUrl = "submitworkgroupchanges.html";
            var numChanges = 0;
            for (key in workgroupchanges) {
              numChanges++;
            }
            var changeIndex = 0;
            var postData = 'tabIndex='+tabView.get("activeIndex")+'&numChanges=' + numChanges + '&periodId='+periodId+'&runId='+${viewmystudentsperiod.run.id};
            for (userId in workgroupchanges) {              
              var workgroupFrom = workgroupchanges[userId]["workgroupFrom"];
              var workgroupTo = workgroupchanges[userId]["workgroupTo"];
              postData += '&userId_'+changeIndex +'='+userId+'&workgroupFrom_'+changeIndex +'='+workgroupFrom+'&workgroupTo_'+changeIndex +'='+workgroupTo;
              changeIndex++;
            }
            //alert(postData);
            var request = YAHOO.util.Connect.asyncRequest('POST', submitChangesUrl, callback, postData);
            // make sure that *all* changes in workgroupchanges are persisted...so wait 5-6 seconds and then refresh the page
            //setTimeout( "window.location.reload()", 5000 );
            
          }
        </c:forEach>
                
    },

    switchStyles: function() {
        Dom.get("ul1").className = "draglist_alt";
        Dom.get("ul2").className = "draglist_alt";
    }
    
};

//////////////////////////////////////////////////////////////////////////////
// custom drag and drop implementation
//////////////////////////////////////////////////////////////////////////////

YAHOO.example.DDList = function(id, sGroup, config) {

    YAHOO.example.DDList.superclass.constructor.call(this, id, sGroup, config);

    this.logger = this.logger || YAHOO;
    var el = this.getDragEl();
    Dom.setStyle(el, "opacity", 0.67); // The proxy is slightly transparent

    this.goingUp = false;
    this.lastY = 0;
};

YAHOO.extend(YAHOO.example.DDList, YAHOO.util.DDProxy, {

    startDrag: function(x, y) {
        this.logger.log(this.id + " startDrag");
        //var workgroupli = document.getElementById(this.id);
        
        //workgroupchanges[this.id] = workgroupli.innerHTML;
        //this.logger.log("body: " + workgroupli.innerHTML + " startDrag");

        // make the proxy look like the source element
        var dragEl = this.getDragEl();
        var clickEl = this.getEl();
        Dom.setStyle(clickEl, "visibility", "hidden");

        dragEl.innerHTML = clickEl.innerHTML;

        Dom.setStyle(dragEl, "color", Dom.getStyle(clickEl, "color"));
        Dom.setStyle(dragEl, "backgroundColor", Dom.getStyle(clickEl, "backgroundColor"));
        Dom.setStyle(dragEl, "border", "2px solid gray");
    },

    endDrag: function(e) {
        this.logger.log(this.id + " endDrag");
        var lastIndexOf_ = this.id.lastIndexOf("_");
        var workgroupFrom = this.id.substr(lastIndexOf_ + 1);
        var userId = this.id.substring(3, lastIndexOf_);
        this.logger.log("last index: " + lastIndexOf_);
        this.logger.log("workgroupFrom: " + workgroupFrom);
        this.logger.log("userId: " + userId);
        workgroupchanges[userId] = new Array();
        workgroupchanges[userId]["workgroupFrom"] = workgroupFrom;
        var elTarget = YAHOO.util.Event.getTarget(e);    
        this.logger.log("elTarget.id: " + elTarget.id);
        this.logger.log("elTarget.tagName: " + elTarget.tagName);
        this.logger.log("elTarget.parentNode: " + elTarget.parentNode);
        this.logger.log("elTarget.parentNode.tagName: " + elTarget.parentNode.tagName);

        var srcEl = this.getEl();
        var proxy = this.getDragEl();

        // Show the proxy element and animate it to the src element's location
        Dom.setStyle(proxy, "visibility", "");
        var a = new YAHOO.util.Motion( 
            proxy, { 
                points: { 
                    to: Dom.getXY(srcEl)
                }
            }, 
            0.2, 
            YAHOO.util.Easing.easeOut 
        )
        var proxyid = proxy.id;
        var thisid = this.id;

        // Hide the proxy and show the source element when finished with the animation
        a.onComplete.subscribe(function() {
                Dom.setStyle(proxyid, "visibility", "hidden");
                Dom.setStyle(thisid, "visibility", "");
                
                var element = document.getElementById(thisid);
                //alert("element: " + element);
                //alert("element.id: " + element.id);
                //alert("element.parentNode.tagName: " + element.parentNode.tagName);
                //alert("element.parentNode.id: " + element.parentNode.id);
                workgroupToUlStr = element.parentNode.id;
                var lastIndexOf_ = workgroupToUlStr.lastIndexOf("_");
                var workgroupTo = workgroupToUlStr.substr(lastIndexOf_ + 1);
                //alert("workgroupTo: " + workgroupTo);
        		workgroupchanges[userId]["workgroupTo"] = workgroupTo;
                
            });
        a.animate();
    },

    onDragDrop: function(e, id) {
        //this.logger.log(this.id + " onDragDrog");

        // If there is one drop interaction, the li was dropped either on the list,
        // or it was dropped on the current location of the source element.
        if (DDM.interactionInfo.drop.length === 1) {

            // The position of the cursor at the time of the drop (YAHOO.util.Point)
            var pt = DDM.interactionInfo.point; 

            // The region occupied by the source element at the time of the drop
            var region = DDM.interactionInfo.sourceRegion; 

            // Check to see if we are over the source element's location.  We will
            // append to the bottom of the list once we are sure it was a drop in
            // the negative space (the area of the list without any list items)
            if (!region.intersect(pt)) {
                var destEl = Dom.get(id);
                var destDD = DDM.getDDById(id);
                destEl.appendChild(this.getEl());
                destDD.isEmpty = false;
                DDM.refreshCache();
            }

        }
    },

    onDrag: function(e) {

        // Keep track of the direction of the drag for use during onDragOver
        var y = Event.getPageY(e);

        if (y < this.lastY) {
            this.goingUp = true;
        } else if (y > this.lastY) {
            this.goingUp = false;
        }

        this.lastY = y;
    },

    onDragOver: function(e, id) {
    
        var srcEl = this.getEl();
        var destEl = Dom.get(id);

        // We are only concerned with list items, we ignore the dragover
        // notifications for the list.
        if (destEl.nodeName.toLowerCase() == "li") {
            var orig_p = srcEl.parentNode;
            var p = destEl.parentNode;

            if (this.goingUp) {
                p.insertBefore(srcEl, destEl); // insert above
            } else {
                p.insertBefore(srcEl, destEl.nextSibling); // insert below
            }

            DDM.refreshCache();
        }
    }
});

Event.onDOMReady(YAHOO.example.DDApp.init, YAHOO.example.DDApp, true);

})();
--></script>


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
	<div id="studentProgressLinks"><span class="nonLink">Generated [3:15pm 7/25/2008]</span><span class="link"><a href="#">Refresh Progress Monitor</a></span><span class="link"><a href="#">Select a Different Project</a></span></div>
	
</div>


<div id="tabSystem" class="yui-navset">
<ul class="yui-nav">
  <li id="tabPicker" style="margin-right:4px;"><a href="Current Runs"><em>Period X</em></a></li>	
  <li id="tabPicker" style="margin-right:4px;"><a href="Shared Runs"><em>Period Y</em></a></li>	
  <li id="tabPicker" style="margin-right:4px;"><a href="Archived Runs"><em>Period Z</em></a></li>	
</ul>
<div class="yui-content" style="background-color:#FFFFFF;">
<div>

<table id="progressTable" style="margin-bottom:20px; margin-top:10px;" summary="project picker screen for management area">
	<thead style="margin:0; padding:0;">
		<tr style="margin:0; padding:0;">
			<th scope="col"><spring:message code="teacher.manage.studentprogress.1"/></th> 
			<th scope="col"><spring:message code="teacher.manage.studentprogress.2"/></th>
			<th scope="col"><spring:message code="teacher.manage.studentprogress.3"/></th>
			<th style="width:10%;" scope="col"><spring:message code="teacher.manage.studentprogress.4"/></th>
			<th style="width:10%;" scope="col"><spring:message code="teacher.manage.studentprogress.5"/></th>
			<th style="width:10%;" scope="col"><spring:message code="teacher.manage.studentprogress.6"/></th>
		</tr>
	</thead>
	<tbody>	
	    <tr>
			<td style=font-weight:bold;" scope="row">
				<ul>
				<li>Student Name 1</li>
				<li>Student Name 2</li>
				</ul>
			</td>
			<td>
				<div style="margin-bottom:12px;"><b>[Progress Bar Here]</b></div>
				<div>
					<div style="float:left;">[Ax, StepY]</div>
					<div style="float:right;">[zz%] of steps</div>
				</div>
			</td>
			<td>
				<ul>
				<li>[item 1]</li>
				<li>[item 2]</li>
				</ul>
			<td>
				<div class="percentValue">[60%]</div>
				<div class="ratioValue"">(150/250)</div>
			</td>
			<td>
				<div class="percentValue">[92%]</div>
				<div class="ratioValue"">(166/180)</div>
			</td>
			<td>
				<div class="percentValue">[90%]</div>
				<div class="ratioValue"">(45/50)</div>
			</td>
		</tr>
		<tr>
			<td style=font-weight:bold;" scope="row">
				<ul>
				<li>Hiroki Terashima</li>
				<li>Lance Armstrong</li>
				</ul>
			</td>
			<td>
				<div style="margin-bottom:12px;"><b>[Progress Bar Here]</b></div>
				<div>
					<div style="float:left;">[A3, Step 6]</div>
					<div style="float:right;">[52%] of steps</div>
				</div>
			</td>
			<td>
				<ul>
				<li>[A1, Step 3 (skipped)]</li>
				<li>[A1, Step 7 (scant)</li>
				<li>[A2 (all skipped)]</li>
				</ul>
			<td>
				<div class="percentValue">[55%]</div>
				<div class="ratioValue"">(137/250)</div>
			</td>
			<td>
				<div class="percentValue">[89%]</div>
				<div class="ratioValue"">(160/180)</div>
			</td>
			<td>
				<div class="percentValue">[94%]</div>
				<div class="ratioValue"">(47/50)</div>
			</td>
		</tr>
		</tbody>
</table>
</div>

<div>
<table>
	Next period's content goes here.
</table>
</div>

<div>
<table>
	Next period's content goes here.
</table>
</div>

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