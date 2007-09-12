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
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link href="<spring:theme code="homepagestylesheet"/>" media="screen" rel="stylesheet"
  type="text/css" />
<script type="text/javascript" src="./javascript/tels/general.js"></script>
<script src="./javascript/tels/prototype.js" type="text/javascript" > </script>
<script src="./javascript/tels/scriptaculous.js" type="text/javascript" > </script>
<script src="./javascript/tels/rotator.js" type="text/javascript" > </script>
<script src="./javascript/tels/rotatorT.js" type="text/javascript" > </script>
<title><spring:message code="application.title" /></title>
</head>

<!-- Support for Spring errors object -->
<%@ include file= "header.jsp"%>
<body onload="randomImages()">
<div id="welcome">
<div id="innerWelcome">
<h1 id="welcomeHeader">
<spring:message code="wise.welcome" />
</h1>
<p id="parastyle">  
	<spring:message code="wise.about.desc" />
</p>
<ul class="none" id="welcomeButtonPos" >
<li>
	<a href="wiseoverview.html">
		<img id="overview" src="<spring:theme code="overview" />" 
		onmouseover="swapImage('overview','<spring:theme code="overviewrollover" />');"
		onmouseout="swapImage('overview','<spring:theme code="overview" />');"		
		/>
	</a>	
</li>
<li>
	<a href="#" onclick="displayNotAvailable('This page is not available yet');"> 
		<img id="preview" src="<spring:theme code="preview" />"
		onmouseover="swapImage('preview','<spring:theme code="previewrollover" />');"
		onmouseout="swapImage('preview','<spring:theme code="preview" />');"
		/>
	</a> 
</li>
<li>
	<a href="#" onclick="displayNotAvailable('This page is not available yet');"> 
		<img id="commonQs" src="<spring:theme code="common" />" 
		onmouseover="swapImage('commonQs','<spring:theme code="commonrollover" />');"
		onmouseout="swapImage('commonQs','<spring:theme code="common" />');"
	    />
	</a> 
</li>
<li>
	<a href="signup.html">
		<img id="signup" src="<spring:theme code="join" />" 
		onmouseover="swapImage('signup','<spring:theme code="joinrollover" />');"
		onmouseout="swapImage('signup','<spring:theme code="join" />');"
	    />
	</a> 
</li>
</ul>
</div>
<div id="signInBox">

<form id="home" method="post" action="j_acegi_security_check">
<h1 id="signInPos"><spring:message code="wise.signIn" /></h1>
 <label for="username" id="homelogin">
 <spring:message code="login.username" />
  </label> 
	<input type="text" name="j_username" id="j_username" size="20"/>
<br />
	<label for="password" id="homelogin">
	<spring:message code="login.password" />
	</label>
	<input type="password" name="j_password" id="j_password" size="20" />
<br />

<img id="waiting" src="<spring:theme code="wait"/>" alt="<spring:message code="wise.wait.alttext" />" /> 
 
<input type="image" id="signIn" src="<spring:theme code="sign_in" />"  
        	onmouseover="swapImage('signIn','<spring:theme code="sign_in_rollover" />');"
        	onmouseout="swapImage('signIn','<spring:theme code="sign_in" />');"
       		onmouseout="swapImage('signIn','images/sign_in.png');"
            onclick="Effect.toggle('waiting', 'appear')" />
</form>
 
<a href="forgotaccount/selectaccounttype.html" id="forgot">
<spring:message code="forgotten.username-or-password" /> 
</a>
</div>
</div>

<div id="wiseInActionPos">
<div id="innerWiseInAction">
<h2 id="heading"><spring:message code="wise.inAction" /></h2>
<img id="rotator" src="<spring:theme code="wise_action" />" />
<div id="actionNav"> 
<img src="<spring:theme code="arrow_prev" />" class="dynamicImage" id="imgPos0" 
onmouseover="this.style.cursor='pointer';" 
onmousedown="this.style.cursor='pointer';" 
onmouseup="this.style.cursor='pointer';"
onmouseout="this.style.cursor='default';" 
onclick="firstLClicked = setLClicked();
oldCtr=counter;
counter=proceedToPreviousImage(firstLClicked,counter);
"   
/>
<input type="text" id="imageInput" value="1" style="display:none;" onchange="moveToImage('rotator',this.value);
oldCtr = counter; 
counter=swapBigImage(this.value,counter,'Action');
prevClick = getPrevClick(prevClick,this.value,oldCtr);"

/>
<a href="javascript:Effect.toggle('imageInput','appear')" id="imgPos6"> 1 of 10 </a>
<img id="imgPos11" class="dynamicImage" src="<spring:theme code="arrow_next" />" 
onmouseover="this.style.cursor='pointer';" 
onmousedown="this.style.cursor='pointer';" 
onmouseup="this.style.cursor='pointer';"
onmouseout="this.style.cursor='default';" 
onclick="firstRClicked = setRClicked();
oldCtr=counter;
counter=proceedToNextImage(firstRClicked,counter);"
/>

</div>
</div>
<div id="testimonialsPos">
<h2 id="heading">
<spring:message code="wise.testimonials" />
</h2>
<img id="rotatorT" src="<spring:theme code="wise_testimonials" />" />
<div id="testimonialsNav">
<img id="imgPos12" class="dynamicImage" src="<spring:theme code="arrow_prev" />" 
onmouseover="this.style.cursor='pointer';" 
onclick="firstLClicked_T = setLClicked_T();oldCtr_T=counter_T;"
onmousedown="this.style.cursor='pointer';" 
onmouseup="this.style.cursor='pointer';"
onmouseout="this.style.cursor='default';" />

<img id="imgPos23" class="dynamicImage" 
src="<spring:theme code="arrow_next" />"  
onmouseover="this.style.cursor='pointer';" 
onclick="firstRClicked_T=setRClicked_T();oldCtr_T=counter_T;"
onmousedown="this.style.cursor='pointer';" 
onmouseup="this.style.cursor='pointer';"
onmouseout="this.style.cursor='default';" />

</div>
</div>
<div id="latestNewsPos">
<h2 id="newsHeading">
<spring:message code="wise.latestNews" />
</h2>
<textarea id="newsContent" rows="12" cols="35"><spring:message code="wise.latestNews" /> </textarea>
<a href="#" onclick="displayNotAvailable('This page is not available yet');">
	<img id="newsArchive" src="<spring:theme code="newsarchive" />" 
       	 onmouseover="this.style.cursor='pointer';swapImage('newsArchive', 'images/News-Archive-Roll.png');"
       	 onmouseout="this.style.cursor='default';swapImage('newsArchive', 'images/News-Archive.png');"       	       			
	/>
</a>
</div>
</div>
<div id="footerPos">
<img id="nsfImage" src="<spring:theme code="nsf_tiny_logo"/>" align="center"/>
<spring:message code="wise.legal-acknowledgment" />  
<a href="http://www.nsf.gov" target="_blank"> <spring:message code="nsf" /></a>.
<spring:message code="wise.copyright" /> &nbsp;&nbsp;&nbsp; 
<a href="#" onclick="displayNotAvailable('This page is not available yet');"><spring:message code="wise.contact" /></a> 

</div>

</body>
</html>

<!-- 
<img id="imgPos1" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" 
onmouseover="swapImage('imgPos1','<spring:theme code="circle_rollover" />');" 
onclick="swapImage('imgPos1','<spring:theme code="circle_selected" />');
oldCtr = counter; 
counter=swapBigImage(0,counter,'Action');
prevClick = getPrevClick(prevClick,0,oldCtr);" 
/>
<img id="imgPos2" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" 
onmouseover="swapImage('imgPos2','<spring:theme code="circle_rollover" />');" 
onclick="swapImage('imgPos2','<spring:theme code="circle_selected" />');
oldCtr = counter; 
counter=swapBigImage(1,counter,'Action');
prevClick=getPrevClick(prevClick,1,oldCtr);" 
/>
<img id="imgPos3" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" 
onmouseover="swapImage('imgPos3','<spring:theme code="circle_rollover" />');" 
onclick="swapImage('imgPos3','<spring:theme code="circle_selected" />');
oldCtr = counter;
counter=swapBigImage(2,counter,'Action');
prevClick = getPrevClick(prevClick,2,oldCtr);" 
/>
<img id="imgPos4" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" 
onmouseover="swapImage('imgPos4','<spring:theme code="circle_rollover" />');"  
onclick="swapImage('imgPos4','<spring:theme code="circle_selected" />');
oldCtr = counter; 
counter=swapBigImage(3,counter,'Action');
prevClick = getPrevClick(prevClick,3,oldCtr);" 
/>
<img id="imgPos5" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" 
onmouseover="swapImage('imgPos5','<spring:theme code="circle_rollover" />');" 
onclick="swapImage('imgPos5','<spring:theme code="circle_selected" />');
oldCtr = counter; 
counter=swapBigImage(4,counter,'Action');
prevClick = getPrevClick(prevClick,4,oldCtr);" 
/>

<img id="imgPos6" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" 
onmouseover="swapImage('imgPos6','<spring:theme code="circle_rollover" />');" 
onclick="swapImage('imgPos6','<spring:theme code="circle_selected" />');
oldCtr = counter; 
counter=swapBigImage(5,counter,'Action');
prevClick = getPrevClick(prevClick,5,oldCtr);" 
/>
<img id="imgPos7" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" 
onmouseover="swapImage('imgPos7','<spring:theme code="circle_rollover" />');" 
onclick="swapImage('imgPos7','<spring:theme code="circle_selected" />');
oldCtr = counter; 
counter=swapBigImage(6,counter,'Action');
prevClick = getPrevClick(prevClick,6,oldCtr);" 
/>
<img id="imgPos8" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" 
onmouseover="swapImage('imgPos8','<spring:theme code="circle_rollover" />');" 
onclick="swapImage('imgPos8','<spring:theme code="circle_selected" />');
oldCtr = counter; 
counter=swapBigImage(7,counter,'Action');
prevClick = getPrevClick(prevClick,7,oldCtr);" 
/>
<img id="imgPos9" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" 
onmouseover="swapImage('imgPos9','<spring:theme code="circle_rollover" />');" 
onclick="swapImage('imgPos9','<spring:theme code="circle_selected" />');
oldCtr = counter; 
counter=swapBigImage(8,counter,'Action');
prevClick = getPrevClick(prevClick,8,oldCtr);" 
/>
<img id="imgPos10" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" 
onmouseover="swapImage('imgPos10','<spring:theme code="circle_rollover" />');" 
onclick="swapImage('imgPos6','<spring:theme code="circle_selected" />');
oldCtr = counter; 
counter=swapBigImage(9,counter,'Action');
prevClick = getPrevClick(prevClick,9,oldCtr);" 
/>


<img id="imgPos13" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" 
onmouseover="swapImage('imgPos13','<spring:theme code="circle_rollover" />');"
onclick="swapImage('imgPos13','<spring:theme code="circle_selected" />');"

/>
<img id="imgPos14" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" 
onmouseover="swapImage('imgPos14','<spring:theme code="circle_rollover" />');"
onclick="swapImage('imgPos14','<spring:theme code="circle_selected" />');"
/>
<img id="imgPos15" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" 
onmouseover="swapImage('imgPos15','<spring:theme code="circle_rollover" />');"
onclick="swapImage('imgPos15','<spring:theme code="circle_selected" />');"
/>
<img id="imgPos16" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" 
onmouseover="swapImage('imgPos16','<spring:theme code="circle_rollover" />');"
onclick="swapImage('imgPos16','<spring:theme code="circle_selected" />');"
/>
<img id="imgPos17" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" 
onmouseover="swapImage('imgPos17','<spring:theme code="circle_rollover" />');"
onclick="swapImage('imgPos17','<spring:theme code="circle_selected" />');"
/>
<img id="imgPos18" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" 
onmouseover="swapImage('imgPos18','<spring:theme code="circle_rollover" />');"
onclick="swapImage('imgPos18','<spring:theme code="circle_selected" />');"
/>
<img id="imgPos19" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" 
onmouseover="swapImage('imgPos19','<spring:theme code="circle_rollover" />');"
onclick="swapImage('imgPos19','<spring:theme code="circle_selected" />');"
/>
<img id="imgPos20" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" 
onmouseover="swapImage('imgPos20','<spring:theme code="circle_rollover" />');"
onclick="swapImage('imgPos20','<spring:theme code="circle_selected" />');"
/>
<img id="imgPos21" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" 
onmouseover="swapImage('imgPos21','<spring:theme code="circle_rollover" />');"
onclick="swapImage('imgPos21','<spring:theme code="circle_selected" />');"
/>
<img id="imgPos22" class="dynamicImage" 
src="<spring:theme code="circle_unselected" />" 
onmouseover="swapImage('imgPos22','<spring:theme code="circle_rollover" />');"
onclick="swapImage('imgPos22','<spring:theme code="circle_selected" />');"
/>

 -->


