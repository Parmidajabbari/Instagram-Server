package server.requests;

import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;

import java.sql.SQLException;

public class FollowTask extends server.requests.Task {

    private int followedUserId;

    @Override
    public String doTask(ManagerHolder managerHolder)throws SQLException {
        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;
        try {
            int followerUserId = super.getCurrentUserId();
            if( databaseOps.isBlocked(followerUserId,followedUserId) || databaseOps.isBlocked(followedUserId,followerUserId) ){
                result = "{'Task' : 'follow', 'error' : true, 'Result' : 'You cannot follow this user!!'}";
            }
            else {
                databaseOps.followUser(followerUserId,followedUserId);
                result = "{'Task' : 'follow', 'error' : false, 'Result' : 'You followed this user successfully!!'}";
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            result = "{'Task' : 'follow', 'error' : true, 'Result' : 'Something went wrong!'}";
        };
        return result;
    }

    // add the user in database and send a respond

}
