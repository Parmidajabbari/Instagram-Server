package server.requests;

import com.google.gson.Gson;
import server.data.CommentsList;
import server.data.ConnectionList;
import server.data.User;
import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;

import java.util.ArrayList;

public class DirectTask extends server.requests.Task {

    private int secondUserId;

    @Override
    public String doTask(ManagerHolder managerHolder) {

        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;
        try {
            ArrayList<User> connections = databaseOps.getConnectionsList(currentUserId);
            ConnectionList res = new ConnectionList("direct", false, "done", connections);
            result = new Gson().toJson(res);
        }
        catch (Exception e){
            result = "{'task' : 'direct', 'error' : true, 'Result' : 'Something went wrong!'}";
        }
        return result;
    }

    // send the message to the other user

}
