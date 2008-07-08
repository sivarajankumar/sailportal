<%@ include file="include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
</head>
<body>
<div>Run name: ${run.sdsOffering.name}</div>
<c:forEach var="period" varStatus="periodStatus" items="${periods}">
  <div>Period Name: ${period.name}</div>
  <ul>  
    <c:forEach var="student" varStatus="studentStatus" items="${period.members}">
      <li>
        ${student.userDetails.firstname} ${student.userDetails.lastname}        
      </li>
    </c:forEach>      
  </ul>
</c:forEach>
</body>
</html>