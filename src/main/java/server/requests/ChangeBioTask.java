package server.requests;

import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;


import java.sql.SQLException;

public class ChangeBioTask extends Task{

    String newBio;

    @Override
    public String doTask(ManagerHolder managerHolder) throws SQLException {
        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;
        try {
            databaseOps.changeBio(currentUserId, newBio);
            result = "{'task' : 'changeBio', 'error' : false, 'Result' : 'done' }";
        }
        catch (Exception e){
            result = "{'task' : 'changeBio', 'error' : true, 'Result' : 'Something went wrong!}";
        }
        return result;
    }
}
