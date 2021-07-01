package server.data;

import java.util.ArrayList;

public class ConnectionList {

    String task;
    boolean error;
    String Result;
    ArrayList<User> connections;

    public ConnectionList(String task, boolean error, String result, ArrayList<User> connections) {
        this.task = task;
        this.error = error;
        this.Result = result;
        this.connections = connections;
    }

}
