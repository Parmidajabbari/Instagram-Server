package server.data;

import java.util.ArrayList;

public class TimeLine {

    String task;
    boolean error;
    String Result;
    ArrayList<Integer> posts;

    public TimeLine(String task, boolean error, String result, ArrayList<Integer> posts) {
        this.task = task;
        this.error = error;
        this.Result = result;
        this.posts = posts;
    }

}
