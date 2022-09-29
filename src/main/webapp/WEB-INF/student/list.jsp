<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>-------Students Manager----------</h1>
<a href="/student?action=create">Create</a>
<table border="1", style="width: 50%">
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>AGE</th>
    </tr>
    <c:forEach var="st" items='${requestScope["listStudent"]}'>
        <tr>
            <td>${st.id}</td>
            <td>${st.name}</td>
            <td>${st.age}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>