package server.requests;

import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;

import java.sql.Date;
import java.sql.SQLException;

public class SendMessage extends Task{

    int receiverId;
    String text;

    @Override
    public String doTask(ManagerHolder managerHolder) throws SQLException {
        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;
        try {
            Date date = new Date(System.currentTimeMillis());
            databaseOps.sendMessage(currentUserId, receiverId, date, text);
            result = "{'Task' : 'sendMessage', 'error' : false, 'Result' : 'done'}";

        }
        catch (Exception e){
            result = "{'Task' : 'sendMessage', 'error' : true, 'Result' : 'Something went wrong!'}";
        }
        return result;
    }
}
