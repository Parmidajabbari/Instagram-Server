package server.requests;

import server.ManagerHolder;
import server.DatabaseOps;

public class CommentTask extends Task {

    private int postId;
    private String comment;

    @Override
    public void doTask(ManagerHolder managerHolder) {
        DatabaseOps databaseOps = managerHolder.getDataBase();
        databaseOps.createComment();
        managerHolder.getNotificationManager().sendNotification(currentUserId);
    }


    // add the comment to database and send a respond

}
