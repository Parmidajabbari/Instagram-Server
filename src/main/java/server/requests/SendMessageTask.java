package server.requests;

import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;

import java.sql.Date;
import java.sql.SQLException;

public class SendMessageTask extends Task{

    int receiverId;
    String text;

    @Override
    public String doTask(ManagerHolder managerHolder) throws SQLException {
        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;
        try {
            Date date = new Date(System.currentTimeMillis());
            databaseOps.sendMessage(currentUserId, receiverId, date, text);
            result = "{'task' : 'sendMessage', 'error' : false, 'Result' : 'done'}";

        }
        catch (Exception e){
            result = "{'task' : 'sendMessage', 'error' : true, 'Result' : 'Something went wrong!'}";
        }
        return result;
    }
}
