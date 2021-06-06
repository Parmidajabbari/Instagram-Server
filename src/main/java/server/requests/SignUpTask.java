package server.requests;

import server.ManagerHolder;

public class SignUpTask extends server.requests.Task {

    private String password;
    private String email;

    @Override
    public void doTask(ManagerHolder managerHolder) {

    }

    // create a new server.User in database ( if possible ) and send a respond

}
