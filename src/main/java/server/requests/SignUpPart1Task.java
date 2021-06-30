package server.requests;

import server.data.GoogleMail;
import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;

import java.sql.SQLException;

public class SignUpPart1Task extends Task{

    private String username;
    private String email;

    @Override
    public String doTask(ManagerHolder managerHolder) throws SQLException {
        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;
        try {
            System.out.println("teeest");
            if (databaseOps.doesUsernameAlreadyExist(username))
                result = "{'task' : 'SignUpPart1', 'error' : true, 'result' : 'Username Already Exists'}";
            else if (databaseOps.doesEmailAlreadyExist(email))
                result = "{'task' : 'SignUpPart1', 'error' : true, 'result' : 'Email Already Exists'}";
            else {
                int min = 1000, max = 9999;
                int code = (int) Math.floor(Math.random() * (max - min + 1) + min);
                String message = " Your Email Verification Code is: \n" + "\t" + code;
                String title = "Instagram Verification";
                //GoogleMail.Send(email, title, message);
                System.out.println(message);
                databaseOps.addNewVCode(username, code);
                result = "{'task' : 'SignUpPart1', 'error' : false, 'Result' : 'A code has been sent to your email.'}";
            }
        }
        catch (Exception e){
            result = "{'task' : 'SignUpPart1', 'error' : true, 'Result' : 'Something went wrong! Pleas try again'}";
            e.printStackTrace();
        }
        System.out.println(result);
        return result;
    }
}
