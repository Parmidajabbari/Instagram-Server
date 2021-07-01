package server.data;

import java.util.ArrayList;

public class CommentsCollection {

    String task;
    boolean error;
    String Result;
    ArrayList<Comment> comments;

    public CommentsCollection(String task, boolean error, String result, ArrayList<Comment> comments) {
        this.task = task;
        this.error = error;
        this.Result = result;
        this.comments = comments;
    }

}
