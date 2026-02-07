<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Customer</title>
</head>
<body>

<h2>Add Customer</h2>

<form action="customer" method="post">
    <input type="hidden" name="action" value="add">

    Name: <input type="text" name="name"><br><br>
    Email: <input type="email" name="email"><br><br>
    Phone: <input type="text" name="phone"><br><br>
    Address: <textarea name="address"></textarea><br><br>

    <input type="submit" value="Add Customer">
</form>

<br>
<a href="<%= request.getContextPath() %>/customer">View All Customers</a>

</body>
</html>
