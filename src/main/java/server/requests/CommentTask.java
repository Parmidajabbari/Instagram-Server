package server.requests;

import server.databaseManagement.ManagerHolder;
import server.databaseManagement.DatabaseOps;

import java.sql.Date;

public class CommentTask extends Task {

    private int postId;
    private String comment;
    private int ownerId;
    private String username;

    @Override
    public String doTask(ManagerHolder managerHolder) {
        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;
        try {
            int commentId = databaseOps.addComment(currentUserId, postId, comment, username);
            result = "{'task' : 'comment', 'error' : false, 'Result' : " + commentId +" }";
        }
        catch (Exception e){
            result = "{'task' : 'comment', 'error' : true, 'Result' : 'Something went wrong! Pleas try again'}";
        }
        return result;
    }


    // add the comment to database and send a respond

}
