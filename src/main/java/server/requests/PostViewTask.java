package server.requests;

import com.google.gson.Gson;
import server.data.Post;
import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;

public class PostViewTask extends server.requests.Task {

    private int postId;

    @Override
    public String doTask(ManagerHolder managerHolder) {

        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;

        try {
            Post post = databaseOps.getPost(postId);
            if( post == null){
                result = "{'Task' : 'postView', 'error' : true, 'Result' : 'Something went wrong! Pleas try again'}";
            }
            else {
                post.setTask("postView");
                post.setError(false);
                post.setResult("done");
                result = new Gson().toJson(post);
            }

        }
        catch (Exception e){
            result = "{'Task' : 'postView', 'error' : true, 'Result' : 'Something went wrong! Pleas try again'}";
        }
        return result;

    }

    // send the data for the requested post

}
