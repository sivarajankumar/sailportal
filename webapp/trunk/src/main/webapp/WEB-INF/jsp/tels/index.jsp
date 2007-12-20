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

<link href="<spring:theme code="homepagestylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />


<script src="./javascript/tels/general.js" 			type="text/javascript"> </script>
<script src="./javascript/tels/prototype.js" 		type="text/javascript"> </script>
<script src="./javascript/tels/effects.js" 			type="text/javascript"> </script>
<script src="./javascript/tels/scriptaculous.js" 	type="text/javascript"> </script>
<script src="./javascript/tels/rotator.js" 			type="text/javascript"> </script>
<script src="./javascript/tels/rotatorT.js" 		type="text/javascript"> </script>

<title><spring:message code="application.title" /></title>

</head>

<body>

<div id="centeredDiv">

<%@ include file="headermain.jsp"%>

<table id="tableTop" cellpadding="0" cellspacing="0">
        		<tr>
        			<td id="boxTableWelcome" class="panelColor">
               		  <div id="header">Welcome to WISE</div>
                      
	<div id="parastyleTable">
        
    <ul id="welcomeTextLinks">
    	<li><a href="#" onClick="javascript: fabulousTurnOff1();">WHAT IS WISE?</a></li>
        <li><a href="#" onClick="javascript: fabulousTurnOff2();">CURRICULUM-BASED</a></li>
        <li><a href="#" onClick="javascript: fabulousTurnOff3();">INQUIRY PROJECTS</a></li>
        <li><a href="#" onClick="javascript: fabulousTurnOff4();">STUDENT ENGAGEMENT</a></li>
        <li><a href="#" onClick="javascript: fabulousTurnOff5();">INTERACTIVE MODELS</a></li>
        <li><a href="#" onClick="javascript: fabulousTurnOff6();">ONLINE GRADING</a></li>
        <li><a href="#" onClick="javascript: fabulousTurnOff7();">FREE & OPEN-SOURCE</a></li>
        </ul>
        <p class="smallText">Click above for details</p>
    </div>
    
					<div id="welcomeBullet1" style=""> 
          			<div class="welcomeBulletHeader">What is WISE?</div>
		  			<p>The Web-Based Inquiry Science Environment is a simple yet powerful learning system designed to foster 
		  			inquiry-based science.  A collection of online and Java-based tools allow students to 
		  			download curriculum-based projects, then explore and reflect at their own pace.  Teachers facilitate the 
		  			process in the classroom and use online tools to offer formative and evaluative assessment of student work.</p>
					</div>
				
					<div id="welcomeBullet2" style="display:none;"> 
          			<div class="welcomeBulletHeader">Curriculum-Based</div>
                    <p>Standards-based WISE projects are specifically tailored for classroom use, and revolve around key conceptual 
                    difficulties that students encounter in biology, chemistry, and physics.  As a result, WISE projects offer a 
                    focused and inquiry-rich supplement to a teacher's core scope and sequence.</p>
					</div>
                    
                    <div id="welcomeBullet3" style="display:none;"> 
          			<div class="welcomeBulletHeader">Inquiry Projects</div>
		  			<p>WISE projects focus on science inquiry. Students explore new ideas and information, ponder discrepant events, write reflections,
		  				form fact-based theories, and validate these theories through discussion and model-based testing. 
		  				Students can work alone, in pairs, or in trios (at the teacher's discretion).</p>
					</div>
                    
                    <div id="welcomeBullet4" style="display:none;"> 
          			<div class="welcomeBulletHeader">Student Engagement</div>
		  			<p>WISE projects draw and sustain student interest using compelling computer-based interactivity.  
		  			The rapid feedback offered by WISE tools encourages students to self-monitor their progress and 
		  			solidify their new ideas before moving onward.</p>
					</div>
                    
                    <div id="welcomeBullet5" style="display:none;"> 
          			<div class="welcomeBulletHeader">Interactive Models</div>
		  			<p>Many WISE projects incorporate interactive models that help make micro and macro scientific 
		  			concepts both visible and testable.  Students experience the core processes of the scientific method 
		  			as they form hypotheses, test them, analyze results, refine ideas, and retest.</p>
                    </div>
                    
					<div id="welcomeBullet6" style="display:none;"> 
          			<div class="welcomeBulletHeader">Teacher Tools</div>
		  			<p>A variety of integrated tools help teachers grade efficiently (using editable comment templates), 
		  			pause all student computers simultaneously (for group discussion), and watch student work unfold online in 
		  			real-time (with the Class Monitor). Teachers can also create customized projects using the powerful Authoring Tool.</p>
					</div>
                    
                    <div id="welcomeBullet7" style="display:none;"> 
          			<div class="welcomeBulletHeader">Free &amp; Open-Source</div>
		  			<p>Best of all, WISE is completely free.  Emerging from education research funding, and based on modern open-source 
		  			technology, WISE is an active, on-going research tool used by an international community of teachers, 
		  			researchers and software developers.</p>
					</div>


    
	<ul id="welcomeButtonPosTable" >
    
    <li><a href="wiseoverview.html" 
    	onmouseout="MM_swapImgRestore()" 
    	onmouseover="MM_swapImage('Overview of WISE button','','./themes/tels/default/images/Animated-WISE-Rollover.png',1)">
    	<img src="./themes/tels/default/images/Animated-WISE.png" alt="Overview of WISE" width="145" height="32" 
    		class="imgNoBorder" id="Overview of WISE button" /></a> </li>
       
    <li><a href="previewproject.html" 
    		onmouseout="MM_swapImgRestore()" 
    		onmouseover="MM_swapImage('Preview of WISE Button','','./themes/tels/default/images/Instant-Preview-Rollover.png',1)">
    		<img src="./themes/tels/default/images/Instant-Preview.png" alt="Preview of WISE Button" width="145" height="32"  
    			class="imgNoBorder" id="Preview of WISE Button" /></a> </li>
		 
	<li><a href="signup.html" 
			onmouseout="MM_swapImgRestore()" 
			onmouseover="MM_swapImage('Join WISE Button','','./themes/tels/default/images/join_wise_rollover.png',1)">
			<img src="./themes/tels/default/images/join_wise.png" alt="Common Questions Button" width="145" height="32" 
				class="imgNoBorder"  id="Join WISE Button" /></a> </li>
</ul> 	
	
                    </td> <!--    End of Welcome Box-->	
                    
                    <td class="width15"></td>  <!--    Separator Column-->	
                    
          <td id="boxTableSignIn" class="panelColor">
                    			<div id="header">Sign In</div>
								<form id="home" method="post" action="j_acegi_security_check">
                                
                                <dl>
                                    <dt><label for="username">Username:  </label> </dt>
                                    <dd><input class="dataBoxStyle" type="text" name="j_username" id="j_username" maxlength="30"/></dd>
                                    <dt><label for="password">Password:	</label></dt>
                                    <dd><input class="dataBoxStyle" type="password" name="j_password" id="j_password" maxlength="20"/></dd>
                                </dl>
                                                       
                                <div class="alignRight">
                                			<input type="image" id="signInButton" src="./themes/tels/default/images/sign_in.png"   
                                            onmouseover="MM_swapImage('signInButton','','./themes/tels/default/images/sign_in_rollover.png',1)"
                                            onmouseout="MM_swapImgRestore()"
                                            onclick="Effect.toggle('waiting', 'appear')" /></div>
                                                 
                                </form>
                                
                                <ul id="signInLinkPositionTable">
                                		<li><a href="forgotaccount/selectaccounttype.html" id="forgotlink">
                                      Forgot Username or Password?</a>  </li>
                                		<li><a href="signup.html" id="joinlink">Want to join WISE?</a></li>
                                </ul>
                                
                                </td>   <!--    End of boxTableSignIn  x-->
                	</tr> 
            </table>             
	
    		    		<table id="tableBottom" cellpadding="0" cellspacing="0">
   		  <tr>
        			<td id="boxWiseInAction" class="panelColor">
                        <div id="header">WISE In Action</div>
                        
                        <div class="alignCenter"><img id="rotator" src="./themes/tels/default/images/wiseInAction/AirBag.jpg" height="228"/></div>
                        
                        <div id="actionNavTable" class="alignCenter"> 
                        
                        <img src="./themes/tels/default/images/wiseInAction/Arrow_Previous.png" class="dynamicImage" id="actionPrevTable" 
                        onmouseover="this.style.cursor='pointer';" 
                        onmousedown="this.style.cursor='pointer';" 
                        onmouseup="this.style.cursor='pointer';"
                        onmouseout="this.style.cursor='default';" 
                        onclick="
                        counter=proceedToPreviousImage(counter);
                        changeText('actionImgLinkTable',counter);"/>
                        
                        <a id="actionImgLinkTable">1 of 10</a>
                        
                        <img id="actionNextTable" src="./themes/tels/default/images/wiseInAction/Arrow_Next.png" 
                        onmouseover="this.style.cursor='pointer';" 
                        onmousedown="this.style.cursor='pointer';" 
                        onmouseup="this.style.cursor='pointer';"
                        onmouseout="this.style.cursor='default';" 
                        onclick="
                        counter=proceedToNextImage(counter);
                        changeText('actionImgLinkTable',counter);" /> 
                   	  </div>
					</td>  <!--    End of boxWISEInAction  -->
                    
                    <td class="width15"></td>  <!--    Separator Column-->	
                    
                    <td id="boxTestimonials" class="panelColor">
                            
                            <div id="header">Testimonials</div>
                            
                            <div class="alignCenter"><img class="dataBoxStyle" id="rotatorT" src="./themes/tels/default/images/testimonial.png" height="228" /></div>
                            
                            <div id="testimonialsNavTable" class="alignCenter">
                            
                            <img id="test_prev" class="dynamicImage" 
                            src="./themes/tels/default/images/wiseInAction/Arrow_Previous.png" 
                            onmouseover="this.style.cursor='pointer';" 
                            onclick="counter_T=proceedToPreviousImage_T(counter_T);
                            changeText_T('testimonialsImgLinkTable',counter_T);"
                            onmousedown="this.style.cursor='pointer';" 
                            onmouseup="this.style.cursor='pointer';"
                            onmouseout="this.style.cursor='default';" />
                            
                            <a id="testimonialsImgLinkTable">1 of 5</a>
                            
                            <img id="test_next" class="dynamicImage" src="./themes/tels/default/images/wiseInAction/Arrow_Next.png" onmouseover="this.style.cursor='pointer';"
                            	onmousedown="this.style.cursor='pointer';" 
                        		onmouseup="this.style.cursor='pointer';"
                        		onmouseout="this.style.cursor='default';" 
                        		onclick="
                        		counter_T=proceedToNextImage_T(counter_T);
                       			 changeText_T('testimonialsImgLinkTable',counter_T);" /> 
                   	  		</div>        
					</td>          
							
							<td class="width15"></td>  
                                    
                    <td id="boxLatestNews" class="panelColor">
                            <div id="header">Latest News</div>
                            <div class="alignCenter">
                            	<textarea id="newsContent" class="dataBoxStyle" rows="16" cols="35">Latest News Goes Here</textarea>
                       		</div>
                                
                                <div class="alignCenter"> 
                                <p id="newsArchiveButton"><a href="#" 
                                onmouseout="MM_swapImgRestore()" 
                                onmouseover="MM_swapImage('News Archive','','./themes/tels/default/images/News-Archive-Roll.png',1)">
                                <img class="imgNoBorder" src="./themes/tels/default/images/News-Archive.png" alt="Go to News Archive" 
                                width="93" height="23" id="News Archive" /></a> </p>
                  				</div>
            		</td>
                </tr> 
            </table>

<table id="footerTable" cellpadding="0" cellspacing="0">
	<tr>
    	<td id="footerLogos">
           		<a class="marginPushLeft" href="http://www.nsf.gov" title="National Science Foundation"><img src="./themes/tels/default/images/NSF-Logo-50x50.png" alt="National Science Foundation" /></a>
                <a href="http://www.telscenter.org/confluence/display/SAIL/Home" title="SAIL Technology"><img src="./themes/tels/default/images/SAIL-Logo-Small.png" alt="SAIL Logo" /></a>
                <a href="http://www.telscenter.org/" title="TELS Consortium"><img src="./themes/tels/default/images/tels_logo.png" alt="SAIL Logo" /></a>
                <a href="#" title="WISE 3.0 Logo"><img src="./themes/tels/default/images/Wise-Logo-W3-Georgia.png" alt="SAIL Logo" /></a>
       	</td>
       	    	
        <td id="footerText">
        	 	<ul>
                <li><a href="signup.html">Join WISE</a></li>
                <li id="footerNav2"><a href="./contactwisegeneral.html">Contact WISE</a></li>
                <li id="footerNav2"><a href="<c:url value="/j_acegi_logout"/>">Sign Out</a></li>     
                <li id="footerNav2"><a href="credits.html">Credits</a></li>
                </ul>
            
                <p>Supported by the National Science Foundation, TELS Grant No. 0334199.</p>
                <p>Powered by SAIL open-source technology. Optimized for <a href="http://www.mozilla.com" title="Firefox web site">Firefox</a> browser.</p>
                <p>Copyright 1998-2008. All rights reserved.</p>
    	</td>
	</tr>
</table >  <!--    End of footer --> 

</div>  <!-- end of #centered div-->

</body>

</html>

