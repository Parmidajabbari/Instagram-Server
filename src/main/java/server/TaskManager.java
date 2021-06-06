package server;//import server.requests.Task;

import server.requests.Task;

public class TaskManager {
    private final ManagerHolder managerHolder;

    public TaskManager(ManagerHolder managerHolder) {
        this.managerHolder = managerHolder;
    }

    public void doTask(String request){
        Task task = TaskResolver.resolveTask(request);
        if (isUserSignedIn(task.currentUserId)) {
            task.doTask(managerHolder);
        }
    }

    private boolean isUserSignedIn(int userId) {
        return true;
    }
}
