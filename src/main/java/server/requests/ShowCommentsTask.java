package server.requests;

import com.google.gson.Gson;
import server.data.Comment;
import server.data.CommentsCollection;
import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;

import java.sql.SQLException;
import java.util.ArrayList;

public class ShowCommentsTask extends Task{

    ArrayList<Integer> commentId;

    @Override
    public String doTask(ManagerHolder managerHolder) throws SQLException {
        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;
        try {
            ArrayList<Comment> comments = new ArrayList<>();
            for (int c : commentId){
                comments.add(databaseOps.showComment(c));
            }
            CommentsCollection res = new CommentsCollection("showComments", false, "done", comments);
            result = new Gson().toJson(res);
        }
        catch (Exception e){
            result = "{'task' : 'showComments', 'error' : true, 'Result' : 'Something went wrong!'}";
        }
        return result;
    }
}
