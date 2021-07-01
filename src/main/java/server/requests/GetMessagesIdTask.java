package server.requests;

import com.google.gson.Gson;
import server.data.TimeLine;
import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;

import java.sql.SQLException;
import java.util.ArrayList;

public class GetMessagesIdTask extends Task{

    int secondUserId;

    @Override
    public String doTask(ManagerHolder managerHolder) throws SQLException {
        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;
        try{
            ArrayList<Integer> chat = databaseOps.getChatId(currentUserId, secondUserId);
            TimeLine res = new TimeLine("getMessagesId", false, "done", chat);
            result = new Gson().toJson(res);
        }
        catch (Exception e){
            result = "{'task' : 'getMessagesId', 'error' : true, 'Result' : 'Something went wrong!'}";
        }
        return result;
    }
}
