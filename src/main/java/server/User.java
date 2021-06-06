package server;

import javax.mail.MessagingException;
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
            System.out.println("invalid username");
            return false;
        } else if (userName.contains(" ") || userName.equals("")) {
            System.out.println("invalid username");
            return false;
        }
        else if (userName.length() < 3 || userName.length() > 20) {
            System.out.println("userName length must be between 4 and 20");
            return false;
        }
        return true;
    }
    //    public static boolean isUserNameUsed() {
    //
    //    }

    public int getPassword() {
        return password;
    }


    public static boolean isPasswordAcceptable(String password) {
        if(password.equals("") || password.contains(" ")) {
            System.out.println("invalid password");
            return false;
        }
        else if(password.length() < 5) {
            System.out.println("invalid password length");
            return false;
        }
        return true;
    }

    public String getEmail() {
        return email;
    }

    public String handleEmail(String inputCode) throws MessagingException {
        HandleEmail handleEmail = new HandleEmail(this.email);

        boolean isValid = handleEmail.isValidEmail();
        //boolean isRepeated = handleEmail.isEmailNotUsed(); // Database

        if(isValid) // && isRepeated
            return "email is not valid";
        else if(!handleEmail.isVerified(inputCode))
            return "wrong code!";
        else
            return "welcome";

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
