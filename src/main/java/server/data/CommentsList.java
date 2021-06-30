package server.data;

import java.util.ArrayList;

public class CommentsList {

    String task;
    boolean error;
    String result;
    ArrayList<Integer> comments;

    public CommentsList(String task, boolean error, String result, ArrayList<Integer> comments) {
        this.task = task;
        this.error = error;
        this.result = result;
        this.comments = comments;
    }

}
