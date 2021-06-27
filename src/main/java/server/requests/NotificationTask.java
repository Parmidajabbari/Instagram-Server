package server.requests;

import server.databaseManagement.ManagerHolder;

public class NotificationTask extends server.requests.Task {


    private String event;

    @Override
    public String doTask(ManagerHolder managerHolder) {
        return null;

    }

    // send a notification according to the event

}
