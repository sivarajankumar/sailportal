<!-- 
 * Copyright (c) 2007 Regents of the University of California (Regents). Created
 * by TELS, Graduate School of Education, University of California at Berkeley.
 *
 * This software is distributed under the GNU Lesser General Public License, v2.
 *
 * Permission is hereby granted, without written agreement and without license
 * or royalty fees, to use, copy, modify, and distribute this software and its
 * documentation for any purpose, provided that the above copyright notice and
 * the following two paragraphs appear in all copies of this software.
 *
 * REGENTS SPECIFICALLY DISCLAIMS ANY WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE. THE SOFTWAREAND ACCOMPANYING DOCUMENTATION, IF ANY, PROVIDED
 * HEREUNDER IS PROVIDED "AS IS". REGENTS HAS NO OBLIGATION TO PROVIDE
 * MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
 *
 * IN NO EVENT SHALL REGENTS BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
 * SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
 * ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
 * REGENTS HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
-->
 
<!-- $Id$ -->

<div id="banner" class="welcome1">
	<img src="<spring:theme code="banner"/>" alt="<spring:message code="wise.banner.alttext" />" />
</div>

	<div class="center heading" ><spring:message code="wise.banner.student" />
	</div>
	<div class="right pandaAlign">	<img src="<spring:theme code="panda"/>" alt="<spring:message code="wise.banner.panda.alttext" />" />
	</div>
    <div>
    <p class="userinfo">
    Hi
	<authz:authentication operation="username" />
	</p>
	<p class="userlogout"> <%@ include file="../logout.jsp"%> </p>
	</div>
