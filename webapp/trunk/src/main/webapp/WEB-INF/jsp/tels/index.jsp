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

<div id="verticalNavigation" class="bgcolorLightBlue">
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
<input type="text" width="20" />
</li>

<li>
<label for="password" class="login">
<spring:message code="login.password" />
</label>
<input type="text" width="20" />
</li>
<li><a href="#"> <spring:message code="wise.signIn" /> </a> </li>
</ul>

</form:form>

</div>

<div id="navigation" class="bgcolorLightBlue">
<ul class="southHeading">
<li class="heading4"><spring:message code="wise.inAction" /></li>
<li class="heading4"><spring:message code="wise.testimonials" /></li>
<li class="heading4"><spring:message code="wise.latestNews" /></li>
</ul>

<div class="inline bgcolorLightBlue">
<img class="marginAdj1 widthAdj1 positionAdj1"  id=rotator name=rotator src="./themes/tels/default/images/wiseInAction/AirBag.jpg">
<img class="marginAdj1 widthAdj1 positionAdj2" id=rotatorT name=rotatorT src="./themes/tels/default/images/wiseInAction/dummyfile.jpg"> 
<b class="marginAdj1 widthAdj1 positionAdj4"> news </b> 
</div>

<div class="inline bgcolorLightBlue">
<img class="dynamicImage marginAdj1" id="imgPos1" name="action_prev" src="./themes/tels/default/images/wiseInAction/arrow_prev.gif" 
onMouseOver="this.style.cursor='pointer';" 
onClick="firstLClicked = setLClicked();oldCtr=counter;counter=proceedToPreviousImage(firstLClicked,counter);"
onMouseDown="this.style.cursor='pointer';" 
onMouseUp="this.style.cursor='pointer';"
onMouseOut="this.style.cursor='default';" />

<img class="dynamicImage marginAdj1" id="imgPos2" name="action_fwd" src="./themes/tels/default/images/wiseInAction/arrow_forward.gif" 
onMouseOver="this.style.cursor='pointer';" 
onClick="firstRClicked=setRClicked();oldCtr=counter;counter=proceedToNextImage(firstRClicked,counter);"
onMouseDown="this.style.cursor='pointer';" 
onMouseUp="this.style.cursor='pointer';"
onMouseOut="this.style.cursor='default';" />

<img class="dynamicImage marginAdj1" id="imgPos3"  name="test_prev" src="./themes/tels/default/images/wiseInAction/arrow_prev.gif"
onMouseOver="this.style.cursor='pointer';" 
onClick="firstLClicked_T = setLClicked_T();oldCtr_T=counter_T;counter_T=proceedToPreviousImage_T(firstLClicked_T,counter_T);"
onMouseDown="this.style.cursor='pointer';" 
onMouseUp="this.style.cursor='pointer';"
onMouseOut="this.style.cursor='default';" />

<img class="dynamicImage marginAdj1" id="imgPos4" name="test_fwd" src="./themes/tels/default/images/wiseInAction/arrow_forward.gif" 
onMouseOver="this.style.cursor='pointer';" 
onClick="firstRClicked_T=setRClicked_T();oldCtr_T=counter_T;counter_T=proceedToNextImage_T(firstRClicked_T,counter_T);"
onMouseDown="this.style.cursor='pointer';" 
onMouseUp="this.style.cursor='pointer';"
onMouseOut="this.style.cursor='default';" />







</div>

</div>


</div>

</body>
</html>
