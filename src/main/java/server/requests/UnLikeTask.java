package server.requests;

import server.databaseManagement.DatabaseOps;
import server.databaseManagement.ManagerHolder;

public class UnLikeTask extends server.requests.Task {

    private int postId;

    @Override
    public String doTask(ManagerHolder managerHolder) {
        DatabaseOps databaseOps = managerHolder.getDataBase();
        String result;
        try {
            if ( databaseOps.isAlreadyLiked(currentUserId, postId) ) {
                databaseOps.unlikePost(currentUserId, postId);
                result = "{'Task' : 'unlike', 'error' : false, 'Result' : 'You unliked this post successfully!!'}";
            }
            else {
                result = "{'Task' : 'unlike', 'error' : true, 'Result' : 'You cannot unlike this post!'}";

            }
        }
        catch (Exception e){
            //System.out.println(e.getMessage());
            result = "{'Task' : 'unlike', 'error' : true, 'Result' : 'something went wrong!'}";
        }
        return result;
    }

    // unlike a post and change it in database

}
