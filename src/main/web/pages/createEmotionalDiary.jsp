<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Записать эмоциональное состояние</title>
</head>
<body>
<h3>Запись эмоционального состояния</h3>

<form modelAttribute="form" action="createEmotionalDiary" method="post" enctype="application/x-www-form-urlencoded">
    <p>Опишите свое эмоциональное состояние в нескольких предложениях.<br>
        Старайтесь не думать и писать первое, что придет в голову.</p>
    <p><textarea rows="10" cols="60" name="diary"></textarea></p>
    <p><input type="submit" value="Записать"></p>

</form>

<c:if test="${not empty requestScope['diaryCreated']}">
    <p>
        Запись зафиксирована.
    </p>

</c:if>

<a href="psychologistMenu">Возврат в меню</a>

</body>
</html>
