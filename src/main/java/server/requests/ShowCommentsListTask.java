package server.requests;

import com.google.gson.Gson;
import server.data.CommentsList;
import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;

import java.sql.SQLException;
import java.util.ArrayList;

public class ShowCommentsListTask extends Task{

    int postId;

    @Override
    public String doTask(ManagerHolder managerHolder) throws SQLException {

        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;

        try {
            ArrayList<Integer> comments = databaseOps.getCommentsList(postId);
            CommentsList commentsList =  new CommentsList("showCommentsList", false, "done", comments);
            result = new Gson().toJson(commentsList);
        }
        catch (Exception e){
            result = "{'task' : 'showCommentsList', 'error' : true, 'Result' : 'Something went wrong!'}";
        }
        return result;
    }
}
