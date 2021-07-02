package server.data;

import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Profile {

    private String task;
    private boolean error;
    private String Result;
    private String userName;
    private String firstName;
    private String lastName;
    private String created;
    private String bio;
    private int followersNumber;
    private int followingNumber;
    private boolean isFollowing;
    private ArrayList<Integer> posts;
    private String proPic;

    public Profile(String userName, String firstName, String lastName, Date created, String bio, int followersNumber, int followingNumber, Blob proPic) throws SQLException {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.created = created.toString();
        this.bio = bio;
        this.followersNumber = followersNumber;
        this.followingNumber = followingNumber;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void setResult(String result) {
        this.Result = result;
    }

    public void setFollowing(boolean following) {
        isFollowing = following;
    }

    public void setPosts(ArrayList<Integer> posts) {
        this.posts = posts;
    }


}
