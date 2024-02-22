<%@ page import="com.task.taskchecker.dao.TaskDAO" %>
<%@ page import="com.task.taskchecker.model.Task" %>
<%@ page import="java.util.List" %>
<%@ page import="com.task.taskchecker.database.TaskDB" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>TaskChecker</title>
</head>

<body>
<h1><%= "To-Do List" %></h1>
    <table>
        <tr>
            <th>Task Name</th>
            <th>Info</th>
            <th>Status</th>
            <th>Due Date</th>
            <th>Action</th>
        </tr>

        <!-- This block of code will iterate through the task list table in the TaskChecker DB -->
        <%
            TaskDB taskList = new TaskDB();
            List<Task> tasks = taskList.select();
            for (Task task : tasks) {
        %>
        <tr>
            <td><%= task.getTaskName() %></td>
            <td><%= task.getTaskInfo() %></td>
            <td><%= task.getTaskStatus() %></td>
            <td><%= task.getDueDate() %></td>
            <td>
                <form action="${pageContext.request.contextPath}/deleteTask" method="post">
                    <input type="hidden" name="taskId" value="${task.getTaskId}">
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
    <label for="taskInfo"/>
    <input type="text" id="taskInfo" name="taskInfo" placeholder="Task Info">
    <label for="taskStatus"/>
    <input type="text" id="taskStatus" name="taskStatus" placeholder="Task Status">
    <label for="dueDate"/>
    <input type="date" id="dueDate" name="dueDate" placeholder="Due Date">
    <button type="submit">Add Task</button>
</form>

</body>

</html>