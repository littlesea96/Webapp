<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>OOC webapp</title>
</head>
<body>
<form action="/user/edit" method="post">
    Username: <input type="text" name="username" value="${username}" readonly>
    <br>
    Name: <input type="text" name="name" value="${name}">
    <br>
    Surname: <input type="text" name="surname" value="${surname}">
    <br>
    Email: <input type="text" name="email" value="${email}">
    <br><br>
    <input type="submit" value="save">
</form>
</body>
</html>