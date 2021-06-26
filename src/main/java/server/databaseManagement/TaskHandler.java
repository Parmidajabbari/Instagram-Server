package server.databaseManagement;

import java.sql.SQLException;

public class TaskHandler {

    private final String task;

    public TaskHandler(String task) {
        this.task = task;
    }

    public String doTask() throws SQLException {
        ManagerHolder managerHolder = new ManagerHolderImpl();
        TaskManager taskManager = new TaskManager(managerHolder);
        return taskManager.doTask(task);
    }
    
}
