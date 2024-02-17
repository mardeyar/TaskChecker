package com.task.taskchecker.dao;

import com.task.taskchecker.model.Task;

import java.sql.SQLException;
import java.util.List;

public interface TaskDAO {

    public int insert(Task task) throws SQLException;
    public int update(Task task) throws SQLException;
    public int delete(int taskId) throws SQLException;
    public int select(int taskId) throws SQLException;
    public List<Task> select() throws SQLException;
}
