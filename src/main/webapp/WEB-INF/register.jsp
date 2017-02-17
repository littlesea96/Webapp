<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>OOC webapp</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <h2>Register</h2>
        <form action="/register" method="post">
            Username: <input type="text" name="username" required="required">
            <br>
            Password: <input type="text" name="password" required="required">
            <br>
            Name: <input type="text" name="name">
            <br>
            Surname: <input type="text" name="surname">
            <br>
            Email: <input type="text" name="email">
            <br><br>
            <input class="btn btn-warning" type="submit" name="button" value="Submit">
        </form>
        <form action="/user" method="get">
            <input class="btn btn-primary" type="submit" name="button" value="Back">
        </form>
    </div>
</body>
</html>