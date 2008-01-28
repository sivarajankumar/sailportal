<%@ include file="../include.jsp" %>

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

<!-- $Id: overview.jsp 997 2007-09-05 16:52:39Z archana $ -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "XHTML1-s.dtd" >

<html lang="en">
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8"></meta>

<link href="../../<spring:theme code="globalstyles"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="../../<spring:theme code="teacherprojectstylesheet" />" media="screen" rel="stylesheet" type="text/css" />


<script type="text/javascript" src="../.././javascript/tels/general.js"></script>

<title>Teacher:Projects - Report Problem </title>

</head>

<body>
<%@ include file="projectHeader.jsp"%>
 <a href="runmanager.html"> Back to Project Run Manager </a>
 <h2> REPORT A PROBLEM WITH A PROJECT </h2>

<div id="overviewContent"> 
<form>
<label> Name: </label> <br />
<label> Email: </label> <br />
<label> Project: </label> <br />
<label> Issue: </label> 
<select> 
<option> Trouble Signing In </option>
<option> Need Help Using WISE </option>
<option> Broken Link </option>
<option> Spelling or Factual Error </option>
<option> Error Messages </option>
<option> Problem Running Java </option>
<option> Problem running a Model/Simulation </option>
<option> Problem with a Student or Period </option>
<option> Problem with Project Editor (authoring) </option>
<option> Misc. Problem </option>
<option> Request for New Feature </option>
</select>
<br />
<label> Operating System: </label> 
<select> 
<option> Windows Vista </option>
<option> All other Windows </option>
<option> Mac OS X </option>
<option> Mac OS 9 or older </option>
<option> Linus </option>
<option> Other/Unknown </option>
</select>
<br />
<label> Web Browser: </label> 
<select> 
<option> Firefox/Mozilla </option>
<option> Internet Explorer </option>
<option> Safari </option>
<option> Netscape 4.0 or older </option>
<option> Opera </option>
<option> Other </option>
</select>
<br />
<label> Subject: </label> <input type="text" size="100" /> <br />
<label> Message: </label> <br />
<textarea cols="100" rows="10"> specify message </textarea> <br />
<ul>
<li> Please be as descriptive
as possible. Make sure to
give any error messages
or the URLs of any
broken links.
</li>
<li>
Make sure to note the
Activity and Step number
where a problem exists.
</li>
<li>
WISE staff will be able to
resolve your problems
more quickly and easily if
you provide as much
detail as possible.
</li>
</ul>

<input type="button" value="Cancel" />
<input type="submit" value="Send Message to WISE" />

</form>

<a href="runmanager.html"> Return to Project Run Manager </a>

</div>

</body>
</html>

