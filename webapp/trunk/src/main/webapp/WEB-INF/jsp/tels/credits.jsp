<%@ include file="include.jsp"%>

<!-- $Id: previewproject.jsp 323 2007-04-21 18:08:49Z hiroki $ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="<spring:theme code="stylesheet"/>" media="screen" rel="stylesheet" type="text/css" />
<link href="<spring:theme code="registerstylesheet"/>" media="screen" rel="stylesheet" type="text/css" />

  
<title>WISE 3.0 Credits Screen</title>

<script type="text/javascript" src="./javascript/pas/utils.js"></script> 
<script type="text/javascript" src="./javascript/tels/general.js"></script>

</head>

<body>

<div id="centeredDiv">

<%@ include file="headermain_returntohomepage.jsp"%>

<h1 id="previewProjectTitle" class="blueText">Credits</h1>

<div id="boxPreviewProject">

<div id="creditsIntroText">The WISE 3.0 education system stems from  open-source project with contributors acrostems from the dedicated efforts of wide-ranging community of 
software development teams. The multidisciplinary collaboration of these teams, spanning a range of technolo are a reflection of the colloborative effort  </div>

<table border="0" cellpadding="4" id="creditsTeamTable">
	<tr>  
		<th colspan=2> 
		contributors <span style="font-size:.6em;">(alphabetical order)</span>
		</th>  
	</tr>
	<tr>
		<td class="creditCategory">Research Program Directors</td> 
		<td>
		<dl> 
			<dt>Ken Bell</dt>
				<dd>The Concord Consortium<a href="mailto:kbell@concord.org">email</a> <a href="http://www.telscenter.org/grad_ed/concord.html">bio</a></dd>
			<dt>Jane Bowyer</dt>
				<dd>Mills College <a href="mailto:jane@mills.edu">email</a> <a href="http://www.telscenter.org/grad_ed/mills.html">bio</a></dd>
			<dt>S. Raj Chaudbury</dt>
				<dd>Christopher Newport University <a href="mailto:schaudhury@cnu.edu">email</a> <a href="http://www.telscenter.org/grad_ed/cnu.html">bio</a></dd>
			<dt>Doug Clark</dt>
				<dd>Arizona State University <a href="mailto:dbc@asu.edu">email</a> <a href="http://www.telscenter.org/grad_ed/asu.html">bio</a></dd>
			<dt>Chris Hoadley</dt>
				<dd>Pennsylvania State University <a href="mailto:tophe@psu.edu">email</a> <a href="http://www.telscenter.org/grad_ed/psu.html">bio</a></dd>
			<dt>Paul Horwitz</dt>
				<dd>The Concord Consortium<a href="mailto:paul@concord.org">email</a> <a href="http://www.telscenter.org/grad_ed/concord.html">bio</a></dd>
			<dt>Yael Kali</dt>
				<dd>Technion--Israel Institute of Technology<a href="mailto:schaudhury@cnu.edu">email</a> <a href="http://www.telscenter.org/grad_ed/technion.html">bio</a></dd>
			<dt>Marica Linn</dt>
				<dd>University of California, Berkeley <a href="mailto:mlinn@berkeley.edu">email</a> <a href="http://www.telscenter.org/grad_ed/ucb.html">bio</a></dd>
			<dt>Tun Nyein</dt>
				 <dd>North Carolina Central University <a href="mailto:tnyein@nccu.edu">email</a> <a href="http://www.telscenter.org/grad_ed/nccu.html">bio</a></dd>
			<dt>James Slotta</dt>
				<dd>University of Toronto<a href="mailto:jslotta@oise.utoronto.ca">email</a> <a href="http://www.telscenter.org/grad_ed/ucb.html">bio</a></dd>
			<dt>Bob Tinker</dt>
				<dd>The Concord Consortium<a href="mailto:bob@concord.org">email</a> <a href="http://www.telscenter.org/grad_ed/concord.html">bio</a></dd>
		</dl>
		</td> 	
	</tr> 
	<tr>
		<td class="creditCategory">Software Programmers</td> 
		<td>
			<dl> 
			<dt>Turadg Aleahmad</dt>
				<dd>Carnegie Mellon University<a href="mailto:turadg@gmail.com ">email</a>  <a href="#">bio pending</a></dd>
			<dt>Stephen Bannasch</dt>
				<dd>Univerity of California, Berkeley<a href="mailto:stephen.bannasch@deanbrook.org">email</a>  <a href="#">bio pending</a></dd>
			<dt>Scott Cytacki</dt>
				<dd>The Concord Consortium <a href="mailto:scytacki@fastmail.fm">email</a> <a href="http://www.telscenter.org/grad_ed/asu.html">bio pending</a></dd>
			<dt>Geoffrey Kwan</dt>
				<dd>Univerity of California, Berkeley<a href="mailto:geoffreykwan@gmail.com">email</a>  <a href="#">bio pending</a></dd>
			<dt>Anthony Perritano</dt>
				<dd>Univerity of California, Berkeley <a href="mailto:jane@mills.edu">email</a> <a href="http://www.telscenter.org/grad_ed/mills.html">bio pending</a></dd>
			<dt>Rokham Sadeghnezhadfard</dt>
				<dd>Univerity of Toronto <a href="mailto:somebodyiknow@gmail.com">email</a> <a href="http://www.telscenter.org/grad_ed/mills.html">bio pending</a></dd>
			<dt>Hiroki Terashima</dt>
				<dd>Univerity of California, Berkeley <a href="mailto:hirochan@berkeley.edu">email</a> <a href="#">bio pending</a></dd>
			<dt>Aaron Unger</dt>
				<dd>The Concord Consortium <a href="mailto:aaron.unger@gmail.com">email</a> <a href="http://www.telscenter.org/grad_ed/asu.html">bio pending</a></dd>
			<dt>Laurel Williams</dt>
				<dd>University of Toronto <a href="mailto:laurel.williams@utoronto.ca">email</a> <a href="http://www.telscenter.org/grad_ed/asu.html">bio pending</a></dd>
			<div class="alumnaeHeader">alumnae</div>
			<div class="alumnaeNames">Sally Ahn, Patrick Lawler, Archana Raghunathan, Jinna Lei, Nathaniel Titterton, Cynick Young</div>
				
		</td> 	
	</tr>
	<tr>
		<td class="creditCategory">Interactive/Interface Design</td> 
		<td>
			<dl> 
			<dt>Matt Fishbach</dt>
				<dd>Univerity of California, Berkeley<a href="mailto:fish771@yahoo.com">email</a>  <a href="#">bio pending</a></dd>
			</dl>
		</td> 
	</tr>
	<tr>
		<td class="creditCategory">Project Management</td> 
		<td>
			<dl>
			<dt>Kathy Benneman</dt>
				<dd>Univerity of California, Berkeley <a href="mailto:kbenneman@berkeley.edu">email</a> <a href="#">bio pending</a></dd>
			</dl>
			<div class="alumnaeHeader">alumnae</div>
			<div class="alumnaeNames">Freda Husic</div>
		</td>  
	</tr>
	<tr>
		<td class="creditCategory">Classroom/Teacher Support</td> 
		<td>
			<dl>
			<dt>Doug Kirkpatrick</dt>
				<dd>Univerity of California, Berkeley<a href="dougkirk@berkeley.edu">email</a> <a href="#">bio pending</a></dd>
			</dl>
		</td>  
	</tr>
	<tr>
	<td class="creditCategory">WISE/TELS Research Team</td>  
		<td>
			<div class="alumnaeNames" style="font-size:.9em; line-height:150%;">Hsin-Yi Change, Jennie Chiu, Stephanie Corliss, Paul Dabenmire, Andrew Fisher, 
			Libby Gerard, Tara Higgins, Patty Holman, Jeff Holmes, Diane Johnson, Samantha Johnson, Nathan Kirk, Hee-Sun Lee, Joey Lee, Dalit Levy,
			Jacqueline Madhok, Paul Mazzei, Kevin McElhaney, Muhsin Meneskse, Frank Raminrez-Marin, Tamar Ronen-Fuhrmann,
			Barrington Ross, Beat Schwendimann, Michelle Spitulnick, Ji Shen, Tina Skjerping, Erika Tate, Keisha Varma, Stephanie Touchman, 
			Michelle Williams, Zhihui (Helen) Zhang,  </div>	
	</tr>
		
	</table>

</div>   <!--  end of boxNewAccountReg -->

<div style="text-align:center;"><a href="index.html"> <img id="return"
	src="<spring:theme code="return_to_homepage" />"
	onmouseover="swapImage('return', '<spring:theme code="return_to_homepage_roll" />');"
	onmouseout="swapImage('return', '<spring:theme code="return_to_homepage" />');" /></a></div>
	
</div>   <!-- end of centered div-->
   
</body>
</html>


