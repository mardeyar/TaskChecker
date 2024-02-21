package com.task.taskchecker.dao;

import com.task.taskchecker.model.Task;

import java.sql.SQLException;
import java.util.List;

public interface TaskDAO {

    void insert(Task task) throws SQLException;
    void update(Task task) throws SQLException;
    void delete(int taskId) throws SQLException;
    //public Task select(int taskId) throws SQLException;
    List<Task> select() throws SQLException;
}
