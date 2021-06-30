package server.data;

import java.util.ArrayList;

public class CommentsCollection {

    String task;
    boolean error;
    String result;
    ArrayList<Comment> comments;

    public CommentsCollection(String task, boolean error, String result, ArrayList<Comment> comments) {
        this.task = task;
        this.error = error;
        this.result = result;
        this.comments = comments;
    }

}
