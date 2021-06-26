package server.databaseManagement;//import server.requests.Task;

import server.requests.Task;

import java.sql.SQLException;

public class TaskManager {
    private final ManagerHolder managerHolder;

    public TaskManager(ManagerHolder managerHolder) {
        this.managerHolder = managerHolder;
    }

    public String doTask(String request) throws SQLException {
        Task task = TaskResolver.resolveTask(request);
        if (isUserSignedIn(task.currentUserId)) {
            return task.doTask(managerHolder);
        }
        return null;
    }

    private boolean isUserSignedIn(int userId) {
        return true;
    }
}
