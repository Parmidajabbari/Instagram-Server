package server.data;

import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;

public class JsonPost {

    private String task;
    private boolean error;
    private String Result;
    //private final Blob image;
    private final String caption;
    private final int likes;
    private final int comments;
    private final String ownerName;
    private final String uploaded;
    private final int ownerId;
    private boolean isLiked;

    public JsonPost( Post post, String task, boolean error, String result, boolean isLiked ) throws SQLException {
        this.caption = post.getCaption();
        this.likes = post.getLikes();
        this.comments = post.getComments();
        this.ownerName = post.getOwnerName();
        this.uploaded = post.getUploaded();
        this.ownerId = post.getOwnerId();
        this.task = task;
        this.error = error;
        this.Result = result;
        this.isLiked = isLiked;
    }


}
