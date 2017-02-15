<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>OOC webapp</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>
    <table  class="table table-striped center">
        <thead>
        <tr>
            <td>Username</td>
            <td>Name</td>
            <td>Surname</td>
            <td>Email</td>
        </tr>
        </thead>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td>${user.username}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.email}</td>
                <td><a class="btn btn-default" href="/user/edit?username=${user.username}">Edit</a></td>
                <td><a class="btn btn-default" href="/user/delete?username=${user.username}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
    <form action="/user" method="post">
        <input type="submit" name="addUser" value="Add User">
    </form>
</body>
</html>