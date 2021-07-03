package server.requests;

import com.google.gson.Gson;
import server.SSSocket;
import server.data.JsonProfile;
import server.data.Post;
import server.data.Profile;
import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Collections;

public class ProfileViewTask extends server.requests.Task {

    private int viewedUserId;

    @Override
    public String doTask(ManagerHolder managerHolder) {
        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result ;
        try {
            if( databaseOps.isBlocked(currentUserId, viewedUserId) ){
                result = "{'task' : 'profileView', 'error' : true, 'Result' : 'You have been blocked by this user!'}";
            }
            else if( databaseOps.isBlocked(viewedUserId, currentUserId) ){
                result = "{'task' : 'profileView', 'error' : true, 'Result' : 'You have blocked this user!'}";

            }
            else {
                Profile profile = databaseOps.showProfile(viewedUserId);
                boolean isFollowing = false;
                if( databaseOps.isAFollowingB(currentUserId, viewedUserId) )
                    isFollowing = true;
                ArrayList<Integer> posts = databaseOps.showUsersPosts(viewedUserId);
                posts.sort(Collections.reverseOrder());
                profile.setFollowing(isFollowing);
                profile.setPosts(posts);
                JsonProfile jsonProfile = new JsonProfile(profile, "profileView", false, "done");
                result = new Gson().toJson(jsonProfile);
                SSSocket socket = transferImage.getSsSocket();
                Blob blob = profile.getProPic();
                int blobLength = (int) blob.length();
                byte[] binaryImg = blob.getBytes(1, blobLength);
                transferImage.getOutput().writeUTF(result);
                socket.sendMessage(binaryImg);
                result = "{'task' : 'ignore', 'error' : true, 'Result' : 'Something went wrong! Pleas try again'}";
            }

        }
        catch (Exception e){
            result = "{'task' : 'profileView', 'error' : true, 'Result' : 'Something went wrong! Pleas try again'}";
            e.printStackTrace();
        }
        return result;
    }

    // send the data of the requested user to show it

}
