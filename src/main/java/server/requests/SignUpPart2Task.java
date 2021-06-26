package server.requests;

import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;

import java.sql.Date;

public class SignUpPart2Task extends server.requests.Task {

    private String password;
    private String email;
    private String username;
    private Date created;

    @Override
    public String doTask(ManagerHolder managerHolder) {
        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;
        Date date = new Date(System.currentTimeMillis());
        try {
            int userId = databaseOps.addNewUser(username, email, password, date);
            result = "{'Task' : 'SignUpPart2', 'error' : false, 'Result' : " + userId +" }";
        }
        catch (Exception e){
            result = "{'Task' : 'SignUpPart2', 'error' : true, 'Result' : 'Something went wrong! Pleas try again'}";
        }
        return result;
    }

    // create a new server.data.User in database ( if possible ) and send a respond

}
