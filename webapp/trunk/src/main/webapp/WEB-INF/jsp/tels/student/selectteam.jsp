<%@ include file="../include.jsp"%>
<!--
  * Copyright (c) 2006 Encore Research Group, University of Toronto
  * 
  * This library is free software; you can redistribute it and/or
  * modify it under the terms of the GNU Lesser General Public
  * License as published by the Free Software Foundation; either
  * version 2.1 of the License, or (at your option) any later version.
  *
  * This library is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
  * Lesser General Public License for more details.
  *
  * You should have received a copy of the GNU Lesser General Public
  * License along with this library; if not, write to the Free Software
  * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
-->

<!-- $Id$ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link href="<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet"
    type="text/css" />
<title><spring:message code="application.title" /></title>
</head>

<body>

<h1>SELECT TEAM</h1>
<h3>Will you be working on this project by yourself or with teammates?</h3>

<div>
  <c:forEach var="run" items="${run_list}">
      <c:choose>
        <c:when test="${fn:length(workgroup_map[run]) == 0}" >
        <spring:message code="not.in.workgroup.yet" />
        </c:when>
        <c:otherwise>
            <c:forEach var="workgroup" items="${workgroup_map[run]}">
              <a href="${http_transport.baseUrl}/offering/${run.sdsOffering.sdsObjectId}/jnlp/${workgroup.sdsWorkgroup.sdsObjectId}">
                By Myself
                <!--  
		        <img id="runproject" src="../<spring:theme code="run_project" />"  />              
		        -->
              </a><br />
            </c:forEach>
        </c:otherwise>
      </c:choose>
  </c:forEach>
</div>
<div><a href="#">With 1-2 Teammates</a></div>
<div><a href="#" onclick="javascript:window.close()">Cancel</a></div>
</body>
</html>
