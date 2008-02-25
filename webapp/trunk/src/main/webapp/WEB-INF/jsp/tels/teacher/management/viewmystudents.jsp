<%@ include file="include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="../../<spring:theme code="yui-fonts-min-stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="yui-container-stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />

<script type="text/javascript" src="../.././javascript/tels/general.js"></script>

<title><spring:message code="viewmystudents.message" /></title>

<!-- FOR LATER REFACTOR <script src="../../../javascript/tels/custom-yui/changegroupdnd.js" type="text/javascript"> </script> -->

<%@ include file="../grading/styles.jsp"%>
<script type="text/javascript" src="../.././javascript/tels/yui/yahoo/yahoo.js"></script>
<script type="text/javascript" src="../.././javascript/tels/yui/event/event.js"></script>  
<script type="text/javascript" src="../.././javascript/tels/yui/connection/connection.js"></script> 
<script type="text/javascript" src="../.././javascript/tels/utils.js"></script>
<script type="text/javascript" src="../.././javascript/tels/teacher/management/viewmystudents.js"></script>

<link href="../../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="teacherprojectstylesheet" />" media="screen" rel="stylesheet" type="text/css" />
<link href="../../<spring:theme code="viewmystudentsstylesheet"/>" media="screen" rel="stylesheet" type="text/css" /><link href="../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet" type="text/css" />

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
  success:handleSuccess,
};


function createNewWorkgroup(periodId, runId) {
  //alert(newGroupCount);
  var newWorkgroupId = -1 - newGroupCount;
  var workareaDiv = document.getElementById("div_for_new_workgroups_"+periodId);
  workareaDiv.innerHTML += "<div class='workarea' id='newgroup_div_"+periodId+"_"+newWorkgroupId+"'>"
                          +"<ul id='ul_"+periodId+"_newgroup_"+newWorkgroupId+"' class='draglist'><li>New Team (UNSAVED. Drag student names here, then click SAVE button to confirm changes.)</li></ul>"
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

</head>

<body class="yui-skin-sam">

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


<div id="centeredDiv">

<%@ include file="headerteachermanagement.jsp"%>

<%@ include file="L2management_managestudents.jsp"%>


<div id="L3Label">manage my students</div> 

<table id="projectTitleBox" border=0>
	<tr>
		<th>${project_name}</th>
		<td>(${project_id})</td>
	</tr>
</table>


<div id="tabSystem" class="yui-navset">
<ul class="yui-nav" style="font-size:.7em;">
	<c:forEach var="viewmystudentsperiod" varStatus="periodStatus" items="${viewmystudentsallperiods}">
		<li style="padding-right:3px; padding-top:0px; margin-top:0px;"><a href="${viewmystudentsperiod.period.name}"><em>Period ${viewmystudentsperiod.period.name}</em></a></li>
	</c:forEach>
</ul>
<div class="yui-content" style="background-color:#FFFFFF;">

  <c:forEach var="viewmystudentsperiod" varStatus="periodStatus" items="${viewmystudentsallperiods}">
	<div><c:choose>
		<c:when test="${fn:length(viewmystudentsperiod.period.members) == 0}">
   		    <!--  there is NO student in this period  -->
				No students registered in this period
			</c:when>
		<c:otherwise>
		    <!--  there are students in this period  -->
		    <ul id="periodHeaderBar">
		    	<li class="periodHeaderStart">${fn:length(viewmystudentsperiod.period.members)} Student(s) / ${fn:length(viewmystudentsperiod.workgroups)} team(s)</li>
		    	<li class="periodHeaderStart"">Student Code for this Period: ${viewmystudentsperiod.run.runcode}-${viewmystudentsperiod.period.name}</li>
		    	<li class="viewStudentsLink"><a href="#" onclick="javascript:createNewWorkgroup(${viewmystudentsperiod.period.id}, ${viewmystudentsperiod.run.id});">Create a New Team</a></li>
		     	<li class="viewStudentsLink"><a href="#" onclick="javascript:popup640('batchstudentchangepassword.html?groupId=${viewmystudentsperiod.period.id}');">Change All Passwords</a></li>
		       	<li class="viewStudentsLink"><a href="#" onclick="javascript:popup('#');">Help</a></li>
		    </ul>
		  			
			<div id="viewStudentsInstructions"><strong>To changes groupings:</strong> &nbsp; mouse over a student name to see a 'hand' icon.  Then click and drag the name.
			<br/> 
			<strong>To create new teams:</strong> &nbsp; click the "Create a New Team" link, then drag student names into the new box.
			<br>
			<strong>To create a PDF file of student work:</strong> &nbsp; click the "Create PDF" link adjacent to each Team Number below.</div>
			
			<div class="workarea" id="groupless_div_${viewmystudentsperiod.period.id}">
			  <ul id="ul_${viewmystudentsperiod.period.id}_groupless" class="draglist">
			    <li class="grouplessHeader">unassigned students</li>

                <c:forEach var="mem" items="${viewmystudentsperiod.grouplessStudents}">
			      <li class="grouplesslist" id="li_${mem.id}_groupless">
			      
			         <span id="userNameWithinView">${mem.userDetails.firstname} ${mem.userDetails.lastname}</span>
    			     <a class="userLinks" href="#" onclick="javascript:popupSpecial('changestudentpassword.html?userName=${mem.userDetails.username}');">Info</a>
    			     <a class="userLinks" href="#" onclick="javascript:popup640('changestudentpassword.html?userName=${mem.userDetails.username}');">Password</a>
    			     <a class="userLinks" href="#" onclick="javascript:popup640('changestudentpassword.html?userName=${mem.userDetails.username}');">Period</a>
    			     <a class="userLinks" href="#" onclick="javascript:popup640('changestudentpassword.html?userName=${mem.userDetails.username}');">Detach</a>
    			  </li>
			    </c:forEach>
			    
  			  </ul>
			</div>

            <c:forEach var="workgroupInPeriod" items="${viewmystudentsperiod.workgroups}" >
              <div class="workarea" id="div_${workgroupInPeriod.id}">
			    <ul id="ul_${viewmystudentsperiod.period.id}_workgroup_${workgroupInPeriod.id}" class="draglist">  
			      <li class="workgroupHeader">TEAM ${workgroupInPeriod.id}
			        <a class="createPdfLink" href="${workgroupInPeriod.workPDFUrl}">Create PDF file for this team's work</a>
			      </li>
			      
			      <c:forEach var="workgroupMember" items="${workgroupInPeriod.members}">
			      
			        <li class="workgrouplist" id="li_${workgroupMember.id}_${workgroupInPeriod.id}">
			         <span id="userNameWithinView">${workgroupMember.userDetails.firstname} ${workgroupMember.userDetails.lastname}</span>
    			     <a class="userLinks" href="#" onclick="javascript:popup('changestudentpassword.html?userName=${workgroupMember.userDetails.username}');">Info</a>
    			     <a class="userLinks" href="#" onclick="javascript:popup640('changestudentpassword.html?userName=${workgroupMember.userDetails.username}');">Password</a>
    			     <a class="userLinks" href="#" onclick="javascript:popup640('changestudentpassword.html?userName=${workgroupMember.userDetails.username}');">Period</a>
    			     <a class="userLinks" href="#" onclick="javascript:popup640('changestudentpassword.html?userName=${workgroupMember.userDetails.username}');">Detach</a>
    			     
			        </li>
			      </c:forEach>
			    </ul>
			   </div>
            </c:forEach>
            
            <div id="div_for_new_workgroups_${viewmystudentsperiod.period.id}">
            </div>
            
         <div id="saveBar">
         		<input type="button" class="saveButton" id="saveButton_${viewmystudentsperiod.period.id}" 
            	value="Save Changes to Team Assignments" />
          </div> 
     
		</c:otherwise>
	</c:choose>
	
	
	</div>
    </c:forEach>
</div>



	
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

</div> 


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