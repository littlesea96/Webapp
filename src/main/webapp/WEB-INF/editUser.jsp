<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>OOC webapp</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>
    <div class="container" style="margin-top: 15px;">
        <h3>Edit ${username}</h3>
        <form action="/user/edit" method="post">
            Username: <input type="text" name="username" value="${username}" readonly>
            <br>
            Name: <input type="text" name="name" value="${name}">
            <br>
            Surname: <input type="text" name="surname" value="${surname}">
            <br>
            Email: <input type="text" name="email" value="${email}">
            <br><br>
            <input class="btn btn-warning" type="submit" name="button" value="save">
        </form>
        <form action="/user" method="get">
            <input class="btn btn-primary" type="submit" name="button" value="back">
        </form>
    </div>
</body>
</html>