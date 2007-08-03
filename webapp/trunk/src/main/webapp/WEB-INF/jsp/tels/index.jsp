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

<%@ include file= "header.jsp"%>
<body onload="randomImages()">
<div class="north_6">
<div id="xsnazzy" class="widthAdj0 bgcolorSeaBlue">
<b class="xtop"><b class="xb1"></b><b class="xb2"></b><b class="xb3"></b><b class="xb4"></b></b>
<div class="border1">
<h4 class="heading2 north6"><spring:message code="wise.welcome" /></h4>
<p class="parastyle north_2"> <spring:message code="wise.about.desc" /></p>
<ul id="lineHeightAdj" class="none right5 north_3 marginVerAdj marginHorAdj">
<li>
	<a href="wiseoverview.html">
		<img id="imgBorderNone" src="<spring:theme code="overview" />" 
		onmouseover="swapWiseImage('imgBorderNone',1);"
		onmouseout="swapWiseImage('imgBorderNone',0);"
		
		/>
	</a>	
</li>

<li>
	<a href="#" onclick="javascript:displayNothingYet('notexisting.html')"> 
		<img id="imgBorder2None" src="<spring:theme code="preview" />"
		onmouseover="swapWiseImage('imgBorder2None',3);"
		onmouseout="swapWiseImage('imgBorder2None',2);"
		/>
	</a> 
</li>

<li>
	<a href="#" onclick="javascript:displayNothingYet('notexisting.html')"> 
		<img id="imgBorder3None" src="<spring:theme code="common" />" 
		onmouseover="swapWiseImage('imgBorder3None',5);"
		onmouseout="swapWiseImage('imgBorder3None',4);"
	    />
	</a> 
</li>

<li>
	<a href="signup.html">
		<img id="imgBorder4None" src="<spring:theme code="join" />" 
		onmouseover="swapWiseImage('imgBorder4None',7);"
		onmouseout="swapWiseImage('imgBorder4None',6);"
	    />
	</a> 
</li>
	
</ul>
</div>
<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b class="xb2"></b><b class="xb1"></b></b>
</div>

<div id="xsnazzy" class="width1 north8 spacingVerAdj0">
<b class="xtop"><b class="xb1"></b><b class="xb2"></b><b class="xb3"></b><b class="xb4"></b></b>
<div class="border1">
<form id="home" method="post" action="j_acegi_security_check">
<h4 class="heading2 north6"><spring:message code="wise.signIn" /></h4>
<ul id="lineHeightAdj1" class="none north_5 marginVerAdj5">
<li>
<label for="username" class="login">
<spring:message code="login.username" />
</label>
<input type="text" name="j_username" id="j_username" size="20"/>
</li>
<li>
<label for="password" class="login">
<spring:message code="login.password" />
</label>
<input type="password" name="j_password" id="j_password" size="20" />
</li>
<li id="waiting" style="display:none" class="marginAdj5">
       		<img id="wait" src="<spring:theme code="wait"/>" alt="<spring:message code="wise.wait.alttext" />" 
       		/> 
</li>
<li class="_right1 marginHorAdj">
 <input type="image" id="signIn" src="<spring:theme code="sign_in" />" 
        	onmouseover="swapSignIn('signIn', 1);"
       		onmouseout="swapSignIn('signIn', 0);"
            onclick="Effect.toggle('waiting', 'appear')" /> 
</li>

</ul>

</form>
<a href="lostpasswordmain.html" class="right6 smallFont1 widthAdj2">
<spring:message code="forgotten.username-or-password" /> 
</a>

</div>

<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b class="xb2"></b><b class="xb1"></b></b>
</div>
</div>
<div class="north7">
<div id="xsnazzy" class="widthAdj5">
<b class="xtop"><b class="xb1"></b><b class="xb2"></b><b class="xb3"></b><b class="xb4"></b></b>

<div class="border1">
<h4 class="heading2 north6 ">
<spring:message code="wise.inAction" />
</h4>
<img id="rotator" class="marginVerAdj1 right4 north0" src="<spring:theme code="wise_action" />" />
<img id="imgPos0" class="dynamicImage" 
src="<spring:theme code="arrow_prev" />" 
onmouseover="this.style.cursor='pointer';" 
onmousedown="this.style.cursor='pointer';" 
onmouseup="this.style.cursor='pointer';"
onmouseout="this.style.cursor='default';" 
onclick="firstLClicked = setLClicked();
oldCtr=counter;
counter=proceedToPreviousImage(firstLClicked,counter);
prevCircle = moveCircle(counter,0);"   
/>
<img id="imgPos1" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" 
onmouseover="swapImage('imgPos1',1)" 
onmouseout = "onOrOff(prevClick,0,'imgPos1');"
onclick="oldCtr = counter; 
counter=swapBigImage(0,counter,'Action');
prevClick = getPrevClick(prevClick,0,oldCtr);" 
/>
<img id="imgPos2" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" 
onmouseover="swapImage('imgPos2',1)" 
onmouseout="onOrOff(prevClick,1,'imgPos2')" 
onclick="oldCtr = counter; 
counter=swapBigImage(1,counter,'Action');
prevClick = getPrevClick(prevClick,1,oldCtr);" 
/>
<img id="imgPos3" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" 
onmouseover="swapImage('imgPos3',1)" 
onmouseout="onOrOff(prevClick,2,'imgPos3')" 
onclick="oldCtr = counter; 
counter=swapBigImage(2,counter,'Action');
prevClick = getPrevClick(prevClick,2,oldCtr);" 
/>
<img id="imgPos4" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" 
onmouseover="swapImage('imgPos4',1)" 
onmouseout="onOrOff(prevClick,3,'imgPos4')" 
onclick="oldCtr = counter; 
counter=swapBigImage(3,counter,'Action');
prevClick = getPrevClick(prevClick,3,oldCtr);" 
/>
<img id="imgPos5" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" 
onmouseover="swapImage('imgPos5',1)" 
onmouseout="onOrOff(prevClick,4,'imgPos5')" 
onclick="oldCtr = counter; 
counter=swapBigImage(4,counter,'Action');
prevClick = getPrevClick(prevClick,4,oldCtr);" 
/>
<img id="imgPos6" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" 
onmouseover="swapImage('imgPos6',1)" 
onmouseout="onOrOff(prevClick,5,'imgPos6')" 
onclick="oldCtr = counter; 
counter=swapBigImage(5,counter,'Action');
prevClick = getPrevClick(prevClick,5,oldCtr);" 
/>
<img id="imgPos7" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" 
onmouseover="swapImage('imgPos7',1)" 
onmouseout="onOrOff(prevClick,6,'imgPos7')" 
onclick="oldCtr = counter; 
counter=swapBigImage(6,counter,'Action');
prevClick = getPrevClick(prevClick,6,oldCtr);" 
/>
<img id="imgPos8" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" 
onmouseover="swapImage('imgPos8',1)" 
onmouseout="onOrOff(prevClick,7,'imgPos8')" 
onclick="oldCtr = counter; 
counter=swapBigImage(7,counter,'Action');
prevClick = getPrevClick(prevClick,7,oldCtr);" 
/>
<img id="imgPos9" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" 
onmouseover="swapImage('imgPos9',1)" 
onmouseout="onOrOff(prevClick,8,'imgPos9')" 
onclick="oldCtr = counter; 
counter=swapBigImage(8,counter,'Action');
prevClick = getPrevClick(prevClick,8,oldCtr);" 
/>
<img id="imgPos10" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" 
onmouseover="swapImage('imgPos10',1)" 
onmouseout="onOrOff(prevClick,9,'imgPos10')" 
onclick="oldCtr = counter; 
counter=swapBigImage(9,counter,'Action');
prevClick = getPrevClick(prevClick,9,oldCtr);" 
/>
<img id="imgPos11" class="dynamicImage" src="<spring:theme code="arrow_next" />" 
onmouseover="this.style.cursor='pointer';" 
onmousedown="this.style.cursor='pointer';" 
onmouseup="this.style.cursor='pointer';"
onmouseout="this.style.cursor='default';" 
onclick="firstRClicked = setRClicked();
oldCtr=counter;
counter=proceedToNextImage(firstRClicked,counter);
prevClick = moveCircle(counter,1);"
/>
</div>

<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b class="xb2"></b><b class="xb1"></b></b>
</div>


<div id="xsnazzy" class="_right4 widthAdj5 ">
<b class="xtop"><b class="xb1"></b><b class="xb2"></b><b class="xb3"></b><b class="xb4"></b></b>
<div class="border1">
<h4 class="heading2 north6">
<spring:message code="wise.testimonials" />
</h4>
<img id="rotatorT" class="right4 north0 marginVerAdj5 " src="<spring:theme code="wise_testimonials" />" />
<img id="imgPos12" class="dynamicImage" src="<spring:theme code="arrow_prev" />" 
onmouseover="this.style.cursor='pointer';" 
onclick="firstLClicked_T = setLClicked_T();oldCtr_T=counter_T;"
onmousedown="this.style.cursor='pointer';" 
onmouseup="this.style.cursor='pointer';"
onmouseout="this.style.cursor='default';" />
<img id="imgPos13" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" />
<img id="imgPos14" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" />
<img id="imgPos15" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" />
<img id="imgPos16" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" />
<img id="imgPos17" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" />
<img id="imgPos18" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" />
<img id="imgPos19" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" />
<img id="imgPos20" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" />
<img id="imgPos21" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" />
<img id="imgPos22" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" />
<img id="imgPos23" class="dynamicImage" 
src="<spring:theme code="arrow_next" />" 
onmouseover="this.style.cursor='pointer';" 
onclick="firstRClicked_T=setRClicked_T();oldCtr_T=counter_T;"
onmousedown="this.style.cursor='pointer';" 
onmouseup="this.style.cursor='pointer';"
onmouseout="this.style.cursor='default';" />

</div>
<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b class="xb2"></b><b class="xb1"></b></b>

</div>

<div id="xsnazzy" class="_right3 width1">
<b class="xtop"><b class="xb1"></b><b class="xb2"></b><b class="xb3"></b><b class="xb4"></b></b>
<div class="border1">
<h4 class="heading2 north6">
<spring:message code="wise.latestNews" />
</h4>
<textarea class="marginVerAdj2 north6" rows="12" cols="35"><spring:message code="wise.latestNews" /> </textarea>
<a href="#"> 
	<img id="newsArchive" style="border:0px;" class="_right1" src="<spring:theme code="newsarchive" />" 
       	 onmouseover="this.style.cursor='pointer';swapNewsImage('newsArchive', 1);"
       	 onmouseout="this.style.cursor='default';swapNewsImage('newsArchive', 0);"       	       			
	/>
</a>
</div>
<b class="xbottom"><b class="xb4"></b><b class="xb3"></b><b class="xb2"></b><b class="xb1"></b></b>

</div>

</div>

<div class="_right1 marginHorAdj1 padding0 south0 widthAdj3">
<img id="nsfImage" src="<spring:theme code="nsf_tiny_logo"/>" 
class="center"/>
<spring:message code="wise.legal-acknowledgment" /> 
<a href="http://www.nsf.gov" target="_blank"> <spring:message code="nsf" /></a>.
<spring:message code="wise.copyright" /> &nbsp;&nbsp;&nbsp; 
<a href="#"><spring:message code="wise.contact" /></a> 

</div>

<!-- Support for Spring errors object -->

</body>
</html>
