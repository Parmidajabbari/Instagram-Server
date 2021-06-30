package server.data;

import java.util.ArrayList;

public class ConnectionList {

    String task;
    boolean error;
    String result;
    ArrayList<User> connections;

    public ConnectionList(String task, boolean error, String result, ArrayList<User> connections) {
        this.task = task;
        this.error = error;
        this.result = result;
        this.connections = connections;
    }

}
