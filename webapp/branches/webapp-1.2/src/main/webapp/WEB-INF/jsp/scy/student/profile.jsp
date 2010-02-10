<%@ include file="../include.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>
    <%@ include file="../admin/adminhead.jsp" %>

</head>
<body>
<%@ include file="../admin/adminheader.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="centeredDiv">

    <form method="post" action="upload.html" enctype="multipart/form-data">
        <input type="file" name="file"/>
        <input type="submit"/>
    </form>

</div>

</div>  <!-- /* End of the CenteredDiv */-->

</body>

</html>




