package server.requests;

import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;

public class UnFollowTask extends server.requests.Task {

    private int unFollowedUserId;

    @Override
    public String doTask(ManagerHolder managerHolder) {
        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;
        try {
            if( !databaseOps.isAFollowingB(currentUserId, unFollowedUserId) ){
                result = "{'task' : 'unfollow', 'error' : true, 'Result' : 'You cannot Unfollow this user!'}";
            }
            else {
                databaseOps.unFollowUser(currentUserId, unFollowedUserId);
                result = "{'task' : 'unfollow', 'error' : false, 'Result' : 'Unfollowed successfully!'}";
            }
        }
        catch (Exception e){
           //System.out.println(e.getMessage());
            result = "{'Task' : 'unfollow', 'error' : true, 'Result' : 'Something went wrong!'}";
        }
        return result;
    }

    // change the database and send a respond

}
