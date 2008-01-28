<%@ include file="include.jsp"%>

   
    <!-- $Id: header.jsp 368 2007-05-05 01:41:18Z mattfish $ -->
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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >
<html xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<link href="../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../<spring:theme code="registerstylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
  
<script src=".././javascript/tels/general.js" type="text/javascript" ></script>
<script src=".././javascript/tels/prototype.js" type="text/javascript" ></script>
<script src=".././javascript/tels/scriptaculous.js" type="text/javascript" ></script>
<script src=".././javascript/effects.js" type="text/javascript" ></script>

<script type="text/javascript">
			function checkIfLegalAcknowledged (form, id) {
			if(form.getElementById(id).checked==true){
			}else{
			}
			}
</script>


<script type="text/javascript">
 
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
 
</script>

<title><spring:message code="teacher.signup.title" /></title>

</head>

<body>

<div id="centeredDiv">

<%@ include file="headermain.jsp"%>

<div style="text-align:center;">   
<!--This bad boy ensures centering of block level elements in IE (avoiding margin:auto bug).  Oh how I hate IE-->

<h1 id="registrationTitle" class="blueText">Teacher Registration</h1>
     
<div id="subtitleTeacherReg">To create a teacher account <br/> please complete the required fields below:</div>

<!-- Support for Spring errors object -->
<div id="regErrorMessages">
	<spring:bind path="teacherAccountForm.*">
  <c:forEach var="error" items="${status.errorMessages}">
    <b>
      <br /><c:out value="${error}"/>
    </b>
  </c:forEach>
</spring:bind>
</div>

<form:form method="post" action="registerteacher.html" commandName="teacherAccountForm" id="teacherRegForm" >  
  <dl>
  
  	<dt><label for="firstname" id="firstname1"><spring:message code="signup.firstname" /></label></dt>
    <dd><form:input path="userDetails.firstname" id="teacherFirstName" size="25" maxlength="25" tabindex="1"/><span class="hint">Required.<span class="hint-pointer"></span></span> </dd>
        
<!--This unusually placed script gets the cursor into the First Name field immediately on page load  (MattFish)-->
<script type="text/javascript">
document.getElementById('teacherFirstName').focus();
</script>
       
  	<dt><label for="lastname" id="lastname1"><spring:message code="signup.lastname"/></label></dt>
	<dd><form:input path="userDetails.lastname" id="teacherLastName" size="25" maxlength="25" tabindex="2"/> <span class="hint">Required. <span class="hint-pointer"></span></span> </dd>
            
    <dt><label for="emailAddress" id="emailAddress1"><spring:message code="signup.emailAddress" /></label></dt>
	<dd><form:input path="userDetails.emailAddress" id="teacherEmail" size="25" maxlength="25" tabindex="3"/> <span class="hint">Required.  Your new account information will be automatically emailed to this email address. <span class="hint-pointer"></span></span></dd>
            
     <dt><label for="city" id="city1"><spring:message code="signup.city" /></label> </dt>
	<dd><form:input path="userDetails.city" id="teacherCity" size="25" maxlength="25" tabindex="4"/>
    <span class="hint">Required.<span class="hint-pointer"></span></span>  </dd>
           
    <dt><label for="state" id="state1" ><spring:message code="signup.state" /></label> </dt>
	<dd><form:input path="userDetails.state" id="teacherState" size="25" maxlength="25" tabindex="5"/>
    	<span class="hint">Required.<span class="hint-pointer"></span></span>  
    	<div id="autocomplete_choices_state" class="autocomplete" ></div>
		<script type="text/javascript">  
			new Ajax.Autocompleter('state', 'autocomplete_choices_state', 'states.html', {paramName: 'sofar'}); 	   		
		</script>  
    </dd>
                
    <dt><label for="country" id="country1"><spring:message code="signup.country" /></label></dt>
	<dd><form:input path="userDetails.country" id="teacherCountry" size="25" maxlength="25" tabindex="6"/> 
    <span class="hint">Required.<span class="hint-pointer"></span></span> 
    <div id="autocomplete_choices_country" class="autocomplete" ></div>
		<script type="text/javascript">  
	new Ajax.Autocompleter('country', 'autocomplete_choices_country', 'countries.html', {paramName: 'sofar'}); 	   		
		</script>
    </dd>
            
    <dt>    <label for="schoolname" id="schoolname1"><spring:message code="signup.schoolname" /></label></dt>
	<dd><form:input path="userDetails.schoolname" id="teacherSchool" size="25" maxlength="25" tabindex="7"/>      <span class="hint">Required.<span class="hint-pointer"></span></span> </dd>
    
    <dt><label for="schoollevel" id="schoollevel1"><spring:message code="signup.schoollevel" /></label> </dt>
	<dd> 
    		<form:select path="userDetails.schoollevel" id="schoollevel" onfocus="Effect.toggle('showSchoolLevelInfo','appear');" onblur="Effect.toggle('showSchoolLevelInfo','appear');">           
    		<c:forEach items="${schoollevels}" var="schoollevel">
            <form:option value="${schoollevel}"><spring:message code="signup.schoollevels.${schoollevel}" /></form:option>
          </c:forEach>
        </form:select>
        <span class="hint">Required.<span class="hint-pointer"></span></span> </dd>
                 
    <dt><label for="curriculumsubjects" id="curriculumsubjects1"><spring:message code="signup.curriculumsubjects" /></label> 
</dt>
	<dd>
     
    <a href="javascript:Effect.toggle('curriculumSubjectsBox','appear')" >click to show/hide subjects</a> 
   
   	<div id="curriculumSubjectsBox" style="display:none;"> 
          	<p><strong>Select Curriculum Subjects:</strong></p>
		  <p> Describe the science topic(s) you teach by selecting one or more checkboxes below.</p>
		  
          <table id="textCurriculumBox">
          <tr>
          <td class="width166"><form:checkbox path="userDetails.curriculumsubjects" id="userDetails.curriculumsubjects1" value="Biology"/><input type="hidden" value="on"/> Biology</td>
          <td class="width122"><form:checkbox path="userDetails.curriculumsubjects" id="userDetails.curriculumsubjects2" value="APBiology"/><input type="hidden" value="on"/> AP Biology</td>
          </tr>
          <tr>
          <td><form:checkbox path="userDetails.curriculumsubjects" id="userDetails.curriculumsubjects3" value="EnvironmentalScience"/><input type="hidden" value="on"/> Environmental Sci</td>
		  <td><form:checkbox path="userDetails.curriculumsubjects" id="userDetails.curriculumsubjects4" value="Chemistry"/><input type="hidden" value="on"/> Chemistry</td>
          </tr>
          <tr>
          <td><form:checkbox path="userDetails.curriculumsubjects" id="userDetails.curriculumsubjects5" value="APChemistry"/><input type="hidden"  value="on"/> AP Chemistry</td>
          <td><form:checkbox path="userDetails.curriculumsubjects" id="userDetails.curriculumsubjects6" value="Astronomy"/><input type="hidden"  value="on"/> Astronomy</td>
          </tr>
          <tr>
          <td><form:checkbox path="userDetails.curriculumsubjects" id="userDetails.curriculumsubjects7" value="Physics"/><input type="hidden" value="on"/> Physics</td>
          <td><form:checkbox path="userDetails.curriculumsubjects" id="userDetails.curriculumsubjects8" value="APPhysics"/><input type="hidden" value="on"/> AP Physics</td>
          </tr>
          <tr>
          <td><form:checkbox path="userDetails.curriculumsubjects" id="userDetails.curriculumsubjects9" value="Anatomy"/><input type="hidden"  value="on"/> Anatomy</td>
          <td><form:checkbox path="userDetails.curriculumsubjects" id="userDetails.curriculumsubjects10" value="EarthScience"/><input type="hidden"  value="on"/> Earth Science</td>
          </tr>
          <tr>
          <td><form:checkbox path="userDetails.curriculumsubjects" id="userDetails.curriculumsubjects11" value="Biotechnology"/><input type="hidden"  value="on"/> Biotechnology</td>
          <td><form:checkbox path="userDetails.curriculumsubjects" id="userDetails.curriculumsubjects12" value="Geology"/><input type="hidden" value="on"/> Geology</td>
		  </tr>
          <tr>
          <td><form:checkbox path="userDetails.curriculumsubjects" id="userDetails.curriculumsubjects14" value="AdvancedIntScience"/><input type="hidden" value="on"/> Adv. Integrated Sci</td>
          <td><form:checkbox path="userDetails.curriculumsubjects" id="userDetails.curriculumsubjects13" value="IntegratedScience"/><input type="hidden" value="on"/> Integrated Sci</td>

          </tr>
          <tr>
          <td><form:checkbox path="userDetails.curriculumsubjects" id="userDetails.curriculumsubjects15" value="Other"/><input type="hidden" value="on"/> Other</td>
          <td></td>
          </tr>
          </table>
          
		 <p>Note: Specifying your curriculum topics is optional, but very helpful to the WISE development
			 team. This information guides the ongoing direction of WISE projects, allowing the creation of
		  projects that best serve the needs of our science teacher community. Thanks for your input!</p>
     </div>
    </dd>
     <div>&nbsp;</div>
     <dt id="layoutForLegal"><label for="legalAcknowledged" id="legalAcknowledged1"><spring:message code="signup.legalAcknowledged" /></label></dt>
	 <dd id="termsOfUse">
	     <form:checkbox path="legalAcknowledged" id="legalAcknowledged"/> 
     I agree to the <a href="termsofuse.html" onClick="return popupSpecial(this, 'terms')"> terms of use.</a>
     </dd>
       
      <dt><label for="password" id="password1"><spring:message code="signup.password" /></label>
</dt>
	  <dd><form:password path="userDetails.password" id="password" size="25" maxlength="25" tabindex="11"/>      		<span class="hint">Your password can contain up to 18 letters and/or numbers. Try to create a password that you can remember!<span class="hint-pointer"></span></span> </dd>

	  <dt><label for="repeatedPassword" id="repeatedPassword2"><spring:message code="signup.password.verify" /></label></dt>
	  <dd><form:password path="repeatedPassword" id="repeatedPassword" size="25" maxlength="25" tabindex="12"/>  <span class="hint">Retype your password.<span class="hint-pointer"></span></span></dd>
          
      </dl>
        
       
 	  <div id="regButtons">
 	    <input type="image" id="save" src="../<spring:theme code="register_save" />" 
    onmouseover="swapImage('save','../<spring:theme code="register_save_roll" />')" 
    onmouseout="swapImage('save','../<spring:theme code="register_save" />')"
    />
    <a href="../index.html"><input type="image" id="cancel" src="../<spring:theme code="register_cancel" />" 
    onmouseover="swapImage('cancel','../<spring:theme code="register_cancel_roll" />')" 
    onmouseout="swapImage('cancel','../<spring:theme code="register_cancel" />')"
    /> </a>	  </div>
           
</form:form>

</div> 

</div>   <!--End of the CenteredDiv -->

</body>
</html>

