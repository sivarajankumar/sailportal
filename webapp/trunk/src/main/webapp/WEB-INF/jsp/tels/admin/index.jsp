<%@ include file="../include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="../../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet" type="text/css" />
<link href="../../<spring:theme code="teacherprojectstylesheet" />" media="screen" rel="stylesheet" type="text/css" />
    
<script type="text/javascript" src="../../javascript/tels/general.js"></script>
<script type="text/javascript" src="../../javascript/tels/effects.js"></script>
<script type="text/javascript" src=".././javascript/tels/general.js"></script>

    
<title><spring:message code="application.title" /></title>

<script type='text/javascript' src='/webapp/dwr/interface/ChangePasswordParametersValidatorJS.js'></script>
<script type='text/javascript' src='/webapp/dwr/engine.js'></script>
<script>
//alert('hi');
//alert(ChangePasswordParametersValidatorJS.test('hi'))
</script>


</head>
<body>
<%@ include file="adminheader.jsp"%>


If you see this, you have admin privileges.<br>

<h2>User Management</h2>
<a href="manageusers.html">Manage Users</a><br>
<a href="lookupuser.html">Look up User</a><br>

<h2>Project Management</h2>
<a href="manageallprojects.html">Manage All Projects</a><br>

<h2>Project Run Management</h2>
<a href="manageallprojectruns.html">Manage All Project Runs</a>


<h2>Other</h2>
<a href="managenewsitems.html">Work with News Items</a><br>

</body>
</html>