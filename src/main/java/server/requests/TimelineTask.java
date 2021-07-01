package server.requests;

import com.google.gson.Gson;
import server.data.TimeLine;
import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;

import java.util.ArrayList;
import java.util.Collections;

public class TimelineTask extends server.requests.Task {


    @Override
    public String doTask(ManagerHolder managerHolder) {
        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;
        try {
            ArrayList<Integer> followings = databaseOps.showUsersFollowings(currentUserId);
            ArrayList<Integer> posts = new ArrayList<>();
            for( int following : followings){
                posts.addAll(databaseOps.showUsersPosts(following));
            }
            posts.addAll(databaseOps.showUsersPosts(currentUserId));
            posts.sort(Collections.reverseOrder());
            TimeLine timeLine =  new TimeLine("timeline", false, "done", posts);
            result = new Gson().toJson(timeLine);
        }
        catch (Exception e){
            result = "{'task' : 'timeline', 'error' : true, 'Result' : 'Something went wrong!'}";
        }
        return result;

    }

    // show the user and it's followings posts

}
