package server;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {

    private String userName;
    private int password;
    private String email;
    private ArrayList<Long> followers = new ArrayList<>();
    private ArrayList<Long> followings = new ArrayList<>();
    private long userId;
    private String bio;
    private static long idGenerate= 10^6;
    private static String userNameError;
    private static String passwordError;

    public User(String userName , String password , String email) {
        this.userName = userName;
        this.password = password.hashCode();
        this.email = email;
        this.userId = generateId();
    }

    public String getUserName() {
        return userName;
    }

    public static boolean isUserAcceptable(String userName) {
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher checker = p.matcher(userName);

        if (checker.find()) {
            userNameError = "invalid";
            return false;
        } else if (userName.contains(" ") || userName.equals("")) {
            userNameError = "invalid";
            return false;
        }
        else if (userName.length() < 3 || userName.length() > 20) {
            userNameError = "length";
            return false;
        }
        return true;
    }

    public static String getUserNameError() {
        return userNameError;
    }

    public int getPassword() {
        return password;
    }

    public static String getPasswordError() {
        return passwordError;
    }

    public static boolean isPasswordAcceptable(String password) {
        if(password.equals("") || password.contains(" ")) {
            passwordError = "invalid";
            return false;
        }
        else if(password.length() < 5) {
            passwordError = "length";
            return false;
        }
        return true;
    }


    public void addFollower(User user) {
        followers.add(user.getUserId());
    }

    public void addFollower(long id) {
        followers.add(id);
    }

    public void removeFollower(User user) {
        followers.remove(user.getUserId());
    }

    public void removeFollower(long id) {
        followers.remove(id);
    }

    public long gerFollower(int index) {
        return followers.get(index);
    }

    public int getFollowersSize(){
        return followers.size();
    }

    public void addFollowing(User user) {
        followings.add(user.getUserId());
    }

    public void addFollowing(long id) {
        followings.add(id);
    }

    public void removeFollowing(User user) {
        followings.remove(user.getUserId());
    }

    public void removeFollowing(long id) {
        followings.remove(id);
    }

    public long getFollowing(int index) {
        return followings.get(index);
    }

    public int getFollowingsSize(){
        return followings.size();
    }

    public long getUserId() {
        return userId;
    }

    public long generateId() {
        long temp = idGenerate;
        idGenerate++;
        return temp;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
