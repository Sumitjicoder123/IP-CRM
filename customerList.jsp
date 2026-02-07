<%@ page import="java.util.*, model.Customer" %>

<%
if (session.getAttribute("user") == null) {
    response.sendRedirect("index.jsp");
}
%>

<!DOCTYPE html>
<html>
<head>
  <title>Customers</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style1.css">

</head>
<body>

<div class="container">
  <h2>Customer Management</h2>

  <form action="CustomerServlet" method="post">
  	 <input type="hidden" name="action" value="add">
  	
    <input name="name" placeholder="Name" required>
    <input name="email" placeholder="Email" required>
    <input name="phone" placeholder="Phone" required>
    <input name="address" placeholder="Address" required>
    <button type="submit">Add Customer</button>
  </form>

  <table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Address</th>
        <th>Action</th>
    </tr>

<%
List<Customer> list = (List<Customer>) request.getAttribute("customers");
if (list != null) {
    for (Customer c : list) {
%>
    <tr>
        <td><%= c.getId() %></td>
        <td><%= c.getName() %></td>
        <td><%= c.getEmail() %></td>
        <td><%= c.getPhone() %></td>
        <td class="address"><%= c.getAddress() %></td>
        <td class="action">
            <a href="CustomerServlet?action=edit&id=<%= c.getId() %>">Edit</a>
            |
            <a href="CustomerServlet?action=delete&id=<%= c.getId() %>"
               onclick="return confirm('Delete this customer?')">Delete</a>
        </td>
    </tr>
<%
    }
}
%>
</table>

  <br>
  <div class="logout">
    <a href="LogoutServlet">Logout</a>
</div>
  
</div>

</body>
</html>
