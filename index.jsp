<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Admin Login</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style1.css">
</head>
<body>

<div class="container">
  <h2>Admin Login</h2>

  <form action="LoginServlet" method="post">
    <input type="text" name="username" placeholder="Username" required>
    <input type="password" name="password" placeholder="Password" required>
    <button type="submit">Login</button>
  </form>

  <p style="color:red; text-align:center;">
    <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
  </p>
</div>

</body>
</html>
