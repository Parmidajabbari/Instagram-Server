package server.requests;

import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;

import java.sql.SQLException;

public class LikeTask extends server.requests.Task {

    private int postId;
    private int postOwner;

    @Override
    public String doTask(ManagerHolder managerHolder) throws SQLException{
        DatabaseOps databaseOps = managerHolder.getDataBase();
        if( !databaseOps.isAlreadyLiked(currentUserId, postId) && !databaseOps.isBlocked(currentUserId, postOwner) && !databaseOps.isBlocked(postOwner,currentUserId)){
            databaseOps.likePost(currentUserId,postId);
        }
    }

    // like the post and add it to database

}
