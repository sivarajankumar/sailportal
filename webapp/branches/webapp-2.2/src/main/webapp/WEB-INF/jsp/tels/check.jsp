<%@ include file="include.jsp"%>

<!-- $Id: signup.jsp 323 2007-04-21 18:08:49Z hiroki $ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="<spring:theme code="registerstylesheet"/>" media="screen" rel="stylesheet" type="text/css" />
  
<title><spring:message code="checkcompatibility.title" /></title>

<script type="text/javascript" src="./javascript/tels/jquery-1.4.1.min.js"></script>
<script type="text/javascript" src="./javascript/tels/utils.js"></script>
<script type="text/javascript" src="./javascript/tels/general.js"></script>
<script type="text/javascript" src="./javascript/tels/browserdetect.js"></script>
<script type="text/javascript" src="./javascript/tels/deployJava.js"></script>
<script type="text/javascript" src="./javascript/tels/compatibilityCheck.js"></script>

<link rel="shortcut icon" href="./themes/tels/default/images/favicon_panda.ico" /> 

</head>

<body onload='checkCompatibility(${specificRequirements})'>

<div id="centeredDiv">

<%@ include file="headermain_nousername.jsp"%>

	<div align='center'>
	
		<table border='1'>
			<tr>
				<td>Resource</td>
				<td>Required Version</td>
				<td>Your Version</td>
				<td>Requirement Satisfied</td>
				<td>Additional Info</td>
			</tr>
			<noscript>
				<tr>
				<td>Javascript</td>
				<td>Enabled</td>
				<td>Disabled</td>
				<td><img src='./themes/tels/default/images/error_16.gif' /></td>
				<td><a href='https://www.google.com/support/adsense/bin/answer.py?answer=12654'>How to enable Javascript</a></td>
				</tr>
			</noscript>
			<tr>
				<td id='javascriptResource'></td>
				<td id='javascriptRequiredVersion'></td>
				<td id='javascriptYourVersion'></td>
				<td id='javascriptRequirementSatisfied'></td>
				<td id='javascriptAdditionalInfo'></td>
			</tr>
			<tr>
				<td id='browserResource'></td>
				<td id='browserRequiredVersion'></td>
				<td id='browserYourVersion'></td>
				<td id='browserRequirementSatisfied'></td>
				<td id='browserAdditionalInfo'></td>
			</tr>
			<tr>
				<td id='quickTimeResource'></td>
				<td id='quickTimeRequiredVersion'></td>
				<td id='quickTimeYourVersion'></td>
				<td id='quickTimeRequirementSatisfied'></td>
				<td id='quickTimeAdditionalInfo'></td>
			</tr>
			<tr>
				<td id='flashResource'></td>
				<td id='flashRequiredVersion'></td>
				<td id='flashYourVersion'></td>
				<td id='flashRequirementSatisfied'></td>
				<td id='flashAdditionalInfo'></td>
			</tr>
			<tr>
				<td id='javaResource'></td>
				<td id='javaRequiredVersion'></td>
				<td id='javaYourVersion'></td>
				<td id='javaRequirementSatisfied'></td>
				<td id='javaAdditionalInfo'></td>
			</tr>
		</table>
	</div>

</div>   <!-- end of centered div-->

</body>
</html>


