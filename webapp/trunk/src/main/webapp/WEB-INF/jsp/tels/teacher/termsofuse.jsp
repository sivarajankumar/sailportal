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

<!-- $Id: index.jsp 888 2007-08-06 23:47:19Z archana $ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" /> 
 
<link href="../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../<spring:theme code="registerstylesheet" />" media="screen" rel="stylesheet"  type="text/css" />
  
  
<script src="../javascript/general.js"				type="text/javascript" ></script> 
<script src="./javascript/tels/effects.js" 			type="text/javascript"> </script>
<script src="./javascript/tels/prototype.js" 		type="text/javascript"> </script>
<script src="./javascript/tels/scriptaculous.js" 	type="text/javascript"> </script>

<script language="Javascript" type="text/javascript">

	agreeCheckbox = window.opener.document.forms[0].agree;
	function yay() {
	      agreeCheckbox.checked = true;
	      window.close();
	}
	function nay() {
	    agreeCheckbox.checked = false;
	    window.close();
	}
</script>

<title>Terms of Use</title>
</head>

<body onload="MM_preloadImages('../images/teacher/Close-Window-Roll.png')">

<div>
  <h1 class="center">WISE Usage Agreement</h1>
</div>

<h5 class="center" id="">The following information describes terms of use for the WISE 3.0 system.</h5>

<div id="useAgreement">

    <p><strong>Usage Agreement </strong></p>
      <p>Before proceeding, we'd like to take a moment to explain our community and the role you'll play within the research project known as WISE. Please take a moment to read this page, as it is very important. </p>
      <p>By joining the WISE online community, you are joining a research community. The community itself is a research project, and we seek your consent to use your contributions to the community as a part of this research. We may use your comments from communities or surveys in our research, and will keep track of some of your actions on our site to help us find ways to improve over time. In addition, your students' work (free from personal identification) may be analyzed to address research questions about the effectiveness of specific curriculum content or functional elements of the online environment. </p>
      <p>By clicking the YES button on the registration page you are consenting to participation in our research about teacher and scientist beliefs about technology and the Internet, as well as our research about the best ways to support community members as they prepare to use WISE projects and author WISE curricula. You also consent to our use  of your written contributions to surveys and community discussions. </p>
      <p>We will not release your identity in any of our research. Likewise, your students' identities will never be viewed or revealed. All community comments will be treated as anonymous in our analyses. Any time we seek to use your identity in conjunction with comments (e.g., as a teacher quote in our public or testimonial pages), we will first seek your permission. </p>
      <p>In the interest of intellectual freedom, WISE allows members to contribute comments and links to public spaces. But to function as a community, we insist that users be considerate and respectful of one another. If you find any content posted by another WISE member to be offensive or inappropriate, please contact WISE using the &quot;Contact WISE&quot; link in the Help area. We will promptly investigate the matter. Illegal, offensive, or inappropriate material or links posted on the WISE site may be deleted by the WISE staff. Users who abuse their WISE membership privileges may have their accounts terminated. </p>
      <p>We value your enthusiastic participation in this community. Your contributions will enable us to continually improve the WISE system, helping teachers effectively make use of WISE curriculum projects, and enabling education researcher contribute their expertise to the authorship of powerful new learning activities. </p>
      <p>If you have any questions about our research or policies, please feel free to contact us. Thank you very much for your participation in this community. </p>      
     <p>Sincerely, 
      <p>Marcia Linn, Director, The WISE Project </p>
</div>

<div class="center" >
 		 <a href="#" onclick="self.close();return false;">
 		 <img src="../themes/tels/default/images/teacher/Close-Window.png" alt="Close Terms of Use" class="imgNoBorder" />
 		 </a> 
 </div> 
    


</body>
</html>




