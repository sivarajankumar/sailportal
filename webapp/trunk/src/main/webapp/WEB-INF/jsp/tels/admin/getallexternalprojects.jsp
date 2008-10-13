<%@ include file="../include.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" />
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet" type="text/css" />
<link href="../<spring:theme code="teacherprojectstylesheet" />" media="screen" rel="stylesheet" type="text/css" />
<link href="../<spring:theme code="teacherhomepagestylesheet" />" media="screen" rel="stylesheet" type="text/css" />
    
<script type="text/javascript" src="../javascript/tels/general.js"></script>
    
<title><spring:message code="application.title" /></title>

<script type='text/javascript' src='/webapp/dwr/interface/ChangePasswordParametersValidatorJS.js'></script>
<script type='text/javascript' src='/webapp/dwr/engine.js'></script>
<script>
//alert('hi');
//alert(ChangePasswordParametersValidatorJS.test('hi'))
</script>


<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>Google Maps JavaScript API Example</title>
    <script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAA6i9SCA124FYnecWsJguS7hT2yXp_ZAY8_ufC3CFXhHIE1NvwkxS8pFGwdBavxNvBCxzMH5fKjlSmVQ"
      type="text/javascript"></script>
    <script type="text/javascript">
    var map;               
    
    //<![CDATA[
    function load() {
      if (GBrowserIsCompatible()) {
        map = new GMap2(document.getElementById("map"));
        map.addControl(new GLargeMapControl());
        map.setCenter(new GLatLng(39.095963,-100.195312), 3);

        var concordpoint = new GLatLng(46.042736,-71.279297);
        var concordmarker = new GMarker(concordpoint, {clickable:true});

        GEvent.addListener(concordmarker, "click", function() {
              concordmarker.openInfoWindowHtml("${infoWindowHTML}, <b>Concord, Massachusetts</b><a href='../previewproject.html?projectType=diy&externalId=1&projectCommunicatorId=1'>Preview</a>");
        });

        map.addOverlay(concordmarker);
        
      }
    }

    function showAddress(address) {
        alert("address: " + address);
    	var map = new GMap2(document.getElementById("map"));
    	var geocoder = new GClientGeocoder();
        geocoder.getLatLng(
          address,
          function(point) {
            if (!point) {
              alert(address + " not found");
            } else {
                alert(address + " found. point: " + point);
              map.setCenter(point, 7);
              var marker = new GMarker(point);
              map.addOverlay(marker);
              marker.openInfoWindowHtml(address);
            }
          }
        );
      }

    //]]>
    </script>

</head>
<body onload="load()" onunload="GUnload()">
    <div id="map" style="width: 600px; height: 400px"></div>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="centeredDiv">

<%@ include file="adminheader.jsp"%>

<h5 style="color:#0000CC;"><a href="index.html">Return to Main Menu</a></h5>

<c:out value="${message}" />

<h2>External Projects</h2>
<table id="adminManageProjectsTable">
	<tr>
		<th> Project Title </th>
		<th> Preview Project </th>
		<th> Import Project to Library </th>		
	</tr>
	<c:forEach var="project" items="${externalProjectList}">
	<c:set var="infoWindowHTML" value="<b>Name:${project.name}</b><a href='../previewproject.html?projectType=diy&externalId=${project.externalId}&projectCommunicatorId=${project.projectCommunicator.id}'></a>" />
	<tr>
		<td>${project.name}</td>		
		<td><a href="../previewproject.html?projectType=diy&externalId=${project.externalId}&projectCommunicatorId=${project.projectCommunicator.id}">Preview</a></td>		
		<td><a href="importexternalproject.html?projectType=diy&externalId=${project.externalId}&projectCommunicatorId=${project.projectCommunicator.id}">Import</a></td>				
	</tr>
	</c:forEach>
</table>

<a href="" onclick="javascript:showAddress('46.042736,-71.279297');">2537 Benvenue Ave CA 94704</a>





	

</body>
</html>