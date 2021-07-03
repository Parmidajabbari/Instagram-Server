package server.data;

import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class JsonProfile {

    private String task;
    private boolean error;
    private String Result;
    private String userName;
    private String firstName;
    private String lastName;
    private String created;
    private String bio = "";
    private int followersNumber;
    private int followingNumber;
    private boolean isFollowing;
    private ArrayList<Integer> posts;

    public JsonProfile(Profile profile, String task, boolean error, String result) throws SQLException {
        this.userName = profile.getUserName();
        this.firstName = profile.getFirstName();
        this.lastName = profile.getLastName();
        this.created = profile.getCreated();
        this.bio = profile.getBio();
        this.followersNumber = profile.getFollowersNumber();
        this.followingNumber = profile.getFollowingNumber();
        this.isFollowing = profile.isFollowing();
        this.posts = profile.getPosts();
        this.task = task;
        this.error = error;
        this.Result = result;
    }


}
