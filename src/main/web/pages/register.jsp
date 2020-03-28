<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Регистрация нового пациента</title>
</head>
<body>
<form action="register" method="post"enctype="application/x-www-form-urlencoded">
    <p>
        <input type="text" name="enteredFirstName" value="${param['firstName']}" placeholder="Имя">
    </p>
    <p>
        <input type="text" name="enteredMiddleName" value="${param['middleName']}" placeholder="Отчество">
    </p>
    <p>
        <input type="text" name="enteredLastName" value="${param['lastName']}" placeholder="Фамилия">
    </p>
    <p>
        <input type="text" name="enteredAcronym" value="${param['acronym']}" placeholder="Сокращение имени">
    </p>

    <p>
        <input type="text" name="enteredUserName" value="${param['username']}" placeholder="Имя пользователя">
    </p>
    <p>
        <input type="password" name="enteredPassword" placeholder="Пароль">
    </p>
    <p><input type="submit" value="Зарегистрировать"></p>

    <c:if test="${not empty requestScope['IsUserCreated']}">
        <p>
            Пациент зарегистрирован.
        </p>
    </c:if>

</form>




</body>
</html>
