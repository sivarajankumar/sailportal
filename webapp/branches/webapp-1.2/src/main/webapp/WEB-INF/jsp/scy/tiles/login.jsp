<%@ include file="../common-taglibs.jsp" %>
<form:form id="home" method="post" action="j_acegi_security_check">
	<table id="loginTable">
		<tr>
			<td class="loginTableLeft"><label for="j_username"><spring:message code="username"/></label></td>
			<td class="loginTableRight"><input class="dataBoxStyle" type="text" name="j_username" id="j_username" size="18"
					   maxlength="60"/></td>
		</tr>
		<tr>
			<td class="loginTableLeft"><label for="j_password"><spring:message code="password"/></label></td>
			<td class="loginTableRight"><input class="dataBoxStyle" type="password" name="j_password" id="j_password" size="18"
					   maxlength="30"/></td>
		</tr>
		<tr>
			<td class="loginTableLeft"></td>
			<td class="loginTableRight"><input type="submit" value="<spring:message code="signinheader"/>"/></td>
		</tr>
        <tr>
            <td class="loginTableBottom"colspan="2">

	<!--li><a href="forgotaccount/selectaccounttype.html" id="forgotlink"><spring:message
			   code="findalostusername"/></a>
	   </li-->
	<a href="signup.html" id="joinlink">Create new SCY account</a>

            </td>
        </tr>
	</table>

</form:form>

<script type="text/javascript">
   dojo.addOnLoad = function(){
    if(document.getElementById("j_username")){
        document.getElementById("j_username").focus();
    }
   }
</script>