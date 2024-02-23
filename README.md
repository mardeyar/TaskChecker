<div style="display: flex; align-items: center;">
  <img align="left" width="40" height="40" src="https://github.com/mardeyar/TaskChecker/blob/master/src/main/webapp/img/favicon.png" alt="Resume application project app icon">
  <h1>TaskChecker</h1>
</div>

## About
A basic task management application to help keep track of your tasks, TaskChecker was developed with the following languages:
* J2EE
* Java
* HTML / CSS

TaskChecker utilizes a GlassFish or Tomcat server to connect with a MySQL database for storing and managing tasks.

## Prerequisites
* An IDE of any kind; I used IntelliJ but any IDE should work
* [XAMPP Control Panel](https://www.apachefriends.org/download.html) to run your Apache and MySQL server
* An instance of [GlassFish](https://glassfish.org/download.html) or [Tomcat](https://tomcat.apache.org/download-90.cgi) to run as your server
* Your browser of choice: Chrome, Firefox, Edge etc.

You will need a pre-existing database for this to run as is. As the code is constructed, it will use a database called **taskchecker** and the table will be called **task_info**. Use the SQL script below to get yourself started:
```sql
CREATE DATABASE IF NOT EXISTS taskchecker;

USE taskchecker;

CREATE TABLE IF NOT EXISTS task_list (
    task_id INT AUTO_INCREMENT PRIMARY KEY,
    task_name VARCHAR(100),
    task_status VARCHAR(16),
    due_date DATE
);
```

## Usage
First, clone this repo to your local<br><br>
```git clone https://github.com/mardeyar/TaskChecker.git```<br><br>
Open up your IDE and import this project. From there, ensure you have properly configured your server for use in your IDE and that your WAR file has been deployed to the server. If you're unsure how to get the server working, please check the documentation for [GlassFish](https://glassfish.org/docs/) or [Tomcat](https://tomcat.apache.org/tomcat-8.5-doc/). If all is configured properly, run the program and your browser will open up to TaskChecker.

## Visuals & Features
On first run, TaskChecker will tell you that you're all caught up on your tasks. Click the calendar to choose your tasks due date then click the green + symbol to begin adding your tasks

![1](https://github.com/mardeyar/TaskChecker/assets/117761940/85a3017f-e432-4bec-930b-b257074e7c96)
![2](https://github.com/mardeyar/TaskChecker/assets/117761940/93603c21-c2b1-4f10-9f4b-13fc4eefd0ff)

Once you have added some tasks, they will start to populate the table. If you have completed a task, you can click the green check to mark it as done

![3](https://github.com/mardeyar/TaskChecker/assets/117761940/2445e7f3-d30a-45bf-9e8f-58c5d8936ed7)

If you no longer need the task displayed, you can click the red garbage can to delete the task from the database

![4](https://github.com/mardeyar/TaskChecker/assets/117761940/e46bbf4e-c1e9-4378-97b4-f55c7af06207)

## License
[MIT License](https://github.com/mardeyar/TaskChecker/blob/master/LICENSE)
