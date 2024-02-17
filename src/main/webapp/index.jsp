<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>TaskChecker</title>
</head>

<body>
    <h1><%= "To-Do List" %></h1>
    <form action="TaskServlet" method="post">
        <table>
            <tr>
                <th>Task Name</th>
                <th>Info</th>
                <th>Status</th>
                <th>Due Date</th>
                <th>Action</th>
            </tr>
            <!-- This block of code will iterate through the task list table in the TaskChecker DB -->
            <c:forEach var="task" items="${tasks}">

            </c:forEach>
        </table>
    </form>

    <a href="hello-servlet">Hello Servlet</a>
</body>

</html>