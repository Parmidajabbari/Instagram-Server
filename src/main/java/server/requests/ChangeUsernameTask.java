package server.requests;

import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;

import java.sql.SQLException;

public class ChangeUsernameTask extends Task{

    String newUserName;

    @Override
    public String doTask(ManagerHolder managerHolder) throws SQLException {
        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;
        try {
            databaseOps.changeUsername(currentUserId, newUserName);
            result = "{'task' : 'changeUsername', 'error' : false, 'Result' : 'done' }";
        }
        catch (Exception e){
            result = "{'task' : 'changeUsername', 'error' : true, 'Result' : 'Something went wrong!}";
        }
        return result;        }
}
