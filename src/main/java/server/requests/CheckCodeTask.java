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
            System.out.println(code);
            if( databaseOps.isCodeCorrect(username, code) ) {
                System.out.println("coreccct");
                result = "{'task' : 'checkCode', 'error' : false, 'Result' : 'The code was correct!'}";
            }
            else
                result = "{'task' : 'checkCode', 'error' : true, 'Result' : 'The code was wrong!'}";

        }
        catch (Exception e){
            result = "{'task' : 'checkCode', 'error' : true, 'Result' : 'Something went wrong!'}";
        }
        return result;
    }
}
