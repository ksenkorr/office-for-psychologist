<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Регистрация нового пациента</title>
</head>
<body>
<form:form modelAttribute="form" action="register" method="post" enctype="application/x-www-form-urlencoded">
    <p>
        <form:input type="text" path="firstName" placeholder="Имя"/>
        <form:errors path="firstName" cssStyle="color: firebrick" />
    </p>
    <p>
        <form:input type="text" path="middleName" placeholder="Отчество"/>
        <form:errors path="middleName" cssStyle="color: firebrick" />
    </p>
    <p>
        <form:input type="text" path="lastName"  placeholder="Фамилия"/>
        <form:errors path="lastName" cssStyle="color: firebrick" />
    </p>
    <p>
        <form:input type="text" path="acronym"  placeholder="Сокращение имени"/>
        <form:errors path="acronym" cssStyle="color: firebrick" />
    </p>

    <p>
        <form:input type="text" path="login"  placeholder="Имя пользователя"/>
        <form:errors path="login" cssStyle="color: firebrick" />

    </p>
    <p>
        <form:input type="password" path="password" placeholder="Пароль"/>
        <form:errors path="password" cssStyle="color: firebrick" />
    </p>
    <p><input type="submit" value="Зарегистрировать"></p>

</form:form>

<c:if test="${not empty requestScope['userCreated']}">
    <p>
        Пациент зарегистрирован.
    </p>
</c:if>


<a href="psychologistMenu">Возврат в меню</a>


</body>
</html>
