package server.requests;

import server.databaseManagement.ManagerHolder;
import server.databaseManagement.DatabaseOps;

import java.sql.Date;

public class CommentTask extends Task {

    private int postId;
    private String comment;
    private int ownerId;

    @Override
    public String doTask(ManagerHolder managerHolder) {
        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;
        try {
            int commentId = databaseOps.addComment(currentUserId, postId, comment);
            result = "{'task' : 'comment', 'error' : false, 'Result' : " + commentId +" }";
        }
        catch (Exception e){
            result = "{'task' : 'comment', 'error' : true, 'Result' : 'Something went wrong! Pleas try again'}";
        }
        return result;
    }


    // add the comment to database and send a respond

}
