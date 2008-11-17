<%@ include file="../include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>View Announcements</title>

<%@ include file="styles.jsp"%>

<script src=".././javascript/tels/general.js" type="text/javascript" > </script>
<script src=".././javascript/tels/prototype.js" type="text/javascript" > </script>
<script src=".././javascript/tels/effects.js" type="text/javascript" > </script>

<script>
var getNewAnnouncements = function(dialog){
    	var newAnnouncement = false;
    	var announcementHTML = "";
    	<c:forEach var="runInfo" items="${current_run_list}">
    		<c:forEach var="announcement" items="${runInfo.run.announcements}">
    			<c:if test="${user.userDetails.lastLoginTime < announcement.timestamp || user.userDetails.lastLoginTime == null}">
    				newAnnouncement = true;
    				announcementHTML = announcementHTML + "<tr><td align='center'><h3>${announcement.title} (posted on:" + "${announcement.timestamp})</h3>" + "${announcement.announcement}<br><br></td></tr>";
   			</c:if>
    		</c:forEach>
    	</c:forEach>
    	
    	if(newAnnouncement){
    		document.getElementById('announcementsTable').innerHTML =  announcementHTML;
    		dialog.show();
    	};
    };
</script>

</head>

<body class="yui-skin-sam">

<%@ include file="./studentHeader.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<a href="index.html?showNewAnnouncements=false">Go back to homepage</a>
<c:forEach var="run" items="${runs}">
<h1>Announcements for ${run.sdsOffering.name}</h1>
<br>
<div id="existingAnnouncements">
	<c:choose>
		<c:when test="${fn:length(run.announcements) > 0}">
			<c:forEach var="announcement" items="${run.announcements}">
			    <c:choose>
			    <c:when test="${user.userDetails.lastLoginTime < announcement.timestamp || user.userDetails.lastLoginTime == null}">
			        <!--  new announcement, make it stand out Matt: please add css-->
    				<div class="newAnnouncement" style="border:2px"><b>NEW!!!</b> ${announcement.title} (posted on: ${announcement.timestamp}) <br/>${announcement.announcement}</div><br/>
   			    </c:when>
   			    <c:otherwise>
				<span id="head"><h4>${announcement.title} </h4> ${announcement.timestamp}</span>
				${announcement.announcement}
				</c:otherwise>
				</c:choose>
			</c:forEach>
		</c:when>
		<c:otherwise>
			No existing announcements found for this run.
		</c:otherwise>
	</c:choose>
</div>
</c:forEach>
<a href="index.html?showNewAnnouncements=false">Go back to homepage</a>

</body>
</html>