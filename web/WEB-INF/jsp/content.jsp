<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Content</title>
</head>
<body>
<%@ include file="header.jsp" %>
<div>
    <span>My name is Govanni Jorgio. Но друзья завуд мэня просто Джованни.</span>
    <p>Flights size: ${requestScope.flights.size()}</p>
    <p>id 1: ${requestScope.flights.get(0).getId()}</p>
    <p>id 2: ${requestScope.flights[1].getId()}</p>
    <p>map value: ${sessionScope.flightsMap.get(1)}</p>
    <p>map value: ${sessionScope.flightsMap[1]}</p>
    <p>cookie: ${cookie["JSESSIONID"].value}, unique</p>
    <p>header: ${header["cookie"]}, all cookies</p>
    <p>param: ${param["id"]}</p>
    <p>param: ${param.something}</p>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>