<%@ include file="../common-taglibs.jsp" %>
<form:form id="home" method="post" action="j_acegi_security_check">
	<table>
		<tr>
			<td><label for="j_username"><spring:message code="username"/></label></td>
			<td><input class="dataBoxStyle" type="text" name="j_username" id="j_username" size="18"
					   maxlength="60"/></td>
		</tr>
		<tr>
			<td><label for="j_password"><spring:message code="password"/></label></td>
			<td><input class="dataBoxStyle" type="password" name="j_password" id="j_password" size="18"
					   maxlength="30"/></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="<spring:message code="signinheader"/>"/></td>
		</tr>
	</table>

</form:form>
<ul id="signInLinkPosition">
	<!--li><a href="forgotaccount/selectaccounttype.html" id="forgotlink"><spring:message
			   code="findalostusername"/></a>
	   </li-->
	<li><a href="signup.html" id="joinlink">Create new SCY account</a></li>
</ul>
