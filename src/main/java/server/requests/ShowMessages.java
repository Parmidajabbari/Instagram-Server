package server.requests;

import com.google.gson.Gson;
import server.data.Chat;
import server.data.Comment;
import server.data.CommentsCollection;
import server.data.Message;
import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;

import java.sql.SQLException;
import java.util.ArrayList;

public class ShowMessages extends Task{

    ArrayList<Integer> messagesId;

    @Override
    public String doTask(ManagerHolder managerHolder) throws SQLException {
        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;
        try {
            ArrayList<Message> chat = new ArrayList<>();
            for (int c : messagesId){
                chat.add(databaseOps.getMeesage(c));
            }
            Chat res = new Chat("showMessages", false, "done", chat);
            result = new Gson().toJson(res);
        }
        catch (Exception e){
            result = "{'Task' : 'showMessages', 'error' : true, 'Result' : 'Something went wrong!'}";
        }
        return result;
    }
}
