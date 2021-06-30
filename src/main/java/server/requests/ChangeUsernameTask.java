package server.requests;

import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;

import java.sql.SQLException;

public class ChangeUsernameTask extends Task{

    String username;

    @Override
    public String doTask(ManagerHolder managerHolder) throws SQLException {
        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;
        try {
            databaseOps.changeUsername(currentUserId, username);
            result = "{'Task' : 'changeUsername', 'error' : false, 'Result' : 'done' }";
        }
        catch (Exception e){
            result = "{'Task' : 'changeUsername', 'error' : true, 'Result' : 'Something went wrong!}";
        }
        return result;        }
}
