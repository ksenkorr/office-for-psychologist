<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Меню пациента</title>
</head>
<body>
<c:if test="${not empty sessionScope['verifiedUserName']}">
    <p>
        Здравствуйте, ${sessionScope['verifiedUserFMLName']}!
    </p>
</c:if>

<p>Выберите интересующий вас пункт меню</p>
<ul>
    <li>Изменить атентификационные данные</li>
    <li><a href="createEmotionalDiary">Записать эмоциональное состояние</a></li>
    <li>Вывести все записи эмоционального состояния</li>
</ul>

<p><a href="login">Выйти из системы</a></p>

</body>
</html>
