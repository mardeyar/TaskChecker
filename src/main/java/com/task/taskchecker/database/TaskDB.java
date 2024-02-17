package com.task.taskchecker.database;

import com.task.taskchecker.dao.TaskDAO;
import com.task.taskchecker.model.Task;
import static com.task.taskchecker.database.SQLConnection.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDB implements TaskDAO {
    private static final String SQL_SELECT = "SELECT taskId, taskName, taskInfo, taskStatus, dueDate FROM task_list";
    private static final String SQL_SELECT_ONE = "SELECT taskId, taskName, taskInfo, taskStatus, dueDate FROM task_list WHERE taskId = ?";
    private static final String SQL_INSERT = "INSERT INTO task_list(taskId, taskName, taskInfo, taskStatus, dueDate) VALUES (?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE task_list SET taskName=?, taskInfo=?, taskStatus=?, dueDate=? WHERE taskId = ?";
    private static final String SQL_DELETE = "DELETE FROM task_list WHERE taskId = ?";

    // Insert the methods from the TaskDAO here
    @Override
    public int insert(Task task) throws SQLException {
        // Pre-requisites for a DB connection
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int resultSet = 0;

        // If this code can run, the table is empty and code should break off
        if (task.getTaskName() == null) {
            return 0;
        }

        // Attempt some data insertion
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT);

            // This block runs a prepared statement to insert the data
            preparedStatement.setInt(1, task.getTaskId());
            preparedStatement.setString(2, task.getTaskName());
            preparedStatement.setString(3, task.getTaskInfo());
            preparedStatement.setString(4, task.getTaskStatus());
            preparedStatement.setDate(5, (Date) task.getDueDate());

            resultSet = preparedStatement.executeUpdate();
        } catch (SQLSyntaxErrorException e) {
            System.err.println("SQL Syntax error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Caught exception: " + e.getMessage());
        } finally {
            // Close the connection to DB and prepared statement
            preparedStatement.close();
            connection.close();
        }
        return resultSet;
    }

    @Override
    public int update(Task task) throws SQLException {
        // Pre-requisites for a DB connection
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int resultSet = 0;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE);

            preparedStatement.setString(1, task.getTaskName());
            preparedStatement.setString(2, task.getTaskInfo());
            preparedStatement.setString(3, task.getTaskStatus());
            preparedStatement.setDate(4, (Date) task.getDueDate());
            preparedStatement.setInt(5, task.getTaskId());

            resultSet = preparedStatement.executeUpdate();
        } catch (SQLSyntaxErrorException e) {
            System.err.println("SQL Syntax error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Caught exception: " + e.getMessage());
        } finally {
            // Close the connection to DB and prepared statement
            preparedStatement.close();
            connection.close();
        }
        return resultSet;
    }

    @Override
    public int delete(int taskId) throws SQLException {
        // Pre-requisites for a DB connection
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int resultSet = 0;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, taskId);
            resultSet = preparedStatement.executeUpdate();
        } catch (SQLSyntaxErrorException e) {
            System.err.println("SQL Syntax error: " + e.getMessage());
        } finally {
            // Close the connection to DB and prepared statement
            preparedStatement.close();
            connection.close();
        }
        return resultSet;
    }

    @Override
    public int select(int taskId) throws SQLException {
        return 0;
    }

    @Override
    public List<Task> select() throws SQLException {
        // Pre-requisites for a DB connection
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        List<Task> tasks = new ArrayList<>();

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT);
            resultSet = preparedStatement.executeQuery();

            // While there is a next option in the resultSet
            while (resultSet.next()) {
                tasks.add(new Task(
                        resultSet.getInt("task_id"),
                        resultSet.getString("task_name"),
                        resultSet.getString("task_info"),
                        resultSet.getString("task_status"),
                        resultSet.getDate("due_date")
                ));
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return tasks;
    }
}
