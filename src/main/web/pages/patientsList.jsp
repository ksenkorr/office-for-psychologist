<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<html>
<head>
    <title>Список пациентов</title>
</head>

<h3>Список пациентов</h3>

<c:set var="number" value="0" scope="page" />

<table frame="hsides" cellspacing="15">
    <tr>
        <th>#</th>
        <th>Имя</th>
        <th>Отчество</th>
        <th>Фамилия</th>
        <th>Сокращение</th>
        <th>Изменить данные</th>
        <th>Количество записей пациента</th>
        <th>Вывести записи пациента</th>
    </tr>
    <c:forEach items="${patients}" var="patient">
    <c:forEach items="${edCountPerPatient}" var="edCount">

    <tr>
        <c:set var="number" value="${number + 1}" scope="page"/>
        <td>${number}</td>
        <td>${patient.firstName}</td>
        <td>${patient.middleName}</td>
        <td>${patient.lastName}</td>
        <td>${patient.nameAcronym}</td>
        <td><input type="submit" formaction=""  value="Изменить"></td>
        <td>${edCount}</td>
        <td><input type="submit" formaction=""  value="Вывести"></td>
    </tr>
    </c:forEach>
    </c:forEach>


</table>

<p><a href="psychologistMenu">Возврат в меню</a></p>

</body>
</html>
