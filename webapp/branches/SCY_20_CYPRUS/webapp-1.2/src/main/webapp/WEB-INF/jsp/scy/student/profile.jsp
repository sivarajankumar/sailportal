<%@ include file="../common-taglibs.jsp" %>
<tiles:insertDefinition name="default-page">
    <tiles:putAttribute name="main">

        <div id="centeredDiv">

            <form method="post" action="upload.html" enctype="multipart/form-data">
                <input type="file" name="file"/>
                <input type="submit"/>
            </form>

        </div>

        </div>  <!-- /* End of the CenteredDiv */-->

    </tiles:putAttribute>
</tiles:insertDefinition>

