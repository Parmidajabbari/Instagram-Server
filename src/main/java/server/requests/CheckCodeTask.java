package server.requests;

import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;

import java.sql.SQLException;

public class CheckCodeTask extends Task{

    String username;
    String email;
    int code;

    @Override
    public String doTask(ManagerHolder managerHolder) throws SQLException {
        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;
        try {
            if( databaseOps.isCodeCorrect(username, code) )
                result = "{'task' : 'checkCode', 'error' : false, 'result' : 'The code was correct!'}";
            else
                result = "{'task' : 'checkCode', 'error' : true, 'result' : 'The code was wrong!'}";

        }
        catch (Exception e){
            result = "{'task' : 'checkCode', 'error' : true, 'result' : 'Something went wrong!'}";
        }
        return result;
    }
}
