package server.requests;

import server.TransferImage;
import server.databaseManagement.ManagerHolder;

import java.sql.SQLException;

abstract public class Task {
    protected String task;
    public int currentUserId;
    public TransferImage transferImage;

    public void setTransferImage(TransferImage transferImage) {
        this.transferImage = transferImage;
    }

    abstract public String doTask(ManagerHolder managerHolder) throws SQLException;

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(int currentUserId) {
        this.currentUserId = currentUserId;
    }
}
