<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Список пациентов</title>
</head>
<body>

<h3>Список пациентов</h3>

<c:forEach items="${patients}" var="patient">
    <p>${patient.firstName} ${patient.middleName} ${patient.lastName} ${patient.nameAcronym}</p>
</c:forEach>

<a href="patientMenu">Возврат в меню</a>

</body>
</html>
