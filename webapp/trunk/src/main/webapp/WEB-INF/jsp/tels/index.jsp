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

<div id="columns" class="bgcolorLightBlue">

<div id="headRows">
<ul class="intropos">
<li class="heading2"><spring:message code="wise.welcome" /></li>
<li class="heading2 positionAdj3"><spring:message code="wise.signIn" /></li>
</ul>
</div>

<div id="verticalNavigation" class="border bgcolorLightBlue">
<p class="parastyle bgcolorLightBlue">
<spring:message code="wise.about.desc" />

</p>

<ul class="intropos">
<li><a href="#"><spring:message code="wise.overview" /></a></li>
<li><a href="#"> <spring:message code="wise.preview" /></a> </li>
<li><a href="#"> <spring:message code="wise.commonQs" /></a></li>
<li><a href="register.html"><b><spring:message code="joinwise" /></b></a></li>
</ul>

<form:form id="home" method="post" action="j_acegi_security_check">
<ul class="loginpos">

<li>
<label for="username" class="login">
<spring:message code="login.username" />
</label>
<input type="text" size="20" />
</li>

<li>
<label for="password" class="login">
<spring:message code="login.password" />
</label>
<input type="password" size="20" />
</li>
<li><a href="#"> <spring:message code="wise.signIn" /> </a> </li>
</ul>

</form:form>
<li>
lost your <a href="lostpasswordmain.html" >password</a>?
</li>
</div>

<div id="navigation" class="bgcolorLightBlue">
<ul class="southHeading">
<li class="heading4"><spring:message code="wise.inAction" /></li>
<li class="heading4"><spring:message code="wise.testimonials" /></li>
<li class="heading4"><spring:message code="wise.latestNews" /></li>
</ul>

<div class="inline bgcolorLightBlue">
<img class="marginAdj1 widthAdj1 positionAdj1 border"  id=rotator src="./themes/tels/default/images/wiseInAction/AirBag.jpg" />
<img class="marginAdj1 widthAdj1 positionAdj2 border" id=rotatorT src="./themes/tels/default/images/wiseInAction/dummyfile.jpg" /> 
<b class="marginAdj1 widthAdj1 positionAdj4 border"> news </b> 
</div>

<div class="inline bgcolorLightBlue">
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

</div>


</div>

</body>
</html>
