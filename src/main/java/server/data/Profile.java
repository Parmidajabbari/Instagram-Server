package server.data;

import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Profile {

    private String userName;
    private String firstName;
    private String lastName;
    private String created;
    private String bio = "";
    private int followersNumber;
    private int followingNumber;
    private boolean isFollowing;
    private ArrayList<Integer> posts;
    private Blob proPic;

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCreated() {
        return created;
    }

    public String getBio() {
        return bio;
    }

    public int getFollowersNumber() {
        return followersNumber;
    }

    public int getFollowingNumber() {
        return followingNumber;
    }

    public boolean isFollowing() {
        return isFollowing;
    }

    public ArrayList<Integer> getPosts() {
        return posts;
    }

    public Blob getProPic() {
        return proPic;
    }

    public Profile(String userName, String firstName, String lastName, Date created, String bio, int followersNumber, int followingNumber, Blob proPic) throws SQLException {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.created = created.toString();
        this.bio = bio;
        this.followersNumber = followersNumber;
        this.followingNumber = followingNumber;
        this.proPic = proPic;
    }


    public void setFollowing(boolean following) {
        isFollowing = following;
    }

    public void setPosts(ArrayList<Integer> posts) {
        this.posts = posts;
    }


}
