<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход в систему</title>
</head>
<body>

<h3>Вход в систему "Кабинет психолога"</h3>

<form method="post" action="login" enctype="application/x-www-form-urlencoded" >

    <p>
        <input type="text" name="enteredUsername" value="${param['username']}" placeholder="Имя пользователя">
    </p>
    <p>
        <input type="password" name="enteredPassword" placeholder="Пароль">
    </p>
    <p><input type="submit" value="Войти"></p>

</form>

</body>
</html>
