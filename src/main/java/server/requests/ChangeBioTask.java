package server.requests;

import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;


import java.sql.SQLException;

public class ChangeBioTask extends Task{

    String bio;

    @Override
    public String doTask(ManagerHolder managerHolder) throws SQLException {
        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;
        try {
            databaseOps.changeBio(currentUserId, bio);
            result = "{'Task' : 'changeBio', 'error' : false, 'Result' : 'done' }";
        }
        catch (Exception e){
            result = "{'Task' : 'changeBio', 'error' : true, 'Result' : 'Something went wrong!}";
        }
        return result;
    }
}
