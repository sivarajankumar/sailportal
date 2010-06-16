<%@ include file="include.jsp"%>

<!-- $Id: signup.jsp 323 2007-04-21 18:08:49Z hiroki $ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="<spring:theme code="registerstylesheet"/>" media="screen" rel="stylesheet" type="text/css" />
  
<title><spring:message code="checkcompatibility.title" /></title>

<script type="text/javascript" src="./javascript/pas/utils.js"></script> 
<script type="text/javascript" src="./javascript/tels/general.js"></script>
<script type="text/javascript" src="./javascript/tels/browserdetect.js"></script>
<script type="text/javascript" src="./javascript/tels/deployJava.js"></script>
<script type="text/javascript" src="./javascript/tels/compatibilityCheck.js"></script>

<link rel="shortcut icon" href="./themes/tels/default/images/favicon_panda.ico" /> 

<script type="text/javascript">
var jsver = 1.0;
</script>
<script language="Javascript1.1">
jsver = 1.1;
</script>
<script language="Javascript1.2">
jsver = 1.2;
</script>
<script language="Javascript1.3">
jsver = 1.3;
</script>
<script language="Javascript1.4">
jsver = 1.4;
</script>
<script language="Javascript1.5">
jsver = 1.5;
</script>
<script language="Javascript1.6">
jsver = 1.6;
</script>

</head>

<body onload='checkCompatibility()'>

<div id="centeredDiv">

<%@ include file="headermain_nousername.jsp"%>

	<div align='center'>
	
		<table border='1'>
			<tr>
				<td>Resource</td>
				<td>Required Version</td>
				<td>Your Version</td>
				<td>Requirement Met</td>
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
				<td id='javascriptRequirementMet'></td>
				<td id='javascriptAdditionalInfo'></td>
				</tr>
			<tr>
				<td id='browserResource'></td>
				<td id='browserRequiredVersion'></td>
				<td id='browserYourVersion'></td>
				<td id='browserRequirementMet'></td>
				<td id='browserAdditionalInfo'></td>
			</tr>
			<tr>
				<td id='quickTimeResource'></td>
				<td id='quickTimeRequiredVersion'></td>
				<td id='quickTimeYourVersion'></td>
				<td id='quickTimeRequirementMet'></td>
				<td id='quickTimeAdditionalInfo'></td>
			</tr>
			<tr>
				<td id='flashResource'></td>
				<td id='flashRequiredVersion'></td>
				<td id='flashYourVersion'></td>
				<td id='flashRequirementMet'></td>
				<td id='flashAdditionalInfo'></td>
			</tr>
			<tr>
				<td id='javaResource'></td>
				<td id='javaRequiredVersion'></td>
				<td id='javaYourVersion'></td>
				<td id='javaRequirementMet'></td>
				<td id='javaAdditionalInfo'></td>
			</tr>
		</table>
	</div>

</div>   <!-- end of centered div-->

</body>
</html>


