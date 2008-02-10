<%@ include file="../include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />


    
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
View all users:

<c:forEach var="user" items="${all_users_list}">
<li>${user.userDetails.firstname} ${user.userDetails.lastname}
<a href="#" onclick="javascript:popup640('../teacher/management/changestudentpassword.html?userName=${user.userDetails.username}');">Password</a>
</li>
</c:forEach>


</body>
</html>