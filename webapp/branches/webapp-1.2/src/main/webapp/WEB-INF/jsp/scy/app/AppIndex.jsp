<%@ include file="../include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>
<%@ include file="../admin/adminhead.jsp" %>

</head>
<body>
<%@ include file="../admin/adminheader.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="centeredDiv">
    <h1>Welcome to SCY!</h1>

    <c:if test="${rickRoll}">
        <object width="425" height="344"><param name="movie" value="http://www.youtube.com/v/oHg5SJYRHA0&hl=en_US&fs=1&"></param><param name="allowFullScreen" value="true"></param><param name="allowscriptaccess" value="always"></param><embed src="http://www.youtube.com/v/oHg5SJYRHA0&hl=en_US&fs=1&" type="application/x-shockwave-flash" allowscriptaccess="always" allowfullscreen="true" width="425" height="344"></embed></object>
    </c:if>

    <c:if test="${showProfilePicture}">
        <img src="${profilePictureUrl}" />
    </c:if>

    <p>
        <center>
            Click to start <a href="/extcomp/scy-lab.jnlp">SCYLab</a>
        </center>
    </p>

</div>

</body>
</html>

