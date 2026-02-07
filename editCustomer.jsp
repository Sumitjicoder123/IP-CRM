<%@ page import="model.Customer" %>

<%
Customer c = (Customer) request.getAttribute("customer");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Customer</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style1.css">
</head>
<body>

<div class="container">
    <h2>Edit Customer</h2>

    <form action="CustomerServlet" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= c.getId() %>">

        <div class="form-row">
            <input type="text" name="name"
                   value="<%= c.getName() %>" placeholder="Name" required>

            <input type="email" name="email"
                   value="<%= c.getEmail() %>" placeholder="Email" required>
        </div>

        <div class="form-row">
            <input type="text" name="phone"
                   value="<%= c.getPhone() %>" placeholder="Phone" required>

            <input type="text" name="address"
                   value="<%= c.getAddress() %>" placeholder="Address" required>
        </div>

        <button type="submit">Update Customer</button>
    </form>
</div>

</body>
</html>
