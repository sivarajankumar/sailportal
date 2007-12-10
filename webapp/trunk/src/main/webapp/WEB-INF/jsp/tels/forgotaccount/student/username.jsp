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

<!-- $Id: login.jsp 341 2007-04-26 22:58:44Z hiroki $ -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link href="../../<spring:theme code="studentforgotstylesheet"/>" media="screen" rel="stylesheet"
    type="text/css" />    
<script type="text/javascript" src="../../javascript/general.js"></script>	
<title>Forgot username - student</title>
</head>

<body>
<h2 class="center"> FORGOTTEN USERNAME</h2>
<h1 class="center"> STUDENT </h1>


<div align="center"> 
<ul id="forgotusernamelist">
<li>
<b>Forgot your UserName?</b>
</li>
<li>
Recall that a username consists of your first name, initial of last
name, month of birth, and day of birth.
</li>
<li>
Example: A user named Jane Doe with a birthday on March 24
will generally have the username JaneD324.
</li>
<br /><br />
<li>
If this doesn't help you recall your Username, try one of the following: 
</li>
</ul>

</div>

<div align="center">

<div id="forgotusernamesuggestions" align="center">
<ol>
<li>
You can always ask your teacher for help
looking up your Username and Password.
</li>
<li>
Or if you know your Project Code you can find
your Username in the list of student names for
that project by <a href="enterprojectcode.html">clicking here.</a>
</li>
</ol>

</div>
<br />
<a align="center" href="../../index.html"> Return to Sign In</a>

</div>

</body>
</html>
