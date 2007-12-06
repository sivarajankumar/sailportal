<%@ include file="include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet" type="text/css" />
<link href="<spring:theme code="homepagestylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
    
<script type="text/javascript" src="./javascript/tels/rotator.js"></script>
    
<title><spring:message code="application.title" /></title>
</head>

<body>

<div id="centeredDiv">

<%@ include file="headermain.jsp"%>


<div id="pageTitle">Contact WISE: General Issues</div>
     
<div id="pageSubtitle">Please describe your issue in as much detail as possible.</div>
						
<div id="pageSubtitleLevel2">
	<ul>
		<li>If you're encountering an error message please include its full text in the <em>Detailed Description</em> box below.</li>
		<li>If you're experiencing broken links or other problems on web pages, please provide a URL link to the location in question.</li>
		<li>When reporting problems with a specific project, please describe the Activity & Step numbers where problems occur.</li>
	</ul>
</div>

<form:form method="post" action="registerteacher.html" id="teacherRegForm" >  
  <dl>
  
  	<dt><label for="NameContact" id=""><span class="asterix">* </span>Name</label></dt>
    <dd><input id="NameContact" size="50" tabindex="1"/></dd>
            
    <dt><label for="emailContact" id=""><span class="asterix">* </span>Email</label></dt>
	<dd><input id="emailContact" size="50"  tabindex="2"/> </dd>
	
	<dt><label for="projectName" id="projectName">Project Name</label></dt>
	<dd><input id="projectName" size="50"  tabindex="2"/> </dd>
            
    <dt><label for="issueTypeContact" id=""><span class="asterix">* </span>Issue Type</label> </dt>
	<dd><select name="issueTypeContact" id="issueTypeContact"  tabindex="3">
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
			</select>
			</dd>
    <dt><label for="operatingSystemContact" id="" >Operating Sytem</label> </dt>
	<dd><select name="operatingSystemContact" id="operatingSystemContact"  tabindex="4">
            <option value="1">Mac OS 9</option>
			<option value="2">Mac OS X Tiger</option>
			<option value="3">Mac OS X Leopard</option>
			<option value="4">Windows Vista</option>
			<option value="5">Windows XP/NT/2000</option>
			<option value="6">Linux</option>
			<option value="7">Other or Not Sure</option>
		</select>
		</dd>
    <dt><label for="browserContact" id="">Web Browser</label></dt>
	<dd><select name="browserContact" id="browserContact" tabindex="5">
			<option value="1">Firefox (Mac)</option>
			<option value="2">Firefox (Windows)</option>
			<option value="3">Internet Explorer (Mac)</option>
			<option value="4">Internet Explorer (Windows)</option>
			<option value="5">Safari (Mac)</option>
			<option value="6">Safari (Windows)</option>
			<option value="7">Opera</option>
			<option value="8">Netscape</option>
			<option value="9">Other</option>
		</select>
		</dd>
	<dt><label for="summaryContact" id=""><span class="asterix">* </span>Issue Summary</label></dt>
	<dd style="color:#3333CC;"><input id="summaryContact" size="50" tabindex="6"/></dd>
	
	<dt><label for="descriptionContact" id=""><span class="asterix">* </span>Detailed Description</label></dt>
	<dd><textarea name="descriptionContact" id="descriptionContact" tabindex="7" rows="10" cols="65"></textarea></dd>
      
     </dl>    
     <div id="asterixWarning">Items marked with <span style="font-size:1.1em; font-weight:bold;">*</span> are required.</div>  
        
    <div align="center"><input type="submit" id="sendMessageButton" value="Send Message" ></input>
       

           
</form:form>



</div>   <!--End of the CenteredDiv -->


</body>
</html>