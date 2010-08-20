<%@ include file="../common-taglibs.jsp" %>
<tiles:insertDefinition name="default-page">
    <tiles:putAttribute name="main">

        <table>
            <tr>
                <td>
                    <strong>First name</strong>
                </td>
                <td>
                    <s:ajaxTextField model="${userDetails}" property="firstName"/>
                </td>

            </tr>
        </table>

        <div id="centeredDiv">

            <form method="post" action="upload.html" enctype="multipart/form-data">
                <input type="file" name="file"/>
                <input type="submit"/>
            </form>

        </div>

        </div>  <!-- /* End of the CenteredDiv */-->

    </tiles:putAttribute>
</tiles:insertDefinition>


