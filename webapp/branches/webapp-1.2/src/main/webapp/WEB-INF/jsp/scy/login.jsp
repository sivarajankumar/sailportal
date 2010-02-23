<%@ include file="common-taglibs.jsp" %>

<tiles:insertDefinition name="default-page">
	<tiles:putAttribute name="extrahead">
		<script type="text/javascript">
			dojo.addOnLoad(function() {
				dojo.byId('j_username').focus();
			});
		</script>
	</tiles:putAttribute>
	<tiles:putAttribute name="main" value="/WEB-INF/jsp/scy/tiles/login.jsp"/>
</tiles:insertDefinition>