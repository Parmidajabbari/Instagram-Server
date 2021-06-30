package server.requests;

import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;

import java.sql.SQLException;

public class AddConnectionTask extends Task{

    int secondUserId;
    String currentUsername;
    String secondUsername;

    @Override
    public String doTask(ManagerHolder managerHolder) throws SQLException {
        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;
        try {
            if( databaseOps.isBlocked(currentUserId, secondUserId) || databaseOps.isBlocked(secondUserId, currentUserId) ){
                result = "{'task' : 'addConnection', 'error' : true, 'Result' : 'You cannot send message to this user!'}";
            }
            else {
                databaseOps.addNewConnection(currentUserId,secondUserId, currentUsername, secondUsername);
                result = "{'task' : 'addConnection', 'error' : false, 'Result' : 'done' }";
            }

        }
        catch (Exception e){
            result = "{'task' : 'addConnection', 'error' : true, 'Result' : 'something went wrong!'}";
        }
        return result;
    }
}
