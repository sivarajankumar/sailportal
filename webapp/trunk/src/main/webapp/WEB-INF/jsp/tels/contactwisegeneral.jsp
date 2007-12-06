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


<div id="pageTitle">
    	<h1 class="blueText">Contact WISE: General Issues</h1>
</div>
     
<div id="pageSubtitle">Describe your issue in as much detail as possible. <br/> This will allow the WISE staff to respond quickly and accurately.</div>
						
<div id="pageSubtitleLevel2">Examples: If you see an error message please include its text in your message.  
For broken links or other problems on web pages, please provide a URL link to the location in question.</div>

<form:form method="post" action="registerteacher.html" id="teacherRegForm" >  
  <dl>
  
  	<dt><label for="NameContact" id=""><span class="asterix">* </span>Name</label></dt>
    <dd><input id="NameContact" size="50" tabindex="1"/></dd>
            
    <dt><label for="emailContact" id=""><span class="asterix">* </span>Email</label></dt>
	<dd><input id="emailContact" size="50"  tabindex="2"/> </dd>
            
    <dt><label for="issueTypeContact" id=""><span class="asterix">* </span>Issue Type</label> </dt>
	<dd><select name="issueTypeContact" id="issueTypeContact"  tabindex="3">
			<option value="2">Trouble Logging In</option>
			<option value="3">Need Help Using WISE</option>
			<option value="4">Broken Link</option>
			<option value="5">Misspellings or Factual Errors in Projects</option>
			<option value="6">Error Messages</option>
			<option value="7">Feature Requests</option>
			<option value="8">Java Applets</option>
			<option value="9">Other</option>
			<option value="10">General Information</option>
			<option value="15">Authoring Help</option>
			<option value="16">General Bug</option>
			<option value="17">Problem with a particular student or period</option>
			</select>
			</dd>
    <dt><label for="operatingSystemContact" id="" >Operating Sytem</label> </dt>
	<dd><select name="operatingSystemContact" id="operatingSystemContact"  tabindex="4">
            <option value="2">Mac OS 9</option>
			<option value="3">Mac OS X Tiger</option>
			<option value="4">Mac OS X Leopard</option>
			<option value="5">Windows Vista</option>
			<option value="6">Windows XP/2000</option>
			<option value="7">Linux</option>
			<option value="8">Other or Not Sure</option>
		</select>
		</dd>
    <dt><label for="browserContact" id="">Web Browser</label></dt>
	<dd><select name="browserContact" id="browserContact" tabindex="5">
			<option value="2">Firefox (Mac)</option>
			<option value="3">Firefox (Windows)</option>
			<option value="4">Internet Explorer (Mac)</option>
			<option value="5">Internet Explorer (Windows)</option>
			<option value="6">Safari (Mac)</option>
			<option value="7">Safari (Windows)</option>
			<option value="8">Opera</option>
			<option value="9">Netscape Communicator</option>
			<option value="10">Other</option>
		</select>
		</dd>
	<dt><label for="summaryContact" id=""><span class="asterix">* </span>Issue Summary</label></dt>
	<dd style="color:#3333CC;"><input id="summaryContact" size="50" tabindex="6"/></dd>
	
	<dt><label for="descriptionContact" id=""><span class="asterix">* </span>Detailed Description</label></dt>
	<dd><textarea name="descriptionContact" id="descriptionContact" tabindex="7" rows="11" cols="60"></textarea></dd>
      
     </dl>    
     <div id="asterixWarning">Items marked with <span style="font-size:1.1em; font-weight:bold;">*</span> are required.</div>  
        
    <div align="center"><input type="submit" id="sendMessageButton" value="Send Message" ></input>
       

           
</form:form>



</div>   <!--End of the CenteredDiv -->


</body>
</html>