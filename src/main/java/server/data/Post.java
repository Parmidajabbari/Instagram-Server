package server.data;

import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;

public class Post {

    private final Blob image;
    private final String caption;
    private final int likes;
    private final int comments;
    private final String ownerName;
    private final String uploaded;
    private final int ownerId;

    public Post(Blob img, String caption, int likes, int comments, String ownerName, Date uploaded, int ownerId) throws SQLException {
        this.image = img;
        this.caption = caption;
        this.likes = likes;
        this.comments = comments;
        this.ownerName = ownerName;
        this.uploaded = uploaded.toString();
        this.ownerId = ownerId;
    }

    public String getCaption() {
        return caption;
    }

    public int getLikes() {
        return likes;
    }

    public int getComments() {
        return comments;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getUploaded() {
        return uploaded;
    }

    public Blob getImage() {
        return image;
    }

    public int getOwnerId() {
        return ownerId;
    }
}


