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

<p>
    Выберите интересующий вас пункт меню, представленный ниже:
</p>
<p>

    <a href="register">Зарегистрировать нового пациента</a>

</p>
<p>
    Изменить регистрационные данные пациента
</p>
<p>
    Вывести список пациентов
</p>

</body>
</html>
