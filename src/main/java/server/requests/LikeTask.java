package server.requests;

import server.DatabaseOps;
import server.ManagerHolder;

import java.sql.SQLException;

public class LikeTask extends server.requests.Task {

    private int postId;
    private int postOwner;

    @Override
    public void doTask(ManagerHolder managerHolder) throws SQLException{
        DatabaseOps databaseOps = managerHolder.getDataBase();
        if( !databaseOps.isAlreadyLiked(currentUserId, postId) && !databaseOps.isBlocked(currentUserId, postOwner) && !databaseOps.isBlocked(postOwner,currentUserId)){
            databaseOps.likePost(currentUserId,postId);
        }
    }

    // like the post and add it to database

}
