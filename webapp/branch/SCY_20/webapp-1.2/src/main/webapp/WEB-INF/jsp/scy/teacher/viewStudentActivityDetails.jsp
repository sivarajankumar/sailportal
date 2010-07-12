<%@ include file="../common-taglibs.jsp" %>

<tiles:insertDefinition name="default-page">

    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <tiles:putAttribute name="main">

        <h1>${activity.name}</h1>
        <table>
            <tr>
                <td>Start date</td>
                <td>${activity.startDate}</td>
            </tr>
            <tr>
                <td>End date</td>
                <td>${activity.endDate}</td>
            </tr>
            <tr>
                <td>Note</td>
                <td>${activity.note}</td>
            </tr>
        </table>

    </tiles:putAttribute>
</tiles:insertDefinition>