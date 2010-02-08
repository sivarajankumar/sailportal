<%@ include file="include.jsp"%>

<!-- $Id$ -->
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

<link href="<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet" type="text/css" />
<link href="<spring:theme code="homepagestylesheet"/>" media="screen" rel="stylesheet" type="text/css" />

<script src="./javascript/tels/killautocomplete.js" type="text/javascript"></script>

<script src="./javascript/tels/general.js" type="text/javascript">
	
</script>
<script src="./javascript/tels/prototype.js" type="text/javascript">
	
</script>
<script src="./javascript/tels/effects.js" type="text/javascript">
	
</script>
<script src="./javascript/tels/scriptaculous.js" type="text/javascript">
	
</script>
<script src="./javascript/tels/rotator.js" type="text/javascript">
	
</script>
<script src="./javascript/tels/rotatorT.js" type="text/javascript">
	
</script>

<link rel="shortcut icon" href="./themes/tels/default/images/favicon_panda.ico" />

<title><spring:message code="application.title" /></title>

<!--NOTE: the following scripts has CONDITIONAL items that only apply to IE (MattFish)-->
<!--[if lt IE 7]>
<script defer type="text/javascript" src="./javascript/tels/iefixes.js"></script>
<![endif]-->

<!--The next two conditional statements fix problems with the Display:Block navigation elements in older IE Browsers (MattFish)-->

<!--[if IE 5]>
<style>
#welcomeTextLinks a {
float: left;
clear: both;
width: 100%;
}
</style>
<![endif]-->

<!--[if lte IE 6]>
<style>
#welcomeTextLinks a {height: 1%;}
</style>
<![endif]-->

</head>

<body>

<div id="centeredDiv" class="homePageMask"> 

<%@ include file="headermain.jsp"%>

<table id="tableTop" cellpadding="0" cellspacing="0">
		<tr>
				<td>
				<div id="boxTableWelcome" class="panelColor1">
				<div id="header">Welcome to WISE</div>

				<div id="parastyleTable">

				<ul id="welcomeTextLinks">
						<li><a href="#" onclick="javascript: fabulousTurnOff1();"><spring:message code="whatiswise" /></a></li>
						<li><a href="#" onclick="javascript: fabulousTurnOff2();"><spring:message code="curriculumbased" /></a></li>
						<li><a href="#" onclick="javascript: fabulousTurnOff3();"><spring:message code="inquiryprojects" /></a></li>
						<li><a href="#" onclick="javascript: fabulousTurnOff4();"><spring:message code="studentengagement" /></a></li>
						<li><a href="#" onclick="javascript: fabulousTurnOff5();"><spring:message code="interactivemodels" /></a></li>
						<li><a href="#" onclick="javascript: fabulousTurnOff6();"><spring:message code="onlinegrading" /></a></li>
						<li><a href="#" onclick="javascript: fabulousTurnOff7();"><spring:message code="freeandopensource" /></a></li>
				</ul>

				<p class="smallText"><spring:message code="clickabovefordetails" /></p>

				</div>
				
				<div id="welcomeBulletContainer">
					<div id="welcomeBullet1" style="">
					<div class="welcomeBulletHeader"><spring:message code="whatiswiseheader" /></div>
					<p>The Web-based Inquiry Science Environment is a research-based digital learning platform that fosters exploration and science inquiry.  
						Students observe, analyze, experiment, and reflect as they navigate WISE projects. 
						Teachers guide and evaluate the process using a suite of classroom-based and online tools.</p><br/>
					<p>This site hosts the latest and most powerful version of WISE &mdash; version 4.0.  Classic WISE can be accessed at <a href='http://wise.berkeley.edu' target='_blank'>http://wise.berkeley.edu</a>.</p>
					</div>
					
					<div id="welcomeBullet2" style="display: none;">
					<div class="welcomeBulletHeader"><spring:message code="curriculumbasedheader" /></div>
					<p><spring:message code="curriculumbasedbullet" /></p>
					</div>
	
					<div id="welcomeBullet3" style="display: none;">
					<div class="welcomeBulletHeader"><spring:message code="inquiryprojectsheader" /></div>
					<p><spring:message code="inquiryprojectsbullet" /></p>
					</div>
	
					<div id="welcomeBullet4" style="display: none;">
					<div class="welcomeBulletHeader"><spring:message code="studentengagementheader" /></div>
					<p><spring:message code="studentengagementbullet" /></p>
					</div>
	
					<div id="welcomeBullet5" style="display: none;">
					<div class="welcomeBulletHeader"><spring:message code="interactivemodelsheader" /></div>
					<p><spring:message code="interactivemodelsbullet" /></p>
					</div>
	
					<div id="welcomeBullet6" style="display: none;">
					<div class="welcomeBulletHeader"><spring:message code="teachertoolsheader" /></div>
					<p><spring:message code="teachertoolsbullet" /></p>
					</div>
	
					<div id="welcomeBullet7" style="display: none;">
					<div class="welcomeBulletHeader"><spring:message code="freeandopensourceheader" /></div>
					<p><spring:message code="freeandopensourcebullet" /></p>
					</div>
				</div>

				<ul id="welcomeButtonPosTable">
	
							<li><a href="wiseoverview.html" title="Launch a brief Flash-based animated overview of the WISE system."
									onmouseout="MM_swapImgRestore()"
									onmouseover="MM_swapImage('Overview of WISE button','','./themes/tels/default/images/AnimatedOverviewRoll.png',1)">
							<img src="./themes/tels/default/images/AnimatedOverview.png" width="145" height="33"
									alt="<spring:message code="animatedoverviewofwise"/>" class="imgNoBorder" id="Overview of WISE button" /> </a></li>
	
							<li><a href="previewprojectlist.html"
									title="View instant previews of WISE inquiry projects across biology, chemistry, and physics topics."
									onmouseout="MM_swapImgRestore()"
									onmouseover="MM_swapImage('Preview of WISE Button','','./themes/tels/default/images/PreviewProjectRoll.png',1)">
							<img src="./themes/tels/default/images/PreviewProject.png" width="145" height="33"
									alt="<spring:message code="instantpreview"/>" class="imgNoBorder" id="Preview of WISE Button" /></a></li>
	
							<li><a href="signup.html"
									title="New to WISE? Create a teacher or student account to enjoy the full array of learning tools offered by WISE."
									onmouseout="MM_swapImgRestore()"
									onmouseover="MM_swapImage('Create Account Button','','./themes/tels/default/images/CreateWiseAccountRoll.png',1)">
							<img src="./themes/tels/default/images/CreateWiseAccount.png" width="145" height="33"
									alt="<spring:message code="createnewwiseaccount"/>" class="imgNoBorder" id="Create Account Button" /></a></li>
	
							<li><a href="./contactwisegeneral.html"
									title="Have a question or a problem with the WISE tools or curriculum projects? Write a message to the WISE team."
									onmouseout="MM_swapImgRestore()"
									onmouseover="MM_swapImage('Contact Wise Button','','./themes/tels/default/images/ContactWiseRoll.png',1)"> <img
									src="./themes/tels/default/images/ContactWise.png" width="145" height="33" alt="Contact WISE" class="imgNoBorder"
									id="Contact Wise Button" /></a></li>
	
					</ul>
								</div>
				</td>
				<!--    End of Welcome Box-->

				<td class="width15"></td>
				<!--    Separator Column-->

				<td id="boxTableSignInHome">
				<div id="boxTableSignInSub1" class="panelColor2"><!--  SignIn Sub Box 1-->
				<div id="header"><spring:message code="signinheader" /></div>

				<form id="home" method="post" action="j_acegi_security_check" autocomplete="off">
				<dl id="signinDefinList">
						<dt><label for="username"><spring:message code="username" /></label></dt>
						<dd><input class="dataBoxStyle" type="text" name="j_username" id="j_username" size="18" maxlength="60" /></dd>

						<!--This unusually placed script gets the cursor into the First Name field immediately on page load.  
									It must appear immediately after the Input field in question  (MattFish)-->
						<script type="text/javascript">
							document.getElementById('j_username').focus();
						</script>

						<dt><label for="password"><spring:message code="password" /></label></dt>
						<dd><input class="dataBoxStyle" type="password" name="j_password" id="j_password" size="18" maxlength="30" /></dd>
				</dl>

				<div class="alignRight"><input type="image" id="signInButton" img src="./themes/tels/default/images/SignIn.png"
						width="100" height="27" alt="Sign In Button"
						onmouseover="MM_swapImage('signInButton','','./themes/tels/default/images/SignInRoll.png',1)"
						onmouseout="MM_swapImgRestore()" onclick="Effect.toggle('waiting', 'appear')" /></div>
				</form>

				<ul id="signInLinkPosition">
						<li><a href="forgotaccount/selectaccounttype.html" id="forgotlink">Forgot your Username or Password?</a>
						</li>
						<li><a href="signup.html" id="joinlink"><spring:message code="createanewwiseaccount" /></a></li>
				</ul>
				</div>

				<div id="boxTableSignInSub2" class="panelColor3"><!--  Researcher/Developer Sub Box 2-->
				<div id="header"><spring:message code="researchdevelop1" /></div>
				<div id="researcherText">
				<p><spring:message code="researchdevelop2" />
				<spring:message code="researchdevelop3" />&nbsp;<a style="color:#666;" href="#" target="_blank">PENDING</a></p>
				</div>
				</div>

				</td>
				<!--    End of boxTableSignIn  x-->
		</tr>
</table>

<table id="tableBottom" cellpadding="0" cellspacing="0">
		<tr>
				<td id="boxWiseInAction"> 
				<div class="panelColor1">
				<div id="header"><spring:message code="home.wiseinactionlabel" /></div>

				<div class="alignCenter"><img id="rotator" src="./themes/tels/default/images/wiseInAction/AirBag.jpg" height="228" /></div>

				<div id="actionNavTable" class="alignCenter"><img
						src="./themes/tels/default/images/wiseInAction/Arrow_Previous.png" class="dynamicImage" id="actionPrevTable"
						onmouseover="this.style.cursor='pointer';" onmousedown="this.style.cursor='pointer';"
						onmouseup="this.style.cursor='pointer';" onmouseout="this.style.cursor='default';"
						onclick="
                        counter=proceedToPreviousImage(counter);
                        changeText('actionImgLinkTable',counter);" />

				<a id="actionImgLinkTable">1&nbsp;<spring:message code="home.wiseinactioncounter" />&nbsp;10</a> <img
						id="actionNextTable" src="./themes/tels/default/images/wiseInAction/Arrow_Next.png"
						onmouseover="this.style.cursor='pointer';" onmousedown="this.style.cursor='pointer';"
						onmouseup="this.style.cursor='pointer';" onmouseout="this.style.cursor='default';"
						onclick="
                        counter=proceedToNextImage(counter);
                        changeText('actionImgLinkTable',counter);" />
				</div>
				</div>
				</td>
				<!--    End of boxWISEInAction  -->

				<td class="width15"></td>
				<!--    Separator Column-->

				<td id="boxTestimonials" >
				<div class="panelColor1">

				<div id="header"><spring:message code="home.testimonialslabel" /></div>

				<div class="alignCenter"><img class="dataBoxStyle" id="rotatorT"
						src="./themes/tels/default/images/testimonial_1.png" height="228" /></div>

				<div id="testimonialsNavTable" class="alignCenter"><img id="test_prev" class="dynamicImage"
						src="./themes/tels/default/images/wiseInAction/Arrow_Previous.png" onmouseover="this.style.cursor='pointer';"
						onclick="counter_T=proceedToPreviousImage_T(counter_T);
                            changeText_T('testimonialsImgLinkTable',counter_T);"
						onmousedown="this.style.cursor='pointer';" onmouseup="this.style.cursor='pointer';"
						onmouseout="this.style.cursor='default';" /> <a id="testimonialsImgLinkTable">1 <spring:message
						code="home.testimonialscounter" /> 5</a> <img id="test_next" class="dynamicImage"
						src="./themes/tels/default/images/wiseInAction/Arrow_Next.png" onmouseover="this.style.cursor='pointer';"
						onmousedown="this.style.cursor='pointer';" onmouseup="this.style.cursor='pointer';"
						onmouseout="this.style.cursor='default';"
						onclick="
                        		counter_T=proceedToNextImage_T(counter_T);
                       			 changeText_T('testimonialsImgLinkTable',counter_T);" />
				</div>
				</div>
				</td>

				<td class="width15"></td>

				<td >
				<div id="boxLatestNews"  class="panelColor1">
				<div id="header">WISE News</div>
				<div id="newsContent">
				<div id="newsContentHeader">${newsItem.title}</div>
				${newsItem.news}</div>

				<div class="alignCenter">
				<p id="newsArchiveButton"><a href="./newsarchive.html" onmouseout="MM_swapImgRestore()"
						onmouseover="MM_swapImage('News Archive','','./themes/tels/default/images/newsArchiveRoll.png',1)"> <img
						class="imgNoBorder" src="./themes/tels/default/images/newsArchive.png" alt="Go to News Archive" width="93" height="23"
						id="News Archive" /></a></p>
				</div>
				</div>
				</td>
		</tr>
</table>

<table id="footerTable" cellpadding="0" cellspacing="0">
		<tr>
				<td id="footerLogos"><a class="marginPushLeft" href="http://www.nsf.gov" title="National Science Foundation"><img
						src="./themes/tels/default/images/NSF-Logo-50x50.png" width="50" height="50" alt="National Science Foundation" /></a> <a
						href="http://www.telscenter.org/confluence/display/SAIL/Home" title="SAIL Technology"><img
						src="./themes/tels/default/images/SAIL-Logo-Small.png" width="96" height="50" alt="SAIL Logo" /></a> <a
						href="http://www.telscenter.org/" title="TELS Consortium"><img src="./themes/tels/default/images/tels_logo.png"
						width="160" height="50" alt="SAIL Logo" /></a> <a href="#" title="WISE 4.0"><img
						src="./themes/tels/default/images/WISE-Logo-Small-1.png" alt="WISE 4.0 Logo" /></a></td>

				<td id="footerText">
				<ul>
						<li><a href="signup.html"><spring:message code="footer.link1" /></a></li>
						<li id="footerNav2"><a href="./contactwisegeneral.html"><spring:message code="footer.link2" /></a></li>
						<li id="footerNav2"><a href="<c:url value="/j_spring_security_logout"/>"><spring:message code="footer.link3" /></a></li>
						<li id="footerNav2"><a href="credits.html"><spring:message code="footer.link4" /></a></li>
				</ul>

				<p><spring:message code="footer.legal1" /></p>
				<p><spring:message code="footer.legal2" /><a href="http://www.mozilla.com" title="Firefox web site">Firefox</a>
				browser.</p>
				<p><spring:message code="footer.legal3" /></p>
				</td>
		</tr>
</table>
<!--    End of footer Table -->

</div>
<!-- end of #centered div-->

</body>

</html>

