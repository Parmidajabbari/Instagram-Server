package server.requests;

import com.google.gson.Gson;
import server.SSSocket;
import server.data.JsonPost;
import server.data.Post;
import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;

import java.sql.Blob;

public class PostViewTask extends server.requests.Task {

    private int postId;

    @Override
    public String doTask(ManagerHolder managerHolder) {

        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;

        try {
            Post post = databaseOps.getPost(postId);
            if( post == null){
                result = "{'task' : 'postView', 'error' : true, 'Result' : 'Something went wrong! Pleas try again'}";
            }
            else {
                boolean isLiked = false;
                if( databaseOps.isAlreadyLiked(currentUserId, postId) )
                    isLiked = true;
                JsonPost jsonPost = new JsonPost(post, "postView", false, "done", isLiked);
                result = new Gson().toJson(jsonPost);
                SSSocket socket = transferImage.getSsSocket();
                Blob blob = post.getImage();
                int blobLength = (int) blob.length();
                byte[] binaryImg = blob.getBytes(1, blobLength);
                transferImage.getOutput().writeUTF("getImage");
                socket.sendMessage(binaryImg);
            }
        }
        catch (Exception e){
            result = "{'task' : 'postView', 'error' : true, 'Result' : 'Something went wrong! Pleas try again'}";
        }
        return result;

    }

    // send the data for the requested post

}
