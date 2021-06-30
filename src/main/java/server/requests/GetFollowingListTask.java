package server.requests;

import com.google.gson.Gson;
import server.data.ConnectionList;
import server.data.User;
import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;

import java.sql.SQLException;
import java.util.ArrayList;

public class GetFollowingListTask extends Task{
    @Override
    public String doTask(ManagerHolder managerHolder) throws SQLException {
        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;
        try {
            ArrayList<User> followingList = databaseOps.getFollowingList(currentUserId);
            ConnectionList res = new ConnectionList("getFollowingList", false, "done", followingList);
            result = new Gson().toJson(res);
        }
        catch (Exception e){
            result = "{'Task' : 'getFollowingList', 'error' : true, 'Result' : 'Something went wrong!'}";
        }
        return result;
    }
}
