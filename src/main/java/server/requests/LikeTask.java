package server.requests;

import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;

import java.sql.SQLException;

public class LikeTask extends server.requests.Task {

    private int postId;
    //private int postOwner;

    @Override
    public String doTask(ManagerHolder managerHolder) throws SQLException{
        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;
        try {
            System.out.println("entered likeTask");
            if (!databaseOps.isAlreadyLiked(currentUserId, postId)){
                // && !databaseOps.isBlocked(currentUserId, postOwner) && !databaseOps.isBlocked(postOwner, currentUserId)) {
                System.out.println("not liked");
                databaseOps.likePost(currentUserId, postId);
                result = "{'task' : 'like', 'error' : false, 'Result' : 'You liked this post successfully!!'}";
            }
            else {
                result = "{'task' : 'like', 'error' : true, 'Result' : 'You cannot like this post!'}";

            }
        }
        catch (Exception e){
            //System.out.println(e.getMessage());
            result = "{'Task' : 'like', 'error' : true, 'Result' : 'something went wrong!'}";
        }
        return result;
    }

    // like the post and add it to database

}
