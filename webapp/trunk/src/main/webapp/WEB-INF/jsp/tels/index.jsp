<%@ include file="include.jsp"%>
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
    type="text/css" href="./themes/tels/default/styles/styles.css"/>
<script src="./javascript/tels/prototype.js" type="text/javascript" ></script>
<script src="./javascript/tels/scriptaculous.js" type="text/javascript" ></script>
<script src="./javascript/tels/rotator.js" type="text/javascript" > </script>
<script src="./javascript/tels/rotatorT.js" type="text/javascript" > </script>

<title><spring:message code="application.title" /></title>
</head>

<body class="bgcolorLightBlue">

<%@ include file= "header.jsp"%>

<!-- Support for Spring errors object -->
<div id="verticalNavigation" class="north3 widthAdj3 border bgcolorLightBlue">
<h4 class="heading2 north3"><spring:message code="wise.welcome" /></h4>
<p class="parastyle bgcolorLightBlue north_3">
<spring:message code="wise.about.desc" />
</p>
<ul class="intropos">
<li><a href="wiseoverview.html"><spring:message code="wise.overview" /></a></li>
<li><a href="#"> <spring:message code="wise.preview" /></a> </li>
<li><a href="#"> <spring:message code="wise.commonQs" /></a></li>
<li><a href="register.html"><b><spring:message code="joinwise" /></b></a></li>
</ul>
</div>

<form:form id="home" method="post" action="j_acegi_security_check">
<ul id="verticalNavigation" class="none bgcolorLightBlue border right3 north4 widthAdj2 marginAdj0 padding0">
<li id="left" class="heading2 north6"><spring:message code="wise.signIn" /></li>
<li class="login north_2">
<label for="username" class="login">
<spring:message code="login.username" />
</label>
<input type="text" size="20" />
</li>
<li class="login north_2">
<label for="password" class="login">
<spring:message code="login.password" />
</label>
<input type="password" size="20" />
</li>
<li class="right north_2"><a href="#"> <spring:message code="wise.signIn" /> </a> </li>

</ul>
<a href="lostpasswordmain.html" class="bgcolorLightBlue right4 widthAdj2 north5">
<spring:message code="forgotten.username-or-password" /> 
</a>
</form:form>
<div id="south" class="padding3 marginAdj3 border widthAdj5">
<h4 class="heading2 north3">
<spring:message code="wise.inAction" />
</h4>
<ul class="none">
<li id="imgPos5" class="marginAdj5"><img id="rotator" class="widthAdj1 border"  src="./themes/tels/default/images/wiseInAction/AirBag.jpg" /></li>
</ul>
<img class="dynamicImage marginAdj3" id="imgPos1" src="./themes/tels/default/images/wiseInAction/arrow_prev.gif" 
onmouseover="this.style.cursor='pointer';" 
onclick="firstLClicked = setLClicked();oldCtr=counter;counter=proceedToPreviousImage(firstLClicked,counter);"
onmousedown="this.style.cursor='pointer';" 
onmouseup="this.style.cursor='pointer';"
onmouseout="this.style.cursor='default';" />

<img class="dynamicImage marginAdj3" id="imgPos2" src="./themes/tels/default/images/wiseInAction/arrow_forward.gif" 
onmouseover="this.style.cursor='pointer';" 
onclick="firstRClicked=setRClicked();oldCtr=counter;counter=proceedToNextImage(firstRClicked,counter);"
onmousedown="this.style.cursor='pointer';" 
onmouseup="this.style.cursor='pointer';"
onmouseout="this.style.cursor='default';" />

</div>
<div class="padding3 south0 right5 marginAdj4 widthAdj5 border">
<h4 class="heading2 north3">
<spring:message code="wise.testimonials" />
</h4>
<ul class="none">
<li id="imgPos6"><img id="rotatorT" class="widthAdj1 border" src="./themes/tels/default/images/wiseInAction/dummyfile.jpg" />
</ul>
<img class="dynamicImage marginAdj1" id="imgPos3" src="./themes/tels/default/images/wiseInAction/arrow_prev.gif"
onmouseover="this.style.cursor='pointer';" 
onclick="firstLClicked_T = setLClicked_T();oldCtr_T=counter_T;counter_T=proceedToPreviousImage_T(firstLClicked_T,counter_T);"
onmousedown="this.style.cursor='pointer';" 
onmouseup="this.style.cursor='pointer';"
onmouseout="this.style.cursor='default';" />

<img class="dynamicImage marginAdj1" id="imgPos4" src="./themes/tels/default/images/wiseInAction/arrow_forward.gif" 
onmouseover="this.style.cursor='pointer';" 
onclick="firstRClicked_T=setRClicked_T();oldCtr_T=counter_T;counter_T=proceedToNextImage_T(firstRClicked_T,counter_T);"
onmousedown="this.style.cursor='pointer';" 
onmouseup="this.style.cursor='pointer';"
onmouseout="this.style.cursor='default';" />

</div>
<div id="right2" class="border south0 marginAdj6 nopadding widthAdj0">
<h4 class="heading2 north3">
<spring:message code="wise.latestNews" />
</h4>
</div>

</body>
</html>

<!--

<div class="padding3 marginAdj3 border widthAdj5">
<h4 class="heading2 north3">
<spring:message code="wise.inAction" />
</h4>
<ul class="none">
<li id="imgPos5" class="space1"><img id="rotator" class="widthAdj1 border"  src="./themes/tels/default/images/wiseInAction/AirBag.jpg" /></li>
</ul>
<img class="dynamicImage marginAdj1" id="imgPos1" src="./themes/tels/default/images/wiseInAction/arrow_prev.gif" 
onmouseover="this.style.cursor='pointer';" 
onclick="firstLClicked = setLClicked();oldCtr=counter;counter=proceedToPreviousImage(firstLClicked,counter);"
onmousedown="this.style.cursor='pointer';" 
onmouseup="this.style.cursor='pointer';"
onmouseout="this.style.cursor='default';" />

<img class="dynamicImage marginAdj1" id="imgPos2" src="./themes/tels/default/images/wiseInAction/arrow_forward.gif" 
onmouseover="this.style.cursor='pointer';" 
onclick="firstRClicked=setRClicked();oldCtr=counter;counter=proceedToNextImage(firstRClicked,counter);"
onmousedown="this.style.cursor='pointer';" 
onmouseup="this.style.cursor='pointer';"
onmouseout="this.style.cursor='default';" />

</div>


<div class="padding3 south0 right5 marginAdj4 widthAdj5 border">
<h4 class="heading2 north3">
<spring:message code="wise.testimonials" />
</h4>
<ul class="none">
<li id="imgPos6" class="space1"><img id="rotatorT" class="widthAdj1 border" src="./themes/tels/default/images/wiseInAction/dummyfile.jpg" />
</ul>
<img class="dynamicImage marginAdj1" id="imgPos3" src="./themes/tels/default/images/wiseInAction/arrow_prev.gif"
onmouseover="this.style.cursor='pointer';" 
onclick="firstLClicked_T = setLClicked_T();oldCtr_T=counter_T;counter_T=proceedToPreviousImage_T(firstLClicked_T,counter_T);"
onmousedown="this.style.cursor='pointer';" 
onmouseup="this.style.cursor='pointer';"
onmouseout="this.style.cursor='default';" />

<img class="dynamicImage marginAdj1" id="imgPos4" src="./themes/tels/default/images/wiseInAction/arrow_forward.gif" 
onmouseover="this.style.cursor='pointer';" 
onclick="firstRClicked_T=setRClicked_T();oldCtr_T=counter_T;counter_T=proceedToNextImage_T(firstRClicked_T,counter_T);"
onmousedown="this.style.cursor='pointer';" 
onmouseup="this.style.cursor='pointer';"
onmouseout="this.style.cursor='default';" />

</div>


<div id="right2" class="border north_1 widthAdj0">
<h4 class="heading2">
<spring:message code="wise.latestNews" />
</h4>
<textarea cols=35 rows=12> here is some news </textarea>
</div>



-->
