package server.requests;

import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;

public class LogInTask extends server.requests.Task {

    private String password;
    private String username;

    @Override
    public String doTask(ManagerHolder managerHolder) {
        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;
        try {
            System.out.println( "username :   " + username + " password:  " + password);
            int userId = databaseOps.loginUser(username, password);
            if( userId == -1 )
                result = "{'task' : 'login', 'error' : true, 'Result' : 'Username or Password is wrong!'}";
            else
                result = "{'task' : 'login', 'error' : false, 'Result' : " + userId +" }";
        }
        catch (Exception e){
            result = "{'task' : 'login', 'error' : true, 'Result' : 'Something went wrong! Pleas try again'}";
        }
        return result;
    }

    // check if this user exists in database and send a respond

}
