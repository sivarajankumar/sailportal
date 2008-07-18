<%@ include file="./include.jsp"%>

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
</head>

<body>
<%@ include file="headermain_nousername.jsp"%>
	
<a href="index.html"> <img id="return"
	src="<spring:theme code="return_to_homepage" />"
	onmouseover="swapImage('return', '<spring:theme code="return_to_homepage_roll" />');"
	onmouseout="swapImage('return', '<spring:theme code="return_to_homepage" />');" /></a>

<c:choose>
	<c:when test="${fn:length(all_news) > 0}">
		<table id="newsArchive" border="2" cellpadding="2" cellspacing="0" align="center">
			<h5>News Archives</h5>
			<tr>
				<th><h5>Title</h5></th><th><h5>Date</h5></th><th><h5>News</h5></th>
			</tr>
			<c:forEach var="news" items="${all_news}">
				<tr>
					<td>${news.title}</td>
					<td>${news.date}</td>
					<td>${news.news}</td>
				</tr>
			</c:forEach>   
		</table>
	</c:when>
	<c:otherwise>
		<h5>No News Items found</h5>
	</c:otherwise>
</c:choose>
</body>
</html>


