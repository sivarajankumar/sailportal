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
<img src="<spring:theme code="arrow_prev" />" class="dynamicImage" id="action_prev" 
onmouseover="this.style.cursor='pointer';" 
onmousedown="this.style.cursor='pointer';" 
onmouseup="this.style.cursor='pointer';"
onmouseout="this.style.cursor='default';" 
onclick="firstLClicked = setLClicked();
oldCtr=counter;
counter=proceedToPreviousImage(firstLClicked,counter);
"   
/>
<input type="text" id="imageInput" value="1" size="2" style="display:none;" onchange="moveToImage('rotator',this.value);
oldCtr = counter; 
counter=swapBigImage(this.value,counter,'Action');
prevClick = getPrevClick(prevClick,this.value,oldCtr);"

/>
<a href="javascript:Effect.toggle('imageInput','appear')" id="actionImgLink"> 1 of 10 </a>
<img id="action_next" class="dynamicImage" src="<spring:theme code="arrow_next" />" 
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

<img id="test_prev" class="dynamicImage" src="<spring:theme code="arrow_prev" />" 
onmouseover="this.style.cursor='pointer';" 
onclick="firstLClicked_T = setLClicked_T();oldCtr_T=counter_T;"
onmousedown="this.style.cursor='pointer';" 
onmouseup="this.style.cursor='pointer';"
onmouseout="this.style.cursor='default';" />

<img id="test_next" class="dynamicImage" 
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
       	 onmouseover="this.style.cursor='pointer';swapImage('newsArchive', '<spring:theme code="newsarchive_roll" />');"
       	 onmouseout="this.style.cursor='default';swapImage('newsArchive', '<spring:theme code="newsarchive" />');"       	       			
	/>
</a>
</div>
</div>
<div id="footerPos">
<img id="nsfImage" src="<spring:theme code="nsf_tiny_logo"/>" align="center"/>
<p id="nsfLink"><spring:message code="wise.legal-acknowledgment" />  
<a href="http://www.nsf.gov" target="_blank"> <spring:message code="nsf" /></a>.
<spring:message code="wise.copyright" /> &nbsp;&nbsp;&nbsp; 
<a href="#" onclick="displayNotAvailable('This page is not available yet');"><spring:message code="wise.contact" /></a> 
</p>
</div>

</body>
</html>
