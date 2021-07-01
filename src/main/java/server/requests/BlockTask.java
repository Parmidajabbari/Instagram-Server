package server.requests;

import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;

public class BlockTask extends server.requests.Task {


    private int blockedUserId;

    @Override
    public String doTask(ManagerHolder managerHolder) {
        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;
        try {
            if( databaseOps.isBlocked(blockedUserId,currentUserId) ){
                result = "{'task' : 'block', 'error' : true, 'Result' : 'You cannot block this user!!'}";
            }
            else {
                if(databaseOps.isAFollowingB(currentUserId, blockedUserId))
                    databaseOps.unFollowUser(currentUserId,blockedUserId);
                if(databaseOps.isAFollowingB(blockedUserId,currentUserId))
                    databaseOps.unFollowUser(blockedUserId,currentUserId);
                databaseOps.blockUser(currentUserId, blockedUserId);
                result = "{'task' : 'block', 'error' : false, 'Result' : 'You have blocked this user successfully!!'}";
            }
        }
        catch (Exception e){
            //System.out.println(e.getMessage());
            result = "{'Task' : 'block', 'error' : true, 'Result' : 'Something went wrong!'}";
        };
        return result;
    }
    // change the database and send a respond

}
