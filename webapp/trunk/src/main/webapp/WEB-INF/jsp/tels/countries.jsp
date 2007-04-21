<ul>
<%
java.lang.String[] countries = {"apple", "archana", "bear", "bone", "cow", "deer", "doe", "ice cream", "mouse", "zebra"};
for (String country : countries) {
    if (country.contains(request.getParameter("sofar")) ) {
%><li><%= country %></li>
<%
    }
}
%>
</ul>