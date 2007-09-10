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
<link href="../../<spring:theme code="teacherhelpstylesheet" />" media="screen" rel="stylesheet"
    type="text/css" />  
<script type="text/javascript" src="../javascript/general.js"></script> 

<title>Teacher:Help - Contact WISE </title>
</head>

<body>
<%@ include file="helpHeader.jsp"%>


<div id="overviewContent">
<h3> CONTACT WISE </h3>
<p> To report a problem with WISE please fill in the information below. </p>
<p> Please be as detailed as possible in describing your problem. For instance, describe any error messages, broken URL links, the
Activity/Step for a problemmatic project page, etc. WISE staff will address your concerns as quickly as possible. </p>

<form>
<label> Your Name: </label> <input type="text" size="100" /><br />
<label> Email: </label> <input type="text" size="100" /><br />
<label> Type of Problem: </label> 
<select> 
<option> Trouble signing In </option>
<option> Need help using WISE </option>
<option> Problem with a student or class period </option>
<option> Broken web link </option>
<option> Misspellings or factual errors in a project </option>
<option> Error messages or programming bugs </option>
<option> Problem with Java </option>
<option> Need help customizing/editing projects </option>
<option> Request for a new WISE feature </option>
<option> Other Problem </option>
</select>
<br />
<label> Operating System: </label> 
<select> 
<option> Mac OS 9 </option>
<option> Mac OS X Tiger </option>
<option> Mac OS X Leopard</option>
<option> Windows 98/2000 </option>
<option> Windows Vista </option>
<option> Linux </option>
<option> Other </option>
</select>

<br />
<label> Web Browser: </label> 
<select> 
<option> Internet Explorer (Windows) </option>
<option> Firefox (Windows) </option>
<option> Firefox (Mac) </option>
<option> Safari (Windows) </option>
<option> Safari (Macintosh) </option>
</select>
<br />
<label> Title: </label> <input type="text" size="100" /> <br />
<label> Description: </label> <br />
<textarea cols="100" rows="10">  </textarea> <br />
<input type="submit" value="Send Email to WISE" />
<input type="button" value="Cancel" />

</form>

</div>


</body>
</html>
