package server.data;

import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Base64;

public class Post {

    private String task;
    private boolean error;
    private String result;
    private final String image;
    private final String caption;
    private final int likes;
    private final int comments;
    private final String ownerName;
    private final String uploaded;
    private final int ownerId;

    public Post(Blob img, String caption, int likes, int comments, String ownerName, Date uploaded, int ownerId) throws SQLException {
        this.image = convertToString(img);
        this.caption = caption;
        this.likes = likes;
        this.comments = comments;
        this.ownerName = ownerName;
        this.uploaded = uploaded.toString();
        this.ownerId = ownerId;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void setResult(String result) {
        this.result = result;
    }

    private String convertToString(Blob blob ) throws SQLException {
        int blobLength = (int) blob.length();
        byte[] binaryImg = blob.getBytes(1, blobLength);
        return Base64.getEncoder().encodeToString(binaryImg);
    }
}

