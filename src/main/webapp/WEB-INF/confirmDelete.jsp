<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>OOC webapp</title>
</head>
<body>
<p>Are you sure you want to delete ${username}?</p>
<form action="/user/delete" method="post">
    <br><br>
    <input type="submit" name="confirm" value="yes">
    <input type="hidden" name="username" value="${username}">
    <input type="submit" name="confirm" value="no">
</form>
</body>
</html>