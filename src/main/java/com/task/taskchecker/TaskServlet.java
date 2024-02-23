package com.task.taskchecker;

import com.task.taskchecker.dao.TaskDAO;
import com.task.taskchecker.database.TaskDB;
import com.task.taskchecker.model.Task;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TaskServlet extends HttpServlet {

    private TaskDAO td;
    // Because the dueDate variable in each method is a String, need to convert to a Date object.
    // It will also need to be converted from that into a SQL date format
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void init() throws ServletException {
        super.init();
        td = new TaskDB();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TaskDAO taskDAO = new TaskDB();

        try {
            List<Task> tasks = taskDAO.select();
            request.setAttribute("tasks", tasks);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathMap = request.getServletPath();
        System.out.println(pathMap);
        switch (pathMap) {
            case "/addTask":
                addTask(request, response);
                break;
            case "/editTask":
                editTask(request, response);
                break;
            case "/deleteTask":
                deleteTask(request, response);
                break;
            default:
                response.sendRedirect("index.jsp");
                break;
        }
    }

    public void addTask(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String taskName = request.getParameter("taskName");
        String dueDateString = request.getParameter("dueDate");

        try {
            Date date = df.parse(dueDateString);
            java.sql.Date dueDate = new java.sql.Date(date.getTime());

            Task newTask = new Task();
            newTask.setTaskName(taskName);
            newTask.setDueDate(dueDate);

            td.insert(newTask);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        response.sendRedirect("index.jsp");
    }

    private void editTask(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int taskId = Integer.parseInt(request.getParameter("taskId"));

        try {
            Task editTask = new Task();
            editTask.setTaskId(taskId);
            td.update(taskId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("index.jsp");
    }

    private void deleteTask(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        try {
            td.delete(taskId);
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
        response.sendRedirect("index.jsp");
    }
}