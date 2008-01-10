<%@ include file="include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />


<link href="<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet" type="text/css" />
<link href="<spring:theme code="homepagestylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
    
<script type="text/javascript" src="./javascript/tels/rotator.js"></script>


<!--CSS for Controls:--> 
<link rel="stylesheet" type="text/css" href="./javascript/tels/yui/container/assets/skins/sam/container.css"> 
<link rel="stylesheet" type="text/css" href="./javascript/tels/yui/menu/assets/skins/sam/menu.css"> 
<link rel="stylesheet" type="text/css" href="./javascript/tels/yui/autocomplete/assets/skins/sam/autocomplete.css"> 
<link rel="stylesheet" type="text/css" href="./javascript/tels/yui/button/assets/skins/sam/button.css"> 
<link rel="stylesheet" type="text/css" href="./javascript/tels/yui/calendar/assets/skins/sam/calendar.css"> 
<link rel="stylesheet" type="text/css" href="./javascript/tels/yui/colorpicker/assets/skins/sam/colorpicker.css"> 
<link rel="stylesheet" type="text/css" href="./javascript/tels/yui/datatable/assets/skins/sam/datatable.css"> 
<link rel="stylesheet" type="text/css" href="./javascript/tels/yui/editor/assets/skins/sam/editor.css"> 
<link rel="stylesheet" type="text/css" href="./javascript/tels/yui/logger/assets/skins/sam/logger.css"> 
<link rel="stylesheet" type="text/css" href="./javascript/tels/yui/tabview/assets/skins/sam/tabview.css"> 
<link rel="stylesheet" type="text/css" href="./javascript/tels/yui/treeview/assets/skins/sam/treeview.css"> 
<link href="<spring:theme code="teachergradingstylesheet"/>" media="screen" rel="stylesheet" type="text/css" /> 

<!--JavaScript source files for the entire YUI Library:--> 
 
<!--Utilities (also aggregated in yahoo-dom-event.js and utilities.js; see readmes in the 
YUI download for details on each of the aggregate files and their contents):--> 
<script type="text/javascript" src="./javascript/tels/yui/yahoo/yahoo-min.js"></script> 
<script type="text/javascript" src="./javascript/tels/yui/dom/dom-min.js"></script> 
<script type="text/javascript" src="./javascript/tels/yui/event/event-min.js"></script> 
<script type="text/javascript" src="./javascript/tels/yui/element/element-beta-min.js"></script> 
<script type="text/javascript" src="./javascript/tels/yui/animation/animation-min.js"></script> 
<script type="text/javascript" src="./javascript/tels/yui/connection/connection-min.js"></script> 
<script type="text/javascript" src="./javascript/tels/yui/datasource/datasource-beta-min.js"></script> 
<script type="text/javascript" src="./javascript/tels/yui/dragdrop/dragdrop-min.js"></script> 
<script type="text/javascript" src="./javascript/tels/yui/history/history-beta-min.js"></script> 
<script type="text/javascript" src="./javascript/tels/yui/imageloader/imageloader-experimental-min.js"></script> 
<script type="text/javascript" src="./javascript/tels/yui/yuiloader/yuiloader-beta-min.js"></script> 
 
<!--YUI's UI Controls:--> 
<script type="text/javascript" src="./javascript/tels/yui/container/container-min.js"></script> 
<script type="text/javascript" src="./javascript/tels/yui/menu/menu-min.js"></script> 
<script type="text/javascript" src="./javascript/tels/yui/autocomplete/autocomplete-min.js"></script> 
<script type="text/javascript" src="./javascript/tels/yui/button/button-beta-min.js"></script> 
<script type="text/javascript" src="./javascript/tels/yui/calendar/calendar-min.js"></script> 
<script type="text/javascript" src="./javascript/tels/yui/colorpicker/colorpicker-beta-min.js"></script> 
<script type="text/javascript" src="./javascript/tels/yui/datatable/datatable-beta-min.js"></script> 
<script type="text/javascript" src="./javascript/tels/yui/editor/editor-beta-min.js"></script> 
<script type="text/javascript" src="./javascript/tels/yui/logger/logger-min.js"></script> 
<script type="text/javascript" src="./javascript/tels/yui/slider/slider-min.js"></script> 
<script type="text/javascript" src="./javascript/tels/yui/tabview/tabview-min.js"></script> 
<script type="text/javascript" src="./javascript/tels/yui/treeview/treeview-min.js"></script>

<script type="text/javascript" src="./javascript/tels/yui/yahoo/yahoo.js"></script>
<script type="text/javascript" src="./javascript/tels/yui/event/event.js"></script>  
<script type="text/javascript" src="./javascript/tels/yui/connection/connection.js"></script> 
<script type="text/javascript" src="./javascript/tels/utils.js"></script>

<title>Contact WISE General Issues</title>

</head>

<body class="yui-skin-sam">

<script type="text/javascript">

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
    
            YAHOO.example.container.wait.setHeader("Loading, please wait...");
            YAHOO.example.container.wait.setBody("<img src=\"http://us.i1.yimg.com/us.yimg.com/i/us/per/gr/gp/rel_interstitial_loading.gif\"/>");
            YAHOO.example.container.wait.render(document.body);

        }

        // Show the Panel
        YAHOO.example.container.wait.show();
        
    }
    
    YAHOO.util.Event.on("sendMessageButton", "click", init);
		
</script>


<div id="centeredDiv">

<%@ include file="headermain_nousername.jsp"%>


<div id="pageTitle">Contact WISE: General Issues</div>
     
<div id="pageSubtitle">Please describe your issue in as much detail as possible.</div>
						
<div id="pageSubtitleLevel2">
	<ul>
		<li>If you're encountering an error message please include its full text in the <em>Detailed Description</em> box below.</li>
		<li>If you're experiencing broken links or other web page problems, please indicate the URL address for the problem page.</li>
	</ul>
</div>

<!-- Support for Spring errors object -->
<div id="regErrorMessages">
<spring:bind path="contactWISEGeneral.*">
  <c:forEach var="error" items="${status.errorMessages}">
        <br /><c:out value="${error}"/>
    </c:forEach>
</spring:bind>
</div>

<form:form commandName="contactWISEGeneral" method="post" action="contactwisegeneral.html" id="contactWISEForm"  >  
  <dl>
  
  	<dt><label for="NameContact" id="NameContact"><span class="asterix">* </span>Name</label></dt>
    <dd><form:input path="name"  id="name" size="50" tabindex="1"/></dd>
            
    <dt><label for="emailContact" id="emailContact"><span class="asterix">* </span>Email</label></dt>
	<dd><form:input path="email" id="email" size="50" tabindex="2"/> </dd>
   
    <dt><label for="issueTypeContact" id="emailContact"><span class="asterix">* </span>Issue Type</label> </dt>
	<dd><form:select path="issuetype" id="issuetype"  tabindex="3">
	      <c:forEach items="${issuetypes}" var="issuetype">
            <form:option value="${issuetype.name}">
            	<spring:message code="issuetypes.${issuetype.name}" />
            </form:option>
          </c:forEach>
		</form:select>
		<!--  
			<option value="" class="popUpMenuDefault">select an item</option>
			<option value="1">Trouble Signing In</option>
			<option value="2">Need Help Using WISE</option>
			<option value="3">Broken Link</option>
			<option value="4">Misspelling or Factual Error in Project</option>
			<option value="5">Problem with Student or Period</option>
			<option value="6">Problem Running a Model/Simulation Step</option>
			<option value="7">Error Message</option>
			<option value="8">Need Help with Authoring</option>
			<option value="9">Other Problem</option>
			<option value="10">Request for New Feature</option>
			</select>-->
	</dd>
    <dt><label for="operatingSystemContact" id="operatingSystemLabel" >Operating System</label> </dt>
	<dd><form:select path="operatingsystem" id="operatingsystem"  tabindex="4">
	      <c:forEach items="${operatingsystems}" var="operatingsystem">
            <form:option value="${operatingsystem.name}">
            	<spring:message code="operatingsystems.${operatingsystem.name}" />
            </form:option>
          </c:forEach>
		</form:select>
	<!-- <select name="operatingSystemContact" id="operatingSystemContact"  tabindex="4">
          	<option value="" class="popUpMenuDefault">select an item</option>
            <option value="1">Mac OS 9</option>
			<option value="2">Mac OS X Tiger</option>
			<option value="3">Mac OS X Leopard</option>
			<option value="4">Windows Vista</option>
			<option value="5">Windows XP/NT/2000</option>
			<option value="6">Linux</option>
			<option value="7">Other or Not Sure</option>
		</select> -->
		</dd>
    <dt><label for="browserContact" id="webBrowserLabel">Web Browser</label></dt>
	<dd><form:select path="webbrowser" id="webbrowser"  tabindex="5">
	      <c:forEach items="${webbrowsers}" var="webbrowser">
            <form:option value="${webbrowser.name}">
            	<spring:message code="webbrowsers.${webbrowser.name}" />
            </form:option>
          </c:forEach>
		</form:select>
	<!-- <select name="browserContact" id="browserContact" tabindex="5">
			<option value="" class="popUpMenuDefault">select an item</option>
			<option value="1">Firefox (Mac)</option>
			<option value="2">Firefox (Windows)</option>
			<option value="3">Internet Explorer (Mac)</option>
			<option value="4">Internet Explorer (Windows)</option>
			<option value="5">Safari (Mac)</option>
			<option value="6">Safari (Windows)</option>
			<option value="7">Opera</option>
			<option value="8">Netscape</option>
			<option value="9">Other</option>
		</select> -->
		</dd>
	<dt><label for="summaryContact" id="summaryContact"><span class="asterix">* </span>Issue Summary</label></dt>
	<dd style="color:#3333CC;"><form:input path="summary" id="summary" size="50" tabindex="6"/></dd>
	
	<dt><label for="descriptionContact" id="descriptionContact"><span class="asterix">* </span>Detailed Description</label></dt>
	<dd><form:textarea path="description" id="description" tabindex="7" rows="9" cols="65"></form:textarea></dd>
      
     </dl>    
     <div id="asterixWarning">Items marked with <span style="font-size:1.1em; font-weight:bold;">*</span> are required.</div>  
        
    <div align="center"><input type="submit" id="sendMessageButton" value="Send Message" ></input></div>
                  
</form:form>

</div>   <!--End of the CenteredDiv -->

<h5><a href="index.html">Return to WISE Home Page</a></h5>

</body>
</html>