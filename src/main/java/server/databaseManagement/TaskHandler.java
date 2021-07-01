package server.databaseManagement;

import server.TransferImage;

import java.sql.SQLException;

public class TaskHandler {

    private final String task;
    private TransferImage transferImage;

    public TaskHandler(String task, TransferImage transferImage) {
        this.task = task;
        this.transferImage = transferImage;
    }

    public String doTask() throws SQLException {
        ManagerHolder managerHolder = new ManagerHolderImpl();
        TaskManager taskManager = new TaskManager(managerHolder, transferImage);
        return taskManager.doTask(task);
    }
    
}
