package server.requests;

import server.databaseManagement.ManagerHolder;
import server.databaseManagement.DatabaseOps;

public class CommentTask extends Task {

    private int postId;
    private String comment;

    @Override
    public String doTask(ManagerHolder managerHolder) {
        DatabaseOps databaseOps = managerHolder.getDataBase();
        databaseOps.createComment();
        managerHolder.getNotificationManager().sendNotification(currentUserId);
        return null;
    }


    // add the comment to database and send a respond

}
