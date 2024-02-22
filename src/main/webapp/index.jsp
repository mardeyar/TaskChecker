<%@ page import="com.task.taskchecker.model.Task" %>
<%@ page import="java.util.List" %>
<%@ page import="com.task.taskchecker.database.TaskDB" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>TaskChecker</title>
    <script src="${pageContext.request.contextPath}/js/operation.js"></script>

</head>

<body>
<h1><%= "To-Do List" %></h1>
    <table>
        <tr>
<%--            <th style="visibility: collapse">Task ID</th>--%>
            <th>Task Name</th>
            <th>Status</th>
            <th>Due Date</th>
            <th>Action</th>
        </tr>

        <!-- This block of code will iterate through the task list table in the TaskChecker DB -->
        <%
            TaskDB taskList = new TaskDB();
            List<Task> tasks;
            try {
                tasks = taskList.select();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            for (Task task : tasks) {
        %>
        <tr>
<%--            <td style="visibility: collapse"><%= task.getTaskId() %></td>--%>
            <td id="taskName_<%= task.getTaskId() %>"><%= task.getTaskName() %></td>
            <td><%= task.getTaskStatus() %></td>
            <td><%= task.getDueDate() %></td>
            <td>
                <form action="${pageContext.request.contextPath}/deleteTask" method="post">
                    <input type="hidden" name="taskId" value="<%= task.getTaskId() %>">
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
        <% } %>

    </table>
<%-- Section in the table designed for adding tasks --%>
<form action="${pageContext.request.contextPath}/addTask" method="post">
    <label for="taskName"/>
    <input type="text" id="taskName" name="taskName" placeholder="Task Name">
    <label for="taskStatus"/>
    <select id="taskStatus" name="taskStatus">
        <option value="In Progress">In Progress</option>
        <option value="Completed">Completed</option>
    </select>
<%--    <input type="text" id="taskStatus" name="taskStatus" placeholder="Task Status">--%>
    <label for="dueDate"/>
    <input type="date" id="dueDate" name="dueDate" placeholder="Due Date">
    <button type="submit">Add Task</button>
</form>

</body>

</html>