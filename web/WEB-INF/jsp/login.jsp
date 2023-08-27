<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Login</title>
</head>
<body>
<%@ include file="header.jsp" %>
<form action="/login" method="post">
    <label for="email"><fmt:message key="page.login.email"/>:
        <input type="text" name="email" id="email" required>
    </label><br>
    <label for="password"><fmt:message key="page.login.password"/>:
        <input type="password" name="password" id="password" required>
    </label>
    <button type="submit"><fmt:message key="page.login.login"/></button>
    <a href="/registration">
        <button type="button"><fmt:message key="page.login.registration"/></button>
    </a>
</form>
<c:if test="${param.error != null}">
    <div style="color:red;">
        <fmt:message key="page.login.authentication.error"/>
    </div>
</c:if>
</body>
</html>
