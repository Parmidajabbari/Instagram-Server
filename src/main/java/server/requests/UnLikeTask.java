package server.requests;

import server.databaseManagement.ManagerHolder;

public class UnLikeTask extends server.requests.Task {

    private int postId;

    @Override
    public String doTask(ManagerHolder managerHolder) {
        return null;
    }

    // unlike a post and change it in database

}
