<%@ page import="com.task.taskchecker.model.Task" %>
<%@ page import="java.util.List" %>
<%@ page import="com.task.taskchecker.database.TaskDB" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/main.css">
    <link rel="icon" type="image/x-icon" href="img/favicon.png">
    <title>TaskChecker</title>
</head>

<body>

    <div id="container">
        <h1>Task<span id="checker">Checker</span></h1>
        <table>
            <!-- This block of code will iterate through the task list table in the TaskChecker DB -->
            <%
                TaskDB taskList = new TaskDB();
                List<Task> tasks;
                try {
                    tasks = taskList.select();
                    Collections.sort(tasks, Comparator.comparing(Task::getDueDate));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                // Display this block of text if there are no tasks
                if (tasks.isEmpty()) {
                    %>
            <tr>
                <td id="empty_list" colspan="4">You're all caught up on your tasks!</td>
            </tr>
            <%
                } else {
                    for (Task task : tasks) {
            %>
            <tr>
                <td id="task_name_display"><%= task.getTaskName() %></td>
                <td id="task_status_display"><%= task.getTaskStatus() %></td>
                <td id="due_date_display"><%= task.getDueDate() %></td>
                <td id="update_btns">
                    <form action="${pageContext.request.contextPath}/editTask" method="post">
                        <input type="hidden" name="taskId" value="<%= task.getTaskId() %>">
                        <button class="btn" type="submit"><img class="icon" src="img/done.png"></button>
                    </form>
                    <form action="${pageContext.request.contextPath}/deleteTask" method="post">
                        <input type="hidden" name="taskId" value="<%= task.getTaskId() %>">
                        <button class="btn" type="submit"><img class="icon" src="img/delete.png"></button>
                    </form>
                </td>
            </tr>
            <%
                    }
                }
            %>
            <tr id="bottom_tr">
                <td id="bottom_td" colspan="4">
                    <%-- Section in the table designed for adding tasks --%>
                    <form action="${pageContext.request.contextPath}/addTask" method="post">
                        <label for="taskName"/>
                        <input type="text" id="taskName" name="taskName" placeholder="Task Name" required>
                        <label for="dueDate"/>
                        <input type="date" id="dueDate" name="dueDate" required>
                        <button id="add" type="submit"><img class="icon" src="img/add.png"></button>

                    </form>
                </td>
            </tr>
        </table>
    </div>

</body>

</html>