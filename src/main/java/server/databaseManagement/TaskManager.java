package server.databaseManagement;//import server.requests.Task;

import server.TransferImage;
import server.requests.Task;

import java.sql.SQLException;

public class TaskManager {
    private final ManagerHolder managerHolder;
    private TransferImage transferImage;

    public TaskManager(ManagerHolder managerHolder, TransferImage transferImage) {
        this.managerHolder = managerHolder;
        this.transferImage = transferImage;
    }

    public String doTask(String request) throws SQLException {
        Task task = TaskResolver.resolveTask(request);
        task.setTransferImage(transferImage);
        if (isUserSignedIn(task.currentUserId)) {
            return task.doTask(managerHolder);
        }
        return null;
    }

    private boolean isUserSignedIn(int userId) {
        return true;
    }
}
