<%@ include file="../include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />


    
<script type="text/javascript" src="../../javascript/tels/general.js"></script>
<script type="text/javascript" src="../../javascript/tels/effects.js"></script>
<script type="text/javascript" src=".././javascript/tels/general.js"></script>
    
<title><spring:message code="application.title" /></title>

<script type='text/javascript' src='/webapp/dwr/engine.js'></script>
</head>
<body>
<%@ include file="adminheader.jsp"%>
<br>
Work with News Item!
<br>
<br>

<a href="#" onclick="javascript:popup640('addnewsitems.html');">Add a new News Item</a>

<br>
<br>

	<c:forEach var="news" items="${all_news}">
		<li>${news.title} ${news.date}
			<a href="#" onclick="javascript:popup640('editnewsitem.html?newsId=${news.id}');">Edit</a>
		</li>
	</c:forEach>
	
</body>
</html>