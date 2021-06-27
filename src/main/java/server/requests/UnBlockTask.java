package server.requests;

import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;

public class UnBlockTask extends server.requests.Task {

    private int unBlockedUserId;

    @Override
    public String doTask(ManagerHolder managerHolder) {
        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;
        try {
            if( databaseOps.isBlocked(unBlockedUserId, currentUserId) ){
                databaseOps.unBlockUser(currentUserId, unBlockedUserId);
                result = "{'Task' : 'unblock', 'error' : false, 'Result' : 'Unblocked successfully!'}";
            }
            else {
                result = "{'Task' : 'unblock', 'error' : true, 'Result' : 'You cannot Unblock this user!'}";
            }
        }
        catch (Exception e){
            //System.out.println(e.getMessage());
            result = "{'Task' : 'unblock', 'error' : true, 'Result' : 'Something went wrong!'}";
        }
        return result;
    }

    // change the database and send a respond

}
