<%@page import="java.util.Map"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.luxoft.training.akka.webcrawler.H2Helper"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WebCrawler</title>
    </head>
    <body>
        <%
            String keyword = request.getParameter("keyword");
            if (keyword == null) {
                keyword = "";
            }
        %>
        <form action="" method="post">
            <input type="text" name="keyword" size="50" value="<%= keyword %>"/>
            <input type="submit" value="Search"/>
        </form>
        <hr/>
        <%
            if (!"".equals(keyword)) {
                Map<String, String> searchResult = H2Helper.searchKeyword(keyword);
                out.println(searchResult.size() + " results found");
        %>
        <table>
            <tr>
                <th>Title</th>
            </tr>
        <%
                for (Map.Entry<String, String> entry: searchResult.entrySet()) {
        %>
            <tr>
                <td><a href="<%= entry.getValue()%>"><%= entry.getKey() %></a></td>
            </tr>
        <%
                }
            }
        %>
        </table>
    </body>
</html>
