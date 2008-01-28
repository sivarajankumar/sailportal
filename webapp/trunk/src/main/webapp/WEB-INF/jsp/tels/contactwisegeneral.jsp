<%@ include file="include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet" type="text/css" />

<title>Contact WISE General Issues</title>
<link href="<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet" type="text/css" />
<%@ include file="teacher/grading/styles.jsp"%>

</head>


<body class="yui-skin-sam">

<script type="text/javascript">

	//preload image if browser is not IE because animated gif will just freeze if user is using IE

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

    YAHOO.util.Event.on("sendMessageButton", "click", init);
		
</script>



<div id="centeredDiv">

<%@ include file="headermain_nousername.jsp"%>

<div style="text-align:center;">   <!--This bad boy ensures centering of block level elements in IE.  Have I mentioned that I hate IE?-->

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

	<authz:authorize ifAllGranted="ROLE_ANONYMOUS">
		<dt><label for="emailContact" id="emailContact"><span class="asterix">* </span>Email</label></dt>
		<dd><form:input path="email" id="email" size="50" tabindex="2"/> </dd>
	</authz:authorize>

	<authz:authorize ifAllGranted="ROLE_TEACHER">
		<dt><label for="emailContact" id="emailContact"><span class="asterix">* </span>Email</label></dt>
		<dd><form:input path="email" id="email" size="50" tabindex="2"/> </dd>
	</authz:authorize>
	   
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

</div>
	<h5 class="center"><a href="index.html">Return to WISE Home Page</a></h5>

</div>   <!--End of the CenteredDiv -->


</body>
</html>