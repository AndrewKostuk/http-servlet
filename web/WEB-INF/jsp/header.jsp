<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${sessionScope.user != null}">
    <div id="logout">
        <form action="/logout" method="post">
            <button type="submit">logout</button>
        </form>
    </div>
</c:if>
<div id="locale">
    <form action="/locale" method="post">
        <button type="submit" name="lang" value="be-BY">BY</button>
        <button type="submit" name="lang" value="en-US">EN</button>
    </form>
</div>
<fmt:setLocale
        value="${sessionScope.lang != null ? sessionScope.lang : (param.lang != null ? param.lang : 'en-US')}"/>
<fmt:setBundle basename="translation"/>