<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>hello world Андрей</h1>
<h1>Список билетов для ${requestScope.flightId}</h1>
<ul>
    <c:forEach var="ticket" items="${requestScope.tickets}">
        <li>
                ${fn:toLowerCase(ticket.description)}
        </li>
    </c:forEach>
</ul>
</body>
</html>
