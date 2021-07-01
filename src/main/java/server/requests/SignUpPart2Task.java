package server.requests;

import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;

import java.sql.Date;

public class SignUpPart2Task extends server.requests.Task {

    private String password;
    private String email;
    private String currentUsername;
    private Date created;

    @Override
    public String doTask(ManagerHolder managerHolder) {
        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;
        Date date = new Date(System.currentTimeMillis());
        try {
            System.out.println(" given username :   " + currentUsername);
            int userId = databaseOps.addNewUser(currentUsername, email, password, date);
            result = "{'task' : 'signUpPart2', 'error' : false, 'Result' : " + userId +" }";
        }
        catch (Exception e){
            result = "{'task' : 'signUpPart2', 'error' : true, 'Result' : 'Something went wrong! Pleas try again'}";
            e.printStackTrace();
        }
        return result;
    }

    // create a new server.data.User in database ( if possible ) and send a respond

}
