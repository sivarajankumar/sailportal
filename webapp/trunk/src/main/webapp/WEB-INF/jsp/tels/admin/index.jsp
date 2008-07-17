<%@ include file="../include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet" type="text/css" />
<link href="../<spring:theme code="teacherprojectstylesheet" />" media="screen" rel="stylesheet" type="text/css" />
<link href="../<spring:theme code="teacherhomepagestylesheet" />" media="screen" rel="stylesheet" type="text/css" />
    
<script src="../javascript/tels/general.js" 			type="text/javascript"> </script>
<script src="../javascript/tels/prototype.js" 			type="text/javascript"> </script>
<script src="../javascript/tels/effects.js" 			type="text/javascript"> </script>
<script src="../javascript/tels/scriptaculous.js" 		type="text/javascript"> </script>
<script src="../javascript/tels/rotator.js" 			type="text/javascript"> </script>
<script src="../javascript/tels/rotatorT.js" 			type="text/javascript"> </script>

    
<title><spring:message code="application.title" /></title>

<script type='text/javascript' src='/webapp/dwr/interface/ChangePasswordParametersValidatorJS.js'></script>
<script type='text/javascript' src='/webapp/dwr/engine.js'></script>

<script>
//alert('hi');
//alert(ChangePasswordParametersValidatorJS.test('hi'))
</script>

</head>

<body>

<div id="centeredDiv">

<%@ include file="adminheader.jsp"%>


<h2 style="color:#0000CC;margin-bottom:40px;">Welcome to the WISE Administrator Page</h2>


<div id="adminTitle">User Management</div>
	<div style="margin:0 0 0 25px;"> 
		<h5><a href="manageusers.html">Manage Users</a></h5>
		<h5 style="color:#999999;">Look up User</h5>
		<!-- <h5><a href="lookupuser.html">Look up User</a></h5>-->
	</div>

<div id="adminTitle">Project Management</div>
	<div style="margin:0 0 0 25px;">
		<h5><a href="manageallprojects.html">Manage All Projects</a></h5>
	</div>
	
<div id="adminTitle">Project Run Management</div>
	<div style="margin:0 0 0 25px;">
		<h5><a href="manageallprojectruns.html">Manage All Project Runs</a></h5>
		<h5><a href="findprojectruns.html">Find Project Runs</a></h5>
	</div>

<div id="adminTitle">Other</div>
	<div style="margin:0 0 0 25px;">
		<h5 style="color:#999999;">Work with News Items</h5>
		<!--//<a href="managenewsitems.html">Work with News Items</a>-->
	</div>
</div>

</body>
</html>