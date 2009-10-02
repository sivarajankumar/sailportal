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

<!-- $Id: header.jsp 368 2007-05-05 01:41:18Z archana $ -->

<div id="navigationL2" class="gradingL2">

	<ul>
		<li >
			<a href="../grading/overview.html"><spring:message code="teacher.grading.l2bar.1"/></a> </li>
       	<li>
       	    <a href="../grading/projectPickerGrading.html?gradeByType=step">Grade by Step</a> </li>
		<li>
			<a class="navigationL2_grading_highlight" style="color:#999999;" href="../grading/projectPickerGrading.html?gradeByType=group"><spring:message code="teacher.grading.l2bar.3"/></a> </li>
		<li>
			<a style="color:#999999;" href="#">Step Values</a> </li>
		<li>
			<a style="color:#999999;" href="#">Score Summary</a> </li>
		<li>
			<a style="color:#999999;" href="./projectPickerGrading.html?gradeByType=value">My Grading Progress</a> </li>
		<li>
			<a style="color:#999999;" href="#">Premade Comments</a> </li>
   </ul>

</div>	
