<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Меню психолога</title>
</head>
<body>
<c:if test="${not empty sessionScope['verifiedUserName']}">
    <p>
    Здравствуйте, ${sessionScope['verifiedUserFMLName']}!
    </p>
</c:if>

<p>Выберите интересующий вас пункт меню:</p>
<ul>
    <li><a href="register">Зарегистрировать нового пациента</a></li>
    <li><a href="patientsList">Вывести список пациентов</a></li>
</ul>
<p><a href="login">Выйти из системы</a></p>

</body>
</html>
